DROP TABLE IF EXISTS `battle_reports`;
CREATE TABLE `battle_reports` (
  `id` bigint(20) NOT NULL,
  `archived` bigint(20) NOT NULL,
  `fight_address_x` int(11) NOT NULL,
  `fight_address_y` int(11) NOT NULL,
  `fight_detail` mediumblob NOT NULL,
  `fight_time` bigint(20) NOT NULL,
  `past_time` bigint(20) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `read_state` int(11) NOT NULL,
  `report_content` blob NOT NULL,
  `report_type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
    
UPDATE `version` SET db_version = '20180710113852';
