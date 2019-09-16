package com.kelystor.boatcross.service;

import com.kelystor.boatcross.dao.JenkinsProjectMapper;
import com.kelystor.boatcross.entity.JenkinsConfig;
import com.kelystor.boatcross.entity.JenkinsProject;
import com.kelystor.boatcross.enums.DeployEnvironment;
import com.kelystor.boatcross.vendor.exception.ApiRequestException;
import com.kelystor.boatcross.vendor.jenkins.JenkinsAPI;
import com.kelystor.boatcross.vendor.jenkins.model.JenkinsBuildResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JenkinsProjectService {
    @Autowired
    private SettingService settingService;
    @Autowired
    private JenkinsProjectMapper jenkinsProjectMapper;

    public void build(final List<String> projects, final DeployEnvironment deployEnvironment, final JenkinsProjectEvent event) {
        List<JenkinsProject> projectList = jenkinsProjectMapper.findByProjectNames(projects, deployEnvironment);
        JenkinsConfig jenkinsConfig = settingService.getJenkinsConfig(deployEnvironment);
        JenkinsAPI jenkinsAPI = buildJenkinsAPIFromConfig(jenkinsConfig);

        event.onSorted(projectList);

        JenkinsProject currentProject = null;
        try {
            for (JenkinsProject project : projectList) {
                currentProject = project;

                event.onTriggerBuild(project);

                // 先获取最新的构建ID，后续触发后再获取一次，如果一致，说明还没有触发构建
                JenkinsBuildResult lastBuildResult = jenkinsAPI.getLastBuildResult(project.getName());
                // 触发构建
                jenkinsAPI.triggerBuild(project.getName(), project.getTriggerToken());

                Thread.sleep(3000);

                // 轮询检测是否构建完成
                JenkinsBuildResult buildingResult;
                while (true) {
                    buildingResult = jenkinsAPI.getLastBuildResult(project.getName());
                    if (!lastBuildResult.getNumber().equals(buildingResult.getNumber())) {
                        // 查看是否构建完成
                        if (buildingResult.getBuilding()) {
                            event.onBuilding(project);
                        } else {
                            event.onFinishBuild(project);
                            break;
                        }
                    }

                    Thread.sleep(3000);
                }

                if (buildingResult.isSuccess()) {
                    String lastBuildVersion = jenkinsAPI.getLastBuildVersion(project.getName());
                    event.onBuildSuccess(project, lastBuildVersion);
                } else {
                    event.onBuildFailed(project, "构建失败，请查看console输出获取出错信息");
                }
            }
        } catch (InterruptedException ignore) {
        } catch (ApiRequestException e) {
            event.onBuildError(currentProject, e.getMessage());
        } finally {
            event.onComplete();
        }
    }

    private JenkinsAPI buildJenkinsAPIFromConfig(JenkinsConfig jenkinsConfig) {
        return JenkinsAPI.connect(jenkinsConfig.getUrl(), jenkinsConfig.getUsername(), jenkinsConfig.getApiToken());
    }
}
