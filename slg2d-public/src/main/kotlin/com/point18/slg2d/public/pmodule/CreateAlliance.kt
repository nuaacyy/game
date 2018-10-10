package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.constg.ADD_ALLIANCE_INFO
import com.point18.slg2d.common.constg.ALLIANCE_NAME_TRY2USE
import pb4server.*
import com.point18.slg2d.public.common.allianceReqInfoChange
import com.point18.slg2d.public.datacache.*
import com.point18.slg2d.public.datacache.AllianceBossVo
import com.point18.slg2d.common.constg.REMOVE_ALLIANCE_INFO
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.ppm
import java.util.*

class CreateAllianceSyncOp : World2PublicAskDealBase() {

    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val createAllianceAskReq = req.createAllianceAskReq
        val allianceId = req.publicId
        val rt = createAlliance(publicCache, allianceId, createAllianceAskReq)

        // 设置结果
        resp.createAllianceAskRt = rt.build()
    }

    private fun createAlliance(
        publicCache: PublicCache,
        allianceId: Long,
        createAllianceReq: CreateAllianceAskReq
    ): CreateAllianceAskRt.Builder {
        val rt = newCreateAllianceAskRtBuilder()

        val giftInfo = com.point18.slg2d.common.pc.pcs.allianceGiftLevelProtoCache.randGetGiftProtoId(0)
        if (giftInfo == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt
        }

        val allianceInfo = publicCache.allianceCache.createAlliance(
            allianceId,
            createAllianceReq.name,
            createAllianceReq.shortName,
            createAllianceReq.pid,
            createAllianceReq.allianceLan,
            createAllianceReq.power,
            createAllianceReq.gameAreaNo
        )

        // 生成联盟礼物信息 todo=====================
        val allianceMember = publicCache.allianceMemberCache.createAllianceMember(
            allianceId,
            createAllianceReq.pid,
            createAllianceReq.playerName,
            createAllianceReq.power,
            createAllianceReq.lastLeaveTime,
            createAllianceReq.honor.toInt(),
            createAllianceReq.canHelpNum,
            1,
            createAllianceReq.gamePltAreaNo,
            createAllianceReq.mapPltAreaNo,
            createAllianceReq.photoProtoId,
            1,
            createAllianceReq.gameAreaNo,
            createAllianceReq.playerCastleLv
        )

        val allianceMemberTrace =
            publicCache.allianceMemberTraceCache.createAllianceMemberTrace(createAllianceReq.pid, allianceId)

        val pltAreas = mutableMapOf<Long, LinkedList<Long>>()
        val ps = pltAreas.getOrPut(allianceMember.mapPltAreaId) { LinkedList() }
        ps.add(allianceMember.id)

        // todo 临时在这里加上一个boss活跃度初始化,以后是跟着活动走的
        val bossInfo = mutableMapOf<Int, LinkedList<AllianceBossVo>>()
        for ((score, vo) in com.point18.slg2d.common.pc.pcs.monsterAllianceScoreProtoCache.monsterAllianceScoreProtoMap) {
            val bi = bossInfo.getOrPut(score) { LinkedList() }
            for (bossP in vo.monsterIdMap) {
                for (i in 1..bossP.bossNum) {
                    bi.add(AllianceBossVo(bossP.bossId, 0))
                }
            }
            bossInfo[score] = bi
        }

        allianceInfo.allianceBossInfoMap = bossInfo

        publicCache.allianceGiftCache.createAllianceGift(giftInfo.id, allianceInfo.id)

        allianceMember.setWrapPosition(com.point18.slg2d.common.constg.ALLIANCE_POSITION_BOSS)

        // 可能该玩家之前已经申请了其他联盟，所以需要删除该玩家加入其他联盟的申请
        val pReqs = publicCache.allianceReqCache.findReqsByPlayerId(createAllianceReq.pid)
        for (pReq in pReqs) {
            publicCache.allianceReqCache.deleteAllianceReq(pReq)

            // val cReq = AllianceQueryReqListInfoVo()
            val cReq = AllianceQueryReqListInfoVo.newBuilder()
            cReq.id = pReq.playerId
            cReq.name = pReq.playerName
            cReq.photoProtoId = pReq.playerPhoto
            cReq.fightValue = pReq.playerFightValue

            // 推送
            val removeAllAllianceMembers =
                publicCache.allianceMemberCache.findAllianceMembersByAllianceId(pReq.allianceId)
            val pltAreaMap = mutableMapOf<Long, LinkedList<Long>>()

            for (am in removeAllAllianceMembers) {
                val ex = pltAreaMap.getOrPut(am.mapPltAreaId) { LinkedList() }
                if (hasRight(am, com.point18.slg2d.common.constg.A_RIGHT_MEMBER_MANAGER)) {
                    ex.add(am.id)
                }
            }

            for ((pltAreaId, playerIds) in pltAreaMap) {
                // allianceReqInfoChange(pltAreaId, playerIds, constg.REMOVE_ALLIANCE_INFO, cReq)
                allianceReqInfoChange(
                    publicCache.publicActor,
                    pltAreaId,
                    playerIds,
                    REMOVE_ALLIANCE_INFO,
                    cReq.build()
                )
            }
        }

        // 联盟创建日志
        insertLog(
            publicCache,
            allianceId,
            com.point18.slg2d.common.constg.A_LOG_CREATE_ALLIANCE,
            createAllianceReq.playerName, createAllianceReq.playerNickName
        )

        rt.rt = ResultCode.SUCCESS.code
        rt.flagColor = allianceInfo.flagColor
        rt.flagStyle = allianceInfo.flagStyle
        rt.flagEffect = allianceInfo.flagEffect
        rt.allianceName = allianceInfo.name
        rt.allianceShortName = allianceInfo.shortName

        // 推送到公共服
        val unLockTell = UnLockAllianceNameTell.newBuilder()
        unLockTell.changeType = ALLIANCE_NAME_TRY2USE
        unLockTell.allianceName = createAllianceReq.name
        unLockTell.allianceShortName = createAllianceReq.shortName

        val p2pmTell = ppm.fillPublic2PublicManagerTellMsgHeader { it.unLockAllianceNameTell = unLockTell.build() }
        ppm.tell2PublicManager(p2pmTell)

        // 推送到公共服
        syncAllianceInfo2AM(
            publicCache,
            allianceInfo,
            allianceMember.memPower,
            allianceMemberTrace.killSolider,
            allianceMemberTrace.monsterScore
        )

        return rt
    }

    fun newCreateAllianceAskRtBuilder(): CreateAllianceAskRt.Builder {
        val rt = CreateAllianceAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}