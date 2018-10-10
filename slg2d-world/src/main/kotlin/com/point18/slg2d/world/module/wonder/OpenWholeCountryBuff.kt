package com.point18.slg2d.world.module.wonder

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNextGameRefTime
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.BUFF_ADD
import com.point18.slg2d.common.constg.CountryBuffs
import com.point18.slg2d.common.constg.OfficeFunction
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.common.syncdomain.CountryBuffData
import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.common.checkOfficeFunction
import com.point18.slg2d.world.common.findOfficeByPlayerId
import com.point18.slg2d.world.common.isWonderPeace
import com.point18.slg2d.world.common.syncData2Home
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.msgnotice.createBuffChangeNotifier
import com.point18.slg2d.world.wpm
import pb4client.OpenWholeCountryBuff
import pb4client.OpenWholeCountryBuffRt
import java.util.*
import java.util.Arrays.asList

class OpenWholeCountryBuffDeal : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val openBuffMsg = msg as OpenWholeCountryBuff
        val buffId = openBuffMsg.buffId
        val rtBuilder = this.openBuff(session, buffId) ?: return
        session.sendMsg(MsgType.OpenWholeCountryBuff_1457, rtBuilder.build())
    }

    private fun openBuff(session: PlayerSession, buffId: Int): OpenWholeCountryBuffRt.Builder? {
        val rtBuilder = OpenWholeCountryBuffRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val areaCache = session.areaCache
        val playerId = session.playerId
        val wonder = areaCache.wonderCache.findBigWonder()
        if (wonder == null) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        val buffProto = pcs.kingBuffProtoCache.kingBuffProtoMap[buffId]
        if (buffProto == null) {
            rtBuilder.rt = ResultCode.NO_PROTO.code
            return rtBuilder
        }

        val buffBasicProto = pcs.buffBasicProtoCache.protoMap[buffProto.buff]
        if (buffBasicProto == null) {
            rtBuilder.rt = ResultCode.NO_PROTO.code
            return rtBuilder
        }

        val top1BuffBasicProto = pcs.buffBasicProtoCache.protoMap[buffProto.buffRankList[0]]
        if (top1BuffBasicProto == null) {
            rtBuilder.rt = ResultCode.NO_PROTO.code
            return rtBuilder
        }

        val top2BuffBasicProto = pcs.buffBasicProtoCache.protoMap[buffProto.buffRankList[1]]
        if (top2BuffBasicProto == null) {
            rtBuilder.rt = ResultCode.NO_PROTO.code
            return rtBuilder
        }

        val top3BuffBasicProto = pcs.buffBasicProtoCache.protoMap[buffProto.buffRankList[2]]
        if (top3BuffBasicProto == null) {
            rtBuilder.rt = ResultCode.NO_PROTO.code
            return rtBuilder
        }

        //校验奇观状态
        if (!isWonderPeace(wonder)) {
            rtBuilder.rt = ResultCode.WONDER_NOT_PEACE.code
            return rtBuilder
        }

        //校验Buff状态
        /*val kingBuff = wonder.buffMap[buffId]
        if (kingBuff != null && kingBuff.startTime < getNowTime() && getNowTime() < kingBuff.endTime) {
            //buff生效中
            rtBuilder.rt = ResultCode.WHOLE_COUNTRY_BUFF_IN_ACTIVE.code
            return rtBuilder
        }*/

        if (getNowTime() < wonder.buffCoolTime) {
            //buff冷却中
            rtBuilder.rt = ResultCode.WHOLE_COUNTRY_BUFF_IN_COOL.code
            return rtBuilder
        }

        //校验权限
        val player = session.player

        if (player.allianceId == 0L || player.allianceId != wonder.belongToAllianceId) {
            rtBuilder.rt = ResultCode.LIMIT_TO_OPEN_COUNTRY_BUFF.code
            return rtBuilder
        }
        val posId = findOfficeByPlayerId(areaCache, playerId)
        val ok = checkOfficeFunction(OfficeFunction.SendCountryBuff, posId)
        if (!ok) {
            rtBuilder.rt = ResultCode.LIMIT_TO_OPEN_COUNTRY_BUFF.code
            return rtBuilder
        }

        //创建Buff
        val nowTime = getNowTime()
        val commonBuff = KingBuff(
            wpm.generateObjIdNew(areaCache),
            buffProto.buff,
            nowTime,
            nowTime + buffBasicProto.time * 1000
        ) // 通用buff
        val top1Buff = KingBuff(
            wpm.generateObjIdNew(areaCache),
            buffProto.buffRankList[0],
            nowTime,
            nowTime + top1BuffBasicProto.time * 1000
        ) // 争夺战第一名额外buff
        val top2Buff = KingBuff(
            wpm.generateObjIdNew(areaCache),
            buffProto.buffRankList[1],
            nowTime,
            nowTime + top2BuffBasicProto.time * 1000
        ) // 争夺战第二名额外buff
        val top3Buff = KingBuff(
            wpm.generateObjIdNew(areaCache),
            buffProto.buffRankList[2],
            nowTime,
            nowTime + top3BuffBasicProto.time * 1000
        ) // 争夺战第三名额外buff

        /** 已经存在的会被替换 **/
        wonder.buffMap[buffId] = LinkedList(asList(commonBuff, top1Buff, top2Buff, top3Buff))

        // 设置冷却时间
        wonder.buffCoolTime = getNextGameRefTime()

        // 向所有在线玩家推送
        val notifier = createBuffChangeNotifier(
            BUFF_ADD,
            commonBuff.id,
            commonBuff.protoId,
            commonBuff.endTime,
            commonBuff.startTime
        )
        val allSessions = fetchAllOnlinePlayerSessions(areaCache)
        for ((_, onlineSession) in allSessions) {
            notifier.notice(onlineSession)
        }

        val rankList = LinkedList<WonderRankVo>(wonder.rankInfoMap.values)
        rankList.sortByDescending { it.score }
        val top1AllianceId = if (rankList.size >= 1) rankList[0].allianceId else null
        val top2AllianceId = if (rankList.size >= 2) rankList[1].allianceId else null
        val top3AllianceId = if (rankList.size >= 3) rankList[2].allianceId else null

        // 向第一名联盟推送
        if (top1AllianceId != null) {
            val top1Notifier = createBuffChangeNotifier(
                BUFF_ADD,
                top1Buff.id,
                top1Buff.protoId,
                top1Buff.endTime,
                top1Buff.startTime
            )
            for (member in areaCache.playerCache.findPlayersByAllianceId(top1AllianceId)) {
                val eachSession = fetchOnlinePlayerSession(areaCache, member.id)
                if (eachSession != null) {
                    top1Notifier.notice(eachSession)
                }
            }
        }

        // 向第二名联盟推送
        if (top2AllianceId != null) {
            val top2Notifier = createBuffChangeNotifier(
                BUFF_ADD,
                top2Buff.id,
                top2Buff.protoId,
                top2Buff.endTime,
                top2Buff.startTime
            )
            for (member in areaCache.playerCache.findPlayersByAllianceId(top2AllianceId)) {
                val eachSession = fetchOnlinePlayerSession(areaCache, member.id)
                if (eachSession != null) {
                    top2Notifier.notice(eachSession)
                }
            }
        }

        // 向第三名联盟推送
        if (top3AllianceId != null) {
            val top3Notifier = createBuffChangeNotifier(
                BUFF_ADD,
                top3Buff.id,
                top3Buff.protoId,
                top3Buff.endTime,
                top3Buff.startTime
            )
            for (member in areaCache.playerCache.findPlayersByAllianceId(top3AllianceId)) {
                val eachSession = fetchOnlinePlayerSession(areaCache, member.id)
                if (eachSession != null) {
                    top3Notifier.notice(eachSession)
                }
            }
        }

        rtBuilder.coolEndTime = (wonder.buffCoolTime / 1000).toInt()

        //同步效果至home
        val commonBuffs = LinkedList<CountryBuffData>()
        val top1Buffs = LinkedList<CountryBuffData>()
        val top2Buffs = LinkedList<CountryBuffData>()
        val top3Buffs = LinkedList<CountryBuffData>()
        wonder.buffMap.forEach { _, buff ->
            val commonBuffData = CountryBuffData(buff[0].id, buff[0].protoId, buff[0].startTime, buff[0].endTime)
            val top1BuffData = CountryBuffData(buff[1].id, buff[1].protoId, buff[1].startTime, buff[1].endTime)
            val top2BuffData = CountryBuffData(buff[2].id, buff[2].protoId, buff[2].startTime, buff[2].endTime)
            val top3BuffData = CountryBuffData(buff[3].id, buff[3].protoId, buff[3].startTime, buff[3].endTime)

            commonBuffs.add(commonBuffData)
            top1Buffs += arrayOf(commonBuffData, top1BuffData)
            top2Buffs += arrayOf(commonBuffData, top2BuffData)
            top3Buffs += arrayOf(commonBuffData, top3BuffData)
        }

        val allPlayers = areaCache.playerCache.findAllPlayers()
        allPlayers.forEach {
            when {
                it.allianceId == top1AllianceId -> {
                    syncData2Home(areaCache, it.id, CountryBuffs, toJson(top1Buffs))
                }
                it.allianceId == top2AllianceId -> {
                    syncData2Home(areaCache, it.id, CountryBuffs, toJson(top2Buffs))
                }
                it.allianceId == top3AllianceId -> {
                    syncData2Home(areaCache, it.id, CountryBuffs, toJson(top3Buffs))
                }
                else -> {
                    syncData2Home(areaCache, it.id, CountryBuffs, toJson(commonBuffs))
                }
            }
        }

        return rtBuilder
    }
}


