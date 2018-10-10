package com.point18.slg2d.world.module.fog

import com.google.protobuf.MessageLite
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.wpm
import com.point18.slg2d.common.constg.CLEAR_FOG_EVENT
import com.point18.slg2d.common.constg.NotGetReward
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.world.event.ClearFog
import pb4client.GetFogReward
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.addResToHome
import com.point18.slg2d.world.module.ReqDealEntered
import pb4client.GetFogRewardRt
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs

class GetFogReward : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        msg as GetFogReward
        val rt = dealGetFogWinReward(session.areaCache, session.playerId, msg)
        session.sendMsg(MsgType.GetFogReward_1574, rt.build())
    }

    private fun dealGetFogWinReward(
        areaCache: AreaCache,
        playerId: Long,
        msg: GetFogReward
    ): GetFogRewardRt.Builder {
        val rt = GetFogRewardRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val fog = areaCache.fogCache.findFogById(playerId, msg.fogId)
        if (fog == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        if (fog.state != NotGetReward) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        val fogProto = pcs.mapOpenProtoCache.mapOpenMap[fog.fogId]
        if (fogProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt
        }

        fog.state = UnLocked

        wpm.es.fire(
            areaCache, playerId, CLEAR_FOG_EVENT, ClearFog(msg.fogId)
        )

        // 添加奖励
        addResToHome(areaCache, ACTION_GET_FOG_REWARD, playerId, fogProto.rewards)
        return rt
    }
}