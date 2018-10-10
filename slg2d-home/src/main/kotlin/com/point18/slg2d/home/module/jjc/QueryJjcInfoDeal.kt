package com.point18.slg2d.home.module.jjc

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.JjcHomeDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.JjcQueryInfoRt
import pb4server.Home2WorldAskResp
import pb4server.JjcInfoQueryAskReq

// 竞技场查询
class QueryJjcInfoDeal : HomeClientMsgDeal, HomeHelperPlus1<JjcHomeDC>(JjcHomeDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {

        prepare(session) { jjcHomeDC: JjcHomeDC ->
            val rt = queryInfo(session, jjcHomeDC)
            if (rt != null) {
                session.sendMsg(MsgType.JjcQueryInfo_711, rt)
            }
        }

    }

    private fun queryInfo(session: PlayerActor, jjcHomeDC: JjcHomeDC): JjcQueryInfoRt? {
        val rt = JjcQueryInfoRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val jjcHome = jjcHomeDC.jjcHome
        val askMsg = JjcInfoQueryAskReq.newBuilder()
        askMsg.addAllAchievementRewards(jjcHome.achievementRewards)
        askMsg.addAllScoreRewards(jjcHome.scoreRewards)
        askMsg.addAllDrawRewards(jjcHome.drawRewards)
        askMsg.todayNum = jjcHome.todayNum
        askMsg.todayBuyCountNum = jjcHome.todayBuyCountNum

        // 获取玩家的排名及实力值
        session.createACS<Home2WorldAskResp>().ask(
            session.worldShardProxy,
            session.fillHome2WorldAskMsgHeader { it.setJjcInfoQueryAskReq(askMsg) },
            Home2WorldAskResp::class.java
        ).whenCompleteKt { askResp, askErr ->

            try {
                when {
                    askErr != null -> {
                        rt.rt = ResultCode.ASK_ERROR1.code
                        session.sendMsg(MsgType.JjcQueryInfo_711, rt.build())
                        return@whenCompleteKt
                    }

                    askResp == null -> {
                        rt.rt = ResultCode.ASK_ERROR2.code
                        session.sendMsg(MsgType.JjcQueryInfo_711, rt.build())
                        return@whenCompleteKt
                    }

                    else -> {
                        if (askResp.jjcInfoQueryAskRt.rt != ResultCode.SUCCESS.code){
                            rt.rt = askResp.jjcInfoQueryAskRt.rt
                            session.sendMsg(MsgType.JjcQueryInfo_711, rt.build())
                            return@whenCompleteKt
                        }
                        // 成功的话,game已经给发了,不需要返回过来再发
                        jjcHome.todayNum = askResp.jjcInfoQueryAskRt.todayNum
                    }
                }

            } catch (e: Exception) {
                normalLog.error("JjcInfoQueryAskReq Error!", e)
                rt.rt = ResultCode.ASK_ERROR3.code
                session.sendMsg(MsgType.JjcQueryInfo_711, rt.build())
                return@whenCompleteKt
            }
        }

        return null
    }

}
