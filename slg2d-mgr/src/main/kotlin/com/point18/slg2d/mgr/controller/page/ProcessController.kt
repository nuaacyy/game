package com.point18.slg2d.mgr.controller.page

import com.point18.slg2d.common.constg.CLUSTER_NODE_NAME
import com.point18.slg2d.common.constg.PLATFORM_NODE_NAME
import com.point18.slg2d.common.constg.PROCESS_NODE_NAME
import com.point18.slg2d.common.resultcode.Code
import com.point18.slg2d.common.zkdomain.CommCfg
import com.point18.slg2d.common.zkdomain.Platform
import com.point18.slg2d.common.zkdomain.ServerProcess
import com.point18.slg2d.mgr.jsonHelper.toJson
import com.point18.slg2d.mgr.jsonHelper.toObj
import com.point18.slg2d.mgr.mpm
import com.point18.slg2d.mgr.resp.MgrGateBaseResp
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1", produces = ["application/json"])
open class ProcessController {

    data class AddGameProcessReq(
        val processName: String,
        val processIp: String,
        val platId: Long,
        val httpPort: Int,
        val tcpPort: Int,
        val seedPort: Int,
        val processType: Int,
        val processWebAddr: String,
        val processTcpAddr: String,
        val processNum: Int,
        val kafkaPId: Int,
        val clusterId: Long,
        val seedNode: Int
    )

