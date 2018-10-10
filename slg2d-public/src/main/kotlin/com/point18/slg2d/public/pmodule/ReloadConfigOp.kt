package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.pc.ProtoCacheStore
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.ReloadPublicConfigAskRt
import pb4server.World2PublicAskReq
import pb4server.World2PublicAskResp
import xyz.ariane.util.lzWarn

class ReloadConfigOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val rt = dealMsg()

        resp.setReloadPublicConfigAskRt(rt)
    }

    fun dealMsg(): ReloadPublicConfigAskRt.Builder {
        val rtBuilder = ReloadPublicConfigAskRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val newPcs = ProtoCacheStore()
//        val newPcs = ProtoCacheStore(ppm.zkDao.zkClient)

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
