ALTER TABLE `home_players` ADD COLUMN `join_alliance_state`  int (11) NOT NULL AFTER `join_activitys`;
    
UPDATE `version` SET db_version = '20180919180059';
