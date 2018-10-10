package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class AllianceTreRefreshResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<AllianceTreRefreshProto>
): Serializable

data class AllianceTreRefreshProto(
    val id: Int, // 编号
    val taskNum: Int, // 任务数量
    val dropTask: String, // 方案具体内容
    val luckTask: String // 幸运值满的方案
): Serializable {
    var dropTaskMap: Map<String, Int> = mapOf()// // 方案具体内容
    var luckTaskMap: Map<Int, Int> = mapOf()// 幸运值满的方案

}

class AllianceTreRefreshProtoCache : ProtoCacheInit("allianceTreRefresh.xml") {
    var allianceTreRefreshProtoMap: Map<Int, AllianceTreRefreshProto> = mapOf()//

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<AllianceTreRefreshResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as AllianceTreRefreshResult

        val tmpAllianceTreRefreshProtoMap: HashMap<Int, AllianceTreRefreshProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            if (tmpAllianceTreRefreshProtoMap.containsKey(vo.taskNum)) {
                throw RuntimeException("allianceTreRefreshLucky.xml :: taskNum[${vo.taskNum}]重复")
            }

            vo.dropTaskMap = hashMapOf()
            val resStringToDropBagFunRet = resStringToDropBag(vo.dropTask)
            if (!resStringToDropBagFunRet.ok) {
                throw RuntimeException("allianceTreRefresh.xml中的DropTask字段配置出错:${vo.dropTask}")
            }
            if (resStringToDropBagFunRet.int < 10000) {
                throw RuntimeException("allianceTreRefresh.xml中的DropTask字段总权值少于10000,当前值是:${resStringToDropBagFunRet.int}")
            }
            val resStringToDropBagFunRetmap =
                resStringToDropBagFunRet.map ?: throw RuntimeException("allianceTreRefresh.xml中的DropTask字段error")
            vo.dropTaskMap = resStringToDropBagFunRetmap
            val luckTaskMap = hashMapOf<Int, Int>()
            for (luckTasks in vo.luckTask.split("|")) {
                val luckInfo = luckTasks.split(";")
                if (luckInfo.size != 2) {
                    throw RuntimeException("allianceTreRefresh.xml中的LuckTask字段解析出来的长度不为2:$luckTasks")
                }

                val luckId = (luckInfo[0].toIntOrNull())     // 幸运值档
                val luckDropId = (luckInfo[1].toIntOrNull()) // 幸运值触发的掉落
                if (luckDropId != null && luckId != null) {
                    luckTaskMap[luckId] = luckDropId
                } else {
                    throw RuntimeException("allianceTreRefresh.xml中的LuckTask字段解析error")
                }
            }
            vo.luckTaskMap = luckTaskMap
            tmpAllianceTreRefreshProtoMap[vo.taskNum] = vo
        }
        this.allianceTreRefreshProtoMap = tmpAllianceTreRefreshProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, info) in this.allianceTreRefreshProtoMap) {
            for ((_, luckyId) in info.dropTaskMap) {
                pcs.allianceTreRefreshLuckyProtoCache.allianceTreRefreshLuckyProtoMap[luckyId]
                    ?: throw RuntimeException("allianceTreRefresh.xml中的dropTask字段中随到的值找不到配置:$luckyId")
            }
            for ((_, lId) in info.luckTaskMap) {
                pcs.allianceTreRefreshLuckyProtoCache.allianceTreRefreshLuckyProtoMap[lId]
                    ?: throw RuntimeException("allianceTreRefresh.xml中的LuckTask字段中随到的值找不到配置:$lId")

            }
        }
    }

    // 根据玩家幸运值来查看是否要作弊
    fun findLuckAllianceTrePlan(luckTaskMap: MutableMap<Int, Int>, luckNum: Int): Int {
        if (luckNum == 0) {
            return 0
        }
        var nowMaxLuckId = 0
        var nowLuckDropId = 0

        for ((luckId, luckDropId) in luckTaskMap) {

            if (luckId < nowMaxLuckId) {
                continue
            }

            if (luckNum % luckId == 0) {
                // 整除,这一档被触发
                nowMaxLuckId = luckNum
                nowLuckDropId = luckDropId
            }
        }

        return nowLuckDropId
    }

}
