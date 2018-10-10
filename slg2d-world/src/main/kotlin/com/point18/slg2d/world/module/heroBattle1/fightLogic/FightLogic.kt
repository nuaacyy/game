package com.point18.slg2d.world.module.heroBattle1.fightLogic

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.module.fightdomain.FightData
import com.point18.slg2d.world.module.fightdomain.HeroData
import xyz.ariane.util.lzWarn
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.HeroEquipVo
import com.point18.slg2d.common.pc.HeroSkillProto
import com.point18.slg2d.common.pc.pcs
import java.util.*
import java.util.Arrays.asList

class FightLogic(
    var areaCache: AreaCache,
    var manager: EntityManager,
    var fightResult: Int,
    var atkFightData: FightData,
    var defFightData: FightData
) {
    private var initialHeroMap = hashMapOf<Int, HeroData>()

    // 初始化
    fun initLogic() {
        this.loadForce(this.atkFightData, ATK_SIDE)
        this.loadForce(this.defFightData, DEF_SIDE)
    }

    // 战斗前
    fun beforeFight() {
        this.manager.init()

        for (entity in this.manager.entityList) {
            //释放固定回气/回血技能
            val hpRecoverSkillProto = pcs.heroSkillProtoCache.heroSkillMap[pcs.basicProtoCache.hpRecoverHeroSkillId]
            if (hpRecoverSkillProto != null) {
                val skill = Skill(entity, hpRecoverSkillProto)
                entity.launchingSkill = skill
                skill.takeSkillEffect()
            }
            val moraleRecoverSkillProto =
                pcs.heroSkillProtoCache.heroSkillMap[pcs.basicProtoCache.moraleRecoverHeroSkillId]
            if (moraleRecoverSkillProto != null) {
                val skill = Skill(entity, moraleRecoverSkillProto)
                entity.launchingSkill = skill
                skill.takeSkillEffect()
            }

            //释放被动技能
            val passiveSkillList = entity.arrayPropertyMap[PassiveSkillList]
            if (passiveSkillList == null) {
                continue
            }
            for (skillId in passiveSkillList) {
                val skillProto = pcs.heroSkillProtoCache.heroSkillMap[skillId]
                if (skillProto == null) {
                    continue
                }
                if (entity.currentState != SkillState) {
                    entity.changeState(SkillState)
                }
                val passiveSkill = Skill(entity, skillProto)
                entity.launchingSkill = passiveSkill
                passiveSkill.takeSkillEffect()
            }
        }

        this.manager.recorder.beforeFightRecord(this.atkFightData, this.defFightData)
    }

    fun fight() {
        var index = 0
        while (true) {
            // 每间隔的更新
            val rst = this.manager.update()
            if (rst != FIGHT_RESULT_ING) {
                this.fightResult = rst
                break
            }

            index++
            if (index > 10000) {
                normalLog.lzWarn { "战斗逻辑帧触发了保险丝" }
                return
            }
        }
    }

    fun afterFight() {
        //更新FightData数据
        initialHeroMap.forEach {
            val entity = this.manager.entityMap[it.key]
            if (entity == null) {
                return@forEach
            }
            it.value.assignHp = entity.getIntProperty(Hp, false)
            it.value.initMorale = entity.getFloatProperty(Morale, false)
        }

        this.manager.recorder.afterFightRecord(this.atkFightData, this.defFightData)
    }

    // 加载战斗单位实体
    private fun loadForce(fightData: FightData, side: Int) {
        val areaCache = this.areaCache
        for (heroData in fightData.heroList) {
            var equipMap: HashMap<Int, HeroEquipVo>? = null
            var exp = 0
            var intimacy = 0
            if (fightData.playerId != 0L && heroData.id != 0L) {
                val hero = areaCache.heroCache.findHeroById(fightData.playerId, heroData.id)
                if (hero == null) {
                    assert(false) { "加载战斗单位实体时，找不到英雄，玩家Id:${fightData.playerId},英雄Id:${heroData.id}" }
                    continue
                }
                equipMap = hero.heroEquipInfoMap
                exp = hero.exp
                intimacy = hero.intimacyExp
            }
            val heroEntity =
                newEntityByHeroData(
                    this.manager,
                    heroData,
                    side,
                    Hero,
                    equipMap,
                    fightData.additionPropertyMap,
                    exp,
                    intimacy
                ) ?: continue

            val unitBaseProto = pcs.unitBaseCache.fetchProtoById(heroData.protoId) ?: continue

            this.initialHeroMap[heroEntity.id] = heroData

            //设置站位
            var posX = unitBaseProto.SPPos
            if (side == DEF_SIDE) {
                //反方向则X轴坐标对称
                posX = pcs.basicProtoCache.mapGridX - posX
            }
            val posY = pcs.basicProtoCache.mapGridY * 2 / 3

            heroEntity.setPos(posX.toDouble(), posY.toDouble())
        }
    }
}

