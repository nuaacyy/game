package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val FAME_HALL_NAMED_QUERY = "findFameHallByWorldId"

@NamedQueries(
    NamedQuery(
        name = FAME_HALL_NAMED_QUERY,
        // language=HQL
        query = "from FameHallEntity where worldId = :worldId"
    )
)

@Entity
@Table(name = "fame_hall")
data class FameHallEntity(

    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "alliance_name", nullable = false, length = 50)
    var allianceName: String,   // 联盟名称

    @Column(name = "alliance_short_name", nullable = false, length = 50)
    var allianceShortName: String,   // 联盟名称

    @Column(name = "player_name", nullable = false, length = 50)
    var playerName: String,     // 玩家名称

    @Column(name = "player_photo_id", nullable = false)
    var playerPhotoId: Int,     // 玩家头像Id

    @Column(name = "occupy_time", nullable = false)
    var occupyTime: Long,       // 占领时长

    @Column(name = "create_time", nullable = false)
    var createTime: Long,       // 占领时间

    @Column(name = "alliance_id", nullable = false)
    var allianceId: Long,       //联盟ID

    @Column(name = "player_id", nullable = false)
    var playerId: Long    // 玩家ID
) : WorldAsset {

    constructor() : this(
        0, 0,  "", "", "", 0,
        0, 0, 0, 0
    )

    override fun primaryKey() = id
}