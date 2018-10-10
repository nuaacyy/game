package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.getTotalPower
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.module.uiCondition.uiConditionCheck
import com.point18.slg2d.world.wpm
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.constg.ALLIANCE_OPEN
import com.point18.slg2d.common.constg.CanHelpNum
import com.point18.slg2d.common.constg.NO_FILTER
import com.point18.slg2d.common.pc.*
import pb4client.AllianceCreate
import pb4client.AllianceCreateRt
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.common.createAlliance
import com.point18.slg2d.world.common.getResearchEffectValue

//创建联盟 802
class DealCreateAlliance : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val name = (msg as AllianceCreate).allianceName
        val shortName = msg.allianceShortName
        val allianceLan = (msg.allianceLan)
        val rt = this.createAlliance(session, name, shortName, allianceLan)
        if (rt != null) {
            session.sendMsg(MsgType.AllianceCreate_802, rt)
        }
    }

    fun createAlliance(session: PlayerSession, name: String, shortName: String, allianceLan: Int): (AllianceCreateRt?) {

        val rt = AllianceCreateRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        // 先验证游戏服能验证的数据
        val areaCache = session.areaCache
        val player = session.player

        // 功能开启
        val uiConditionRt = uiConditionCheck(session.areaCache, player, ALLIANCE_OPEN)
        if (uiConditionRt != ResultCode.SUCCESS.code) {
            rt.rt = uiConditionRt
            return rt.build()
        }

        if (name.length <= 1) {
            rt.rt = (ResultCode.ALLIANCE_NAME_ERROR.code)
            return rt.build()
        }

        if (name[0] == ' ' || name.last() == ' ') {
            rt.rt = ResultCode.ALLIANCE_NAME_ERROR.code
            return rt.build()
        }

        // 联盟名称验证
        val res1 = pcs.wordCache.check(name, pcs.basicProtoCache.allianceNameLength, WORD_CHECK_ALLIANCE_NAME)
        when (res1.wordCheckResult) {
            WORD_CHECK_RESULT_FORBIDDEN -> {
                rt.rt = (ResultCode.ALLIANCE_NAME_ERROR.code)
                return rt.build()
            }

            WORD_CHECK_RESULT_LENGTH_SHORT -> {
                rt.rt = (ResultCode.ALLIANCE_NAME_LENGTH_NOT_ENOUGH.code)
                return rt.build()
            }
            WORD_CHECK_RESULT_LENGTH_EXCEED -> {
                rt.rt = (ResultCode.ALLIANCE_NAME_LENGTH_EXCEED.code)
                return rt.build()
            }
        }

        // 联盟简称 验证
        val res2 = pcs.wordCache.check(shortName, pcs.basicProtoCache.allianceShortNameLength, WORD_CHECK_SHORT_ALLIANCE_NAME)
        when (res2.wordCheckResult) {
            WORD_CHECK_RESULT_FORBIDDEN -> {
                rt.rt = (ResultCode.ALLIANCE_SHORT_NAME_NOT_ALLOWED.code)
                return rt.build()
            }
            WORD_CHECK_RESULT_LENGTH_SHORT -> {
                rt.rt = (ResultCode.ALLIANCE_SHORT_NAME_NOT_ENOUGH.code)
                return rt.build()
            }
            WORD_CHECK_RESULT_LENGTH_EXCEED -> {
                rt.rt = (ResultCode.ALLIANCE_SHORT_NAME_LENGTH_EXCEED.code)
                return rt.build()
            }

        }

        // 验证玩家是否已有联盟
        if (player.allianceId != 0L) {
            rt.rt = (ResultCode.ALLIANCE_PLAYER_HAS_ALLIANCE.code)
            return rt.build()
        }

        // 然后一次性发送RPC请求到公共服
        val myTarget = areaCache.targetCache.findMyTargetByPlayerId(session.playerId)
        val aid = wpm.generateObjIdNew(areaCache)
        if (myTarget == null) {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }

        val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
        if (castle == null) {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }

        createAlliance(session, aid, name, shortName,
                player.name, myTarget.getTotalPower(), player.lastLeaveTime, player.honor,
                getResearchEffectValue(areaCache, NO_FILTER, player, CanHelpNum),
                allianceLan, player.worldId, player.worldId, player.areaNo, player.photoProtoId, castle.lv, player.allianceNickName)

        return null
    }
}


