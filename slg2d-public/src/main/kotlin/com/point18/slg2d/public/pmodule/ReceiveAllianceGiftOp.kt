package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.sec2MilliSec
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.public.datacache.PublicCache
import com.point18.slg2d.common.pc.findValueFromDropBag
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.public.common.sendAllianceGift
import com.point18.slg2d.common.resultcode.ResultCode
import pb4server.*

class ReceiveAllianceGiftOpByWorld : World2PublicAskDealBase() {
    override fun dealWorldAsk(publicCache: PublicCache, req: World2PublicAskReq, resp: World2PublicAskResp.Builder) {
        val internalMessage = req.receiveAllianceGiftAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val worldId = req.worldId
        val rt = dealMsg(publicCache, allianceId, playerId, worldId, internalMessage)

        resp.setReceiveAllianceGiftAskRt(rt)
    }
}

class ReceiveAllianceGiftOpByHome : Home2PublicAskDealBase() {
    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val internalMessage = req.receiveAllianceGiftAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, 0, internalMessage)

        resp.setReceiveAllianceGiftAskRt(rt)
    }
}

private fun dealMsg(publicCache: PublicCache,
            allianceId: Long,
            playerId: Long,
            worldId: Long,
            req: ReceiveAllianceGiftAskReq): ReceiveAllianceGiftAskRt.Builder {

    val rt = ReceiveAllianceGiftAskRt.newBuilder()
    rt.rt = ResultCode.SUCCESS.code
    val members = publicCache.allianceMemberCache.findAllianceMembersByAllianceId(allianceId)

    for (giftVo in req.giftMapList) {
        val giftProto = pcs.allianceGiftProtoCache.allianceGiftProtoMap[giftVo.giftId]
        if (giftProto == null) {
            normalLog.error("找不到联盟礼物配置:${giftVo.giftId}")
            continue
        }
        val props = pcs.equipCache.equipProtoMap[giftProto.props]
        if (props == null) {
            normalLog.error("找不到礼物中的道具配置:${giftVo.giftId}")
            continue
        }
        for (i in 1..giftVo.giftNum) {
            for (member in members) {
                val addResString = findValueFromDropBag(props.extend2DropMap)
                val addRes = addResString
                val dmap = pcs.dropBagCache.dropBagMap[addRes]
                if (dmap == null) {
                    normalLog.error("找不到礼物中的掉落的道具配置")
                    continue
                }
                sendAllianceGift(
                    publicCache.publicActor,
                    member.mapPltAreaId,
                    member.id,
                    getNowTime() + sec2MilliSec(giftProto.endTime),
                    giftVo.giftId,
                    dmap.drop,
                    ""
                )
            }
        }
    }

    return rt
}