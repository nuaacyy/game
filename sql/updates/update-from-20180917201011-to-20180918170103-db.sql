ALTER TABLE `players` ADD COLUMN `join_alliance_reqs`  varchar (2000) NOT NULL AFTER `join_activitys`;
    
UPDATE `version` SET db_version = '20180918170103';
