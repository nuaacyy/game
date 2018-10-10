package com.point18.slg2d.common.publicentities

import com.point18.slg2d.common.persistence.AllianceAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Index
import javax.persistence.*

const val ALLIANCE_MEMBER_TRACE_NAMED_QUERY = "findAllianceMemberTrace"

const val ALLIANCE_ALL_MEMBER_TRACE_NAMED_QUERY = "findAllAllianceMemberTrace"

@NamedQueries(
    NamedQuery(
        name = ALLIANCE_MEMBER_TRACE_NAMED_QUERY,
        // language=HQL
        query = "from AllianceMemberTraceEntity where allianceId = :allianceId"
    ),

    NamedQuery(
        name = ALLIANCE_ALL_MEMBER_TRACE_NAMED_QUERY,
        // language=HQL
        query = "from AllianceMemberTraceEntity"
    )
)
@Entity
@Table(name = "alliance_member_traces")
data class AllianceMemberTraceEntity(
    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Id
    @Column(name = "alliance_id", nullable = false)
    @Index(name = "ALLIANCE_MEMBER_TRACES_ALLIANCE_ID")
    override var allianceId: Long,

    @Column(name = "honor", nullable = false)
    var honor: Long,

    @Column(name = "kill_solider", nullable = false)
    var killSolider: Long,

    @Column(name = "cure_solider", nullable = false)
    var cureSolider: Long,

    @Column(name = "kill_monster", nullable = false)
    var killMonster: Long,

    @Column(name = "week_honor", nullable = false)
    var weekHonor: Long,

    @Column(name = "week_kill_solider", nullable = false)
    var weekKillSolider: Long,

    @Column(name = "week_cure_solider", nullable = false)
    var weekCureSolider: Long,

    @Column(name = "week_transportation_value", nullable = false)
    var weekTransportationValue: Long,

    @Column(name = "week_kill_monster", nullable = false)
    var weekKillMonster: Long,

    @Column(name = "week_monster_score", nullable = false)
    var weekMonsterScore: Long,

    @Column(name = "monster_score", nullable = false)
    var monsterScore: Long,

    @Column(name = "week_help", nullable = false)
    var weekHelp: Long,

    @Column(name = "alliance_competition_score", nullable = false)
    var allianceCompetitionScore: Long,

    @Column(name = "alliance_competition_score_change_time", nullable = false)
    var allianceCompetitionScoreChangeTime: Long,

    @Column(name = "alliance_competition_quest_success_num", nullable = false)
    var allianceCompetitionQuestSuccessNum: Int,

    @Column(name = "alliance_competition_quest_get_num", nullable = false)
    var allianceCompetitionQuestGetNum: Int,

    @Column(name = "player_id", nullable = false)
    var playerId: Long

) : AllianceAsset {
    constructor() : this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

    override fun primaryKey() = id
}