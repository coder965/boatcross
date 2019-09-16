package com.kelystor.boatcross.entity;

import java.io.Serializable;

public class JenkinsProject implements Serializable {
	/**
	 * id
	 */
	private Integer id;

	/**
	 * 名称
	 */
	private String name;

    /**
     * 阿里云容器名称
     */
	private String aliyunName;

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
     * 优先级
     */
	private Integer priority;

	/**
	 * 环境
	 */
	private String env;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAliyunName() {
        return aliyunName;
    }

    public void setAliyunName(String aliyunName) {
        this.aliyunName = aliyunName;
    }

    public String getGitRepositoryUrl() {
        return gitRepositoryUrl;
    }

    public void setGitRepositoryUrl(String gitRepositoryUrl) {
        this.gitRepositoryUrl = gitRepositoryUrl;
    }

    public String getGitRepositoryPath() {
        return gitRepositoryPath;
    }

    public void setGitRepositoryPath(String gitRepositoryPath) {
        this.gitRepositoryPath = gitRepositoryPath;
    }

    public String getTriggerToken() {
        return triggerToken;
    }

    public void setTriggerToken(String triggerToken) {
        this.triggerToken = triggerToken;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    @Override
    public String toString() {
        return "JenkinsProject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", aliyunName='" + aliyunName + '\'' +
                ", gitRepositoryUrl='" + gitRepositoryUrl + '\'' +
                ", gitRepositoryPath='" + gitRepositoryPath + '\'' +
                ", triggerToken='" + triggerToken + '\'' +
                ", priority=" + priority +
                ", env='" + env + '\'' +
                '}';
    }
}