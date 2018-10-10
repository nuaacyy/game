package com.point18.slg2d.public.pmodule

import pb4server.*
import com.point18.slg2d.public.common.allianceHelpInfoChange
import com.point18.slg2d.public.common.dealAfterHelp
import com.point18.slg2d.public.common.dealHelperNotice
import com.point18.slg2d.public.datacache.*
import com.point18.slg2d.common.constg.REMOVE_ALLIANCE_INFO
import com.point18.slg2d.common.constg.boolToInt
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*

class GoAllianceHelpOp : Home2PublicAskDealBase() {
    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val internalMsg = req.goAllianceHelpAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMsg)
        // 设置结果
        resp.setGoAllianceHelpAskRt(rt)
    }

    fun dealMsg(
            publicCache: PublicCache,
            allianceId: Long,
            playerId: Long,
            req: GoAllianceHelpAskReq
    ): GoAllianceHelpAskRt.Builder {
        val rt = newGoAllianceHelpAskRtBuilder()
        val helpIds = req.helpIdsList

        val alliance = publicCache.allianceCache.findAllianceById(allianceId)
        if (alliance == null) {
            rt.rt = ResultCode.ALLIANCE_QUERY_NOT_EXIST.code
            return rt
        }

        val allianceMemberTrace =
                publicCache.allianceMemberTraceCache.findAllianceMemberTraceByAllianceIdAndPlayerId(
                        allianceId,
                        playerId
                )
        if (allianceMemberTrace == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        val helpAllianceMember = publicCache.allianceMemberCache.findAllianceMemberById(playerId)
        if (helpAllianceMember == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        val helpPlayers = mutableMapOf<Long, AllianceMember>()// 被帮助的人的信息集合  key:ID  value :1
        val helpTypeInfos = mutableMapOf<Long, Int>()

        val hId = LinkedList<Long>()
        for (helpId in helpIds) {
            val helpVo = publicCache.allianceHelpCache.findAllianceHelpById(helpId)
            if (helpVo == null) {
                continue
            }
            if (helpVo.allianceId != alliance.id) {
                continue
            }

            val helpPlayer =
                    publicCache.allianceMemberCache.findAllianceMemberById(helpVo.helpPlayerId)
            if (helpPlayer == null) {
                continue
            }
            if (helpAllianceMember.mapPltAreaId != helpPlayer.mapPltAreaId) {
                continue
            }

            if (helpVo.helpPlayerId == playerId) {
                continue
            }

            if (helpVo.nowHelpNum >= publicCache.allianceCache.getReallyAllianceCanHelpNum(helpPlayer)
            ) {
                continue
            }

            if (helpVo.helperIds.contains(playerId)) {
                continue
            }

            helpVo.nowHelpNum += 1
            // 推送给客户端变更
            dealAfterHelp(
                    publicCache.publicActor, helpPlayer.mapPltAreaId, helpVo.helpPlayerId, helpVo.helpType,
                    helpVo.helpValue1, helpVo.helpValue2, helpVo.helpValue3, helpVo.helpValue4, playerId
            )

            helpPlayers[helpVo.helpPlayerId] = helpPlayer

            hId.add(helpId)

            allianceMemberTrace.weekHelp += 1

            helpVo.helperIds.add(playerId)
            rt.helpSuccess = boolToInt(true)
        }

        val playerIds = LinkedList<Long>()
        playerIds.add(helpAllianceMember.id)

        allianceHelpInfoChange(
                publicCache.publicActor,
                helpAllianceMember.mapPltAreaId,
                playerIds,
                REMOVE_ALLIANCE_INFO,
                hId
        )

        for ((helpPlayerId, helpPlayer) in helpPlayers) {
            // 被帮助的玩家获得提示
            var helpType = 0
            val ht = helpTypeInfos[helpPlayerId]
            if (ht != null) {
                helpType = ht
            }
            dealHelperNotice(
                    publicCache.publicActor,
                    helpPlayer.mapPltAreaId,
                    helpPlayerId,
                    helpAllianceMember.name,
                    helpType
            )
        }

        return rt
    }

    fun newGoAllianceHelpAskRtBuilder(): GoAllianceHelpAskRt.Builder {
        val rt = GoAllianceHelpAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}