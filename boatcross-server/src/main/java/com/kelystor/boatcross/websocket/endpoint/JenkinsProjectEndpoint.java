package com.kelystor.boatcross.websocket.endpoint;

import com.kelystor.boatcross.config.BcSpringConfigurator;
import com.kelystor.boatcross.entity.JenkinsProject;
import com.kelystor.boatcross.service.JenkinsProjectEvent;
import com.kelystor.boatcross.service.JenkinsProjectService;
import com.kelystor.boatcross.util.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@ServerEndpoint(
        value = "/jenkins-project",
        configurator = BcSpringConfigurator.class,
        encoders = JenkinsDeployResultEncoder.class,
        decoders = JenkinsDeployRequestDecoder.class
)
@Component
public class JenkinsProjectEndpoint {
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    @Autowired
    private JenkinsProjectService jenkinsProjectService;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param deployRequest 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(JenkinsDeployRequest deployRequest, Session session) {
        jenkinsProjectService.build(deployRequest.getProjects(), ContextUtil.currentDeployEnvironment(), new JenkinsProjectEvent() {
            @Override
            public void onSorted(List<JenkinsProject> projects) {
                for (int i = 0; i < projects.size(); i++) {
                    JenkinsDeployResult result = JenkinsDeployResult.build(projects.get(i).getName(), String.format("等待中（顺序%s）", i + 1));
                    sendMessage(result);
                }
            }

            @Override
            public void onTriggerBuild(JenkinsProject project) {
                JenkinsDeployResult result = JenkinsDeployResult.build(project.getName(), "正在触发构建");
                sendMessage(result);
            }

            @Override
            public void onBuilding(JenkinsProject project) {
                JenkinsDeployResult result = JenkinsDeployResult.build(project.getName(), String.format("构建中%s", stringRepeat(".", ThreadLocalRandom.current().nextInt(5) + 1)));
                sendMessage(result);
            }

            @Override
            public void onFinishBuild(JenkinsProject project) {
                JenkinsDeployResult result = JenkinsDeployResult.build(project.getName(), "构建完成");
                sendMessage(result);
            }

            @Override
            public void onBuildSuccess(JenkinsProject project, String newVersion) {
                JenkinsDeployResult result = JenkinsDeployResult.build(project.getName(), newVersion == null ? "构建成功，但没有版本号" : "构建完成，版本号 " + newVersion);
                sendMessage(result);
            }

            @Override
            public void onBuildFailed(JenkinsProject project, String message) {
                JenkinsDeployResult result = JenkinsDeployResult.build(project.getName(), message);
                sendMessage(result);
            }

            @Override
            public void onBuildError(JenkinsProject project, String message) {
                JenkinsDeployResult result = JenkinsDeployResult.build(project.getName(), "构建出错：" + message);
                sendMessage(result);
            }

            @Override
            public void onComplete() {
                JenkinsDeployResult result = JenkinsDeployResult.complete();
                sendMessage(result);
            }
        });
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    private void sendMessage(JenkinsDeployResult result) {
        try {
            this.session.getBasicRemote().sendObject(result);
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }

    private String stringRepeat(String str, int times) {
        return String.join("", Collections.nCopies(times, str));
    }

}