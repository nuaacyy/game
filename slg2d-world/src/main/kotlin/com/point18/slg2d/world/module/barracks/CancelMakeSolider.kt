package com.point18.slg2d.world.module.barracks

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.syncBarrack2Home
import com.point18.slg2d.world.msgnotice.createBarracksChangeNotifier
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode

data class CancelMakeSoliderRt(
    var rt: Int = ResultCode.SUCCESS.code,
    var cancelNum: Int = 0
)

// 取消造兵
fun cancelMakeSolider(areaCache: AreaCache, playerId: Long, soliderId: Int): CancelMakeSoliderRt {

    val barracksVo = areaCache.barracksCache.findBarracksByPlayerIdAndSoldierId(playerId, soliderId)
    if (barracksVo == null || barracksVo.overTime == 0L) {
        return CancelMakeSoliderRt(ResultCode.BARRACK_NO_IN_ERROR.code)
    }

    val soliderProto = pcs.soliderCache.soliderProtoMap[soliderId]
    if (soliderProto == null) {
        return CancelMakeSoliderRt(ResultCode.NO_PROTO.code)
    }

    val nowMakeNum = barracksVo.nowMakeNum

    // 修改数据
    areaCache.barracksCache.makeSoliderUpdate(barracksVo, 0L)
    barracksVo.nowMakeNum = 0

    // 推送给客户端变更
    val session = fetchOnlinePlayerSession(areaCache, playerId)
    if (session != null) {
        val notice = createBarracksChangeNotifier(barracksVo)
        notice.notice(session)
    }

    //同步兵营数据至home
    syncBarrack2Home(areaCache, playerId)

    return CancelMakeSoliderRt(ResultCode.SUCCESS.code, nowMakeNum)
}

