package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.OneToOnePlayerAsset
import org.hibernate.annotations.Index
import org.hibernate.annotations.Type
import javax.persistence.*

const val HOME_MY_TARGET_NAMED_QUERY = "findHomeMyTargetByPlayerId"

@NamedQueries(
    NamedQuery(
        name = HOME_MY_TARGET_NAMED_QUERY,
        // language=HQL
        query = "from HomeMyTargetEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "home_my_targets")
data class HomeMyTargetEntity(
    @Column(name = "world_talk_num", nullable = false)
    var worldTalkNum: Int, // 玩家世界频道说话次数

    @Column(name = "alliance_talk_num", nullable = false)
    var allianceTalkNum: Int, // 玩家联盟频道说话次数

    @Column(name = "have_friend_num", nullable = false)
    var haveFriendNum: Int, // 好友个数

    @Column(name = "normal_lottery_num", nullable = false)
    var normalLotteryNum: Int, // 普通抽卡次数

    @Column(name = "super_lottery_num", nullable = false)
    var superLotteryNum: Int, // 高级抽卡次数

    @Column(name = "hero_equip_adv_num", nullable = false)
    var heroEquipAdvNum: Int, // 武将装备进阶次数

    @Column(name = "hero_skill_lv_up_num", nullable = false)
    var heroSkillLvUpNum: Int, // 武将技能强化次数

    @Column(name = "normal_move_city_num", nullable = false)
    var normalMoveCityNum: Int, // 指定迁城次数

    @Column(name = "rand_move_city_num", nullable = false)
    var randMoveCityNum: Int, // 随机迁城次数

    @Column(name = "get_king_equip_num", nullable = false)
    var getKingEquipNum: Int, // 领取君主装备次数

    @Column(name = "jjc_fight_num", nullable = false)
    var jjcFightNum: Int, // 竞技场战斗次数

    @Column(name = "jjc_shop_buy_num", nullable = false)
    var jjcShopBuyNum: Int, // 竞技场商店购买次数

    @Column(name = "buy_surprise", nullable = false)
    var buySurprise: Int, // 竞技场商店购买次数

    @Column(name = "before_bank_num", nullable = false)
    var beforeBankNum: String, // 银行投资次数

    @Column(name = "bank_num", nullable = false)
    var bankNum: String, // 银行投资次数

    @Column(name = "bank_money", nullable = false)
    var bankMoney: String, // 银行投资金额

    @Column(name = "diamond_cost_num", nullable = false)
    var diamondCostNum: Long, // 玩家钻石总消耗

    @Column(name = "alliance_coin_cost_num", nullable = false)
    var allianceCoinCostNum: Long, // 玩家联盟币总消耗

    @Column(name = "diamond_shop_buy_num", nullable = false)
    var diamondShopBuyNum: Int, // 钻石商店购买次数

    @Column(name = "alliance_shop_buy_num", nullable = false)
    var allianceShopBuyNum: Int, // 联盟商店购买次数

    @Column(name = "activity_shop_buy_num", nullable = false)
    var activityShopBuyNum: Int, // 挑战商店购买次数

    @Column(name = "alliance_competition_score", nullable = false)
    var allianceCompetitionScore: Int, // 挑战商店购买次数

    @Column(name = "alliance_competition_task_num", nullable = false)
    var allianceCompetitionTaskNum: Int, // 挑战商店购买次数

    @Column(name = "casino_boss_num", nullable = false)
    var casinoBossNum: String, // 赌场遇见boss的次数

    @Column(name = "casino_num", nullable = false)
    var casinoNum: String, // 赌场抽奖次数

    @Column(name = "casino_kill_boss_num", nullable = false)
    var casinoKillBossNum: String, // 赌场抽奖次数

    @Type(type = "text")
    @Column(name = "get_res_info", nullable = false)
    var getResInfo: String, // 累计获得资源情况

    @Type(type = "text")
    @Column(name = "clear_time_info", nullable = false)
    var clearTimeInfo: String, // 累计使用加速情况

    @Id
    @Column(name = "player_id", nullable = false)
    @Index(name = "HOME_MY_TARGETS_PLAYER_ID")
    override var playerId: Long         // 玩家ID

) : OneToOnePlayerAsset {

    constructor() : this(
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "", "", "", 0L, 0L, 0, 0, 0, 0, 0, "", "", "", "", "",
        0L
    )
}