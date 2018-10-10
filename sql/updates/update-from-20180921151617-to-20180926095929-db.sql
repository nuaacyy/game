ALTER TABLE `alliance_members` CHANGE `curent_pos` `office_info` varchar(1000) NOT NULL AFTER `name`;
    
UPDATE `version` SET db_version = '20180926095929';
