create table bc_jenkins_project (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) NOT NULL COMMENT '姓名',
  `git_repository_url` varchar(255) DEFAULT NULL COMMENT 'git版本库地址',
  `git_repository_path` varchar(255) DEFAULT NULL COMMENT 'git版本库路径',
  `trigger_token` varchar(255) DEFAULT NULL COMMENT '触发构建的token',
  `env` varchar(255) DEFAULT NULL COMMENT '环境',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Jenkins项目';