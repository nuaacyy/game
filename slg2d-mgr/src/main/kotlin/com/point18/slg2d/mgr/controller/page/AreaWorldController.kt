package com.point18.slg2d.mgr.controller.page

import com.point18.slg2d.common.constg.AREA_NOT_DEPLOY
import com.point18.slg2d.common.constg.AREA_UNKNOWN
import com.point18.slg2d.common.constg.CLUSTER_NODE_NAME
import com.point18.slg2d.common.constg.WORLD_AREA_NODE_NAME
import com.point18.slg2d.common.resultcode.Code
import com.point18.slg2d.common.zkdomain.CommCfg
import com.point18.slg2d.common.zkdomain.WorldArea
import com.point18.slg2d.mgr.jsonHelper.toJson
import com.point18.slg2d.mgr.jsonHelper.toObj
import com.point18.slg2d.mgr.mconstg.NOT_DEPLOY
import com.point18.slg2d.mgr.mpm
import com.point18.slg2d.mgr.resp.AllWorldAreaResp
import com.point18.slg2d.mgr.resp.MgrGateBaseResp
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1", produces = ["application/json"])
open class AreaWorldController {

    data class AddGameAreaReq(
        val areaNo: Int,
        val gameAreaName: String,
        val pltAreaNo: Long,    // 这个对应的是worldId
        val areaState: Int,
        val opgameId: Int,
        val payKey: String,
        val openAreaDate: Long,
        val loginKey: String,
        val giftKey: String,
        val whichAction: Int,
        val clusterId: Long
    )

