-- --------------------------------------------------------
-- 主机:                           121.196.234.79
-- 服务器版本:                        5.5.40 - MySQL Community Server (GPL)
-- 服务器操作系统:                      linux2.6
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 duobao 的数据库结构
CREATE DATABASE IF NOT EXISTS `duobao` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `duobao`;


-- 导出  表 duobao.DATABASECHANGELOG 结构
-- 导出  表 duobao.t_activity 结构
CREATE TABLE IF NOT EXISTS `t_activity` (
  `activity_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动ID',
  `type_id` int(11) NOT NULL COMMENT '活动类型ID',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '活动状态：1-上架、0-下架',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT NULL COMMENT '创建者ID',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_id` int(11) DEFAULT NULL COMMENT '更新者ID',
  PRIMARY KEY (`activity_id`),
  KEY `FK_t_activity_t_activity_type` (`type_id`),
  CONSTRAINT `FK_t_activity_t_activity_type` FOREIGN KEY (`type_id`) REFERENCES `t_activity_type` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动表' COLLATE 'utf8_general_ci';

-- 数据导出被取消选择。


-- 导出  表 duobao.t_activity_type 结构
CREATE TABLE IF NOT EXISTS `t_activity_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动类型ID',
  `type_name` int(11) NOT NULL COMMENT '类型名称',
  `img` varchar(50) DEFAULT NULL COMMENT '类型图片',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '类型状态：1-上架、0-下架',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT NULL COMMENT '创建者ID',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_id` int(11) DEFAULT NULL COMMENT '更新者ID',
  PRIMARY KEY (`type_id`),
  UNIQUE KEY `type_name` (`type_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动类型表';

-- 数据导出被取消选择。


-- 导出  表 duobao.t_goods 结构
CREATE TABLE IF NOT EXISTS `t_goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `type_id` int(11) NOT NULL COMMENT '商品类型ID',
  `goods_name` varchar(50) NOT NULL COMMENT '商品名称',
  `goods_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '商品状态：0-下架、1-上架',
  `statement` varchar(1000) NOT NULL DEFAULT '0' COMMENT '夺宝声明',
  `img` varchar(200) NOT NULL DEFAULT '0' COMMENT '商品图片（多图）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT NULL COMMENT '创建者ID',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_id` int(11) DEFAULT NULL COMMENT '更新者ID',
  PRIMARY KEY (`goods_id`),
  UNIQUE KEY `goods_name` (`goods_name`),
  KEY `FK_commodity_commodity_type` (`type_id`),
  CONSTRAINT `FK_commodity_commodity_type` FOREIGN KEY (`type_id`) REFERENCES `t_goods_type` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

-- 数据导出被取消选择。


-- 导出  表 duobao.t_goods_bid 结构
CREATE TABLE IF NOT EXISTS `t_goods_bid` (
  `bid_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品竞购ID',
  `goods_id` int(11) NOT NULL COMMENT '商品ID',
  `term` int(11) NOT NULL DEFAULT '0' COMMENT '期数',
  `total_amount` int(11) NOT NULL DEFAULT '0' COMMENT '总参与人次（竞购总价格，每人次一元）',
  `join_amount` int(11) NOT NULL DEFAULT '0' COMMENT '已参与人次（剩余人次=总参与人次 - 已参与人次）',
  `bid_status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '竞购状态：0-未上架、1-进行中、2-已揭晓',
  `winner_id` int(11) NOT NULL DEFAULT '0' COMMENT '获取用户ID',
  `lucky_num` int(11) NOT NULL DEFAULT '0' COMMENT '幸运号码',
  `publish_time` timestamp NULL DEFAULT NULL COMMENT '揭晓时间',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT NULL COMMENT '创建者ID',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_id` int(11) DEFAULT NULL COMMENT '更新者ID',
  PRIMARY KEY (`bid_id`),
  KEY `FK__user` (`winner_id`),
  KEY `FK_goods_bid_goods` (`goods_id`),
  CONSTRAINT `FK_goods_bid_goods` FOREIGN KEY (`goods_id`) REFERENCES `t_goods` (`goods_id`),
  CONSTRAINT `FK__user` FOREIGN KEY (`winner_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品竞购表';

-- 数据导出被取消选择。


-- 导出  表 duobao.t_goods_type 结构
CREATE TABLE IF NOT EXISTS `t_goods_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品类型ID',
  `type_name` varchar(20) NOT NULL COMMENT '商品类型名称',
  `type_img` varchar(50) NOT NULL COMMENT '商品类型图片',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '商品类型状态：1-上架、0-下架',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT NULL COMMENT '创建者ID',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_id` int(11) DEFAULT NULL COMMENT '更新者ID',
  PRIMARY KEY (`type_id`),
  UNIQUE KEY `type_name` (`type_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品类型表';

-- 数据导出被取消选择。


-- 导出  表 duobao.t_integral 结构
CREATE TABLE IF NOT EXISTS `t_integral` (
  `integral_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '积分明细ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `integral_type` tinyint(2) NOT NULL COMMENT '积分类型：0-签到、1-新手任务',
  `in_out` tinyint(1) NOT NULL COMMENT '积分来源或消耗：1-增加、0-消耗',
  `amount` int(11) NOT NULL COMMENT '单次积分值',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_id` int(11) DEFAULT NULL COMMENT '创建者ID',
  PRIMARY KEY (`integral_id`),
  KEY `FK_user_integral_user` (`user_id`),
  CONSTRAINT `FK_user_integral_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分明细表';

-- 数据导出被取消选择。


-- 导出  表 duobao.t_message 结构
CREATE TABLE IF NOT EXISTS `t_message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `content` varchar(200) DEFAULT NULL COMMENT '内容',
  `message_type` tinyint(2) NOT NULL COMMENT '消息类型：0-活动、1-中奖、2-发货、3-客服、4-系统',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_id` int(11) DEFAULT NULL COMMENT '创建者ID',
  PRIMARY KEY (`message_id`),
  KEY `FK_t_message_t_user` (`user_id`),
  CONSTRAINT `FK_t_message_t_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息表';

-- 数据导出被取消选择。


-- 导出  表 duobao.t_moment 结构
CREATE TABLE IF NOT EXISTS `t_moment` (
  `moment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '动态ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `bid_id` int(11) NOT NULL COMMENT '商品竞购ID',
  `img` varchar(200) DEFAULT NULL COMMENT '图片',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_id` int(11) DEFAULT NULL COMMENT '创建者ID',
  PRIMARY KEY (`moment_id`),
  KEY `FK_t_moment_t_user` (`user_id`),
  KEY `FK_t_moment_t_goods_bid` (`bid_id`),
  CONSTRAINT `FK_t_moment_t_goods_bid` FOREIGN KEY (`bid_id`) REFERENCES `t_goods_bid` (`bid_id`),
  CONSTRAINT `FK_t_moment_t_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='晒单动态表';

-- 数据导出被取消选择。


-- 导出  表 duobao.t_red_pocket 结构
CREATE TABLE IF NOT EXISTS `t_red_pocket` (
  `pocket_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '红包ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `pocket_name` varchar(50) DEFAULT NULL COMMENT '红包名称',
  `pocket_status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '红包状态：1-可使用、0-过期已使用',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT NULL COMMENT '创建者ID',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_id` int(11) DEFAULT NULL COMMENT '更新者ID',
  PRIMARY KEY (`pocket_id`),
  KEY `FK_red_pocket_user` (`user_id`),
  CONSTRAINT `FK_red_pocket_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='红包明细表';

-- 数据导出被取消选择。


-- 导出  表 duobao.t_shopping_cart 结构
CREATE TABLE IF NOT EXISTS `t_shopping_cart` (
  `item_id` int(11) NOT NULL COMMENT '条目ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `bid_id` int(11) NOT NULL COMMENT '商品竞购ID',
  `amount` int(11) NOT NULL DEFAULT '0' COMMENT '数量',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT NULL COMMENT '创建者ID',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_id` int(11) DEFAULT NULL COMMENT '更新者ID',
  PRIMARY KEY (`item_id`),
  KEY `FK_t_shopping_cart_t_user` (`user_id`),
  KEY `FK_t_shopping_cart_t_goods_bid` (`bid_id`),
  CONSTRAINT `FK_t_shopping_cart_t_goods_bid` FOREIGN KEY (`bid_id`) REFERENCES `t_goods_bid` (`bid_id`),
  CONSTRAINT `FK_t_shopping_cart_t_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车表';

-- 数据导出被取消选择。


-- 导出  表 duobao.t_sms_code 结构
CREATE TABLE IF NOT EXISTS `t_sms_code` (
  `cellphone` varchar(11) NOT NULL COMMENT '手机号',
  `code` varchar(255) NOT NULL COMMENT '验证码',
  `receive_time` datetime NOT NULL COMMENT '最近一次发送接收验证码的时间',
  `times` int(11) DEFAULT NULL COMMENT '每日发送验证码次数',
  `verified` tinyint(1) DEFAULT NULL COMMENT '是否验证：1-已验证、0-未验证',
  `code_type` tinyint(2) NOT NULL COMMENT '验证码类型',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`cellphone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信验证码表';

-- 数据导出被取消选择。


-- 导出  表 duobao.t_user 结构
CREATE TABLE IF NOT EXISTS `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `spread_id` varchar(20) NOT NULL COMMENT '推广ID',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(50) DEFAULT NULL COMMENT '用户头像',
  `coin` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '抢币（虚拟货币）',
  `integral` int(11) NOT NULL DEFAULT '0' COMMENT '积分',
  `cellphone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `qq` varchar(11) DEFAULT NULL COMMENT '用户QQ',
  `shipping_address` varchar(100) DEFAULT NULL COMMENT '收货地址',
  `user_status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '用户状态：1-正常、0-禁用',
  `role` varchar(10) NOT NULL DEFAULT 'USER' COMMENT '用户角色：ADMIN-管理员、USER-用户',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT NULL COMMENT '创建者ID',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_id` int(11) DEFAULT NULL COMMENT '更新者ID',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `spread_id` (`spread_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 数据导出被取消选择。


-- 导出  表 duobao.t_user_bid 结构
CREATE TABLE IF NOT EXISTS `t_user_bid` (
  `bid_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品竞购ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `bid_amout` int(11) NOT NULL DEFAULT '1' COMMENT '竞购次数（一次一元）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT NULL COMMENT '创建者ID',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_id` int(11) DEFAULT NULL COMMENT '更新者ID',
  PRIMARY KEY (`bid_id`),
  UNIQUE KEY `Unique` (`bid_id`,`user_id`),
  KEY `FK_user_bid_user` (`user_id`),
  CONSTRAINT `FK_user_bid_goods_bid` FOREIGN KEY (`bid_id`) REFERENCES `t_goods_bid` (`bid_id`),
  CONSTRAINT `FK_user_bid_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户竞购记录表';

-- 数据导出被取消选择。


-- 导出  表 duobao.t_user_token 结构
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
