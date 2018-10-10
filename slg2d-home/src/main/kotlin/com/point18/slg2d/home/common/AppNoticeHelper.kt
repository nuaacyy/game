package com.point18.slg2d.home.common

import com.point18.slg2d.common.constg.NOTICE_TYPE_CENTER
import com.point18.slg2d.common.constg.OPEN_SETTING_TEST
import com.point18.slg2d.common.constg.TEXT_READ_INFO
import com.point18.slg2d.common.constg.ZH_CN
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.worldentities.NoticeSwitchVo
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomeSyncDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import java.time.LocalDateTime

class AppNoticeHelper : HomeHelperPlus1<HomeSyncDC>(HomeSyncDC::class.java) {

    fun pushAppNotice(
        session: PlayerActor,
        protoId: Int,
        enemyLv: Int,
        vararg params: String
    ) {
        prepare(session) { homeSyncDC ->
            val checkResult = checkNoticeSetting(homeSyncDC, session, protoId, enemyLv)

            if (checkResult.flag) {
                val contentLan = pcs.lanWithParam(ZH_CN, checkResult.contentLanId, *params)
                val titleLan = pcs.lanWithParam(ZH_CN, checkResult.titleLanId)

                // todo jh app 测试用
                sendMarqueeAsk2World(
                    session,
                    NOTICE_TYPE_CENTER,
                    session.playerId,
                    "$titleLan:$contentLan",
                    TEXT_READ_INFO
                )
            }
        }
    }

    private class NoticeSettingCheckRt(
        var flag: Boolean,
        var titleLanId: String,
        var contentLanId: String,
        var music: String,
        var icon: String
    )

    private fun checkNoticeSetting(
        homeSyncDC: HomeSyncDC,
        session: PlayerActor,
        protoId: Int,
        enemyLv: Int
    ): NoticeSettingCheckRt {

        val rt = NoticeSettingCheckRt(false, "", "", "", "")

        if (!OPEN_SETTING_TEST) {
            if (session.isOnline()) return rt
        }

        val syncData = homeSyncDC.syncData

        val appNoticeProto = pcs.appNoticeProtoCache.appNoticeProtoMap[protoId] ?: return rt

        // 获取开关
        val switchRecord = syncData.switches[appNoticeProto.typeId]
        val switch = if (switchRecord == null) {
            NoticeSwitchVo(appNoticeProto.typeId, appNoticeProto.disturbSwitch, appNoticeProto.switch)
        } else {
            switchRecord
        }

        // 禁用通知，返回false
        if (switch.switch == 0) return rt

        // 勿扰开启，判断勿扰时间
        if (switch.refuseDisturb == 1) {
            // 小于警戒等级返回false
            val cautionLv = if (syncData.cautionLv == -1) {
                pcs.basicProtoCache.ignoreTownLevel
            } else syncData.cautionLv

            if (enemyLv != 0) {
                if (enemyLv <= cautionLv) return rt
            }

            val refuseDisturbOpen = if (syncData.refuseDisturbOpen == -1) {
                val startHour = pcs.basicProtoCache.disturbMin[0][0]
                val startMin = pcs.basicProtoCache.disturbMin[0][1]
                startHour * 60 + startMin
            } else syncData.refuseDisturbOpen

            val refuseDisturbEnd = if (syncData.refuseDisturbEnd == -1) {
                val endHour = pcs.basicProtoCache.disturbMin[1][0]
                val endMin = pcs.basicProtoCache.disturbMin[1][1]
                endHour * 60 + endMin
            } else syncData.refuseDisturbEnd

            val now = LocalDateTime.now()
            val allMinutes = now.hour * 60 + now.minute
            if (refuseDisturbOpen <= refuseDisturbEnd) {
                if (allMinutes in refuseDisturbOpen..refuseDisturbEnd) {
                    return rt
                }
            } else {
                if (allMinutes >= refuseDisturbOpen || allMinutes <= refuseDisturbEnd) {
                    return rt
                }
            }
        }

        rt.flag = true
        rt.titleLanId = appNoticeProto.idTitle
        rt.contentLanId = appNoticeProto.message
        rt.music = appNoticeProto.messagemusic
        rt.icon = appNoticeProto.messageIcon

        return rt
    }
}