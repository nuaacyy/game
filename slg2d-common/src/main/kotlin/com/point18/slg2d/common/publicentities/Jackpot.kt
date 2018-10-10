package com.point18.slg2d.common.publicentities

import com.point18.slg2d.common.persistence.PublicAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Index
import javax.persistence.*

const val JACKPOT_QUERY = "jackpotquery"

@NamedQueries(
    NamedQuery(
        name = JACKPOT_QUERY,
        // language=HQL
        query = "from JackpotEntity where publicId = :publicId"
    )
)
@Entity
@Table(name = "jackpot")
data class JackpotEntity(

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Id
    @Column(name = "public_id", nullable = false)
    @Index(name = "JACKPOT_PUBLIC_ID")
    override var publicId: Long,

    @Column(name = "total_money", nullable = false, length = 100)
    var totalMoney: Long,

    @Column(name = "last_time", nullable = false, length = 100)
    var lastTime: Long,

    @Column(name = "last_refresh_time", nullable = false, length = 100)
    var lastRefreshTime: Long

) : PublicAsset {

    constructor() : this(
        0, 0, 0, 0, 0
    )

    override fun primaryKey() = id
}

