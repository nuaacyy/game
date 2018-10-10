package com.point18.slg2d.world.area4data

import akka.actor.ActorRef
import xyz.ariane.util.CommonDao
import java.util.*

// 连接会话
class PlayerSessionCache(areaCache: AreaCache) : BaseCache(areaCache) {

    // 在线玩家的会话管理
    var epSessionMap: HashMap<ActorRef, PlayerSession> = hashMapOf() // 以连接为索引的玩家会话表
    var playerIdSessionMap: HashMap<Long, PlayerSession> = hashMapOf() // 以角色ID为索引的玩家会话表

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
    }

    override fun doInitData(worldInitData: WorldInitData) {

    }

    override fun doCacheLink() {
    }

    // 准备预备上线的玩家会话
    // 这类会话不可能存在于在线列表中
    fun preparePlayerSession(
        areaCache: AreaCache,
        channelActor: ActorRef,
        playerId: Long,
        player: Player
    ): PlayerSession {
        val session = PlayerSession(areaCache, channelActor, playerId, player)
        this.playerIdSessionMap[playerId] = session
        this.epSessionMap[channelActor] = session

        return session
    }

    // 下线,将玩家会话从在线会话表中移除
    fun playerSessionOffline(session: PlayerSession) {
        val playerId = session.playerId

        // 从在线会话中移除
        this.playerIdSessionMap.remove(playerId)
        val epClient = session.channelActor
        this.epSessionMap.remove(epClient)
    }
}

// 根据EpNo获取在线会话
fun fetchOnlinePlayerSessionByEpNo(areaCache: AreaCache, epNo: ActorRef): PlayerSession? {
    val sessionCache = areaCache.sessionCache
    return sessionCache.epSessionMap[epNo]
}

// 获取在线的玩家会话
fun fetchOnlinePlayerSession(areaCache: AreaCache, playerId: Long): PlayerSession? {
    val sessionCache = areaCache.sessionCache
    return sessionCache.playerIdSessionMap[playerId]
}

// 获取所有的在线会话
fun fetchAllOnlinePlayerSessions(areaCache: AreaCache): HashMap<Long, PlayerSession> {
    val sessionCache = areaCache.sessionCache
    return sessionCache.playerIdSessionMap
}
