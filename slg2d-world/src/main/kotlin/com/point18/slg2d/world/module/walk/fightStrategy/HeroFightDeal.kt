package com.point18.slg2d.world.module.walk.fightStrategy

import com.point18.slg2d.common.commonfunc.getRandIntAtArea
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.FIGHT_RESULT_ING
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.common.convertHeroFightData
import com.point18.slg2d.world.common.fightData2Builder
import com.point18.slg2d.world.module.fightdomain.*
import com.point18.slg2d.world.wpm
import pb4battle.BattleMsg
import pb4battle.BattleServiceGrpc
import java.util.*

//处理英雄战接口
interface IHeroFightDeal {
    // 打完一小场执行逻辑
    fun dealAfterOneFight(
        areaCache: AreaCache,
        atkFightData: FightData,
        defFightData: FightData,
        runType: Int,
        posX: Int,
        posY: Int,
        fightInfo: FightInfo
    ): Boolean

    // 打完所有战斗执行逻辑
    fun dealAfterAllFight(
        areaCache: AreaCache,
        atkFightData: FightData,
        defFightDataList: LinkedList<FightData>,
        posX: Int,
        posY: Int,
        allFightInfo: AllFightInfo
    ): Boolean
}

val heroFight = HeroFight()

class HeroFight {
    fun doHeroFight(
        areaCache: AreaCache,
        fightType: Int,
        runType: Int,
        posX: Int,
        posY: Int,
        atkFightData: FightData,
        defFightDataList: LinkedList<FightData>,
        tempData: HashMap<Int, Any> = hashMapOf(),
        additionPropertyMap: HashMap<Set<Int>, Map<Int, Int>>? = null,
        onSuccess: ((AllFightInfo) -> Unit)? = null,
        onFail: (() -> Unit)? = null
    ) {
        // 确认战斗策略
        val deal = fightDeal.heroFightDealMap[fightType]
        if (deal == null) {
            normalLog.error("对应类型的战斗处理不存在:$fightType")
            return
        }

        val ranSeed = getRandIntAtArea(areaCache.randSeed, Int.MAX_VALUE)
        areaCache.worldActor.createACS<pb4battle.BattleMsg.DoHeroFightResp>().computeKt {
            val channel = wpm.battleServiceMgr.selectBattleService()
            if (channel == null) {
                val rtBuilder = pb4battle.BattleMsg.DoHeroFightResp.newBuilder()
                rtBuilder.rt = ResultCode.PROCESS_ERROR_BATTLE_SERVICE_ERROR.code
                return@computeKt rtBuilder.build()
            }

            val requestBuilder = BattleMsg.DoHeroFightReq.newBuilder()
            requestBuilder.battleTime = pcs.basicProtoCache.battleTime
            requestBuilder.ranSeed = ranSeed
            requestBuilder.setAtkFightData(fightData2Builder(areaCache, atkFightData))
            for (defFightData in defFightDataList) {
                requestBuilder.addDefFightDatas(fightData2Builder(areaCache, defFightData))
            }
            additionPropertyMap?.forEach { starAttrInfo, addPorpertyInfo ->
                val starInfoBuilder = pb4battle.BattleMsg.StarInfo.newBuilder()
                starAttrInfo.forEach { starInfoBuilder.addStarAttrs(it) }
                addPorpertyInfo.forEach {
                    val propertyBuilder = pb4battle.BattleMsg.AdditionPropertyInfo.newBuilder()
                    propertyBuilder.addProperty = it.key
                    propertyBuilder.addValue = it.value
                    starInfoBuilder.addAddPropertys(propertyBuilder)
                }
                requestBuilder.addStarInfos(starInfoBuilder)
            }
            val stub = BattleServiceGrpc.newBlockingStub(channel)
            stub.doHeroFight(requestBuilder.build())
        }.whenCompleteKt { heroFightResult, err ->
            if (err != null || heroFightResult == null || heroFightResult.rt != ResultCode.SUCCESS.code) {
                onFail?.invoke()
                return@whenCompleteKt
            }
            val allFightInfo = AllFightInfo(FIGHT_RESULT_ING, LinkedList())

            var index = 0
            heroFightResult.fightInfoList.forEach {
                val defFightData = defFightDataList[index]
                index++
                val nowResult = it.fightResult

                val recorderCollection = FightRecordCollection()
                for (statistics in it.statisticsList) {
                    recorderCollection.intRecordMap[statistics.recordKey] = statistics.recordValue
                }

                val bytes = convertHeroFightData(it).build().toByteArray()

                val detailInfo = FightInfo(
                    nowResult,
                    tempData,
                    EachFightInfo(recorderCollection, bytes)
                )

                // 每轮战斗结束时的处理
                deal.dealAfterOneFight(
                    areaCache,
                    atkFightData,
                    defFightData,
                    runType,
                    posX,
                    posY,
                    detailInfo
                )
                allFightInfo.fightResult = nowResult
                allFightInfo.detailInfoList.add(detailInfo.detailInfo)
            }

            // 所有战斗结束后的处理
            deal.dealAfterAllFight(
                areaCache,
                atkFightData,
                defFightDataList,
                posX,
                posY,
                allFightInfo
            )
            onSuccess?.invoke(allFightInfo)
        }
    }
}