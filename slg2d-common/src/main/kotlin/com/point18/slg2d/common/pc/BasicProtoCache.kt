package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.getRandInt
import com.point18.slg2d.common.commonfunc.getSpecifiedTime
import com.point18.slg2d.common.constg.*
import java.io.Serializable
import java.time.Duration
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.Arrays.asList

data class BasicResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<BasicProto>
) : Serializable

data class BasicProto(
    val id: String,
    val value: String
) : Serializable

class BasicProtoCache : ProtoCacheInit("basic.xml") {
    var basicMap: HashMap<String, String> = hashMapOf() //

    //攻击参数
    var attackPara: Float = 0f//
    //最大普攻减伤
    var maxAttackReduce: Float = 0f//
    //等级防御参数
    var lvDefencePara: Float = 0f//
    //防御参数
    var defencePara: Float = 0f//
    //普攻兵力临界1
    var attackTroopsPoint1: Int = 0 //
    //普攻兵力临界2
    var attackTroopsPoint2: Int = 0 //
    //普攻兵力参数1
    var attackTroopsPara1: Double = 0.0 //
    //普攻伤害参数1
    var attackHarmPara1: Double = 0.0//
    //普攻兵力参数2
    var attackTroopsPara2: Double = 0.0//
    //普攻伤害参数2
    var attackHarmPara2: Double = 0.0//
    //普攻兵力参数3
    var attackTroopsPara3: Double = 0.0//
    //普攻伤害参数3
    var attackHarmPara3: Double = 0.0//
    //攻击浮动比例
    var attackFloat: Int = 0 //
    //未破防物伤系数
    var noBrokenAttackPara: Float = 0f//
    //谋攻参数
    var magicPara: Double = 0.0//
    //最大谋攻减伤
    var maxMagicReduce: Double = 0.0//
    //等级谋略参数
    var lvMagicPara: Double = 0.0//
    //谋防参数
    var magicDefencePara: Double = 0.0//
    //谋攻兵力临界1
    var magicTroopsPoint1: Int = 0 //
    //谋攻兵力临界2
    var magicTroopsPoint2: Int = 0 //
    //谋攻兵力参数1
    var magicTroopsPara1: Double = 0.0//
    //谋攻伤害参数1
    var magicHarmPara1: Double = 0.0//
    //谋攻兵力参数2
    var magicTroopsPara2: Double = 0.0//
    //谋攻伤害参数2
    var magicHarmPara2: Double = 0.0//
    //谋攻兵力参数3
    var magicTroopsPara3: Double = 0.0//
    //谋攻伤害参数3
    var magicHarmPara3: Double = 0.0//
    //谋攻浮动比例
    var magicFloat: Int = 0 //
    //未破防物伤系数
    var noBrokenMagicPara: Double = 0.0//
    //暴击参数
    var critPara: Int = 0 //
    //闪避参数
    var dodgePara: Int = 0 //
    //普攻伤兵参数
    var attackWoundPara: Double = 0.0//
    //普攻死兵参数
    var attackDeadPara: Double = 0.0//
    //谋攻伤兵参数
    var magicWoundPara: Double = 0.0//
    //谋攻死兵参数
    var magicDeadPara: Double = 0.0//
    //回复参数
    var treatPara: Double = 0.0//
    //回复指数
    var treatIndex: Double = 0.0//
    //行军速度参数
    var speedPara: Int = 0 //

    //创建联盟时消耗的金币数
    var allianceCreateGoldCost: Int = 0 //
    //创建联盟时消耗的元宝(玉符)数
    var allianceCreateAcerCost: Int = 0 //
    //创建联盟时城主府等级要求
    var allianceCreateMainCityLv: Int = 0 //

    //退出联盟惩罚时间（分钟）
    var allianceQuitDuration: Long = 0//

    //联盟标记设置时间间隔(分)
    var allianceSetMarkDuration: Long = Duration.ZERO.toNanos() //

    //新手时期时间（时）
    var playerProtectDuration: Long = Duration.ZERO.toNanos() //
    //战报最多保存条数
    var fightReportSaveNum: Int = 0 //
    //战报最多保存时间
    var fightReportSaveTime: Int = 0 //
    //初始的任务
    var firstTask: List<Int> = LinkedList() //
    //第一个新手引导编号
    var guideFirstId: Int = 0 //
    //最后一个新手引导编号
    var guideEndId: Int = 0 //
    // 新手引导的N场战斗对应的landbaseId  map<新手引导的ID>landbaseId
    var guideFightConfig: HashMap<Int, Int> = hashMapOf() //
    //新手出生赠送武将id
    var guideGetHero: List<Int> = LinkedList() //

    //换头像免费次数
    var photoFreeCount: Int = 0 //

    //换头像消耗元宝
    var photoChangeCost: Int = 0 //

    //换头像免费倒计时（小时）
    var photoFreeDuration: Long = Duration.ZERO.toNanos() //

    //更换头像花费
    var changePhotoUse: List<ResVo> = LinkedList() //

    //可换头像数量
    var changePhotoNum: Int = 0 //

    //默认头像
    var defaultPhoto: Int = 0//

    //世界聊天免费次数
    var worldChatFreeNum: Int = 0 // todo jh 删除？

    //世界聊天消耗
    var worldChatCost: List<ResVo> = LinkedList() //

    //世界频道发言间隔
    var worldChatSpaceTime: Int = 0 //
    //同盟频道发言间隔
    var groupChatSpaceTime: Int = 0 //
    //国家频道发言间隔
    var nationChatSpaceTime: Int = 0 //
    //出征距离限制
    var goLimit: Int = 0 //

    //创建分组数量限制
    var createGroupNumLimit: Int = 0 //

    //联系人数量限制
    var friendsNumLimit: Int = 0 //

    //群内成员数量上限
    var groupMemberMaxNum: Int = 0 //

    //最近联系人列表数量
    var recentlyContactMaxNum: Int = 0 //

    //保存多少条聊天记录
    var saveChatListMaxNum: Int = 0 //

    //创建聊天组数量限制
    var createChatGroupNumLimit: Int = 0 //

    var playerNameLength: List<Int> = LinkedList() // [2]int //玩家名字长度范围（字母长度）
    var allianceNameLength: List<Int> = LinkedList() // [2]int //联盟名称长度范围（字母长度）
    var allianceShortNameLength: List<Int> = LinkedList() // [2]int //联盟缩写长度范围（字母长度）
    var allianceDescriptionLength: List<Int> = LinkedList() // [2]int //联盟公告字数长度范围（字母长度）
    var leagueSloganLength: List<Int> = LinkedList() // [2]int //联盟标语字数长度范围（字母长度）
    var allianceMarkTitleLength: List<Int> = LinkedList() // [2]int //联盟标记标题字数长度范围（字母长度）
    var allianceMarkDescriptionLength: List<Int> = LinkedList() // [2]int //联盟标记内容字数长度范围（字母长度）
    var allianceTopicTitleLength: List<Int> = LinkedList() // [2]int //联盟邮件主题标题长度（字母长度）
    var allianceTopicMessageLength: List<Int> = LinkedList() // [2]int //联盟邮件主题内容长度（字母长度）
    var playerDescriptionLength: List<Int> = LinkedList() // [2]int //玩家个人简介长度（字母长度）
    var chatMessageLength: List<Int> = LinkedList() // [2]int //聊天消息长度（字母长度）
    var allianceWaijiaoLength: List<Int> = LinkedList() // [2]int //联盟外交长度范围（字母长度）
    var allianceNickNameLength: List<Int> = LinkedList() // [2]int //联盟昵称长度范围（字母长度）
    var markTextLength: List<Int> = LinkedList() // [2]int //坐标分享长度范围（字母长度）

    var nickNameCostForProps: List<ResVo> = LinkedList()  // 修改联盟昵称道具消耗
    var changeNameCostForRes: List<ResVo> = LinkedList()  // 修改玩家名字资源消耗

    var allianceDefaultFlag: List<Int> = LinkedList() // [3]int //默认的联盟旗帜

    //联盟外交修改冷却时间（分钟）
    var allianceRelationShipSetDuration: Long = Duration.ZERO.toNanos() //

    //联盟旗帜修改消耗资源
    var allianceFlagCost: List<ResVo> = LinkedList() //
    // 首次加入联盟奖励
    var firstJoinCripsAward: List<ResVo> = LinkedList() //

    //世界保存多少条聊天记录
    var saveWorldChatListMaxNum: Int = 0 //
    //帮派保存多少条聊天记录
    var saveGroupChatListMaxNum: Int = 0 //
    //本州保存多少条聊天记录
    var saveNationChatListMaxNum: Int = 0 //
    //系统保存多少条聊天记录
    var savSystemChatListMaxNum: Int = 0 //

    //人民币兑换游戏币（角）--一角=多少元宝
    var rmbExchangeGold: Int = 0 //

    //邮件过期时间（单位:时）
    var mailExpireDuration: Long = Duration.ZERO.toNanos() //
    //播放战斗的新手战斗
    var goInBattleLand: LinkedList<Int> = LinkedList() //

    //联盟日志过期时间（单位:时）
    var allianceLogTimeLimit: Long = Duration.ZERO.toNanos() //

    // 服务器保护时间
    var serverOpenTime: Long = Duration.ZERO.toNanos() //

    //战报每页数量
    var fightReportAskNum: Int = 0 //

    //联盟标记数量上限
    var allianceMarkCountLimit: Int = 0 //

    // 爬塔初始次数
    var towerFirstTimes: Int = 0 //
    // 回复时间(秒数)
    var towerTimesRecovery: Int = 0 //
    // 玩家初始爬塔上限
    var towerTimesLimits: Int = 0 //

    // 建筑免费加速时间(秒)
    var upBuildingFree: Int = 0 //
    // 建筑免费加速时间
    var upBuildingFreeDuration: Long = Duration.ZERO.toNanos() //

    // 个人在单日外交留言板留言上限
    var numOfStatementsLimit: Int = 0 //
    // 联盟成员在所有联盟单日外交留言数量上限
    var allianceAiplomacyAllLimit: Int = 0 //

    // 外交留言板信息保存最大数量上限
    var allianceAiplomacyNumlimit: Int = 0 //
    // 外交留言板信息保存时间上限（天）
    var allianceAiplomacyTimelimit: Int = 0 //

    // 竞技场初始部队数量
    var initJjcForcesNum: Int = 0 //
    // 竞技场初始布阵数量
    var initJjcPlansNum: Int = 0 //

    // 布阵名称长度范围
    var jjcPlanNameLength: List<Int> = LinkedList() // [2]int

    // 初始竞技场的竞技场守护者（NPC）数量
    var jjcInitNpcCount: Int = 0 //

    // 竞技场挑战失败冷却时间（秒）
    var arenaLoseCD: Int = 0 //
    //清除挑战失败冷却时间价格
    var arenaCleanCD: Int = 0 //
    // 竞技场次数上限(超过了次数每日重置时不回复)
    var arenaTimesLimit: Int = 0 //
    //竞技场每日免费赠送次数
    var arenaFreeTimes: Int = 0 //
    // 竞技场购买一次获得的次数
    var arenaBuyTimes: Int = 0 //
    // 竞技场挑战成功获得的积分
    var jjcFightWinScore: Int = 0 //
    // 竞技场挑战失败获得的积分
    var jjcFightFailureScore: Int = 0 //
    // 每次竞技场战斗不论胜负都有一个竞技币奖励
    var arenaCoinReward: List<ResVo> = LinkedList() //

    // 竞技场商店刷新时间
//    var jjcRefreshShopTime: cron.Schedule//

    // 竞技场商店刷新价格
    var shopRefreshCost: Int = 0 //
    // 竞技场显示防守方配置 key minRank_maxRank  value 显示部队数量
    var arenaRankShow: HashMap<String, Int> = hashMapOf() //

    //竞技场星数条件
    var arenaSuccess: List<Int> = listOf()

    // 可放入素材卡数量
    var expCardNum: Int = 0 //

    // 武将最高等级
    var heroMaxLv: Int = 0 //

    // 装备强化无损等级
    var noLossLv: Int = 0 //
    // 装备强化有损比例(万分比)
    var lossScale: Int = 0 //

    // 玩家初始的推图进度
    var firstMission: Int = 0 //

    // 玩法—战报一页请求数量-竞技场，推图，爬塔
    var pVEReportAskNum: Int = 0 //
    // 玩法—战报最多保存条数-竞技场，推图，爬塔
    var pVEReportSaveNum: Int = 0 //
    // 玩法—战报最多保存时间（小时）-竞技场，推图，爬塔
    var pVEReportSaveTime: Int = 0 //

    // 初始帮派帮助次数
    var helpStarTimes: Int = 0 //

    // 初始造兵数
    var trainStarNum: Int = 0 //
    // 初始医疗所用量
    var cureStarNum: Int = 0 //

    // 通用战后死兵比例
    var commonDieRate: Int = 0 //
    // 通用战后伤兵比例
    var commonShangRate: Int = 0 //
    // 通用战后回兵比例
    var commonHuiRate: Int = 0 //

    //pvp攻击方战后死兵比例
    var pvpAtkDieRate: Int = 0 //
    //pvp攻击方战后伤兵比例
    var pvpAtkShangRate: Int = 0 //
    //pvp攻击方战后回兵比例
    var pvpAtkHuiRate: Int = 0 //

    //pvp防守方战后死兵比例
    var pvpDefDieRate: Int = 0 //
    //pvp防守方战后伤兵比例
    var pvpDefShangRate: Int = 0 //
    //pvp防守方战后回兵比例
    var pvpDefHuiRate: Int = 0 //

    // 特殊PVE战后死兵比例
    var pveDieRate: Int = 0 //
    // 特殊PVE战后伤兵比例
    var pveShangRate: Int = 0 //
    // 特殊PVE战后回兵比例
    var pveHuiRate: Int = 0 //

    // 名字前缀
    var namePrefix: String = ""//

    // 初始内政槽位
    var interiorHeroFirstNum: Int = 0 //

