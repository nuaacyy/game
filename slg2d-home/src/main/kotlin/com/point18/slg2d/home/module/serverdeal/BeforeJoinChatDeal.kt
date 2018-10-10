package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.VipDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.askDeal.H2HAsk
import pb4server.BeforeJoinRoomAskRt
import pb4server.Home2HomeAskReq
import pb4server.Home2HomeAskResp

class BeforeJoinChatDeal : H2HAsk, HomeHelperPlus2<HomePlayerDC, VipDC>(
    HomePlayerDC::class.java, VipDC::class.java
) {
    override fun dealHomeAsk(session: PlayerActor, req: Home2HomeAskReq, resp: Home2HomeAskResp.Builder) {

        val receiveMsg = req.beforeJoinRoomAskReq
        val rt = BeforeJoinRoomAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.allianceShortName = ""
        rt.areaNo = 0
        rt.castleLv = 0
        rt.playerName = ""
        rt.protoId = 1
        rt.vipLv = 1
        rt.fightValue = 0
        rt.playerShortName = ""

        prepare(session) { homePlayerDC, vipDC ->
            if (homePlayerDC.player.chatRoomList.firstOrNull { it.chatRoomId == receiveMsg.groupId } != null) {
                rt.rt = ResultCode.HAVE_ROOM.code
                resp.setBeforeJoinRoomAskRt(rt)
                return@prepare
            }

            if (homePlayerDC.player.chatRoomList.size >= pcs.basicProtoCache.createGroupNumLimit) {
                rt.rt = ResultCode.NO_MORE_ROOM.code
                resp.setBeforeJoinRoomAskRt(rt)
                return@prepare
            }

            rt.allianceShortName = homePlayerDC.player.allianceShortName
            rt.areaNo = homePlayerDC.player.areaNo
            rt.castleLv = homePlayerDC.player.castleLv
            rt.playerName = homePlayerDC.player.name
            rt.protoId = homePlayerDC.player.photoProtoId
            rt.vipLv = vipDC.vipInfo.vipLv
            rt.fightValue = 0
            rt.playerShortName = homePlayerDC.player.allianceNickName
            resp.setBeforeJoinRoomAskRt(rt)
        }
    }
}