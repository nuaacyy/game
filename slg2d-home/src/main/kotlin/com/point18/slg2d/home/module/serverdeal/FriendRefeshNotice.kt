package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.constg.FRIEND_CHANGE
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.FriendDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.H2HTell
import com.point18.slg2d.home.msgnotice.createFriendApplySuccessNotifier
import pb4server.Home2HomeTell

class FriendRefreshNoticeDeal : H2HTell, HomeHelperPlus1<FriendDC>(FriendDC::class.java) {

    override fun dealHomeTell(session: PlayerActor, worldId: Long, playerId: Long, msg: Home2HomeTell) {
        prepare(session) { friendDC ->
            val receiveMsg = msg.friendRefreshNoticeTell
            val isOnline = session.isOnline()
            val friends = friendDC.findFriendById()
            var groupId: Long = 0
            for (friend in friends) {
                if (friend.tarPlayerId == receiveMsg.myPlayerId) {
                    groupId = friend.groupId
                    friend.name = receiveMsg.name
                    friend.vipLv = receiveMsg.vipLv
                    friend.allianceShortName = receiveMsg.allianceShortName
                    friend.photoId = receiveMsg.photoProtoId
                    friend.castleLv = receiveMsg.castleLv
                    friend.castleSkin = receiveMsg.skinType
                    friend.shortName = receiveMsg.shortName
                }
            }

            if (isOnline) {
                val notice = createFriendApplySuccessNotifier(
                    FRIEND_CHANGE,
                    receiveMsg.myPlayerId,
                    receiveMsg.name,
                    receiveMsg.areaNo,
                    receiveMsg.vipLv,
                    receiveMsg.allianceShortName,
                    receiveMsg.photoProtoId,
                    receiveMsg.castleLv,
                    receiveMsg.skinType,
                    groupId,
                    receiveMsg.shortName
                )
                notice.notice(session)
            }
        }
    }

}
