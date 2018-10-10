package com.point18.slg2d.public.pmodule

import pb4server.*
import com.point18.slg2d.public.common.dealAfterSetAllianceMark
import com.point18.slg2d.public.datacache.*
import com.point18.slg2d.common.resultcode.ResultCode

class RemoveAllianceMarkSyncOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
            publicCache: PublicCache,
            req: World2PublicAskReq,
            resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.removeAllianceMarkAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMessage)

        resp.setRemoveAllianceMarkAskRt(rt)
    }

    fun dealMsg(
            publicCache: PublicCache,
            allianceId: Long,
            playerId: Long,
            req: RemoveAllianceMarkAskReq
    ): RemoveAllianceMarkAskRt.Builder {
        val rt = newRemoveAllianceMarkAskRtBuilder()

        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        if (alce == null) {
            rt.rt = ResultCode.ALLIANCE_QUERY_NOT_EXIST.code
            return rt
        }
        val allianceMember = publicCache.allianceMemberCache.findAllianceMemberById(playerId)
        // 验证权限

        if (allianceMember == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        if (!hasRight(allianceMember, com.point18.slg2d.common.constg.A_RIGHT_MARK)) {
            rt.rt = ResultCode.ALLIANCE_POSITION_RIGHT_FORBIDDEN.code
            return rt
        }

        val mark = publicCache.allianceMarkCache.findMarksByAllianceIdAndId(
                allianceMember.allianceId,
                req.markId
        )
        if (mark == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        publicCache.allianceMarkCache.deleteAllianceMark(mark)
        insertLog(publicCache, alce.id, com.point18.slg2d.common.constg.A_LOG_DELETE_ALLIANCE_MARK, allianceMember.name, allianceMember.allianceNickName, mark.title)

        // 向联盟成员推送联盟标记信息
        val allAllianceMember =
                publicCache.allianceMemberCache.findAllianceMembersByAllianceId(allianceId)

        /*val allianceMarkInfoVo = AllianceMarkInfoVo()
        allianceMarkInfoVo.type = mark.type
        allianceMarkInfoVo.playerId = allianceMember.id
        allianceMarkInfoVo.playerName = allianceMember.name
        allianceMarkInfoVo.x = mark.x
        allianceMarkInfoVo.y = mark.y
        allianceMarkInfoVo.title = mark.title
        allianceMarkInfoVo.desp = mark.description
        allianceMarkInfoVo.markTime = mark.markTime
        allianceMarkInfoVo.markId = mark.id
        allianceMarkInfoVo.pltAreaNo = mark.pltAreaNo
        for ((p, _) in allianceMember.alliancePosMap) {
            allianceMarkInfoVo.positions.add(p)
        }*/
        val allianceMarkInfoVo = AllianceMarkInfoVo.newBuilder()
        allianceMarkInfoVo.type = mark.type
        allianceMarkInfoVo.playerId = allianceMember.id
        allianceMarkInfoVo.playerName = allianceMember.name
        allianceMarkInfoVo.x = mark.x
        allianceMarkInfoVo.y = mark.y
        allianceMarkInfoVo.title = mark.title
        allianceMarkInfoVo.desp = mark.description
        allianceMarkInfoVo.markTime = mark.markTime
        allianceMarkInfoVo.markId = mark.id
        allianceMarkInfoVo.pltAreaNo = mark.pltAreaNo
        allianceMarkInfoVo.photoProtoId = 0
        for ((p, _) in allianceMember.alliancePosMap) {
            allianceMarkInfoVo.addPositions(p)
        }


        val pltAreas = mutableMapOf<Long, Int>()

        for (am in allAllianceMember) {
            pltAreas[am.mapPltAreaId] = 1
        }

        for ((pltAreaId, _) in pltAreas) {
            // dealAfterSetAllianceMark(pltAreaId, allianceMarkInfoVo, 3, allianceId)
            dealAfterSetAllianceMark(publicCache.publicActor, pltAreaId, allianceMarkInfoVo.build(), 3, allianceId)
        }

        return rt
    }

    fun newRemoveAllianceMarkAskRtBuilder(): RemoveAllianceMarkAskRt.Builder {
        val rt = RemoveAllianceMarkAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}