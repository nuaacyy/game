package com.point18.slg2d.world.module.walk.fightStrategy

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.module.fightdomain.FightData
import com.point18.slg2d.world.module.fightdomain.FightInfo
import com.point18.slg2d.world.module.fightdomain.createRewardInfoForReport
import com.point18.slg2d.world.module.report.ReportInfo
import com.point18.slg2d.world.wpm
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.event.BossFight
import com.point18.slg2d.world.event.PlayerActivityChange
import com.point18.slg2d.world.common.*
import com.point18.slg2d.world.msgnotice.createHunterRecordNotifier
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.selectAllPropFromDrops
import java.util.*
import java.util.Arrays.asList

// PVE打BOSS战斗流程
class PveFightCallBossAction(var action: Int) : IBossFightDeal {
    override fun dealAfterOneFight(
        areaCache: AreaCache,
        atkGroup: WalkForceGroup,
        defGroup: WalkForceGroup?,
        atkFightData: FightData,
        defFightData: FightData,
        runType: Int,
        posX: Int,
        posY: Int,
        fightInfo: FightInfo
    ): Boolean {
        val callBoss = areaCache.callBossCache.findCallBossByXy(posX, posY)
        if (callBoss == null) {
            return false
        }

        val player = areaCache.playerCache.findPlayerById(atkFightData.playerId)
        if (player == null) {
            normalLog.error("找不到玩家信息:%d", atkFightData.playerId)
            return false
        }

        val monsterProto = pcs.monsterProtoCache.findMonsterProto(callBoss.bossId)
        if (monsterProto == null) {
            normalLog.error("魔物的配置不存在:%d", callBoss.bossId)
            return false
        }

        // 发生战斗
        val monsterLv = monsterProto.unitLv
        var atkMonsterAction = 0
        when (monsterLv) {
            1 -> atkMonsterAction = ATK_MONSTER_1
            2 -> atkMonsterAction = ATK_MONSTER_2
            3 -> atkMonsterAction = ATK_MONSTER_3
            4 -> atkMonsterAction = ATK_MONSTER_4
            5 -> atkMonsterAction = ATK_MONSTER_5
        }

        wpm.es.fire(
            areaCache,
            atkFightData.playerId,
            PLAYER_ACTIVITY_CHANGE,
            PlayerActivityChange(atkMonsterAction, 1, 0)
        )

        //处理血量与伤害
        val oldHp = fightInfo.detailInfo.record.intRecordMap[DefHpBeforeFight] ?: 0
        var currentHp = fightInfo.detailInfo.record.intRecordMap[DefHpAfterFight] ?: oldHp
        if (currentHp > oldHp) {
            currentHp = oldHp
        }
        var currentDamage = ((oldHp - currentHp) * (1.0 + getResearchEffectValue(
            areaCache,
            NO_FILTER,
            player,
            AtkMonsterHurtAdd
        ) / 10000.0) * (1.0 + getResearchEffectValue(
            areaCache,
            NO_FILTER,
            player,
            HunterRaceDamageAdd + monsterProto.race - 1
        ) / 10000.0)).toInt()
        if (oldHp - currentDamage < 0) {
            currentDamage = oldHp
        }
        currentHp = oldHp - currentDamage
        callBoss.nowHp = currentHp

        if (currentDamage > 0) {
            //更新个人排行
            val playerAtkRecord = callBoss.atkRecordsMap.getOrPut(
                atkFightData.playerId
            ) { PersonalCallBossAtkRecord(atkFightData.playerId, 0, getNowTime()) }
            playerAtkRecord.totalDamage += currentDamage
            playerAtkRecord.updateTime = getNowTime()
            val personalRank = areaCache.callBossCache.findPersonalCallBossRank(callBoss)
            personalRank.rank.updateValue(playerAtkRecord)

            //更新个人统计
            var addScore = monsterProto.attackAllianceGift
            if (currentHp <= 0) {
                addScore = monsterProto.killAllianceGift
            }
            targetAddVal(
                areaCache,
                atkFightData.playerId,
                MonsterScore,
                asList(addScore.toLong())
            )

            if (player.allianceId != 0L) {
                //更新联盟排行
                val allianceRank = areaCache.callBossCache.findAllianceCallBossRankByXY(callBoss)
                var allianceAtkRecord = allianceRank.rank.findByKey(player.allianceId)
                if (allianceAtkRecord == null) {
                    allianceAtkRecord = AllianceCallBossAtkRecord(player.allianceId, hashMapOf())
                }
                allianceAtkRecord.memberRecordMap[playerAtkRecord.playerId] = playerAtkRecord
                allianceRank.rank.updateValue(allianceAtkRecord)

                //更新联盟积分
                val updateInfoMap = hashMapOf<Int, String>()
                updateInfoMap[UpdateMonsterScore] = addScore.toString()
                updateAllianceMemberInfo(areaCache, player.allianceId, player.id, updateInfoMap)
            }

            if (player.allianceId == callBoss.allianceId) {
                //修改帮助记录
                if (!callBoss.helpMemberMap.contains(player.id)) {
                    callBoss.helpMemberMap.add(player.id)
                }
            }

            //推送攻击记录
            val session = fetchOnlinePlayerSession(areaCache, player.id)
            if (session != null) {
                val notifier = createHunterRecordNotifier(
                    Add,
                    callBoss.x,
                    callBoss.y,
                    callBoss.bossId,
                    callBoss.nowHp,
                    callBoss.calSameAllianceAtkRecordCount(areaCache, player.allianceId)
                )
                notifier.notice(session)
            }

            targetAddVal(
                areaCache,
                atkFightData.playerId,
                AtkMonster,
                asList(monsterProto.mainId.toLong(), monsterProto.level.toLong(), 1L)
            )

            updateBossInvite(areaCache, callBoss)

            //更新血量
            noticeCellUpdate(areaCache, posX, posY)
        }

        //推送魔物受伤信息
        noticeMonsterDamageInfo(areaCache, posX, posY, callBoss.bossId, currentDamage, atkGroup.id)

        // 发生魔物战斗事件
        wpm.es.fire(
            areaCache,
            atkGroup.mainPlayerId,
            BOSS_FIGHT,
            BossFight(
                posX, posY, callBoss.bossId, monsterProto.mainId, monsterProto.level,
                callBoss.nowHp, COMMON_BOSS_TYPE, 1
            )
        )

        //========生成战斗奖励================
        if (callBoss.nowHp <= 0) {
            // 击杀魔物
            var atkMonsterSuccessAction = 0
            when (monsterLv) {
                1 -> atkMonsterSuccessAction = ATK_MONSTER_SUCC_1
                2 -> atkMonsterSuccessAction = ATK_MONSTER_SUCC_2
                3 -> atkMonsterSuccessAction = ATK_MONSTER_SUCC_3
                4 -> atkMonsterSuccessAction = ATK_MONSTER_SUCC_4
                5 -> atkMonsterSuccessAction = ATK_MONSTER_SUCC_5
            }

            wpm.es.fire(
                areaCache,
                atkFightData.playerId,
                PLAYER_ACTIVITY_CHANGE,
                PlayerActivityChange(atkMonsterSuccessAction, 1, 0)
            )

            if (player.allianceId != 0L) {
                val updateInfoMap = hashMapOf<Int, String>()
                updateInfoMap[UpdateKillMonsterNum] = "1"
                updateAllianceMemberInfo(areaCache, player.allianceId, player.id, updateInfoMap)
            }

            targetAddVal(
                areaCache,
                atkFightData.playerId,
                KillMonster,
                asList(monsterProto.mainId.toLong(), monsterProto.level.toLong(), 1L)
            )
        }

        //生成战报
        val reward = createRewardInfoForReport()
        val lordProto = pcs.lordExpAwardProtoCache.getProtoByLv(monsterProto.lordExp, player.kingLv)
        if (lordProto != null) {
            reward.kingExp = lordProto.lordExp * (10000 + getResearchEffectValue(
                areaCache,
                NO_FILTER,
                player,
                HunterMonsterExpAdd
            )) / 10000 * (10000 + getResearchEffectValue(
                areaCache,
                NO_FILTER,
                player,
                KingExpAdd
            )) / 10000
            addResToHome(
                areaCache,
                this.action,
                player.id,
                asList(ResVo(RES_KING_EXP, NOT_PROPS_SUB_TYPE, reward.kingExp.toLong()))
            )
        }
        for (heroData in atkFightData.heroList) {
            val hero = areaCache.heroCache.findHeroById(atkFightData.playerId, heroData.id)
            if (hero == null) {
                continue
            }
            val heroProto = pcs.heroExpAwardProtoCache.getProtoByLv(monsterProto.heroExp, hero.level)
            if (heroProto == null) {
                continue
            }
            reward.heroExp[hero.protoId] = heroProto.heroExp
            addHeroExp(areaCache, player.id, hero.id, heroProto.heroExp)
        }
        reward.resVos = selectAllPropFromDrops(monsterProto.atkDrops)
        reward.resVos.addAll(
            selectAllPropFromDrops(
                monsterProto.atkCoins, getResearchEffectValue(
                    areaCache,
                    NO_FILTER,
                    player,
                    HunterDropRateAdd
                )
            )
        )
        var killResVos = LinkedList<ResVo>()
        var allianceDrop = ""
        if (callBoss.nowHp <= 0) {
            //添加最后一击奖励
            killResVos = selectAllPropFromDrops(monsterProto.killDrops)
            allianceDrop = monsterProto.allianceProps
            //发送联盟礼物
            receiveAllianceGift(areaCache, player.allianceId, monsterProto.allianceDrops)
        }
        addResToHome(areaCache, ACTION_FIGHT_BOSS, player.id, reward.resVos)

        //=======生成战报，并更新部队信息（战损、治疗）=========================
        val reportInfo = ReportInfo(
            areaCache,
            posX,
            posY,
            atkFightData,
            defFightData,
            reward,
            null,
            fightInfo.detailInfo.detailFightInfo
        )
        val calExpRt = calKingRealAddExp(player.kingLv, player.kingExp, reward.kingExp)
        reportInfo.genAtkMonsterReport(
            monsterProto.id,
            callBoss.nowHp,
            currentDamage,
            fightInfo.tempRecords[CostEnergy] as Int,
            killResVos,
            allianceDrop,
            player.photoProtoId,
            calExpRt.expAfterCal,
            calExpRt.lvAfterCal
        )

        //召唤礼
        if (callBoss.nowHp <= 0) {
            val callPlayer = areaCache.playerCache.findPlayerById(callBoss.playerId)
            if (callPlayer != null) {
                var callResVos = LinkedList<ResVo>()
                if (monsterProto.minHelpNum <= callBoss.helpMemberMap.count()) {
                    callResVos = selectAllPropFromDrops(monsterProto.callDrops)
                    addResToHome(areaCache, ACTION_FIGHT_BOSS, callPlayer.id, callResVos)
                }
                //添加召唤礼战报
                val callReportInfo = ReportInfo(
                    areaCache,
                    posX,
                    posY,
                    atkFightData,
                    defFightData,
                    null,
                    null,
                    "".toByteArray()
                )
                callReportInfo.genCallBossRewardReport(
                    callBoss.bossId,
                    callResVos,
                    callBoss.playerId,
                    callBoss.allianceId,
                    callBoss.helpMemberMap
                )
            }
        }

        targetAddVal(areaCache, atkFightData.playerId, AtkMonsterCount)

        return false
    }
}
