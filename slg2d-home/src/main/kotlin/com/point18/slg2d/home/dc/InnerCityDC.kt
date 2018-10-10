package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.INNER_CITY_NAMED_QUERY_PLAYER
import com.point18.slg2d.common.homeentities.InnerCityEntity
import com.point18.slg2d.common.homeentities.InnerCityPK
import com.point18.slg2d.home.AbstractDataContainer
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.forwardHeartDeal2World
import com.point18.slg2d.home.hpm
import com.point18.slg2d.common.mcache.OneKeyIndex
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.*
import java.io.Serializable
import java.time.Duration
import java.util.*

// 建筑
class InnerCityDC : AbstractDataContainer<List<InnerCityEntity>>() {

    val innerCityMap = OneKeyIndex<Long, InnerCity> { it.id }

    override fun initImpl(data: List<InnerCityEntity>?, depDCRepo: DataContainerRepo) {
        data?.forEach {
            val innerCityWrap = wdb.recover(it) { InnerCity() }

            add(innerCityWrap)
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<InnerCityEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(INNER_CITY_NAMED_QUERY_PLAYER)
                .setLong("playerId", playerId)
                .listNoDup<InnerCityEntity>()
            list
        }
        return data
    }

    private fun add(v: InnerCity) {
        innerCityMap.insertByKey(v)
    }

    // 创建建筑
    fun createInnerCity(
        playerId: Long,
        castleId: Long,
        positionIndex: Int,
        buildingType: Int,
        buildingLv: Int,
        startTime: Long,
        completeTime: Long,
        state: Int
    ): InnerCity {
        val id = hpm.generateObjIdNew()
        val innerCity = InnerCity(
            id,
            positionIndex,
            buildingType,
            buildingLv,
            startTime,
            completeTime,
            state,
            0,//commonfun.ZeroTime
            0,//constg.STABLE
            0,
            castleId,
            playerId
        )

        wdb.save(innerCity)
        // 添加到缓存
        add(innerCity)

        return innerCity
    }

    private fun removeInnerCity(innerCity: InnerCity) {
        innerCityMap.deleteByKey(innerCity)
    }

    // 删除一个建筑
    fun deleteInnerCity(innerCity: InnerCity) {
        removeInnerCity(innerCity)
        wdb.delete(innerCity)
    }

    // 找到一个城池中的所有建筑
    fun findInnerCityListFromCastleId(castleId: Long): LinkedList<InnerCity> {
        // 尝试从缓存中获取
        val buildings = LinkedList<InnerCity>()
        innerCityMap.map {
            if (it.cityId == castleId) {
                buildings += it
            }
            true
        }
        return buildings
    }

    fun findInnerCityListFromCastleIdAndType(
        castleId: Long,
        innerCityType: Int
    ): LinkedList<InnerCity> {
        // 尝试从缓存中获取
        val buildings = LinkedList<InnerCity>()
        innerCityMap.map {
            if (it.cityId == castleId && it.cityType == innerCityType) {
                buildings += it
            }
            true
        }
        return buildings
    }

    // 通过坑位找建筑
    fun findInnerCityFromPositionIndex(
        castleId: Long,
        positionIndex: Int
    ): InnerCity? {
        var innerCity: InnerCity? = null
        innerCityMap.map {
            val checkRt = it.cityId == castleId && it.positionIndex == positionIndex
            if (checkRt) {
                innerCity = it
            }
            !checkRt
        }

        return innerCity
    }

    // 找到一个城池中的某个建筑(城池，建筑类型)
    fun findMaxLvInnerBuildingFromCastleIdAndType(
        castleId: Long,
        buildingType: Int
    ): InnerCity? {
        var innerCity: InnerCity? = null
        innerCityMap.map {
            val checkRt = it.cityId == castleId && it.cityType == buildingType
            if (checkRt) {
                val ic = innerCity
                if (ic == null || it.lv > ic.lv) {
                    innerCity = it
                }
            }
            !checkRt
        }
        return innerCity
    }

    // 找到某个建筑(建筑Id)
    fun findInnerCityFromId(innerCityId: Long): InnerCity? {
        // 尝试从缓存中获取
        return innerCityMap.findByKey(innerCityId)
    }

    // 更新建筑的升级完成时间
    fun updateInnerCityUpgradeState(
        building: InnerCity,
        state: Int,
        completeTime: Long,
        startTime: Long
    ) {
        building.completeTime = completeTime
        building.startTime = startTime
        building.state = state
    }

