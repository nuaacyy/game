alter table castles drop column `map_castle_id`;
alter table castles drop column `lock_info`;
alter table castles drop column `special_skill`;
alter table castles drop column `def_special_skill`;
alter table castles drop column `relic_skill`;
alter table castles drop column `defenders`;
    
UPDATE `version` SET db_version = '20180906192637';
