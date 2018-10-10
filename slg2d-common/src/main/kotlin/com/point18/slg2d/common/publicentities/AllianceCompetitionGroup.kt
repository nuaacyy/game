package com.point18.slg2d.common.publicentities

import com.point18.slg2d.common.persistence.AllianceAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Index
import javax.persistence.*

const val ALLIANCE_COMPETITION_GROUP_NAMED_QUERY = "findAllianceCompetitionGroup"
const val ALLIANCE_COMPETITION_GROUP_NAMED_ALL_QUERY = "findAllAllianceCompetitionGroup"
@NamedQueries(
    NamedQuery(
        name = ALLIANCE_COMPETITION_GROUP_NAMED_QUERY,
        // language=HQL
        query = "from AllianceCompetitionGroupEntity where allianceId = :allianceId"
    ),
    NamedQuery(
        name = ALLIANCE_COMPETITION_GROUP_NAMED_ALL_QUERY,
        // language=HQL
        query = "from AllianceCompetitionGroupEntity"
    )
)
@Entity
@Table(name = "alliance_competition_groups")
data class AllianceCompetitionGroupEntity(
    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Id
    @Column(name = "alliance_id", nullable = false)
    @Index(name = "ALLIANCE_COMPETITION_GROUPS_ALLIANCE_ID")
    override var allianceId: Long,

    @Column(name = "group_id", nullable = false)
    var groupId: Int,

    @Column(name = "state_rank_lv", nullable = false)
    var stateRankLv: Int,

    @Column(name = "over_rank_lv", nullable = false)
    var overRankLv: Int,

    @Column(name = "score", nullable = false)
    var score: Int


) : AllianceAsset {
    constructor() : this(0, 0, 0, 0, 0, 0)

    override fun primaryKey() = id
}