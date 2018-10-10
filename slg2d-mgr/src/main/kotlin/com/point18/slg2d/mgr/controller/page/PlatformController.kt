package com.point18.slg2d.mgr.controller.page

import com.point18.slg2d.common.constg.PLATFORM_NODE_NAME
import com.point18.slg2d.common.constg.PROCESS_NODE_NAME
import com.point18.slg2d.common.resultcode.Code
import com.point18.slg2d.common.zkdomain.Platform
import com.point18.slg2d.common.zkdomain.ServerProcess
import com.point18.slg2d.mgr.jsonHelper.toJson
import com.point18.slg2d.mgr.jsonHelper.toObj
import com.point18.slg2d.mgr.mpm
import com.point18.slg2d.mgr.resp.MgrGateBaseResp
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1", produces = ["application/json"])
open class PlatformController {

    data class PlatformResp(
        val id: Long,
        val opId: String,
        val gameId: String,
        val platformName: String
    )

    @GetMapping(value = ["/addPlat"])
    open fun addPlat(platName: String, opId: String, gameId: String): MgrGateBaseResp {
        val opid = opId.toLongOrNull()
        val gameid = gameId.toLongOrNull()
        if (opid == null || gameid == null) {
            return MgrGateBaseResp(Code.PARAM_ERROR.code, Code.PARAM_ERROR.errMsg, null)
        }
        val allPlatforms = mutableListOf<Platform>()
        mpm.dao.findNodeDataOfChildren(PLATFORM_NODE_NAME).forEach {
            allPlatforms.add(toObj(it))
        }
        val samePlatform = allPlatforms.filter { it.name == platName }
        if (samePlatform.isNotEmpty()) {
            return MgrGateBaseResp(Code.PLATFORM_OPID_EXIST.code, Code.PLATFORM_OPID_EXIST.errMsg, null)
        }

        val id = mpm.dao.genId() ?: return MgrGateBaseResp(Code.ID_GEN_ERR.code, Code.ID_GEN_ERR.errMsg, null)

        val platform = Platform(
            id.toLong(),
            opid,
            platName,
            gameid
        )
        mpm.dao.createOrUpdateData("$PLATFORM_NODE_NAME/${platform.id}", toJson(platform))
        //TODO 记录日志
        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, null)
    }

    @GetMapping(value = ["/queryPlat"])
    open fun queryPlatform(): MgrGateBaseResp {
        val allPlat = mutableListOf<Platform>()
        mpm.dao.findNodeDataOfChildren(PLATFORM_NODE_NAME).forEach {
            allPlat.add(toObj(it))
        }
        val allPlatResp = mutableListOf<PlatformResp>()
        allPlat.forEach {
            allPlatResp.add(PlatformResp(it.id, it.opId.toString(), it.gameId.toString(), it.name))
        }

        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, allPlatResp)
    }

    data class UpdatePlatformReq(
        val id: Long,  // `json:"id"`
        val opId: Int,  // `json:"opId"`
        val gameId: Int,  // `json:"gameId"`
        val platformName: String      // `json:"platformName"`
    )

    @PostMapping(value = ["/updatePlat"])
    open fun updatePlat(@RequestBody param: UpdatePlatformReq): MgrGateBaseResp {

        val platformStr = mpm.dao.findNodeData("$PLATFORM_NODE_NAME/${param.id}")
        if (platformStr == null) {
            return MgrGateBaseResp(Code.GROUP_PLATFORM_NOT_EXIST.code, Code.GROUP_PLATFORM_NOT_EXIST.errMsg, null)
        }
        val platform = toObj<Platform>(platformStr)

        // 更新数据并保存
        platform.opId = param.opId.toLong()
        platform.gameId = param.gameId.toLong()
        platform.name = param.platformName

        mpm.dao.createOrUpdateData("$PLATFORM_NODE_NAME/${platform.id}", toJson(platform))

        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, null)
    }

    @GetMapping(value = ["/delPlat"])
    open fun delPlat(id: String): MgrGateBaseResp {
        val platformId = id.toLong()
        val platformStr = mpm.dao.findNodeData("$PLATFORM_NODE_NAME/${platformId}")
        if (platformStr == null) {
            return MgrGateBaseResp(Code.GROUP_PLATFORM_NOT_EXIST.code, Code.GROUP_PLATFORM_NOT_EXIST.errMsg, null)
        }

        // 如果这个平台下有process，不能删
        val relatedProcess = mutableListOf<ServerProcess>()
        mpm.dao.findNodeNameOfChildren(PROCESS_NODE_NAME).forEach { ip ->
            mpm.dao.findNodeDataOfChildren("$PROCESS_NODE_NAME/$ip").forEach { processDataStr ->
                val processConfig = toObj<ServerProcess>(processDataStr)
                if (processConfig.platformId != platformId) {
                    relatedProcess.add(processConfig)
                }
            }
        }
        if (relatedProcess.isNotEmpty()) {
            return MgrGateBaseResp(Code.PLATFORM_EXIST_SERVER.code, Code.PLATFORM_EXIST_SERVER.errMsg, null)
        }

        // 删除平台数据
        mpm.dao.clear("$PLATFORM_NODE_NAME/${platformId}")

        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, null)
    }
}