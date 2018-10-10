package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.PAY_NAMED_QUERY_PLAYER
import com.point18.slg2d.common.homeentities.PayEntity
import com.point18.slg2d.common.homeentities.PayPK
import com.point18.slg2d.home.AbstractDataContainer
import com.point18.slg2d.common.mcache.OneKeyIndex
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.constg.PlayerId
import java.io.Serializable
import java.time.Duration
import java.util.*

class PayDC : AbstractDataContainer<List<PayEntity>>() {

    val payMap = OneKeyIndex<Long, Pay> { it.id }

    override fun initImpl(data: List<PayEntity>?, depDCRepo: DataContainerRepo) {
        data?.map {
            val payWrap = wdb.recover(it) { Pay() }

            payMap.insertByKey(payWrap)
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<PayEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(PAY_NAMED_QUERY_PLAYER)
                .setLong("playerId", playerId)
                .listNoDup<PayEntity>()
            list
        }
        return data
    }

    fun createPay(playerId: Long, onlyId: Long, payId: Int): Pay {
        val pay = Pay(
            onlyId,
            payId,
            0,//time.Now(),
            playerId
        )

        wdb.save(pay)
        this.payMap.insertByKey(pay)

        return pay
    }

    // 根据ID拿信息
    fun findPayById(id: Long): Pay? {
        return this.payMap.findByKey(id)
    }

    // 拿到玩家所有的充值信息
    fun findAllPay(): LinkedList<Pay> {
        val allPay = LinkedList<Pay>()
        this.payMap.map {
            allPay.add(it)
        }
        return allPay
    }
}

class Pay(
    var id: Long,

    var payId: Int,               // pay.xml ID
    var payTime: Int,  // 充值的时间
    var playerId: Long            // 玩家ID
) : EntityWrapper<PayEntity> {
    constructor() : this(0, 0, 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = PayPK(playerId, id)

    override fun toEntity(): PayEntity {
        return PayEntity(
            id,
            payId,
            payTime,
            playerId
        )
    }

    override fun wrap(entity: PayEntity) {
        id = entity.id
        payId = entity.payId
        payTime = entity.payTime
        playerId = entity.playerId
    }
}