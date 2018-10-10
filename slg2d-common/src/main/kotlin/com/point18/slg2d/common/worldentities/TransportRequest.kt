package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val TRANSPORT_REQUEST_NAMED_QUERY = "findTransportRequestByWorldId"

@NamedQueries(
    NamedQuery(
        name = TRANSPORT_REQUEST_NAMED_QUERY,
        // language=HQL
        query = "from TransportRequestEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "transport_requests")
data class TransportRequestEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "res", nullable = false, length = 1000)
    var res: String,  // 请求运输的资源

    @Column(name = "alliance_id", nullable = false)
    var allianceId: Long,  //联盟Id

    @Column(name = "player_id", nullable = false)
    var playerId: Long            // 玩家ID
) : WorldAsset {
    constructor() : this(0, 0L, "", 0L, 0L)

    override fun primaryKey() = id
}