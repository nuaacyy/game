package com.point18.slg2d.home.module.equip

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ACTION_USE_PROPS
import com.point18.slg2d.common.constg.RES_PROPS
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
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
import pb4client.UsePropRt
import java.util.*
import java.util.Arrays.asList

// 使用道具
class UsePropDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper()
) : HomeClientMsgDeal, HomeHelperPlus4<HomePlayerDC, NewEquipDC, HeroDC, HomeMyTargetDC>(
    HomePlayerDC::class.java,
    NewEquipDC::class.java,
    HeroDC::class.java,
    HomeMyTargetDC::class.java,
    asList(resHelper, effectHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, newEquipDC: NewEquipDC, heroDC: HeroDC, homeMyTargetDC: HomeMyTargetDC ->
            val usePropMsg = msg as pb4client.UseProp
            val propId = usePropMsg.usePropId
            val propNum = usePropMsg.usePropNum
            val extendVal = usePropMsg.extendVal
            val buyEquipRt = useProp(
                session, propId, propNum, extendVal,
                homePlayerDC, newEquipDC, heroDC, homeMyTargetDC
            )
            if (buyEquipRt != null) {
                session.sendMsg(MsgType.UseProp_1063, buyEquipRt)
            }
        }
    }

    private fun useProp(
        session: PlayerActor, propId: Long, propNum: Int, extendVal: String,
        homePlayerDC: HomePlayerDC, newEquipDC: NewEquipDC, heroDC: HeroDC, homeMyTargetDC: HomeMyTargetDC
    ): UsePropRt? {
        val rt = UsePropRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.resString = ""

        val player = homePlayerDC.player
        val propsVo = newEquipDC.findPropById(propId)
        if (propsVo == null) {
            rt.rt = ResultCode.NO_EQUIP_ERROR.code
            return rt.build()
        }

        val propProto = pcs.equipCache.equipProtoMap[propsVo.equipProtoId]
        if (propProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        val condMap = equipM.usePropCond[propProto.mainType]
        if (condMap != null) {
            val cond = condMap[propProto.subType]
            if (cond != null) {
                //检查道具使用条件
                val isOk = cond.useCondition(propsVo.equipProtoId, session, propNum, extendVal)
                if (isOk != ResultCode.SUCCESS) {
                    rt.rt = isOk.code
                    return rt.build()
                }
            }
        }

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

        val cost = LinkedList<ResVo>(
            asList(ResVo(RES_PROPS, propsVo.equipProtoId.toLong(), propNum.toLong()))
        )

        val checkCost = resHelper.checkRes(session, cost)
        if (!checkCost) {
            rt.rt = ResultCode.LESS_RESOUCE.code
            return rt.build()
        }

        val action = ACTION_USE_PROPS
        val costRes = resHelper.costResWithoutNotice(session, action, player, cost)

        val helpers = Helpers(resHelper, effectHelper)
        val depDcs = UsePropsDepDcs(homePlayerDC, heroDC, homeMyTargetDC)
        val usePropReturn =
            use.useProp(
                depDcs,
                propsVo.equipProtoId,
                session,
                propNum,
                extendVal,
                helpers,
                cost,
                costRes
            ) { useRt, getResS ->
                val usePropRt = UsePropRt.newBuilder()
                usePropRt.rt = useRt
                usePropRt.resString = getResS
                session.sendMsg(MsgType.UseProp_1063, usePropRt.build())
            }

        if (usePropReturn == null) {
            return null
        }
        if (usePropReturn.rt != ResultCode.SUCCESS) {
            rt.rt = usePropReturn.rt.code
            resHelper.addResWithoutNotice(session, action, player, cost)
        } else {
            costRes.noticeCostRes(session, player)
        }

        rt.resString = usePropReturn.s

        return rt.build()
    }

}