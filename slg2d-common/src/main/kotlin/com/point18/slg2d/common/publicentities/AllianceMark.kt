package com.point18.slg2d.common.publicentities
import com.point18.slg2d.common.persistence.AllianceAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Index
import javax.persistence.*

const val ALLIANCE_MARK_NAMED_QUERY = "findAllianceMark"

@NamedQueries(
    NamedQuery(
        name = ALLIANCE_MARK_NAMED_QUERY,
        // language=HQL
        query = "from AllianceMarkEntity where allianceId = :allianceId"
    )
)
@Entity
@Table(name = "alliance_marks")
data class AllianceMarkEntity(
    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
     var id: Long,

    @Id
    @Column(name = "alliance_id", nullable = false)
    @Index(name = "ALLIANCE_MARKS_ALLIANCE_ID")
    override var allianceId: Long,

    @Column(name = "type", nullable = false)
    var type: Int,

    @Column(name = "player_id", nullable = false)
    var playerId: Long,

    @Column(name = "x", nullable = false)
    var x: Int,

    @Column(name = "y", nullable = false)
    var y: Int,

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "description", nullable = false)
    var description: String,

    @Column(name = "mark_time", nullable = false)
    var markTime: Long,

    @Column(name = "plt_area_no", nullable = false)
    var pltAreaNo: Int

) : AllianceAsset {
    constructor() : this(0, 0, 0, 0, 0, 0, "", "", 0, 0)

    override fun primaryKey() = id
}