package com.point18.slg2d.home.dc

import com.point18.slg2d.home.AbstractDataContainer
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.homeentities.BANK_ACCELERATE_NAMED_QUERY
import com.point18.slg2d.common.homeentities.BankAccelerateEntity
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.PlayerId
import java.io.Serializable
import java.time.Duration

class BankAccelerateDC : AbstractDataContainer<BankAccelerateEntity>() {

    var bankAccelerate: BankAccelerate? = null

    override fun initImpl(data: BankAccelerateEntity?, depDCRepo: DataContainerRepo) {
        if (data != null) {
            val bankWrap = wdb.recover(data) { BankAccelerate() }

            this.bankAccelerate = bankWrap
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): BankAccelerateEntity? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(BANK_ACCELERATE_NAMED_QUERY)
                .setLong("playerId", playerId)
                .listNoDup<BankAccelerateEntity>()
            if (list.isEmpty()) {
                null
            } else {
                list[0]
            }
        }
        return data
    }

    fun findBankQuickByPlayerId(): (BankAccelerate?) {
        return this.bankAccelerate
    }

    fun createBankQuick(
        overTime: Long,
        usePlan: Int,
        useTime: Long,
        useProps: HashMap<Int, Int>,
        props: Map<Int, Int>
    ): BankAccelerate {
        val propsMap= HashMap<Int,Int>(props)
        val bankAccelerate = BankAccelerate(usePlan, useTime, useProps, overTime, 0, propsMap, playerId)

        wdb.save(bankAccelerate)
        this.bankAccelerate = bankAccelerate
        return bankAccelerate
    }

    // 删除一条记录
    fun delBankQuickInfo() {
        val bankAccelerate = this.bankAccelerate
        if (bankAccelerate == null) {
            return
        }

        wdb.delete(bankAccelerate)
        this.bankAccelerate = null
    }
}

// 投资银行
class BankAccelerate(
    var usePlan: Int,                   // 使用的方案
    var useTime: Long,       // 投资的时间
    var useProps: HashMap<Int, Int>,       // 投资的时间
    var overTime: Long,      // 投资到点时间
    var isMail: Int,                   // 是否已经发送过邮件通知  0-没有  1-是的
    var props: HashMap<Int, Int>,                   // 收益
    var playerId: Long         // 玩家ID

) : EntityWrapper<BankAccelerateEntity> {
    constructor() : this( 0, 0, hashMapOf(), 0, 0, hashMapOf(), 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = playerId

    override fun toEntity(): BankAccelerateEntity {
        return BankAccelerateEntity(
            playerId,
            usePlan,
            useTime,
            toJson(useProps),
            overTime,
            isMail,
            toJson(props)
        )
    }

    override fun wrap(entity: BankAccelerateEntity) {
        playerId = entity.playerId
        usePlan = entity.usePlan
        useTime = entity.useTime
        useProps = toObj(entity.useProps)
        overTime = entity.overTime
        isMail = entity.isMail
        props = toObj(entity.props)
    }
}