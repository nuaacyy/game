package com.point18.slg2d.world.module.realm

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.msgnotice.*
import pb4client.RequireModuleData
import pb4client.RequireModuleDataRt

class RequireModuleDataDeal : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rtBuilder = this.getModuleData(session, msg as RequireModuleData)
        session.sendMsg(MsgType.RequireModuleData_2500, rtBuilder.build())
    }

    private fun getModuleData(session: PlayerSession, msg: RequireModuleData): RequireModuleDataRt.Builder {
        val rtBuilder = RequireModuleDataRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        initModuleData(session, msg.moduleId)

        return rtBuilder
    }

    private fun initModuleData(session: PlayerSession, moduleId: Int) {
        when(moduleId) {
            TRANSPORT_REQUEST_INIT -> initTransportRequest(session)
            PLAYER_PRISON_INIT -> initPlayerPrison(session)
            ACHIEVEMENT_INIT -> initAchievement(session)
            HUNTER_INFO_INIT -> initHunterInfo(session)
            WONDER_INFO_INIT -> initWonderInfo(session)
            INSTANCE_INFO_INIT -> initInstanceInfo(session)
        }
    }

    private fun initTransportRequest(session: PlayerSession) {
        val player = session.player
        val areaCache = session.areaCache

        val reqList =
            areaCache.transportRequestCache.findTransportRequestByAllianceId(player.allianceId)
        val notifier = createTransportRequestInitNotifier(player, reqList)
        notifier.notice(session)
    }

    private fun initPlayerPrison(session: PlayerSession) {
        val player = session.player
        val areaCache = session.areaCache

        val notifier = createPlayerPrisonInitNotifier(areaCache, player)
        notifier.notice(session)
    }

    private fun initAchievement(session: PlayerSession) {
        val playerId = session.playerId
        val areaCache = session.areaCache

        val notifier = AchievementInitNotifier(areaCache, playerId)
        notifier.notice(session)
    }

    private fun initHunterInfo(session: PlayerSession) {
        val player = session.player
        val areaCache = session.areaCache

        val notifier = createHunterInfoInitNotifier(areaCache, player)
        notifier.notice(session)
    }

    private fun initWonderInfo(session: PlayerSession) {
        val playerId = session.playerId
        val areaCache = session.areaCache

        val bigWonder = areaCache.wonderCache.findBigWonder()
        if (bigWonder != null) {
            val notifier = createWonderInfoInitNotifier(areaCache, playerId, bigWonder)
            notifier.notice(session)
        }
    }

    private fun initInstanceInfo(session: PlayerSession) {
        val playerId = session.playerId
        val areaCache = session.areaCache

        val notifier = createInstanceInfoInitNotifier(areaCache, playerId)
        notifier.notice(session)
    }

}