package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.commonfunc.getNowMTime
import pb4client.AllianceGiftRemove
import pb4client.AllianceGiftRemoveRt
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*

// 领取联盟礼物

class RemoveAllianceGiftDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val giftIds = LinkedList<Long>()

        for (id in (msg as AllianceGiftRemove).onlyIdList) {
            giftIds.add(id)
        }

        val removeGiftRt = this.removeAllianceGift(session, giftIds)
        if (removeGiftRt != null) {
            removeGiftRt.addAllOnlyId(msg.onlyIdList)
            session.sendMsg(MsgType.AllianceGiftRemove_903, removeGiftRt.build())
        }
    }

    fun removeAllianceGift(session: PlayerSession, giftIds: LinkedList<Long>): AllianceGiftRemoveRt.Builder? {
        val rt = AllianceGiftRemoveRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val areaCache = session.areaCache
        val player = session.player
        if (player.allianceId == 0L) {
            rt.rt = (ResultCode.ALLIANCE_QUERY_NOT_EXIST.code)
            return rt
        }

        // 验证与记录数据
        for (giftId in giftIds) {
            val giftVo = areaCache.myAllianceGiftCache.findMyAllianceGiftsByPlayerIdAndId(player.id, giftId)
            if (giftVo == null) {
                rt.rt = (ResultCode.PARAMETER_ERROR.code)
                return rt
            }

            // 未领取的并且没过期的不能删除
            if (giftVo.isGet == 0 && getNowMTime() < giftVo.overTime) {
                rt.rt = (ResultCode.ALLIANCE_GIFT_REMOVE_ERROR.code)
                return rt
            }
        }

        for (giftId in giftIds) {
            val giftVo = areaCache.myAllianceGiftCache.findMyAllianceGiftsByPlayerIdAndId(player.id, giftId)
            if (giftVo == null) {
                rt.rt = (ResultCode.PARAMETER_ERROR.code)
                return rt
            }
            areaCache.myAllianceGiftCache.delAllianceGift(giftVo)
        }

        return null
    }

}

