package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.constg.SYS_MAIL
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.SendAllianceAwardMailAskReq
import pb4server.SendAllianceAwardMailAskRt
import pb4server.World2PublicAskReq
import pb4server.World2PublicAskResp
import com.point18.slg2d.public.common.sendMailToPlayer
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*

class SendAllianceAwardMailOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(publicCache: PublicCache, req: World2PublicAskReq, resp: World2PublicAskResp.Builder) {
        val internalMessage = req.sendAllianceAwardMailAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val worldId = req.worldId
        val rt = dealMsg(publicCache, allianceId, playerId, worldId, internalMessage)

        resp.setSendAllianceAwardMailAskRt(rt)
    }

    fun dealMsg(publicCache: PublicCache,
                allianceId: Long,
                playerId: Long,
                worldId: Long,
                req: SendAllianceAwardMailAskReq): SendAllianceAwardMailAskRt.Builder {

        val rt = SendAllianceAwardMailAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val alliance = publicCache.allianceCache.findAllianceById(allianceId)
        if (alliance == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        val allMembers = publicCache.allianceMemberCache.findAllianceMembersByAllianceId(alliance.id)

        allMembers.groupBy { it.mapPltAreaId }.forEach { pltAreaId, members ->
            val memberIds = LinkedList<Long>()
            members.forEach { memberIds.add(it.id) }
            val titleParasList = LinkedList<String>()
            for (p in req.titleParasList) {
                titleParasList.add(p)
            }

            val contentParas = LinkedList<String>()
            for (t in req.contentParasList) {
                contentParas.add(t)
            }
            sendMailToPlayer(
                    publicCache.publicActor,
                    pltAreaId,
                    memberIds,
                    req.readType,
                    req.title,
                    titleParasList,
                    req.content,
                    contentParas,
                    0,
                    SYS_MAIL,
                    req.attach,
                    0,
                    ""
            )
        }
        return rt
    }
}