package com.point18.slg2d.world.module.wonder

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.world.common.checkOfficeFunction
import com.point18.slg2d.world.common.findOfficeByPlayerId
import com.point18.slg2d.world.common.isWonderPeace
import com.point18.slg2d.world.common.sendMail
import pb4client.AwardAlliance
import pb4client.AwardAllianceRt
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*
import java.util.Arrays.asList

// 赏赐
class AwardAllianceDeal : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val awardMsg = msg as AwardAlliance
        val awardId = awardMsg.awardId
        val targetPlayerId = awardMsg.targetPlayerId
        val rt = this.awardAlliance(session, awardId, targetPlayerId)
        session.sendMsg(MsgType.AwardAlliance_1456, rt)
    }

    private fun awardAlliance(session: PlayerSession, awardId: Int, targetPlayerId: Long): AwardAllianceRt {
        val rtBuilder = AwardAllianceRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val areaCache = session.areaCache
        val playerId = session.playerId

        val proto = pcs.kingAwardProtoCache.kingAwardProtoMap[awardId]
        if (proto == null) {
            rtBuilder.rt = ResultCode.NO_PROTO.code
            return rtBuilder.build()
        }

        //校验发放奖励的权限
        val havePosId = findOfficeByPlayerId(areaCache, playerId)
        if (!checkOfficeFunction(OfficeFunction.AwardAlliance, havePosId)) {
            rtBuilder.rt = ResultCode.LIMIT_TO_AWARD_ALLIANCE.code
            return rtBuilder.build()
        }

        val wonder = areaCache.wonderCache.findBigWonder()
        if (wonder == null) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder.build()
        }

        if (!isWonderPeace(wonder)) {
            rtBuilder.rt = ResultCode.WONDER_NOT_PEACE.code
            return rtBuilder.build()
        }

        //校验是否同联盟
        val player = session.player
        val targetPlayer = areaCache.playerCache.findPlayerById(targetPlayerId)
        if (targetPlayer == null) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder.build()
        }
        if (player.allianceId == 0L || player.allianceId != targetPlayer.allianceId) {
            rtBuilder.rt = ResultCode.ALLIANCE_PLAYER_NO_JOIN.code
            return rtBuilder.build()
        }

        //校验是否已发奖励、剩余次数
        var awardList = wonder.awardInfoMap[awardId]
        if (awardList == null) {
            awardList = LinkedList()
        }

        val awardProto = pcs.kingAwardProtoCache.kingAwardProtoMap[awardId]
        if (awardProto == null) {
            rtBuilder.rt = ResultCode.NO_PROTO.code
            return rtBuilder.build()
        }

        if (awardList.size >= awardProto.limit) {
            rtBuilder.rt = ResultCode.AWARD_NUM_LIMIT.code
            return rtBuilder.build()
        }
        for (id in awardList) {
            if (id == targetPlayerId) {
                rtBuilder.rt = ResultCode.HAVE_AWARDED.code
                return rtBuilder.build()
            }
        }

        val awardMap = wonder.awardInfoMap
        awardMap[awardId] = awardList

        //添加发奖记录
        awardList.add(targetPlayerId)

        //发送邮件
        val mailInfo = MailInfo(
            TEXT_READ_LAN,
            RECEIVE_ALLIANCE_AWARD_TITLE,
            LinkedList(),
            RECEIVE_ALLIANCE_AWARD_CONTENT,
            LinkedList(asList(awardProto.name))
        )

        sendMail(areaCache, targetPlayerId, mailInfo, attach = LinkedList(proto.awardList))

        return rtBuilder.build()
    }

}
