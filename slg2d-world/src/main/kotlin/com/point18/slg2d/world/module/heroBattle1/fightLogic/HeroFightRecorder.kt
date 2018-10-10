package com.point18.slg2d.world.module.heroBattle1.fightLogic

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.fightdomain.FightData
import com.point18.slg2d.world.module.fightdomain.FightRecordCollection
import java.util.*

//战斗记录
class HeroFightRecorder : FightRecordCollection() {
    lateinit var manager: EntityManager

    var totalHpBeforeFight = 0

    //战斗前的统计
    fun beforeFightRecord(atkFightData: FightData, defFightData: FightData) {
        manager.entityList.forEach {
            if (it.getIntProperty(FightSide, false) == ATK_SIDE) {
                this.intRecordMap[AtkHpBeforeFight] = (this.intRecordMap[AtkHpBeforeFight] ?: 0) +
                    (it.getIntProperty(Hp, false))
            } else {
                this.intRecordMap[DefHpBeforeFight] = (this.intRecordMap[DefHpBeforeFight] ?: 0) +
                    (it.getIntProperty(Hp, false))
            }
        }
    }

    //战斗后的统计
    fun afterFightRecord(atkFightData: FightData, defFightData: FightData) {
        val totalHpAfterFight = atkFightData.heroList.sumBy { it.assignHp }
        this.intRecordMap[AtkHpAfterFight] = atkFightData.heroList.sumBy { it.assignHp }
        this.intRecordMap[DefHpAfterFight] = defFightData.heroList.sumBy { it.assignHp }
        this.intRecordMap[AtkDeadHeroNum] = atkFightData.heroList.count { it.assignHp == 0 }
        this.intRecordMap[AtkTotalBeDamage] = totalHpBeforeFight - totalHpAfterFight
    }

    //战斗中根据接收到的记录进行统计
    fun receiveRecord(
        senderId: Int,
        receiverId: Int,
        behaviorType: Int,
        intParas: HashMap<Int, Int>?,
        floatParas: HashMap<Int, Double>?,
        entityList: LinkedList<Entity>?
    ) {
        when (behaviorType) {
            LogLaunchSkill -> {
                val sender = manager.entityMap[senderId] ?: return

                if (sender.getIntProperty(FightSide, false) != ATK_SIDE) {
                    return
                }
                this.intRecordMap[AtkLaunchedSkillNum] = (this.intRecordMap[AtkLaunchedSkillNum] ?: 0) + 1
            }
        }
    }
}