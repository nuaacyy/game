package com.point18.slg2d.home.module.innerCity

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.DELETE_INNER_CITY
import com.point18.slg2d.common.constg.STABLE
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.common.TargetHelper
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.IHeartDeal
import com.point18.slg2d.home.module.event.RefreshBuildEffectEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createInnerCityInfoChangedNotifier
import xyz.ariane.util.lzWarn
import java.util.Arrays.asList

/**
 * 建筑拆除心跳回调用
 */
class InnerCityDestroyHeartTriggerDeal(
    private val targetHelper: TargetHelper = TargetHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper()
) : IHeartDeal, HomeHelperPlus1<InnerCityDC>(
    InnerCityDC::class.java,
    asList(targetHelper, effectHelper)
) {

    override fun dealHeart(session: PlayerActor, actionId: Long, onComplete: (rt: Int) -> Unit) {
        prepare(session) { innerCityDC: InnerCityDC ->
            val rt = destroyInnerCity(session, actionId, innerCityDC)
            onComplete(rt)
        }
    }

    private fun destroyInnerCity(
        session: PlayerActor,
        actionId: Long,
        innerCityDC: InnerCityDC
    ): Int {
        val innerCityInfo = innerCityDC.findInnerCityFromId(actionId)
        if (innerCityInfo == null) {
            // 找不到建筑
            normalLog.lzWarn { "HeartInnerCityDestroy 找不到InnerCity对象" }
            return ResultCode.PARAMETER_ERROR.code
        }
        if (innerCityInfo.state == STABLE) {
            normalLog.lzWarn { "HeartInnerCityDestroy 建筑处于稳定状态，无法拆除" }
            return ResultCode.HEART_INVALID.code
        }

        // 删除建筑
        innerCityDC.deleteInnerCity(innerCityInfo)

        // 向客户端推送建筑拆除
        createInnerCityInfoChangedNotifier(
            DELETE_INNER_CITY,
            innerCityInfo.cityType,
            innerCityInfo.id,
            (innerCityInfo.startTime / 1000).toInt(),
            (innerCityInfo.completeTime / 1000).toInt(),
            innerCityInfo.state, innerCityInfo.positionIndex,
            innerCityInfo.lv, innerCityInfo.helpId
        ).notice(session)

        // 触发刷新建筑效果事件
        fireEvent(session, RefreshBuildEffectEvent(targetHelper, effectHelper))

        return ResultCode.SUCCESS.code
    }
}
