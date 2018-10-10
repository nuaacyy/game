alter table relices drop column `next_ref_time`;
    
UPDATE `version` SET db_version = '20180906205643';
