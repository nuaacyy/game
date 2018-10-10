package com.point18.slg2d.world.module.walk.fightStrategy

import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.module.fightdomain.FightData
import com.point18.slg2d.world.module.fightdomain.FightInfo
import com.point18.slg2d.world.module.fightdomain.createRewardInfoForReport
import com.point18.slg2d.world.module.report.ReportInfo
import com.point18.slg2d.world.wpm
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.event.BossFight
import com.point18.slg2d.world.event.PlayerActivityChange
import com.point18.slg2d.world.common.*
import com.point18.slg2d.world.msgnotice.createHunterRecordNotifier
import com.point18.slg2d.world.msgnotice.createKillActivityBossNotifier
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.resVoToResString
import com.point18.slg2d.common.pc.selectAllPropFromDrops
import java.util.*
import java.util.Arrays.asList

// PVE打活动BOSS战斗流程
class PveFightActivityBossAction(var action: Int) : IBossFightDeal {
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
        val activityBoss = areaCache.activityBossCache.findActivityBossByXy(posX, posY)
        if (activityBoss == null) {
            return false
        }

        val player = areaCache.playerCache.findPlayerById(atkFightData.playerId)
        if (player == null) {
            normalLog.error("找不到玩家信息:${atkFightData.playerId}")
            return false
        }

        val personalRank = areaCache.activityBossCache.findPersonalActivityBossRankByXY(posX, posY)
        if (personalRank == null) {
            normalLog.error("($posX,$posY)对应的魔物的个人排行不存在")
            return false
        }

        val allianceRank = areaCache.activityBossCache.findAllianceActivityBossRankByXY(posX, posY)
        if (allianceRank == null) {
            normalLog.error("($posX,$posY)对应的魔物的联盟排行不存在")
            return false
        }

        val monsterProto = pcs.monsterProtoCache.findMonsterProto(activityBoss.bossId)
        if (monsterProto == null) {
            normalLog.error("魔物的配置不存在:${activityBoss.bossId}")
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
        activityBoss.nowHp = currentHp

        if (currentDamage > 0) {
            //更新个人排行
            val playerAtkRecord = activityBoss.atkRecordsMap.getOrPut(
                atkFightData.playerId
            ) { PersonalActivityBossAtkRecord(atkFightData.playerId, 0, getNowTime()) }
            playerAtkRecord.totalDamage += currentDamage
            playerAtkRecord.updateTime = getNowTime()
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
                var allianceAtkRecord = allianceRank.rank.findByKey(player.allianceId)
                if (allianceAtkRecord == null) {
                    allianceAtkRecord = AllianceActivityBossAtkRecord(player.allianceId, hashMapOf())
                }
                allianceAtkRecord.memberRecordMap[playerAtkRecord.playerId] = playerAtkRecord
                allianceRank.rank.updateValue(allianceAtkRecord)

                //更新联盟积分
                val updateInfoMap = hashMapOf<Int, String>()
                updateInfoMap[UpdateMonsterScore] = addScore.toString()
                updateAllianceMemberInfo(areaCache, player.allianceId, player.id, updateInfoMap)
            }

            /*if (player.allianceId == activityBoss.allianceId) {
                //修改帮助记录
                if (!activityBoss.helpMemberMap.contains(player.id)) {
                    activityBoss.helpMemberMap.add(player.id)
                }
            }*/

            //推送攻击记录
            val session = fetchOnlinePlayerSession(areaCache, player.id)
            if (session != null) {
                val notifier = createHunterRecordNotifier(
                    Add,
                    activityBoss.x,
                    activityBoss.y,
                    activityBoss.bossId,
                    activityBoss.nowHp,
                    activityBoss.calSameAllianceAtkRecordCount(areaCache, player.allianceId)
                )
                notifier.notice(session)
            }

            targetAddVal(
                areaCache,
                atkFightData.playerId,
                AtkMonster,
                asList(monsterProto.mainId.toLong(), monsterProto.level.toLong(), 1L)
            )

            updateBossInvite(areaCache, activityBoss)

            //更新血量
            noticeCellUpdate(areaCache, posX, posY)

        }

        //推送魔物受伤信息
        noticeMonsterDamageInfo(areaCache, posX, posY, activityBoss.bossId, currentDamage, atkGroup.id)

        // 发生魔物战斗事件
        wpm.es.fire(
            areaCache,
            atkGroup.mainPlayerId,
            BOSS_FIGHT,
            BossFight(
                posX, posY, activityBoss.bossId, monsterProto.mainId, monsterProto.level,
                activityBoss.nowHp, ACTIVITY_BOSS_TYPE, 1
            )
        )

        //========生成战斗奖励================
        if (activityBoss.nowHp <= 0) {
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

            // 活动魔物击杀播报
            createKillActivityBossNotifier(player.name, player.allianceShortName, activityBoss.activityBossId).notice(
                areaCache
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
        if (activityBoss.nowHp <= 0) {
            //添加最后一击奖励
            killResVos = selectAllPropFromDrops(monsterProto.killDrops)
            allianceDrop = monsterProto.allianceProps
            //发送联盟礼物
            receiveAllianceGift(areaCache, player.allianceId, monsterProto.allianceDrops)
            //获取排行奖励
            val bossName = pcs.monsterProtoCache.findMonsterProto(activityBoss.bossId)?.name ?: "未知Boss"
            val joinNum = allianceRank.rank.queryAllJoinNum()
            for ((i, atkRecord) in allianceRank.rank.queryValue(joinNum).withIndex()) {
                val rank = i + 1
                val award = pcs.monsterActivityProtoCache.findEventRankRewardByRank(activityBoss.activityBossId, rank)
                if (award == null) {
                    continue
                }
                val allAward = LinkedList<ResVo>()
                for (dropId in award.rewardBags) {
                    val drop = pcs.dropBagCache.dropBagMap[dropId]
                    if (drop == null) {
                        continue
                    }
                    allAward += drop.dropMap
                }

                // 发送联盟奖励邮件
                sendAllianceAwardMail(
                    areaCache,
                    atkRecord.allianceId,
                    TEXT_READ_LAN,
                    ACTIVITY_BOSS_ALLIANCE_RANK_TITLE,
                    listOf(),
                    ACTIVITY_BOSS_ALLIANCE_RANK_CONTENT,
                    asList(bossName, rank.toString()),
                    resVoToResString(allAward)
                )
            }
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
            activityBoss.nowHp,
            currentDamage,
            fightInfo.tempRecords[CostEnergy] as Int,
            killResVos,
            allianceDrop,
            player.photoProtoId,
            calExpRt.expAfterCal,
            calExpRt.lvAfterCal
        )

        targetAddVal(
            areaCache,
            atkFightData.playerId,
            AtkMonsterCount
        )

        return false
    }
}
