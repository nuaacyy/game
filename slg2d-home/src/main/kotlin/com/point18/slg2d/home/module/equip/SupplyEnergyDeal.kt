package com.point18.slg2d.home.module.equip

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.generateDecree
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.NewEquipDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.SupplyEnergyRt
import pb4server.AddDecreeAskReq
import pb4server.Home2WorldAskResp
import java.util.*
import java.util.Arrays.asList

//补充体力
class SupplyEnergyDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus2<HomePlayerDC, NewEquipDC>(
    HomePlayerDC::class.java, NewEquipDC::class.java,
    asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, newEquipDC: NewEquipDC ->
            val player = homePlayerDC.player
            generateDecree(player)
            var currentDecree = player.decree
            val allDegree =
                newEquipDC.findAllPropByPlayerIdAndMainType(PROP_RES, ZhenglingBag)
            allDegree.sortWith(Comparator { a, b ->
                val protoA = pcs.equipCache.equipProtoMap[a.equipProtoId]
                if (protoA == null || protoA.extend1.toIntOrNull() == null) {
                    return@Comparator 1
                }
                val protoB = pcs.equipCache.equipProtoMap[b.equipProtoId]
                if (protoB == null || protoB.extend1.toIntOrNull() == null) {
                    return@Comparator -1
                }
                return@Comparator protoA.extend1.toInt() - protoB.extend1.toInt()
            })
            val costRes = LinkedList<ResVo>()
            var addDecree = 0
            for (decreeProp in allDegree) {
                if (currentDecree >= player.decreeLimit) {
                    break
                }
                val decreeProto = pcs.equipCache.equipProtoMap[decreeProp.equipProtoId]
                if (decreeProto == null) {
                    continue
                }
                var num = 0L
                for (i in 1..decreeProp.haveNum) {
                    if (currentDecree >= player.decreeLimit) {
                        break
                    }
                    decreeProto.useGetMap.forEach { resVo ->
                        assert(resVo.resType == RES_DECREE)
                        currentDecree += resVo.num.toInt()
                        addDecree += resVo.num.toInt()
                    }
                    num++
                }
                if (num > 0) {
                    costRes += ResVo(RES_PROPS, decreeProp.equipProtoId.toLong(), num)
                }
            }

            if (costRes.isEmpty()) {
                returnMsg(session, ResultCode.LESS_RESOUCE.code)
                return@prepare
            }

            val action = ACTION_USE_PROPS

            val costRt =
                resHelper.costResWithoutNotice(session, action, player, costRes)

            val askMsg = AddDecreeAskReq.newBuilder()
            askMsg.addNum = addDecree
            askMsg.useProp = boolToInt(true)
            session.createACS<Home2WorldAskResp>().ask(
                session.worldShardProxy,
                session.fillHome2WorldAskMsgHeader { it.setAddDecreeAskReq(askMsg) },
                Home2WorldAskResp::class.java
            ).whenCompleteKt { askResp, askErr ->
                try {
                    when {
                        askErr != null -> {
                            resHelper.addResWithoutNotice(session, action, player, costRes)
                            returnMsg(session, ResultCode.ASK_ERROR1.code)
                            return@whenCompleteKt
                        }
                        askResp == null -> {
                            resHelper.addResWithoutNotice(session, action, player, costRes)
                            returnMsg(session, ResultCode.ASK_ERROR2.code)
                            return@whenCompleteKt
                        }
                        else -> {
                            if (askResp.addDecreeAskRt.rt != ResultCode.SUCCESS.code) {
                                resHelper.addResWithoutNotice(session, action, player, costRes)
                                returnMsg(session, askResp.addDecreeAskRt.rt)
                                return@whenCompleteKt
                            } else {
                                costRt.noticeCostRes(session, player)
                                returnMsg(session, ResultCode.SUCCESS.code)
                                return@whenCompleteKt
                            }
                        }
                    }

                } catch (e: Exception) {
                    normalLog.error("AddDecreeAskReq Error!", e)
                    resHelper.addResWithoutNotice(session, action, player, costRes)
                    returnMsg(session, ResultCode.ASK_ERROR3.code)
                    return@whenCompleteKt
                }
            }
        }
    }

    private fun returnMsg(session: PlayerActor, rt: Int) {
        val rtBuilder = SupplyEnergyRt.newBuilder()
        rtBuilder.rt = rt
        session.sendMsg(MsgType.SupplyEnergy_1494, rtBuilder.build())
    }
}