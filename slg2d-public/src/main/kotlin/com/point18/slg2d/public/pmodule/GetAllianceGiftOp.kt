package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.GetAllianceGiftAskReq
import pb4server.GetAllianceGiftAskRt
import pb4server.World2PublicAskReq
import pb4server.World2PublicAskResp
import com.point18.slg2d.public.common.allianceGiftChange
import com.point18.slg2d.public.common.sendAllianceGift
import com.point18.slg2d.common.resultcode.ResultCode

class GetAllianceGiftOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
            publicCache: PublicCache,
            req: World2PublicAskReq,
            resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.getAllianceGiftAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMessage)

        resp.setGetAllianceGiftAskRt(rt)
    }

    fun dealMsg(
            publicCache: PublicCache,
            allianceId: Long,
            playerId: Long,
            req: GetAllianceGiftAskReq
    ): GetAllianceGiftAskRt.Builder {
        val rt = newGetAllianceGiftAskRtBuilder()

        val allianceGiftInfo = publicCache.allianceGiftCache.findAllianceGiftById(allianceId)
        if (allianceGiftInfo != null) {
            val allianceMembers =
                    publicCache.allianceMemberCache.findAllianceMembersByAllianceId(allianceId)
            // 检测礼物是否需要升级
            // 联盟礼物没满级了,获得经验
            val nowGiftLvProto =
                    com.point18.slg2d.common.pc.pcs.allianceGiftLevelProtoCache.allianceGiftLevelProtoCacheMap[allianceGiftInfo.giftLv]
            if (nowGiftLvProto == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt
            }
            if (allianceGiftInfo.giftExp + req.addExp >= nowGiftLvProto.exp) {
                val ex = com.point18.slg2d.common.pc.pcs.allianceGiftLevelProtoCache.allianceGiftLevelProtoCacheMap[allianceGiftInfo.giftLv + 1]
                if (ex != null) {
                    // 联盟礼物没满级了 联盟礼物升级
                    allianceGiftInfo.giftLv += 1
                    allianceGiftInfo.giftExp = allianceGiftInfo.giftExp + req.addExp - nowGiftLvProto.exp

                    val nowEx =
                            com.point18.slg2d.common.pc.pcs.allianceGiftLevelProtoCache.allianceGiftLevelProtoCacheMap[allianceGiftInfo.giftLv + 1]
                    if (nowEx == null) {
                        // 升级完之后是否满级了,满级的话把经验设置成0
                        allianceGiftInfo.giftExp = 0
                    }
                }
            } else {
                allianceGiftInfo.giftExp += req.addExp
            }

            val allianceGiftProp = com.point18.slg2d.common.pc.pcs.allianceGiftProtoCache.allianceGiftProtoMap[allianceGiftInfo.bigGiftId]
            if (allianceGiftProp == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt
            }
            val nowBigGift = com.point18.slg2d.common.pc.pcs.equipCache.equipProtoMap[allianceGiftProp.props]
            if (nowBigGift == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt
            }

            allianceGiftInfo.bigGiftExp += req.addBigExp
            while (allianceGiftInfo.bigGiftExp > com.point18.slg2d.common.pc.pcs.basicProtoCache.allianceBigTreRefreshExp) {
                // 钥匙值满了.发放奖励
                val afterNowGiftLvProto =
                        com.point18.slg2d.common.pc.pcs.allianceGiftLevelProtoCache.allianceGiftLevelProtoCacheMap[allianceGiftInfo.giftLv]
                if (afterNowGiftLvProto == null) {
                    rt.rt = ResultCode.NO_PROTO.code
                    return rt
                }

                val getGiftIdString = com.point18.slg2d.common.pc.findValueFromDropBag(afterNowGiftLvProto.giftShapeMap)
                val getGiftId = getGiftIdString
                val giftProto = com.point18.slg2d.common.pc.pcs.allianceGiftProtoCache.allianceGiftProtoMap[getGiftId]
                if (giftProto == null) {
                    rt.rt = ResultCode.NO_PROTO.code
                    return rt
                }

                val props = com.point18.slg2d.common.pc.pcs.equipCache.equipProtoMap[giftProto.props]
                if (props == null) {
                    rt.rt = ResultCode.NO_PROTO.code
                    return rt
                }

                for (am in allianceMembers) {
                    val addResString = com.point18.slg2d.common.pc.findValueFromDropBag(props.extend2DropMap)
                    val addRes = addResString
                    val dmap = com.point18.slg2d.common.pc.pcs.dropBagCache.dropBagMap[addRes]
                    if (dmap == null) {
                        rt.rt = ResultCode.NO_PROTO.code
                        return rt
                    }
                    sendAllianceGift(
                            publicCache.publicActor,
                            am.mapPltAreaId,
                            am.id,
                            getNowTime() + giftProto.endTime * 1000,
                            getGiftId,
                            dmap.drop,
                            ""
                    )
                }

                // 重新设置钥匙经验与大礼物ID
                val giftInfo = com.point18.slg2d.common.pc.pcs.allianceGiftLevelProtoCache.randGetGiftProtoId(allianceGiftInfo.giftLv)
                if (giftInfo == null) {
                    rt.rt = ResultCode.NO_PROTO.code
                    return rt
                }
                allianceGiftInfo.bigGiftId = giftInfo.id
                allianceGiftInfo.bigGiftExp -= com.point18.slg2d.common.pc.pcs.basicProtoCache.allianceBigTreRefreshExp
            }

            for (am in allianceMembers) {
                allianceGiftChange(
                        publicCache.publicActor,
                        am.mapPltAreaId, am.id, allianceGiftInfo.bigGiftId,
                        allianceGiftInfo.bigGiftExp, allianceGiftInfo.giftLv, allianceGiftInfo.giftExp
                )
            }

        }

        return rt
    }

    fun newGetAllianceGiftAskRtBuilder(): GetAllianceGiftAskRt.Builder {
        val rt = GetAllianceGiftAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}