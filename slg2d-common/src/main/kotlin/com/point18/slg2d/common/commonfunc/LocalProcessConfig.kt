package com.point18.slg2d.common.commonfunc

import java.util.*

val processConfig = LocalProcessConfig()

// 本机进程配置
class LocalProcessConfig {
    private val processConfigMutex: LockObj = LockObj()

    var gameId: Int = 0 // 游戏ID

    var id: Int = 0 // 进程配置的唯一ID

    var clusterId: Long = 0L // 集群ID

    private val areaConfigs = HashMap<Long, AreaConfig>() // 各个游戏区

    var processInnerAddr: String = "" // 服务器间访问用的进程地址

    var tcpAddr: String = "" // 服务器对外地址 socket
    var tcpPort: Int = 0    // 长连接端口

    var isCheckText: Int = 1  // 是否要检测屏蔽字

    // 找到特定游戏区的配置
    fun findSpecAreaConfig(pltAreaNo: Long): AreaConfig? {
        synchronized(processConfigMutex) {
            return areaConfigs[pltAreaNo]
        }
    }

    // 找到特定游戏区的配置
    fun findSpecAreaConfig(areaNo: Int): AreaConfig? {
        synchronized(processConfigMutex) {
            areaConfigs.forEach {
                if (it.value.areaNo == areaNo) {
                    return it.value
                }
            }
            return null
        }
    }

    //查找所有游戏区配置 (list格式)
    fun findAllAreaConfig(): List<AreaConfig> {
        synchronized(processConfigMutex) {
            return areaConfigs.values.toList()
        }
    }

    //查找所有游戏区配置 (map格式  key<worldId>)
    fun findAllAreaConfigByMap(): HashMap<Long, AreaConfig> {
        synchronized(processConfigMutex) {
            return areaConfigs
        }
    }

    // 添加特定区配置
    fun addSpecAreaCOnfig(areaConfig: AreaConfig) {
        synchronized(processConfigMutex) {
            areaConfigs[areaConfig.pltAreaNo] = areaConfig
        }
    }
}