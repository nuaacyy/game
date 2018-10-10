package com.point18.slg2d.world.common

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Player
import com.point18.slg2d.world.area4data.WonderRankVo
import xyz.ariane.util.lzDebug
import java.util.*

fun getResearchEffPlusRate(
    areaCache: AreaCache,
    filterType: Int,
    player: Player,
    rEffType: Int
): Double {
    return 1.0 + getResearchEffectValue(areaCache, filterType, player, rEffType) / 10000.0
}

// 取科技效果的值
fun getResearchEffectValue(
    areaCache: AreaCache,
    filterType: Int,
    player: Player,
    researchEffectType: Int
): Int {
    var allValue = 0

    val infoByHome = areaCache.infoByHomeCache.findInfoByHomeByPlayerId(player.id)
    val nowTime = getNowTime()

    // 祭坛效果
    if (FILTER_ALTAR_KILL_NUM == FILTER_ALTAR_KILL_NUM and filterType) {
        val altarBuffVal = pcs.altarBuffProtoCache.getAltarEff(player.prisonKillNum, researchEffectType)
        allValue += altarBuffVal
    }

    // 建筑效果
    if (FILTER_INNER_CITY == FILTER_INNER_CITY and filterType) {
        allValue += infoByHome?.getEffectByType(BUILDING_EFFECT, researchEffectType) ?: 0
    }

    // 科技效果
    if (FILTER_RESEARCH == FILTER_RESEARCH and filterType) {
        allValue += infoByHome?.getEffectByType(RESEARCH_EFFECT, researchEffectType) ?: 0
    }

    // 天赋效果
    if (FILTER_TALENT == FILTER_TALENT and filterType) {
        allValue += infoByHome?.getEffectByType(TALENT_EFFECT, researchEffectType) ?: 0
    }

    // vip效果
    if (FILTER_VIP == FILTER_VIP and filterType) {
        allValue += infoByHome?.getEffectByType(VIP_EFFECT, researchEffectType) ?: 0
    }

    // 皮肤效果
    if (FILTER_SKIN == FILTER_SKIN and filterType) {
        allValue += infoByHome?.getEffectByType(SKIN_EFFECT, researchEffectType) ?: 0
    }

    // 装备效果
    if (FILTER_EQUIP == FILTER_EQUIP and filterType) {
        allValue += infoByHome?.getEffectByType(EQUIP_EFFECT, researchEffectType) ?: 0
    }

    // 技能效果
    if (FILTER_SKILL == FILTER_SKILL and filterType) {
        allValue += infoByHome?.getEffectByType(SKILL_EFFECT, researchEffectType) ?: 0
    }

    // 监狱效果
    if (FILTER_PRISON == FILTER_PRISON and filterType) {
        val allPrison = areaCache.prisonCache.findPrisonsByPlayerId(player.id)
        var maxBuffLv = 0

        if (player.maxLvPrisonBuffEndTime > nowTime) {
            maxBuffLv = pcs.basicProtoCache.fakeLordLevel
        } else {
            for (prison in allPrison) {
                val bePrisonPlayer = areaCache.playerCache.findPlayerById(prison.prisonPlayerId)
                if (bePrisonPlayer == null) {
                    normalLog.error(" EffectHelper.kt :  bePrisonPlayer == null")
                    continue
                }
                if (maxBuffLv < bePrisonPlayer.kingLv) {
                    maxBuffLv = bePrisonPlayer.kingLv
                }
            }
        }

        val mapPrisonPlayerKingLv = pcs.prisonBuffProtoCache.prisonBuffProtoMap[maxBuffLv]
        if (mapPrisonPlayerKingLv != null) {
            val buffMap = mapPrisonPlayerKingLv.buffMap
            allValue += (buffMap[researchEffectType] ?: 0)
        }
    }

    // 奇观效果
    if (FILTER_WONDER == FILTER_WONDER and filterType) {
        val wonderMap = hashMapOf<Int, Int>()
        for (protoMap in player.allianceOccupyInfo.values) {
            for ((wonderProtoId, _) in protoMap) {
                val wonderProto =
                    pcs.wonderLocationProtoCache.wonderLocationProtoMap[wonderProtoId]
                if (wonderProto != null) {
                    if (wonderMap[wonderProtoId] == null) {
                        allValue += wonderProto.buffMap[researchEffectType] ?: 0
                        wonderMap[wonderProtoId] = 1 // 相同奇观只有一个生效
                    }
                }
            }
        }
    }

    // 王国buff效果
    if (FILTER_WHOLE_COUNTRY_BUFF == FILTER_WHOLE_COUNTRY_BUFF and filterType) {
        val bigWonder = areaCache.wonderCache.findBigWonder()
        if (bigWonder != null) {
            val rankList = LinkedList<WonderRankVo>(bigWonder.rankInfoMap.values)
            rankList.sortByDescending { it.score }
            val top1AllianceId = if (rankList.size >= 1) rankList[0].allianceId else null
            val top2AllianceId = if (rankList.size >= 2) rankList[1].allianceId else null
            val top3AllianceId = if (rankList.size >= 3) rankList[2].allianceId else null
            for ((_, kingBuff) in bigWonder.buffMap) {
                if (nowTime < kingBuff[0].startTime || kingBuff[0].endTime < nowTime) {
                    continue
                }
                val buffBasicProto = pcs.buffBasicProtoCache.protoMap[kingBuff[0].protoId]
                if (buffBasicProto == null) {
                    normalLog.lzDebug { " 找不到Buff配置${kingBuff[0].protoId}  " }
                    continue
                }
                allValue += (buffBasicProto.effectMap[researchEffectType] ?: 0)
                if (player.allianceId == top1AllianceId) {
                    val top1BuffBasicProto = pcs.buffBasicProtoCache.protoMap[kingBuff[1].protoId]
                    if (top1BuffBasicProto == null) {
                        normalLog.lzDebug { " 找不到Buff配置${kingBuff[1].protoId}  " }
                        continue
                    }
                    allValue += (top1BuffBasicProto.effectMap[researchEffectType] ?: 0)
                } else if (player.allianceId == top2AllianceId) {
                    val top2BuffBasicProto = pcs.buffBasicProtoCache.protoMap[kingBuff[2].protoId]
                    if (top2BuffBasicProto == null) {
                        normalLog.lzDebug { " 找不到Buff配置${kingBuff[2].protoId}  " }
                        continue
                    }
                    allValue += (top2BuffBasicProto.effectMap[researchEffectType] ?: 0)
                } else if (player.allianceId == top3AllianceId) {
                    val top3BuffBasicProto = pcs.buffBasicProtoCache.protoMap[kingBuff[3].protoId]
                    if (top3BuffBasicProto == null) {
                        normalLog.lzDebug { " 找不到Buff配置${kingBuff[3].protoId}  " }
                        continue
                    }
                    allValue += (top3BuffBasicProto.effectMap[researchEffectType] ?: 0)
                }
            }
        }
    }

    // 官职效果
    if (FILTER_OFFICE == FILTER_OFFICE and filterType) {
        val posId = findOfficeByPlayerId(areaCache, player.id)
        if (posId != 0) {
            val pos = pcs.officeProtoCache.officeProtoMap[posId]
            if (pos != null) {
                allValue += pos.buffMap[researchEffectType] ?: 0
            }
        }
    }

    // 祭坛buff效果
    if (FILTER_ALTER_BUFF == FILTER_ALTER_BUFF and filterType) {
        val buffs = areaCache.buffCache.findBuffsByPlayerId(player.id)
        for (buff in buffs) {
            val buffProto = pcs.buffBasicProtoCache.protoMap[buff.protoId] ?: continue
            for ((effType, effValue) in buffProto.effectMap) {
                if (effType != researchEffectType) {
                    continue
                }
                allValue += effValue
            }
        }
    }

    if (allValue < 0) {
        allValue = 0
    }

    return allValue
}