package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.sec2MilliSec
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.Mass
import com.point18.slg2d.common.constg.Run
import com.point18.slg2d.common.constg.Wait
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.mcache.TwoKeyIndex
import com.point18.slg2d.common.mcache.TwoKeyIndexSlice
import com.point18.slg2d.common.worldentities.MASS_NAMED_QUERY
import com.point18.slg2d.common.worldentities.MassEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

// 集结表
class Mass(
    var worldId: Long,
    var id: Long,

    var mainPlayerId: Long,         // 团长ID
    var fightType: Int,             // 战斗类别 打人/打地/打城
    var massName: String,           // 集结名称
    var gotoX: Int,                 // 前往的集结X坐标
    var gotoY: Int,                 // 前往的集结Y坐标
    var startX: Int,                // 团长集合的X坐标
    var startY: Int,                // 团长集合的Y坐标
    var goTime: Long,               // 集结时间
    var massStartTime: Long,        // 集结开始时间，出发时为发车时间
    var massState: Int,             // 1-集结中 2-等待中 3-出征中
    var groupId: Long,              // 出发后的行军组Id
    var arriveTime: Long,           // 到达时间
    var allianceId: Long            // 所属联盟ID
) : EntityWrapper<MassEntity> {
    var memberInfoList: LinkedList<MassMember> = LinkedList()

    constructor() : this(
        0, 0, 0, 0, "", 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): MassEntity {
        return MassEntity(
            worldId,
            id,
            mainPlayerId,
            fightType,
            massName,
            gotoX,
            gotoY,
            startX,
            startY,
            goTime,
            massStartTime,
            massState,
            groupId,
            arriveTime,
            toJson(memberInfoList),
            allianceId
        )
    }

    override fun wrap(entity: MassEntity) {
        worldId = entity.worldId
        id = entity.id
        mainPlayerId = entity.mainPlayerId
        fightType = entity.fightType
        massName = entity.massName
        gotoX = entity.gotoX
        gotoY = entity.gotoY
        startX = entity.startX
        startY = entity.startY
        goTime = entity.goTime
        massStartTime = entity.massStartTime
        massState = entity.massState
        groupId = entity.groupId
        arriveTime = entity.arriveTime
        allianceId = entity.allianceId
        memberInfoList = toObj(entity.membereInfos)
    }

    fun findMassMember(playerId: Long): MassMember? {
        return this.memberInfoList.firstOrNull { it.playerId == playerId }
    }

    fun removeMassMember(playerId: Long) {
        this.memberInfoList.removeIf { it.playerId == playerId }
    }

    fun putMassMember(member: MassMember) {
        this.memberInfoList.removeIf { it.playerId == member.playerId }
        this.memberInfoList.add(member)
    }
}

//集结的成员
class MassMember(
    var playerId: Long,       //`json:"player_id"`   // 玩家Id
    var groupId: Long,      // `json:"group_id"`    // 参战部队ID
    var arriveTime: Long //`json:"arrive_time"` // 到达时间
) {
    constructor() : this(0, 0, 0)
}

