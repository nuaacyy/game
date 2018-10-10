package com.point18.slg2d.world.module.heroBattle1.fightLogic

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.HeroSkillEffProto
import com.point18.slg2d.common.pc.HeroSkillProto
import java.util.*

open class Buff(
    var comeFromEntity: Entity,   //Buff来源的实体
    var attachEntity: Entity,   //附加的实体
    var skillProto: HeroSkillProto,    //技能配置
    var skillEffProto: HeroSkillEffProto,//技能效果配置
    var relatedId: Int  //关联Id
) {
    var id: Int = comeFromEntity.manager.genId()
    var nextTakeEffectTime: Int = Int.MAX_VALUE     //下次生效时间
    var loseEffectTime: Int =
        if (skillProto.type == PassiveSkill) Int.MAX_VALUE else comeFromEntity.manager.currentTime + skillEffProto.auraHandler   //失效时间

    var comeFromEntityClone: Entity = comeFromEntity.clone()   //Buff来源的实体的数据镜像

    var defaultHandleMap: HashMap<Int, LinkedList<(buff: Buff) -> Unit>> = hashMapOf()
    var checkHandleMap: HashMap<Int, LinkedList<(buff: Buff) -> Boolean>> = hashMapOf()
    var afterHurtHandleMap: HashMap<Int, LinkedList<(atkEntity: Entity, buff: Buff, hurt: Int) -> Unit>> =
        hashMapOf()
    var beHurtHandleMap: HashMap<Int, LinkedList<(buff: Buff, atkEntity: Entity, hurt: Int) -> Int>> = hashMapOf()
    var checkDistanceHandleMap: HashMap<Int, LinkedList<(buff: Buff, range: Double) -> Boolean>> = hashMapOf()

    //附加buff
    fun attach() {
        val skillType = this.skillEffProto.skillType
        val strategy = selectBuffStrategy(skillType)

        this.attachEntity.addBuff(this)

        strategy.onAttach(this)

        //添加buff附加日志
        val intParas = hashMapOf<Int, Int>()
        intParas[SkillId] = this.skillProto.id
        intParas[SkillEffId] = this.skillEffProto.id
        intParas[BuffId] = this.id
        this.attachEntity.manager.receiveLogRequest(
            this.comeFromEntity.id,
            this.attachEntity.id,
            LogAttachBuff,
            intParas,
            null
        )

        //buff附加后的处理
        val handlerList = this.defaultHandleMap[OnBuffAttach] ?: return
        for (handle in handlerList) {
            handle(this)
        }
    }

    //分离buff
    fun detach() {
        val skillType = this.skillEffProto.skillType
        val strategy = selectBuffStrategy(skillType)
        val buffList = this.attachEntity.buffMap[skillType]
        if (buffList != null) {
            for (buff in buffList) {
                if (buff != this) {
                    continue
                }
                buffList.remove(buff)
                this.attachEntity.buffMap[skillType] = buffList
                break
            }
        }
        strategy.onDetach(this)

        //添加buff分离日志
        val intParas = hashMapOf<Int, Int>()
        intParas[SkillId] = this.skillProto.id
        intParas[SkillEffId] = this.skillEffProto.id
        intParas[BuffId] = this.id
        this.attachEntity.manager.receiveLogRequest(
            this.comeFromEntity.id,
            this.attachEntity.id,
            LogDetachBuff,
            intParas
        )

        //buff移除后的处理
        val handlerList = this.defaultHandleMap[OnBuffDetach] ?: return
        for (handle in handlerList) {
            handle(this)
        }
    }

    //执行Buff
    fun runBuff(): Boolean {
        if (this.attachEntity.manager.currentTime >= this.nextTakeEffectTime) {
            val handlerList = this.defaultHandleMap[OnBuffHeart]
            if (handlerList != null) {
                for (handle in handlerList) {
                    handle(this)
                }
            }
        }
        return this.attachEntity.manager.currentTime < this.loseEffectTime
    }

    data class CheckConflictResult(
        var result: Int,
        var buff: Buff?
    )

    //检测buff冲突
    //0、不冲突 1、冲突，替换 2、冲突，无法替换
    fun checkConflict(): CheckConflictResult {
        if (this.skillEffProto.isFilter != 0) {
            //找到一个同类型的buff
            val sameBuffList = this.attachEntity.buffMap[this.skillEffProto.skillType]
            if (sameBuffList != null && sameBuffList.count() > 0) {
                when (this.skillEffProto.isFilter) {
                    BuffCover ->
                        return CheckConflictResult(ConflictAndReplace, sameBuffList[0])
                    BuffIgnore ->
                        return CheckConflictResult(ConflictAndNoReplace, null)
                    BuffCompareByTime -> {
                        if (sameBuffList[0].loseEffectTime > this.attachEntity.manager.currentTime + this.skillEffProto.auraHandler) {
                            return CheckConflictResult(ConflictAndNoReplace, null)
                        }
                        return CheckConflictResult(ConflictAndReplace, sameBuffList[0])
                    }
                }
            }
        }
        if (this.skillEffProto.isMerGe != 0) {
            var sameBuff: Buff? = null
            for ((_, buffList) in this.attachEntity.buffMap) {
                if (sameBuff != null) {
                    break
                }
                for (buff in buffList) {
                    if (buff.skillEffProto.skillType != this.skillEffProto.skillType) {
                        continue
                    }
                    if (buff.skillEffProto.isConduce != this.skillEffProto.isConduce) {
                        continue
                    }
                    if (buff.skillEffProto.skillEffBaseType != this.skillEffProto.skillEffBaseType) {
                        continue
                    }
                    if (buff.skillEffProto.isPer != this.skillEffProto.isPer) {
                        continue
                    }
                    sameBuff = buff
                    break
                }
            }
            if (sameBuff != null) {
                if (sameBuff.skillEffProto.skillEffBasePoint > this.skillEffProto.skillEffBasePoint) {
                    return CheckConflictResult(ConflictAndReplace, sameBuff)
                }
                return CheckConflictResult(ConflictAndNoReplace, null)
            }
        }
        return CheckConflictResult(NoConflict, null)
    }
}

