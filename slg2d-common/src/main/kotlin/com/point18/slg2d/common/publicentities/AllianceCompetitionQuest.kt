package com.point18.slg2d.common.publicentities

import com.point18.slg2d.common.persistence.AllianceAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Index
import javax.persistence.*

const val ALLIANCE_COMPETITION_QUEST_NAMED_QUERY = "findAllianceCompetitionQuest"

@NamedQueries(
    NamedQuery(
        name = ALLIANCE_COMPETITION_QUEST_NAMED_QUERY,
        // language=HQL
        query = "from AllianceCompetitionQuestEntity where allianceId = :allianceId"
    )
)
@Entity
@Table(name = "alliance_competition_quests")
data class AllianceCompetitionQuestEntity(
    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Id
    @Column(name = "alliance_id", nullable = false)
    @Index(name = "ALLIANCE_COMPETITION_QUESTS_ALLIANCE_ID")
    override var allianceId: Long,

    @Column(name = "indexAddress", nullable = false)
    var indexAddress: Int,

    @Column(name = "quest_id", nullable = false)
    var questId: Int,

    @Column(name = "ref_time", nullable = false)
    var refTime: Long

) : AllianceAsset {
    constructor() : this(0, 0, 0, 0, 0)

    override fun primaryKey() = id
}