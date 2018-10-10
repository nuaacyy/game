package com.point18.slg2d.world.module.viewprofile

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.ResourceParticularRt
import com.point18.slg2d.common.resultcode.ResultCode

// 个人势力（资源详情）
class ResourceParticularDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val resourceParticularRt = resourceParticular(session)

        session.sendMsg(MsgType.ResourceParticular_25, resourceParticularRt)
    }

    fun resourceParticular(session: PlayerSession): ResourceParticularRt {
        val rtBuilder = ResourceParticularRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code
// todo 迁移错误
//        val areaCache = session.areaCache
//
//        val player = session.player
//        if (player == null) {
//            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
//            return rtBuilder.build()
//        }
//
//        // 资源产量
//        val resYield = areaData.findResourceYield(areaCache, player.id)
//        if (resYield == null) {
//            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
//            return rtBuilder.build()
//        }
//
//        val baseWood = resYield.buildingWood
//        val baseIron = resYield.buildingIron
//        val baseStone = resYield.buildingStone
//        val baseFood = resYield.buildingFood
//
//        // 2-设施产量
//        val buildingResBuilder = ResourceParticularInfo.newBuilder()
//        buildingResBuilder.types = constg.buildingResYield
//        val buildingYieldBuilder = YieldChange.newBuilder()
//        buildingYieldBuilder.addWood = resYield.buildingWood
//        buildingYieldBuilder.addIron = resYield.buildingIron
//        buildingYieldBuilder.addStone = resYield.buildingStone
//        buildingYieldBuilder.addFood = resYield.buildingFood
//        buildingYieldBuilder.addCoin = resYield.buildingCoin
//        buildingResBuilder.setYieldChange(buildingYieldBuilder)
//        rtBuilder.addResourceParticularInfo(buildingResBuilder)
//
//        // 将联盟加成算成一个累计值给客户端
//        val aWood =
//            (Math.ceil(baseWood * (resYield.allianceWood + resYield.allianceResearchWood) / 100.0) + resYield.npcCityWood + resYield.memWood).toInt()
//        val aIron =
//            (Math.ceil(baseIron * (resYield.allianceIron + resYield.allianceResearchIron) / 100.0) + resYield.npcCityIron + resYield.memIron).toInt()
//        val aStone =
//            (Math.ceil(baseStone * (resYield.allianceStone + resYield.allianceResearchStone) / 100.0) + resYield.npcCityStone + resYield.memStone).toInt()
//        val aFood =
//            (Math.ceil(baseFood * (resYield.allianceFood + resYield.allianceResearchFood) / 100.0) + resYield.npcCityFood + resYield.memFood).toInt()
//
//        //3-联盟所有加成
//        val allianceResBuilder = ResourceParticularInfo.newBuilder()
//        allianceResBuilder.types = constg.allianceResYield
//        val allianceYieldBuilder = YieldChange.newBuilder()
//        allianceYieldBuilder.addWood = aWood
//        allianceYieldBuilder.addIron = aIron
//        allianceYieldBuilder.addStone = aStone
//        allianceYieldBuilder.addFood = aFood
//        allianceYieldBuilder.addCoin = 0
//        allianceResBuilder.setYieldChange(allianceYieldBuilder)
//        rtBuilder.addResourceParticularInfo(allianceResBuilder)
//
//        //科技加成
//        val rWood = (Math.ceil((baseWood * resYield.researchWood) / 100.0)).toInt()
//        val rIron = (Math.ceil((baseIron * resYield.researchIron) / 100.0)).toInt()
//        val rStone = (Math.ceil((baseStone * resYield.researchStone) / 100.0)).toInt()
//        val rFood = (Math.ceil((baseFood * resYield.researchFood) / 100.0)).toInt()
//        val rCoin = (Math.ceil((baseFood * resYield.researchCoin) / 100.0)).toInt()
//        val researchResBuilder = ResourceParticularInfo.newBuilder()
//        researchResBuilder.types = constg.researchResYield
//        val researchYieldBuilder = YieldChange.newBuilder()
//        researchYieldBuilder.addWood = rWood
//        researchYieldBuilder.addIron = rIron
//        researchYieldBuilder.addStone = rStone
//        researchYieldBuilder.addFood = rFood
//        researchYieldBuilder.addCoin = rCoin
//        researchResBuilder.setYieldChange(researchYieldBuilder)
//        rtBuilder.addResourceParticularInfo(researchResBuilder)
//
//        //vip加成
//        val vWood = (Math.ceil((baseWood * resYield.vipWood) / 100.0)).toInt()
//        val vIron = (Math.ceil((baseIron * resYield.vipIron) / 100.0)).toInt()
//        val vStone = (Math.ceil((baseStone * resYield.vipStone) / 100.0)).toInt()
//        val vFood = (Math.ceil((baseFood * resYield.vipFood) / 100.0)).toInt()
//        val vCoin = (Math.ceil((baseFood * resYield.vipCoin) / 100.0)).toInt()
//        val vipResBuilder = ResourceParticularInfo.newBuilder()
//        vipResBuilder.types = constg.vipResYield
//        val vipYieldBuilder = YieldChange.newBuilder()
//        vipYieldBuilder.addWood = vWood
//        vipYieldBuilder.addIron = vIron
//        vipYieldBuilder.addStone = vStone
//        vipYieldBuilder.addFood = vFood
//        vipYieldBuilder.addCoin = vCoin
//        vipResBuilder.setYieldChange(vipYieldBuilder)
//        rtBuilder.addResourceParticularInfo(vipResBuilder)
//
//        //天赋加成
//        val tWood = (Math.ceil((baseWood * resYield.talentWood) / 100.0)).toInt()
//        val tIron = (Math.ceil((baseIron * resYield.talentIron) / 100.0)).toInt()
//        val tStone = (Math.ceil((baseStone * resYield.talentStone) / 100.0)).toInt()
//        val tFood = (Math.ceil((baseFood * resYield.talentFood) / 100.0)).toInt()
//        val tCoin = (Math.ceil((baseFood * resYield.talentCoin) / 100.0)).toInt()
//        val talentResBuilder = ResourceParticularInfo.newBuilder()
//        talentResBuilder.types = constg.talentResYield
//        val talentYieldBuilder = YieldChange.newBuilder()
//        talentYieldBuilder.addWood = tWood
//        talentYieldBuilder.addIron = tIron
//        talentYieldBuilder.addStone = tStone
//        talentYieldBuilder.addFood = tFood
//        talentYieldBuilder.addCoin = tCoin
//        talentResBuilder.setYieldChange(talentYieldBuilder)
//        rtBuilder.addResourceParticularInfo(talentResBuilder)
//
//        //5-维持消耗
//        val useResBuilder = ResourceParticularInfo.newBuilder()
//        useResBuilder.types = constg.useUpResYield
//        val useYieldBuilder = YieldChange.newBuilder()
//        useYieldBuilder.addWood = tWood
//        useYieldBuilder.addIron = tIron
//        useYieldBuilder.addStone = tStone
//        useYieldBuilder.addFood = tFood
//        useYieldBuilder.addCoin = tCoin
//        useResBuilder.setYieldChange(useYieldBuilder)
//        rtBuilder.addResourceParticularInfo(useResBuilder)

        //返回值
        return rtBuilder.build()

    }
}



