package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Player
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.*
import com.point18.slg2d.world.event.GetNewBuff
import com.point18.slg2d.world.module.walk.dealWalkFinish
import com.point18.slg2d.world.module.walk.walkComm.goHome
import com.point18.slg2d.world.msgnotice.createCastlePosChangeNotifier
import com.point18.slg2d.world.wpm
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import pb4server.MoveCityAskReq
import pb4server.MoveCityAskRt
import java.util.*
import java.util.Arrays.asList

class MoveCityDeal : Home2WorldAskDealBase() {

    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val askMsg = req.moveCityAskReq
        val rt = dealMoveCity(areaCache, req.playerId, askMsg)
        resp.setMoveCityAskRt(rt)
    }

    private fun dealMoveCity(areaCache: AreaCache, playerId: Long, askMsg: MoveCityAskReq): MoveCityAskRt.Builder {
        val rt = MoveCityAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val x = askMsg.x
        val y = askMsg.y

        val player = areaCache.playerCache.findPlayerById(playerId)

        if (player == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        //校验自身的行军部队，除了领主释放必须全部是静止状态
        var kingBackWalkLine: Walk? = null
        val myForces = areaCache.walkForceCache.findWalkForceByPlayerId(player.id)
        for (force in myForces) {
            val group = areaCache.walkForceGroupCache.findWalkForceGroupById(force.forceGroupId)
            if (group == null) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return rt
            }

            if (group.runningState == Running && group.gotoRunType != WalkMainHeroGoHome) {
                rt.rt = ResultCode.FORCE_RUNNING_OUTSIDE.code
                return rt
            }

            if (group.runningState == MassWaiting) {
                rt.rt = ResultCode.FORCE_RUNNING_OUTSIDE.code
                return rt
            }

            //判断部队是否静止，过滤掉领主回家的情况
            if (group.gotoRunType == WalkMainHeroGoHome) {
                //瞬移回家
                val walkLine = areaCache.walkCache.findWalkByGroupId(force.forceGroupId)
                if (walkLine == null) {
                    continue
                }

                kingBackWalkLine = walkLine
            }
        }

        if (kingBackWalkLine != null) {
            dealWalkFinish(areaCache, kingBackWalkLine)
        }

        val (rst, oldX, oldY, newX, newY) = moveCity(areaCache, player, x, y)

        if (rst != ResultCode.SUCCESS) {
            rt.rt = rst.code
            return rt
        }

        noticeCellUpdate(areaCache, oldX, oldY)
        noticeCellUpdate(areaCache, newX, newY)

        return rt
    }
}

data class MoveCityFunRet(
    var r: ResultCode,
    var x0: Int,
    var y0: Int,
    var x1: Int,
    var y2: Int
)

fun moveCity(areaCache: AreaCache, player: Player, x: Int, y: Int): MoveCityFunRet {
    // 校验坐标
    if (!checkMoveCastleFreeCell(areaCache, player.id, x, y)) {
        return MoveCityFunRet(ResultCode.MOVE_CITY_POS_ERROR, 0, 0, x, y)
    }

    val freeCell = checkFreeCell(areaCache, x, y, player.id)
    if (!freeCell) {
        return MoveCityFunRet(ResultCode.MOVE_CITY_POS_ERROR, 0, 0, x, y)
    }

    // 查找行军线
    val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
    if (castle == null) {
        return MoveCityFunRet(ResultCode.PARAMETER_ERROR, 0, 0, 0, 0)
    }

    val oldX = castle.x
    val oldY = castle.y
    val gotoWalks = areaCache.walkCache.findWalksByGotoXy(oldX, oldY)

    //行军线排序
    gotoWalks.sortBy { it.marchTimeArrival }

    //行军部队秒到
    for (walkLine in gotoWalks) {
        dealWalkFinish(areaCache, walkLine)
    }

    //将在本城的驻扎/增援部队全部遣返
    val groupsInCastle =
        areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(oldX, oldY, Reinforce or Stationed)
    for (group in groupsInCastle) {
        // 遣返
        goHome(areaCache, oldX, oldY, group)

        //发送遣返邮件
        val mailInfo = MailInfo(
            TEXT_READ_LAN,
            SYSTEM_MAIL,
            LinkedList(),
            FORCE_BE_SEND_HOME_CONTENT,
            LinkedList(asList(player.name))
        )
        sendMail(areaCache, group.mainPlayerId, mailInfo)
    }

    val session = fetchOnlinePlayerSession(areaCache, player.id)

    //修改城池坐标
    areaCache.castleCache.updateCastlePos(castle, x, y)

    //迁城到雪地，去除保护罩buff
    dealCoverAfterMoveCity(areaCache, player, x, y)

    if (session != null) {
        val notifier = createCastlePosChangeNotifier(x, y)
        notifier.notice(session)
    }

    //刷新当前集结
    updateMassPos(areaCache, oldX, oldY, x, y)

    //添加统计
    targetAddVal(
        areaCache,
        player.id,
        MoveCity
    )
    return MoveCityFunRet(ResultCode.SUCCESS, oldX, oldY, x, y)
}

// 迁城后根据土地类型判断是否要改变保护罩的状态
fun dealCoverAfterMoveCity(areaCache: AreaCache, player: Player, x: Int, y: Int) {
    val (_, wonderAreaType) =
            pcs.wonderLocationProtoCache.findInWonderType(x, y)

    val (monsterLocationProto, snowAreaType) =
            pcs.monsterActivityLocationProtoCache.findActivityBossAreaType(x, y)

    val session = fetchOnlinePlayerSession(areaCache, player.id)

    if (wonderAreaType == WONDER_BLACK) {
        // 去除保护罩
        val delCoverTypes = arrayOf(BUFF_EFFECT_DEF_COVER, BUFF_EFFECT_SNOW_COVER)
        removeCover(areaCache, delCoverTypes, player, session)
    }

    if (snowAreaType == SNOW_AREA && monsterLocationProto != null) {
        // 去除保护罩
        val delCoverTypes = arrayOf(BUFF_EFFECT_DEF_COVER, BUFF_EFFECT_SNOW_COVER)
        removeCover(areaCache, delCoverTypes, player, session)

        // 活动boss存在，迁到雪地就上雪地保护罩
        val activityBoss = areaCache.activityBossCache.findActivityBossByXy(
            monsterLocationProto.baseIndex[0],
            monsterLocationProto.baseIndex[1]
        )
        if (activityBoss != null && activityBoss.nowHp > 0) {
            val snowCoverBuffId = pcs.basicProtoCache.specialActivityProtect
            val proto = pcs.buffBasicProtoCache.protoMap[snowCoverBuffId]
            if (proto == null) {
                assert(false) { "找不到雪地保护罩buff$snowCoverBuffId" }
            } else {
                val buffTime = proto.time * 1000
                if (buffTime != 0) {
                    val buffOverTime = getNowTime() + buffTime
                    wpm.es.fire(
                        areaCache, player.id, GET_BUFF,
                        GetNewBuff(player.id, snowCoverBuffId, buffOverTime)
                    )
                }
            }
        }
    }
}
