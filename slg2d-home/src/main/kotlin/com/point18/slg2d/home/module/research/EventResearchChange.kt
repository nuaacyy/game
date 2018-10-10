package com.point18.slg2d.home.module.research

import com.point18.slg2d.common.constg.AllResYield
import com.point18.slg2d.common.constg.RESEARCH_EFFECT
import com.point18.slg2d.common.constg.ResearchStrength
import com.point18.slg2d.common.constg.isRefreshResAtOnce
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.IEventHandler
import com.point18.slg2d.home.module.event.ResearchEffectChangeEvent

class ResearchEffectChangeEventHandler : IEventHandler<ResearchEffectChangeEvent>,
    HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun handleEvent(session: PlayerActor, event: ResearchEffectChangeEvent) {
        prepare(session) { homePlayerDC: HomePlayerDC ->
            val changeEffect = event.changeEffect
            val targetHelper = event.targetHelper
            val player = homePlayerDC.player
            val effectHelper = event.effectHelper
            val refreshRes = event.refreshRes

            var isRefRes = false

            // 刷新研发实力统计
            targetHelper.targetAddVal(session, ResearchStrength, null)

            val updateMap = hashMapOf<Int, Int>()
            for ((researchEffectType, _) in changeEffect) {
                // 科技加成
                val effectMap = pcs.researchCache.researchProtoMapByEffectType
                val effectResearchMap = effectMap[researchEffectType] // 找到所有有加成这个效果类型的科技
                var allValue = 0
                val playerResearchInfo = player.researchInfoMap

                if (effectResearchMap != null) {
                    for ((effectResearchId, _) in effectResearchMap) {
                        val playerResearch = playerResearchInfo[effectResearchId] ?: continue
                        val value =
                            pcs.researchCache.getEffValue(
                                effectResearchId,
                                playerResearch.researchLv,
                                researchEffectType
                            )
                        allValue += value
                    }
                }

                player.putResearchEffectInfoMap(researchEffectType, allValue)
                if (isRefreshResAtOnce(researchEffectType)) {
                    isRefRes = true
                }

                updateMap[researchEffectType] = allValue
            }


            if (isRefRes) {
                refreshRes.refreshResource(session, AllResYield)
            }

            effectHelper.syncEffect2World(session, RESEARCH_EFFECT)
        }

    }
}