    var deputyLeadeNub: Int = 0  // 副盟主初始数量
    var memberNub: Int = 0  // 成员初始数量
    var diplomatNub: Int = 0  // 外交官初始数量
    var commanderNub: Int = 0  // 指挥官初始数量
    var headNub: Int = 0  // 团长初始数量
    var eliteGroupNub: Int = 0  // 每个精英团成员可任命初始数量
    var diplomaticRelationsNub: Int = 0  // 外交关系初始设立数量
    var technologyDonateTimes: Int = 0  // 联盟普通捐献回复上限次数
    var technologyRecoverTime: Int = 0  // 联盟捐献每次回复时间
    var batteryRange: Int = 0  // 炮台攻击范围
    var magicArrayRange: Int = 0  // 魔法阵影响范围
    var allianceFlagRange: Int = 0  // 联盟旗帜影响范围

    var allianceFlagBuildTime: Int = 0  // 帮忙建造帮派战旗每次时间，单位秒
    var allianceFlagBuildRatio: Int = 0  // 帮忙建造帮派战旗攻城值与加速时间（秒）的比值

    var militaryTalentResetCost: List<ResVo> = LinkedList() //军事天赋重置消耗道具id
    var economyTalentResetCost: List<ResVo> = LinkedList() //经济天赋重置消耗道具id
    var monsterTalentResetCost: List<ResVo> = LinkedList() //魔物天赋重置消耗道具id
    var allTalentResetCost: List<ResVo> = LinkedList() //重置所有天赋道具id

    var lmmcTalentReset: List<ResVo> = LinkedList()  //联盟名称更改消耗（奖励配置）
    var lmjcTalentReset: List<ResVo> = LinkedList()  //联盟简称更改消耗（奖励配置）

    var alliancePositionLimit: Int = 0  // 玩家可担任联盟职位的数量上限
    var cripsDonateDateLimit: Int = 0  // 帮派捐赠次数

    var systemDemiseTime: Int = 0  // 帮主自动换人时间(小时)

    var allArea: Int = 0  // 大地图边长

    var resourceArea: Int = 0  // 撒资源点小格子边长
    var allResGridNum: Int = 0  // 所有资源大块数量

    var monsterArea: Int = 0  // 撒魔物小格子边长
    var allMonsterGridNum: Int = 0 // 所有怪物大块数量

    var relicArea: Int = 0 // 撒遗迹小格子边长
    var allRelicGridNum: Int = 0 // 所有遗迹大块数量

    var mapMonster: Int = 0 //                             // 魔物刷新方式
    var mapRelic: Int = 0 //                             // 遗迹刷新方式
    var resProportionMap: HashMap<Int, ResProportionMapHelpVo> = hashMapOf()// 资源点刷新物品权值
    var resProportionList: List<Int> = LinkedList() //                           // 资源点刷新物品列表 用于随机
    var timeZone: Int = 0 //                             // 服务器刷新大地图的时间整数点

    var mapSelection: String = ""//

    var energyLimit: Int = 0  //行动力上限
    var initialEnergy: Int = 0  //初始行动力
    var energyRecovery: Int = 0  //行动力恢复速度

    var bornFrist: Int = 0  // 初始出生数
    var bornUp: Int = 0  // 出生数量增量
    var peopleNum: Int = 0  // 单服导入上限

    var mapMonsterInitialTimesYear: Int = 0  // 魔物刷新对应基准时间<年>
    var mapMonsterInitialTimesMonth: Int = 0  // 魔物刷新对应基准时间<月>
    var mapMonsterInitialTimesDay: Int = 0  // 魔物刷新对应基准时间<日>
    var allianceMinimumCombatPower: Long = 0  // 联盟初始可直接加入的战斗力变化

    var baseHeroSpeed: Double = 0.0 //英雄速度
    var baseSpySpeed: Double = 0.0 //侦查速度
    var baseTransportSpeed: Double = 0.0 //运输速度

    var heroSpeedPara: Float = 0f //英雄战移动速度参数

    var robTransportRate: Int = 0  //掠夺运输比例

    var impeachmentTime: HashMap<Int, Int> = hashMapOf() //
    var impeachmentConsumptionCost: List<ResVo> = LinkedList() //

    var tradShipRefreshTime: Int = 0  // 商船刷新倒计时	21600
    var allianceGiftLimit: Int = 0  // 礼物上限

    var tradShipOpenCondition: List<Int> = LinkedList() //

    var caveTime: List<Int> = LinkedList()  //藏兵时间(小时)
    var massTime: List<Int> = LinkedList()  //集结时间(分钟)
    var playerMassedLimit: Int = 0 //单个玩家被集结的联盟上限

    var protectUnMassCityLv: Int = 0  //单人玩家被集结保护主城等级
    var massRelicMinMember: Int = 0  //攻击遗迹最低需要加入人数

    var monsterBossShowNumber: Int = 0  //魔物排行榜显示人数
    var monsterBossNumber: Int = 0  //魔物排行榜总人数
    var newMonsterBossTime: Int = 0  //新服世界boss开启时间
    var preMonsterBossTime: Int = 0  //世界boss预告时间

    var soliderAttackPara: Double = 0.0 //士兵攻击参数
    var soliderDefendPara: Double = 0.0 //士兵防御参数
    var minAttDefDiff: Double = 0.0 //最小攻防差
    var soliderHarmPara: Double = 0.0 //士兵伤害参数
    var maxDefendCityTroops: Int = 0 //     //最大守城兵力

    var warCrazy: Int = 0  // 战争狂热唯一ID
    var wonderBuff: Int = 0  // 战神庇护唯一ID
    var numberOfTitlesAvailable: Int = 0 //

    var mapRounds: Int = 0  // 每次撒点轮数
    var areaMaxDensity: Int = 0  // 区域最大密度

    var rewardMaxNum: Int = 0  // 奖励背包上限

    var strongHoldRecoveryTime: Int = 0  //据点恢复时间
    var strongHoldAtkCount: Int = 0  //据点进攻次数

    var pvpAttackerDieRate: Int = 0  //PVP进攻方死兵率
    var relicReviveRate: Int = 0  //进攻遗迹回兵比例
    var strongholdReviveRate: Int = 0  //进攻据点回兵比例

    var getVipRewardRefreshHour: Int = 0  //vip奖励刷新时间（小时）
    var getVipRewardRefreshMinute: Int = 0  //vip奖励刷新时间（分钟）
    var vipEveryDayGet: Int = 0 // 每天获得vip经验基数
    var vipEveryDayMost: Int = 0 // 每天获得vip经验最大值

    var prisonNum: Int = 0      // 监狱人数上限	30
    var fastReborn: List<ResVo> = LinkedList()  // 立即复活道具id 	14:71501_1
    var rebornTime: Int = 0      // 复活时间（秒）	360
    var fastKill: List<ResVo> = LinkedList()  // 立即处决道具id 	14:70201_1
    var fastDead: Int = 0       // 毒蘑菇加快处死道具ID
    var killTime: Int = 0       // 处决时间
    var killWaitTimeMin: Int = 0   // 最小的等待处决时间(单位秒)

    var openSkinStrengthen: Int = 0  //城堡皮肤开启等级

    var ransomMin: Int = 0  // 最小赎金	1000
    var ransomMax: Int = 0  // 最大赎金	999999999
    var commissionMin: Int = 0  // 最小佣金	1000
    var commissionMax: Int = 0  // 最大佣金	999999999
    var ransomRate: Int = 0  // 赎金税率（万分比）	3000
    var commissionRate: Int = 0  // 佣金税率（万分比）	3000

    var destoryBuildingMinTime: Int = 0 //      //建筑拆除最低时间
    var destoryBuildingProps: List<ResVo> = LinkedList()  //建筑拆除道具消耗

    var addEquipmentPositionMin: Int = 0  // 君主装备背包下限
    var addEquipmentPositionMax: Int = 0  // 君主装备背包上限

    var interiorTaskRefreshTime: Int = 0 //      // 内政任务刷新时间
    var interiorTaskBaseNum: Int = 0 //      // 内政任务初始数量
    var interiorTaskPointMaxNum: Int = 0 //      // 内政任务点最大数量
    var interiorTaskBaseDrops: List<ResVo> = LinkedList()  //内政任务道具消耗

    var allianceTreasureQuestTime: Int = 0 //      // 联盟宝藏系统刷新时间，单位，小时
    var allianceTreasureCloseTime: Int = 0 //      // 加入联盟后使用联盟宝藏时间(小时)
    var allianceTreasureCostDrops: List<ResVo> = LinkedList()  //宝藏刷新道具
    var allianceTreRefreshBaseNum: Int = 0 //      // 初始联盟宝藏个数

    var resWeight: HashMap<Int, Double> = hashMapOf() //资源所占负重

    var fightAfterSevice: Int = 0  //开服后开启争夺时间(小时)
    var fightInterval: Int = 0  //开启争夺间隔(小时)

    var redBagPlayerMinNum: Int = 0  // 红包数量的最小个数
    var redBagPlayerMaxNum: Int = 0  // 红包数量的最大个数
    var redBagListCost: Int = 0  // 红包发送钻石最少数量

    var allianceCompetitionConditions: Int = 0  // 表示联盟人数达到这个值允许参与
    var allianceCompetitionOnesConditions: Int = 0  // 这个字段表示个人参与的主堡等级
    var numberOfInitialTasks: Int = 0  // 联盟总动员任务刷新数量
    var allianceCompetitionAwardTime: Int = 0  // 联盟总动员活动领奖时间 单位秒
    var allianceCompetitionContinuedTime: Int = 0  // 联盟总动员活动持续时间 单位秒
    var allianceCompetitionInitialTime: Int = 0  // 联盟总动员任务刷新时间，单位秒

    var allianceCompetitionOpenTime: List<Int> = LinkedList()  // 联盟总动员活动开启时间 日_时_分_秒
    var allianceCompetitionFirstLevel: Int = 0  // 联盟初始段位

    var allianceCompetitionQuestValue: List<ResVo> = LinkedList()  // 购买联盟总动员领取任务机会的消耗

    var baseWoodYield: Int = 0  //木材基础产量（小时）
    var baseIronYield: Int = 0  //水晶基础产量（小时）
    var baseStoneYield: Int = 0  //石头基础产量（小时）
    var baseFoodYield: Int = 0  //粮食基础产量（小时）
    var baseCoinYield: Int = 0  //金币基础产量（小时）

    var baseWoodLimit: Int = 0  //木材基础产量上限
    var baseIronLimit: Int = 0  //水晶基础产量上限
    var baseStoneLimit: Int = 0  //石头基础产量上限
    var baseFoodLimit: Int = 0  //粮食基础产量上限
    var baseCoinLimit: Int = 0  //金币基础产量上限
    var resStorageLimit: Long = 0
    var pardom: Int = 0 //      //天下大赦次数
    var noticeCd: Int = 0 //      //国王公告cd时间
    var noticePrice: List<ResVo> = LinkedList()  //国王公告发送给联盟价格
    var arenaRewardLimit: Long = 0L  // 竞技场累计上限
    var redBagOverTime: Int = 0 //
    var heroMoraleLimit: Int = 0  // 英雄士气上限
    var killSoliderAddMorale: Int = 0  //击杀士兵增加的士气
    var killHeroAddMorale: Int = 0  //击杀英雄增加的士气
    var redBagMostCost: Int = 0  // 红包金额最大
    var deleteSystemMessages: Int = 0   // 已经处理过的好友请求消息的过期时间
    var allianceBigTreRefreshExp: Int = 0 // 联盟大礼物所需经验值
    var helpRewordLimit: Int = 0 // 联盟帮助每天获得上限值
    var helpTimesReword: Int = 0 // 联盟帮助每次获得值
    var maxInstancePower: Int = 0 // 推图体力上限
    var instancePowerRecover: Int = 0 // 推图体力每点回复时间-秒
    var firstInstancePower: Int = 0 // 初始体力
    var firstInstance: Int = 0 // 推图初始关卡
    var monsterRankNum: Int = 0 //魔物排行榜个人或联盟排名数量上限
    var moveMainCityConsumeMap = mutableMapOf<Int, Int>()
    var tradShipSurpriseMinNum = 0
    var tradShipSurpriseMaxNum = 0
    var reconConsume: List<ResVo> = LinkedList()
    var removeEquipCard: List<ResVo> = LinkedList()
    var favoritesMarkTop: Int = 0
    var favoritesMarkStart: Int = 0
    var arenaShopNum: Int = 0
    var arenaRewardTime: LinkedList<Int> = LinkedList()
    var maxRankingNumeber: Int = 0
    var cityMisfireProps: List<ResVo> = LinkedList()
    var cityFireTime: Int = 0

    var emailUpDateNumb: Int = 0    //邮件分页条目
    var fakeLordLevel: Int = 0
    var fakeLordHeroProtoId: Int = 0
    var fakeLordDisappear: Int = 0
    var lordBackSpeed: Int = 0
    var fakeLordCost: List<ResVo> = LinkedList()
    var houseHeroLimit: Int = 0    //后宅单层英雄数量限制
    var citySmokeTime: Int = 0 // 冒烟时间

