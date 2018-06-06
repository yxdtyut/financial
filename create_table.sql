CREATE TABLE `product` (
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '产品编号',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '产品名称',
  `threshold_amount` decimal(15,3) NOT NULL COMMENT '起投金额',
  `step_amount` decimal(15,3) NOT NULL COMMENT '投资步长',
  `lock_term` smallint(6) NOT NULL COMMENT '锁定期',
  `reward_rate` decimal(5,3) NOT NULL COMMENT '收益率，0-100 百分比值',
  `STATUS` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '状态,audinting:审核中,in_sell:销售中,locked:暂停销售,finished:已结束',
  `memo` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `create_at` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` datetime DEFAULT NULL COMMENT '创建者',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` datetime DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE `order_t` (
  `order_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '订单编号',
  `chan_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '渠道编号',
  `product_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '产品编号',
  `chan_user_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '渠道用户编号',
  `order_type` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '类型,APPLY:申购,REDEEM:赎回',
  `order_status` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '状态,INIT:初始化,PROCESS:处理中,SUCCESS:成功,FAIL:失败	',
  `outer_order_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '外部订单编号',
  `amount` decimal(15,3) NOT NULL COMMENT '金额',
  `memo` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `create_at` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建时间',
  `update_at` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;