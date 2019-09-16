
package com.kelystor.boatcross.controller;

import com.kelystor.boatcross.entity.JenkinsConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/setting")
public class SettingController {
    @RequestMapping
    public String list() {
        return "setting/list";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(
            @ModelAttribute(name = "jenkins") JenkinsConfig jenkinsConfig
    ) {
        return "setting/list";
    }

}
