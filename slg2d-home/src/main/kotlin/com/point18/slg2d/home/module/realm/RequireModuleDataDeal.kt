package com.point18.slg2d.home.module.realm

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ModuleDataHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.RequireModuleData
import pb4client.RequireModuleDataRt
import java.util.Arrays.asList

class RequireModuleDataDeal(private val moduleDataHelper: ModuleDataHelper = ModuleDataHelper()) : HomeClientMsgDeal,
    HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java, asList(moduleDataHelper)) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) {
            val rtBuilder = getModuleData(session, msg as RequireModuleData)
            session.sendMsg(MsgType.CreateScopeMsg_304, rtBuilder.build())
        }
    }

    private fun getModuleData(session: PlayerActor, msg: RequireModuleData): RequireModuleDataRt.Builder {
        val rtBuilder = RequireModuleDataRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        moduleDataHelper.initModuleData(session, msg.moduleId)

        return rtBuilder
    }
}
