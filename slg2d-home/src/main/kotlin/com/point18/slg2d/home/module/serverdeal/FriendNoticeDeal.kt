package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.FRIEND_APPLY_WAIT_STATE
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.FriendApplyDC
import com.point18.slg2d.home.dc.FriendDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.hpm
import com.point18.slg2d.home.module.askDeal.H2HAsk
import com.point18.slg2d.home.msgnotice.createFriendApplyChangeNotifier
import pb4server.FriendNoticeAskRt
import pb4server.Home2HomeAskReq
import pb4server.Home2HomeAskResp

class FriendNoticeDeal : H2HAsk, HomeHelperPlus3<HomePlayerDC, FriendApplyDC, FriendDC>(
    HomePlayerDC::class.java, FriendApplyDC::class.java, FriendDC::class.java
) {

    override fun dealHomeAsk(session: PlayerActor, req: Home2HomeAskReq, resp: Home2HomeAskResp.Builder) {
        prepare(session) { homePlayerDC, friendApplyDC, friendDC ->
            val askMsg = req.friendNoticeAskReq
            val player = homePlayerDC.player
            val isOnline = session.isOnline()
            val rt = FriendNoticeAskRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code

            val myFriends = friendDC.findFriendById()

            // 好友数量限制
            if (myFriends.size >= pcs.basicProtoCache.friendsNumLimit) {
                rt.rt = ResultCode.NO_MORE_FRIEND.code
            }
            // 存储申请信息
            // 是否已经有申请
            var isHaveApply = 0
            val applyInfo = friendApplyDC.findMyFriendApplyById(askMsg.myPlayerId)

            if (applyInfo != null) {
                isHaveApply = 1
                applyInfo.applyTime = getNowTime()
                applyInfo.applyState = FRIEND_APPLY_WAIT_STATE
                applyInfo.applyCastleLv = askMsg.castleLv
                applyInfo.applyPlayerPhotoId = askMsg.photoProtoId
                applyInfo.applyPlayerName = askMsg.name
                applyInfo.applyPlayerAllianceShortName = askMsg.allianceShortName
                applyInfo.applyPlayerVipLv = askMsg.vipLv
                applyInfo.applyPlayerShortName = askMsg.shortName
            }
            if (isHaveApply == 0) {
                val id = hpm.generateObjIdNew()
                friendApplyDC.createFriendApply(
                    id,
                    askMsg.areaNo.toLong(),
                    askMsg.myPlayerId,
                    askMsg.photoProtoId,
                    askMsg.name,
                    askMsg.areaNo,
                    askMsg.vipLv,
                    askMsg.allianceShortName,
                    askMsg.castleLv,
                    askMsg.skinType,
                    askMsg.shortName,
                    player.playerId
                )
            }
            // 推送给客户端
            if (isOnline) {
                val nowTime = getNowTime()
                val notice = createFriendApplyChangeNotifier(
                    askMsg.myPlayerId,
                    askMsg.name,
                    askMsg.areaNo,
                    askMsg.vipLv,
                    askMsg.allianceShortName,
                    askMsg.photoProtoId,
                    askMsg.state,
                    askMsg.castleLv,
                    askMsg.skinType,
                    nowTime,
                    askMsg.shortName
                )
                notice.notice(session)
            }
            resp.setFriendNoticeAskRt(rt)
        }
    }
}
