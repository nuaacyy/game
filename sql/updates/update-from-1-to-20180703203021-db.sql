DROP TABLE IF EXISTS `version`;
CREATE TABLE `version` (
  `id` bigint(20) NOT NULL,
  `db_version` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 行军组添加状态变化时间
ALTER TABLE `new_walk_force_groups` ADD COLUMN `state_change_time` bigint(20) NOT NULL AFTER `running_state`;

-- 属地添加战果信息
ALTER TABLE `belong_cell` ADD COLUMN `atk_battle_rs` int(11) NOT NULL AFTER `id`;
ALTER TABLE `belong_cell` ADD COLUMN `def_battle_rs` int(11) NOT NULL AFTER `atk_battle_rs`;
ALTER TABLE `belong_cell` ADD COLUMN `def_player_id` bigint(20) NOT NULL AFTER `def_battle_rs`;
    
UPDATE `version` SET db_version = '20180703203021';
