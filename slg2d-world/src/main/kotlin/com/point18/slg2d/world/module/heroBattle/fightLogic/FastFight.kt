package com.point18.slg2d.world.module.heroBattle.fightLogic

import com.point18.slg2d.common.constg.FIGHT_RESULT_ING
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.module.fightdomain.FightData
import com.point18.slg2d.world.module.fightdomain.FightRecordCollection
import pb4client.*

class FastFight(
    var areaCache: AreaCache,
    var atkFightData: FightData,
    var defFightData: FightData
) {
    fun initAndStart(battleTime: Int): HeroFightResult {
        val manager = newEntityManager(this.areaCache, battleTime)
        val logic = FightLogic(this.areaCache, manager, FIGHT_RESULT_ING, atkFightData, defFightData)
        logic.initLogic()
        val reportBuilder = HeroFightReport.newBuilder()

        for (entity in logic.manager.entityList) {
            val entityBuilder = FightEntity.newBuilder()
            entityBuilder.id = entity.id.toLong()

            for ((k, v) in entity.intPropertyMap) {
                val intBuilder = IntProperty.newBuilder()
                intBuilder.propertyType = k
                intBuilder.propertyValue = v.toLong()
                entityBuilder.addIntPropertys(intBuilder)
            }
            for ((k, v) in entity.floatPropertyMap) {
                val floatBuilder = FloatProperty.newBuilder()
                floatBuilder.propertyType = k
                floatBuilder.propertyValue = v
                entityBuilder.addFloatPropertys(floatBuilder)
            }
            reportBuilder.addEntitys(entityBuilder)
        }

        logic.beforeFight()
        logic.fight()
        logic.afterFight()

        reportBuilder.fightResult = logic.fightResult
        for (record in logic.manager.requestRecord) {
            if (record is FightRecord.Builder) {
                reportBuilder.addRecords(record)
            }

        }

        val str = reportBuilder.build().toByteArray()
        return HeroFightResult(FightDomain(logic.fightResult, str), manager.recorder)
    }
}

data class HeroFightResult(
    var fightDomain: FightDomain,
    var recordCollection: FightRecordCollection
)

data class FightDomain(
    var fightResult: Int, //战斗结果  1-进攻方全灭  2-防守方全灭  0-规定回合未结束
    var fightInfo: ByteArray //战斗信息
)

