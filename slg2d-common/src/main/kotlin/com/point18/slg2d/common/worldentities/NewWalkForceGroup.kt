package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val WALK_FORCE_GROUP_NAMED_QUERY = "findWalkForceGroupByWorldId"

@NamedQueries(
    NamedQuery(
        name = WALK_FORCE_GROUP_NAMED_QUERY,
        // language=HQL
        query = "from WalkForceGroupEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "walk_force_groups")
data class WalkForceGroupEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "is_get_main_hero", nullable = false)
    var isGetMainHero: Int,            // 用于PVP胜利之后的回城,是否抓到了敌军领主,要出现一个囚车动画跟着 0- 没有  1-有抓到

    @Column(name = "running_state", nullable = false)
    var runningState: Int,  // 行军中/驻扎中

    @Column(name = "state_change_time", nullable = false)
    var stateChangeTime: Long,  // 状态变化时间

    @Column(name = "goto_run_type", nullable = false)
    var gotoRunType: Int,  // 前往时的行军类别

    @Column(name = "now_x", nullable = false)
    var nowX: Int,  // 这个部队当前在哪里

    @Column(name = "now_y", nullable = false)
    var nowY: Int,  // 这个部队当前在哪里

    @Column(name = "mass_id", nullable = false)
    var massId: Long,

    @Column(name = "main_player_id", nullable = false)
    var mainPlayerId: Long  // 这个行军主体的操作权玩家ID
) : WorldAsset {
    constructor() : this(
        0,
        0, 0, 0, 0, 0, 0, 0, 0, 0
    )

    override fun primaryKey() = id
}