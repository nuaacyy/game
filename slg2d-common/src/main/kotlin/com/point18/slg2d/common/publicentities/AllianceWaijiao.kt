package com.point18.slg2d.common.publicentities

import com.point18.slg2d.common.persistence.AllianceAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Index
import javax.persistence.*

const val ALLIANCE_WAIJIAO_NAMED_QUERY = "findAllianceWaijiao"

@NamedQueries(
    NamedQuery(
        name = ALLIANCE_WAIJIAO_NAMED_QUERY,
        // language=HQL
        query = "from AllianceWaijiaoEntity where allianceId = :allianceId"
    )
)
@Entity
@Table(name = "alliance_waijiaos")
data class AllianceWaijiaoEntity(
    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Id
    @Column(name = "alliance_id", nullable = false)
    @Index(name = "ALLIANCE_WAIJIAOS_ALLIANCE_ID")
    override var allianceId: Long,

    @Column(name = "flag_color", nullable = false)
    var flagColor: Int,

    @Column(name = "flag_style", nullable = false)
    var flagStyle: Int,

    @Column(name = "flag_effect", nullable = false)
    var flagEffect: Int,

    @Column(name = "aid", nullable = false)
    var aid: Long,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "short_name", nullable = false)
    var shortName: String,

    @Column(name = "player_id", nullable = false)
    var playerId: Long,

    @Column(name = "player_name", nullable = false)
    var playerName: String,

    @Column(name = "player_position", nullable = false)
    var playerPosition: String,

    @Column(name = "create_time", nullable = false)
    var createTime: Long,

    @Column(name = "waijiao_info", nullable = false)
    var waijiaoInfo: String,

    @Column(name = "area_no", nullable = false)
    var areaNo: Int,

    @Column(name = "photo_proto_id", nullable = false)
    var photoProtoId: Int,

    @Column(name = "nick_name", nullable = false, length = 100)
    var nickName: String

) : AllianceAsset {
    constructor() : this(0, 0, 0, 0, 0, 0, "", "", 0, "", "", 0, "", 0, 0, "")

    override fun primaryKey() = id
}