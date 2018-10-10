package com.point18.slg2d.common.publicentities

import com.point18.slg2d.common.persistence.AllianceAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Index
import javax.persistence.*

const val ALLIANCE_NAMED_QUERY = "findAlliance"

const val All_ALLIANCE_NAMED_QUERY = "findAllAlliance"

@NamedQueries(
    NamedQuery(
        name = ALLIANCE_NAMED_QUERY,
        // language=HQL
        query = "from AllianceEntity where allianceId = :allianceId"
    ),

    NamedQuery(
        name = All_ALLIANCE_NAMED_QUERY,
        // language=HQL
        query = "from AllianceEntity"
    )
)
@Entity
@Table(name = "alliances")
data class AllianceEntity(
    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Id
    @Column(name = "alliance_id", nullable = false)
    @Index(name = "ALLIANCES_ALLIANCE_ID")
    override var allianceId: Long,

    @Column(name = "name", nullable = false, length = 50)
    var name: String,

    @Column(name = "short_name", nullable = false, length = 10)
    var shortName: String,

    @Column(name = "main_player_id", nullable = false)
    var mainPlayerId: Long,

    @Column(name = "description", nullable = false, length = 500)
    var description: String,

    @Column(name = "slogan", nullable = false, length = 500)
    var slogan: String,

    @Column(name = "next_mark_time", nullable = false)
    var nextMarkTime: Long,

    @Column(name = "power_limit", nullable = false)
    var powerLimit: Long,

    @Column(name = "can_add_power", nullable = false)
    var canAddPower: Long,

    @Column(name = "make_over_time", nullable = false)
    var makeOverTime: Long,

    @Column(name = "make_over_pid", nullable = false)
    var makeOverPid: Long,

    @Column(name = "flag_color", nullable = false)
    var flagColor: Int,

    @Column(name = "flag_style", nullable = false)
    var flagStyle: Int,

    @Column(name = "flag_effect", nullable = false)
    var flagEffect: Int,

    @Column(name = "alliance_lan", nullable = false)
    var allianceLan: Int,

    @Column(name = "create_at", nullable = false)
    var createAt: Long,

    @Column(name = "alliance_help_num_add", nullable = false)
    var allianceHelpNumAdd: Int,

    @Column(name = "alliance_boss_score", nullable = false)
    var allianceBossScore: Int,

    @Column(name = "alliance_boss_info", nullable = false, length = 1000)
    var allianceBossInfo: String,

    @Column(name = "alliance_rank_lv", nullable = false)
    var allianceRankLv: Int,

    @Column(name = "alliance_competition_score", nullable = false)
    var allianceCompetitionScore: Int,

    @Column(name = "alliance_competition_score_change_time", nullable = false)
    var allianceCompetitionScoreChangeTime: Long,

    @Column(name = "alliance_competition_ticket", nullable = false)
    var allianceCompetitionTicket: Int,

    @Column(name = "wonder_award_info", nullable = false, length = 1000)
    var wonderAwardInfo: String,

    @Column(name = "join_activity", nullable = false, length = 100000)
    var joinActivity: String,

    @Column(name = "alliance_power", nullable = false)
    var alliancePower: Long,

    @Column(name = "alliance_member_num", nullable = false)
    var allianceMemberNum: Int,

    @Column(name = "alliance_area_no", nullable = false)
    var allianceAreaNo: Int,

    @Column(name = "alliance_occupy_info", nullable = false, length = 2000)
    var allianceOccupyInfo: String

) : AllianceAsset {
    constructor() : this(
        0, 0, "", "", 0, "", "", 0, 0,
        0, 0, 0, 0, 0, 0, 0,
        0, 0, 0,
        "", 0, 0, 0,
        0, "", "", 0L, 0, 0, ""
    )

    override fun primaryKey() = id
}