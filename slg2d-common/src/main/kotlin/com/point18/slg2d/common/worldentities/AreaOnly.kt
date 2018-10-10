package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val AREA_ONLY_NAMED_QUERY = "findAreaOnlyByWorldId"

@NamedQueries(
    NamedQuery(
        name = AREA_ONLY_NAMED_QUERY,
        // language=HQL
        query = "from AreaOnlyEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "area_onlys")
data class AreaOnlyEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "now_player_num", nullable = false)
    var nowPlayerNum: Int,                  // 本区玩家进入数量

    @Column(name = "now_create_player_on_area", nullable = false)
    var nowCreatePlayerOnArea: Int,         // 当前新玩家进入游戏是投在哪个小方块内

    @Column(name = "now_create_player_circle_num", nullable = false)
    var nowCreatePlayerCircleNum: Int,      // 当前新玩家进入游戏的投点已经是第几轮了  人数 >= 该值 * basic.bornUp + basic.bornFrist 的时候,NowCreatePlayerOnArea转换到下一个

    @Column(name = "every_small_map_player_num", nullable = false, length = 100000)
    var everySmallMapPlayerNum: String,     // 该大地图上的各个生态小方块内的玩家情况

    @Column(name = "last_way_for_map_refresh", nullable = false)
    var lastWayForMapRefresh: Int,          // 上次刷新大地图的时候用的那个人数方案(用来重启服务器之后的刷新,如果是0就表示是新服)

    @Column(name = "inited_map", nullable = false)
    var initedMap: Int,                  // 是否初始化地图

    @Column(name = "next_ref_boss_time", nullable = false)
    var nextRefBossTime: Long,           //下次刷新魔物的时间

    @Column(name = "next_jjc_day_reward_time", nullable = false)
    var nextJjcDayRewardTime: Long      // 下次jjc每日奖励发放时间
) : WorldAsset {
    constructor() : this(
        0, 0, 0, 0, 0,
        "", 0, 0, 0, 0
    )

    override fun primaryKey() = id
}