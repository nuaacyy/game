package com.point18.slg2d.world.module.boss

import akka.actor.ActorRef
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.tellNoSender
import com.point18.slg2d.common.msgtrans.ScMessageAtSend
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.module.ReqDealWithConn
import pb4client.AllianceHunterRankInfo
import pb4client.PersonalHunterRankInfo
import pb4client.QueryHunterRank
import pb4client.QueryHunterRankRt

//设置狩猎配置
class QueryHunterBossRankDeal : ReqDealWithConn() {

    override fun dealConnReq(cache: AreaCache, channelActor: ActorRef, msg: MessageLite, playerId: Long) {
        val queryRank = msg as QueryHunterRank
        val posX = queryRank.posX
        val posY = queryRank.posY
        val rtBuilder = dealHunterQueryRank(cache, posX, posY)
        val scMsg =
            ScMessageAtSend(MsgType.QueryHunterRank_1492, cache.currentClientMsgNo, rtBuilder.build())
        channelActor.tellNoSender(scMsg)
    }

    private fun dealHunterQueryRank(
        areaCache: AreaCache,
        posX: Int,
        posY: Int
    ): QueryHunterRankRt.Builder {
        val rtBuilder = QueryHunterRankRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val rankNum = pcs.basicProtoCache.monsterRankNum

        val commonBoss = areaCache.commonBossCache.findCommonBossByXY(posX, posY)
        if (commonBoss != null) {
            val monsterProto = pcs.monsterProtoCache.findMonsterProto(commonBoss.bossId)
            if (monsterProto == null) {
                rtBuilder.rt = ResultCode.NO_PROTO.code
                return rtBuilder
            }
            val unitProto = pcs.unitBaseCache.protoMap[monsterProto.unit]
            if (unitProto == null) {
                rtBuilder.rt = ResultCode.NO_PROTO.code
                return rtBuilder
            }

            val personalRank = areaCache.commonBossCache.findPersonalCommonBossRankByXY(posX, posY)
            if (personalRank != null) {
                val records = personalRank.rank.queryValue(rankNum)
                var index = 1
                for (record in records) {
                    val recordPlayer = areaCache.playerCache.findPlayerById(record.playerId)
                    if (recordPlayer == null) {
                        normalLog.error("找不到排行榜记录玩家信息:%d", record.playerId)
                        continue
                    }

                    val recordBuilder = PersonalHunterRankInfo.newBuilder()
                    recordBuilder.rank = index
                    recordBuilder.playerId = record.playerId
                    recordBuilder.allianceShortName = recordPlayer.allianceShortName
                    recordBuilder.playerName = recordPlayer.name
                    recordBuilder.photoId = recordPlayer.photoProtoId
                    recordBuilder.damageRate = (record.totalDamage / unitProto.hp * 10000).toInt()
                    rtBuilder.addPersonalRankInfos(recordBuilder)
                    index++
                }
            }
            val allianceRank = areaCache.commonBossCache.findAllianceCommonBossRankByXY(posX, posY)
            if (allianceRank != null) {
                val records = allianceRank.rank.queryValue(rankNum)
                var index = 1
                for (record in records) {
                    val recordAllianceMembers = areaCache.playerCache.findPlayersByAllianceId(record.allianceId)
                    if (recordAllianceMembers.isEmpty()) {
                        normalLog.error("找不到排行榜记录的联盟信息:%d", record.allianceId)
                        continue
                    }
                    val recordPlayer = recordAllianceMembers[0]
                    val recordBuilder = AllianceHunterRankInfo.newBuilder()
                    recordBuilder.rank = index
                    recordBuilder.allianceId = record.allianceId
                    recordBuilder.allianceShortName = recordPlayer.allianceShortName
                    recordBuilder.allianceName = recordPlayer.allianceName
                    var totalDamage = 0
                    record.memberRecordMap.forEach { totalDamage += it.value.totalDamage }
                    recordBuilder.damageRate = (totalDamage / unitProto.hp * 10000).toInt()
                    recordBuilder.flagColor = recordPlayer.flagColor
                    recordBuilder.flagEffect = recordPlayer.flagEffect
                    recordBuilder.flagStyle = recordPlayer.flagStyle
                    rtBuilder.addAllianceRankInfos(recordBuilder)
                    index++
                }
            }
            return rtBuilder
        }

        val callBoss = areaCache.callBossCache.findCallBossByXy(posX, posY)
        if (callBoss != null) {
            val monsterProto = pcs.monsterProtoCache.findMonsterProto(callBoss.bossId)
            if (monsterProto == null) {
                rtBuilder.rt = ResultCode.NO_PROTO.code
                return rtBuilder
            }

            val unitProto = pcs.unitBaseCache.protoMap[monsterProto.unit]
            if (unitProto == null) {
                rtBuilder.rt = ResultCode.NO_PROTO.code
                return rtBuilder
            }

            val personalRank = areaCache.callBossCache.findPersonalCallBossRank(callBoss)
            val personalRecords = personalRank.rank.queryValue(rankNum)
            for (index in personalRecords.indices) {
                val record = personalRecords[index]
                val recordPlayer = areaCache.playerCache.findPlayerById(record.playerId)
                if (recordPlayer == null) {
                    normalLog.error("找不到排行榜记录玩家信息:%d", record.playerId)
                    continue
                }
                val recordBuilder = PersonalHunterRankInfo.newBuilder()
                recordBuilder.rank = index
                recordBuilder.playerId = record.playerId
                recordBuilder.allianceShortName = recordPlayer.allianceShortName
                recordBuilder.playerName = recordPlayer.name
                recordBuilder.photoId = recordPlayer.photoProtoId
                recordBuilder.damageRate = (record.totalDamage / unitProto.hp * 10000).toInt()
                rtBuilder.addPersonalRankInfos(recordBuilder)
            }

            val allianceRank = areaCache.callBossCache.findAllianceCallBossRankByXY(callBoss)
            val allianceRecords = allianceRank.rank.queryValue(rankNum)
            for (index in allianceRecords.indices) {
                val record = allianceRecords[index]
                val recordAllianceMembers = areaCache.playerCache.findPlayersByAllianceId(record.allianceId)
                if (recordAllianceMembers.isEmpty()) {
                    normalLog.error("找不到排行榜记录的联盟信息:%d", record.allianceId)
                    continue
                }
                val recordPlayer = recordAllianceMembers[0]
                val recordBuilder = AllianceHunterRankInfo.newBuilder()
                recordBuilder.rank = index
                recordBuilder.allianceId = record.allianceId
                recordBuilder.allianceShortName = recordPlayer.allianceShortName
                recordBuilder.allianceName = recordPlayer.allianceName
                var totalDamage = 0
                record.memberRecordMap.forEach { totalDamage += it.value.totalDamage }
                recordBuilder.damageRate = (totalDamage / unitProto.hp * 10000).toInt()
                recordBuilder.flagColor = recordPlayer.flagColor
                recordBuilder.flagEffect = recordPlayer.flagEffect
                recordBuilder.flagStyle = recordPlayer.flagStyle
                rtBuilder.addAllianceRankInfos(recordBuilder)
            }
            return rtBuilder
        }

        val activityBoss = areaCache.activityBossCache.findActivityBossByXy(posX, posY)
        if (activityBoss != null) {
            val monsterProto = pcs.monsterProtoCache.findMonsterProto(activityBoss.bossId)
            if (monsterProto == null) {
                rtBuilder.rt = ResultCode.NO_PROTO.code
                return rtBuilder
            }

            val unitProto = pcs.unitBaseCache.protoMap[monsterProto.unit]
            if (unitProto == null) {
                rtBuilder.rt = ResultCode.NO_PROTO.code
                return rtBuilder
            }

            val personalRank = areaCache.activityBossCache.findPersonalActivityBossRankByXY(posX, posY)
            if (personalRank != null) {
                val records = personalRank.rank.queryValue(rankNum)
                var index = 1
                for (record in records) {
                    val recordPlayer = areaCache.playerCache.findPlayerById(record.playerId)
                    if (recordPlayer == null) {
                        normalLog.error("找不到排行榜记录玩家信息:%d", record.playerId)
                        continue
                    }
                    val recordBuilder = PersonalHunterRankInfo.newBuilder()
                    recordBuilder.rank = index
                    recordBuilder.playerId = record.playerId
                    recordBuilder.allianceShortName = recordPlayer.allianceShortName
                    recordBuilder.playerName = recordPlayer.name
                    recordBuilder.photoId = recordPlayer.photoProtoId
                    recordBuilder.damageRate = (record.totalDamage / unitProto.hp * 10000).toInt()
                    rtBuilder.addPersonalRankInfos(recordBuilder)
                    index++
                }
            }
            val allianceRank = areaCache.activityBossCache.findAllianceActivityBossRankByXY(posX, posY)
            if (allianceRank != null) {
                val records = allianceRank.rank.queryValue(rankNum)
                var index = 1
                for (record in records) {
                    val recordAllianceMembers = areaCache.playerCache.findPlayersByAllianceId(record.allianceId)
                    if (recordAllianceMembers.isEmpty()) {
                        normalLog.error("找不到排行榜记录的联盟信息:%d", record.allianceId)
                        continue
                    }
                    val recordPlayer = recordAllianceMembers[0]
                    val recordBuilder = AllianceHunterRankInfo.newBuilder()
                    recordBuilder.rank = index
                    recordBuilder.allianceId = record.allianceId
                    recordBuilder.allianceShortName = recordPlayer.allianceShortName
                    recordBuilder.allianceName = recordPlayer.allianceName
                    var totalDamage = 0
                    record.memberRecordMap.forEach { totalDamage += it.value.totalDamage }
                    recordBuilder.damageRate = (totalDamage / unitProto.hp * 10000).toInt()
                    recordBuilder.flagColor = recordPlayer.flagColor
                    recordBuilder.flagEffect = recordPlayer.flagEffect
                    recordBuilder.flagStyle = recordPlayer.flagStyle
                    rtBuilder.addAllianceRankInfos(recordBuilder)
                    index++
                }
            }
            return rtBuilder
        }

        rtBuilder.rt = ResultCode.MONSTER_NOT_EXIST.code
        return rtBuilder
    }
}