ALTER TABLE `gift_bags` ADD COLUMN `cur_count`  int(11) NOT NULL AFTER `id`;
ALTER TABLE `gift_bags` ADD COLUMN `cur_level`  int(11) NOT NULL AFTER `cur_count`;
ALTER TABLE `gift_bags` DROP COLUMN `levels`;
    
UPDATE `version` SET db_version = '20180915182859';
