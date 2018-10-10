package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.io.Serializable
import javax.persistence.*

const val PLAYER_SETTING_NAMED_QUERY_PLAYER = "findPlayerSettingByWorldId"

@NamedQueries(
    NamedQuery(
        name = PLAYER_SETTING_NAMED_QUERY_PLAYER,
        // language=HQL
        query = "from PlayerSettingEntity where worldId = :worldId"
    )
)

@Entity
@Table(name = "player_setting")
data class PlayerSettingEntity(
    @Id
    @Column(name = "world_id", nullable = false)
    override var worldId: Long,         // 玩家ID

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "refuse_disturb_open", nullable = false)
    var refuseDisturbOpen: Int,    // 勿扰开始时间

    @Column(name = "refuse_disturb_end", nullable = false)
    var refuseDisturbEnd: Int,    // 勿扰结束时间

    @Column(name = "caution_lv", nullable = false)
    var cautionLv: Int,    // 警戒等级

    @Type(type = "text")
    @Column(name = "switches", nullable = false)
    var switches: String,   // 通知开关

    @Column(name = "player_id", nullable = false)
    var playerId: Long    // 玩家ID

) : WorldAsset {
    constructor() : this(
        0L, 0L, 0, 0, 0, "", 0L
    )

    override fun primaryKey() = id
}

class NoticeSwitchVo(
    var typeProtoId: Int, // 模板类型Id
    var refuseDisturb: Int, // 勿扰控制
    var switch: Int //开关
) : Serializable