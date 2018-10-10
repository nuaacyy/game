ALTER TABLE `home_syncs` CHANGE `country_position_id` `office_info` varchar(1000) NOT NULL AFTER `max_lv_in_prison`;
    
UPDATE `version` SET db_version = '20180921151617';
