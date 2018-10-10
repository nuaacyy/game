package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.PlayerAsset
import xyz.ariane.util.annotation.NoArgConstructor
import org.hibernate.annotations.Index
import javax.persistence.*

@NoArgConstructor
data class MarkPK(
    @Id
    @Column(name = "player_id")
    override var playerId: Long,

    @Id
    @Column(name = "id", nullable = false)
    var id: Long
) : PlayerAsset {
    override fun primaryKey() = this
}

const val MARK_NAMED_QUERY_PLAYER = "findMarkByPlayerId"

@NamedQueries(
    NamedQuery(
        name = MARK_NAMED_QUERY_PLAYER,
        // language=HQL
        query = "from MarkEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "marks")
@IdClass(MarkPK::class)
data class MarkEntity(
    @Id
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "x", nullable = false)
    var x: Int,  // 位置x

    @Column(name = "y", nullable = false)
    var y: Int,  // 位置y

    @Column(name = "markGroup", nullable = false)
    var group: Int,              // 分组

    @Column(name = "plt_area_no", nullable = false)
    var pltAreaNo: Int,  // 所属服务器  游戏内K轴

    @Column(name = "name", nullable = false, length = 100)
    var name: String,

    @Column(name = "player_id", nullable = false)
    @Index(name = "MARKS_PLAYER_ID")
    override var playerId: Long    // 玩家编号
) : PlayerAsset {
    constructor() : this(0, 0, 0, 0, 0, "", 0)

    override fun primaryKey() = MarkPK(playerId, id)
}