package com.point18.slg2d.home.common

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.resVoCombine
import com.point18.slg2d.common.pc.resVoToResString
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus8
import com.point18.slg2d.home.module.event.*
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.*
import pb4server.*
import xyz.ariane.util.lzDebug
import xyz.ariane.util.lzInfo
import java.util.*
import java.util.Arrays.asList

/**
关于资源的处理
 */

data class LackResVo(
    val lackType: Int,
    val lackNum: Long,
    val extend1: Int //如果类型是道具的话,这个字段表示道具ID,其余类型这个是0
)

data class CheckAndTellResResult(
    val checkRst: Boolean,
    val lackRes: LinkedList<LackResVo>,
    val haveRes: LinkedList<LackResVo>
)

class CostResWithoutNoticeResult(
    val resourceYieldDC: ResourceYieldDC,
    val isNoticeResource: Boolean,
    val isNoticeTowerNum: Boolean,
    val propNotifier: PropsChangeNotifier?
) {
    //通知扣除资源
    fun noticeCostRes(session: PlayerActor, player: HomePlayer) {
        if (isNoticeResource) {
            // 资源扣除后，推送真实的资源数量
            val yieldRes = resourceYieldDC.findResourceYield()
            val refreshResourceNotifier = createRefreshResourceNotifier(
                player,
                player.wood,
                player.food,
                player.iron,
                player.stone,
                player.decree,
                yieldRes.calTime
            )
            refreshResourceNotifier.notice(session)
        }
        if (propNotifier != null) {
            propNotifier.notice(session)
        }
    }
}

