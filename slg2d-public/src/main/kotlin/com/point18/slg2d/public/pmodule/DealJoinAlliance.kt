package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.constg.ALLIANCE_MAX_MEMBER
import com.point18.slg2d.common.constg.ALLIANCE_MEMBER_FLAG_JOIN
import com.point18.slg2d.common.constg.Add
import com.point18.slg2d.common.constg.REMOVE_ALLIANCE_INFO
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.common.*
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.*
import java.util.*

// 处理加入联盟申请
class DealJoinAllianceSyncOp : World2PublicAskDealBase() {

    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.dealJoinAllianceAskReq
        val allianceId = req.publicId
        val rt = dealJoinAlliance(publicCache, allianceId, internalMessage)


        resp.dealJoinAllianceAskRt = rt.build()
    }

    private fun dealJoinAlliance(
        publicCache: PublicCache,
        allianceId: Long,
        req: DealJoinAllianceAskReq
    ): DealJoinAllianceAskRt.Builder {
        val rt = newDealJoinAllianceAskRtBuilder()

        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        val mPlayer = publicCache.allianceMemberCache.findAllianceMemberById(req.pid)
        if (mPlayer == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        if (!hasRight(mPlayer, com.point18.slg2d.common.constg.A_RIGHT_MEMBER_MANAGER)) {
            rt.rt = ResultCode.ALLIANCE_POSITION_RIGHT_FORBIDDEN.code
            return rt
        }

        if (req.reqType == 1) {
            val queryPlayerInfoVo = req.queryPlayerInfoAskVo
            // 同意
            if (alce == null) {
                rt.rt = ResultCode.ALLIANCE_PLAYER_NO_JOIN.code
                return rt
            }

            // 验证是否有对应的加入联盟请求
            val reqV =
                publicCache.allianceReqCache.findAllianceReqByAidWithPid(allianceId, req.reqPid)
            if (reqV == null) {
                rt.rt = ResultCode.ALLIANCE_JOIN_REQ_NOT_EXIST.code
                return rt
            }

            //验证联盟人数上限是否已经达到最大可招收数
            val aPlayers =
                publicCache.allianceMemberCache.findAllianceMembersByAllianceId(allianceId)
            if (aPlayers.size >= ALLIANCE_MAX_MEMBER) {
                rt.rt = ResultCode.ALLIANCE_PLAYERS_EXCEED.code
                return rt
            }

            // 删除玩家申请加入其他联盟的请求
            val pReqs = publicCache.allianceReqCache.findReqsByPlayerId(req.reqPid)
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
                val pltAreas = mutableMapOf<Long, LinkedList<Long>>()

                for (am in removeAllAllianceMembers) {
                    val ps = pltAreas.getOrPut(am.mapPltAreaId) { LinkedList() }
                    if (hasRight(am, com.point18.slg2d.common.constg.A_RIGHT_MEMBER_MANAGER)) {
                        ps.add(am.id)
                    }
                }

                for ((pltAreaId, playerIds) in pltAreas) {
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

            // 加入联盟日志
            insertLog(
                publicCache,
                allianceId,
                com.point18.slg2d.common.constg.A_LOG_ENTER_ALLIANCE,
                queryPlayerInfoVo.playerName,
                queryPlayerInfoVo.playerNickName
            )

            // 生成联盟成员信息
            // 去登陆服拿玩家数据
            var isOnline = 0
            if (queryPlayerInfoVo.lastLoginTime > queryPlayerInfoVo.offTime) {
                isOnline = 1
            }

            val allianceMember = publicCache.allianceMemberCache.createAllianceMember(
                alce.id, req.reqPid,
                queryPlayerInfoVo.playerName, 0, queryPlayerInfoVo.offTime, 0, 0, 5, reqV.pltAreaNo,
                reqV.pltAreaNo, queryPlayerInfoVo.photoProtoId, isOnline, reqV.areaNo, queryPlayerInfoVo.playerCastleLv
            )

            // 推送到公共服
            syncAllianceInfo2AM(
                publicCache,
                alce,
                allianceMember.memPower,
                0,
                0
            )


            // 检测玩家是否是第一次加入这个联盟
            val allianceMemberTrace =
                publicCache.allianceMemberTraceCache.findAllianceMemberTraceByAllianceIdAndPlayerId(
                    alce.id,
                    req.reqPid
                )
            if (allianceMemberTrace == null) {
                publicCache.allianceMemberTraceCache.createAllianceMemberTrace(req.reqPid, alce.id)
            }

            val pltAreass = mutableMapOf<Long, LinkedList<Long>>()
            val pss = pltAreass.getOrPut(allianceMember.mapPltAreaId) { LinkedList() }
            pss.add(allianceMember.id)

            insertLog(
                publicCache,
                alce.id,
                com.point18.slg2d.common.constg.A_LOG_ALLIANCE_JOIN,
                mPlayer.name,
                mPlayer.allianceNickName,
                allianceMember.name,
                allianceMember.allianceNickName
            )

            allianceMember.resetWrapPosition()

            val allianceMembers =
                publicCache.allianceMemberCache.findAllianceMembersByAllianceId(allianceId)
            val members = LinkedList<MemberPlayerInfoVo>()
            for (playerA in allianceMembers) {
                if (playerA.id == req.reqPid) {
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
                    // member.positionsList.add(pos)
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
                allianceMember.mapPltAreaId,
                req.reqPid,
                allianceId,
                alce.name,
                alce.shortName,
                alce.flagColor,
                alce.flagStyle,
                alce.flagEffect,
                members,
                aPbMarks
            )

            // 通知home服添加奇观
            noticeOccupyWonder2Home(publicCache.publicActor, req.reqPid, Add, alce.allianceOccupyInfo)

            // 通知world服添加奇观
            val memberIds = LinkedList<Long>()
            memberIds.add(req.reqPid)
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
                    req.reqPid,
                    queryPlayerInfoVo.playerName,
                    nowPos,
                    allianceMember.onlineState,
                    allianceMember.photoProtoId
                )
            }

        } else {
            // 拒绝
            // 请求是否存在
            val reqV =
                publicCache.allianceReqCache.findAllianceReqByAidWithPid(allianceId, req.reqPid)
            if (reqV == null) {
                rt.rt = ResultCode.ALLIANCE_JOIN_REQ_NOT_EXIST.code
                return rt
            }

            // 删除请求
            publicCache.allianceReqCache.deleteAllianceReq(reqV)

            // val cReq = AllianceQueryReqListInfoVo()
            val cReq = AllianceQueryReqListInfoVo.newBuilder()
            cReq.id = reqV.playerId
            cReq.name = reqV.playerName
            cReq.photoProtoId = reqV.playerPhoto
            cReq.fightValue = reqV.playerFightValue

            // 推送
            val removeAllAllianceMembers =
                publicCache.allianceMemberCache.findAllianceMembersByAllianceId(reqV.allianceId)
            val pltAreas = mutableMapOf<Long, LinkedList<Long>>()

            for (am in removeAllAllianceMembers) {
                val ps = pltAreas.getOrPut(am.mapPltAreaId) { LinkedList() }
                if (hasRight(am, com.point18.slg2d.common.constg.A_RIGHT_MEMBER_MANAGER)) {
                    ps.add(am.id)
                }
            }

            for ((pltAreaId, playerIds) in pltAreas) {
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

        return rt
    }

    fun newDealJoinAllianceAskRtBuilder(): DealJoinAllianceAskRt.Builder {
        val rt = DealJoinAllianceAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}