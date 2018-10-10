CREATE TABLE `move_server_cell_lock` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `start_time` bigint(20) NOT NULL,
  `x` int(11) NOT NULL,
  `y` int(11) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `players` ADD COLUMN `in_move_server` int(11) NOT NULL AFTER `house_coin`;
ALTER TABLE `home_players` ADD COLUMN `in_move_server` int(11) NOT NULL AFTER `house_coin`;
ALTER TABLE `alliance_members` ADD COLUMN `in_move_server` int(11) NOT NULL AFTER `game_plt_area_id`;
    
UPDATE `version` SET db_version = '20180710174835';
