package com.point18.slg2d.world.module.setting

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.module.ReqDealEntered
import pb4client.GetNoticeSetting
import pb4client.GetNoticeSettingRt
import pb4client.NoticeSwitch

class GetNoticeSettingDeal : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rtBuilder = getPlayerSetting(session, msg as GetNoticeSetting)
        session.sendMsg(MsgType.GetNoticeSetting_1580, rtBuilder.build())
    }

    private fun getPlayerSetting(session: PlayerSession, msg: GetNoticeSetting): GetNoticeSettingRt.Builder {
        val rtBuilder = createGetNoticeSettingRtBuilder()

        val areaCache = session.areaCache
        val playerId = session.playerId

        val playerSetting = areaCache.playerSettingCache.findPlayerSettingByPlayerId(playerId)
        if (playerSetting == null) {
            rtBuilder.rt = ResultCode.CANT_FIND_PLAYER_SETTING.code
            return rtBuilder
        }

        rtBuilder.refuseDisturbOpen = playerSetting.refuseDisturbOpen
        rtBuilder.refuseDisturbEnd = playerSetting.refuseDisturbEnd
        rtBuilder.cautionLv = playerSetting.cautionLv

        for ((_, noticeSwitch) in playerSetting.switches) {
            val noticeSwitchBuilder = NoticeSwitch.newBuilder()
            noticeSwitchBuilder.typeProtoId = noticeSwitch.typeProtoId
            noticeSwitchBuilder.refuseDisturb = noticeSwitch.refuseDisturb
            noticeSwitchBuilder.switch = noticeSwitch.switch
            rtBuilder.addSwitches(noticeSwitchBuilder.build())
        }
        
        return rtBuilder
    }

    // 生成rtBuilder，-1是约定的默认值
    private fun createGetNoticeSettingRtBuilder(): GetNoticeSettingRt.Builder {
        val rtBuilder = GetNoticeSettingRt.newBuilder()
        rtBuilder.refuseDisturbOpen = -1
        rtBuilder.refuseDisturbEnd = -1
        rtBuilder.cautionLv = -1
        rtBuilder.rt = ResultCode.SUCCESS.code
        return rtBuilder
    }
}