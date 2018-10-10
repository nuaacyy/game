package com.point18.slg2d.world.module.heroBattle.fightLogic

import com.point18.slg2d.common.pc.HeroSkillEffProto
import com.point18.slg2d.common.pc.HeroSkillProto

//默认的buff创建
object DefaultBuffFactory : IBuffFactory<Buff> {
    override fun createBuff(
        comeFromEntity: Entity,
        attachEntity: Entity,
        skillProto: HeroSkillProto,
        skillEffProto: HeroSkillEffProto,
        relatedId: Int
    ): Buff {
        return Buff(comeFromEntity, attachEntity, skillProto, skillEffProto, relatedId)
    }
}

//移动释放技能的buff创建（突进）
object MoveLaunchSkillBuffFactory : IBuffFactory<MoveLaunchSkillBuff> {
    override fun createBuff(
        comeFromEntity: Entity,
        attachEntity: Entity,
        skillProto: HeroSkillProto,
        skillEffProto: HeroSkillEffProto,
        relatedId: Int
    ): MoveLaunchSkillBuff {
        return MoveLaunchSkillBuff(comeFromEntity, attachEntity, skillProto, skillEffProto, relatedId)
    }
}

//复活buff创建
object FuhuoBuffFactory : IBuffFactory<FuhuoBuff> {
    override fun createBuff(
        comeFromEntity: Entity,
        attachEntity: Entity,
        skillProto: HeroSkillProto,
        skillEffProto: HeroSkillEffProto,
        relatedId: Int
    ): FuhuoBuff {
        return FuhuoBuff(comeFromEntity, attachEntity, skillProto, skillEffProto, relatedId)
    }
}

//护盾buff创建
object HudunBuffFactory : IBuffFactory<HudunBuff> {
    override fun createBuff(
        comeFromEntity: Entity,
        attachEntity: Entity,
        skillProto: HeroSkillProto,
        skillEffProto: HeroSkillEffProto,
        relatedId: Int
    ): HudunBuff {
        return HudunBuff(comeFromEntity, attachEntity, skillProto, skillEffProto, relatedId)
    }
}

//触发类buff创建
object ChufaBuffFactory : IBuffFactory<ChufaBuff> {
    override fun createBuff(
        comeFromEntity: Entity,
        attachEntity: Entity,
        skillProto: HeroSkillProto,
        skillEffProto: HeroSkillEffProto,
        relatedId: Int
    ): ChufaBuff {
        return ChufaBuff(comeFromEntity, attachEntity, skillProto, skillEffProto, relatedId)
    }
}