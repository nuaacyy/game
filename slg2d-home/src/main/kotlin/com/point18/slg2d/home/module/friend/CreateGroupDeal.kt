package com.point18.slg2d.home.module.friend

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.FRIEND_GROUP_ADD
import com.point18.slg2d.common.constg.GROUP_ID_INIT
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.WORD_CHECK_RESULT_FORBIDDEN
import com.point18.slg2d.common.pc.WORD_CHECK_RESULT_LENGTH_EXCEED
import com.point18.slg2d.common.pc.WORD_CHECK_RESULT_LENGTH_SHORT
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.FriendGroupDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createFriendGroupChangeNotifier
import pb4client.GroupInfo
import pb4client.MakeFriendGroup
import pb4client.MakeFriendGroupRt

// 创建分组
class CreateGroupDeal : HomeClientMsgDeal, HomeHelperPlus1<FriendGroupDC>(FriendGroupDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { friendGroupDC: FriendGroupDC ->
            val groupName = (msg as MakeFriendGroup).groupName
            val makeFriendGroupRt = makeGroup(session, groupName, friendGroupDC)
            session.sendMsg(MsgType.MakeFriendGroup_703, makeFriendGroupRt)
        }
    }

    private fun makeGroup(session: PlayerActor, groupName: String, friendGroupDC: FriendGroupDC): MakeFriendGroupRt {
        val rt = MakeFriendGroupRt.newBuilder()
        val groupBuilder = GroupInfo.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        groupBuilder.groupId = GROUP_ID_INIT
        groupBuilder.groupName = ""

        val groupList = friendGroupDC.findPlayerFriendGroup()

        // 组名长度检测
        var newNameString = groupName
        val checkMsg = pcs.wordCache.check(
            newNameString,
            pcs.basicProtoCache.groupNameLeng,
            com.point18.slg2d.common.pc.WORD_CHECK_MESSAGE
        )

        if (checkMsg.wordCheckResult == WORD_CHECK_RESULT_FORBIDDEN) {
            rt.rt = ResultCode.CHECK_WORD_ERR.code
            rt.setGroupInfo(groupBuilder)
            return rt.build()
        }
        if (checkMsg.wordCheckResult == WORD_CHECK_RESULT_LENGTH_EXCEED ||
            checkMsg.wordCheckResult == WORD_CHECK_RESULT_LENGTH_SHORT
        ) {
            rt.rt = ResultCode.OVER_MORE_LENGTH.code
            rt.setGroupInfo(groupBuilder)
            return rt.build()
        }
        newNameString = checkMsg.newString

        // 群组上限
        val groupLimit = pcs.basicProtoCache.createGroupNumLimit
        if (groupList.size >= groupLimit) {
            rt.rt = ResultCode.GROUP_NUM_MORE.code
            rt.setGroupInfo(groupBuilder)
            return rt.build()
        }

        val groupInfo = friendGroupDC.createFriendGroup(newNameString)

        groupBuilder.groupId = groupInfo.id
        groupBuilder.groupName = groupInfo.groupName

        // 通知
        val notice = createFriendGroupChangeNotifier(FRIEND_GROUP_ADD, groupInfo.id, groupInfo.groupName)
        notice.notice(session)

        rt.setGroupInfo(groupBuilder)

        return rt.build()
    }
}
