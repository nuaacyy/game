package com.point18.slg2d.home.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.CHANGE_INNER_CITY
import com.point18.slg2d.common.constg.CLEAR_TIME_INNER_CITY_BUILDING_UPGRADE
import com.point18.slg2d.common.constg.CLEAR_TIME_RESEARCH
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayer
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createInnerCityInfoChangedNotifier
import com.point18.slg2d.home.msgnotice.createResearchChangeNotifier
import pb4client.SendAllianceHelp
import pb4client.SendAllianceHelpRt
import pb4server.Home2PublicAskResp
import pb4server.SendAllianceHelpAskReq

// 登记帮助信息

class SendAllianceHelpDeal2 : HomeClientMsgDeal, HomeHelperPlus2<HomePlayerDC, InnerCityDC>(
    HomePlayerDC::class.java, InnerCityDC::class.java
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, innerCityDC: InnerCityDC ->
            val helpType = (msg as SendAllianceHelp).helpType
            val helpValue1 = msg.helpValue1
            val helpValue2 = msg.helpValue2
            val helpValue3 = msg.helpValue3
            val helpValue4 = msg.helpValue4

            val sendHelpRt = this.sendAllianceHelp(
                session, helpType, helpValue1, helpValue2,
                helpValue3, helpValue4, homePlayerDC, innerCityDC
            )
            if (sendHelpRt != null) {
                session.sendMsg(MsgType.SendAllianceHelp_1072, sendHelpRt)
            }
        }
    }

    private fun sendAllianceHelp(
        session: PlayerActor, helpType: Int, helpValue1: Long, helpValue2: Long,
        helpValue3: Long, helpValue4: Long, homePlayerDC: HomePlayerDC, innerCityDC: InnerCityDC
    ): SendAllianceHelpRt? {
        val rt = SendAllianceHelpRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.helpType = helpType

        val player = homePlayerDC.player

        if (player.allianceId == 0L) {
            rt.rt = (ResultCode.ALLIANCE_QUERY_NOT_EXIST.code)
            return rt.build()
        }

        if (helpType == CLEAR_TIME_RESEARCH) {
            val researchId = helpValue1.toInt()
            val researchLv = helpValue2.toInt() - 1

            // 检测玩家是否是当前等级
            val researchVo = player.researchInfoMap
            val vo = researchVo[researchId]
            if (vo == null) {
                rt.rt = (ResultCode.SEND_ALLIANCE_HELP_ERROR.code)
                return rt.build()
            }
            if (vo.researchLv != researchLv) {
                rt.rt = (ResultCode.SEND_ALLIANCE_HELP_ERROR.code)
                return rt.build()
            }
            if (vo.helpId != 0L) {
                rt.rt = (ResultCode.GO_ALLIANCE_HELP_HAS_ERROR.code)
                return rt.build()
            }
        } else if (helpType == CLEAR_TIME_INNER_CITY_BUILDING_UPGRADE) {
            val buildType = helpValue1.toInt()
            val buildLv = helpValue2.toInt() - 1
            val buildId = helpValue3

            // 检测玩家是否是当前等级
            val building = innerCityDC.findInnerCityFromId(buildId)
            if (building == null) {
                rt.rt = (ResultCode.SEND_ALLIANCE_HELP_ERROR.code)
                return rt.build()
            }

            if (building.completeTime == 0L) {
                rt.rt = (ResultCode.GO_ALLIANCE_HELP_HAS_ERROR.code)
                return rt.build()
            }

            if (building.lv != buildLv) {
                rt.rt = (ResultCode.GO_ALLIANCE_HELP_HAS_ERROR.code)
                return rt.build()
            }

            if (building.cityType != buildType) {
                rt.rt = (ResultCode.GO_ALLIANCE_HELP_HAS_ERROR.code)
                return rt.build()
            }

            if (building.helpId != 0L) {
                rt.rt = (ResultCode.GO_ALLIANCE_HELP_HAS_ERROR.code)
                return rt.build()
            }
        } else {
            rt.rt = (ResultCode.SEND_ALLIANCE_HELP_TYPE_ERROR.code)
            return rt.build()
        }

        var nowHelpNum = 0
        if (helpType == CLEAR_TIME_RESEARCH) {
            val researchId = helpValue1.toInt()
            // 检测玩家是否是当前等级
            val researchVo = player.researchInfoMap
            val vo = researchVo[researchId]
            if (vo == null) {
                rt.rt = (ResultCode.PARAMETER_ERROR.code)
                return rt.build()
            }
            nowHelpNum = vo.helperIds.size
        } else if (helpType == CLEAR_TIME_INNER_CITY_BUILDING_UPGRADE) {
            val buildId = helpValue3

            // 检测玩家是否是当前等级
            val building = innerCityDC.findInnerCityFromId(buildId)
            if (building == null) {
                rt.rt = (ResultCode.PARAMETER_ERROR.code)
                return rt.build()
            }
            nowHelpNum = (building.helperIdMap.size)
        }

        sendHelp(
            session, player.allianceId, player, helpType,
            helpValue1, helpValue2, helpValue3, helpValue4, nowHelpNum, innerCityDC
        )

        return null
    }

    // 请求联盟帮助
    private fun sendHelp(
        session: PlayerActor, allianceId: Long, player: HomePlayer, helpType: Int, helpValue1: Long,
        helpValue2: Long, helpValue3: Long, helpValue4: Long, nowHelpNum: Int, innerCityDC: InnerCityDC
    ) {
        val askMsg = SendAllianceHelpAskReq.newBuilder()
        askMsg.helpType = helpType
        askMsg.helpValue1 = helpValue1
        askMsg.helpValue2 = helpValue2
        askMsg.helpValue3 = helpValue3
        askMsg.helpValue4 = helpValue4
        askMsg.nowHelpNum = nowHelpNum

        session.createACS<Home2PublicAskResp>().ask(
            session.publicShardProxy,
            session.fillHome2PublicAskMsgHeader(allianceId) {
                it.setSendAllianceHelpAskReq(askMsg)
            },
            Home2PublicAskResp::class.java
        ).whenCompleteKt { askResp, askErr ->
            val rt = SendAllianceHelpRt.newBuilder()
            rt.helpType = helpType
            rt.rt = ResultCode.SUCCESS.code

            if (askErr != null || askResp == null) {
                // todo 重试...
                rt.rt = ResultCode.PROCESS_ERROR_RPC_EXCEPTION.code
            } else if (askResp.sendAllianceHelpAskRt.rt != ResultCode.SUCCESS.code) {
                // todo 重试????? 由于W服的数据已经异常了,重试100次这个都是错的怎么办...
                rt.rt = askResp.sendAllianceHelpAskRt.rt
            } else {
                if (helpType == CLEAR_TIME_RESEARCH) {
                    // 检测玩家是否是当前等级
                    val researchVo = player.researchInfoMap
                    val vo = researchVo[helpValue1.toInt()] ?: return@whenCompleteKt
                    vo.helpId = askResp.sendAllianceHelpAskRt.helpId
                    player.researchInfoMap[helpValue1.toInt()] = vo

                    // 推送给客户端变更
                    val notice = createResearchChangeNotifier(
                        helpValue1.toInt(), vo.researchLv,
                        vo.researchOverTime, vo.helpId
                    )
                    notice.notice(session)

                } else if (helpType == CLEAR_TIME_INNER_CITY_BUILDING_UPGRADE) {
                    val buildId = helpValue3
                    // 检测玩家是否是当前等级
                    val building = innerCityDC.findInnerCityFromId(buildId)
                    if (building == null) {
                        return@whenCompleteKt
                    }
                    if (building.completeTime != 0L) {
                        building.helpId = askResp.sendAllianceHelpAskRt.helpId
                        // 推送给客户端变更
                        createInnerCityInfoChangedNotifier(
                            CHANGE_INNER_CITY,
                            building.cityType,
                            building.id,
                            (building.startTime / 1000).toInt(),
                            (building.completeTime / 1000).toInt(),
                            building.state,
                            building.positionIndex,
                            building.lv,
                            building.helpId
                        ).notice(session)
                    }
                }
            }

            session.sendMsg(MsgType.SendAllianceHelp_1072, rt.build())
        }
    }
}


