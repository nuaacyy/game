package com.point18.slg2d.world.module.heroBattle1.fightLogic

import com.point18.slg2d.common.commonfunc.getRandInt
import xyz.ariane.util.lzWarn
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import java.util.*
import java.util.Arrays.asList

var finder = EntityFinder(
    hashMapOf()
)

data class FindRst(
    var mainEntity: Entity? = null,
    var targetEntityList: LinkedList<Entity> = LinkedList()
)

class EntityFinder(
    val filterMap: HashMap<Int, (entity: Entity, camp: Int, unitType: Int, special: Int, aoeRadius: Double, lineType: Int, lineLength: Double, repeat: Boolean, selectNum: Int) -> FindRst>
) {
    fun entityFinderInit() {
        this.filterMap[Self] = ::findSelf
        this.filterMap[AtkTarget] = ::findAtkTarget
        this.filterMap[AoeBySelf] = ::findAoeBySelf
        this.filterMap[AoeByAtkTarget] = ::findAoeByAtkTarget
        this.filterMap[PosByAtkTarget] = ::findSceneByTargetPos
        this.filterMap[PosBySelf] = ::findSceneBySelfPos
        this.filterMap[LineAoe] = ::findLineAoe
        this.filterMap[FullScreenAoe] = ::findFullScreenAoe
        this.filterMap[FanShapedAoe] = ::findFanShapedAoe
    }

    //查找普攻目标
    fun findNormalAtkTarget(entity: Entity): List<Entity> {
        val camp = checkCamp(entity, EnemyForce)
        return findAtkTarget(entity, camp, 0, 0, 0.0, 0, 0.0, false, 0).targetEntityList
    }

    //选择目标查找策略
    fun selectFindStrategy(putRange: Int): ((entity: Entity, camp: Int, unitType: Int, special: Int, aoeRadius: Double, lineType: Int, lineLength: Double, repeat: Boolean, selectNum: Int) -> FindRst)? {
        val strategy = this.filterMap[putRange]
        if (strategy == null) {
            normalLog.lzWarn { "该目标查找策略未注册:$putRange" }
            return null
        }
        return strategy
    }

    //选择自身
    fun findSelf(
        entity: Entity,
        camp: Int,
        unitType: Int,
        special: Int,
        aoeRadius: Double,
        lineType: Int,
        lineLength: Double,
        repeat: Boolean,
        selectNum: Int
    ): FindRst {
        val entityList = LinkedList<Entity>()
        entityList.add(entity)
        return FindRst(entity, entityList)
    }

    //选择普攻目标
    fun findAtkTarget(
        entity: Entity,
        camp: Int,
        unitType: Int,
        special: Int,
        aoeRadius: Double,
        lineType: Int,
        lineLength: Double,
        repeat: Boolean,
        selectNum: Int
    ): FindRst {
        var targetEntity: Entity? = null
        var minXDistance = Double.MAX_VALUE
        var minYDistance = Double.MIN_VALUE
        val juli = entity.getFloatProperty(ARR_GONGJIJULI)
        entity.manager.entityList.forEach {
            //过滤死亡目标
            if ((it.getIntProperty(Hp, false)) <= 0) {
                return@forEach
            }

            //过滤阵营
            if (!campFilter(entity, it, camp)) {
                return@forEach
            }

            //判断距离
            val xDistance = calXDistance(entity, it)
            val atkVolume = it.getFloatProperty(AtkVolume, false)
            if (xDistance > juli + atkVolume) {
                return@forEach
            }

            for ((_, buffList) in it.buffMap) {
                for (buff in buffList) {
                    val handlerList = buff.checkDistanceHandleMap[OnCheckDistance]
                        ?: continue

                    for (handle in handlerList) {
                        if (handle(buff, juli)) {
                            return@forEach
                        }
                    }
                }
            }

            //选择最近的
            if (xDistance - atkVolume > minXDistance) {
                return@forEach
            }
            val yDistance = calYDistance(entity, it)
            if (xDistance - atkVolume == minXDistance && yDistance > minYDistance) {
                return@forEach
            }
            minXDistance = xDistance - atkVolume
            minYDistance = yDistance
            targetEntity = it
        }

        val findEntityList = LinkedList<Entity>()
        val te = targetEntity
        if (te != null) {
            findEntityList.add(te)
        }
        return FindRst(targetEntity, findEntityList)
    }

    //选择已自身范围内的目标
    fun findAoeBySelf(
        entity: Entity,
        camp: Int,
        unitType: Int,
        special: Int,
        aoeRadius: Double,
        lineType: Int,
        lineLength: Double,
        repeat: Boolean,
        selectNum: Int
    ): FindRst {
        val checkedCamp = checkCamp(entity, camp)

        var targetEntityList = LinkedList<Entity>()
        entity.manager.entityList.forEach {
            //过滤死亡目标
            if ((it.getIntProperty(Hp, false)) <= 0) {
                return@forEach
            }

            //过滤阵营
            if (!campFilter(entity, it, checkedCamp)) {
                return@forEach
            }

            //目标阵营筛选
            if (unitType == 1 && it.intPropertyMap[UnitType] != Hero) {
                return@forEach
            }
            if (unitType == 2 && it.intPropertyMap[UnitType] != Summoned) {
                return@forEach
            }

            //过滤范围
            val distance = calDistance(entity, it)
            val atkVolume = it.getFloatProperty(AtkVolume, false)
            if (distance > aoeRadius + atkVolume) {
                return@forEach
            }

            targetEntityList.add(it)
        }

        if (targetEntityList.isEmpty()) {
            return FindRst(entity, targetEntityList)
        }

        targetEntityList = filterBySpecial(entity, targetEntityList, special)

        return FindRst(entity, filterBySelectNum(targetEntityList, selectNum, repeat))
    }

    //选择已攻击目标范围内的目标
    fun findAoeByAtkTarget(
        entity: Entity,
        camp: Int,
        unitType: Int,
        special: Int,
        aoeRadius: Double,
        lineType: Int,
        lineLength: Double,
        repeat: Boolean,
        selectNum: Int
    ): FindRst {
        val atkTargets = finder.findNormalAtkTarget(entity)
        if (atkTargets.count() == 0) {
            return FindRst()
        }
        val checkedCamp = checkCamp(entity, camp)

        val atkEntity = atkTargets[0]
        var targetEntityList = LinkedList<Entity>()
        entity.manager.entityList.forEach {
            //过滤死亡目标
            if ((it.getIntProperty(Hp, false)) <= 0) {
                return@forEach
            }

            //过滤阵营
            if (!campFilter(entity, it, checkedCamp)) {
                return@forEach
            }

            //目标类型筛选
            if (unitType == 1 && it.intPropertyMap[UnitType] != Hero) {
                return@forEach
            }
            if (unitType == 2 && it.intPropertyMap[UnitType] != Summoned) {
                return@forEach
            }

            //过滤范围
            val distance = calDistance(atkEntity, it)
            val atkVolume = it.getFloatProperty(AtkVolume, false)
            if (distance > aoeRadius + atkVolume) {
                return@forEach
            }

            targetEntityList.add(it)
        }

        if (targetEntityList.isEmpty()) {
            return FindRst(atkEntity, targetEntityList)
        }

        targetEntityList = filterBySpecial(entity, targetEntityList, special)

        return FindRst(atkEntity, filterBySelectNum(targetEntityList, selectNum, repeat))
    }

    //查找自身为坐标的地
    fun findSceneBySelfPos(
        entity: Entity,
        camp: Int,
        unitType: Int,
        special: Int,
        aoeRadius: Double,
        lineType: Int,
        lineLength: Double,
        repeat: Boolean,
        selectNum: Int
    ): FindRst {
        val sceneEntity = entity.clone()
        sceneEntity.entityType = Skill
        sceneEntity.intPropertyMap[UnitType] = Skill
        entity.manager.pushEntity(sceneEntity)
        return FindRst(entity, LinkedList(asList(sceneEntity)))
    }

    //查找目标为坐标的地
    fun findSceneByTargetPos(
        entity: Entity,
        camp: Int,
        unitType: Int,
        special: Int,
        aoeRadius: Double,
        lineType: Int,
        lineLength: Double,
        repeat: Boolean,
        selectNum: Int
    ): FindRst {
        val findEntityList = finder.findNormalAtkTarget(entity)
        if (findEntityList.count() == 0) {
            return FindRst()
        }
        val sceneEntity = entity.clone()
        sceneEntity.entityType = Skill
        sceneEntity.intPropertyMap[UnitType] = Skill
        sceneEntity.floatPropertyMap[PosX] = findEntityList[0].getFloatProperty(PosX, false)
        sceneEntity.floatPropertyMap[PosY] = findEntityList[0].getFloatProperty(PosY, false)
        entity.manager.pushEntity(sceneEntity)
        return FindRst(findEntityList[0], LinkedList(asList(sceneEntity)))
    }

    //查找线型Aoe
    fun findLineAoe(
        entity: Entity,
        camp: Int,
        unitType: Int,
        special: Int,
        aoeRadius: Double,
        lineType: Int,
        lineLength: Double,
        repeat: Boolean,
        selectNum: Int
    ): FindRst {
        val findEntityList = finder.findNormalAtkTarget(entity)
        if (findEntityList.count() == 0) {
            return FindRst()
        }
        val checkedCamp = checkCamp(entity, camp)

        val atkEntity = findEntityList[0]
        var targetEntityList = LinkedList<Entity>()
        val fromX = entity.getFloatProperty(PosX, false)
        val fromY = entity.getFloatProperty(PosY, false)
        val toX = atkEntity.getFloatProperty(PosX, false)
        val toY = atkEntity.getFloatProperty(PosY, false)
        entity.manager.entityList.forEach {
            //过滤死亡目标
            if ((it.getIntProperty(Hp, false)) <= 0) {
                return@forEach
            }

            //过滤阵营
            if (!campFilter(entity, it, checkedCamp)) {
                return@forEach
            }

            //目标类型筛选
            if (unitType == 1 && it.intPropertyMap[UnitType] != Hero) {
                return@forEach
            }
            if (unitType == 2 && it.intPropertyMap[UnitType] != Summoned) {
                return@forEach
            }

            //过滤范围
            if (!checkInLine(
                    fromX,
                    fromY,
                    toX,
                    toY,
                    aoeRadius,
                    lineType,
                    lineLength,
                    it.getFloatProperty(PosX, false),
                    it.getFloatProperty(PosY, false)
                )
            ) {
                return@forEach
            }
            targetEntityList.add(it)
        }

        if (targetEntityList.isEmpty()) {
            return FindRst(entity, targetEntityList)
        }

        targetEntityList = filterBySpecial(entity, targetEntityList, special)

        return FindRst(atkEntity, filterBySelectNum(targetEntityList, selectNum, repeat))
    }

    //全屏aoe
    fun findFullScreenAoe(
        entity: Entity,
        camp: Int,
        unitType: Int,
        special: Int,
        aoeRadius: Double,
        lineType: Int,
        lineLength: Double,
        repeat: Boolean,
        selectNum: Int
    ): FindRst {
        val checkedCamp = checkCamp(entity, camp)
        var targetEntityList = LinkedList<Entity>()
        entity.manager.entityList.forEach {
            //过滤死亡目标
            if ((it.getIntProperty(Hp, false)) <= 0) {
                return@forEach
            }

            //过滤阵营
            if (!campFilter(entity, it, checkedCamp)) {
                return@forEach
            }

            //目标类型筛选
            if (unitType == 1 && it.intPropertyMap[UnitType] != Hero) {
                return@forEach
            }
            if (unitType == 2 && it.intPropertyMap[UnitType] != Summoned) {
                return@forEach
            }

            targetEntityList.add(it)
        }

        if (targetEntityList.isEmpty()) {
            return FindRst(entity, targetEntityList)
        }

        targetEntityList = filterBySpecial(entity, targetEntityList, special)

        return FindRst(entity, filterBySelectNum(targetEntityList, selectNum, repeat))
    }

    //扇形Aoe
    fun findFanShapedAoe(
        entity: Entity,
        camp: Int,
        unitType: Int,
        special: Int,
        aoeRadius: Double,
        lineType: Int,
        lineLength: Double,
        repeat: Boolean,
        selectNum: Int
    ): FindRst {
        val findEntityList = finder.findNormalAtkTarget(entity)
        if (findEntityList.count() == 0) {
            return FindRst()
        }
        val checkedCamp = checkCamp(entity, camp)

        val atkEntity = findEntityList[0]
        var targetEntityList = LinkedList<Entity>()
        val fromX = entity.getFloatProperty(PosX, false)
        val fromY = entity.getFloatProperty(PosY, false)
        val toX = atkEntity.getFloatProperty(PosX, false)
        val toY = atkEntity.getFloatProperty(PosY, false)
        entity.manager.entityList.forEach {
            //过滤死亡目标
            if ((it.getIntProperty(Hp, false)) <= 0) {
                return@forEach
            }

            //过滤阵营
            if (!campFilter(entity, it, checkedCamp)) {
                return@forEach
            }

            //目标类型筛选
            if (unitType == 1 && it.intPropertyMap[UnitType] != Hero) {
                return@forEach
            }
            if (unitType == 2 && it.intPropertyMap[UnitType] != Summoned) {
                return@forEach
            }

            //过滤范围
            if (!checkInFanShaped(
                    fromX,
                    fromY,
                    toX,
                    toY,
                    aoeRadius,
                    lineLength,
                    it.getFloatProperty(PosX, false),
                    it.getFloatProperty(PosY, false)
                )
            ) {
                return@forEach
            }
            targetEntityList.add(it)
        }

        if (targetEntityList.isEmpty()) {
            return FindRst(entity, targetEntityList)
        }

        targetEntityList = filterBySpecial(entity, targetEntityList, special)

        return FindRst(atkEntity, filterBySelectNum(targetEntityList, selectNum, repeat))
    }

    fun findByRange(
        entity: Entity,
        camp: Int,
        range: Double
    ): Entity? {
        val checkedCamp = checkCamp(entity, camp)
        var targetEntity: Entity? = null
        var minXDistance = Double.MAX_VALUE
        var minYDistance = Double.MAX_VALUE
        entity.manager.entityList.forEach {
            //过滤死亡目标
            if ((it.getIntProperty(Hp, false)) <= 0) {
                return@forEach
            }

            //过滤阵营
            if (!campFilter(entity, it, checkedCamp)) {
                return@forEach
            }

            //判断距离
            val xDistance = calXDistance(entity, it)
            val atkVolume = it.getFloatProperty(AtkVolume, false)
            if (xDistance > range + atkVolume) {
                return@forEach
            }

            //选择最近的
            if (xDistance - atkVolume > minXDistance) {
                return@forEach
            }
            val yDistance = calYDistance(entity, it)
            if (xDistance - atkVolume == minXDistance && yDistance > minYDistance) {
                return@forEach
            }

            minXDistance = xDistance - atkVolume
            minYDistance = yDistance
            targetEntity = it
        }
        return targetEntity
    }

    //特殊效果筛选
    fun filterBySpecial(entity: Entity, targetEntityList: LinkedList<Entity>, special: Int): LinkedList<Entity> {
        if (special == 0) {
            return targetEntityList
        }
        if (targetEntityList.isEmpty()) {
            return targetEntityList
        }

        var targetEntity = targetEntityList[0]
        for (i in 1 until targetEntityList.count()) {
            when (special) {
                HpLowest -> {
                    if ((targetEntity.getIntProperty(Hp, false)) < (targetEntityList[i].intPropertyMap[Hp]
                            ?: 0)
                    ) {
                        targetEntity = targetEntityList[i]
                    }
                }
                HpHighest -> {
                    if ((targetEntity.getIntProperty(Hp, false)) > (targetEntityList[i].intPropertyMap[Hp]
                            ?: 0)
                    ) {
                        targetEntity = targetEntityList[i]
                    }
                }
                Farest -> {
                    val distance1 = calDistance(entity, targetEntity)
                    val atkVolume1 = targetEntity.getFloatProperty(AtkVolume, false)
                    val distance2 = calDistance(entity, targetEntityList[i])
                    val atkVolume2 = targetEntityList[i].getFloatProperty(AtkVolume, false)
                    if (distance1 - atkVolume1 < distance2 - atkVolume2) {
                        targetEntity = targetEntityList[i]
                    }
                }
                Nearest -> {
                    val distance1 = calDistance(entity, targetEntity)
                    val atkVolume1 = targetEntity.getFloatProperty(AtkVolume, false)
                    val distance2 = calDistance(entity, targetEntityList[i])
                    val atkVolume2 = targetEntityList[i].getFloatProperty(AtkVolume, false)
                    if (distance1 - atkVolume1 > distance2 - atkVolume2) {
                        targetEntity = targetEntityList[i]
                    }
                }
                MoraleLowest -> {
                    if (targetEntity.getFloatProperty(Morale, false) < targetEntityList[i].getFloatProperty(
                            Morale, false)
                    ) {
                        targetEntity = targetEntityList[i]
                    }
                }
                MoraleHighest -> {
                    if (targetEntity.getFloatProperty(Morale, false) > targetEntityList[i].getFloatProperty(
                            Morale, false)
                    ) {
                        targetEntity = targetEntityList[i]
                    }
                }
            }
        }
        return LinkedList(asList(targetEntity))
    }

    //数量筛选
    fun filterBySelectNum(targetEntityList: LinkedList<Entity>, selectNum: Int, repeat: Boolean): LinkedList<Entity> {
        if (selectNum == 0) {
            return targetEntityList
        }
        val finalEntityList = LinkedList<Entity>()
        for (i in 0 until selectNum) {
            if (targetEntityList.count() == 0) {
                break
            }
            val ranIndex = getRandInt(targetEntityList.count())
            finalEntityList.add(targetEntityList[ranIndex])
            if (!repeat) {
                targetEntityList.removeAt(ranIndex)
            }
        }
        return finalEntityList
    }

    //过滤阵营
    fun campFilter(entity: Entity, it: Entity, camp: Int): Boolean {
        //过滤阵营
        when (camp) {
            FriendlyForce -> {
                if (it.getIntProperty(FightSide, false) != entity.getIntProperty(FightSide, false)) {
                    return false
                }
            }
            FriendlyForceExceptSelf -> {
                if (it.getIntProperty(FightSide, false) != entity.getIntProperty(FightSide, false)) {
                    return false
                }
                if (entity.id == it.id) {
                    return false
                }
            }
            EnemyForce -> {
                if (it.getIntProperty(FightSide, false) == entity.getIntProperty(FightSide, false)) {
                    return false
                }
            }
            SelfForce -> {
                if (entity.id != it.id && it.intPropertyMap[AttachHeroId] != entity.id) {
                    return false
                }
            }
        }
        return true
    }
}

//检测阵营
fun checkCamp(entity: Entity, camp: Int): Int {
    //检查buff状态
    for ((_, buffList) in entity.buffMap) {
        for (buff in buffList) {
            val handlerList = buff.checkHandleMap[OnSelectTarget] ?: continue

            for (handle in handlerList) {
                if (!handle(buff)) {
                    continue
                }
                return NoDistinguish
            }
        }
    }
    return camp
}
