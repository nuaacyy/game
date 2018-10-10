package com.point18.slg2d.home.module.innerCity

import com.point18.slg2d.common.constg.STABLE
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.InnerCityHelper
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.IHeartDeal
import java.util.Arrays.asList

/**
 * 建筑升级心跳回调用
 */
class InnerCityUpgradeHeartTriggerDeal(
    private val innerCityHelper: InnerCityHelper = InnerCityHelper()
) : IHeartDeal,
    HomeHelperPlus1<InnerCityDC>(
        InnerCityDC::class.java,
        asList(innerCityHelper)
    ) {

    override fun dealHeart(session: PlayerActor, actionId: Long, onComplete: (rt: Int) -> Unit) {
        prepare(session) { innerCityDC: InnerCityDC ->
            val rt = upgradeInnerCity(session, actionId, innerCityDC)
            onComplete(rt)
        }
    }

    private fun upgradeInnerCity(
        session: PlayerActor,
        actionId: Long,
        innerCityDC: InnerCityDC
    ): Int {
        val innerCityInfo = innerCityDC.findInnerCityFromId(actionId)
        if (innerCityInfo == null) {
            // 没有建筑需要处理了
            return ResultCode.PARAMETER_ERROR.code
        }
        if (innerCityInfo.state == STABLE) {
            return ResultCode.HEART_INVALID.code
        }

        // 更新建筑状态
        innerCityDC.updateInnerCityUpgradeState(innerCityInfo, STABLE, 0, 0)

        // 处理建筑升级
        innerCityHelper.buildLvUp(session, innerCityInfo)

        return ResultCode.SUCCESS.code
    }

}