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
	ADD COLUMN `wxId` VARCHAR(64) NULL DEFAULT NULL AFTER `cellphone`,
	ADD COLUMN `wbId` VARCHAR(64) NULL DEFAULT NULL AFTER `wxId`,
	ADD COLUMN `qqId` VARCHAR(64) NULL DEFAULT NULL AFTER `wbId`,
	ADD UNIQUE INDEX `wxId` (`wxId`),
	ADD UNIQUE INDEX `wbId` (`wbId`),
	ADD UNIQUE INDEX `qqId` (`qqId`);
	