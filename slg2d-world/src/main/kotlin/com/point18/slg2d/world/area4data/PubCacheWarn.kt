package com.point18.slg2d.world.area4data

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.mcache.TwoKeyIndex
import com.point18.slg2d.common.mcache.TwoKeyIndexSlice
import com.point18.slg2d.world.WorldDatabase
import xyz.ariane.util.CommonDao
import java.util.*

class WarnData(
    var playerId: Long,
    var walk: Walk
)

class CacheWarn(areaCache: AreaCache, val db: WorldDatabase) : BaseCache(areaCache) {

    private val warnMapByPlayer = TwoKeyIndex<Long, Long, WarnData>({ it.playerId }, { it.walk.id })
    private val warnMapByPos = TwoKeyIndexSlice<Int, Int, WarnData>({ it.walk.marchAimsX }, { it.walk.marchAimsY },
        { walkA, walkB -> walkA.playerId == walkB.playerId && walkA.walk.id == walkB.walk.id })

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
    }

    override fun doInitData(worldInitData: WorldInitData) {

    }

    override fun doCacheLink() {
        //从行军线中获取预警数据
        areaCache.walkCache.walkMap.map { walk ->
            createWarnByWalk(walk)
            true
        }
    }

    //查找预警行军
    fun findWarnWalksByPlayerId(playerId: Long): List<WarnData> {
        val walks = LinkedList<WarnData>()
        warnMapByPlayer.findByOneKeyFilter(playerId) { walks.add(it) }
        return walks
    }

    //根据行军创建预警数据
    fun createWarnByWalk(walk: Walk): List<WarnData> {
        val aimX = walk.marchAimsX
        val aimY = walk.marchAimsY
        val warnDataList = LinkedList<WarnData>()
        var state = Running
        var checkCastle = false
        var sameAlliance = false
        when (walk.marchState) {
            WalkJoinMass,
            WalkJoinRelicMass,
            WalkTransport,
            WalkStation,
            WalkReinforce -> {
                checkCastle = true
                sameAlliance = true
            }
            WalkFightPlayer -> {
                checkCastle = true
                state = Reinforce
            }
            WalkScout -> {
                checkCastle = true
                state = Reinforce
            }
            WalkOccupyCell -> state = Stationed
            WalkOccupyWonder -> state = Reinforce
        }

        val walkGroup = areaCache.walkForceGroupCache.findWalkForceGroupById(walk.walkForceGroupId)
        if (walkGroup == null) {
            //Assert
            return warnDataList
        }
        if (checkCastle) {
            val castle = areaCache.castleCache.findCastleByXy(aimX, aimY)
            if (castle != null && (checkSameAlliance(
                    walkGroup.mainPlayerId,
                    castle.playerId
                ) != 0L) == sameAlliance
            ) {
                val walkData = WarnData(castle.playerId, walk)
                warnDataList.add(walkData)
                warnMapByPlayer.insertByKey(walkData)
                warnMapByPos.insertByKey(walkData)
            }
        }
        if (state == Running) {
            return warnDataList
        }
        val groups = areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(aimX, aimY, state)
        for (group in groups) {
            if ((checkSameAlliance(walkGroup.mainPlayerId, group.mainPlayerId) != 0L) != sameAlliance) {
                continue
            }
            val walkData = WarnData(group.mainPlayerId, walk)
            warnDataList.add(walkData)
            warnMapByPlayer.insertByKey(walkData)
            warnMapByPos.insertByKey(walkData)
        }
        return warnDataList
    }

    data class ChangeWalkData(
        var addWalkDataList: LinkedList<WarnData>,
        var delWalkDataList: LinkedList<WarnData>
    )

    fun updateWarnByPos(posX: Int, posY: Int): ChangeWalkData {
        val oldWalkDataMap = hashMapOf<Long, HashMap<Long, WarnData>>()
        val newWalkDataMap = hashMapOf<Long, HashMap<Long, WarnData>>()
        // 从缓存中根据坐标获取旧的行军预警
        warnMapByPos.findByKey(posX, posY) { walkData: WarnData ->
            val dataMap = oldWalkDataMap[walkData.playerId]
            if (dataMap == null) {
                val defaultData = hashMapOf<Long, WarnData>()
                defaultData[walkData.walk.id] = walkData
                oldWalkDataMap[walkData.playerId] = defaultData
            } else {
                dataMap[walkData.walk.id] = walkData
            }
            true
        }
        // 根据坐标获取新军线
        val walks = areaCache.walkCache.findWalksByGotoXy(posX, posY)
        for (walk in walks) {
            var state = Running
            var checkCastle = false
            var sameAlliance = false
            when (walk.marchState) {
                WalkJoinMass,
                WalkJoinRelicMass,
                WalkTransport,
                WalkStation,
                WalkReinforce -> {
                    checkCastle = true
                    sameAlliance = true
                }
                WalkFightPlayer -> {
                    checkCastle = true
                    state = Reinforce
                }
                WalkScout -> {
                    checkCastle = true
                    state = Reinforce
                }
                WalkOccupyCell -> state = Stationed
                WalkOccupyWonder -> state = Reinforce
            }

            val walkGroup = areaCache.walkForceGroupCache.findWalkForceGroupById(walk.walkForceGroupId)
            if (walkGroup == null) {
                //Assert
                continue
            }
            if (checkCastle) {
                val castle = areaCache.castleCache.findCastleByXy(posX, posY)
                if (castle != null && (checkSameAlliance(
                        walkGroup.mainPlayerId,
                        castle.playerId
                    ) != 0L) == sameAlliance
                ) {
                    val walkData = WarnData(
                        castle.playerId,
                        walk
                    )
                    var dataMap = newWalkDataMap[walkData.playerId]
                    if (dataMap == null) {
                        dataMap = hashMapOf()
                        newWalkDataMap[walkData.playerId] = dataMap
                    }
                    dataMap[walkData.walk.id] = walkData
                }
            }
            if (state == Running) {
                continue
            }
            val groups = areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(posX, posY, state)
            for (group in groups) {
                if ((checkSameAlliance(walkGroup.mainPlayerId, group.mainPlayerId) != 0L) != sameAlliance) {
                    continue
                }
                val walkData = WarnData(
                    group.mainPlayerId,
                    walk
                )
                val dataMap = newWalkDataMap.getOrPut(walkData.playerId) { hashMapOf() }
                dataMap[walkData.walk.id] = walkData
            }
        }
        val changeWalkData = ChangeWalkData(LinkedList(), LinkedList())
        for ((playerId, dataMap) in oldWalkDataMap) {
            for ((walkId, oldWalkData) in dataMap) {
                val data = newWalkDataMap[playerId]
                if (data == null || data[walkId] == null) {
                    changeWalkData.delWalkDataList.add(oldWalkData)
                }
            }
        }
        for ((playerId, dataMap) in newWalkDataMap) {
            for ((walkId, newWalkData) in dataMap) {
                val data = oldWalkDataMap[playerId]
                if (data == null || data[walkId] == null) {
                    changeWalkData.addWalkDataList.add(newWalkData)
                }
            }
        }
        changeWalkData.delWalkDataList.forEach {
            warnMapByPlayer.deleteByKey(it)
            warnMapByPos.deleteByKey(it)
        }
        changeWalkData.addWalkDataList.forEach {
            warnMapByPlayer.insertByKey(it)
            warnMapByPos.insertByKey(it)
        }
        return changeWalkData
    }

    private fun checkSameAlliance(playerIdA: Long, playerIdB: Long): Long {
        val playerA = areaCache.playerCache.findPlayerById(playerIdA)
        val playerB = areaCache.playerCache.findPlayerById(playerIdB)
        if (playerA == null || playerB == null) {
            return 0
        }
        if (playerA.allianceId == playerB.allianceId) {
            return playerA.allianceId
        }
        return 0
    }
}