class ResHelper(
    private val refreshResHelper: RefreshResourceHelper = RefreshResourceHelper(),
    private val targetHelper: TargetHelper = TargetHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper(),
    private val propHelper: PropsHelper = PropsHelper()
) : HomeHelperPlus8<HomePlayerDC, ResourceYieldDC, BagDC, NewEquipDC, VipDC, SkinDC,
        FriendDC, HomeMyTargetDC>(
    HomePlayerDC::class.java, ResourceYieldDC::class.java, BagDC::class.java, NewEquipDC::class.java,
    VipDC::class.java, SkinDC::class.java, FriendDC::class.java,
    HomeMyTargetDC::class.java,
    asList(refreshResHelper, targetHelper, effectHelper, propHelper)
) {

    fun checkRes(
        session: PlayerActor,
        resVos: ResVo
    ): Boolean {
        val costs = LinkedList<ResVo>(
            Arrays.asList(resVos)
        )
        return checkRes(session, costs)
    }

    /**
    资源检测
     */
    fun checkRes(
        session: PlayerActor,
        resVos: List<ResVo>
    ): Boolean {
        return prepare(session) { homePlayerDC: HomePlayerDC, resourceYieldDC: ResourceYieldDC, bagDC: BagDC,
                                  newEquipDC: NewEquipDC, vipDC: VipDC, skinDC: SkinDC, friendDC: FriendDC,
                                  homeMyTargetDC: HomeMyTargetDC ->
            if (resVos.count() <= 0) {
                return@prepare false
            }

            // 计算玩家资源数量，不向客户端推送资源数量，在扣除资源时再推送
            val now = getNowTime()
            val player = homePlayerDC.player
            val (wood, iron, stone, food, coin) = refreshResHelper.refreshPlayerResource(session, now)
            val decree = player.decree

            // 创建一个临时的map来保存验证的中间情况,模拟资源扣除
            val nowCostResNum = hashMapOf<ResourceType, Long>() //资源
            val nowCostPropsNum = hashMapOf<Int, Int>()               //道具
            for (resVo in resVos) {
                // 避免刷资源的情况
                if (resVo.num < 0) {
                    normalLog.error(
                        "checkRes() 资源问题BUG：PlayerId：${player.playerId}，Type：${resVo.resType}，Num：${resVo.num}."
                    )
                    return@prepare false
                }

                var haveNum = 0L
                when (resVo.resType) {
                    RES_SILVER_COIN ->
                        //银币
                        haveNum = player.silverCoin - (nowCostResNum[resVo.resType] ?: 0)
                    RES_GOLD_COIN ->
                        //金币
                        haveNum = player.goldCoin - (nowCostResNum[resVo.resType] ?: 0)
                    RES_CASINO_COIN ->
                        //赌场币
                        haveNum = player.casinoCoin - (nowCostResNum[resVo.resType] ?: 0)
                    RES_COIN ->
                        //铜币
                        haveNum = coin - (nowCostResNum[resVo.resType] ?: 0)
                    RES_FOOD ->
                        //粮食
                        haveNum = food - (nowCostResNum[resVo.resType] ?: 0)
                    RES_WOOD ->
                        //木料
                        haveNum = wood - (nowCostResNum[resVo.resType] ?: 0)
                    RES_STONE ->
                        //石料
                        haveNum = stone - (nowCostResNum[resVo.resType] ?: 0)
                    RES_IRON ->
                        //铁矿
                        haveNum = iron - (nowCostResNum[resVo.resType] ?: 0)
                    RES_GOLD ->
                        //元宝
                        haveNum = player.gold - (nowCostResNum[resVo.resType] ?: 0)
                    RES_DECREE ->
                        //政令
                        haveNum = decree.toLong() - (nowCostResNum[resVo.resType] ?: 0)
                    RES_BIND_GOLD ->
                        //绑定元宝
                        haveNum = player.bindGold + player.gold - (nowCostResNum[resVo.resType] ?: 0)
                    RES_HONOR ->
                        //荣誉
                        haveNum = player.honor - (nowCostResNum[resVo.resType] ?: 0)
                    RES_ALLIANCE_COIN ->
                        // 联盟币
                        haveNum = player.allianceCoin - (nowCostResNum[resVo.resType] ?: 0)
                    RES_HERO_EXP_POOL ->
                        // 英雄经验池
                        haveNum = player.heroExpPool - (nowCostResNum[resVo.resType] ?: 0)
                    RES_PROPS -> {
                        //道具
                        val propsId = resVo.subType.toInt()
                        val num = resVo.num.toInt()
                        val proto = pcs.equipCache.equipProtoMap[propsId]
                        if (proto == null) {
                            return@prepare false
                        }

                        val propsVo = newEquipDC.findNormalBagPropByProtoId(bagDC, propsId)
                        if (propsVo == null) {
                            return@prepare false
                        }
                        if (num > (propsVo.haveNum - (nowCostPropsNum[propsId] ?: 0))) {
                            return@prepare false
                        } else {
                            val prop = nowCostPropsNum[propsId]
                            if (prop != null) {
                                nowCostPropsNum[propsId] = (nowCostPropsNum[propsId] ?: 0) + num
                            } else {
                                nowCostPropsNum[propsId] = num
                            }
                        }
                    }
                    RES_JJC_COIN ->
                        //竞技币
                        haveNum = player.jjcCoin.toLong() - (nowCostResNum[resVo.resType] ?: 0)
                    RES_HOUSE_COIN ->
                        // 家园币
                        haveNum = player.houseCoin - (nowCostPropsNum[resVo.resType] ?: 0)
                    else -> {
                        normalLog.error("检测奖励格式的时候发现未定义的奖励类型-${resVo.resType}.")
                        return@prepare false
                    }
                }
                if (resVo.resType != RES_PROPS) {
                    if (resVo.num > haveNum) {
                        // 不够,报错
                        normalLog.lzDebug { "检测奖励 ${resVo.resType}  ${resVo.num} " }
                        return@prepare false
                    } else {
                        val res = nowCostResNum[resVo.resType]
                        if (res != null) {
                            nowCostResNum[resVo.resType] = (nowCostResNum[resVo.resType] ?: 0) + resVo.num
                        } else {
                            nowCostResNum[resVo.resType] = resVo.num
                        }
                    }
                }

            }

            return@prepare true
        }
    }

    /**
    把玩家身上资源与传入的参数进行比较 ,返回缺的资源量与可以扣的
     */

    fun checkAndTellRes(
        session: PlayerActor,
        player: HomePlayer,
        originResVos: List<ResVo>
    ): CheckAndTellResResult {
        return prepare(session) { homePlayerDC: HomePlayerDC, resourceYieldDC: ResourceYieldDC, bagDC: BagDC,
                                  newEquipDC: NewEquipDC, vipDC: VipDC, skinDC: SkinDC, friendDC: FriendDC,
                                  homeMyTargetDC: HomeMyTargetDC ->
            val lackResVos = LinkedList<LackResVo>()
            val haveResVos = LinkedList<LackResVo>()
            if (originResVos.size <= 0) {
                return@prepare CheckAndTellResResult(true, lackResVos, haveResVos)
            }

            val resVos = resVoCombine(LinkedList(originResVos))

            // 计算玩家资源数量，不向客户端推送资源数量，在扣除资源时再推送
            val now = getNowTime()
            val (wood, iron, stone, food, coin) = refreshResHelper.refreshPlayerResource(session, now)
            val decree = player.decree
            for (resVo in resVos) {
                // 避免刷资源的情况
                if (resVo.num <= 0) {
                    normalLog.error(
                        "checkAndTellRes() 资源问题BUG：PlayerId：%d，Type：%d，Num：%d.",
                        player.playerId,
                        resVo.resType,
                        resVo.num
                    )
                    return@prepare CheckAndTellResResult(false, lackResVos, haveResVos)
                }

                var haveNum: Long
                var isProps = false
                when (resVo.resType) {
                    RES_SILVER_COIN ->
                        //银币
                        haveNum = player.silverCoin
                    RES_GOLD_COIN ->
                        //金币
                        haveNum = player.goldCoin
                    RES_CASINO_COIN ->
                        //赌场币
                        haveNum = player.casinoCoin
                    RES_COIN ->
                        //铜币
                        haveNum = coin
                    RES_FOOD ->
                        //粮食
                        haveNum = food
                    RES_WOOD ->
                        //木料
                        haveNum = wood
                    RES_STONE ->
                        //石料
                        haveNum = stone
                    RES_IRON ->
                        //铁矿
                        haveNum = iron
                    RES_GOLD ->
                        //元宝
                        haveNum = player.gold
                    RES_DECREE ->
                        //政令
                        haveNum = decree.toLong()
                    RES_BIND_GOLD ->
                        //绑定元宝
                        haveNum = player.bindGold + player.gold
                    RES_HONOR ->
                        //荣誉
                        haveNum = player.honor
                    RES_JJC_COIN ->
                        //竞技币
                        haveNum = player.jjcCoin.toLong()
                    RES_ALLIANCE_COIN ->
                        // 联盟币
                        haveNum = player.allianceCoin
                    RES_HERO_EXP_POOL ->
                        // 英雄经验池
                        haveNum = player.heroExpPool
                    RES_HOUSE_COIN ->
                        // 家园币
                        haveNum = player.houseCoin
                    RES_PROPS -> {
                        // 道具
                        isProps = true
                        val equip = newEquipDC.findPropByProtoId(resVo.subType.toInt())
                        if (equip == null) {
                            haveNum = 0
                        } else {
                            haveNum = equip.haveNum.toLong()
                        }
                    }

                    else -> {
                        normalLog.error("检测奖励格式的时候发现未定义的奖励类型-%d.", resVo.resType)
                        return@prepare CheckAndTellResResult(false, lackResVos, haveResVos)
                    }
                }

                if (isProps) {
                    if (haveNum < resVo.num) {
                        // 不够的道具直接用钻石补齐
                        val propProto = pcs.equipCache.equipProtoMap[resVo.subType.toInt()]
                        if (propProto == null) {
                            return@prepare CheckAndTellResResult(false, lackResVos, haveResVos)
                        }

                        val n = resVo.num - haveNum // 需要用钻石的数量
                        val onePrice = propProto.fastBuyMap[0].num

                        val lackResVo = LackResVo(
                            RES_BIND_GOLD,
                            n * onePrice,
                            0
                        )
                        lackResVos += lackResVo

                        if (haveNum != 0L) {
                            val haveResVo = LackResVo(
                                resVo.resType,
                                haveNum,
                                resVo.subType.toInt()
                            )
                            haveResVos += haveResVo
                        }
                    } else {
                        val haveResVo = LackResVo(
                            resVo.resType,
                            resVo.num,
                            resVo.subType.toInt()
                        )
                        haveResVos += haveResVo
                    }
                } else {
                    if (haveNum < resVo.num) {
                        val lackResVo = LackResVo(
                            resVo.resType,
                            resVo.num - haveNum,
                            0
                        )
                        lackResVos += lackResVo

                        val haveResVo = LackResVo(
                            resVo.resType,
                            haveNum,
                            0
                        )
                        haveResVos += haveResVo
                    } else {
                        val haveResVo = LackResVo(
                            resVo.resType,
                            resVo.num,
                            0
                        )
                        haveResVos += haveResVo
                    }
                }
            }

            return@prepare CheckAndTellResResult(true, lackResVos, haveResVos)
        }
    }

    private fun tryRefreshResources(
        session: PlayerActor,
        player: HomePlayer,
        isRefresh4Res: Boolean,
        yieldRes: ResourceYield,
        now: Long
    ): Boolean {
        if (isRefresh4Res) {
            return true
        }
        val (wood, iron, stone, food, coin) = refreshResHelper.refreshPlayerResource(session, now)
        player.wood = wood
        player.iron = iron
        player.stone = stone
        player.food = food
        player.coin = coin
        yieldRes.calTime = now
        return true
    }

    /**
    资源扣除
     */
    fun costRes(
        session: PlayerActor,
        action: Int,
        player: HomePlayer,
        resVos: ResVo
    ) {
        val costs = LinkedList<ResVo>(
            Arrays.asList(resVos)
        )
        return costRes(session, action, player, costs)
    }

    fun costRes(
        session: PlayerActor,
        action: Int,
        player: HomePlayer,
        resVos: List<ResVo>
    ) {
        val rst = costResWithoutNotice(session, action, player, resVos)
        rst.noticeCostRes(session, player)
    }

    //不推送 扣除资源
    fun costResWithoutNotice(
        session: PlayerActor,
        action: Int,
        player: HomePlayer,
        resVo: ResVo
    ): CostResWithoutNoticeResult {
        val costs = LinkedList<ResVo>(
            Arrays.asList(resVo)
        )

        return costResWithoutNotice(session, action, player, costs)
    }

    //不推送 扣除资源
    fun costResWithoutNotice(
        session: PlayerActor,
        action: Int,
        player: HomePlayer,
        resVos: List<ResVo>
    ): CostResWithoutNoticeResult {
        return prepare(session) { homePlayerDC: HomePlayerDC, resourceYieldDC: ResourceYieldDC, bagDC: BagDC,
                                  newEquipDC: NewEquipDC, vipDC: VipDC, skinDC: SkinDC, friendDC: FriendDC,
                                  homeMyTargetDC: HomeMyTargetDC ->
            // 计算玩家资源数量
            val now = getNowTime()
            val yieldRes = resourceYieldDC.findResourceYield()
            var isNoticeResource = false // 用于标识是否需要推送资源数量变化消息3000
            var isRefresh4Res = false    // 是否刷新过基本的四种资源
            var isNoticeTowerNum = false //是否通知爬塔数据
            var propNotifier: PropsChangeNotifier? = null

            for (resVo in resVos) {
                // 避免刷资源的情况
                if (resVo.num < 0) {
                    normalLog.error(
                        "Un-reachable:CostRes() Resource Leak: PlayerId.%d,Type.%d,Num.%d.",
                        player.playerId,
                        resVo.resType,
                        resVo.num
                    )
                    continue
                }

                // 忽略没有变化的资源
                if (resVo.num == 0L) {
                    continue
                }

                // 是否需要推送资源变化
                if (resVo.resType != RES_HONOR && resVo.resType != RES_PROPS) {
                    isNoticeResource = true
                }

                when (resVo.resType) {
                    RES_SILVER_COIN -> {
                        //银币
                        if (player.silverCoin - resVo.num < 0) {
                            player.silverCoin = 0
                        } else {
                            player.silverCoin -= resVo.num
                        }
                    }
                    RES_GOLD_COIN -> {
                        //金币
                        if (player.goldCoin - resVo.num < 0) {
                            player.goldCoin = 0
                        } else {
                            player.goldCoin -= resVo.num
                        }
                    }
                    RES_CASINO_COIN -> {
                        //赌场币
                        if (player.casinoCoin - resVo.num < 0) {
                            player.casinoCoin = 0
                        } else {
                            player.casinoCoin -= resVo.num
                        }
                    }
                    RES_COIN -> {
                        //铜币
                        isRefresh4Res = tryRefreshResources(session, player, isRefresh4Res, yieldRes, now)
                        if (player.coin - resVo.num < 0) {
                            player.coin = 0
                        } else {
                            player.coin -= resVo.num
                        }
                    }
                    RES_ALLIANCE_COIN -> {
                        // 联盟币
                        if (player.allianceCoin - resVo.num < 0) {
                            player.allianceCoin = 0
                        } else {
                            player.allianceCoin -= resVo.num
                        }
                        homeMyTargetDC.targetInfo.allianceCoinCostNum += resVo.num
                    }
                    RES_HERO_EXP_POOL -> {
                        // 英雄经验池
                        if (player.heroExpPool - resVo.num < 0) {
                            player.heroExpPool = 0
                        } else {
                            player.heroExpPool -= resVo.num
                        }
                    }
                    RES_FOOD -> {
                        //粮食
                        isRefresh4Res = tryRefreshResources(session, player, isRefresh4Res, yieldRes, now)
                        if (player.food - resVo.num < 0) {
                            player.food = 0
                        } else {
                            player.food = player.food - resVo.num
                        }
                    }
                    RES_WOOD -> {
                        // 木头
                        isRefresh4Res = tryRefreshResources(session, player, isRefresh4Res, yieldRes, now)
                        if (player.wood - resVo.num < 0) {
                            player.wood = 0
                        } else {
                            player.wood -= resVo.num
                        }
                    }
                    RES_STONE -> {
                        // 石头
                        isRefresh4Res = tryRefreshResources(session, player, isRefresh4Res, yieldRes, now)
                        if (player.stone - resVo.num < 0) {
                            player.stone = 0
                        } else {
                            player.stone -= resVo.num
                        }
                    }
                    RES_IRON -> {
                        // 铁矿
                        isRefresh4Res = tryRefreshResources(session, player, isRefresh4Res, yieldRes, now)
                        if (player.iron - resVo.num < 0) {
                            player.iron = 0
                        } else {
                            player.iron -= resVo.num
                        }
                    }
                    RES_GOLD -> {
                        // 元宝
                        player.gold -= resVo.num

                        homeMyTargetDC.targetInfo.diamondCostNum += resVo.num
                        fireEvent(session, ResChangeEvent())
                    }
                    RES_HONOR ->
                        // 荣誉
                        player.honor -= resVo.num // 直接扣减玩家的总荣誉值
                    RES_BIND_GOLD -> {
                        // 绑定元宝/钻石
                        var costBindGold = 0L
                        if (player.bindGold >= resVo.num) {
                            player.bindGold -= resVo.num
                            costBindGold = resVo.num
                        } else {
                            val goldNum = resVo.num - player.bindGold
                            if (player.gold < goldNum) {
                                normalLog.lzInfo {
                                    "Un-reachable:CostRes() Player.Gold(${player.gold}) < GoldNum($goldNum) is impossible."
                                }
                                return@prepare CostResWithoutNoticeResult(resourceYieldDC, false, false, null)
                            }

                            costBindGold = player.bindGold

                            player.gold -= goldNum
                            player.bindGold = 0
                        }

                        homeMyTargetDC.targetInfo.diamondCostNum += resVo.num
                        fireEvent(session, ResChangeEvent())
                    }
                    RES_PROPS -> {
                        // 道具
                        val propsId = resVo.subType
                        val num = resVo.num

                        val propsVo = newEquipDC.findNormalBagPropByProtoId(bagDC, propsId.toInt())
                        if (propsVo == null || num > propsVo.haveNum) {
                            normalLog.lzInfo {
                                "Un-reachable:CostRes() FindEquipInfoByProtoId(PlayerId${player.playerId},PropsId$propsId) is null," +
                                        "or Num.$num > HaveNum."
                            }
                            return@prepare CostResWithoutNoticeResult(resourceYieldDC, false, false, null)
                        }

                        // 删除物品并推送给客户端
                        if (propNotifier == null) {
                            propNotifier = createPropsChangeNotifier()
                        }
                        propNotifier.append(
                            RemoveProps,
                            propsVo.id,
                            propsVo.equipProtoId,
                            num.toInt(),
                            propsVo.lv,
                            propsVo.exp,
                            propsVo.propertyMap
                        )

                        propHelper.removeProps(session, propsVo.id, num.toInt())
                    }
                    RES_JJC_COIN ->
                        // 竞技币
                        player.jjcCoin -= resVo.num.toInt() // 直接扣减玩家的竞技币
                    RES_HOUSE_COIN ->
                        // 家园币
                        player.houseCoin -= resVo.num
                    else -> {
                        normalLog.lzInfo { "Un-reachable:CostRes() ResVo.ResType == ${resVo.resType} is impossible." }
                        return@prepare CostResWithoutNoticeResult(resourceYieldDC, false, false, null)
                    }
                }
            }
            return@prepare CostResWithoutNoticeResult(resourceYieldDC, isNoticeResource, isNoticeTowerNum, propNotifier)
        }
    }

    // 资源增加
    fun addRes(session: PlayerActor, action: Int, player: HomePlayer, resVos: List<ResVo>): Boolean {
        return prepare(session) { homePlayerDC: HomePlayerDC, resourceYieldDC: ResourceYieldDC, bagDC: BagDC,
                                  newEquipDC: NewEquipDC, vipDC: VipDC, skinDC: SkinDC, friendDC: FriendDC,
                                  homeMyTargetDC: HomeMyTargetDC ->
            val rst =
                addResWithoutNotice(session, action, player, resVos)
            if (!rst.result) {
                return@prepare false
            }

            noticeAddRes(
                session,
                player,
                action,
                rst.isNoticeResource,
                rst.isKingExpChange,
                rst.isTalentPointChange,
                rst.isVipChange,
                rst.addResNotifier,
                rst.propChangeNotifier,
                rst.heroList,
                LinkedList(resVos),
                resourceYieldDC,
                vipDC
            )

            return@prepare true
        }
    }

    data class AddResWithoutNoticeResult(
        val result: Boolean,
        val isNoticeResource: Boolean,
        val isKingExpChange: Boolean,
        val isTalentPointChange: Boolean,
        val isVipChange: Boolean,
        val addResNotifier: AddResChangeNotifier?,
        val propChangeNotifier: PropsChangeNotifier?,
        val heroList: LinkedList<Hero>
    )

    // 计算玩家的5种基础资源的总和
    private fun resAllNum(session: PlayerActor): Long {
        val now = getNowTime()
        val (wood, iron, stone, food, coin) = refreshResHelper.refreshPlayerResource(session, now)
        return (wood + iron + stone + food + coin)
    }

    //不推送 增加资源
    fun addResWithoutNotice(
        session: PlayerActor,
        action: Int,
        player: HomePlayer,
        resVo: ResVo
    ): AddResWithoutNoticeResult {
        val costs = LinkedList<ResVo>(
            Arrays.asList(resVo)
        )

        return addResWithoutNotice(session, action, player, costs)
    }

    fun addResWithoutNotice(
        session: PlayerActor,
        action: Int,
        player: HomePlayer,
        resVos: List<ResVo>
    ): AddResWithoutNoticeResult {
        return prepare(session) { homePlayerDC: HomePlayerDC, resourceYieldDC: ResourceYieldDC, bagDC: BagDC,
                                  newEquipDC: NewEquipDC, vipDC: VipDC, skinDC: SkinDC, friendDC: FriendDC,
                                  homeMyTargetDC: HomeMyTargetDC ->
            // 刷新玩家资源数量
            val now = getNowTime()
            var isNoticeResource = false    // 用于标识是否需要推送资源数量变化消息3000
            var isRefresh4Res = false       // 是否刷新过基本的四种资源
            var isKingExpChange = false     //标识是否有君主经验变化
            var isTalentPointChange = false //标识是否天赋点变化
            var isVipChange = false         //标识是否vip变化

            var addResNotifier: AddResChangeNotifier? = null
            var propChangeNotifier: PropsChangeNotifier? = null

            // oncefun在当前函数体内，只执行一次
            val heroList = LinkedList<Hero>()
            val yieldRes = resourceYieldDC.findResourceYield()
            for (resVo in resVos) {
                // 如果执行这个断言，更多的可能是配置上的错误
                assert(resVo.num > 0) { "${resVo.resType} , ${resVo.num}" }

                // 忽略没有变化的资源
                if (resVo.num <= 0) {
                    continue
                }

                // 是否需要推送资源变化
                if (resVo.resType != RES_HONOR && resVo.resType != RES_PROPS) {
                    isNoticeResource = true
                }

                when (resVo.resType) {
                    RES_SILVER_COIN -> {
                        // 银币
                        player.silverCoin += resVo.num
                    }
                    RES_GOLD_COIN -> {
                        // 金币
                        player.goldCoin += resVo.num
                    }
                    RES_CASINO_COIN -> {
                        // 赌场币
                        player.casinoCoin += resVo.num
                    }
                    RES_COIN -> {
                        // 铜币
                        val resAllNum = resAllNum(session)
                        // 总资源锁 达到这个值的话就不允许资源在被获取到,自然增长不影响 因为自然增长跟这个值比是微不足道的
                        if (resAllNum < pcs.basicProtoCache.resStorageLimit) {
                            player.coin += resVo.num
                        }
                    }
                    RES_ALLIANCE_COIN ->
                        // 联盟币
                        player.allianceCoin += resVo.num
                    RES_HERO_EXP_POOL ->
                        // 联盟币
                        player.heroExpPool += resVo.num
                    RES_FOOD -> {
                        // 粮食
                        isRefresh4Res = tryRefreshResources(session, player, isRefresh4Res, yieldRes, now)
                        val resAllNum = resAllNum(session)
                        // 总资源锁 达到这个值的话就不允许资源在被获取到,自然增长不影响 因为自然增长跟这个值比是微不足道的
                        if (resAllNum < pcs.basicProtoCache.resStorageLimit) {
                            player.food += resVo.num
                        }
                    }
                    RES_WOOD -> {
                        // 木头
                        isRefresh4Res = tryRefreshResources(session, player, isRefresh4Res, yieldRes, now)
                        val resAllNum = resAllNum(session)
                        // 总资源锁 达到这个值的话就不允许资源在被获取到,自然增长不影响 因为自然增长跟这个值比是微不足道的
                        if (resAllNum < pcs.basicProtoCache.resStorageLimit) {
                            player.wood += resVo.num
                        }
                    }
                    RES_STONE -> {
                        // 石头
                        isRefresh4Res = tryRefreshResources(session, player, isRefresh4Res, yieldRes, now)
                        val resAllNum = resAllNum(session)
                        // 总资源锁 达到这个值的话就不允许资源在被获取到,自然增长不影响 因为自然增长跟这个值比是微不足道的
                        if (resAllNum < pcs.basicProtoCache.resStorageLimit) {
                            player.stone += resVo.num
                        }
                    }
                    RES_IRON -> {
                        // 铁矿
                        isRefresh4Res = tryRefreshResources(session, player, isRefresh4Res, yieldRes, now)
                        val resAllNum = resAllNum(session)
                        // 总资源锁 达到这个值的话就不允许资源在被获取到,自然增长不影响 因为自然增长跟这个值比是微不足道的
                        if (resAllNum < pcs.basicProtoCache.resStorageLimit) {
                            player.iron += resVo.num
                        }
                    }
                    RES_GOLD -> {
                        // 元宝
                        player.gold += resVo.num
                    }
                    RES_DECREE -> {
                        // 行动力，只增不减，直接推送world
                        val askMsg = AddDecreeAskReq.newBuilder()
                        askMsg.addNum = resVo.num.toInt()
                        askMsg.useProp = boolToInt(false)
                        session.createACS<Home2WorldAskResp>()
                            .ask(session.worldShardProxy, session.fillHome2WorldAskMsgHeader {
                                it.setAddDecreeAskReq(askMsg)
                            }, Home2WorldAskResp::class.java)
                            .whenCompleteKt { askResp, askErr ->

                                try {
                                    when {
                                        askErr != null -> {
                                        }
                                        askResp == null -> {

                                        }
                                        else -> {
                                            val rt = askResp.addDecreeAskRt
                                            if (rt.rt != ResultCode.SUCCESS.code) {

                                            } else {

                                            }
                                        }
                                    }

                                } catch (e: Exception) {
                                }
                            }
                    }
                    RES_HONOR -> {
                        // 荣誉
                        player.honor += resVo.num // 通过邮件奖励等方式获得的荣誉值，只算入玩家的总荣誉，不算入本周或本日的荣誉值
                        if (player.allianceId != 0L) {
                            val updateInfoMap = hashMapOf<Int, String>()
                            updateInfoMap[UpdateHonor] = resVo.num.toString()
                            updateAllianceMemberInfo(session, player.allianceId, player.playerId, updateInfoMap)
                        }
                    }
                    RES_BIND_GOLD -> {
                        // 绑定元宝
                        player.bindGold += resVo.num
                    }
                    RES_PROPS -> {
                        // 道具
                        val propsId = resVo.subType
                        val num = resVo.num

                        val newEquips = propHelper.getProps(session, propsId.toInt(), num.toInt())

                        // 推送给客户端
                        if (propChangeNotifier == null) {
                            propChangeNotifier = createPropsChangeNotifier()
                        }

                        // 获取道具
                        fireEvent(session, PropChangeEvent(propsId.toInt()))

                        for (newEquip in newEquips) {
                            val newProto = pcs.equipCache.equipProtoMap[newEquip.equipProtoId]
                            if (newProto == null) {
                                normalLog.lzDebug { "pcs.equipCache.equipProtoMap[newEquip.equipProtoId] == null" }
                                continue
                            }
                            if (newProto.useAtOnce == 1) {
                                fireEvent(session, UsePropsAtOnceEvent(newEquip.id, newProto.id, newEquip.haveNum))
                            } else {
                                propChangeNotifier.append(
                                    AddProps,
                                    newEquip.id,
                                    newEquip.equipProtoId,
                                    num.toInt(),
                                    newEquip.lv,
                                    newEquip.exp,
                                    newEquip.propertyMap
                                )
                            }
                        }
                    }
                    RES_JJC_COIN ->
                        // 竞技币
                        player.jjcCoin += resVo.num.toInt() // 直接增加玩家的竞技币
                    RES_HOUSE_COIN ->
                        // 家园币
                        player.houseCoin += resVo.num
                    RES_KING_EXP -> {
                        if (player.kingLv < pcs.kingExpCache.maxLvProto.level) {
                            var addExp = resVo.num
                            var currentLv = player.kingLv
                            var currentExp = player.kingExp
                            while (addExp > 0) {
                                val proto = pcs.kingExpCache.kingExpMap[currentLv]
                                if (proto == null) {
                                    normalLog.lzDebug { "pcs.kingExpCache.kingExpMap[currentLv] == null" }
                                    continue
                                }
                                if (currentExp + addExp < proto.exp) {
                                    currentExp += addExp.toInt()
                                    addExp = 0
                                } else {
                                    addExp = currentExp + addExp - proto.exp
                                    currentExp = 0
                                    currentLv += 1
                                    if (currentLv == pcs.kingExpCache.maxLvProto.level) {
                                        addExp = 0
                                    }
                                }
                            }

                            val oldKingLv = player.kingLv
                            player.kingLv = currentLv
                            player.kingExp = currentExp

                            // 把变化后的城池等级同步到世界去
                            val askMsg = UpdateInfoByHomeAskReq.newBuilder()
                            val updateInfoByHomeVo = UpdateInfoByHomeVo.newBuilder()
                            updateInfoByHomeVo.updateType = UPDATE_INFO_BY_HOME_PLAYER_KING_LV
                            updateInfoByHomeVo.updateValue = toJson(currentLv)
                            askMsg.addUpdates(updateInfoByHomeVo)
                            val updateInfoByHomeVo2 = UpdateInfoByHomeVo.newBuilder()
                            updateInfoByHomeVo2.updateType = UPDATE_INFO_BY_HOME_PLAYER_KING_EXP
                            updateInfoByHomeVo2.updateValue = toJson(currentExp)
                            askMsg.addUpdates(updateInfoByHomeVo2)

                            session.createACS<Home2WorldAskResp>()
                                .ask(
                                    session.worldShardProxy,
                                    session.fillHome2WorldAskMsgHeader { it.setUpdateInfoByHomeAskReq(askMsg) },
                                    Home2WorldAskResp::class.java
                                )
                                .whenCompleteKt { askResp, askErr ->

                                    try {
                                        when {
                                            askErr != null -> {
                                            }
                                            askResp == null -> {

                                            }
                                            else -> {
                                                val rt = askResp.updateInfoByHomeAskRt
                                                if (rt.rt != ResultCode.SUCCESS.code) {

                                                } else {

                                                }
                                            }
                                        }

                                    } catch (e: Exception) {
                                    }
                                }

                            if (oldKingLv < currentLv) {
                                //发送君主等级变化事件
                                fireEvent(session, KingLvUpEvent(oldKingLv, currentLv))

                                var mTp = 0
                                var eTp = 0
                                var monsterTp = 0
                                for ((tId, tLv) in player.unlockedTalentMap) {

                                    val tProtoMap = pcs.talentCache.talentIdMap[tId]
                                    if (tProtoMap == null) {
                                        normalLog.error("pcs.talentCache.talentIdMap ${tId}天赋模板不存在!! ")
                                        continue
                                    }

                                    // 从0到现在的等级的消耗叠加
                                    for (lv in 1..tLv) {
                                        val tProto = tProtoMap[lv]
                                        if (tProto == null) {
                                            normalLog.error("tProtos $lv 天赋模板不存在!! ")
                                            continue
                                        }

                                        for ((cK, cV) in tProto.cost) {
                                            if (cK == MilitaryTalent) {
                                                mTp += cV
                                            } else if (cK == EconomyTalent) {
                                                eTp += cV
                                            } else if (cK == MonsterTalent) {
                                                monsterTp += cV
                                            }
                                        }
                                    }
                                }

                                val kingExpProto = pcs.kingExpCache.kingExpMap[currentLv]
                                if (kingExpProto == null) {
                                    normalLog.lzDebug { "pcs.kingExpCache.kingExpMap[currentLv] == null" }
                                    return@prepare AddResWithoutNoticeResult(
                                        false,
                                        false,
                                        false,
                                        false,
                                        false,
                                        null,
                                        null,
                                        heroList
                                    )
                                }

                                var currentMTP = kingExpProto.militaryTalent - mTp
                                if (currentMTP < 0) {
                                    currentMTP = 0
                                }

                                var currentETP = kingExpProto.economicsTalent - eTp
                                if (currentETP < 0) {
                                    currentETP = 0
                                }

                                var currentMonsterTP = kingExpProto.monsterTalent - monsterTp
                                if (currentMonsterTP < 0) {
                                    currentMonsterTP = 0
                                }

                                //增加科技点数
                                player.talentPointMap[MilitaryTalent] = currentMTP
                                player.talentPointMap[EconomyTalent] = currentETP
                                player.talentPointMap[MonsterTalent] = currentMonsterTP
                                isTalentPointChange = true

                                //重算君主实力
                                targetHelper.targetAddVal(session, KingStrength, null)
                            }
                            isKingExpChange = true
                        }
                    }
                    RES_VIP_EXP -> {
                        isVipChange = true
                        //增加vip经验
                        var addExp = resVo.num
                        val vipInfo = vipDC.vipInfo
                        var currentLv = vipInfo.vipLv
                        var currentExp = vipInfo.vipExp
                        while (addExp > 0) {
                            if (currentLv >= pcs.vipSetCache.maxLvVip.level) {
                                break
                            }
                            val nextProto = pcs.vipSetCache.vipSetMap[currentLv]
                            if (nextProto == null) {
                                normalLog.lzDebug { "pcs.vipSetCache.vipSetMap[currentLv] == null" }
                                continue
                            }
                            if (currentExp + addExp < nextProto.needExp) {
                                currentExp += addExp.toInt()
                                addExp = 0
                            } else {
                                addExp = currentExp + addExp - nextProto.needExp
                                currentExp = 0
                                currentLv += 1

                            }
                        }
                        val oldVipLv = vipInfo.vipLv
                        //vip升级
                        vipInfo.vipLv = currentLv

                        if (player.allianceId != 0L) {
                            val updateInfoMap = hashMapOf<Int, String>()
                            updateInfoMap[UpdateVipLv] = vipInfo.vipLv.toString()
                            updateAllianceMemberInfo(session, player.allianceId, player.playerId, updateInfoMap)
                        }

                        vipInfo.vipExp = currentExp
                        if (oldVipLv < currentLv) {
                            //vip等级变化导致的属性加成变化
                            isVipChange = true
                            val tmp = pcs.vipSetCache.vipSetMap[currentLv]
                            if (tmp == null) {
                                normalLog.lzDebug { "pcs.vipSetCache.vipSetMap[currentLv] == null" }
                                return@prepare AddResWithoutNoticeResult(
                                    false,
                                    false,
                                    false,
                                    false,
                                    false,
                                    null,
                                    null,
                                    heroList
                                )
                            }
                            val ability = tmp.ability
                            //vip升级奖励
                            for (lv in oldVipLv + 1..currentLv) {
                                val proto = pcs.vipSetCache.vipSetMap[lv]
                                if (proto == null) {
                                    normalLog.lzDebug { "pcs.vipSetCache.vipSetMap[lv] == null" }
                                    continue
                                }
                                addRes(session, ACTION_VIP_LV_UP_REWARD, player, proto.reward)
                            }
                            //发送vip等级变化事件
                            fireEvent(
                                session,
                                VipLvChangeEvent(oldVipLv, currentLv, ability, effectHelper, refreshResHelper)
                            )

                            // tell home 向好友推送
                            var skinType = 1
                            val skins = skinDC.findSkinsByPlayerId()
                            for (skin in skins) {
                                if (skin.isUse == 1) {
                                    skinType = skin.skinType
                                    break
                                }
                            }
                            val tellHomeMsg = FriendRefreshNoticeTell.newBuilder()
                            tellHomeMsg.myPlayerId = player.playerId
                            tellHomeMsg.photoProtoId = player.photoProtoId
                            tellHomeMsg.name = player.name
                            tellHomeMsg.areaNo = player.areaNo
                            tellHomeMsg.vipLv = currentLv
                            tellHomeMsg.allianceShortName = player.allianceShortName
                            tellHomeMsg.state = 0
                            tellHomeMsg.castleLv = player.castleLv
                            tellHomeMsg.skinType = skinType
                            tellHomeMsg.shortName = player.allianceNickName
                            val myFriends = friendDC.findFriendById()

                            for (friend in myFriends) {
                                val home2homeTell = session.fillHome2HomeTellMsgHeader(
                                    friend.tarPlayerId
                                ) { it.setFriendRefreshNoticeTell(tellHomeMsg) }
                                session.tellHome(home2homeTell)
                            }
                        }
                    }
                }

                // 添加需要推送资源增加的信息
                if (resVo.resType == RES_GOLD || resVo.resType == RES_BIND_GOLD || resVo.resType == RES_COIN || resVo.resType == RES_ALLIANCE_COIN ||
                    resVo.resType == RES_WOOD || resVo.resType == RES_IRON || resVo.resType == RES_STONE || resVo.resType == RES_FOOD ||
                    resVo.resType == RES_DECREE || resVo.resType == RES_FAME || resVo.resType == RES_HONOR || resVo.resType == RES_JJC_COIN ||
                    resVo.resType == RES_HERO_EXP_POOL || resVo.resType == RES_HOUSE_COIN || resVo.resType == RES_CASINO_COIN ||
                    resVo.resType == RES_SILVER_COIN || resVo.resType == RES_GOLD_COIN
                ) {
                    if (addResNotifier == null) {
                        addResNotifier = createAddResChangeNotifier(action)
                    }
                    addResNotifier.append(resVo.resType, 0, resVo.num.toInt())

                    var resInfo = homeMyTargetDC.targetInfo.getResInfo.getOrPut(resVo.resType) { 0 }
                    resInfo += resVo.num
                    homeMyTargetDC.targetInfo.getResInfo[resVo.resType] = resInfo
                }
            }

            fireEvent(session, ResChangeEvent())
            return@prepare AddResWithoutNoticeResult(
                true,
                isNoticeResource,
                isKingExpChange,
                isTalentPointChange,
                isVipChange,
                addResNotifier,
                propChangeNotifier,
                heroList
            )
        }
    }

    //通知添加资源
    private fun noticeAddRes(
        session: PlayerActor,
        player: HomePlayer,
        action: Int,
        isNoticeResource: Boolean,
        isKingExpChange: Boolean,
        isTalentPointChange: Boolean,
        isVipChange: Boolean,
        addResNotifier: AddResChangeNotifier?,
        propChangeNotifier: PropsChangeNotifier?,
        heroList: LinkedList<Hero>,
        resVos: LinkedList<ResVo>,
        resourceYieldDC: ResourceYieldDC,
        vipDC: VipDC
    ) {

        if (isNoticeResource) {
            // 资源添加后，推送真实的资源数量
            val yieldRes = resourceYieldDC.findResourceYield()
            val refreshResourceNotifier = createRefreshResourceNotifier(
                player, player.wood, player.food, player.iron,
                player.stone, player.decree, yieldRes.calTime
            )
            refreshResourceNotifier.notice(session)
        }

        if (heroList.count() > 0) {
            //刷新英雄实力
            targetHelper.targetAddVal(session, HeroStrength, null)
            targetHelper.refreshAllHeroPower(session, false)
            // 推送新增的武将信息
            sendHeroListInfo(session, heroList)
        }

        //君主经验推送
        if (isKingExpChange) {
            createKingExpChangeNotifier(player.kingLv, player.kingExp).notice(session)
        }

        //天赋点推送
        if (isTalentPointChange) {
            createTalentPointChangeNotifier(player.talentPointMap).notice(session)
        }

        //vip推送
        if (isVipChange) {
            val vipInfo = vipDC.vipInfo
            val tomorrowGetExp =
                (vipInfo.vipLv * pcs.basicProtoCache.vipEveryDayGet * (1 + 0.2 * vipInfo.continueOnlineDay))
                    .toInt()

            createVipChangeNotifier(
                vipInfo.vipLv,
                vipInfo.vipExp,
                tomorrowGetExp
            ).notice(session)
        }

        //道具推送
        if (propChangeNotifier != null) {
            propChangeNotifier.notice(session)
        }

        if (addResNotifier != null) {
            addResNotifier.notice(session)
        }

        // 有获得奖励就推送消息
        if (resVos.size > 0) {
            createGetResNotifier(action, resVoToResString(resVos)).notice(session)
        }
    }

}
