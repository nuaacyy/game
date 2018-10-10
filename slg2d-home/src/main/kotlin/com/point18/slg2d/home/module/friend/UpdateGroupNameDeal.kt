package com.point18.slg2d.home.module.friend

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.FRIEND_GROUP_CHANGE
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
import pb4client.ChangeGroup
import pb4client.ChangeGroupRt

// 群组命名
class UpdateGroupNameDeal : HomeClientMsgDeal, HomeHelperPlus1<FriendGroupDC>(FriendGroupDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { friendGroupDC: FriendGroupDC ->
            val newName = (msg as ChangeGroup).newName
            val groupId = msg.groupId
            val changeGroupRt = changGroup(session, newName, groupId, friendGroupDC)
            session.sendMsg(MsgType.ChangeGroup_707, changeGroupRt)
        }
    }

    private fun changGroup(session: PlayerActor, newName: String, groupId: Long, friendGroupDC: FriendGroupDC): ChangeGroupRt {
        val rt = ChangeGroupRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.newName = newName
        rt.groupId = groupId

        // 组名长度检测
        var newNameString = newName
        val checkMsg = pcs.wordCache.check(
            newNameString,
            pcs.basicProtoCache.groupNameLeng,
            com.point18.slg2d.common.pc.WORD_CHECK_MESSAGE
        )
        if (checkMsg.wordCheckResult == WORD_CHECK_RESULT_FORBIDDEN) {
            rt.rt = ResultCode.CHECK_WORD_ERR.code
            return rt.build()
        }
        if (checkMsg.wordCheckResult == WORD_CHECK_RESULT_LENGTH_SHORT ||
            checkMsg.wordCheckResult == WORD_CHECK_RESULT_LENGTH_EXCEED
        ) {
            rt.rt = ResultCode.CHAT_MSG_LENGTH_OVER.code
            return rt.build()
        }
        newNameString = checkMsg.newString

        val groupInfo = friendGroupDC.findMyFriendGroupById(groupId)

        if (groupInfo == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        val groupList = friendGroupDC.findPlayerFriendGroup()

        for (group in groupList) {
            if (group.id == groupId) {
                group.groupName = newNameString
                // 通知
                val notice = createFriendGroupChangeNotifier(FRIEND_GROUP_CHANGE, groupId, newNameString)
                notice.notice(session)
                return rt.build()
            }
        }

        rt.rt = ResultCode.PARAMETER_ERROR.code
        return rt.build()
    }
}
