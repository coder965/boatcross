package com.kelystor.boatcross.entity;

import java.io.Serializable;

public class User implements Serializable {
	/**
	 * uid
	 */
	private Integer uid;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 花名
	 */
	private String nickname;

	/**
	 * 头像
	 */
	private String avatar;

	/**
	 * gitlab私有token
	 */
	private String gitlabPrivateToken;

	/**
	 * 钉钉用户id
	 */
	private String dingdingUserId;

	/**
	 * 钉钉部门id
	 */
	private Integer dingdingDepartmentId;

	/**
	 * 钉钉部门名称
	 */
	private String dingdingDepartmentName;

}