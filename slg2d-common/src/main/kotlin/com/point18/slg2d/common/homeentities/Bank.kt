package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.OneToOnePlayerAsset
import javax.persistence.*

const val BANK_NAMED_QUERY = "findBankByPlayerId"

@NamedQueries(
    NamedQuery(
        name = BANK_NAMED_QUERY,
        // language=HQL
        query = "from BankEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "banks")
data class BankEntity(
    @Id
    @Column(name = "player_id", nullable = false)
    override var playerId: Long,         // 玩家ID

    @Column(name = "use_plan", nullable = false)
    var usePlan: Int,                   // 使用的方案

    @Column(name = "use_money", nullable = false)
    var useMoney: Long,       // 投资的真钻

    @Column(name = "use_bind_money", nullable = false)
    var useBindMoney: Long,       // 投资的绑钻

    @Column(name = "over_time", nullable = false)
    var overTime: Long,      // 投资到点时间

    @Column(name = "is_mail", nullable = false)
    var isMail: Int,                   // 是否已经发送过邮件通知  0-没有  1-是的

    @Column(name = "rate", nullable = false)
    var rate: Int                   // 投资时利率

) : OneToOnePlayerAsset {
    constructor() : this(0, 0, 0, 0, 0, 0, 0)
}
