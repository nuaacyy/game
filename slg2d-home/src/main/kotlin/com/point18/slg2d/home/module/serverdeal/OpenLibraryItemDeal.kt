package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.BossLibVo
import com.point18.slg2d.home.dc.LibraryDC
import com.point18.slg2d.home.dc.MonsterLibVo
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.W2HAsk
import com.point18.slg2d.home.module.event.GetNewLibraryEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createNewLibraryItemNotifier
import pb4server.OpenLibraryItemAskRt
import pb4server.World2HomeAskReq
import pb4server.World2HomeAskResp

class OpenLibraryItemDeal : W2HAsk, HomeHelperPlus1<LibraryDC>(
    LibraryDC::class.java
) {

    override fun dealWorldAsk(session: PlayerActor, req: World2HomeAskReq, resp: World2HomeAskResp.Builder) {
        prepare(session) { libraryDC ->
            val rt = OpenLibraryItemAskRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code

            val msg = req.openLibraryItemAskReq
            val type = msg.type
            val protoId = msg.protoId

            if (type == OPEN_MONSTER_LIB_ITEM) {
                openMonsterLibItem(libraryDC, session, protoId)
            } else if (type == OPEN_BOSS_LIB_ITEM) {
                val kill = msg.kill

                val isKill = if (kill == MONSTER_LIB_KILL) {
                    true
                } else if (kill == MONSTER_LIB_ATK) {
                    false
                } else {
                    rt.rt = ResultCode.PARAMETER_ERROR.code
                    resp.setOpenLibraryItemAskRt(rt)
                    return@prepare
                }

                openBossLibItem(libraryDC, session, type, protoId, isKill)
            }

            resp.setOpenLibraryItemAskRt(rt)
        }
    }

    // 点亮魔物图鉴
    private fun openBossLibItem(libraryDC: LibraryDC, session: PlayerActor, type: Int, protoId: Int, kill: Boolean) {
        val library = libraryDC.library
        val bossItem = library.boss

        val libraryType = when (type) {
            OPEN_BOSS_LIB_ITEM -> {
                val monsterProto =
                    pcs.monsterProtoCache.findMonsterProto(protoId) ?: return
                monsterProto.libraryType
            }
            else -> {
                null
            }
        } ?: return

        val libInfo = bossItem[libraryType]
        if (libInfo == null) {
            val protoIdLibInfoMap = hashMapOf<Int, BossLibVo>()
            val bossLibInfo = if (kill) {
                BossLibVo(protoId, 1, 0)
            } else {
                BossLibVo(protoId, 0, 1)
            }
            protoIdLibInfoMap[protoId] = bossLibInfo
            bossItem[libraryType] = protoIdLibInfoMap
            library.newItem.add(LIB_BOSS)
            createNewLibraryItemNotifier(library.newItem).notice(session)
        } else {
            val bossLibInfo = libInfo[protoId]
            if (bossLibInfo == null) {
                val newBossLibInfo = if (kill) {
                    BossLibVo(protoId, 1, 0)
                } else {
                    BossLibVo(protoId, 0, 1)
                }
                libInfo[protoId] = newBossLibInfo
            } else {
                if (kill) {
                    bossLibInfo.killNum = bossLibInfo.killNum + 1
                } else {
                    bossLibInfo.attackNum = bossLibInfo.attackNum + 1
                }
            }
        }

        fireEvent(session, GetNewLibraryEvent())
    }

    // 点亮怪物图鉴 protoId 关卡配置Id
    private fun openMonsterLibItem(libraryDC: LibraryDC, session: PlayerActor, protoId: Int) {
        val library = libraryDC.library
        val monsterItem = library.monster

        val instanceProto = pcs.instanceProtoCache.protoMap[protoId] ?: return  //关卡配置
        val monsterProtoIdNumMap = mutableMapOf<Int, Int>()  // 怪物模板Id和关卡中的数量
        for (unitTeamProtoId in instanceProto.unitTeamList) {   // 遍历怪物配置(unitTeam.xml)的Id
            val unitTeamProto = pcs.unitTeamProtoCache.protoMap[unitTeamProtoId] ?: return  // 怪物配置
            for ((_, unitTeam) in unitTeamProto.gridMap) {
                val unitBaseId = unitTeam.unitProto  // 怪物表(unitBase.xml)的Id
                val monsterNum = monsterProtoIdNumMap[unitBaseId]
                if (monsterNum == null) {
                    monsterProtoIdNumMap[unitBaseId] = 1
                } else {
                    monsterProtoIdNumMap[unitBaseId] = monsterNum + 1
                }
            }
        }

        for ((monsterProtoId, num) in monsterProtoIdNumMap) {
            val monsterProto = pcs.unitBaseCache.protoMap[monsterProtoId] ?: continue
            val libraryType = monsterProto.libraryType
            if (libraryType == 0) {
                continue
            }
            if (monsterProto.npcType != UNIT_TYPE_MONSTER) { // npcType==2 代表是怪物
                continue
            }
            val libInfo = monsterItem[libraryType]
            if (libInfo == null) {
                val subMap = monsterItem.getOrPut(libraryType) { HashMap() }
                subMap[monsterProtoId] = MonsterLibVo(monsterProtoId, num)
                //library.newItem.add(LIB_MONSTER)
                //createNewLibraryItemNotifier(library.newItem).notice(session)
            } else {
                val monsterLibVo = libInfo.getOrPut(monsterProtoId) { MonsterLibVo(monsterProtoId, 0) }
                monsterLibVo.killNum += num
            }
        }

        fireEvent(session, GetNewLibraryEvent())
    }
}