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
import com.point18.slg2d.home.common.forwardHeartDeal2World
import com.point18.slg2d.home.dc.BankAccelerateDC
import com.point18.slg2d.home.dc.HomeMyTargetDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.helper.HomeHelperPlus4
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.GetBankRewardEvent
import com.point18.slg2d.home.module.fireEvent
import pb4client.BankAccelerate
import pb4client.BankAccelerateRt
import pb4client.propVo
import java.util.*
import java.util.Arrays.asList

// 银行加速投资
class BankAccelerateDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus4<HomePlayerDC, BankAccelerateDC, InnerCityDC, HomeMyTargetDC>(
    HomePlayerDC::class.java, BankAccelerateDC::class.java,
    InnerCityDC::class.java, HomeMyTargetDC::class.java,
    asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, bankAccelerateDC: BankAccelerateDC,
                             innerCityDC: InnerCityDC, homeMyTargetDC: HomeMyTargetDC ->
            val usePlan = (msg as BankAccelerate).userPlan
            val value = LinkedList(msg.usePropsList)

            val rt = bankAccelerate(
                session, usePlan, value,
                homePlayerDC, innerCityDC, bankAccelerateDC, homeMyTargetDC
            )
            session.sendMsg(MsgType.BankAccelerate_1561, rt)
        }
    }

    private fun bankAccelerate(
        session: PlayerActor, usePlan: Int, value: LinkedList<propVo>,
        homePlayerDC: HomePlayerDC, innerCityDC: InnerCityDC,
        bankAccelerateDC: BankAccelerateDC, homeMyTargetDC: HomeMyTargetDC
    ): BankAccelerateRt {
        val rt = BankAccelerateRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        //客户端传进来的道具
        if (value.size == 0) {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }

        //银行加速模板
        val bankAccelerateProto = pcs.bankAccelerateProtoCache.bankAccelerateProtoMap[usePlan]
        if (bankAccelerateProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        val player = homePlayerDC.player

        val innerCityList = innerCityDC.findEffectiveInnerBuildingsByType((bankAccelerateProto.buildId).toInt())
        var maxLv = 0
        for (innerCity in innerCityList) {
            if (innerCity.lv > maxLv) {
                maxLv = innerCity.lv
            }
        }

        //等级未解锁
        if (maxLv < bankAccelerateProto.buildLv) {
            rt.rt = ResultCode.BANK_BUILD_LV_ERROR.code
            return rt.build()
        }

        // 是否已有加速任务
        if (bankAccelerateDC.bankAccelerate != null) {
            rt.rt = ResultCode.BANK_HAVE_ERROR.code
            return rt.build()
        }

        val costs = LinkedList<ResVo>()
        var useTime = 0L //道具的总时间
        val costData: HashMap<Int, Int> = hashMapOf()

        for (v in value) {
            val proProp = pcs.equipCache.equipProtoMap[v.protoId]
            if (proProp == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt.build()
            }

            costs += ResVo(RES_PROPS, v.protoId.toLong(), v.num.toLong())
            useTime += (proProp.extend1).toLong() * v.num
            costData[v.protoId] = v.num
        }

        // 验证资源
        val checkCost = resHelper.checkRes(session, costs)
        if (!checkCost) {
            rt.rt = ResultCode.LESS_RESOUCE.code
            return rt.build()
        }

        if (useTime < bankAccelerateProto.accelerate) {
            rt.rt = ResultCode.BANK_ACCELERATE_ERROR.code
            return rt.build()
        }

        resHelper.costRes(session, com.point18.slg2d.common.constg.ACTION_BANK, player, costs)

        // 注册投资
        val nowTime = getNowTime()

        val bankAccelerate = bankAccelerateDC.createBankQuick(
            nowTime + bankAccelerateProto.time * 1000,
            usePlan,
            useTime,
            costData,
            bankAccelerateProto.propsMap
        )

        // 任务检测

        // 银行次数检测
        var beforeBankNum = homeMyTargetDC.targetInfo.beforeBankNum.getOrPut(BANK_ACCELERATE) { 0 }
        beforeBankNum++
        homeMyTargetDC.targetInfo.beforeBankNum[BANK_ACCELERATE] = beforeBankNum
        var anyNum = homeMyTargetDC.targetInfo.beforeBankNum.getOrPut(BANK_ANY) { 0 }
        anyNum++
        homeMyTargetDC.targetInfo.beforeBankNum[BANK_ANY] = anyNum

        fireEvent(session, GetBankRewardEvent())

        //创建心跳
        forwardHeartDeal2World(session, CreateHeart, BankAccelerate, bankAccelerate.playerId, bankAccelerate.overTime)

        return rt.build()
    }
}
