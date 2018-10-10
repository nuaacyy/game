package com.point18.slg2d.world.area4data

import akka.actor.ActorRef
import com.point18.slg2d.common.baseg.CacheStore
import com.point18.slg2d.common.commonfunc.AreaConfig
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.getTime
import com.point18.slg2d.common.commonfunc.tellNoSender
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.actor.WorldActor
import com.point18.slg2d.world.common.sendMarqueeNotice2Player
import com.point18.slg2d.world.wpm
import pb4client.ChatInfo
import pb4client.NewChatMessage
import pb4client.Notice
import pb4server.*
import xyz.ariane.util.CommonDao
import java.util.*

abstract class BaseCache(val areaCache: AreaCache) {

    protected val worldId = areaCache.areaConfig.pltAreaNo

    // 缓存初始化
    abstract fun init()

    // 将数据从数据库加载到缓存
    abstract fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao)

    // 初始化数据
    abstract fun doInitData(worldInitData: WorldInitData)

    // 根据缓存间的关联做相关处理
    abstract fun doCacheLink()

}

// 游戏区当前的缓存数据
class AreaCache(val areaConfig: AreaConfig, val worldActor: WorldActor, val db: WorldDatabase) : CacheStore {

    var inited: Boolean = false // 是否初始化完毕了

    var currentTimeDiff: Long  // 当前ID前缀,需要和服务器唯一No合成对象编号

    var currentClientMsgNo: Int = 0 // 客户端消息的当前序号，当收到来自其他服的消息时，会通过这个字段暂存消息ID。

    val randSeed: Random = Random()

    val baseCaches: LinkedList<BaseCache> = LinkedList() // 缓存列表

    val wonderCache: CacheWonder                        // 奇观缓存
    val prisonCache: CachePrison                        // 监禁缓存
    val walkCache: CacheWalk                            // 行军缓存
    val warnCache: CacheWarn                            // 预警缓存
    val transportRequestCache: CacheTransportRequest    // 运输请求缓存
    val playerActivityCache: CachePlayerActivity        // 玩家活动缓存
    val playerSettingCache: CachePlayerSetting          // 玩家设置缓存
    val serverActivityCache: CacheServerActivity        // 系统活动缓存
    val taskCache: CacheTask                            // 玩家任务缓存
    private val noticeCache: CacheNotice                // 公告缓存
    val playerCache: CachePlayer                        // 玩家缓存
    val targetCache: CacheTarget                        // 玩家成就缓存
    val castleCache: CacheCastle                        // 城池缓存
    val heroCache: CacheHero                            // 武将缓存
    val barracksCache: CacheBarracks                    // 兵营缓存
    val myAllianceGiftCache: CacheMyAllianceGift        // 联盟礼物
    val sessionCache: PlayerSessionCache                //在线玩家缓存

    val caveCache: CacheCave                            // 藏兵
    val achievementCache: CacheAchievement              // 成就
    val armyPlanCache: CacheArmyPlan                    // 部队预设
    val walkForceCache: CacheWalkForce                  // 部队缓存
    val walkForceGroupCache: CacheWalkForceGroup        // 行军组缓存
    val mapCellWatcherCache: CacheMapCellWatcher        // 观察列表缓存
    val massCache: CacheMass                            // 集结缓存
    val buffCache: CacheBuff                            // buff缓存
    val activityRankCache: CacheActivityRank            // 活动排行数据
    val mapCellCache: CacheMapCell                      // 地图格子上的对象的相关信息
    val areaOnlyCache: CacheAreaOnly

    val callBossCache: CacheCallBoss                    //召唤魔物缓存
    val commonBossCache: CacheCommonBoss                //普通魔物缓存
    val commonResCache: CacheCommonRes                  //普通资源地缓存
    val deadBossResCache: CacheDeadBossRes              //尸体资源地缓存
    val relicCache: CacheRelic                          //遗迹缓存

    val instanceCache: CacheInstance                    //推图缓存
    val instanceDropCache: CacheInstanceDrop            //推图掉落缓存
    val rankCache: CacheRank
    val homeHeartCache: CacheHomeHeart                  // home服心跳缓存
    val jjcCache: CacheJjc                              // 竞技场
    val chatCache: CacheChat                            // 世界聊天缓存
    val fameHallCache: CacheFameHall                    // 名人堂缓存
    val fogCache: CacheFog                              //迷雾缓存
    val activityBossAreaCache: CacheActivityBossArea    // 活动Boos地区缓存
    val activityBossCache: CacheActivityBoss            // 活动Boos缓存
    val belongCellCache: CacheBelongCell                // 归属地块缓存
    val bossInviteCache: CacheBossInvite                // 魔物邀请

