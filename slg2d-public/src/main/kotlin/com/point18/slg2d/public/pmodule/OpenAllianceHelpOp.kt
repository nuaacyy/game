package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.*
import pb4server.*
import com.point18.slg2d.common.resultcode.ResultCode

class OpenAllianceHelpOp : Home2PublicAskDealBase() {
    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val internalMsg = req.openAllianceHelpAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMsg)
        // 设置结果
        resp.setOpenAllianceHelpAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: OpenAllianceHelpAskReq
    ): OpenAllianceHelpAskRt.Builder {
        val rt = newOpenAllianceHelpAskRtBuilder()

        val alliance = publicCache.allianceCache.findAllianceById(allianceId)
        if (alliance == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        // 我的研发信息
        val allInfo = publicCache.allianceHelpCache.findAllianceHelpByAllianceId(alliance.id)
        val player = publicCache.allianceMemberCache.findAllianceMemberById(playerId)
        if (player == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        for (info in allInfo) {
            if (info.helpPlayerId == playerId) {
                // 玩家自己
                val myHelpVo = MyHelpVo.newBuilder()
                myHelpVo.helpId = info.id
                myHelpVo.helpType = info.helpType
                myHelpVo.maxHelpNum =
                        publicCache.allianceCache.getReallyAllianceCanHelpNum(player)
                myHelpVo.nowHelpNum = info.nowHelpNum
                myHelpVo.helpValue1 = info.helpValue1
                myHelpVo.helpValue2 = info.helpValue2
                myHelpVo.helpValue3 = info.helpValue3
                myHelpVo.helpValue4 = info.helpValue4

                rt.addMyHelpVo(myHelpVo)
            } else {
                val alliancePlayer =
                    publicCache.allianceMemberCache.findAllianceMemberById(info.helpPlayerId)

                if (alliancePlayer == null) {
                    continue
                }

                if (alliancePlayer.mapPltAreaId != player.mapPltAreaId) {
                    continue
                }

                if (info.helperIds.contains(playerId)) {
                    continue
                }

                val allianceHelpVo = AllianceHelpVo.newBuilder()
                allianceHelpVo.helpId = info.id
                allianceHelpVo.helpType = info.helpType
                allianceHelpVo.maxHelpNum =
                        publicCache.allianceCache.getReallyAllianceCanHelpNum(alliancePlayer)
                allianceHelpVo.nowHelpNum = info.nowHelpNum
                allianceHelpVo.helpValue1 = info.helpValue1
                allianceHelpVo.helpValue2 = info.helpValue2
                allianceHelpVo.helpValue3 = info.helpValue3
                allianceHelpVo.helpValue4 = info.helpValue4
                allianceHelpVo.helpPlayerId = info.helpPlayerId
                allianceHelpVo.helpPlayerName = alliancePlayer.name
                allianceHelpVo.photoId = alliancePlayer.photoProtoId

                rt.addAllianceHelpVos(allianceHelpVo)
            }
        }

        return rt
    }

    fun newOpenAllianceHelpAskRtBuilder(): OpenAllianceHelpAskRt.Builder {
        val rt = OpenAllianceHelpAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}
