package com.point18.slg2d.world.gmsg

import com.point18.slg2d.common.commonfunc.processConfig
import com.point18.slg2d.world.MoveServerInfoVo
import com.point18.slg2d.world.World2WorldManagerTellDealBase
import com.point18.slg2d.world.actor.WorldManagerActor
import com.point18.slg2d.world.wpm
import pb4server.World2WorldManagerTell

class AddNewAreaTellDeal : World2WorldManagerTellDealBase() {
    override fun dealWorldTell(worldManagerActor: WorldManagerActor, msg: World2WorldManagerTell) {
        val tell = msg.addNewAreaTell

        if (tell.worldId == 0L) {
            // 刚开服 全部缓存起来
            // 根据联盟那边传来的占领情况列出一份跨服列表
            val allAreaConfig = processConfig.findAllAreaConfigByMap()

            // 先填充了已经被占领的服务器
            val haveMasterArea = mutableMapOf<Long, Int>()
            for (r in worldManagerActor.temporaryAreaOcc) {
                if (r.worldIdList.size == 0) {
                    continue
                }

                for (worldId in r.worldIdList) {
                    val areaConfig = allAreaConfig[worldId]
                    if (areaConfig == null) {
                        continue
                    }
                    wpm.getWorldManagerInfoFromPublicManager().allServerInfo[worldId] =
                            MoveServerInfoVo(
                                worldId,
                                areaConfig.areaNo,
                                areaConfig.areaName, // 服务器名字
                                r.allianceMainMemberName,// 服务器国王名
                                r.allianceAreaNo,// 王国所属联盟所在服
                                r.allianceName,// 王国所属联盟名字
                                r.allianceShortName,// 王国所属联盟简称
                                (areaConfig.areaPublishTime / 1000).toInt()
                            )
                    haveMasterArea[worldId] = 1
                }
            }

            for ((worldId, areaConfig) in allAreaConfig) {
                if (haveMasterArea[worldId] != null) {
                    // 这个服务器有主人了 信息已经填充过了
                    continue
                }
                wpm.getWorldManagerInfoFromPublicManager().allServerInfo[worldId] =
                        MoveServerInfoVo(
                            worldId,
                            areaConfig.areaNo,
                            areaConfig.areaName, // 服务器名字
                            "",// 服务器国王名
                            0,// 王国所属联盟所在服
                            "",// 王国所属联盟名字
                            "",// 王国所属联盟简称
                            (areaConfig.areaPublishTime / 1000).toInt()
                        )

            }
        } else {
            // 有新服添加 肯定是没有占领者的
            val areaConfig = processConfig.findSpecAreaConfig(tell.worldId)
            if (areaConfig == null) {
                return
            }
            wpm.getWorldManagerInfoFromPublicManager().allServerInfo[tell.worldId] =
                    MoveServerInfoVo(
                        tell.worldId,
                        areaConfig.areaNo,
                        areaConfig.areaName, // 服务器名字
                        "",// 服务器国王名
                        0,// 王国所属联盟所在服
                        "",// 王国所属联盟名字
                        "",// 王国所属联盟简称
                        (areaConfig.areaPublishTime / 1000).toInt()
                    )
        }
    }
}

