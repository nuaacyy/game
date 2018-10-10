package com.point18.slg2d.home.module.talent

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.*
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.TalentResetEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createTalentPointChangeNotifier
import pb4client.ResetTalentPoint
import pb4client.ResetTalentPointRt
import java.util.*

class ResetTalentPointDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val refreshResourceHelper: RefreshResourceHelper = RefreshResourceHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus1<HomePlayerDC>(
        HomePlayerDC::class.java,
        Arrays.asList(
            resHelper,
            refreshResourceHelper,
            effectHelper
        )
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val unlockTalentMsg = (msg as ResetTalentPoint)
        prepare(session) { homePlayerDC ->
            val rt = resetTalentPoint(
                session,
                unlockTalentMsg.talentType,
                unlockTalentMsg.forceUse,
                homePlayerDC
            )
            session.sendMsg(MsgType.ResetTalentPoint_1212, rt)
        }
    }

    private fun resetTalentPoint(
        session: PlayerActor,
        talentType: Int,
        forceUse: Int,
        homePlayerDC: HomePlayerDC
    ): ResetTalentPointRt {
        val rt = ResetTalentPointRt.newBuilder()
        rt.rt = com.point18.slg2d.common.resultcode.ResultCode.SUCCESS.code
        rt.talentType = talentType

        //查找自己
        val player = homePlayerDC.player

        //检查类别
        val cost: LinkedList<ResVo> = LinkedList()

        if (talentType == MilitaryTalent) {
            cost += pcs.basicProtoCache.militaryTalentResetCost

        } else if (talentType == EconomyTalent) {
            cost += pcs.basicProtoCache.economyTalentResetCost

        } else if (talentType == MonsterTalent) {
            cost += pcs.basicProtoCache.monsterTalentResetCost

        } else {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }
        //检查天赋重置道具

        if (!resHelper.checkRes(session, cost)) {

            if (cost.size > 0) {
                val (ok, needRes) = com.point18.slg2d.common.pc.props2GoldCost(cost[0])
                if (ok != ResultCode.SUCCESS) {
                    rt.rt = (ok.code)
                    return rt.build()
                }

                //校验需要的资源
                if (!resHelper.checkRes(session, needRes)) {
                    rt.rt = (ResultCode.LESS_RESOUCE.code)
                    return rt.build()
                }
                //扣除资源
                resHelper.costRes(session, ACTION_RESET_TALENT, player, needRes)
            } else {
                rt.rt = (ResultCode.LESS_RESOUCE.code)
                return rt.build()
            }
        } else {
            //扣除天赋重置道具
            resHelper.costRes(session, ACTION_USE_TALENT_PLAN, player, cost)
        }
        //重算天赋点
        val kingExpProto = pcs.kingExpCache.kingExpMap[player.kingLv]
        if (kingExpProto == null) {
            rt.rt = (ResultCode.NO_PROTO.code)
            return rt.build()
        }
        if (talentType == MilitaryTalent) {
            player.talentPointMap[MilitaryTalent] = kingExpProto.militaryTalent

        } else if (talentType == EconomyTalent) {
            player.talentPointMap[EconomyTalent] = kingExpProto.economicsTalent

        } else if (talentType == MonsterTalent) {
            player.talentPointMap[MonsterTalent] = kingExpProto.monsterTalent
        }

        // 重算解锁天赋、天赋效果
        val newUnlockMap = player.unlockedTalentMap
        val newEffectMap = hashMapOf<Int, Int>()

        val listOfReset = LinkedList<Int>()
        for ((id, level) in player.unlockedTalentMap) {
            val lvTalents = pcs.talentCache.talentIdMap[id]
            if (lvTalents == null) {
                continue
            }

            for ((lvProto, proto) in lvTalents) {

                if (proto.talentType == talentType) {
                    listOfReset.add(proto.talentId)
                }

                if (proto.level != level) {
                    continue
                }
                for ((effKey, effVal) in proto.effect) {
                    newEffectMap[effKey] = (newEffectMap[effKey] ?: 0) + effVal
                }
            }

        }

        for (eachTalentId in listOfReset) {
            newUnlockMap.remove(eachTalentId)
        }

        player.unlockedTalentMap = newUnlockMap
        player.talentEffectInfoMap = newEffectMap

        // 重新计算天赋效果
        fireEvent(
            session, TalentResetEvent(
                refreshResourceHelper, effectHelper
            )
        )

        // 通知天赋点变化
        createTalentPointChangeNotifier(player.talentPointMap).notice(session)

        // 加入日志
        return rt.build()
    }

}