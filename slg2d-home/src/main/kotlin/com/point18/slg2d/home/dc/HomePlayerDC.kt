package com.point18.slg2d.home.dc

import com.fasterxml.jackson.annotation.JsonIgnore
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.inDay
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.HOME_PLAYER_NAMED_QUERY
import com.point18.slg2d.common.homeentities.HomePlayerEntity
import com.point18.slg2d.home.AbstractDataContainer
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.forwardHeartDeal2World
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

open class HomePlayerDC : AbstractDataContainer<HomePlayerEntity>() {

    open lateinit var player: HomePlayer
        protected set

    override fun loadAllFromDB(playerId: Long, dao: CommonDao): HomePlayerEntity? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(HOME_PLAYER_NAMED_QUERY)
                .setLong("playerId", playerId)
                .listNoDup<HomePlayerEntity>()
            list.firstOrNull()
        }
        return data
    }

    override fun initImpl(data: HomePlayerEntity?, depDCRepo: DataContainerRepo) {
        if (data != null) {
            val playerWrap = wdb.recover(HomePlayer::class.java, data)

            player = playerWrap
        }
    }

    fun createPlayer(p: HomePlayer) {
        player = p
        wdb.save(p)
    }
}

class HomePlayer(
    var playerId: Long,
    var worldId: Long,

    var user: String,        // 账户名
    var pwd: String,        // 密码
    var osdkUserId: String,       // 用户的唯一标识
    var channelId: String,        // 用户来源 playerId	cps playerId，手游的分包ID，默认值为0。cps id在手游分包中是必须要发的数据
    var opId: Int,        // 玩家来自哪个运营商
    var createAccTime: Long,  // 注册时间)

    var areaNo: Int,                 // 所属地图区,实际上游戏服的AreaNo已经没有意义了.这个字段值会在初始化的时候读配置来填充,之后会在迁服的时候来被修改
    var name: String,  // 玩家角色名
    var decree: Int,                // 政令 通过 GetDecree() 获取  行动力
    var decreeLimit: Int,                // 政令上限
    var decreeTime: Long,  // 政令结算时间
    var power: Int,  // 势力
    var wood: Long,  // 木材
    var iron: Long,  // 铁
    var stone: Long,  // 石头
    var food: Long,  // 食物
    var gold: Long,  // 元宝
    var bindGold: Long,  // 绑定元宝
    var coin: Long,  // 铜币
    var casinoCoin: Long,  // 赌场币
    var silverCoin: Long,  // 银币
    var goldCoin: Long,  // 金币
    var honor: Long,  // 总荣誉
    var allianceCoin: Long,  // 联盟币
    var heroExpPool: Long,  // 英雄经验池
    var focusCastleId: Long,  // 主城RT
    var allianceId: Long,    // 所属同盟ID
    var allianceAt: Long,  // 加入联盟时间
    var alliancePosMap: HashMap<Int, AlliancePosInfo>,
    var allianceName: String,  // 联盟名称
    var allianceShortName: String,  // 联盟简称
    var flagColor: Int,           // 联盟旗帜的颜色
    var flagStyle: Int,           // 联盟旗帜的样式
    var flagEffect: Int,           // 联盟旗帜图案
    var allianceRnum: Int,           // R1 - R5 阶级
    var quitPunishTime: Int,  // 退盟惩罚结束时间
    var selfIntroduction: String,       // 自我介绍
    var lastEnterGameTime: Long,  // 最近一次上线时间
    var lastLeaveTime: Long,  // 最后一次离线时间
    var birthTime: Long,  // 玩家创建账号时间
    var rookieEndTime: Long,  // 新手期结束时间
    var chestFreePrizeTime: Long,  // 免费宝箱领取时间
    var chestFreeCnt: Int,                // 免费宝箱数量
    var chestKillRefTime: Long,  // 击杀宝箱时间
    var chestKillCnt: Int,                // 击杀宝箱击杀数量
    var chestKillPrize: Int,                // 是否已经领取击杀宝箱
    var isNotFirstChest: Int,                // 是否不是首次抽取宝箱
    var photoFreeTime: Int,  // 可免费换头像时间
    var photoProtoId: Int,       // 头像
    var photoFreeCount: Int,                // 初始剩余免费更换头像次数

    //聊天相关
    var worldTalkLimit: Int,                // 每天世界频道免费聊天次数
    var worldTalkLast: Long,        // 世界频道上次聊天时间
    var allianceTalkLast: Long,     // 联盟上次发言时间
    var boardcastLast: Long,        // 上次喇叭时间

    var guideStep: Int,  // 新手引导步骤ID
    var accType: Int,  // 角色类型，标识角色的分类属性，1:正常 2:测试 3:gm/福利 4:其  他
    var isSleep: Int,  // 玩家是否处于休眠状态：0-非休眠状态；1-休眠状态
    var firstPayTime: Long,  // 首次充值时间
    var lastPayTime: Long,  // 最后一次充值时间
    var banShuNotice: String,  // TODO 版署评审
    var jjcCoin: Int,  // 竞技币数量
    var houseCoin: Long,  // 家园币数量
    var waijiaoCount: Int,                // 今天外交次数
    var lastWaijiaoCount: Long,  // 上次外加时间
    var drapHeroInfoMap: HashMap<Int, Int>,               // 玩家召唤武将情况
    var isFirstJoinAlliance: Int,  // 玩家是否加入过帮派 0-否 1-是
    // 玩家的科技信息
    var researchInfoMap: HashMap<Int, ResearchVo>,               // 玩家科技升级情况
    var researchEffectInfoMap: HashMap<Int, Int>,                      // 玩家科技效果集合
    var buildingEffectInfoMap: HashMap<Int, Int>,                      // 玩家建筑效果集合
    var innerBuildingEffectInfoMap: HashMap<Int, Int>,                      // 玩家内城建筑效果集合
    var innerBuildingUnlockAreaMap: HashMap<Int, Int>,
    var allianceNickName: String,  // 联盟昵称
    var intHeroNum: Int,  // 内政英雄数量
    var diamondShopInfoMap: HashMap<Int, HashMap<Int, DiamondShopVo>>,                    // 玩家钻石商城限购物品购买情况
    var allianceResearchNum: Int,                // 联盟普通捐献次数
    var lastAllianceResearchTime: Long,  // 上次联盟普通捐献次数回复时间
    var weekAllianceResearchReward: Int,                // 联盟普通捐献获取到的贡献值
    var allianceResearchRewardTime: Long,  // 上次普通捐献时间
    var timeBoxNumMap: HashMap<Int, TimeBoxInfo>,                 // 时光之盒研究槽位
    var talentPointMap: HashMap<Int, Int>,                    //当前天赋点
    var unlockedTalentMap: HashMap<Int, Int>,                     //已解锁天赋 talentId-level
    var talentEffectInfoMap: HashMap<Int, Int>,                  //天赋效果集合
    var kingLv: Int,  // 君主等级
    var kingExp: Int,  // 君主经验
    var mainHeroId: Long,    // 领主ID 这个值不可能是0
    var allianceGiftGetMap: HashMap<Int, Int>,                            // 本日已经获得过的联盟礼物档次
    var lastAllianceGiftGetTime: Long,         // 上次获得过的联盟礼物档次的时间
    var allianceGiftProtoId: Int,                       // 剩余可领取的宝箱模版ID
    var allianceGiftNum: Int,                       // 剩余可领取的宝箱数量
    var openAllianceSendGift: Int,  //是否开启联盟赠礼功能 0-未开启 1-开启
    var unitTaskId: Int,  // 当前已完成的的最新章节任务ID
    var prisonKillNum: Int,  // 一共杀过多少个玩家
    var kingEquipBagNum: Int,                                              // 君主装备背包容量
    var lastGetOnlineTime: Long,  // 上次领取在线礼包任务时间
    var todayOnlineNum: Int,                                              // 本日领取次数
    var nextOnlineReward: String,                                   // 当前档奖励
    var nextOnlineRewardOverTime: Long,  // 当前档时间
    var bigOnlineReward: String,                                   // 本日大奖奖励
    // 联盟总动员字段
    var allianceCompetitionId: Long,  // 本次联盟总动员为哪个帮派效力
    var allianceCompetitionMyScore: Int,  // 本次联盟总动员获得的积分
    var allianceCompetitionRankLv: Int,              // 本次联盟总动员效力帮派的段位
    var allianceCompetitionTicket: Int,              // 当前是否拥有联盟总动员门票 0-无 1-有
    var allianceCompetitionGetTaskNum: Int,              // 可领任务次数
    var allianceCompetitionBuyTaskNum: Int,              // 已购买任务次数
    var allianceCompetitionRewardMap: HashMap<Int, AllianceCompetitionRewardVo>,                    // key : 联盟阶段ID
    var allianceHelpTodayGet: Int, // 玩家今天获得的联盟帮助奖励联盟币数量
    var lastAllianceHelpTime: Long, // 上次联盟帮助时间

    // 推图体力
    var buyStrengthNum: Int, // 今日购买体力次数
    var lastBuyStrengthTime: Long, // 上次购买体力的时间

    // 君主装备养成
    var nowUseKingEquipPlan: Int, // 当前正在使用的君主预设方案

    var maxMark: Int, // 收藏上限
    var joinActivitys: LinkedList<JoinActivity>,

    //限时商店 购买上限
    var buyLimitInfo: HashMap<Int, Int>,

    var loginTime: Long,  // 登陆时间
    var wallFireEndTime: Long,  //字段灭火时间
    var maxLvPrisonBuffEndTime: Long, // 最高监禁等级领主buff结束时间
    var castleLv: Int, //主城等级
    var focusChatRoomId: Long,  // 正在哪一个聊天室聊天
    var focusChatPlayerId: Long,  //  正在哪一个玩家聊天
    var chatRoomList: LinkedList<MyChat>, // 加入的聊天室和对应的最后阅读时间
    var chatPlayerList: LinkedList<MyChat>, // 加入的私聊和对应的最后阅读时间
    var openCasinoTime: Long, //赌场开启
    var blackPlayers: LinkedList<Long>,    // 被xxxx拉黑
    var isFirstEnterGame: Int,
    var haveFinishGuideLine: LinkedList<Int>, // 被动触发引导完成列表
    var inMoveServer: Int,  // 迁服状态 0-不在 1-迁服中
    var monthCards: HashMap<Int, MonthCard>, // 月卡信息
    var joinAllianceState: Int, // 入帮状态  0-空闲 1-正在尝试加入联盟中
    var allianceOccupyInfo: java.util.HashMap<Long, java.util.HashMap<Int, Int>> // 联盟占领世界情况  <世界Id, <奇观模板Id, 1>>

) : EntityWrapper<HomePlayerEntity> {
    constructor() : this(
        0, 0, "", "", "", "", 0,
        0, 0, "", 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, hashMapOf(),
        "", "", 0, 0, 0, 0, 0,
        "", 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, "", 0,
        0, 0, 0, hashMapOf(), 0, hashMapOf(), hashMapOf(), hashMapOf(), hashMapOf(), hashMapOf(),
        "", 0, hashMapOf(), 0, 0,
        0, 0, hashMapOf(), hashMapOf(), hashMapOf(), hashMapOf(),
        0, 0,
        0, hashMapOf(), 0, 0,
        0, 0,
        0, 0, 0, 0,
        0, "", 0, "", 0,
        0, 0, 0, 0, 0, hashMapOf(), 0, 0,
        0, 0, 0, 0,
        LinkedList(), hashMapOf(), 0, 0, 0, 0, 0,
        0, LinkedList(), LinkedList(), 0, LinkedList(), FIRST_ENTER_GAME, LinkedList(), 0, hashMapOf(), 0,
        hashMapOf()
    )

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = playerId

    override fun toEntity(): HomePlayerEntity {
        return HomePlayerEntity(
            playerId,
            worldId,
            user,
            pwd,
            osdkUserId,
            channelId,
            opId,
            createAccTime,
            areaNo,
            name,
            decree,
            decreeLimit,
            decreeTime,
            power,
            wood,
            iron,
            stone,
            food,
            gold,
            bindGold,
            coin,
            casinoCoin,
            silverCoin,
            goldCoin,
            honor,
            allianceCoin,
            heroExpPool,
            focusCastleId,
            allianceId,
            allianceAt,
            toJson(alliancePosMap),
            allianceName,
            allianceShortName,
            flagColor,
            flagStyle,
            flagEffect,
            allianceRnum,
            quitPunishTime,
            selfIntroduction,
            lastEnterGameTime,
            lastLeaveTime,
            birthTime,
            rookieEndTime,
            chestFreePrizeTime,
            chestFreeCnt,
            chestKillRefTime,
            chestKillCnt,
            chestKillPrize,
            isNotFirstChest,
            photoFreeTime,
            photoProtoId,
            photoFreeCount,
            worldTalkLimit,
            worldTalkLast,
            allianceTalkLast,
            boardcastLast,
            guideStep,
            accType,
            isSleep,
            firstPayTime,
            lastPayTime,
            banShuNotice,
            jjcCoin,
            houseCoin,
            waijiaoCount,
            lastWaijiaoCount,
            toJson(drapHeroInfoMap),
            isFirstJoinAlliance,
            toJson(researchInfoMap),
            toJson(researchEffectInfoMap),
            toJson(buildingEffectInfoMap),
            toJson(innerBuildingEffectInfoMap),
            toJson(innerBuildingUnlockAreaMap),
            allianceNickName,
            intHeroNum,
            toJson(diamondShopInfoMap),
            allianceResearchNum,
            lastAllianceResearchTime,
            weekAllianceResearchReward,
            allianceResearchRewardTime,
            toJson(timeBoxNumMap),
            toJson(talentPointMap),
            toJson(unlockedTalentMap),
            toJson(talentEffectInfoMap),
            kingLv,
            kingExp,
            mainHeroId,
            toJson(allianceGiftGetMap),
            lastAllianceGiftGetTime,
            allianceGiftProtoId,
            allianceGiftNum,
            openAllianceSendGift,
            unitTaskId,
            prisonKillNum,
            kingEquipBagNum,
            lastGetOnlineTime,
            todayOnlineNum,
            nextOnlineReward,
            nextOnlineRewardOverTime,
            bigOnlineReward,
            allianceCompetitionId,
            allianceCompetitionMyScore,
            allianceCompetitionRankLv,
            allianceCompetitionTicket,
            allianceCompetitionGetTaskNum,
            allianceCompetitionBuyTaskNum,
            toJson(allianceCompetitionRewardMap),
            allianceHelpTodayGet,
            lastAllianceHelpTime,
            buyStrengthNum, // 今日购买体力次数
            lastBuyStrengthTime, // 上次购买体力的时间
            nowUseKingEquipPlan,
            maxMark,
            toJson(joinActivitys),
            toJson(buyLimitInfo),
            loginTime,
            wallFireEndTime,
            maxLvPrisonBuffEndTime,
            castleLv,
            focusChatRoomId,
            focusChatPlayerId,
            toJson(chatRoomList),
            openCasinoTime,
            toJson(blackPlayers),
            isFirstEnterGame,
            toJson(haveFinishGuideLine),
            inMoveServer,
            toJson(monthCards),
            joinAllianceState,
            toJson(allianceOccupyInfo)
        )
    }

    override fun wrap(entity: HomePlayerEntity) {
        playerId = entity.playerId
        worldId = entity.worldId
        user = entity.user
        pwd = entity.pwd
        osdkUserId = entity.osdkUserId
        channelId = entity.channelId
        opId = entity.opId
        createAccTime = entity.createAccTime
        areaNo = entity.areaNo
        name = entity.name
        decree = entity.decree
        decreeLimit = entity.decreeLimit
        decreeTime = entity.decreeTime
        power = entity.power
        wood = entity.wood
        iron = entity.iron
        stone = entity.stone
        food = entity.food
        gold = entity.gold
        bindGold = entity.bindGold
        coin = entity.coin
        casinoCoin = entity.casinoCoin
        silverCoin = entity.silverCoin
        goldCoin = entity.goldCoin
        honor = entity.honor
        allianceCoin = entity.allianceCoin
        heroExpPool = entity.heroExpPool
        focusCastleId = entity.focusCastleId
        allianceId = entity.allianceId
        allianceAt = entity.allianceAt
        alliancePosMap = toObj(entity.alliancePos)
        allianceName = entity.allianceName
        allianceShortName = entity.allianceShortName
        flagColor = entity.flagColor
        flagStyle = entity.flagStyle
        flagEffect = entity.flagEffect
        allianceRnum = entity.allianceRnum
        quitPunishTime = entity.quitPunishTime
        selfIntroduction = entity.selfIntroduction
        lastEnterGameTime = entity.lastEnterGameTime
        lastLeaveTime = entity.lastLeaveTime
        birthTime = entity.birthTime
        rookieEndTime = entity.rookieEndTime
        chestFreePrizeTime = entity.chestFreePrizeTime
        chestFreeCnt = entity.chestFreeCnt
        chestKillRefTime = entity.chestKillRefTime
        chestKillCnt = entity.chestKillCnt
        chestKillPrize = entity.chestKillPrize
        isNotFirstChest = entity.isNotFirstChest
        photoFreeTime = entity.photoFreeTime
        photoProtoId = entity.photoProtoId
        photoFreeCount = entity.photoFreeCount
        worldTalkLimit = entity.worldTalkLimit
        worldTalkLast = entity.worldTalkLast
        allianceTalkLast = entity.allianceTalkLast
        boardcastLast = entity.boardcastLast
        guideStep = entity.guideStep
        accType = entity.accType
        isSleep = entity.isSleep
        firstPayTime = entity.firstPayTime
        lastPayTime = entity.lastPayTime
        banShuNotice = entity.banShuNotice
        jjcCoin = entity.jjcCoin
        houseCoin = entity.houseCoin
        waijiaoCount = entity.waijiaoCount
        lastWaijiaoCount = entity.lastWaijiaoCount
        drapHeroInfoMap = toObj(entity.drapHeroInfo)
        isFirstJoinAlliance = entity.isFirstJoinAlliance
        researchInfoMap = toObj(entity.researchInfo)
        researchEffectInfoMap = toObj(entity.researchEffectInfo)
        buildingEffectInfoMap = toObj(entity.buildingEffectInfo)
        innerBuildingEffectInfoMap = toObj(entity.innerBuildingEffectInfo)
        innerBuildingUnlockAreaMap = toObj(entity.innerBuildingUnlockArea)
        allianceNickName = entity.allianceNickName
        intHeroNum = entity.intHeroNum
        diamondShopInfoMap = toObj(entity.diamondShopInfo)
        allianceResearchNum = entity.allianceResearchNum
        lastAllianceResearchTime = entity.lastAllianceResearchTime
        weekAllianceResearchReward = entity.weekAllianceResearchReward
        allianceResearchRewardTime = entity.allianceResearchRewardTime
        timeBoxNumMap = toObj(entity.timeBoxNum)
        talentPointMap = toObj(entity.talentPoint)
        unlockedTalentMap = toObj(entity.unlockedTalent)
        talentEffectInfoMap = toObj(entity.talentEffectInfo)
        kingLv = entity.kingLv
        kingExp = entity.kingExp
        mainHeroId = entity.mainHeroId
        allianceGiftGetMap = toObj(entity.allianceGiftGet)
        lastAllianceGiftGetTime = entity.lastAllianceGiftGetTime
        allianceGiftProtoId = entity.allianceGiftProtoId
        allianceGiftNum = entity.allianceGiftNum
        openAllianceSendGift = entity.openAllianceSendGift
        unitTaskId = entity.unitTaskId
        prisonKillNum = entity.prisonKillNum
        kingEquipBagNum = entity.kingEquipBagNum
        lastGetOnlineTime = entity.lastGetOnlineTime
        todayOnlineNum = entity.todayOnlineNum
        nextOnlineReward = entity.nextOnlineReward
        nextOnlineRewardOverTime = entity.nextOnlineRewardOverTime
        bigOnlineReward = entity.bigOnlineReward
        allianceCompetitionId = entity.allianceCompetitionId
        allianceCompetitionMyScore = entity.allianceCompetitionMyScore
        allianceCompetitionRankLv = entity.allianceCompetitionRankLv
        allianceCompetitionTicket = entity.allianceCompetitionTicket
        allianceCompetitionGetTaskNum = entity.allianceCompetitionGetTaskNum
        allianceCompetitionBuyTaskNum = entity.allianceCompetitionBuyTaskNum
        allianceCompetitionRewardMap = toObj(entity.allianceCompetitionReward)
        allianceHelpTodayGet = entity.allianceHelpTodayGet
        lastAllianceHelpTime = entity.lastAllianceHelpTime
        buyStrengthNum = entity.buyStrengthNum
        lastBuyStrengthTime = entity.lastBuyStrengthTime
        nowUseKingEquipPlan = entity.nowUseKingEquipPlan
        maxMark = entity.maxMark
        joinActivitys = toObj(entity.joinActivitys)
        buyLimitInfo = toObj(entity.buyLimitInfo)
        loginTime = entity.loginTime
        wallFireEndTime = entity.wallFireEndTime
        maxLvPrisonBuffEndTime = entity.maxLvPrisonBuffEndTime
        castleLv = entity.castleLv
        focusChatRoomId = entity.focusChatRoomId
        focusChatPlayerId = entity.focusChatPlayerId
        chatRoomList = toObj(entity.chatRoomList)
        openCasinoTime = entity.openCasinoTime
        blackPlayers = toObj(entity.blackPlayers)
        isFirstEnterGame = entity.isFirstEnterGame
        haveFinishGuideLine = toObj(entity.haveFinishGuideLine)
        inMoveServer = entity.inMoveServer
        monthCards = toObj(entity.monthCards)
        joinAllianceState = entity.joinAllianceState
        allianceOccupyInfo = toObj(entity.allianceOccupyInfo)
    }

    // 修改玩家的科技信息
    fun putResearchInfoMap(key: Int, vo: ResearchVo) {
        this.researchInfoMap[key] = vo
    }

    fun getAlliancePosMapValue(pos: Int): Boolean {
        return this.alliancePosMap[pos] != null
    }

    //修改天赋点
    fun putTalentPointMap(pointMap: Map<Int, Int>) {
        for (eachEntry in pointMap)
            this.talentPointMap[eachEntry.key] = eachEntry.value
    }

    // 修改玩家的时光之盒研究槽位情况
    fun putTimeBoxNumMap(key: Int, vo: TimeBoxInfo) {
        this.timeBoxNumMap[key] = vo
    }

    // 修改玩家的科技效果信息
    fun putResearchEffectInfoMap(key: Int, newValue: Int) {
        this.researchEffectInfoMap[key] = newValue
    }

    // 修改玩家的内城建筑效果信息 ------>>>>> 这个方法只有一个gamecommon地方调用  游戏逻辑同意掉gamecommon 的那个壳,那个方法是会同步数据到其他服务器去的
    fun putInnerBuildingEffectInfoMap(updateMap: Map<Int, Int>) {
        for ((key, newValue) in updateMap) {
            this.innerBuildingEffectInfoMap[key] = newValue
        }
    }

    // 清空帮派职位
    fun clearWrapPosition() {
        val posMap = hashMapOf<Int, AlliancePosInfo>()
        this.alliancePosMap = posMap
    }

    // 初始化帮派初始职位
    fun resetWrapPosition() {
        this.alliancePosMap = hashMapOf()
        val alliancePosInfo = AlliancePosInfo(getNowTime())
        this.alliancePosMap[ALLIANCE_POSITION_MEMBER] = alliancePosInfo
    }

    fun getMaxAlliancePos(): Int {
        var max = 0
        for ((posId, _) in this.alliancePosMap) {
            if (max == 0) {
                max = posId
            } else {
                if (posId < max) {
                    max = posId
                }
            }
        }
        return max
    }

    fun researchUpdate(
        session: PlayerActor,
        researchId: Int,
        researchVo: ResearchVo,
        overTime: Long,
        startTime: Long
    ) {
        forwardHeartDeal2World(session, DeleteHeart, ResearchLvUp, researchId.toLong(), overTime)

        researchVo.researchOverTime = overTime
        researchVo.researchStartTime = startTime

        if (overTime != 0L && startTime != 0L) {
            forwardHeartDeal2World(session, CreateHeart, ResearchLvUp, researchId.toLong(), overTime)
        }
    }

    // 查找当前正在使用的研发队列数量
    fun findResearchNum(): Int {
        var num = 0
        for ((_, value) in this.researchInfoMap) {
            if (value.researchOverTime != com.point18.slg2d.common.commonfunc.zeroTime.time) {
                num++
            }
        }
        return num
    }

    // 判断玩家是否有这个职位
    fun playerIsHavePos(pos: Int): Boolean {
        return getAlliancePosMapValue(pos)
    }
}

