package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.world.wpm
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.event.PlayerActivityChange
import com.point18.slg2d.world.common.refreshAllHeroPower
import com.point18.slg2d.world.common.targetAddVal
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.msgnotice.createPlayerPrisonChangeNotifier
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import pb4server.UpdateInfoByHomeAskRt
import pb4server.UpdateInfoByHomeVo
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.common.syncdomain.HeroData
import com.point18.slg2d.common.syncdomain.HomeEffectData
import com.point18.slg2d.common.syncdomain.TargetData
import java.util.*

class UpdateInfoByHomeDeal : Home2WorldAskDealBase() {

    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val msg = req.updateInfoByHomeAskReq

        val rt = updateInfoByHome(areaCache, req.playerId, msg.updatesList)
        resp.updateInfoByHomeAskRt = rt
        return
    }

    private fun updateInfoByHome(
        areaCache: AreaCache,
        playerId: Long,
        updates: List<UpdateInfoByHomeVo>
    ): UpdateInfoByHomeAskRt {
        val rt = UpdateInfoByHomeAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }
        val homeInfoByHome = areaCache.infoByHomeCache.findInfoByHomeByPlayerId(playerId)
        if (homeInfoByHome == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        for (updateVo in updates) {
            when (updateVo.updateType) {
                UPDATE_INFO_BY_HOME_SKIN -> homeInfoByHome.nowSkinId = toObj(updateVo.updateValue)
                UPDATE_INFO_BY_HOME_CASTLE_LV -> {
                    val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
                    if (castle == null) {
                        rt.rt = ResultCode.PARAMETER_ERROR.code
                        return rt.build()
                    }
                    castle.lv = toObj(updateVo.updateValue)
                    noticeCellUpdate(areaCache, castle.x, castle.y)
                }
                UPDATE_INFO_BY_HOME_HERO -> {
                    val heroInfoList = toObj<LinkedList<HeroData>>(updateVo.updateValue)
                    heroInfoList.forEach {
                        val hero = areaCache.heroCache.findHeroById(playerId, it.id)
                        if (hero == null) {
                            //创建英雄
                            areaCache.heroCache.createHero(playerId, it.id, it.protoId, it.equipMap)

                        } else {
                            //修改英雄数据
                            hero.level = it.lv
                            hero.advLv = it.star
                            hero.awake = it.awake
                            hero.heroEquipInfoMap = it.equipMap
                            hero.exp = it.exp
                            hero.skillId1 = it.skill1
                            hero.skillId2 = it.skill2
                            hero.skillId3 = it.skill3
                            hero.skillId4 = it.skill4
                        }
                    }
                    refreshAllHeroPower(areaCache, playerId, true)
                }
                UPDATE_INFO_BY_HOME_TARGET -> {
                    val target = areaCache.targetCache.findMyTargetByPlayerId(playerId)
                    if (target == null) {
                        rt.rt = ResultCode.PARAMETER_ERROR.code
                        return rt.build()
                    }
                    val targetInfo = toObj<TargetData>(updateVo.updateValue)

                    val oldPower = target.getTotalPower()
                    assert(targetInfo.values.isNotEmpty())
                    when (targetInfo.targetId) {
                        CasinoNormalMonsterScore -> {
                            wpm.es.fire(
                                areaCache,
                                playerId,
                                com.point18.slg2d.common.constg.PLAYER_ACTIVITY_CHANGE,
                                PlayerActivityChange(
                                    ATK_NORMAL_MONSTER,
                                    (targetInfo.values[0]).toInt(),
                                    0
                                )
                            )
                        }
                        CasinoHighMonsterScore -> {
                            wpm.es.fire(
                                areaCache,
                                playerId,
                                com.point18.slg2d.common.constg.PLAYER_ACTIVITY_CHANGE,
                                PlayerActivityChange(
                                    ATK_HIGH_MONSTER,
                                    (targetInfo.values[0]).toInt(),
                                    0
                                )
                            )
                        }
                        else -> {
                            targetAddVal(areaCache, playerId, targetInfo.targetId, targetInfo.values)
                        }
                    }
                }
                UPDATE_INFO_BY_HOME_ICON -> {
                    player.photoProtoId = toObj(updateVo.updateValue)
                    val mainHero = areaCache.heroCache.findHeroById(playerId, player.mainHeroId)
                    if (mainHero != null && mainHero.mainHeroPrisonPlayerId != 0L) {
                        val prisonInfo =
                            areaCache.prisonCache.findPrisonsByPlayerIdAndPrisonPlayerId(mainHero.mainHeroPrisonPlayerId, playerId)
                        if (prisonInfo != null) {
                            val session = fetchOnlinePlayerSession(areaCache, mainHero.mainHeroPrisonPlayerId)
                            if (session != null) {
                                val noticeAboutPrison = createPlayerPrisonChangeNotifier(areaCache, ADD_PRISON, prisonInfo)
                                if (noticeAboutPrison != null) {
                                    noticeAboutPrison.notice(session)
                                }
                            }
                        }
                    }
                }
                UPDATE_INFO_BY_HOME_BUILD_INFO -> {
                    val updateInfoByHomeBuildInfoVo = toObj<UpdateInfoByHomeBuildInfoVo>(updateVo.updateValue)
                    homeInfoByHome.buildInfoMap[updateInfoByHomeBuildInfoVo.buildType] =
                        updateInfoByHomeBuildInfoVo.buildLv
                }
                UPDATE_INFO_BY_HOME_EFFECT -> {
                    val effectData = toObj<HomeEffectData>(updateVo.updateValue)
                    homeInfoByHome.effectMap[effectData.effectType] = effectData.effectMap
                }
                UPDATE_INFO_BY_HOME_VIP_LV -> {
                    val vipLv = toObj<Int>(updateVo.updateValue)
                    homeInfoByHome.vipLv = vipLv

                    //刷新vip效果
                    val vipEffectMap = hashMapOf<Int, Int>()
                    pcs.vipSetCache.vipSetMap[vipLv]?.ability?.forEach {
                        vipEffectMap[it.key] = it.value
                    }
                    homeInfoByHome.effectMap[VIP_EFFECT] = vipEffectMap
                }
                UPDATE_INFO_BY_HOME_PLAYER_NAME -> {
                    player.name = toObj(updateVo.updateValue)
                    val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
                    if (castle != null) {
                        noticeCellUpdate(areaCache, castle.x, castle.y)
                    }
                }
                UPDATE_INFO_BY_HOME_PLAYER_NICK_NAME -> {
                    player.allianceNickName = toObj(updateVo.updateValue)
                    val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
                    if (castle != null) {
                        noticeCellUpdate(areaCache, castle.x, castle.y)
                    }
                }
                UPDATE_INFO_BY_HOME_PLAYER_KING_LV -> {
                    player.kingLv = toObj(updateVo.updateValue)
                }
                UPDATE_INFO_BY_HOME_PLAYER_KING_EXP -> {
                    player.kingExp = toObj(updateVo.updateValue)
                }
                UPDATE_INFO_BY_HOME_UNITTASK -> {
                    player.unitTaskId = toObj(updateVo.updateValue)
                }
                UPDATE_INFO_BY_TASK_FINISH -> {
                    homeInfoByHome.finishTasks[toObj(updateVo.updateValue)] = 1
                }
            }
        }

        return rt.build()
    }
}