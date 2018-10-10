package com.point18.slg2d.home.dc

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.homeentities.*
import com.point18.slg2d.home.AbstractDataContainer
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration

class QuotaBagDC : AbstractDataContainer<QuotaBagEntity>() {

    lateinit var quotaBag: QuotaBag

    override fun loadAllFromDB(playerId: Long, dao: CommonDao): QuotaBagEntity? {
        return dao.findWithTransaction { session ->
            val list = session.getNamedQuery(QUOTA_BAG_NAMED_QUERY)
                .setLong("playerId", playerId)
                .listNoDup<QuotaBagEntity>()
            list.firstOrNull()
        }
    }

    override fun initImpl(data: QuotaBagEntity?, depDCRepo: DataContainerRepo) {
        if (data != null) {
            val quotaBagWrap = wdb.recover(QuotaBag::class.java, data)
            quotaBag = quotaBagWrap
        }
    }

    fun createRecord(playerId: Long) {
        quotaBag = QuotaBag(playerId, 0, getNowTime(), 0, 0)
        wdb.save(quotaBag)
    }
}

class QuotaBag(
    var playerId: Long, // 玩家编号
    var quotaBagId: Int, // 满额礼包ID(初始化0)
    var endTime: Long, // 上一次礼包结束时间(初始化为创建角色时间)
    var degree: Int, // 触发礼包后充值的钻石数量
    var rewardId: Int // 礼包奖励
) : EntityWrapper<QuotaBagEntity> {
    constructor() : this(0, 0, 0, 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = playerId

    override fun toEntity(): QuotaBagEntity {
        return QuotaBagEntity(
            playerId,
            quotaBagId,
            endTime,
            degree,
            rewardId
        )
    }

    override fun wrap(entity: QuotaBagEntity) {
        playerId = entity.playerId
        quotaBagId = entity.quotaBagId
        endTime = entity.endTime
        degree = entity.degree
        rewardId = entity.rewardId
    }

    fun refresh(quotaBagId: Int, endTime: Long, rewardId: Int) {
        this.quotaBagId = quotaBagId
        this.endTime = endTime
        this.rewardId = rewardId
    }

    fun isOverdue(): Boolean {
        return this.endTime < getNowTime()
    }

    fun addDegree(degree: Int) {
        this.degree += degree
    }

    fun finish() {
        this.endTime = getNowTime()
    }
}