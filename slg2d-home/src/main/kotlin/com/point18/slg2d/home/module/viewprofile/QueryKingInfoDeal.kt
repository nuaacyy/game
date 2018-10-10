package com.point18.slg2d.home.module.viewprofile

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.RefreshResourceHelper
import com.point18.slg2d.home.common.TargetHelper
import com.point18.slg2d.home.dc.HomeSyncDC
import com.point18.slg2d.home.dc.ResourceYieldDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.KingInfo
import pb4client.QueryKingInfo
import pb4client.QueryKingInfoRt
import pb4server.Home2HomeAskResp
import pb4server.QueryPlayerTargetAskReq
import java.util.Arrays.asList

class QueryKingInfoDeal(
    private val targetHelper: TargetHelper = TargetHelper(),
    private val refreshResourceHelper: RefreshResourceHelper = RefreshResourceHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus2<HomeSyncDC, ResourceYieldDC>(
        HomeSyncDC::class.java, ResourceYieldDC::class.java,
        asList(targetHelper, refreshResourceHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        msg as QueryKingInfo
        queryKingInfo(session, msg.playerId)
    }

    private fun queryKingInfo(session: PlayerActor, targetPlayerId: Long) {
        prepare(session) { homeSyncDC: HomeSyncDC, resourceYieldDC: ResourceYieldDC ->
            val rtBuilder = QueryKingInfoRt.newBuilder()
            rtBuilder.rt = ResultCode.SUCCESS.code
            rtBuilder.playerId = targetPlayerId

            val playerId = session.playerId
            if (playerId != targetPlayerId) {
                //向home发送请求
                val queryAskInfoBuilder = QueryPlayerTargetAskReq.newBuilder()
                session.createACS<Home2HomeAskResp>().ask(session.homeShardProxy, session.fillHome2HomeAskMsgHeader {
                    it.setQueryPlayerTargetAskReq(queryAskInfoBuilder)
                    it.playerId = targetPlayerId
                }, Home2HomeAskResp::class.java).whenCompleteKt { askResp, askErr ->
                    try {

                        if (askErr != null) {
                            rtBuilder.rt = ResultCode.ASK_ERROR1.code
                            session.sendMsg(MsgType.QueryKingInfo_1216, rtBuilder.build())
                            return@whenCompleteKt
                        }
                        if (askResp == null) {
                            rtBuilder.rt = ResultCode.ASK_ERROR2.code
                            session.sendMsg(MsgType.QueryKingInfo_1216, rtBuilder.build())
                            return@whenCompleteKt
                        }
                        val rt = askResp.queryPlayerTargetAskRt
                        rtBuilder.rt = rt.rt
                        if (rt.rt == ResultCode.SUCCESS.code) {
                            rt.kingInfosList.forEach {
                                rtBuilder.addKingInfos(it)
                            }
                        }
                        session.sendMsg(MsgType.QueryKingInfo_1216, rtBuilder.build())
                    } catch (e: Exception) {
                        normalLog.error("QueryKingInfoRt Error!", e)
                        rtBuilder.rt = ResultCode.ASK_ERROR3.code
                        session.sendMsg(MsgType.QueryKingInfo_1216, rtBuilder.build())
                        return@whenCompleteKt
                    }
                }
                return@prepare
            }

            val kingInfoMap = getKingInfo(homeSyncDC, resourceYieldDC, session)
            for ((k, v) in kingInfoMap) {
                val kingInfoBuilder = KingInfo.newBuilder()
                kingInfoBuilder.infoType = k
                kingInfoBuilder.value = v
                rtBuilder.addKingInfos(kingInfoBuilder)
            }
            session.sendMsg(MsgType.QueryKingInfo_1216, rtBuilder.build())
        }
    }

    private fun getKingInfo(
        homeSyncDC: HomeSyncDC,
        resourceYieldDC: ResourceYieldDC,
        session: PlayerActor
    ): HashMap<Int, Long> {
        val kingInfoMap = hashMapOf<Int, Long>()
        val resYield = resourceYieldDC.resourceYield
        val refreshResRt = refreshResourceHelper.refreshPlayerResource(session, getNowTime())
        val targetMap = homeSyncDC.syncData.targetMap
        kingInfoMap[WinRecord] = targetMap.getOrDefault(WinRecord, 0)
        kingInfoMap[FailRecord] = targetMap.getOrDefault(FailRecord, 0)
        kingInfoMap[AttackWinRecord] = targetMap.getOrDefault(AttackWinRecord, 0)
        kingInfoMap[DefendWinRecord] = targetMap.getOrDefault(DefendWinRecord, 0)
        kingInfoMap[WeakenStrength] = targetMap.getOrDefault(WeakenStrength, 0)
        kingInfoMap[AttackFailRecord] = targetMap.getOrDefault(AttackFailRecord, 0)
        kingInfoMap[DefendFailRecord] = targetMap.getOrDefault(DefendFailRecord, 0)
        var loseNum = kingInfoMap[FailRecord] ?: 1
        if (loseNum <= 0) {
            loseNum = 1
        }
        kingInfoMap[WinRate] = targetMap.getOrDefault(WinRecord, 0) * 10000 / loseNum
        kingInfoMap[KillSoliderNum] = targetMap.getOrDefault(KillSoliderNum, 0)
        kingInfoMap[KillTrapNum] = targetMap.getOrDefault(KillTrapNum, 0)
        kingInfoMap[LoseSoliderNum] = targetMap.getOrDefault(LoseSoliderNum, 0)
        kingInfoMap[LoseTrapNum] = targetMap.getOrDefault(LoseTrapNum, 0)
        kingInfoMap[TotalCureNum] = targetMap.getOrDefault(TotalCureNum, 0)
        kingInfoMap[TotalDamageNum] = targetMap.getOrDefault(TotalDamageNum, 0)
        kingInfoMap[CatchKingNum] = targetMap.getOrDefault(CatchKingNum, 0)
        kingInfoMap[KillKingNum] = targetMap.getOrDefault(KillKingNum, 0)
        kingInfoMap[SoliderStrength] = targetMap.getOrDefault(SoliderStrength, 0)
        kingInfoMap[ResearchStrength] = targetHelper.getResearchPower(session)
        kingInfoMap[TrapStrength] = targetMap.getOrDefault(TrapStrength, 0)
        kingInfoMap[BuildStrength] = targetHelper.getBuildingPower(session)
        kingInfoMap[MissionStrength] = targetHelper.getMissionPower(session)
        kingInfoMap[KingStrength] = targetHelper.getKingPower(session)
        kingInfoMap[HeroStrength] = targetHelper.getHeroPower(session)
        kingInfoMap[NowJJcRank] = targetMap.getOrDefault(NowJJcRank, 0)
        kingInfoMap[MaxJJcRank] = targetMap.getOrDefault(MaxJJcRank, 0)
        kingInfoMap[JjcWinRecord] = targetMap.getOrDefault(JjcWinRecord, 0)
        kingInfoMap[CatchKingEscapeNum] = targetMap.getOrDefault(CatchKingEscapeNum, 0)
        kingInfoMap[KingEscapeNum] = targetMap.getOrDefault(KingEscapeNum, 0)
        kingInfoMap[KingBeCatchNum] = targetMap.getOrDefault(KingBeCatchNum, 0)
        kingInfoMap[KingBeKillNum] = targetMap.getOrDefault(KingBeKillNum, 0)
        kingInfoMap[GetKingRewardNum] = targetMap.getOrDefault(GetKingRewardNum, 0)
        kingInfoMap[MonsterScore] = targetMap.getOrDefault(MonsterScore, 0)
        kingInfoMap[TransportFoodNum] = targetMap.getOrDefault(TransportFoodNum, 0)
        kingInfoMap[TransportStoneNum] = targetMap.getOrDefault(TransportStoneNum, 0)
        kingInfoMap[TransportWoodNum] = targetMap.getOrDefault(TransportWoodNum, 0)
        kingInfoMap[TransportIronNum] = targetMap.getOrDefault(TransportIronNum, 0)
        kingInfoMap[TransportCoinNum] = targetMap.getOrDefault(TransportCoinNum, 0)
        kingInfoMap[TotalHelpAlly] = targetMap.getOrDefault(TotalHelpAlly, 0)
        kingInfoMap[TotalFarmNum] = targetMap.getOrDefault(TotalFarmNum, 0)
        kingInfoMap[FoodNowNum] = refreshResRt.food
        kingInfoMap[FoodStorageLimit] = resYield.foodLimit
        kingInfoMap[FoodYieldNum] = resYield.totalFood.toLong()
        kingInfoMap[WoodNowNum] = refreshResRt.wood
        kingInfoMap[WoodStorageLimit] = resYield.woodLimit
        kingInfoMap[WoodYieldNum] = resYield.totalWood.toLong()
        kingInfoMap[StoneNowNum] = refreshResRt.stone
        kingInfoMap[StoneStorageLimit] = resYield.stoneLimit
        kingInfoMap[StoneYieldNum] = resYield.totalStone.toLong()
        kingInfoMap[IronNowNum] = refreshResRt.iron
        kingInfoMap[IronStorageLimit] = resYield.ironLimit
        kingInfoMap[IronYieldNum] = resYield.totalIron.toLong()
        kingInfoMap[CoinNowNum] = refreshResRt.coin
        kingInfoMap[CoinStorageLimit] = resYield.coinLimit
        kingInfoMap[CoinYieldNum] = resYield.totalCoin.toLong()
        kingInfoMap[BubingNum] = targetMap.getOrDefault(BubingNum, 0)
        kingInfoMap[Bubing1Num] = targetMap.getOrDefault(Bubing1Num, 0)
        kingInfoMap[Bubing2Num] = targetMap.getOrDefault(Bubing2Num, 0)
        kingInfoMap[Bubing3Num] = targetMap.getOrDefault(Bubing3Num, 0)
        kingInfoMap[GongbingNum] = targetMap.getOrDefault(GongbingNum, 0)
        kingInfoMap[Gongbing1Num] = targetMap.getOrDefault(Gongbing1Num, 0)
        kingInfoMap[Gongbing2Num] = targetMap.getOrDefault(Gongbing2Num, 0)
        kingInfoMap[Gongbing3Num] = targetMap.getOrDefault(Gongbing3Num, 0)
        kingInfoMap[QibingNum] = targetMap.getOrDefault(QibingNum, 0)
        kingInfoMap[Qibing1Num] = targetMap.getOrDefault(Qibing1Num, 0)
        kingInfoMap[Qibing2Num] = targetMap.getOrDefault(Qibing2Num, 0)
        kingInfoMap[Qibing3Num] = targetMap.getOrDefault(Qibing3Num, 0)
        kingInfoMap[ChebingNum] = targetMap.getOrDefault(ChebingNum, 0)
        kingInfoMap[Chebing1Num] = targetMap.getOrDefault(Chebing1Num, 0)
        kingInfoMap[Chebing2Num] = targetMap.getOrDefault(Chebing2Num, 0)
        kingInfoMap[Chebing3Num] = targetMap.getOrDefault(Chebing3Num, 0)
        return kingInfoMap
    }
}

