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

CREATE TABLE `activity_reward` (
  `activity_reward_id` bigint(32) NOT NULL COMMENT '主键id',
  `stock_total` int(8) NOT NULL DEFAULT '0' COMMENT '总数',
  `stock_remain` int(8) NOT NULL DEFAULT '0' COMMENT '剩余数量',
  `stock_used` int(8) NOT NULL DEFAULT '0' COMMENT '已使用数量',
  `activity_id` bigint(32) NOT NULL COMMENT '关联的活动id',
  `reward_type` tinyint(2) NOT NULL COMMENT '类型',
  `reward_id` bigint(32) NOT NULL DEFAULT '0' COMMENT '关联的奖励对应id',
  `rule` varchar(500) NOT NULL DEFAULT '' COMMENT '奖励发放规则',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `op_time` bigint(20) NOT NULL COMMENT '更新时间',
  `is_valid` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `last_ver` int(8) NOT NULL DEFAULT '1' COMMENT '版本',
  `extend_field` varchar(500) DEFAULT '' COMMENT '扩展字段',
  PRIMARY KEY (`activity_reward_id`),
  KEY `idx_activity_id` (`activity_id`) USING BTREE,
  KEY `idx_reward_id` (`reward_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动奖励库存表';


CREATE TABLE `activity_record` (
  `activity_record_id` bigint(20) NOT NULL COMMENT '主键',
  `customer_register_id` varchar(32) NOT NULL COMMENT '用户ID',
  `activity_id` bigint(20) NOT NULL COMMENT '活动ID',
  `schedule` varchar(255) NOT NULL DEFAULT '' COMMENT '进度记录',
  `reward` varchar(255) NOT NULL DEFAULT '' COMMENT '奖励属性',
  `status` tinyint(4) NOT NULL COMMENT '是否有效',
  `extend_field` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展属性',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `op_time` bigint(20) NOT NULL COMMENT '操作时间',
  `last_ver` int(11) NOT NULL COMMENT '版本号',
  `is_valid` tinyint(4) NOT NULL COMMENT '是否有效',
  PRIMARY KEY (`activity_record_id`),
  KEY `idx_uid_ac_id` (`customer_register_id`,`activity_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动参与记录表';


CREATE TABLE `coupon_kind` (
  `coupon_kind_id` bigint(32) NOT NULL COMMENT '优惠券类型id',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '优惠名称',
  `coupon_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '优惠券类型',
  `price` int(11) NOT NULL DEFAULT '0' COMMENT '优惠金额',
  `effect_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '有效时间',
  `rules` varchar(500) NOT NULL DEFAULT '' COMMENT '规则列表，json串',
  `use_range` int(11) NOT NULL DEFAULT '1' COMMENT '适用范围,位运算表示 1<<0:预售,1<<1 堂食 1<<2 外卖',
  `extend_field` varchar(200) NOT NULL DEFAULT '' COMMENT '扩展字段列表，json串',
  `is_valid` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否有效',
  `last_ver` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
  `create_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间毫秒',
  `op_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改时间毫秒',
  PRIMARY KEY (`coupon_kind_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板表';

CREATE TABLE `coupon_customer` (
  `coupon_customer_id` bigint(32) NOT NULL COMMENT '优惠券id',
  `customer_register_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户id',
  `coupon_kind_id` bigint(32) NOT NULL DEFAULT '0' COMMENT '优惠券模板id',
  `coupon_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '优惠券类型',
  `price` int(11) NOT NULL DEFAULT '0' COMMENT '优惠券金额',
  `activity_id` bigint(32) NOT NULL DEFAULT '0' COMMENT '活动id',
  `activity_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '活动类型',
  `channel` varchar(32) NOT NULL DEFAULT '' COMMENT '领取渠道',
  `effective_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '生效时间',
  `expire_time` bigint(11) NOT NULL DEFAULT '0' COMMENT '过期时间',
  `status` tinyint(2) NOT NULL COMMENT '状态，1:未使用，2:已使用，3:已过期',
  `extend_field` varchar(500) NOT NULL DEFAULT '' COMMENT '扩展字段列表，json串',
  `is_valid` tinyint(2) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `last_ver` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
  `create_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间毫秒',
  `op_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '操作时间毫秒',
  PRIMARY KEY (`coupon_customer_id`),
  KEY `idx_customer_register_id` (`customer_register_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户领取记录表';

CREATE TABLE `coupon_consume_detail` (
  `coupon_consume_detail_id` bigint(32) NOT NULL COMMENT '主键ID',
  `customer_register_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户id',
  `coupon_customer_id` bigint(32) NOT NULL DEFAULT '0' COMMENT '优惠券id',
  `price` int(11) NOT NULL DEFAULT '0' COMMENT '优惠券金额',
  `deduct_fee` int(11) NOT NULL DEFAULT '0' COMMENT '实际抵扣金额',
  `order_id` varchar(32) NOT NULL DEFAULT '' COMMENT '订单id',
  `snapshot_id` varchar(32) NOT NULL DEFAULT '' COMMENT '支付快照Id',
  `is_valid` tinyint(2) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `last_ver` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `create_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间毫秒',
  `op_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '所属活动id',
  `entity_id` varchar(32) NOT NULL DEFAULT '' COMMENT '店铺ID',
  PRIMARY KEY (`coupon_consume_detail_id`),
  KEY `idx_coupon_customer_id` (`coupon_customer_id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券消费记录表'


CREATE TABLE `activity_channel_relation` (
  `activity_channel_relation_id` bigint(20) NOT NULL COMMENT '主键',
  `activity_id` bigint(20) NOT NULL COMMENT '活动ID',
  `promotion_channel_id` bigint(20) NOT NULL COMMENT '推广渠道ID',
  `activity_url` varchar(255) NOT NULL COMMENT '活动推广链接',
  `extend_field` varchar(500) NOT NULL DEFAULT '' COMMENT '扩展属性',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `op_time` bigint(20) NOT NULL COMMENT '操作时间',
  `last_ver` int(8) NOT NULL COMMENT '版本号',
  `is_valid` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否有效（0-失效 1-有效）',
  PRIMARY KEY (`activity_channel_relation_id`),
  KEY `idx_activity_id_pc_id` (`activity_id`,`promotion_channel_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='活动渠道关联表';

CREATE TABLE `promotion_channel` (
  `promotion_channel_id` bigint(20) NOT NULL COMMENT '主键',
  `channel_name` varchar(50) NOT NULL COMMENT '渠道名称',
  `channel_category` varchar(50) NOT NULL COMMENT '渠道分类',
  `remark` varchar(255) NOT NULL COMMENT '备注',
  `status` tinyint(4) NOT NULL COMMENT '状态（1-启用 2-停用）',
  `operator` varchar(50) NOT NULL COMMENT '操作者',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `op_time` bigint(20) NOT NULL COMMENT '操作时间',
  `is_valid` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否有效（0-失效 1-有效）',
  PRIMARY KEY (`promotion_channel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='推广渠道配置表';




