package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.*
import com.point18.slg2d.home.AbstractDataContainer
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import java.io.Serializable
import java.time.Duration

open class HomeMyTargetDC : AbstractDataContainer<HomeMyTargetEntity>() {

    open lateinit var targetInfo: HomeMyTarget
        protected set

    override fun loadAllFromDB(playerId: Long, dao: CommonDao): HomeMyTargetEntity? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(HOME_MY_TARGET_NAMED_QUERY)
                .setLong("playerId", playerId)
                .listNoDup<HomeMyTargetEntity>()
            list.firstOrNull()
        }
        return data
    }

    override fun initImpl(data: HomeMyTargetEntity?, depDCRepo: DataContainerRepo) {
        if (data != null) {
            val syncWrap = wdb.recover(data) { HomeMyTarget() }
            targetInfo = syncWrap
        } else {
            val sync = HomeMyTarget(
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, hashMapOf(), hashMapOf(), hashMapOf(), 0, 0L, 0, 0, 0, 0, 0, hashMapOf(), hashMapOf(), hashMapOf(), hashMapOf(), hashMapOf(),
                playerId
            )

            wdb.save(sync)
            this.targetInfo = sync

        }
    }
}

class HomeMyTarget(
    var worldTalkNum: Int, // 玩家世界频道说话次数
    var allianceTalkNum: Int, // 玩家联盟频道说话次数
    var haveFriendNum: Int, // 玩家拥有过的好友个数,只增不减
    var normalLotteryNum: Int, // 普通抽卡次数
    var superLotteryNum: Int, // 高级抽卡次数
    var heroEquipAdvNum: Int, // 英雄装备进阶次数
    var heroSkillLvUpNum: Int, // 强化英雄技能次数
    var normalMoveCityNum: Int, // 普通迁城次数
    var randMoveCityNum: Int, // 随机迁城次数
    var getKingEquipNum: Int, // 领取君主锻造装备次数
    var jjcFightNum: Int, // 竞技场挑战次数
    var jjcShopBuyNum: Int, // 竞技场商店购买次数
    var buySurprise: Int, // 商船购买惊喜次数
    var beforeBankNum: HashMap<Int, Int>, // 银行投资次数(即便取消也算)
    var bankNum: HashMap<Int, Int>, // 银行投资次数
    var bankMoney: HashMap<Int, Int>, // 银行投资金额,
    var diamondCostNum: Long, // 玩家消耗总钻石数量
    var allianceCoinCostNum: Long, // 玩家消耗联盟币数量
    var diamondShopBuyNum: Int, // 钻石商店购买物品次数
    var allianceShopBuyNum: Int, // 联盟商店购买物品次数
    var activityShopBuyNum: Int, // 挑战商店购买物品次数
    var allianceCompetitionScore: Int, // 联盟总动员获得总积分数
    var allianceCompetitionTaskNum: Int, // 联盟总动员完成任务数量
    var casinoBossNum: HashMap<Int, Int>, // 赌场遇见特殊boss的次数
    var casinoNum: HashMap<Int, Int>,     // 赌场抽奖次数
    var casinoKillBossNum: HashMap<Int, Int>, // 赌场杀死特殊boss的次数
    var getResInfo: HashMap<Int, Long>,
    var clearTimeInfo: HashMap<Int, Long>, // 加速时间信息

    var playerId: Long                                  // 玩家Id
) : EntityWrapper<HomeMyTargetEntity> {

    constructor() : this(
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, hashMapOf(), hashMapOf(), hashMapOf(), 0L, 0L, 0, 0, 0, 0, 0, hashMapOf(), hashMapOf(), hashMapOf(), hashMapOf(), hashMapOf(),
        0L
    )

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = playerId

    override fun toEntity(): HomeMyTargetEntity {
        return HomeMyTargetEntity(
            worldTalkNum,
            allianceTalkNum,
            haveFriendNum,
            normalLotteryNum,
            superLotteryNum,
            heroEquipAdvNum,
            heroSkillLvUpNum,
            normalMoveCityNum,
            randMoveCityNum,
            getKingEquipNum,
            jjcFightNum,
            jjcShopBuyNum,
            buySurprise,
            toJson(beforeBankNum),
            toJson(bankNum),
            toJson(bankMoney),
            diamondCostNum,
            allianceCoinCostNum,
            diamondShopBuyNum,
            allianceShopBuyNum,
            activityShopBuyNum,
            allianceCompetitionScore,
            allianceCompetitionTaskNum,
            toJson(casinoBossNum),
            toJson(casinoNum),
            toJson(casinoKillBossNum),
            toJson(getResInfo),
            toJson(clearTimeInfo),
            playerId
        )
    }

    override fun wrap(entity: HomeMyTargetEntity) {
        worldTalkNum = entity.worldTalkNum
        allianceTalkNum = entity.allianceTalkNum
        playerId = entity.playerId
        haveFriendNum = entity.haveFriendNum
        normalLotteryNum = entity.normalLotteryNum
        superLotteryNum = entity.superLotteryNum
        heroEquipAdvNum = entity.heroEquipAdvNum
        heroSkillLvUpNum = entity.heroSkillLvUpNum
        normalMoveCityNum = entity.normalMoveCityNum
        randMoveCityNum = entity.randMoveCityNum
        getKingEquipNum = entity.getKingEquipNum
        jjcFightNum = entity.jjcFightNum
        jjcShopBuyNum = entity.jjcShopBuyNum
        buySurprise = entity.buySurprise
        beforeBankNum = toObj(entity.beforeBankNum)
        bankNum = toObj(entity.bankNum)
        bankMoney = toObj(entity.bankMoney)
        diamondCostNum = entity.diamondCostNum
        allianceCoinCostNum = entity.allianceCoinCostNum
        diamondShopBuyNum = entity.diamondShopBuyNum
        allianceShopBuyNum = entity.allianceShopBuyNum
        activityShopBuyNum = entity.activityShopBuyNum
        allianceCompetitionScore = entity.allianceCompetitionScore
        allianceCompetitionTaskNum = entity.allianceCompetitionTaskNum
        casinoBossNum = toObj(entity.casinoBossNum)
        casinoNum = toObj(entity.casinoNum)
        casinoKillBossNum = toObj(entity.casinoKillBossNum)
        getResInfo = toObj(entity.getResInfo)
        clearTimeInfo = toObj(entity.clearTimeInfo)
    }
}