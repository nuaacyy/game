package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.common.dealAfterWriteAllianceWaijiao
import com.point18.slg2d.public.datacache.PublicCache
import com.point18.slg2d.common.constg.AllianceWaijiaoRedPoint
import pb4server.*
import com.point18.slg2d.common.resultcode.ResultCode

class WriteAllianceWaijiaoOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
            publicCache: PublicCache,
            req: World2PublicAskReq,
            resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.writeAllianceWaijiaoAskReq
        val playerId = req.playerId
        val rt = dealMsg(publicCache, playerId, internalMessage)

        resp.setWriteAllianceWaijiaoAskRt(rt)
    }

    fun dealMsg(
            publicCache: PublicCache,
            playerId: Long,
            req: WriteAllianceWaijiaoAskReq
    ): WriteAllianceWaijiaoAskRt.Builder {

        val rt = newWriteAllianceWaijiaoAskRtBuilder()

        val flagColor = req.flagColor
        val flagStyle = req.flagStyle
        val flagEffect = req.flagEffect
        val aName = req.aName
        val asName = req.asName
        // 目标帮
        val alliance = publicCache.allianceCache.findAllianceById(publicCache.publicActor.publicId)
        if (alliance == null) {
            rt.rt = ResultCode.ALLIANCE_QUERY_NOT_EXIST.code
            return rt
        }

        val playerName = req.playerName
        val playerPos = req.playerPos
        val photoProtoId = req.photoProtoId

        // 存储
        val waijiao = publicCache.allianceWaijiaoCache.createAllianceWaijiao(
                flagColor,
                flagStyle,
                flagEffect,
                req.myAllianceId,
                aName,
                asName,
                playerId,
                playerName,
                playerPos,
                publicCache.publicActor.publicId,
                req.waijiaoInfo,
                req.areaNo,
                photoProtoId,
                req.nickName
        )

        // 判断是否需要删除一条旧的
        val allInfo =
                publicCache.allianceWaijiaoCache.findAllianceWaijiaoByAllianceId(publicCache.publicActor.publicId)
        if (com.point18.slg2d.common.pc.pcs.basicProtoCache.allianceAiplomacyNumlimit != 0 && allInfo.size > com.point18.slg2d.common.pc.pcs.basicProtoCache.allianceAiplomacyNumlimit) {
            // 删除
            val dlt = publicCache.allianceWaijiaoCache.findAllianceEarlyWaijiaoByAllianceId(
                    publicCache.publicActor.publicId
            )
            publicCache.allianceWaijiaoCache.deleteAllianceWaijiaoById(dlt)
        }

        // 推送
        val allAllianceMembers =
                publicCache.allianceMemberCache.findAllianceMembersByAllianceId(publicCache.publicActor.publicId)
        val pltAreas = mutableMapOf<Long, Int>()

        for (am in allAllianceMembers) {
            pltAreas[am.mapPltAreaId] = 1
        }

        for ((pltAreaId, _) in pltAreas) {
            dealAfterWriteAllianceWaijiao(
                    publicCache.publicActor,
                    pltAreaId,
                    AllianceWaijiaoRedPoint,
                    publicCache.publicActor.publicId
            )
        }

        val allianceWaijiaoInfo = AllianceWaijiaoInfoVo.newBuilder()

        allianceWaijiaoInfo.flagColor = waijiao.flagColor
        allianceWaijiaoInfo.flagStyle = waijiao.flagStyle
        allianceWaijiaoInfo.flagEffect = waijiao.flagEffect
        allianceWaijiaoInfo.aid = waijiao.aid
        allianceWaijiaoInfo.name = waijiao.name
        allianceWaijiaoInfo.shortName = waijiao.shortName
        allianceWaijiaoInfo.playerId = waijiao.playerId
        allianceWaijiaoInfo.playerName = waijiao.playerName
        allianceWaijiaoInfo.playerPositions = waijiao.playerPosition
        allianceWaijiaoInfo.createTime = (waijiao.createTime / 1000).toInt()
        allianceWaijiaoInfo.waijiaoInfo = waijiao.waijiaoInfo
        allianceWaijiaoInfo.mapPltAreaNo = waijiao.areaNo
        allianceWaijiaoInfo.photoProtoId = waijiao.photoProtoId
        allianceWaijiaoInfo.nickName = waijiao.nickName
        allianceWaijiaoInfo.waijiaoId = waijiao.id

        rt.setAllianceWaijiaoInfos(allianceWaijiaoInfo)

        return rt
    }

    fun newWriteAllianceWaijiaoAskRtBuilder(): WriteAllianceWaijiaoAskRt.Builder {
        val rt = WriteAllianceWaijiaoAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}