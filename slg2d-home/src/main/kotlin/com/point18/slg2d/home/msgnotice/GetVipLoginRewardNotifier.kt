package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.GetVipLoginReward
import pb4client.VipLoginReward

//vip登录奖励通知
data class GetVipLoginRewardNotifier(
    val msg: pb4client.GetVipLoginReward.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.GetVipLoginReward_3160, this.msg.build())
    }
}

fun createGetVipLoginRewardNotifier(
    nowReward: String,
    nextReward: String,
    continueDay: Int,
    getRewardOrNot: Int
): GetVipLoginRewardNotifier {
    val getVipLoginRewardBuilder = GetVipLoginReward.newBuilder()
    getVipLoginRewardBuilder.continueOnlineDay = continueDay
    val vipLoginRewardBuilder = VipLoginReward.newBuilder()
    vipLoginRewardBuilder.currentReward = nowReward
    vipLoginRewardBuilder.nextReward = nextReward
    getVipLoginRewardBuilder.getRewardOrNot = getRewardOrNot
    getVipLoginRewardBuilder.setRewardInfo(vipLoginRewardBuilder)
    return GetVipLoginRewardNotifier(getVipLoginRewardBuilder)
}


