package com.point18.slg2d.world.module.buff

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.Buffs
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Buff
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.syncData2Home
import com.point18.slg2d.world.msgnotice.createBuffChangeNotifier
import java.util.*

class BuffTimeOverHeartHandler : IHeartHandler<AreaCache> {

    override fun handleHeart(cache: AreaCache) {
        buffTimeOverHeart(cache)
    }

    private fun buffTimeOverHeart(areaCache: AreaCache) {
        var i = 0
        while (true) {
            val buff = areaCache.buffCache.peekBuffOverTimeFinish()
            if (buff == null) {
                // 没有需要处理的
                return
            }

            // 弹出目标元素
            areaCache.buffCache.popBuffOverTime()

            if (buff.overTime == 0L) {
                // 什么都不需要做
                continue
            }

            val findBuff = areaCache.buffCache.findBuffsByPlayerIdAndId(buff.playerId, buff.id)
            if (findBuff == null) {
                // buff在过期之前已经被其他逻辑删除
                continue
            }

            buffTimeOver(areaCache, buff)

            i++
            if (i > 100000) {
                return
            }
        }
    }

    // buff过期
    private fun buffTimeOver(areaCache: AreaCache, buff: Buff) {
        areaCache.buffCache.deleteBuff(buff)
        val buffProto = pcs.buffBasicProtoCache.protoMap[buff.protoId]
        if (buffProto == null) {
            return
        }

        // 推送
        val session = fetchOnlinePlayerSession(areaCache, buff.playerId)
        if (session != null) {
            val buffChangeNotifier = createBuffChangeNotifier(
                com.point18.slg2d.common.constg.BUFF_REMOVE,
                buff.id,
                buff.protoId,
                buff.overTime,
                buff.startTime
            )
            buffChangeNotifier.notice(session)
        }

        val buffs = areaCache.buffCache.findBuffsByPlayerId(buff.playerId)
        val buffIds = LinkedList<Int>()
        for (b in buffs) {
            buffIds.add(b.protoId)
        }

        syncData2Home(areaCache, buff.playerId, Buffs, toJson(buffIds))
    }
}




