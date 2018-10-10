package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.*
import com.point18.slg2d.home.AbstractDataContainer
import com.point18.slg2d.home.hpm
import com.point18.slg2d.common.mcache.OneKeyIndex
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.PlayerId
import com.point18.slg2d.common.pc.pcs
import java.io.Serializable
import java.time.Duration
import java.util.*

class HeroDC : AbstractDataContainer<List<HeroEntity>>() {
    val heroIdMap = OneKeyIndex<Long, Hero> { it.id }
    val heroProtoMap = OneKeyIndex<Int, Hero> { it.protoId }

    override fun initImpl(data: List<HeroEntity>?, depDCRepo: DataContainerRepo) {
        data?.map {
            val heroWrap = wdb.recover(it) { Hero() }

            heroIdMap.insertByKey(heroWrap)
            heroProtoMap.insertByKey(heroWrap)
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<HeroEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(HERO_NAMED_QUERY)
                .setLong("playerId", playerId)
                .listNoDup<HeroEntity>()
            list
        }
        return data
    }

    // 找到所有正在升阶中的武将
    fun findHeroInSuperLvUp(): HashMap<Long, Hero> {
        val playerHeroMap = hashMapOf<Long, Hero>()
        this.heroIdMap.index.forEach {
            if (it.value.superUpEndTime != com.point18.slg2d.common.commonfunc.zeroTime.time) {
                playerHeroMap[it.value.id] = it.value
            }
        }
        return playerHeroMap
    }

    fun findHeroById(id: Long): Hero? {
        return heroIdMap.findByKey(id)
    }

    // 找到所有正在升星中的武将
    fun findHeroInStarLvUp(): HashMap<Long, Hero> {
        val playerHeroMap = hashMapOf<Long, Hero>()
        this.heroIdMap.index.forEach {
            if (it.value.starUpEndTime != com.point18.slg2d.common.commonfunc.zeroTime.time) {
                playerHeroMap[it.value.id] = it.value
            }
        }
        return playerHeroMap
    }

    // 获取指定类型的武将列表
    fun findHeroList(): LinkedList<Hero> {
        // 尝试从缓存中获取
        val heroList = LinkedList<Hero>()
        this.heroIdMap.index.forEach { heroList += it.value }

        return heroList
    }

    // 新建武将
    fun createHero(playerId: Long, id: Long, heroProtoId: Int): Hero? {
        val heroProto = pcs.unitBaseCache.fetchProtoById(heroProtoId)
        if (heroProto == null) {
            return null
        }

        val equips = hashMapOf<Int, HeroEquipVo>()
        heroProto.heroTrophiesMap.forEach { a ->
            equips[a.key] = HeroEquipVo(a.value)
        }

        var heroId = id
        if (heroId == 0L) {
            heroId = hpm.generateObjIdNew()
        }
        val hero = Hero(
            heroId,
            heroProtoId,
            1,
            1,
            0,
            heroProto.skill1,
            heroProto.skill2,
            heroProto.skill3,
            heroProto.skill4,
            playerId,
            1,
            heroProto.intSkill1,
            heroProto.intSkill2,
            heroProto.intSkill3,
            0,
            0,
            com.point18.slg2d.common.commonfunc.zeroTime.time,
            com.point18.slg2d.common.commonfunc.zeroTime.time,
            0,
            com.point18.slg2d.common.constg.IN_CITY,
            com.point18.slg2d.common.commonfunc.zeroTime.time,
            com.point18.slg2d.common.commonfunc.zeroTime.time,
            0,
            0,
            0,
            0
        )
        hero.heroEquipInfoMap = equips

        cacheSingleHero(hero)

        // 保存
        wdb.save(hero)

        return hero
    }

    //获取玩家所有的武将
    fun findHeroMapByPlayer(): Map<Long, Hero> {
        return this.heroIdMap.index
    }

    fun findProtoHeroMapMap(): Map<Int, Hero> {
        return this.heroProtoMap.index
    }

    private fun cacheSingleHero(hero: Hero) {
        // 存入缓存
        this.heroIdMap.insertByKey(hero)
        this.heroProtoMap.insertByKey(hero)

    }
}

data class HeroProperty(
    var heroId: Long, // 武将操作码
    var heroProtoId: Int, // 武将ID
    var lv: Int, // 武将等级
    var advLv: Int, // 武将进阶等级
    var exp: Int, // 武将经验
    var attW: Double, // 武将攻击资质
    var hpW: Double, // 武将生命资质
    var defenceW: Double, // 武将防御资质
    var speed: Double, // 武将速度
    var baoji: Double, //暴击
    var baojiLv: Double, //暴击率
    var skill1: Int, // 技能1
    var skill2: Int, // 技能2
    var skill3: Int, // 技能3
    var skill4: Int, // 技能4
    var awake: Int, // 觉醒等级
    var IntSkillId1: Int, // 武将内政技能1
    var IntSkillId2: Int, // 武将内政技能2
    var IntSkillId3: Int, // 武将内政技能3
    var IntHeroAddress: Int, // 非0表示武将在执政中,这个值表示执政位ID
    var mainHeroState: Int, // 领主属性状态字段 0-不是领主 1-正常状态领主 2-被监禁领主 3-..........
    var heroPosState: Int, //英雄位置状态 1、在城内 2、出征中 3、在藏兵洞穴中
    var heroEquips: HashMap<Int, HeroEquipVo>,
    var starLvUpEndTime: Long, // 升星到点时间
    var superLvUpEndTime: Long, // 升阶到点时间
    var mainHeroStartTime: Long, // 领主状态开始时间
    var mainHeroOverTime: Long, // 领主状态结束时间
    var heroPower: Long, // 英雄实力
    var onFloorIdx: Int        // 所在后宅楼层
)

