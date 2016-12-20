-- --------------------------------------------------------
-- 主机:                           121.196.234.79
-- 服务器版本:                        5.7.14 - MySQL Community Server (GPL)
-- 服务器操作系统:                      linux-glibc2.5
-- HeidiSQL 版本:                  9.3.0.5114
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 duobao_fly.databasechangelog 结构
CREATE TABLE IF NOT EXISTS `databasechangelog` (
	`ID` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
	`AUTHOR` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
	`FILENAME` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
	`DATEEXECUTED` datetime NOT NULL,
	`ORDEREXECUTED` int(11) NOT NULL,
	`EXECTYPE` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
	`MD5SUM` varchar(35) COLLATE utf8_unicode_ci DEFAULT NULL,
	`DESCRIPTION` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
	`COMMENTS` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
	`TAG` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
	`LIQUIBASE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
	`CONTEXTS` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
	`LABELS` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。
-- 导出  表 duobao_fly.DATABASECHANGELOG 结构
CREATE TABLE IF NOT EXISTS `DATABASECHANGELOG` (
	`ID` varchar(255) NOT NULL,
	`AUTHOR` varchar(255) NOT NULL,
	`FILENAME` varchar(255) NOT NULL,
	`DATEEXECUTED` datetime NOT NULL,
	`ORDEREXECUTED` int(11) NOT NULL,
	`EXECTYPE` varchar(10) NOT NULL,
	`MD5SUM` varchar(35) DEFAULT NULL,
	`DESCRIPTION` varchar(255) DEFAULT NULL,
	`COMMENTS` varchar(255) DEFAULT NULL,
	`TAG` varchar(255) DEFAULT NULL,
	`LIQUIBASE` varchar(20) DEFAULT NULL,
	`CONTEXTS` varchar(255) DEFAULT NULL,
	`LABELS` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 duobao_fly.databasechangeloglock 结构
CREATE TABLE IF NOT EXISTS `databasechangeloglock` (
	`ID` int(11) NOT NULL,
	`LOCKED` bit(1) NOT NULL,
	`LOCKGRANTED` datetime DEFAULT NULL,
	`LOCKEDBY` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
	PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 数据导出被取消选择。
-- 导出  表 duobao_fly.DATABASECHANGELOGLOCK 结构
CREATE TABLE IF NOT EXISTS `DATABASECHANGELOGLOCK` (
	`ID` int(11) NOT NULL,
	`LOCKED` bit(1) NOT NULL,
	`LOCKGRANTED` datetime DEFAULT NULL,
	`LOCKEDBY` varchar(255) DEFAULT NULL,
	PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 duobao_fly.t_activity 结构
CREATE TABLE IF NOT EXISTS `t_activity` (
	`activity_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '活动ID',
	`category_id` INT(11) NOT NULL COMMENT '活动分类ID',
	`name` VARCHAR(50) NOT NULL COMMENT '活动名称',
	`content` VARCHAR(1000) NULL DEFAULT NULL COMMENT '活动内容',
	`enabled` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '活动状态：1-上架、0-下架',
	`create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`activity_id`)
)
	COMMENT='活动表'
	COLLATE='utf8_general_ci'
	ENGINE=InnoDB
;


-- 数据导出被取消选择。
-- 导出  表 duobao_fly.t_activity_category 结构
CREATE TABLE `t_activity_category` (
	`category_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '活动分类ID',
	`name` VARCHAR(50) NOT NULL COMMENT '分类名称',
	`img` VARCHAR(50) NULL DEFAULT NULL COMMENT '分类图片',
	`enabled` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '分类状态：1-上架、0-下架',
	`create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`category_id`),
	UNIQUE INDEX `type_name` (`name`)
)
	COMMENT='活动分类表'
	COLLATE='utf8_general_ci'
	ENGINE=InnoDB
	AUTO_INCREMENT=2
;


-- 数据导出被取消选择。
-- 导出  表 duobao_fly.t_area 结构
CREATE TABLE IF NOT EXISTS `t_area` (
	`area_id` bigint(24) NOT NULL,
	`parent_id` bigint(24) DEFAULT NULL,
	`name` varchar(50) DEFAULT NULL,
	`province` varchar(50) DEFAULT NULL,
	`city` varchar(50) DEFAULT NULL,
	`district` varchar(50) DEFAULT NULL,
	`longitude` varchar(20) DEFAULT NULL,
	`latitude` varchar(20) DEFAULT NULL,
	PRIMARY KEY (`area_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地区表';

-- 数据导出被取消选择。
-- 导出  表 duobao_fly.t_goods 结构
CREATE TABLE IF NOT EXISTS `t_goods` (
	`goods_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
	`type_id` int(11) NOT NULL COMMENT '商品类型ID',
	`goods_name` varchar(50) NOT NULL COMMENT '商品名称',
	`goods_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '商品状态：0-下架、1-上架',
	`channel` int(11) NOT NULL DEFAULT '0' COMMENT '商品频道（0，普通 1，爆款 2，新货）',
	`total_amount` int(11) NOT NULL COMMENT '总需人数',
	`single_price` int(11) NOT NULL COMMENT '单次购买价格',
	`statement` varchar(1000) NOT NULL DEFAULT '0' COMMENT '夺宝声明',
	`img` varchar(200) NOT NULL COMMENT '商品图片（多图）',
	`img_detail` varchar(200) DEFAULT NULL COMMENT '图文详情',
	`create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`goods_id`),
	UNIQUE KEY `goods_name` (`goods_name`),
	KEY `FK_commodity_commodity_type` (`type_id`),
	CONSTRAINT `FK_commodity_commodity_type` FOREIGN KEY (`type_id`) REFERENCES `t_goods_type` (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='商品表';

-- 数据导出被取消选择。
-- 导出  表 duobao_fly.t_goods_bid 结构
CREATE TABLE IF NOT EXISTS `t_goods_bid` (
	`bid_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品竞购ID',
	`goods_id` int(11) NOT NULL COMMENT '商品ID',
	`total_amount` int(11) NOT NULL COMMENT '总参与人次（竞购总价格）',
	`join_amount` int(11) NOT NULL DEFAULT '0' COMMENT '已参与人次（剩余人次=总参与人次 - 已参与人次）',
	`bid_status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '竞购状态：0-未上架、1-进行中、2-中断 3、待揭晓 4、已揭晓',
	`winner_id` int(11) DEFAULT NULL COMMENT '获取用户ID',
	`lucky_num` int(11) DEFAULT NULL COMMENT '幸运号码',
	`single_price` int(11) NOT NULL COMMENT '单笔金额',
	`publish_time` timestamp NULL DEFAULT NULL COMMENT '揭晓时间',
	`create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`bid_id`),
	KEY `FK_goods_bid_goods` (`goods_id`),
	CONSTRAINT `FK_goods_bid_goods` FOREIGN KEY (`goods_id`) REFERENCES `t_goods` (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='商品竞购表';

-- 数据导出被取消选择。
-- 导出  表 duobao_fly.t_goods_type 结构
CREATE TABLE IF NOT EXISTS `t_goods_type` (
	`type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品类型ID',
	`type_name` varchar(20) NOT NULL COMMENT '商品类型名称',
	`type_img` varchar(50) DEFAULT NULL COMMENT '商品类型图片',
	`status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '商品类型状态：1-上架、0-下架',
	`create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
# 	`create_id` int(11) DEFAULT NULL COMMENT '创建者ID',
	`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
# 	`update_id` int(11) DEFAULT NULL COMMENT '更新者ID',
	PRIMARY KEY (`type_id`),
	UNIQUE KEY `type_name` (`type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='商品类型表';

-- 数据导出被取消选择。
-- 导出  表 duobao_fly.t_integral 结构
CREATE TABLE IF NOT EXISTS `t_integral` (
	`integral_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '积分明细ID',
	`user_id` int(11) NOT NULL COMMENT '用户ID',
	`integral_type` tinyint(2) NOT NULL COMMENT '积分类型：0-签到、1-新手任务',
	`in_out` tinyint(1) NOT NULL COMMENT '积分来源或消耗：1-增加、0-消耗',
	`amount` int(11) NOT NULL COMMENT '单次积分值',
	`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
# 	`create_id` int(11) DEFAULT NULL COMMENT '创建者ID',
	PRIMARY KEY (`integral_id`),
	KEY `FK_user_integral_user` (`user_id`),
	CONSTRAINT `FK_user_integral_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分明细表';

-- 数据导出被取消选择。
-- 导出  表 duobao_fly.t_message 结构
CREATE TABLE IF NOT EXISTS `t_message` (
	`message_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息ID',
	`user_id` int(11) DEFAULT NULL COMMENT '用户ID',
	`title` varchar(50) DEFAULT NULL COMMENT '标题',
	`content` varchar(200) DEFAULT NULL COMMENT '内容',
	`message_type` tinyint(2) NOT NULL COMMENT '消息类型：0-活动、1-中奖、2-发货、3-客服、4-系统',
	`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
# 	`create_id` int(11) DEFAULT NULL COMMENT '创建者ID',
	PRIMARY KEY (`message_id`),
	KEY `FK_t_message_t_user` (`user_id`),
	CONSTRAINT `FK_t_message_t_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息表';

-- 数据导出被取消选择。
-- 导出  表 duobao_fly.t_moment 结构
CREATE TABLE IF NOT EXISTS `t_moment` (
	`moment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '动态ID',
	`user_id` int(11) NOT NULL COMMENT '用户ID',
	`bid_id` int(11) NOT NULL COMMENT '商品竞购ID',
	`img` varchar(200) DEFAULT NULL COMMENT '图片',
	`create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
# 	`create_id` int(11) DEFAULT NULL COMMENT '创建者ID',
	PRIMARY KEY (`moment_id`),
	KEY `FK_t_moment_t_user` (`user_id`),
	KEY `FK_t_moment_t_goods_bid` (`bid_id`),
	CONSTRAINT `FK_t_moment_t_goods_bid` FOREIGN KEY (`bid_id`) REFERENCES `t_goods_bid` (`bid_id`),
	CONSTRAINT `FK_t_moment_t_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='晒单动态表';

-- 数据导出被取消选择。
-- 导出  表 duobao_fly.t_red_pocket 结构
CREATE TABLE IF NOT EXISTS `t_red_pocket` (
	`pocket_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '红包ID',
	`user_id` int(11) NOT NULL COMMENT '用户ID',
	`pocket_name` varchar(50) DEFAULT NULL COMMENT '红包名称',
	`pocket_status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '红包状态：1-可使用、0-过期已使用',
	`create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
# 	`create_id` int(11) DEFAULT NULL COMMENT '创建者ID',
	`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
# 	`update_id` int(11) DEFAULT NULL COMMENT '更新者ID',
	PRIMARY KEY (`pocket_id`),
	KEY `FK_red_pocket_user` (`user_id`),
	CONSTRAINT `FK_red_pocket_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='红包明细表';

-- 数据导出被取消选择。
-- 导出  表 duobao_fly.t_shipping_address 结构
CREATE TABLE IF NOT EXISTS `t_shipping_address` (
	`address_id` int(11) NOT NULL AUTO_INCREMENT,
	`user_id` int(11) NOT NULL DEFAULT '0',
	`name` varchar(50) NOT NULL,
	`cellphone` varchar(50) NOT NULL,
	`base_address` varchar(50) NOT NULL,
	`address` varchar(50) NOT NULL,
	`province_id` bigint(20) NOT NULL,
	`city_id` bigint(20) NOT NULL,
	`prefecture_id` bigint(20) NOT NULL,
	`street_id` bigint(20) DEFAULT NULL,
	`is_default` tinyint(1) NOT NULL DEFAULT '0',
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='地址';

-- 数据导出被取消选择。
-- 导出  表 duobao_fly.t_shipping_record 结构
CREATE TABLE IF NOT EXISTS `t_shipping_record` (
	`id` int(11) NOT NULL COMMENT 'ID',
	`user_id` int(11) NOT NULL COMMENT '用户ID',
	`goods_id` int(11) NOT NULL COMMENT '商品ID',
	`bid_id` int(11) NOT NULL COMMENT '期数',
	`lucky_num` int(11) NOT NULL COMMENT '幸运号码',
	`receiver_name` varchar(50) NOT NULL COMMENT '收货人姓名',
	`cellphone` varchar(50) NOT NULL COMMENT '手机号码',
	`shipping_address` varchar(200) NOT NULL COMMENT '收货地址',
	`shipping_status` tinyint(2) NOT NULL COMMENT '状态',
	`publish_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '中奖时间',
	`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发货记录表';

-- 数据导出被取消选择。
-- 导出  表 duobao_fly.t_shopping_cart 结构
CREATE TABLE IF NOT EXISTS `t_shopping_cart` (
	`cart_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
	`user_id` int(11) NOT NULL COMMENT '用户ID',
	`goods_id` int(11) NOT NULL COMMENT '商品ID',
	`update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`choose_amount` int(11) DEFAULT '1' COMMENT '选购数量',
	PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=143 DEFAULT CHARSET=utf8 COMMENT='购物车';

-- 数据导出被取消选择。
-- 导出  表 duobao_fly.t_shopping_log 结构
CREATE TABLE IF NOT EXISTS `t_shopping_log` (
	`item_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '条目ID',
	`user_id` int(11) NOT NULL COMMENT '用户ID',
	`bid_id` int(11) NOT NULL COMMENT '商品竞购ID',
	`amount` int(11) NOT NULL DEFAULT '0' COMMENT '数量',
	`goods_id` int(11) NOT NULL COMMENT '商品ID',
	`bid_nums` varchar(1000) NOT NULL COMMENT '购买号码（以逗号分隔）',
	`user_ip` varchar(20) NOT NULL COMMENT '用户Ip',
	`ip_address` varchar(20) NOT NULL COMMENT 'ip地址',
	`selected` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否被选中',
	`create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`item_id`),
	KEY `FK_t_shopping_cart_t_user` (`user_id`),
	KEY `FK_t_shopping_cart_t_goods_bid` (`bid_id`),
	CONSTRAINT `FK_t_shopping_cart_t_goods_bid` FOREIGN KEY (`bid_id`) REFERENCES `t_goods_bid` (`bid_id`),
	CONSTRAINT `FK_t_shopping_cart_t_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='购物记录表';

-- 数据导出被取消选择。
-- 导出  表 duobao_fly.t_sms_code 结构
CREATE TABLE IF NOT EXISTS `t_sms_code` (
	`cellphone` varchar(11) NOT NULL COMMENT '手机号',
	`code` varchar(255) NOT NULL COMMENT '验证码',
	`receive_time` datetime NOT NULL COMMENT '最近一次发送接收验证码的时间',
	`times` int(11) DEFAULT NULL COMMENT '每日发送验证码次数',
	`verified` tinyint(1) DEFAULT NULL COMMENT '是否验证：1-已验证、0-未验证',
	`code_type` tinyint(2) NOT NULL COMMENT '验证码类型',
	`create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`cellphone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信验证码表';

-- 数据导出被取消选择。
-- 导出  表 duobao_fly.t_system_user 结构
CREATE TABLE IF NOT EXISTS `t_system_user` (
	`user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
	`username` varchar(50) NOT NULL COMMENT '用户名',
	`password` varchar(50) NOT NULL COMMENT '密码',
	PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- 数据导出被取消选择。
-- 导出  表 duobao_fly.t_test 结构
CREATE TABLE IF NOT EXISTS `t_test` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`age` int(11) DEFAULT NULL,
	`name` varchar(50) DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 duobao_fly.t_trade 结构
CREATE TABLE IF NOT EXISTS `t_trade` (
	`trade_id` int(11) NOT NULL AUTO_INCREMENT,
	`user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户Id',
	`trade_no` varchar(50) NOT NULL,
	`trade_source` tinyint(2) NOT NULL COMMENT '1.支付宝 2.微信',
	`trade_status` tinyint(2) NOT NULL COMMENT '-2,支付失败 -1支付取消 0待支付 1支付成功',
	`trade_type` tinyint(2) NOT NULL COMMENT '1.点券充值 2.购买商品',
	`keyword` varchar(50) DEFAULT NULL COMMENT '订单关键字',
	`trade_info` varchar(50) DEFAULT NULL COMMENT '订单信息',
	`description` varchar(50) DEFAULT NULL COMMENT '订单描述',
	`pay_msg` varchar(50) DEFAULT NULL COMMENT '支付回调结果信息',
	`amount` varchar(50) DEFAULT NULL COMMENT '交易金额',
	`create_time` datetime DEFAULT CURRENT_TIMESTAMP,
	`update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`trade_id`),
	UNIQUE KEY `trade_no` (`trade_no`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='交易记录表';

-- 数据导出被取消选择。
-- 导出  表 duobao_fly.t_user 结构
CREATE TABLE IF NOT EXISTS `t_user` (
	`user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
	`username` varchar(50) DEFAULT NULL COMMENT '用户名',
	`password` varchar(50) DEFAULT NULL COMMENT '密码',
	`spread_id` varchar(20) NOT NULL COMMENT '推广ID',
	`nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
	`avatar` varchar(200) DEFAULT NULL COMMENT '用户头像',
	`coin` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '抢币（虚拟货币）',
	`integral` int(11) NOT NULL DEFAULT '0' COMMENT '积分',
	`cellphone` varchar(11) DEFAULT NULL COMMENT '手机号码',
	`qq` varchar(11) DEFAULT NULL COMMENT '用户QQ',
	`wx_id` varchar(50) DEFAULT NULL,
	`wb_id` varchar(50) DEFAULT NULL,
	`qq_id` varchar(50) DEFAULT NULL,
	`invitor` varchar(11) DEFAULT NULL,
	`user_status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '用户状态：1-正常、0-禁用',
# 	`role` varchar(10) NOT NULL DEFAULT 'USER' COMMENT '用户角色：ADMIN-管理员、USER-用户',
	`create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
# 	`create_id` int(11) DEFAULT NULL COMMENT '创建者ID',
	`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
# 	`update_id` int(11) DEFAULT NULL COMMENT '更新者ID',
	PRIMARY KEY (`user_id`),
	UNIQUE KEY `spread_id` (`spread_id`),
	UNIQUE KEY `username` (`username`),
	UNIQUE KEY `wx_id` (`wx_id`),
	UNIQUE KEY `wb_id` (`wb_id`),
	UNIQUE KEY `qq_id` (`qq_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 数据导出被取消选择。
-- 导出  表 duobao_fly.t_user_bid 结构
CREATE TABLE IF NOT EXISTS `t_user_bid` (
	`bid_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品竞购ID',
	`user_id` int(11) NOT NULL COMMENT '用户ID',
	`bid_amout` int(11) NOT NULL DEFAULT '1' COMMENT '竞购次数（一次一元）',
	`create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
# 	`create_id` int(11) DEFAULT NULL COMMENT '创建者ID',
	`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
# 	`update_id` int(11) DEFAULT NULL COMMENT '更新者ID',
	PRIMARY KEY (`bid_id`),
	UNIQUE KEY `Unique` (`bid_id`,`user_id`),
	KEY `FK_user_bid_user` (`user_id`),
	CONSTRAINT `FK_user_bid_goods_bid` FOREIGN KEY (`bid_id`) REFERENCES `t_goods_bid` (`bid_id`),
	CONSTRAINT `FK_user_bid_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户竞购记录表';

-- 数据导出被取消选择。
-- 导出  表 duobao_fly.t_user_token 结构
CREATE TABLE IF NOT EXISTS `t_user_token` (
	`user_id` int(11) NOT NULL COMMENT '用户ID',
	`user_token` varchar(50) NOT NULL COMMENT '用户TOKEN',
	`active_time` timestamp NULL DEFAULT NULL COMMENT '最后活跃时间',
	`login_time` timestamp NULL DEFAULT NULL COMMENT '登录时间',
	`logout_time` timestamp NULL DEFAULT NULL COMMENT '登出时间',
	`online_time` bigint(20) DEFAULT NULL COMMENT '在线时长',
	`total_login_num` int(11) DEFAULT NULL COMMENT '总登录次数',
	`day_login_num` int(11) DEFAULT NULL COMMENT '当天登录次数',
	`session_status` tinyint(2) DEFAULT '1' COMMENT '会话状态 1正常 2正常登出 3强制登出',
	`create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时只记录一次，不做其他修改',
	PRIMARY KEY (`user_id`),
	UNIQUE KEY `user_token` (`user_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户认证表';

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;


-- 2016-11-16 14:23 新增
CREATE TABLE `t_shipping_record` (
	`id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
	`user_id` INT(11) NOT NULL COMMENT '用户ID',
	`goods_id` INT(11) NOT NULL COMMENT '商品ID',
	`bid_id` INT(11) NOT NULL COMMENT '期数',
	`lucky_num` INT(11) NOT NULL COMMENT '幸运号码',
	`receiver_name` VARCHAR(50) NOT NULL COMMENT '收货人姓名',
	`cellphone` VARCHAR(50) NOT NULL COMMENT '手机号码',
	`shipping_address` VARCHAR(200) NOT NULL COMMENT '收货地址',
	`shipping_status` TINYINT(2) NOT NULL COMMENT '状态',
	`publish_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '中奖时间',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
)
	COMMENT='发货记录表'
	COLLATE='utf8_general_ci'
	ENGINE=InnoDB
;


-- 2016-12-20 20:00 新增
CREATE TABLE `t_lucky_share` (
	`share_id` INT(11) NOT NULL AUTO_INCREMENT,
	`user_id` INT(11) NOT NULL,
	`bid_id` INT(11) NOT NULL,
	`goods_id` INT(11) NOT NULL COMMENT '商品ID',
	`goods_name` VARCHAR(50) NOT NULL COMMENT '宝贝名称',
	`lucky_num` VARCHAR(50) NOT NULL COMMENT '幸运号码',
	`comment` VARCHAR(50) NOT NULL COMMENT '评论',
	`join_amount` INT(11) NOT NULL COMMENT '参与人数',
	`img` VARCHAR(250) NULL DEFAULT NULL COMMENT '分享图片（多图）',
	`publish_time` DATETIME NOT NULL COMMENT '开奖时间',
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`share_id`),
	UNIQUE INDEX `bid_id` (`bid_id`)
)
	COMMENT='好运分享记录表'
	COLLATE='utf8_general_ci'
	ENGINE=InnoDB
	AUTO_INCREMENT=11
;