    @PostMapping(value = ["/addGameArea"])
    open fun addWorldArea(@RequestBody param: AddGameAreaReq): MgrGateBaseResp {
        val allWorldAreas = mutableListOf<WorldArea>()
        mpm.dao.findNodeDataOfChildren(WORLD_AREA_NODE_NAME).forEach {
            allWorldAreas.add(toObj(it))
        }

        val targetNode = allWorldAreas.firstOrNull { it.pltAreaNo == param.pltAreaNo }
        if (targetNode != null) {
            return MgrGateBaseResp(Code.GAMEAREA_HAS_EXIST.code, Code.GAMEAREA_HAS_EXIST.errMsg, null)
        }

        val id = mpm.dao.genId() ?: return MgrGateBaseResp(Code.ID_GEN_ERR.code, Code.ID_GEN_ERR.errMsg, null)

        val worldArea = WorldArea(
            id.toLong(),
            param.areaNo,
            param.pltAreaNo,
            param.areaState,
            param.gameAreaName,
            toJson(mutableListOf<String>()),
            toJson(mutableListOf<String>()),
            param.opgameId,
            param.payKey,
            param.loginKey,
            param.giftKey,
            param.openAreaDate,
            NOT_DEPLOY,
            param.clusterId
        )

        val worldAreaNode = "$WORLD_AREA_NODE_NAME/${worldArea.id}"
        mpm.dao.createOrUpdateData(worldAreaNode, toJson(worldArea))

        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, null)
    }

    /**
     * 删除游戏区
     */
    @GetMapping(value = ["/delGameArea"])
    open fun delWorldArea(id: String): MgrGateBaseResp {
        val worldAreaId = id.toLongOrNull()
        if (worldAreaId == null) {
            return MgrGateBaseResp(Code.PARAM_ERROR.code, Code.PARAM_ERROR.errMsg, null)
        }

        val allWorldArea = mutableListOf<WorldArea>()
        mpm.dao.findNodeDataOfChildren(WORLD_AREA_NODE_NAME).forEach {
            allWorldArea.add(toObj(it))
        }
        val worldArea = allWorldArea.firstOrNull { it.id == worldAreaId }
        if (worldArea == null) {
            return MgrGateBaseResp(Code.GAMEAREA_NOT_EXIST.code, Code.GAMEAREA_NOT_EXIST.errMsg, null)
        }

        val worldAreaNode = "$WORLD_AREA_NODE_NAME/${worldArea.id}"
        mpm.dao.clear(worldAreaNode)

        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, null)
    }

    @GetMapping(value = ["/queryWorldArea"])
    open fun queryWorldArea(): MgrGateBaseResp {
        // 获取所有的游戏区
        val worldAreas = mutableListOf<WorldArea>()
        mpm.dao.findNodeDataOfChildren(WORLD_AREA_NODE_NAME).forEach {
            worldAreas.add(toObj(it))
        }

        // 填充响应
        val worldAreasResp = mutableListOf<AllWorldAreaResp>()
        for (worldArea in worldAreas) {
            // 获取游戏区运行状态
            var operationState = AREA_UNKNOWN
            if (worldArea.deployState == NOT_DEPLOY) {
                // 尚未部署
                operationState = AREA_NOT_DEPLOY
            } else {
                //TODO 已部署，获取进程状态
            }

            val worldAreaResp = AllWorldAreaResp(
                worldArea.id,
                worldArea.areaNo,
                worldArea.gameAreaName,
                worldArea.pltAreaNo,
                worldArea.areaState,
                worldArea.whiteRosters,
                worldArea.blackRosters,
                worldArea.opgameId,
                worldArea.payKey,
                worldArea.loginKey,
                worldArea.giftKey,
                worldArea.openAreaDate,
                operationState,
                worldArea.deployState,
                0,
                worldArea.clusterId
            )
            worldAreasResp.add(worldAreaResp)
        }

        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, worldAreasResp)
    }

    data class UpdateWorldAreaReq(
        val id: Long,
        val areaNo: Int,
        val gameAreaName: String = "",
        val pltAreaNo: Long,
        val areaState: Int,
        val whiteRoster: String = "",
        val blackRoster: String = "",
        val opgameId: Int,
        val payKey: String = "",
        val loginKey: String = "",
        val giftKey: String = "",
        val operationState: Int,
        val openAreaDate: Long,
        val deployState: Int,
        val clusterId: Long
    )

    @PostMapping(value = ["/updateGameArea"])
    open fun updateWorldArea(@RequestBody param: UpdateWorldAreaReq): MgrGateBaseResp {
        val worldAreas = mutableListOf<WorldArea>()
        mpm.dao.findNodeDataOfChildren(WORLD_AREA_NODE_NAME).forEach {
            worldAreas.add(toObj(it))
        }

        val worldArea = worldAreas.firstOrNull { it.id == param.id }
        if (worldArea == null) {
            return MgrGateBaseResp(Code.GAMEAREA_NOT_FOUND.code, Code.GAMEAREA_NOT_FOUND.errMsg, null)
        }

        val allCommCfg = mutableListOf<CommCfg>()
        mpm.dao.findNodeDataOfChildren(CLUSTER_NODE_NAME).forEach {
            allCommCfg.add(toObj(it))
        }
        val cluster = allCommCfg.firstOrNull { it.id == param.clusterId }
            ?: return MgrGateBaseResp(Code.COMM_CFG_NOT_FOUND.code, Code.COMM_CFG_NOT_FOUND.errMsg, null)

        worldArea.pltAreaNo = param.pltAreaNo
        worldArea.blackRosters = param.blackRoster
        worldArea.areaNo = param.areaNo
        worldArea.areaState = param.areaState
        worldArea.deployState = param.deployState
        worldArea.gameAreaName = param.gameAreaName
        worldArea.giftKey = param.giftKey
        worldArea.loginKey = param.loginKey
        worldArea.opgameId = param.opgameId
        worldArea.payKey = param.payKey
        worldArea.whiteRosters = param.whiteRoster
        worldArea.openAreaDate = param.openAreaDate
        worldArea.clusterId = cluster.id

        //更新ip节点
        val updateNode = "$WORLD_AREA_NODE_NAME/${worldArea.id}"
        mpm.dao.createOrUpdateData(updateNode, toJson(worldArea))

        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, null)
    }

    /**
     * 测试数据
     */
    @PostMapping(value = ["/queryTestData"])
    open fun queryTestData(@RequestBody data: String): MgrGateBaseResp {
        println("测试数据。。。")
        println(data)
        var tempData = data.toIntOrNull()
        if (tempData == null) {
            return MgrGateBaseResp(Code.PARAM_ERROR.code, Code.PARAM_ERROR.errMsg, null)
        }

        tempData++

        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, tempData)
    }

    @GetMapping(value = ["/queryTestGetData"])
    open fun queryTestGetData(data: String): MgrGateBaseResp {
        println("测试数据。。。")
        println(data)
        var tempData = data.toIntOrNull()
        if (tempData == null) {
            return MgrGateBaseResp(Code.PARAM_ERROR.code, Code.PARAM_ERROR.errMsg, null)
        }

        tempData++

        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, tempData)
    }

}

