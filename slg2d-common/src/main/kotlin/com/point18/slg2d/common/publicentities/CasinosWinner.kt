package com.point18.slg2d.common.publicentities

import com.point18.slg2d.common.persistence.PublicAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Index
import javax.persistence.*

const val PUBLIC_WINNER_ID_QUERY = "findCasinosWinner"

@NamedQueries(
    NamedQuery(
        name = PUBLIC_WINNER_ID_QUERY,
        // language=HQL
        query = "from CasinosWinnerEntity where publicId = :publicId"
    )
)
@Entity
@Table(name = "casinos_winner")
data class CasinosWinnerEntity(
    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Id
    @Column(name = "public_id", nullable = false)
    @Index(name = "CASINOS_WINNER_PUBLIC_ID")
    override var publicId: Long,

    @Column(name = "area_no", nullable = false)
    var areaNo: Int,

    @Column(name = "player_name", nullable = false)
    var playerName: String,

    @Column(name = "alliance_short_name", nullable = false)
    var allianceShortName: String,

    @Column(name = "reward_time", nullable = false)
    var rewardTime: Long,

    @Column(name = "gift_num", nullable = false)
    var giftNum: Long,

    @Column(name = "player_id", nullable = false)
    var playerId: Long

) : PublicAsset {
    constructor() : this(0, 0, 0, "", "", 0, 0, 0)

    override fun primaryKey() = id
}