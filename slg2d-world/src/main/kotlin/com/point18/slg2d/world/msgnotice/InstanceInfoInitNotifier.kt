package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.PlayerSession
import pb4client.InstanceInfoInit
import pb4client.InstanceStar
import pb4client.InstanceUnitInfo
import java.util.*

class InstanceInfoInitNotifier(
    val msg: InstanceInfoInit.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.InstanceInfoInit_3305, this.msg.build())
    }
}

// 初始化客户端数据的推送
fun createInstanceInfoInitNotifier(areaCache: AreaCache, playerId: Long): InstanceInfoInitNotifier {
    val notifierBuilder = InstanceInfoInit.newBuilder()

    val instance = areaCache.instanceCache.findInstance(playerId)
    if (instance != null) {
        val starInfo = LinkedList<InstanceStar>()
        for ((floor, starNum) in instance.starInfoMap) {
            val v = pb4client.InstanceStar.newBuilder()
            v.floorId = floor
            starNum.forEach {
                v.addStarCondition(it)
            }
            starInfo.add(v.build())
        }
        notifierBuilder.addAllInstanceStars(starInfo)

        val unitInfoList = LinkedList<InstanceUnitInfo>()
        for ((unitId, vv) in instance.unitInfoMap) {
            val v = pb4client.InstanceUnitInfo.newBuilder()
            v.unitId = unitId
            for (b in vv.getStarBox) {
                v.addStarNumBox(b)
            }
            unitInfoList.add(v.build())
        }
        notifierBuilder.addAllInstanceUnitInfos(unitInfoList)
    }

    return InstanceInfoInitNotifier(notifierBuilder)
}
