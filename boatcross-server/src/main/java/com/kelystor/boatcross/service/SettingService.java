package com.kelystor.boatcross.service;

import com.kelystor.boatcross.dao.SettingMapper;
import com.kelystor.boatcross.entity.JenkinsConfig;
import com.kelystor.boatcross.entity.Setting;
import com.kelystor.boatcross.enums.DeployEnvironment;
import com.kelystor.boatcross.mapper.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SettingService {
    @Autowired
    private SettingMapper settingMapper;
    @Autowired
    private JsonMapper jsonMapper;

    public JenkinsConfig getJenkinsConfig(DeployEnvironment environment) {
        Optional<Setting> setting = settingMapper.findByKeyAndEnvironment("jenkins", environment);
        return setting.map(settingValue -> jsonMapper.toObject(settingValue.getValue(), JenkinsConfig.class)).orElse(null);
    }
}
