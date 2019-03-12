CREATE TABLE `activity_config` (
  `activity_config_id` bigint(20) NOT NULL COMMENT '主键',
  `activity_code` varchar(16) NOT NULL COMMENT '活动编号',
  `activity_title` varchar(50) NOT NULL DEFAULT '' COMMENT '活动标题',
  `activity_type` tinyint(4) NOT NULL COMMENT '活动类型',
  `creator` varchar(50) NOT NULL COMMENT '活动创建人',
  `operator` varchar(50) NOT NULL COMMENT '活动操作人',
  `env` varchar(16) NOT NULL COMMENT '环境 dev pre publish daily',
  `publisher` varchar(50) NOT NULL COMMENT '活动发布人',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '活动状态（1-待发布 2-进行中 3-已暂停 4-已终止 5-已过期）',
  `start_time` bigint(20) NOT NULL COMMENT '开始时间',
  `end_time` bigint(20) NOT NULL COMMENT '结束时间',
  `publish_time` bigint(20) NOT NULL COMMENT '发布时间',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `op_time` bigint(20) NOT NULL COMMENT '更新时间',
  `is_valid` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `last_ver` int(8) NOT NULL DEFAULT '1' COMMENT '版本号',
  `description` varchar(5120) NOT NULL DEFAULT '' COMMENT '活动的一些描述',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '活动备注',
  `extend_field` varchar(500) NOT NULL DEFAULT '' COMMENT '扩展字段列表，json串',
  PRIMARY KEY (`activity_config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动配置表';



