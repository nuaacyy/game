package com.point18.slg2d.world.module.instance

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.UnitInfo
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ACTION_INSTANCE_GET_STAR_BOX
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.module.uiCondition.uiConditionCheck
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.constg.INSTANCE_OPEN
import com.point18.slg2d.world.common.addResToHome
import pb4client.InstanceGetStarReward
import pb4client.InstanceGetStarRewardRt
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*

// 推图领取星宝箱奖励
class GetInstanceStarReward : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val chapterId = (msg as InstanceGetStarReward).chapterId
        val starNum = msg.starNum

        val rt = getInstanceStarReward(session, chapterId, starNum)

        session.sendMsg(MsgType.InstanceGetStarReward_1473, rt)
    }

    fun getInstanceStarReward(
        session: PlayerSession,
        chapterId: Int,
        starNum: Int
    ): (InstanceGetStarRewardRt) {
        val rt = InstanceGetStarRewardRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.chapterId = chapterId
        rt.starNum = starNum

        // 验证一些模版基础数据

        val player = session.player

        // 功能开启
        val uiConditionRt = uiConditionCheck(session.areaCache, player, INSTANCE_OPEN)
        if (uiConditionRt != ResultCode.SUCCESS.code) {
            rt.rt = uiConditionRt
            return rt.build()
        }

        val instanceUnitProto = pcs.instanceUnitProtoCache.protoMap[chapterId]
        if (instanceUnitProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        val dropId = instanceUnitProto.unitStarRewardMap[starNum]
        if (dropId == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        val instanceVo = session.areaCache.instanceCache.findInstance(session.playerId)
        if (instanceVo == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        val instanceUnitVo = instanceVo.unitInfoMap.getOrPut(chapterId) { UnitInfo(LinkedList()) }  // 玩家章节信息
        if (instanceUnitVo.getStarBox.contains(starNum)) {
            rt.rt = ResultCode.INSTANCE_STAR_BOX_HAVE_ERROR.code
            return rt.build()
        }

        // 检测一下玩家当前章节所获得的星星
        var haveStarNum = 0
        for ((id, starSet) in instanceVo.starInfoMap) {
            val instanceProto = pcs.instanceProtoCache.protoMap[id]
            if (instanceProto == null) {
                continue
            }

            if (instanceProto.unitId != chapterId) {
                continue
            }
            haveStarNum += starSet.count()
        }

        if (haveStarNum < starNum) {
            rt.rt = ResultCode.INSTANCE_STAR_BOX_NO_ENOUGH_ERROR.code
            return rt.build()
        }

        // 进行领奖
        val reward = pcs.dropBagCache.dropBagMap[dropId]
        if (reward == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        addResToHome(session.areaCache, ACTION_INSTANCE_GET_STAR_BOX, player.id, reward.dropMap)

        // 修改数据
        instanceUnitVo.getStarBox.add(starNum)

        return rt.build()
    }

}
