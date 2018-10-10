package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.OneToOnePlayerAsset
import org.hibernate.annotations.Index
import org.hibernate.annotations.Type
import javax.persistence.*

const val HOME_PLAYER_ACCOUNT_NAMED_QUERY = "findPlayerByAccountName"
const val HOME_PLAYER_NAMED_QUERY = "findPlayerByPlayerId"

@NamedQueries(
    NamedQuery(
        name = HOME_PLAYER_ACCOUNT_NAMED_QUERY,
        // language=HQL
        query = "from HomePlayerEntity where user = :user"
    ),
    NamedQuery(
        name = HOME_PLAYER_NAMED_QUERY,
        // language=HQL
        query = "from HomePlayerEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "home_players")
data class HomePlayerEntity(
    @Id
    @Column(name = "player_id", nullable = false)
    override var playerId: Long,

    @Column(name = "world_id", nullable = false)
    var worldId: Long,  // 世界服Id

    @Column(name = "user", nullable = false, length = 50)
    @Index(name = "HOME_PLAYERS_USER")
    var user: String,        // 账户名

    @Column(name = "pwd", nullable = false, length = 50)
    var pwd: String,        // 密码

    @Column(name = "osdk_user_id", nullable = false, length = 200)
    var osdkUserId: String,       // 用户的唯一标识

    @Column(name = "channel_id", nullable = false, length = 50)
    var channelId: String,        // 用户来源 id	cps id，手游的分包ID，默认值为0。cps id在手游分包中是必须要发的数据

    @Column(name = "op_id", nullable = false)
    var opId: Int,        // 玩家来自哪个运营商

    @Column(name = "create_acc_time", nullable = false)
    var createAccTime: Long,  // 注册时间)

    @Column(name = "area_no", nullable = false)
    var areaNo: Int,                 // 所属地图区,实际上游戏服的AreaNo已经没有意义了.这个字段值会在初始化的时候读配置来填充,之后会在迁服的时候来被修改

    @Column(name = "name", nullable = false, length = 50)
    var name: String,  // 玩家角色名

    @Column(name = "decree", nullable = false)
    var decree: Int,                // 政令 通过 GetDecree() 获取  行动力

    @Column(name = "decree_limit", nullable = false)
    var decreeLimit: Int,                // 政令上限

    @Column(name = "decree_time", nullable = false)
    var decreeTime: Long,  // 政令结算时间

    @Column(name = "power", nullable = false)
    var power: Int,  // 势力

    @Column(name = "wood", nullable = false)
    var wood: Long,  // 木材

    @Column(name = "iron", nullable = false)
    var iron: Long,  // 铁

    @Column(name = "stone", nullable = false)
    var stone: Long,  // 石头

    @Column(name = "food", nullable = false)
    var food: Long,  // 食物

    @Column(name = "gold", nullable = false)
    var gold: Long,  // 元宝

    @Column(name = "bind_gold", nullable = false)
    var bindGold: Long,  // 绑定元宝

    @Column(name = "coin", nullable = false)
    var coin: Long,  // 铜币

    @Column(name = "casino_coin", nullable = false)
    var casinoCoin: Long,  // 赌场币

    @Column(name = "silver_coin", nullable = false)
    var silverCoin: Long,  // 银币

    @Column(name = "gold_coin", nullable = false)
    var goldCoin: Long,  // 金币

    @Column(name = "honor", nullable = false)
    var honor: Long,  // 总荣誉

    @Column(name = "alliance_coin", nullable = false)
    var allianceCoin: Long,  // 联盟币

    @Column(name = "hero_exp_pool", nullable = false)
    var heroExpPool: Long,  // 英雄经验池

    @Column(name = "focus_castle_id", nullable = false)
    var focusCastleId: Long,  // 关注城池ID

    @Column(name = "alliance_id", nullable = false)
    var allianceId: Long,    // 所属同盟ID

    @Column(name = "alliance_at", nullable = false)
    var allianceAt: Long,  // 加入联盟时间

    @Column(name = "alliance_pos", nullable = false, length = 1000)
    var alliancePos: String,      // 玩家的所有帮派职位

    @Column(name = "alliance_name", nullable = false, length = 100)
    var allianceName: String,  // 联盟名称

    @Column(name = "alliance_short_name", nullable = false, length = 100)
    var allianceShortName: String,  // 联盟简称

    @Column(name = "flag_color", nullable = false)
    var flagColor: Int,           // 联盟旗帜的颜色

    @Column(name = "flag_style", nullable = false)
    var flagStyle: Int,           // 联盟旗帜的样式

    @Column(name = "flag_effect", nullable = false)
    var flagEffect: Int,           // 联盟旗帜图案

    @Column(name = "alliance_rnum", nullable = false)
    var allianceRnum: Int,           // R1 - R5 阶级

    @Column(name = "quit_punish_time", nullable = false)
    var quitPunishTime: Int,  // 退盟惩罚结束时间

    @Column(name = "self_introduction", nullable = false, length = 250)
    var selfIntroduction: String,       // 自我介绍

    @Column(name = "last_enter_game_time", nullable = false)
    var lastEnterGameTime: Long,  // 最近一次上线时间

    @Column(name = "last_leave_time", nullable = false)
    var lastLeaveTime: Long,  // 最后一次离线时间

    @Column(name = "birth_time", nullable = false)
    var birthTime: Long,  // 玩家创建账号时间

    @Column(name = "rookie_end_time", nullable = false)
    var rookieEndTime: Long,  // 新手期结束时间

    @Column(name = "chest_free_prize_time", nullable = false)
    var chestFreePrizeTime: Long,  // 免费宝箱领取时间

    @Column(name = "chest_free_cnt", nullable = false)
    var chestFreeCnt: Int,                // 免费宝箱数量

    @Column(name = "chest_kill_ref_time", nullable = false)
    var chestKillRefTime: Long,  // 击杀宝箱时间

    @Column(name = "chest_kill_cnt", nullable = false)
    var chestKillCnt: Int,                // 击杀宝箱击杀数量

    @Column(name = "chest_kill_prize", nullable = false)
    var chestKillPrize: Int,                // 是否已经领取击杀宝箱

    @Column(name = "is_not_first_chest", nullable = false)
    var isNotFirstChest: Int,                // 是否不是首次抽取宝箱

    @Column(name = "photo_free_time", nullable = false)
    var photoFreeTime: Int,  // 可免费换头像时间

    @Column(name = "photo_proto_id", nullable = false, length = 250)
    var photoProtoId: Int,       // 头像

    @Column(name = "photo_free_count", nullable = false)
    var photoFreeCount: Int,                // 初始剩余免费更换头像次数

    @Column(name = "world_talk_limit", nullable = false)
    var worldTalkLimit: Int,                // 每天世界频道免费聊天次数

    @Column(name = "world_talk_last", nullable = false)
    var worldTalkLast: Long,  // 世界频道最新免费聊天时间

    @Column(name = "alliance_talk_last", nullable = false)
    var allianceTalkLast: Long,  // 世界频道最新免费聊天时间

    @Column(name = "boardcast_last", nullable = false)
    var boardcastLast: Long,  // 世界频道最新免费聊天时间

    @Column(name = "guide_step", nullable = false)
    var guideStep: Int,  // 新手引导步骤ID

    @Column(name = "acc_type", nullable = false)
    var accType: Int,  // 角色类型，标识角色的分类属性，1:正常 2:测试 3:gm/福利 4:其他

    @Column(name = "is_sleep", nullable = false)
    var isSleep: Int,  // 玩家是否处于休眠状态：0-非休眠状态；1-休眠状态

    @Column(name = "first_pay_time", nullable = false)
    var firstPayTime: Long,  // 首次充值时间

    @Column(name = "last_pay_time", nullable = false)
    var lastPayTime: Long,  // 最后一次充值时间

    @Column(name = "ban_shu_notice", nullable = false, length = 50)
    var banShuNotice: String,  // TODO 版署评审

    @Column(name = "jjc_coin", nullable = false)
    var jjcCoin: Int,  // 竞技币数量

    @Column(name = "house_coin", nullable = false)
    var houseCoin: Long,  // 家园币数量

    @Column(name = "waijiao_count", nullable = false)
    var waijiaoCount: Int,                // 今天外交次数

    @Column(name = "last_waijiao_count", nullable = false)
    var lastWaijiaoCount: Long,  // 上次外加时间

    @Column(name = "drap_hero_info", nullable = false, length = 200)
    var drapHeroInfo: String,  // 玩家召唤武将情况

    @Column(name = "is_first_join_alliance", nullable = false)
    var isFirstJoinAlliance: Int,  // 玩家是否加入过帮派 0-否 1-是
    // 玩家的科技信息

    @Type(type = "text")
    @Column(name = "research_info", nullable = false)
    var researchInfo: String,  // 玩家科技升级情况

    @Type(type = "text")
    @Column(name = "research_effect_info", nullable = false)
    var researchEffectInfo: String,  // 玩家科技效果集合

    @Type(type = "text")
    @Column(name = "building_effect_info", nullable = false)
    var buildingEffectInfo: String,  // 玩家建筑效果集合

    @Type(type = "text")
    @Column(name = "inner_building_effect_info", nullable = false)
    var innerBuildingEffectInfo: String,  // 玩家内城建筑效果集合

    @Type(type = "text")
    @Column(name = "inner_building_unlock_area", nullable = false)
    var innerBuildingUnlockArea: String,  // 玩家内城区域解锁表

    @Column(name = "alliance_nick_name", nullable = false, length = 100)
    var allianceNickName: String,  // 联盟昵称

    @Column(name = "int_hero_num", nullable = false)
    var intHeroNum: Int,  // 内政英雄数量

    @Column(name = "diamond_shop_info", nullable = false, length = 1000)
    var diamondShopInfo: String,  // 玩家钻石商城限购物品购买情况

    @Column(name = "alliance_research_num", nullable = false)
    var allianceResearchNum: Int,                // 联盟普通捐献次数

    @Column(name = "last_alliance_research_time", nullable = false)
    var lastAllianceResearchTime: Long,  // 上次联盟普通捐献次数回复时间

    @Column(name = "week_alliance_research_reward", nullable = false)
    var weekAllianceResearchReward: Int,                // 联盟普通捐献获取到的贡献值

    @Column(name = "alliance_research_reward_time", nullable = false)
    var allianceResearchRewardTime: Long,  // 上次普通捐献时间

    @Column(name = "time_box_num", nullable = false, length = 1000)
    var timeBoxNum: String,  // 时光之盒研究槽位

    @Column(name = "talent_point", nullable = false, length = 100)
    var talentPoint: String,   //当前天赋点

    @Column(name = "unlocked_talent", nullable = false, length = 1000)
    var unlockedTalent: String,  //已解锁天赋

    @Column(name = "talent_effect_info", nullable = false, length = 1000)
    var talentEffectInfo: String,  //天赋效果

    @Column(name = "king_lv", nullable = false)
    var kingLv: Int,  // 君主等级

    @Column(name = "king_exp", nullable = false)
    var kingExp: Int,  // 君主经验

    @Column(name = "main_hero_id", nullable = false)
    var mainHeroId: Long,    // 领主ID 这个值不可能是0

    @Column(name = "alliance_gift_get", nullable = false)
    var allianceGiftGet: String,  // 本日已经获得过的联盟礼物档次

    @Column(name = "last_alliance_gift_get_time", nullable = false)
    var lastAllianceGiftGetTime: Long,         // 上次获得过的联盟礼物档次的时间

    @Column(name = "alliance_gift_proto_id", nullable = false)
    var allianceGiftProtoId: Int,                       // 剩余可领取的宝箱模版ID

    @Column(name = "alliance_gift_num", nullable = false)
    var allianceGiftNum: Int,                       // 剩余可领取的宝箱数量

    @Column(name = "open_alliance_send_gift", nullable = false)
    var openAllianceSendGift: Int,  //是否开启联盟赠礼功能 0-未开启 1-开启

    @Column(name = "unit_task_id", nullable = false)
    var unitTaskId: Int,  // 当前已完成的的最新章节任务ID

    @Column(name = "prison_kill_num", nullable = false)
    var prisonKillNum: Int,  // 一共杀过多少个玩家

    @Column(name = "king_equip_bag_num", nullable = false)
    var kingEquipBagNum: Int,                                              // 君主装备背包容量

    @Column(name = "last_get_online_time", nullable = false)
    var lastGetOnlineTime: Long,  // 上次领取在线礼包任务时间

    @Column(name = "today_online_num", nullable = false)
    var todayOnlineNum: Int,                                              // 本日领取次数

    @Column(name = "next_online_reward", nullable = false)
    var nextOnlineReward: String,                                   // 当前档奖励

    @Column(name = "next_online_reward_over_time", nullable = false)
    var nextOnlineRewardOverTime: Long,  // 当前档时间

    @Column(name = "big_online_reward", nullable = false)
    var bigOnlineReward: String,                                   // 本日大奖奖励
    // 联盟总动员字段

    @Column(name = "alliance_competition_id", nullable = false)
    var allianceCompetitionId: Long,  // 本次联盟总动员为哪个帮派效力

    @Column(name = "alliance_competition_my_score", nullable = false)
    var allianceCompetitionMyScore: Int,  // 本次联盟总动员获得了多少积分

    @Column(name = "alliance_competition_rank_lv", nullable = false)
    var allianceCompetitionRankLv: Int,              // 本次联盟总动员效力帮派的段位

    @Column(name = "alliance_competition_ticket", nullable = false)
    var allianceCompetitionTicket: Int,              // 当前是否拥有联盟总动员门票 0-无 1-有

    @Column(name = "alliance_competition_get_task_num", nullable = false)
    var allianceCompetitionGetTaskNum: Int,              // 可领任务次数

    @Column(name = "alliance_competition_buy_task_num", nullable = false)
    var allianceCompetitionBuyTaskNum: Int,              // 已购买任务次数

    @Type(type = "text")
    @Column(name = "alliance_competition_reward", nullable = false)
    var allianceCompetitionReward: String,   // 本次的阶段奖励

    @Column(name = "alliance_help_today_get", nullable = false)
    var allianceHelpTodayGet: Int,  // 精英关卡玩家最高挑战层

    @Column(name = "last_alliance_help_time", nullable = false)
    var lastAllianceHelpTime: Long,  // 精英关卡玩家最高挑战层

    @Column(name = "buy_strength_num", nullable = false)
    var buyStrengthNum: Int,

    @Column(name = "last_buy_strength_time", nullable = false)
    var lastBuyStrengthTime: Long,

    @Column(name = "now_use_king_equip_plan", nullable = false)
    var nowUseKingEquipPlan: Int,

    @Column(name = "max_mark", nullable = false)
    var maxMark: Int,

    @Type(type = "text")
    @Column(name = "join_activitys", nullable = false)
    var joinActivitys: String,   // 玩家参与过的活动信息

    @Column(name = "buy_limit_info", nullable = false)
    var buyLimitInfo: String,

    @Column(name = "login_time", nullable = false)
    var loginTime: Long, //`sql:"-"` // 登陆时间

    @Column(name = "wall_fire_end_time", nullable = false)
    var wallFireEndTime: Long, //`sql:"-"` // 自动灭火时间

    @Column(name = "max_lv_prison_buff_end_time", nullable = false)
    var maxLvPrisonBuffEndTime: Long, // 最高监禁等级领主buff结束时间

    @Column(name = "castle_lv", nullable = false)
    var castleLv: Int,

    @Column(name = "focus_chat_room_id", nullable = false)
    var focusChatRoomId: Long, //

    @Column(name = "focus_chat_player_id", nullable = false)
    var focusChatPlayerId: Long, //

    @Column(name = "chat_room_list", nullable = false)
    var chatRoomList: String,

    @Column(name = "open_casino_time", nullable = false)
    var openCasinoTime: Long,

    @Column(name = "black_players", nullable = false)
    var blackPlayers: String,

    @Column(name = "is_first_enter_game", nullable = false)
    var isFirstEnterGame: Int,

    @Column(name = "have_finish_guide_line", nullable = false)
    var haveFinishGuideLine: String,

    @Column(name = "in_move_server", nullable = false)
    var inMoveServer: Int,

    @Column(name = "month_cards", nullable = false)
    var monthCards: String,

    @Column(name = "join_alliance_state", nullable = false)
    var joinAllianceState: Int,

    @Column(name = "alliance_occupy_info", nullable = false, length = 2000)
    var allianceOccupyInfo: String

) : OneToOnePlayerAsset {
    constructor() : this(
        0, 0, "", "", "", "", 0, 0, 0, "", 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, "", "", "", 0, 0, 0, 0, 0,
        "", 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, "", 0, 0, 0, 0, "",
        0, "", "", "", "",
        "", "", 0, "", 0, 0, 0,
        0, "", "", "", "",
        0, 0, 0, "", 0,
        0, 0, 0, 0,
        0, 0, 0, 0, "", 0, "", 0,
        0, 0, 0, 0, 0, "",
        0, 0, 0, 0,
        0, 0, "", "", 0, 0,
        0, 0, 0, 0, "", 0,
        "", 0, "", 0, "", 0, ""
    )
}