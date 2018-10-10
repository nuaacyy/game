alter table `alliances` modify column `alliance_occupy_info` varchar(2000) NOT NULL after `alliance_member_num`;
alter table `home_players` modify column `alliance_occupy_info` varchar(2000) NOT NULL after `alliance_nick_name`;
alter table `players` modify column `alliance_occupy_info` varchar(2000) NOT NULL after `alliance_nick_name`;
alter table `world_wonder` rename to `wonder`;

    
UPDATE `version` SET db_version = '20180926143346';
