package com.point18.slg2d.home.module.friend

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.FRIEND_CHANGE
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.FriendDC
import com.point18.slg2d.home.dc.FriendGroupDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createFriendApplySuccessNotifier
import pb4client.MoveInGroup
import pb4client.MoveInGroupRt
import pb4server.FriendGroupNoticeAskReq
import pb4server.Home2HomeAskReq
import pb4server.Home2HomeAskResp
import xyz.ariane.util.lzWarn

// 移动好友到分组
class MoveInGroupDeal : HomeClientMsgDeal, HomeHelperPlus2<FriendGroupDC, FriendDC>(
    FriendGroupDC::class.java, FriendDC::class.java
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { friendGroupDC: FriendGroupDC, friendDC: FriendDC ->
            val playerIdList = (msg as MoveInGroup).playerIdList
            val groupId = msg.groupId
            val moveInGroupRt = moveInGroup(
                session, playerIdList, groupId,
                friendGroupDC, friendDC
            )
            session.sendMsg(MsgType.MoveInGroup_704, moveInGroupRt)
        }
    }

    private fun moveInGroup(
        session: PlayerActor, playerList: List<Long>, groupId: Long,
        friendGroupDC: FriendGroupDC, friendDC: FriendDC
    ): MoveInGroupRt {
        val rt = MoveInGroupRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.groupId = groupId

        val groupInfo = friendGroupDC.findMyFriendGroupById(groupId)
        if (groupId.toInt() != 0 && groupInfo == null) {
            rt.rt = ResultCode.NOT_FRIEND_GROUP.code
            return rt.build()
        }

        val friendLimit = pcs.basicProtoCache.groupMemberMaxNum

        // 组员上限
        val friendInfoList = friendDC.findFriendByGroupId(groupId)
        if ((friendInfoList.size + (playerList.size)) > friendLimit) {
            rt.rt = ResultCode.GROUP_NO_MEMBER.code
            return rt.build()
        }

        for (playerID in playerList) {
            val friendInfo = friendDC.findMyFriendById(playerID)
            if (friendInfo == null) {
                continue
            }

            val askMsg = FriendGroupNoticeAskReq.newBuilder()
            askMsg.groupId = groupId
            val askMsgBuilder = Home2HomeAskReq.newBuilder()
            askMsgBuilder.playerId = playerID
            askMsgBuilder.setFriendGroupNoticeAskReq(askMsg)

            session.createACS<Home2HomeAskResp>().ask(
                session.homeShardProxy,
                askMsgBuilder.build(),
                Home2HomeAskResp::class.java
            ).whenCompleteKt { askRes, err ->
                if (err != null || askRes == null) {
                    normalLog.lzWarn { "好友移动分组失败:{$askRes}" }
                    return@whenCompleteKt
                }
            }

            // 推送好友变化消息
            val notice = createFriendApplySuccessNotifier(
                FRIEND_CHANGE,
                friendInfo.tarPlayerId,
                friendInfo.name,
                friendInfo.areaNo,
                friendInfo.vipLv,
                friendInfo.allianceShortName,
                friendInfo.photoId,
                friendInfo.castleLv,
                friendInfo.castleSkin,
                groupId,
                friendInfo.shortName
            )
            notice.notice(session)

            // 更新组信息
            friendInfo.groupId = groupId
        }

        return rt.build()
    }
}
