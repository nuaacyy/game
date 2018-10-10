package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.MARK_NORMAL
import pb4server.*
import com.point18.slg2d.public.common.dealAfterSetAllianceMark
import com.point18.slg2d.public.datacache.*
import com.point18.slg2d.common.resultcode.ResultCode

class SetAllianceMarkSyncOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
            publicCache: PublicCache,
            req: World2PublicAskReq,
            resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.setAllianceMarkAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMessage)

        resp.setSetAllianceMarkAskRt(rt)
    }

    fun dealMsg(
            publicCache: PublicCache,
            allianceId: Long,
            playerId: Long,
            req: SetAllianceMarkAskReq
    ): SetAllianceMarkAskRt.Builder {
        val rt = newSetAllianceMarkAskRtBuilder()

        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        if (alce == null) {
            rt.rt = ResultCode.ALLIANCE_QUERY_NOT_EXIST.code
            return rt
        }

        if (req.markType == MARK_NORMAL) {
            // 联盟信息

            // 验证联盟标记数量是否达到上限
            val allMarks = publicCache.allianceMarkCache.findMarksByAllianceId(alce.id)
            if (allMarks.size >= com.point18.slg2d.common.pc.pcs.basicProtoCache.allianceMarkCountLimit) {
                rt.rt = ResultCode.ALLIANCE_MARK_COUNT_EXCEED.code
                return rt
            }

            // 验证权限
            val allianceMember = publicCache.allianceMemberCache.findAllianceMemberById(playerId)
            if (allianceMember == null) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return rt
            }
            if (!hasRight(allianceMember, com.point18.slg2d.common.constg.A_RIGHT_MARK)) {
                rt.rt = ResultCode.ALLIANCE_POSITION_RIGHT_FORBIDDEN.code
                return rt
            }

            // 是否已标记
            var flag = 0
            var mark = publicCache.allianceMarkCache.findMarkByAidXY(
                    MARK_NORMAL,
                    allianceMember.allianceId,
                    req.x,
                    req.y,
                    req.pltAreaNo
            )
            if (mark != null) {
                // 保存联盟标记
                mark.playerId = playerId
                mark.title = req.title
                mark.description = req.desp

                // 修改
                flag = 2

            } else {
                // 检查标记设置时间间隔
                if (alce.nextMarkTime != 0L && alce.nextMarkTime > getNowTime()) {
                    rt.endTime = alce.nextMarkTime
                    return rt
                }

                // 保存联盟标记
                mark = publicCache.allianceMarkCache.createAllianceMark(
                        publicCache, allianceMember.allianceId, MARK_NORMAL, playerId, req.x,
                        req.y, req.pltAreaNo, req.title, req.desp
                )

                insertLog(
                        publicCache,
                        alce.id,
                        com.point18.slg2d.common.constg.A_LOG_CREATE_ALLIANCE_MARK,
                        allianceMember.name, allianceMember.allianceNickName,
                        req.title
                )

                // 记录联盟标记下次可设置时间戳
                alce.nextMarkTime = getNowTime() + com.point18.slg2d.common.pc.pcs.basicProtoCache.allianceSetMarkDuration

                // 添加
                flag = 1
            }

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
            allianceMarkInfoVo.pltAreaNo = mark.pltAreaNo
            allianceMarkInfoVo.markId = mark.id

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
            allianceMarkInfoVo.photoProtoId = allianceMember.photoProtoId
            for ((p, _) in allianceMember.alliancePosMap) {
                allianceMarkInfoVo.addPositions(p)
            }


            val pltAreas = mutableMapOf<Long, Int>()

            for (am in allAllianceMember) {
                pltAreas[am.mapPltAreaId] = 1
            }

            for ((pltAreaId, _) in pltAreas) {
                // dealAfterSetAllianceMark(pltAreaId, allianceMarkInfoVo, flag, allianceId)
                dealAfterSetAllianceMark(
                        publicCache.publicActor,
                        pltAreaId,
                        allianceMarkInfoVo.build(),
                        flag,
                        allianceId
                )
            }
        }

        return rt
    }

    fun newSetAllianceMarkAskRtBuilder(): SetAllianceMarkAskRt.Builder {
        val rt = SetAllianceMarkAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.endTime = 0
        return rt
    }
}