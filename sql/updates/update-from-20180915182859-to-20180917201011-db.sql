ALTER TABLE `area_onlys` ADD COLUMN `next_jjc_day_reward_time`  bigint(20) NOT NULL AFTER `last_way_for_map_refresh`;
    
UPDATE `version` SET db_version = '20180917201011';