    var lowDropFreeNum = 0                         // 免费普通招募免费次数
    var lowDropFreeCd = 0                          // 普通招募CD时间
    var highDropFreeCd = 0                         // 高级招募CD时间
    var shardConvertMap = mutableMapOf<Int, Int>() // 英雄星级和碎片转换 碎片数是key，星级value
    var readingWorldChatListMaxNum = 0
    var hornChatSpaceTime = 0                      // 喇叭cd间隔 秒
    var hornCost: List<ResVo> = LinkedList()   // 喇叭道具消耗
    var wonderWarOpen: List<Int> = LinkedList()
    var wonderWarClose: List<Int> = LinkedList()
    var wonderProtect: List<List<Int>> = ArrayList()
    var activityHospitalNum = 0
    var wonderFireTime = 0
    var wonderSmokeTime = 0
    var battleTime = 0
    var monsterBattleTime = 0
    var groupNameLeng: List<Int> = LinkedList()
    val leftPosition = hashMapOf<Int, Pair<Int, Int>>()
    val rightPosition = hashMapOf<Int, Pair<Int, Int>>()
    var mapGridX = 0
    var mapGridY = 0
    var keepChatRecordTime = 0L
    var groupNumberLimit = 0
    var monsterActivityOpen: List<Int> = LinkedList()
    var monsterActivityRound = 0
    var monsterActivityAdvance = 0
    var monsterActivityContinue = 0
    var monsterActivityMail = 0
    var specialActivityProtect = 0
    var palaceKill = 0
    var palacePoolBase: Long = 0
    var palaceIncrementTime = 0
    var palaceTimeStr: String = ""
    var palaceNormalPool = 0
    var palaceSeniorPool = 0
    var palaceFree = 0
    var snowBelongTime = 0
    var wonderBlackBelongTime = 0
    var snowSpeed = 0
    var moveLvLimit = 0
    var defaultSkinId = 0
    var hpRecoverHeroSkillId = 0
    var moraleRecoverHeroSkillId = 0
    var heroBattleDefaultMoraleWithEnemy = 0.0
    var guideGetItemMap: List<ResVo> = LinkedList()
    var markNameLength: List<Int> = LinkedList()
    var monsterHeroMax = 0
    var posTypeXSplit: List<Pair<Int, Int>> = LinkedList()
    var posTypeYSplit: List<Pair<Int, Int>> = LinkedList()
    var ignoreTownLevel = 0
    var disturbMin: List<List<Int>> = ArrayList()
    var mapMonsterRefreshTime = 0 //普通魔物刷新时间间隔(单位:秒)
    var darkLairSpeed = 0 //加入集结、集结被遣返、集结结束回城的加速速度
    var monthCardDays = 0 //月卡持续天数
    var darkDelete: List<ResVo> = LinkedList()
    var transferResourcesLimitMap: Map<Int, Map<Int, Long>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<BasicResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as BasicResult

        for (vo in readXmlResult.l) {
            val tmp = this.basicMap[vo.id]
            if (tmp != null) {
                throw RuntimeException("config_game/basic.xml 配置有重复")
            }
            this.basicMap[vo.id] = vo.value
        }

        val shoucangText = parseInt(this, "shoucangText")
        val shoucangTexts = listOf(1, shoucangText)
        this.markNameLength = shoucangTexts

        // 赌场
        this.palaceFree = parseInt(this, "palaceFree")
        this.palaceSeniorPool = parseInt(this, "palaceSeniorPool")
        this.palaceNormalPool = parseInt(this, "palaceNormalPool")

        // 赌场刷新时间
        val refreshTime = parse2Int(this, "palaceTime", ";")
        if (refreshTime.size != 2) {
            throw RuntimeException("basic中的palaceTime字段解析出错2")
        }
        if (refreshTime[0] > 24 || refreshTime[1] > 59) {
            throw RuntimeException("basic中的palaceTime字段解析出错2")
        }
        var refreshTimeString = " "
        for (value in refreshTime) {
            refreshTimeString = "$refreshTimeString$value:"
        }
        this.palaceTimeStr = refreshTimeString + "0"

        this.palaceIncrementTime = parseInt(this, "palaceIncrementTime")
        this.palacePoolBase = parseLong(this, "palacePoolBase")
        this.palaceKill = parseInt(this, "palaceKill")

        val groupNameLength =
            basicMap["groupNameLength"] ?: throw RuntimeException("basic中的groupNameLength字段解析出错1")
        val groupNameLengthSpilt = groupNameLength.split(":")
        if (groupNameLengthSpilt.size != 2) {
            throw RuntimeException("basic中的groupNameLength字段解析出错2")
        }
        this.groupNameLeng = LinkedList(asList(groupNameLengthSpilt[0].toInt(), groupNameLengthSpilt[1].toInt()))

        val heroStarNum = basicMap["heroStarNum"] ?: throw RuntimeException("basic中的heroStarNum字段解析出错1")
        val heroStarNumSpilt = heroStarNum.split("|")
        if (heroStarNumSpilt.size != 5) {
            throw RuntimeException("basic中的heroStarNum字段解析出错2")
        }
        for (each in heroStarNumSpilt) {
            val eachSpilt = each.split(";")
            if (eachSpilt.size != 2) {
                throw RuntimeException("basic中的heroStarNum字段解析出错2")
            }
            val shardNum = eachSpilt[0].toIntOrNull() ?: throw RuntimeException("basic中的heroStarNum字段解析出错2")
            val star = eachSpilt[1].toIntOrNull() ?: throw RuntimeException("basic中的heroStarNum字段解析出错2")
            this.shardConvertMap[shardNum] = star
        }

        val hornCostStr = basicMap["hornCost"]
        if (hornCostStr != null) {
            val hornCost = resStringToResVoList(hornCostStr)
            if (hornCost == null) {
                throw RuntimeException("basic中的reconConsume字段解析出错")
            } else {
                this.hornCost = hornCost
            }
        }

        val guideGetItemStr = basicMap["guideGetItem"]
        if (guideGetItemStr != null) {
            val guideGetItem = resStringToResVoList(guideGetItemStr)
            if (guideGetItem == null) {
                throw RuntimeException("basic中的guideGetItem字段解析出错")
            } else {
                this.guideGetItemMap = guideGetItem
            }
        }

        this.groupNumberLimit = parseInt(this, "groupNumberLimit")
        this.hornChatSpaceTime = parseInt(this, "hornChatSpaceTime")
        this.readingWorldChatListMaxNum = parseInt(this, "readingWorldChatListMaxNum")
        this.lowDropFreeNum = parseInt(this, "lowDropFreeNum")
        this.lowDropFreeCd = parseInt(this, "lowDropFreeCd")
        this.highDropFreeCd = parseInt(this, "highDropFreeCd")

        this.keepChatRecordTime = parseLong(this, "deleteFriendNews")
        if (this.keepChatRecordTime > MaxKeepChatRecordTime * 24 * 60) {
            throw RuntimeException("basic中的deleteFriendNews字段太大了,重新配")
        }

        val fakeLordCostStr = basicMap["fakeLordCost"]
        if (fakeLordCostStr != null) {
            val fakeLordCost = resStringToResVoList(fakeLordCostStr)
            if (fakeLordCost == null) {
                throw RuntimeException("basic中的reconConsume字段解析出错")
            } else {
                this.fakeLordCost = fakeLordCost
            }
        }

        this.lordBackSpeed = parseInt(this, "lordBackSpeed")

        this.lordBackSpeed = parseInt(this, "lordBackSpeed")

        this.fakeLordDisappear = parseInt(this, "fakeLordDisappear")

        this.cityFireTime = parseInt(this, "cityFireTime")

        this.citySmokeTime = parseInt(this, "citySmokeTime")

        this.fakeLordLevel = parseInt(this, "fakeLordLevel")

        this.fakeLordHeroProtoId = parseInt(this, "fakeLordHero")

        val cityMisfirePropsTmpS =
            this.basicMap["cityMisfireProps"]
                ?: throw RuntimeException("basic中的tmpBasicMap  cityMisfireProps  字段解析出错")
        val cityMisfirePropsTmp = resStringToResVoList(cityMisfirePropsTmpS)
        if (cityMisfirePropsTmp == null || cityMisfirePropsTmp.isEmpty()) {
            throw RuntimeException("basic中的tmpBasicMap  cityMisfireProps  字段解析出错")
        }
        this.cityMisfireProps = cityMisfirePropsTmp

        this.houseHeroLimit = parseInt(this, "houseHeroLimit")

        // 排行榜最大排行数量
        this.maxRankingNumeber = parseInt(this, "maxRankingNumeber")

        // 竞技场刷新时间
        val arenaRewardTime = this.basicMap["arenaRewardTime"]
        if (arenaRewardTime == null) {
            throw RuntimeException("basic 解析时错误，map不存在arenaRewardTime该单元")
        }
        val arenaRewardTimeSplitFirst = arenaRewardTime.split("%")
        if (arenaRewardTimeSplitFirst.size != 3) {
            throw RuntimeException("basic 解析时错误，arenaRewardTime该单元err1")
        }
        for (eachTime in arenaRewardTimeSplitFirst) {
            val arenaRewardTimeSplitSecond = eachTime.split(":")
            if (arenaRewardTimeSplitSecond.size != 2) {
                throw RuntimeException("basic 解析时错误，arenaRewardTime该单元err2")
            }
            val tmp1 = arenaRewardTimeSplitSecond[0].toIntOrNull()
            val tmp2 = arenaRewardTimeSplitSecond[1].toIntOrNull()
            if (tmp1 == null || tmp2 == null || tmp1 < 0 || tmp2 < 0 || tmp1 > 24 || tmp2 > 60) {
                throw RuntimeException("basic 解析时错误，arenaRewardTime该单元err3")
            } else {
                this.arenaRewardTime.add(tmp1)
                this.arenaRewardTime.add(tmp2)
            }
        }

        // 商店槽位数
        this.arenaShopNum = parseInt(this, "arenaShopNum")

        // 攻击参数
        this.attackPara = parseFloat32(this, "attackPara")

// 最大普攻减伤
        this.maxAttackReduce = parseFloat32(this, "maxAttackReduce")

// 等级防御参数
        this.lvDefencePara = parseFloat32(this, "lvDefencePara")

// 防御参数
        this.defencePara = parseFloat32(this, "defencePara")

// 普攻兵力临界1
        this.attackTroopsPoint1 = parseInt(this, "attackTroopsPoint1")

// 普攻兵力临界2
        this.attackTroopsPoint2 = parseInt(this, "attackTroopsPoint2")

// 普攻兵力参数1
        this.attackTroopsPara1 = parseFloat64(this, "attackTroopsPara1")

// 普攻伤害参数1
        this.attackHarmPara1 = parseFloat64(this, "attackHarmPara1")

// 普攻兵力参数2
        this.attackTroopsPara2 = parseFloat64(this, "attackTroopsPara2")

// 普攻伤害参数2
        this.attackHarmPara2 = parseFloat64(this, "attackHarmPara2")

// 普攻兵力参数3
        this.attackTroopsPara3 = parseFloat64(this, "attackTroopsPara3")

// 普攻伤害参数3
        this.attackHarmPara3 = parseFloat64(this, "attackHarmPara3")

// 攻击浮动比例
        this.attackFloat = parseInt(this, "attackFloat")

//未破防物伤系数
        this.noBrokenAttackPara = parseFloat32(this, "noBrokenAttackPara")

// 谋攻参数
        this.magicPara = parseFloat64(this, "magicPara")

// 最大谋攻减伤
        this.maxMagicReduce = parseFloat64(this, "maxMagicReduce")

// 等级谋略参数
        this.lvMagicPara = parseFloat64(this, "lvMagicPara")

// 谋防参数
        this.magicDefencePara = parseFloat64(this, "magicDefencePara")

// 谋攻兵力临界1
        this.magicTroopsPoint1 = parseInt(this, "magicTroopsPoint1")

// 谋攻兵力临界2
        this.magicTroopsPoint2 = parseInt(this, "magicTroopsPoint2")

// 谋攻兵力参数1
        this.magicTroopsPara1 = parseFloat64(this, "magicTroopsPara1")

// 谋攻伤害参数1
        this.magicHarmPara1 = parseFloat64(this, "magicHarmPara1")

// 谋攻兵力参数2
        this.magicTroopsPara2 = parseFloat64(this, "magicTroopsPara2")

// 谋攻伤害参数2
        this.magicHarmPara2 = parseFloat64(this, "magicHarmPara2")

// 谋攻兵力参数3
        this.magicTroopsPara3 = parseFloat64(this, "magicTroopsPara3")

// 谋攻伤害参数3
        this.magicHarmPara3 = parseFloat64(this, "magicHarmPara3")

// 谋攻浮动比例
        this.magicFloat = parseInt(this, "magicFloat")

//未破防法伤系数
        this.noBrokenMagicPara = parseFloat64(this, "noBrokenMagicPara")

//暴击参数
        this.critPara = parseInt(this, "critPara")

//闪避参数
        this.dodgePara = parseInt(this, "dodgePara")

// 普攻伤兵参数
        this.attackWoundPara = parseFloat64(this, "attackWoundPara")

// 普攻死兵参数
        this.attackDeadPara = parseFloat64(this, "attackDeadPara")

// 谋攻伤兵参数
        this.magicWoundPara = parseFloat64(this, "magicWoundPara")

// 谋攻死兵参数
        this.magicDeadPara = parseFloat64(this, "magicDeadPara")

// 回复参数
        this.treatPara = parseFloat64(this, "treatPara")

// 回复指数
        this.treatIndex = parseFloat64(this, "treatIndex")

        // 行军速度参数
        this.speedPara = parseInt(this, "speedPara")

        this.fightReportSaveNum = parseInt(this, "fightReportSaveNum")

        this.fightReportSaveTime = parseInt(this, "fightReportSaveTime")

        this.playerProtectDuration = parseDuration(this.basicMap["newHeroHelpTime"], "h")

        this.goLimit = parseInt(this, "goLimit")

        val guideGetHero: LinkedList<Int> = LinkedList()
        val guideGetHeroReward = stringsSplit(this.basicMap["guideGetHero"], ";")
        for (hId in guideGetHeroReward) {
            val hpId = strconvAtoi(hId) ?: throw RuntimeException("val hpId= strconvAtoi(hId)转化时，得到结果是空")
            guideGetHero.add(hpId)
        }
        this.guideGetHero = guideGetHero

        this.guideFirstId = parseInt(this, "guideFirstId")

        this.guideEndId = parseInt(this, "guideEndId")

        val initTask = this.basicMap["firstTask"]
        val firstTask: LinkedList<Int> = LinkedList()
        for (t in stringsSplit(initTask, ":")) {
            val task =
                strconvAtoi(t)
                    ?: throw RuntimeException("basic.xml :: firstTask :: ${this.basicMap["firstTask"]}错误: ")
            if (task != 0) {
                firstTask.add(task)
            }
        }
        this.firstTask = firstTask

        // 联盟创建条件
        this.allianceCreateGoldCost = parseInt(this, "foundCripsCostSilver")

        this.allianceCreateAcerCost = parseInt(this, "foundCripsCostGold")

        this.allianceCreateMainCityLv = parseInt(this, "foundCripsNeedLevel")

        // 联盟惩罚时间
        this.allianceQuitDuration = parseDuration(this.basicMap["cripsPunishment"], "m")

