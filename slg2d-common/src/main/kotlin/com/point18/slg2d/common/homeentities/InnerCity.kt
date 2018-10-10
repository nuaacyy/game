package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.PlayerAsset
import xyz.ariane.util.annotation.NoArgConstructor
import org.hibernate.annotations.Index
import javax.persistence.*

@NoArgConstructor
data class InnerCityPK(
    @Id
    @Column(name = "player_id")
    override var playerId: Long,

    @Id
    @Column(name = "id", nullable = false)
    var id: Long
) : PlayerAsset {
    override fun primaryKey() = this
}

const val INNER_CITY_NAMED_QUERY_PLAYER = "findInnerCityByPlayerId"

@NamedQueries(
    NamedQuery(
        name = INNER_CITY_NAMED_QUERY_PLAYER,
        // language=HQL
        query = "from InnerCityEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "inner_citys")
@IdClass(InnerCityPK::class)
data class InnerCityEntity(
    // 建筑唯一ID
    @Id
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "position_index", nullable = false)
    var positionIndex: Int,  // 位置索引

    @Column(name = "city_type", nullable = false)
    var cityType: Int,  // 类型

    @Column(name = "lv", nullable = false)
    var lv: Int,  // 等级

    @Column(name = "start_time", nullable = false)
    var startTime: Long,  // 建筑升级开始时间

    @Column(name = "complete_time", nullable = false)
    var completeTime: Long,  // 建筑升级完成时间

    @Column(name = "state", nullable = false)
    var state: Int,                // 建筑升级状态

    @Column(name = "destroy_time", nullable = false)
    var destroyTime: Long,  // 建筑拆除完成时间

    @Column(name = "destroy_state", nullable = false)
    var destroyState: Int,                // 建筑拆除状态

    @Column(name = "helper_ids", nullable = false, length = 5000)
    var helperIds: String,  // 帮忙加速过的玩家

    @Column(name = "help_id", nullable = false)
    var helpId: Long,  // 对应的帮派帮助表里的ID

    @Column(name = "city_id", nullable = false)
    var cityId: Long,  // 所属城池Id（关联的城池Id）

    @Column(name = "player_id", nullable = false)
    @Index(name = "INNER_CITYS_PLAYER_ID")
    override var playerId: Long    // 所属玩家Id
) : PlayerAsset {
    constructor() : this(
        0, 0, 0, 0, 0, 0, 0, 0, 0
        , "", 0, 0, 0
    )

    override fun primaryKey() = InnerCityPK(playerId, id)
}

