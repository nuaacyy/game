package com.point18.slg2d.public.pmodule

import pb4server.SendAllianceMailAskReq
import pb4server.SendAllianceMailAskRt
import pb4server.World2PublicAskReq
import pb4server.World2PublicAskResp
import com.point18.slg2d.public.common.sendMailToPlayer
import com.point18.slg2d.public.datacache.*
import com.point18.slg2d.common.constg.ALLIANCE_MAIL
import com.point18.slg2d.common.constg.ALLIANCE_POSITION_BOSS
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*

class SendAllianceMailSyncOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(publicCache: PublicCache, req: World2PublicAskReq, resp: World2PublicAskResp.Builder) {
        val internalMessage = req.sendAllianceMailAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val worldId = req.worldId
        val rt = dealMsg(publicCache, allianceId, playerId, worldId, internalMessage)

        resp.setSendAllianceMailAskRt(rt)
    }

    fun dealMsg(publicCache: PublicCache,
                allianceId: Long,
                playerId: Long,
                worldId: Long,
                req: SendAllianceMailAskReq): SendAllianceMailAskRt.Builder {

        val rt = SendAllianceMailAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val member = publicCache.allianceMemberCache.findAllianceMemberById(playerId)
        if (member == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        val alliance = publicCache.allianceCache.findAllianceById(member.allianceId)
        if (alliance == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        if (!playerIsHavePos(member, ALLIANCE_POSITION_BOSS)) {
            rt.rt = ResultCode.ALLIANCE_POSITION_NO_ENERGH.code
            return rt
        }

        val allMembers =
                publicCache.allianceMemberCache.findAllianceMembersByAllianceId(member.allianceId)

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
                    ALLIANCE_MAIL,
                    "",
                    0,
                    "",
                    member.id,
                    member.name,
                    member.allianceNickName,
                    alliance.id,
                    alliance.name,
                    alliance.shortName
            )
        }
        return rt
    }
}