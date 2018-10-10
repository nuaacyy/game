package com.point18.slg2d.world.module.playerActivity

import com.point18.slg2d.world.area4data.*
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.constg.ALLIANCE_ACTIVITY
import pb4client.PlayerActivityRankVo
import pb4client.SelectNowRank
import pb4client.SelectNowRankRt
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*

// 查询当前活动排行
class SelectNowRankDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val activityId = (msg as SelectNowRank).activityId
        val (rt, isRpc) = selectNowRank(session, activityId)

        if (!isRpc && rt != null) {
            session.sendMsg(MsgType.SelectNowRank_1336, rt)
        }
    }

    data class SelectNowRankReturn(var selectNowRankRt: SelectNowRankRt?, var isRpc: Boolean)

    private fun selectNowRank(session: PlayerSession, activityId: Int): (SelectNowRankReturn) {
        val rt = SelectNowRankRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val areaCache = session.areaCache

        val player = session.player

        val activityP = pcs.eventTimeProtoCache.protoMap[activityId]
        if (activityP == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return SelectNowRankReturn(rt.build(), false)
        }
        if (activityP.eventType != ALLIANCE_ACTIVITY) {
            // 个人挑战
            val activityVo = areaCache.serverActivityCache.findServerActivityByActivityId(activityId)
            if (activityVo == null) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return SelectNowRankReturn(rt.build(), false)
            }
            val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
            if (castle == null) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return SelectNowRankReturn(rt.build(), false)
            }
            var castleLv = castle.lv
            val playerActivity = areaCache.playerActivityCache.findPlayerActivityByPlayerIdAndActivityId(session.playerId, activityId)
            if (playerActivity != null) {
                castleLv = playerActivity.castleLv
            }

            val eventInProtoMap = pcs.eventInformationProtoCache.protoMap[activityId]
            if (eventInProtoMap == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return SelectNowRankReturn(rt.build(), false)
            }

            val eventInProto = eventInProtoMap[castleLv]
            if (eventInProto == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return SelectNowRankReturn(rt.build(), false)
            }

            val allJoinActivityPlayersByCastleLv = areaCache.playerActivityCache.findAllPlayerActivityInfoMap(activityId)
            val p = allJoinActivityPlayersByCastleLv[eventInProto.id]
            if (p == null) {
                return SelectNowRankReturn(rt.build(), false)
            } else {
                val pars = LinkedList<PlayerActivityRankVo>()
                p.sortByDescending { it.score }
                for (info in p) {
                    val par = pb4client.PlayerActivityRankVo.newBuilder()
                    val pInfo = areaCache.playerCache.findPlayerById(info.playerId)
                    if (pInfo == null) {
                        continue
                    }
                    par.playerId = info.playerId
                    par.playerName = pInfo.name
                    par.nickName = pInfo.allianceNickName
                    par.allianceShortName = pInfo.allianceShortName
                    par.score = info.score
                    par.photoId = pInfo.photoProtoId

                    pars.add(par.build())
                }

                rt.addAllPActivityRankVos(pars)
            }

            return SelectNowRankReturn(rt.build(), false)
        } else {
            // 联盟挑战
            // 查询的是联盟活动,去联盟服拿数据
            com.point18.slg2d.world.common.selectNowRank(session, activityId)
            return SelectNowRankReturn(null, true)
        }
    }
}