    // 向World服请求更新建筑升级状态
    fun updateInnerCityUpgradeState2World(
        session: PlayerActor,
        building: InnerCity,
        state: Int,
        completeTime: Long,
        startTime: Long
    ) {
        building.completeTime = completeTime
        building.startTime = startTime
        building.state = state

        if (completeTime == 0L || state == STABLE) {
            // 已完成，取消心跳
            forwardHeartDeal2World(session, DeleteHeart, InnerCityUpgrade, building.id, completeTime)
        } else {
            // 更新心跳
            forwardHeartDeal2World(session, UpdateHeart, InnerCityUpgrade, building.id, completeTime)
        }
    }

    // 更新建筑的拆除完成时间
    fun updateInnerCityDestroyState(
        building: InnerCity,
        state: Int,
        destroyTime: Long,
        startTime: Long
    ) {
        building.destroyTime = destroyTime
        building.startTime = startTime
        building.state = state
    }

    fun updateInnerCityDestroyState2World(
        session: PlayerActor,
        building: InnerCity,
        state: Int,
        destroyTime: Long,
        startTime: Long
    ) {
        building.destroyTime = destroyTime
        building.startTime = startTime
        building.state = state

        if (destroyTime != 0L || state == STABLE) {
            // 更新心跳时间
            forwardHeartDeal2World(session, UpdateHeart, InnerCityDestroy, building.id, destroyTime)
        } else {
            // 取消心跳
            forwardHeartDeal2World(session, DeleteHeart, InnerCityDestroy, building.id, destroyTime)
        }
    }

    // 根据建造类别查找有效建筑
    fun findEffectiveInnerBuildingsByType(buildingType: Int): LinkedList<InnerCity> {
        val rt = LinkedList<InnerCity>()
        innerCityMap.map {
            if (it.cityType == buildingType && it.lv > 0) {
                rt += it
            }
            true
        }
        return rt
    }

    // 找到一个城池中的所有建筑
    fun findInnerBuildingsFromCastleId(castleId: Long): LinkedList<InnerCity> {
        // 尝试从缓存中获取
        val rt = LinkedList<InnerCity>()
        innerCityMap.map {
            if (it.cityId == castleId) {
                rt += it
            }
            true
        }
        return rt
    }

    //查找所有内城建筑
    fun findAllInnerCityBuildings(): LinkedList<InnerCity> {
        val rt = LinkedList<InnerCity>()
        innerCityMap.map {
            rt += it
            true
        }
        return rt
    }
}

// 建筑
class InnerCity(
    // 建筑唯一ID
    var id: Long,

    var positionIndex: Int,  // 位置索引
    var cityType: Int,  // 类型
    var lv: Int,  // 等级
    var startTime: Long,  // 建筑升级开始时间
    var completeTime: Long,  // 建筑升级完成时间
    var state: Int,                // 建筑升级状态
    var destroyTime: Long,  // 建筑拆除完成时间
    var destroyState: Int,                // 建筑拆除状态
    var helpId: Long,  // 对应的帮派帮助表里的ID
    var cityId: Long,  // 所属城池Id（关联的城池Id）
    var playerId: Long    // 所属玩家Id
) : EntityWrapper<InnerCityEntity> {
    var helperIdMap: HashMap<Long, Long> = hashMapOf()// `sql:"-"`                     // 帮忙加速过的玩家

    constructor() : this(
        0, 0, 0, 0, 0, 0, 0, 0, 0
        , 0, 0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = InnerCityPK(playerId, id)

    override fun toEntity(): InnerCityEntity {
        return InnerCityEntity(
            id,
            positionIndex,
            cityType,
            lv,
            startTime,
            completeTime,
            state,
            destroyTime,
            destroyState,
            toJson(helperIdMap),
            helpId,
            cityId,
            playerId
        )
    }

    override fun wrap(entity: InnerCityEntity) {
        id = entity.id
        positionIndex = entity.positionIndex
        cityType = entity.cityType
        lv = entity.lv
        startTime = entity.startTime
        completeTime = entity.completeTime
        state = entity.state
        destroyTime = entity.destroyTime
        destroyState = entity.destroyState
        helpId = entity.helpId
        cityId = entity.cityId
        playerId = entity.playerId
    }
}