package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val WALK_NAMED_QUERY = "findWalkByWorldId"

@NamedQueries(
    NamedQuery(
        name = WALK_NAMED_QUERY,
        // language=HQL
        query = "from WalkEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "walks")
data class WalkEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long, //唯一Id

    @Column(name = "march_state", nullable = false)
    var marchState: Int,               //出征状态

    @Column(name = "march_time_off", nullable = false)
    var marchTimeOff: Long,  //出征时间

    @Column(name = "march_time_arrival", nullable = false)
    var marchTimeArrival: Long,  //到达时间

    @Column(name = "march_place_x", nullable = false)
    var marchPlaceX: Int,               //出发地X

    @Column(name = "march_place_y", nullable = false)
    var marchPlaceY: Int,               //出发地Y

    @Column(name = "march_aims_x", nullable = false)
    var marchAimsX: Int,               //目标地X

    @Column(name = "march_aims_y", nullable = false)
    var marchAimsY: Int,               //目标地Y

    @Column(name = "initial_walk_time", nullable = false)
    var initialWalkTime: Int,  //初始的行军时间

    @Column(name = "walk_force_group_id", nullable = false)
    var walkForceGroupId: Long,  // 行军线中的行军主体ID

    @Column(name = "is_atk_monster_home", nullable = false)
    var isAtkMonsterHome: Int,  //是否攻击魔物回城

    @Column(name = "walk_speed", nullable = false)
    var walkSpeed: Double,  //行军速度

    @Column(name = "now_walk_robot_x", nullable = false)
    var nowWalkRobotX: Int,        //`sql:"-"` // 行军小人当前所在的大地图大坐标X

    @Column(name = "now_walk_robot_y", nullable = false)
    var nowWalkRobotY: Int,    //`sql:"-"` // 行军小人当前所在的大地图大坐标Y

    @Column(name = "is_conflict", nullable = false)
    var isConflict: Int //`sql:"-"` //是否行军冲突 0、否 1、是
) : WorldAsset {

    constructor() : this(
        0,
        0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0.0, 0, 0, 0
    )

    override fun primaryKey() = id
}