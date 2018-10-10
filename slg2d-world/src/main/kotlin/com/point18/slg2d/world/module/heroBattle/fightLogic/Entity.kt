package com.point18.slg2d.world.module.heroBattle.fightLogic

import com.point18.slg2d.common.constg.*
import java.util.*

data class AtkEntityInfo(
    val entity: Entity,
    val atkIndex: Int
)

class Entity(
    var id: Int,            //唯一Id
    var entityType: Int,            //实体类型
    var name: String,          //名称（调试用）
    var manager: EntityManager  //实体管理器
) {
    val intPropertyMap: HashMap<Int, Int> = hashMapOf()     //int属性
    val floatPropertyMap: HashMap<Int, Double> = hashMapOf() //float属性
    val arrayPropertyMap: HashMap<Int, LinkedList<Int>> = hashMapOf()   //[]int属性
    var atkLaunchTime: Int = 0     //攻击/技能发动时间
    var actionEndTime: Int = 0     //行为(移动/攻击/技能/飞行)结束时间
    var atkTargetId: Int = 0       //攻击目标Id
    var atkIndex: Int = 0          //当前攻击标志，每次攻击+1
    var actionIndex: Int = 0       //行为标志 每次行为+1
    var currentState: Int = 0      //当前状态
    var flySpeed: Double = 0.0        //飞行速度
    var flyTargetX: Double = 0.0      //飞行目的地X
    var flyTargetY: Double = 0.0      //飞行目的地Y
    var launchingSkill: Skill? = null                 // 发动中的技能
    var aStarPath: LinkedList<MapNode> = LinkedList()
    val buffMap: HashMap<Int, LinkedList<Buff>> = hashMapOf()        // 身上的buff列表
    val skillEffMap: HashMap<Int, LinkedList<SkillEffect>> = hashMapOf() // 待生效的技能效果列表
    val beAtkMap: HashMap<Int, LinkedList<AtkEntityInfo>> = hashMapOf() // 待生效的普攻
    var launchUniqueSkill = true //发动大招的标记，默认是一直发动，客户端通过该字段控制何时发动大招
    var resetLaunchUniqueSkillFlag = false //成功发动大招后，是否重置发动大招标记
    var gridX: Int = 0
    var gridY: Int = 0

    //设置格子
    fun setGrid(newGridX: Int, newGridY: Int) {
        gridX = newGridX
        gridY = newGridY
        changeFloatProperty(PosX, newGridX.toDouble())
        changeFloatProperty(PosY, newGridY.toDouble())
    }

    //设置位置
    fun setPos(newPosX: Double, newPosY: Double) {
        gridX = newPosX.toInt()
        gridY = newPosY.toInt()
        this.floatPropertyMap[PosX] = newPosX
        this.floatPropertyMap[PosY] = newPosY
    }

    //运行效果
    fun execute() {
        // 运行buff
        for ((_, buffList) in this.buffMap) {
            var toDetachBuffList: LinkedList<Buff>? = null
            for (buff in buffList) {
                if (!buff.runBuff()) {
                    if (toDetachBuffList == null) {
                        toDetachBuffList = LinkedList()
                    }
                    toDetachBuffList.add(buff)
                }
            }

            if (toDetachBuffList != null) {
                for (detachBuff in toDetachBuffList) {
                    detachBuff.detach()
                }
            }
        }

        // 运行普攻效果
        var toDelBeAtk: HashMap<Int, Int>? = null
        for ((takeEffTime, entityList) in this.beAtkMap) {
            if (takeEffTime > this.manager.currentTime) {
                continue
            }
            if (toDelBeAtk == null) {
                toDelBeAtk = hashMapOf()
            }
            toDelBeAtk[takeEffTime] = 1
            for (et in entityList) {
                takeNormalAtk(et.entity, et.atkIndex, this)
            }
        }
        if (toDelBeAtk != null) {
            for ((takeEffTime, _) in toDelBeAtk) {
                this.beAtkMap.remove(takeEffTime)
            }
        }

        // 运行技能效果
        var toDelEff: HashMap<Int, Int>? = null
        for ((takeEffTime, effList) in this.skillEffMap) {
            if (takeEffTime > this.manager.currentTime) {
                continue
            }

            if (toDelEff == null) {
                toDelEff = hashMapOf()
            }
            toDelEff[takeEffTime] = 1
            for (eff in effList) {
                eff.takeSkillEffect(this)
            }
        }
        if (toDelEff != null) {
            for ((takeEffTime, _) in toDelEff) {
                this.skillEffMap.remove(takeEffTime)
            }
        }
    }

    //获取buff修改后的整数属性
    fun getIntProperty(key: Int, buffed: Boolean = true): Int {
        val value = this.intPropertyMap[key] ?: return 0
        if (!buffed) {
            return value
        }
        val buffList = this.buffMap[SKILL_SHUXINGBIANHUAN] ?: return value
        var changeVal = 0.0
        for (buff in buffList) {
            val propertyType = buff.skillEffProto.skillEffBaseType
            if (key != propertyType) {
                continue
            }
            if (buff.skillEffProto.isPer == TRUE) {
                changeVal += value * buff.skillEffProto.skillEffBasePoint / 10000
            } else {
                changeVal += buff.skillEffProto.skillEffBasePoint
            }
        }
        return value + Math.ceil(changeVal).toInt()
    }

    //获取buff修改后的浮点属性
    fun getFloatProperty(key: Int, buffed: Boolean = true): Double {
        val value = this.floatPropertyMap[key] ?: return 0.0
        if (!buffed) {
            return value
        }
        val buffList = this.buffMap[SKILL_SHUXINGBIANHUAN] ?: return value
        var changeVal = value
        for (buff in buffList) {
            val propertyType = buff.skillEffProto.skillEffBaseType
            if (key != propertyType) {
                continue
            }
            changeVal += if (buff.skillEffProto.isPer == TRUE) {
                value * buff.skillEffProto.skillEffBasePoint / 10000
            } else {
                value + buff.skillEffProto.skillEffBasePoint
            }
        }
        if (changeVal < 0) {
            return 0.0
        }
        return changeVal
    }

    //修改属性
    fun changeIntProperty(key: Int, changeValue: Int) {
        val oldValue = this.intPropertyMap[key]
        if (oldValue == changeValue) {
            return
        }
        this.intPropertyMap[key] = changeValue
        onIntLogPropertyChange(this, key)
    }

    //修改属性
    fun changeFloatProperty(key: Int, changeValue: Double) {
        val oldValue = this.floatPropertyMap[key]
        if (oldValue == changeValue) {
            return
        }
        this.floatPropertyMap[key] = changeValue
        onFloatLogPropertyChange(this, key)
    }

    //改变实体状态
    fun changeState(newState: Int) {
        val oldState = this.currentState
        this.currentState = newState
        onStateChange(this, oldState, newState)
    }

    //打断
    fun interrupt(vararg checkStates: Int) {
        for (state in checkStates) {
            if (state != this.currentState) {
                continue
            }
            when (state) {
                MoveState -> {
                    this.changeState(NullState)
                }
                AttackState -> {
                    //重置技攻击发动时间
                    this.atkLaunchTime = 0
                    //重置攻击结束时间
                    this.actionEndTime = 0
                    //设置当前状态
                    this.changeState(NullState)
                }
                SkillState -> {
                    val currentSkill = this.launchingSkill
                    if (currentSkill == null || currentSkill.skillProto.canStop == 0) {
                        this.launchingSkill = null
                        this.atkLaunchTime = 0
                        this.actionEndTime = 0
                        this.changeState(NullState)
                    }
                }
            }
            break
        }
    }

    //拷贝
    fun clone(): Entity {
        val cloneEntity = Entity(
            this.manager.genId(),
            this.entityType,
            "",
            this.manager
        )
        //拷贝一份属性数据
        for ((k, _) in this.intPropertyMap) {
            cloneEntity.intPropertyMap[k] = this.getIntProperty(k)
        }
        for ((k, _) in this.floatPropertyMap) {
            cloneEntity.floatPropertyMap[k] = this.getFloatProperty(k)
        }
        return cloneEntity
    }

    fun addBuff(buff: Buff) {
        val skillType = buff.skillEffProto.skillType
        val buffList = this.buffMap.getOrPut(skillType) { LinkedList() }
        buffList.add(buff)
        this.buffMap[skillType] = buffList
    }
}

fun newEntity(manager: EntityManager, entityType: Int): Entity {
    val id = manager.genId()
    val entity = Entity(
        id,
        entityType,
        "",
        manager
    )

    manager.pushEntity(entity)
    return entity
}

