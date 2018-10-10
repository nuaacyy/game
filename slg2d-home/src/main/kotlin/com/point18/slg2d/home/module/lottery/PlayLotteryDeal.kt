package com.point18.slg2d.home.module.lottery

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.*
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.sendHeroListInfo
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus6
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.LotteryEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createLotteryChangeNotifier
import pb4client.DrawResInfo
import pb4client.PlayLottery
import pb4client.PlayLotteryRt
import java.util.*

class PlayLotteryDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus6<
            HomePlayerDC,
            LotteryDC,
            NewEquipDC,
            BagDC,
            HeroDC,
            HomeMyTargetDC
            >(
        HomePlayerDC::class.java,
        LotteryDC::class.java,
        NewEquipDC::class.java,
        BagDC::class.java,
        HeroDC::class.java,
        HomeMyTargetDC::class.java,
        Arrays.asList(resHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val protoId = (msg as PlayLottery).protoId
        val drawTimes = msg.drawTimes

        prepare(session) { homePlayerDC, lotteryDC, newEquipDC, bagDC, heroDC, homeMyTargetDC ->
            val rt = playLottery(
                session,
                protoId,
                drawTimes,
                homePlayerDC,
                lotteryDC,
                newEquipDC,
                bagDC,
                heroDC,
                homeMyTargetDC
            )
            session.sendMsg(MsgType.PlayLottery_1546, rt)
        }

    }

    private fun playLottery(
        session: PlayerActor,
        protoId: Int,
        drawTimes: Int,
        homePlayerDC: HomePlayerDC,
        lotteryDC: LotteryDC,
        newEquipDC: NewEquipDC,
        bagDC: BagDC,
        heroDC: HeroDC,
        homeMyTargetDC: HomeMyTargetDC
    ): PlayLotteryRt {
        val rt = PlayLotteryRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        // 验证次数
        if (drawTimes != 1 && drawTimes != 10) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        // 验证模板
        val proto = pcs.drawHeroProtoCache.dropBagMap[protoId]
        if (proto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        // 验证活动时间
        val now = getNowTime()
        if (now > proto.actEndTime || now < proto.actStartTime) {
            if (proto.actEndTime != 0L && proto.actStartTime != 0L) {
                rt.rt = ResultCode.LOTTERY_OUT_OF_DATE.code
                return rt.build()
            }
        }

        val lotteryS = lotteryDC.findDrawHeroByPlayerId(session.playerId)
        val lotteryList = lotteryS.filter { it.protoId == protoId }

        if (lotteryList.size != 1) {
            normalLog.error("draw_card表数据出现问题!")
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        val lottery = lotteryList[0]
        // 抽到的东西list
        val dropList = LinkedList<Drop>()
        // 必得的奖励list
        val rewardList = LinkedList<ResVo>()
        // 消耗
        val costRes = LinkedList<ResVo>()
        // 获得的英雄
        val heroList = LinkedList<Hero>()

        // 是否到每天6点定时刷新折扣 和普通的次数
        val lastRefreshDiscount = getDiscountRefreshTime(lottery.lastUseDiscountTime)
        val nowRefreshDiscount = getDiscountRefreshTime(now)
        if (nowRefreshDiscount - lastRefreshDiscount > 24 * 3600 * 1000) {
            lottery.lastUseDiscountTime = now
            lottery.discountToday = 1
        }

        when (lottery.protoId) {
        // 普通
            NORMAL_LOTTERY -> {
                if (drawTimes == 1) {
                    if (lottery.restFreeDrawTimes != 0
                        && (now >= lottery.lastFreeDrawTime + pcs.basicProtoCache.lowDropFreeCd * 1000)
                    ) {
                        // todo 免费抽卡
                        val drop = drawCard(proto, lottery)
                        if (drop != null) {
                            dropList.add(drop)
                        }
                        val mustGet = proto.mustGetResVo
                        rewardList += mustGet
                        lottery.restFreeDrawTimes--
                        lottery.lastFreeDrawTime = now
                    } else {
                        // todo 消耗抽卡
                        costRes += proto.oneCostResVo

                        // 资源检测和消耗，还有补全
                        var checkResPass = resHelper.checkRes(session, costRes)
                        if (!checkResPass) {
                            // 道具不足  补全
                            val propsId = costRes[0].subType.toInt()
                            val shouldCost = costRes[0].num.toInt()
                            var costNum1 = 0
                            val propsProto = pcs.equipCache.equipProtoMap[propsId]
                            if (propsProto == null) {
                                rt.rt = ResultCode.NO_PROTO.code
                                return rt.build()
                            }
                            val propsCache =
                                newEquipDC.findNormalBagPropByProtoId(bagDC, propsId)

                            if (propsCache == null) {
                                costNum1 = shouldCost
                            } else {
                                costNum1 = shouldCost - propsCache.haveNum
                            }
                            val costNum2 = shouldCost - costNum1
                            costRes.clear()
                            if (costNum2 != 0) {
                                val propsCost = ResVo(RES_PROPS, propsId.toLong(), costNum2.toLong())
                                costRes.add(propsCost)
                            }
                            for (i in 1..costNum1) {
                                costRes += propsProto.fastBuyMap
                            }
                        }

                        checkResPass = resHelper.checkRes(session, costRes)
                        if (!checkResPass) {
                            rt.rt = ResultCode.LESS_RESOUCE.code
                            return rt.build()
                        }
                        resHelper.costRes(session, ACTION_EXTRACT_CHEST, homePlayerDC.player, costRes)
                        val mustGet = proto.mustGetResVo
                        rewardList += mustGet
                        val drop = drawCard(proto, lottery)
                        if (drop != null) {
                            dropList.add(drop)
                        }
                    }
                }

                if (drawTimes == 10) {
                    // 检查折扣
                    if (lottery.discountToday == 1) {
                        costRes += proto.moreCostDiscountResVo
                    } else {
                        costRes += proto.moreCostResVo
                    }

                    // 资源检测和消耗，还有补全
                    var checkResPass = resHelper.checkRes(session, costRes)
                    if (!checkResPass) {
                        // 道具不足  补全
                        val propsId = costRes[0].subType.toInt()
                        val shouldCost = costRes[0].num.toInt()
                        var costNum1 = 0
                        val propsProto = pcs.equipCache.equipProtoMap[propsId]
                        if (propsProto == null) {
                            rt.rt = ResultCode.NO_PROTO.code
                            return rt.build()
                        }
                        val propsCache = newEquipDC.findNormalBagPropByProtoId(bagDC, propsId)
                        if (propsCache == null) {
                            costNum1 = shouldCost
                        } else {
                            costNum1 = shouldCost - propsCache.haveNum
                        }
                        val costNum2 = shouldCost - costNum1
                        costRes.clear()
                        if (costNum2 != 0) {
                            val propsCost = ResVo(RES_PROPS, propsId.toLong(), costNum2.toLong())
                            costRes.add(propsCost)
                        }
                        for (i in 1..costNum1) {
                            costRes += propsProto.fastBuyMap
                        }
                    }

                    checkResPass = resHelper.checkRes(session, costRes)
                    if (!checkResPass) {
                        rt.rt = ResultCode.LESS_RESOUCE.code
                        return rt.build()
                    }
                    resHelper.costRes(session, ACTION_EXTRACT_CHEST, homePlayerDC.player, costRes)

                    // 折扣--
                    if (lottery.discountToday > 0) {
                        lottery.discountToday--
                        lottery.lastUseDiscountTime = now
                    }

                    // 抽卡
                    for (i in 1..10) {
                        val mustGet = proto.mustGetResVo
                        rewardList += mustGet
                        val drop = drawCard(proto, lottery)
                        if (drop != null) {
                            dropList.add(drop)
                        }
                    }
                }
                createLotteryChangeNotifier(
                    lottery.protoId,
                    lottery.lastFreeDrawTime / 1000 + pcs.basicProtoCache.lowDropFreeCd,
                    lottery.restFreeDrawTimes,
                    lottery.discountToday,
                    (10 - lottery.drawSum % 10).toInt(),
                    proto.actStartTime / 1000,
                    proto.actEndTime / 1000
                )
                    .notice(session)

                homeMyTargetDC.targetInfo.normalLotteryNum += drawTimes
            }

        // 高级
            SUPER_LOTTERY -> {

                if (drawTimes == 1) {
                    if (lottery.restFreeDrawTimes != 0
                        && (now >= lottery.lastFreeDrawTime + pcs.basicProtoCache.highDropFreeCd * 1000)
                    ) {
                        // todo 免费抽卡
                        val drop = drawCard(proto, lottery)
                        if (drop != null) {
                            dropList.add(drop)
                        }
                        val mustGet = proto.mustGetResVo
                        rewardList += mustGet
                        lottery.restFreeDrawTimes--
                        lottery.lastFreeDrawTime = now
                    } else {
                        // todo 消耗抽卡
                        costRes += proto.oneCostResVo

                        // 资源检测和消耗，还有补全
                        var checkResPass = resHelper.checkRes(session, costRes)
                        if (!checkResPass) {
                            // 道具不足  补全
                            val propsId = costRes[0].subType.toInt()
                            val shouldCost = costRes[0].num.toInt()
                            var costNum1 = 0
                            val propsProto = pcs.equipCache.equipProtoMap[propsId]
                            if (propsProto == null) {
                                rt.rt = ResultCode.NO_PROTO.code
                                return rt.build()
                            }
                            val propsCache = newEquipDC.findNormalBagPropByProtoId(bagDC, propsId)
                            if (propsCache == null) {
                                costNum1 = shouldCost
                            } else {
                                costNum1 = shouldCost - propsCache.haveNum
                            }
                            val costNum2 = shouldCost - costNum1
                            costRes.clear()
                            if (costNum2 != 0) {
                                val propsCost = ResVo(RES_PROPS, propsId.toLong(), costNum2.toLong())
                                costRes.add(propsCost)
                            }
                            for (i in 1..costNum1) {
                                costRes += propsProto.fastBuyMap
                            }
                        }

                        checkResPass = resHelper.checkRes(session, costRes)
                        if (!checkResPass) {
                            rt.rt = ResultCode.LESS_RESOUCE.code
                            return rt.build()
                        }
                        resHelper.costRes(session, ACTION_EXTRACT_CHEST, homePlayerDC.player, costRes)
                        val mustGet = proto.mustGetResVo
                        rewardList += mustGet
                        val drop = drawCard(proto, lottery)
                        if (drop != null) {
                            dropList.add(drop)
                        }
                    }
                }

                if (drawTimes == 10) {
                    // 检查折扣
                    if (lottery.discountToday >= 1) {
                        costRes += proto.moreCostDiscountResVo
                        lottery.discountToday--
                    } else {
                        costRes += proto.moreCostResVo
                    }

                    // 资源检测和消耗，还有补全
                    var checkResPass = resHelper.checkRes(session, costRes)
                    if (!checkResPass) {
                        // 道具不足  补全
                        val propsId = costRes[0].subType.toInt()
                        val shouldCost = costRes[0].num.toInt()
                        var costNum1 = 0
                        val propsProto = pcs.equipCache.equipProtoMap[propsId]
                        if (propsProto == null) {
                            rt.rt = ResultCode.NO_PROTO.code
                            return rt.build()
                        }
                        val propsCache = newEquipDC.findNormalBagPropByProtoId(bagDC, propsId)
                        if (propsCache == null) {
                            costNum1 = shouldCost
                        } else {
                            costNum1 = shouldCost - propsCache.haveNum
                        }
                        val costNum2 = shouldCost - costNum1
                        costRes.clear()
                        if (costNum2 != 0) {
                            val propsCost = ResVo(RES_PROPS, propsId.toLong(), costNum2.toLong())
                            costRes.add(propsCost)
                        }
                        for (i in 1..costNum1) {
                            costRes += propsProto.fastBuyMap
                        }
                    }

                    // 资源检测和消耗
                    checkResPass = resHelper.checkRes(session, costRes)
                    if (!checkResPass) {
                        rt.rt = ResultCode.LESS_RESOUCE.code
                        return rt.build()
                    }
                    resHelper.costRes(session, ACTION_EXTRACT_CHEST, homePlayerDC.player, costRes)

                    // 折扣--
                    if (lottery.discountToday > 0) {
                        lottery.discountToday--
                        lottery.lastUseDiscountTime = now
                    }

                    // 抽卡
                    for (i in 1..10) {
                        val mustGet = proto.mustGetResVo
                        rewardList += mustGet
                        val drop = drawCard(proto, lottery)
                        if (drop != null) {
                            dropList.add(drop)
                        }
                    }
                }

                createLotteryChangeNotifier(
                    lottery.protoId,
                    lottery.lastFreeDrawTime / 1000 + pcs.basicProtoCache.highDropFreeCd,
                    lottery.restFreeDrawTimes,
                    lottery.discountToday,
                    (10 - lottery.drawSum % 10).toInt(),
                    proto.actStartTime / 1000,
                    proto.actEndTime / 1000
                )
                    .notice(session)

                homeMyTargetDC.targetInfo.superLotteryNum += drawTimes

            }

        // 活动
            else -> {
                if (drawTimes == 1) {

                    // todo 活动有没有免费抽卡？？
                    lottery.restFreeDrawTimes = 0

                    // todo 消耗抽卡
                    costRes += proto.oneCostResVo

                    // 资源检测和消耗，还有补全
                    var checkResPass = resHelper.checkRes(session, costRes)
                    if (!checkResPass) {
                        // 道具不足  补全
                        val propsId = costRes[0].subType.toInt()
                        val shouldCost = costRes[0].num.toInt()
                        var costNum1 = 0
                        val propsProto = pcs.equipCache.equipProtoMap[propsId]
                        if (propsProto == null) {
                            rt.rt = ResultCode.NO_PROTO.code
                            return rt.build()
                        }
                        val propsCache = newEquipDC.findNormalBagPropByProtoId(bagDC, propsId)
                        if (propsCache == null) {
                            costNum1 = shouldCost
                        } else {
                            costNum1 = shouldCost - propsCache.haveNum
                        }
                        val costNum2 = shouldCost - costNum1
                        costRes.clear()
                        if (costNum2 != 0) {
                            val propsCost = ResVo(RES_PROPS, propsId.toLong(), costNum2.toLong())
                            costRes.add(propsCost)
                        }
                        for (i in 1..costNum1) {
                            costRes += propsProto.fastBuyMap
                        }
                    }

                    checkResPass = resHelper.checkRes(session, costRes)
                    if (!checkResPass) {
                        rt.rt = ResultCode.LESS_RESOUCE.code
                        return rt.build()
                    }
                    resHelper.costRes(session, ACTION_EXTRACT_CHEST, homePlayerDC.player, costRes)
                    val mustGet = proto.mustGetResVo
                    rewardList += mustGet
                    val drop = drawCard(proto, lottery)
                    if (drop != null) {
                        dropList.add(drop)
                    }

                }

                if (drawTimes == 10) {
                    // 检查折扣
                    if (lottery.discountToday == 1) {
                        costRes += proto.moreCostDiscountResVo
                    } else {
                        costRes += proto.moreCostResVo
                    }

                    // 资源检测和消耗
                    // 资源检测和消耗，还有补全
                    var checkResPass = resHelper.checkRes(session, costRes)
                    if (!checkResPass) {
                        // 道具不足  补全
                        val propsId = costRes[0].subType.toInt()
                        val shouldCost = costRes[0].num.toInt()
                        var costNum1 = 0
                        val propsProto = pcs.equipCache.equipProtoMap[propsId]
                        if (propsProto == null) {
                            rt.rt = ResultCode.NO_PROTO.code
                            return rt.build()
                        }
                        val propsCache = newEquipDC.findNormalBagPropByProtoId(bagDC, propsId)
                        if (propsCache == null) {
                            costNum1 = shouldCost
                        } else {
                            costNum1 = shouldCost - propsCache.haveNum
                        }
                        val costNum2 = shouldCost - costNum1
                        costRes.clear()
                        if (costNum2 != 0) {
                            val propsCost = ResVo(RES_PROPS, propsId.toLong(), costNum2.toLong())
                            costRes.add(propsCost)
                        }
                        for (i in 1..costNum1) {
                            costRes += propsProto.fastBuyMap
                        }
                    }

                    checkResPass = resHelper.checkRes(session, costRes)
                    if (!checkResPass) {
                        rt.rt = ResultCode.LESS_RESOUCE.code
                        return rt.build()
                    }
                    resHelper.costRes(session, ACTION_EXTRACT_CHEST, homePlayerDC.player, costRes)

                    // 折扣--
                    if (lottery.discountToday > 0) {
                        lottery.discountToday--
                        lottery.lastUseDiscountTime = now
                    }

                    // 抽卡
                    for (i in 1..10) {
                        val mustGet = proto.mustGetResVo
                        rewardList += mustGet
                        val drop = drawCard(proto, lottery)
                        if (drop != null) {
                            dropList.add(drop)
                        }
                    }
                }

                createLotteryChangeNotifier(
                    lottery.protoId,
                    lottery.lastFreeDrawTime / 1000 + pcs.basicProtoCache.highDropFreeCd,
                    lottery.restFreeDrawTimes,
                    lottery.discountToday,
                    (10 - lottery.drawSum % 10).toInt(),
                    proto.actStartTime / 1000,
                    proto.actEndTime / 1000
                )
                    .notice(session)
            }
        }

        // 英雄碎片转换
        val drawResInfoList = LinkedList<DrawResInfo>()
        for (eachDrop in dropList) {
            val drawResInfo = DrawResInfo.newBuilder()
            // 看看是不是英雄碎片
            val propsId = eachDrop.id
            val propsProto = pcs.equipCache.equipProtoMap[propsId]
            if (propsProto == null) {
                // todo 这些需要在 模板加载时候验证
                normalLog.error("$propsId,抽奖奖品拿不到id")
                continue
            }
            if (propsProto.mainType == PROP_HERO) {
                // 英雄碎片
                // 检查有没有这个英雄
                val listHero = heroDC.findHeroList()
                val heroId = propsProto.extend1.toIntOrNull()
                val star = pcs.basicProtoCache.shardConvertMap[eachDrop.num]

                if (heroId == null || star == null) {
                    drawResInfo.type = 2
                    drawResInfo.num = eachDrop.num
                    drawResInfo.protoId = eachDrop.id

                    rewardList += ResVo(RES_PROPS, eachDrop.id.toLong(), eachDrop.num.toLong())

                } else {
                    val haveHero = listHero.filter { it.protoId == heroId }
                    if (haveHero.size == 0) {
                        // todo 转成英雄
                        val hero = heroDC.createHero(session.playerId, 0, heroId)
                        if (hero == null) {
                            normalLog.error("找不到英雄${heroId}的模板出错，找不到转换 ")
                            continue
                        } else {
                            hero.advLv = star
                            drawResInfo.type = 1
                            drawResInfo.num = 1
                            drawResInfo.protoId = heroId
                            heroList.add(hero)
                        }

                    } else {
                        drawResInfo.type = 3
                        drawResInfo.num = eachDrop.num
                        drawResInfo.protoId = eachDrop.id

                        rewardList += ResVo(RES_PROPS, eachDrop.id.toLong(), eachDrop.num.toLong())
                    }
                }

            } else {
                // 普通道具
                drawResInfo.type = 2
                drawResInfo.num = eachDrop.num
                drawResInfo.protoId = eachDrop.id

                rewardList += ResVo(RES_PROPS, eachDrop.id.toLong(), eachDrop.num.toLong())

            }
            drawResInfoList.add(drawResInfo.build())
        }

        rt.addAllRewards(drawResInfoList)
        resHelper.addRes(session, ACTION_EXTRACT_CHEST, homePlayerDC.player, rewardList)

        if (heroList.size != 0) {
            sendHeroListInfo(session, heroList)
        }

        fireEvent(session, LotteryEvent(lottery.protoId, drawTimes))

        return rt.build()
    }

    // 抽卡动作函数
    fun drawCard(proto: DrawHeroProto, lotteryData: Lottery): Drop? {
        lotteryData.drawSum++

        // 触发保底
        if (lotteryData.drawSum % proto.drawProtect2 == 0L) {
            // 隐形保底
            val dropPropsProtoId = proto.selectThemDraw2() ?: return null
            val dropProto = pcs.dropPropsProtoCache.dropPropsMap[dropPropsProtoId] ?: return null
            val drop = randomSelect(dropProto.dropMap)
            return drop

        } else if (lotteryData.drawSum % proto.drawProtect1 == 0L) {
            // 显性保底
            val dropPropsProtoId = proto.selectThemDraw1() ?: return null
            val dropProto = pcs.dropPropsProtoCache.dropPropsMap[dropPropsProtoId] ?: return null
            val drop = randomSelect(dropProto.dropMap)
            return drop

        } else {
            // 基本池
            val dropPropsProtoId = proto.selectbasicDraw() ?: return null
            val dropProto = pcs.dropPropsProtoCache.dropPropsMap[dropPropsProtoId] ?: return null
            val drop = randomSelect(dropProto.dropMap)
            return drop
        }
    }
}