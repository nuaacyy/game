package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import javax.persistence.*

const val INFO_BY_HOME_QUERY = "findInfoByHomeByWorldId"

@NamedQueries(
    NamedQuery(
        name = INFO_BY_HOME_QUERY,
        // language=HQL
        query = "from InfoByHomeEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "info_by_homes")
data class InfoByHomeEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "now_skin_id", nullable = false)
    var nowSkinId: Int,   // 玩家当前正在使用的皮肤模版ID

    @Column(name = "vip_lv", nullable = false)
    var vipLv: Int,   // vip等级

    @Column(name = "build_info", nullable = false, length = 5000)
    var buildInfo: String,   // 局部建筑的信息

    @Column(name = "effect_info", nullable = false, length = 5000)
    var effectInfo: String,   // 局部效果的信息

    @Type(type = "text")
    @Column(name = "finishTasks", nullable = false)
    var finishTasks: String,  // 所有结束的任务

    @Column(name = "player_id", nullable = false)
    var playerId: Long   // 玩家Id
) : WorldAsset {
    constructor() : this(0, 0, 0, 0, "", "", "", 0)

    override fun primaryKey() = id
}