        this.allianceSetMarkDuration = parseDuration(this.basicMap["markTimeInterval"], "m")

        // 修改联盟旗帜消耗资源
        val tmpFlagCost = this.basicMap["flagCost"] ?: throw RuntimeException("修改联盟旗帜消耗资源失败")
        val flagCostVo = resStringToResVoList(tmpFlagCost)
        if (flagCostVo == null || flagCostVo.isEmpty()) {
            throw RuntimeException("basic中的flagCost字段解析出错")
        } else {
            this.allianceFlagCost = flagCostVo
        }

        // 修改联盟昵称消耗
        val nickNameCosts = this.basicMap["nicNameCost"]
        if (nickNameCosts != null) {
            val nickNameCostsVo = resStringToResVoList(nickNameCosts)
            if (nickNameCostsVo == null || nickNameCostsVo.isEmpty()) {
                throw RuntimeException("basic中的nicNameCost字段解析出错")
            } else {
                this.nickNameCostForProps = nickNameCostsVo
            }
        }

        // 修改玩家名字消耗
        val changeNameCosts = this.basicMap["nameCost"]
        if (changeNameCosts != null) {
            val changeNameCostsVo = resStringToResVoList(changeNameCosts)
            if (changeNameCostsVo == null || changeNameCostsVo.isEmpty()) {
                throw RuntimeException("basic中的nameCost字段解析出错")
            } else {
                this.changeNameCostForRes = changeNameCostsVo
            }
        }

        val tmpBasicMapfirstJoinCripsAward = this.basicMap["firstJoinCripsAward"]
        if (tmpBasicMapfirstJoinCripsAward != null) {
            val firstJoinCripsAwardVo = resStringToResVoList(tmpBasicMapfirstJoinCripsAward)
            if (firstJoinCripsAwardVo == null || firstJoinCripsAwardVo.isEmpty()) {
                throw RuntimeException("basic中的firstJoinCripsAward字段解析出错")
            } else {
                this.firstJoinCripsAward = firstJoinCripsAwardVo
            }
        }

        val guideFightConfig = this.basicMap["guideFightConfig"]
        this.guideFightConfig = hashMapOf()
        val guideFights = stringsSplit(guideFightConfig, "%")
        for (gfs in guideFights) {
            val v = stringsSplit(gfs, ":")
            if (v.size != 2) {
                throw RuntimeException("basic中的guideFightConfig配置错了")
            }

            val guideId = strconvAtoi(v[0])
                ?: throw RuntimeException("basic.xml :: guideFightConfig ::${this.basicMap["guideFightConfig"]}错误 ")

            val landbaseId = strconvAtoi(v[1])
                ?: throw RuntimeException("basic.xml :: guideFightConfig ::${this.basicMap["guideFightConfig"]}错误 ")

            this.guideFightConfig[guideId] = landbaseId
        }

        // 换头像免费次数
        this.photoFreeCount = parseInt(this, "changeHeadFreeNum")

        // 换头像消耗元宝
        this.photoChangeCost = parseInt(this, "changeHeadCost")

        // 换头像免费倒计时（小时）
        this.photoFreeDuration = parseDuration(this.basicMap["changeHeadFreeTime"], "h")

        val photoChangeUse = this.basicMap["changeHeadUse"]
        if (photoChangeUse != null) {
            val photoChangeCost = resStringToResVoList(photoChangeUse)
            if (photoChangeCost == null || photoChangeCost.isEmpty()) {
                throw RuntimeException("basic中的changeHeadUse配置错了")
            } else {
                this.changePhotoUse = photoChangeCost
            }
        }

        this.changePhotoNum = parseInt(this, "changeHeadNum")


        this.defaultPhoto = parseInt(this, "changeHeadFrist")

        this.worldChatFreeNum = parseInt(this, "worldChatFreeNum")

        val worldChatConsume = this.basicMap["worldChatCost"]
        if (worldChatConsume != null) {
            val worldChatCost = resStringToResVoList(worldChatConsume)
            if (worldChatCost == null || worldChatCost.isEmpty()) {
                throw RuntimeException("basic中的worldChatCost配置错了")
            } else {
                this.worldChatCost = worldChatCost
            }
        }

        this.worldChatSpaceTime = parseInt(this, "worldChatSpaceTime")

        this.groupChatSpaceTime = parseInt(this, "groupChatSpaceTime")

        this.nationChatSpaceTime = parseInt(this, "nationChatSpaceTime")

        this.createGroupNumLimit = parseInt(this, "createGroupNumLimit")

        this.friendsNumLimit = parseInt(this, "friendsNumLimit")

        this.groupMemberMaxNum = parseInt(this, "groupMemberMaxNum")
        if (this.groupMemberMaxNum > MaxGroupChatMembersNum) {
            throw RuntimeException("basic中的groupMemberMaxNum配置太大,改小一点")
        }

        this.recentlyContactMaxNum = parseInt(this, "recentlyContactMaxNum")

        this.saveChatListMaxNum = parseInt(this, "saveChatMsgNum")
        if (this.saveChatListMaxNum > MaxKeepChatRecordNum) {
            throw RuntimeException("basic中的saveChatMsgNum配置太大,改小一点")
        }

        this.createChatGroupNumLimit = parseInt(this, "createChatGroupNumLimit")
        if (this.createChatGroupNumLimit > MaxGroupChatMembersNum) {
            throw RuntimeException("basic中的createChatGroupNumLimit配置太大,改小一点")
        }

        val allianceShopLimit = stringsSplit(this.basicMap["cripsShopOpenLimet"], ":")
        if (allianceShopLimit.size != 2) {
            throw RuntimeException("basic中的cripsShopOpenLimet配置错了")
        }

        // 玩家名字长度范围
        this.playerNameLength = parse2Int(this, "playerNameLength")

        // 联盟名称长度范围
        this.allianceNameLength = parse2Int(this, "cripsNameLength")

        // 联盟缩写长度范围
        this.allianceShortNameLength = parse2Int(this, "cripSabbreviationLength")

        // 联盟公告长度范围
        this.allianceDescriptionLength = parse2Int(this, "cripsNoticeLength")

        // 联盟标语长度范围
        this.leagueSloganLength = parse2Int(this, "leagueSloganLength")

        // 联盟标记标题长度范围
        this.allianceMarkTitleLength = parse2Int(this, "cripsTabTitleLength")

        // 联盟标记内容长度范围
        this.allianceMarkDescriptionLength = parse2Int(this, "cripsTabContentLength")

        // 联盟邮件主题标题长度（字母长度）
        this.allianceTopicTitleLength = parse2Int(this, "mailTitleLength")

        // 联盟邮件主题内容长度（字母长度）
        this.allianceTopicMessageLength = parse2Int(this, "mailContentLength")

        // 联盟名称长度范围
        this.allianceWaijiaoLength = parse2Int(this, "allianceWaijiaoLength")

        // 联盟昵称长度范围
        this.allianceNickNameLength = parse2Int(this, "nicNameLength")

        // 联盟昵称长度范围
        val markTextLen = strconvAtoi(this.basicMap["markText"])
            ?: throw RuntimeException("Basic.xml :: markText ::${this.basicMap["markText"]}转换整型有误1:")
        this.markTextLength = LinkedList(asList(0, markTextLen))

        // 玩家个人简介长度（字母长度）
        val playerDescriptionLen = strconvAtoi(this.basicMap["powerWorldLimitNum"])
            ?: throw RuntimeException("Basic.xml :: powerWorldLimitNum ::${this.basicMap["powerWorldLimitNum"]}转换整型有误1:")
        this.playerDescriptionLength = LinkedList(asList(0, playerDescriptionLen))

        // 聊天消息长度（字母长度）

        val chatMessageLen = strconvAtoi(this.basicMap["chatWorldLimitNum"])
            ?: throw RuntimeException("Basic.xml :: chatWorldLimitNum ::${this.basicMap["chatWorldLimitNum"]}转换整型有误1:")
        this.chatMessageLength = LinkedList(asList(0, chatMessageLen))

        // 联盟默认的旗帜
        val allianceDefaultFlag: LinkedList<Int> = LinkedList()
        val sFlag = stringsSplit(this.basicMap["baseFlagId"], "%")
        if (sFlag.size != 3) {
            throw RuntimeException("basic.xml :: baseFlagId 需要配置默认旗帜的三个属性: ${this.basicMap["baseFlagId"]}.")
        }

        // 旗帜颜色
        val iFlag1 = strconvAtoi(sFlag[0])
            ?: throw RuntimeException("Basic.xml :: baseFlagId ::${this.basicMap["baseFlagId"]}转换整型有误1:")
        allianceDefaultFlag.add(iFlag1)

        // 旗帜样式
        val iFlag2 = strconvAtoi(sFlag[1])
            ?: throw RuntimeException("Basic.xml :: baseFlagId ::${this.basicMap["baseFlagId"]}转换整型有误2:")
        allianceDefaultFlag.add(iFlag2)

        // 旗帜效果
        val iFlag3 = strconvAtoi(sFlag[2])
            ?: throw RuntimeException("Basic.xml :: baseFlagId ::${this.basicMap["baseFlagId"]}转换整型有误3")
        allianceDefaultFlag.add(iFlag3)
        this.allianceDefaultFlag = allianceDefaultFlag

        // 修改联盟外交冷却时间
        this.allianceRelationShipSetDuration = parseDuration(this.basicMap["cripsDiplomacyTime"], "m")

        this.saveWorldChatListMaxNum = parseInt(this, "saveWorldChatListMaxNum")

        this.saveGroupChatListMaxNum = parseInt(this, "saveGroupChatListMaxNum")

        this.saveNationChatListMaxNum = parseInt(this, "saveNationChatListMaxNum")

        this.savSystemChatListMaxNum = parseInt(this, "savSystemChatListMaxNum")

        this.fightReportAskNum = parseInt(this, "fightReportAskNum")

        this.allianceMarkCountLimit = parseInt(this, "cripsMarkNumLimit")

        this.towerFirstTimes = parseInt(this, "towerFirstTimes")

        this.towerTimesRecovery = parseInt(this, "towerTimesRecovery")

        this.towerTimesLimits = parseInt(this, "towerTimesLimits")

        this.numOfStatementsLimit = parseInt(this, "numOfStatementsLimit")

        this.allianceAiplomacyAllLimit = parseInt(this, "allianceAiplomacyAllLimit")

        this.allianceAiplomacyNumlimit = parseInt(this, "allianceAiplomacyNumlimit")

        this.allianceAiplomacyTimelimit = parseInt(this, "allianceAiplomacyTimelimit")

        this.initJjcForcesNum = parseInt(this, "arenaFirstTeam")

        this.initJjcPlansNum = parseInt(this, "arenaFirstPrepareTeam")

        this.jjcPlanNameLength = parse2Int(this, "arenaFirstPrepareLength")

        this.jjcInitNpcCount = parseInt(this, "arenaNpcNum")

        this.arenaLoseCD = parseInt(this, "arenaLoseCD")

        this.arenaCleanCD = parseInt(this, "arenaCleanCD")

        this.arenaTimesLimit = parseInt(this, "arenaTimesLimit")

        this.arenaFreeTimes = parseInt(this, "arenaFreeTimes")

        val arenaCoinRewardString = this.basicMap["arenaCoinReward"]
        if (arenaCoinRewardString != null) {
            val arenaCoinRewardTmp = resStringToResVoList(arenaCoinRewardString)
            if (arenaCoinRewardTmp == null || arenaCoinRewardTmp.isEmpty()) {
                throw RuntimeException("basic中的arenaCoinRewardString配置错了")
            } else {
                this.arenaCoinReward = arenaCoinRewardTmp
            }
        }

        val arenaSucessString = this.basicMap["arenaSuccess"]
            ?: throw RuntimeException("basic中的arenaSucess木有配置")
        val arenaSucessConditions = LinkedList<Int>()
        stringsSplit(arenaSucessString, ";").forEach {
            val condition = it.toIntOrNull() ?: throw RuntimeException("basic中的arenaSuccess配置错了")
            arenaSucessConditions.add(condition)
        }
        this.arenaSuccess = arenaSucessConditions

        this.arenaBuyTimes = parseInt(this, "arenaBuyTimes")

        val jjcFightScores = stringsSplit(this.basicMap["arenaFightScore"], "%")
        for (jjcScore in jjcFightScores) {
            val ss = stringsSplit(jjcScore, ":")
            if (ss.size != 2) {
                throw RuntimeException("Basic.xml::arenaFightScore 配置有误${jjcScore}长度无法分割成2个.")
            }

            // 积分
            val score = strconvAtoi(ss[1]) ?: throw RuntimeException("Basic.xml::arenaFightScore 转换整型有误:")

            when {
                ss[0] == "1" -> // 失败
                    this.jjcFightFailureScore = score
                ss[0] == "2" -> // 成功
                    this.jjcFightWinScore = score
                else -> throw RuntimeException("Basic.xml::arenaFightScore 位置的类型: ${ss[0]}")
            }
        }

        this.goInBattleLand = LinkedList()
        val goInBattleLands = stringsSplit(this.basicMap["goInBattleLand"], ":")
        for (goInBattleLand in goInBattleLands) {
            val g = strconvAtoi(goInBattleLand)
            if (g != null) {
                this.goInBattleLand.add(g)
            }
        }

        val rmbExchangeGolds = stringsSplit(this.basicMap["rmbExchangeGold"], ":")
        if (rmbExchangeGolds.size != 2) {
            throw RuntimeException("Basic.xml::rmbExchangeGold 字段长度有误 != 2")
        }

        val rmbExchangeGold =
            strconvAtoi(rmbExchangeGolds[1]) ?: throw RuntimeException("Basic.xml::rmbExchangeGold 转换整型有误:")

        this.rmbExchangeGold = rmbExchangeGold

        // 邮件过期删除时间（时）
        this.mailExpireDuration = parseDuration(this.basicMap["mailAutoDeleteTime"], "h")

        // 联盟日志过期删除时间（时）
        this.allianceLogTimeLimit = parseDuration(this.basicMap["allianceLogTimeLimit"], "h")

        // 联盟日志过期删除时间（时）
        this.serverOpenTime = parseDuration(this.basicMap["serverOpenTime"], "h")

