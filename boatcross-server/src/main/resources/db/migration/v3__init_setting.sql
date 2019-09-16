create table bc_setting (
  `key` varchar(64) NOT NULL COMMENT '配置项',
  `value` varchar(1024) NOT NULL COMMENT '配置值',
  `env` varchar(20) DEFAULT NULL COMMENT '环境'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='配置信息';