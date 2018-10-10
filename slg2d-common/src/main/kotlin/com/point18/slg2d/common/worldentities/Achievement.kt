package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val ACHIVEMENT_NAMED_QUERY = "findAchievementByWorldId"

@NamedQueries(
    NamedQuery(
        name = ACHIVEMENT_NAMED_QUERY,
        // language=HQL
        query = "from AchievementEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "achievements")
data class AchievementEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "achievement_id", nullable = false)
    var achievementId: Int,  // 成就配置Id

    @Column(name = "state", nullable = false)
    var state: Int,  // 成就状态 0-进行中 1-已完成 2-已领取奖励

    @Column(name = "progress", nullable = false, length = 50)
    var progress: String,  // 进度

    @Column(name = "player_id", nullable = false)
    var playerId: Long           // 玩家ID

) : WorldAsset {
    constructor() : this(0, 0, 0, 0, "", 0)

    override fun primaryKey() = id
}