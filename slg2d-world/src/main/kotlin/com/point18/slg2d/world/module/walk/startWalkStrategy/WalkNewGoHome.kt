package com.point18.slg2d.world.module.walk.startWalkStrategy

import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.module.walk.IWalkDeal
import com.point18.slg2d.world.module.walk.walkComm.WalkParam
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.Del
import com.point18.slg2d.common.constg.WalkReinforceWonder
import com.point18.slg2d.common.constg.WONDER_BASE
import com.point18.slg2d.world.common.noticeWonderReinforce
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*

// 回城
class WalkGoHome : BaseWalkStrategy(), IWalkDeal {
    override fun getPosByTarget(
        areaCache: AreaCache,
        targetId: Long,
        gotoX: Int,
        gotoY: Int
    ): IWalkDeal.GetPosByTargetResult {
        return IWalkDeal.GetPosByTargetResult(ResultCode.SUCCESS.code, gotoX, gotoY)
    }

    override fun walkStartCheck(areaCache: AreaCache, wp: WalkParam, rs: IWalkDeal.WalkStartCheckResult?): Int {
        return ResultCode.SUCCESS.code
    }

    override fun walkStartDeal(areaCache: AreaCache, walkLineInfo: Walk, targetId: Long): Int {
        val rst = pcs.wonderLocationProtoCache.findInWonderType(walkLineInfo.marchPlaceX, walkLineInfo.marchPlaceY)
        if (rst.int != WONDER_BASE) {
            return ResultCode.SUCCESS.code
        }
        val group = areaCache.walkForceGroupCache.findWalkForceGroupById(walkLineInfo.walkForceGroupId)
        if (group == null) {
            normalLog.error("行军线对应的行军组不存在,行军线Id:%d", walkLineInfo.id)
            return ResultCode.PARAMETER_ERROR.code
        }

        if (group.gotoRunType == WalkReinforceWonder) {
            //通知奇观增援部队变化
            val player = areaCache.playerCache.findPlayerById(group.mainPlayerId)
            if (player == null) {
                normalLog.error("找不到行军组中的玩家信息,行军组Id:%d", group.id)
                return ResultCode.PARAMETER_ERROR.code
            }

            val allAllianceMembers = areaCache.playerCache.findPlayersByAllianceId(player.allianceId)
            val memberIds = LinkedList<Long>()
            for (member in allAllianceMembers) {
                memberIds.add(member.id)
            }
            noticeWonderReinforce(areaCache, Del, walkLineInfo.walkForceGroupId, memberIds)
        }
        return ResultCode.SUCCESS.code
    }

}
