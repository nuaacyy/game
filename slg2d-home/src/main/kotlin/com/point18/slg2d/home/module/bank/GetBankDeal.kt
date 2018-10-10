package com.point18.slg2d.home.module.bank

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.BankDC
import com.point18.slg2d.home.dc.HomeMyTargetDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.GetBankRewardEvent
import com.point18.slg2d.home.module.fireEvent
import pb4client.GetBankRt
import java.util.*
import java.util.Arrays.asList

// 收获银行投资
class GetBankDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus3<HomePlayerDC, BankDC, HomeMyTargetDC>(
    HomePlayerDC::class.java, BankDC::class.java, HomeMyTargetDC::class.java,
    asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, bankDC: BankDC, homeMyTargetDC: HomeMyTargetDC ->
            val rt = getBank(session, homePlayerDC, bankDC, homeMyTargetDC)
            session.sendMsg(MsgType.GetBank_38, rt)
        }
    }

    private fun getBank(
        session: PlayerActor, homePlayerDC: HomePlayerDC, bankDC: BankDC, homeMyTargetDC: HomeMyTargetDC
    ): GetBankRt {
        val rt = GetBankRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val myBank = bankDC.bank

        if (myBank == null) {
            rt.rt = ResultCode.BANK_NO_HAVE_ERROR.code
            return rt.build()
        }

        // 是否满足条件
        val nowTime = getNowTime()
        if (myBank.overTime >= nowTime) {
            rt.rt = ResultCode.BANK_NO_TIME_OVER_ERROR.code
            return rt.build()
        }

        val player = homePlayerDC.player

        // 收获资源
        // 先把之前投资的钱原样还给玩家
        val addRes = LinkedList<ResVo>()
        if (myBank.useMoney != 0L) {
            addRes += ResVo(RES_GOLD, NOT_PROPS_SUB_TYPE, myBank.useMoney)
        }

        if (myBank.useBindMoney != 0L) {
            addRes += ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, myBank.useBindMoney)
        }

        // 计算利率
        val rateMoney = ((myBank.useMoney + myBank.useBindMoney).toDouble() * (myBank.rate.toDouble() / 10000)).toLong()

        // 加上利息
        addRes += ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, rateMoney)

        resHelper.addRes(session, ACTION_GET_BANK, player, addRes)

        // 删除数据
        bankDC.delBankInfo()

        // 银行次数检测
        var bankNum = homeMyTargetDC.targetInfo.bankNum.getOrPut(BANK_DIAMOND) { 0 }
        bankNum++
        homeMyTargetDC.targetInfo.bankNum[BANK_DIAMOND] = bankNum
        var anyNum = homeMyTargetDC.targetInfo.bankNum.getOrPut(BANK_ANY) { 0 }
        anyNum++
        homeMyTargetDC.targetInfo.bankNum[BANK_ANY] = anyNum

        // 银行收益检测
        var bankMoney = homeMyTargetDC.targetInfo.bankMoney.getOrPut(BANK_DIAMOND) { 0 }
        bankMoney += ((myBank.useMoney + myBank.useBindMoney).toDouble() * (myBank.rate.toDouble() / 10000)).toInt()
        homeMyTargetDC.targetInfo.bankMoney[BANK_DIAMOND] = bankMoney

        fireEvent(session, GetBankRewardEvent())

        return rt.build()
    }

}
