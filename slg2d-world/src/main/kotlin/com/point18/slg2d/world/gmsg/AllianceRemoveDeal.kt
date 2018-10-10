package com.point18.slg2d.world.gmsg

import com.point18.slg2d.world.PublicManager2WorldManagerTellDealBase
import com.point18.slg2d.world.wpm
import pb4server.PublicManager2WorldManagerTell

class AllianceRemoveDeal : PublicManager2WorldManagerTellDealBase() {
    override fun dealPublicManagerTell(msg: PublicManager2WorldManagerTell) {
        val removeAllianceId = msg.allianceRemoveTell.allianceId // 解散的联盟ID
        val allianceName = msg.allianceRemoveTell.allianceName // 解散的联盟ID
        val allianceShortName = msg.allianceRemoveTell.allianceShortName // 解散的联盟ID
        val info = wpm.getWorldManagerInfoFromPublicManager()
        info.useAllianceName.remove(allianceName)
        info.useAllianceShortName.remove(allianceShortName)

        var removeIndex = -1
        for ((_, ranks) in info.allianceRankInfo) {
            for (index in ranks.indices) {
                if (ranks[index].allianceId == removeAllianceId) {
                    removeIndex = index
                    break
                }
            }

            if (removeIndex != -1) {
                ranks.removeAt(removeIndex)
            }
        }
    }
}