        // 建筑免费加速时间（秒）
        this.upBuildingFreeDuration = parseDuration(this.basicMap["upBuildingFree"], "s")

        this.upBuildingFree = parseInt(this, "upBuildingFree")

// 竞技场商店刷新时间
//        val jjcShopRefreshTime = this.basicMap["arenaRefresh"]
//        val (jjcSchedule, e3) = commonfunc.NewEveryDaySchedule(jjcShopRefreshTime)
//        if (e3 == null) {
//            throw RuntimeException("basic.xml :: arenaRefresh :: ${this.basicMap["arenaRefresh"]} to cron.Schedule 错误:.")
//        }
//        this.jjcRefreshShopTime = jjcSchedule

        // 竞技场商店刷新价格
        this.shopRefreshCost = parseInt(this, "arenaShopRefreshPrice")

        this.expCardNum = parseInt(this, "expCardNum")

        this.heroMaxLv = parseInt(this, "heroMaxLv")

        this.noLossLv = parseInt(this, "noLossLv")

        this.lossScale = parseInt(this, "lossScale")

        this.firstMission = parseInt(this, "firstMission")

        this.pVEReportAskNum = parseInt(this, "pveReportAskNum")

        this.pVEReportSaveNum = parseInt(this, "pveReportSaveNum")

        this.pVEReportSaveTime = parseInt(this, "pveReportSaveTime")

        this.helpStarTimes = parseInt(this, "helpStarTimes")

        this.interiorHeroFirstNum = parseInt(this, "interiorHeroFirstNum")

        this.deputyLeadeNub = parseInt(this, "deputyLeadeNub")

        this.memberNub = parseInt(this, "memberNub")

        this.diplomatNub = parseInt(this, "diplomatNub")

        this.commanderNub = parseInt(this, "commanderNub")

        this.headNub = parseInt(this, "headNub")

        this.eliteGroupNub = parseInt(this, "eliteGroupNub")

        this.diplomaticRelationsNub = parseInt(this, "diplomaticRelationsNub")

        this.technologyDonateTimes = parseInt(this, "technologyDonateTimes")

        this.technologyRecoverTime = parseInt(this, "technologyRecoverTime")

        this.batteryRange = parseInt(this, "batteryRange")

        this.magicArrayRange = parseInt(this, "magicArrayRange")

        this.allianceFlagRange = parseInt(this, "allianceFlagRange")

        this.allianceFlagBuildTime = parseInt(this, "allianceFlagBuildTime")

        this.allianceFlagBuildRatio = parseInt(this, "allianceFlagBuildRatio")

        this.alliancePositionLimit = parseInt(this, "alliancePositionLimit")

        this.cripsDonateDateLimit = parseInt(this, "cripsDonateDateLimit")

        this.systemDemiseTime = parseInt(this, "systemDemiseTime")

        this.trainStarNum = parseInt(this, "trainStarNum")

        this.cureStarNum = parseInt(this, "cureStarNum")

        val commonDieWoundRate = stringsSplit(this.basicMap["commonDieWoundRate"], ":")
        if (commonDieWoundRate.size != 3) {
            throw RuntimeException("basic.xml :: commonDieWoundRate :: ${this.basicMap["commonDieWoundRate"]} to cron.Schedule 错误 .")
        }

        val commonDieRate =
            strconvAtoi(commonDieWoundRate[0]) ?: throw RuntimeException("basic.xml :: commonDieRate error.")
        this.commonDieRate = commonDieRate

        val commonShangRate =
            strconvAtoi(commonDieWoundRate[1]) ?: throw RuntimeException("basic.xml :: commonShangRate error.")
        this.commonShangRate = commonShangRate

        val commonHuiRate =
            strconvAtoi(commonDieWoundRate[2]) ?: throw RuntimeException("basic.xml :: commonHuiRate error.")
        this.commonHuiRate = commonHuiRate

        val pvpAttDieWoundRate = stringsSplit(this.basicMap["pvpAttDieWoundRate"], ":")
        if (pvpAttDieWoundRate.size != 3) {
            throw RuntimeException("basic.xml :: pvpAttDieWoundRate :: ${this.basicMap["pvpAttDieWoundRate"]} to cron.Schedule 错误1.")
        }

        val pvpAtkDieRate =
            strconvAtoi(pvpAttDieWoundRate[0]) ?: throw RuntimeException("basic.xml :: pvpAtkDieRate error.")
        this.pvpAtkDieRate = pvpAtkDieRate

        val pvpAtkShangRate =
            strconvAtoi(pvpAttDieWoundRate[1]) ?: throw RuntimeException("basic.xml :: pvpAtkShangRate error.")
        this.pvpAtkShangRate = pvpAtkShangRate

        val pvpAtkHuiRate =
            strconvAtoi(pvpAttDieWoundRate[2]) ?: throw RuntimeException("basic.xml :: pvpAtkHuiRate error.")
        this.pvpAtkHuiRate = pvpAtkHuiRate

        val pvpDefDieWoundRate = stringsSplit(this.basicMap["pvpDefDieWoundRate"], ":")
        if (pvpDefDieWoundRate.size != 3) {
            throw RuntimeException("basic.xml :: pvpDefDieWoundRate :: ${this.basicMap["pvpDefDieWoundRate"]} to cron.Schedule 错误 .")
        }

        val pvpDefDieRate =
            strconvAtoi(pvpDefDieWoundRate[0]) ?: throw RuntimeException("basic.xml ::pvpDefDieWoundRate 错误1")
        this.pvpDefDieRate = pvpDefDieRate

        val pvpDefShangRate =
            strconvAtoi(pvpDefDieWoundRate[1]) ?: throw RuntimeException("basic.xml ::pvpDefShangRate 错误1")
        this.pvpDefShangRate = pvpDefShangRate

        val pvpDefHuiRate =
            strconvAtoi(pvpDefDieWoundRate[2]) ?: throw RuntimeException("basic.xml ::pvpDefHuiRate 错误1")

        this.pvpDefHuiRate = pvpDefHuiRate

        val pveDieWoundRate = stringsSplit(this.basicMap["pveDieWoundRate"], ":")
        if (pveDieWoundRate.size != 3) {
            throw RuntimeException("basic.xml :: pveDieWoundRate :: ${this.basicMap["pveDieWoundRate"]} to cron.Schedule 错误 .")
        }

        val pveDieRate = strconvAtoi(pveDieWoundRate[0]) ?: throw RuntimeException("basic.xml ::pveDieRate 错误1")
        this.pveDieRate = pveDieRate

        val pveShangRate = strconvAtoi(pveDieWoundRate[1]) ?: throw RuntimeException("basic.xml ::pveShangRate 错误1")
        this.pveShangRate = pveShangRate

        val pveHuiRate = strconvAtoi(pveDieWoundRate[2]) ?: throw RuntimeException("basic.xml ::pveHuiRate 错误1")
        this.pveHuiRate = pveHuiRate

        val tmpNamePrefix = this.basicMap["namePrefix"]
        if (tmpNamePrefix != null) {
            this.namePrefix = tmpNamePrefix
        }

        val jjcRankShowForceNumss = this.basicMap["arenaRankShow"]
        val jjcRankShowForceNums = stringsSplit(jjcRankShowForceNumss, "%")

        this.arenaRankShow = hashMapOf()
        for (jjcRankShowForceNum in jjcRankShowForceNums) {
            val jjcRankShowForce = stringsSplit(jjcRankShowForceNum, ":")
            if (jjcRankShowForce.size != 2) {
                throw RuntimeException("basic.xml :: arenaRankShow :: ${this.basicMap["arenaRankShow"]} to cron.Schedule 错误 .")
            }
            val jjcRankShow = stringsSplit(jjcRankShowForce[0], "_")
            if (jjcRankShow.size != 2) {
                throw RuntimeException("basic.xml :: arenaRankShow :: ${this.basicMap["arenaRankShow"]} to cron.Schedule 错误 .")
            }

            val showNum = strconvAtoi(jjcRankShowForce[1]) ?: throw RuntimeException("jjcRankShowForce error")
            this.arenaRankShow[jjcRankShowForce[0]] = showNum
        }

        val tmpBasicMapjunshiTalentReset = this.basicMap["junshiTalentReset"]
        if (tmpBasicMapjunshiTalentReset != null) {
            val junshiTalentResetCost = resStringToResVoList(tmpBasicMapjunshiTalentReset)
            if (null == junshiTalentResetCost || junshiTalentResetCost.isEmpty()) {
                throw RuntimeException("basic中的tmpBasicMap  junshiTalentReset字段解析出错")
            } else {
                this.militaryTalentResetCost = junshiTalentResetCost
            }
        }

        val jingjiTalentResetString = this.basicMap["jingjiTalentReset"]
            ?: throw RuntimeException("basic中的tmpBasicMap  jingjiTalentResetCost  字段解析出错")
        val jingjiTalentResetCost = resStringToResVoList(jingjiTalentResetString)
        if (jingjiTalentResetCost == null || jingjiTalentResetCost.isEmpty()) {
            throw RuntimeException("basic中的tmpBasicMap  jingjiTalentResetCost  字段解析出错")
        } else {
            this.economyTalentResetCost = jingjiTalentResetCost
        }

        val monsterTalentReset = this.basicMap["monsterTalentReset"]
            ?: throw RuntimeException("basic中的tmpBasicMap  monsterTalentReset  字段解析出错")
        val monsterTalentResetCostId = resStringToResVoList(monsterTalentReset)
        if (monsterTalentResetCostId == null || monsterTalentResetCostId.isEmpty()) {
            throw RuntimeException("basic中的tmpBasicMap  monsterTalentReset  字段解析出错")
        } else {
            this.monsterTalentResetCost = monsterTalentResetCostId
        }

        val allTalentReset = this.basicMap["allTalentReset"]
            ?: throw RuntimeException("basic中的tmpBasicMap  allTalentReset  字段解析出错")
        val allTalentResetCostId = resStringToResVoList(allTalentReset)
        if (allTalentResetCostId == null || allTalentResetCostId.isEmpty()) {
            throw RuntimeException("basic中的tmpBasicMap  allTalentReset  字段解析出错")
        } else {
            this.allTalentResetCost = allTalentResetCostId
        }

        val tmpbasicMaplmmcTalentReset = basicMap["lmmcTalentReset"]
        if (tmpbasicMaplmmcTalentReset != null) {
            val lmmcTalentResetCost = resStringToResVoList(tmpbasicMaplmmcTalentReset)
            if (lmmcTalentResetCost == null || lmmcTalentResetCost.isEmpty()) {
                throw RuntimeException("basic中的lmmcTalentReset配置错了")
            } else {
                this.lmmcTalentReset = lmmcTalentResetCost
            }
        }

        val tmpbasicMaplmjcTalentReset = basicMap["lmjcTalentReset"]
        if (tmpbasicMaplmjcTalentReset != null) {
            val lmjcTalentResetCost = resStringToResVoList(tmpbasicMaplmjcTalentReset)
            if (lmjcTalentResetCost == null || lmjcTalentResetCost.isEmpty()) {
                throw RuntimeException("basic中的lmjcTalentReset配置错了")
            } else {
                this.lmjcTalentReset = lmjcTalentResetCost
            }
        }

        this.mapMonster = parseInt(this, "mapMonster")

        this.mapRelic = parseInt(this, "mapRelic")

        this.timeZone = parseInt(this, "timeZone")

        this.bornFrist = parseInt(this, "bornFrist")

        this.bornUp = parseInt(this, "bornUp")

        this.peopleNum = parseInt(this, "peopleNum")

        this.allianceMinimumCombatPower = parseLong(this, "allianceMinimumCombatPower")

        this.baseHeroSpeed = parseFloat64(this, "baseHeroSpeed")

        this.baseSpySpeed = parseFloat64(this, "baseSpySpeed")

        this.baseTransportSpeed = parseFloat64(this, "baseTransportSpeed")

        this.heroSpeedPara = parseFloat32(this, "heroSpeedPara")

        this.robTransportRate = parseInt(this, "robTransportRate")

        this.tradShipRefreshTime = parseInt(this, "tradShipRefreshTime")

        this.allianceGiftLimit = parseInt(this, "allianceGiftLimit")

        val tradShipOpenCondition = LinkedList<Int>()
        val tradShipOpenConditions = stringsSplit(this.basicMap["tradShipOpenCondition"], ":")
        if (tradShipOpenConditions.size != 3) {
            throw RuntimeException("basic中的tradShipOpenCondition配置错了")
        }

        for (c in tradShipOpenConditions) {
            val cc = strconvAtoi(c) ?: throw RuntimeException("basic中的tradShipOpenConditions配置错了")
            tradShipOpenCondition.add(cc)
        }
        this.tradShipOpenCondition = tradShipOpenCondition

        val caveTime = LinkedList<Int>()
        val caveTimes = stringsSplit(this.basicMap["caveTime"], ":")
        if ((caveTimes.size) == 0) {
            throw RuntimeException("basic中的caveTime配置错了")
        }
        for (c in caveTimes) {
            val cc = strconvAtoi(c) ?: throw RuntimeException("basic中的caveTime配置错了")
            caveTime.add(cc)
        }
        this.caveTime = caveTime

        val massTime = LinkedList<Int>()
        val massTimes = stringsSplit(this.basicMap["buildUpTime"], ":")
        if ((massTimes.size) == 0) {
            throw RuntimeException("basic中的buildUpTime配置错了")
        }
        for (c in massTimes) {
            val cc = strconvAtoi(c) ?: throw RuntimeException("basic中的buildUpTime配置错了")
            massTime.add(cc)
        }
        this.massTime = massTime

        playerMassedLimit = parseInt(this, "allianceMass")

