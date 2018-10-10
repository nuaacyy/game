package com.point18.slg2d.home.module.bank

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.forwardHeartDeal2World
import com.point18.slg2d.home.dc.BankDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.CancelBankRt
import java.util.*
import java.util.Arrays.asList

// 取消银行投资
class CancelBankDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus2<HomePlayerDC, BankDC>(
    HomePlayerDC::class.java, BankDC::class.java,
    asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, bankDC: BankDC ->
            val rt = cancelBank(session, homePlayerDC, bankDC)
            session.sendMsg(MsgType.CancelBank_39, rt)
        }
    }

    private fun cancelBank(
        session: PlayerActor,
        homePlayerDC: HomePlayerDC,
        bankDC: BankDC
    ): CancelBankRt {
        val rt = CancelBankRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = homePlayerDC.player

        val bank = bankDC.bank

        if (bank == null) {
            rt.rt = ResultCode.BANK_NO_HAVE_ERROR.code
            return rt.build()
        }

        // 是否满足条件
        val nowTime = getNowTime()
        if (bank.overTime < nowTime) {
            rt.rt = ResultCode.BANK_TIME_OVER_ERROR.code
            return rt.build()
        }

        // 收获资源
        // 先把之前投资的钱原样还给玩家
        val addRes = LinkedList<ResVo>()
        if (bank.useMoney != 0L) {
            addRes += ResVo(RES_GOLD, NOT_PROPS_SUB_TYPE, bank.useMoney)
        }

        if (bank.useBindMoney != 0L) {
            addRes += ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, bank.useBindMoney)
        }

        resHelper.addRes(session, ACTION_CANCEL_BANK, player, addRes)

        // 删除数据
        bankDC.delBankInfo()

        //删除心跳
        forwardHeartDeal2World(session, DeleteHeart, Bank, bank.playerId, bank.overTime)

        return rt.build()
    }

}
