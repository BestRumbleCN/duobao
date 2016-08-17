-- 去掉表中多余的create_id和update_id字段
ALTER TABLE `t_user`
DROP COLUMN `role`,
DROP COLUMN `create_id`,
DROP COLUMN `update_id`;

ALTER TABLE `t_activity`
DROP COLUMN `create_id`,
DROP COLUMN `update_id`;

ALTER TABLE `t_activity_type`
DROP COLUMN `create_id`,
DROP COLUMN `update_id`;

ALTER TABLE `t_goods`
DROP COLUMN `create_id`,
DROP COLUMN `update_id`;

ALTER TABLE `t_goods_bid`
DROP COLUMN `create_id`,
DROP COLUMN `update_id`;

ALTER TABLE `t_goods_type`
DROP COLUMN `create_id`,
DROP COLUMN `update_id`;

ALTER TABLE `t_integral`
DROP COLUMN `create_id`;

ALTER TABLE `t_message`
DROP COLUMN `create_id`;

ALTER TABLE `t_moment`
DROP COLUMN `create_id`;

ALTER TABLE `t_red_pocket`
DROP COLUMN `create_id`,
DROP COLUMN `update_id`;

ALTER TABLE `t_shopping_cart`
DROP COLUMN `create_id`,
DROP COLUMN `update_id`;

ALTER TABLE `t_user_bid`
DROP COLUMN `create_id`,
DROP COLUMN `update_id`;