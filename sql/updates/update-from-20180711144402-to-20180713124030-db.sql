DROP TABLE IF EXISTS `my_targets`;
CREATE TABLE `my_targets` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `activity_score_info` varchar(1000) NOT NULL,
  `atk_monster_info` varchar(1000) NOT NULL,
  `atk_monster_num` int(11) NOT NULL,
  `attack_fail_record` int(11) NOT NULL,
  `attack_win_record` int(11) NOT NULL,
  `be_prison_num` int(11) NOT NULL,
  `build_strength` bigint(20) NOT NULL,
  `catch_king_escape_num` int(11) NOT NULL,
  `cave_solider_num` int(11) NOT NULL,
  `cure_solider_num` bigint(20) NOT NULL,
  `damage_solider_info` varchar(1000) NOT NULL,
  `defend_fail_record` int(11) NOT NULL,
  `defend_win_record` int(11) NOT NULL,
  `defense_failed` int(11) NOT NULL,
  `defense_success` int(11) NOT NULL,
  `fail_record` int(11) NOT NULL,
  `farm_empty_info` varchar(1000) NOT NULL,
  `farm_res_count_info` varchar(1000) NOT NULL,
  `farm_res_num_info` varchar(1000) NOT NULL,
  `get_prison_num` int(11) NOT NULL,
  `get_reward_gold_num` bigint(20) NOT NULL,
  `hero_strength` bigint(20) NOT NULL,
  `instance_fight_num` int(11) NOT NULL,
  `jjc_rank` int(11) NOT NULL,
  `jjc_win_record` int(11) NOT NULL,
  `join_mass_num` int(11) NOT NULL,
  `kill_king_num` int(11) NOT NULL,
  `kill_monster_info` varchar(1000) NOT NULL,
  `kill_monster_score` bigint(20) NOT NULL,
  `kill_solider_info` varchar(1000) NOT NULL,
  `king_be_kill_num` int(11) NOT NULL,
  `king_escape_num` int(11) NOT NULL,
  `king_strength` bigint(20) NOT NULL,
  `last_Jjc_Rank_Time` bigint(20) NOT NULL,
  `last_kill_monster_score_time` bigint(20) NOT NULL,
  `last_kill_solider_time` bigint(20) NOT NULL,
  `last_power_change_time` bigint(20) NOT NULL,
  `make_equip_info` varchar(1000) NOT NULL,
  `make_solider_info` varchar(1000) NOT NULL,
  `make_trap_info` varchar(1000) NOT NULL,
  `mass_num` int(11) NOT NULL,
  `max_jjc_rank` int(11) NOT NULL,
  `mission_strength` bigint(20) NOT NULL,
  `move_city_count` int(11) NOT NULL,
  `playerId` bigint(20) NOT NULL,
  `plunder_res_num` bigint(20) NOT NULL,
  `pos_type_info` varchar(1000) NOT NULL,
  `receive_online_gift_count` int(11) NOT NULL,
  `research_count` int(11) NOT NULL,
  `research_strength` bigint(20) NOT NULL,
  `scout_num` int(11) NOT NULL,
  `solider_die_num` bigint(20) NOT NULL,
  `solider_strength` bigint(20) NOT NULL,
  `total_help_ally` int(11) NOT NULL,
  `total_kill` bigint(20) NOT NULL,
  `transport_res_info` varchar(1000) NOT NULL,
  `trap_die_num` bigint(20) NOT NULL,
  `trap_strength` bigint(20) NOT NULL,
  `weaken_strength` bigint(20) NOT NULL,
  `win_record` int(11) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `home_syncs` ADD COLUMN `targets` varchar(1000) NOT NULL AFTER `score_time`;
    
UPDATE `version` SET db_version = '20180713124030';
