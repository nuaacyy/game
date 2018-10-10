package com.point18.slg2d.world.module.heroBattle1.fightLogic

import com.point18.slg2d.common.pc.HeroSkillEffProto
import com.point18.slg2d.common.pc.HeroSkillProto

//buff策略
interface IBuffStrategy<T : Buff> {
    // 附加
    fun onAttach(buff: T)

    // 移除
    fun onDetach(buff: T)
}

interface IBuffFactory<T : Buff> {
    // 创建BUFF
    fun createBuff(
        comeFromEntity: Entity,
        attachEntity: Entity,
        skillProto: com.point18.slg2d.common.pc.HeroSkillProto,
        skillEffProto: com.point18.slg2d.common.pc.HeroSkillEffProto,
        relatedId: Int = 0
    ): T
}

val buffStrategyMap = hashMapOf<Int, IBuffStrategy<Buff>>()
val buffFactoryMap = hashMapOf<Int, IBuffFactory<Buff>>()

//注册buff
inline fun <reified T : Buff> registerBuff(buffType: Int, strategy: IBuffStrategy<T>, factory: IBuffFactory<T>) {
    buffStrategyMap[buffType] = object : IBuffStrategy<Buff> {

        override fun onAttach(buff: Buff) {
            if (buff !is T) {
                assert(false) { "buff回调类型与buff类型不一致:$buffType" }
                return
            }
            strategy.onAttach(buff)
        }

        override fun onDetach(buff: Buff) {
            if (buff !is T) {
                assert(false) { "buff回调类型与buff类型不一致:$buffType" }
                return
            }
            strategy.onDetach(buff)
        }
    }

    buffFactoryMap[buffType] = object : IBuffFactory<Buff> {
        override fun createBuff(
            comeFromEntity: Entity,
            attachEntity: Entity,
            skillProto: HeroSkillProto,
            skillEffProto: HeroSkillEffProto,
            relatedId: Int
        ): Buff {
            return factory.createBuff(comeFromEntity, attachEntity, skillProto, skillEffProto, relatedId)
        }
    }
}

//选择buff策略
fun selectBuffStrategy(skillType: Int): IBuffStrategy<Buff> {
    return requireNotNull(buffStrategyMap[skillType])
}

//选择buff创建工厂
fun selectBuffFactory(buffType: Int): IBuffFactory<Buff> {
    return requireNotNull(buffFactoryMap[buffType])
}
