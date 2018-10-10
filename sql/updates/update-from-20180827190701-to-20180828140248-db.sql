ALTER TABLE `players` DROP COLUMN `decree_limit`;
    
UPDATE `version` SET db_version = '20180828140248';
