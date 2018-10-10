package com.point18.slg2d.home.common

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.common.syncdomain.HomeEffectData
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus8
import pb4server.Home2WorldAskResp
import pb4server.UpdateInfoByHomeAskReq
import xyz.ariane.util.lzDebug
import java.util.*

class ResearchEffectHelper :
    HomeHelperPlus8<HomePlayerDC, HeroDC, VipDC, SkinDC, NewEquipDC, BagDC, HomeSyncDC, GiftBagRecordDC>(
        HomePlayerDC::class.java, HeroDC::class.java, VipDC::class.java, SkinDC::class.java, NewEquipDC::class.java,
        BagDC::class.java, HomeSyncDC::class.java, GiftBagRecordDC::class.java
    ) {

    // 取科技效果的值
    fun getResearchEffectValue(
        session: PlayerActor,
        filterType: Int,
        researchEffectType: Int
    ): Int {
        return prepare(session) { homePlayerDC: HomePlayerDC, heroDC: HeroDC, vipDC: VipDC,
                                  skinDC: SkinDC, newEquipDC: NewEquipDC, bagDC: BagDC, homeSyncDC: HomeSyncDC,
                                  giftBagRecordDC: GiftBagRecordDC ->


            val player = homePlayerDC.player
            var allValue = 0

            // 祭坛效果
            if (FILTER_ALTAR_KILL_NUM == FILTER_ALTAR_KILL_NUM and filterType) {
                val altarBuffVal = pcs.altarBuffProtoCache.getAltarEff(player.prisonKillNum, researchEffectType)
                allValue += altarBuffVal
            }

            if (FILTER_INNER_CITY == FILTER_INNER_CITY and filterType) {
                val buildingValue = player.innerBuildingEffectInfoMap[researchEffectType]
                if (buildingValue != null) {
                    allValue += buildingValue
                }
            }

            if (FILTER_RESEARCH == FILTER_RESEARCH and filterType) {
                val effectValue = player.researchEffectInfoMap[researchEffectType]
                if (effectValue != null) {
                    allValue += effectValue
                }
            }

            if (FILTER_TALENT == FILTER_TALENT and filterType) {
                val talentEffectMap = findTalentEffectInfoMapByPlayer(heroDC, player)
                val effectValue = talentEffectMap[researchEffectType]
                if (effectValue != null) {
                    allValue += effectValue
                }
            }
            if (FILTER_VIP == FILTER_VIP and filterType) {

                val vipEffectMap = getVipEffectInfoMapByPlayerId(vipDC)
                val effectValue = vipEffectMap[researchEffectType]
                if (effectValue != null) {
                    allValue += effectValue
                }
            }
            if (FILTER_SKIN == FILTER_SKIN and filterType) {

                val allSkins = skinDC.findSkinsByPlayerId()
                for (skin in allSkins) {
                    val skinMap = pcs.skinAttributeCache.skinAttributeProtoMapBySkinTypeAndStar[skin.skinType]
                    if (skinMap == null) {
                        continue
                    }
                    val skinProto = skinMap[skin.star]
                    if (skinProto == null) {
                        continue
                    }
                    val effectValue = skinProto.effectMap[researchEffectType]
                    if (effectValue != null) {
                        allValue += effectValue
                    }
                }
            }
            if (FILTER_EQUIP == FILTER_EQUIP and filterType) {
                val equipEffectMap = getKingEquipEffect(player, heroDC, newEquipDC, bagDC)
                val effectValue = equipEffectMap[researchEffectType]
                if (effectValue != null) {
                    allValue += effectValue
                }
            }
            if (FILTER_SKILL == FILTER_SKILL and filterType) {
                val heroMap = heroDC.findHeroMapByPlayer()
                for ((_, hero) in heroMap) {
                    allValue += getIntHeroAddByRefreshType(hero, researchEffectType)
                }
            }
            if (FILTER_PRISON == FILTER_PRISON and filterType) {
                val maxBuffLv = homeSyncDC.syncData.maxLvInPrison
                val mapPrisonPlayerKingLv = pcs.prisonBuffProtoCache.prisonBuffProtoMap[maxBuffLv]
                if (mapPrisonPlayerKingLv != null) {
                    val buffMap = mapPrisonPlayerKingLv.buffMap
                    allValue += (buffMap[researchEffectType] ?: 0)
                }
            }
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
            if (FILTER_WHOLE_COUNTRY_BUFF == FILTER_WHOLE_COUNTRY_BUFF and filterType) {
                val nowTime = getNowTime()
                homeSyncDC.syncData.countryBuffList.forEach {
                    if (nowTime !in it.startTime..it.endTime) {
                        return@forEach
                    }
                    val buffBasicProto = pcs.buffBasicProtoCache.protoMap[it.buffProtoId]
                    if (buffBasicProto == null) {
                        normalLog.lzDebug { " 找不到Buff配置${it.buffProtoId}  " }
                        return@forEach
                    }
                    allValue += (buffBasicProto.effectMap[researchEffectType] ?: 0)
                }
            }
            if (FILTER_OFFICE == FILTER_OFFICE and filterType) {
                val officeProtoId = homeSyncDC.syncData.officeMap[session.worldId] // 只有本服的官职有加成

                if (officeProtoId != null) {
                    val officeProto = pcs.officeProtoCache.officeProtoMap[officeProtoId]
                    if (officeProto != null) {
                        allValue += officeProto.buffMap[researchEffectType] ?: 0
                    }
                }
            }

            if (FILTER_ALTER_BUFF == FILTER_ALTER_BUFF and filterType) {
                val buffs = homeSyncDC.syncData.buffs

                for (buffId in buffs) {
                    val buffProto = pcs.buffBasicProtoCache.protoMap[buffId] ?: continue

                    for ((effType, effValue) in buffProto.effectMap) {
                        if (effType != researchEffectType) {
                            continue
                        }
                        allValue += effValue
                    }
                }
            }

            if (FILTER_GIFTBAG == FILTER_GIFTBAG and filterType) {
                val effects = giftBagRecordDC.giftBagRecord.effects
                allValue += effects[researchEffectType] ?: 0
            }

            if (allValue < 0) {
                allValue = 0
            }
            return@prepare allValue
        }
    }

    /** 获取天赋效果集合 **/
    fun findTalentEffectInfoMapByPlayer(heroDc: HeroDC, player: HomePlayer): HashMap<Int, Int> {
        val hero = heroDc.findHeroById(player.mainHeroId)
        if (hero == null) {
            return hashMapOf()
        }
        if (hero.mainHeroState != MAIN_HERO) {
            // 领主状态异常 天赋模块与装备模块失效
            return hashMapOf()
        }

        return player.talentEffectInfoMap
    }

    /** 获取VIP效果 **/
    fun getVipEffectInfoMapByPlayerId(vipDC: VipDC): Map<Int, Int> {
        val vipInfo = vipDC.findVipByPlayerId()
        val vipProto = pcs.vipSetCache.vipSetMap[vipInfo.vipLv] ?: return hashMapOf()
        return vipProto.ability
    }

    private fun getSkinEffect(skinDC: SkinDC): Map<Int, Int> {
        val allSkins = skinDC.findSkinsByPlayerId()
        val skinEffectMap = hashMapOf<Int, Int>()
        for (skin in allSkins) {
            val skinMap = pcs.skinAttributeCache.skinAttributeProtoMapBySkinTypeAndStar[skin.skinType]
            if (skinMap == null) {
                continue
            }
            val skinProto = skinMap[skin.star]
            if (skinProto == null) {
                continue
            }
            skinProto.effectMap.forEach {
                skinEffectMap[it.key] = (skinEffectMap[it.key] ?: 0) + it.value
            }
        }
        return skinEffectMap
    }

    //同步效果值至world
    fun syncEffect2World(session: PlayerActor, effectType: EffectType) {
        return prepare(session) { homePlayerDC: HomePlayerDC, heroDC: HeroDC, vipDC: VipDC, skinDC: SkinDC, newEquipDC: NewEquipDC, bagDC: BagDC, homeSyncDC: HomeSyncDC, giftBagRecordDC: GiftBagRecordDC ->

            val player = homePlayerDC.player
            val effectMap = when (effectType) {
                RESEARCH_EFFECT -> {
                    player.researchEffectInfoMap
                }
                BUILDING_EFFECT -> {
                    player.innerBuildingEffectInfoMap
                }
                TALENT_EFFECT -> {
                    player.talentEffectInfoMap
                }
                SKIN_EFFECT -> {
                    getSkinEffect(skinDC)
                }
                EQUIP_EFFECT -> {
                    getKingEquipEffect(player, heroDC, newEquipDC, bagDC)
                }
                SKILL_EFFECT -> {
                    val heroMap = heroDC.findHeroMapByPlayer()
                    val effMap = hashMapOf<Int, Int>()
                    for ((_, hero) in heroMap) {
                        val effMap1 = getIntHeroByRefreshType(hero)
                        effMap1.forEach { k, v -> effMap[k] = (effMap[k] ?: 0) + v }
                    }
                    effMap
                }
                VIP_EFFECT -> {
                    getVipEffectInfoMapByPlayerId(vipDC)
                }
                else -> {
                    return@prepare
                }
            }

            val newEffectMap = hashMapOf<Int, Int>()
            effectMap.forEach {
                newEffectMap[it.key] = it.value
            }
            val askMsg = UpdateInfoByHomeAskReq.newBuilder()
            val updateInfoByHomeVo = pb4server.UpdateInfoByHomeVo.newBuilder()
            updateInfoByHomeVo.updateType = UPDATE_INFO_BY_HOME_EFFECT
            updateInfoByHomeVo.updateValue = toJson(HomeEffectData(effectType, newEffectMap))
            askMsg.addUpdates(updateInfoByHomeVo)

            session.createACS<Home2WorldAskResp>()
                .ask(
                    session.worldShardProxy,
                    session.fillHome2WorldAskMsgHeader { it.setUpdateInfoByHomeAskReq(askMsg) },
                    Home2WorldAskResp::class.java
                )
                .whenCompleteKt { rt, askErr ->
                    try {
                        when {
                            askErr != null -> {
                            }
                            rt == null -> {

                            }
                            else -> {
                                val updateInfoByHomeAskRt = rt.updateInfoByHomeAskRt
                                if (updateInfoByHomeAskRt.rt != ResultCode.SUCCESS.code) {

                                } else {

                                }
                            }
                        }

                    } catch (e: Exception) {
                    }
                }
        }
    }
}

