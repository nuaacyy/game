package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Index
import org.hibernate.annotations.Type
import javax.persistence.*

const val PLAYER_NAMED_QUERY = "findPlayerByWorldId"

@NamedQueries(
    NamedQuery(
        name = PLAYER_NAMED_QUERY,
        // language=HQL
        query = "from PlayerEntity where worldId = :worldId"
    )
)

@Entity
@Table(name = "players")
data class PlayerEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "db_id", nullable = false)
    var dbId: Long,

    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "user", nullable = false, length = 50)
    @Index(name = "HOME_PLAYERS_USER")
    var user: String,        // 账户名

    @Column(name = "area_no", nullable = false)
    var areaNo: Int,                 // 所属地图区,实际上游戏服的AreaNo已经没有意义了.这个字段值会在初始化的时候读配置来填充,之后会在迁服的时候来被修改

    @Column(name = "name", nullable = false, length = 50)
    var name: String,  // 玩家角色名

    @Column(name = "decree", nullable = false)
    var decree: Int,                // 政令 通过 GetDecree() 获取  行动力

    @Column(name = "decree_time", nullable = false)
    var decreeTime: Long,  // 政令结算时间

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

    @Column(name = "house_coin", nullable = false)
    var houseCoin: Long,  // 家园币数量

    @Column(name = "waijiao_count", nullable = false)
    var waijiaoCount: Int,                // 今天外交次数

    @Column(name = "last_waijiao_count", nullable = false)
    var lastWaijiaoCount: Long,  // 上次外加时间

    @Column(name = "jjc_rank", nullable = false)
    var jjcRank: Int,              // 玩家JJC名次

    @Column(name = "jjc_rank_gold", nullable = false)
    var jjcRankGold: Long,  // 竞技场可以领取的累计钻石

    @Column(name = "jjc_rank_JJcoin", nullable = false)
    var jjcRankJJCoin: Long,  // 竞技场可以领取的累计钻石

    @Column(name = "is_first_join_alliance", nullable = false)
    var isFirstJoinAlliance: Int,  // 玩家是否加入过帮派 0-否 1-是

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

    @Column(name = "auto_hunter", nullable = false)
    var autoHunter: Int,  // 自动猎杀

    @Column(name = "auto_use_energy", nullable = false)
    var autoUseEnergy: Int,  // 自动使用行动力

    @Column(name = "strength", nullable = false)
    var strength: Int,

    @Column(name = "last_strength_time", nullable = false)
    var lastStrengthTime: Long,

    @Type(type = "text")
    @Column(name = "join_activitys", nullable = false)
    var joinActivitys: String,   // 玩家参与过的活动信息

    @Column(name = "account_id", nullable = false)
    var accountId: Long,  // 关联的账号ID

    @Column(name = "login_time", nullable = false)
    var loginTime: Long, //`sql:"-"` // 登陆时间

    @Column(name = "wall_Fire_End_Time", nullable = false)
    var wallFireEndTime: Long, //`sql:"-"` // 自动灭火时间

    @Column(name = "max_Lv_Prison_Buff_End_Time", nullable = false)
    var maxLvPrisonBuffEndTime: Long, // 最高监禁等级领主buff结束时间

    @Column(name = "join_chat_rooms", nullable = false, length = 100)
    var joinChatRooms: String,  // 联盟简称

    @Column(name = "in_move_server", nullable = false)
    var inMoveServer: Int,

    @Column(name = "new_player_activity", nullable = false)
    var newPlayerActivity: Int,

    @Column(name = "join_alliance_reqs", nullable = false, length = 2000)
    var joinAllianceReqs: String,

    @Column(name = "alliance_occupy_info", nullable = false, length = 2000)
    var allianceOccupyInfo: String

) : WorldAsset {
    constructor() : this(
        0, 0,
        0, "", 0, "", 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, "", "", "", 0, 0, 0, 0, 0,
        "", 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, "", 0, 0, 0, 0,
        0, 0, 0,
        "", 0, "", 0, 0, 0,
        0,
        0, 0, 0, "", 0,
        0, 0, 0, 0,
        0, 0, 0, 0, 0,
        "", 0, 0, 0, 0, "", 0, 0, "", ""
    )

    override fun primaryKey() = dbId
}