package com.point18.slg2d.world.module.walk.fight

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.module.fightdomain.createFightDataByTestFightData
import com.point18.slg2d.world.module.soliderBattle.fight.createSoliderFight
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.commonfunc.toJson
import pb4client.TestFight
import pb4client.TestFightRt
import com.point18.slg2d.common.resultcode.ResultCode

//战斗模拟器
class TestFightDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val areaCache = session.areaCache
        val testMsg = msg as TestFight
        val atkFightData = createFightDataByTestFightData(testMsg.atkTestFightData)
        val defFightData = createFightDataByTestFightData(testMsg.defTestFightData)

        val detailInfo: String
        val fastFight = createSoliderFight(
            areaCache,
            0,
            0,
            0,
            atkFightData,
            defFightData
        )
        val info = fastFight.initAndStart()
        detailInfo = toJson(info)

        val rtBuilder = TestFightRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code
        rtBuilder.detailFightInfo = detailInfo
        session.sendMsg(MsgType.TestFight_114, rtBuilder.build())
    }
}