data class JoinActivity(
    var activityProtoId: Int, // 参与过的活动模版ID
    var overTime: Long,  // 活动结束时间
    var myScore: Long, // 我的积分
    var myRank: Int, // 我的排名
    var rankId: Long // 对应的排行榜记录行ID
)

data class AlliancePosInfo(
    var getPosTime: Long //获得职位的时间
)

// 科技信息
data class ResearchVo(
    var pqIndex: Int, //`json:"-"` // 队列索引
    var researchLv: Int,        // 科技等级
    var researchStartTime: Long,   // 科技开始升级的时间
    var researchOverTime: Long,  // 科技升级完成的时间
    var helperIds: HashMap<Long, Long>, // 帮忙加速过的玩家
    var helpId: Long         // 对应的帮派帮助表里的ID
)

data class DiamondShopVo(
    var buyNum: Int,       // 已买数量
    var lastBuyTime: Int // 上次购买时间
)

data class TimeBoxInfo(
    var relicBoxId: Int,             //掉落包Id
    var baseRate: Int,             //掉落倍率
    var studyTime: Long,           //研究时间
    var timeBoxTimeOver: Long  //时光之盒研究到点时间
)

// 联盟总动员领奖信息
data class AllianceCompetitionRewardVo(
    var isGet: Int,          // 本阶段是否已经领过了 0-否 1-是
    var reward: HashMap<Int, String> // 具体奖励 key : 位置 value : 奖励格式
)

class MyChat(
    var chatRoomId: Long,                  // 聊天室id或者好友id
    var lastReadTime: Long                  // 上次读取消息时间
) : Serializable

class MonthCard(
    var lastRecvTime: Long, // 上一次领取时间
    var overdueTime: Long // 过期时间
) {
    @JsonIgnore
    fun isActive(): Boolean {
        return this.overdueTime > getNowTime()
    }

    @JsonIgnore
    fun isRecved(): Boolean {
        return inDay(this.lastRecvTime)
    }

    fun recordRecved() {
        this.lastRecvTime = getNowTime()
    }
}