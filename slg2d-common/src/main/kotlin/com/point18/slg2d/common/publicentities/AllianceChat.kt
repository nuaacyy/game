package com.point18.slg2d.common.publicentities

import com.point18.slg2d.common.persistence.AllianceAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Index
import javax.persistence.*

const val ALLIANCE_CHAT_NAMED_QUERY = "findAllianceChat"

@NamedQueries(
    NamedQuery(
        name = ALLIANCE_CHAT_NAMED_QUERY,
        // language=HQL
        query = "from AllianceChatEntity where allianceId = :allianceId"
    )
)
@Entity
@Table(name = "alliance_chat")
data class AllianceChatEntity(
    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Id
    @Column(name = "alliance_id", nullable = false)
    @Index(name = "ALLIANCE_CHAT_ALLIANCE_ID")
    override var allianceId: Long,

    @Column(name = "area_no", nullable = false)
    var areaNo: Int,

    @Column(name = "msg", nullable = false, length = 250)
    var msg: String,

    @Column(name = "msg_type", nullable = false)
    var msgType: Int,

    @Column(name = "vip_lv", nullable = false)
    var vipLv: Int,

    @Column(name = "alliance_pos", nullable = false)
    var alliancePos: Int,

    @Column(name = "icon_proto_id", nullable = false)
    var iconProtoId: Int,

    @Column(name = "alliance_name", nullable = false, length = 50)
    var allianceName: String,

    @Column(name = "alliance_short_name", nullable = false, length = 50)
    var allianceShortName: String,

    @Column(name = "player_name", nullable = false, length = 50)
    var playerName: String,

    @Column(name = "player_short_name", nullable = false, length = 50)
    var playerShortName: String,

    @Column(name = "kingdom_pos", nullable = false)
    var kingdomPos: Int,

    @Column(name = "wonder_pos", nullable = false)
    var wonderPos: Int,

    @Column(name = "chat_time", nullable = false)
    var chatTime: Long,

    @Column(name = "player_id", nullable = false)
    var playerId: Long,   // 玩家ID

    @Column(name = "plt_area_no", nullable = false)
    var pltAreaNo: Long

) : AllianceAsset {
    constructor() : this(
        0, 0, 0, "", 0, 0, 0, 0, "",
        "", "", "", 0, 0, 0, 0,
        0
    )

    override fun primaryKey() = id
}