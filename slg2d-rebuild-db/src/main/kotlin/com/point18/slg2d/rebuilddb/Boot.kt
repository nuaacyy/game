package com.point18.slg2d.rebuilddb

import com.point18.slg2d.common.baseg.ClusterRole
import com.point18.slg2d.common.baseg.GameWorldShard
import com.point18.slg2d.common.baseg.ProcessMgr
import com.point18.slg2d.common.constg.PROCESS_WORLD

open class RebuildDbProcess : ProcessMgr(PROCESS_WORLD, ClusterRole.world) {

    override val includingShards = listOf(GameWorldShard.world)

    init {
        println("RebuildDbProcess init")
    }

    fun rebuildDb() {
        // 解析配置
        initZk("cfg/startconfig.json")

        // 初始化数据库访问
        initCommonDao {
            if (startConfig.createTable) {
                it.setProperty("hbm2ddl.auto", "create");
                it.setProperty("hibernate.hbm2ddl.auto", "create");
            }
        }
    }

    override fun beforeCreatingActorSystem() {
    }

    override fun afterCreatingActorSystem() {

    }

}

// 世界进程单例
var pm = object : RebuildDbProcess() {

}