package com.kelystor.boatcross.entity;

import java.io.Serializable;

public class JenkinsProject implements Serializable {
	/**
	 * id
	 */
	private Integer id;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * git版本库地址
	 */
	private String gitRepositoryUrl;

	/**
	 * git版本库路径
	 */
	private String gitRepositoryPath;

	/**
	 * 触发构建的token
	 */
	private String triggerToken;

	/**
	 * 环境
	 */
	private String env;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGitRepositoryUrl() {
        return gitRepositoryUrl;
    }

    public String getGitRepositoryPath() {
        return gitRepositoryPath;
    }

    public String getTriggerToken() {
        return triggerToken;
    }

    public String getEnv() {
        return env;
    }
}