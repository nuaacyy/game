package com.point18.slg2d.world.module.heroBattle.fightLogic

import com.point18.slg2d.common.constg.*

fun takeNormalAtk(entity: Entity, atkIndex: Int, targetEntity: Entity) {
    val targetHp = targetEntity.getIntProperty(Hp, false)
    if (targetHp <= 0) {
        //受击方已死，则不进行受到普攻判定
        return
    }

    //受到普攻伤害前的处理
    for ((_, buffList) in targetEntity.buffMap) {
        for (buff in buffList) {
            val handlerList = buff.checkHandleMap[OnBeforeHurt] ?: continue

            for (handle in handlerList) {
                if (!handle(buff)) {
                    return
                }
            }
        }
    }

    val rst = calAttackHurt(entity, targetEntity)

    var costHp = rst.hurt


    //造成伤害加深
    for ((_, buffList) in entity.buffMap) {
        for (buff in buffList) {
            val handlerList = buff.beHurtHandleMap[OnHurt] ?: continue

            for (handle in handlerList) {
                costHp = handle(buff, entity, costHp)
            }
        }
    }

    //受伤害加深
    for ((_, buffList) in targetEntity.buffMap) {
        for (buff in buffList) {
            val handlerList = buff.beHurtHandleMap[OnBeHurt1] ?: continue

            for (handle in handlerList) {
                costHp = handle(buff, entity, costHp)
            }
        }
    }

    //护盾
    for ((_, buffList) in targetEntity.buffMap) {
        for (buff in buffList) {
            val handlerList = buff.beHurtHandleMap[OnBeHurt2] ?: continue

            for (handle in handlerList) {
                costHp = handle(buff, entity, costHp)
            }
        }
    }

    //共苦
    for ((_, buffList) in targetEntity.buffMap) {
        for (buff in buffList) {
            val handlerList = buff.beHurtHandleMap[OnBeHurt3] ?: continue

            for (handle in handlerList) {
                costHp = handle(buff, entity, costHp)
            }
        }
    }

    var realCostHp = costHp
    if (realCostHp > targetHp) {
        realCostHp = targetHp
    }

    //扣血
    targetEntity.changeIntProperty(Hp, targetHp - realCostHp)

    //普攻回血
    val atkAddHp = entity.getIntProperty(ARR_ATKADDHP)
    var addHp = atkAddHp
    val hpLimit = entity.getIntProperty(ARR_HPLIMIT)
    val hp = entity.getIntProperty(Hp, false)
    if (hp >= hpLimit) {
        addHp = 0
    }
    if (addHp > 0) {
        if (addHp + hp > hpLimit) {
            addHp = hpLimit - hp
        }

        //加血
        entity.changeIntProperty(Hp, hp + addHp)
    }

    //普通回气
    val atkAddMorale = entity.getFloatProperty(ARR_ATKADDMORALE)
    var addMorale = atkAddMorale
    var morale = entity.getFloatProperty(Morale, false)
    val moraleLimit = entity.getFloatProperty(MoraleLimit, false)
    if (morale >= moraleLimit) {
        addMorale = 0.0
    }
    if (addMorale > 0) {
        if (addMorale + morale > moraleLimit) {
            addMorale = moraleLimit - morale
        }
        //加士气
        entity.changeFloatProperty(Morale, morale + addMorale)
    }

    morale = entity.getFloatProperty(Morale, false)

    var atkKillAddMorale = 0.0
    var killAddMorale = 0.0
    if (targetHp == realCostHp) {
        atkKillAddMorale = entity.getFloatProperty(KillAddMorale, false)
        //击杀回气
        killAddMorale = atkKillAddMorale
        if (morale >= moraleLimit) {
            killAddMorale = 0.0
        }
        if (killAddMorale > 0) {
            if (killAddMorale + morale > moraleLimit) {
                killAddMorale = moraleLimit - morale
            }
            //加士气
            entity.changeFloatProperty(Morale, morale + killAddMorale)
        }
    }

    //扣血日志请求
    val intParas = hashMapOf<Int, Int>()
    val floatParas = hashMapOf<Int, Double>()
    intParas[IsDodge] = boolToInt(rst.isDodge)
    intParas[IsCrit] = boolToInt(rst.isCrit)
    intParas[CostHp] = costHp
    intParas[AddHp] = atkAddHp
    intParas[AtkIndex] = atkIndex
    floatParas[AddMorale] = atkAddMorale + atkKillAddMorale
    entity.manager.receiveLogRequest(entity.id, targetEntity.id, LogAttack, intParas, floatParas)

    //受到普攻伤害后的处理（伤害反弹）
    if ((entity.getIntProperty(Hp, false)) <= 0) {
        //攻击方已死亡不用判定
        return
    }
    for ((_, buffList) in targetEntity.buffMap) {
        for (buff in buffList) {
            val handlerList = buff.afterHurtHandleMap[OnAfterHurt] ?: continue

            for (handle in handlerList) {
                handle(entity, buff, realCostHp)
            }
        }
    }
}