fun newEntityByHeroData(
    manager: EntityManager,
    heroData: HeroData,
    side: Int,
    entityType: Int,
    equipMap: HashMap<Int, HeroEquipVo>?,
    additionPropertyMap: HashMap<Set<Int>, Map<Int, Int>>? = null,
    exp: Int = 0,
    intimacy: Int = 0
): Entity? {
    val rst =
        com.point18.slg2d.world.common.getHeroBasicInfo(
            heroData.protoId,
            heroData.lv,
            heroData.star,
            heroData.awake,
            equipMap,
            additionPropertyMap
        )
    val atk = rst.atkW.toFloat()
    val def = rst.defW.toFloat()
    val magic = rst.magicW.toFloat()
    val magicDef = rst.magicDefW.toFloat()
    var hp = rst.hpW
    if (heroData.assignHp != 0) {
        //存在指定血量
        hp = heroData.assignHp.toDouble()
    }
    val speed = rst.speedW.toFloat()
    val baoji = rst.baojiW.toFloat()
    val baojilv = rst.baojilvW.toFloat()
    val hpRecovery = rst.hpRecoveryW
    val moraleRecovery = rst.moraleRecoveryW.toFloat()
    val atkAddHp = rst.atkAddHpW
    val atkAddMorale = rst.atkAddMoraleW.toFloat()
    val dodge = rst.dodgeW.toFloat()
    val atkType = rst.atkType
    val unitBase = pcs.unitBaseCache.protoMap[heroData.protoId]
    if (unitBase == null) {
        normalLog.lzWarn { "英雄配置不存在:${heroData.protoId}" }
        return null
    }

    val heroEntity = newEntity(manager, entityType)

    heroEntity.name = unitBase.name + ":" + heroData.pos

    //加载属性
    heroEntity.currentState = NullState

    heroEntity.intPropertyMap[FightSide] = side
    heroEntity.intPropertyMap[ProtoId] = heroData.protoId
    heroEntity.intPropertyMap[UnitType] = Hero
    heroEntity.intPropertyMap[AtkType] = atkType
    heroEntity.intPropertyMap[Lv] = heroData.lv
    heroEntity.intPropertyMap[Star] = heroData.star
    heroEntity.intPropertyMap[Awake] = heroData.awake
    heroEntity.intPropertyMap[Hp] = Math.ceil(hp).toInt()
    heroEntity.intPropertyMap[ARR_HPLIMIT] = Math.ceil(rst.hpW).toInt()
    heroEntity.intPropertyMap[ARR_HPRECOVREY] = Math.ceil(hpRecovery).toInt()
    heroEntity.intPropertyMap[ARR_ATKADDHP] = Math.ceil(atkAddHp).toInt()
    heroEntity.intPropertyMap[AliveTime] = Int.MAX_VALUE
    heroEntity.intPropertyMap[BulletSpeed] = unitBase.bulletSpeed
    heroEntity.intPropertyMap[Exp] = exp
    heroEntity.intPropertyMap[Intimacy] = intimacy

    heroEntity.floatPropertyMap[ARR_WUGONG] = atk.toDouble()
    heroEntity.floatPropertyMap[ARR_WUFANG] = def.toDouble()
    heroEntity.floatPropertyMap[ARR_FAGONG] = magic.toDouble()
    heroEntity.floatPropertyMap[ARR_FAFANG] = magicDef.toDouble()
    heroEntity.floatPropertyMap[ARR_SUDU] = speed.toDouble()
    heroEntity.floatPropertyMap[ARR_BAOJI] = baoji.toDouble()
    heroEntity.floatPropertyMap[ARR_BAOJILV] = baojilv.toDouble()
    heroEntity.floatPropertyMap[ARR_INTERVAL] = unitBase.attackTime.toDouble()
    heroEntity.floatPropertyMap[ARR_GONGJIJULI] = unitBase.range.toDouble()

    heroEntity.floatPropertyMap[ARR_MORALERECOVERY] = moraleRecovery.toDouble()
    heroEntity.floatPropertyMap[ARR_ATKADDMORALE] = atkAddMorale.toDouble()
    heroEntity.floatPropertyMap[ARR_DODGE] = dodge.toDouble()
    heroEntity.floatPropertyMap[Morale] =
        if (side == DEF_SIDE) pcs.basicProtoCache.heroBattleDefaultMoraleWithEnemy else heroData.initMorale
    heroEntity.floatPropertyMap[MoraleLimit] = pcs.basicProtoCache.heroMoraleLimit.toDouble()
    heroEntity.floatPropertyMap[KillAddMorale] = pcs.basicProtoCache.killHeroAddMorale.toDouble()
    heroEntity.floatPropertyMap[TouchVolume] = unitBase.touchVolume.toDouble()
    heroEntity.floatPropertyMap[AtkVolume] = unitBase.atkVolume.toDouble()

    heroEntity.arrayPropertyMap[UniqueSkillList] = LinkedList()
    heroEntity.arrayPropertyMap[ActiveSkillList] = LinkedList()
    heroEntity.arrayPropertyMap[PassiveSkillList] = LinkedList()
    heroEntity.arrayPropertyMap[AtkAnimation] = LinkedList(unitBase.atkAnimationList)

    val unlockSkills = LinkedList<HeroSkillProto>()

    if (heroData.skillList != null) {
        unlockSkills.clear()
        heroData.skillList.forEach {
            val skillProto = pcs.heroSkillProtoCache.heroSkillMap[it]
            if (skillProto != null && skillProto.level > 0) {
                unlockSkills.add(skillProto)
            }
        }
    }

    for (skillProto in unlockSkills) {
        when (skillProto.type) {
            UniqueSkill ->
                heroEntity.arrayPropertyMap.getOrPut(UniqueSkillList) { LinkedList() }.add(skillProto.id)
            ActiveSkill ->
                heroEntity.arrayPropertyMap.getOrPut(ActiveSkillList) { LinkedList() }.add(skillProto.id)
            PassiveSkill ->
                heroEntity.arrayPropertyMap.getOrPut(PassiveSkillList) { LinkedList() }.add(skillProto.id)
        }
    }

    val activeSkillNum = heroEntity.arrayPropertyMap[ActiveSkillList]?.size ?: 0
    val atkOrderProto = pcs.attackOrderProtoCache.orderMap[heroData.protoId]?.get(activeSkillNum)
    if (atkOrderProto != null) {
        val actionOrderList = LinkedList<Int>()
        atkOrderProto.orderList.forEach {
            actionOrderList.add(it)
        }
        heroEntity.arrayPropertyMap[ActionOrder] = actionOrderList
    } else {
        heroEntity.arrayPropertyMap[ActionOrder] = LinkedList(asList(NormalAttack))
    }

    return heroEntity
}





