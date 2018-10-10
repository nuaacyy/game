package com.point18.slg2d.mgr.updatedata

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.zkdomain.v1.*
import com.point18.slg2d.mgr.jsonHelper.toJson
import com.point18.slg2d.mgr.jsonHelper.toObj
import com.point18.slg2d.mgr.mpm
import xyz.ariane.util.lzInfo

class UpdateZkDataTo1 : UpdateZkData {

    override fun update() {

        val targetVersion = 1

        normalLog.lzInfo { "更新数据到版本 $targetVersion" }

        // 更新MgrInfo
        normalLog.lzInfo { "开始更新数据到版本 $MGR_INFO_NODE_NAME" }
        val mgrInfoStr = mpm.dao.findNodeData(MGR_INFO_NODE_NAME)
        if (mgrInfoStr != null) {
            val mgrInfo = toObj<MgrInfo>(mgrInfoStr)
            mgrInfo.dataVersion = targetVersion
            mgrInfo.version = targetVersion
            mpm.dao.createOrUpdateData(
                MGR_INFO_NODE_NAME,
                    toJson(mgrInfo)
            )
        }

        // 更新进程配置
        normalLog.lzInfo { "开始更新数据到版本 $PROCESS_NODE_NAME" }
        val processes = findAllProcesses()
        processes.forEach { eachProcess ->
            eachProcess.dataVersion = targetVersion
            mpm.dao.createOrUpdateData(
                    "$PROCESS_NODE_NAME/${eachProcess.processIp}/${eachProcess.processType}",
                    toJson(eachProcess)
            )
        }

        // 更新平台配置
        normalLog.lzInfo { "开始更新数据到版本 $PLATFORM_NODE_NAME" }
        val allPlat = mutableListOf<Platform>()
        mpm.dao.findNodeDataOfChildren(PLATFORM_NODE_NAME).forEach {
            allPlat.add(toObj(it))
        }
        allPlat.forEach { eachPlat ->
            eachPlat.dataVersion = targetVersion
            mpm.dao.createOrUpdateData("$PLATFORM_NODE_NAME/${eachPlat.id}", toJson(eachPlat))
        }

        // 更新集群配置
        normalLog.lzInfo { "开始更新数据到版本 $CLUSTER_NODE_NAME" }
        val allCommCfg = mutableListOf<CommCfg>()
        mpm.dao.findNodeDataOfChildren(CLUSTER_NODE_NAME).forEach {
            allCommCfg.add(toObj(it))
        }
        allCommCfg.forEach { eachCluster ->
            eachCluster.dataVersion = targetVersion
            mpm.dao.createOrUpdateData("$CLUSTER_NODE_NAME/${eachCluster.id}", toJson(eachCluster))
        }

        // 更新游戏区配置
        normalLog.lzInfo { "开始更新数据到版本 $WORLD_AREA_NODE_NAME" }
        val worldAreas = mutableListOf<WorldArea>()
        mpm.dao.findNodeDataOfChildren(WORLD_AREA_NODE_NAME).forEach {
            worldAreas.add(toObj(it))
        }
        worldAreas.forEach { eachWorld ->
            eachWorld.dataVersion = targetVersion
            mpm.dao.createOrUpdateData("$WORLD_AREA_NODE_NAME/${eachWorld.id}", toJson(eachWorld))
        }

    }

    private fun findAllProcesses(type: Int? = null): MutableList<Process> {
        val allProcess = mutableListOf<Process>()
        if (type != null && type != PROCESS_WORLD &&
                type != PROCESS_HOME &&
                type != PROCESS_CHAT &&
                type != PROCESS_PUBLIC &&
                type != PROCESS_GATE
        ) {
            return allProcess
        }

        val childNodes = mpm.dao.findNodeNameOfChildren(PROCESS_NODE_NAME) // find all ip nodes
        if (type == null) {
            // 不指定设备类型，就找出全部类型的process
            childNodes.forEach { it ->
                val tmpPath1 = "$PROCESS_NODE_NAME/$it/$PROCESS_WORLD"
                val tmpPath2 = "$PROCESS_NODE_NAME/$it/$PROCESS_HOME"
                val tmpPath3 = "$PROCESS_NODE_NAME/$it/$PROCESS_CHAT"
                val tmpPath4 = "$PROCESS_NODE_NAME/$it/$PROCESS_PUBLIC"
                val tmpPath5 = "$PROCESS_NODE_NAME/$it/$PROCESS_GATE"
                val tmpProcess1 = mpm.dao.findNodeData(tmpPath1)
                val tmpProcess2 = mpm.dao.findNodeData(tmpPath2)
                val tmpProcess3 = mpm.dao.findNodeData(tmpPath3)
                val tmpProcess4 = mpm.dao.findNodeData(tmpPath4)
                val tmpProcess5 = mpm.dao.findNodeData(tmpPath5)
                if (tmpProcess1 != null) {
                    allProcess.add(toObj(tmpProcess1))
                }
                if (tmpProcess2 != null) {
                    allProcess.add(toObj(tmpProcess2))
                }
                if (tmpProcess3 != null) {
                    allProcess.add(toObj(tmpProcess3))
                }
                if (tmpProcess4 != null) {
                    allProcess.add(toObj(tmpProcess4))
                }
                if (tmpProcess5 != null) {
                    allProcess.add(toObj(tmpProcess5))
                }
            }

        } else {
            // 指定设备类型，就找出对应类型的process
            childNodes.forEach { it ->
                val tmpPath = "$PROCESS_NODE_NAME/$it/$type"
                val tmpProcess = mpm.dao.findNodeData(tmpPath)
                if (tmpProcess != null) {
                    allProcess.add(toObj(tmpProcess))
                }
            }
        }

        return allProcess
    }
}