    val playerCreateMap: HashMap<Int, Int> = hashMapOf()                            // 大地图玩家出生规则
    val infoByHomeCache: CacheInfoByHome                                            // 玩家home服同步过来的数据
    val moveServerCellLockCache: CacheMoveServerCellLock                            // 魔物邀请

    init {
        val startTime = getTime(2016, 9, 28, 8, 0, 0)
        currentTimeDiff = getNowTime() - startTime

        this.chatCache = CacheChat(this, db)
        this.baseCaches.add(this.chatCache)

        this.jjcCache = CacheJjc(this, db)
        this.baseCaches.add(this.jjcCache)

        this.playerCache = CachePlayer(this, db)
        this.baseCaches.add(this.playerCache)

        this.taskCache = CacheTask(this, db)
        this.baseCaches.add(this.taskCache)

        this.targetCache = CacheTarget(this, db)
        this.baseCaches.add(this.targetCache)

        this.heroCache = CacheHero(this, db)
        this.baseCaches.add(this.heroCache)

        this.castleCache = CacheCastle(this, db)
        this.baseCaches.add(this.castleCache)

        this.noticeCache = CacheNotice(this, db)
        this.baseCaches.add(this.noticeCache)

        this.barracksCache = CacheBarracks(this, db)
        this.baseCaches.add(this.barracksCache)

        this.areaOnlyCache = CacheAreaOnly(this, db)
        this.baseCaches.add(this.areaOnlyCache)

        this.myAllianceGiftCache = CacheMyAllianceGift(this, db)
        this.baseCaches.add(this.myAllianceGiftCache)

        this.caveCache = CacheCave(this, db)
        this.baseCaches.add(this.caveCache)

        this.walkCache = CacheWalk(this, db)
        this.baseCaches.add(this.walkCache)

        this.walkForceCache = CacheWalkForce(this, db)
        this.baseCaches.add(this.walkForceCache)

        this.walkForceGroupCache = CacheWalkForceGroup(this, db)
        this.baseCaches.add(this.walkForceGroupCache)

        this.mapCellWatcherCache = CacheMapCellWatcher(this)
        this.baseCaches.add(this.mapCellWatcherCache)

        this.massCache = CacheMass(this, db)
        this.baseCaches.add(this.massCache)

        this.warnCache = CacheWarn(this, db)
        this.baseCaches.add(this.warnCache)

        this.transportRequestCache = CacheTransportRequest(this, db)
        this.baseCaches.add(this.transportRequestCache)

        this.buffCache = CacheBuff(this, db)
        this.baseCaches.add(this.buffCache)

        this.playerActivityCache = CachePlayerActivity(this, db)
        this.baseCaches.add(this.playerActivityCache)

        this.playerSettingCache = CachePlayerSetting(this, db)
        this.baseCaches.add(this.playerSettingCache)

        this.serverActivityCache = CacheServerActivity(this, db)
        this.baseCaches.add(this.serverActivityCache)

        this.activityRankCache = CacheActivityRank(this, db)
        this.baseCaches.add(this.activityRankCache)

        this.prisonCache = CachePrison(this, db)
        this.baseCaches.add(this.prisonCache)

        this.wonderCache = CacheWonder(this, db)
        this.baseCaches.add(this.wonderCache)

        this.mapCellCache = CacheMapCell(this)
        this.baseCaches.add(this.mapCellCache)

        this.achievementCache = CacheAchievement(this, db)
        this.baseCaches.add(this.achievementCache)

        this.armyPlanCache = CacheArmyPlan(this, db)
        this.baseCaches.add(this.armyPlanCache)

        this.callBossCache = CacheCallBoss(this, db)
        this.baseCaches.add(this.callBossCache)

        this.commonBossCache = CacheCommonBoss(this, db)
        this.baseCaches.add(this.commonBossCache)

        this.commonResCache = CacheCommonRes(this, db)
        this.baseCaches.add(this.commonResCache)

        this.deadBossResCache = CacheDeadBossRes(this, db)
        this.baseCaches.add(this.deadBossResCache)

        this.relicCache = CacheRelic(this, db)
        this.baseCaches.add(this.relicCache)

        this.instanceCache = CacheInstance(this, db)
        this.baseCaches.add(this.instanceCache)

        this.instanceDropCache = CacheInstanceDrop(this, db)
        this.baseCaches.add(this.instanceDropCache)

        this.rankCache = CacheRank(this, db)
        this.baseCaches.add(this.rankCache)

        this.sessionCache = PlayerSessionCache(this)
        this.baseCaches.add(this.sessionCache)

        this.homeHeartCache = CacheHomeHeart(this, db)
        this.baseCaches.add(this.homeHeartCache)

        this.infoByHomeCache = CacheInfoByHome(this, db)
        this.baseCaches.add(this.infoByHomeCache)

        this.fameHallCache = CacheFameHall(this, db)
        this.baseCaches.add(this.fameHallCache)

        this.fogCache = CacheFog(this, db)
        this.baseCaches.add(this.fogCache)

        this.activityBossAreaCache = CacheActivityBossArea(this, db)
        this.baseCaches.add(this.activityBossAreaCache)

        this.activityBossCache = CacheActivityBoss(this, db)
        this.baseCaches.add(this.activityBossCache)

        this.belongCellCache = CacheBelongCell(this, db)
        this.baseCaches.add(this.belongCellCache)

        this.bossInviteCache = CacheBossInvite(this, db)
        this.baseCaches.add(this.bossInviteCache)

        this.moveServerCellLockCache = CacheMoveServerCellLock(this, db)
        this.baseCaches.add(this.moveServerCellLockCache)

        for (eachCache in this.baseCaches) {
            eachCache.init()
        }
    }

