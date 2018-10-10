package com.point18.slg2d.home.module.equip

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ACTION_BUY_RESSHOP
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.dc.HeroDC
import com.point18.slg2d.home.dc.HomeMyTargetDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.NewEquipDC
import com.point18.slg2d.home.helper.HomeHelperPlus4
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.BuyAndUseProp
import pb4client.BuyAndUsePropRt
import java.util.*
import java.util.Arrays.asList

// 快捷购买道具并且生效,仅限购买1个,多个的话不可能走这个消息
class BuyAndUsePropDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper()
) : HomeClientMsgDeal, HomeHelperPlus4<HomePlayerDC, NewEquipDC, HeroDC, HomeMyTargetDC>(
    HomePlayerDC::class.java, NewEquipDC::class.java, HeroDC::class.java, HomeMyTargetDC::class.java,
    asList(resHelper, effectHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, newEquipDC: NewEquipDC, heroDC: HeroDC, homeMyTargetDC: HomeMyTargetDC ->
            val buyAndUseMsg = msg as BuyAndUseProp
            val propId = buyAndUseMsg.usePropId
            val extendVal = buyAndUseMsg.extendVal
            val buyAndUsePropRt = buyAndUseProp(
                session, propId.toInt(), extendVal, homePlayerDC, newEquipDC, heroDC, homeMyTargetDC
            )
            if (buyAndUsePropRt != null) {
                session.sendMsg(MsgType.BuyAndUseProp_1064, buyAndUsePropRt)
            }
        }
    }

    private fun buyAndUseProp(
        session: PlayerActor,
        propId: Int,
        extendVal: String,
        homePlayerDC: HomePlayerDC,
        newEquipDC: NewEquipDC,
        heroDC: HeroDC,
        homeMyTargetDC: HomeMyTargetDC
    ): BuyAndUsePropRt? {
        val rt = BuyAndUsePropRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.resString = ""

        val player = homePlayerDC.player

        val propProto = pcs.equipCache.equipProtoMap[propId]
        if (propProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }
        var haveNum = 0

        val propsVo = newEquipDC.findPropByProtoId(propId)
        if (propsVo != null) {
            haveNum = propsVo.haveNum
        }

        if (haveNum != 0) {
            // 玩家拥有这个道具不需要购买的
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        //查找对应道具的使用
        val useMap = equipM.usePropEff[propProto.mainType]
        if (useMap == null) {
            rt.rt = ResultCode.NO_FIND_USE_PROPS_ACTION.code
            return rt.build()
        }

        val use = useMap[propProto.subType]
        if (use == null) {
            rt.rt = ResultCode.NO_FIND_USE_PROPS_ACTION.code
            return rt.build()
        }

        val needRes = LinkedList(propProto.fastBuyMap)
        //校验需要的资源
        if (needRes.size < 1 || !resHelper.checkRes(session, needRes)) {
            rt.rt = ResultCode.LESS_RESOUCE.code
            return rt.build()
        }

        //扣除资源
        val action = ACTION_BUY_RESSHOP
        val costRes = resHelper.costResWithoutNotice(session, action, player, needRes)

        val helpers = Helpers(resHelper, effectHelper)
        val depDcs = UsePropsDepDcs(homePlayerDC, heroDC, homeMyTargetDC)
        val usePropReturn =
            use.useProp(depDcs, propId, session, 1, extendVal, helpers, needRes, costRes) { useRt, getResS ->
                val buyAndUsePropRt = BuyAndUsePropRt.newBuilder()
                buyAndUsePropRt.rt = useRt
                buyAndUsePropRt.resString = getResS
                session.sendMsg(MsgType.BuyAndUseProp_1064, buyAndUsePropRt.build())
            }

        if (usePropReturn == null) {
            return null
        }
        if (usePropReturn.rt != ResultCode.SUCCESS) {
            rt.rt = usePropReturn.rt.code
            resHelper.addResWithoutNotice(session, action, player, needRes)
        } else {
            costRes.noticeCostRes(session, player)
        }

        rt.resString = usePropReturn.s

        return rt.build()
    }

}