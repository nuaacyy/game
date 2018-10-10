package com.point18.slg2d.world.module.walk

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.noticeMassGroup
import com.point18.slg2d.world.common.noticeReinforceMassGroup
import com.point18.slg2d.world.common.sendMail
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.module.walk.walkComm.goHome
import com.point18.slg2d.world.module.walk.walkComm.halfWayGoHome
import pb4client.CancelMass
import pb4client.CancelMassRt
import java.util.*

// 取消集结
class CancelMassDeal : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = this.cancelMass(session, msg as CancelMass)
        session.sendMsg(MsgType.CancelMass_1255, rt)
    }

    private fun cancelMass(session: PlayerSession, cancelMass: CancelMass): CancelMassRt {
        val rtBuilder = CancelMassRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val areaCache = session.areaCache
        val massId = cancelMass.massId
        rtBuilder.rt = doCancelMass(areaCache, session.playerId, massId)

        return rtBuilder.build()
    }
}

fun doCancelMass(areaCache: AreaCache, playerId: Long, massId: Long): Int {
    val mass = areaCache.massCache.findMassById(massId)
    if (mass == null) {
        // 找不到目标集结
        return ResultCode.PARAMETER_ERROR.code
    }
    if (mass.mainPlayerId != playerId) {
        //发起集结者才能取消集结
        return ResultCode.PARAMETER_ERROR.code
    }

    // 取消集结处理
    noticeMassGroup(areaCache, Del, mass)
    if (mass.fightType == WalkFightPlayer || mass.fightType == WalkOccupyWonder) {
        noticeReinforceMassGroup(areaCache, Del, mass)
    }

    // 部队回城
    when (mass.massState) {
        Mass,
        Wait -> {
            // 所有部队遣返
            for (member in mass.memberInfoList) {
                val group = areaCache.walkForceGroupCache.findWalkForceGroupById(member.groupId)
                if (group == null) {
                    normalLog.error("找不到集结中的行军组信息:${member.groupId}")
                    return ResultCode.PARAMETER_ERROR.code
                }
                if (group.runningState == Running) {
                    val walk = areaCache.walkCache.findWalkByGroupId(member.groupId)
                    if (walk == null) {
                        normalLog.error("找不到行军组对应的行军线:${member.groupId}")
                        return ResultCode.PARAMETER_ERROR.code
                    }
                    halfWayGoHome(areaCache, walk)

                } else {
                    goHome(areaCache, mass.startX, mass.startY, group)
                }
            }
        }
        Run -> {
            // 行军组遣返
            val walk = areaCache.walkCache.findWalkByGroupId(mass.groupId)
            if (walk == null) {
                normalLog.error("找不到行军组对应的行军线:${mass.groupId}")
                return ResultCode.PARAMETER_ERROR.code
            }

            // 半路回家
            halfWayGoHome(areaCache, walk)
        }
    }

    // 删除集结
    areaCache.massCache.deleteMass(mass)

    // 发送集结解散的邮件
    val mailInfo = MailInfo(
            TEXT_READ_LAN,
            SYSTEM_MAIL,
            LinkedList(),
            MASS_DISMISS_CONTENT,
            LinkedList()
    )
    for (member in mass.memberInfoList) {
        sendMail(areaCache, member.playerId, mailInfo)
    }

    return ResultCode.SUCCESS.code
}
