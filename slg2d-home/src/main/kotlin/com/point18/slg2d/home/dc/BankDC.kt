package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.*
import com.point18.slg2d.home.AbstractDataContainer
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.constg.PlayerId
import java.io.Serializable
import java.time.Duration

class BankDC : AbstractDataContainer<BankEntity>() {

    var bank: Bank? = null

    override fun initImpl(data: BankEntity?, depDCRepo: DataContainerRepo) {
        if (data != null) {
            val bankWrap = wdb.recover(data) { Bank() }

            this.bank = bankWrap
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): BankEntity? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(BANK_NAMED_QUERY)
                .setLong("playerId", playerId)
                .listNoDup<BankEntity>()
            if (list.isEmpty()) {
                null
            } else {
                list[0]
            }
        }
        return data
    }

    fun findBankByPlayerId(): (Bank?) {
        return this.bank

    }

    fun createBank(
        overTime: Long,
        usePlan: Int,
        useGold: Long,
        useBindGold: Long,
        rate: Int
    ): (Bank) {
        val bank = Bank(usePlan, useGold, useBindGold, overTime, 0, rate, playerId)

        wdb.save(bank)
        this.bank = bank
        return bank
    }

    // 删除一条记录
    fun delBankInfo() {
        val bank = this.bank
        if (bank == null) {
            return
        }

        wdb.delete(bank)
        this.bank = null
    }

}

// 投资银行
class Bank(
    var usePlan: Int,                   // 使用的方案
    var useMoney: Long,       // 投资的真钻
    var useBindMoney: Long,       // 投资的绑钻
    var overTime: Long,      // 投资到点时间
    var isMail: Int,                   // 是否已经发送过邮件通知  0-没有  1-是的
    var rate: Int,                   // 投资时利率
    var playerId: Long         // 玩家ID
) : EntityWrapper<BankEntity> {
    constructor() : this( 0, 0, 0, 0, 0, 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = playerId

    override fun toEntity(): BankEntity {
        return BankEntity(
            playerId,
            usePlan,
            useMoney,
            useBindMoney,
            overTime,
            isMail,
            rate
        )
    }

    override fun wrap(entity: BankEntity) {
        playerId = entity.playerId
        usePlan = entity.usePlan
        useMoney = entity.useMoney
        useBindMoney = entity.useBindMoney
        overTime = entity.overTime
        isMail = entity.isMail
        rate = entity.rate
    }
}

