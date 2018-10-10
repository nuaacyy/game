package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.*
import java.util.*

class OpenAllianceCompetitionMainOp : Home2PublicAskDealBase() {
    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val internalMsg = req.openAllianceCompetitionMainAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMsg)
        // 设置结果
        resp.setOpenAllianceCompetitionMainAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: OpenAllianceCompetitionMainAskReq
    ): OpenAllianceCompetitionMainAskRt.Builder {
        val rt = newOpenAllianceCompetitionMainAskRtBuilder()

        val alce = publicCache.allianceCache.findAllianceById(allianceId)

        if (alce == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        if (alce.allianceCompetitionTicket == com.point18.slg2d.common.constg.NO_HAVE_ALLIANCE_COMPETITION_TICKET) {
            return rt
        }

        var allQuests =
            publicCache.allianceCompetitionQuestCache.findAllianceCompetitionQuestsByAllianceId(allianceId)

        // 检测是否有任务可以刷新出来
        // 先统计出来所有的正在存在的任务,做一份黑名单给刷新用
        val nowQuestId = mutableMapOf<Int, Int>()
        val needRefIndex = LinkedList<Int>() // 要刷的index
        for (quest in allQuests) {
            if (quest.questId == 0 && quest.refTime < getNowTime()) {
                needRefIndex.add(quest.index)
                continue
            }
            nowQuestId[quest.questId] = 1
        }

        if (needRefIndex.size != 0) {
            val luckQuests =
                com.point18.slg2d.common.pc.pcs.allianceCompetitionQuestProtoCache.refreshAllianceCompetition(
                    needRefIndex.size,
                    nowQuestId
                )

            var i = 0
            for ((_, luckQuest) in luckQuests) {
                val q = publicCache.allianceCompetitionQuestCache.findAllianceCompetitionQuestByAllianceIdAndIndex(
                    allianceId,
                    needRefIndex[i]
                )
                if (q != null) {
                    q.refTime = 0
                    q.questId = luckQuest.id
                    i++
                }
            }
        }

        allQuests =
                publicCache.allianceCompetitionQuestCache.findAllianceCompetitionQuestsByAllianceId(allianceId)

        for (quest in allQuests) {
            val q = AllianceCompetitionQuestVo.newBuilder()
            q.index = quest.index
            q.questId = quest.questId
            q.refTime = (quest.refTime / 1000).toInt()
            rt.addQuests(q)
        }

        rt.nowScore = alce.allianceCompetitionScore
        val alliancemembers =
            publicCache.allianceMemberCache.findAllianceMembersByAllianceId(allianceId)
        var joinNum = 0
        for (member in alliancemembers) {
            if (member.allianceCompetitionTicket == com.point18.slg2d.common.constg.HAVE_ALLIANCE_COMPETITION_TICKET) {
                joinNum += 1
            }
        }

        rt.joinPlayerNum = joinNum
        rt.rewardTime = 0

        return rt
    }

    fun newOpenAllianceCompetitionMainAskRtBuilder(): OpenAllianceCompetitionMainAskRt.Builder {
        val rt = OpenAllianceCompetitionMainAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.nowScore = 0
        rt.joinPlayerNum = 0
        rt.rewardTime = 0
        return rt
    }
}