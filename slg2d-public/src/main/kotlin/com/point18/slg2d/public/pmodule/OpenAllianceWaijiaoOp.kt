package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.PublicCache
import pb4server.*
import com.point18.slg2d.common.resultcode.ResultCode

class OpenAllianceWaijiaoOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.openAllianceWaijiaoAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMessage)

        resp.setOpenAllianceWaijiaoAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: OpenAllianceWaijiaoAskReq
    ): OpenAllianceWaijiaoAskRt.Builder {
        val rt = newOpenAllianceWaijiaoAskRtBuilder()

        val waijiaos = publicCache.allianceWaijiaoCache.findAllianceWaijiaoByAllianceId(allianceId)

        for (waijiao in waijiaos) {
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

            rt.addAllianceWaijiaoInfos(allianceWaijiaoInfo)
        }

        return rt
    }

    fun newOpenAllianceWaijiaoAskRtBuilder(): OpenAllianceWaijiaoAskRt.Builder {
        val rt = OpenAllianceWaijiaoAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}