package com.kelystor.boatcross.controller;

import com.kelystor.boatcross.annotation.CurrentDeployEnvironment;
import com.kelystor.boatcross.dao.JenkinsProjectMapper;
import com.kelystor.boatcross.entity.JenkinsConfig;
import com.kelystor.boatcross.enums.DeployEnvironment;
import com.kelystor.boatcross.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private JenkinsProjectMapper jenkinsProjectMapper;
    @Autowired
    private SettingService settingService;

    @RequestMapping
    public String index(Model model, @CurrentDeployEnvironment DeployEnvironment deployEnvironment) {
        JenkinsConfig jenkinsConfig = settingService.getJenkinsConfig(deployEnvironment);

        model.addAttribute("jenkinsConfig", jenkinsConfig);
        model.addAttribute("projects", jenkinsProjectMapper.findByEnvironments(deployEnvironment));
        return "project/index";
    }

}
