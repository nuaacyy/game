ALTER TABLE `home_players` ADD have_finish_guide_line VARCHAR(255) NOT NULL AFTER `guide_step`;
    
UPDATE `version` SET db_version = '20180706155421';
