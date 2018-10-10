package com.point18.slg2d.world.module.realm

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.PhotoQueryRt
import com.point18.slg2d.common.resultcode.ResultCode

// 玩家打开换头像界面时的查询 41
class PhotoQueryDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = photoQuery(session)
        session.sendMsg(MsgType.PhotoQuery_41, rt)
    }

    fun photoQuery(session: PlayerSession): (PhotoQueryRt) {
        val rt = PhotoQueryRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.protoId = 0
        rt.freeCount = 0
        rt.freeTime = 0

        val player = session.player

        // 当前头像的武将模版ID
        rt.protoId = player.photoProtoId

        val nowTime = getNowTime()

        // 免费次数及冷却时间
        if (player.photoFreeCount > 0) {
            rt.freeCount = player.photoFreeCount
        } else {
            // 可以免费更换头像的时间(前提是先消耗完初始的免费次数)
            if (player.photoFreeTime > nowTime) {
                rt.freeTime = player.photoFreeTime
            } else {
                rt.freeCount = 1
            }
        }

        return rt.build()
    }

}