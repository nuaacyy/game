package com.point18.slg2d.world.module.walk

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.area4data.WalkForceGroup
import com.point18.slg2d.world.module.walk.endWalkStrategy.*
import com.point18.slg2d.world.module.walk.fightStrategy.fightDeal
import com.point18.slg2d.world.module.walk.startWalkStrategy.*
import com.point18.slg2d.world.module.walk.walkComm.WalkParam
import com.point18.slg2d.world.wpm
import java.util.*

var walkM: WalkModule = WalkModule()

class WalkModule : com.point18.slg2d.common.baseg.IModule {
    val walkDeals = hashMapOf<Int, IWalkDeal>()
    val cellDeals = hashMapOf<Int, HashMap<Int, ICellDeal>>()

    override fun moduleInit() {

        // 初始化新的行军策略
        this.walkDeals[WalkFightPlayer] = WalkGoFight()              // 打人
        this.walkDeals[WalkCommonBoss] = WalkCommonBoss()            // 打普通魔物
        this.walkDeals[WalkFarm] = WalkFarm()                        // 采集
        this.walkDeals[WalkGoHome] = WalkGoHome()                     // 回城
        this.walkDeals[WalkTransport] = WalkTransport()              // 运输
        this.walkDeals[WalkOccupyCell] = WalkOccupyCell()            // 占领土地
        this.walkDeals[WalkScout] = WalkScout()                      // 侦查
        this.walkDeals[WalkReinforce] = WalkReinforce()              // 增援
        this.walkDeals[WalkRelic] = WalkRelic()                      // 遗迹
        this.walkDeals[WalkJoinMass] = WalkJoinMass()                // 加入集结
        this.walkDeals[WalkJoinRelicMass] = WalkJoinMass()           // 加入集结
        this.walkDeals[WalkStation] = WalkStation()                  // 驻扎
        this.walkDeals[WalkMainHeroGoHome] = WalkMainHeroHome()   // 领主回家
        this.walkDeals[WalkOccupyWonder] = WalkOccupyWounder(CheckHaveSolider or CheckNotSameAlliance)    // 占领奇观
        this.walkDeals[WalkReinforceWonder] = WalkReinforceWounder() // 增援奇观
        this.walkDeals[WalkCallBoss] = WalkCallBoss()                // 打召唤boss
        this.walkDeals[WalkChangeOccupyWonder] = WalkOccupyWounder(CheckHaveSolider)      // 指挥官换防
        this.walkDeals[WalkActivityBoss] = WalkActivityBoss()        // 打活动boss

        // 行军结束策略
        this.cellDeals.getOrPut(WalkCommonBoss) { hashMapOf() }.let {
            it[CELL_MONSTER] = DealOnCommonBossCell()
        }
        this.cellDeals.getOrPut(WalkJoinMass) { hashMapOf() }.let {
            it[CELL_CASTLE] = DealJoinMassOnCastleCell()
        }
        this.cellDeals.getOrPut(WalkJoinRelicMass) { hashMapOf() }.let {
            it[CELL_CASTLE] = DealJoinMassOnCastleCell()
        }
        this.cellDeals.getOrPut(WalkFightPlayer) { hashMapOf() }.let {
            it[CELL_CASTLE] = DealFightPlayerOnCastleCell()
        }
        this.cellDeals.getOrPut(WalkFarm) { hashMapOf() }.let {
            it[CELL_RESOURCE] = DealOnCommonResCell()
            it[CELL_BOSS_DEAD_RESOURCE] = DealOnBossResCell()
        }
        this.cellDeals.getOrPut(WalkTransport) { hashMapOf() }.let {
            it[CELL_CASTLE] = DealTransportOnCastleCell()
        }
        this.cellDeals.getOrPut(WalkMainHeroGoHome) { hashMapOf() }.let {
            it[CELL_CASTLE] = DealMainHeroHomeOnCastleICell()
        }
        this.cellDeals.getOrPut(WalkScout) { hashMapOf() }.let {
            it[CELL_RELIC] = DealScoutOnRelicCell()
            it[CELL_CASTLE] = DealScoutOnCastleCell()
            it[CELL_WONDER] = DealScoutOnWonderCell()
        }
        this.cellDeals.getOrPut(WalkGoHome) { hashMapOf() }.let {
            it[CELL_CASTLE] = DealGoHomeOnCastleCell()
        }
        this.cellDeals.getOrPut(WalkOccupyCell) { hashMapOf() }.let {
            it[CELL_RESOURCE] = DealOnCommonResCell()
            it[CELL_EMPTY] = DealOccupyOnEmptyCell()
        }
        this.cellDeals.getOrPut(WalkOccupyWonder) { hashMapOf() }.let {
            it[CELL_WONDER] = DealOccupyOnWonderCell()
        }
        this.cellDeals.getOrPut(WalkChangeOccupyWonder) { hashMapOf() }.let {
            it[CELL_WONDER] = DealOccupyOnWonderCell()
        }
        this.cellDeals.getOrPut(WalkStation) { hashMapOf() }.let {
            it[CELL_CASTLE] = DealStationOnCastleCell()
        }
        this.cellDeals.getOrPut(WalkReinforce) { hashMapOf() }.let {
            it[CELL_CASTLE] = DealReinforceOnCastleCell()
        }
        this.cellDeals.getOrPut(WalkRelic) { hashMapOf() }.let {
            it[CELL_RELIC] = DealFightOnRelicCell()
            it[CELL_CASTLE] = DealFightPlayerOnCastleCell()
        }
        this.cellDeals.getOrPut(WalkReinforceWonder) { hashMapOf() }.let {
            it[CELL_WONDER] = DealReinforceOnWonderICell()
        }
        this.cellDeals.getOrPut(WalkCallBoss) { hashMapOf() }.let {
            it[CELL_CALL_BOSS] = DealOnCallBossCell()
        }
        this.cellDeals.getOrPut(WalkActivityBoss) { hashMapOf() }.let {
            it[CELL_ACTIVITY_BOSS] = DealOnActivityBossCell()
        }

        // 初始化战斗策略
        fightDeal.initDealFight()

        // 注册行军心跳
        wpm.hs.registerHeartHandler(HeartWalk())

        // 行军小人心跳
        wpm.hs.registerHeartHandler(HeartUpdateWalkRobot())

        // 行军集结心跳
        wpm.hs.registerHeartHandler(HeartMass())

        // 注册事件
        wpm.es.register(RELIC_DISAPPEAR, RelicDisappearEventHandler())
        wpm.es.register(ALLOW_ALLIANCE, JoinAllianceEventHandler())
        wpm.es.register(FINAL_QUIT_ALLIANCE, FinalQuitAllianceEventHandler())
        wpm.es.register(GOD_OF_WAR_ON, GodOfWarOnEventHandler())
    }
}

// 行军的处理策略
interface IWalkDeal {
    data class GetPosByTargetResult(
        var result: Int,
        var posX: Int,
        var posY: Int
    )

    data class WalkStartCheckResult(
        var limit: Int = -1 // -1默认值，表示数据不正确
    )

    // 根据目标获取坐标
    fun getPosByTarget(areaCache: AreaCache, targetId: Long, gotoX: Int, gotoY: Int): GetPosByTargetResult

    // 行军开始的独立检测
    fun startCheck(areaCache: AreaCache, wp: WalkParam): Int

    // 行军开始时的检测
    fun walkStartCheck(areaCache: AreaCache, wp: WalkParam, rs: WalkStartCheckResult? = null): Int

    // 行军开始的处理
    fun walkStartDeal(areaCache: AreaCache, walkLineInfo: Walk, targetId: Long): Int
}

interface ICellDeal {
    // 行军结束时的检测
    fun walkFinishCheck(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk): Int

    // 行军结束的处理
    fun walkFinishDeal(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk)
}
