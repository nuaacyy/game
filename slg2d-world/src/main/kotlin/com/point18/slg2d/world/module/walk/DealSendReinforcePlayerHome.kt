package com.point18.slg2d.world.module.walk

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.WalkForceGroup
import com.point18.slg2d.world.common.sendMail
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.module.walk.walkComm.goHome
import com.point18.slg2d.world.module.walk.walkComm.halfWayGoHome
import pb4client.SendReinforcePlayerHome
import pb4client.SendReinforcePlayerHomeRt
import xyz.ariane.util.lzWarn
import java.util.*
import java.util.Arrays.asList

class SendReinforcePlayerHomeDeal : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = this.sendReinforcePlayerHome(session, msg as SendReinforcePlayerHome)
        session.sendMsg(MsgType.SendReinforcePlayerHome_1260, rt.build())
    }

    // 遣返增援或驻防部队
    private fun sendReinforcePlayerHome(
        session: PlayerSession,
        sendHomeMsg: SendReinforcePlayerHome
    ): SendReinforcePlayerHomeRt.Builder {
        val rtBuilder = SendReinforcePlayerHomeRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val areaCache = session.areaCache
        val groupIds = sendHomeMsg.groupIdsList
        val player = session.player

        val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
        if (castle == null) {
            normalLog.lzWarn { "找不到玩家城池信息:${player.focusCastleId}" }
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        val groupList = LinkedList<WalkForceGroup>()
        for (groupId in groupIds) {
            val group = areaCache.walkForceGroupCache.findWalkForceGroupById(groupId)
            if (group == null) {
                normalLog.lzWarn { "遣返的行军组找不到：$groupId" }
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder
            }
            groupList.add(group)
            if (group.gotoRunType != WalkReinforce && group.gotoRunType != WalkStation) {
                normalLog.lzWarn { "遣返的行军组不是增援或驻扎：$groupId" }
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder
            }
            if (group.runningState == Reinforce || group.runningState == Stationed) {
                if (group.nowX != castle.x || group.nowY != castle.y) {
                    normalLog.lzWarn { "未到达目的地：$groupId" }
                    rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                    return rtBuilder
                }
            } else if (group.runningState == Running) {
                val walk = areaCache.walkCache.findWalkByGroupId(group.id)
                if (walk == null || walk.marchAimsX != castle.x || walk.marchAimsY != castle.y) {
                    normalLog.lzWarn { "目的地坐标错误：$groupId" }
                    rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                    return rtBuilder
                }
            }
        }
        for (group in groupList) {
            when (group.runningState) {
                Running -> {
                    val walk = areaCache.walkCache.findWalkByGroupId(group.id)
                    if (walk == null) {
                        normalLog.lzWarn { "行军组状态是行军中，但找不到行军线:${group.id}" }
                        rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                        return rtBuilder
                    }
                    halfWayGoHome(areaCache, walk)
                }
                Reinforce,
                Stationed -> {
                    goHome(areaCache, castle.x, castle.y, group)
                }
            }
            val mailInfo = MailInfo(
                TEXT_READ_LAN,
                SYSTEM_MAIL,
                LinkedList(),
                WALK_BE_SEND_HOME_CONTENT,
                LinkedList(asList(player.name))
            )
            sendMail(areaCache, group.mainPlayerId, mailInfo)
        }

        return rtBuilder
    }

}

