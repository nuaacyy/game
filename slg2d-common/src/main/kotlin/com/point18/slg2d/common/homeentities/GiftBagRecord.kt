package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.OneToOnePlayerAsset
import javax.persistence.*

const val GIFT_BAG_RECORD_NAMED_QUERY = "findGiftBagRecordByPlayerId"

@NamedQueries(
    NamedQuery(
        name = GIFT_BAG_RECORD_NAMED_QUERY,
        // language=HQL
        query = "from GiftBagRecordEntity where playerId = :playerId"
    )
)

@Entity
@Table(name = "gift_bag_records")
data class GiftBagRecordEntity(
    @Id
    @Column(name = "player_id", nullable = false)
    override var playerId: Long,

    @Column(name = "records", nullable = false, length = 100000)
    var records: String                                    // 礼包购买数据
) : OneToOnePlayerAsset {

    constructor() : this(
        0, ""
    )
}