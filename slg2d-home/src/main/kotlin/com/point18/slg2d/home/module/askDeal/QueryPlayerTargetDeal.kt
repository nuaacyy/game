package com.point18.slg2d.home.module.askDeal

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomeSyncDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import pb4client.KingInfo
import pb4server.Home2HomeAskReq
import pb4server.Home2HomeAskResp
import pb4server.QueryPlayerTargetAskRt

class QueryPlayerTargetDeal : H2HAsk, HomeHelperPlus1<HomeSyncDC>(HomeSyncDC::class.java) {

    override fun dealHomeAsk(session: PlayerActor, req: Home2HomeAskReq, resp: Home2HomeAskResp.Builder) {
        val rtBuilder = QueryPlayerTargetAskRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        prepare(session) { homeSyncDC: HomeSyncDC ->
            val kingInfoMap = getKingInfo(homeSyncDC, session)
            for ((k, v) in kingInfoMap) {
                val kingInfoBuilder = KingInfo.newBuilder()
                kingInfoBuilder.infoType = k
                kingInfoBuilder.value = v
                rtBuilder.addKingInfos(kingInfoBuilder)
            }
            resp.setQueryPlayerTargetAskRt(rtBuilder)
        }
    }

    private fun getKingInfo(homeSyncDC: HomeSyncDC, session: PlayerActor): HashMap<Int, Long> {
        val kingInfoMap = hashMapOf<Int, Long>()
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
        return kingInfoMap
    }
}