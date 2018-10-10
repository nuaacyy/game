package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val JJC_NAMED_QUERY = "findJjcByWorldId"

@NamedQueries(
    NamedQuery(
        name = JJC_NAMED_QUERY,
        // language=HQL
        query = "from JjcEntity where worldId = :worldId"
    )
)

@Entity
@Table(name = "jjcs")
data class JjcEntity(

    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "last_fight_time", nullable = false)
    var lastFightTime: Long,  //上次挑战时间

    @Column(name = "next_fight_time", nullable = false)
    var nextFightTime: Long,  //下次挑战时间

    @Column(name = "max_rank", nullable = false)
    var maxRank: Int,  // 玩家竞技场历史最高排名

    @Column(name = "rank1", nullable = false)
    var rank1: Int,  // 挑战对手排名1（低，数值大）

    @Column(name = "rank2", nullable = false)
    var rank2: Int,  // 挑战对手排名2（中，数值中）

    @Column(name = "rank3", nullable = false)
    var rank3: Int,  // 挑战对手排名3（高，数值小）

    @Column(name = "score", nullable = false)
    var score: Int,                // 积分

    @Column(name = "score_time", nullable = false)
    var scoreTime: Long,  // 积分计算时间

    @Column(name = "player_id", nullable = false)
    var playerId: Long    // 玩家ID
) : WorldAsset {

    constructor() : this(
        0, 0,   0, 0, 0, 0, 0, 0,
        0, 0, 0
    )

    override fun primaryKey() = id
}