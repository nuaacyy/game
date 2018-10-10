package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.*
import com.point18.slg2d.common.constg.ALLIANCE_POSITION_BOSS
import com.point18.slg2d.common.constg.ALLIANCE_POSITION_MEMBER
import com.point18.slg2d.common.constg.JJC_RANK_CAN_BE_CHALLENGE
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.mcache.TwoKeyIndex
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.worldentities.PLAYER_NAMED_QUERY
import com.point18.slg2d.common.worldentities.PlayerEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

class Player(
    var dbId: Long, // 毫无意义的ID 只作为存储数据库的主键 绝对不允许业务逻辑使用
    var worldId: Long, // 所属区唯一ID
    var id: Long,
    var user: String, // 登录用户名
    var areaNo: Int,     // 所属地图区号
    var name: String,  // 玩家角色名
    var decree: Int,                // 政令 通过 GetDecree() 获取  行动力
    var decreeTime: Long,  // 政令结算时间
    var wood: Long,  // 木材
    var iron: Long,  // 铁
    var stone: Long,  // 石头
    var food: Long,  // 食物
    var gold: Long,  // 元宝
    var bindGold: Long,  // 绑定元宝
    var coin: Long,  // 铜币
    var honor: Long,  // 总荣誉
    var allianceCoin: Long,  // 联盟币
    var heroExpPool: Long,  // 英雄经验池
    var focusCastleId: Long,  // 关注城池ID
    var allianceId: Long,    // 所属同盟ID
    var allianceAt: Long,  // 加入联盟时间
    var alliancePosMap: HashMap<Int, AlliancePosInfo>, //map[: Int]*AlliancePosInfo
    var allianceName: String,  // 联盟名称
    var allianceShortName: String,  // 联盟简称
    var flagColor: Int,           // 联盟旗帜的颜色
    var flagStyle: Int,           // 联盟旗帜的样式
    var flagEffect: Int,           // 联盟旗帜图案
    var allianceRNum: Int,           // R1 - R5 阶级
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
    var guideStep: Int,  // 新手引导步骤ID
    var accType: Int,  // 角色类型，标识角色的分类属性，1:正常 2:测试 3:gm/福利 4:其  他
    var isSleep: Int,  // 玩家是否处于休眠状态：0-非休眠状态；1-休眠状态
    var firstPayTime: Long,  // 首次充值时间
    var lastPayTime: Long,  // 最后一次充值时间
    var banShuNotice: String,  // TODO 版署评审
    var houseCoin: Long,  // 家园币数量
    var waijiaoCount: Int,                // 今天外交次数
    var lastWaijiaoCount: Long,  // 上次外加时间
    var jjcRank: Int,              // 玩家JJC名次
    var jjcRankGold: Long,  // 竞技场可以领取的累计钻石
    var jjcRankJJCoin: Long,  // 竞技场可以领取的累计钻石
    var isFirstJoinAlliance: Int,  // 玩家是否加入过帮派 0-否 1-是
    var allianceNickName: String,  // 联盟昵称
    var intHeroNum: Int,  // 内政英雄数量
    var diamondShopInfoMap: HashMap<Int, HashMap<Int, DiamondShopVo>>,//                     // 玩家钻石商城限购物品购买情况
    var allianceResearchNum: Int,                // 联盟普通捐献次数
    var lastAllianceResearchTime: Long,  // 上次联盟普通捐献次数回复时间
    var weekAllianceResearchReward: Int,                // 联盟普通捐献获取到的贡献值
    var allianceResearchRewardTime: Long,  // 上次普通捐献时间
    var kingLv: Int,  // 君主等级
    var kingExp: Int,  // 君主经验
    var mainHeroId: Long,    // 领主ID 这个值不可能是0
    var allianceGiftGetMap: HashMap<Int, Int>,//map[:Int]:Int                               // 本日已经获得过的联盟礼物档次
    var lastAllianceGiftGetTime: Long,         // 上次获得过的联盟礼物档次的时间
    var allianceGiftProtoId: Int,                       // 剩余可领取的宝箱模版ID
    var allianceGiftNum: Int,                       // 剩余可领取的宝箱数量
    var openAllianceSendGift: Int,  //是否开启联盟赠礼功能 0-未开启 1-开启
    var unitTaskId: Int,  // 当前已完成的的最新章节任务ID
    var prisonKillNum: Int,  // 一共杀过多少个玩家
    //魔物功能
    var autoHunter: Int,//自动猎杀
    var autoUseEnergy: Int,//字段使用行动力

    // 推图体力
    var strength: Int, // 当前体力
    var lastStrengthTime: Long, // 当前的体力的上次变化时间

    var joinActivityList: LinkedList<JoinActivity>,

    var accountId: Long,  // 关联的账号ID
    var loginTime: Long,  // 登陆时间
    var wallFireEndTime: Long,
    var maxLvPrisonBuffEndTime: Long, // 最高监禁等级领主buff结束时间
    var joinChatRooms: LinkedList<Long>, // 加入的聊天室id
    var inMoveServer: Int,  // 迁服状态 0-不在 1-迁服中
    var newPlayerActivity: Int,  // 新手挑战状态 0-新手挑战已完成 1-还未完成新手挑战
    var joinAllianceReqs: HashMap<Long, Int>, // 该玩家申请过的联盟ID列表
    var allianceOccupyInfo: HashMap<Long, HashMap<Int, Int>> // 联盟占领世界情况  <世界Id, <奇观模板Id, 1>>

) : EntityWrapper<PlayerEntity> {
    constructor() : this(
        0, 0, 0, "", 0, "", 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, hashMapOf(), "", "", 0, 0, 0, 0, 0,
        "", 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, "", 0, 0, 0, 0, 0,
        0, 0, "", 0, hashMapOf(),
        0, 0, 0,
        0,
        0, 0, 0, hashMapOf(), 0,
        0,
        0, 0, 0, 0, 0, 0, 0, 0,
        LinkedList(), 0, 0, 0, 0, LinkedList(), 0, 0, hashMapOf(),
        hashMapOf()
    )

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): PlayerEntity {
        return PlayerEntity(
            worldId,
            dbId,
            id,
            user,
            areaNo,
            name,
            decree,
            decreeTime,
            wood,
            iron,
            stone,
            food,
            gold,
            bindGold,
            coin,
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
            allianceRNum,
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
            guideStep,
            accType,
            isSleep,
            firstPayTime,
            lastPayTime,
            banShuNotice,
            houseCoin,
            waijiaoCount,
            lastWaijiaoCount,
            jjcRank,
            jjcRankGold,
            jjcRankJJCoin,
            isFirstJoinAlliance,
            allianceNickName,
            intHeroNum,
            toJson(diamondShopInfoMap),
            allianceResearchNum,
            lastAllianceResearchTime,
            weekAllianceResearchReward,
            allianceResearchRewardTime,
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
            autoHunter,
            autoUseEnergy,
            strength,
            lastStrengthTime,
            toJson(joinActivityList),
            accountId,
            loginTime,
            wallFireEndTime,
            maxLvPrisonBuffEndTime,
            toJson(joinChatRooms),
            inMoveServer,
            newPlayerActivity,
            toJson(joinAllianceReqs),
            toJson(allianceOccupyInfo)
        )
    }

    override fun wrap(entity: PlayerEntity) {
        dbId = entity.dbId
        worldId = entity.worldId
        id = entity.id
        user = entity.user
        areaNo = entity.areaNo
        name = entity.name
        decree = entity.decree
        decreeTime = entity.decreeTime
        wood = entity.wood
        iron = entity.iron
        stone = entity.stone
        food = entity.food
        gold = entity.gold
        bindGold = entity.bindGold
        coin = entity.coin
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
        allianceRNum = entity.allianceRnum
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
        guideStep = entity.guideStep
        accType = entity.accType
        isSleep = entity.isSleep
        firstPayTime = entity.firstPayTime
        lastPayTime = entity.lastPayTime
        banShuNotice = entity.banShuNotice
        houseCoin = entity.houseCoin
        waijiaoCount = entity.waijiaoCount
        lastWaijiaoCount = entity.lastWaijiaoCount
        jjcRank = entity.jjcRank
        jjcRankGold = entity.jjcRankGold
        jjcRankJJCoin = entity.jjcRankJJCoin
        isFirstJoinAlliance = entity.isFirstJoinAlliance
        allianceNickName = entity.allianceNickName
        intHeroNum = entity.intHeroNum
        diamondShopInfoMap = toObj(entity.diamondShopInfo)
        allianceResearchNum = entity.allianceResearchNum
        lastAllianceResearchTime = entity.lastAllianceResearchTime
        weekAllianceResearchReward = entity.weekAllianceResearchReward
        allianceResearchRewardTime = entity.allianceResearchRewardTime
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
        autoHunter = entity.autoHunter
        autoUseEnergy = entity.autoUseEnergy
        strength = entity.strength
        lastStrengthTime = entity.lastStrengthTime
        joinActivityList = toObj(entity.joinActivitys)
        accountId = entity.accountId
        loginTime = entity.loginTime
        wallFireEndTime = entity.wallFireEndTime
        maxLvPrisonBuffEndTime = entity.maxLvPrisonBuffEndTime
        joinChatRooms = toObj(entity.joinChatRooms)
        inMoveServer = entity.inMoveServer
        newPlayerActivity = entity.newPlayerActivity
        joinAllianceReqs = toObj(entity.joinAllianceReqs)
        allianceOccupyInfo = toObj(entity.allianceOccupyInfo)
    }

    fun getAlliancePosMapValue(pos: Int): Boolean {
        return this.alliancePosMap[pos] != null
    }

    //修改本日已经获得过的联盟礼物档次集合
    fun putAllianceGiftGetMap(effectMap: HashMap<Int, Int>) {
        this.allianceGiftGetMap = effectMap
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

    // 新增帮派职位
    fun setWrapPosition(pos: Int) {
        val alliancePosInfo = AlliancePosInfo(getNowTime())
        this.alliancePosMap[pos] = alliancePosInfo
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
}

data class JoinActivity(
    var activityProtoId: Int,// 参与过的活动模版ID
    var overTime: Long,  // 活动结束时间
    var myScore: Long, // 我的积分
    var myRank: Int, // 我的排名
    var rankId: Long // 对应的排行榜记录行ID
)

data class AlliancePosInfo(
    var getPosTime: Long //获得职位的时间
)

data class DiamondShopVo(
    var buyNum: Int,       // 已买数量
    var lastBuyTime: Int // 上次购买时间
)

data class TimeBoxInfo(
    var dropPropId: Int,//     `json:"drop_prop_id"`       //掉落包Id
    var baseRate: Int,//     `json:"base_rate"`          //掉落倍率
    var timeRate: Int,//     `json:"time_rate"`          //时间倍率
    var studyTime: Long,//   `json:"study_time"`         //研究时间
    var timeBoxTimeOver: Long //`json:"time_box_time_over"` //时光之盒研究到点时间
)

class CachePlayer(areaCache: AreaCache, val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val playerMap = OneKeyIndex<Long, Player> { it.id } // 玩家缓存
    private val playerAccIdMap = OneKeyIndex<Long, Player> { it.accountId } // 以账号ID为key的玩家缓存
    private val playerRankMap = OneKeyIndex<Int, Player> { it.jjcRank } // 以竞技场排名为key的玩家缓存
    private val playerNameMap = hashMapOf<String, Player>()// 以角色名字为key的玩家索引
    private val playerUserMap = hashMapOf<String, Player>()// 以角色用户名为key的玩家索引
    private val alliancePlayerMap =
        TwoKeyIndex<Long, Long, Player>({ it.allianceId }, { it.id }, { k, _ -> k == 0L })// 联盟中的所有成员[联盟ID:玩家ID]

    private val player4MaxPrisonBuffTime = PriorityQueue<Player> { a, b ->
        when {
            a.maxLvPrisonBuffEndTime > b.maxLvPrisonBuffEndTime -> 1
            a.maxLvPrisonBuffEndTime < b.maxLvPrisonBuffEndTime -> -1
            else -> 0
        }
    }

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.playerEntities =
                    session.getNamedQuery(PLAYER_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.playerEntities.forEach { entity ->
            try {
                val player = db.wdb.recover(entity) { Player() }

                cacheSinglePlayer(player)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    // 根据ID获取玩家
    fun findPlayerEntityById(id: Long): PlayerEntity? {
        if (id == 0L) {
            return null
        }
        val info = playerMap.findByKey(id)
        if (info == null) {
            return null
        } else {
            return info.toEntity()
        }
    }

    fun createPlayerByMoveServer(b: PlayerEntity) {
        val player = Player()
        player.wrap(b)
        val dbId = wpm.generateObjIdNew(areaCache)
        player.worldId = worldId
        player.dbId = dbId

        val myAreaConfig = processConfig.findSpecAreaConfig(worldId)
        if (myAreaConfig != null) {
            player.areaNo = myAreaConfig.areaNo
        }


        // todo 在这边需要维护一些重置数据,jjcRank就是这类数据,应该不止这么一个
        val jjcRank =
            JJC_RANK_CAN_BE_CHALLENGE + areaCache.playerCache.findPlayerNum() + 1
        player.jjcRank = jjcRank

        // 初始化玩家的竞技场数据
        areaCache.jjcCache.createJjc(player.id, jjcRank)

        insert(areaCache, player)

        // 存入缓存
        cacheSinglePlayer(player)

        // 处理buff 的优先级队列
        if (player.maxLvPrisonBuffEndTime != 0L) {
            player4MaxPrisonBuffTime.add(player)
        }
    }

    // 暂时移除某个玩家的优先级队列数据,如果迁服失败再重新添回来
    fun stopPlayerForMoveServer(playerId: Long) {
        val player = findPlayerById(playerId)
        if (player == null) {
            return
        }
        val del = findPlayerById(playerId)
        if (del != null) {
            // 处理buff 的优先级队列
            if (player.maxLvPrisonBuffEndTime != 0L) {
                player4MaxPrisonBuffTime.remove(player)
            }
        }
    }

    // 迁服失败,把数据重新添加到优先级队列
    fun revivePlayerForMoveServer(playerId: Long) {
        val player = findPlayerById(playerId)
        if (player == null) {
            return
        }
        val del = findPlayerById(playerId)
        if (del != null) {
            // 处理buff 的优先级队列
            if (player.maxLvPrisonBuffEndTime != 0L) {
                player4MaxPrisonBuffTime.add(player)
            }
        }
    }

    // 移除某个玩家的所有数据
    fun clearPlayerForMoveServer(playerId: Long) {
        val del = findPlayerById(playerId)
        if (del != null) {
            delete(areaCache, del)

            // 从缓存中删除
            cacheRemovePlayer(del)
        }
    }

    private fun cacheSinglePlayer(player: Player) {
        playerMap.insertByKey(player)
        playerAccIdMap.insertByKey(player)
        playerRankMap.insertByKey(player)
        playerNameMap[player.name] = player
        playerUserMap[player.user] = player
        if (player.allianceId != 0L) {
            alliancePlayerMap.insertByKey(player)
        }
    }

    private fun cacheRemovePlayer(player: Player) {
        playerMap.deleteByKey(player)
        playerAccIdMap.deleteByKey(player)
        playerRankMap.deleteByKey(player)
        playerNameMap.remove(player.name)
        playerUserMap.remove(player.user)
        if (player.allianceId != 0L) {
            alliancePlayerMap.deleteByKey(player)
        }
    }

    // 玩家的JJC排名发生了变化
    fun updateJjcRank(player: Player, newRank: Int) {
        playerRankMap.updateByKey(newRank, player) {
            player.jjcRank = newRank
        }
    }

    data class CreatePlayerRst(
        val player: Player?,
        var rst: Boolean
    )

    fun createPlayer(player: Player): CreatePlayerRst {
        val areaOnly = areaCache.areaOnlyCache.findAreaOnly()
        val nowNum = areaOnly.nowPlayerNum
        val areaId = areaCache.areaConfig.areaId

        val diff = 6 - intLen(nowNum)
        if (diff <= 0) {
            normalLog.error("创建角色失败--当前数量：${nowNum}，报错值为：$diff")
            return CreatePlayerRst(null, false)
        }

        var diffString = ""
        for (i in 1 until diff + 1) {
            diffString += "0"
        }
        val rand = getRandInt(8) + 1
        val n = rand.toString() + areaId.toString() + diffString + nowNum.toString()

        val name = n.toLong()
        player.name = pcs.basicProtoCache.namePrefix + java.lang.Long.toHexString(name)
        insert(areaCache, player)

        // 存入缓存
        cacheSinglePlayer(player)

        return CreatePlayerRst(player, true)
    }

    fun createPlayerForGm(player: Player): CreatePlayerRst {
        val areaOnly = areaCache.areaOnlyCache.findAreaOnly()
        val nowNum = areaOnly.nowPlayerNum
        val areaId = areaCache.areaConfig.areaId

        val diff = 6 - intLen(nowNum)
        if (diff <= 0) {
            normalLog.error("创建角色失败--当前数量：${nowNum}，报错值为：$diff")
            return CreatePlayerRst(null, false)
        }

        var diffString = ""
        for (i in 1 until diff + 1) {
            diffString += "0"
        }
        val rand = getRandInt(8) + 1
        val n = rand.toString() + areaId.toString() + diffString + nowNum.toString()

        val name = Integer.parseInt(n)
        player.name = pcs.basicProtoCache.namePrefix + Integer.toHexString(name)

        // 存入缓存
        cacheSinglePlayer(player)

        return CreatePlayerRst(player, true)
    }

    // 获取所有玩家
    fun findAllPlayers(): List<Player> {
        val players = LinkedList<Player>()
        playerMap.map { players.add(it) }
        return players
    }

    // 获取当前世界玩家总人数
    fun findPlayerNum(): Int {
        return playerMap.indexLen()
    }

    // 根据Jjc排行获取玩家
    fun findPlayerByJjcRank(rank: Int): Player? {
        return playerRankMap.findByKey(rank)
    }

    // 根据玩家名字获取玩家
    fun findPlayerByName(name: String): Player? {
        return playerNameMap[name]
    }

    // 根据玩家用户名获取玩家
    fun findPlayerByUser(user: String): Player? {
        return playerUserMap[user]
    }

    // 根据ID获取玩家
    fun findPlayerById(id: Long): Player? {
        if (id == 0L) {
            return null
        }
        return playerMap.findByKey(id)
    }

    // 玩家加入联盟（包括创建联盟）
    fun addPlayer2Alliance(player: Player) {
        // 添加到联盟-成员缓存
        alliancePlayerMap.insertByKey(player)
    }

    // 玩家离开联盟
    fun removePlayer2Alliance(player: Player) {
        // 添加到联盟-成员缓存
        alliancePlayerMap.deleteByKey(player)
    }

    // 根据联盟ID获取联盟中所有玩家信息
    fun findPlayersByAllianceId(aid: Long): List<Player> {
        val members = LinkedList<Player>()
        alliancePlayerMap.findByOneKeyFilter(aid) { members.add(it) }
        return members
    }

    //根据联盟查找该联盟内第一个玩家
    fun findFirstPlayerByAllianceId(allianceId: Long): Player? {
        var firstPlayer: Player? = null
        alliancePlayerMap.findByOneKeyFilter(allianceId) {
            firstPlayer = it
            false
        }
        return firstPlayer
    }

    //查找所有盟主
    fun findAllAllianceLeader(): List<Player> {
        val leaders = LinkedList<Player>()
        alliancePlayerMap.map {
            if (playerIsHavePos(it, ALLIANCE_POSITION_BOSS)) {
                leaders.add(it)
            }
            true
        }
        return leaders
    }

    //查找盟主
    fun findAllianceLeader(allianceId: Long): Player? {
        var leader: Player? = null
        alliancePlayerMap.findByOneKeyFilter(allianceId) {
            val rst = playerIsHavePos(it, ALLIANCE_POSITION_BOSS)
            if (rst) {
                leader = it
            }
            !rst
        }
        return leader
    }

    // 玩家总人数
    fun findAllPlayerNum(): Int {
        return playerMap.indexLen()
    }

    // 获取一个玩家的战斗力在本服的排名
    data class PlayerPowerRank(
        var player: Player,
        var powerValue: Long
    )

    fun findPowerRankByPlayerId(playerId: Long): Int {
        val players = findAllPlayers()
        val pRanks = LinkedList<PlayerPowerRank>()
        for (player in players) {
            val target = areaCache.targetCache.findMyTargetByPlayerId(player.id) ?: continue
            val pv = target.getTotalPower()

            pRanks.add(
                PlayerPowerRank(
                    player,
                    pv
                )
            )
        }

        if (pRanks.count() > 1) {
            pRanks.sortByDescending { it.powerValue }
        }

        var rank = 0
        var index = 0
        for (info in pRanks) {
            if (info.player.id == playerId) {
                rank = index + 1
            }
            index++
        }
        return rank
    }

    fun updatePlayerMaxLvPrisonBuffEndTime(player: Player, endTime: Long) {
        val oldTime = player.maxLvPrisonBuffEndTime
        if (oldTime != 0L) {
            player4MaxPrisonBuffTime.remove(player)
        }
        player.maxLvPrisonBuffEndTime = endTime
        if (endTime != 0L) {
            player4MaxPrisonBuffTime.add(player)
        }
    }

    fun peekPlayerMaxLvPrisonBuffTimeFinish(): (Player?) {
        val player = player4MaxPrisonBuffTime.peek()
        if (player == null) {
            return null
        }

        val nowTime = getNowTime()

        if (player.maxLvPrisonBuffEndTime < nowTime) {
            return player
        }

        return null
    }

    fun popPlayerMaxLvPrisonBuffTime() {
        player4MaxPrisonBuffTime.poll()
    }
}


fun intLen(num: Int): Int {
    var n = 1
    var temNum = num
    while (true) {
        if (temNum / 10 != 0) {
            n += 1
            temNum /= 10
        } else {
            break
        }
    }
    return n
}

// 判断玩家是否有这个职位
fun playerIsHavePos(player: Player, pos: Int): Boolean {
    return player.getAlliancePosMapValue(pos)
}