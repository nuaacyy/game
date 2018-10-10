alter table `boss_invites` drop column `player_id`;
alter table `boss_invites` add `alliance_id` BIGINT(20) NOT NULL AFTER `id`;
alter table `boss_invites` add `latest_invite_time` BIGINT(20) NOT NULL AFTER `alliance_id`;
    
UPDATE `version` SET db_version = '20180828172709';
