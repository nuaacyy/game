package com.point18.slg2d.home.module.friend

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.FRIEND_CHANGE
import com.point18.slg2d.common.constg.FRIEND_GROUP_DEL
import com.point18.slg2d.common.constg.GROUP_ID_INIT
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.FriendDC
import com.point18.slg2d.home.dc.FriendGroupDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createFriendApplySuccessNotifier
import com.point18.slg2d.home.msgnotice.createFriendGroupChangeNotifier
import pb4client.RemoveGroup
import pb4client.RemoveGroupRt
import pb4server.FriendGroupNoticeAskReq
import pb4server.Home2HomeAskReq
import pb4server.Home2HomeAskResp
import xyz.ariane.util.lzWarn

// 移除分组
class RemoveGroupDeal : HomeClientMsgDeal, HomeHelperPlus2<FriendGroupDC, FriendDC>(
    FriendGroupDC::class.java, FriendDC::class.java
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { friendGroupDC: FriendGroupDC, friendDC: FriendDC ->
            val groupId = (msg as RemoveGroup).groupId
            val removeGroupRt = removeGroup(session, groupId, friendGroupDC, friendDC)
            session.sendMsg(MsgType.RemoveGroup_706, removeGroupRt)
        }
    }

    private fun removeGroup(session: PlayerActor, groupId: Long, friendGroupDC: FriendGroupDC, friendDC: FriendDC): RemoveGroupRt {
        val rt = RemoveGroupRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.groupId = groupId

        val groupInfo = friendGroupDC.findMyFriendGroupById(groupId)

        if (groupInfo == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        val friendList = friendDC.findFriendByGroupId(groupId)

        // 通知
        val notice = createFriendGroupChangeNotifier(FRIEND_GROUP_DEL, groupId, groupInfo.groupName)
        notice.notice(session)

        if (friendList.size > 0) {
            for (friend in friendList) {
                // 通知
                val askMsg = FriendGroupNoticeAskReq.newBuilder()
                askMsg.groupId = groupId
                val askMsgBuilder = Home2HomeAskReq.newBuilder()
                askMsgBuilder.playerId = friend.tarPlayerId
                askMsgBuilder.setFriendGroupNoticeAskReq(askMsg)

                session.createACS<Home2HomeAskResp>().ask(
                    session.homeShardProxy,
                    askMsgBuilder.build(),
                    Home2HomeAskResp::class.java
                ).whenCompleteKt { askRes, err ->
                    if (err != null || askRes == null) {
                        normalLog.lzWarn { "删除分组:{$askRes}" }
                        return@whenCompleteKt
                    }
                    // 推送好友信息变化
                    val noticeSuccess = createFriendApplySuccessNotifier(
                        FRIEND_CHANGE,
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
                    noticeSuccess.notice(session)

                    friend.groupId = GROUP_ID_INIT
                }
            }
        }

        friendGroupDC.delFriendGroup(groupInfo)

        return rt.build()
    }
}
