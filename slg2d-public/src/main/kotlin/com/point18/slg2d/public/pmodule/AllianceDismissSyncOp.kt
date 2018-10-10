package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.constg.BIG_WONDER
import com.point18.slg2d.common.constg.Del
import com.point18.slg2d.common.constg.REMOVE_ALLIANCE_INFO
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.common.allianceDismissNotic2G
import com.point18.slg2d.public.common.noticeCleanWonder2World
import com.point18.slg2d.public.common.noticeOccupyWonder2Home
import com.point18.slg2d.public.common.noticeOccupyWonder2World
import com.point18.slg2d.public.datacache.PublicCache
import com.point18.slg2d.public.datacache.deleteAllianceLog
import com.point18.slg2d.public.datacache.findAllianceReqsByAllianceId
import com.point18.slg2d.public.ppm
import pb4server.*
import java.util.*

class AllianceDismissSyncOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.allianceDismissAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMessage)

        resp.setAllianceDismissAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: AllianceDismissAskReq
    ): AllianceDismissAskRt.Builder {
        val rt = newAllianceDismissAskRtBuilder()

        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        if (alce == null) {
            rt.rt = ResultCode.ALLIANCE_PLAYER_NO_JOIN.code
            return rt
        }
        val allianceMember = publicCache.allianceMemberCache.findAllianceMemberById(playerId)

        if (allianceMember == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        // 验证联盟人数
        val players = publicCache.allianceMemberCache.findAllianceMembersByAllianceId(
            allianceMember.allianceId
        )

        if (players.size != 1) {
            rt.rt = ResultCode.MEMBER_DISMISS_ALLIANCE_ERROR.code
            return rt
        }

        // 验证玩家的职位是否拥有该模块操作权限
        if (!hasRight(allianceMember, com.point18.slg2d.common.constg.A_RIGHT_DISMISS)) {
            rt.rt = ResultCode.ALLIANCE_POSITION_RIGHT_FORBIDDEN.code
            return rt
        }

        // 验证世界占领情况
        for ((_, wonderOccupy) in alce.allianceOccupyInfo) {
            for ((wonderProtoId, _) in wonderOccupy) {
                if (wonderProtoId == BIG_WONDER) {
                    rt.rt = ResultCode.WONDER_DISMISS_ALLIANCE_ERROR.code
                    return rt
                }
            }
        }

        // 清空联盟日志记录
        val aLogs = publicCache.allianceLogCache.findLogsByAllianceId(alce.id)
        val delLogs = LinkedList<Long>()
        for ((_, aLog) in aLogs) {
            delLogs.add(aLog.id)
        }

        for (d in delLogs) {
            deleteAllianceLog(aLogs, d)
        }

        // 删除联盟标记
        val aMarks = publicCache.allianceMarkCache.findMarksByAllianceId(alce.id)
        for ((_, aMark) in aMarks) {
            publicCache.allianceMarkCache.deleteAllianceMark(aMark)
        }

        // 将玩家从联盟中移除，并推送联盟信息
        val aid = allianceMember.allianceId // player中的AllianceId会在下面的方法中被重置,所以这里需要提前取出。
        val pltAreas = mutableMapOf<Long, Int>()

        for (playerA in players) {
            pltAreas[playerA.mapPltAreaId] = 1

            publicCache.allianceMemberCache.deleteAllianceMember(playerA)
            for (help in publicCache.allianceHelpCache.findAllianceHelpByPlayerId(playerA.id)) {
                publicCache.allianceHelpCache.deleteAllianceHelp(help)
            }
        }

        for ((pltAreaId, _) in pltAreas) {
            allianceDismissNotic2G(publicCache.publicActor, pltAreaId, aid)
        }

        // 删除申请加入该联盟
        val aReqs = findAllianceReqsByAllianceId(publicCache, aid)
        for (aReq in aReqs) {
            publicCache.allianceReqCache.deleteAllianceReq(aReq)
        }

        // 删除礼物信息
        val allianceGift = publicCache.allianceGiftCache.findAllianceGiftById(allianceId)
        publicCache.allianceGiftCache.deleteAllianceGift(allianceGift)

        val allianceMemberTraces =
            publicCache.allianceMemberTraceCache.findAllianceMemberTracesByAllianceId(allianceId)
        for (allianceMemberTrace in allianceMemberTraces) {
            publicCache.allianceMemberTraceCache.deleteAllianceMemberTrace(allianceMemberTrace)
        }

        // 删除联盟信息
        publicCache.allianceCache.deleteAlliance(alce)

        // 通知不同world服联盟成员失去奇观
        val allMembers =
            publicCache.allianceMemberCache.findAllianceMembersByAllianceId(allianceId)
        allMembers.groupBy { it.mapPltAreaId }.forEach { pltAreaId, members ->
            val memberIds = LinkedList<Long>()
            members.forEach { memberIds.add(it.id) }
            noticeOccupyWonder2World(publicCache.publicActor, pltAreaId, memberIds, Del, alce.allianceOccupyInfo)
        }

        // 通知home服失去奇观
        allMembers.forEach {
            noticeOccupyWonder2Home(publicCache.publicActor, it.id, Del, alce.allianceOccupyInfo)
        }

        // 通知世界重置奇观
        for ((worldId, wonderOccupy) in alce.allianceOccupyInfo) {
            val wonderIds = arrayListOf<Int>()
            for ((wonderId, _) in wonderOccupy) {
                wonderIds.add(wonderId)
            }

            noticeCleanWonder2World(publicCache.publicActor, worldId, wonderIds)
        }

        // 推送到公共服
        val syncAllianceSimpleInfoTell = SyncAllianceSimpleInfoTell.newBuilder()
        syncAllianceSimpleInfoTell.changeType = REMOVE_ALLIANCE_INFO
        syncAllianceSimpleInfoTell.allianceId = alce.id

        val p2pmTell2 = ppm.fillPublic2PublicManagerTellMsgHeader {
            it.syncAllianceSimpleInfoTell = syncAllianceSimpleInfoTell.build()
        }
        ppm.tell2PublicManager(p2pmTell2)

        return rt
    }

    private fun newAllianceDismissAskRtBuilder(): AllianceDismissAskRt.Builder {
        val rt = AllianceDismissAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}
