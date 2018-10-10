package com.point18.slg2d.world.msgnotice

import akka.actor.ActorRef
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.msgtrans.ScMessageAtSend
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.MonsterDamageInfo

// 魔物伤害信息推送
class MonsterDamageInfoNotifier(
    val msg: MonsterDamageInfo.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.MonsterDamageInfo_3176, this.msg.build())
    }

    fun notice(areaCache: AreaCache, channelActor: ActorRef) {
        val scMsg = ScMessageAtSend(MsgType.MonsterDamageInfo_3176, areaCache.currentClientMsgNo, this.msg.build())
        channelActor.tell(scMsg, ActorRef.noSender())
    }
}

fun createMonsterDamageInfoNotifier(
    x: Int,
    y: Int,
    bossId: Int,
    costHp: Int,
    groupId: Long
): MonsterDamageInfoNotifier {
    val infoBuilder = MonsterDamageInfo.newBuilder()
    infoBuilder.posX = x
    infoBuilder.posY = y
    infoBuilder.bossId = bossId
    infoBuilder.costHp = costHp
    infoBuilder.groupId = groupId

    return MonsterDamageInfoNotifier(infoBuilder)
}


