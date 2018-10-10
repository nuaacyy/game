package com.point18.slg2d.world.module.heroBattle.fightLogic

import com.point18.slg2d.common.constg.ARR_INTERVAL
import com.point18.slg2d.common.constg.LogLaunchSkill
import com.point18.slg2d.common.constg.LogSingSkill
import com.point18.slg2d.common.constg.SkillId
import com.point18.slg2d.common.pc.pcs

class Skill(
    var entity: Entity,
    var skillProto: com.point18.slg2d.common.pc.HeroSkillProto
) {

    var launchedCount: Int = 0     //发动次数
    val takeEffectMap: HashMap<Int, HashMap<Int, Int>> = hashMapOf() //效果Id-实体Id

    //吟唱
    fun sing() {
        this.entity.launchingSkill = this

        //发动的时间
        this.entity.atkLaunchTime = this.entity.manager.currentTime + this.skillProto.castAnimation
        this.entity.actionEndTime = this.entity.manager.currentTime + this.skillProto.lastTime
        if (Math.ceil(this.entity.getFloatProperty(ARR_INTERVAL) * 1000.0).toInt() > this.skillProto.lastTime) {
            this.entity.actionEndTime = this.entity.manager.currentTime +
                    Math.ceil(this.entity.getFloatProperty(ARR_INTERVAL) * 1000.0).toInt()
        }

        //添加吟唱技能日志
        val intParas = hashMapOf<Int, Int>()
        intParas[SkillId] = this.skillProto.id
        this.entity.manager.receiveLogRequest(this.entity.id, this.entity.id, LogSingSkill, intParas, null)

        //判断是否立即发动
        if (this.skillProto.castAnimation > 0) {
            return
        }
        launch()
    }

    //发动
    fun launch() {
        this.launchedCount++

        if (this.launchedCount == 1) {
            //第一次发动添加技能发动日志
            val intParas = hashMapOf<Int, Int>()
            intParas[SkillId] = this.skillProto.id
            this.entity.manager.receiveLogRequest(this.entity.id, this.entity.id, LogLaunchSkill, intParas, null)
        }

        //吟唱结束，附加技能效果到目标对象上
        this.takeSkillEffect()
        if (this.launchedCount >= this.skillProto.singNum) {
            //this.entity.launchingSkill = nil
            this.entity.atkLaunchTime = Int.MAX_VALUE
            return
        }

        //计算下次释放效果的时机，并加入待释放技能队列中
        val nextLaunchTime = this.entity.manager.currentTime + this.skillProto.singTime
        this.entity.atkLaunchTime = nextLaunchTime
    }

    //执行技能效果
    fun takeSkillEffect() {
        for (skillEffId in this.skillProto.skillEffList) {
            val skillEffectProto = pcs.heroSkillEffProtoCache.heroSkillEffMap[skillEffId] ?: continue

            val skillEff = SkillEffect(this.entity, this.skillProto, skillEffectProto)
            skillEff.launchSkillEffect()
        }
    }
}