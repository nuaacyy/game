CREATE TABLE `giftbags` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `gift_id` int(11) NOT NULL,
  `levels` varchar(255) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `giftbag_records` (
  `player_id` bigint(20) NOT NULL,
  `records` longtext NOT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `home_players` ADD COLUMN `month_cards`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL AFTER `max_mark`;
    
UPDATE `version` SET db_version = '20180910133503';
