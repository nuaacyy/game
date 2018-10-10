package com.point18.slg2d.world.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.A_RIGHT_MEMBER_MANAGER
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.dealJoinAlliance
import com.point18.slg2d.world.module.ReqDealEntered
import pb4client.AllianceDealJoinReq
import pb4client.AllianceDealJoinReqRt
import pb4server.CheckJoinAllianceStateReq
import pb4server.World2HomeAskResp

//处理玩家加入联盟申请 806
class DealJoinAllianceReq : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = AllianceDealJoinReqRt.newBuilder()
        val reqType = (msg as AllianceDealJoinReq).reqType
        val reqPid = msg.reqPlayerId

        rt.reqType = reqType
        rt.reqPlayerId = reqPid

        var res: JoinAllianceFuncRT
        if (reqType == 1) {
            //同意
            // 发ASK到home去检测玩家的入帮状态
            // 邀请的是其他服的玩家,去玩家所在服拿数据
            val reqMsg = CheckJoinAllianceStateReq.newBuilder()
            session.areaCache.worldActor.createACS<World2HomeAskResp>()
                .ask(
                    session.areaCache.worldActor.homeShardRegion,
                    session.areaCache.fillW2HAskMsgHeader(reqPid) {
                        it.setCheckJoinAllianceStateReq(reqMsg)
                    },
                    World2HomeAskResp::class.java
                )
                .whenCompleteKt { hrt, err ->
                    try {
                        when {
                            err != null -> {
                                rt.rt = ResultCode.ASK_ERROR1.code
                                session.sendMsg(MsgType.AllianceDealJoinReq_806, rt.build())
                            }
                            hrt == null -> {
                                rt.rt = ResultCode.ASK_ERROR2.code
                                session.sendMsg(MsgType.AllianceDealJoinReq_806, rt.build())
                            }
                            else -> {
                                if (hrt.checkJoinAllianceStateRt.rt != ResultCode.SUCCESS.code) {
                                    rt.rt = hrt.checkJoinAllianceStateRt.rt
                                    session.sendMsg(MsgType.AllianceDealJoinReq_806, rt.build())
                                } else {
                                    // home的验证通过了 并且已经把玩家状态设置成了正在尝试加入联盟中
                                    res = this.joinAllowed(session, reqPid, reqType)
                                    val rrt = res.rt
                                    if (rrt != null) {
                                        rt.rt = rrt.rt
                                        session.sendMsg(MsgType.AllianceDealJoinReq_806, rt.build())
                                    }
                                }
                            }
                        }


                    } catch (e: Exception) {
                        rt.rt = ResultCode.ASK_ERROR3.code
                        session.sendMsg(MsgType.AllianceDealJoinReq_806, rt.build())
                    }
                }
        } else {
            //拒绝
            res = this.joinRefused(session, reqPid, reqType)
            val rrt = res.rt
            if (rrt != null) {
                rt.rt = rrt.rt
                session.sendMsg(MsgType.AllianceDealJoinReq_806, rt.build())
            }
        }
    }

    data class JoinAllianceFuncRT(var rt: AllianceDealJoinReqRt?, var id: Long)

    /********************************************* 806 拒绝玩家加入联盟 *********************************************/
    fun joinRefused(session: PlayerSession, reqPid: Long, reqType: Int): JoinAllianceFuncRT {
        val rt = AllianceDealJoinReqRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.reqPlayerId = reqPid
        rt.reqType = reqType
        var allianceId = 0L

        val player = session.player
        val res = JoinAllianceFuncRT(rt.build(), allianceId)
        if (player.allianceId == 0L) {
            rt.rt = (ResultCode.ALLIANCE_ARGS_ERROR.code)
            res.id = allianceId
            res.rt = rt.build()
            return res
        }

        // 验证玩家的职位是否拥有该模块操作权限
        if (!hasRight(player, A_RIGHT_MEMBER_MANAGER)) {
            rt.rt = (ResultCode.ALLIANCE_POSITION_RIGHT_FORBIDDEN.code)
            res.id = allianceId
            res.rt = rt.build()
            return res
        }

        allianceId = player.allianceId

        dealJoinAlliance(session, reqType, allianceId, reqPid)
        res.id = allianceId
        res.rt = null
        return res
    }

    /********************************************* 806 同意玩家加入联盟 *********************************************/
    fun joinAllowed(session: PlayerSession, reqPid: Long, reqType: Int): JoinAllianceFuncRT {
        val rt = AllianceDealJoinReqRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.reqPlayerId = reqPid
        rt.reqType = reqType
        var allianceId = 0L
        val player = session.player
        val res = JoinAllianceFuncRT(rt.build(), allianceId)

        // 验证玩家的职位是否拥有该模块操作权限
        if (!hasRight(player, A_RIGHT_MEMBER_MANAGER)) {
            rt.rt = (ResultCode.ALLIANCE_POSITION_RIGHT_FORBIDDEN.code)
            res.id = allianceId
            res.rt = rt.build()
            return res
        }

        allianceId = player.allianceId
        dealJoinAlliance(session, reqType, allianceId, reqPid)

        res.id = allianceId
        res.rt = null
        return res
    }
}






