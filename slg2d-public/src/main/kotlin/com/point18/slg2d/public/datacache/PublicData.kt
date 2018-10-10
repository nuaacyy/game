package com.point18.slg2d.public.datacache

import com.point18.slg2d.common.baseg.CacheStore
import com.point18.slg2d.common.commonfunc.AreaConfig
import com.point18.slg2d.public.PublicDatabase
import com.point18.slg2d.public.PublicMenagerDatabase
import com.point18.slg2d.public.actor.PublicActor
import com.point18.slg2d.public.actor.PublicManagerActor
import xyz.ariane.util.CommonDao
import java.util.*

abstract class BaseCache(val publicCache: PublicCache) {
    // 缓存初始化
    abstract fun init()

    // 将数据从数据库加载到缓存
    abstract fun doCacheLoad(publicInitData: PublicInitData, dao: CommonDao)

    // 初始化数据
    abstract fun doInitData(publicInitData: PublicInitData)

    // 根据缓存间的关联做相关处理
    abstract fun doCacheLink()
}

// 游戏区当前的缓存数据
class PublicCache(areaConfig: AreaConfig, val publicActor: PublicActor, val db: PublicDatabase) : CacheStore {

    var inited: Boolean = false // 是否初始化完毕了

    var currentClientMsgNo: Int = 0 // 当前客户端消息序号，消息流转用

    val baseCaches: LinkedList<BaseCache> = LinkedList() // 缓存列表

    var allianceCache: CacheAlliance
    val allianceChatCache: CacheAllianceChat
    val allianceActivityCache: CacheAllianceActivity
    val everyAllianceActivityCache: CacheEveryAllianceActivity
    val allianceActivityRankCache: CacheAllianceActivityRank
    val allianceCompetitionQuestCache: CacheAllianceCompetitionQuest
    val allianceGiftCache: CacheAllianceGift
    val allianceHelpCache: CacheAllianceHelp
    val allianceLogCache: CacheAllianceLog
    val allianceMarkCache: CacheAllianceMark
    val allianceMemberCache: CacheAllianceMember
    val allianceMemberTraceCache: CacheAllianceMemberTrace
    val allianceReplyCache: CacheAllianceReply
    val allianceReqCache: CacheAllianceReq
    val allianceTopicCache: CacheAllianceTopic
    val allianceWaijiaoCache: CacheAllianceWaijiao
    val chatRoomCache: CacheChatRoom
    val roomChatRecordsCache: CacheRoomChatRecord
    val casinosWinnersCache: CacheCasinosWinner
    val jackpotCache: CacheJackpot

    init {
        this.jackpotCache = CacheJackpot(this, db)
        this.baseCaches.add(jackpotCache)

        this.casinosWinnersCache = CacheCasinosWinner(this, db)
        this.baseCaches.add(casinosWinnersCache)

        this.roomChatRecordsCache = CacheRoomChatRecord(this, db)
        this.baseCaches.add(roomChatRecordsCache)

        this.chatRoomCache = CacheChatRoom(this, db)
        this.baseCaches.add(chatRoomCache)

        this.allianceCache = CacheAlliance(this, db)
        this.baseCaches.add(allianceCache)

        this.allianceChatCache = CacheAllianceChat(this, db)
        this.baseCaches.add(allianceChatCache)

        this.allianceActivityCache = CacheAllianceActivity(this, db)
        this.baseCaches.add(allianceActivityCache)

        this.everyAllianceActivityCache = CacheEveryAllianceActivity(this, db)
        this.baseCaches.add(everyAllianceActivityCache)

        this.allianceActivityRankCache = CacheAllianceActivityRank(this, db)
        this.baseCaches.add(allianceActivityRankCache)

        this.allianceCompetitionQuestCache = CacheAllianceCompetitionQuest(this, db)
        this.baseCaches.add(allianceCompetitionQuestCache)

        this.allianceGiftCache = CacheAllianceGift(this, db)
        this.baseCaches.add(allianceGiftCache)

        this.allianceHelpCache = CacheAllianceHelp(this, db)
        this.baseCaches.add(allianceHelpCache)

        this.allianceLogCache = CacheAllianceLog(this, db)
        this.baseCaches.add(allianceLogCache)

        this.allianceMarkCache = CacheAllianceMark(this, db)
        this.baseCaches.add(allianceMarkCache)

        this.allianceMemberCache = CacheAllianceMember(this, db)
        this.baseCaches.add(allianceMemberCache)

        this.allianceMemberTraceCache = CacheAllianceMemberTrace(this, db)
        this.baseCaches.add(allianceMemberTraceCache)

        this.allianceReplyCache = CacheAllianceReply(this, db)
        this.baseCaches.add(allianceReplyCache)

        this.allianceReqCache = CacheAllianceReq(this, db)
        this.baseCaches.add(allianceReqCache)

        this.allianceTopicCache = CacheAllianceTopic(this, db)
        this.baseCaches.add(allianceTopicCache)

        this.allianceWaijiaoCache = CacheAllianceWaijiao(this, db)
        this.baseCaches.add(allianceWaijiaoCache)

        for (eachCache in this.baseCaches) {
            eachCache.init()
        }

    }
}

// 游戏区当前的缓存数据
class PublicManagerCache(
    areaConfig: AreaConfig,
    val publicManagerActor: PublicManagerActor,
    val db: PublicMenagerDatabase
) :
    CacheStore {

    var inited: Boolean = false // 是否初始化完毕了

    var currentClientMsgNo: Int = 0 // 当前客户端消息序号，消息流转用

    val baseCaches: LinkedList<BaseCache> = LinkedList() // 缓存列表

    val allianceCache: CacheAllianceManager
    val publicActivityManagerCache: CachePublicActivityManager
    val allianceCompetitionGroupCacheManager: CacheAllianceCompetitionGroupManager

    init {
        this.allianceCache = CacheAllianceManager(db)
        this.publicActivityManagerCache = CachePublicActivityManager(db)
        this.allianceCompetitionGroupCacheManager = CacheAllianceCompetitionGroupManager(db)
    }
}

