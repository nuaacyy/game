package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.constg.Add
import com.point18.slg2d.common.constg.Del
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.P2HTell
import pb4server.Public2HomeTell

class OccupyWonderDeal : P2HTell, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {
    override fun dealPublicTell(session: PlayerActor, playerId: Long, msg: Public2HomeTell) {
        val tell = msg.occupyWonderTell
        val occupyWonderInfoList = tell.occupyWonderInfoList
        val changeType = tell.changeType

        prepare(session) { homePlayerDC: HomePlayerDC ->
            val player = homePlayerDC.player

            when (changeType) {
                Add -> {
                    for (occupyWonder in occupyWonderInfoList) {
                        val wonderProtoMap =
                            player.allianceOccupyInfo.getOrPut(occupyWonder.worldId) { hashMapOf() }
                        for (wonderProtoId in occupyWonder.wonderIdsList) {
                            wonderProtoMap[wonderProtoId] = 1
                        }
                    }
                }
                Del -> {
                    for (occupyWonder in occupyWonderInfoList) {
                        val wonderProtoMap = player.allianceOccupyInfo[occupyWonder.worldId] ?: return@prepare
                        for (wonderProtoId in occupyWonder.wonderIdsList) {
                            wonderProtoMap.remove(wonderProtoId)
                        }
                        if (wonderProtoMap.size == 0) {
                            player.allianceOccupyInfo.remove(occupyWonder.worldId)
                        }
                    }
                }
            }
        }
    }
}