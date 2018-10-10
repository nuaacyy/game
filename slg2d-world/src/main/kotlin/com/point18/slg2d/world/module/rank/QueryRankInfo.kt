package com.point18.slg2d.world.module.rank

import com.point18.slg2d.world.area4data.*
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.module.uiCondition.uiConditionCheck
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.QueryRank
import pb4client.QueryRankInfoRt
import pb4client.QueryRankRt
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.common.findOfficeByPlayerId
import java.util.*

// 查看
class QueryRankDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        // 获取排行类型  页数
        val rankType = (msg as QueryRank).type
        val page = msg.page
        val pageNum = msg.num

        // 数据返回定义
        val queryRankRt = queryRank(session, rankType, page, pageNum)

        // 发送数据
        session.sendMsg(MsgType.QueryRank_502, queryRankRt)
    }

    fun queryRank(session: PlayerSession, rankType: Int, page: Int, pageNum: Int): (QueryRankRt) {
        val rt = QueryRankRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.myRank = 0
        rt.type = rankType
        rt.page = page
        rt.myValue = 0

        if (page <= 0 || pageNum <= 0) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        val areaCache = session.areaCache
        val player = session.player

        // 功能开启
        val uiConditionRt = uiConditionCheck(areaCache, player, RANK_OPEN)
        if (uiConditionRt != ResultCode.SUCCESS.code) {
            rt.rt = uiConditionRt
            return rt.build()
        }

        val startNum = (page - 1) * pageNum + 1
        var endNum = page * pageNum
        val arenaStartRank = (page - 1) * pageNum + 1
        val arenaEndRank = page * pageNum


        if (rankType == JjcRank) {
            queryJjcRankInfo(areaCache, rt, arenaStartRank, arenaEndRank, player)
            return rt.build()
        }

        val ranks = areaCache.rankCache.findRankInfo(rankType)
        if (ranks == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        // 根据玩家id拿到排行
        var index = 1
        var myOrder = 0
        for (v in ranks.queryValue(100)) {
            if (v.playerId == session.playerId) {
                myOrder = index
                break
            }
            index += 1
        }

        val target = areaCache.targetCache.findMyTargetByPlayerId(session.playerId)
        if (target == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        when (rankType) {
            PowerRank -> rt.myValue = target.getTotalPower()
            KillSoliderRank -> rt.myValue = target.totalKill
            KillMonsterScore -> rt.myValue = target.killMonsterScore
            else -> {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return rt.build()
            }
        }
        rt.myRank = myOrder

        val rankNum = ranks.queryAllJoinNum()
        if (rankNum == 0) {
            return rt.build()
        }

        if (rankNum < endNum) {
            endNum = rankNum
        }
        if (startNum > endNum) {
            return rt.build()
        }

        val reallyRanks = ranks.queryPartValue(startNum, endNum)

        for (eachTarget in reallyRanks) {
            val playerTarget = areaCache.playerCache.findPlayerById(eachTarget.playerId)
            if (playerTarget == null) {
                continue
            }

            val currentPos = findOfficeByPlayerId(areaCache, eachTarget.playerId)
            val queryRankInfoRt = QueryRankInfoRt.newBuilder()
            queryRankInfoRt.name = playerTarget.name
            queryRankInfoRt.photoProtoId = playerTarget.photoProtoId
            queryRankInfoRt.allianceShortName = playerTarget.allianceShortName
            queryRankInfoRt.playerId = playerTarget.id
            queryRankInfoRt.curentPos = currentPos
            queryRankInfoRt.nickName = playerTarget.allianceNickName
            queryRankInfoRt.robotProtoId = 0
            queryRankInfoRt.score = ranks.findTByV(eachTarget).toInt()

            rt.addRankInfo(queryRankInfoRt)
        }

        return rt.build()
    }

    fun queryJjcRankInfo(areaCache: AreaCache, rt: QueryRankRt.Builder, startRank: Int, endRank: Int, player: Player): QueryRankRt.Builder {
        if (endRank > pcs.basicProtoCache.maxRankingNumeber) {
            return rt
        }

        val ranks = areaCache.rankCache.findRankInfo(JjcRank)
        if (ranks == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        val listOfRemovedArenaRank = LinkedList<MyTarget>()
        val tmpRanks = ranks.queryPartValue(0, endRank)
        val arenaRanks = LinkedList<MyTarget>()

        arenaRanks += tmpRanks
        for (eachTarget in arenaRanks) {
            if (eachTarget.jjcRank > pcs.basicProtoCache.maxRankingNumeber
                || eachTarget.jjcRank < startRank
                || eachTarget.jjcRank > endRank) {
                listOfRemovedArenaRank.add(eachTarget)
            }
        }

        arenaRanks.removeAll(listOfRemovedArenaRank)

        for (rankArena in startRank..endRank) {
            val targetExist = arenaRanks.firstOrNull { it.jjcRank == rankArena }
            if (targetExist == null) {
                val tmpTarget = MyTarget()
                tmpTarget.jjcRank = rankArena
                arenaRanks.add(tmpTarget)
            }
        }

        arenaRanks.sortBy { it.jjcRank }

        // 把机器人和玩家的信息加入消息addRankInfo
        for (eachTarget in arenaRanks) {
            if (eachTarget.playerId == 0L) {
                val robotProtoId = findJjcRobotByRank(eachTarget.jjcRank)
                val queryRankInfoRt = QueryRankInfoRt.newBuilder()
                queryRankInfoRt.name = ""
                queryRankInfoRt.photoProtoId = 0
                queryRankInfoRt.score = 0
                queryRankInfoRt.allianceShortName = ""
                queryRankInfoRt.playerId = 0
                queryRankInfoRt.curentPos = 0
                queryRankInfoRt.nickName = ""
                queryRankInfoRt.robotProtoId = robotProtoId

                rt.addRankInfo(queryRankInfoRt)
                continue

            } else {
                val playerTarget = areaCache.playerCache.findPlayerById(eachTarget.playerId)
                if (playerTarget == null) {
                    continue
                }

                val currentPos = findOfficeByPlayerId(areaCache, eachTarget.playerId)
                val queryRankInfoRt = QueryRankInfoRt.newBuilder()
                queryRankInfoRt.name = playerTarget.name
                queryRankInfoRt.photoProtoId = playerTarget.photoProtoId
                queryRankInfoRt.allianceShortName = playerTarget.allianceShortName
                queryRankInfoRt.playerId = playerTarget.id
                queryRankInfoRt.curentPos = currentPos
                queryRankInfoRt.nickName = playerTarget.allianceNickName
                queryRankInfoRt.robotProtoId = 0

                // 求玩家的防守实力值
                val defForces = areaCache.armyPlanCache.findArmyPlan(eachTarget.playerId, JjcFight, JjcDef)
                var eachPlayerPower = 0L
                if (defForces == null) {
                    queryRankInfoRt.score = 0
                    rt.addRankInfo(queryRankInfoRt)
                    continue
                } else {
                    val defHeroMap = defForces.heroMap
                    for ((_, heroId) in defHeroMap) {
                        val tmpHero = areaCache.heroCache.findHeroById(eachTarget.playerId, heroId) ?: continue
                        eachPlayerPower += tmpHero.heroStrength
                    }
                    queryRankInfoRt.score = eachPlayerPower.toInt()
                    rt.addRankInfo(queryRankInfoRt)
                }
            }
        }

        // 自己的防守实力值
        val defForces = areaCache.armyPlanCache.findArmyPlan(player.id, JjcFight, JjcDef)
        if (defForces == null) {
            rt.myValue = 0
        } else {
            var myPower = 0L
            val defHeroMap = defForces.heroMap
            for ((_, heroId) in defHeroMap) {
                val tmpHero = areaCache.heroCache.findHeroById(player.id, heroId) ?: continue
                myPower += tmpHero.heroStrength
            }
            rt.myValue = myPower
        }

        // 自己的排名
        if (player.jjcRank < pcs.basicProtoCache.maxRankingNumeber) {
            rt.myRank = player.jjcRank
        } else if (player.jjcRank > pcs.basicProtoCache.maxRankingNumeber) {
            rt.myRank = 0
        }

        return rt
    }

}



