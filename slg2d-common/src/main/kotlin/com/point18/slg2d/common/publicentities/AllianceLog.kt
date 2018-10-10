package com.point18.slg2d.common.publicentities

import com.point18.slg2d.common.persistence.AllianceAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Index
import javax.persistence.*

const val ALLIANCE_LOG_NAMED_QUERY = "findAllianceLog"

@NamedQueries(
    NamedQuery(
        name = ALLIANCE_LOG_NAMED_QUERY,
        // language=HQL
        query = "from AllianceLogEntity where allianceId = :allianceId"
    )
)
@Entity
@Table(name = "alliance_logs")
data class AllianceLogEntity(
    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Id
    @Column(name = "alliance_id", nullable = false)
    @Index(name = "ALLIANCE_LOGS_ALLIANCE_ID")
    override var allianceId: Long,

    @Column(name = "once_key", nullable = false)
    var onceKey: String,

    @Column(name = "log_type", nullable = false)
    var logType: Int,

    @Column(name = "log_info", nullable = false)
    var logInfo: String,

    @Column(name = "log_time", nullable = false)
    var logTime: Long

) : AllianceAsset {
    constructor() : this(0, 0, "", 0, "", 0)

    override fun primaryKey() = id
}
