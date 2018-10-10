alter table players add column `alliance_occupy_info` longtext NOT NULL after `alliance_nick_name`;
    
UPDATE `version` SET db_version = '20180920172333';