        this.resProportionMap = hashMapOf()
        val resProportionList = LinkedList<Int>()
        val resProportions = stringsSplit(this.basicMap["resProportion"], "%")
        var allQuanzhi = 0
        var nowValue = 0
        for (resProportion in resProportions) {
            val ress = stringsSplit(resProportion, ":")
            if (ress.size != 2) {
                throw RuntimeException("basic中的resProportion配置错了")
            }
            val resType = strconvAtoi(ress[0]) ?: throw RuntimeException("basic中的resProportion配置错了")
            val resQuanzhi = strconvAtoi(ress[1]) ?: throw RuntimeException("basic中的resProportion配置错了")

            allQuanzhi += resQuanzhi
            val resProportionMapresType = ResProportionMapHelpVo(nowValue + 1, nowValue + resQuanzhi)
            this.resProportionMap[resType] = resProportionMapresType
            nowValue += resQuanzhi

            resProportionList.add(resType)
        }
        this.resProportionList = resProportionList

        this.impeachmentTime = hashMapOf()
        val impeachmentTimes = stringsSplit(this.basicMap["impeachmentTime"], "%")
        for (i in impeachmentTimes) {
            val c = stringsSplit(i, ":")
            if (c.size != 2) {
                throw RuntimeException("basic中的impeachmentTime配置错了")
            }
            val pos = strconvAtoi(c[0]) ?: throw RuntimeException("basic中的impeachmentTime配置错了")
            val needHours = strconvAtoi(c[1]) ?: throw RuntimeException("basic中的impeachmentTime配置错了")
            this.impeachmentTime[pos] = needHours
        }

        val tmpbasicMapimpeachmentConsumption = basicMap["impeachmentConsumption"]
        if (tmpbasicMapimpeachmentConsumption != null) {
            val impeachmentConsumptionCost = resStringToResVoList(tmpbasicMapimpeachmentConsumption)
            if (null == impeachmentConsumptionCost || impeachmentConsumptionCost.isEmpty()) {
                throw RuntimeException("basic中的impeachmentConsumption配置错了")
            }
            this.impeachmentConsumptionCost = impeachmentConsumptionCost
        }

        if (allQuanzhi != 10000) {
            throw RuntimeException("basic中的resProportion配置错了2")
        }

        val resourceArea =
            strconvAtoi(this.basicMap["resourceArea"]) ?: throw RuntimeException("basic中的resourceArea 配置错了")
        this.resourceArea = resourceArea

        val mapMonsterInitialTimes = stringsSplit(this.basicMap["mapMonsterInitialTime"], ":")
        if (mapMonsterInitialTimes.size != 3) {
            throw RuntimeException("basic中的mapMonsterInitialTime配置错了")
        }

        val mapMonsterInitialTimesYear =
            strconvAtoi(mapMonsterInitialTimes[0]) ?: throw RuntimeException("mapMonsterInitialTimesYear配置错了")
        this.mapMonsterInitialTimesYear = mapMonsterInitialTimesYear
        val mapMonsterInitialTimesMonth =
            strconvAtoi(mapMonsterInitialTimes[1]) ?: throw RuntimeException("mapMonsterInitialTimesMonth配置错了 ")
        this.mapMonsterInitialTimesMonth = mapMonsterInitialTimesMonth

        val mapMonsterInitialTimesDay =
            strconvAtoi(mapMonsterInitialTimes[2]) ?: throw RuntimeException("mapMonsterInitialTimesDay 配置错了 ")
        this.mapMonsterInitialTimesDay = mapMonsterInitialTimesDay

        val monsterArea = strconvAtoi(this.basicMap["monsterArea"]) ?: throw RuntimeException("monsterArea 配置错了 ")
        this.monsterArea = monsterArea
        val relicArea = strconvAtoi(this.basicMap["relicArea"]) ?: throw RuntimeException("relicArea 配置错了 ")
        this.relicArea = relicArea

        // 大地图的长宽
        val allArea = strconvAtoi(this.basicMap["allArea"]) ?: throw RuntimeException("allArea 配置错了 ")
        this.allArea = allArea

        // 计算各大块的数量
        val eachResGridNum = this.allArea / this.resourceArea
        this.allResGridNum = eachResGridNum * eachResGridNum

        val eachMonsterGridNum = this.allArea / this.monsterArea
        val allMonsterGridNum = eachMonsterGridNum * eachMonsterGridNum
        this.allMonsterGridNum = allMonsterGridNum

        val eachRelicGridNum = this.allArea / this.relicArea
        val allRelicGridNum = eachRelicGridNum * eachRelicGridNum
        this.allRelicGridNum = allRelicGridNum

        this.mapSelection = "bigMapCompose_1" // todo 大地图选择方案目前写死

        this.energyLimit = parseInt(this, "firstEnergyLimit")

        this.initialEnergy = parseInt(this, "firstEnergy")

        this.energyRecovery = parseInt(this, "recoveryEnergy")

        this.protectUnMassCityLv = parseInt(this, "proptectAggregation")

        this.massRelicMinMember = parseInt(this, "inimumNumAggregation")

        this.monsterBossShowNumber = parseInt(this, "monsterBossShowNumber")

        this.monsterBossNumber = parseInt(this, "monsterBossNumber")

        this.newMonsterBossTime = parseInt(this, "newMonsterBossTime")

        this.preMonsterBossTime = parseInt(this, "preMonsterBossTime")

        this.soliderAttackPara = parseFloat64(this, "soliderAttackPara")

        this.soliderDefendPara = parseFloat64(this, "soliderDefendPara")

        this.minAttDefDiff = parseFloat64(this, "minAttDefDiff")

        this.soliderHarmPara = parseFloat64(this, "soliderHarmPara")

        this.maxDefendCityTroops = parseInt(this, "maxDefendCityTroops")

        this.warCrazy = parseInt(this, "warCrazy")

        this.wonderBuff = parseInt(this, "wonderBuff")

        this.numberOfTitlesAvailable = parseInt(this, "numberOfTitlesAvailable")

        this.mapRounds = parseInt(this, "mapRounds")

        this.areaMaxDensity = parseInt(this, "areaMaxDensity")

        this.rewardMaxNum = parseInt(this, "rewardMaxNum")

        this.strongHoldRecoveryTime = parseInt(this, "strongholdTmine")

        this.strongHoldAtkCount = parseInt(this, "strongholdNum")

        this.pvpAttackerDieRate = parseInt(this, "pvpAttackerDieRate")


        this.relicReviveRate = parseInt(this, "relicReviveRate")


        this.strongholdReviveRate = parseInt(this, "strongholdReviveRate")


        this.prisonNum = parseInt(this, "prisonNum")


        this.rebornTime = parseInt(this, "rebornTime")


        this.killTime = parseInt(this, "killTime")

        this.killWaitTimeMin = parseInt(this, "killWaitTimeMin")

        this.openSkinStrengthen = parseInt(this, "openSkinStrengthen")


        this.commissionRate = parseInt(this, "commissionRate")


        this.addEquipmentPositionMin = parseInt(this, "addEquipmentPositionMin")


        this.addEquipmentPositionMax = parseInt(this, "addEquipmentPositionMax")


        this.interiorTaskRefreshTime = parseInt(this, "interiorTaskRefreshTime")


        this.interiorTaskPointMaxNum = parseInt(this, "interiorTaskPointMaxNum")


        this.interiorTaskBaseNum = parseInt(this, "interiorTaskBaseNum")


        this.allianceTreasureQuestTime = parseInt(this, "allianceTreasureQuestTime")


        this.allianceTreRefreshBaseNum = parseInt(this, "allianceTreRefreshBaseNum")


        this.allianceTreasureCloseTime = parseInt(this, "allianceTreasureCloseTime")


        this.ransomRate = parseInt(this, "ransomRate")


        this.commissionMax = parseInt(this, "commissionMax")


        this.commissionMin = parseInt(this, "commissionMin")


        this.ransomMax = parseInt(this, "ransomMax")


        this.ransomMin = parseInt(this, "ransomMin")

        val fastRebornS = this.basicMap["fastReborn"]
            ?: throw RuntimeException("basic中的tmpBasicMap  fastReborn  字段解析出错")
        val tmpFastReborn = resStringToResVoList(fastRebornS)
        if (tmpFastReborn == null || tmpFastReborn.isEmpty()) {
            throw RuntimeException("basic中的tmpBasicMap  fastReborn  字段解析出错")
        } else {
            this.fastReborn = tmpFastReborn
        }

        val tmpFastKill = this.basicMap["fastKill"] ?: throw RuntimeException("basic中的fastKill字段解析出错")
        val fastKill = resStringToResVoList(tmpFastKill)
        if (fastKill == null || fastKill.isEmpty()) {
            throw RuntimeException("basic中的fastKill字段解析出错")
        }
        this.fastKill = fastKill

        this.fastDead = parseInt(this, "fastDead")

        val getViipRewardRefreshTimeStrs = stringsSplit(this.basicMap["vipDayGiveTime"], ";")
        if (getViipRewardRefreshTimeStrs.size != 2) {
            throw RuntimeException("basic.xml vipDayGiveTime错误{${this.basicMap["vipDayGiveTime"]}}")
        }
        val hour = strconvAtoi(getViipRewardRefreshTimeStrs[0])
            ?: throw RuntimeException("basic.xml vipDayGiveTime错误{${this.basicMap["vipDayGiveTime"]}}")
        if (hour < 0 || hour > 23) {
            throw RuntimeException("basic.xml vipDayGiveTime错误{${this.basicMap["vipDayGiveTime"]}}")
        }
        this.getVipRewardRefreshHour = hour
        val minute = strconvAtoi(getViipRewardRefreshTimeStrs[1])
            ?: throw RuntimeException("basic.xml vipDayGiveTime错误{${this.basicMap["vipDayGiveTime"]}}")
        if (minute < 0 || minute > 59) {
            throw RuntimeException("basic.xml vipDayGiveTime错误{${this.basicMap["vipDayGiveTime"]}}")
        }
        this.getVipRewardRefreshMinute = minute

        this.vipEveryDayGet = parseInt(this, "vipEveryDayGet")

        this.vipEveryDayMost = parseInt(this, "vipEveryDayMost")

        this.destoryBuildingMinTime = parseInt(this, "destroyBuildingMinTime")

        val tmpbasicMapdestroyBuildingProps = this.basicMap["destroyBuildingProps"]
            ?: throw RuntimeException("basic中的tmpBasicMap  destroyBuildingProps  字段解析出错")
        val destoryBuildingPropsCost = resStringToResVoList(tmpbasicMapdestroyBuildingProps)
        if (destoryBuildingPropsCost == null || destoryBuildingPropsCost.isEmpty()) {
            throw RuntimeException("basic中的tmpBasicMap  destroyBuildingProps  字段解析出错")
        } else {
            this.destoryBuildingProps = destoryBuildingPropsCost
        }

        val tmpbasicMapallianceTreasureCost = basicMap["allianceTreasureCost"]
        if (tmpbasicMapallianceTreasureCost != null) {
            val allianceTreasureCostDrops = resStringToResVoList(tmpbasicMapallianceTreasureCost)
            if (allianceTreasureCostDrops == null || allianceTreasureCostDrops.isEmpty()) {
                throw RuntimeException("basic中的allianceTreasureCost字段解析出错")
            }
            this.allianceTreasureCostDrops = allianceTreasureCostDrops
        }

        val tmpbasicMapallianceCompetitionQuestValue = basicMap["allianceCompetitionQuestValue"]
        if (tmpbasicMapallianceCompetitionQuestValue != null) {
            val allianceCompetitionQuestValue = resStringToResVoList(tmpbasicMapallianceCompetitionQuestValue)
            if (allianceCompetitionQuestValue == null || allianceCompetitionQuestValue.isEmpty()) {
                throw RuntimeException("basic中的allianceCompetitionQuestValue字段解析出错")
            }
            this.allianceCompetitionQuestValue = allianceCompetitionQuestValue
        }

        val tmpbasicMapinteriorTaskBaseDropNumber = basicMap["interiorTaskBaseDropNumber"]
        if (tmpbasicMapinteriorTaskBaseDropNumber != null) {
            val interiorTaskBaseDrops = resStringToResVoList(tmpbasicMapinteriorTaskBaseDropNumber)
            if (interiorTaskBaseDrops == null || interiorTaskBaseDrops.isEmpty()) {
                throw RuntimeException("basic中的interiorTaskBaseDropNumber字段解析出错")
            } else {
                this.interiorTaskBaseDrops = interiorTaskBaseDrops
            }
        }
        val resWeightTmp = this.basicMap["resWeight"]
        if (resWeightTmp != null) {
            val paraseFloatMapRet = parseFloatMap(resWeightTmp)
            if (paraseFloatMapRet == null) {
                throw RuntimeException("basic中的resWeight字段解析出错")
            } else {
                this.resWeight = paraseFloatMapRet

            }
        }

        this.fightAfterSevice = parseInt(this, "fightAfterService")

        this.fightInterval = parseInt(this, "fightInterval")

        val redBagPlayerNum = stringsSplit(this.basicMap["redBagPlayerNum"], ":")
        if (redBagPlayerNum.size != 2) {
            throw RuntimeException("basic中的redBagPlayerNum字段解析出错")
        }

        val redBagPlayerMinNum =
            strconvAtoi(redBagPlayerNum[0]) ?: throw RuntimeException("basic中的redBagPlayerMinNum字段解析出错")
        this.redBagPlayerMinNum = redBagPlayerMinNum

        val redBagPlayerMaxNum =
            strconvAtoi(redBagPlayerNum[1]) ?: throw RuntimeException("basic中的redBagPlayerMaxNum字段解析出错")
        this.redBagPlayerMaxNum = redBagPlayerMaxNum

        this.redBagListCost = parseInt(this, "redBagListCost")


        this.allianceCompetitionConditions = parseInt(this, "allianceCompetitionConditions")


        this.allianceCompetitionOnesConditions = parseInt(this, "allianceCompetitionOnesConditions")


        this.numberOfInitialTasks = parseInt(this, "numberOfInitialTasks")


        this.allianceCompetitionAwardTime = parseInt(this, "allianceCompetitionAwardTime")


        this.allianceCompetitionContinuedTime = parseInt(this, "allianceCompetitionContinuedTime")


        this.allianceCompetitionInitialTime = parseInt(this, "allianceCompetitionInitialTime")


        this.allianceCompetitionFirstLevel = parseInt(this, "allianceCompetitionFirstLevel")

        val allianceCompetitionOpenTimeStrs = stringsSplit(this.basicMap["allianceCompetitionOpenTime"], "_")
        if (allianceCompetitionOpenTimeStrs.size != 4) {
            throw RuntimeException("basic中的allianceCompetitionOpenTime字段解析出错")
        }

