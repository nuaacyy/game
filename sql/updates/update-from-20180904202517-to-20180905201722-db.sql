alter table area_onlys add column `next_ref_boss_time` bigint(20) NOT NULL after `last_way_for_map_refresh`;
    
UPDATE `version` SET db_version = '20180905201722';
