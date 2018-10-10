package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.constg.ADD_ALLIANCE_INFO
import com.point18.slg2d.common.constg.ALLIANCE_MAX_MEMBER
import com.point18.slg2d.common.constg.ALLIANCE_MEMBER_FLAG_JOIN
import com.point18.slg2d.common.constg.Add
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.common.*
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.*
import java.util.*

// 申请加入联盟
class JoinAllianceByIdSyncOp : World2PublicAskDealBase() {

    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.joinAllianceByIdAskReq
        val allianceId = req.publicId
        val rt = joinAllianceById(publicCache, allianceId, internalMessage)

        resp.joinAllianceByIdAskRt = rt.build()
    }

    private fun joinAllianceById(
        publicCache: PublicCache,
        allianceId: Long,
        req: JoinAllianceByIdAskReq
    ): JoinAllianceByIdAskRt.Builder {
        val rt = newJoinAllianceByIdAskRtBuilder()

        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        if (alce == null) {
            rt.rt = ResultCode.ALLIANCE_QUERY_NOT_EXIST.code
            return rt
        }

        if (alce.canAddPower >= 0 && req.fightValue >= alce.canAddPower) {
            // 是否达到了无需同意的战斗力
            // 验证联盟人数上限是否已经达到最大可招收数
            val aPlayers =
                publicCache.allianceMemberCache.findAllianceMembersByAllianceId(allianceId)
            if (aPlayers.size >= ALLIANCE_MAX_MEMBER) {
                rt.rt = ResultCode.ALLIANCE_PLAYERS_EXCEED.code
                return rt
            }

            // 加入联盟日志
            insertLog(
                publicCache,
                allianceId,
                com.point18.slg2d.common.constg.A_LOG_ENTER_ALLIANCE,
                req.playerName,
                req.playerNickName
            )

            // 生成联盟成员信息
            // 去登陆服拿玩家数据
            var isOnline = 0
            if (req.lastLoginTime > req.offTime) {
                isOnline = 1
            }

            val allianceMember = publicCache.allianceMemberCache.createAllianceMember(
                alce.id, req.pid,
                req.playerName, 0, req.offTime, 0, 0, 5, req.pltAreaNo,
                req.pltAreaNo, req.photoProtoId, isOnline, req.areaNo, req.playerCastleLv
            )

            // 联盟基础信息发生变化 推送到公共服
            syncAllianceInfo2AM(publicCache, alce, allianceMember.memPower, 0, 0)

            // 检测玩家是否是第一次加入这个联盟
            val allianceMemberTrace =
                publicCache.allianceMemberTraceCache.findAllianceMemberTraceByAllianceIdAndPlayerId(
                    alce.id,
                    req.pid
                )
            if (allianceMemberTrace == null) {
                publicCache.allianceMemberTraceCache.createAllianceMemberTrace(req.pid, alce.id)
            }

            val pltAreass = mutableMapOf<Long, LinkedList<Long>>()
            val ps = pltAreass.getOrPut(allianceMember.mapPltAreaId) { LinkedList() }
            ps.add(allianceMember.id)

            allianceMember.resetWrapPosition()

            val allianceMembers =
                publicCache.allianceMemberCache.findAllianceMembersByAllianceId(allianceId)
            val members = LinkedList<MemberPlayerInfoVo>()
            for (playerA in allianceMembers) {
                if (playerA.id == req.pid) {
                    // 不包括playerM
                    continue
                }

                // 联盟成员信息
                /*val member = MemberPlayerInfoVo()
                member.playerId = playerA.id
                member.playerName = playerA.name
                member.isOnline = playerA.onlineState
                member.protoId = playerA.photoProtoId
                for ((pos, _) in playerA.alliancePosMap) {
                    member.positions.add(pos)
                }
                members.add(member)*/

                val member = MemberPlayerInfoVo.newBuilder()
                member.playerId = playerA.id
                member.playerName = playerA.name
                member.isOnline = playerA.onlineState
                member.protoId = playerA.photoProtoId
                for ((pos, _) in playerA.alliancePosMap) {
                    member.addPositions(pos)
                }
                members.add(member.build())
            }

            val aMarks = publicCache.allianceMarkCache.findMarksByAllianceId(allianceId)
            val aPbMarks = LinkedList<AllianceMarkInfoVo>()
            for ((_, aMark) in aMarks) {
                val playerM = publicCache.allianceMemberCache.findAllianceMemberById(aMark.playerId)
                if (playerM == null) {
                    continue
                }
                /*val aPbMark = AllianceMarkInfoVo()
                aPbMark.playerId = playerM.id
                aPbMark.playerName = playerM.name
                aPbMark.type = aMark.type
                aPbMark.x = aMark.x
                aPbMark.y = aMark.y
                aPbMark.title = aMark.title
                aPbMark.desp = aMark.description
                aPbMark.markTime = aMark.markTime
                aPbMark.markId = aMark.id
                aPbMark.pltAreaNo = aMark.pltAreaNo
                for ((p, _) in playerM.alliancePosMap) {
                    aPbMark.positions.add(p)
                }

                aPbMarks.add(aPbMark)*/

                val aPbMark = AllianceMarkInfoVo.newBuilder()
                aPbMark.playerId = playerM.id
                aPbMark.playerName = playerM.name
                aPbMark.type = aMark.type
                aPbMark.x = aMark.x
                aPbMark.y = aMark.y
                aPbMark.title = aMark.title
                aPbMark.desp = aMark.description
                aPbMark.markTime = aMark.markTime
                aPbMark.markId = aMark.id
                aPbMark.pltAreaNo = aMark.pltAreaNo
                aPbMark.photoProtoId = playerM.photoProtoId
                for ((p, _) in playerM.alliancePosMap) {
                    aPbMark.addPositions(p)
                }

                aPbMarks.add(aPbMark.build())
            }

            joinInAllianceSuccess(
                publicCache.publicActor,
                allianceMember.mapPltAreaId, req.pid, allianceId, alce.name, alce.shortName,
                alce.flagColor, alce.flagStyle, alce.flagEffect, members, aPbMarks
            )

            // 通知home服添加奇观
            noticeOccupyWonder2Home(publicCache.publicActor, req.pid, Add, alce.allianceOccupyInfo)

            // 通知world服添加奇观
            val memberIds = LinkedList<Long>()
            memberIds.add(req.pid)
            noticeOccupyWonder2World(publicCache.publicActor, allianceMember.mapPltAreaId, memberIds, Add, alce.allianceOccupyInfo)

            val nowPos = LinkedList<Int>()
            for ((p, _) in allianceMember.alliancePosMap) {
                nowPos.add(p)
            }
            val pltAreas = mutableMapOf<Long, Int>()

            for (am in allianceMembers) {
                pltAreas[am.mapPltAreaId] = 1
            }

            for ((pltAreaId, _) in pltAreas) {
                allianceMemberInfoChange(
                    publicCache.publicActor,
                    pltAreaId,
                    allianceId,
                    ALLIANCE_MEMBER_FLAG_JOIN,
                    req.pid,
                    req.playerName,
                    nowPos,
                    allianceMember.onlineState,
                    allianceMember.photoProtoId
                )
            }

        } else {
            // 验证重复申请
            val aReq =
                publicCache.allianceReqCache.findAllianceReqByAidWithPid(allianceId, req.pid)
            if (aReq != null) {
                rt.rt = ResultCode.ALLIANCE_REQ_ALREADY_EXIST.code
                return rt
            }

            // 是否达到对方联盟设置的最低战斗力要求
            if (req.fightValue < alce.powerLimit) {
                rt.rt = ResultCode.ALLIANCE_POWER_NOT_ENOUGH.code
                return rt
            }

            // 添加联盟申请
            publicCache.allianceReqCache.createAllianceReq(
                allianceId,
                req.pid,
                req.fightValue,
                req.playerName,
                req.playerPhoto,
                req.pltAreaNo,
                req.areaNo
            )

            rt.isCreateJoinInfo = 1

            // val cReq = AllianceQueryReqListInfoVo()
            val cReq = AllianceQueryReqListInfoVo.newBuilder()
            cReq.id = req.pid
            cReq.name = req.playerName
            cReq.photoProtoId = req.playerPhoto
            cReq.fightValue = req.fightValue

            val pltAreas = mutableMapOf<Long, LinkedList<Long>>()

            for (am in publicCache.allianceMemberCache.findAllianceMembersByAllianceId(allianceId)) {
                val ps = pltAreas.getOrPut(am.mapPltAreaId) { LinkedList() }

                if (hasRight(am, com.point18.slg2d.common.constg.A_RIGHT_MEMBER_MANAGER)) {
                    ps.add(am.id)
                }
            }

            for ((pltAreaId, playerIds) in pltAreas) {
                // allianceReqInfoChange(pltAreaId, playerIds, constg.ADD_ALLIANCE_INFO, cReq)
                allianceReqInfoChange(
                    publicCache.publicActor,
                    pltAreaId,
                    playerIds,
                    ADD_ALLIANCE_INFO,
                    cReq.build()
                )
            }
        }

        return rt
    }

    fun newJoinAllianceByIdAskRtBuilder(): JoinAllianceByIdAskRt.Builder {
        val rt = JoinAllianceByIdAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}