package com.point18.slg2d.home.module.mark

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.MarkDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.DelMark
import pb4client.DelMarkRt

// 删除收藏
class DelMarkDeal(
) : HomeClientMsgDeal,
    HomeHelperPlus1<MarkDC>(
        MarkDC::class.java
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val markId = (msg as DelMark).id
        prepare(session) { markDC ->
            val delMarkRt = delMark(markId, markDC)
            session.sendMsg(MsgType.DelMark_20, delMarkRt)
        }
    }

    private fun delMark(markId: Long, markDC: MarkDC): DelMarkRt {

        val rt = DelMarkRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.id = markId

        // 检测是否存在
        val markInfo = markDC.findMarkById(markId)

        if (markInfo == null) {
            rt.rt = ResultCode.MARK_NOT_DEL.code
            return rt.build()
        }

        // 删除
        markDC.delMark(markInfo)

        return rt.build()
    }
}


