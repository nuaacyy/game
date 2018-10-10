package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.publicentities.All_ALLIANCE_NAMED_QUERY
import com.point18.slg2d.common.publicentities.AllianceEntity
import com.point18.slg2d.public.datacache.*
import xyz.ariane.util.listNoDup
import pb4server.*
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*

class QueryAllianceListSyncOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.queryAllianceListAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMessage)

        resp.setQueryAllianceListAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: QueryAllianceListAskReq
    ): QueryAllianceListAskRt.Builder {
        val rt = newQueryAllianceListAskRtBuilder()

        val allCanAlliance = LinkedList<AllianceEntity>()

        var allAllianceInfoByDd = listOf<AllianceEntity>()
        publicCache.db.fetchDao().execWithTransaction { session ->
            allAllianceInfoByDd =
                    session.getNamedQuery(All_ALLIANCE_NAMED_QUERY)
                        .listNoDup()
        }

        if (req.allianceName != "") {
            val allCanAllianceByName = LinkedList<AllianceEntity>()
            val allCanAllianceByShortName = LinkedList<AllianceEntity>()
            for (alce in allAllianceInfoByDd) {
                if (req.allianceLan != 0 && req.allianceLan != alce.allianceLan) {
                    continue
                }
                if (alce.shortName.toUpperCase() == req.allianceName.toUpperCase()) {
                    allCanAllianceByShortName.add(alce)
                } else if (alce.name.toUpperCase().contains(req.allianceName.toUpperCase())) {
                    allCanAllianceByName.add(alce)
                }
            }

            // 排序
            allCanAllianceByName.sortWith(Comparator { a1, a2 ->
                if (a1.allianceMemberNum > a2.allianceMemberNum) {
                    -1
                } else if (a1.allianceMemberNum == a2.allianceMemberNum) {
                    0
                } else {
                    1
                }
            })

            allCanAllianceByShortName.sortWith(Comparator { a1, a2 ->
                if (a1.allianceMemberNum > a2.allianceMemberNum) {
                    -1
                } else if (a1.allianceMemberNum == a2.allianceMemberNum) {
                    0
                } else {
                    1
                }
            })

            allCanAlliance.addAll(allCanAllianceByName)
            allCanAlliance.addAll(allCanAllianceByShortName)

        } else {
            for (alce in allAllianceInfoByDd) {
                if (req.allianceLan != 0 && req.allianceLan != alce.allianceLan) {
                    continue
                }
                if (alce.allianceAreaNo != req.playerMapAreaNo) {
                    continue
                }

                allCanAlliance.add(alce)
            }

            // 排序
            allCanAlliance.sortWith(Comparator { a1, a2 ->
                if (a1.allianceMemberNum > a2.allianceMemberNum) {
                    -1
                } else if (a1.allianceMemberNum == a2.allianceMemberNum) {
                    0
                } else {
                    1
                }
            })
        }


        for (alce in allCanAlliance) {
            if (rt.alliancesBuilderList.size >= 50) {
                break
            }
            //查询是否存在请求
            var op = 0
            val reqVo =
                publicCache.allianceReqCache.findAllianceReqByAidWithPid(alce.id, playerId)
            if (reqVo != null) {
                op = 1
            }

            val allianceQueryListInfo = AllianceQueryListInfoVo.newBuilder()
            allianceQueryListInfo.id = alce.id
            allianceQueryListInfo.name = alce.name
            allianceQueryListInfo.shortName = alce.shortName
            allianceQueryListInfo.reservePlayers = alce.allianceMemberNum
            allianceQueryListInfo.powerValue = publicCache.allianceMemberCache.findAlliancesAllPower(
                alce.id
            ).toLong()
            allianceQueryListInfo.operate = op
            allianceQueryListInfo.allianceLan = alce.allianceLan
            allianceQueryListInfo.canAddPower = alce.canAddPower
            allianceQueryListInfo.canReqPower = alce.powerLimit
            allianceQueryListInfo.flagColor = alce.flagColor
            allianceQueryListInfo.flagStyle = alce.flagStyle
            allianceQueryListInfo.flagEffect = alce.flagEffect
            allianceQueryListInfo.alliancePower = alce.alliancePower
            allianceQueryListInfo.areaNo = alce.allianceAreaNo

            val giftVo = publicCache.allianceGiftCache.findAllianceGiftById(alce.id)
            if (giftVo != null) {
                allianceQueryListInfo.giftLv = giftVo.giftLv
            }

            rt.addAlliances(allianceQueryListInfo)
        }

        return rt
    }

    fun newQueryAllianceListAskRtBuilder(): QueryAllianceListAskRt.Builder {
        val rt = QueryAllianceListAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}