package com.point18.slg2d.world.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.ACTION_ALLIANCE_GIFT_OPEN
import com.point18.slg2d.common.constg.GET_ALLIANCE_GIFT
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.resStringToResVoList
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.addResToHome
import com.point18.slg2d.world.common.getAllianceGift
import com.point18.slg2d.world.event.GetAllianceGift
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.wpm
import pb4client.AllianceGiftGet
import pb4client.AllianceGiftGetRt
import java.util.*

// 领取联盟礼物
class GetAllianceGiftDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val giftIds = LinkedList<Long>()

        for (id in (msg as AllianceGiftGet).onlyIdList) {
            giftIds.add(id)
        }

        val getGiftRt = this.getAllianceGift(session, giftIds)
        if (getGiftRt != null) {
            getGiftRt.addAllOnlyId(msg.onlyIdList)
            session.sendMsg(MsgType.AllianceGiftGet_902, getGiftRt.build())
        }
    }

    private fun getAllianceGift(session: PlayerSession, giftIds: LinkedList<Long>): (AllianceGiftGetRt.Builder?) {
        val rt = AllianceGiftGetRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val areaCache = session.areaCache

        val player = session.player

        if (player.allianceId == 0L) {
            rt.rt = ResultCode.ALLIANCE_QUERY_NOT_EXIST.code
            return rt
        }

        // 可增加联盟大礼包的经验情况值
        var addExp = 0
        var addBigExp = 0
        val addRes = LinkedList<ResVo>() // 可获取的总奖励

        // 验证与记录数据
        val getGiftIds = LinkedList<Long>()
        val delGiftIds = LinkedList<Long>()
        for (giftId in giftIds) {
            val giftVo = areaCache.myAllianceGiftCache.findMyAllianceGiftsByPlayerIdAndId(player.id, giftId)
            if (giftVo == null) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return rt
            }

            val giftProto = pcs.allianceGiftProtoCache.allianceGiftProtoMap[giftVo.giftId]
            if (giftProto == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt
            }

            val add = resStringToResVoList(giftVo.giftInfo)
            if (add == null) {
                rt.rt = ResultCode.ALLIANCE_GIFT_ERROR.code
                return rt
            }

            if (getNowTime() > giftVo.overTime) {
                // 过期的删除
                delGiftIds.add(giftId)
            } else {
                addRes += add
                addExp += giftProto.giftExp
                addBigExp += giftProto.bigGiftExp

                getGiftIds.add(giftId)
            }
        }

        // 发奖励
        addResToHome(areaCache, ACTION_ALLIANCE_GIFT_OPEN, player.id, addRes)

        wpm.es.fire(
            session.areaCache,
            session.playerId,
            GET_ALLIANCE_GIFT,
            GetAllianceGift(getGiftIds.size)
        )

        // 去公共服同步数据
        getAllianceGift(session, player.allianceId, addExp, addBigExp, getGiftIds, delGiftIds)

        return null
    }

}
