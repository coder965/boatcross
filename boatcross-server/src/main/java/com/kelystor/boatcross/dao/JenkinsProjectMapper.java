package com.kelystor.boatcross.dao;


import com.kelystor.boatcross.entity.JenkinsProject;
import com.kelystor.boatcross.enums.DeployEnvironment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JenkinsProjectMapper {
    List<JenkinsProject> findAll();

    void save(JenkinsProject jenkinsProject);

    int update(JenkinsProject jenkinsProject);

    List<JenkinsProject> findByProjectNames(List<String> projects, DeployEnvironment environment);

    List<JenkinsProject> findByEnvironments(DeployEnvironment environment);
}