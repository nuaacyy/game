package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.PlayerAsset
import xyz.ariane.util.annotation.NoArgConstructor
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@NoArgConstructor
data class BattleReportPK(
    @Id
    @Column(name = "player_id")
    override var playerId: Long,

    @Id
    @Column(name = "id", nullable = false)
    var id: Long
) : PlayerAsset {
    override fun primaryKey() = this
}

const val BATTLE_REPORT_NAMED_QUERY = "findBattleReportByPlayerId"

@NamedQueries(
    NamedQuery(
        name = BATTLE_REPORT_NAMED_QUERY,
        // language=HQL
        query = "from BattleReportEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "battle_reports")
@IdClass(BattleReportPK::class)
data class BattleReportEntity(
    // 战斗数据
    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "read_state", nullable = false)
    var readState: Int,               // 阅读状态  0-未读 1-已读

    @Column(name = "archived", nullable = false)
    var archived: Long,  // 收藏时间。0：未收藏，1：收藏

    @Column(name = "report_type", nullable = false)
    var reportType: Int,               // 战报类型

    @Column(name = "fight_time", nullable = false)
    var fightTime: Long,  // 战斗时间

    @Column(name = "fight_address_x", nullable = false)
    var fightAddressX: Int,               // 战斗地点

    @Column(name = "fight_address_y", nullable = false)
    var fightAddressY: Int,               // 战斗地点

    @Column(name = "past_time", nullable = false)
    var pastTime: Long,  // 过期时间

    @Column(name = "report_content", nullable = false, columnDefinition = "blob")
    var reportContent: ByteArray,  // 战报内容

    @Column(name = "fight_detail", nullable = false, columnDefinition = "MediumBlob")
    var fightDetail: ByteArray,  // 战斗过程

    @Column(name = "player_id", nullable = false)
    override var playerId: Long  // 玩家ID，
) : PlayerAsset {
    constructor() : this(0, 0, 0, 0, 0, 0, 0, 0, byteArrayOf(), "".toByteArray(), 0)

    override fun primaryKey() = BattleReportPK(playerId, id)
}