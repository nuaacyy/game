package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.OneToOnePlayerAsset
import com.point18.slg2d.common.commonfunc.getNowTime
import javax.persistence.*

const val JJC_SHOP_NAMED_QUERY_PLAYER = "findJjcHomeByPlayerId"

@NamedQueries(
    NamedQuery(
        name = JJC_SHOP_NAMED_QUERY_PLAYER,
        // language=HQL
        query = "from JjcHomeEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "jjc_home")
data class JjcHomeEntity(
    @Id
    @Column(name = "player_id", nullable = false)
    override var playerId: Long,

    @Column(name = "items_info", nullable = false, length = 1000)  // 商店信息
    var itemsInfo: String,

    @Column(name = "refresh_time", nullable = false)  // 刷新商店时间
    var refreshTime: Long,

    @Column(name = "times", nullable = false)  // 刷新商店次数
    var times: Int,

    @Column(name = "score_rewards_string", nullable = false, length = 500)
    var scoreRewardsString: String,           // 本日已领取的积分奖励ID列表

    @Column(name = "draw_rewards_string", nullable = false, length = 1000)
    var drawRewardsString: String,  //已领取的竞技场奖励模版ID（历史最高排名奖励）

    @Column(name = "achievement_rewards_string", nullable = false, length = 1000)
    var achievementRewardsString: String,           // 成就兑换已领取的积分奖励ID列表

    @Column(name = "last_buy_count_time", nullable = false)  // 上次购买竞技场挑战次数的时间
    var lastBuyCountTime: Long,

    @Column(name = "today_buy_count_num", nullable = false)  // 今天买了多少次竞技场挑战次数
    var todayBuyCountNum: Int,

    @Column(name = "today_num", nullable = false)  // 今天剩余的竞技场
    var todayNum: Int

) : OneToOnePlayerAsset {
    constructor() : this(
         0, "", getNowTime(), 0, "",
        "", "",0,0,5
    )
}