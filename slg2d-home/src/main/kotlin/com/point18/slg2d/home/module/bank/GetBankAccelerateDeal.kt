package com.point18.slg2d.home.module.bank

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.ACTION_GET_BANK
import com.point18.slg2d.common.constg.BANK_ACCELERATE
import com.point18.slg2d.common.constg.BANK_ANY
import com.point18.slg2d.common.constg.RES_PROPS
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.BankAccelerateDC
import com.point18.slg2d.home.dc.HomeMyTargetDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.GetBankRewardEvent
import com.point18.slg2d.home.module.fireEvent
import pb4client.GetBankAccelerateRt
import java.util.*
import java.util.Arrays.asList

class GetBankAccelerateDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus3<HomePlayerDC, BankAccelerateDC, HomeMyTargetDC>(
    HomePlayerDC::class.java, BankAccelerateDC::class.java, HomeMyTargetDC::class.java,
    asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, bankAccelerateDC: BankAccelerateDC, homeMyTargetDC: HomeMyTargetDC ->
            val rt = getBankAccelerate(session, homePlayerDC, bankAccelerateDC, homeMyTargetDC)
            session.sendMsg(MsgType.GetBankAccelerate_1563, rt)
        }
    }

    private fun getBankAccelerate(
        session: PlayerActor, homePlayerDC: HomePlayerDC, bankAccelerateDC: BankAccelerateDC, homeMyTargetDC: HomeMyTargetDC
    ): GetBankAccelerateRt {
        val rt = GetBankAccelerateRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = homePlayerDC.player
        val myBankAccelerate = bankAccelerateDC.bankAccelerate

        if (myBankAccelerate == null) {
            rt.rt = ResultCode.BANK_NO_HAVE_ERROR.code
            return rt.build()
        }

        // 是否满足条件
        val nowTime = getNowTime()
        if (myBankAccelerate.overTime >= nowTime) {
            rt.rt = ResultCode.BANK_NO_TIME_OVER_ERROR.code
            return rt.build()
        }

        // 收获资源
        val addRes = LinkedList<ResVo>()

        var totalMin = 0

        for (prop in myBankAccelerate.props) {
            addRes += ResVo(RES_PROPS, prop.key.toLong(), prop.value.toLong())
            val propsPro = pcs.equipCache.equipProtoMap[prop.key]
            if (propsPro != null) {
                totalMin += propsPro.extend1.toInt()
            }
        }

        resHelper.addRes(session, ACTION_GET_BANK, player, addRes)

        // 删除数据
        bankAccelerateDC.delBankQuickInfo()

        // 银行次数检测
        var bankNum = homeMyTargetDC.targetInfo.bankNum.getOrPut(BANK_ACCELERATE) { 0 }
        bankNum++
        homeMyTargetDC.targetInfo.bankNum[BANK_ACCELERATE] = bankNum
        var anyNum = homeMyTargetDC.targetInfo.bankNum.getOrPut(BANK_ANY) { 0 }
        anyNum++
        homeMyTargetDC.targetInfo.bankNum[BANK_ANY] = anyNum

        // 银行收益检测
        var bankMin = homeMyTargetDC.targetInfo.bankMoney.getOrPut(BANK_ACCELERATE) { 0 }
        bankMin += totalMin
        homeMyTargetDC.targetInfo.bankMoney[BANK_ACCELERATE] = bankMin

        fireEvent(session, GetBankRewardEvent())

        return rt.build()
    }
}