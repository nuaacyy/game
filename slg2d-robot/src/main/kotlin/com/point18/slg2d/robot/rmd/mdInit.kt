package com.point18.slg2d.robot.rmd

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.robot.robotData.msgDeals

// 服务器主动发给客户端的消息号都是3000以后的，让msgDeal处理的
fun initMsgDeal() {
    msgDeals[MsgType.RefreshMoney_3000] = ::md3000RefreshMoney
    msgDeals[MsgType.DecreeChange_3015] = ::mdDecreeChange
    msgDeals[MsgType.NoReadMessageChange_3053] = ::mdNoReadMessageChange
    msgDeals[MsgType.NewChatMessage_3080] = ::mdNewChatMessage
    msgDeals[MsgType.VipChange_3133] = ::mdVipChange
    msgDeals[MsgType.EnterGameMapRt_3137] = ::mdEnterGameMap
    msgDeals[MsgType.GetVipLoginReward_3160] = ::mdGetVipLoginReward
    msgDeals[MsgType.InnerCityInfoChanged_3168] = ::mdInnerCityInfoChanged
    msgDeals[MsgType.AchievementChange_3164] = ::mdAchivementChange
    msgDeals[MsgType.EnterGameHomeRt_3200] = ::md3200EnterGameHomeRt
    msgDeals[MsgType.Disconnected] = ::mdCloseConnection
}
