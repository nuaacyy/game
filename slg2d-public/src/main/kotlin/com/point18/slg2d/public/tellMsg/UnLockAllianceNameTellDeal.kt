package com.point18.slg2d.public.tellMsg

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.public.datacache.PublicManagerCache
import com.point18.slg2d.public.pmodule.Public2PublicManagerTellDealBase
import com.point18.slg2d.public.ppm
import pb4server.AllianceNameChangeTell
import pb4server.AllianceNameChangeVo
import pb4server.Public2PublicManagerTell
import xyz.ariane.util.tellNoSender

// 修改联盟公共中央节点的名字暂用情况
class UnLockAllianceNameTellDeal : Public2PublicManagerTellDealBase() {
    override fun dealPublicTell(publicCache: PublicManagerCache, msg: Public2PublicManagerTell) {
        val tellMsg = msg.unLockAllianceNameTell
        dealUnLockAllianceNameTell(publicCache, tellMsg.changeType, tellMsg.allianceName, tellMsg.allianceShortName)
    }

    private fun dealUnLockAllianceNameTell(
        publicCache: PublicManagerCache,
        changeType: Int,
        name: String,
        shortName: String
    ) {

        val allianceNameChangeVos = AllianceNameChangeTell.newBuilder()

        if (name != "") {
            val nMap = publicCache.allianceCache.nowUseNameMap[name]
            if (nMap != null) {
                if (changeType == ALLIANCE_NAME_TRY2USE) {
                    nMap.state = ALLIANCE_NAME_IN_USE
                    nMap.overTime = 0

                    val allianceNameChangeVo = AllianceNameChangeVo.newBuilder()
                    allianceNameChangeVo.changeType = 1
                    allianceNameChangeVo.nameType = SET_ALLIANCE_NAME
                    allianceNameChangeVo.name = name
                    allianceNameChangeVos.addAllianceNameChangeVos(allianceNameChangeVo.build())

                } else if (changeType == ALLIANCE_NAME_DEL_USE) {
                    publicCache.allianceCache.nowUseNameMap.remove(name)

                    val allianceNameChangeVo = AllianceNameChangeVo.newBuilder()
                    allianceNameChangeVo.changeType = 2
                    allianceNameChangeVo.nameType = SET_ALLIANCE_NAME
                    allianceNameChangeVo.name = name
                    allianceNameChangeVos.addAllianceNameChangeVos(allianceNameChangeVo.build())
                }
            }
        }

        if (shortName != "") {
            val snMap = publicCache.allianceCache.nowUseShortNameMap[shortName]
            if (snMap != null) {
                if (changeType == ALLIANCE_NAME_TRY2USE) {
                    snMap.state = ALLIANCE_NAME_IN_USE
                    snMap.overTime = 0

                    val allianceNameChangeVo = AllianceNameChangeVo.newBuilder()
                    allianceNameChangeVo.changeType = 1
                    allianceNameChangeVo.nameType = SET_ALLIANCE_SHORT_NAME
                    allianceNameChangeVo.name = shortName
                    allianceNameChangeVos.addAllianceNameChangeVos(allianceNameChangeVo.build())
                } else if (changeType == ALLIANCE_NAME_DEL_USE) {
                    publicCache.allianceCache.nowUseShortNameMap.remove(shortName)
                    val allianceNameChangeVo = AllianceNameChangeVo.newBuilder()
                    allianceNameChangeVo.changeType = 2
                    allianceNameChangeVo.nameType = SET_ALLIANCE_SHORT_NAME
                    allianceNameChangeVo.name = shortName
                    allianceNameChangeVos.addAllianceNameChangeVos(allianceNameChangeVo.build())
                }
            }
        }

        publicCache.allianceCache.nowSycnNameVersion++
        allianceNameChangeVos.nowSycnNameVersion = publicCache.allianceCache.nowSycnNameVersion
        ppm.p2wSyncInfoRouter.tellNoSender(
            ppm.fillPublicManager2WorldManagerTellMsgHeader { it.setAllianceNameChangeTell(allianceNameChangeVos) }
        )

    }
}