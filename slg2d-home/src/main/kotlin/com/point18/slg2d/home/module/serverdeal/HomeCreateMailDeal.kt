package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.NEW_MAIL_SETTING
import com.point18.slg2d.common.constg.UnRead
import com.point18.slg2d.common.constg.boolToInt
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.resStringToResVoList
import com.point18.slg2d.common.pc.resVoToResString
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.AppNoticeHelper
import com.point18.slg2d.home.dc.MailDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.H2HTell
import com.point18.slg2d.home.msgnotice.createGetNewMailNotifier
import pb4server.Home2HomeTell
import java.util.*
import java.util.Arrays.asList

class HomeCreateMailDeal(
    private val appNoticeHelper: AppNoticeHelper = AppNoticeHelper()
) : H2HTell,
    HomeHelperPlus1<MailDC>(
        MailDC::class.java,
        asList(appNoticeHelper)
    ) {

    override fun dealHomeTell(session: PlayerActor, worldId: Long, playerId: Long, msg: Home2HomeTell) {
        prepare(session) { mailDC: MailDC ->
            val tell = msg.createMailTell
            val attach = resStringToResVoList(tell.attach)
            val isDraw = boolToInt(attach == null || attach.count() == 0)
            val res = if (attach != null) {
                LinkedList<ResVo>(attach)
            } else {
                null
            }

            val mailInfoVo = tell.mailInfo
            val mailInfo = MailInfo(
                mailInfoVo.readType,
                mailInfoVo.title,
                LinkedList(mailInfoVo.titleParamList),
                mailInfoVo.message,
                LinkedList(mailInfoVo.messageParamList)
            )

            mailDC.createMail(
                playerId,
                tell.sendPlayerId,
                tell.sendPlayerName,
                tell.sendPlayerNickName,
                tell.sendAllianceId,
                tell.sendAllianceShortName,
                tell.mailType,
                UnRead,
                resVoToResString(res),
                isDraw,
                getNowTime(),
                tell.sysId,
                mailInfo,
                tell.extend1
            )
            val getNewMail = createGetNewMailNotifier(tell.mailType)
            getNewMail.notice(session)

            appNoticeHelper.pushAppNotice(
                session,
                NEW_MAIL_SETTING,
                0
            )
        }
    }
}