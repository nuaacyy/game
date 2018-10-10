-- MySQL dump 10.13  Distrib 5.7.23, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: slg2d
-- ------------------------------------------------------
-- Server version	5.7.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `achievements`
--

DROP TABLE IF EXISTS `achievements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `achievements` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `achievement_id` int(11) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `progress` varchar(50) NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `activity_boss_area`
--

DROP TABLE IF EXISTS `activity_boss_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_boss_area` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `activity_boss_id` int(11) NOT NULL,
  `advance_mail` int(11) NOT NULL,
  `finish_time` bigint(20) NOT NULL,
  `init_time` bigint(20) NOT NULL,
  `location_id` int(11) NOT NULL,
  `refresh_time` bigint(20) NOT NULL,
  `start_time` bigint(20) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `activity_bosses`
--

DROP TABLE IF EXISTS `activity_bosses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_bosses` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `activity_boss_id` int(11) NOT NULL,
  `atk_records` longtext,
  `boss_id` int(11) NOT NULL,
  `now_hp` int(11) NOT NULL,
  `x` int(11) NOT NULL,
  `y` int(11) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `activity_ranks`
--

DROP TABLE IF EXISTS `activity_ranks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_ranks` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `over_time` bigint(20) NOT NULL,
  `rank_nfo` longtext NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `alliance_activities`
--

DROP TABLE IF EXISTS `alliance_activities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alliance_activities` (
  `public_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `activity_id` int(11) NOT NULL,
  `over_time` bigint(20) NOT NULL,
  PRIMARY KEY (`public_id`,`id`),
  KEY `ALLIANCE_ACTIVITIES_PUBLIC_ID` (`public_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `alliance_activity_ranks`
--

DROP TABLE IF EXISTS `alliance_activity_ranks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alliance_activity_ranks` (
  `public_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `over_time` bigint(20) NOT NULL,
  `rank_info` longtext NOT NULL,
  PRIMARY KEY (`public_id`,`id`),
  KEY `ALLIANCE_ACTIVITY_RANKS_PUBLIC_ID` (`public_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `alliance_chat`
--

DROP TABLE IF EXISTS `alliance_chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alliance_chat` (
  `id` bigint(20) NOT NULL,
  `alliance_id` bigint(20) NOT NULL,
  `alliance_name` varchar(50) NOT NULL,
  `alliance_pos` int(11) NOT NULL,
  `alliance_short_name` varchar(50) NOT NULL,
  `area_no` int(11) NOT NULL,
  `chat_time` bigint(20) NOT NULL,
  `icon_proto_id` int(11) NOT NULL,
  `kingdom_pos` int(11) NOT NULL,
  `msg` varchar(250) NOT NULL,
  `msg_type` int(11) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `player_name` varchar(50) NOT NULL,
  `player_short_name` varchar(50) NOT NULL,
  `plt_area_no` bigint(20) NOT NULL,
  `vip_lv` int(11) NOT NULL,
  `wonder_pos` int(11) NOT NULL,
  PRIMARY KEY (`id`,`alliance_id`),
  KEY `ALLIANCE_CHAT_ALLIANCE_ID` (`alliance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `alliance_competition_groups`
--

DROP TABLE IF EXISTS `alliance_competition_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alliance_competition_groups` (
  `id` bigint(20) NOT NULL,
  `alliance_id` bigint(20) NOT NULL,
  `group_id` int(11) NOT NULL,
  `over_rank_lv` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  `state_rank_lv` int(11) NOT NULL,
  PRIMARY KEY (`id`,`alliance_id`),
  KEY `ALLIANCE_COMPETITION_GROUPS_ALLIANCE_ID` (`alliance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `alliance_competition_quests`
--

DROP TABLE IF EXISTS `alliance_competition_quests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alliance_competition_quests` (
  `id` bigint(20) NOT NULL,
  `alliance_id` bigint(20) NOT NULL,
  `indexAddress` int(11) NOT NULL,
  `quest_id` int(11) NOT NULL,
  `ref_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`,`alliance_id`),
  KEY `ALLIANCE_COMPETITION_QUESTS_ALLIANCE_ID` (`alliance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `alliance_gifts`
--

DROP TABLE IF EXISTS `alliance_gifts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alliance_gifts` (
  `id` bigint(20) NOT NULL,
  `alliance_id` bigint(20) NOT NULL,
  `big_gift_exp` int(11) NOT NULL,
  `big_gift_id` int(11) NOT NULL,
  `gift_exp` int(11) NOT NULL,
  `gift_lv` int(11) NOT NULL,
  PRIMARY KEY (`id`,`alliance_id`),
  KEY `ALLIANCE_GIFTS_ALLIANCE_ID` (`alliance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `alliance_helps`
--

DROP TABLE IF EXISTS `alliance_helps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alliance_helps` (
  `id` bigint(20) NOT NULL,
  `alliance_id` bigint(20) NOT NULL,
  `help_player_id` bigint(20) NOT NULL,
  `help_type` int(11) NOT NULL,
  `help_value1` bigint(20) NOT NULL,
  `help_value2` bigint(20) NOT NULL,
  `help_value3` bigint(20) NOT NULL,
  `help_value4` bigint(20) NOT NULL,
  `helper_ids` longtext NOT NULL,
  `now_help_num` int(11) NOT NULL,
  `send_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`,`alliance_id`),
  KEY `ALLIANCE_HELPS_ALLIANCE_ID` (`alliance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `alliance_logs`
--

DROP TABLE IF EXISTS `alliance_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alliance_logs` (
  `id` bigint(20) NOT NULL,
  `alliance_id` bigint(20) NOT NULL,
  `log_info` varchar(255) NOT NULL,
  `log_time` bigint(20) NOT NULL,
  `log_type` int(11) NOT NULL,
  `once_key` varchar(255) NOT NULL,
  PRIMARY KEY (`id`,`alliance_id`),
  KEY `ALLIANCE_LOGS_ALLIANCE_ID` (`alliance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `alliance_marks`
--

DROP TABLE IF EXISTS `alliance_marks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alliance_marks` (
  `id` bigint(20) NOT NULL,
  `alliance_id` bigint(20) NOT NULL,
  `description` varchar(255) NOT NULL,
  `mark_time` bigint(20) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `plt_area_no` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  `x` int(11) NOT NULL,
  `y` int(11) NOT NULL,
  PRIMARY KEY (`id`,`alliance_id`),
  KEY `ALLIANCE_MARKS_ALLIANCE_ID` (`alliance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `alliance_member_traces`
--

DROP TABLE IF EXISTS `alliance_member_traces`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alliance_member_traces` (
  `id` bigint(20) NOT NULL,
  `alliance_id` bigint(20) NOT NULL,
  `alliance_competition_quest_get_num` int(11) NOT NULL,
  `alliance_competition_quest_success_num` int(11) NOT NULL,
  `alliance_competition_score` bigint(20) NOT NULL,
  `alliance_competition_score_change_time` bigint(20) NOT NULL,
  `cure_solider` bigint(20) NOT NULL,
  `honor` bigint(20) NOT NULL,
  `kill_monster` bigint(20) NOT NULL,
  `kill_solider` bigint(20) NOT NULL,
  `monster_score` bigint(20) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `week_cure_solider` bigint(20) NOT NULL,
  `week_help` bigint(20) NOT NULL,
  `week_honor` bigint(20) NOT NULL,
  `week_kill_monster` bigint(20) NOT NULL,
  `week_kill_solider` bigint(20) NOT NULL,
  `week_monster_score` bigint(20) NOT NULL,
  `week_transportation_value` bigint(20) NOT NULL,
  PRIMARY KEY (`id`,`alliance_id`),
  KEY `ALLIANCE_MEMBER_TRACES_ALLIANCE_ID` (`alliance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `alliance_members`
--

DROP TABLE IF EXISTS `alliance_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alliance_members` (
  `id` bigint(20) NOT NULL,
  `alliance_id` bigint(20) NOT NULL,
  `alliance_at` bigint(20) NOT NULL,
  `alliance_competition_score` int(11) NOT NULL,
  `alliance_competition_ticket` int(11) NOT NULL,
  `alliance_nick_name` varchar(100) NOT NULL,
  `alliance_pos` varchar(1000) NOT NULL,
  `alliance_rnum` int(11) NOT NULL,
  `can_help_num` int(11) NOT NULL,
  `in_move_server` int(11) NOT NULL,
  `last_get_monster_score` bigint(20) NOT NULL,
  `last_leave_time` bigint(20) NOT NULL,
  `map_area_no` int(11) NOT NULL,
  `map_plt_area_id` bigint(20) NOT NULL,
  `mem_power` bigint(20) NOT NULL,
  `monster_score` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `office_info` varchar(1000) NOT NULL,
  `online_state` int(11) NOT NULL,
  `photo_proto_id` int(11) NOT NULL,
  `player_castle_lv` int(11) NOT NULL,
  `sign_topics` varchar(5000) NOT NULL,
  `vip_lv` int(11) NOT NULL,
  PRIMARY KEY (`id`,`alliance_id`),
  KEY `ALLIANCE_MEMBERS_ALLIANCE_ID` (`alliance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `alliance_replies`
--

DROP TABLE IF EXISTS `alliance_replies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alliance_replies` (
  `id` bigint(20) NOT NULL,
  `alliance_id` bigint(20) NOT NULL,
  `message` varchar(255) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `reply_at` bigint(20) NOT NULL,
  `topic_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`,`alliance_id`),
  KEY `ALLIANCE_REPLIES_ALLIANCE_ID` (`alliance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `alliance_reqs`
--

DROP TABLE IF EXISTS `alliance_reqs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alliance_reqs` (
  `id` bigint(20) NOT NULL,
  `alliance_id` bigint(20) NOT NULL,
  `area_no` int(11) NOT NULL,
  `player_fight_value` bigint(20) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `player_name` varchar(255) NOT NULL,
  `player_photo` int(11) NOT NULL,
  `plt_area_no` bigint(20) NOT NULL,
  `req_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`,`alliance_id`),
  KEY `ALLIANCE_REQS_ALLIANCE_ID` (`alliance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `alliance_topics`
--

DROP TABLE IF EXISTS `alliance_topics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alliance_topics` (
  `id` bigint(20) NOT NULL,
  `alliance_id` bigint(20) NOT NULL,
  `create_at` bigint(20) NOT NULL,
  `last_at` bigint(20) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `readT` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `ttype` int(11) NOT NULL,
  PRIMARY KEY (`id`,`alliance_id`),
  KEY `ALLIANCE_TOPICS_ALLIANCE_ID` (`alliance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `alliance_waijiaos`
--

DROP TABLE IF EXISTS `alliance_waijiaos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alliance_waijiaos` (
  `id` bigint(20) NOT NULL,
  `alliance_id` bigint(20) NOT NULL,
  `aid` bigint(20) NOT NULL,
  `area_no` int(11) NOT NULL,
  `create_time` bigint(20) NOT NULL,
  `flag_color` int(11) NOT NULL,
  `flag_effect` int(11) NOT NULL,
  `flag_style` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `nick_name` varchar(100) NOT NULL,
  `photo_proto_id` int(11) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `player_name` varchar(255) NOT NULL,
  `player_position` varchar(255) NOT NULL,
  `short_name` varchar(255) NOT NULL,
  `waijiao_info` varchar(255) NOT NULL,
  PRIMARY KEY (`id`,`alliance_id`),
  KEY `ALLIANCE_WAIJIAOS_ALLIANCE_ID` (`alliance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `alliances`
--

DROP TABLE IF EXISTS `alliances`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alliances` (
  `id` bigint(20) NOT NULL,
  `alliance_id` bigint(20) NOT NULL,
  `alliance_area_no` int(11) NOT NULL,
  `alliance_boss_info` varchar(1000) NOT NULL,
  `alliance_boss_score` int(11) NOT NULL,
  `alliance_competition_score` int(11) NOT NULL,
  `alliance_competition_score_change_time` bigint(20) NOT NULL,
  `alliance_competition_ticket` int(11) NOT NULL,
  `alliance_help_num_add` int(11) NOT NULL,
  `alliance_lan` int(11) NOT NULL,
  `alliance_member_num` int(11) NOT NULL,
  `alliance_occupy_info` varchar(2000) NOT NULL,
  `alliance_power` bigint(20) NOT NULL,
  `alliance_rank_lv` int(11) NOT NULL,
  `can_add_power` bigint(20) NOT NULL,
  `create_at` bigint(20) NOT NULL,
  `description` varchar(500) NOT NULL,
  `flag_color` int(11) NOT NULL,
  `flag_effect` int(11) NOT NULL,
  `flag_style` int(11) NOT NULL,
  `join_activity` longtext NOT NULL,
  `main_player_id` bigint(20) NOT NULL,
  `make_over_pid` bigint(20) NOT NULL,
  `make_over_time` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `next_mark_time` bigint(20) NOT NULL,
  `power_limit` bigint(20) NOT NULL,
  `short_name` varchar(10) NOT NULL,
  `slogan` varchar(500) NOT NULL,
  `wonder_award_info` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`,`alliance_id`),
  KEY `ALLIANCES_ALLIANCE_ID` (`alliance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `area_onlys`
--

DROP TABLE IF EXISTS `area_onlys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `area_onlys` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `every_small_map_player_num` longtext NOT NULL,
  `inited_map` int(11) NOT NULL,
  `last_way_for_map_refresh` int(11) NOT NULL,
  `next_jjc_day_reward_time` bigint(20) NOT NULL,
  `next_ref_boss_time` bigint(20) NOT NULL,
  `now_create_player_circle_num` int(11) NOT NULL,
  `now_create_player_on_area` int(11) NOT NULL,
  `now_player_num` int(11) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `army_plans`
--

DROP TABLE IF EXISTS `army_plans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `army_plans` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `big_target` int(11) NOT NULL,
  `hero_info` varchar(1000) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `small_target` int(11) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bags`
--

DROP TABLE IF EXISTS `bags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bags` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `bag_type` int(11) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bankAccelerates`
--

DROP TABLE IF EXISTS `bankAccelerates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bankAccelerates` (
  `player_id` bigint(20) NOT NULL,
  `is_mail` int(11) NOT NULL,
  `over_time` bigint(20) NOT NULL,
  `props` varchar(255) NOT NULL,
  `use_plan` int(11) NOT NULL,
  `use_props` varchar(255) NOT NULL,
  `use_time` bigint(20) NOT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `banks`
--

DROP TABLE IF EXISTS `banks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banks` (
  `player_id` bigint(20) NOT NULL,
  `is_mail` int(11) NOT NULL,
  `over_time` bigint(20) NOT NULL,
  `rate` int(11) NOT NULL,
  `use_bind_money` bigint(20) NOT NULL,
  `use_money` bigint(20) NOT NULL,
  `use_plan` int(11) NOT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `barracks`
--

DROP TABLE IF EXISTS `barracks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `barracks` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `can_cure_num` int(11) NOT NULL,
  `can_event_cure_num` int(11) NOT NULL,
  `cure_over_time` bigint(20) NOT NULL,
  `cure_queue` int(11) NOT NULL,
  `cure_start_time` bigint(20) NOT NULL,
  `event_cure_over_time` bigint(20) NOT NULL,
  `event_cure_queue` int(11) NOT NULL,
  `event_cure_start_time` bigint(20) NOT NULL,
  `now_cure_num` int(11) NOT NULL,
  `now_event_cure_num` int(11) NOT NULL,
  `now_Make_num` int(11) NOT NULL,
  `over_time` bigint(20) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `soldier_id` int(11) NOT NULL,
  `soldier_num` int(11) NOT NULL,
  `start_time` bigint(20) NOT NULL,
  `up_num` int(11) NOT NULL,
  `up_over_time` bigint(20) NOT NULL,
  `up_start_time` bigint(20) NOT NULL,
  `up_to_solider_id` int(11) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `battle_reports`
--

DROP TABLE IF EXISTS `battle_reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `battle_reports` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `archived` bigint(20) NOT NULL,
  `fight_address_x` int(11) NOT NULL,
  `fight_address_y` int(11) NOT NULL,
  `fight_detail` mediumblob NOT NULL,
  `fight_time` bigint(20) NOT NULL,
  `past_time` bigint(20) NOT NULL,
  `read_state` int(11) NOT NULL,
  `report_content` blob NOT NULL,
  `report_type` int(11) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `belong_cell`
--

DROP TABLE IF EXISTS `belong_cell`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `belong_cell` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `atk_battle_rs` int(11) NOT NULL,
  `def_battle_rs` int(11) NOT NULL,
  `def_player_id` bigint(20) NOT NULL,
  `over_time` bigint(20) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `x` int(11) NOT NULL,
  `y` int(11) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `black_players`
--

DROP TABLE IF EXISTS `black_players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `black_players` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `alliance_short_name` varchar(255) NOT NULL,
  `area_no` int(11) NOT NULL,
  `black_player_id` bigint(20) NOT NULL,
  `castle_lv` int(11) NOT NULL,
  `castle_skin` int(11) NOT NULL,
  `fight_value` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `photo_id` int(11) NOT NULL,
  `short_name` varchar(255) NOT NULL,
  `vip_lv` int(11) NOT NULL,
  `world_id` bigint(20) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `boss_invites`
--

DROP TABLE IF EXISTS `boss_invites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `boss_invites` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `alliance_id` bigint(20) NOT NULL,
  `latest_invite_time` bigint(20) NOT NULL,
  `pos_x` int(11) NOT NULL,
  `pos_y` int(11) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `buffs`
--

DROP TABLE IF EXISTS `buffs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buffs` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `over_time` bigint(20) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `proto_id` int(11) NOT NULL,
  `start_time` bigint(20) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `call_bosses`
--

DROP TABLE IF EXISTS `call_bosses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `call_bosses` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `alliance_id` bigint(20) NOT NULL,
  `atk_records` longtext,
  `boss_id` int(11) NOT NULL,
  `help_members` varchar(5000) NOT NULL,
  `now_hp` int(11) NOT NULL,
  `over_time` bigint(20) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `protect_over_time` bigint(20) NOT NULL,
  `x` int(11) NOT NULL,
  `y` int(11) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `casino_player`
--

DROP TABLE IF EXISTS `casino_player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `casino_player` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `finish_date` bigint(20) NOT NULL,
  `palace_id` int(11) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `casinos`
--

DROP TABLE IF EXISTS `casinos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `casinos` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `atk_boss_count` int(11) NOT NULL,
  `atk_count` int(11) NOT NULL,
  `bless_count` int(11) NOT NULL,
  `free_count` int(11) NOT NULL,
  `is_atk` int(11) NOT NULL,
  `is_atk_boss` int(11) NOT NULL,
  `is_bless` int(11) NOT NULL,
  `open_free_Time` bigint(20) NOT NULL,
  `palace_id` int(11) NOT NULL,
  `palace_level` int(11) NOT NULL,
  `total_atk_boss_count` int(11) NOT NULL,
  `use_bless_count` int(11) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `casinos_winner`
--

DROP TABLE IF EXISTS `casinos_winner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `casinos_winner` (
  `public_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `alliance_short_name` varchar(255) NOT NULL,
  `area_no` int(11) NOT NULL,
  `gift_num` bigint(20) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `player_name` varchar(255) NOT NULL,
  `reward_time` bigint(20) NOT NULL,
  PRIMARY KEY (`public_id`,`id`),
  KEY `CASINOS_WINNER_PUBLIC_ID` (`public_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `castles`
--

DROP TABLE IF EXISTS `castles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `castles` (
  `world_id` bigint(20) NOT NULL,
  `db_id` bigint(20) NOT NULL,
  `castle_state` int(11) NOT NULL,
  `castle_status_end_time` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `lv` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `proto_id` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `x` int(11) NOT NULL,
  `y` int(11) NOT NULL,
  PRIMARY KEY (`world_id`,`db_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `caves`
--

DROP TABLE IF EXISTS `caves`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caves` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `hide_force_group_id` bigint(20) NOT NULL,
  `hide_over_time` bigint(20) NOT NULL,
  `hide_start_time` bigint(20) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chat_rooms`
--

DROP TABLE IF EXISTS `chat_rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_rooms` (
  `public_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `create_time` bigint(20) NOT NULL,
  `icon_proto_ids` varchar(100) NOT NULL,
  `last_chat_time` bigint(20) NOT NULL,
  `member_num` int(11) NOT NULL,
  `members` varchar(5000) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `room_name` varchar(100) NOT NULL,
  PRIMARY KEY (`public_id`,`id`),
  KEY `CHAT_ROOMS_PUBLIC_ID` (`public_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chats`
--

DROP TABLE IF EXISTS `chats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chats` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `alliance_name` varchar(50) NOT NULL,
  `alliance_pos` int(11) NOT NULL,
  `alliance_short_name` varchar(50) NOT NULL,
  `chat_time` bigint(20) NOT NULL,
  `icon_proto_id` int(11) NOT NULL,
  `kingdom_pos` int(11) NOT NULL,
  `msg` varchar(500) NOT NULL,
  `msg_type` int(11) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `player_name` varchar(50) NOT NULL,
  `player_short_name` varchar(50) NOT NULL,
  `read_type` int(11) NOT NULL,
  `vip_lv` int(11) NOT NULL,
  `wonder_pos` int(11) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `common_bosses`
--

DROP TABLE IF EXISTS `common_bosses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `common_bosses` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `boss_datas` longtext,
  `grid_id` int(11) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `common_reses`
--

DROP TABLE IF EXISTS `common_reses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `common_reses` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `grid_id` int(11) NOT NULL,
  `res_datas` longtext,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dead_boss_reses`
--

DROP TABLE IF EXISTS `dead_boss_reses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dead_boss_reses` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `farm_end_time` bigint(20) NOT NULL,
  `farm_start_time` bigint(20) NOT NULL,
  `group_id` bigint(20) NOT NULL,
  `now_res_num` int(11) NOT NULL,
  `over_time` bigint(20) NOT NULL,
  `res_id` int(11) NOT NULL,
  `x` int(11) NOT NULL,
  `y` int(11) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `every_alliance_activities`
--

DROP TABLE IF EXISTS `every_alliance_activities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `every_alliance_activities` (
  `id` bigint(20) NOT NULL,
  `alliance_id` bigint(20) NOT NULL,
  `activity_id` int(11) NOT NULL,
  `now_target` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  PRIMARY KEY (`id`,`alliance_id`),
  KEY `EVERY_ALLIANCE_ACTIVITIES_ALLIANCE_ID` (`alliance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fame_hall`
--

DROP TABLE IF EXISTS `fame_hall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fame_hall` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `alliance_id` bigint(20) NOT NULL,
  `alliance_name` varchar(50) NOT NULL,
  `alliance_short_name` varchar(50) NOT NULL,
  `create_time` bigint(20) NOT NULL,
  `occupy_time` bigint(20) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `player_name` varchar(50) NOT NULL,
  `player_photo_id` int(11) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fogs`
--

DROP TABLE IF EXISTS `fogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fogs` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `fog_id` int(11) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `solider_info` varchar(2000) NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `force_plans`
--

DROP TABLE IF EXISTS `force_plans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `force_plans` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `plan` varchar(5000) NOT NULL,
  `plan_id` int(11) NOT NULL,
  `plan_name` varchar(50) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `friend_applys`
--

DROP TABLE IF EXISTS `friend_applys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friend_applys` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `apply_castle_lev` int(11) NOT NULL,
  `apply_castle_skin` int(11) NOT NULL,
  `apply_player_alliance_short_name` varchar(50) NOT NULL,
  `apply_player_area_no` int(11) NOT NULL,
  `apply_player_id` bigint(20) NOT NULL,
  `apply_player_name` varchar(50) NOT NULL,
  `apply_player_photo_id` int(11) NOT NULL,
  `apply_player_short_name` varchar(255) NOT NULL,
  `apply_player_vip_lv` int(11) NOT NULL,
  `apply_state` int(11) NOT NULL,
  `apply_time` bigint(20) NOT NULL,
  `world_id` bigint(20) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `friend_chat`
--

DROP TABLE IF EXISTS `friend_chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friend_chat` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `alliance_name` varchar(50) NOT NULL,
  `alliance_pos` int(11) NOT NULL,
  `alliance_short_name` varchar(50) NOT NULL,
  `area_no` int(11) NOT NULL,
  `friend_id` bigint(20) NOT NULL,
  `icon_id` int(11) NOT NULL,
  `kingdom_pos` int(11) NOT NULL,
  `last_talk_time` bigint(20) NOT NULL,
  `msg_type` int(11) NOT NULL,
  `player_name` varchar(50) NOT NULL,
  `player_short_name` varchar(50) NOT NULL,
  `record` varchar(1000) NOT NULL,
  `talk_player_id` bigint(20) NOT NULL,
  `vip_lv` int(11) NOT NULL,
  `wonder_pos` int(11) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `friend_groups`
--

DROP TABLE IF EXISTS `friend_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friend_groups` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `group_name` varchar(200) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friends` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `alliance_short_name` varchar(255) NOT NULL,
  `area_no` int(11) NOT NULL,
  `castle_lv` int(11) NOT NULL,
  `castle_skin` int(11) NOT NULL,
  `fight_value` int(11) NOT NULL,
  `group_id` bigint(20) NOT NULL,
  `is_real_friend` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `photo_id` int(11) NOT NULL,
  `short_name` varchar(255) NOT NULL,
  `tar_player_id` bigint(20) NOT NULL,
  `vip_lv` int(11) NOT NULL,
  `world_id` bigint(20) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `furniture`
--

DROP TABLE IF EXISTS `furniture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `furniture` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `check_time` bigint(20) NOT NULL,
  `direction` int(11) NOT NULL,
  `end_time` bigint(20) NOT NULL,
  `floor_idx` int(11) NOT NULL,
  `produce_res` varchar(200) NOT NULL,
  `proto_id` int(11) NOT NULL,
  `start_time` bigint(20) NOT NULL,
  `state` int(11) NOT NULL,
  `x` int(11) NOT NULL,
  `y` int(11) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gift_bag_records`
--

DROP TABLE IF EXISTS `gift_bag_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gift_bag_records` (
  `player_id` bigint(20) NOT NULL,
  `records` longtext NOT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gift_bags`
--

DROP TABLE IF EXISTS `gift_bags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gift_bags` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `cur_count` int(11) NOT NULL,
  `cur_level` int(11) NOT NULL,
  `end_time` bigint(20) NOT NULL,
  `gift_id` int(11) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `guild_house`
--

DROP TABLE IF EXISTS `guild_house`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guild_house` (
  `player_id` bigint(20) NOT NULL,
  `comfort` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `heros`
--

DROP TABLE IF EXISTS `heros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `heros` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `adv_lv` int(11) NOT NULL,
  `awake` int(11) NOT NULL,
  `exp` int(11) NOT NULL,
  `hero_equip_info` longtext NOT NULL,
  `hero_strength` bigint(20) NOT NULL,
  `int_hero_address` int(11) NOT NULL,
  `int_skill_id1` int(11) NOT NULL,
  `int_skill_id2` int(11) NOT NULL,
  `int_skill_id3` int(11) NOT NULL,
  `intimacy_exp` int(11) NOT NULL,
  `intimacy_lv` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `main_hero_prison_player_id` bigint(20) NOT NULL,
  `main_hero_state` int(11) NOT NULL,
  `main_hero_state_end_time` bigint(20) NOT NULL,
  `main_hero_state_start_time` bigint(20) NOT NULL,
  `on_floor_idx` int(11) NOT NULL,
  `pos_state` int(11) NOT NULL,
  `proto_id` int(11) NOT NULL,
  `skill_id1` int(11) NOT NULL,
  `skill_id2` int(11) NOT NULL,
  `skill_id3` int(11) NOT NULL,
  `skill_id4` int(11) NOT NULL,
  `star_up_end_time` bigint(20) NOT NULL,
  `super_up_end_time` bigint(20) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `home_achievements`
--

DROP TABLE IF EXISTS `home_achievements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `home_achievements` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `achievement_id` int(11) NOT NULL,
  `progress` varchar(50) NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `home_hearts`
--

DROP TABLE IF EXISTS `home_hearts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `home_hearts` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `action_id` bigint(20) NOT NULL,
  `home_action` int(11) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `state` int(11) NOT NULL,
  `trigger_time` bigint(20) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `home_my_targets`
--

DROP TABLE IF EXISTS `home_my_targets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `home_my_targets` (
  `player_id` bigint(20) NOT NULL,
  `activity_shop_buy_num` int(11) NOT NULL,
  `alliance_coin_cost_num` bigint(20) NOT NULL,
  `alliance_competition_score` int(11) NOT NULL,
  `alliance_competition_task_num` int(11) NOT NULL,
  `alliance_shop_buy_num` int(11) NOT NULL,
  `alliance_talk_num` int(11) NOT NULL,
  `bank_money` varchar(255) NOT NULL,
  `bank_num` varchar(255) NOT NULL,
  `before_bank_num` varchar(255) NOT NULL,
  `buy_surprise` int(11) NOT NULL,
  `casino_boss_num` varchar(255) NOT NULL,
  `casino_kill_boss_num` varchar(255) NOT NULL,
  `casino_num` varchar(255) NOT NULL,
  `clear_time_info` longtext NOT NULL,
  `diamond_cost_num` bigint(20) NOT NULL,
  `diamond_shop_buy_num` int(11) NOT NULL,
  `get_king_equip_num` int(11) NOT NULL,
  `get_res_info` longtext NOT NULL,
  `have_friend_num` int(11) NOT NULL,
  `hero_equip_adv_num` int(11) NOT NULL,
  `hero_skill_lv_up_num` int(11) NOT NULL,
  `jjc_fight_num` int(11) NOT NULL,
  `jjc_shop_buy_num` int(11) NOT NULL,
  `normal_lottery_num` int(11) NOT NULL,
  `normal_move_city_num` int(11) NOT NULL,
  `rand_move_city_num` int(11) NOT NULL,
  `super_lottery_num` int(11) NOT NULL,
  `world_talk_num` int(11) NOT NULL,
  PRIMARY KEY (`player_id`),
  KEY `HOME_MY_TARGETS_PLAYER_ID` (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `home_players`
--

DROP TABLE IF EXISTS `home_players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `home_players` (
  `player_id` bigint(20) NOT NULL,
  `acc_type` int(11) NOT NULL,
  `alliance_at` bigint(20) NOT NULL,
  `alliance_coin` bigint(20) NOT NULL,
  `alliance_competition_buy_task_num` int(11) NOT NULL,
  `alliance_competition_get_task_num` int(11) NOT NULL,
  `alliance_competition_id` bigint(20) NOT NULL,
  `alliance_competition_my_score` int(11) NOT NULL,
  `alliance_competition_rank_lv` int(11) NOT NULL,
  `alliance_competition_reward` longtext NOT NULL,
  `alliance_competition_ticket` int(11) NOT NULL,
  `alliance_gift_get` varchar(255) NOT NULL,
  `alliance_gift_num` int(11) NOT NULL,
  `alliance_gift_proto_id` int(11) NOT NULL,
  `alliance_help_today_get` int(11) NOT NULL,
  `alliance_id` bigint(20) NOT NULL,
  `alliance_name` varchar(100) NOT NULL,
  `alliance_nick_name` varchar(100) NOT NULL,
  `alliance_occupy_info` varchar(2000) NOT NULL,
  `alliance_pos` varchar(1000) NOT NULL,
  `alliance_research_num` int(11) NOT NULL,
  `alliance_research_reward_time` bigint(20) NOT NULL,
  `alliance_rnum` int(11) NOT NULL,
  `alliance_short_name` varchar(100) NOT NULL,
  `alliance_talk_last` bigint(20) NOT NULL,
  `area_no` int(11) NOT NULL,
  `ban_shu_notice` varchar(50) NOT NULL,
  `big_online_reward` varchar(255) NOT NULL,
  `bind_gold` bigint(20) NOT NULL,
  `birth_time` bigint(20) NOT NULL,
  `black_players` varchar(255) NOT NULL,
  `boardcast_last` bigint(20) NOT NULL,
  `building_effect_info` longtext NOT NULL,
  `buy_limit_info` varchar(255) NOT NULL,
  `buy_strength_num` int(11) NOT NULL,
  `casino_coin` bigint(20) NOT NULL,
  `castle_lv` int(11) NOT NULL,
  `channel_id` varchar(50) NOT NULL,
  `chat_room_list` varchar(255) NOT NULL,
  `chest_free_cnt` int(11) NOT NULL,
  `chest_free_prize_time` bigint(20) NOT NULL,
  `chest_kill_cnt` int(11) NOT NULL,
  `chest_kill_prize` int(11) NOT NULL,
  `chest_kill_ref_time` bigint(20) NOT NULL,
  `coin` bigint(20) NOT NULL,
  `create_acc_time` bigint(20) NOT NULL,
  `decree` int(11) NOT NULL,
  `decree_limit` int(11) NOT NULL,
  `decree_time` bigint(20) NOT NULL,
  `diamond_shop_info` varchar(1000) NOT NULL,
  `drap_hero_info` varchar(200) NOT NULL,
  `first_pay_time` bigint(20) NOT NULL,
  `flag_color` int(11) NOT NULL,
  `flag_effect` int(11) NOT NULL,
  `flag_style` int(11) NOT NULL,
  `focus_castle_id` bigint(20) NOT NULL,
  `focus_chat_player_id` bigint(20) NOT NULL,
  `focus_chat_room_id` bigint(20) NOT NULL,
  `food` bigint(20) NOT NULL,
  `gold` bigint(20) NOT NULL,
  `gold_coin` bigint(20) NOT NULL,
  `guide_step` int(11) NOT NULL,
  `have_finish_guide_line` varchar(255) NOT NULL,
  `hero_exp_pool` bigint(20) NOT NULL,
  `honor` bigint(20) NOT NULL,
  `house_coin` bigint(20) NOT NULL,
  `in_move_server` int(11) NOT NULL,
  `inner_building_effect_info` longtext NOT NULL,
  `inner_building_unlock_area` longtext NOT NULL,
  `int_hero_num` int(11) NOT NULL,
  `iron` bigint(20) NOT NULL,
  `is_first_enter_game` int(11) NOT NULL,
  `is_first_join_alliance` int(11) NOT NULL,
  `is_not_first_chest` int(11) NOT NULL,
  `is_sleep` int(11) NOT NULL,
  `jjc_coin` int(11) NOT NULL,
  `join_activitys` longtext NOT NULL,
  `join_alliance_state` int(11) NOT NULL,
  `king_equip_bag_num` int(11) NOT NULL,
  `king_exp` int(11) NOT NULL,
  `king_lv` int(11) NOT NULL,
  `last_alliance_gift_get_time` bigint(20) NOT NULL,
  `last_alliance_help_time` bigint(20) NOT NULL,
  `last_alliance_research_time` bigint(20) NOT NULL,
  `last_buy_strength_time` bigint(20) NOT NULL,
  `last_enter_game_time` bigint(20) NOT NULL,
  `last_get_online_time` bigint(20) NOT NULL,
  `last_leave_time` bigint(20) NOT NULL,
  `last_pay_time` bigint(20) NOT NULL,
  `last_waijiao_count` bigint(20) NOT NULL,
  `login_time` bigint(20) NOT NULL,
  `main_hero_id` bigint(20) NOT NULL,
  `max_lv_prison_buff_end_time` bigint(20) NOT NULL,
  `max_mark` int(11) NOT NULL,
  `month_cards` varchar(255) NOT NULL,
  `name` varchar(50) NOT NULL,
  `next_online_reward` varchar(255) NOT NULL,
  `next_online_reward_over_time` bigint(20) NOT NULL,
  `now_use_king_equip_plan` int(11) NOT NULL,
  `op_id` int(11) NOT NULL,
  `open_alliance_send_gift` int(11) NOT NULL,
  `open_casino_time` bigint(20) NOT NULL,
  `osdk_user_id` varchar(200) NOT NULL,
  `photo_free_count` int(11) NOT NULL,
  `photo_free_time` int(11) NOT NULL,
  `photo_proto_id` int(11) NOT NULL,
  `power` int(11) NOT NULL,
  `prison_kill_num` int(11) NOT NULL,
  `pwd` varchar(50) NOT NULL,
  `quit_punish_time` int(11) NOT NULL,
  `research_effect_info` longtext NOT NULL,
  `research_info` longtext NOT NULL,
  `rookie_end_time` bigint(20) NOT NULL,
  `self_introduction` varchar(250) NOT NULL,
  `silver_coin` bigint(20) NOT NULL,
  `stone` bigint(20) NOT NULL,
  `talent_effect_info` varchar(1000) NOT NULL,
  `talent_point` varchar(100) NOT NULL,
  `time_box_num` varchar(1000) NOT NULL,
  `today_online_num` int(11) NOT NULL,
  `unit_task_id` int(11) NOT NULL,
  `unlocked_talent` varchar(1000) NOT NULL,
  `user` varchar(50) NOT NULL,
  `waijiao_count` int(11) NOT NULL,
  `wall_fire_end_time` bigint(20) NOT NULL,
  `week_alliance_research_reward` int(11) NOT NULL,
  `wood` bigint(20) NOT NULL,
  `world_id` bigint(20) NOT NULL,
  `world_talk_last` bigint(20) NOT NULL,
  `world_talk_limit` int(11) NOT NULL,
  PRIMARY KEY (`player_id`),
  KEY `HOME_PLAYERS_USER` (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `home_syncs`
--

DROP TABLE IF EXISTS `home_syncs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `home_syncs` (
  `player_id` bigint(20) NOT NULL,
  `all_barracks` longtext NOT NULL,
  `all_country_buffs` varchar(1000) NOT NULL,
  `all_effects` varchar(1000) NOT NULL,
  `buffs` varchar(1000) NOT NULL,
  `castle_state` int(11) NOT NULL,
  `castle_status_end_time` bigint(20) NOT NULL,
  `instance_floor` int(11) NOT NULL,
  `jjc_coin_reward` bigint(20) NOT NULL,
  `jjc_gold_reward` bigint(20) NOT NULL,
  `jjc_rank` int(11) NOT NULL,
  `max_jjc_rank` int(11) NOT NULL,
  `max_lv_in_prison` int(11) NOT NULL,
  `office_info` varchar(1000) NOT NULL,
  `score` int(11) NOT NULL,
  `score_time` bigint(20) NOT NULL,
  `targets` varchar(1000) NOT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `home_tasks`
--

DROP TABLE IF EXISTS `home_tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `home_tasks` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `on_world` int(11) NOT NULL,
  `over_time` bigint(20) NOT NULL,
  `task_finish` bigint(20) NOT NULL,
  `task_now_state` int(11) NOT NULL,
  `task_proto_id` int(11) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `house_theme`
--

DROP TABLE IF EXISTS `house_theme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `house_theme` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `subjectId` int(11) NOT NULL,
  `template` varchar(2000) NOT NULL,
  `theme_name` varchar(100) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `icons`
--

DROP TABLE IF EXISTS `icons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `icons` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `icon_id` int(11) NOT NULL,
  `over_time` bigint(20) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `in_make_king_equips`
--

DROP TABLE IF EXISTS `in_make_king_equips`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `in_make_king_equips` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `cost_equip_id` bigint(20) NOT NULL,
  `hei_yao_id` int(11) NOT NULL,
  `king_equip_id` int(11) NOT NULL,
  `over_time` bigint(20) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `info_by_homes`
--

DROP TABLE IF EXISTS `info_by_homes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `info_by_homes` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `build_info` varchar(5000) NOT NULL,
  `effect_info` varchar(5000) NOT NULL,
  `finishTasks` longtext NOT NULL,
  `now_skin_id` int(11) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `vip_lv` int(11) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `inner_citys`
--

DROP TABLE IF EXISTS `inner_citys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inner_citys` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `city_id` bigint(20) NOT NULL,
  `city_type` int(11) NOT NULL,
  `complete_time` bigint(20) NOT NULL,
  `destroy_state` int(11) NOT NULL,
  `destroy_time` bigint(20) NOT NULL,
  `help_id` bigint(20) NOT NULL,
  `helper_ids` varchar(5000) NOT NULL,
  `lv` int(11) NOT NULL,
  `position_index` int(11) NOT NULL,
  `start_time` bigint(20) NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `instance`
--

DROP TABLE IF EXISTS `instance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instance` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `now_fight` int(11) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `star_info` varchar(5000) NOT NULL,
  `unit_infos` varchar(5000) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `instance_drop`
--

DROP TABLE IF EXISTS `instance_drop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instance_drop` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `now_check_time` int(11) NOT NULL,
  `now_get` int(11) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `props_id` int(11) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jackpot`
--

DROP TABLE IF EXISTS `jackpot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jackpot` (
  `public_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `last_refresh_time` bigint(20) NOT NULL,
  `last_time` bigint(20) NOT NULL,
  `total_money` bigint(20) NOT NULL,
  PRIMARY KEY (`public_id`,`id`),
  KEY `JACKPOT_PUBLIC_ID` (`public_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jjc_home`
--

DROP TABLE IF EXISTS `jjc_home`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jjc_home` (
  `player_id` bigint(20) NOT NULL,
  `achievement_rewards_string` varchar(1000) NOT NULL,
  `draw_rewards_string` varchar(1000) NOT NULL,
  `items_info` varchar(1000) NOT NULL,
  `last_buy_count_time` bigint(20) NOT NULL,
  `refresh_time` bigint(20) NOT NULL,
  `score_rewards_string` varchar(500) NOT NULL,
  `times` int(11) NOT NULL,
  `today_buy_count_num` int(11) NOT NULL,
  `today_num` int(11) NOT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jjcs`
--

DROP TABLE IF EXISTS `jjcs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jjcs` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `last_fight_time` bigint(20) NOT NULL,
  `max_rank` int(11) NOT NULL,
  `next_fight_time` bigint(20) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `rank1` int(11) NOT NULL,
  `rank2` int(11) NOT NULL,
  `rank3` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  `score_time` bigint(20) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `king_equip_plans`
--

DROP TABLE IF EXISTS `king_equip_plans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `king_equip_plans` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `plan` varchar(5000) NOT NULL,
  `plan_id` int(11) NOT NULL,
  `plan_name` varchar(50) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `library`
--

DROP TABLE IF EXISTS `library`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `library` (
  `player_id` bigint(20) NOT NULL,
  `boss` longtext NOT NULL,
  `card` longtext NOT NULL,
  `equip` longtext NOT NULL,
  `monster` longtext NOT NULL,
  `new_item` varchar(200) NOT NULL,
  `prop` longtext NOT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lotterys`
--

DROP TABLE IF EXISTS `lotterys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lotterys` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `activity_over_time` bigint(20) NOT NULL,
  `discount_today` int(11) NOT NULL,
  `draw_sum` bigint(20) NOT NULL,
  `free_refresh_time` bigint(20) NOT NULL,
  `last_free_draw_time` bigint(20) NOT NULL,
  `last_use_discount_time` bigint(20) NOT NULL,
  `proto_Id` int(11) NOT NULL,
  `rest_free_draw_times` int(11) NOT NULL,
  `this_round_draw_sum` bigint(20) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mails`
--

DROP TABLE IF EXISTS `mails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mails` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `attach` varchar(255) NOT NULL,
  `extend1` varchar(200) NOT NULL,
  `gm_email_id` bigint(20) NOT NULL,
  `is_draw` int(11) NOT NULL,
  `is_read` int(11) NOT NULL,
  `is_sign` int(11) NOT NULL,
  `mail_info_string` varchar(1000) NOT NULL,
  `send_alliance_id` bigint(20) NOT NULL,
  `send_alliance_short_name` varchar(50) NOT NULL,
  `send_player_id` bigint(20) NOT NULL,
  `send_player_name` varchar(50) NOT NULL,
  `send_player_nick_name` varchar(50) NOT NULL,
  `send_time` bigint(20) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `marks`
--

DROP TABLE IF EXISTS `marks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marks` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `markGroup` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `plt_area_no` int(11) NOT NULL,
  `x` int(11) NOT NULL,
  `y` int(11) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `masses`
--

DROP TABLE IF EXISTS `masses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `masses` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `alliance_id` bigint(20) NOT NULL,
  `arrive_time` bigint(20) NOT NULL,
  `fight_type` int(11) NOT NULL,
  `go_time` bigint(20) NOT NULL,
  `goto_x` int(11) NOT NULL,
  `goto_y` int(11) NOT NULL,
  `group_id` bigint(20) NOT NULL,
  `main_player_id` bigint(20) NOT NULL,
  `mass_name` varchar(100) NOT NULL,
  `mass_start_time` bigint(20) NOT NULL,
  `mass_state` int(11) NOT NULL,
  `membere_infos` varchar(255) NOT NULL,
  `start_x` int(11) NOT NULL,
  `start_y` int(11) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `merchant_ships`
--

DROP TABLE IF EXISTS `merchant_ships`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `merchant_ships` (
  `player_id` bigint(20) NOT NULL,
  `next_re_time` bigint(20) NOT NULL,
  `now_times` int(11) NOT NULL,
  `shop` varchar(2000) NOT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `move_server_cell_lock`
--

DROP TABLE IF EXISTS `move_server_cell_lock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `move_server_cell_lock` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `start_time` bigint(20) NOT NULL,
  `x` int(11) NOT NULL,
  `y` int(11) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `my_alliance_gifts`
--

DROP TABLE IF EXISTS `my_alliance_gifts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `my_alliance_gifts` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `gift_id` int(11) NOT NULL,
  `gift_info` varchar(500) NOT NULL,
  `is_get` int(11) NOT NULL,
  `over_time` bigint(20) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `my_targets`
--

DROP TABLE IF EXISTS `my_targets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `new_equips`
--

DROP TABLE IF EXISTS `new_equips`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `new_equips` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `bag_id` bigint(20) NOT NULL,
  `card_info` varchar(5000) NOT NULL,
  `equip_on_address` int(11) NOT NULL,
  `equip_proto_id` int(11) NOT NULL,
  `exp` int(11) NOT NULL,
  `foreign_id` bigint(20) NOT NULL,
  `have_num` int(11) NOT NULL,
  `lv` int(11) NOT NULL,
  `propertys` varchar(1000) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notices`
--

DROP TABLE IF EXISTS `notices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notices` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `content` varchar(800) NOT NULL,
  `frequency` int(11) NOT NULL,
  `notice_next_time` bigint(20) NOT NULL,
  `notice_position` int(11) NOT NULL,
  `notice_tid` int(11) NOT NULL,
  `notice_time_end` bigint(20) NOT NULL,
  `notice_time_start` bigint(20) NOT NULL,
  `notice_type` int(11) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pays`
--

DROP TABLE IF EXISTS `pays`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pays` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `pay_id` int(11) NOT NULL,
  `pay_time` int(11) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `player_activities`
--

DROP TABLE IF EXISTS `player_activities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `player_activities` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `activity_id` int(11) NOT NULL,
  `castle_lv` int(11) NOT NULL,
  `now_target` int(11) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `score` int(11) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `player_setting`
--

DROP TABLE IF EXISTS `player_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `player_setting` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `caution_lv` int(11) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `refuse_disturb_end` int(11) NOT NULL,
  `refuse_disturb_open` int(11) NOT NULL,
  `switches` longtext NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `players` (
  `world_id` bigint(20) NOT NULL,
  `db_id` bigint(20) NOT NULL,
  `acc_type` int(11) NOT NULL,
  `account_id` bigint(20) NOT NULL,
  `alliance_at` bigint(20) NOT NULL,
  `alliance_coin` bigint(20) NOT NULL,
  `alliance_gift_get` varchar(255) NOT NULL,
  `alliance_gift_num` int(11) NOT NULL,
  `alliance_gift_proto_id` int(11) NOT NULL,
  `alliance_id` bigint(20) NOT NULL,
  `alliance_name` varchar(100) NOT NULL,
  `alliance_nick_name` varchar(100) NOT NULL,
  `alliance_occupy_info` varchar(2000) NOT NULL,
  `alliance_pos` varchar(1000) NOT NULL,
  `alliance_research_num` int(11) NOT NULL,
  `alliance_research_reward_time` bigint(20) NOT NULL,
  `alliance_rnum` int(11) NOT NULL,
  `alliance_short_name` varchar(100) NOT NULL,
  `area_no` int(11) NOT NULL,
  `auto_hunter` int(11) NOT NULL,
  `auto_use_energy` int(11) NOT NULL,
  `ban_shu_notice` varchar(50) NOT NULL,
  `bind_gold` bigint(20) NOT NULL,
  `birth_time` bigint(20) NOT NULL,
  `chest_free_cnt` int(11) NOT NULL,
  `chest_free_prize_time` bigint(20) NOT NULL,
  `chest_kill_cnt` int(11) NOT NULL,
  `chest_kill_prize` int(11) NOT NULL,
  `chest_kill_ref_time` bigint(20) NOT NULL,
  `coin` bigint(20) NOT NULL,
  `decree` int(11) NOT NULL,
  `decree_time` bigint(20) NOT NULL,
  `diamond_shop_info` varchar(1000) NOT NULL,
  `first_pay_time` bigint(20) NOT NULL,
  `flag_color` int(11) NOT NULL,
  `flag_effect` int(11) NOT NULL,
  `flag_style` int(11) NOT NULL,
  `focus_castle_id` bigint(20) NOT NULL,
  `food` bigint(20) NOT NULL,
  `gold` bigint(20) NOT NULL,
  `guide_step` int(11) NOT NULL,
  `hero_exp_pool` bigint(20) NOT NULL,
  `honor` bigint(20) NOT NULL,
  `house_coin` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `in_move_server` int(11) NOT NULL,
  `int_hero_num` int(11) NOT NULL,
  `iron` bigint(20) NOT NULL,
  `is_first_join_alliance` int(11) NOT NULL,
  `is_not_first_chest` int(11) NOT NULL,
  `is_sleep` int(11) NOT NULL,
  `jjc_rank` int(11) NOT NULL,
  `jjc_rank_gold` bigint(20) NOT NULL,
  `jjc_rank_JJcoin` bigint(20) NOT NULL,
  `join_activitys` longtext NOT NULL,
  `join_alliance_reqs` varchar(2000) NOT NULL,
  `join_chat_rooms` varchar(100) NOT NULL,
  `king_exp` int(11) NOT NULL,
  `king_lv` int(11) NOT NULL,
  `last_alliance_gift_get_time` bigint(20) NOT NULL,
  `last_alliance_research_time` bigint(20) NOT NULL,
  `last_enter_game_time` bigint(20) NOT NULL,
  `last_leave_time` bigint(20) NOT NULL,
  `last_pay_time` bigint(20) NOT NULL,
  `last_strength_time` bigint(20) NOT NULL,
  `last_waijiao_count` bigint(20) NOT NULL,
  `login_time` bigint(20) NOT NULL,
  `main_hero_id` bigint(20) NOT NULL,
  `max_Lv_Prison_Buff_End_Time` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `new_player_activity` int(11) NOT NULL,
  `open_alliance_send_gift` int(11) NOT NULL,
  `photo_free_count` int(11) NOT NULL,
  `photo_free_time` int(11) NOT NULL,
  `photo_proto_id` int(11) NOT NULL,
  `prison_kill_num` int(11) NOT NULL,
  `quit_punish_time` int(11) NOT NULL,
  `rookie_end_time` bigint(20) NOT NULL,
  `self_introduction` varchar(250) NOT NULL,
  `stone` bigint(20) NOT NULL,
  `strength` int(11) NOT NULL,
  `unit_task_id` int(11) NOT NULL,
  `user` varchar(50) NOT NULL,
  `waijiao_count` int(11) NOT NULL,
  `wall_Fire_End_Time` bigint(20) NOT NULL,
  `week_alliance_research_reward` int(11) NOT NULL,
  `wood` bigint(20) NOT NULL,
  PRIMARY KEY (`world_id`,`db_id`),
  KEY `HOME_PLAYERS_USER` (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `prisons`
--

DROP TABLE IF EXISTS `prisons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prisons` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `prison_player_id` bigint(20) NOT NULL,
  `ransom` bigint(20) NOT NULL,
  `reward_gold` bigint(20) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `public_activities`
--

DROP TABLE IF EXISTS `public_activities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `public_activities` (
  `public_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `activity_id` int(11) NOT NULL,
  `now_state` int(11) NOT NULL,
  `over_time` bigint(20) NOT NULL,
  `reward_time` bigint(20) NOT NULL,
  PRIMARY KEY (`public_id`,`id`),
  KEY `PUBLIC_ACTIVITIES_PUBLIC_ID` (`public_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `quota_bags`
--

DROP TABLE IF EXISTS `quota_bags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quota_bags` (
  `player_id` bigint(20) NOT NULL,
  `degree` int(11) NOT NULL,
  `end_time` bigint(20) NOT NULL,
  `quota_bag_id` int(11) NOT NULL,
  `reward_id` int(11) NOT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `relices`
--

DROP TABLE IF EXISTS `relices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relices` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `grid_id` int(11) NOT NULL,
  `relic_datas` longtext,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resource_yields`
--

DROP TABLE IF EXISTS `resource_yields`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_yields` (
  `player_id` bigint(20) NOT NULL,
  `alliance_food` int(11) NOT NULL,
  `alliance_iron` int(11) NOT NULL,
  `alliance_research_food` int(11) NOT NULL,
  `alliance_research_iron` int(11) NOT NULL,
  `alliance_research_stone` int(11) NOT NULL,
  `alliance_research_wood` int(11) NOT NULL,
  `alliance_stone` int(11) NOT NULL,
  `alliance_wood` int(11) NOT NULL,
  `building_coin` int(11) NOT NULL,
  `building_food` int(11) NOT NULL,
  `building_iron` int(11) NOT NULL,
  `building_stone` int(11) NOT NULL,
  `building_wood` int(11) NOT NULL,
  `cal_time` bigint(20) NOT NULL,
  `coin_limit` bigint(20) NOT NULL,
  `food_limit` bigint(20) NOT NULL,
  `iron_limit` bigint(20) NOT NULL,
  `mem_food` int(11) NOT NULL,
  `mem_iron` int(11) NOT NULL,
  `mem_stone` int(11) NOT NULL,
  `mem_wood` int(11) NOT NULL,
  `npc_city_food` int(11) NOT NULL,
  `npc_city_iron` int(11) NOT NULL,
  `npc_city_stone` int(11) NOT NULL,
  `npc_city_wood` int(11) NOT NULL,
  `research_coin` int(11) NOT NULL,
  `research_food` int(11) NOT NULL,
  `research_iron` int(11) NOT NULL,
  `research_stone` int(11) NOT NULL,
  `research_wood` int(11) NOT NULL,
  `stone_limit` bigint(20) NOT NULL,
  `talent_coin` int(11) NOT NULL,
  `talent_food` int(11) NOT NULL,
  `talent_iron` int(11) NOT NULL,
  `talent_stone` int(11) NOT NULL,
  `talent_wood` int(11) NOT NULL,
  `total_coin` int(11) NOT NULL,
  `total_food` int(11) NOT NULL,
  `total_iron` int(11) NOT NULL,
  `total_stone` int(11) NOT NULL,
  `total_wood` int(11) NOT NULL,
  `use_up_coin` int(11) NOT NULL,
  `use_up_food` int(11) NOT NULL,
  `use_up_iron` int(11) NOT NULL,
  `use_up_stone` int(11) NOT NULL,
  `use_up_wood` int(11) NOT NULL,
  `vip_coin` int(11) NOT NULL,
  `vip_food` int(11) NOT NULL,
  `vip_iron` int(11) NOT NULL,
  `vip_stone` int(11) NOT NULL,
  `vip_wood` int(11) NOT NULL,
  `wood_limit` bigint(20) NOT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `room_chat_record`
--

DROP TABLE IF EXISTS `room_chat_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_chat_record` (
  `public_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `alliance_id` bigint(20) NOT NULL,
  `alliance_name` varchar(50) NOT NULL,
  `alliance_pos` int(11) NOT NULL,
  `alliance_short_name` varchar(50) NOT NULL,
  `area_no` int(11) NOT NULL,
  `chat_time` bigint(20) NOT NULL,
  `icon_proto_id` int(11) NOT NULL,
  `kingdom_pos` int(11) NOT NULL,
  `msg` varchar(250) NOT NULL,
  `msg_type` int(11) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `player_name` varchar(50) NOT NULL,
  `player_short_name` varchar(50) NOT NULL,
  `plt_area_no` bigint(20) NOT NULL,
  `room_id` bigint(20) NOT NULL,
  `vip_lv` int(11) NOT NULL,
  `wonder_pos` int(11) NOT NULL,
  PRIMARY KEY (`public_id`,`id`),
  KEY `ROOM_CHAT_RECORD_PUBLIC_ID` (`public_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `server_activities`
--

DROP TABLE IF EXISTS `server_activities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `server_activities` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `activity_id` int(11) NOT NULL,
  `over_time` bigint(20) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `skins`
--

DROP TABLE IF EXISTS `skins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skins` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `is_use` int(11) NOT NULL,
  `skin_type` int(11) NOT NULL,
  `star` int(11) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `talent_plans`
--

DROP TABLE IF EXISTS `talent_plans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `talent_plans` (
  `player_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `plan` varchar(5000) NOT NULL,
  `plan_id` int(11) NOT NULL,
  `plan_name` varchar(50) NOT NULL,
  PRIMARY KEY (`player_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasks` (
  `world_id` bigint(20) NOT NULL,
  `db_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `over_time` bigint(20) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `task_finish` bigint(20) NOT NULL,
  `task_now_state` int(11) NOT NULL,
  `task_proto_id` int(11) NOT NULL,
  PRIMARY KEY (`world_id`,`db_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thumb_info`
--

DROP TABLE IF EXISTS `thumb_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thumb_info` (
  `player_id` bigint(20) NOT NULL,
  `check_time` bigint(20) NOT NULL,
  `thumb_in` varchar(2000) NOT NULL,
  `thumb_in_alliance` varchar(2000) NOT NULL,
  `thumb_in_num` int(11) NOT NULL,
  `thumb_in_total` int(11) NOT NULL,
  `thumb_out` varchar(2000) NOT NULL,
  `thumb_out_num` int(11) NOT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `transport_requests`
--

DROP TABLE IF EXISTS `transport_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transport_requests` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `alliance_id` bigint(20) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `res` varchar(1000) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `version`
--

DROP TABLE IF EXISTS `version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `version` (
  `id` bigint(20) NOT NULL,
  `db_version` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vips`
--

DROP TABLE IF EXISTS `vips`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vips` (
  `player_id` bigint(20) NOT NULL,
  `continue_online_day` int(11) NOT NULL,
  `last_get_vip_reward_time` bigint(20) NOT NULL,
  `last_refresh_energy_time` bigint(20) NOT NULL,
  `vipExp` int(11) NOT NULL,
  `vip_lv` int(11) NOT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `walk_force_groups`
--

DROP TABLE IF EXISTS `walk_force_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `walk_force_groups` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `goto_run_type` int(11) NOT NULL,
  `is_get_main_hero` int(11) NOT NULL,
  `main_player_id` bigint(20) NOT NULL,
  `mass_id` bigint(20) NOT NULL,
  `now_x` int(11) NOT NULL,
  `now_y` int(11) NOT NULL,
  `running_state` int(11) NOT NULL,
  `state_change_time` bigint(20) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `walk_forces`
--

DROP TABLE IF EXISTS `walk_forces`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `walk_forces` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `force_group_id` bigint(20) NOT NULL,
  `hero_ids` varchar(200) NOT NULL,
  `initial_soliders` varchar(2000) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `res` varchar(1000) NOT NULL,
  `res_from_info` varchar(100) NOT NULL,
  `soliders` varchar(2000) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `walks`
--

DROP TABLE IF EXISTS `walks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `walks` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `initial_walk_time` int(11) NOT NULL,
  `is_atk_monster_home` int(11) NOT NULL,
  `is_conflict` int(11) NOT NULL,
  `march_aims_x` int(11) NOT NULL,
  `march_aims_y` int(11) NOT NULL,
  `march_place_x` int(11) NOT NULL,
  `march_place_y` int(11) NOT NULL,
  `march_state` int(11) NOT NULL,
  `march_time_arrival` bigint(20) NOT NULL,
  `march_time_off` bigint(20) NOT NULL,
  `now_walk_robot_x` int(11) NOT NULL,
  `now_walk_robot_y` int(11) NOT NULL,
  `walk_force_group_id` bigint(20) NOT NULL,
  `walk_speed` double NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wonder`
--

DROP TABLE IF EXISTS `wonder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wonder` (
  `world_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `award_info` varchar(1000) NOT NULL,
  `belong_to_alliance_id` bigint(20) NOT NULL,
  `buff_cool_time` bigint(20) NOT NULL,
  `buff_info` varchar(1000) NOT NULL,
  `init_time` bigint(20) NOT NULL,
  `last_notice_time` bigint(20) NOT NULL,
  `notice` varchar(5000) NOT NULL,
  `occupy_group_id` bigint(20) NOT NULL,
  `occupy_time` bigint(20) NOT NULL,
  `occupy_start_time` bigint(20) NOT NULL,
  `office_info` varchar(1000) NOT NULL,
  `pardon_count` int(11) NOT NULL,
  `rank_info` longtext NOT NULL,
  `status` int(11) NOT NULL,
  `status_over_time` bigint(20) NOT NULL,
  `war_finish_time` bigint(20) NOT NULL,
  `war_start_time` bigint(20) NOT NULL,
  `wonder_proto_id` int(11) NOT NULL,
  `wonder_war_status` int(11) NOT NULL,
  PRIMARY KEY (`world_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `worldheros`
--

DROP TABLE IF EXISTS `worldheros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `worldheros` (
  `world_id` bigint(20) NOT NULL,
  `db_id` bigint(20) NOT NULL,
  `adv_lv` int(11) NOT NULL,
  `awake` int(11) NOT NULL,
  `exp` int(11) NOT NULL,
  `hero_equip_info` longtext NOT NULL,
  `hero_strength` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `int_hero_address` int(11) NOT NULL,
  `int_skill_id1` int(11) NOT NULL,
  `int_skill_id2` int(11) NOT NULL,
  `int_skill_id3` int(11) NOT NULL,
  `intimacy_exp` int(11) NOT NULL,
  `intimacy_lv` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `main_hero_prison_player_id` bigint(20) NOT NULL,
  `main_hero_state` int(11) NOT NULL,
  `main_hero_state_end_time` bigint(20) NOT NULL,
  `main_hero_state_start_time` bigint(20) NOT NULL,
  `on_floor_idx` int(11) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `pos_state` int(11) NOT NULL,
  `proto_id` int(11) NOT NULL,
  `skill_id1` int(11) NOT NULL,
  `skill_id2` int(11) NOT NULL,
  `skill_id3` int(11) NOT NULL,
  `skill_id4` int(11) NOT NULL,
  `star_up_end_time` bigint(20) NOT NULL,
  `super_up_end_time` bigint(20) NOT NULL,
  PRIMARY KEY (`world_id`,`db_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on
