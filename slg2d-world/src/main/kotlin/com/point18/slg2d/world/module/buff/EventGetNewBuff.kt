package com.point18.slg2d.world.module.buff

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchEpNo
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.common.syncData2Home
import com.point18.slg2d.world.event.DefendCoverOn
import com.point18.slg2d.world.event.GetNewBuff
import com.point18.slg2d.world.event.GodOfWarOn
import com.point18.slg2d.world.msgnotice.createBuffChangeNotifier
import com.point18.slg2d.world.wpm
import java.util.*

class GetNewBuffEventHandler : IEventHandler<AreaCache> {

    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        getNewBuff(cache, event, eventType, playerId)
    }

    // 获取新buff
    private // 获取新buff
    fun getNewBuff(areaCache: AreaCache, eve: Any, eventType: EventType, playerId: Long) {
        val buffProtoId = (eve as GetNewBuff).buffProtoId
        val buffOverTime = eve.buffOverTime
        val buffPlayerId = eve.buffPlayerId

        val session = fetchOnlinePlayerSession(areaCache, buffPlayerId)
        val epNo = fetchEpNo(areaCache, buffPlayerId)

        // 先验证互斥要删除的buff
        val buffBasicProto = pcs.buffBasicProtoCache.protoMap[buffProtoId]
        if (buffBasicProto == null) {
            return
        }

        val buffProto = pcs.buffProtoCache.protoMap[buffBasicProto.typeEffect]
        if (buffProto == null) {
            return
        }

        val buffs = areaCache.buffCache.findBuffsByPlayerId(buffPlayerId)
        val delBuffs = LinkedList<Long>()
        for (buff in buffs) {
            // 删除互斥buff
            val playerBuffBasicProto = pcs.buffBasicProtoCache.protoMap[buff.protoId]
            if (playerBuffBasicProto == null) {
                continue
            }
            val blacklistMapProto = buffProto.blacklistMap[playerBuffBasicProto.typeEffect]
            if (blacklistMapProto != null) {
                delBuffs.add(buff.id)
            }

            // 删除同类型buff
            if (buffBasicProto.typeEffect == playerBuffBasicProto.typeEffect) {
                delBuffs.add(buff.id)
            }
        }

        for (delBuffId in delBuffs) {
            val delBuff = areaCache.buffCache.findBuffsByPlayerIdAndId(playerId, delBuffId)
            if (delBuff == null) {
                continue
            }

            val delBuffBasicProto = pcs.buffBasicProtoCache.protoMap[delBuff.protoId]
            if (delBuffBasicProto == null) {
                continue
            }

            areaCache.buffCache.deleteBuff(delBuff)

            if (session != null) {
                val buffChangeNotice = createBuffChangeNotifier(
                    BUFF_REMOVE,
                    delBuff.id,
                    delBuff.protoId,
                    delBuff.overTime,
                    delBuff.startTime
                )
                buffChangeNotice.notice(session)
            }
        }

        // 然后获得一个buff
        val buff = areaCache.buffCache.createBuff(buffPlayerId, buffProtoId, buffOverTime)

        // 推送
        if (session != null) {
            val buffNotice = createBuffChangeNotifier(BUFF_ADD, buff.id, buff.protoId, buff.overTime, buff.startTime)
            buffNotice.notice(session)
        }

        // 需要触发事件的buff
        if (buffBasicProto.typeEffect == BUFF_EFFECT_DEF_COVER) {
            // 抛出一个事件，告诉开启了保护罩这个buff
            wpm.es.fire(areaCache, buff.playerId, DEF_COVER_ON, DefendCoverOn())

        } else if (buffBasicProto.typeEffect == BUFF_EFFECT_WALK_EFFECT) {
            val player = areaCache.playerCache.findPlayerById(buffPlayerId)
            if (player == null) {
                return
            }

            val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
            if (castle == null) {
                return
            }
            // 触发战争狂热,如果有保护罩 要取消掉
            val (have, _, delBuff) = areaCache.buffCache.findBuffValueByBuffType(buffPlayerId, BUFF_EFFECT_DEF_COVER)
            val (have2, _, delBuff2) = areaCache.buffCache.findBuffValueByBuffType(buffPlayerId, BUFF_EFFECT_GOD_OF_WAR)
            val (have3, _, delBuff3) = areaCache.buffCache.findBuffValueByBuffType(buffPlayerId, BUFF_EFFECT_SNOW_COVER)
            if (have && delBuff != null) {
                // 删除buff
                areaCache.buffCache.deleteBuff(delBuff)

                if (session != null) {
                    val buffChangeNotice = createBuffChangeNotifier(
                        BUFF_REMOVE,
                        delBuff.id,
                        delBuff.protoId,
                        delBuff.overTime,
                        delBuff.startTime
                    )
                    buffChangeNotice.notice(session)
                }
            }
            if (have2 && delBuff2 != null) {
                // 删除buff
                areaCache.buffCache.deleteBuff(delBuff2)

                if (session != null) {
                    val buffChangeNotice = createBuffChangeNotifier(
                        BUFF_REMOVE,
                        delBuff2.id,
                        delBuff2.protoId,
                        delBuff2.overTime,
                        delBuff2.startTime
                    )
                    buffChangeNotice.notice(session)
                }
            }
            if (have3 && delBuff3 != null) {
                // 删除buff
                areaCache.buffCache.deleteBuff(delBuff3)

                if (session != null) {
                    val buffChangeNotice = createBuffChangeNotifier(
                        BUFF_REMOVE,
                        delBuff3.id,
                        delBuff3.protoId,
                        delBuff3.overTime,
                        delBuff3.startTime
                    )
                    buffChangeNotice.notice(session)
                }
            }
        } else if (buffBasicProto.typeEffect == BUFF_EFFECT_GOD_OF_WAR) {
            // 抛出一个事件，告诉开启了战神buff
            wpm.es.fire(areaCache, buff.playerId, GOD_OF_WAR_ON, GodOfWarOn())
        }

        // buff在大地图有表现, 需要让视野玩家刷新地块
        if (
            buffBasicProto.typeEffect == BUFF_EFFECT_DEF_COVER ||
            buffBasicProto.typeEffect == BUFF_EFFECT_WALK_EFFECT ||
            buffBasicProto.typeEffect == BUFF_EFFECT_GOD_OF_WAR ||
            buffBasicProto.typeEffect == BUFF_EFFECT_SNOW_COVER
        ) {
            val player = areaCache.playerCache.findPlayerById(buffPlayerId) ?: return
            val castle = areaCache.castleCache.findCastleById(player.focusCastleId) ?: return
            noticeCellUpdate(areaCache, castle.x, castle.y)
        }

        val currentBuffs = areaCache.buffCache.findBuffsByPlayerId(buffPlayerId)
        val buffIds = LinkedList<Int>()
        for (b in currentBuffs) {
            buffIds.add(b.protoId)
        }

        syncData2Home(areaCache, buff.playerId, Buffs, toJson(buffIds))
    }
}