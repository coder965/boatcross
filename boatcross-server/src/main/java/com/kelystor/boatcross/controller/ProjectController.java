package com.kelystor.boatcross.controller;

import com.kelystor.boatcross.dao.JenkinsProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private JenkinsProjectMapper jenkinsProjectMapper;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("projects", jenkinsProjectMapper.findAll());
        return "project";
    }

}
