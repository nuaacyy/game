package com.point18.slg2d.common.publicentities

import com.point18.slg2d.common.persistence.AllianceAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Index
import javax.persistence.*

const val ALLIANCE_MEMBER_NAMED_QUERY = "findAllianceMember"

const val ALLIANCE_ALL_MEMBER_NAMED_QUERY = "findAllAllianceMember"

@NamedQueries(
    NamedQuery(
        name = ALLIANCE_MEMBER_NAMED_QUERY,
        // language=HQL
        query = "from AllianceMemberEntity where allianceId = :allianceId"
    ),

    NamedQuery(
        name = ALLIANCE_ALL_MEMBER_NAMED_QUERY,
        // language=HQL
        query = "from AllianceMemberEntity"
    )
)
@Entity
@Table(name = "alliance_members")
data class AllianceMemberEntity(
    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Id
    @Column(name = "alliance_id", nullable = false)
    @Index(name = "ALLIANCE_MEMBERS_ALLIANCE_ID")
    override var allianceId: Long,

    @Column(name = "alliance_at", nullable = false)
    var allianceAt: Long,

    @Column(name = "alliance_pos", nullable = false, length = 1000)
    var alliancePos: String,

    @Column(name = "alliance_rnum", nullable = false)
    var allianceRnum: Int,

    @Column(name = "alliance_nick_name", nullable = false, length = 100)
    var allianceNickName: String,

    @Column(name = "can_help_num", nullable = false)
    var canHelpNum: Int,

    @Column(name = "name", nullable = false, length = 50)
    var name: String,

    @Column(name = "photo_proto_id", nullable = false)
    var photoProtoId: Int,

    @Column(name = "online_state", nullable = false)
    var onlineState: Int,

    @Column(name = "last_leave_time", nullable = false)
    var lastLeaveTime: Long,

    @Column(name = "mem_power", nullable = false)
    var memPower: Long,

    @Column(name = "player_castle_lv", nullable = false)
    var playerCastleLv: Int,

    @Column(name = "map_plt_area_id", nullable = false)
    var mapPltAreaId: Long,

    @Column(name = "map_area_no", nullable = false)
    var mapAreaNo: Int,

    @Column(name = "monster_score", nullable = false)
    var monsterScore: Int,

    @Column(name = "last_get_monster_score", nullable = false)
    var lastGetMonsterScore: Long,

    @Column(name = "alliance_competition_score", nullable = false)
    var allianceCompetitionScore: Int,

    @Column(name = "alliance_competition_ticket", nullable = false)
    var allianceCompetitionTicket: Int,

    @Column(name = "vip_lv", nullable = false)
    var vipLv: Int,

    @Column(name = "office_info", nullable = false, length = 1000)
    var officeInfo: String,

    @Column(name = "sign_topics", nullable = false, length = 5000)
    var signTopic: String,

    @Column(name = "in_move_server", nullable = false)
    var inMoveServer: Int

) : AllianceAsset {
    constructor() : this(
        0, 0, 0, "", 0, "", 0, "",
        0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0,
        0, "", "", 0
    )

    override fun primaryKey() = id
}