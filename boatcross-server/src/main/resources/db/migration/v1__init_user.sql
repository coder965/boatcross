create table bc_user (
  `uid` int(10) NOT NULL AUTO_INCREMENT COMMENT 'uid',
  `username` char(12) NOT NULL COMMENT '用户名',
  `name` varchar(64) NOT NULL COMMENT '姓名',
  `nickname` varchar(64) DEFAULT NULL COMMENT '花名',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `gitlab_private_token` varchar(255) DEFAULT NULL COMMENT 'gitlab私有token',
  `dingding_user_id` int(10) DEFAULT NULL COMMENT '钉钉用户id',
  `dingding_department_id` int(10) DEFAULT NULL COMMENT '钉钉部门id',
  `dingding_department_name` varchar(255) DEFAULT NULL COMMENT '钉钉部门名称',
  PRIMARY KEY (`uid`),
  UNIQUE KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息';