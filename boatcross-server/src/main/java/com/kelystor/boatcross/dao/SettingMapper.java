package com.kelystor.boatcross.dao;


import com.kelystor.boatcross.entity.Setting;
import com.kelystor.boatcross.enums.DeployEnvironment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SettingMapper {
    List<Setting> findAll();

    Optional<Setting> findByKeyAndEnvironment(String key, DeployEnvironment environment);

    void save(Setting setting);
    
    int update(Setting setting);
}