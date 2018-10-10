package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.common.commonfunc.getRandInt
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.*

class GmCreatePlayerGo : GmCommand {
    override fun exec(session: PlayerSession, message: String): Int {
        val areaCache = session.areaCache
        //这个东西是用来往数据库中插数据的,不一定正确数据,只要能起服务器
        for (i in 1..10000000) {
            //查找可用位置
            val (x, y) = findFreeCastlePosForGm(areaCache, 21)
            if (x != -1 && y != -1) {
                createRobot(areaCache, x * 10000 + y, x, y, true)
            }

            val (x2, y2) = findFreeCastlePosForGm(areaCache, 27)
            if (x2 != -1 && y2 != -1) {
                createRobot(areaCache, x2 * 10000 + y2, x2, y2, true)
            }

            val (x3, y3) = findFreeCastlePosForGm(areaCache, 38)
            if (x3 != -1 && y3 != -1) {
                createRobot(areaCache, x3 * 10000 + y3, x3, y3, true)
            }

            val (x4, y4) = findFreeCastlePosForGm(areaCache, 44)
            if (x4 != -1 && y4 != -1) {
                createRobot(areaCache, x4 * 10000 + y4, x4, y4, true)
            }
//
//            val (x5, y5) = gamecommon.findFreeCastlePosForGm(areaCache, 78)
//            if (x5 != -1 && y5 != -1) {
//                createRobot(areaCache, x5 * 10000 + y5, x5, y5, true)
//            }

            // 1号生态自然玩家城堡
            val (x6, y6) = findFreeCastlePosForGm(areaCache, 1)
            if (x6 != -1 && y6 != -1) {
                createRobot(areaCache, x6 * 10000 + y6, x6, y6, false)
            }

            if (i == 1) {
//                // 2号生态全是魔物
                var xyMap = getFarmInfoByGridId(2, pcs.basicProtoCache.resourceArea) // 获取到这个小方块里所有的XY
                for ((_, info) in xyMap) {
                    if (!checkFreeCellWithCastleForGm(areaCache, info.x, info.y)) {
                        continue
                    }

                    val normalCell = checkNormalCell(info.x, info.y)
                    if (!normalCell) {
                        continue
                    }

                    val freeCell = checkFreeCell(areaCache, info.x, info.y, 0)
                    if (freeCell) {
                        // 找到空地了 放一个魔物进去 随机魔物
                        val bossId = pcs.monsterProtoCache.fetchRandomMonster().id // TODO 这边什么意思？拿到的bossId不用？
                        refreshMonsterInfo(areaCache, bossId, info.x, info.y)
                    }
                }

                // 3号生态全是资源点
                xyMap.clear()
                xyMap = getFarmInfoByGridId(3, pcs.basicProtoCache.resourceArea) // 获取到这个小方块里所有的XY
                for ((_, info) in xyMap) {
                    if (!checkFreeCellWithCastleForGm(areaCache, info.x, info.y)) {
                        continue
                    }

                    val normalCell = checkNormalCell(info.x, info.y)
                    if (!normalCell) {
                        continue
                    }

                    val freeCell = checkFreeCell(areaCache, info.x, info.y, 0)
                    if (freeCell) {
                        // 找到空地了
                        val randRes = getRandInt(pcs.resPointProtoCache.resPointList.size)
                        val resProto = pcs.resPointProtoCache.resPointList[randRes]
                        areaCache.commonResCache.createCommonRes(
                            info.x,
                            info.y,
                            resProto.id,
                            resProto.resTotal,
                            resProto.level
                        )
                    }
                }
            }

            if (x == -1 && y == -1 &&
                x2 == -1 && y2 == -1 &&
                x3 == -1 && y3 == -1 &&
                x4 == -1 && y4 == -1
            //x5 == -1 && y5 == -1
            ) {
                com.point18.slg2d.common.commonfunc.normalLog.error("GM执行创建角色--寻找落脚点失败:$i")
                break
            }
        }

        return 1
    }
}