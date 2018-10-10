package com.point18.slg2d.common.publicentities

import com.point18.slg2d.common.persistence.AllianceAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Index
import org.hibernate.annotations.Type
import javax.persistence.*

const val ALLIANCE_HELP_NAMED_QUERY = "findAllianceHelp"

@NamedQueries(
    NamedQuery(
        name = ALLIANCE_HELP_NAMED_QUERY,
        // language=HQL
        query = "from AllianceHelpEntity where allianceId = :allianceId"
    )
)
@Entity
@Table(name = "alliance_helps")
data class AllianceHelpEntity(
    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Id
    @Column(name = "alliance_id", nullable = false)
    @Index(name = "ALLIANCE_HELPS_ALLIANCE_ID")
    override var allianceId: Long,

    @Column(name = "help_type", nullable = false)
    var helpType: Int,

    @Column(name = "now_help_num", nullable = false)
    var nowHelpNum: Int,

    @Column(name = "help_player_id", nullable = false)
    var helpPlayerId: Long,

    @Column(name = "help_value1", nullable = false)
    var helpValue1: Long,

    @Column(name = "help_value2", nullable = false)
    var helpValue2: Long,

    @Column(name = "help_value3", nullable = false)
    var helpValue3: Long,

    @Column(name = "help_value4", nullable = false)
    var helpValue4: Long,

    @Column(name = "send_time", nullable = false)
    var sendTime: Long,

    @Type(type = "text")
    @Column(name = "helper_ids", nullable = false)
    var helperIds: String

) : AllianceAsset {
    constructor() : this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "")

    override fun primaryKey() = id
}