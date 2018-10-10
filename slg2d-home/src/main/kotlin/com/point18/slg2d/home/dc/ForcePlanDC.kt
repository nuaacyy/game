package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.FORCE_PLAN_NAMED_QUERY_PLAYER
import com.point18.slg2d.common.homeentities.ForcePlanEntity
import com.point18.slg2d.common.homeentities.ForcePlanPK
import com.point18.slg2d.home.AbstractDataContainer
import com.point18.slg2d.home.hpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.PlayerId
import java.io.Serializable
import java.time.Duration
import java.util.*

// 君主装备预设

class ForcePlanDC : AbstractDataContainer<List<ForcePlanEntity>>() {

    val forcePlanMap = hashMapOf<Int, ForcePlan>()

    override fun initImpl(data: List<ForcePlanEntity>?, depDCRepo: DataContainerRepo) {
        data?.forEach {
            val forcePlan = wdb.recover(it) { ForcePlan() }

            forcePlanMap[forcePlan.planId] = forcePlan
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<ForcePlanEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(FORCE_PLAN_NAMED_QUERY_PLAYER)
                .setLong("playerId", playerId)
                .listNoDup<ForcePlanEntity>()
            list
        }
        return data
    }

    fun createForcePlan(
        planId: Int,
        planName: String,
        planVo: ForcePlanVo
    ): (ForcePlan) {
        val id = hpm.generateObjIdNew()
        val forcePlan = ForcePlan(id, planId, planName, planVo, playerId)

        wdb.save(forcePlan)
        this.forcePlanMap[planId] = forcePlan

        return forcePlan
    }
}

class ForcePlan(
    var id: Long,

    var planId: Int,          // 排序ID
    var planName: String,  // 预设名字
    var planMap: ForcePlanVo,// `sql:"-"`                   // 预设内容
    var playerId: Long   // 玩家ID
) : EntityWrapper<ForcePlanEntity> {
    constructor() : this(0, 0, "", ForcePlanVo(), 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = ForcePlanPK(playerId, id)

    override fun toEntity(): ForcePlanEntity {
        return ForcePlanEntity(
            id,
            planId,
            planName,
            toJson(planMap),
            playerId
        )
    }

    override fun wrap(entity: ForcePlanEntity) {
        id = entity.id
        planId = entity.planId
        planName = entity.planName
        planMap = toObj(entity.plan)
        playerId = entity.playerId
    }
}

class ForcePlanVo(
    var heroInfo: LinkedList<Long>,      // 武将唯一ID
    var soliderInfo: HashMap<Int, Int>// key 士兵模版ID  value 数量
) {
    constructor() : this(LinkedList(), hashMapOf())
}


