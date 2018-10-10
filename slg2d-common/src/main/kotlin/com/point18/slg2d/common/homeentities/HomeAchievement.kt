package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.PlayerAsset
import xyz.ariane.util.annotation.NoArgConstructor
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Index
import javax.persistence.*

@NoArgConstructor
data class HomeAchievementPK(
    @Id
    @Column(name = "player_id")
    override var playerId: Long,

    @Id
    @Column(name = "id", nullable = false)
    var id: Long
) : PlayerAsset {
    override fun primaryKey() = this
}

const val HOME_ACHIVEMENT_NAMED_QUERY = "findHomeAchievementByPlayerId"

@NamedQueries(
    NamedQuery(
        name = HOME_ACHIVEMENT_NAMED_QUERY,
        // language=HQL
        query = "from HomeAchievementEntity where playerId = :playerId"
    )
)

@Entity
@Table(name = "home_achievements")
@IdClass(HomeAchievementPK::class)
data class HomeAchievementEntity(
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
    @Index(name = "HOME_ACHIEVEMENTS_PLAYER_ID")
    override var playerId: Long           // 玩家ID

) : PlayerAsset {
    constructor() : this(0, 0, 0, "", 0)

    override fun primaryKey() = HomeAchievementPK(playerId, id)
}
