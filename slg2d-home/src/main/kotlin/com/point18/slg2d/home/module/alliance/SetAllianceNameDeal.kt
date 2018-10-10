//修改联盟名称/简称
package com.point18.slg2d.home.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ACTION_ALLIANCE_SET_NAME
import com.point18.slg2d.common.constg.SET_ALLIANCE_LAN
import com.point18.slg2d.common.constg.SET_ALLIANCE_NAME
import com.point18.slg2d.common.constg.SET_ALLIANCE_SHORT_NAME
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.*
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.CostResWithoutNoticeResult
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus4
import com.point18.slg2d.home.hpm
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.SetAllianceName
import pb4client.SetAllianceNameRt
import pb4server.FriendRefreshNoticeTell
import pb4server.Home2PublicAskResp
import pb4server.Home2PublicManagerAskResp
import pb4server.SetAllianceNameAskReq
import java.util.*
import java.util.Arrays.asList

class SetAllianceNameDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus4<HomePlayerDC, VipDC, SkinDC, FriendDC>(
    HomePlayerDC::class.java, VipDC::class.java, SkinDC::class.java, FriendDC::class.java,
    asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, vipDC: VipDC, skinDC: SkinDC, friendDC: FriendDC ->
            val name = (msg as SetAllianceName).name
            val setType = msg.setType
            val rt = this.setAllianceName(
                session, name, setType, homePlayerDC, vipDC, skinDC, friendDC
            )
            if (rt != null) {
                session.sendMsg(MsgType.AllianceSetName_898, rt)
            }
        }
    }

    private fun setAllianceName(
        session: PlayerActor, name: String, nameType: Int, homePlayerDC: HomePlayerDC,
        vipDC: VipDC, skinDC: SkinDC, friendDC: FriendDC
    ): SetAllianceNameRt? {
        val rt = SetAllianceNameRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.name = name
        rt.setType = nameType

        val player = homePlayerDC.player

        if (player.allianceId == 0L) {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }

        var cost = LinkedList<ResVo>()
        if (nameType == SET_ALLIANCE_NAME) {
            // 联盟名称验证
            if (name.length <= 1) {
                rt.rt = (ResultCode.ALLIANCE_NAME_ERROR.code)
                return rt.build()
            }

            if (name[0] == ' ' || name.last() == ' ') {
                rt.rt = (ResultCode.ALLIANCE_NAME_ERROR.code)
                return rt.build()
            }

            val res1 =
                pcs.wordCache.check(
                    name,
                    pcs.basicProtoCache.allianceNameLength,
                    com.point18.slg2d.common.pc.WORD_CHECK_ALLIANCE_NAME
                )
            when (res1.wordCheckResult) {
                WORD_CHECK_RESULT_FORBIDDEN -> {
                    rt.rt = (ResultCode.ALLIANCE_NAME_ERROR.code)
                    return rt.build()
                }

                WORD_CHECK_RESULT_LENGTH_SHORT -> {
                    rt.rt = (ResultCode.ALLIANCE_NAME_LENGTH_NOT_ENOUGH.code)
                    return rt.build()
                }

                WORD_CHECK_RESULT_LENGTH_EXCEED -> {
                    rt.rt = (ResultCode.ALLIANCE_NAME_LENGTH_EXCEED.code)
                    return rt.build()
                }

            }
            cost = LinkedList(pcs.basicProtoCache.lmmcTalentReset)
        } else if (nameType == SET_ALLIANCE_SHORT_NAME) {
            // 联盟简称 验证
            if (name.length <= 1) {
                rt.rt = (ResultCode.ALLIANCE_NAME_ERROR.code)
                return rt.build()
            }

            if (name[0] == ' ' || name.last() == ' ') {
                rt.rt = (ResultCode.ALLIANCE_NAME_ERROR.code)
                return rt.build()
            }

            val res2 = pcs.wordCache.check(
                name,
                pcs.basicProtoCache.allianceShortNameLength,
                WORD_CHECK_SHORT_ALLIANCE_NAME
            )
            when (res2.wordCheckResult) {
                WORD_CHECK_RESULT_FORBIDDEN -> {
                    rt.rt = (ResultCode.ALLIANCE_SHORT_NAME_NOT_ALLOWED.code)
                    return rt.build()
                }

                WORD_CHECK_RESULT_LENGTH_EXCEED -> {
                    rt.rt = (ResultCode.ALLIANCE_SHORT_NAME_LENGTH_EXCEED.code)
                    return rt.build()
                }

            }

            cost = LinkedList(pcs.basicProtoCache.lmjcTalentReset)
        } else if (nameType == SET_ALLIANCE_LAN) {

        } else {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        var costResWithoutNoticeResult: CostResWithoutNoticeResult? = null
        if (nameType != SET_ALLIANCE_LAN) {
            // 验证资源
            val checkCost = resHelper.checkRes(session, cost)
            if (!checkCost) {
                if (cost.size > 0) {
                    val needRes = props2GoldCost(cost[0])
                    val tmp = needRes.listOfResVo
                    if (needRes.res != ResultCode.SUCCESS) {
                        rt.rt = needRes.res.code
                        return rt.build()
                    }

                    //校验需要的资源
                    if (!resHelper.checkRes(session, needRes.listOfResVo)) {
                        rt.rt = ResultCode.LESS_RESOUCE.code
                        return rt.build()
                    }

                    cost = tmp

                } else {
                    rt.rt = ResultCode.LESS_RESOUCE.code
                    return rt.build()
                }
            }

            // 扣除消耗
            costResWithoutNoticeResult =
                    resHelper.costResWithoutNotice(
                        session,
                        ACTION_ALLIANCE_SET_NAME,
                        player,
                        cost
                    )
        }

        if (nameType != SET_ALLIANCE_LAN && costResWithoutNoticeResult == null) {
            rt.rt = (ResultCode.LESS_RESOUCE.code)
            return rt.build()
        }

        // 然后发送到公共服去检测是否有重复的
        changeName(
            session,
            player,
            name,
            nameType,
            player.allianceId,
            cost,
            resHelper,
            costResWithoutNoticeResult
        )
        if (nameType == SET_ALLIANCE_SHORT_NAME) {
            // 告诉好友
            val vipLv = vipDC.getVipLv()
            var skinType = 1
            val skins = skinDC.findSkinsByPlayerId()
            for (skin in skins) {
                if (skin.isUse == 1) {
                    skinType = skin.skinType
                    break
                }
            }
            val tellHomeMsg = FriendRefreshNoticeTell.newBuilder()
            tellHomeMsg.myPlayerId = player.playerId
            tellHomeMsg.photoProtoId = player.photoProtoId
            tellHomeMsg.name = player.name
            tellHomeMsg.areaNo = player.areaNo
            tellHomeMsg.vipLv = vipLv
            tellHomeMsg.allianceShortName = name
            tellHomeMsg.state = 0
            tellHomeMsg.castleLv = player.castleLv
            tellHomeMsg.skinType = skinType
            tellHomeMsg.shortName = player.allianceNickName
            val myFriends = friendDC.findFriendById()

            for (friend in myFriends) {
                val home2homeTell = session.fillHome2HomeTellMsgHeader(
                    friend.tarPlayerId
                ) { it.setFriendRefreshNoticeTell(tellHomeMsg) }
                session.tellHome(home2homeTell)
            }
        }
        return null
    }

    // 修改联盟名称/检测简称
    private fun changeName(
        session: PlayerActor,
        player: HomePlayer,
        name: String,
        nameType: Int,
        allianceId: Long,
        cost: LinkedList<ResVo>,
        resHelper: ResHelper,
        costResWithoutNoticeResult: CostResWithoutNoticeResult?
    ) {
        val askMsg = SetAllianceNameAskReq.newBuilder()
        askMsg.setType = nameType
        askMsg.name = name

        // 如果修改的是语种,不需要前往中央服验证名字
        if (nameType == SET_ALLIANCE_LAN) {
            session.createACS<Home2PublicAskResp>().ask(
                session.publicShardProxy,
                session.fillHome2PublicAskMsgHeader(allianceId) {
                    it.setSetAllianceNameAskReq(askMsg)
                },
                Home2PublicAskResp::class.java
            ).whenCompleteKt { askResp, askErr ->
                val rt = SetAllianceNameRt.newBuilder()
                rt.rt = ResultCode.SUCCESS.code
                if (askErr != null || askResp == null) {
                    // todo 重试...
                    rt.rt = ResultCode.PROCESS_ERROR_RPC_EXCEPTION.code
                    resHelper.addResWithoutNotice(
                        session,
                        ACTION_ALLIANCE_SET_NAME,
                        player,
                        cost
                    )
                } else if (askResp.setAllianceNameAskRt.rt != ResultCode.SUCCESS.code) {
                    // todo 重试????? 由于W服的数据已经异常了,重试100次这个都是错的怎么办...
                    rt.rt = askResp.setAllianceNameAskRt.rt
                    resHelper.addResWithoutNotice(
                        session,
                        ACTION_ALLIANCE_SET_NAME,
                        player,
                        cost
                    )
                } else {
                    rt.setType = nameType
                    rt.name = name

                    if (costResWithoutNoticeResult != null) {
                        costResWithoutNoticeResult.noticeCostRes(session, player)
                    }
                }

                session.sendMsg(MsgType.AllianceSetName_898, rt.build())
            }
        } else {
            session.createACS<Home2PublicManagerAskResp>().ask(
                hpm.allianceManagerProxy,
                session.fillHome2PublicManagerAskMsgHeader {
                    it.setSetAllianceNameAskReq(askMsg)
                },
                Home2PublicManagerAskResp::class.java
            ).whenCompleteKt { askResp, askErr ->
                val rt = SetAllianceNameRt.newBuilder()
                rt.rt = ResultCode.SUCCESS.code
                if (askErr != null || askResp == null) {
                    rt.rt = ResultCode.PROCESS_ERROR_RPC_EXCEPTION.code
                    resHelper.addResWithoutNotice(
                        session,
                        ACTION_ALLIANCE_SET_NAME,
                        player,
                        cost
                    )

                    session.sendMsg(MsgType.AllianceSetName_898, rt.build())
                } else if (askResp.setAllianceNameAskRt.rt != ResultCode.SUCCESS.code) {
                    rt.rt = askResp.setAllianceNameAskRt.rt
                    resHelper.addResWithoutNotice(
                        session,
                        ACTION_ALLIANCE_SET_NAME,
                        player,
                        cost
                    )

                    session.sendMsg(MsgType.AllianceSetName_898, rt.build())
                } else {
                    session.createACS<Home2PublicAskResp>().ask(
                        session.publicShardProxy,
                        session.fillHome2PublicAskMsgHeader(allianceId) {
                            it.setSetAllianceNameAskReq(askMsg)
                        },
                        Home2PublicAskResp::class.java
                    ).whenCompleteKt { askResp, askErr ->
                        val rt = SetAllianceNameRt.newBuilder()
                        rt.rt = ResultCode.SUCCESS.code
                        if (askErr != null || askResp == null) {
                            // todo 重试...
                            rt.rt = ResultCode.PROCESS_ERROR_RPC_EXCEPTION.code
                            resHelper.addResWithoutNotice(
                                session,
                                ACTION_ALLIANCE_SET_NAME,
                                player,
                                cost
                            )
                        } else if (askResp.setAllianceNameAskRt.rt != ResultCode.SUCCESS.code) {
                            // todo 重试????? 由于W服的数据已经异常了,重试100次这个都是错的怎么办...
                            rt.rt = askResp.setAllianceNameAskRt.rt
                            resHelper.addResWithoutNotice(
                                session,
                                ACTION_ALLIANCE_SET_NAME,
                                player,
                                cost
                            )
                        } else {
                            rt.setType = nameType
                            rt.name = name

                            if (costResWithoutNoticeResult != null) {
                                costResWithoutNoticeResult.noticeCostRes(session, player)
                            }
                        }

                        session.sendMsg(MsgType.AllianceSetName_898, rt.build())
                    }
                }
            }
        }
    }
}



