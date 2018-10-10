package com.point18.slg2d.home.module.guildhouse

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.resVoCombine
import com.point18.slg2d.common.pc.resVoToResString
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ProduceReturn
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.getProduce
import com.point18.slg2d.home.common.getProduceEndTime
import com.point18.slg2d.home.dc.Furniture
import com.point18.slg2d.home.dc.FurnitureDC
import com.point18.slg2d.home.dc.GuildHouseDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.FurnitureProduce
import pb4client.FurnitureProduceRt
import pb4client.FurnitureTimeInfo
import java.util.*
import java.util.Arrays.asList

class GatherFurnitureProduceDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus3<HomePlayerDC, GuildHouseDC, FurnitureDC>(
    HomePlayerDC::class.java, GuildHouseDC::class.java, FurnitureDC::class.java,
    asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, guildHouseDC: GuildHouseDC, furnitureDC: FurnitureDC ->
            val reqMsg = msg as FurnitureProduce

            val rtBuilder = this.gatherFurnitureProduce(
                session,
                reqMsg,
                homePlayerDC,
                guildHouseDC,
                furnitureDC
            )
            session.sendMsg(MsgType.GatherFurnitureProduce_1529, rtBuilder.build())
        }
    }

    private fun gatherFurnitureProduce(
        session: PlayerActor,
        msg: FurnitureProduce,
        homePlayerDC: HomePlayerDC,
        guildHouseDC: GuildHouseDC,
        furnitureDC: FurnitureDC
    ): FurnitureProduceRt.Builder {
        val rtBuilder = FurnitureProduceRt.newBuilder()
        rtBuilder.resVo = ""
        rtBuilder.rt = ResultCode.SUCCESS.code

        val player = homePlayerDC.player
        val guildHouse = guildHouseDC.guildHouse
        val comfort = guildHouse.comfort
        val nowFurniture = furnitureDC.nowFurniture

        val allRes = LinkedList<ResVo>()
        val furnitureProMap = mutableMapOf<Furniture, ProduceReturn>()
        for (furnitureId in msg.furnitureIdList) {
            val furniture = nowFurniture[furnitureId]
            if (furniture == null) {
                normalLog.error("找不到家具缓存$furnitureId")
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder
            }

            // 找到模板
            val furnitureProto = pcs.furnitureProtoCache.furnitureProtoMap[furniture.protoId]
            if (furnitureProto == null) {
                rtBuilder.rt = ResultCode.NO_FURNITURE_PROTO.code
                return rtBuilder
            }

            // 校验是否为功能性家具
            if (furnitureProto.produceRes.isEmpty()) { // 通过功能家具的产出非空，来判断是否是功能家具
                normalLog.error("家具不是功能家具${furniture.id}")
                rtBuilder.rt = ResultCode.FURNITURE_NOT_FUNCTIONAL.code
                return rtBuilder
            }

            if (furniture.startTime == 0L) {
                continue
            }

            // 获取家具产出
            val produce = getProduce(furniture, comfort)
            if (produce.rtCode.code != ResultCode.SUCCESS.code) {
                normalLog.error("获取家具产出失败$furnitureId")
                rtBuilder.rt = produce.rtCode.code
                return rtBuilder
            }
            // 保存产出 (此时奖励还没发)
            furnitureProMap[furniture] = produce
        }

        // 累计产出，重新刷新
        furnitureProMap.forEach {
            val furniture = it.key
            val produce = it.value

            furniture.produceRes = LinkedList() // 清空产出
            furniture.startTime = produce.checkTime
            furniture.checkTime = produce.checkTime
            furniture.endTime = 0
            allRes += produce.res

            val (rtCode: ResultCode, endTime: Long) = getProduceEndTime(furniture, comfort)
            if (rtCode.code != ResultCode.SUCCESS.code) {
                rtBuilder.rt = rtCode.code
                return rtBuilder
            }
            furniture.endTime = endTime
            val timeInfoBuilder = FurnitureTimeInfo.newBuilder()
            timeInfoBuilder.id = furniture.id
            timeInfoBuilder.startTime = (furniture.startTime / 1000).toInt()
            timeInfoBuilder.endTime = (furniture.endTime / 1000).toInt()
            rtBuilder.addFurnitures(timeInfoBuilder)
        }

        // 增加产出资源
        val combineRes = resVoCombine(allRes)
        resHelper.addRes(session, com.point18.slg2d.common.constg.ACTION_FURNITURE_PRODUCE, player, combineRes)

        // 返回产出信息
        val resString = resVoToResString(combineRes)
        rtBuilder.resVo = resString

        return rtBuilder
    }
}