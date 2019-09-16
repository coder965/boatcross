create table bc_jenkins_project (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `aliyun_name` varchar(64) DEFAULT NULL COMMENT '阿里云容器名称',
  `git_repository_url` varchar(255) DEFAULT NULL COMMENT 'git版本库地址',
  `git_repository_path` varchar(255) DEFAULT NULL COMMENT 'git版本库路径',
  `trigger_token` varchar(255) DEFAULT NULL COMMENT '触发构建的token',
  `priority` smallint(3) DEFAULT NULL COMMENT '优先级（数字越小越优先）',
  `env` varchar(20) NOT NULL DEFAULT '' COMMENT '环境',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Jenkins项目';