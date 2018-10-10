ALTER TABLE `alliances` ADD COLUMN `alliance_occupy_info`  varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL AFTER `alliance_member_num`;
    
UPDATE `version` SET db_version = '20180911182514';
