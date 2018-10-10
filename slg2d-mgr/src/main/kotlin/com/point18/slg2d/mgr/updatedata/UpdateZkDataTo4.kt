package com.point18.slg2d.mgr.updatedata

import com.point18.slg2d.common.commonfunc.ZkTransaction
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.zkdomain.v4.*
import com.point18.slg2d.mgr.jsonHelper.toJson
import com.point18.slg2d.mgr.jsonHelper.toObj
import com.point18.slg2d.mgr.mpm
import xyz.ariane.util.lzInfo

class UpdateZkDataTo4 : UpdateZkData {

    override fun update() {

        val targetVersion = 4

        normalLog.lzInfo { "更新数据到版本 $targetVersion" }

        val transaction = ZkTransaction(mpm.dao.zkClient())

        // 更新集群配置
        normalLog.lzInfo { "开始更新数据到版本 $PROCESS_NODE_NAME" }
        val processes = findAllProcesses()
        processes.forEach { eachProcess ->
            val newProcess = ServerProcess(
                eachProcess.id,
                eachProcess.processIp,
                eachProcess.procesName,
                eachProcess.httpAddr,
                eachProcess.httpPort,
                eachProcess.tcpAddr,
                eachProcess.tcpPort,
                eachProcess.rpcPort,
                eachProcess.platformId,
                eachProcess.processType,
                eachProcess.kafkaPId,
                eachProcess.processNum,
                eachProcess.clusterId,
                eachProcess.seedNode
            )
            newProcess.dataVersion = targetVersion

            transaction.setData(
                "$PROCESS_NODE_NAME/${newProcess.processIp}/${newProcess.processType}",
                toJson(newProcess)
            )
        }

        // 更新MgrInfo
        normalLog.lzInfo { "开始更新数据到版本 $MGR_INFO_NODE_NAME" }
        val mgrInfoStr = mpm.dao.findNodeData(MGR_INFO_NODE_NAME)
        if (mgrInfoStr != null) {
            val mgrInfo = toObj<MgrInfo>(mgrInfoStr)
            mgrInfo.dataVersion = targetVersion
            mgrInfo.version = targetVersion
            transaction.setData(
                MGR_INFO_NODE_NAME,
                toJson(mgrInfo)
            )
        }

        transaction.commit()?.forEach {
            normalLog.lzInfo { "${it.forPath}-${it.type}" }
        }
    }

    private fun findAllProcesses(type: Int? = null): MutableList<com.point18.slg2d.common.zkdomain.v3.ServerProcess> {
        val allProcess = mutableListOf<com.point18.slg2d.common.zkdomain.v3.ServerProcess>()
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
