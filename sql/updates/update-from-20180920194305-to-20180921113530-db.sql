alter table `home_players` add column `alliance_occupy_info` longtext NOT NULL after `alliance_nick_name`;
ALTER TABLE `home_players` CHANGE `allianceHelpTodayGet` `alliance_help_today_get` int(11) NOT NULL AFTER `alliance_gift_proto_id`;
ALTER TABLE `home_players` CHANGE `lastAllianceHelpTime` `last_alliance_help_time` bigint(20) NOT NULL AFTER `last_alliance_gift_get_time`;
    
UPDATE `version` SET db_version = '20180921113530';
