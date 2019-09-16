package com.kelystor.boatcross.controller;

import com.kelystor.boatcross.dao.JenkinsProjectMapper;
import com.kelystor.boatcross.entity.JenkinsProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/jenkins")
public class JenkinsController {
    @Autowired
    private JenkinsProjectMapper jenkinsProjectMapper;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "jenkins/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(JenkinsProject jenkinsProject) {
        jenkinsProjectMapper.save(jenkinsProject);
        return "redirect:/jenkins/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("projects", jenkinsProjectMapper.findAll());
        return "jenkins/list";
    }
}
