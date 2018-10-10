ALTER TABLE `players` DROP COLUMN plt_area_no,DROP COLUMN map_plt_area_no;
ALTER TABLE `home_players` DROP COLUMN plt_area_no,DROP COLUMN map_plt_area_no;
ALTER TABLE `alliance_members` DROP COLUMN game_plt_area_id;

    
UPDATE `version` SET db_version = '20180927110859';
