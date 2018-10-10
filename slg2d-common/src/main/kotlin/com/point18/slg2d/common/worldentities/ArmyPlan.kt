package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val ARMY_PLAN_NAMED_QUERY = "findArmyPlanByWorldId"

@NamedQueries(
    NamedQuery(
        name = ARMY_PLAN_NAMED_QUERY,
        // language=HQL
        query = "from ArmyPlanEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "army_plans")
data class ArmyPlanEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "big_target", nullable = false)
    var bigTarget: Int,            // 大目标 1、竞技场 2、推图 3、魔物

    @Column(name = "small_target", nullable = false)
    var smallTarget: Int,            // 小目标 竞技场进攻/防守 魔物1...N

    @Column(name = "hero_info", nullable = false, length = 1000)
    var heroInfo: String,  // 英雄信息 位置-英雄Id

    @Column(name = "player_id", nullable = false)
    var playerId: Long            // 玩家ID
) : WorldAsset {
    constructor() : this(0, 0, 0, 0, "", 0)

    override fun primaryKey() = id
}