package com.point18.slg2d.home.module.photo

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.IconDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.QueryAllIconRt
import java.util.*

// 查询所有头像
class QueryAllIconDeal(
) : HomeClientMsgDeal,
    HomeHelperPlus1<IconDC>(
        IconDC::class.java
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val iconId = (msg as pb4client.QueryAllIcon)
        prepare(session) { iconDC ->
            val rt = queryAllIcon(iconDC)
            session.sendMsg(MsgType.QueryAllIcon_1512, rt)
        }
    }

    fun queryAllIcon(iconDC: IconDC): (QueryAllIconRt) {
        val dealRt = QueryAllIconRt.newBuilder()
        dealRt.rt = com.point18.slg2d.common.resultcode.ResultCode.SUCCESS.code
        val allIconIds = LinkedList<Int>() //该玩家有的所有头像ID都放在这个list

        val iconProto = pcs.lordHeadIconProtoCache.lordHeadIconMap
        for ((id, protoRecord) in iconProto) {
            if (protoRecord.type == 0) {
                allIconIds.add(id)
            }
        }
        println("现在免费的allIconIds：$allIconIds")

        val allIcon = iconDC.iconsMap

        // 从Cache里面找到头像,那些头像都不是默认就有的头像
        allIcon.forEach { (iconID, _) ->
            allIconIds.add(iconID)
        }
        println("现在免费+有条件的allIconIds：$allIconIds")
        dealRt.addAllIconIds(allIconIds)
        return dealRt.build()
    }

}
