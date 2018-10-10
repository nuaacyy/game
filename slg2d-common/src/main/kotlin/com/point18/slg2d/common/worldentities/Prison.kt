package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val PRISON_NAMED_QUERY = "findPrisonByWorldId"

@NamedQueries(
    NamedQuery(
        name = PRISON_NAMED_QUERY,
        // language=HQL
        query = "from PrisonEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "prisons")
data class PrisonEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "prison_player_id", nullable = false)
    var prisonPlayerId: Long,  // 被监禁玩家ID

    @Column(name = "ransom", nullable = false)
    var ransom: Long,  // 设置的赎金

    @Column(name = "reward_gold", nullable = false)
    var rewardGold: Long,  // 设置的赏金

    @Column(name = "player_id", nullable = false)
    var playerId: Long  // 玩家ID
) : WorldAsset {
    constructor() : this(0,0L, 0L, 0L, 0L, 0L)

    override fun primaryKey() = id
}