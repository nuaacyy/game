package com.point18.slg2d.world.module.transport

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.resStringToResVo
import com.point18.slg2d.common.pc.resStringToResVoList
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.msgnotice.createTransportRequestChangeNotifier
import pb4client.PublishTransportRequest
import pb4client.PublishTransportRequestRt
import java.util.*

class PublishTransportRequestDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val publishMsg = msg as PublishTransportRequest
        val rt = this.publishTransportRequest(session, publishMsg)
        session.sendMsg(MsgType.PublishTransportRequest_1262, rt)
    }

    fun publishTransportRequest(
        session: PlayerSession,
        publishMsg: PublishTransportRequest
    ): PublishTransportRequestRt {
        val rtBuilder = PublishTransportRequestRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val areaCache = session.areaCache
        val playerId = session.playerId
        val resStr = publishMsg.res
        //校验发布请求的资源参数
        val finalResVos = LinkedList<ResVo>()
        val resVos = resStringToResVoList(resStr)
        if (resVos == null) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder.build()
        }
        for (resVo in resVos) {
            if (!isTransportResource(resVo.resType)) {
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder.build()
            }
            if (resVo.num > 0) {
                finalResVos.add(resVo)
            }
        }
        if (finalResVos.count() == 0) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder.build()
        }

        //校验联盟及权限
        val player = session.player

        if (player.allianceId == 0L) {
            rtBuilder.rt = ResultCode.ALLIANCE_PLAYER_NO_JOIN.code
            return rtBuilder.build()
        }
        if (!player.getAlliancePosMapValue(ALLIANCE_POSITION_BOSS)
            && !player.getAlliancePosMapValue(ALLIANCE_POSITION_ASSISTANT)
            && !player.getAlliancePosMapValue(ALLIANCE_POSITION_MANAGER)) {
            rtBuilder.rt = ResultCode.ALLIANCE_POSITION_NO_ENERGH.code
            return rtBuilder.build()
        }

        //校验是否已发布请求
        val playerReq = areaCache.transportRequestCache.findTransportRequestByPlayerId(playerId)
        if (playerReq != null) {
            //先删除旧的运输
            deleteTransport(areaCache, player, playerReq.id)
        }

        //生成发布信息
        val req = areaCache.transportRequestCache.createTransportRequest(player, finalResVos)

        //广播运输请求

        val allianceMembers = areaCache.playerCache.findPlayersByAllianceId(player.allianceId)
        for (member in allianceMembers) {
            val eachSession = fetchOnlinePlayerSession(areaCache, member.id)
            if (eachSession != null) {
                val notifier = createTransportRequestChangeNotifier(player, req, Add)
                notifier.notice(eachSession)
            }
        }

        return rtBuilder.build()
    }

}


