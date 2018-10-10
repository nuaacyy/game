package com.point18.slg2d.common.publicentities

import com.point18.slg2d.common.persistence.AllianceAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Index
import javax.persistence.*

const val ALLIANCE_REQ_NAMED_QUERY = "findAllianceReq"

@NamedQueries(
    NamedQuery(
        name = ALLIANCE_REQ_NAMED_QUERY,
        // language=HQL
        query = "from AllianceReqEntity where allianceId = :allianceId"
    )
)

@Entity
@Table(name = "alliance_reqs")
data class AllianceReqEntity(
    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Id
    @Column(name = "alliance_id", nullable = false)
    @Index(name = "ALLIANCE_REQS_ALLIANCE_ID")
    override var allianceId: Long,

    @Column(name = "player_id", nullable = false)
    var playerId: Long,

    @Column(name = "player_name", nullable = false)
    var playerName: String,

    @Column(name = "player_photo", nullable = false)
    var playerPhoto: Int,

    @Column(name = "player_fight_value", nullable = false)
    var playerFightValue: Long,

    @Column(name = "req_time", nullable = false)
    var reqTime: Long,

    @Column(name = "plt_area_no", nullable = false)
    var pltAreaNo: Long,

    @Column(name = "area_no", nullable = false)
    var areaNo: Int

) : AllianceAsset {
    constructor() : this(0, 0, 0, "", 0, 0, 0, 0, 0)

    override fun primaryKey() = id
}
