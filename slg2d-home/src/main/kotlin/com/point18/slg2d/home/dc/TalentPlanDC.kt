package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.TALENT_PLAN_NAMED_QUERY_PLAYER
import com.point18.slg2d.common.homeentities.TalentPlanEntity
import com.point18.slg2d.common.homeentities.TalentPlanPK
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

// 天赋
class TalentPlanDC : AbstractDataContainer<List<TalentPlanEntity>>() {
    val talentPlans = hashMapOf<Long,TalentPlan>()


    override fun initImpl(data: List<TalentPlanEntity>?, depDCRepo: DataContainerRepo) {
        data?.forEach {
            val talentPlanWrap = wdb.recover(it) { TalentPlan() }
            talentPlans[talentPlanWrap.id] = talentPlanWrap

        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<TalentPlanEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(TALENT_PLAN_NAMED_QUERY_PLAYER)
                .setLong("playerId", playerId)
                .listNoDup<TalentPlanEntity>()
            list
        }
        return data
    }


    fun createTalentPlan(planId: Int, planName: String, plan: HashMap<Int, Int>, playerId: Long) {

        val id = hpm.generateObjIdNew()
        val p = TalentPlan(
            id,
            planId,
            planName,
            plan,
            playerId
        )

        wdb.save(p)

        // 保存到缓存
        talentPlans[id] = p
    }

    // 删除
    fun deleteTalentPlan(talentPlan: TalentPlan) {
        if (talentPlan.id == 0L) {
            return
        }

        wdb.delete(talentPlan)

        // 从缓存移除
        talentPlans.remove(talentPlan.id)
    }

    fun findTalentPlansByPlayerId(playerId: Long): LinkedList<TalentPlan> {
        val plans = LinkedList<TalentPlan>()
        talentPlans.values.forEach{
            plans.add(it)
        }
        return plans
    }

    fun findTalentPlanByPlayerIdAndId(playerId: Long, planId: Int): TalentPlan? {
        val rt: TalentPlan? = talentPlans.values.firstOrNull { it.planId == planId }
        return rt
    }

}

class TalentPlan(
    var id: Long,

    var planId: Int,          // 排序ID
    var planName: String,  // 预设名字
    var planMap: HashMap<Int, Int>, // 预设内容  KEY : 天赋ID  VALUE : 天赋等级
    var playerId: Long   // 玩家ID
) : EntityWrapper<TalentPlanEntity> {
    constructor() : this(0, 0, "", hashMapOf(), 0L)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = TalentPlanPK(playerId, id)

    override fun toEntity(): TalentPlanEntity {
        return TalentPlanEntity(
            id,
            planId,
            planName,
            toJson(planMap),
            playerId
        )
    }

    override fun wrap(entity: TalentPlanEntity) {
        id = entity.id
        planId = entity.planId
        planName = entity.planName
        planMap = toObj(entity.plan)
        playerId = entity.playerId
    }
}