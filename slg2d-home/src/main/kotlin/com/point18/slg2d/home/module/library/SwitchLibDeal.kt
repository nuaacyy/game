package com.point18.slg2d.home.module.library

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.*

class SwitchLibDeal(
) : HomeClientMsgDeal,
    HomeHelperPlus1<LibraryDC>(
        LibraryDC::class.java
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { libraryDC ->
            val rtBuilder = this.getLibraryInfo(msg as SwitchLibTag, libraryDC)
            session.sendMsg(MsgType.SwitchLibTag_1556, rtBuilder.build())
        }
    }

    private fun getLibraryInfo(msg: SwitchLibTag, libraryDC: LibraryDC): SwitchLibTagRt.Builder {
        val rtBuilder = SwitchLibTagRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val type = msg.type // 协议中规定的标签页类型 1-装备 2-道具 3-卡片 4-魔物 5-怪物

        val library = libraryDC.library

        var prop: HashMap<Int, CommonLibVo>? = null
        //var qualityProp: MutableMap<Int, MutableSet<Int>>? = null
        var boss: HashMap<Int, HashMap<Int, BossLibVo>>? = null
        var monster: HashMap<Int, HashMap<Int, MonsterLibVo>>? = null

        // 红点
        when (type) {
            com.point18.slg2d.common.constg.LIB_EQUIP -> {
                prop = library.equip            // 装备
                library.newItem.remove(com.point18.slg2d.common.constg.LIB_EQUIP)
            }
            com.point18.slg2d.common.constg.LIB_PROP -> {
                prop = library.prop             // 道具
                library.newItem.remove(com.point18.slg2d.common.constg.LIB_PROP)
            }
            com.point18.slg2d.common.constg.LIB_CARD -> {
                prop = library.card             // 卡片
                library.newItem.remove(com.point18.slg2d.common.constg.LIB_CARD)
            }
            com.point18.slg2d.common.constg.LIB_BOSS -> {
                boss = library.boss             // 魔物
                library.newItem.remove(com.point18.slg2d.common.constg.LIB_BOSS)
            }
            com.point18.slg2d.common.constg.LIB_MONSTER -> {
                monster = library.monster       // 怪物
                library.newItem.remove(com.point18.slg2d.common.constg.LIB_MONSTER)
            }
            else -> {
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder
            }
        }

        when {
            prop != null -> addPropLibInfo(rtBuilder, prop)
        //qualityProp != null -> addQualityPropLibInfo(rtBuilder, qualityProp)
            monster != null -> addMonsterLibInfo(rtBuilder, monster)
            boss != null -> addBossLibInfo(rtBuilder, boss)
        }

        return rtBuilder
    }

    private fun addBossLibInfo(
        rtBuilder: SwitchLibTagRt.Builder,
        boss: MutableMap<Int, HashMap<Int, BossLibVo>>
    ) {
        for ((libraryType, subMap) in boss) {
            val libInfoBuilder = BossLibInfo.newBuilder()
            libInfoBuilder.libraryType = libraryType
            for ((protoId, libInfo) in subMap) {
                val bossInfoBuilder = BossInfo.newBuilder()
                bossInfoBuilder.protoId = protoId
                bossInfoBuilder.attackNum = libInfo.attackNum
                bossInfoBuilder.killNum = libInfo.killNum
                libInfoBuilder.addBossInfo(bossInfoBuilder)
            }
            rtBuilder.addBossLibItems(libInfoBuilder)
        }
    }

    private fun addMonsterLibInfo(
        rtBuilder: SwitchLibTagRt.Builder,
        monster: MutableMap<Int, HashMap<Int, MonsterLibVo>>
    ) {
        for ((libraryType, subMap) in monster) {
            val libInfoBuilder = MonsterLibInfo.newBuilder()
            if (subMap.isEmpty()) {
                return
            }

            for ((protoId, _) in subMap) {
                libInfoBuilder.protoId = protoId
                break
            }

            var killNum = 0
            for ((_, libInfo) in subMap) {
                libInfoBuilder.libraryType = libraryType
                killNum += libInfo.killNum
            }
            libInfoBuilder.killNum = killNum

            rtBuilder.addMonsterLibItems(libInfoBuilder)
        }
    }

    private fun addPropLibInfo(
        rtBuilder: SwitchLibTagRt.Builder,
        prop: MutableMap<Int, CommonLibVo>
    ) {
        for (libInfo in prop.values) {
            val libInfoBuilder = LibInfo.newBuilder()
            libInfoBuilder.protoId = libInfo.protoId
            libInfoBuilder.addAllProtoIds(libInfo.protoIds)
            rtBuilder.addLibItems(libInfoBuilder)
        }
    }
}