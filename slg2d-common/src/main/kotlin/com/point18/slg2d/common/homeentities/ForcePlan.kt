package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.PlayerAsset
import xyz.ariane.util.annotation.NoArgConstructor
import org.hibernate.annotations.Index
import javax.persistence.*

@NoArgConstructor
data class ForcePlanPK(
    @Id
    @Column(name = "player_id")
    override var playerId: Long,

    @Id
    @Column(name = "id", nullable = false)
    var id: Long
) : PlayerAsset {
    override fun primaryKey() = this
}

const val FORCE_PLAN_NAMED_QUERY_PLAYER = "findForcePlanByPlayerId"

@NamedQueries(
    NamedQuery(
        name = FORCE_PLAN_NAMED_QUERY_PLAYER,
        // language=HQL
        query = "from ForcePlanEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "force_plans")
@IdClass(ForcePlanPK::class)
data class ForcePlanEntity(
    @Id
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "plan_id", nullable = false)
    var planId: Int,          // 排序ID

    @Column(name = "plan_name", nullable = false, length = 50)
    var planName: String,  // 预设名字

    @Column(name = "plan", nullable = false, length = 5000)
    var plan: String,  // 预设内容

    @Column(name = "player_id", nullable = false)
    @Index(name = "FORCE_PLANS_PLAYER_ID")
    override var playerId: Long   // 玩家ID
) : PlayerAsset {
    constructor() : this(0, 0, "", "", 0)

    override fun primaryKey() = ForcePlanPK(playerId, id)
}