ALTER TABLE `giftbags` ADD COLUMN `end_time`  bigint(20) NOT NULL AFTER `id`;
ALTER TABLE giftbags RENAME TO gift_bags;
ALTER TABLE giftbag_records RENAME TO gift_bag_records;
CREATE TABLE `quota_bags` (
  `player_id` bigint(20) NOT NULL,
  `degree` int(11) NOT NULL,
  `end_time` bigint(20) NOT NULL,
  `quota_bag_id` int(11) NOT NULL,
  `reward_id` int(11) NOT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `world_wonder` CHANGE `position_info` `office_info` varchar(1000) NOT NULL AFTER `occupy_start_time`;
    
UPDATE `version` SET db_version = '20180912171308';
