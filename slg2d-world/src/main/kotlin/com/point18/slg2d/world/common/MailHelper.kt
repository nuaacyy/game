package com.point18.slg2d.world.common

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Player
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.constg.SYS_MAIL
import pb4server.CreateMailTell
import pb4server.MailInfoVo
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.resVoToResString
import java.util.*

fun sendMail(
    areaCache: AreaCache,
    targetPlayerId: Long,
    mailInfo: MailInfo,
    sendPlayer: Player? = null,
    mailType: Int = SYS_MAIL,
    attach: LinkedList<ResVo>? = null,
    sysId: Long = 0,
    extend1: String = ""
) {
    sendMail(
        areaCache,
        targetPlayerId,
        mailInfo,
        (sendPlayer?.id ?: 0L),
        sendPlayer?.name ?: "",
        sendPlayer?.allianceNickName ?: "",
        sendPlayer?.allianceId ?: 0L,
        sendPlayer?.allianceName ?: "",
        sendPlayer?.allianceShortName ?: "",
        mailType,
        attach,
        sysId,
        extend1
    )
}

fun sendMail(
    areaCache: AreaCache,
    targetPlayerId: Long,
    mailInfo: MailInfo,
    sendPlayerId: Long,
    sendPlayerName: String,
    sendPlayerNickName: String,
    sendAllianceId: Long,
    sendAllianceName: String,
    sendAllianceShortName: String,
    mailType: Int = SYS_MAIL,
    attach: LinkedList<ResVo>? = null,
    sysId: Long = 0,
    extend1: String = ""
) {

    val tell = CreateMailTell.newBuilder()
    tell.setMailInfo(newMailInfoVoBuilder(mailInfo))
    tell.sendPlayerId = sendPlayerId
    tell.sendPlayerName = sendPlayerName
    tell.sendPlayerNickName = sendPlayerNickName
    tell.sendAllianceId = sendAllianceId
    tell.sendAllianceName = sendAllianceName
    tell.sendAllianceShortName = sendAllianceShortName
    tell.mailType = mailType
    tell.attach = resVoToResString(attach)
    tell.sysId = sysId
    tell.extend1 = extend1

    areaCache.tellHome(areaCache.fillW2HTellMsgHeader(targetPlayerId) {
        it.setCreateMailTell(tell)
    })
}

fun newMailInfoVoBuilder(mailInfo: MailInfo): MailInfoVo.Builder {
    val mailInfoVo = MailInfoVo.newBuilder()
    mailInfoVo.readType = mailInfo.readType
    mailInfoVo.title = mailInfo.title
    mailInfoVo.message = mailInfo.message
    mailInfoVo.addAllTitleParam(mailInfo.titleParam)
    mailInfoVo.addAllMessageParam(mailInfo.messageParam)
    return mailInfoVo
}