        val allianceCompetitionOpenTimeD = strconvAtoi(allianceCompetitionOpenTimeStrs[0])
        val allianceCompetitionOpenTimeH = strconvAtoi(allianceCompetitionOpenTimeStrs[1])
        val allianceCompetitionOpenTimeM = strconvAtoi(allianceCompetitionOpenTimeStrs[2])
        val allianceCompetitionOpenTimeS = strconvAtoi(allianceCompetitionOpenTimeStrs[3])
        if (allianceCompetitionOpenTimeD == null ||
            allianceCompetitionOpenTimeH == null ||
            allianceCompetitionOpenTimeM == null ||
            allianceCompetitionOpenTimeS == null
        ) {
            throw RuntimeException("basic中的allianceCompetitionOpenTime字段解析出错")
        }

        val allianceCompetitionOpenTime: LinkedList<Int> = LinkedList()
        allianceCompetitionOpenTime.add(allianceCompetitionOpenTimeD)
        allianceCompetitionOpenTime.add(allianceCompetitionOpenTimeH)
        allianceCompetitionOpenTime.add(allianceCompetitionOpenTimeM)
        allianceCompetitionOpenTime.add(allianceCompetitionOpenTimeS)
        this.allianceCompetitionOpenTime = allianceCompetitionOpenTime

        this.baseWoodYield = parseInt(this, "baseYieldWood")


        this.baseIronYield = parseInt(this, "baseYieldCrystal")


        this.baseStoneYield = parseInt(this, "baseYieldStone")


        this.baseFoodYield = parseInt(this, "baseYieldFood")


        this.baseCoinYield = parseInt(this, "baseYieldCoin")


        this.baseWoodLimit = parseInt(this, "baseToplimitWood")


        this.baseIronLimit = parseInt(this, "baseToplimitCrystal")


        this.baseStoneLimit = parseInt(this, "baseToplimitStone")


        this.baseFoodLimit = parseInt(this, "baseToplimitFood")


        this.baseCoinLimit = parseInt(this, "baseToplimitCoin")

        this.resStorageLimit = parseLong(this, "resStorageLimit")

        this.pardom = parseInt(this, "pardon")


        this.noticeCd = parseInt(this, "noticeCD")


        this.arenaRewardLimit = parseLong(this, "arenaRewardLimit")


        this.redBagOverTime = parseInt(this, "redBagOverTime")


        this.redBagMostCost = parseInt(this, "redBagMostCost")


        this.deleteSystemMessages = parseInt(this, "deleteSystemMessages")

        this.allianceBigTreRefreshExp = parseInt(this, "allianceBigTreRefreshExp")
        this.helpRewordLimit = parseInt(this, "helpRewordLimit")
        this.helpTimesReword = parseInt(this, "helpTimesReword")

        this.maxInstancePower = parseInt(this, "maxInstancePower")
        this.instancePowerRecover = parseInt(this, "instancePowerRecover")
        this.firstInstancePower = parseInt(this, "firstInstancePower")
        this.firstInstance = parseInt(this, "firstInstance")
        this.favoritesMarkTop = parseInt(this, "favoritesMarkTop")
        this.favoritesMarkStart = parseInt(this, "favoritesMarkStart")

        val tmpBasicMapnoticePrice = this.basicMap["noticePrice"]
        if (tmpBasicMapnoticePrice != null) {
            val noticePrice = resStringToResVoList(tmpBasicMapnoticePrice)
            if (null == noticePrice || noticePrice.isEmpty()) {
                throw RuntimeException("basic中的noticePrice字段解析出错")
            } else {
                this.noticePrice = noticePrice
            }
        }

        this.heroMoraleLimit = parseInt(this, "heroMoraleLimit")

        this.killSoliderAddMorale = parseInt(this, "killArmyAddMorale")

        this.killHeroAddMorale = parseInt(this, "killHeroAddMorale")

        this.monsterRankNum = parseInt(this, "monsterRankNum")

        val moveMainCityConsume = this.basicMap["moveMainCityConsume"]
        if (moveMainCityConsume != null) {
            val moveMainCityConsumess = moveMainCityConsume.split("|")
            for (moveMainCityConsumes in moveMainCityConsumess) {
                val moveMainCityConsumeInfo = moveMainCityConsumes.split(";")
                if (moveMainCityConsumeInfo.size != 2) {
                    throw RuntimeException("basic中的moveMainCityConsume字段解析出错")
                }
                val propId = moveMainCityConsumeInfo[0].toInt()
                val useType = moveMainCityConsumeInfo[1].toInt()

                this.moveMainCityConsumeMap[useType] = propId
            }
        }

        val tradShipSurpriseNums = stringsSplit(this.basicMap["tradShipSurpriseNum"], ";")
        if (tradShipSurpriseNums.size != 2) {
            throw RuntimeException("basic中的tradShipSurpriseNum字段解析出错")
        }

        this.tradShipSurpriseMinNum = tradShipSurpriseNums[0].toInt()
        this.tradShipSurpriseMaxNum = tradShipSurpriseNums[1].toInt()

        val scoutCostStr = basicMap["reconConsume"]
        if (scoutCostStr != null) {
            val scoutCost = resStringToResVoList(scoutCostStr)
            if (scoutCost == null || scoutCost.isEmpty()) {
                throw RuntimeException("basic中的reconConsume字段解析出错")
            } else {
                this.reconConsume = scoutCost
            }
        }

        val removeEquipCardS =
            this.basicMap["removeEquipCard"]
                ?: throw RuntimeException("basic中的tmpBasicMap  removeEquipCard  字段解析出错")
        val removeEquipCardTmp = resStringToResVoList(removeEquipCardS)
        if (removeEquipCardTmp == null || removeEquipCardTmp.isEmpty()) {
            throw RuntimeException("basic中的tmpBasicMap  removeEquipCard  字段解析出错")
        }
        this.removeEquipCard = removeEquipCardTmp

        this.emailUpDateNumb = parseInt(this, "emailUpDateNumb")

        this.wonderWarOpen = parse2Int(this, "wonderWarOpen", ";")
        this.wonderWarClose = parse2Int(this, "wonderWarClose", ";")
        if (wonderWarOpen[0] > wonderWarClose[0]) {
            throw RuntimeException("basic中wonderWarOpen的星期大于wonderWarClose的星期")
        }
        if (wonderWarOpen[0] > 7 || wonderWarClose[0] > 7) {
            throw RuntimeException("basic中wonderWarOpen或wonderWarClose的星期大于7")
        }
        if (wonderWarOpen[0] == wonderWarClose[0] && wonderWarOpen[1] > wonderWarClose[1]) {
            throw RuntimeException("basic中wonderWarOpen的时间大于wonderWarClose的时间")
        }
        if (wonderWarOpen[1] > 24 || wonderWarClose[1] > 24) {
            throw RuntimeException("basic中wonderWarOpen或wonderWarClose时间大于24")
        }

        val wonderProtectS = basicMap["wonderProtect"] ?: throw RuntimeException("basic中的wonderProtect字段解析出错1")
        val wonderProtectSplit = wonderProtectS.split("|")
        if (wonderProtectSplit.size != 2) {
            throw RuntimeException("basic中的wonderProtect字段解析出错2")
        }
        val wonderProtectList = ArrayList<List<Int>>()
        for (s in wonderProtectSplit) {
            val split1 = s.split(";")
            if (split1.size != 3) {
                throw RuntimeException("basic中的wonderProtect字段解析出错3")
            }
            val i1 = split1[0].toIntOrNull()
            val i2 = split1[1].toIntOrNull()
            val i3 = split1[2].toIntOrNull()
            if (i1 == null || i2 == null || i3 == null) {
                throw RuntimeException("basic中的wonderProtect字段解析出错4")
            }
            wonderProtectList.add(ArrayList(asList(i1, i2, i3)))
        }
        if (wonderProtectList[0][0] > wonderProtectList[1][0]) {
            throw RuntimeException("basic中wonderProtect星期配置有误")
        }
        if (wonderProtectList[0][0] > 7 || wonderProtectList[1][0] > 7) {
            throw RuntimeException("basic中wonderProtect星期配置大于7")
        }
        if (wonderProtectList[0][0] == wonderProtectList[1][0] && wonderProtectList[0][1] > wonderProtectList[1][1]) {
            throw RuntimeException("basic中wonderProtect小时配置有误")
        }
        if (wonderProtectList[0][1] > 24 || wonderProtectList[1][1] > 24) {
            throw RuntimeException("basic中wonderProtect小时配置大于24")
        }
        wonderProtect = wonderProtectList

        this.activityHospitalNum = parseInt(this, "activityHospitalNum")

        this.wonderFireTime = parseInt(this, "wonderFireTime")

        this.wonderSmokeTime = parseInt(this, "wonderSmokeTime")

        this.battleTime = parseInt(this, "battleTime")

        this.monsterBattleTime = parseInt(this, "monsterBattleTime")

        val positionFightLeft = this.basicMap["positionFightLeft"]
        if (positionFightLeft == null) {
            throw RuntimeException("basic中的positionFightLeft字段解析出错1")
        }
        val leftStrs = stringsSplit(positionFightLeft, "|")
        if (leftStrs.count() != 9) {
            throw RuntimeException("basic中的positionFightLeft字段解析出错2")
        }
        for (i in 1..leftStrs.count()) {
            val strs = stringsSplit(leftStrs[i - 1], ";")
            if (strs.count() != 2) {
                throw RuntimeException("basic中的positionFightLeft字段解析出错3")
            }
            val x = strs[0].toIntOrNull()
            val y = strs[1].toIntOrNull()
            if (x == null || y == null) {
                throw RuntimeException("basic中的positionFightLeft字段解析出错4")
            }
            this.leftPosition[i] = Pair(x, y)
        }

        val positionFightRight = this.basicMap["positionFightRight"]
        if (positionFightRight == null) {
            throw RuntimeException("basic中的positionFightRight字段解析出错1")
        }
        val rightStrs = stringsSplit(positionFightRight, "|")
        if (rightStrs.count() != 9) {
            throw RuntimeException("basic中的positionFightRight字段解析出错2")
        }
        for (i in 1..rightStrs.count()) {
            val strs = stringsSplit(rightStrs[i - 1], ";")
            if (strs.count() != 2) {
                throw RuntimeException("basic中的positionFightRight字段解析出错3")
            }
            val x = strs[0].toIntOrNull()
            val y = strs[1].toIntOrNull()
            if (x == null || y == null) {
                throw RuntimeException("basic中的positionFightRight字段解析出错4")
            }
            this.rightPosition[i] = Pair(x, y)
        }

        this.mapGridX = parseInt(this, "mapGridx")
        this.mapGridY = parseInt(this, "mapGridy")

        this.monsterActivityOpen = parse2Int(this, "monsterActivityOpen", ";")
        this.monsterActivityAdvance = parseInt(this, "monsterActivityAdvance")
        this.monsterActivityContinue = parseInt(this, "monsterActivityContinue")
        this.monsterActivityRound = parseInt(this, "monsterActivityRound")
        this.monsterActivityMail = parseInt(this, "monsterActivityMail")
        this.specialActivityProtect = parseInt(this, "specialActivityProtect")

        this.snowBelongTime = parseInt(this, "snowBelongTime")
        this.wonderBlackBelongTime = parseInt(this, "heitudiProtectTime")
        this.snowSpeed = parseInt(this, "snowSpeed")
        this.moveLvLimit = parseInt(this, "snowMove")

        this.defaultSkinId = parseInt(this, "castleSkinDefaultID")

        this.hpRecoverHeroSkillId = parseInt(this, "hpRecoverHeroSkillId")
        this.moraleRecoverHeroSkillId = parseInt(this, "moraleRecoverHeroSkillId")

        this.heroBattleDefaultMoraleWithEnemy = parseFloat64(this, "heroBattleDefaultMoraleWithEnemy")

        this.monsterHeroMax = parseInt(this, "monsterHeroMax")

        val posTypeXSplitStrList = stringsSplit(this.basicMap["positionTypeXSplit"], "|")
        require(posTypeXSplitStrList.size == 3) {
            "positionTypeXSplit的长度不为3,${this.basicMap["positionTypeXSplit"]}"
        }
        val posXSplit = LinkedList<Pair<Int, Int>>()
        posTypeXSplitStrList.forEach {
            val strList = stringsSplit(it, ";")
            require(strList.size == 2) {
                "positionTypeXSplit的内容错误，${this.basicMap["positionTypeXSplit"]}"
            }
            val startX = strList[0].toIntOrNull()
                ?: throw RuntimeException("positionTypeXSplit的内容错误，${this.basicMap["positionTypeXSplit"]}")
            val lenX = strList[1].toIntOrNull()
                ?: throw RuntimeException("positionTypeXSplit的内容错误，${this.basicMap["positionTypeXSplit"]}")
            posXSplit.add(Pair(startX, lenX))
        }
        this.posTypeXSplit = posXSplit

        val posTypeYSplitStrList = stringsSplit(this.basicMap["positionTypeYSplit"], "|")
        require(posTypeYSplitStrList.size == 5) {
            "positionTypeYSplit的长度不为5,${this.basicMap["positionTypeYSplit"]}"
        }
        val posYSplit = LinkedList<Pair<Int, Int>>()
        posTypeYSplitStrList.forEach {
            val strList = stringsSplit(it, ";")
            require(strList.size == 2) {
                "positionTypeYSplit的内容错误，${this.basicMap["positionTypeYSplit"]}"
            }
            val startY = strList[0].toIntOrNull()
                ?: throw RuntimeException("positionTypeYSplit的内容错误，${this.basicMap["positionTypeYSplit"]}")
            val lenY = strList[1].toIntOrNull()
                ?: throw RuntimeException("positionTypeYSplit的内容错误，${this.basicMap["positionTypeYSplit"]}")
            posYSplit.add(Pair(startY, lenY))
        }
        this.posTypeYSplit = posYSplit

        this.ignoreTownLevel = parseInt(this, "ignoreTownLevel")

