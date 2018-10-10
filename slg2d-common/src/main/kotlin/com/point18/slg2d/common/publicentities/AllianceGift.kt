package com.point18.slg2d.common.publicentities

import com.point18.slg2d.common.persistence.AllianceAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Index
import javax.persistence.*

const val ALLIANCE_GIFT_NAMED_QUERY = "findAllianceGift"

@NamedQueries(
    NamedQuery(
        name = ALLIANCE_GIFT_NAMED_QUERY,
        // language=HQL
        query = "from AllianceGiftEntity where allianceId = :allianceId"
    )
)
@Entity
@Table(name = "alliance_gifts")
data class AllianceGiftEntity(
    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Id
    @Column(name = "alliance_id", nullable = false)
    @Index(name = "ALLIANCE_GIFTS_ALLIANCE_ID")
    override var allianceId: Long,

    @Column(name = "big_gift_id", nullable = false)
    var bigGiftId: Int,

    @Column(name = "big_gift_exp", nullable = false)
    var bigGiftExp: Int,

    @Column(name = "gift_lv", nullable = false)
    var giftLv: Int,

    @Column(name = "gift_exp", nullable = false)
    var giftExp: Int

) : AllianceAsset {
    constructor() : this(0, 0, 0, 0, 0, 0)

    override fun primaryKey() = id
}