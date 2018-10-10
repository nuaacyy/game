DROP TABLE IF EXISTS `player_setting`;
CREATE TABLE `player_setting` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `caution_lv` int(11) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `refuse_disturb_end` int(11) NOT NULL,
  `refuse_disturb_open` int(11) NOT NULL,
  `switches` longtext NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
    
UPDATE `version` SET db_version = '20180904202517';
