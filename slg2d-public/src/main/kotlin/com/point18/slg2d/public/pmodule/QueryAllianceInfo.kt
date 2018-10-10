package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.constg.ALLIANCE_MAX_MEMBER
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.QueryAllianceInfoAskReq
import pb4server.QueryAllianceInfoAskRt
import pb4server.World2PublicAskReq
import pb4server.World2PublicAskResp

class QueryAllianceInfoSyncOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.queryAllianceInfoAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMessage)

        resp.setQueryAllianceInfoAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: QueryAllianceInfoAskReq
    ): QueryAllianceInfoAskRt.Builder {
        val rt = newQueryAllianceInfoAskRtBuilder()

        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        if (alce == null) {
            rt.rt = ResultCode.ALLIANCE_ARGS_ERROR.code
            return rt
        }

        // 获取当前联盟等级配置信息
        val mainAllianceMember =
            publicCache.allianceMemberCache.findAllianceMemberById(alce.mainPlayerId)

        if (mainAllianceMember == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        // 设置值
        rt.id = allianceId
        rt.name = alce.name
        rt.shortName = alce.shortName
        rt.alliancePlayerId = alce.mainPlayerId
        rt.playerName = mainAllianceMember.name
        val aPlayers = publicCache.allianceMemberCache.findAllianceMembersByAllianceId(allianceId)
        rt.reservePlayers = aPlayers.size
        rt.limitPlayers = ALLIANCE_MAX_MEMBER
        rt.powerValue = publicCache.allianceMemberCache.findAlliancesAllPower(alce.id)
        rt.woodConst = 0
        rt.ironConst = 0
        rt.stoneConst = 0
        rt.foodstuffConst = 0
        rt.description = alce.description
        rt.ctrbtWeek = 0
        rt.ctrbtTotal = 0
        rt.powerLimit = alce.powerLimit
        rt.canAddLimit = alce.canAddPower
        rt.flagColor = alce.flagColor
        rt.flagStyle = alce.flagStyle
        rt.flagEffect = alce.flagEffect
        rt.biaoyu = alce.slogan
        rt.allianceLan = alce.allianceLan
        rt.allianceInAreaNo = mainAllianceMember.mapAreaNo
        var occupyWonderCount = 0
        for ((_, wonderMap) in alce.allianceOccupyInfo) {
            occupyWonderCount += wonderMap.size
        }
        rt.occupyWonderCount = occupyWonderCount
        var isApplt = 0
        if (publicCache.allianceReqCache.findAllianceReqByAidWithPid(allianceId, playerId) != null) {
            isApplt = 1
        }
        rt.isApply = isApplt
        val giftVo = publicCache.allianceGiftCache.findAllianceGiftById(alce.id)
        if (giftVo != null) {
            rt.giftLv = giftVo.giftLv
        }

        rt.nextPublishTime = 0
        rt.wood = 0
        rt.iron = 0
        rt.stone = 0
        rt.food = 0

        return rt
    }

    fun newQueryAllianceInfoAskRtBuilder(): QueryAllianceInfoAskRt.Builder {
        val rt = QueryAllianceInfoAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.id = 0L //联盟ID
        rt.name = "" //联盟名称
        rt.shortName = "" //联盟简称
        rt.alliancePlayerId = 0L //盟主ID
        rt.playerName = "" //盟主名称
        rt.reservePlayers = 0 //当前招收人数
        rt.limitPlayers = 0 //招收人数上限
        rt.powerValue = 0 //当前势力值
        rt.woodConst = 0L //NPC城池木材固定值
        rt.ironConst = 0L //NPC城池铁矿固定值
        rt.stoneConst = 0L //NPC城池石料固定值
        rt.foodstuffConst = 0L //NPC城池粮食固定值
        rt.description = "" //联盟公告
        rt.ctrbtWeek = 0L //玩家本周贡献
        rt.ctrbtTotal = 0L //玩家总贡献
        rt.relationShipId = 0 //联盟关系：1-友好；2-敌对；3-中立
        rt.rewards = 0 //奖池金额
        rt.powerLimit = 0L //允许加入联盟的最低战斗力
        rt.nextPublishTime = 0 //下次可发布任务时间
        rt.hasCountyCity = 0 //联盟是否有占领的郡城：0-没有；1-有
        rt.flagColor = 0 //旗帜的颜色模版ID
        rt.flagStyle = 0 //旗帜的样式模版ID
        rt.flagEffect = 0 //旗帜的图案模版ID
        rt.setRelationEndTime = 0 //联盟外交变更冷却结束时间
        rt.wood = 0 //木料加成
        rt.iron = 0 //晶矿加成
        rt.stone = 0 //石料加成
        rt.food = 0 //粮食加成
        rt.canAddLimit = 0L // 可以直接加入的战斗力 如果<0就表示没开启这个功能
        rt.biaoyu = "" //联盟标语
        rt.allianceLan = 0 //联盟语种
        rt.allianceInAreaNo = 0 //联盟所属的服务器编号
        rt.occupyWonderCount = 0 //占领的奇观数量
        rt.isApply = 0 // 是否申请过这个联盟
        rt.fightRank = 0 // 战斗力排行
        rt.giftLv = 0 // 礼物等级

        return rt
    }
}