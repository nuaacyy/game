package com.point18.slg2d.world.module.mainHeroprison

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.PRISON_AWAITING_EXECUTION
import pb4client.CheckEatPoisonNumRt
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode

// 查询所需吃的毒蘑菇数量
class CheckEatPoisonDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = checkEatPoison(session)
        session.sendMsg(MsgType.CheckEatPoisonNum_1359, rt)
    }

    private fun checkEatPoison(session: PlayerSession): CheckEatPoisonNumRt {
        val rt = CheckEatPoisonNumRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.num = 0

        val areaCache = session.areaCache
        val player = session.player
        val hero = areaCache.heroCache.findHeroById(player.id, player.mainHeroId)
        if (hero == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        if (hero.mainHeroState != PRISON_AWAITING_EXECUTION) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        val poisonProto = pcs.equipCache.equipProtoMap[pcs.basicProtoCache.fastDead]

        if (poisonProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        val poisonTime = poisonProto.extend1.toIntOrNull()
        if (poisonTime == null){
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        // 判断时间是否允许服毒
        if (hero.mainHeroStateEndTime - getNowTime() < poisonTime) {
            rt.rt = ResultCode.EAR_POISON_TIME_ERROR.code
            return rt.build()
        }

        // 判断玩家的实力,决定消耗
        val myPowerRank = areaCache.playerCache.findPowerRankByPlayerId(session.playerId)
        if (myPowerRank == 0) {
            rt.rt = ResultCode.RANK_ERROR.code
            return rt.build()
        }

        val costNum = pcs.fastDeadPropsProtoCache.findEventRankRewardByRank(myPowerRank)
        rt.num = costNum
        return rt.build()
    }
}


