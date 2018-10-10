package com.point18.slg2d.home.module.friend


import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.FRIEND_DEL
import com.point18.slg2d.common.constg.GROUP_ID_INIT
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.Friend
import com.point18.slg2d.home.dc.FriendDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createFriendApplySuccessNotifier
import pb4client.RemoveFriend
import pb4client.RemoveFriendRt
import pb4server.FriendRemoveAskReq
import pb4server.Home2HomeAskReq
import pb4server.Home2HomeAskResp
import xyz.ariane.util.lzWarn
import java.util.*

// 删除好友
class RemoveFriendDeal : HomeClientMsgDeal, HomeHelperPlus1<FriendDC>(FriendDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { friendDC: FriendDC ->
            val tarPlayerIdList = (msg as RemoveFriend).tarPlayerIdList
            val rt = this.removeFriend(session, LinkedList(tarPlayerIdList), friendDC)

            session.sendMsg(MsgType.RemoveFriend_702, rt)
        }
    }

    private fun removeFriend(session: PlayerActor, tarPlayerIdList: LinkedList<Long>, friendDC: FriendDC): RemoveFriendRt {
        val rtBuilder = RemoveFriendRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        if (tarPlayerIdList.size <= 0) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder.build()
        }

        // 判断要删除的人是否在好友列表中
        val deleteList = LinkedList<Friend>()
        for (tarPlayerId in tarPlayerIdList) {
            val friendPlayer = friendDC.findMyFriendById(tarPlayerId)
            if (friendPlayer != null) {
                deleteList.add(friendPlayer)
            }
        }

        // 删除好友
        for (friend in deleteList) {
            val askMsg = FriendRemoveAskReq.newBuilder()
            askMsg.myPlayerId = session.playerId

            val askMsgBuilder = Home2HomeAskReq.newBuilder()
            askMsgBuilder.playerId = friend.tarPlayerId
            askMsgBuilder.setFriendRemoveAskReq(askMsg)

            session.createACS<Home2HomeAskResp>().ask(
                session.homeShardProxy,
                askMsgBuilder.build(),
                Home2HomeAskResp::class.java
            ).whenCompleteKt { askRes, err ->
                if (err != null || askRes == null) {
                    normalLog.lzWarn { "删除好友失败:{$askRes}" }
                    return@whenCompleteKt
                }

                friendDC.delFriendInfo(friend)
                // 推送好友信息
                val notice = createFriendApplySuccessNotifier(
                    FRIEND_DEL,
                    friend.tarPlayerId,
                    friend.name,
                    friend.areaNo,
                    friend.vipLv,
                    friend.allianceShortName,
                    friend.photoId,
                    friend.castleLv,
                    friend.castleSkin,
                    GROUP_ID_INIT,
                    friend.shortName
                )
                notice.notice(session)
            }
        }

        return rtBuilder.build()
    }
}


