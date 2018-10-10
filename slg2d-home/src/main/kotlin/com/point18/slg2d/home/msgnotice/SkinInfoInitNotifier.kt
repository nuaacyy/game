package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.SkinInfo
import pb4client.SkinInfoInit

// 成就变化推送
data class SkinInfoInitNotifier(
    val msg: pb4client.SkinInfoInit.Builder
) {
    fun append(skinType: Int, star: Int, isUse: Int) {
        val infoBuilder = SkinInfo.newBuilder()
        infoBuilder.skinType = skinType
        infoBuilder.star = star
        infoBuilder.isUse = isUse
        msg.addSkins(infoBuilder)
    }

    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.SkinInfoInit_3350, this.msg.build())
    }
}

fun createSkinInfoInitNotifier(): SkinInfoInitNotifier {
    val notifierBuilder = SkinInfoInit.newBuilder()
    return SkinInfoInitNotifier(notifierBuilder)
}