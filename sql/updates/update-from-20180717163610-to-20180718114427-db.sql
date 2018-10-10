ALTER TABLE `home_my_targets` ADD before_bank_num VARCHAR(255) NOT NULL AFTER `bank_num`;
    
UPDATE `version` SET db_version = '20180718114427';
