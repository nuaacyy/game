package com.point18.slg2d.world.gmsg

import com.point18.slg2d.world.AllianceRankInfoVo
import com.point18.slg2d.world.PublicManager2WorldManagerTellDealBase
import com.point18.slg2d.world.wpm
import pb4server.PublicManager2WorldManagerTell

class AllianceSimpleInfoChangeTellDeal : PublicManager2WorldManagerTellDealBase() {
    override fun dealPublicManagerTell(msg: PublicManager2WorldManagerTell) {
        val tell = msg.allianceSimpleInfoChangeTell

        // 排行数据
        if (tell.allianceRankInfoList.size != 0) {
            val rankInfo = hashMapOf<Int, MutableList<AllianceRankInfoVo>>()
            for (ri in tell.allianceRankInfoList) {
                for (r in ri.allianceRankInfoList) {
                    rankInfo.getOrPut(ri.rankType) { mutableListOf() }.add(
                        AllianceRankInfoVo(
                            r.allianceName, r.allianceShortName, r.allianceId,
                            r.flagColor, r.flagStyle, r.flagEffect, r.value, r.extend1
                        )
                    )
                }
            }

            wpm.getWorldManagerInfoFromPublicManager().allianceRankInfo = rankInfo
        }


        // 联盟占领世界情况
        if (tell.allianceOccupyInfo.allianceId != 0L) {
            val occWorldIds = mutableMapOf<Long, Int>() // 把publicM服传过来的该变化联盟占领的world 从list格式转成map 方便使用
            for (worldId in tell.allianceOccupyInfo.worldIdList) {
                occWorldIds[worldId] = 1
            }
            for ((worldId, occVo) in wpm.getWorldManagerInfoFromPublicManager().allServerInfo) {
                if (occVo.allianceName != tell.allianceOccupyInfo.allianceName) {
                    continue
                }

                // 当前是我占领的现在不归我了
                if (occWorldIds[worldId] == null) {
                    occVo.allianceAreaId = 0
                    occVo.allianceName = ""
                    occVo.allianceShortName = ""
                    occVo.kingName = ""
                }
            }


            for (worldId in tell.allianceOccupyInfo.worldIdList) {
                val serverInfo = wpm.getWorldManagerInfoFromPublicManager().allServerInfo[worldId]
                if (serverInfo == null) {
                    continue
                }

                // 当前仍然是我的 但是可能有数据更新 名字变化 帮主变化什么的  或者是 本来不是我的,现在归我了 都这么执行
                serverInfo.allianceAreaId = tell.allianceOccupyInfo.allianceAreaNo
                serverInfo.allianceName = tell.allianceOccupyInfo.allianceName
                serverInfo.allianceShortName = tell.allianceOccupyInfo.allianceShortName
                serverInfo.kingName = tell.allianceOccupyInfo.allianceMainMemberName

            }
        }
    }
}

