package com.point18.slg2d.home.module.innerCity

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.BORN_NEED
import com.point18.slg2d.common.constg.CHANGE_INNER_CITY
import com.point18.slg2d.common.constg.STABLE
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createInnerCityInfoChangedNotifier
import pb4client.MoveInnerCity
import pb4client.MoveInnerCityRt

// 移动内城建筑
class MoveInnerCityDeal : HomeClientMsgDeal, HomeHelperPlus1<InnerCityDC>(InnerCityDC::class.java) {
    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { innerCityDC: InnerCityDC ->
            val cityId = (msg as MoveInnerCity).cityId
            val innerCityId = msg.innerCityId
            val newPositionIndex = msg.newPositionIndex

            val moveInnerCityRt = moveInnerCity(session, innerCityDC, cityId, innerCityId, newPositionIndex)

            session.sendMsg(MsgType.MoveInnerCity_56, moveInnerCityRt)
        }
    }

    private fun moveInnerCity(
        session: PlayerActor,
        innerCityDC: InnerCityDC,
        castleId: Long,
        innerCityId: Long,
        newPositionIndex: Int
    ): MoveInnerCityRt {
        val rt = MoveInnerCityRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.oldPositionIndex = 0
        rt.newPositionIndex = 0

        val innerCity = innerCityDC.findInnerCityFromId(innerCityId)
        if (innerCity == null) {
            // 没找到该建筑
            rt.rt = ResultCode.INNER_CITY_NOT_FIND_BUILDING.code
            return rt.build()
        }

        // 建筑模板
        val buildingProto = pcs.innerBuildingCache.innerBuildingProtoMap[innerCity.cityType]
        if (buildingProto == null) {
            // 建筑配置没找到
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        if (buildingProto.bornType != BORN_NEED) {
            // 该建筑不是可拆除或可建造建筑
            rt.rt = ResultCode.INNER_CITY_CAN_NOT_BUILD.code
            return rt.build()
        }

        if (innerCity.positionIndex == newPositionIndex) {
            // 目标坑位不能是自身坑位
            rt.rt = ResultCode.INNER_CITY_CAN_NOT_MOVE_TO_SELF.code
            return rt.build()
        }

        if (innerCity.state != STABLE) {
            // 状态不对
            rt.rt = ResultCode.INNER_CITY_STATE_ERROR.code
            return rt.build()
        }

        val buildingLocationProto = pcs.innerBuildingLocationCache.protoMap[newPositionIndex]
        if (buildingLocationProto == null) {
            // 没找到坑位模板
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        var canMove = false
        for (vo in buildingLocationProto.interfaceTypeList) {
            if (vo == innerCity.cityType) {
                canMove = true
                break
            }
        }

        if (!canMove) {
            // 坑位上不能移动
            rt.rt = ResultCode.INNER_CITY_CAN_NOT_BUILD.code
            return rt.build()
        }

        val moveToInnerCity = innerCityDC.findInnerCityFromPositionIndex(castleId, newPositionIndex)
        if (moveToInnerCity != null) {
            val moveToBuildingProto = pcs.innerBuildingCache.innerBuildingProtoMap[moveToInnerCity.cityType]
            if (moveToBuildingProto == null) {
                // 建筑配置没找到
                rt.rt = ResultCode.NO_PROTO.code
                return rt.build()
            }

            if (moveToBuildingProto.bornType != BORN_NEED) {
                // 该建筑不是可拆除或可建造建筑
                rt.rt = ResultCode.INNER_CITY_CAN_NOT_BUILD.code
                return rt.build()
            }
            if (moveToInnerCity.state != STABLE) {
                //目标建筑状态不对
                rt.rt = ResultCode.INNER_CITY_STATE_ERROR.code
                return rt.build()
            }
        }

        rt.oldPositionIndex = innerCity.positionIndex
        rt.newPositionIndex = newPositionIndex

        if (moveToInnerCity != null) {
            moveToInnerCity.positionIndex = innerCity.positionIndex
            createInnerCityInfoChangedNotifier(
                CHANGE_INNER_CITY,
                moveToInnerCity.cityType,
                moveToInnerCity.id,
                (moveToInnerCity.startTime / 1000).toInt(),
                (moveToInnerCity.completeTime / 1000).toInt(),
                moveToInnerCity.state, moveToInnerCity.positionIndex,
                moveToInnerCity.lv, moveToInnerCity.helpId
            ).notice(session)
        }

        innerCity.positionIndex = newPositionIndex
        createInnerCityInfoChangedNotifier(
            CHANGE_INNER_CITY,
            innerCity.cityType,
            innerCity.id,
            (innerCity.startTime / 1000).toInt(),
            (innerCity.completeTime / 1000).toInt(),
            innerCity.state, innerCity.positionIndex,
            innerCity.lv, innerCity.helpId
        ).notice(session)

        return rt.build()
    }

}