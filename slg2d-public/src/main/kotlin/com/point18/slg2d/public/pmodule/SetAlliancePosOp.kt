package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.common.getNewAlliancePos
import com.point18.slg2d.public.common.noticeChangeKing2World
import com.point18.slg2d.public.common.posChangeNoticAllAlliance
import com.point18.slg2d.public.datacache.PublicCache
import com.point18.slg2d.public.datacache.playerIsHavePos
import pb4server.ResetAlliancePosAskReq
import pb4server.ResetAlliancePosAskRt
import pb4server.World2PublicAskReq
import pb4server.World2PublicAskResp
import java.util.*

class SetAlliancePosOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.resetAlliancePosAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMessage)

        resp.setResetAlliancePosAskRt(rt)
    }

    private fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: ResetAlliancePosAskReq
    ): ResetAlliancePosAskRt.Builder {

        val rt = newResetAlliancePosAskRtBuilder()

        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        if (alce == null) {
            rt.rt = ResultCode.ALLIANCE_PLAYER_NO_JOIN.code
            return rt
        }

        val player = publicCache.allianceMemberCache.findAllianceMemberById(playerId)
        if (player == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        //参数有效性
        if (req.posIdList.size > pcs.basicProtoCache.numberOfTitlesAvailable) {
            rt.rt = ResultCode.MEMBER_POS_MAX_ERROR.code
            return rt
        }

        for (p in req.posIdList) {
            val posProto = pcs.posRightCache.posName[p]
            if (posProto == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt
            }
        }

        val setPlayer = publicCache.allianceMemberCache.findAllianceMemberById(req.posPlayerId)
        if (setPlayer == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        val oldPos = LinkedList<Int>()
        for ((p, _) in setPlayer.alliancePosMap) {
            oldPos.add(p)
        }

        //是否是相同联盟
        if (player.allianceId != setPlayer.allianceId) {
            rt.rt = ResultCode.ALLIANCE_SET_POSITION_FORBIDDEN.code
            return rt
        }

        //验证玩家的职位是否拥有该模块操作权限
        if (!hasRight(player, A_RIGHT_SET_POS)) {
            rt.rt = ResultCode.ALLIANCE_POSITION_RIGHT_FORBIDDEN.code
            return rt
        }

        // 判断职位大小
        var setPos = 0
        for ((p, _) in setPlayer.alliancePosMap) {
            setPos = p
        }

        var myPos = 0
        for ((p, _) in player.alliancePosMap) {
            myPos = p
        }

        val setPosProto = pcs.posRightCache.posName[setPos]
        val myPosProto = pcs.posRightCache.posName[myPos]
        if (setPosProto == null || myPosProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt
        }

        if (setPosProto.step <= myPosProto.step) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        val allAllianceMember =
            publicCache.allianceMemberCache.findAllianceMembersByAllianceId(allianceId)

        for (newPos in req.posIdList) {
            val posProto = pcs.posRightCache.posName[newPos]
            if (posProto == null) {
                continue
            }
            if (newPos != ALLIANCE_POSITION_BOSS) {
                // 验证当前职位任命数是否等于职位配置数
                var posNowNum = 0
                for (allianceMember in allAllianceMember) {
                    val ex = allianceMember.alliancePosMap[newPos]
                    if (ex != null) {
                        posNowNum++
                    }
                }

                if (posNowNum >= posProto.positionNumb) {
                    rt.rt = ResultCode.ALLIANCE_SET_POSITION_EXCEED.code
                    return rt
                }
            } else {
//                val timeIsOk = checkChangeMainPlayer(publicCache, setPlayer, player)
//                if (!timeIsOk) {
//                    rt.rt = ResultCode.ALLIANCE_GIVE_TIME_ERROR.code
//                    return rt
//                }
            }

            // 盟主转让
            if (!playerIsHavePos(player, ALLIANCE_POSITION_BOSS) && newPos == ALLIANCE_POSITION_BOSS) {
                rt.rt = ResultCode.ALLIANCE_POSITION_RIGHT_FORBIDDEN.code
                return rt
            }
        }

        // 验证职位的不可并存
        for ((p, _) in setPlayer.alliancePosMap) {
            setPlayer.delWrapPosition(p)
        }

        for (newPos in req.posIdList) {
            val posProto = pcs.posRightCache.posName[newPos]
            if (posProto == null) {
                continue
            }
            // 职位任命
            setPlayer.setWrapPosition(newPos)

            val nowPos = LinkedList<Int>()
            for ((p, _) in setPlayer.alliancePosMap) {
                nowPos.add(p)
            }

            // 通知这个玩家被设置了新职位
            getNewAlliancePos(
                publicCache.publicActor,
                setPlayer.mapPltAreaId, req.posPlayerId, allianceId, toJson(setPlayer.alliancePosMap),
                alce.name, alce.shortName
            )

            if (newPos == ALLIANCE_POSITION_BOSS) {
                val mainPlayerOldPos = LinkedList<Int>()
                for ((p, _) in player.alliancePosMap) {
                    mainPlayerOldPos.add(p)
                }

                // 移除当前国王职位
                player.delWrapPosition(newPos)
                // 安排到R2
                player.setWrapPosition(ALLIANCE_POSITION_ASSISTANT)

                // 盟主的帮帮主丢失了
                getNewAlliancePos(
                    publicCache.publicActor,
                    player.mapPltAreaId, player.id, allianceId, toJson(player.alliancePosMap),
                    alce.name, alce.shortName
                )

                // 同步修改世界服新国王
                for ((worldId, wonderOccupy) in alce.allianceOccupyInfo) {
                    var isKing = false
                    for ((wonderProtoId, _) in wonderOccupy) {
                        if (wonderProtoId == BIG_WONDER) {
                            isKing = true
                            break
                        }
                    }

                    if (isKing) {
                        noticeChangeKing2World(publicCache.publicActor, worldId, setPlayer.id)
                    }
                }

                alce.mainPlayerId = setPlayer.id

                insertLog(
                    publicCache,
                    alce.id,
                    A_LOG_MAKE_OVER,
                    player.name,
                    player.allianceNickName,
                    setPlayer.name,
                    setPlayer.allianceNickName
                )

                // 推送到公共服
                syncAllianceInfo2AM(
                    publicCache,
                    alce,
                    0,
                    0,
                    0
                )
            } else {
                insertLog(
                    publicCache,
                    alce.id,
                    A_LOG_APPOINT,
                    player.name,
                    player.allianceNickName,
                    setPlayer.name,
                    setPlayer.allianceNickName,
                    newPos.toString()
                )
            }

            val pltAreas = mutableMapOf<Long, Int>()

            for (am in allAllianceMember) {
                pltAreas[am.mapPltAreaId] = 1
            }

            for ((pltAreaId, _) in pltAreas) {
                // 邮件通知帮里其他人
                posChangeNoticAllAlliance(
                    publicCache.publicActor,
                    pltAreaId, setPlayer.allianceId, newPos, player.name, setPlayer.name,
                    GETNEW_POS, nowPos, req.posPlayerId, setPlayer.onlineState, setPlayer.photoProtoId
                )
            }
        }

        return rt
    }

    private fun newResetAlliancePosAskRtBuilder(): ResetAlliancePosAskRt.Builder {
        val rt = ResetAlliancePosAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}