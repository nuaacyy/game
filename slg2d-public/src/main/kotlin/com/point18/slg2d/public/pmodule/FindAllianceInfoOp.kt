package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.datacache.CacheAllianceManager
import com.point18.slg2d.public.datacache.PublicManagerCache
import pb4server.*

// 世界来联盟管理服获取一些需要的数据
class FindAllianceInfoOp : WorldManager2PublicManagerAskDealBase() {
    override fun dealWorldAsk(
        publicCache: PublicManagerCache,
        req: WorldManager2PublicManagerAskReq,
        resp: WorldManager2PublicManagerAskResp.Builder
    ) {
        val internalMsg = req.findUseAllianceNamesReq
        val rt = dealMsg(publicCache, internalMsg)
        // 设置结果
        resp.setFindUseAllianceNamesRt(rt)
    }

    fun dealMsg(
        publicCache: PublicManagerCache,
        req: FindUseAllianceNamesReq
    ): FindUseAllianceNamesRt.Builder {
        val rt = newFindUseAllianceNamesRtBuilder()

        for ((name, vo) in publicCache.allianceCache.nowUseNameMap) {
            if (vo.overTime != 0L || vo.state != ALLIANCE_NAME_IN_USE) {
                continue
            }

            rt.addAllianceName(name)
        }

        for ((shortName, vo) in publicCache.allianceCache.nowUseShortNameMap) {
            if (vo.overTime != 0L || vo.state != ALLIANCE_NAME_IN_USE) {
                continue
            }

            rt.addAllianceShortName(shortName)
        }

        rt.nowSycnNameVersion = publicCache.allianceCache.nowSycnNameVersion

        rt.addAllAllianceRankInfo(makeAllianceRank(publicCache))

        // 联盟占领世界情况
        for ((allianceId, occVo) in publicCache.allianceCache.allianceOccupyInfos) {
            if (occVo.worldIds.size == 0) {
                continue
            }

            val occ = AllianceOccupyInfo.newBuilder()
            occ.allianceId = allianceId
            occ.allianceMainMemberName = occVo.allianceMainMemberName
            occ.allianceAreaNo = occVo.allianceAreaNo
            occ.allianceName = occVo.allianceName
            occ.allianceShortName = occVo.allianceShortName
            occ.addAllWorldId(occVo.worldIds)

            rt.addAllianceOccupyInfos(occ.build())
        }

        return rt
    }

    fun newFindUseAllianceNamesRtBuilder(): FindUseAllianceNamesRt.Builder {
        val rt = FindUseAllianceNamesRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}