	-- 创建系统用户表
	CREATE TABLE `t_system_user` (
		`user_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
		`username` VARCHAR(50) NOT NULL COMMENT '用户名',
		`password` VARCHAR(50) NOT NULL COMMENT '密码',
		PRIMARY KEY (`user_id`)
	)
	COMMENT='系统用户表'
	COLLATE='utf8_general_ci'
	ENGINE=InnoDB;
	
	ALTER TABLE `t_user`
	DROP COLUMN `qq`,
	ADD COLUMN `wx_id` VARCHAR(64) NULL DEFAULT NULL COMMENT '微信openId' AFTER `cellphone`,
	ADD COLUMN `wb_id` VARCHAR(64) NULL DEFAULT NULL COMMENT '微博openId' AFTER `wx_id`,
	ADD COLUMN `qq_id` VARCHAR(64) NULL DEFAULT NULL COMMENT 'QQopenId' AFTER `wb_id`,
	ADD UNIQUE INDEX `wx_id` (`wx_id`),
	ADD UNIQUE INDEX `wb_id` (`wb_id`),
	ADD UNIQUE INDEX `qq_id` (`qq_id`);
	