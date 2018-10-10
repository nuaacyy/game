package com.point18.slg2d.home.common

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.HeroEquipVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.common.syncdomain.TargetData
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus4
import com.point18.slg2d.home.msgnotice.createValueChgNotifier
import pb4server.Home2WorldAskResp
import pb4server.UpdateInfoByHomeAskReq
import xyz.ariane.util.lzDebug
import xyz.ariane.util.lzWarn
import java.util.*
import java.util.Arrays.asList

/**
 * 统计目标帮助类
 */
class TargetHelper : HomeHelperPlus4<HomePlayerDC, InnerCityDC, HeroDC, HomeTaskDC>(
    HomePlayerDC::class.java,
    InnerCityDC::class.java,
    HeroDC::class.java,
    HomeTaskDC::class.java
) {

    /**
     * 增加统计目标数值
     */
    fun targetAddVal(session: PlayerActor, targetId: Int, valList: List<Int>? = null) {
        when (targetId) {
            ResearchStrength -> {
                val researchPower = getResearchPower(session)
                forwardTargetChange2World(session, targetId, LinkedList(asList(researchPower)))
            }
            BuildStrength -> {
                val buildingPower = getBuildingPower(session)
                forwardTargetChange2World(session, targetId, LinkedList(asList(buildingPower)))
            }
            HeroStrength -> {
                val heroLordPower = getHeroPower(session)
                forwardTargetChange2World(session, targetId, LinkedList(asList(heroLordPower)))
            }
            KingStrength -> {
                val kingPower = getKingPower(session)
                forwardTargetChange2World(session, targetId, LinkedList(asList(kingPower)))
            }
            MissionStrength -> {
                val missionPower = getMissionPower(session)
                forwardTargetChange2World(session, targetId, LinkedList(asList(missionPower)))
            }
            TotalHelpAlly ->
                if (valList != null && valList.isNotEmpty()) {
                    forwardTargetChange2World(session, targetId, LinkedList(asList(valList[0].toLong())))
                }
            ResearchCount,
            ReceiveOnlineGiftCount ->
                if (valList != null && valList.isNotEmpty()) {
                    forwardTargetChange2World(session, targetId, LinkedList(asList(valList[0].toLong())))
                }
            MakeEquip -> {
                if (valList == null || valList.count() < 2) {
                    return
                }
                val val1 = valList[0]
                val val2 = valList[1]
                forwardTargetChange2World(
                    session,
                    targetId,
                    LinkedList(asList(val1.toLong(), val2.toLong()))
                )
            }
            else -> {
                normalLog.lzWarn { "不支持的目标类型{$targetId}" }
            }
        }
    }

    private fun forwardTargetChange2World(
        session: PlayerActor,
        targetId: Int,
        values: LinkedList<Long>
    ) {
        val askMsg = UpdateInfoByHomeAskReq.newBuilder()
        val updateInfoByHomeVo = pb4server.UpdateInfoByHomeVo.newBuilder()
        updateInfoByHomeVo.updateType = UPDATE_INFO_BY_HOME_TARGET
        updateInfoByHomeVo.updateValue = toJson(TargetData(targetId, values))
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

    fun refreshAllHeroPower(session: PlayerActor, notice: Boolean) {
        prepare(session) { _, _, heroDC, _ ->
            refreshAllHeroPower(heroDC, session, notice)
        }
    }

    //刷新所有英雄实力
    private fun refreshAllHeroPower(heroDC: HeroDC, session: PlayerActor, notice: Boolean) {
        val heroMap = heroDC.findHeroMapByPlayer()
        val changeHeroMap = hashMapOf<Long, Hero>()
        for ((_, hero) in heroMap) {
            //重算英雄实力
            val heroPower = calHeroPower(
                hero.protoId,
                hero.level,
                hero.advLv,
                hero.awake,
                hero.skillId1,
                hero.skillId2,
                hero.skillId3,
                hero.skillId4,
                hero.heroEquipInfoMap
            )
            if (hero.heroStrength != heroPower) {
                hero.heroStrength = heroPower
                changeHeroMap[hero.id] = hero
            }
        }
        if (changeHeroMap.count() == 0) {
            return
        }
        if (!notice) {
            return
        }
        //发送英雄属性变化通知
        val notifier = createValueChgNotifier()
        for ((id, hero) in changeHeroMap) {
            notifier.append(id, HERO_POWER, hero.heroStrength)
        }
        notifier.notice(session)
    }

    //计算英雄战力
    fun calHeroPower(
        heroProtoId: Int,
        lv: Int,
        star: Int,
        awake: Int,
        skillId1: Int,
        skillId2: Int,
        skillId3: Int,
        skillId4: Int,
        equipInfoMap: HashMap<Int, HeroEquipVo>? = null
    ): Long {
        val unitBaseProto = pcs.unitBaseCache.fetchProtoById(heroProtoId)
        if (unitBaseProto == null) {
            normalLog.error("找不到英雄单位$heroProtoId")
            return 0
        }
        if (unitBaseProto.npcType == UNIT_TYPE_MONSTER) {
            return unitBaseProto.battleNum
        }

        val (atkW, defW, magicW, magicDefW, hpW, speedW, baojiW, baojilvW, hpRecoveryW,
                moraleRecoveryW, atkAddHpW, atkAddMoraleW, dodgeW, atkType) = getHeroBasicInfo(
            heroProtoId,
            lv,
            star,
            awake,
            equipInfoMap
        )
        val fightPara = pcs.fightingParaProtoCache.fightingParaProtoMapByAtkType[atkType]
        if (fightPara == null) {
            normalLog.lzDebug { "TargetHelper.kt : pc.pcs.fightingParaProtoCache.fightingParaProtoMapByAtkType[atkType] == null" }
            return 0
        }
        var atk = atkW
        var atkPara = fightPara.attackPara
        if (atkType == FashuXing) {
            atk = magicW
            atkPara = fightPara.defencePara
        }
        val propertyPower =
            atk * atkPara +
                    defW * fightPara.defencePara +
                    magicDefW * fightPara.magicDefPara +
                    hpW * fightPara.hpPara +
                    hpRecoveryW * fightPara.hpRecoverPara +
                    moraleRecoveryW * fightPara.moraleRecoverPara +
                    atkAddHpW * fightPara.attackAddHpPara +
                    atkAddMoraleW * fightPara.attackAddMoralePara +
                    dodgeW * fightPara.dodgePara +
                    baojiW * fightPara.critPara

        val unlockSkillIds = LinkedList<Int>()
        val rankMap = pcs.heroRankProtoCache.heroRankProtoCache[heroProtoId]
        if (rankMap == null) {
            normalLog.lzDebug { "TargetHelper.kt : pc.pcs.heroRankProtoCache.heroRankProtoCache[heroProtoId] == null" }
            return 0
        }
        val heroRankProto = rankMap[awake]
        if (heroRankProto == null) {
            normalLog.lzDebug { "TargetHelper.kt : rankMap[awake] == null" }
            return 0
        }
        for (pos in heroRankProto.unlockSkillPosList) {
            when (pos) {
                1 -> unlockSkillIds += skillId1
                2 -> unlockSkillIds += skillId2
                3 -> unlockSkillIds += skillId3
                4 -> unlockSkillIds += skillId4
            }
        }
        var skillPower = 0.0
        for (skillId in unlockSkillIds) {
            val skill = pcs.heroSkillProtoCache.heroSkillMap[skillId] ?: continue

            skillPower += skill.fightValue
        }

        return propertyPower.toLong() + skillPower.toLong()
    }

    /** 获取科研实力 **/
    fun getResearchPower(session: PlayerActor): Long {
        return prepare(session) { homePlayerDC: HomePlayerDC, _: InnerCityDC, _: HeroDC, _: HomeTaskDC ->
            getResearchPower(homePlayerDC)
        }
    }

    private fun getResearchPower(homePlayerDC: HomePlayerDC): Long {
        val player = homePlayerDC.player
        var researchPower = 0L
        for (data in player.researchInfoMap) {
            val infoMap = pcs.researchCache.researchProtoMapByLv[data.key] ?: continue

            val info = infoMap[data.value.researchLv] ?: continue

            researchPower += info.power
        }
        return researchPower
    }

    /** 获取建筑实力 **/
    fun getBuildingPower(session: PlayerActor): Long {
        return prepare(session) { _: HomePlayerDC, innerCityDC: InnerCityDC, _: HeroDC, _: HomeTaskDC ->
            getBuildingPower(innerCityDC)
        }
    }

    private fun getBuildingPower(innerCityDC: InnerCityDC): Long {
        var buildingPower = 0L
        innerCityDC.findAllInnerCityBuildings().forEach {
            val info = pcs.innerBuildingDataCache.fetchProtoByTypeLv(it.cityType, it.lv) ?: return@forEach
            buildingPower += info.power
        }
        return buildingPower
    }

    fun getHeroPower(session: PlayerActor): Long {
        return prepare(session) { _, _, heroDC, _ ->
            getHeroPower(heroDC, session)
        }
    }

    //获取英雄实力
    private fun getHeroPower(heroDC: HeroDC, session: PlayerActor): Long {
        var heroLordPower = 0L
        heroDC.findHeroMapByPlayer().forEach { _, hero ->
            val rankMap = pcs.heroRankProtoCache.heroRankProtoCache[hero.protoId]
            if (rankMap == null) {
                //Assert
                return@forEach
            }
            val rankProto = rankMap[hero.awake]
            if (rankProto != null) {
                heroLordPower += rankProto.power
            }
            val starMap = pcs.heroStarProtoCache.heroStarProtoCache[hero.protoId]
            if (starMap == null) {
                //Assert
                return@forEach
            }
            val starProto = starMap[hero.advLv]
            if (starProto != null) {
                heroLordPower += starProto.power
            }
        }
        return heroLordPower
    }

    /** 获取君主实力 **/
    fun getKingPower(session: PlayerActor): Long {
        return prepare(session) { homePlayerDC: HomePlayerDC, _: InnerCityDC, _: HeroDC, _: HomeTaskDC ->
            getKingPower(homePlayerDC)
        }
    }

    private fun getKingPower(homePlayerDC: HomePlayerDC): Long {
        val player = homePlayerDC.player
        return pcs.kingExpCache.kingExpMap[player.kingLv]?.power?.toLong() ?: 0L
    }

    /** 获取任务实力 **/
    fun getMissionPower(session: PlayerActor): Long {
        return prepare(session) { homePlayerDC: HomePlayerDC, innerCityDC: InnerCityDC, heroDC: HeroDC, homeTaskDC: HomeTaskDC ->
            getMissionPower(homeTaskDC, session)
        }
    }

    private fun getMissionPower(homeTaskDC: HomeTaskDC, session: PlayerActor): Long {
        var missionPower = 0L
        val allTasks = homeTaskDC.findAllTaskByPlayerId()
        for (task in allTasks) {
            if (task.taskNowState != TaskHasGetReward) {
                continue
            }
            val taskProto = pcs.questCache.findSpecTaskProto(task.taskProtoId)
            if (taskProto == null) {
                //Assert
                continue
            }
            missionPower += taskProto.power
        }
        return missionPower
    }
}