class MoveLaunchSkillBuff(
    comeFromEntity: Entity,   //Buff来源的实体
    attachEntity: Entity,   //附加的实体
    skillProto: HeroSkillProto,    //技能配置
    skillEffProto: HeroSkillEffProto,//技能效果配置
    relatedId: Int  //关联Id
) : Buff(comeFromEntity, attachEntity, skillProto, skillEffProto, relatedId) {
    var controlEntity: Entity? = null//控制的实体
}

class FuhuoBuff(
    comeFromEntity: Entity,   //Buff来源的实体
    attachEntity: Entity,   //附加的实体
    skillProto: HeroSkillProto,    //技能配置
    skillEffProto: HeroSkillEffProto,//技能效果配置
    relatedId: Int  //关联Id
) : Buff(comeFromEntity, attachEntity, skillProto, skillEffProto, relatedId) {
    var leftReviveCount: Int = skillEffProto.onceEffect //剩余复活次数
    var reviveTime: Int = Int.MAX_VALUE //复活时间
}

class HudunBuff(
    comeFromEntity: Entity,   //Buff来源的实体
    attachEntity: Entity,   //附加的实体
    skillProto: HeroSkillProto,    //技能配置
    skillEffProto: HeroSkillEffProto,//技能效果配置
    relatedId: Int  //关联Id
) : Buff(comeFromEntity, attachEntity, skillProto, skillEffProto, relatedId) {
    var leftTakeInHurt: Int = skillEffProto.skillEffBasePoint.toInt()
}

class ChufaBuff(
    comeFromEntity: Entity,   //Buff来源的实体
    attachEntity: Entity,   //附加的实体
    skillProto: HeroSkillProto,    //技能配置
    skillEffProto: HeroSkillEffProto,//技能效果配置
    relatedId: Int  //关联Id
) : Buff(comeFromEntity, attachEntity, skillProto, skillEffProto, relatedId) {
    var coolOverTime: Int = Int.MAX_VALUE //冷却结束时间
}