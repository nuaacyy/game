package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.KIING_EQUIP_PLAN_NAMED_QUERY
import com.point18.slg2d.common.homeentities.KingEquipPlanEntity
import com.point18.slg2d.common.homeentities.KingEquipPlanPK
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

class KingEquipPlanDC : AbstractDataContainer<List<KingEquipPlanEntity>>() {
    val kingEquipPlans = LinkedList<KingEquipPlan>()

    override fun initImpl(data: List<KingEquipPlanEntity>?, depDCRepo: DataContainerRepo) {
        data?.map {
            val kingEquipPlan = wdb.recover(it) { KingEquipPlan() }

            kingEquipPlans += kingEquipPlan
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<KingEquipPlanEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(KIING_EQUIP_PLAN_NAMED_QUERY)
                .setLong("playerId", playerId)
                .listNoDup<KingEquipPlanEntity>()
            list
        }
        return data
    }

    fun createKingEquipPlan(
        planId: Int,
        planName: String,
        planParam: HashMap<Long, Int>,
        playerId: Long
    ): KingEquipPlan {
        val id = hpm.generateObjIdNew()
        val plan = KingEquipPlan(
            id,
            planId,
            planName,
            playerId
        )
        plan.planMap = planParam

        wdb.save(plan)

        // 保存到缓存
        kingEquipPlans += plan

        return plan
    }

    // 删除联盟标记
    fun deleteKingEquipPlan(kingEquipPlan: KingEquipPlan) {
        if (kingEquipPlan.id == 0L) {
            return
        }

        wdb.delete(kingEquipPlan)

        // 从缓存移除
        kingEquipPlans.remove(kingEquipPlan)
    }

    fun findKingEquipPlansByPlayerId(): LinkedList<KingEquipPlan> {
        return kingEquipPlans
    }

    fun findKingEquipPlanByPlayerIdAndId(planId: Int): KingEquipPlan? {
        for (plan in kingEquipPlans) {
            if (plan.planId == planId) {
                return plan
            }
        }

        return null
    }
}

// 君主装备预设
class KingEquipPlan(
    var id: Long,

    var planId: Int,          // 排序ID
    var planName: String,  // 预设名字
    var playerId: Long    // 玩家ID
) : EntityWrapper<KingEquipPlanEntity> {
    var planMap: HashMap<Long, Int> = hashMapOf() //`sql:"-"`                   // 预设内容  KEY : 物品唯一ID  VALUE : 君主槽位

    constructor() : this(
        0, 0, "", 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = KingEquipPlanPK(playerId, id)

    override fun toEntity(): KingEquipPlanEntity {
        return KingEquipPlanEntity(
            id,
            planId,
            planName,
            toJson(planMap),
            playerId
        )
    }

    override fun wrap(entity: KingEquipPlanEntity) {
        id = entity.id
        planId = entity.planId
        planName = entity.planName
        playerId = entity.playerId
        planMap = toObj(entity.plan)
    }
}
