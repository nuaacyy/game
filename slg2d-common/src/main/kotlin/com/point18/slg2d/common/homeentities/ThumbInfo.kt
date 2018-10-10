package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.OneToOnePlayerAsset
import javax.persistence.*

const val THUMB_INFO_NAMED_QUERY_PLAYER = "findThumbInfoByPlayerId"

@NamedQueries(
    NamedQuery(
        name = THUMB_INFO_NAMED_QUERY_PLAYER,
        // language=HQL
        query = "from ThumbInfoEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "thumb_info")
data class ThumbInfoEntity(
    @Id
    @Column(name = "player_id", nullable = false)
    override var playerId: Long,         // 玩家ID

    @Column(name = "thumb_out", nullable = false, length = 2000)
    var thumbOut: String,    // 点赞信息

    @Column(name = "thumb_in", nullable = false, length = 2000)
    var thumbIn: String,    // 被赞信息

    @Column(name = "thumb_in_alliance", nullable = false, length = 2000)
    var thumbInAlliance: String,    // 同联盟被赞信息

    @Column(name = "thumb_in_total", nullable = false)
    var thumbInTotal: Int,    // 被赞总数

    @Column(name = "thumb_in_num", nullable = false)
    var thumbInNum: Int,    // 今日被赞数

    @Column(name = "thumb_out_num", nullable = false)
    var thumbOutNum: Int,    // 今日点赞数

    @Column(name = "check_time", nullable = false)
    var checkTime: Long    // 查看点赞信息时间

) : OneToOnePlayerAsset {
    constructor() : this(
        0, "", "", "", 0,
        0, 0, 0
    )
}

