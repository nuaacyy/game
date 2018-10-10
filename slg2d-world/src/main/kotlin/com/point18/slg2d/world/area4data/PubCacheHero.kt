package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.MAIN_HERO
import com.point18.slg2d.common.constg.NO_MAIN_HERO
import com.point18.slg2d.common.constg.OUT_CITY
import com.point18.slg2d.common.homeentities.HeroEquipVo
import com.point18.slg2d.common.mcache.TwoKeyIndex
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.worldentities.HERO_NAMED_QUERY
import com.point18.slg2d.common.worldentities.WorldHeroEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

class Hero(
        var dbId: Long, // 毫无意义的ID 只作为存储数据库的主键 绝对不允许业务逻辑使用
        var worldId: Long,
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
        private var intimacyLv: Int,                                                 //武将好感度等级
        var onFloorIdx: Int                                                  //在后宅楼层
) : EntityWrapper<WorldHeroEntity> {
    var heroEquipInfoMap: HashMap<Int, HeroEquipVo> =
            hashMapOf()//      map[: Int]*HeroEquipVo                                                      // 英雄佩戴装备情况  KEY : 模版包ID

    constructor() : this(
            0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): WorldHeroEntity {
        return WorldHeroEntity(
                worldId,
                dbId,
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

    override fun wrap(entity: WorldHeroEntity) {
        worldId = entity.worldId
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
        dbId = entity.dbId
    }
}

class CacheHero(areaCache: AreaCache, val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    val heroMap = TwoKeyIndex<Long, Long, Hero>({ it.playerId }, { it.id })

    // 领主状态队列
    private val hero4MainHero: Queue<Hero> = PriorityQueue { c1, c2 ->
        when {
            c1.mainHeroStateEndTime > c2.mainHeroStateEndTime -> 1
            c1.mainHeroStateEndTime == c2.mainHeroStateEndTime -> 0
            else -> -1
        }
    }

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.heroEntities =
                    session.getNamedQuery(HERO_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.heroEntities.forEach { entity ->
            try {
                val hero = db.wdb.recover(entity) { Hero() }

                cacheSingleHero(hero)

                // 英雄状态 优先级队列
                if (hero.mainHeroStateEndTime != 0L) {
                    hero4MainHero.add(hero)
                }

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {

    }

    // 获取指定类型的武将列表
    fun findHeroListByPlayerId(playerId: Long): LinkedList<WorldHeroEntity> {
        // 尝试从缓存中获取
        val heroList = LinkedList<WorldHeroEntity>()
        heroMap.findByOneKeyFilter(playerId) { heroList.add(it.toEntity()) }

        return heroList
    }

    fun createHeroByMoveServer(b: WorldHeroEntity) {
        val hero = Hero()
        hero.wrap(b)
        val dbId = wpm.generateObjIdNew(areaCache)

        hero.worldId = worldId
        hero.dbId = dbId

        insert(areaCache, hero)

        // 添加到缓存中
        cacheSingleHero(hero)

        // 英雄状态 优先级队列
        if (hero.mainHeroStateEndTime != 0L) {
            hero4MainHero.add(hero)
        }
    }

    // 暂时移除某个玩家的优先级队列数据,如果迁服失败再重新添回来
    fun stopHeroForMoveServer(playerId: Long) {
        val delList = findHeroListByType(playerId)
        for (del in delList) {
            // 英雄状态 优先级队列
            if (del.mainHeroStateEndTime != 0L) {
                hero4MainHero.remove(del)
            }
        }
    }

    // 迁服失败,把数据重新添加到优先级队列
    fun reviveHeroForMoveServer(playerId: Long) {
        val delList = findHeroListByType(playerId)
        for (del in delList) {
            // 英雄状态 优先级队列
            if (del.mainHeroStateEndTime != 0L) {
                hero4MainHero.add(del)
            }
        }
    }

    // 移除某个玩家的所有数据
    fun clearHeroForMoveServer(playerId: Long) {
        val delList = findHeroListByType(playerId)
        for (del in delList) {
            delete(areaCache, del)

            // 从缓存中删除
            heroMap.deleteByKey(del)
        }
    }

    // 新建武将
    fun createHero(playerId: Long, heroProtoId: Int): Hero? {
        val heroProto = pcs.unitBaseCache.fetchProtoById(heroProtoId)
        if (heroProto == null) {
            return null
        }
        val equips = hashMapOf<Int, HeroEquipVo>()
        heroProto.heroTrophiesMap.forEach { a ->
            equips[a.key] = HeroEquipVo(a.value)
        }

        val id = wpm.generateObjIdNew(areaCache)
        val dbId = wpm.generateObjIdNew(areaCache)
        val hero = Hero(
                dbId,
                worldId,
                id,
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
        insert(areaCache, hero)

        return hero
    }

    fun createHero(
            playerId: Long,
            heroId: Long,
            heroProtoId: Int,
            equips: HashMap<Int, HeroEquipVo>
    ): Hero? {
        val heroProto = pcs.unitBaseCache.fetchProtoById(heroProtoId)
        if (heroProto == null) {
            return null
        }

        val dbId = wpm.generateObjIdNew(areaCache)
        val hero = Hero(
                dbId,
                worldId,
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
        insert(areaCache, hero)

        return hero
    }

    private fun cacheSingleHero(hero: Hero) {
        // 存入缓存
        heroMap.insertByKey(hero)
    }

    fun updateMainHeroStateEndTime(hero: Hero, endTime: Long) {
        val oldTime = hero.mainHeroStateEndTime
        if (oldTime != 0L) {
            hero4MainHero.remove(hero)
        }
        hero.mainHeroStateEndTime = endTime
        if (endTime != 0L) {
            hero4MainHero.add(hero)
        }
    }

    fun peekMainHeroStateEndTimeFinish(): (Hero?) {
        val hero = hero4MainHero.peek()
        if (hero == null) {
            return null
        }

        val nowTime = getNowTime()

        if (hero.mainHeroStateEndTime < nowTime) {
            return hero
        }

        return null
    }

    fun popMainHeroStateEndTime() {
        hero4MainHero.poll()
    }

    // 根据ID获取武将
    fun findHeroById(playerId: Long, id: Long): Hero? {
        return heroMap.findByKey(playerId, id)
    }

    // 获取所有内政位上的武将 -- 内政英雄已经没了.这个改成守城英雄使用了
    fun findAllDefHero(playerId: Long): List<Hero> {
        val targetHeroList = LinkedList<Hero>()
        heroMap.findByOneKeyFilter(playerId) {
            if (it.intHeroAddress != 0) {
                targetHeroList.add(it)
            }
            true
        }
        return targetHeroList
    }

    //查找驻防英雄
    private fun findIntAddressHeroList(playerId: Long): List<Hero> {
        val heroList = LinkedList<Hero>()
        // 尝试从缓存中获取
        heroMap.findByOneKeyFilter(playerId) {
            if (it.intHeroAddress != 0) {
                heroList.add(it)
            }
            true
        }
        return heroList
    }

    // 获取指定类型的武将列表
    fun findHeroListByType(playerId: Long): List<Hero> {
        // 尝试从缓存中获取
        val heroList = LinkedList<Hero>()
        heroMap.findByOneKeyFilter(playerId) { heroList.add(it) }

        return heroList
    }

    //获取玩家所有的武将
    fun findHeroMapByPlayer(playerId: Long): HashMap<Long, Hero> {
        val playerHeroMap = hashMapOf<Long, Hero>()
        heroMap.findByOneKeyFilter(playerId) {
            playerHeroMap[it.id] = it
            true
        }
        return playerHeroMap
    }

    // 状态异常防守英雄补全
    fun getReserveHeroList(playerId: Long): List<Hero> {

        val allDefHeroList = findIntAddressHeroList(playerId) //全部防守英雄
        val allHeroList = findHeroListByType(playerId) // 全部英雄list
        val allStateAbnormalHerosList = LinkedList<Hero>() // 全部异常英雄
        val canUseHeroList = LinkedList<Hero>() // 可以作为补全英雄列表
        val realDefHeroList = LinkedList<Hero>() // 最终的补全英雄列表
        realDefHeroList += allDefHeroList

        // 防守英雄里面的全部异常英雄加入allStateAbnormalHeroList
        for (eachHero in allDefHeroList) {
            // 如果这个英雄出城了，就是异常的
            if (eachHero.posState == OUT_CITY) {
                allStateAbnormalHerosList.add(eachHero)
                continue
            }

            // 如果这个是主英雄，但是被俘，中毒，死亡了，就是异常的
            if (eachHero.mainHeroState != NO_MAIN_HERO && eachHero.mainHeroState != MAIN_HERO) {
                allStateAbnormalHerosList.add(eachHero)
            }
        }

        // 遍历全部英雄 把可以用作替补的英雄放进heroListCanUsed
        for (CanUseHero in allHeroList) {

            // 状态异常的不算
            // 状态异常：1，不是领主而且出城了 2，是领主，但是状态异常（出城，死亡，俘虏，返回路上等）
            // 如果这个英雄出城了，就是异常的
            if (CanUseHero.posState == OUT_CITY) {
                continue
            }

            // 如果这个是主英雄，但是被俘，中毒，死亡了，就是异常的
            if (CanUseHero.mainHeroState != NO_MAIN_HERO && CanUseHero.mainHeroState != MAIN_HERO) {
                continue
            }

            // 已经在在防守英雄里面的不算
            if (CanUseHero in allDefHeroList) {
                continue
            }

            // 添加到canUseHeroList
            canUseHeroList.add(CanUseHero)
        }

        // 剔除状态异常的英雄
        realDefHeroList.removeAll(allStateAbnormalHerosList)

        // 补全的数量 = addSize
        val addSize = 5 - realDefHeroList.size

        if (canUseHeroList.size <= addSize) {
            realDefHeroList += canUseHeroList
            return realDefHeroList
        } else {
            // 英雄根据这个比较强排序
            canUseHeroList.sortWith(HeroComparator())
            for (index in 0..(addSize - 1)) {
                val tmpHero = canUseHeroList.getOrNull(index) ?: continue
                realDefHeroList.add(tmpHero)
            }
            return realDefHeroList
        }
    }

    class HeroComparator : Comparator<Hero> {
        override fun compare(o1: Hero?, o2: Hero?): Int {
            if (o1 == null) {
                return 1
            }
            if (o2 == null) {
                return -1
            }

            val advLvDiff = o1.advLv - o2.advLv // 降序
            val awakeDiff = o1.awake - o2.awake // 降序
            val levelDiff = o1.level - o2.level // 降序

            if (advLvDiff == 0) {
                if (awakeDiff == 0) {
                    if (levelDiff == 0) {
                        if (o1.id >= o2.id) {
                            return 1
                        }
                        return -1
                    }
                    return -levelDiff
                }
                return -awakeDiff
            }
            return -advLvDiff
        }
    }
}

//要修改武将内政位信息,要把武将转移缓存,必须要掉这个方法不能再外部使用!!!!!!!!!!!!!!!!
//oldGeziId - 变化前的格子ID
//newGeziId - 变化到的格子ID
//3个情况--1.从0到非0 --2.从非0到0 -- 3.从非0到非0  有不同缓存处理策略
fun updateIntAddressId(hero: Hero, newIntAddress: Int) {
    hero.intHeroAddress = newIntAddress
}