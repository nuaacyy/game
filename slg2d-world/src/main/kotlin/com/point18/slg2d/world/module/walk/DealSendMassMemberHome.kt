package com.point18.slg2d.world.module.walk

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.WalkForce
import com.point18.slg2d.world.common.noticeDelMassGroup
import com.point18.slg2d.world.common.noticeMassGroup
import com.point18.slg2d.world.common.sendMail
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.module.walk.walkComm.goHome
import com.point18.slg2d.world.module.walk.walkComm.halfWayGoHome
import pb4client.SendMassMemberHome
import pb4client.SendMassMemberHomeRt
import java.util.*
import java.util.Arrays.asList

class SendMassMemberHomeDeal : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = this.dealSendMassMemberHome(session, msg as SendMassMemberHome)
        session.sendMsg(MsgType.SendMassMemberHome_1259, rt)
    }

    private fun dealSendMassMemberHome(session: PlayerSession, sendHomeMsg: SendMassMemberHome): SendMassMemberHomeRt {
        val areaCache = session.areaCache
        val playerId = session.playerId
        val massId = sendHomeMsg.massId
        val targetPlayerIds = LinkedList(sendHomeMsg.tarPlayerIdsList)

        val rtBuilder = SendMassMemberHomeRt.newBuilder()
        rtBuilder.rt = sendMassMemberHome(areaCache, massId, playerId, targetPlayerIds)

        return rtBuilder.build()
    }
}

fun sendMassMemberHome(areaCache: AreaCache, massId: Long, playerId: Long, targetPlayerIds: List<Long>): Int {
    val player = areaCache.playerCache.findPlayerById(playerId)
    if (player == null) {
        normalLog.error("找不到玩家信息:%d", playerId)
        return ResultCode.PARAMETER_ERROR.code
    }
    val mass = areaCache.massCache.findMassById(massId)
    if (mass == null) {
        return ResultCode.PARAMETER_ERROR.code
    }
    if (playerId != mass.mainPlayerId) {
        return ResultCode.MassMemberUnableSendHome.code
    }
    for (targetPlayerId in targetPlayerIds) {
        if (targetPlayerId == playerId) {
            return ResultCode.MassMemberUnableSendHome.code
        }
        val member = mass.findMassMember(targetPlayerId)
        if (member == null) {
            return ResultCode.PARAMETER_ERROR.code
        }
    }

    when (mass.massState) {
        Run -> {
            val group = areaCache.walkForceGroupCache.findWalkForceGroupById(mass.groupId)
            if (group == null) {
                normalLog.error("找不到集结对应的行军组:%d", mass.groupId)
                return ResultCode.PARAMETER_ERROR.code
            }
            val walk = areaCache.walkCache.findWalkByGroupId(mass.groupId)
            if (walk == null) {
                normalLog.error("集结状态是行军中，但找不到对应的行军线:%d", mass.id)
                return ResultCode.PARAMETER_ERROR.code
            }

            val nowPos = areaCache.walkCache.calCurrentPos(walk)
            val nowX = nowPos.posX.toInt()
            val nowY = nowPos.posY.toInt()

            // 半路脱离
            val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(mass.groupId)
            val forceMap = hashMapOf<Long, WalkForce>()
            for (force in forces) {
                forceMap[force.playerId] = force
            }
            for (targetPlayerId in targetPlayerIds) {
                val force = forceMap[targetPlayerId]
                if (force == null) {
                    normalLog.error("找不到对应的部队信息:%d", targetPlayerId)
                    return ResultCode.PARAMETER_ERROR.code
                }

                // 更换部队所属行军组，然后回家
                val newGroup = areaCache.walkForceGroupCache.createWalkForceGroup(
                        targetPlayerId,
                        0,
                        Running,
                        group.gotoRunType,
                        group.nowX,
                        group.nowY
                )
                areaCache.walkForceCache.updateWalkForceGroupId(force, newGroup.id)
                goHome(areaCache, nowX, nowY, newGroup)
            }
        }
        else -> {
            for (targetPlayerId in targetPlayerIds) {
                val member = mass.findMassMember(targetPlayerId)
                if (member == null) {
                    normalLog.error("集结信息中无对应玩家信息:%d", targetPlayerId)
                    return ResultCode.PARAMETER_ERROR.code
                }
                val group = areaCache.walkForceGroupCache.findWalkForceGroupById(member.groupId)
                if (group == null) {
                    normalLog.error("找不到集结玩家行军组信息:%d", member.groupId)
                    return ResultCode.PARAMETER_ERROR.code
                }
                if (group.runningState == Running) {
                    val walk = areaCache.walkCache.findWalkByGroupId(member.groupId)
                    if (walk == null) {
                        normalLog.error("集结状态是行军中，但找不到对应玩家的行军线:%d", member.groupId)
                        return ResultCode.PARAMETER_ERROR.code
                    }
                    halfWayGoHome(areaCache, walk)
                } else {
                    goHome(areaCache, mass.startX, mass.startY, group)
                }
            }
        }
    }

    for (targetPlayerId in targetPlayerIds) {
        val mailInfo = MailInfo(
                TEXT_READ_LAN,
                SYSTEM_MAIL,
                LinkedList(),
                FORCE_BE_SEND_HOME_CONTENT,
                LinkedList(asList(player.name))
        )

        sendMail(areaCache, targetPlayerId, mailInfo)

        mass.removeMassMember(targetPlayerId)
        noticeDelMassGroup(areaCache, mass.id, asList(targetPlayerId))
    }

    noticeMassGroup(areaCache, Update, mass)
    return ResultCode.SUCCESS.code
}



