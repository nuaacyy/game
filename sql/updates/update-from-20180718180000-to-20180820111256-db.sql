ALTER TABLE `world_wonder` CHANGE `god_of_war_status` `wonder_war_status` int(11) NOT NULL AFTER `wonder_proto_id`;
    
UPDATE `version` SET db_version = '20180820111256';
