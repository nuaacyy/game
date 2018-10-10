package com.point18.slg2d.world.module.boss

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.updateBossInvite
import com.point18.slg2d.world.module.ReqDealEntered
import pb4client.InviteTogetherHunter
import pb4client.InviteTogetherHunterRt

//邀请猎杀
class InviteHunterDeal : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val inviteMsg = msg as InviteTogetherHunter
        val posX = inviteMsg.posX
        val posY = inviteMsg.posY
        val rt = inviteHunter(session, posX, posY)

        session.sendMsg(MsgType.InviteTogetherHunter_1493, rt.build())
    }

    private fun inviteHunter(
        session: PlayerSession,
        posX: Int,
        posY: Int
    ): InviteTogetherHunterRt.Builder {
        val rtBuilder = InviteTogetherHunterRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val player = session.player
        if (player.allianceId == 0L) {
            rtBuilder.rt = ResultCode.ALLIANCE_PLAYER_NO_JOIN.code
            return rtBuilder
        }

        val areaCache = session.areaCache
        val boss = areaCache.commonBossCache.findCommonBossByXY(posX, posY)
            ?: areaCache.callBossCache.findCallBossByXy(posX, posY)
            ?: areaCache.activityBossCache.findActivityBossByXy(posX, posY)
        if (boss == null) {
            rtBuilder.rt = ResultCode.MONSTER_NOT_EXIST.code
            return rtBuilder
        }

        var existInvite = areaCache.bossInviteCache.findInviteByPosAndAllianceId(posX, posY, player.allianceId)
        if (existInvite == null) {
            //创建新的邀请
            existInvite = areaCache.bossInviteCache.createBossInvite(player, boss)
        }
        existInvite.latestInviteTime = getNowTime()
        updateBossInvite(areaCache, existInvite, boss)
        return rtBuilder
    }
}