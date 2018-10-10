package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.HunterInfoChange
import pb4client.HunterInviteInfo
import pb4client.HunterRecord

// 猎杀信息推送
class HunterInfoChangeNotifier(
    val msg: HunterInfoChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.HunterInfoChange_3174, this.msg.build())
    }
}

fun createHunterRecordNotifier(
    changeType: Int,
    x: Int,
    y: Int,
    bossId: Int,
    nowHp: Int,
    haveHunterNum: Int
): HunterInfoChangeNotifier {
    val changeBuilder = HunterInfoChange.newBuilder()
    changeBuilder.addOrDel = changeType
    val recordBuilder = HunterRecord.newBuilder()
    recordBuilder.posX = x
    recordBuilder.posY = y
    recordBuilder.bossId = bossId
    recordBuilder.nowHp = nowHp
    recordBuilder.haveHunterNum = haveHunterNum
    changeBuilder.setRecordInfo(recordBuilder)

    return HunterInfoChangeNotifier(changeBuilder)
}

fun newHunterInviteNotifier(
    changeType: Int,
    inviteId: Long,
    latestInviteTime: Long,
    pltAreaNo: Long,
    x: Int,
    y: Int,
    bossId: Int,
    nowHp: Int,
    haveHunterNum: Int
): HunterInfoChangeNotifier {
    val changeBuilder = pb4client.HunterInfoChange.newBuilder()
    changeBuilder.addOrDel = changeType
    val inviteBuilder = pb4client.HunterInviteInfo.newBuilder()
    inviteBuilder.inviteId = inviteId
    inviteBuilder.inviteTime = latestInviteTime
    inviteBuilder.pltAreaNo = pltAreaNo
    inviteBuilder.posX = x
    inviteBuilder.posY = y
    inviteBuilder.bossId = bossId
    inviteBuilder.nowHp = nowHp
    inviteBuilder.haveHunterNum = haveHunterNum
    changeBuilder.setInviteInfo(inviteBuilder)

    return HunterInfoChangeNotifier(changeBuilder)
}

fun newHunterInviteNotifier(
    changeType: Int,
    inviteBuilder: HunterInviteInfo.Builder
): HunterInfoChangeNotifier {
    val changeBuilder = pb4client.HunterInfoChange.newBuilder()
    changeBuilder.addOrDel = changeType
    changeBuilder.setInviteInfo(inviteBuilder)

    return HunterInfoChangeNotifier(changeBuilder)
}


