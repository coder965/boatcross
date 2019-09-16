package com.kelystor.boatcross.entity;

import com.kelystor.boatcross.enums.DeployEnvironment;

import java.io.Serializable;

public class Setting implements Serializable {

	/**
	 * 配置项
	 */
	private String key;

	/**
	 * 配置值
	 */
	private String value;

	/**
	 * 环境
	 */
	private DeployEnvironment env;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DeployEnvironment getEnv() {
        return env;
    }

    public void setEnv(DeployEnvironment env) {
        this.env = env;
    }
}