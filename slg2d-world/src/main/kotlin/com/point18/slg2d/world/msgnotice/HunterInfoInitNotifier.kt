package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Player
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.getAllianceInvites
import pb4client.HunterInfoInit
import pb4client.HunterRecord

class HunterInfoInitNotifier(
    val msg: HunterInfoInit.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.HunterInfoInit_3303, this.msg.build())
    }
}

// 初始化客户端数据的推送
fun createHunterInfoInitNotifier(areaCache: AreaCache, player: Player): HunterInfoInitNotifier {
    val notifierBuilder = HunterInfoInit.newBuilder()

    val allCallBoss = areaCache.callBossCache.findAllCallBossByAtkPlayerId(player.id)
    for (callBoss in allCallBoss) {
        val record = HunterRecord.newBuilder()
        record.posX = callBoss.x
        record.posY = callBoss.y
        record.bossId = callBoss.bossId
        record.nowHp = callBoss.nowHp
        record.haveHunterNum = callBoss.calSameAllianceAtkRecordCount(areaCache, player.allianceId)
        notifierBuilder.addRecordInfo(record)
    }

    val allCommonBoss = areaCache.commonBossCache.findPersonalAllAtkBoss(player.id)
    for (commonBoss in allCommonBoss) {
        val record = HunterRecord.newBuilder()
        record.posX = commonBoss.x
        record.posY = commonBoss.y
        record.bossId = commonBoss.bossId
        record.nowHp = commonBoss.nowHp
        record.haveHunterNum = commonBoss.calSameAllianceAtkRecordCount(areaCache, player.allianceId)
        notifierBuilder.addRecordInfo(record)
    }

    val allActivityBoss = areaCache.activityBossCache.findAllActivityBossByAtkPlayerId(player.id)
    for (activityBoss in allActivityBoss) {
        val record = HunterRecord.newBuilder()
        record.posX = activityBoss.x
        record.posY = activityBoss.y
        record.bossId = activityBoss.bossId
        record.nowHp = activityBoss.nowHp
        record.haveHunterNum = activityBoss.calSameAllianceAtkRecordCount(areaCache, player.allianceId)
        notifierBuilder.addRecordInfo(record)
    }

    val inviteBuilders = getAllianceInvites(areaCache, player)
    inviteBuilders.forEach {
        notifierBuilder.addInviteInfo(it)
    }

    notifierBuilder.autoHunter = player.autoHunter
    notifierBuilder.autoUseEnergy = player.autoUseEnergy

    return HunterInfoInitNotifier(notifierBuilder)
}
