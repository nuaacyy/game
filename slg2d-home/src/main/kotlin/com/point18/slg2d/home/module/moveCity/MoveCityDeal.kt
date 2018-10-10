package com.point18.slg2d.home.module.moveCity

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.props2GoldCost
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomeMyTargetDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.MoveCityEvent
import com.point18.slg2d.home.module.fireEvent
import pb4client.MoveCity
import pb4client.MoveCityRt
import pb4server.Home2WorldAskResp
import pb4server.MoveCityAskReq
import java.util.*
import java.util.Arrays.asList

class MoveCityDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus2<HomePlayerDC, HomeMyTargetDC>(
        HomePlayerDC::class.java,
        HomeMyTargetDC::class.java,
        Arrays.asList(resHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC, homeMyTargetDC ->
            val moveMsg = (msg as MoveCity)
            val x = (moveMsg.x)
            val y = (moveMsg.y)
            val costType = (moveMsg.costType)

            val rst = fixPointMoveCity(session, x, y, homePlayerDC, homeMyTargetDC)
            if (rst != null) {
                session.sendMsg(MsgType.MakeGroundBuild_360, rst)
            }
        }
    }

    private fun fixPointMoveCity(
        session: PlayerActor,
        x: Int,
        y: Int,
        homePlayerDC: HomePlayerDC,
        homeMyTargetDC: HomeMyTargetDC
    ): MoveCityRt? {
        val rt = MoveCityRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.x = x
        rt.y = y

        val player = homePlayerDC.player
        val bc = pcs.basicProtoCache

        // 根据不同的迁城终点  决定不同的消耗道具
        val wonderType = pcs.wonderLocationProtoCache.findInWonderType(x, y)
        val wonderProto = wonderType.wonderLocationProto
        val bossAreaType = pcs.monsterActivityLocationProtoCache.findActivityBossAreaType(x, y)
        val bossAreaProto = bossAreaType.locationProto
        val moveLvLimit = bc.moveLvLimit
        val usePropId: Int
        if (wonderProto != null) {
            if (wonderType.int == WONDER_MID || wonderType.int == WONDER_BASE) {
                rt.rt = (ResultCode.PARAMETER_ERROR.code)
                return rt.build()
            } else if (wonderType.int == WONDER_BLACK){
                if (player.castleLv < moveLvLimit) {
                    rt.rt = ResultCode.MOVE_CITY_LV_LIMIT.code
                    return rt.build()
                }
                // 根据奇观类型选择迁城道具
                val consumeType = moveCityTypeByWonderType(wonderProto.wondersType)
                val propsId = bc.moveMainCityConsumeMap[consumeType]
                if (propsId == null) {
                    rt.rt = ResultCode.NO_PROTO.code
                    return rt.build()
                }
                usePropId = propsId
            } else {
                val propsId = bc.moveMainCityConsumeMap[0]
                if (propsId == null) {
                    rt.rt = (ResultCode.NO_PROTO.code)
                    return rt.build()
                }
                usePropId = propsId
            }
        } else if (bossAreaProto != null) {
            if (wonderType.int == SNOW_BASE) {
                rt.rt = (ResultCode.PARAMETER_ERROR.code)
                return rt.build()
            } else {
                if (player.castleLv < moveLvLimit) {
                    rt.rt = ResultCode.MOVE_CITY_LV_LIMIT.code
                    return rt.build()
                }

                val consumeType = SNOW_MOVE_CITY_PROP
                val propsId = bc.moveMainCityConsumeMap[consumeType]
                if (propsId == null) {
                    rt.rt = (ResultCode.NO_PROTO.code)
                    return rt.build()
                }
                usePropId = propsId
            }
        } else {
            val propsId = bc.moveMainCityConsumeMap[0]
            if (propsId == null) {
                rt.rt = (ResultCode.NO_PROTO.code)
                return rt.build()
            }
            usePropId = propsId
        }

        var costs = LinkedList(asList(ResVo(RES_PROPS, usePropId.toLong(), 1)))
        val checkRst = resHelper.checkRes(session, costs)
        if (!checkRst) {
            val (ok, needRes) = props2GoldCost(costs[0])
            if (ok != ResultCode.SUCCESS) {
                rt.rt = ResultCode.LESS_RESOUCE.code
                return rt.build()
            }

            //校验需要的资源
            if (!resHelper.checkRes(session, needRes)) {
                rt.rt = ResultCode.LESS_RESOUCE.code
                return rt.build()
            }

            costs = needRes
        }

        val action = ACTION_MOVE_CITY
        val costResWithoutNoticeResult = resHelper.costResWithoutNotice(session, action, player, costs)

        val askMsg = MoveCityAskReq.newBuilder()
        askMsg.x = x
        askMsg.y = y

        session.createACS<Home2WorldAskResp>()
            .ask(session.worldShardProxy, session.fillHome2WorldAskMsgHeader {
                it.setMoveCityAskReq(askMsg)
            }, Home2WorldAskResp::class.java)
            .whenCompleteKt { askResp, askErr ->


                try {
                    when {
                        askErr != null -> {
                            rt.rt = ResultCode.ASK_ERROR1.code
                            resHelper.addResWithoutNotice(session, action, player, costs)
                        }

                        (askResp == null ) -> {
                            rt.rt = ResultCode.ASK_ERROR2.code
                            resHelper.addResWithoutNotice(session, action, player, costs)
                        }

                        else -> {
                            if (askResp.moveCityAskRt.rt != ResultCode.SUCCESS.code){
                                rt.rt = askResp.moveCityAskRt.rt
                                resHelper.addResWithoutNotice(session, action, player, costs)
                            }else{
                                costResWithoutNoticeResult.noticeCostRes(session, player)
                                homeMyTargetDC.targetInfo.normalMoveCityNum++
                                fireEvent(session, MoveCityEvent(FixedPointMove))
                            }

                        }
                    }

                } catch (e: Exception) {
                    normalLog.error("MoveCityAskReq Error!", e)
                    rt.rt = ResultCode.ASK_ERROR3.code
                }
                session.sendMsg(MsgType.MakeGroundBuild_360, rt.build())
            }

        return null
    }
}