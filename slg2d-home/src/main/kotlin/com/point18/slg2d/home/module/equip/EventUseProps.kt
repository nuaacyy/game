package com.point18.slg2d.home.module.equip

import com.point18.slg2d.common.constg.ACTION_USE_PROPS
import com.point18.slg2d.common.constg.RES_PROPS
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.dc.HeroDC
import com.point18.slg2d.home.dc.HomeMyTargetDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.IEventHandler
import com.point18.slg2d.home.module.event.UsePropsAtOnceEvent
import xyz.ariane.util.lzInfo
import java.util.*
import java.util.Arrays.asList

class UsePropsEventHandler(
    private val resHelper: ResHelper = ResHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper()
) : IEventHandler<UsePropsAtOnceEvent>, HomeHelperPlus3<HomePlayerDC, HeroDC, HomeMyTargetDC>(
    HomePlayerDC::class.java, HeroDC::class.java, HomeMyTargetDC::class.java,
    asList(resHelper, effectHelper)
) {

    override fun handleEvent(session: PlayerActor, event: UsePropsAtOnceEvent) {
        prepare(session) { homePlayerDC: HomePlayerDC, heroDC: HeroDC, homeMyTargetDC: HomeMyTargetDC ->
            val propsOnlyId = event.propsOnlyId
            val propsId = event.propsId
            val num = event.num
            val propProto = pcs.equipCache.equipProtoMap[propsId]
            if (propProto != null) {
                val useMap = equipM.usePropEff[propProto.mainType]
                if (useMap == null) {
                    return@prepare
                }

                val use = useMap[propProto.subType]
                if (use != null) {
                    val cost = LinkedList<ResVo>(
                        asList(ResVo(RES_PROPS, propsId.toLong(), num.toLong()))
                    )
                    val checkCost = resHelper.checkRes(session, cost)
                    if (!checkCost) {
                        //  todo 错误
                        return@prepare
                    }

                    val player = homePlayerDC.player
                    val action = ACTION_USE_PROPS
                    val costRes = resHelper.costResWithoutNotice(session, action, player, cost)

                    val helpers = Helpers(resHelper, effectHelper)
                    val depDcs = UsePropsDepDcs(homePlayerDC, heroDC, homeMyTargetDC)
                    val usePropReturn =
                        use.useProp(depDcs, propsId, session, num, "", helpers, cost, costRes) { useRt, getResS ->
                            if (useRt != ResultCode.SUCCESS.code) {
                                com.point18.slg2d.common.commonfunc.normalLog.lzInfo { "获得道具并且马上使用失败了,道具ID为:$propsId,失败原因为:$useRt" }
                            }
                        }

                    if (usePropReturn == null) {
                        // 已经异步使用道具 接口回调里做了资源的回退处理了
                        return@prepare
                    }
                    if (usePropReturn.rt != ResultCode.SUCCESS) {
                        // 使用道具失败了
                        resHelper.addResWithoutNotice(session, action, player, cost)
                        return@prepare
                    } else {
                        costRes.noticeCostRes(session, player)
                    }
                }
            }
        }
    }
}