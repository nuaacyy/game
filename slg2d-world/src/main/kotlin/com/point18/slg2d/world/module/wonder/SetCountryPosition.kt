package com.point18.slg2d.world.module.wonder

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.KingOffice
import com.point18.slg2d.common.constg.OfficeFunction
import com.point18.slg2d.common.constg.OneLvOffice
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.checkOfficeFunction
import com.point18.slg2d.world.common.dismissOffice
import com.point18.slg2d.world.common.findOfficeByPlayerId
import com.point18.slg2d.world.common.grantOffice
import com.point18.slg2d.world.module.ReqDealEntered
import pb4client.SetCountryPosition
import pb4client.SetCountryPositionRt

class SetCountryPositionDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val setPosMsg = msg as SetCountryPosition
        val posId = setPosMsg.posId
        val targetPlayerId = setPosMsg.targetPlayerId
        if (posId == 0) {
            // 取消职位
            val rt = this.dealCancelPos(session, targetPlayerId) ?: return
            session.sendMsg(MsgType.SetCountryPosition_1455, rt.build())
            if (rt.rt == ResultCode.SUCCESS.code) {
                //val bigWonder = findBigWonder(session.areaCache)
				//UpdateWonderInfo(session.areaCache, Update, bigWonder.belongToAllianceId, bigWonder)
            }
        } else {
            val rt = this.dealSetPosition(session, posId, targetPlayerId) ?: return
            session.sendMsg(MsgType.SetCountryPosition_1455, rt.build())
            if (rt.rt == ResultCode.SUCCESS.code) {
                //val bigWonder = findBigWonder(session.areaCache)
				//UpdateWonderInfo(session.areaCache, Update, bigWonder.belongToAllianceId, bigWonder)
            }
        }
    }

    private fun dealSetPosition(session: PlayerSession, posId: Int, targetPlayerId: Long): SetCountryPositionRt.Builder {
        val rtBuilder = SetCountryPositionRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val areaCache = session.areaCache
        val playerId = session.playerId

        val targetPlayer = areaCache.playerCache.findPlayerById(targetPlayerId)
        if (targetPlayer == null) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        // 操作者的官职
        val setPosId = findOfficeByPlayerId(areaCache, playerId)
        val setOfficeProto = pcs.officeProtoCache.officeProtoMap[setPosId]
        if (setOfficeProto == null) {
            rtBuilder.rt = ResultCode.NO_PROTO.code
            return rtBuilder
        }

        // 校验官职职能是否能设置官职
        val ok = checkOfficeFunction(OfficeFunction.SetOffice, setPosId)
        if (!ok) {
            rtBuilder.rt = ResultCode.LIMIT_TO_SET_OFFICE.code
            return rtBuilder
        }

        // 要设置的官职
        val beSetOfficeProto = pcs.officeProtoCache.officeProtoMap[posId]
        if (beSetOfficeProto == null) {
            rtBuilder.rt = ResultCode.NO_PROTO.code
            return rtBuilder
        }

        if (setOfficeProto.mainType != KingOffice || beSetOfficeProto.mainType != KingOffice) {
            rtBuilder.rt = ResultCode.UNABLE_SET_SUCH_OFFICE.code
            return rtBuilder
        }

        // subType越小，官职等级越高。这里表示只能设置比自己低的官职
        if (setOfficeProto.subType >= beSetOfficeProto.subType) {
            rtBuilder.rt = ResultCode.LIMIT_TO_SET_OFFICE.code
            return rtBuilder
        }

        //不能给自己设置官职
        if (playerId == targetPlayerId) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        //校验被设置的人的原有官职
        val haveOfficeId = findOfficeByPlayerId(areaCache, targetPlayerId)
        if (haveOfficeId != 0) {
            val haveOffice = pcs.officeProtoCache.officeProtoMap[haveOfficeId]
            if (haveOffice == null) {
                rtBuilder.rt = ResultCode.NO_PROTO.code
                return rtBuilder
            }
            if (haveOffice.mainType != KingOffice) {
                rtBuilder.rt = ResultCode.UNABLE_SET_SUCH_OFFICE.code
                return rtBuilder
            }
            if (haveOffice.subType == OneLvOffice) {
                rtBuilder.rt = ResultCode.LIMIT_TO_SET_OFFICE.code
                return rtBuilder
            }
        }

        val rt = grantOffice(areaCache, posId, targetPlayer)
        rtBuilder.rt = rt

        return rtBuilder
    }

    private fun dealCancelPos(session: PlayerSession, targetPlayerId: Long): SetCountryPositionRt.Builder {
        val rtBuilder = SetCountryPositionRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val areaCache = session.areaCache
        val playerId = session.playerId
        val cancelPosId = findOfficeByPlayerId(areaCache, playerId)

        val targetPlayer = areaCache.playerCache.findPlayerById(targetPlayerId)
        if (targetPlayer == null) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        val ok = checkOfficeFunction(OfficeFunction.SetOffice, cancelPosId)
        if (!ok) {
            rtBuilder.rt = ResultCode.LIMIT_TO_SET_OFFICE.code
            return rtBuilder
        }

        val cancelOfficeProto = pcs.officeProtoCache.officeProtoMap[cancelPosId]
        if (cancelOfficeProto == null) {
            rtBuilder.rt = ResultCode.NO_PROTO.code
            return rtBuilder
        }

        val toCancelPosId = findOfficeByPlayerId(areaCache, targetPlayerId)
        if (toCancelPosId == 0) {
            rtBuilder.rt = ResultCode.NO_OFFICE_TO_CANCEL.code
            return rtBuilder
        }

        val toCancelOfficeProto = pcs.officeProtoCache.officeProtoMap[toCancelPosId]
        if (toCancelOfficeProto == null) {
            rtBuilder.rt = ResultCode.NO_PROTO.code
            return rtBuilder
        }

        if (cancelOfficeProto.mainType != KingOffice || toCancelOfficeProto.mainType != KingOffice) {
            rtBuilder.rt = ResultCode.UNABLE_SET_SUCH_OFFICE.code
            return rtBuilder
        }

        if (cancelOfficeProto.subType >= toCancelOfficeProto.subType) {
            rtBuilder.rt = ResultCode.LIMIT_TO_SET_OFFICE.code
            return rtBuilder
        }

        val bigWonder = areaCache.wonderCache.findBigWonder()
        if (bigWonder == null) {
            rtBuilder.rt = ResultCode.WONDER_NOT_EXIST.code
            return rtBuilder
        }

        //清除官职
       dismissOffice(areaCache, targetPlayer, bigWonder)

        return rtBuilder
    }
}



