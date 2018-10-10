package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val PLAYER_ACTIVITY_NAMED_QUERY = "findPlayerActivityByWorldId"

@NamedQueries(
    NamedQuery(
        name = PLAYER_ACTIVITY_NAMED_QUERY,
        // language=HQL
        query = "from PlayerActivityEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "player_activities")
data class PlayerActivityEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "activity_id", nullable = false)
    var activityId: Int,              // 活动模版ID

    @Column(name = "score", nullable = false)
    var score: Int,              // 本活动积分

    @Column(name = "now_target", nullable = false)
    var nowTarget: Int,              // 当前完成度(是一个中间值,比如条件是杀100个兵,本次击杀了99个.那么下次击杀1个也就可以完成1次积分获取了)

    @Column(name = "castle_lv", nullable = false)
    var castleLv: Int,              // 参加这个活动时的主堡等级

    @Column(name = "player_id", nullable = false)
    var playerId: Long    // 玩家ID
) : WorldAsset {
    constructor() : this(0, 0L, 0, 0, 0, 0, 0L)

    override fun primaryKey() = id
}