        val disturbMinS = basicMap["disturbMin"] ?: throw RuntimeException("basic中的disturbMin字段解析出错1")
        val disturbMinSplit = disturbMinS.split("|")
        if (disturbMinSplit.size != 2) {
            throw RuntimeException("basic中的disturbMin字段解析出错2")
        }
        val disturbMinList = ArrayList<List<Int>>()
        for (s in disturbMinSplit) {
            val split1 = s.split(";")
            if (split1.size != 2) {
                throw RuntimeException("basic中的disturbMin字段解析出错3")
            }
            val i1 = split1[0].toIntOrNull()
            val i2 = split1[1].toIntOrNull()
            if (i1 == null || i2 == null) {
                throw RuntimeException("basic中的disturbMin字段解析出错4")
            }
            disturbMinList.add(ArrayList(asList(i1, i2)))
        }
        if (disturbMinList[0][0] < 0 || disturbMinList[0][0] > 24) {
            throw RuntimeException("basic中disturbMin小时配置有误1")
        }
        if (disturbMinList[1][0] < 0 || disturbMinList[1][0] > 24) {
            throw RuntimeException("basic中disturbMin小时配置有误2")
        }
        if (disturbMinList[0][1] < 0 || disturbMinList[0][1] > 60) {
            throw RuntimeException("basic中disturbMin分钟配置有误1")
        }
        if (disturbMinList[1][1] < 0 || disturbMinList[1][1] > 60) {
            throw RuntimeException("basic中disturbMin分钟配置有误2")
        }
        this.disturbMin = disturbMinList

        this.mapMonsterRefreshTime = parseInt(this, "mapMonsterRefreshTime")

        this.darkLairSpeed = parseInt(this, "darkLairSpeed")

        this.monthCardDays = parseInt(this, "monthCardDays")

        val darkListStr = basicMap["dark_delete"] ?: throw RuntimeException("basic中的dark_delete字段不存在")
        this.darkDelete = resStringToResVoList(darkListStr) ?: throw RuntimeException("basic中的dark_delete字段解析出错")

        val trlMap: MutableMap<Int, MutableMap<Int, Long>> = mutableMapOf()
        val transferResourcesLimit =
            basicMap["transferResourcesLimit"] ?: throw RuntimeException("basic中的transferResourcesLimit字段解析出错1")
        val transferResourcesLimits = transferResourcesLimit.split("%")
        for (trl in transferResourcesLimits) {
            val trls = trl.split(",")
            if (trls.size != 2) {
                throw RuntimeException("basic中的transferResourcesLimit字段解析出错2,根据,解析出来的长度不是2")
            }

            val buildLv = trls[0].split(":")
            if (buildLv.size != 2) {
                throw RuntimeException("basic中的transferResourcesLimit字段解析出错3,根据:解析出来的长度不是2")
            }
            val minBuildLv = buildLv[0].toIntOrNull()
            val maxBuildLv = buildLv[1].toIntOrNull()
            if (minBuildLv == null || maxBuildLv == null || minBuildLv > maxBuildLv) {
                throw RuntimeException("basic中的transferResourcesLimit字段解析出错4,解析出来的城堡等级异常,minBuildLv:${minBuildLv},maxBuildLv:${maxBuildLv}")
            }

            val resMaxInfo = mutableMapOf<Int, Long>()
            val resMaxs = trls[1].split(";")
            for (resMax in resMaxs) {
                val ress = resMax.split("_")
                if (ress.size != 2) {
                    throw RuntimeException("basic中的transferResourcesLimit字段解析出错5,解析出来的资源根据_分割长度不是2,resMax:${resMax}")
                }

                val resType = ress[0].toIntOrNull()
                val resNum = ress[1].toLongOrNull()
                if (resType == null || resNum == null) {
                    throw RuntimeException("basic中的transferResourcesLimit字段解析出错6,解析出来的资源类型_资源量异常,ress:${ress}")
                }

                if (resType != RES_COIN && resType != RES_FOOD && resType != RES_WOOD && resType != RES_STONE && resType != RES_IRON) {
                    throw RuntimeException("basic中的transferResourcesLimit字段解析出错7,解析出来的资源类型异常,resType:${resType}")
                }

                resMaxInfo[resType] = resNum
            }

            for (i in minBuildLv..maxBuildLv) {
                trlMap[i] = resMaxInfo
            }
        }
        this.transferResourcesLimitMap = trlMap.toMap()
    }

    override fun postCheck(pcs: ProtoCacheStore) {

        val defaultIconProto = pcs.lordHeadIconProtoCache.lordHeadIconMap[this.defaultPhoto]
            ?: throw RuntimeException("basic.xml. 默认头像${this.defaultPhoto} 在头像表中找不到")
        if (defaultIconProto.activityOrHeroCheckMap.size > 1) {
            throw RuntimeException("basic.xml. 默认头像${this.defaultPhoto} 需要的条件多余两个")
        }
        val iconCondition = defaultIconProto.activityOrHeroCheckMap[ICON_TYPE_HERO]
        if (iconCondition != null && !this.guideGetHero.containsAll(iconCondition)) {
            throw RuntimeException("basic.xml. 默认头像${this.defaultPhoto} 不符合需要的前置条件")
        }

        if (!pcs.skinAttributeCache.skinAttributeProtoMap.containsKey(pcs.basicProtoCache.defaultSkinId)) {
            throw RuntimeException("basic.xml. 默认皮肤${pcs.basicProtoCache.defaultSkinId} 在皮肤表中找不到")
        }

        //配置的第一个任务在不在任务中

        for (taskId in this.firstTask) {
            pcs.questCache.findSpecTaskProto(taskId)
                ?: throw RuntimeException("basic.xml.firstTask :: $taskId 在任务模版列表中找不到...")
        }

        //验证配置的旗帜是否存在

        if (!pcs.allianceFlagCache.existFlag(FLAG_TYPE_COLOR, this.allianceDefaultFlag[0])) {
            throw RuntimeException("basic.xml.baseFlagId :: %d 在旗帜的【颜色】ID在模版列表中找不到...")
        }


        if (!pcs.allianceFlagCache.existFlag(FLAG_TYPE_STYLE, this.allianceDefaultFlag[1])) {
            throw RuntimeException("basic.xml.baseFlagId :: %d 在旗帜的【样式】ID在模版列表中找不到...")
        }


        if (!pcs.allianceFlagCache.existFlag(FLAG_TYPE_EFFECT, this.allianceDefaultFlag[2])) {
            throw RuntimeException("basic.xml.baseFlagId :: %d 在旗帜的【效果】ID在模版列表中找不到...")
        }
    }

    // 将字符串转换成int
    fun parseInt(b: BasicProtoCache, s: String): Int {
        val tmp = b.basicMap[s]
        if (tmp != null) {
            val i = (tmp.toIntOrNull()) ?: throw RuntimeException("fun parseInt  basic 解析时错误，转换出错")
            return i
        } else {
            throw RuntimeException("fun parseInt basic 解析时错误，map不存在该单元")
        }
    }

    // 将字符串转换成long
    private fun parseLong(b: BasicProtoCache, s: String): Long {
        val tmp = b.basicMap[s]
        if (tmp != null) {
            val i = (tmp.toLongOrNull()) ?: throw RuntimeException("fun parseInt  basic 解析时错误，转换出错")
            return i
        } else {
            throw RuntimeException("fun parseInt basic 解析时错误，map不存在该单元")
        }
    }

    private fun parseDuration(str: String?, unit: String): Long {
        if (str != null) {
            return when (unit) {
                "s" -> {
                    Duration.of(str.toLong(), ChronoUnit.SECONDS).toMillis()
                }
                "m" -> {
                    Duration.of(str.toLong(), ChronoUnit.MINUTES).toMillis()
                }
                "h" -> {
                    Duration.of(str.toLong(), ChronoUnit.HOURS).toMillis()
                }
                else -> {
                    throw RuntimeException("xml分析时，将字符串转换成Duration：error")
                }
            }
        } else {
            throw RuntimeException("xml分析时，字符串转换成Duration：str == null")
        }
    }

    //将字符串转换成float64
    private fun parseFloat64(b: BasicProtoCache, s: String): Double {
        val tmp = b.basicMap[s]
        return if (tmp != null) {
            val i = (tmp.toDoubleOrNull()) ?: throw RuntimeException("$s fun parseFloat64 解析时错误，转换出错")
            i
        } else {
            throw RuntimeException("fun parseFloat64 解析时错误，map${s}不存在该单元")
        }
    }

    //将字符串转换成float32
    private fun parseFloat32(b: BasicProtoCache, s: String): Float {
        val tmp = b.basicMap[s]
        return if (tmp != null) {
            val i = (tmp.toFloatOrNull()) ?: throw RuntimeException("$s fun parseFloat32 解析时错误，转换出错")
            i
        } else {
            throw RuntimeException("fun parseFloat32 解析时错误，map${s}不存在该单元")
        }
    }

    private fun parse2Int(b: BasicProtoCache, s: String): List<Int> {
        val ii = LinkedList(asList(0, 0))
        val tmp = b.basicMap[s]
        if (tmp != null) {
            val ss = tmp.split(":")
            if (ss.size != 2) {
                throw RuntimeException("解析Basic.xml :: $s :: $tmp -> [2]int 错误:无法确定长度范围.")
            }

            // 最小长度
            val ia = (ss[0].toIntOrNull()) ?: throw RuntimeException("解析Basic.xml :: $s :: $tmp -> [2]int 错误  .")

            // 最大长度
            val ib = (ss[1].toIntOrNull()) ?: throw RuntimeException("解析Basic.xml :: $s :: $tmp-> [2]int 错误  .")

            ii[0] = ia
            ii[1] = ib

            return ii
        } else {
            throw RuntimeException("fun parse2Int 转换时，basicMap$s 不存在该单元")
        }
    }

    private fun parse2Int(b: BasicProtoCache, s: String, d: String): List<Int> {
        val ii = LinkedList(asList(0, 0))
        val tmp = b.basicMap[s]
        if (tmp != null) {
            val ss = tmp.split(d)
            if (ss.size != 2) {
                throw RuntimeException("解析Basic.xml :: $s :: $tmp -> [2]int 错误:无法确定长度范围.")
            }

            // 最小长度
            val ia = (ss[0].toIntOrNull()) ?: throw RuntimeException("解析Basic.xml :: $s :: $tmp -> [2]int 错误  .")

            // 最大长度
            val ib = (ss[1].toIntOrNull()) ?: throw RuntimeException("解析Basic.xml :: $s :: $tmp-> [2]int 错误  .")

            ii[0] = ia
            ii[1] = ib

            return ii

        } else {
            throw RuntimeException("fun parse2Int 转换时，basicMap$s 不存在该单元")
        }
    }

    // 获取下一次JJc每日奖励时间
    fun getNextJjcDayRewardTime(last: Long, now: Long): Long {
        val points = ArrayList<Long>()
        val lastTime = if (last == 0L) getNowTime() else last
        for (index in 0..2) {
            val step = index * 2
            val hour = this.arenaRewardTime[step]
            val min = this.arenaRewardTime[step + 1]
            points.add(getSpecifiedTime(lastTime, hour, min, 0))
        }

        if (now >= points[2]) {
            return points[0] + 24 * 60 * 60 * 1000
        }

        if (now >= points[1]) {
            return points[2]
        }

        if (now >= points[0]) {
            return points[1]
        } else {
            return points[0]
        }
    }
}

fun convertRateStr2Float(ss: String?, split: String?): Double {
    if (ss == null || split == null) {
        throw RuntimeException("fun convertRateStr2Float：输入的字符串是null")
    }
    val fs = LinkedList<Double>()
    for (s in ss.split(split)) {
        val f = (s.toDouble())
        fs.add(f)
    }

    return (fs[0] / fs[1])
}

fun selectKeyFromMap(intMap: Map<Int, Int>): Int {
    var totalRate = 0
    for ((_, rate) in intMap) {
        totalRate += rate
    }
    if (totalRate <= 0) {
        return 0
    }
    val dropRand = getRandInt(totalRate)
    var tempRate = 0
    for ((key, rate) in intMap) {
        if (dropRand <= tempRate + rate) {
            return key
        }
        tempRate += rate
    }
    return 0
}

//每个掉落包均随机
fun selectAllPropFromDrops(drops: List<Drop>, addRate: Int = 0): LinkedList<ResVo> {
    val dropMap = hashMapOf<Int, Int>()
    for (drop in drops) {
        if (getRandInt(10000) >= drop.percent * (10000 + addRate) / 10000) {
            continue
        }
        dropMap[drop.id] = (dropMap[drop.id] ?: 0) + drop.num
    }
    val props = LinkedList<ResVo>()
    for ((dropId, dropNum) in dropMap) {
        val dropProp = pcs.dropPropsProtoCache.dropPropsMap[dropId]
        if (dropProp == null) {
            continue
        }
        val prop = randomSelect(dropProp.dropMap)
        if (prop == null) {
            continue
        }

        val propsSubType = prop.id.toLong()
        val propsNum = (prop.num * dropNum).toLong()
        props.add(
            ResVo(RES_PROPS, propsSubType, propsNum)
        )
    }
    return props
}

//随机出一个掉落
fun selectOnePropFromDrops(drops: LinkedList<Drop>): LinkedList<ResVo> {
    val props = LinkedList<ResVo>()
    var totalRate = 0
    for (dropInfo in drops) {
        totalRate += dropInfo.percent
    }
    //commonfunc.debugAssert(totalRate > 0, "掉落包几率配置错误")

    val dropRand = getRandInt(totalRate)
    var tempRate = 0
    for (dropInfo in drops) {
        if (dropRand <= tempRate + dropInfo.percent) {
            val dropProto = pcs.dropBagCache.dropBagMap[dropInfo.id]
            for (i in 0..dropInfo.num) {
                if (dropProto != null) {
                    for (each in dropProto.dropMap) {
                        props.add(each)
                    }
                }
            }
            return props
        }
        tempRate += dropInfo.percent
    }
    return props
}

// 资源点刷新物品权值帮助Vo
data class ResProportionMapHelpVo(
    var minNum: Int, // 资源点刷新物品权值最小值
    var maxNum: Int // 资源点刷新物品权值最大值
)