    @PostMapping(value = ["/addServer"])
    open fun addProcess(@RequestBody param: AddGameProcessReq): MgrGateBaseResp {

        val allProcess = mpm.dao.findAllProcesses()

        // 验证 进程所属分区ID 是否存在
        val isExist = mpm.dao.findNodeData("$PROCESS_NODE_NAME/${param.processIp}/${param.processType}")
        if (isExist != null && isExist.length > 1) {
            return MgrGateBaseResp(Code.PROCESS_HAS_EXIST.code, Code.PROCESS_HAS_EXIST.errMsg, null)
        }

        // 验证进程号， 必须大于1小于10！
        val processNum = param.processNum
        if (processNum < 1 || processNum > 10) {
            return MgrGateBaseResp(Code.PROCESSNUM_NOT_MATCH.code, Code.PROCESSNUM_NOT_MATCH.errMsg, null)
        }

        val platForm = mpm.dao.findNodeData("$PLATFORM_NODE_NAME/${param.platId}")
        if (platForm == null || platForm.isNullOrBlank()) {
            return MgrGateBaseResp(Code.PLATFORM_NOT_FOUND.code, Code.PLATFORM_NOT_FOUND.errMsg, null)
        }

        val id = mpm.dao.genId() ?: return MgrGateBaseResp(Code.ID_GEN_ERR.code, Code.ID_GEN_ERR.errMsg, null)

        val process = ServerProcess(
            id.toLong(),
            param.processIp,
            param.processName,
            param.processIp,
            param.httpPort,
            param.processTcpAddr,
            param.tcpPort,
            param.seedPort,
            param.platId,
            param.processType,
            param.kafkaPId,
            param.processNum,
            param.clusterId,
            param.seedNode
        )

        val allClusters = mutableListOf<CommCfg>()
        mpm.dao.findNodeDataOfChildren(CLUSTER_NODE_NAME).forEach {
            allClusters.add(toObj(it))
        }
        val cluster = allClusters.firstOrNull { it.id == process.clusterId }
        if (cluster == null) {
            return MgrGateBaseResp(Code.COMM_CFG_NOT_FOUND.code, Code.COMM_CFG_NOT_FOUND.errMsg, null)
        }
        mpm.dao.createOrUpdateData(
            "$PROCESS_NODE_NAME/${param.processIp}/${process.processType}",
            toJson(process)
        )
        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, null)
    }

    @GetMapping(value = ["/delProcess"])
    open fun delProcess(ip: String, processType: Int): MgrGateBaseResp {
        val clearNode = "$PROCESS_NODE_NAME/$ip/$processType"
        val rt = mpm.dao.clear(clearNode)  // 清理这个process对应的ip节点数据
        if (!rt) {
            // 进程配置不存在
            return MgrGateBaseResp(Code.PROCESS_NOT_FOUND.code, Code.PROCESS_NOT_FOUND.errMsg, null)
        }

        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, null)
    }

    data class ProcessResp(
        var id: Long,
        var processIp: String,
        var processName: String,
        var processWebAddr: String,
        var httpPort: Int,
        var processTcpAddr: String,
        var tcpPort: Int,
        var seedPort: Int,
        var platName: String,
        var processType: Int,
        var kafkaPId: Int,
        var processNum: Int,
        var areaName: String,
        var platId: Long,
        var recommandKafkaPId: Int,
        var clusterId: Long,
        var seedNode: Int
    )

    @GetMapping(value = ["/queryServer"])
    open fun queryProcess(): MgrGateBaseResp {
        val processesResp = mutableListOf<ProcessResp>()

        // 找到所有平台
        val allPlatform = mutableListOf<Platform>()
        mpm.dao.findNodeDataOfChildren(PLATFORM_NODE_NAME).forEach {
            allPlatform.add(toObj(it))
        }

        // 找到所有的进程
        val allProcess = mpm.dao.findAllProcesses()
        for (process in allProcess) {
            val platform = allPlatform.firstOrNull { it.id == process.platformId }
            val platformName = if (platform == null) {
                "沒有"
            } else {
                platform.name
            }

            val areaName = "没有"
            val processResp = ProcessResp(
                process.id,
                process.processIp,
                process.procesName,
                process.httpAddr,
                process.httpPort,
                process.tcpAddr,
                process.tcpPort,
                process.seedPort,
                platformName,
                process.processType,
                process.kafkaPId,
                process.processNum,
                areaName,
                process.platformId,
                0,
                process.clusterId,
                process.seedNode
            )
            processesResp.add(processResp)
        }

        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, processesResp)
    }

    data class UpdateGameProcessReq(
        val id: Long,
        val processName: String = "",
        val processIp: String = "",
        val platId: String = "",
        val httpPort: Int,
        val tcpPort: Int,
        val seedPort: Int,
        val areaId: Int,
        val pltAreaNo: Int,
        val processType: Int,
        val processWebAddr: String = "",
        val processTcpAddr: String = "",
        val startLock: String = "",
        val processNum: Int,
        val platName: String = "",
        val areaName: String = "",
        val kafkaPId: Int,
        val clusterId: Long,
        val seedNode: Int
    )

    /**
     * 进程是根据IP和类型记录的，所以这里禁止修改IP和类型
     */
    @PostMapping(value = ["/updateServer"])
    open fun updateProcess(@RequestBody param: UpdateGameProcessReq): MgrGateBaseResp {
        // 验证 进程所属分区ID，必须是10的整数倍！
        val kafkaPId = param.kafkaPId
        if (kafkaPId % 10 != 0 || kafkaPId > 1000) {
            return MgrGateBaseResp(Code.KAFKAPID_NOT_MATCH.code, Code.KAFKAPID_NOT_MATCH.errMsg, null)
        }

        // 验证进程号， 必须大于1小于10！
        val processNum = param.processNum
        if (processNum < 1 || processNum > 10) {
            return MgrGateBaseResp(Code.PROCESSNUM_NOT_MATCH.code, Code.PROCESSNUM_NOT_MATCH.errMsg, null)
        }

        // 验证 进程所属集群存在
        val existCommCfg = mpm.dao.findNodeData("$CLUSTER_NODE_NAME/${param.clusterId}")
        if (existCommCfg == null) {
            return MgrGateBaseResp(Code.COMM_CFG_NOT_FOUND.code, Code.COMM_CFG_NOT_FOUND.errMsg, null)
        }

        // 验证 进程所属分区ID 是否存在
        val processStr = mpm.dao.findNodeData("$PROCESS_NODE_NAME/${param.processIp}/${param.processType}")
        if (processStr == null) {
            return MgrGateBaseResp(Code.PROCESS_NOT_FOUND.code, Code.PROCESS_NOT_FOUND.errMsg, null)
        }
        val process = toObj<ServerProcess>(processStr)

        val platForm = mpm.dao.findNodeData("$PLATFORM_NODE_NAME/${param.platId}")
        if (platForm == null) {
            return MgrGateBaseResp(Code.PLATFORM_NOT_FOUND.code, Code.PLATFORM_NOT_FOUND.errMsg, null)
        }

        val cluster = mpm.dao.findNodeData("$CLUSTER_NODE_NAME/${process.clusterId}")
        if (cluster == null) {
            return MgrGateBaseResp(Code.COMM_CFG_NOT_FOUND.code, Code.COMM_CFG_NOT_FOUND.errMsg, null)
        }

        // 更新进程配置
        // 注意，进程配置依赖IP和类型，所有这两个值是无法修改的，需要新的话，直接创建。
        process.kafkaPId = param.kafkaPId
        process.httpAddr = param.processWebAddr
        process.httpPort = param.httpPort
        process.platformId = param.platId.toLong()
        process.procesName = param.processName
        process.processNum = param.processNum
        process.seedPort = param.seedPort
        process.tcpAddr = param.processTcpAddr
        process.tcpPort = param.tcpPort
        process.clusterId = param.clusterId
        process.seedNode = param.seedNode

        val node = "$PROCESS_NODE_NAME/${process.processIp}/${process.processType}"
        mpm.dao.createOrUpdateData(node, toJson(process))
        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, null)
    }
}