alter table chats add column `read_type` int(11) NOT NULL after `player_short_name`;
    
UPDATE `version` SET db_version = '20180920194305';
