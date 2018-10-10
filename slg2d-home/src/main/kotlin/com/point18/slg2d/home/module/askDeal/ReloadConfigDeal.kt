package com.point18.slg2d.home.module.askDeal

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.pc.ProtoCacheStore
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import pb4server.ReloadHomeConfigAskReq
import pb4server.ReloadHomeConfigAskRt
import pb4server.World2HomeAskReq
import pb4server.World2HomeAskResp
import xyz.ariane.util.lzWarn

class ReloadConfigDeal : W2HAsk, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java
) {

    override fun dealWorldAsk(session: PlayerActor, req: World2HomeAskReq, resp: World2HomeAskResp.Builder) {
        val internalMessage = req.reloadHomeConfigAskReq
        val rt = reloadConfig(session, internalMessage)
        resp.setReloadHomeConfigAskRt(rt)
    }

    private fun reloadConfig(session: PlayerActor, req: ReloadHomeConfigAskReq): ReloadHomeConfigAskRt.Builder {
        val rtBuilder = ReloadHomeConfigAskRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val newPcs = ProtoCacheStore()

        try {
            newPcs.initProtoCache()
        } catch (e: Exception) {
            normalLog.lzWarn { "重载配置错误:$e" }
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }
        pcs = newPcs

        return rtBuilder
    }
}