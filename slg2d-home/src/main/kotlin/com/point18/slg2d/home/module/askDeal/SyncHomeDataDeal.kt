package com.point18.slg2d.home.module.askDeal

import akka.actor.ActorRef
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.HomeHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.HomeSyncDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.syncDeal.*
import pb4server.SyncHomeAskRt
import pb4server.World2HomeAskReq
import pb4server.World2HomeAskResp
import java.util.Arrays.asList

interface SyncMsgDeal {
    fun dealSync(session: PlayerActor, data: String): Int
}

class SyncMsgDealWrap(private val syncMsgDeal: SyncMsgDeal) : HomeHelperPlus1<HomeSyncDC>(HomeSyncDC::class.java,
    asList(syncMsgDeal as HomeHelper)) {

    /**
     * 外部的处理
     */
    fun dealSyncOutSide(
        sender: ActorRef,
        session: PlayerActor,
        askMsg: World2HomeAskReq,
        resp: World2HomeAskResp.Builder
    ) {
        requireDc(session) {
            session.clientMsgNo = askMsg.clientMsgNo

            val rt = SyncHomeAskRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code

            val msg = askMsg.syncHomeAskReq
            rt.rt = syncMsgDeal.dealSync(session, msg.data)
            resp.setSyncHomeAskRt(rt)

            // 返回响应
            sender.tell(resp.build(), session.self)
        }
    }
}

class SyncHomeDataDeal : W2HAsk, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java
) {

    private val syncDealsAtHome: MutableMap<SyncData, SyncMsgDealWrap> = mutableMapOf()

    /**
     * 注册同步处理
     */
    init {
        syncDealsAtHome[MaxLvInPrison] = SyncMsgDealWrap(SyncMaxLvInPrisonDeal())
        syncDealsAtHome[Barracks] = SyncMsgDealWrap(SyncBarracksDeal())
        syncDealsAtHome[Effects] = SyncMsgDealWrap(SyncEffectsDeal())
        syncDealsAtHome[CountryBuffs] = SyncMsgDealWrap(SyncCountryBuffDeal())
        syncDealsAtHome[Buffs] = SyncMsgDealWrap(SyncBuffChangeDeal())
        syncDealsAtHome[JjcRankSync] = SyncMsgDealWrap(SyncJjcRankDeal())
        syncDealsAtHome[JjcMaxRankSync] = SyncMsgDealWrap(SyncMaxJjcRankDeal())
        syncDealsAtHome[JjcScoreSync] = SyncMsgDealWrap(SyncJjcSoreDeal())
        syncDealsAtHome[JjcScoreTimeSync] = SyncMsgDealWrap(SyncJjcSoreTimeDeal())
        syncDealsAtHome[JjcRewardSync] = SyncMsgDealWrap(SyncJjcRewardDeal())
        syncDealsAtHome[CastleStateSync] = SyncMsgDealWrap(SyncCastleStateDeal())
        syncDealsAtHome[CastleStateEndTimeSync] = SyncMsgDealWrap(SyncCastleStateEndTime())
        syncDealsAtHome[DecreeSync] = SyncMsgDealWrap(SyncDecreeDeal())
        syncDealsAtHome[OfficeSync] = SyncMsgDealWrap(SyncOfficeDeal())
        syncDealsAtHome[MainHeroSync] = SyncMsgDealWrap(SyncMainHeroDeal())
        syncDealsAtHome[InstanceFloorSync] = SyncMsgDealWrap(SyncInstanceFloorDeal())
        syncDealsAtHome[TargetSync] = SyncMsgDealWrap(SyncTargetsDeal())
        syncDealsAtHome[PlayerSettingSync] = SyncMsgDealWrap(SyncPlayerSettingDeal())

        for ((_, syncDeals) in syncDealsAtHome) {
            syncDeals.initHelper()
        }
    }

    fun findSpecSyncDeal(syncData: SyncData): SyncMsgDealWrap? {
        return syncDealsAtHome[syncData]
    }

    override fun dealWorldAsk(session: PlayerActor, req: World2HomeAskReq, resp: World2HomeAskResp.Builder) {
        // 下面的方法不要删除，这个方法在这里不需要实现，因为不可能被调用到，如果被调用了，就是有问题。
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}