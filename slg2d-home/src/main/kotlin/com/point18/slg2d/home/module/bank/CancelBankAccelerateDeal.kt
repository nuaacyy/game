package com.point18.slg2d.home.module.bank

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.ACTION_CANCEL_BANK
import com.point18.slg2d.common.constg.BankAccelerate
import com.point18.slg2d.common.constg.DeleteHeart
import com.point18.slg2d.common.constg.RES_PROPS
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.forwardHeartDeal2World
import com.point18.slg2d.home.dc.BankAccelerateDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.CancleBankAccelerateRt
import java.util.*
import java.util.Arrays.asList

class CancelBankAccelerateDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus2<HomePlayerDC, BankAccelerateDC>(
    HomePlayerDC::class.java, BankAccelerateDC::class.java,
    asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, bankAccelerateDC: BankAccelerateDC ->
            val rt = cancelBankAccelerate(session, homePlayerDC, bankAccelerateDC)
            session.sendMsg(MsgType.CancelBankAccelerate_1562, rt)
        }
    }

    private fun cancelBankAccelerate(
        session: PlayerActor, homePlayerDC: HomePlayerDC, bankAccelerateDC: BankAccelerateDC
    ): CancleBankAccelerateRt {
        val rt = CancleBankAccelerateRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = homePlayerDC.player
        val myBankAccelerate = bankAccelerateDC.bankAccelerate
        if (myBankAccelerate == null) {
            rt.rt = ResultCode.BANK_NO_HAVE_ERROR.code
            return rt.build()
        }
        val nowTime = getNowTime()
        if (myBankAccelerate.overTime < nowTime) {
            rt.rt = ResultCode.BANK_TIME_OVER_ERROR.code
            return rt.build()
        }

        // 收获资源
        // 先把之前投资的钱原样还给玩家
        val addRes = LinkedList<ResVo>()
        if ((myBankAccelerate.useProps).size != 0) {
            for (v in myBankAccelerate.useProps) {
                addRes += ResVo(RES_PROPS, v.key.toLong(), v.value.toLong())
            }
        }

        resHelper.addRes(session, ACTION_CANCEL_BANK, player, addRes)

        // 删除数据
        bankAccelerateDC.delBankQuickInfo()

        //删除心跳
        forwardHeartDeal2World(
            session,
            DeleteHeart,
            BankAccelerate,
            myBankAccelerate.playerId,
            myBankAccelerate.overTime
        )

        return rt.build()
    }
}