class CacheMass(areaCache: AreaCache,val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val massMap = OneKeyIndex<Long, Mass> { it.id }
    private val massPosMap = TwoKeyIndexSlice<Int, Int, Mass>({ it.gotoX }, { it.gotoY }, { mA, mB -> mA.id == mB.id })
    private val allianceMassMap = TwoKeyIndex<Long, Long, Mass>({ it.allianceId }, { it.id })
    private val playerMassMap = TwoKeyIndex<Long, Long, Mass>({ it.mainPlayerId }, { it.id })
    private val groupMassMap = OneKeyIndex<Long, Mass> { it.groupId }

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.massEntities =
                    session.getNamedQuery(MASS_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.massEntities.forEach { entity ->
            try {
                val mass = db.wdb.recover(entity) { Mass() }

                this.massMap.insertByKey(mass)
                this.massPosMap.insertByKey(mass)
                this.allianceMassMap.insertByKey(mass)
                this.playerMassMap.insertByKey(mass)
                if (mass.massState == Run) {
                    this.groupMassMap.insertByKey(mass)
                }

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    //创建集结
    fun createMass(
        player: Player,
        fightType: Int,
        name: String,
        startX: Int,
        startY: Int,
        toX: Int,
        toY: Int,
        timeSec: Int,
        groupId: Long
    ): Mass {
        val id = wpm.generateObjIdNew(areaCache)
        val mass = Mass(
            worldId,
            id,
            player.id,
            fightType,
            name,
            toX,
            toY,
            startX,
            startY,
            getNowTime() + sec2MilliSec(timeSec),
            getNowTime(),
            Mass,
            groupId,
            0,
            player.allianceId
        )

        mass.putMassMember(MassMember(player.id, groupId, getNowTime()))

        massMap.insertByKey(mass)
        massPosMap.insertByKey(mass)
        allianceMassMap.insertByKey(mass)
        playerMassMap.insertByKey(mass)

        insert(areaCache, mass)

        return mass
    }

    // 集结发车
    fun massRun(mass: Mass, groupId: Long, arriveTime: Long) {
        mass.massStartTime = getNowTime()
        mass.arriveTime = arriveTime
        mass.groupId = groupId
        mass.massState = Run

        groupMassMap.insertByKey(mass)
    }

    // 删除集结
    fun deleteMass(mass: Mass) {
        if (mass.id == 0L) {
            return
        }

        delete(areaCache, mass)

        //删除集结
        massMap.deleteByKey(mass)
        massPosMap.deleteByKey(mass)
        allianceMassMap.deleteByKey(mass)
        playerMassMap.deleteByKey(mass)
        groupMassMap.deleteByKey(mass)

    }

    fun findMassById(id: Long): Mass? {
        return massMap.findByKey(id)
    }

    // 查找所有结束的集结
    fun findAllOverMass(): List<Mass> {
        val allMass = LinkedList<Mass>()
        val nowTime = getNowTime()
        massMap.map {
            when (it.massState) {
                Mass -> {
                    if (it.goTime < nowTime) {
                        allMass.add(it)
                    }
                }
                Wait -> {
                    var allArrive = true
                    for (member in it.memberInfoList) {
                        if (member.arriveTime > nowTime) {
                            allArrive = false
                            break
                        }
                    }
                    if (allArrive) {
                        allMass.add(it)
                    }
                }
                Run -> {
                    if (it.arriveTime < nowTime) {
                        allMass.add(it)
                    }
                }
            }
            true
        }
        return allMass
    }

    // 查找所有联盟内的集结
    fun findMassByAllianceId(allianceId: Long): List<Mass> {
        val allMass = LinkedList<Mass>()
        allianceMassMap.findByOneKeyFilter(allianceId) { allMass.add(it) }
        return allMass
    }

    // 根据目的地查找集结
    fun findAllMassByPos(posX: Int, posY: Int): List<Mass> {
        val allMass = LinkedList<Mass>()
        massPosMap.findByKey(posX, posY) { allMass.add(it) }
        return allMass
    }

    // 获取集结中士兵总量
    fun getSoliderNumExceptMainInMass(mass: Mass): Int {
        var totalNum = 0
        mass.memberInfoList.forEach {
            if (it.playerId == mass.mainPlayerId) {
                return@forEach
            }
            totalNum += areaCache.walkForceGroupCache.getSoliderNumInGroup(it.groupId)
        }
        return totalNum
    }

    // 查找玩家的集结
    fun findMassByPlayerId(playerId: Long): List<Mass> {
        val allMass = LinkedList<Mass>()
        playerMassMap.findByOneKeyFilter(playerId) { allMass.add(it) }
        return allMass
    }

    // 根据行军的行军组Id查询集结
    fun findMassByRunGroupId(groupId: Long): Mass? {
        return groupMassMap.findByKey(groupId)
    }
}



