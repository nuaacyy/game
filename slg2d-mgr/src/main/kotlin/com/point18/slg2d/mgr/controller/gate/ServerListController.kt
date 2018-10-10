package com.point18.slg2d.mgr.controller.gate

import com.point18.slg2d.common.constg.PROCESS_GATE
import com.point18.slg2d.common.constg.WORLD_AREA_NODE_NAME
import com.point18.slg2d.common.resultcode.Code
import com.point18.slg2d.common.zkdomain.ServerProcess
import com.point18.slg2d.common.zkdomain.WorldArea
import com.point18.slg2d.mgr.jsonHelper.toObj
import com.point18.slg2d.mgr.mpm
import com.point18.slg2d.mgr.resp.MgrGateBaseResp
import com.point18.slg2d.mgr.resp.MgrGateBaseResp2Client
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1", produces = ["application/json"])
open class ServerListController {

    data class QueryServerListResp(
            val name: String,
            val status: Int,
            val server_id: Long,
            val serverTcpAdd: String,
            val tcpPort: Int
    )

    class QueryServerListReq {
        var userId: String = ""
        var gameId: Int = 0
        var opId: Long = 0L
        var gameOdId: Int = 0
        var serverId: Int = 0
        var columns: String = ""
    }

    @PostMapping(value = ["/queryServerList"], consumes = ["application/x-www-form-urlencoded"])
    open fun queryServerList(@ModelAttribute param: QueryServerListReq): MgrGateBaseResp2Client {
        val userId = param.userId
        val gameId = param.gameId
        val opId = param.opId
        val gameOdId = param.gameOdId
        val serverId = param.serverId
        val columns = param.columns
        val queryServerListResp = mutableListOf<QueryServerListResp>()

        // 找到所有的进程
        val processClusterMap = mutableMapOf<Long, MutableList<ServerProcess>>()
        val allProcess = mpm.dao.findAllProcesses()
        for (process in allProcess) {
            if (process.processType == PROCESS_GATE) {
                val list = processClusterMap.getOrPut(process.clusterId) { mutableListOf() }
                list.add(process)
            }
        }

        // 查找所有游戏服进程
        val worldAreas = mutableListOf<WorldArea>()
        mpm.dao.findNodeDataOfChildren(WORLD_AREA_NODE_NAME).forEach {
            worldAreas.add(toObj(it))
        }

        for (worldArea in worldAreas) {
            // 找到这个区所属的集群，并找到这个集群所有的网关地址，暂时随机挑选一个。
            val clusterId = worldArea.clusterId
            val list = processClusterMap[clusterId]
            if (list == null || list.size == 0) {
                continue
            }
            val firstGate = list[0]

            // 填充游戏区信息
            val tcpPort = firstGate.tcpPort
            val queryServerInfo =
                    QueryServerListResp(
                            worldArea.gameAreaName,
                            worldArea.areaState,
                            worldArea.pltAreaNo,
                            firstGate.tcpAddr,
                            tcpPort
                    )

            queryServerListResp.add(queryServerInfo)
        }

        // 游戏客户端0表示发送成功
        return MgrGateBaseResp2Client(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, queryServerListResp)
    }

    data class QueryPlayerServerResp(
            val serverId: Long,
            val tcp: String,
            val tcpPort: Int
    )

    @GetMapping(value = ["/queryPlayerServer"])
    open fun queryPlayerServer(): MgrGateBaseResp {
        val queryPlayerServersResp = mutableListOf<QueryPlayerServerResp>()

        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, queryPlayerServersResp)
    }

}