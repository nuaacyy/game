package com.point18.slg2d.home.common

import com.point18.slg2d.common.constg.SYS_MAIL
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.resVoToResString
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import pb4server.CreateMailTell
import pb4server.MailInfoVo
import java.util.*

class MailHelper : HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    fun sendMail(
        session: PlayerActor,
        targetPlayerId: Long,
        mailInfo: MailInfo,
        mailType: Int = SYS_MAIL,
        attach: LinkedList<ResVo>? = null,
        sysId: Long = 0,
        extend1: String = ""
    ) {
        prepare(session) { homePlayerDC: HomePlayerDC ->
            val player = homePlayerDC.player
            var playerName = player.name
            var playerNickName = player.allianceNickName
            if (mailType == SYS_MAIL) {
                playerName = ""
                playerNickName = ""
            }

            val msgBuilder = CreateMailTell.newBuilder()
            msgBuilder.setMailInfo(newMailInfoVoBuilder(mailInfo))
            msgBuilder.sendPlayerId = player.playerId
            msgBuilder.sendPlayerName = playerName
            msgBuilder.sendPlayerNickName = playerNickName
            msgBuilder.sendAllianceId = player.allianceId
            msgBuilder.sendAllianceName = player.allianceName
            msgBuilder.sendAllianceShortName = player.allianceShortName
            msgBuilder.mailType = mailType
            msgBuilder.attach = resVoToResString(attach)
            msgBuilder.sysId = sysId
            msgBuilder.extend1 = extend1

            session.tellHome(session.fillHome2HomeTellMsgHeader(targetPlayerId) {
                it.setCreateMailTell(msgBuilder)
            })
        }
    }

    private fun newMailInfoVoBuilder(mailInfo: MailInfo): MailInfoVo.Builder {
        val mailInfoVo = MailInfoVo.newBuilder()
        mailInfoVo.readType = mailInfo.readType
        mailInfoVo.title = mailInfo.title
        mailInfoVo.message = mailInfo.message
        mailInfoVo.addAllTitleParam(mailInfo.titleParam)
        mailInfoVo.addAllMessageParam(mailInfo.messageParam)
        return mailInfoVo
    }
}