package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.PlayerAsset
import xyz.ariane.util.annotation.NoArgConstructor
import org.hibernate.annotations.Index
import javax.persistence.*

@NoArgConstructor
data class FurniturePK(
    @Id
    @Column(name = "player_id")
    override var playerId: Long,

    @Id
    @Column(name = "id", nullable = false)
    var id: Long
) : PlayerAsset {
    override fun primaryKey() = this
}

const val FURNITURE_NAMED_QUERY_PLAYER = "findFurnitureByPlayerId"

@NamedQueries(
    NamedQuery(
        name = FURNITURE_NAMED_QUERY_PLAYER,
        // language=HQL
        query = "from FurnitureEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "furniture")
@IdClass(FurniturePK::class)
data class FurnitureEntity(

    // 家具唯一ID
    @Id
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "floor_idx", nullable = false)
    var floorIdx: Int,    // 楼层 1-1F 2-2F

    @Column(name = "x", nullable = false)
    var x: Int,    // 家具x坐标

    @Column(name = "y", nullable = false)
    var y: Int,    // 家具y坐标

    @Column(name = "direction", nullable = false)
    var direction: Int,    // 家具朝向

    @Column(name = "start_time", nullable = false)
    var startTime: Long,    // 开始收集时间

    @Column(name = "check_time", nullable = false)
    var checkTime: Long,    // 刷新收集时间

    @Column(name = "end_time", nullable = false)
    var endTime: Long,    // 刷新满时间

    @Column(name = "produce_res", nullable = false, length = 200)
    var produceRes: String,   // 产出资源数量

    @Column(name = "proto_id", nullable = false)
    var protoId: Int,    // 家具类型

    @Column(name = "state", nullable = false)
    var state: Int,    // 家具状态

    @Column(name = "player_id", nullable = false)
    @Index(name = "FURNITURE_PLAYER_ID")
    override var playerId: Long    // 所属玩家Id
) : PlayerAsset {
    constructor() : this(
        0, 0, 0, 0, 0,
        0, 0, 0,
        "", 0, 0, 0
    )

    override fun primaryKey() = FurniturePK(playerId, id)
}