class Hero(
    var id: Long,

    var protoId: Int,                                              // 武将模板ID
    var level: Int,                                              // 武将等级
    var advLv: Int,                                              // 星数
    var exp: Int,                                  // 武将经验
    var skillId1: Int,                                              // 武将技能1
    var skillId2: Int,                                              // 武将技能2
    var skillId3: Int,                                              // 武将技能3
    var skillId4: Int,                                              // 武将技能4
    var playerId: Long,                                  // 玩家编号
    var awake: Int,                                              // 觉醒等级
    var intSkillId1: Int,                                              // 武将内政技能1
    var intSkillId2: Int,                                              // 武将内政技能2
    var intSkillId3: Int,                                              // 武将内政技能3
    var intHeroAddress: Int,                                              // 非0表示武将在执政中,这个值表示执政位ID
    var mainHeroState: Int,                                              // 领主属性状态字段 0-不是领主 1-正常状态领主 2...3....4...8-在藏兵洞穴中
    var mainHeroStateStartTime: Long,  // 领主状态变化的开始时间,要记录这个是因为有科技会影响这个时间,但是不影响已经关押的人的时间
    var mainHeroStateEndTime: Long,  // 领主状态变化的结束时间,这个字段的意义取决于当前所属的状态
    var mainHeroPrisonPlayerId: Long,                                  // 关押该领主的玩家ID
    var posState: Int,                                              // 位置状态 1、在城内 2、出征中
    var starUpEndTime: Long,  // 升星结束时间
    var superUpEndTime: Long,  // 升阶结束时间
    var heroStrength: Long,                                  // 英雄实力
    var intimacyExp: Int,                                                //武将好感度
    var intimacyLv: Int,                                                 //武将好感度等级
    var onFloorIdx: Int                                                  //在后宅楼层
) : EntityWrapper<HeroEntity> {
    var heroEquipInfoMap: HashMap<Int, HeroEquipVo> = hashMapOf() // 英雄佩戴装备情况  KEY : 模版包ID

    constructor() : this(
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = HeroPK(playerId, id)

    override fun toEntity(): HeroEntity {
        return HeroEntity(
            id,
            protoId,
            level,
            advLv,
            exp,
            skillId1,
            skillId2,
            skillId3,
            skillId4,
            playerId,
            awake,
            intSkillId1,
            intSkillId2,
            intSkillId3,
            intHeroAddress,
            mainHeroState,
            mainHeroStateStartTime,
            mainHeroStateEndTime,
            mainHeroPrisonPlayerId,
            posState,
            toJson(heroEquipInfoMap),
            starUpEndTime,
            superUpEndTime,
            heroStrength,
            intimacyExp,
            intimacyLv,
            onFloorIdx
        )
    }

    override fun wrap(entity: HeroEntity) {
        id = entity.id
        protoId = entity.protoId
        level = entity.level
        advLv = entity.advLv
        exp = entity.exp
        skillId1 = entity.skillId1
        skillId2 = entity.skillId2
        skillId3 = entity.skillId3
        skillId4 = entity.skillId4
        playerId = entity.playerId
        awake = entity.awake
        intSkillId1 = entity.intSkillId1
        intSkillId2 = entity.intSkillId2
        intSkillId3 = entity.intSkillId3
        intHeroAddress = entity.intHeroAddress
        mainHeroState = entity.mainHeroState
        mainHeroStateStartTime = entity.mainHeroStateStartTime
        mainHeroStateEndTime = entity.mainHeroStateEndTime
        mainHeroPrisonPlayerId = entity.mainHeroPrisonPlayerId
        posState = entity.posState
        starUpEndTime = entity.starUpEndTime
        superUpEndTime = entity.superUpEndTime
        heroStrength = entity.heroStrength
        intimacyExp = entity.intimacyExp
        intimacyLv = entity.intimacyLv
        onFloorIdx = entity.onFloorIdx
        heroEquipInfoMap = toObj(entity.heroEquipInfo)
    }

    fun toSyncDomain(): com.point18.slg2d.common.syncdomain.HeroData {
        return com.point18.slg2d.common.syncdomain.HeroData(
            id,
            protoId,
            level,
            advLv,
            awake,
            exp,
            skillId1,
            skillId2,
            skillId3,
            skillId4,
            heroEquipInfoMap
        )
    }
}