    fun <T> createACS(
        actor: ActorRef,
        message: Any,
        expectedResponseClass: Class<T>,
        completeFun: (T?, Throwable?) -> Unit
    ) {
        worldActor.createACS<T>()
            .ask(actor, message, expectedResponseClass)
            .whenCompleteKt { askResp, err ->
                // 存入客户端消息流转序号
                when (askResp) {
                    is World2PublicAskResp -> {
                        currentClientMsgNo = askResp.clientMsgNo
                        completeFun(askResp, err)
                    }
                    is World2HomeAskResp -> {
                        currentClientMsgNo = askResp.clientMsgNo
                        completeFun(askResp, err)
                    }
                    is World2PublicManagerAskResp -> {
                        currentClientMsgNo = askResp.clientMsgNo
                        completeFun(askResp, err)
                    }
                    else -> completeFun(null, IllegalStateException("ACS返回类型错误！"))
                }
            }
    }

    // 填充发往另一个世界的Ask消息的消息头
    fun fillW2WAskMsgHeader(
        worldId: Long,
        playerId: Long,
        wrap: (wrapMsg: World2WorldAskReq.Builder) -> Unit
    ): World2WorldAskReq {
        return World2WorldAskReq.newBuilder().let {
            it.worldId = worldId
            it.playerId = playerId
            it.clientMsgNo = currentClientMsgNo

            wrap(it)

            it.build()
        }
    }

    // 填充发往另一个世界的Tell消息的消息头
    fun fillW2WTellMsgHeader(
        worldId: Long,
        playerId: Long,
        wrap: (wrapMsg: World2WorldTell.Builder) -> Unit
    ): World2WorldTell {
        return World2WorldTell.newBuilder().let { it ->
            it.playerId = playerId
            it.worldId = worldId
            it.clientMsgNo = currentClientMsgNo

            wrap(it)

            it.build()
        }
    }

    // 填充发往公共的Ask消息的消息头
    fun fillW2PAskMsgHeader(
        publicId: Long,
        playerId: Long,
        wrap: (wrapMsg: World2PublicAskReq.Builder) -> Unit
    ): World2PublicAskReq {
        return World2PublicAskReq.newBuilder().let {
            it.worldId = areaConfig.pltAreaNo
            it.publicId = publicId
            it.playerId = playerId
            it.clientMsgNo = currentClientMsgNo

            wrap(it)

            it.build()
        }
    }

