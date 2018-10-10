package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.OneToOnePlayerAsset
import org.hibernate.annotations.Type
import javax.persistence.*

const val HOME_SYNC_NAMED_QUERY = "findHomeSyncByPlayerId"

@NamedQueries(
    NamedQuery(
        name = HOME_SYNC_NAMED_QUERY,
        // language=HQL
        query = "from HomeSyncEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "home_syncs")
data class HomeSyncEntity(
    @Id
    @Column(name = "player_id", nullable = false)
    override var playerId: Long,         // 玩家ID

    @Column(name = "max_lv_in_prison", nullable = false)
    var maxLvInPrison: Int,

    @Type(type = "text")
    @Column(name = "all_barracks", nullable = false)
    var allBarracks: String,

    @Column(name = "all_effects", nullable = false, length = 1000)
    var allEffects: String,

    @Column(name = "all_country_buffs", nullable = false, length = 1000)
    var allCountryBuffs: String,

    @Column(name = "buffs", nullable = false, length = 1000)
    var buffs: String,

    @Column(name = "max_jjc_rank", nullable = false)
    var maxJjcRank: Int,

    @Column(name = "jjc_rank", nullable = false)
    var jjcRank: Int,

    @Column(name = "score_time", nullable = false)
    var scoreTime: Long,

    @Column(name = "score", nullable = false)
    var score: Int,

    @Column(name = "jjc_coin_reward", nullable = false)
    var jjcCoinReward: Long,

    @Column(name = "jjc_gold_reward", nullable = false)
    var jjcGoldReward: Long,

    @Column(name = "castle_state", nullable = false)
    var castleState: Int,

    @Column(name = "castle_status_end_time", nullable = false)
    var castleStatusEndTime: Long,

    @Column(name = "office_info", nullable = false, length = 1000)
    var officeInfo: String,

    @Column(name = "instance_floor", nullable = false)
    var instanceFloor: Int,

    @Column(name = "targets", nullable = false, length = 1000)
    var targets: String

) : OneToOnePlayerAsset {

    constructor() : this(
        0, 0, "", "", "", "",
        0, 0, 0, 0, 0, 0,
        0, 0, "", 0, ""
    )
}