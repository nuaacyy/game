alter table home_players drop column `sync_realm`;
alter table home_players drop column `decree_day_time`;
alter table home_players drop column `decree_buy_num`;
alter table home_players drop column `fortress`;
alter table home_players drop column `building_queue`;
alter table home_players drop column `tower_num`;
alter table home_players drop column `tower_num_last_time`;
alter table home_players drop column `today_alliance_give_num`;
alter table home_players drop column `last_alliance_give_time`;
alter table home_players drop column `heiyaoshi_next_time`;
alter table home_players drop column `heiyaoshi_luck_num`;
alter table home_players drop column `stronghold_count`;
alter table home_players drop column `stronghold_max_count`;
alter table home_players drop column `stronghold_time`;
    
UPDATE `version` SET db_version = '20180907094211';
