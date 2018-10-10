package com.point18.slg2d.world.gmsg

import com.point18.slg2d.common.constg.SET_ALLIANCE_NAME
import com.point18.slg2d.common.constg.SET_ALLIANCE_SHORT_NAME
import com.point18.slg2d.world.PublicManager2WorldManagerTellDealBase
import com.point18.slg2d.world.wpm
import pb4server.PublicManager2WorldManagerTell

class AllianceNameChangeDeal : PublicManager2WorldManagerTellDealBase() {
    override fun dealPublicManagerTell(msg: PublicManager2WorldManagerTell) {
        val tell = msg.allianceNameChangeTell

        if (tell.nowSycnNameVersion <= wpm.getWorldManagerInfoFromPublicManager().syncVersion) {
            // 如果公共服发过来的版本号比我的要小 忽略这一条
            // todo 如果期间公共服被重启,当前版本号未记库 会重置成0 需要维护
            return
        }
        for (vo in tell.allianceNameChangeVosList) {
            if (vo.changeType == 1) {
                // 新增元素
                if (vo.nameType == SET_ALLIANCE_NAME) {
                    wpm.getWorldManagerInfoFromPublicManager().useAllianceName[vo.name] = 1
                } else if (vo.nameType == SET_ALLIANCE_SHORT_NAME) {
                    wpm.getWorldManagerInfoFromPublicManager().useAllianceShortName[vo.name] = 1
                }
            } else if (vo.changeType == 2) {
                // 删除元素
                if (vo.nameType == SET_ALLIANCE_NAME) {
                    wpm.getWorldManagerInfoFromPublicManager().useAllianceName.remove(vo.name)
                } else if (vo.nameType == SET_ALLIANCE_SHORT_NAME) {
                    wpm.getWorldManagerInfoFromPublicManager().useAllianceShortName.remove(vo.name)
                }
            }
        }

    }
}

