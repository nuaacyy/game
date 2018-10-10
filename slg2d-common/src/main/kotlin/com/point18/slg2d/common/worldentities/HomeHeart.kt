package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val HOME_HEART_NAMED_QUERY = "findHomeHeartByWorldId"

@NamedQueries(
    NamedQuery(
        name = HOME_HEART_NAMED_QUERY,
        // language=HQL
        query = "from HomeHeartEntity where worldId = :worldId"
    )
)

@Entity
@Table(name = "home_hearts")
data class HomeHeartEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "player_id", nullable = false)
    var playerId: Long,

    @Column(name = "home_action", nullable = false)
    var homeAction: Int,    //home服行为类别

    @Column(name = "action_id", nullable = false)
    var actionId: Long,     //对应行为的唯一Id

    @Column(name = "trigger_time", nullable = false)
    var triggerTime: Long,  //触发时间

    @Column(name = "state", nullable = false)
    var state: Int          //心跳状态，处理丢包等异常情况
) : WorldAsset {

    constructor() : this(
        0, 0, 0, 0, 0, 0, 0
    )

    override fun primaryKey() = id
}