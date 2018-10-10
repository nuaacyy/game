package com.point18.slg2d.home.module.talent

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.*
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.TalentLvChangeEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createTalentPointChangeNotifier
import pb4client.UpgradeTalent
import pb4client.UpgradeTalentRt
import java.util.*

class UpgradeTalentDeal(
    private val refreshResourceHelper: RefreshResourceHelper = RefreshResourceHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus1<HomePlayerDC>(
        HomePlayerDC::class.java,
        Arrays.asList(refreshResourceHelper, effectHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val unlockTalentMsg = msg as UpgradeTalent
        prepare(session) { homePlayerDC ->
            val rt = unlockTalent(
                session,
                unlockTalentMsg.talentId,
                unlockTalentMsg.targetTalentLevel,
                homePlayerDC
            )
            session.sendMsg(MsgType.UnlockTalent_1211, rt)
        }
    }

    fun unlockTalent(
        session: PlayerActor,
        talentId: Int,
        targetLevel: Int,
        homePlayerDC: HomePlayerDC
    ): UpgradeTalentRt {
        val rt = UpgradeTalentRt.newBuilder()
        rt.rt = com.point18.slg2d.common.resultcode.ResultCode.SUCCESS.code
        rt.talentId = talentId
        rt.targetTalentLevel = targetLevel

        //查找自己
        val player = homePlayerDC.player

        //检查天赋配置
        val talents = pcs.talentCache.talentIdMap[talentId]
        if (talents == null) {
            //天赋配置不存在
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        val talent = talents[targetLevel]
        if (talent == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        //检查前置天赋条件
        val zeroTalent = talents[1]
        if (zeroTalent == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        if (zeroTalent.condition.isNotEmpty()) {
            var meetCondition = false

            for ((condKey, condVal) in zeroTalent.condition) {

                if (condVal <= 0) {
                    continue
                }
                val tLv = player.unlockedTalentMap[condKey]

                if (tLv != null && tLv >= condVal) {
                    meetCondition = true
                    break
                }
            }

            if (!meetCondition) {
                rt.rt = (ResultCode.TALENT_PRECONDITION_DISSATIFY.code)
                return rt.build()
            }
        }

        //检测天赋等级
        val currentTalentLevel = player.unlockedTalentMap[talent.talentId] ?: 0
        val differLv = targetLevel - currentTalentLevel

        if (differLv <= 0) {
            //当前等级已满足升级要求
            rt.targetTalentLevel = currentTalentLevel
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        //计算所需花费
        val cost = hashMapOf<Int, Int>()
        for (lv in (currentTalentLevel + 1)..targetLevel) {
            val differTalent = talents[lv]

            if (differTalent == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt.build()
            }

            for ((costType, costVal) in differTalent.cost) {
                val tmpCost = cost[costType] ?: 0
                cost[costType] = costVal + tmpCost
            }
        }

        //检查天赋点数
        for ((costType, costVal) in cost) {
            val currentPoint = player.talentPointMap[costType]
            if (currentPoint == null) {
                rt.rt = ResultCode.LESS_TALENT_POINT.code
                return rt.build()
            }
            if (currentPoint < costVal) {
                rt.rt = ResultCode.LESS_TALENT_POINT.code
                return rt.build()
            }
        }

        //扣除天赋点
        for ((costType, costVal) in cost) {
            val tmp = player.talentPointMap[costType]
            if (tmp == null) {
                rt.rt = ResultCode.LESS_TALENT_POINT.code
                return rt.build()
            }

            player.talentPointMap[costType] = tmp - costVal
        }

        player.unlockedTalentMap[talent.talentId] = talent.level

        //重算天赋效果
        for ((id, level) in player.unlockedTalentMap) {
            val lvTalents = pcs.talentCache.talentIdMap[id]

            if (lvTalents == null) {
                continue
            }

            for ((_, idTalent) in lvTalents) {

                if (idTalent.level != level) {
                    continue
                }

                for ((effKey, effVal) in idTalent.effect) {
                    player.talentEffectInfoMap[effKey] = (player.talentEffectInfoMap[effKey] ?: 0) + effVal
                }
                break
            }
        }

        //发送天赋等级变化事件
        fireEvent(session, TalentLvChangeEvent(refreshResourceHelper, effectHelper))

        //通知天赋点变化
        createTalentPointChangeNotifier(player.talentPointMap).notice(session)

        return rt.build()
    }
}