    // 填充发往公共管理节点的Ask消息的消息头
    fun fillW2PmAskMsgHeader(
        playerId: Long,
        wrap: (wrapMsg: World2PublicManagerAskReq.Builder) -> Unit
    ): World2PublicManagerAskReq {
        return World2PublicManagerAskReq.newBuilder().let {
            it.worldId = areaConfig.pltAreaNo
            it.playerId = playerId
            it.clientMsgNo = currentClientMsgNo

            wrap(it)

            it.build()
        }
    }

    // 填充发往玩家服Ask消息的消息头
    fun fillW2HAskMsgHeader(
        playerId: Long,
        wrap: (wrapMsg: World2HomeAskReq.Builder) -> Unit
    ): World2HomeAskReq {
        return World2HomeAskReq.newBuilder().let {
            it.worldId = areaConfig.pltAreaNo
            it.playerId = playerId
            it.clientMsgNo = currentClientMsgNo

            wrap(it)

            it.build()
        }
    }

    // 填充发往玩家服Tell消息的消息头
    fun fillW2HTellMsgHeader(
        playerId: Long,
        wrap: (wrapMsg: World2HomeTell.Builder) -> Unit
    ): World2HomeTell {
        return World2HomeTell.newBuilder().let { it ->
            it.playerId = playerId
            it.clientMsgNo = currentClientMsgNo

            wrap(it)

            it.build()
        }
    }

    fun tellHome(msg: World2HomeTell) {
        worldActor.homeShardRegion.tell(msg, ActorRef.noSender())
    }

    fun tellWorld(msg: World2WorldTell) {
        worldActor.worldShardRegion.tell(msg, ActorRef.noSender())
    }

    // 推送app消息
    fun pushAppNotice(
        playerId: Long,
        protoId: Int,
        enemyLv: Int, // 不需要判定警戒等级就填0
        vararg params: String
    ) {
        val checkResult = playerSettingCache.checkNoticeSetting(playerId, protoId, enemyLv)

        if (checkResult.flag) {
            val contentLan = pcs.lanWithParam(ZH_CN, checkResult.contentLanId, *params)
            val titleLan = pcs.lanWithParam(ZH_CN, checkResult.titleLanId)

            // todo jh app 测试用
            sendMarqueeNotice2Player(
                this,
                NOTICE_TYPE_CENTER,
                playerId,
                "$titleLan:$contentLan",
                TEXT_READ_INFO
            )
        }
    }

    // 系统广播
    fun sendSysBroadcastMsg(message: String) {
        val chat = Chat()
        chat.msg = message
        chat.msgType = SYSTEM_NOTICE
        chat.readType = TEXT_READ_LAN
        // chatTime 和 worldId不用设置 会在createChat中设置

        chatCache.createChat(chat)

        // 组装聊天消息
        val newChatMessageBuilder = NewChatMessage.newBuilder()
        val chatInfoBuilder = ChatInfo.newBuilder()
        chatInfoBuilder.id = chat.id
        chatInfoBuilder.type = CHAT_TYPE_WORLD
        chatInfoBuilder.isSystem = 1
        chatInfoBuilder.country = 24
        chatInfoBuilder.allianceName = ""
        chatInfoBuilder.allianceShortName = ""
        chatInfoBuilder.alliancePositions = ""
        chatInfoBuilder.player = ""
        chatInfoBuilder.playerId = 0
        chatInfoBuilder.playerShortName = ""
        chatInfoBuilder.playerIcon = 0
        chatInfoBuilder.sendTime = (chat.chatTime / 1000).toInt()
        chatInfoBuilder.messageType = SYSTEM_NOTICE
        chatInfoBuilder.office = 0
        chatInfoBuilder.vipLv = 0
        chatInfoBuilder.areaNo = areaConfig.areaNo
        val noticeBuilder = Notice.newBuilder()
        noticeBuilder.readType = TEXT_READ_LAN
        noticeBuilder.noticeLanId = message
        chatInfoBuilder.message = noticeBuilder.build()
        newChatMessageBuilder.chatInfo = chatInfoBuilder.build()

        val multicastMSG = MulticastEnvelopeMsg.newBuilder()
        multicastMSG.msgType = MsgType.NewChatMessage_3080.msgType
        multicastMSG.newChatMsg = newChatMessageBuilder.build()
        multicastMSG.channel = worldChannelOf(areaConfig.pltAreaNo)
        wpm.multicastServiceRouter.tellNoSender(
            multicastMSG.build()
        )

    }
}