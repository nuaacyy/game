package com.point18.slg2d.world.module.setting

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.PlayerSettingSync
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.common.syncdomain.PlayerSettingData
import com.point18.slg2d.common.worldentities.NoticeSwitchVo
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.syncData2Home
import com.point18.slg2d.world.module.ReqDealEntered
import pb4client.ChangeNoticeSetting
import pb4client.ChangeNoticeSettingRt

class ChangeNoticeSettingDeal : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rtBuilder = changePlayerSetting(session, msg as ChangeNoticeSetting)
        session.sendMsg(MsgType.ChangeNoticeSetting_1581, rtBuilder.build())
    }

    private fun changePlayerSetting(session: PlayerSession, msg: ChangeNoticeSetting): ChangeNoticeSettingRt.Builder {
        val rtBuilder = ChangeNoticeSettingRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val areaCache = session.areaCache
        val playerId = session.playerId

        val playerSetting = areaCache.playerSettingCache.findPlayerSettingByPlayerId(playerId)
        if (playerSetting == null) {
            rtBuilder.rt = ResultCode.CANT_FIND_PLAYER_SETTING.code
            return rtBuilder
        }

        val refuseDisturbOpen = msg.refuseDisturbOpen
        val refuseDisturbEnd = msg.refuseDisturbEnd
        val cautionLv = msg.cautionLv

        if ((refuseDisturbOpen < 0 || refuseDisturbOpen > 24 * 60) && refuseDisturbOpen != -1) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }
        if ((refuseDisturbEnd < 0 || refuseDisturbEnd > 24 * 60) && refuseDisturbEnd != -1) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }
        if ((cautionLv < 0 || cautionLv > 99) && cautionLv != -1) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        if (refuseDisturbOpen != -1) {
            playerSetting.refuseDisturbOpen = refuseDisturbOpen
        }
        if (refuseDisturbEnd != -1) {
            playerSetting.refuseDisturbEnd = refuseDisturbEnd
        }
        if (cautionLv != -1) {
            playerSetting.cautionLv = cautionLv
        }

        for (noticeSwitch in msg.switchesList) {
            val typeProtoId = noticeSwitch.typeProtoId
            val refuseDisturb = noticeSwitch.refuseDisturb
            val switch = noticeSwitch.switch
            val noticeSwitchVo = playerSetting.switches[typeProtoId]
            if (noticeSwitchVo == null) {
                val tmpNoticeSwitchVo = NoticeSwitchVo(
                    typeProtoId,
                    -1,
                    -1
                )
                if (refuseDisturb == 0 || refuseDisturb == 1) {
                    tmpNoticeSwitchVo.refuseDisturb = refuseDisturb
                }
                if (switch == 0 || switch == 1) {
                    tmpNoticeSwitchVo.switch = switch
                }
                playerSetting.switches[typeProtoId] = tmpNoticeSwitchVo
            } else {
                if ((refuseDisturb == 0 || refuseDisturb == 1) && noticeSwitchVo.refuseDisturb != refuseDisturb) {
                    noticeSwitchVo.refuseDisturb = refuseDisturb
                }
                if ((switch == 0 || switch == 1) && noticeSwitchVo.switch != switch) {
                    noticeSwitchVo.switch = switch
                }
            }
        }

        // 同步玩家设置到home
        syncData2Home(areaCache, playerId, PlayerSettingSync, toJson(PlayerSettingData(
            playerSetting.refuseDisturbOpen,
            playerSetting.refuseDisturbEnd,
            playerSetting.cautionLv,
            playerSetting.switches
        )))

        return rtBuilder
    }
}