package com.point18.slg2d.world.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.ALLIANCE_OPEN
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.module.uiCondition.uiConditionCheck
import pb4client.AllianceQueryListInfo
import pb4client.QueryApplyAllianceListRt
import pb4server.QueryApplyAllianceListAskReq
import pb4server.World2PublicAskResp

// 查询已申请联盟列表 838
class QueryApplyAllianceList : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = this.queryList(session)
        if (rt != null) {
            session.sendMsg(MsgType.QueryApplyAllianceList_838, rt)
        }
    }

    fun queryList(session: PlayerSession): (QueryApplyAllianceListRt?) {
        val crt = QueryApplyAllianceListRt.newBuilder()
        crt.rt = ResultCode.SUCCESS.code

        val player = session.player

        // 功能开启
        val uiConditionRt = uiConditionCheck(session.areaCache, player, ALLIANCE_OPEN)
        if (uiConditionRt != ResultCode.SUCCESS.code) {
            crt.rt = uiConditionRt
            return crt.build()
        }

        if (player.joinAllianceReqs.size == 0) {
            return crt.build()
        }

        val req = QueryApplyAllianceListAskReq.newBuilder()
        val areaCache = session.areaCache
        val playerId = session.playerId

        var nowIndex = 0
        for ((reqId, _) in player.joinAllianceReqs) {
            areaCache.createACS(
                areaCache.worldActor.publicShardRegion,
                areaCache.fillW2PAskMsgHeader(reqId, playerId) {
                    it.setQueryApplyAllianceListAskReq(req)
                },
                World2PublicAskResp::class.java
            ) { askResp, err ->
                try {
                    when {
                        err != null -> {
                            crt.rt = ResultCode.ASK_ERROR1.code
                        }
                        askResp == null -> {
                            crt.rt = ResultCode.ASK_ERROR2.code
                        }
                        else -> {
                            val rt = askResp.queryApplyAllianceListAskRt
                            if (rt.rt == ResultCode.SUCCESS.code) {
                                val allianceQueryListInfo = AllianceQueryListInfo.newBuilder()
                                allianceQueryListInfo.id = rt.alliance.id
                                allianceQueryListInfo.name = rt.alliance.name
                                allianceQueryListInfo.shortName = rt.alliance.shortName
                                allianceQueryListInfo.reservePlayers = rt.alliance.reservePlayers
                                allianceQueryListInfo.powerValue = rt.alliance.powerValue
                                allianceQueryListInfo.operate = 1
                                allianceQueryListInfo.allianceLan = rt.alliance.allianceLan
                                allianceQueryListInfo.canAddPower = rt.alliance.canAddPower
                                allianceQueryListInfo.canReqPower = rt.alliance.canReqPower
                                allianceQueryListInfo.flagColor = rt.alliance.flagColor
                                allianceQueryListInfo.flagStyle = rt.alliance.flagStyle
                                allianceQueryListInfo.flagEffect = rt.alliance.flagEffect
                                allianceQueryListInfo.alliancePower = rt.alliance.alliancePower
                                allianceQueryListInfo.giftLv = rt.alliance.giftLv
                                allianceQueryListInfo.areaNo = rt.alliance.areaNo

                                crt.addAlliances(allianceQueryListInfo.build())
                            }
                            crt.rt = rt.rt
                        }
                    }
                } catch (e: Exception) {
                    normalLog.error("QueryApplyAllianceList Error!", e)
                    crt.rt = ResultCode.ASK_ERROR3.code
                }

                nowIndex++  // 回来一次自增一次 如果自增到了最后一个 就返回消息给客户端

                if (nowIndex == player.joinAllianceReqs.size) {
                    session.sendMsg(MsgType.QueryApplyAllianceList_838, crt.build())
                }
            }
        }


        return null
    }

}

