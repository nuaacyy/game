package com.point18.slg2d.home.module.mark

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode.*
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.MarkDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.AddMark
import pb4client.AddMarkRt
import pb4client.MarkInfo

// 添加收藏
class AddMarkDeal(
) : HomeClientMsgDeal,
    HomeHelperPlus2<HomePlayerDC, MarkDC>(
        HomePlayerDC::class.java,
        MarkDC::class.java
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val aimsX = (msg as AddMark).aimsX
        val aimsY = msg.aimsY
        val areaNo = msg.areaNo
        val group = msg.group
        val name = msg.name
        prepare(session) { homePlayerDC, markDC ->
            val addMarkRt = addMark(aimsX, aimsY, areaNo, group, name, homePlayerDC, markDC)
            session.sendMsg(MsgType.AddMark_19, addMarkRt)
        }
    }

    fun addMark(
        aimsX: Int,
        aimsY: Int,
        areaNo: Int,
        group: Int,
        name: String,
        homePlayerDC: HomePlayerDC,
        markDC: MarkDC
    ): AddMarkRt {
        val rt = AddMarkRt.newBuilder()
        rt.rt = SUCCESS.code
        val markInfoBuilder = MarkInfo.newBuilder()
        markInfoBuilder.name = name
        markInfoBuilder.areaNo = areaNo
        markInfoBuilder.landX = aimsX
        markInfoBuilder.landY = aimsY
        markInfoBuilder.group = group
        markInfoBuilder.id = 0
        rt.markInfo = markInfoBuilder.build()

        // 检测输入是否超出范围
        if (!com.point18.slg2d.common.constg.isValidXY(aimsX, aimsY)) {
            rt.rt = MARK_ERR.code
            return rt.build()
        }

        // 长度不足
        if (name.length <= 0) {
            rt.rt = NAME_NIL.code
            return rt.build()
        }

        val player = homePlayerDC.player

        val n = name.length //中文字符算2，英文字符算1

        // 长度超过
        if (n > pcs.basicProtoCache.markNameLength[1]) {
            rt.rt = NAME_LENGTH_EXCEED.code
            return rt.build()
        }

        // 检测是否存在
        val markInfo = markDC.findMarkByPos(aimsX, aimsY, areaNo)

        if (markInfo != null) {
            // 修改数据

            markInfo.x = aimsX
            markInfo.y = aimsY
            markInfo.group = group
            markInfo.name = name
            markInfo.pltAreaNo = areaNo

            markInfoBuilder.id = markInfo.id
            rt.setMarkInfo(markInfoBuilder)
        } else {
            // 新建
            // 检查标记数目是否超过上限
            val marks = markDC.findMarkListByPlayerId()

            if (marks.size >= player.maxMark) {
                rt.rt = (MARK_NUM_EXCEED.code)
                return rt.build()
            }

            // 存入新的标记
            val mark = markDC.createPlayerMark(aimsX, aimsY, areaNo, group, name)
            markInfoBuilder.id = mark.id
            rt.setMarkInfo(markInfoBuilder)
        }

        return rt.build()
    }
}


