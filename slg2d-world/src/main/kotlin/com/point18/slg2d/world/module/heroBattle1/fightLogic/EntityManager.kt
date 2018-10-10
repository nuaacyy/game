package com.point18.slg2d.world.module.heroBattle1.fightLogic

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.module.heroBattle1.behaviorTree.base.BNode
import com.point18.slg2d.world.module.heroBattle1.behaviorTree.node.ConditionNode
import com.point18.slg2d.world.module.heroBattle1.behaviorTree.node.SelectorNode
import com.point18.slg2d.world.module.heroBattle1.behaviorTree.node.SequenceNode
import com.point18.slg2d.world.module.heroBattle1.fightLogic.actionNode.*
import com.point18.slg2d.world.module.heroBattle1.fightLogic.conditionNode.*
import pb4client.*
import com.point18.slg2d.common.pc.pcs
import java.util.*

//实体管理器，同时承担记录战报的工作
class EntityManager(
    var areaCache: AreaCache,
    val battleTime: Int
) {
    var currentTime: Int = 0 //当前是时间
    var atkHeroNum: Int = 0
    var defHeroNum: Int = 0
    var idIndex: Int = 0 //id标识，用于生成战斗中的唯一Id
    val obstruct = Obstruct()
    private val scenePointMap: HashMap<Int, Entity> = hashMapOf()
    val entityMap: HashMap<Int, Entity> = hashMapOf()
    val entityList: LinkedList<Entity> = LinkedList()
    val requestRecord: LinkedList<Any> = LinkedList()
    val recorder: HeroFightRecorder = HeroFightRecorder()
    val cacheChangeProperty: HashMap<Int, HashMap<Int, HashSet<Int>>> =
        hashMapOf()   //当前帧变化的属性缓存，EntityId-PropertyType(0:Int 1:Double)-PropertyId

    private val heroDecisionTree = initHeroDecisionTree()   //英雄对象决策树
    private val skillDecisionTree = initSkillDecisionTree() //技能对象决策树

    //生成唯一Id
    fun genId(): Int {
        return ++this.idIndex
    }

    //放入实体对象
    fun pushEntity(entity: Entity) {
        if (entity.entityType == Skill) {
            this.scenePointMap[entity.id] = entity
            return
        }

        this.entityMap[entity.id] = entity
        this.entityList.add(entity)
    }

    fun init() {
        for (entity in this.entityList) {
            if (entity.entityType != Hero) {
                continue
            }

            // 统计攻防英雄数
            if (entity.getIntProperty(
                    FightSide,
                    false
                ) == ATK_SIDE
            ) {
                this.atkHeroNum++
            } else {
                this.defHeroNum++
            }

            // 设置阻挡
            obstruct.updateObstruct(entity, entity.gridX, entity.gridY)
        }

        // 排序
        this.entityList.sortWith(Comparator { o1, o2 ->
            if (o1 == null) {
                return@Comparator 1
            }
            if (o2 == null) {
                return@Comparator -1
            }
            if (Math.abs(o1.gridY - pcs.basicProtoCache.mapGridY / 2) > Math.abs(o2.gridY - pcs.basicProtoCache.mapGridY / 2)) {
                return@Comparator 1
            }
            if (Math.abs(o1.gridY - pcs.basicProtoCache.mapGridY / 2) < Math.abs(o2.gridY - pcs.basicProtoCache.mapGridY / 2)) {
                return@Comparator -1
            }
            if (Math.abs(o1.gridX - pcs.basicProtoCache.mapGridX / 2) > Math.abs(o2.gridX - pcs.basicProtoCache.mapGridX / 2)) {
                return@Comparator 1
            }
            if (Math.abs(o1.gridX - pcs.basicProtoCache.mapGridX / 2) < Math.abs(o2.gridX - pcs.basicProtoCache.mapGridX / 2)) {
                return@Comparator -1
            }
            return@Comparator 0
        })
    }

    // 逻辑帧更新
    fun update(): Int {
        // 递增时间
        this.currentTime += UpdateTime

        // 处理场景对象
        val toDelPoint = hashMapOf<Int, Int>()
        for ((id, entity) in this.scenePointMap) {
            entity.execute()

            skillDecisionTree.execute(entity)

            val aliveTime = entity.getIntProperty(AliveTime, false)
            if (aliveTime == 0 || aliveTime > this.currentTime) {
                continue
            }

            toDelPoint[id] = 1
        }
        for ((id, _) in toDelPoint) {
            this.scenePointMap.remove(id)
        }

        //处理战斗逻辑对象
        for (i in 0..(this.entityList.count() - 1)) {
            val entity = this.entityList[i]
            if (this.atkHeroNum <= 0) {
                this.onFinish(FIGHT_RESULT_LOSE)
                return FIGHT_RESULT_LOSE
            }
            if (this.defHeroNum <= 0) {
                this.onFinish(FIGHT_RESULT_WIN)
                return FIGHT_RESULT_WIN
            }
            val aliveTime = entity.getIntProperty(AliveTime, false)
            if (aliveTime != 0 && aliveTime <= this.currentTime) {
                entity.changeIntProperty(Hp, 0)
            }

            entity.execute()

            // 检查死亡状态
            if (entity.getIntProperty(Hp, false) <= 0) {
                if (entity.currentState != DieState && entity.currentState != ReviveState) {
                    entity.changeState(DieState)
                }
                continue
            }

//            entity.update()
            heroDecisionTree.execute(entity)
        }

        if (this.cacheChangeProperty.isNotEmpty()) {
            this.cacheChangeProperty.forEach { cacheChange ->
                val entity = this.entityMap[cacheChange.key] ?: return@forEach
                val intParaChange = cacheChange.value[0]
                val floatParaChange = cacheChange.value[1]
                var intParas: HashMap<Int, Int>? = null
                var floatParas: HashMap<Int, Double>? = null
                if (intParaChange != null) {
                    intParas = hashMapOf()
                    intParaChange.forEach { intParas[it] = entity.getIntProperty(it) }
                }
                if (floatParaChange != null) {
                    floatParas = hashMapOf()
                    floatParaChange.forEach { floatParas[it] = entity.getFloatProperty(it) }
                }
                entity.manager.receiveLogRequest(
                    entity.id,
                    entity.id,
                    LogProperty,
                    intParas,
                    floatParas
                )
            }
            this.cacheChangeProperty.clear()
        }

        if (this.currentTime >= battleTime * 1000) {
            this.onFinish(FIGHT_RESULT_LOSE)
            return FIGHT_RESULT_LOSE
        }
        return com.point18.slg2d.common.constg.FIGHT_RESULT_ING
    }

    //战斗结束
    private fun onFinish(fightResult: Int) {
        //添加日志行为
        val intParas = hashMapOf<Int, Int>()
        intParas[FightResult] = fightResult
        this.receiveLogRequest(0, 0, LogFightFinish, intParas, null)
    }

    //接收请求，加入记录
    fun receiveLogRequest(
        senderId: Int,
        receiverId: Int,
        behaviorType: Int,
        intParas: HashMap<Int, Int>? = null,
        floatParas: HashMap<Int, Double>? = null,
        entityList: LinkedList<Entity>? = null,
        arrayParas: LinkedList<Pair<Int, Pair<HashMap<Int, Int>, HashMap<Int, Double>>>>? = null
    ) {
        this.recorder.receiveRecord(senderId, receiverId, behaviorType, intParas, floatParas, null)

        //过滤日志记录
        if (behaviorType == LogLaunchSkill) {
            return
        }

        val recordBuilder = FightRecord.newBuilder()
        recordBuilder.frame = this.currentTime / UpdateTime
        recordBuilder.senderId = senderId.toLong()
        recordBuilder.receiverId = receiverId.toLong()
        val requestBuilder = FightRequest.newBuilder()
        requestBuilder.behaviorType = behaviorType
        if (intParas != null) {
            for ((k, v) in intParas) {
                val intPropertyBuilder = IntProperty.newBuilder()
                intPropertyBuilder.propertyType = k
                intPropertyBuilder.propertyValue = v.toLong()
                requestBuilder.addIntPropertys(intPropertyBuilder)
            }
        }
        if (floatParas != null) {
            for ((k, v) in floatParas) {
                val floatPropertyBuilder = FloatProperty.newBuilder()
                floatPropertyBuilder.propertyType = k
                floatPropertyBuilder.propertyValue = v
                requestBuilder.addFloatPropertys(floatPropertyBuilder)
            }
        }
        if (entityList != null) {
            for (entity in entityList) {
                val fightEntityBuilder = FightEntity.newBuilder()
                fightEntityBuilder.id = entity.id.toLong()
                for ((k, v) in entity.intPropertyMap) {
                    val intPropertyBuilder = IntProperty.newBuilder()
                    intPropertyBuilder.propertyType = k
                    intPropertyBuilder.propertyValue = v.toLong()
                    fightEntityBuilder.addIntPropertys(intPropertyBuilder)
                }
                for ((k, v) in entity.floatPropertyMap) {
                    val floatPropertyBuilder = FloatProperty.newBuilder()
                    floatPropertyBuilder.propertyType = k
                    floatPropertyBuilder.propertyValue = v
                    fightEntityBuilder.addFloatPropertys(floatPropertyBuilder)
                }
                requestBuilder.addEntitys(fightEntityBuilder)
            }
        }
        if (arrayParas != null) {
            for (array in arrayParas) {
                val arrayParaBuilder = ArrayProperty.newBuilder()
                arrayParaBuilder.propertyType = array.first
                array.second.first.forEach {
                    val intPropertyBuilder = IntProperty.newBuilder()
                    intPropertyBuilder.propertyType = it.key
                    intPropertyBuilder.propertyValue = it.value.toLong()
                    arrayParaBuilder.addIntPropertys(intPropertyBuilder)
                }
                array.second.second.forEach {
                    val floatPropertyBuilder = FloatProperty.newBuilder()
                    floatPropertyBuilder.propertyType = it.key
                    floatPropertyBuilder.propertyValue = it.value
                    arrayParaBuilder.addFloatPropertys(floatPropertyBuilder)
                }
                requestBuilder.addArrayPropertys(arrayParaBuilder)
            }
        }

        recordBuilder.setRequest(requestBuilder)

        this.requestRecord.add(recordBuilder)
    }

    //初始化英雄决策树
    private fun initHeroDecisionTree(): BNode<Entity> {
        val alwaysSuccessNode = BNode<Entity>()
        return ConditionNode(
            { it.actionEndTime > it.manager.currentTime },

            //有行为进行中，进行行为刷新
            SelectorNode(
                ConditionNode(
                    { it.currentState == FlyState },
                    FlyActionNode()
                ),//飞行
                ConditionNode(
                    { it.currentState == SkillState && it.atkLaunchTime <= it.manager.currentTime },
                    LaunchSkillActionNode()
                ),//放技能
                ConditionNode(
                    { it.currentState == AttackState && it.atkLaunchTime <= it.manager.currentTime },
                    LaunchAttackActionNode()
                ),//普攻
                alwaysSuccessNode
            ),

            //选择一个有效行为
            SelectorNode(
                //判断技能
                SequenceNode(
                    SkillSingConditionNode(),
                    SelectorNode(
                        SequenceNode(
                            //准备放大招
                            UniqueSkillConditionNode(),
                            UniqueSkillActionNode()
                        ),
                        SequenceNode(
                            //准备放普通技能
                            CommonSkillConditionNode(),
                            CommonSkillActionNode()
                        )
                    )
                ),
                //判断普攻
                SequenceNode(
                    AttackConditionNode(),
                    AttackActionNode()
                ),
                //判断移动
                SequenceNode(
                    MoveConditionNode()
                ),
                alwaysSuccessNode
            )
        )
    }

    //初始化技能决策树
    private fun initSkillDecisionTree(): BNode<Entity> {
        return ConditionNode(
            { it.actionEndTime >= it.manager.currentTime },

            //有行为进行中，进行行为刷新
            SelectorNode(
                ConditionNode(
                    { it.currentState == FlyState },
                    FlyActionNode()
                )//飞行
            )
        )
    }
}

fun newEntityManager(areaCache: AreaCache, battleTime: Int): EntityManager {
    val manager = EntityManager(areaCache, battleTime)
    manager.recorder.manager = manager
    return manager
}


