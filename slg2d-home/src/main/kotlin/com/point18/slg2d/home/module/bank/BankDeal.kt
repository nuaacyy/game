package com.point18.slg2d.home.module.bank

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.common.forwardHeartDeal2World
import com.point18.slg2d.home.dc.BankDC
import com.point18.slg2d.home.dc.HomeMyTargetDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.GetBankRewardEvent
import com.point18.slg2d.home.module.fireEvent
import pb4client.Bank
import pb4client.BankRt
import java.util.*
import java.util.Arrays.asList

// 银行投资
class BankDeal(
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper(),
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus3<HomePlayerDC, BankDC, HomeMyTargetDC>(
    HomePlayerDC::class.java, BankDC::class.java, HomeMyTargetDC::class.java,
    asList(effectHelper, resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val usePlan = (msg as Bank).userPlan
        val useMoney = msg.useMoney
        prepare(session) { homePlayerDC: HomePlayerDC, bankDC: BankDC, homeMyTargetDC: HomeMyTargetDC ->
            val rt = bank(
                session, usePlan, useMoney,
                homePlayerDC, bankDC, homeMyTargetDC
            )
            session.sendMsg(MsgType.Bank_37, rt)
        }
    }

    private fun bank(
        session: PlayerActor, usePlan: Int, useMoney: Long,
        homePlayerDC: HomePlayerDC, bankDC: BankDC, homeMyTargetDC: HomeMyTargetDC
    ): BankRt {

        val rt = BankRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.userPlan = usePlan
        rt.useMoney = useMoney

        val bankProto = pcs.bankProtoCache.bankProtoMap[usePlan]
        if (bankProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        val player = homePlayerDC.player

        val banMax = effectHelper.getResearchEffectValue(session, NO_FILTER, CangbaodigongInvestmentAdd)

        if (useMoney < bankProto.minMoney || useMoney > banMax) {
            rt.rt = ResultCode.BANK_MONEY_ERROR.code
            return rt.build()
        }

        if (bankDC.bank != null) {
            rt.rt = ResultCode.BANK_HAVE_ERROR.code
            return rt.build()
        }

        // 验证资源, 只要钻石够就可以买,不管真钻绑钻,但是要记下来真/绑具体数字
        val costs = ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, useMoney)
        val checkCost = resHelper.checkRes(session, costs)
        if (!checkCost) {
            rt.rt = ResultCode.LESS_RESOUCE.code
            return rt.build()
        }

        // 验证通过之后不要马上扣除,计算玩家本次投资的金额,优先扣除真钻
        val reallyCosts = LinkedList<ResVo>()
        val useGold: Long
        var useBindGold = 0L
        if (player.gold >= useMoney) {
            // 真钻充足 全部都消耗真钻
            reallyCosts += ResVo(RES_GOLD, NOT_PROPS_SUB_TYPE, useMoney)
            useGold = useMoney
        } else {
            reallyCosts += ResVo(RES_GOLD, NOT_PROPS_SUB_TYPE, player.gold)
            reallyCosts += ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, useMoney - player.gold)
            useGold = player.gold
            useBindGold = useMoney - player.gold
        }

        resHelper.costRes(session, ACTION_BANK, player, reallyCosts)

        // 注册投资
        val nowTime = getNowTime()

        val reallyRate = bankProto.basicRate + effectHelper.getResearchEffectValue(
            session,
            NO_FILTER,
            CangbaodigongLilvAdd
        )
        val bank = bankDC.createBank(
            nowTime + bankProto.time * 1000,
            usePlan,
            useGold,
            useBindGold,
            reallyRate
        )

        var beforeBankNum = homeMyTargetDC.targetInfo.beforeBankNum.getOrPut(BANK_DIAMOND) { 0 }
        beforeBankNum++
        homeMyTargetDC.targetInfo.beforeBankNum[BANK_DIAMOND] = beforeBankNum
        var anyNum = homeMyTargetDC.targetInfo.beforeBankNum.getOrPut(BANK_ANY) { 0 }
        anyNum++
        homeMyTargetDC.targetInfo.beforeBankNum[BANK_ANY] = anyNum

        fireEvent(session, GetBankRewardEvent())

        //创建心跳
        forwardHeartDeal2World(session, CreateHeart, com.point18.slg2d.common.constg.Bank, bank.playerId, bank.overTime)

        return rt.build()
    }
}
