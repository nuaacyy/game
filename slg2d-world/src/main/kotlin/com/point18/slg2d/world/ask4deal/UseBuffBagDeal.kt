package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.event.GetNewBuff
import com.point18.slg2d.world.wpm
import pb4server.BuffBagAskReq
import pb4server.BuffBagAskRt
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp

class UseBuffBagDeal : Home2WorldAskDealBase() {
    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val askMsg = req.buffBagAskReq
        val rt = dealBuff(areaCache, req.playerId, askMsg)
        resp.setBuffBagAskRt(rt)
    }

    private fun dealBuff(areaCache: AreaCache, playerId: Long, askMsg: BuffBagAskReq): BuffBagAskRt.Builder {
        val buffBasicProto = pcs.buffBasicProtoCache.protoMap[askMsg.buffBasicProtoId]

        val rt = BuffBagAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        if (buffBasicProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt
        }

        val buffs = areaCache.buffCache.findBuffsByPlayerId(playerId)

        // 在雪地上，无法使用保护罩buff
        if (buffBasicProto.typeEffect == BUFF_EFFECT_DEF_COVER) {
            val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
            if (castle != null) {
                val (_, snowArea) =
                    pcs.monsterActivityLocationProtoCache.findActivityBossAreaType(castle.x, castle.y)
                if (snowArea == SNOW_AREA) {
                    rt.rt = ResultCode.NO_DEF_BECAUSE_SNOW_COVER.code
                    return rt
                }
            }
        }

        // 在黑土地上，无法使用保护罩buff
        if (buffBasicProto.typeEffect == BUFF_EFFECT_DEF_COVER) {
            val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
            if (castle != null) {
                val (_, wonderArea) =
                    pcs.wonderLocationProtoCache.findInWonderType(castle.x, castle.y)
                if (wonderArea == WONDER_BLACK) {
                    rt.rt = ResultCode.NO_DEF_BECAUSE_WONDER_COVER.code
                    return rt
                }
            }
        }

        // 有战争狂热，无法使用保护罩Buff
        if (buffBasicProto.typeEffect == BUFF_EFFECT_DEF_COVER) {
            val (have, _, _) = areaCache.buffCache.findBuffValueByBuffType(playerId, BUFF_EFFECT_WALK_EFFECT)
            if (have) {
                rt.rt = ResultCode.NO_DEF_BECAUSE_WALK_EFFECT.code
                return rt
            }
        }

        for (buff in buffs) {
            val buffBasicProto2 = pcs.buffBasicProtoCache.protoMap[buff.protoId]
            if (buffBasicProto2 == null) {
                continue
            }
            // 检测是否有一样效果的并且比即将使用的这个持续时间更长
            if (buffBasicProto2.typeEffect == buffBasicProto2.typeEffect && buffBasicProto2.effect == buffBasicProto2.effect
            ) {
                if (buff.overTime - getNowTime() > buffBasicProto2.time * 1000) {
                    rt.rt = ResultCode.PARAMETER_ERROR.code
                    return rt
                }
            }
        }

        val buffTime = buffBasicProto.time * 1000
        if (buffTime != 0) {
            val buffOverTime = getNowTime() + buffTime
            wpm.es.fire(
                areaCache, playerId, GET_BUFF,
                GetNewBuff(playerId, buffBasicProto.id, buffOverTime)
            )
        }

        return rt
    }
}