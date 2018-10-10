package com.point18.slg2d.world.module.walk.fightStrategy

import com.point18.slg2d.common.commonfunc.getRandIntAtArea
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.FIGHT_RESULT_ING
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.WalkForceGroup
import com.point18.slg2d.world.common.convertHeroFightData
import com.point18.slg2d.world.common.fightData2Builder
import com.point18.slg2d.world.module.fightdomain.*
import com.point18.slg2d.world.wpm
import pb4battle.BattleMsg
import pb4battle.BattleServiceGrpc
import java.util.*

interface IBossFightDeal {

    fun dealAfterOneFight(
        areaCache: AreaCache,
        atkGroup: WalkForceGroup,
        defGroup: WalkForceGroup?,
        atkFightData: FightData,
        defFightData: FightData,
        runType: Int,
        posX: Int,
        posY: Int,
        fightInfo: FightInfo
    ): Boolean

}

val bossFight = BossFight()

class BossFight {
    fun doBossFight(
        areaCache: AreaCache,
        fightType: Int,
        runType: Int,
        posX: Int,
        posY: Int,
        atkForceGroup: WalkForceGroup,
        defForceGroup: WalkForceGroup?,
        atkFightData: FightData,
        defFightData: FightData,
        tempData: HashMap<Int, Any> = hashMapOf(),
        onSuccess: ((AllFightInfo) -> Unit)? = null,
        onErr: (() -> Unit)? = null
    ) {
        val deal = fightDeal.bossFightDealMap[fightType]
        if (deal == null) {
            normalLog.error("对应类型的战斗处理不存在:$fightType")
            return
        }

        val allFightInfo = AllFightInfo(FIGHT_RESULT_ING, LinkedList())

        val ranSeed = getRandIntAtArea(areaCache.randSeed, Int.MAX_VALUE)
        areaCache.worldActor.createACS<pb4battle.BattleMsg.DoHeroFightResp>().computeKt {
            val channel = wpm.battleServiceMgr.selectBattleService()
            if (channel == null) {
                val rtBuilder = pb4battle.BattleMsg.DoHeroFightResp.newBuilder()
                rtBuilder.rt = ResultCode.PROCESS_ERROR_BATTLE_SERVICE_ERROR.code
                return@computeKt rtBuilder.build()
            }

            val requestBuilder = BattleMsg.DoHeroFightReq.newBuilder()
            requestBuilder.battleTime = pcs.basicProtoCache.monsterBattleTime
            requestBuilder.ranSeed = ranSeed
            requestBuilder.setAtkFightData(fightData2Builder(areaCache, atkFightData))
            requestBuilder.addDefFightDatas(fightData2Builder(areaCache, defFightData))
            val stub = BattleServiceGrpc.newBlockingStub(channel)
            stub.doHeroFight(requestBuilder.build())
        }.whenCompleteKt { heroFightResult, err ->
            if (err != null || heroFightResult == null || heroFightResult.rt != ResultCode.SUCCESS.code) {
                onErr?.invoke()
                return@whenCompleteKt
            }

            if (heroFightResult.fightInfoList.isEmpty()) {
                onErr?.invoke()
                return@whenCompleteKt
            }

            val fightInfo = heroFightResult.fightInfoList[0]

            val nowResult = heroFightResult.fightResult

            val recorderCollection = FightRecordCollection()
            for (statistics in fightInfo.statisticsList) {
                recorderCollection.intRecordMap[statistics.recordKey] = statistics.recordValue
            }

            val bytes = convertHeroFightData(fightInfo).build().toByteArray()

            val detailInfo = FightInfo(
                nowResult,
                tempData,
                EachFightInfo(recorderCollection, bytes)
            )

            // 每轮战斗结束时的处理
            deal.dealAfterOneFight(
                areaCache,
                atkForceGroup,
                defForceGroup,
                atkFightData,
                defFightData,
                runType,
                posX,
                posY,
                detailInfo
            )
            allFightInfo.fightResult = nowResult
            allFightInfo.detailInfoList.add(detailInfo.detailInfo)

            onSuccess?.invoke(allFightInfo)
        }
    }
}