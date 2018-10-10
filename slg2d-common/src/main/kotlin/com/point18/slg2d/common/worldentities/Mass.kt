package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val MASS_NAMED_QUERY = "findMassByWorldId"

@NamedQueries(
    NamedQuery(
        name = MASS_NAMED_QUERY,
        // language=HQL
        query = "from MassEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "masses")
data class MassEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "main_player_id", nullable = false)
    var mainPlayerId: Long,     // 团长ID

    @Column(name = "fight_type", nullable = false)
    var fightType: Int,                // 战斗类别 打人/打地/打城

    @Column(name = "mass_name", nullable = false, length = 100)
    var massName: String,       //集结名称

    @Column(name = "goto_x", nullable = false)
    var gotoX: Int,                // 前往的集结X坐标

    @Column(name = "goto_y", nullable = false)
    var gotoY: Int,                // 前往的集结Y坐标

    @Column(name = "start_x", nullable = false)
    var startX: Int,                // 团长集合的X坐标

    @Column(name = "start_y", nullable = false)
    var startY: Int,                // 团长集合的Y坐标

    @Column(name = "go_time", nullable = false)
    var goTime: Long,  // 集结时间

    @Column(name = "mass_start_time", nullable = false)
    var massStartTime: Long,  //集结开始时间，出发时为发车时间

    @Column(name = "mass_state", nullable = false)
    var massState: Int,                //1-集结中 2-等待中 3-出征中

    @Column(name = "group_id", nullable = false)
    var groupId: Long,                //出发后的行军组Id

    @Column(name = "arrive_time", nullable = false)
    var arriveTime: Long,  //到达时间

    @Column(name = "membere_infos", nullable = false)
    var membereInfos: String,     //战斗成员信息

    @Column(name = "alliance_id", nullable = false)
    var allianceId: Long  // 所属联盟ID
) : WorldAsset {
    constructor() : this(
        0,
        0, 0, 0, "", 0, 0, 0, 0, 0, 0, 0, 0,
        0, "", 0
    )

    override fun primaryKey() = id
}