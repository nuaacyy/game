package com.point18.slg2d.home.module.casino

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.*
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.common.syncdomain.TargetData
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.CasinoDC
import com.point18.slg2d.home.dc.HomeMyTargetDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.GetCasinoBossNumEvent
import com.point18.slg2d.home.module.event.GetCasinoKillBossNumEvent
import com.point18.slg2d.home.module.fireEvent
import pb4client.CasinosLottery
import pb4client.CasinosLotteryRt
import pb4server.DrawCasinoAskReq
import pb4server.Home2PublicAskResp
import pb4server.Home2WorldAskResp
import pb4server.UpdateInfoByHomeAskReq
import java.util.*
import java.util.Arrays.asList

// 抽奖
class CasinosLotteryDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus3<CasinoDC, HomePlayerDC, HomeMyTargetDC>(
    CasinoDC::class.java, HomePlayerDC::class.java, HomeMyTargetDC::class.java,
    asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { casinoDC: CasinoDC, homePlayerDC: HomePlayerDC, homeMyTargetDC: HomeMyTargetDC ->
            val type = (msg as CasinosLottery).type
            val isBoss = msg.isBoss
            val isBless = msg.isBless
            val queryCasinoInfoRt = queryCasinoInfo(
                session, type, isBoss, isBless,
                casinoDC, homePlayerDC, homeMyTargetDC
            )
            if (queryCasinoInfoRt != null) {
                session.sendMsg(MsgType.CasinosLottery_1579, queryCasinoInfoRt)
            }
        }
    }

    private fun queryCasinoInfo(
        session: PlayerActor, type: Int, isBoss: Int, isBless: Int,
        casinoDC: CasinoDC, homePlayerDC: HomePlayerDC, homeMyTargetDC: HomeMyTargetDC
    ): CasinosLotteryRt? {
        val rtBuilder = CasinosLotteryRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code
        rtBuilder.isBoss = 0
        rtBuilder.blessType = 0
        rtBuilder.atkCount = 0
        rtBuilder.reward = ""
        rtBuilder.giftNum = 0

        val palaceId = casinoDC.findMyCasinoId()
        if (palaceId == null) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder.build()
        }
        // 查找玩家抽奖信息
        val casinoInfo = casinoDC.findAllCasino(type, palaceId)

        // 是否有奖池
        if (casinoInfo == null) {
            rtBuilder.rt = ResultCode.CASINO_TYPE_ERROR.code
            return rtBuilder.build()
        }

        if (casinoInfo.isAtkBoss != isBoss) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder.build()
        }

        if (casinoInfo.isBless != isBless) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder.build()
        }

        val casinoss = pcs.palaceProtoCache.palaceProtoMap[palaceId]
        if (casinoss == null) {
            rtBuilder.rt = ResultCode.CASINO_ERROR.code
            return rtBuilder.build()
        }
        val casinoProto = casinoss[type]
        if (casinoProto == null) {
            rtBuilder.rt = ResultCode.CASINO_ERROR.code
            return rtBuilder.build()
        }

        val player = homePlayerDC.player
        // 如果是祝福或者拥有免费次数，不消耗资源
        val freeCount = casinoInfo.freeCount
        if (freeCount <= 0 && isBless == 0) {
            // 消耗的资源
            val costRes = casinoProto.costMap
            val checkResPass = resHelper.checkRes(session, costRes)
            if (!checkResPass) {
                rtBuilder.rt = ResultCode.LESS_RESOUCE.code
                return rtBuilder.build()
            }
            // 扣除资源
            resHelper.costRes(session, ACTION_EXTRACT_CHEST, player, costRes)
        }
        // 减少免费次数
        if (freeCount > 0) {
            casinoInfo.freeCount--
        }

        var casinoNum = homeMyTargetDC.targetInfo.casinoNum.getOrPut(type) { 0 }
        casinoNum++
        homeMyTargetDC.targetInfo.casinoNum[type] = casinoNum

        var isBossReward = 0
        var bossTime = 0 // boss可攻击次数
        var isBossTime = 0 // 是否刷boss
        var isGift = 0 // 是否刷大奖

        // 当前攻击的是否是boss  isBoss: 1:是，0：不是
        if (isBoss == 1) {
            rtBuilder.isBoss = 1
            isBossReward = HIGH_BOSS_REWARD
            /////////////////////////最后一下是否刷大奖/////////////////////
            casinoInfo.atkBossCount++
            if (casinoInfo.atkBossCount >= casinoInfo.totalAtkBossCount) {

                var casinoKillBossNum = homeMyTargetDC.targetInfo.casinoKillBossNum.getOrPut(type) { 0 }
                casinoKillBossNum++
                homeMyTargetDC.targetInfo.casinoKillBossNum[type] = casinoKillBossNum

                fireEvent(session, GetCasinoKillBossNumEvent())
                // 大奖
                rtBuilder.isBoss = 0
                casinoInfo.atkBossCount = 0
                val rateGift = com.point18.slg2d.common.commonfunc.getRandInt(BASE)
                if (rateGift <= casinoProto.giftRate) {
                    isGift = 1
                }
            }
            rtBuilder.atkCount = casinoInfo.atkBossCount
        } else {
            isBossReward = NORMAL_BOSS_REWARD
            /////////////////最后一下是否刷boss/////////////////
            casinoInfo.atkCount++
            if (casinoInfo.atkCount >= pcs.basicProtoCache.palaceKill) {
                // 修改数据
                casinoInfo.atkCount = 0
                isBossTime = 1

            }
            rtBuilder.atkCount = casinoInfo.atkCount
        }

        ////////////////////////奖励////////////////////
        val dropList = LinkedList<Drop>() // 掉落
        val rewardList = LinkedList<ResVo>() // 奖励
        val drop = draw(casinoProto, isBossReward)
        if (drop != null) {
            dropList.add(drop)
        }
        for (eachDrop in dropList) {
            rewardList += ResVo(RES_PROPS, eachDrop.id.toLong(), eachDrop.num.toLong())
        }

        //////////////////是否是祝福//////////////////
        if (casinoInfo.blessCount > casinoInfo.useBlessCount) {
            casinoInfo.useBlessCount++
            if (casinoInfo.blessCount == casinoInfo.useBlessCount) {
                casinoInfo.isBless = 0
                casinoInfo.blessCount = 0
                casinoInfo.useBlessCount = 0
            } else {
                rtBuilder.blessType = type
                casinoInfo.isBless = 1
            }
        } else {
            val rateSurprise = com.point18.slg2d.common.commonfunc.getRandInt(BASE)
            if (rateSurprise <= casinoProto.surpriseRate) {
                val surpriseTimes = casinoProto.surpriseTimesDraw()
                if (surpriseTimes != null) {
                    casinoInfo.blessCount = surpriseTimes
                }
                casinoInfo.isBless = 1
                rtBuilder.blessType = type
            } else {
                casinoInfo.isBless = 0
            }
        }

        // 出boss
        if (isBossTime == 1) {
            val rateBoss = com.point18.slg2d.common.commonfunc.getRandInt(BASE)
            // 出boss
            if (rateBoss <= casinoProto.bossRate) {
                rtBuilder.isBoss = 1

                var casinoBossNum = homeMyTargetDC.targetInfo.casinoBossNum.getOrPut(type) { 0 }
                casinoBossNum++
                homeMyTargetDC.targetInfo.casinoBossNum[type] = casinoBossNum

                fireEvent(session, GetCasinoBossNumEvent())
                // boss可以攻击的次数
                val bossTimeTemp = casinoProto.bossTimeDraw()
                if (bossTimeTemp != null) {
                    bossTime = bossTimeTemp
                }
            }
            casinoInfo.totalAtkBossCount = bossTime
        }
        casinoInfo.isAtkBoss = rtBuilder.isBoss

        var monsterType = 0
        var casinoScoreType = 0
        if (type == NORMAL) {
            monsterType = ATK_NORMAL_MONSTER
            casinoScoreType = CasinoNormalMonsterScore
        } else {
            monsterType = ATK_HIGH_MONSTER
            casinoScoreType = CasinoHighMonsterScore
        }
        val values = LinkedList<Long>()
        val activityConditionProto = pcs.eventConditionProtoCache.protoMap[monsterType]
        if (activityConditionProto != null) {
            values.add((activityConditionProto.score).toLong())
        }
        val askMsg = UpdateInfoByHomeAskReq.newBuilder()
        val updateInfoByHomeVo = pb4server.UpdateInfoByHomeVo.newBuilder()
        updateInfoByHomeVo.updateType = UPDATE_INFO_BY_HOME_TARGET
        updateInfoByHomeVo.updateValue = toJson(TargetData(casinoScoreType, values))
        askMsg.addUpdates(updateInfoByHomeVo)

        session.createACS<Home2WorldAskResp>().ask(
            session.worldShardProxy,
            session.fillHome2WorldAskMsgHeader { it.setUpdateInfoByHomeAskReq(askMsg) },
            Home2WorldAskResp::class.java
        ).whenCompleteKt { askResp, askErr ->
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

            } catch (e: Exception) { }
        }

        // 出大奖
        if (isGift == 1) {
            var normalOrHigh = 0
            if (type == NORMAL) {
                normalOrHigh = pcs.basicProtoCache.palaceNormalPool
            } else {
                normalOrHigh = pcs.basicProtoCache.palaceSeniorPool
            }
            val askDrawCasinoMsg = DrawCasinoAskReq.newBuilder()
            askDrawCasinoMsg.rate = normalOrHigh
            askDrawCasinoMsg.areaNo = player.areaNo
            askDrawCasinoMsg.playerName = player.name
            askDrawCasinoMsg.allianceShortName = player.allianceNickName
            askDrawCasinoMsg.myPlayerId = player.playerId

            session.createACS<Home2PublicAskResp>()
                .ask(
                    session.publicShardProxy,
                    session.fillHome2PublicAskMsgHeader(CASINO_ID) { it.setDrawCasinoAskReq(askDrawCasinoMsg) },
                    Home2PublicAskResp::class.java
                )
                .whenCompleteKt { askResp, askErr ->
                    if (askErr != null || askResp == null) {
                        rtBuilder.rt = ResultCode.PROCESS_ERROR_RPC_EXCEPTION.code
                    } else if (askResp.drawCasinoAskRt.rt != ResultCode.SUCCESS.code) {
                        rtBuilder.rt = askResp.drawCasinoAskRt.rt
                    } else {
                        rtBuilder.rt = askResp.drawCasinoAskRt.rt
                        val totalMoney = askResp.drawCasinoAskRt.totalMoney
                        rtBuilder.giftNum = totalMoney

                        val list = LinkedList<Long>()
                        list.add(totalMoney)
                        rewardList += ResVo(RES_GOLD, NOT_PROPS_SUB_TYPE, totalMoney)
                        // 添加奖励
                        resHelper.addRes(session, ACTION_GET_FOG_CASINO, player, rewardList)
                    }
                    session.sendMsg(MsgType.CasinosLottery_1579, rtBuilder.build())
                }
        } else {
            // 添加奖励
            resHelper.addRes(session, ACTION_GET_FOG_CASINO, player, rewardList)
            // 奖励返回格式
            rtBuilder.reward = resVoToResString(rewardList)
            return rtBuilder.build()
        }
        return null
    }

    // 抽奖奖励：1：普通，2：boss
    private fun draw(proto: PalaceProto, type: Int): Drop? {
        if (type == NORMAL_BOSS_REWARD) {
            val dropPropsProtoId = proto.monsterRewardDraw() ?: return null
            val dropProto = pcs.dropPropsProtoCache.dropPropsMap[dropPropsProtoId] ?: return null
            val drop = randomSelect(dropProto.dropMap)
            return drop
        } else if (type == HIGH_BOSS_REWARD) {
            val dropPropsProtoId = proto.bossRewardDraw() ?: return null
            val dropProto = pcs.dropPropsProtoCache.dropPropsMap[dropPropsProtoId] ?: return null
            val drop = randomSelect(dropProto.dropMap)
            return drop
        }
        return null
    }
}