package com.point18.slg2d.home.module.research

import com.point18.slg2d.common.constg.NO_FILTER
import com.point18.slg2d.common.constg.RESEARCH_UP_SETTING
import com.point18.slg2d.common.constg.ResearchCount
import com.point18.slg2d.common.constg.ResearchEffectWalkQueueAdd
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.*
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.ResearchVo
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.IHeartDeal
import com.point18.slg2d.home.module.event.ResearchEffectChangeEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createResearchChangeNotifier
import java.util.*
import java.util.Arrays.asList

/**
科技研发的心跳.
 */
class ResearchHeart(
    private val targetHelper: TargetHelper = TargetHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper(),
    private val refreshResHelper: RefreshResourceHelper = RefreshResourceHelper(),
    private val appNoticeHelper: AppNoticeHelper = AppNoticeHelper()
) : IHeartDeal, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java,
    asList(targetHelper, effectHelper, refreshResHelper, appNoticeHelper)
) {

    override fun dealHeart(session: PlayerActor, actionId: Long, onComplete: (rt: Int) -> Unit) {
        prepare(session) { homePlayerDC: HomePlayerDC ->
            val rt = researchLvUp(session, actionId, homePlayerDC)
            onComplete(rt)
        }
    }

    private fun researchLvUp(session: PlayerActor, actionId: Long, playerDC: HomePlayerDC): Int {
        // 找到正在升级中的那个完成的科技
        var research: ResearchVo? = null
        val player = playerDC.player
        for ((k, v) in player.researchInfoMap) {
            if (k == actionId.toInt()) {
                research = v
            }
        }

        if (research == null) {
            return ResultCode.PARAMETER_ERROR.code
        }

        val nowResearchProto0 = pcs.researchCache.researchProtoMapByLv[actionId.toInt()]
        if (null == nowResearchProto0) {
            return ResultCode.PARAMETER_ERROR.code
        }
        val nowResearchProto = nowResearchProto0[research.researchLv + 1]
        if (null == nowResearchProto) {
            return ResultCode.PARAMETER_ERROR.code
        }

        if (research.helpId != 0.toLong()) {
            // 如果在帮助列表中的,要做处理
            removeAllianceHelp(session, player.allianceId, research.helpId)
        }

        research.researchLv = research.researchLv + 1
        research.helpId = 0
        player.researchUpdate(
            session,
            actionId.toInt(),
            research,
            0L,
            0L
        )
        research.helperIds = hashMapOf()
        player.putResearchInfoMap(actionId.toInt(), research)

        //添加统计
        targetHelper.targetAddVal(
            session,
            ResearchCount,
            LinkedList(asList(1))
        )

        // 应用通知推送
        appNoticeHelper.pushAppNotice(
            session,
            RESEARCH_UP_SETTING,
            0,
            nowResearchProto.name
        )

        // 效果生效
        fireEvent(
            session,
            ResearchEffectChangeEvent(nowResearchProto.effectMap, targetHelper, effectHelper, refreshResHelper)
        )

        // 推送给客户端变更
        val notice = createResearchChangeNotifier(
            actionId.toInt(),
            research.researchLv,
            research.researchOverTime,
            research.helpId
        )
        notice.notice(session)


        for ((effType, _) in nowResearchProto.effectMap) {

            if (effType == ResearchEffectWalkQueueAdd) {
                val addForceNum = effectHelper.getResearchEffectValue(
                    session,
                    NO_FILTER,
                    ResearchEffectWalkQueueAdd
                ) // 新增部队上限

                if (addForceNum != 0) {

                }
            }
        }

        return ResultCode.SUCCESS.code
    }
}