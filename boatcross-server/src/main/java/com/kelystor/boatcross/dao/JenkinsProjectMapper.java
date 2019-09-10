package com.kelystor.boatcross.dao;


import com.kelystor.boatcross.entity.JenkinsProject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JenkinsProjectMapper {
    List<JenkinsProject> findAll();
}