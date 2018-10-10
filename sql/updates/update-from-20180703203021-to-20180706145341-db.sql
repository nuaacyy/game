ALTER TABLE `players` ADD user VARCHAR(50) NOT NULL AFTER unlocked_talent;

ALTER TABLE `players` ADD INDEX `HOME_PLAYERS_USER` (`user`);

ALTER TABLE `home_my_targets` MODIFY COLUMN casino_boss_num VARCHAR(255) NOT NULL;

ALTER TABLE `home_my_targets` MODIFY COLUMN casino_kill_boss_num VARCHAR(255) NOT NULL;

ALTER TABLE `home_my_targets` MODIFY COLUMN casino_num VARCHAR(255) NOT NULL;
    
UPDATE `version` SET db_version = '20180706145341';
