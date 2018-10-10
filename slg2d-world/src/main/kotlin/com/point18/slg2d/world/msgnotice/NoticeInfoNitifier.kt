package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.Notice
import pb4client.NoticeInfo

// 公告信息
class NoticeInfoNotifier(
    val msg: NoticeInfo.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.NoticeInfo_3055, this.msg.build())
    }
}

fun createNoticeInfoNotifier(noticeType: Int, lanId: String, readType: Int, vararg params: String): NoticeInfoNotifier {
    val noticeInfoBuilder = NoticeInfo.newBuilder()
    noticeInfoBuilder.noticeType = noticeType
    val noticeBuilder = Notice.newBuilder()
    noticeBuilder.readType = readType
    noticeBuilder.noticeLanId = lanId
    params.forEach { noticeBuilder.addNoticeParams(it) }
    noticeInfoBuilder.setNoticeInfos(noticeBuilder)
    return NoticeInfoNotifier(noticeInfoBuilder)
}

