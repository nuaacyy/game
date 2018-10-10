package com.point18.slg2d.world.module.report

import com.google.protobuf.ByteString
import com.point18.slg2d.common.commonfunc.*
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.resVoToResString
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.common.genHeroInfoForReport
import com.point18.slg2d.world.common.getEvaluateNum
import com.point18.slg2d.world.common.getResearchEffectValue
import com.point18.slg2d.world.module.fightdomain.*
import com.point18.slg2d.world.module.fightdomain.RewardInfoForReport
import pb4client.*
import pb4server.CreateBattleReportAskHomeReq
import pb4server.World2HomeAskResp
import java.util.*
import java.util.Arrays.asList

const val AtkFight: Int = 1
const val DefFight: Int = 2

class ReportInfo(
    var areaCache: AreaCache,
    var posX: Int,
    var posY: Int,
    var atkFightData: FightData?,
    var defFightData: FightData?,
    var atkReward: RewardInfoForReport?,
    var defReward: RewardInfoForReport?,
    var fightInfo: ByteArray
) {

    // 填充战报通用数据
    fun preFillReport(): BattleReportInfo.Builder {
        val reportBuilder = BattleReportInfo.newBuilder()
        reportBuilder.fightTime = getTimeSec(getNowTime())
        reportBuilder.fightAddressX = this.posX
        reportBuilder.fightAddressY = this.posY
        return reportBuilder
    }

    // 保存战报
    fun saveReport(
        playerId: Long,
        reportData: BattleReportInfo.Builder,
        detailInfo: ByteArray,
        readState: Int = UnRead
    ) {


        // 计算战报过期时间
        val nowSec = reportData.fightTime
        var pastTime = sec2MilliSec(nowSec + pcs.basicProtoCache.pVEReportSaveTime * 3600)

        // 保存战报
        var reportContentBytes = ByteArray(0)
        when (reportData.reportType) {
            FIGHT_PLAYER_REPORT -> {
                reportContentBytes = reportData.pvpTroopsFightReportBuilder.build().toByteArray()
                pastTime = sec2MilliSec(nowSec + pcs.basicProtoCache.fightReportSaveTime * 3600)
            }
            ATK_MONSTER_REPORT ->
                reportContentBytes = reportData.hunterFightReportBuilder.build().toByteArray()
            ATK_ALLIANCE_MONSTER_REPORT ->
                reportContentBytes = reportData.allianceHunterFightReportBuilder.build().toByteArray()
            ATK_WORLD_MONSTER_REPORT ->
                reportContentBytes = reportData.worldHunterFightReportBuilder.build().toByteArray()
            FIGHT_RELIC_REPORT ->
                reportContentBytes = reportData.massRuinsFightReportBuilder.build().toByteArray()
            SCOUT_REPORT ->
                reportContentBytes = reportData.scoutReportBuilder.build().toByteArray()
            BE_SCOUT_REPORT ->
                reportContentBytes = reportData.beScoutReportBuilder.build().toByteArray()
            FARM_REPORT ->
                reportContentBytes = reportData.collectReportBuilder.build().toByteArray()
            TRANSPORT_REPORT ->
                reportContentBytes = reportData.transportReportBuilder.build().toByteArray()
            HUNTER_CALL_REPORT ->
                reportContentBytes = reportData.hunterCallInfoBuilder.build().toByteArray()
            JJC_FIGHT_REPORT ->
                reportContentBytes = reportData.jjcFightReportBuilder.build().toByteArray()
            STATION_DEF_REPORT -> {
                reportContentBytes = reportData.stationDefReportBuilder.build().toByteArray()
                pastTime = sec2MilliSec(nowSec + pcs.basicProtoCache.fightReportSaveTime * 3600)
            }
        }

        val createReportBuilder = CreateBattleReportAskHomeReq.newBuilder()
        val reportBuilder = pb4server.BattleReport.newBuilder()
        reportBuilder.readState = readState
        reportBuilder.reportType = reportData.reportType
        reportBuilder.fightTime = sec2MilliSec(reportData.fightTime)
        reportBuilder.posX = reportData.fightAddressX
        reportBuilder.posY = reportData.fightAddressY
        reportBuilder.pastTime = pastTime
        reportBuilder.reportContent = ByteString.copyFrom(reportContentBytes)
        reportBuilder.fightDetail = ByteString.copyFrom(detailInfo)
        createReportBuilder.setReport(reportBuilder)
        this.areaCache.createACS(areaCache.worldActor.homeShardRegion, areaCache.fillW2HAskMsgHeader(playerId) {
            it.setCreateBattleReportAskHomeReq(createReportBuilder)
        }, World2HomeAskResp::class.java) { askResp, err ->
            //todo 添加战报返回处理
            try {
                when {
                    err != null -> {
                        return@createACS
                    }
                    askResp == null -> {
                        return@createACS
                    }
                    else -> {
                    }
                }
            } catch (e: Exception) {
            }
        }
    }

    // 生成与玩家战斗战报
    fun genFightPlayerReport(
        rewardMap: HashMap<Long, RewardInfoForReport>?,
        atkSoliderDataAfterFight: SoliderDataAfterFight?,
        defSoliderDataAfterFight: SoliderDataAfterFight?,
        atkIsMass: Boolean, defIsMass: Boolean, isCityFight: Boolean, wonderId: Int, fightResult: Int,
        isPrison: Boolean = false, isBePrison: Boolean = false
    ) {
        val atkMass = boolToInt(atkIsMass)
        val defMass = boolToInt(defIsMass)
        val cityFight = boolToInt(isCityFight)

        var atkHeroList = LinkedList<HeroInfoForReport.Builder>()
        var defHeroList = LinkedList<HeroInfoForReport.Builder>()

        val atkFightData = this.atkFightData
        val defFightData = this.defFightData

        if (atkFightData != null) {
            atkHeroList = this.genHeroInfo(atkFightData, 0.0)
            if (rewardMap != null) {
                for (hero in atkHeroList) {
                    val rewards = rewardMap[atkFightData.playerId] ?: continue
                    hero.addExp = rewards.heroExp[hero.protoId] ?: 0
                }
            }
        }
        if (defFightData != null) {
            defHeroList = this.genHeroInfo(defFightData, 0.0)
            if (rewardMap != null) {
                for (hero in defHeroList) {
                    val rewards = rewardMap[defFightData.playerId] ?: continue
                    hero.addExp = rewards.heroExp[hero.protoId] ?: 0
                }
            }
        }

        if (atkFightData != null) {
            for (atk in atkFightData.soliderDataList) {
                val report = this.preFillReport()
                report.reportType = FIGHT_PLAYER_REPORT
                val pvpBuilder = PvpFightReport.newBuilder()
                pvpBuilder.fightType = AtkFight
                pvpBuilder.fightResult = fightResult
                pvpBuilder.atkIsMass = atkMass
                pvpBuilder.defIsMass = defMass
                pvpBuilder.cityState = cityFight
                pvpBuilder.wonderId = wonderId
                pvpBuilder.isPrison = boolToInt(isPrison)
                pvpBuilder.isBePrison = boolToInt(isBePrison)

                if (rewardMap != null) {
                    val reward = rewardMap[atk.playerId]
                    if (reward == null) {
                        normalLog.error("奖励信息中缺失攻击的玩家:${atk.playerId}")
                        continue
                    }
                    val rewardBuilder = pb4client.RewardInfoForReport.newBuilder()
                    rewardBuilder.kingExp = reward.kingExp
                    rewardBuilder.resVo = resVoToResString(reward.resVos)
                    pvpBuilder.setReward(rewardBuilder)
                }
                if (atkSoliderDataAfterFight != null) {
                    this.genSoliderInfoByAfterFightData(atkFightData, atkSoliderDataAfterFight)
                        .forEach { pvpBuilder.addAtkFightInfo(it) }
                }
                if (defSoliderDataAfterFight != null) {
                    this.genSoliderInfoByAfterFightData(defFightData, defSoliderDataAfterFight)
                        .forEach { pvpBuilder.addDefFightInfo(it) }
                }
                atkHeroList.forEach { pvpBuilder.addAtkHeros(it) }
                defHeroList.forEach { pvpBuilder.addDefHeros(it) }
                report.setPvpTroopsFightReport(pvpBuilder)
                this.saveReport(atk.playerId, report, this.fightInfo)
            }
        }

        if (defFightData != null) {
            for (def in defFightData.soliderDataList) {
                val report = this.preFillReport()
                report.reportType = FIGHT_PLAYER_REPORT
                val pvpBuilder = PvpFightReport.newBuilder()
                pvpBuilder.fightType = DefFight
                pvpBuilder.fightResult = fightResult
                pvpBuilder.atkIsMass = atkMass
                pvpBuilder.defIsMass = defMass
                pvpBuilder.cityState = cityFight
                pvpBuilder.wonderId = wonderId
                pvpBuilder.isPrison = boolToInt(isBePrison)
                pvpBuilder.isBePrison = boolToInt(isPrison)

                if (rewardMap != null) {
                    val reward = rewardMap[def.playerId]
                    if (reward == null) {
                        normalLog.error("奖励信息中缺失防守的玩家:${def.playerId}")
                        continue
                    }
                    val rewardBuilder = pb4client.RewardInfoForReport.newBuilder()
                    rewardBuilder.kingExp = reward.kingExp
                    rewardBuilder.resVo = resVoToResString(reward.resVos)
                    pvpBuilder.setReward(rewardBuilder)
                }
                if (atkSoliderDataAfterFight != null) {
                    this.genSoliderInfoByAfterFightData(atkFightData, atkSoliderDataAfterFight)
                        .forEach { pvpBuilder.addAtkFightInfo(it) }
                }
                if (defSoliderDataAfterFight != null) {
                    this.genSoliderInfoByAfterFightData(defFightData, defSoliderDataAfterFight)
                        .forEach { pvpBuilder.addDefFightInfo(it) }
                }
                atkHeroList.forEach { pvpBuilder.addAtkHeros(it) }
                defHeroList.forEach { pvpBuilder.addDefHeros(it) }
                report.setPvpTroopsFightReport(pvpBuilder)
                this.saveReport(def.playerId, report, this.fightInfo)
            }
        }
    }

    fun genHeroInfo(fightData: FightData, totalHeroExp: Double): LinkedList<HeroInfoForReport.Builder> {
        val heroInfoList = LinkedList<HeroInfoForReport.Builder>()
        val addExp = (totalHeroExp / fightData.heroList.count()).toInt()
        for (heroData in fightData.heroList) {
            val heroBuilder =
                genHeroInfoForReport(this.areaCache, fightData.playerId, heroData.id, addExp)
                    ?: continue
            heroInfoList.add(heroBuilder)
        }
        return heroInfoList
    }

    fun genSoliderInfoByAfterFightData(
        fightData: FightData?,
        soliderDataAfterFight: SoliderDataAfterFight
    ): LinkedList<FightInfoForReport.Builder> {
        val atkFightInfoList = LinkedList<FightInfoForReport.Builder>()
        if (fightData == null) {
            return atkFightInfoList
        }
        for (soliderData in fightData.soliderDataList) {
            val fightInfoBuilder = FightInfoForReport.newBuilder()
            if (soliderData.playerId != 0L) {
                fightInfoBuilder.setFightPlayerInfo(this.getPlayerInfoByPlayerId(soliderData.playerId))
            }
            for ((soliderId, soliderNum) in soliderData.soliderMap) {
                val woundedData = soliderDataAfterFight.woundedSoliderDataMap[soliderData.playerId]
                if (woundedData == null) {
                    normalLog.error("找不到伤兵数据:${soliderData.playerId}")
                    continue
                }
                val diedData = soliderDataAfterFight.diedSoliderDataMap[soliderData.playerId]
                if (diedData == null) {
                    normalLog.error("找不到死兵数据:${soliderData.playerId}")
                    continue
                }

                val soliderInfoBuilder = SoliderInfoForReport.newBuilder()
                soliderInfoBuilder.protoId = soliderId
                soliderInfoBuilder.totalNum = soliderNum
                soliderInfoBuilder.woundedNum = woundedData.soliderMap[soliderId] ?: 0
                soliderInfoBuilder.diedNum = diedData.soliderMap[soliderId] ?: 0
                fightInfoBuilder.addSoliders(soliderInfoBuilder)
            }

            val killData = soliderDataAfterFight.killedSoliderDataMap[soliderData.playerId]
            if (killData == null) {
                normalLog.error("找不到击杀数据:${soliderData.playerId}")
                continue
            }
            for ((killSoliderId, killSoliderNum) in killData.soliderMap) {
                val killInfoBuilder = KillInfoForReport.newBuilder()
                killInfoBuilder.killSoliderId = killSoliderId
                killInfoBuilder.killSoliderNum = killSoliderNum
                fightInfoBuilder.addKillInfos(killInfoBuilder)
            }
            atkFightInfoList.add(fightInfoBuilder)
        }
        return atkFightInfoList
    }

    //生成驻防部队防守胜利的战报
    fun genStationForceDefSuccessReport(atkPlayerId: Long, defPlayerId: Long, tellPlayerId: Long) {
        val areaCache = this.areaCache
        val report = this.preFillReport()
        report.reportType = STATION_DEF_REPORT
        val stationBuilder = StationDefReport.newBuilder()
        stationBuilder.atkPlayerId = atkPlayerId
        stationBuilder.defPlayerId = defPlayerId
        val atkPlayer = areaCache.playerCache.findPlayerById(atkPlayerId)
        if (atkPlayer != null) {
            stationBuilder.atkPlayerName = atkPlayer.name
            stationBuilder.atkAllianceShortName = atkPlayer.allianceShortName
            val castle = areaCache.castleCache.findCastleById(atkPlayer.focusCastleId)
            if (castle != null) {
                stationBuilder.atkPosX = castle.x
                stationBuilder.atkPosY = castle.y
            }
        }
        val defPlayer = areaCache.playerCache.findPlayerById(defPlayerId)
        if (defPlayer != null) {
            stationBuilder.defPlayerName = defPlayer.name
            stationBuilder.defAllianceShortName = defPlayer.allianceShortName
            val castle = areaCache.castleCache.findCastleById(defPlayer.focusCastleId)
            if (castle != null) {
                stationBuilder.defPosX = castle.x
                stationBuilder.defPosY = castle.y
            }
        }
        report.setStationDefReport(stationBuilder)
        this.saveReport(tellPlayerId, report, this.fightInfo)
    }

    //生成与魔物战斗战报
    fun genAtkMonsterReport(
        monsterId: Int,
        leftHp: Int,
        costHp: Int,
        costEnergy: Int,
        killRes: LinkedList<ResVo>,
        allianceDrop: String,
        photoId: Int,
        expAfterFight: Int,
        kingLvAfterFight: Int
    ) {
        val atkFightData = requireNotNull(this.atkFightData)
        val atkReward = requireNotNull(this.atkReward)

        val report = this.preFillReport()
        report.reportType = ATK_MONSTER_REPORT
        val hunterBuilder = HunterFightReport.newBuilder()
        hunterBuilder.kingExp = atkReward.kingExp
        hunterBuilder.kingExpAfterFight = expAfterFight
        hunterBuilder.kingLvAfterFight = kingLvAfterFight
        for (heroData in atkFightData.heroList) {
            val heroBuilder =
                genHeroInfoForReport(
                    this.areaCache,
                    atkFightData.playerId,
                    heroData.id,
                    atkReward.heroExp[heroData.protoId] ?: 0
                ) ?: continue
            hunterBuilder.addHeros(heroBuilder)
        }

        val hunterInfoBuilder = HunterFightInfo.newBuilder()
        hunterInfoBuilder.monsterId = monsterId
        hunterInfoBuilder.monsterLeftHp = leftHp
        hunterInfoBuilder.bloodConsumption = costHp
        hunterInfoBuilder.consume = costEnergy
        hunterBuilder.setHunterFightInfo(hunterInfoBuilder)

        hunterBuilder.resVo = resVoToResString(atkReward.resVos)
        hunterBuilder.finishResVo = resVoToResString(killRes)
        hunterBuilder.allianceResVo = allianceDrop
        hunterBuilder.photoId = photoId

        report.setHunterFightReport(hunterBuilder)

        this.saveReport(atkFightData.playerId, report, this.fightInfo)
    }

    //召唤礼物战报
    fun genCallBossRewardReport(
        monsterId: Int,
        callRes: LinkedList<ResVo>,
        callPlayerId: Long,
        allianceId: Long,
        helperMap: HashSet<Long>
    ) {
        val report = this.preFillReport()
        report.reportType = HUNTER_CALL_REPORT

        val hunterCallBuilder = HunterCallInfo.newBuilder()
        hunterCallBuilder.monsterId = monsterId
        hunterCallBuilder.resVo = resVoToResString(callRes)
        val player = areaCache.playerCache.findFirstPlayerByAllianceId(allianceId)

        val callPlayerBuilder = this.getPlayerInfoByPlayerId(callPlayerId) ?: return
        hunterCallBuilder.setCallPlayer(callPlayerBuilder)
        helperMap.forEach {
            val helpPlayer = areaCache.playerCache.findPlayerById(it) ?: return@forEach
            val playerInfoBuilder = FightPlayerInfo.newBuilder()
            playerInfoBuilder.id = it
            playerInfoBuilder.name = helpPlayer.name
            playerInfoBuilder.photo = helpPlayer.photoProtoId
            playerInfoBuilder.allianceId = allianceId
            if (player != null) {
                playerInfoBuilder.allianceName = player.allianceName
                playerInfoBuilder.allianceShortName = player.allianceShortName
            }
            hunterCallBuilder.addHelpers(playerInfoBuilder)
        }
        report.setHunterCallInfo(hunterCallBuilder)
        this.saveReport(callPlayerId, report, "".toByteArray())
    }

    //生成采集战报
    fun genFarmReport(resType: Int, resLv: Int) {
        val atkFightData = this.atkFightData
        if (atkFightData == null) {
            normalLog.error("采集，无攻击方数据")
            return
        }

        val report = this.preFillReport()
        report.reportType = FARM_REPORT
        val collectBuilder = CollectReport.newBuilder()
        val rewardInfo = this.atkReward
        if (rewardInfo != null) {
            collectBuilder.resVo = resVoToResString(rewardInfo.resVos)
        }
        this.genHeroInfo(atkFightData, 0.0).forEach { collectBuilder.addHeros(it) }
        collectBuilder.resType = resType
        collectBuilder.resLv = resLv
        report.setCollectReport(collectBuilder)
        this.saveReport(atkFightData.playerId, report, this.fightInfo)
    }

    //生成运输战报
    fun genTransportReport(transportPlayerId: Long, beTransportPlayerId: Long) {
        val areaCache = this.areaCache
        val report = this.preFillReport()
        report.reportType = TRANSPORT_REPORT
        val transportPlayer = areaCache.playerCache.findPlayerById(transportPlayerId)
        if (transportPlayer == null) {
            normalLog.error("找不到运输方玩家:$transportPlayerId")
            return
        }
        val transportPlayerCastle = areaCache.castleCache.findCastleById(transportPlayer.focusCastleId)
        if (transportPlayerCastle == null) {
            normalLog.error("找不到运输方玩家的城池:$transportPlayerId")
            return
        }

        val beTransportPlayer = areaCache.playerCache.findPlayerById(beTransportPlayerId)
        if (beTransportPlayer == null) {
            normalLog.error("找不到被运输方玩家:$beTransportPlayerId")
            return
        }
        val beTransportPlayerCastle = areaCache.castleCache.findCastleById(beTransportPlayer.focusCastleId)
        if (beTransportPlayerCastle == null) {
            normalLog.error("找不到被运输方玩家的城池:$beTransportPlayerId")
            return
        }

        val transportBuilder = TransportReport.newBuilder()
        transportBuilder.transportPlayerId = transportPlayerId
        transportBuilder.transportPlayerName = transportPlayer.name
        transportBuilder.transportPlayerShortName = transportPlayer.allianceNickName
        transportBuilder.transportPosX = transportPlayerCastle.x
        transportBuilder.transportPosY = transportPlayerCastle.y
        transportBuilder.beTransportPlayerId = beTransportPlayerId
        transportBuilder.beTransportPlayerName = beTransportPlayer.name
        transportBuilder.beTransportPlayerShortName = beTransportPlayer.allianceNickName
        transportBuilder.beTransportPosX = beTransportPlayerCastle.x
        transportBuilder.beTransportPosY = beTransportPlayerCastle.y
        transportBuilder.allianceId = transportPlayer.allianceId
        transportBuilder.allianceName = transportPlayer.allianceName
        transportBuilder.allianceShortName = transportPlayer.allianceShortName

        val rewardInfo = this.atkReward
        if (rewardInfo != null) {
            transportBuilder.resVo = resVoToResString(rewardInfo.resVos)
        }
        report.setTransportReport(transportBuilder)
        this.saveReport(transportPlayerId, report, this.fightInfo)
        this.saveReport(beTransportPlayerId, report, this.fightInfo)
    }

    //生成攻打遗迹战报
    fun genAtkRelicReport(
        relicId: Int,
        atkSoliderDataAfterFight: SoliderDataAfterFight,
        defSoliderDataAfterFight: SoliderDataAfterFight,
        totalRecovery: Int,
        fightResult: Int
    ) {
        val atkFightData = this.atkFightData
        if (atkFightData == null) {
            normalLog.error("遗迹，无攻击方数据")
            return
        }
        val defFightData = this.defFightData
        if (defFightData == null) {
            normalLog.error("遗迹，无防守方数据")
            return
        }

        //计算攻方经验
        var atkTotalKingExp = 0.0
        var atkTotalHeroExp = 0.0
        for (soliderData in defFightData.soliderDataList) {
            for ((id, num) in soliderData.soliderMap) {
                val soliderProto = pcs.soliderCache.soliderProtoMap[id]
                if (soliderProto == null) {
                    normalLog.error("找不到士兵配置：$id")
                    continue
                }
                atkTotalKingExp += soliderProto.lordExp * (num) * (defFightData.damagedRate[id] ?: 0.0)
                atkTotalHeroExp += soliderProto.heroExp * (num) * (defFightData.damagedRate[id] ?: 0.0)
            }
        }

        for (atk in atkFightData.soliderDataList) {
            val report = this.preFillReport()
            report.reportType = FIGHT_RELIC_REPORT
            val massBuilder = MassRuinsFightReport.newBuilder()
            massBuilder.relicId = relicId
            massBuilder.fightResult = fightResult
            massBuilder.recoveryNum = totalRecovery
            this.genSoliderInfoByAfterFightData(atkFightData, atkSoliderDataAfterFight)
                .forEach { massBuilder.addAtkFightInfo(it) }
            this.genSoliderInfoByAfterFightData(defFightData, defSoliderDataAfterFight)
                .forEach { massBuilder.addDefFightInfo(it) }
            this.genHeroInfo(atkFightData, atkTotalHeroExp).forEach { massBuilder.addAtkHeros(it) }
            this.genHeroInfo(defFightData, 0.0).forEach { massBuilder.addDefHeros(it) }
            val rewardBuilder = pb4client.RewardInfoForReport.newBuilder()
            rewardBuilder.kingExp = (atkTotalKingExp / atkFightData.soliderDataList.count()).toInt()
            rewardBuilder.resVo = resVoToResString(atkReward?.resVos)

            massBuilder.setReward(rewardBuilder)
            report.setMassRuinsFightReport(massBuilder)
            this.saveReport(atk.playerId, report, this.fightInfo)
        }
    }

    fun genAtkRelicReport(
        relicId: Int,
        atkSoliderDataAfterFight: SoliderDataAfterFight,
        defSoliderDataAfterFight: SoliderDataAfterFight,
        totalRecovery: Int,
        fightResult: Int,
        playerId: Long,
        addRelicBoxResult: Int
    ) {
        val atkFightData = this.atkFightData
        if (atkFightData == null) {
            normalLog.error("遗迹，无攻击方数据")
            return
        }
        val defFightData = this.defFightData
        if (defFightData == null) {
            normalLog.error("遗迹，无防守方数据")
            return
        }

        //计算攻方经验
        var atkTotalKingExp = 0.0
        var atkTotalHeroExp = 0.0
        for (soliderData in defFightData.soliderDataList) {
            for ((id, num) in soliderData.soliderMap) {
                val soliderProto = pcs.soliderCache.soliderProtoMap[id]
                if (soliderProto == null) {
                    normalLog.error("找不到士兵配置：$id")
                    continue
                }
                atkTotalKingExp += soliderProto.lordExp * (num) * (defFightData.damagedRate[id] ?: 0.0)
                atkTotalHeroExp += soliderProto.heroExp * (num) * (defFightData.damagedRate[id] ?: 0.0)
            }
        }

        val report = this.preFillReport()
        report.reportType = FIGHT_RELIC_REPORT
        val massBuilder = MassRuinsFightReport.newBuilder()
        massBuilder.relicId = relicId
        massBuilder.fightResult = fightResult
        massBuilder.recoveryNum = totalRecovery
        massBuilder.addRelicBoxResult = addRelicBoxResult
        this.genSoliderInfoByAfterFightData(atkFightData, atkSoliderDataAfterFight)
            .forEach { massBuilder.addAtkFightInfo(it) }
        this.genSoliderInfoByAfterFightData(defFightData, defSoliderDataAfterFight)
            .forEach { massBuilder.addDefFightInfo(it) }
        this.genHeroInfo(atkFightData, atkTotalHeroExp).forEach { massBuilder.addAtkHeros(it) }
        this.genHeroInfo(defFightData, 0.0).forEach { massBuilder.addDefHeros(it) }
        val rewardBuilder = pb4client.RewardInfoForReport.newBuilder()
        rewardBuilder.kingExp = (atkTotalKingExp / atkFightData.soliderDataList.count()).toInt()
        rewardBuilder.resVo = resVoToResString(atkReward?.resVos)

        massBuilder.setReward(rewardBuilder)
        report.setMassRuinsFightReport(massBuilder)
        this.saveReport(playerId, report, this.fightInfo)
    }

    //生成侦查战报
    fun genScoutFailedReport(scoutPlayerId: Long, cellType: Int, scoutResult: Int) {
        val report = this.preFillReport()
        report.reportType = SCOUT_REPORT

        val scoutBuilder = ScoutReport.newBuilder()
        scoutBuilder.result = scoutResult
        scoutBuilder.cellType = cellType

        when (cellType) {
            CELL_RELIC -> {
                val relicCell = areaCache.relicCache.findRelicByXY(this.posX, this.posY)
                if (relicCell == null) {
                    normalLog.error("侦查，找不到遗迹（${this.posX},${this.posY}）")
                    return
                }
                scoutBuilder.relicId = relicCell.relicId
            }
            CELL_CASTLE -> {
                val castle = areaCache.castleCache.findCastleByXy(this.posX, this.posY)
                if (castle == null) {
                    normalLog.error("侦查，找不到玩家城（${this.posX},${this.posY}）")
                    return
                }
                val inCityPlayerInfoBuilder = ScoutPlayerInfo.newBuilder()
                inCityPlayerInfoBuilder.setPlayerInfo(this.getPlayerInfoByPlayerId(castle.playerId))
                val inCityForceBuilder = ScoutForceInfo.newBuilder()
                inCityForceBuilder.setPlayerInfo(inCityPlayerInfoBuilder)
                scoutBuilder.setDefForce(inCityForceBuilder)
            }
            CELL_WONDER -> {
            }
            else -> return
        }

        report.setScoutReport(scoutBuilder)
        this.saveReport(scoutPlayerId, report, this.fightInfo)
    }

    //侦查玩家战报
    fun genScoutPlayerReport(scoutPlayerId: Long, beScoutPlayerId: Long, scoutResult: Int) {
        val areaCache = this.areaCache
        val report = this.preFillReport()
        report.reportType = SCOUT_REPORT

        val scoutBuilder = ScoutReport.newBuilder()
        scoutBuilder.result = scoutResult
        scoutBuilder.cellType = CELL_CASTLE

        if (scoutResult != ResultCode.SUCCESS.code) {
            report.setScoutReport(scoutBuilder)
            this.saveReport(scoutPlayerId, report, this.fightInfo)
            return
        }
        val scoutPlayer = areaCache.playerCache.findPlayerById(scoutPlayerId)
        if (scoutPlayer == null) {
            normalLog.error("找不到侦查的玩家:$scoutPlayerId")
            return
        }

        val beScoutPlayer = areaCache.playerCache.findPlayerById(beScoutPlayerId)
        if (beScoutPlayer == null) {
            normalLog.error("找不到被侦查的玩家信息:$beScoutPlayerId")
            return
        }

        //对方炸兵术
        val rst = areaCache.buffCache.findBuffValueByBuffType(beScoutPlayerId, BUFF_EFFECT_ZHABING)
        var cheatRate = rst.value
        if (!rst.isHave) {
            cheatRate = 1
        }

        //填充玩家资源信息
        val res = LinkedList<ResVo>()
        res.add(ResVo(RES_COIN, NOT_PROPS_SUB_TYPE, beScoutPlayer.coin))
        res.add(ResVo(RES_FOOD, NOT_PROPS_SUB_TYPE, beScoutPlayer.food))
        res.add(ResVo(RES_WOOD, NOT_PROPS_SUB_TYPE, beScoutPlayer.wood))
        res.add(ResVo(RES_STONE, NOT_PROPS_SUB_TYPE, beScoutPlayer.stone))
        res.add(ResVo(RES_IRON, NOT_PROPS_SUB_TYPE, beScoutPlayer.iron))

        scoutBuilder.reward = resVoToResString(res)

        //统计兵营数据
        val barracksMap = areaCache.barracksCache.findBarracksMapByPlayerId(beScoutPlayerId)
        val soliderMap = hashMapOf<Int, Int>()
        val trapMap = hashMapOf<Int, Int>()
        val hospitalSoliderMap = hashMapOf<Int, Int>()
        val hospitalTrapMap = hashMapOf<Int, Int>()

        for ((_, barrack) in barracksMap) {
            val soliderProto = pcs.soliderCache.soliderProtoMap[barrack.soldierId]
            if (soliderProto == null) {
                normalLog.error("找不到士兵配置：${barrack.soldierId}")
                continue
            }

            if (isSolider(soliderProto.armyType)) {
                if (barrack.soldierNum > 0) {
                    soliderMap[barrack.soldierId] = (soliderMap[barrack.soldierId] ?: 0) + barrack.soldierNum
                }
                if (barrack.nowCureNum + barrack.canCureNum > 0) {
                    hospitalSoliderMap[barrack.soldierId] = (hospitalSoliderMap[barrack.soldierId] ?: 0) +
                            barrack.nowCureNum + barrack.canCureNum
                }
                if (barrack.nowEventCureNum + barrack.canEventCureNum > 0) {
                    hospitalSoliderMap[barrack.soldierId] = (hospitalSoliderMap[barrack.soldierId] ?: 0) +
                            barrack.nowEventCureNum + barrack.canEventCureNum
                }
            } else if (isTrap(soliderProto.armyType)) {
                if (barrack.soldierNum > 0) {
                    trapMap[barrack.soldierId] = (trapMap[barrack.soldierId] ?: 0) + barrack.soldierNum
                }
                if (barrack.nowCureNum + barrack.canCureNum > 0) {
                    hospitalTrapMap[barrack.soldierId] = (hospitalTrapMap[barrack.soldierId] ?: 0) +
                            barrack.nowCureNum + barrack.canCureNum
                }
                if (barrack.nowEventCureNum + barrack.canEventCureNum > 0) {
                    hospitalSoliderMap[barrack.soldierId] = (hospitalSoliderMap[barrack.soldierId] ?: 0) +
                            barrack.nowEventCureNum + barrack.canEventCureNum
                }
            }
        }

        val inCityForceBuilder = ScoutForceInfo.newBuilder()
        var inCityTrapBuilder: ScoutSoliderInfo.Builder? = null
        var hospitalSoliderBuilder: ScoutSoliderInfo.Builder? = null
        var hospitalTrapBuilder: ScoutSoliderInfo.Builder? = null
        var hideForceBuilder: ScoutForceInfo.Builder? = null
        var stationForceBuilderList: LinkedList<ScoutForceInfo.Builder>? = null
        var reinforceForceBuilderList: LinkedList<ScoutForceInfo.Builder>? = null
        var massForceBuilderList: LinkedList<ScoutForceInfo.Builder>? = null
        var buildingBuilderList = LinkedList<TerritoryBuilding.Builder>()

        var inCityHeroInfoBuilder: ScoutHerosInfo.Builder? = null
        val inCityPlayerInfoBuilder = ScoutPlayerInfo.newBuilder()
        inCityPlayerInfoBuilder.setPlayerInfo(this.getPlayerInfoByPlayerId(beScoutPlayerId))

        //开启侦察功能。显示目标守城部队的总数估算值
        if (getResearchEffectValue(areaCache, NO_FILTER, scoutPlayer, ScoutResearchLv1) != 0) {
            scoutBuilder.haveData = ScoutHaveSolider
            inCityForceBuilder.setSoliderInfo(this.fillScoutSolider(TotalNum, soliderMap, cheatRate))

            val defHeroList = areaCache.heroCache.getReserveHeroList(beScoutPlayerId)
            inCityHeroInfoBuilder = this.fillScoutHero(EasyInfo, beScoutPlayer.id, defHeroList)
        }

        //显示目标守城部队的兵种构成，显示守城陷阱的兵种构成，显示守城陷阱的总数估算值
        if (getResearchEffectValue(areaCache, NO_FILTER, scoutPlayer, ScoutResearchLv2) != 0) {
            //士兵
            inCityForceBuilder.setSoliderInfo(this.fillScoutSolider(SoliderInfoByArmyType, soliderMap, cheatRate))

            //陷阱
            scoutBuilder.haveData = scoutBuilder.haveData or ScoutHaveTrap
            inCityTrapBuilder = this.fillScoutSolider(TotalNum, trapMap, cheatRate)
        }

        //显示给在目标点驻防部队的总数估算值，增援的部队的总数估算值
        if (getResearchEffectValue(areaCache, NO_FILTER, scoutPlayer, ScoutResearchLv3) != 0) {
            //驻扎
            scoutBuilder.haveData = scoutBuilder.haveData or ScoutHaveStation
            stationForceBuilderList = this.fillForceByState(
                Stationed,
                TotalNum,
                EasyInfo,
                cheatRate,
                posX,
                posY,
                false
            )

            //增援
            scoutBuilder.haveData = scoutBuilder.haveData or ScoutHaveReinforce
            reinforceForceBuilderList = this.fillForceByState(
                Reinforce,
                TotalNum,
                TotalNum,
                cheatRate,
                posX,
                posY,
                false
            )
        }

        //显示目标点，守城部队、守城陷阱的详细兵种名称和对应估算值，显示驻防部队、增援部队的兵种构成和对应估算值
        if (getResearchEffectValue(areaCache, NO_FILTER, scoutPlayer, ScoutResearchLv4) != 0) {
            //士兵
            inCityForceBuilder.setSoliderInfo(this.fillScoutSolider(EasyInfo, soliderMap, cheatRate))

            //驻扎
            stationForceBuilderList = this.fillForceByState(
                Stationed,
                SoliderInfoByArmyType,
                EasyInfo,
                cheatRate,
                posX,
                posY,
                false
            )

            //增援
            reinforceForceBuilderList = this.fillForceByState(
                Reinforce,
                SoliderInfoByArmyType,
                TotalNum,
                cheatRate,
                posX,
                posY,
                false
            )
        }

        //显示目标领主所在位置（不在，藏兵，集结，城内）
        if (getResearchEffectValue(areaCache, NO_FILTER, scoutPlayer, ScoutResearchLv5) != 0) {
            val mainHero = areaCache.heroCache.findHeroById(beScoutPlayer.id, beScoutPlayer.mainHeroId)
            if (mainHero == null) {
                normalLog.error("找不到玩家的领主英雄信息:${beScoutPlayer.mainHeroId}")
                return
            }
            when (mainHero.posState) {
                IN_CITY -> {
                    when (mainHero.mainHeroState) {
                        IN_HIDE -> {
                            inCityPlayerInfoBuilder.laridState = MAIN_HERO_IN_CAVE
                        }
                        PRISON_ESCAP_FROM,
                        PRISON_AWAITING_EXECUTION,
                        PRISON_EXECUTION,
                        PRISON_EAT_MUSHROOM,
                        PRISON_DIE,
                        CAN_RESURGENCE -> {
                            inCityPlayerInfoBuilder.laridState = MAIN_HERO_IN_WALK
                        }
                        else -> {
                            inCityPlayerInfoBuilder.laridState = MAIN_HERO_IN_CITY
                        }
                    }
                }
                OUT_CITY -> {
                    inCityPlayerInfoBuilder.laridState = MAIN_HERO_IN_WALK

                    val massList = areaCache.massCache.findMassByPlayerId(beScoutPlayer.id)
                    for (mass in massList) {
                        if (mass.massState == Run) {
                            continue
                        }

                        val massMember = mass.findMassMember(beScoutPlayer.id) ?: continue

                        val mainForce = areaCache.walkForceCache.findMainForceByGroupId(massMember.groupId) ?: continue

                        mainForce.heroIdList.firstOrNull { it == beScoutPlayer.mainHeroId } ?: continue

                        inCityPlayerInfoBuilder.laridState = MAIN_HERO_IN_MASS
                        break
                    }
                }
            }
        }

        //显示驻防部队、增援部队的详细兵种名称和对应估算值
        if (getResearchEffectValue(areaCache, NO_FILTER, scoutPlayer, ScoutResearchLv6) != 0) {
            //驻扎
            stationForceBuilderList = this.fillForceByState(
                Stationed,
                EasyInfo,
                EasyInfo,
                cheatRate,
                posX,
                posY,
                false
            )

            //增援
            reinforceForceBuilderList = this.fillForceByState(
                Reinforce,
                EasyInfo,
                TotalNum,
                cheatRate,
                posX,
                posY,
                false
            )
        }

        //显示目标派出去集结部队和藏兵部队的兵种信息和估算值，显示提供驻防的玩家名称，提供增援的玩家名称
        if (getResearchEffectValue(areaCache, NO_FILTER, scoutPlayer, ScoutResearchLv7) != 0) {
            scoutBuilder.haveData = scoutBuilder.haveData or ScoutHaveHide

            //集结
            scoutBuilder.haveData = scoutBuilder.haveData or ScoutHaveMass
            massForceBuilderList = this.fillForceByMass(TotalNum, EasyInfo, cheatRate, beScoutPlayerId, false)

            //藏兵
            val caveInfo = areaCache.caveCache.findCaveByPlayerId(beScoutPlayerId)
            if (caveInfo != null) {
                val caveGroup = areaCache.walkForceGroupCache.findWalkForceGroupById(caveInfo.hideForceGroupId)
                if (caveGroup == null) {
                    normalLog.error("藏兵信息中无藏兵部队信息:${caveInfo.hideForceGroupId}")
                    return
                }
                hideForceBuilder = this.fillForceByGroup(EasyInfo, EasyInfo, cheatRate, caveGroup, true)
                val mainHero = areaCache.heroCache.findHeroById(beScoutPlayer.id, beScoutPlayer.mainHeroId)
                if (mainHero == null) {
                    normalLog.error("找不到玩家的领主英雄信息:${beScoutPlayer.mainHeroId}")
                    return
                }
                hideForceBuilder.playerInfoBuilder.laridState = mainHero.posState
            }

            //驻扎
            stationForceBuilderList = this.fillForceByState(
                Stationed,
                EasyInfo,
                EasyInfo,
                cheatRate,
                posX,
                posY,
                true
            )

            //增援
            reinforceForceBuilderList = this.fillForceByState(
                Reinforce,
                EasyInfo,
                TotalNum,
                cheatRate,
                posX,
                posY,
                true
            )
        }

        //显示目标守城英雄的详细信息
        if (getResearchEffectValue(areaCache, NO_FILTER, scoutPlayer, ScoutResearchLv8) != 0) {
            val defHeroList = areaCache.heroCache.getReserveHeroList(beScoutPlayerId)
            inCityHeroInfoBuilder = this.fillScoutHero(DetailInfo, beScoutPlayer.id, defHeroList)

            //驻扎
            stationForceBuilderList = this.fillForceByState(
                Stationed,
                EasyInfo,
                DetailInfo,
                cheatRate,
                posX,
                posY,
                true
            )
        }

        //显示目标城堡所有建筑信息对应等级，守城军团详细信息，守城陷阱详细信息
        if (getResearchEffectValue(areaCache, NO_FILTER, scoutPlayer, ScoutResearchLv9) != 0) {
            //建筑
            buildingBuilderList = this.fillBuilding(beScoutPlayer)

            //士兵
            inCityForceBuilder.setSoliderInfo(this.fillScoutSolider(DetailInfo, soliderMap, cheatRate))

            //陷阱
            inCityTrapBuilder = this.fillScoutSolider(DetailInfo, trapMap, cheatRate)

            //驻扎
            stationForceBuilderList = this.fillForceByState(
                Stationed,
                DetailInfo,
                DetailInfo,
                cheatRate,
                posX,
                posY,
                true
            )


            //增援
            reinforceForceBuilderList = this.fillForceByState(
                Reinforce,
                DetailInfo,
                TotalNum,
                cheatRate,
                posX,
                posY,
                true
            )

            //集结
            massForceBuilderList =
                    this.fillForceByMass(DetailInfo, DetailInfo, cheatRate, beScoutPlayerId, false)
        }

        //显示目标所有部队详细信息，伤兵营里伤兵详细信息，陷阱伤兵营里陷阱详细信息，显示目标正在运行中的战斗BUF和对应等级
        if (getResearchEffectValue(areaCache, NO_FILTER, scoutPlayer, ScoutResearchLv10) != 0) {
            //buff
            val buffs = areaCache.buffCache.findBuffsByPlayerId(beScoutPlayer.id)
            buffs.forEach {
                scoutBuilder.addCastleBuffs(it.protoId)
            }

            //伤兵
            scoutBuilder.haveData = scoutBuilder.haveData or ScoutHaveSoliderHospital
            hospitalSoliderBuilder = this.fillScoutSolider(DetailInfo, hospitalSoliderMap, cheatRate)

            //陷阱伤兵
            scoutBuilder.haveData = scoutBuilder.haveData or ScoutHaveTrapHospital
            hospitalTrapBuilder = this.fillScoutSolider(DetailInfo, hospitalTrapMap, cheatRate)

            //藏兵
            val caveInfo = areaCache.caveCache.findCaveByPlayerId(beScoutPlayerId)
            if (caveInfo != null) {
                val caveGroup = areaCache.walkForceGroupCache.findWalkForceGroupById(caveInfo.hideForceGroupId)
                if (caveGroup == null) {
                    normalLog.error("藏兵信息中无藏兵部队信息:${caveInfo.hideForceGroupId}")
                    return
                }
                hideForceBuilder = this.fillForceByGroup(DetailInfo, DetailInfo, cheatRate, caveGroup, true)
            }
        }

        inCityForceBuilder.setPlayerInfo(inCityPlayerInfoBuilder)
        if (inCityHeroInfoBuilder != null) {
            inCityForceBuilder.setHeroInfo(inCityHeroInfoBuilder)
        }
        scoutBuilder.setDefForce(inCityForceBuilder)
        if (inCityTrapBuilder != null) {
            scoutBuilder.setScoutTrapsInfo(inCityTrapBuilder)
        }
        if (hideForceBuilder != null) {
            scoutBuilder.setHideForce(hideForceBuilder)
        }
        if (hospitalSoliderBuilder != null) {
            scoutBuilder.setScoutSoliderHospital(hospitalSoliderBuilder)
        }
        if (hospitalTrapBuilder != null) {
            scoutBuilder.setScoutTrapsHospital(hospitalTrapBuilder)
        }
        stationForceBuilderList?.forEach { scoutBuilder.addStationedForces(it) }
        reinforceForceBuilderList?.forEach { scoutBuilder.addReinforceForces(it) }
        massForceBuilderList?.forEach { scoutBuilder.addMassForces(it) }
        buildingBuilderList.forEach { scoutBuilder.addTerritoryBuilding(it) }
        report.setScoutReport(scoutBuilder)
        this.saveReport(scoutPlayerId, report, this.fightInfo)
    }

    private fun fillBuilding(beScoutPlayer: Player): LinkedList<TerritoryBuilding.Builder> {
        val buildingBuilderList = LinkedList<TerritoryBuilding.Builder>()
        val infoByHome =
            areaCache.infoByHomeCache.findInfoByHomeByPlayerId(beScoutPlayer.id) ?: return buildingBuilderList
        infoByHome.buildInfoMap.forEach { buildingType, buildingLvs ->
            buildingLvs.forEach {
                if (it <= 0) {
                    return@forEach
                }
                val buildingProto = pcs.innerBuildingCache.innerBuildingProtoMap[buildingType]
                if (buildingProto != null && !intToBool(buildingProto.canScout)) {
                    return@forEach
                }
                val buildingBuilder = TerritoryBuilding.newBuilder()
                buildingBuilder.buildId = buildingType
                buildingBuilder.lv = it
                buildingBuilderList.add(buildingBuilder)
            }
        }

        return buildingBuilderList
    }

    private fun fillForceByState(
        forceType: Int,
        scoutSoliderLv: Int,
        scoutHeroLv: Int,
        cheatRate: Int,
        x: Int,
        y: Int,
        withPlayerInfo: Boolean = true
    ): LinkedList<ScoutForceInfo.Builder> {
        val forceBuilderList = LinkedList<ScoutForceInfo.Builder>()
        val groups =
            areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(x, y, forceType)
        for (group in groups) {
            forceBuilderList.add(fillForceByGroup(scoutSoliderLv, scoutHeroLv, cheatRate, group, withPlayerInfo))
        }
        return forceBuilderList
    }

    private fun fillForceByMass(
        scoutSoliderLv: Int,
        scoutHeroLv: Int,
        cheatRate: Int,
        playerId: Long,
        withPlayerInfo: Boolean = true
    ): LinkedList<ScoutForceInfo.Builder> {
        val forceBuilderList = LinkedList<ScoutForceInfo.Builder>()
        val massList = areaCache.massCache.findMassByPlayerId(playerId)
        for (mass in massList) {
            if (mass.massState == Run) {
                continue
            }
            val massMember = mass.findMassMember(playerId) ?: continue

            val group = areaCache.walkForceGroupCache.findWalkForceGroupById(massMember.groupId) ?: continue

            val forceBuilder = this.fillForceByGroup(scoutSoliderLv, scoutHeroLv, cheatRate, group, withPlayerInfo)
            forceBuilderList.add(forceBuilder)
        }
        return forceBuilderList
    }

    private fun fillForceByGroup(
        scoutSoliderLv: Int,
        scoutHeroLv: Int,
        cheatRate: Int,
        group: WalkForceGroup,
        withPlayerInfo: Boolean = true
    ): ScoutForceInfo.Builder {
        val soliderMap = hashMapOf<Int, Int>()
        val heroList = LinkedList<Hero>()
        val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(group.id)
        for (force in forces) {
            for ((id, num) in force.soliderMap) {
                if (num <= 0) {
                    continue
                }
                soliderMap[id] = (soliderMap[id] ?: 0) + num
            }

            for (heroId in force.heroIdList) {
                val hero =
                    areaCache.heroCache.findHeroById(force.playerId, heroId) ?: continue
                heroList.add(hero)
            }
        }

        val forceBuilder = ScoutForceInfo.newBuilder()
        if (withPlayerInfo) {
            val playerInfoBuilder = ScoutPlayerInfo.newBuilder()
            playerInfoBuilder.setPlayerInfo(this.getPlayerInfoByPlayerId(group.mainPlayerId))
            forceBuilder.setPlayerInfo(playerInfoBuilder)
        }

        forceBuilder.setSoliderInfo(this.fillScoutSolider(scoutSoliderLv, soliderMap, cheatRate))
        forceBuilder.setHeroInfo(this.fillScoutHero(scoutHeroLv, group.mainPlayerId, heroList))
        return forceBuilder
    }

    private fun fillScoutSolider(
        scoutLv: Int,
        soliderMap: HashMap<Int, Int>,
        cheatRate: Int
    ): ScoutSoliderInfo.Builder? {
        when (scoutLv) {
            TotalNum -> {
                //显示士兵估算总量
                var totalSolider = 0
                for ((_, num) in soliderMap) {
                    totalSolider += num
                }
                val scoutBuilder = ScoutSoliderInfo.newBuilder()
                scoutBuilder.type = TotalNum
                scoutBuilder.soliderCount = getEvaluateNum(totalSolider * cheatRate)
                return scoutBuilder
            }
            EasyInfo -> {
                //显示士兵简略信息
                val scoutBuilder = ScoutSoliderInfo.newBuilder()
                scoutBuilder.type = EasyInfo
                var totalEvaluateNum = 0
                for ((id, num) in soliderMap) {
                    val evaluateNum = getEvaluateNum(num * cheatRate)
                    totalEvaluateNum += evaluateNum
                    val soliderBuilder = ScoutSolider.newBuilder()
                    soliderBuilder.protoId = id
                    soliderBuilder.count = evaluateNum
                    scoutBuilder.addScoutSolider(soliderBuilder)
                }
                scoutBuilder.soliderCount = totalEvaluateNum
                return scoutBuilder
            }
            DetailInfo -> {
                val scoutBuilder = ScoutSoliderInfo.newBuilder()
                scoutBuilder.type = DetailInfo
                var totalNum = 0
                for ((id, num) in soliderMap) {
                    totalNum += num * cheatRate
                    val soliderBuilder = ScoutSolider.newBuilder()
                    soliderBuilder.protoId = id
                    soliderBuilder.count = num * cheatRate
                    scoutBuilder.addScoutSolider(soliderBuilder)
                }
                scoutBuilder.soliderCount = totalNum
                return scoutBuilder
            }
            SoliderInfoByArmyType -> {
                val scoutSoliderBuilder = ScoutSoliderInfo.newBuilder()
                scoutSoliderBuilder.type = SoliderInfoByArmyType

                val soliderTypeMap = hashMapOf<Int, Int>()
                for ((id, num) in soliderMap) {
                    val soliderProto = pcs.soliderCache.soliderProtoMap[id]
                    if (soliderProto == null) {
                        normalLog.error("找不到士兵配置：$id")
                        continue
                    }
                    soliderTypeMap[soliderProto.armyType] = (soliderTypeMap[soliderProto.armyType] ?: 0) + num
                }
                var totalEvaluateNum = 0
                for ((armyType, num) in soliderTypeMap) {
                    val soliderByArmyTypeBuilder = ScoutSoliderByArmyType.newBuilder()
                    soliderByArmyTypeBuilder.armyType = armyType
                    val evaluateNum = getEvaluateNum(num * cheatRate)
                    soliderByArmyTypeBuilder.count = evaluateNum
                    totalEvaluateNum += evaluateNum
                    scoutSoliderBuilder.addScoutSoliderByArmyType(soliderByArmyTypeBuilder)
                }
                scoutSoliderBuilder.soliderCount = totalEvaluateNum
                return scoutSoliderBuilder
            }
        }
        return null
    }

    private fun fillScoutHero(
        scoutLv: Int,
        playerId: Long,
        heroList: List<Hero>
    ): ScoutHerosInfo.Builder? {
        val heroDataList = LinkedList<HeroData>()
        heroList.forEach {
            val heroData = HeroData(
                it.id,
                it.protoId,
                it.level,
                it.advLv,
                it.awake,
                0,
                0
            )
            heroDataList.add(heroData)
        }
        return fillScoutHeroData(scoutLv, playerId, heroDataList)
    }

    private fun fillScoutHeroData(
        scoutLv: Int,
        playerId: Long,
        heroList: List<HeroData>
    ): ScoutHerosInfo.Builder? {
        when (scoutLv) {
            TotalNum -> {
                //显示英雄总量
                val scoutBuilder = ScoutHerosInfo.newBuilder()
                scoutBuilder.herosNum = heroList.count()
                return scoutBuilder
            }
            EasyInfo -> {
                //显示英雄简略信息
                val scoutBuilder = ScoutHerosInfo.newBuilder()
                scoutBuilder.herosNum = heroList.count()
                val player = areaCache.playerCache.findPlayerById(playerId)
                for (hero in heroList) {
                    val heroBuilder = HeroInfoForReport.newBuilder()
                    heroBuilder.protoId = hero.protoId
                    heroBuilder.isLarid = boolToInt(player != null && player.mainHeroId == hero.id)
                    scoutBuilder.addHeros(heroBuilder)
                }
                return scoutBuilder
            }
            DetailInfo -> {
                //显示英雄详细信息
                val scoutBuilder = ScoutHerosInfo.newBuilder()
                scoutBuilder.herosNum = heroList.count()
                val player = areaCache.playerCache.findPlayerById(playerId)
                for (hero in heroList) {
                    val heroBuilder = HeroInfoForReport.newBuilder()
                    heroBuilder.protoId = hero.protoId
                    heroBuilder.lv = hero.lv
                    heroBuilder.starLv = hero.star
                    heroBuilder.grade = hero.awake
                    heroBuilder.isLarid = boolToInt(player != null && player.mainHeroId == hero.id)
                    scoutBuilder.addHeros(heroBuilder)
                }
                return scoutBuilder
            }
        }
        return null
    }

    fun genScoutRelicReport(scoutPlayerId: Long, relicCell: Relic) {
        val report = this.preFillReport()
        report.reportType = SCOUT_REPORT
        val scoutBuilder = ScoutReport.newBuilder()
        scoutBuilder.cellType = CELL_RELIC
        scoutBuilder.relicId = relicCell.relicId

        //遗迹奖励
        scoutBuilder.reward = resVoToResString(
            asList(
                ResVo(
                    RES_RELIC_REWARD,
                    relicCell.timeBoxId.toLong(), relicCell.dropRate.toLong()
                )
            )
        )

        var scoutResult = ResultCode.SUCCESS.code
        if (relicCell.isUnScout) {
            scoutResult = ResultCode.HAVE_FANZHENCHA_BUFF.code
        }
        scoutBuilder.result = scoutResult

        if (scoutResult != ResultCode.SUCCESS.code) {
            report.setScoutReport(scoutBuilder)
            this.saveReport(scoutPlayerId, report, this.fightInfo)
            return
        }

        val scoutPlayer = areaCache.playerCache.findPlayerById(scoutPlayerId)
        if (scoutPlayer == null) {
            normalLog.error("侦查遗迹，找不到侦查者信息：$scoutPlayerId")
            return
        }

        val fightData = createFightDataBySoliderTeamId(relicCell.lineUpId)

        val soliderMap = hashMapOf<Int, Int>()
        val trapMap = hashMapOf<Int, Int>()
        for ((id, num) in fightData.soliderDataList[0].soliderMap) {
            if (num <= 0) {
                continue
            }
            val soliderProto = pcs.soliderCache.soliderProtoMap[id]
            if (soliderProto == null) {
                normalLog.error("找不到士兵配置：$id")
                continue
            }

            if (isSolider(soliderProto.armyType)) {
                soliderMap[id] = (soliderMap[id] ?: 0) + num
            }
            if (isTrap(soliderProto.armyType)) {
                trapMap[id] = (trapMap[id] ?: 0) + num
            }
        }

        val inCityForceBuilder = ScoutForceInfo.newBuilder()
        var inCityHeroInfoBuilder: ScoutHerosInfo.Builder? = null
        var inCityTrapBuilder: ScoutSoliderInfo.Builder? = null

        //开启侦察功能。显示目标守城部队的总数估算值
        if (getResearchEffectValue(areaCache, NO_FILTER, scoutPlayer, ScoutResearchLv1) != 0) {
            scoutBuilder.haveData = ScoutHaveSolider
            inCityForceBuilder.setSoliderInfo(this.fillScoutSolider(TotalNum, soliderMap, 1))

            inCityHeroInfoBuilder = this.fillScoutHeroData(EasyInfo, 0, fightData.heroList)
        }

        //显示目标守城部队的兵种构成，显示守城陷阱的兵种构成，显示守城陷阱的总数估算值
        if (getResearchEffectValue(areaCache, NO_FILTER, scoutPlayer, ScoutResearchLv2) != 0) {
            //士兵
            inCityForceBuilder.setSoliderInfo(this.fillScoutSolider(SoliderInfoByArmyType, soliderMap, 1))

            //陷阱
            scoutBuilder.haveData = scoutBuilder.haveData or ScoutHaveTrap
            inCityTrapBuilder = this.fillScoutSolider(TotalNum, trapMap, 1)
        }

        //显示目标点，守城部队、守城陷阱的详细兵种名称和对应估算值，显示驻防部队、增援部队的兵种构成和对应估算值
        if (getResearchEffectValue(areaCache, NO_FILTER, scoutPlayer, ScoutResearchLv4) != 0) {
            //士兵
            inCityForceBuilder.setSoliderInfo(this.fillScoutSolider(EasyInfo, soliderMap, 1))
        }

        //显示目标守城英雄的详细信息
        if (getResearchEffectValue(areaCache, NO_FILTER, scoutPlayer, ScoutResearchLv8) != 0) {
            inCityHeroInfoBuilder = this.fillScoutHeroData(EasyInfo, 0, fightData.heroList)
        }

        //显示目标城堡所有建筑信息对应等级，守城军团详细信息，守城陷阱详细信息
        if (getResearchEffectValue(areaCache, NO_FILTER, scoutPlayer, ScoutResearchLv9) != 0) {
            //士兵
            inCityForceBuilder.setSoliderInfo(this.fillScoutSolider(DetailInfo, soliderMap, 1))

            //陷阱
            inCityTrapBuilder = this.fillScoutSolider(DetailInfo, trapMap, 1)
        }

        if (inCityHeroInfoBuilder != null) {
            inCityForceBuilder.setHeroInfo(inCityHeroInfoBuilder)
        }
        scoutBuilder.setDefForce(inCityForceBuilder)
        if (inCityTrapBuilder != null) {
            scoutBuilder.setScoutTrapsInfo(inCityTrapBuilder)
        }

        report.setScoutReport(scoutBuilder)
        this.saveReport(scoutPlayerId, report, this.fightInfo)
    }

    fun genScoutWonderReport(scoutPlayerId: Long, wonderId: Int, scoutResult: Int) {
        val report = this.preFillReport()
        report.reportType = SCOUT_REPORT
        val scoutBuilder = ScoutReport.newBuilder()
        scoutBuilder.result = scoutResult
        scoutBuilder.cellType = CELL_WONDER

        if (scoutResult != ResultCode.SUCCESS.code) {
            report.setScoutReport(scoutBuilder)
            this.saveReport(scoutPlayerId, report, this.fightInfo)
            return
        }
        val scoutPlayer = areaCache.playerCache.findPlayerById(scoutPlayerId)
        if (scoutPlayer == null) {
            normalLog.error("侦查，找不到侦查玩家信息:$scoutPlayerId")
            return
        }

        val wonder = areaCache.wonderCache.findWonder(wonderId)
        if (wonder == null) {
            normalLog.error("找不到奇观信息:$wonderId")
            return
        }

        if (wonder.occupyGroupId != 0L) {
            val occupyGroup = areaCache.walkForceGroupCache.findWalkForceGroupById(wonder.occupyGroupId)
            if (occupyGroup == null) {
                normalLog.error("找不到奇观占领部队信息：${wonder.occupyGroupId}")
                return
            }

            val rst =
                areaCache.buffCache.findBuffValueByBuffType(occupyGroup.mainPlayerId, BUFF_EFFECT_ZHABING)
            var cheatRate = rst.value
            if (!rst.isHave) {
                cheatRate = 1
            }

            var stationForceBuilderList: LinkedList<ScoutForceInfo.Builder>? = null
            var reinforceForceBuilderList: LinkedList<ScoutForceInfo.Builder>? = null

            //显示给在目标点驻防部队的总数估算值，增援的部队的总数估算值
            if (getResearchEffectValue(areaCache, NO_FILTER, scoutPlayer, ScoutResearchLv3) != 0) {
                //驻扎
                scoutBuilder.haveData = scoutBuilder.haveData or ScoutHaveStation
                stationForceBuilderList = this.fillForceByState(
                    Stationed,
                    TotalNum,
                    EasyInfo,
                    cheatRate,
                    posX,
                    posY,
                    false
                )

                //增援
                scoutBuilder.haveData = scoutBuilder.haveData or ScoutHaveReinforce
                reinforceForceBuilderList = this.fillForceByState(
                    Reinforce,
                    TotalNum,
                    TotalNum,
                    cheatRate,
                    posX,
                    posY,
                    false
                )
            }

            //显示目标点，守城部队、守城陷阱的详细兵种名称和对应估算值，显示驻防部队、增援部队的兵种构成和对应估算值
            if (getResearchEffectValue(areaCache, NO_FILTER, scoutPlayer, ScoutResearchLv4) != 0) {
                //驻扎
                stationForceBuilderList = this.fillForceByState(
                    Stationed,
                    SoliderInfoByArmyType,
                    EasyInfo,
                    cheatRate,
                    posX,
                    posY,
                    false
                )

                //增援
                reinforceForceBuilderList = this.fillForceByState(
                    Reinforce,
                    SoliderInfoByArmyType,
                    TotalNum,
                    cheatRate,
                    posX,
                    posY,
                    false
                )
            }

            //显示驻防部队、增援部队的详细兵种名称和对应估算值
            if (getResearchEffectValue(areaCache, NO_FILTER, scoutPlayer, ScoutResearchLv6) != 0) {
                //驻扎
                stationForceBuilderList = this.fillForceByState(
                    Stationed,
                    EasyInfo,
                    EasyInfo,
                    cheatRate,
                    posX,
                    posY,
                    false
                )

                //增援
                reinforceForceBuilderList = this.fillForceByState(
                    Reinforce,
                    EasyInfo,
                    TotalNum,
                    cheatRate,
                    posX,
                    posY,
                    false
                )
            }

            //显示目标派出去集结部队和藏兵部队的兵种信息和估算值，显示提供驻防的玩家名称，提供增援的玩家名称
            if (getResearchEffectValue(areaCache, NO_FILTER, scoutPlayer, ScoutResearchLv7) != 0) {
                //驻扎
                stationForceBuilderList = this.fillForceByState(
                    Stationed,
                    EasyInfo,
                    EasyInfo,
                    cheatRate,
                    posX,
                    posY,
                    true
                )

                //增援
                reinforceForceBuilderList = this.fillForceByState(
                    Reinforce,
                    EasyInfo,
                    EasyInfo,
                    cheatRate,
                    posX,
                    posY,
                    true
                )
            }

            //显示目标守城英雄的详细信息
            if (getResearchEffectValue(areaCache, NO_FILTER, scoutPlayer, ScoutResearchLv8) != 0) {
                //驻扎
                stationForceBuilderList = this.fillForceByState(
                    Stationed,
                    EasyInfo,
                    DetailInfo,
                    cheatRate,
                    posX,
                    posY,
                    true
                )

                //增援
                reinforceForceBuilderList = this.fillForceByState(
                    Reinforce,
                    EasyInfo,
                    DetailInfo,
                    cheatRate,
                    posX,
                    posY,
                    true
                )
            }

            //显示目标城堡所有建筑信息对应等级，守城军团详细信息，守城陷阱详细信息
            if (getResearchEffectValue(areaCache, NO_FILTER, scoutPlayer, ScoutResearchLv9) != 0) {
                //驻扎
                stationForceBuilderList = this.fillForceByState(
                    Stationed,
                    DetailInfo,
                    DetailInfo,
                    cheatRate,
                    posX,
                    posY,
                    true
                )


                //增援
                reinforceForceBuilderList = this.fillForceByState(
                    Reinforce,
                    DetailInfo,
                    DetailInfo,
                    cheatRate,
                    posX,
                    posY,
                    true
                )
            }

            stationForceBuilderList?.forEach { scoutBuilder.addStationedForces(it) }
            reinforceForceBuilderList?.forEach { scoutBuilder.addReinforceForces(it) }
        }

        report.setScoutReport(scoutBuilder)
        this.saveReport(scoutPlayerId, report, this.fightInfo)
    }

    private fun getPlayerInfoByPlayerId(playerId: Long): FightPlayerInfo.Builder? {
        val player = areaCache.playerCache.findPlayerById(playerId) ?: return null
        val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
        if (castle == null) {
            normalLog.error("找不到玩家对应的城池信息:${player.focusCastleId}")
            return null
        }
        val playerInfoBuilder = FightPlayerInfo.newBuilder()
        playerInfoBuilder.id = playerId
        playerInfoBuilder.name = player.name
        playerInfoBuilder.photo = player.photoProtoId
        playerInfoBuilder.level = player.kingLv
        playerInfoBuilder.vipLevel = areaCache.infoByHomeCache.findInfoByHomeByPlayerId(playerId)?.vipLv ?: 0
        playerInfoBuilder.allianceId = player.allianceId
        playerInfoBuilder.allianceName = player.allianceName
        playerInfoBuilder.allianceShortName = player.allianceShortName
        playerInfoBuilder.posX = castle.x
        playerInfoBuilder.posY = castle.y
        playerInfoBuilder.allianceRnum = player.allianceRNum
        val wonder = areaCache.wonderCache.findBigWonder()
        playerInfoBuilder.countryPos = wonder?.officeMap?.get(playerId) ?: 0
        return playerInfoBuilder
    }

    //生成被侦查战报
    fun genBeScoutReport(
        scoutPlayerId: Long,
        beScoutPlayerId: Long,
        cellType: Int,
        result: Int = ResultCode.SUCCESS.code
    ) {
        val report = this.preFillReport()
        report.reportType = BE_SCOUT_REPORT
        val beScoutBuilder = BeScoutReport.newBuilder()
        beScoutBuilder.result = result
        beScoutBuilder.cellType = cellType
        beScoutBuilder.setPlayerInfo(this.getPlayerInfoByPlayerId(scoutPlayerId))

        when (cellType) {
            CELL_RESOURCE -> {
                val resCell = areaCache.commonResCache.findCommonResByXY(this.posX, this.posY)
                if (resCell == null) {
                    normalLog.error("找不到资源地(${this.posX},${this.posY})")
                    return
                }
                val resProto = pcs.resPointProtoCache.resPointMap[resCell.resId]
                if (resProto == null) {
                    normalLog.error("找不到资源地配置:${resCell.resId}")
                    return
                }
                beScoutBuilder.resType = resProto.resType
                beScoutBuilder.resLv = resCell.lv
            }

            CELL_CASTLE,
            CELL_EMPTY,
            CELL_WONDER -> {
            }
            else ->
                return
        }
        report.setBeScoutReport(beScoutBuilder)
        this.saveReport(beScoutPlayerId, report, this.fightInfo)
    }

    //生成竞技场战报
    fun genJjcFightReport(
        fightResult: Int,
        fightSide: Int,
        changeRank: Int,
        defRank: Int,
        readState: Int
    ) {
        val atkFightData = this.atkFightData
        if (atkFightData == null) {
            normalLog.error("竞技场战斗，无攻击方数据")
            return
        }
        val defFightData = this.defFightData
        if (defFightData == null) {
            normalLog.error("竞技场战斗，无防守方数据")
            return
        }

        val report = this.preFillReport()
        report.reportType = JJC_FIGHT_REPORT
        val jjcReportBuilder = JjcFightReport.newBuilder()
        jjcReportBuilder.fightResult = fightResult
        jjcReportBuilder.fightType = fightSide
        jjcReportBuilder.rankChange = 0
        if (fightResult == FIGHT_RESULT_WIN) {
            jjcReportBuilder.rankChange = changeRank
        }

        for (hero in atkFightData.heroList) {
            val forceBuilder = JjcFightForceInfo.newBuilder()
            forceBuilder.protoId = hero.protoId
            forceBuilder.level = hero.lv
            forceBuilder.starLv = hero.star
            forceBuilder.awake = hero.awake
            jjcReportBuilder.addAtkJjcFightForce(forceBuilder)
        }

        for (hero in defFightData.heroList) {
            val forceBuilder = JjcFightForceInfo.newBuilder()
            forceBuilder.protoId = hero.protoId
            forceBuilder.level = hero.lv
            forceBuilder.starLv = hero.star
            forceBuilder.awake = hero.awake
            jjcReportBuilder.addDefJjcFightForce(forceBuilder)
        }

        if (fightSide == ATK_SIDE) {
            //攻击方，设置防守方名字
            if (defFightData.playerId != 0L) {
                val defPlayer = areaCache.playerCache.findPlayerById(defFightData.playerId)
                if (defPlayer != null) {
                    jjcReportBuilder.enemyName = defPlayer.name
                    jjcReportBuilder.enemyPhoto = defPlayer.photoProtoId
                    jjcReportBuilder.enemyAllianceName = defPlayer.allianceName
                }
            } else {
                jjcReportBuilder.defRank = defRank
            }
            report.setJjcFightReport(jjcReportBuilder)
            this.saveReport(atkFightData.playerId, report, this.fightInfo, readState)
            return
        }

        val atkPlayer = areaCache.playerCache.findPlayerById(atkFightData.playerId)
        if (atkPlayer != null) {
            jjcReportBuilder.enemyName = atkPlayer.name
            jjcReportBuilder.enemyPhoto = atkPlayer.photoProtoId
            jjcReportBuilder.enemyAllianceName = atkPlayer.allianceName
        }

        report.setJjcFightReport(jjcReportBuilder)
        this.saveReport(defFightData.playerId, report, this.fightInfo, readState)
    }
}