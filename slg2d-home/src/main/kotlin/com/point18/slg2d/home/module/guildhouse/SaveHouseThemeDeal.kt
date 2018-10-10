package com.point18.slg2d.home.module.guildhouse

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.UP_LIMIT_HOUSE_THEME
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.FurnitureDC
import com.point18.slg2d.home.dc.FurnitureInfo
import com.point18.slg2d.home.dc.HouseThemeDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.SaveHouseTheme
import pb4client.SaveHouseThemeRt
import java.util.*

// 保存后宅主题处理
class SaveHouseThemeDeal : HomeClientMsgDeal, HomeHelperPlus2<FurnitureDC, HouseThemeDC>(
    FurnitureDC::class.java, HouseThemeDC::class.java
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {

        prepare(session) { furnitureDC: FurnitureDC, houseThemeDC: HouseThemeDC ->
            val reqMsg = msg as SaveHouseTheme
            val rtBuilder = this.handleSaveHouseTheme(
                session, reqMsg, furnitureDC, houseThemeDC
            )
            session.sendMsg(MsgType.SaveHouseTheme_1528, rtBuilder.build())
        }
    }

    private fun handleSaveHouseTheme(
        session: PlayerActor,
        msg: SaveHouseTheme,
        furnitureDC: FurnitureDC,
        houseThemeDC: HouseThemeDC
    ): SaveHouseThemeRt.Builder {
        val rtBuilder = SaveHouseThemeRt.newBuilder()
        rtBuilder.themeId = 0
        rtBuilder.rt = ResultCode.SUCCESS.code

        val themeId = msg.themeId
        var name = msg.name

        val nowFurniture = furnitureDC.nowFurniture
        val houseThemes = houseThemeDC.houseThemeMap

        if (themeId == 0L) {    // 保存主题
            // TODO 上限是否要根据配置指定？
            if (houseThemes.size >= UP_LIMIT_HOUSE_THEME) {
                com.point18.slg2d.common.commonfunc.normalLog.error("玩家${houseThemeDC.playerId} 保存的主题数已达上限")
                rtBuilder.rt = ResultCode.LIMIT_HOUSE_THEME_NUM.code
                return rtBuilder
            }

            if (name == null || "" == name.trim()) {
                name = "新建主题${houseThemes.size + 1}"
            }

            // 从session中获取后宅当前摆放的家具，创建主题
            val furnitureInfoList: LinkedList<FurnitureInfo> = LinkedList()
            nowFurniture.values.forEach {
                furnitureInfoList.add(
                    FurnitureInfo(
                        it.x,
                        it.y,
                        it.direction,
                        it.protoId
                    )
                )
            }
            val houseTheme = houseThemeDC.createHouseTheme(
                houseThemeDC.playerId,
                name,
                0,
                furnitureInfoList
            )

            rtBuilder.themeId = houseTheme.id
        } else {    // 修改主题
            val houseTheme = houseThemes[themeId]
            if (houseTheme == null) {
                com.point18.slg2d.common.commonfunc.normalLog.error("找不到主题[$themeId]的缓存")
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder
            }

            // 从session中获取后宅当前摆放的家具，创建主题
            val furnitureInfoList: LinkedList<FurnitureInfo> = LinkedList()
            nowFurniture.values.forEach {
                furnitureInfoList.add(
                    FurnitureInfo(
                        it.x,
                        it.y,
                        it.direction,
                        it.protoId
                    )
                )
            }

            houseTheme.furnitureInfoList = furnitureInfoList
            rtBuilder.themeId = 0
        }

        return rtBuilder
    }
}