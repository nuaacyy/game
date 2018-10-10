package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.OneToOnePlayerAsset
import javax.persistence.*

const val BANK_ACCELERATE_NAMED_QUERY = "findBankAccelerateByPlayerId"

@NamedQueries(
    NamedQuery(
        name = BANK_ACCELERATE_NAMED_QUERY,
        // language=HQL
        query = "from BankAccelerateEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "bankAccelerates")
data class BankAccelerateEntity(
    @Id
    @Column(name = "player_id", nullable = false)
    override var playerId: Long,         // 玩家ID

    @Column(name = "use_plan", nullable = false)
    var usePlan: Int,                   // 使用的方案

    @Column(name = "use_time", nullable = false)
    var useTime: Long,       // 投资的真钻

    @Column(name = "use_props", nullable = false)
    var useProps: String,       // 投资的绑钻

    @Column(name = "over_time", nullable = false)
    var overTime: Long,      // 投资到点时间

    @Column(name = "is_mail", nullable = false)
    var isMail: Int,                   // 是否已经发送过邮件通知  0-没有  1-是的

    @Column(name = "props", nullable = false)
    var props: String                   // 投资时利率
) : OneToOnePlayerAsset {
    constructor() : this( 0,0, 0, "", 0, 0, "")
}
