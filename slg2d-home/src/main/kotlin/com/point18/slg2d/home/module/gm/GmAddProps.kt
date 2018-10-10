package com.point18.slg2d.home.module.gm

import com.point18.slg2d.common.constg.AddProps
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.PropsHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.event.UsePropsAtOnceEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createPropsChangeNotifier
import java.util.Arrays.asList

class GmAddProps(
    private val propsHelper: PropsHelper = PropsHelper()
) : GmCommand, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java,
    asList(propsHelper)
) {

    override fun exec(session: PlayerActor, message: String) {
        // 格式1： -gm add 类型 数量
        // 格式2： -gm changeCD building
        val messages = message.split(" ")
        if (messages.size == 1) {
            return
        }

        if (messages.size != 4) {
            return
        }

        val propId = messages[2].toIntOrNull()
        if (propId == null) {
            return
        }

        val num = messages[3].toIntOrNull()
        if (num == null) {
            return
        }

        val propProto = pcs.equipCache.equipProtoMap[propId]
        if (propProto == null) {
            return
        }
        val newEquips = propsHelper.getProps(session, propId, num)

        //推送给客户端
        val propsChangeNotifier = createPropsChangeNotifier()
        for (newEquip in newEquips) {
            val newProto = pcs.equipCache.equipProtoMap[newEquip.equipProtoId]
            if (newProto == null) {
                continue
            }
            if (newProto.useAtOnce == 1) {
                // 触发部队战斗力变化事件
                fireEvent(session, UsePropsAtOnceEvent(newEquip.id, newProto.id, newEquip.haveNum))
            } else {
                if (propProto.stackNum == 1) {
                    propsChangeNotifier.append(
                        AddProps,
                        newEquip.id,
                        newEquip.equipProtoId,
                        1,
                        newEquip.lv,
                        newEquip.exp,
                        newEquip.propertyMap
                    )
                } else {
                    propsChangeNotifier.append(
                        AddProps,
                        newEquip.id,
                        newEquip.equipProtoId,
                        num,
                        newEquip.lv,
                        newEquip.exp,
                        newEquip.propertyMap
                    )
                }
            }
        }
        propsChangeNotifier.notice(session)
    }
}