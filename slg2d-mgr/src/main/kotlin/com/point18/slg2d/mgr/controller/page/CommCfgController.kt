package com.point18.slg2d.mgr.controller.page

import com.point18.slg2d.common.constg.CLUSTER_NODE_NAME
import com.point18.slg2d.common.resultcode.Code
import com.point18.slg2d.common.zkdomain.CommCfg
import com.point18.slg2d.mgr.jsonHelper.toJson
import com.point18.slg2d.mgr.jsonHelper.toObj
import com.point18.slg2d.mgr.mpm
import com.point18.slg2d.mgr.resp.MgrGateBaseResp
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1", produces = ["application/json"])
open class CommCfgController {

    data class CreateCommCfgReq(
        val seedNodes: String,
        val groupName: String,
        val kafkaAddrs: String,
        val dcLogTopic: String
    )

    @PostMapping(value = ["/addCommCfg"])
    open fun addCommCfg(@RequestBody param: CreateCommCfgReq): MgrGateBaseResp {
        if (param.groupName.trim() == "") {
            return MgrGateBaseResp(Code.COMM_CFG_NAME_ERROR.code, Code.COMM_CFG_NAME_ERROR.errMsg, null)
        }

        if (param.seedNodes.trim() == "") {
            return MgrGateBaseResp(Code.COMM_CFG_OTHER_PARAM_ERROR.code, Code.COMM_CFG_OTHER_PARAM_ERROR.errMsg, null)
        }

        val allCommCfg = mutableListOf<CommCfg>()
        mpm.dao.findNodeDataOfChildren(CLUSTER_NODE_NAME).forEach {
            allCommCfg.add(toObj(it))
        }

        val id = mpm.dao.genId() ?: return MgrGateBaseResp(Code.ID_GEN_ERR.code, Code.ID_GEN_ERR.errMsg, null)

        val commCfg = CommCfg(
            id.toLong(),
            param.groupName,
            param.seedNodes,
            param.kafkaAddrs,
            param.dcLogTopic
        )
        mpm.dao.createOrUpdateData("$CLUSTER_NODE_NAME/${commCfg.id}", toJson(commCfg))

        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, null)
    }

    @GetMapping(value = ["/delCommCfg"])
    open fun delCommCfg(id: Long): MgrGateBaseResp {
        // 想删除集群，必须先把集群下的process删除
        val processes = mpm.dao.findAllProcesses().filter { it.clusterId == id }
        if (processes.isNotEmpty()) {
            return MgrGateBaseResp(Code.PROCESSNO_EXIST.code, Code.PROCESSNO_EXIST.errMsg, null)
        }

        // 删除目标集群配置
        val rt = mpm.dao.clear("$CLUSTER_NODE_NAME/$id")
        if (!rt) {
            return MgrGateBaseResp(Code.COMM_CFG_NOT_FOUND.code, Code.COMM_CFG_NOT_FOUND.errMsg, null)
        }

        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, null)
    }

    data class CommCfgResp(
        val id: Long,
        val groupName: String,
        val seedNodes: String,
        val kafkaAddrs: String,
        val dcLogTopic: String
    )

    @GetMapping(value = ["/queryAllCommCfgs"])
    open fun queryAllCommCfgs(): MgrGateBaseResp {
        val cfgResps = mutableListOf<CommCfgResp>()

        val cfgs = mutableListOf<CommCfg>()
        mpm.dao.findNodeDataOfChildren(CLUSTER_NODE_NAME).forEach {
            cfgs.add(toObj(it))
        }

        for (eachCommCfg in cfgs) {
            val commCfgResp = CommCfgResp(
                eachCommCfg.id,
                eachCommCfg.groupName,
                eachCommCfg.seedNodes,
                eachCommCfg.kafkaAddrs,
                eachCommCfg.dcLogTopic
            )
            cfgResps.add(commCfgResp)
        }
        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, cfgResps)
    }

    @GetMapping(value = ["/queryCommCfgDetail"])
    open fun queryCommCfgDetail(): MgrGateBaseResp {
        //数据库以后做
        //todo
        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, null)
    }

    data class UpdateCommCfgReq(
        val id: Long,
        val groupName: String,
        val seedNodes: String,
        val kafkaAddrs: String,
        val dcLogTopic: String
    )

    @PostMapping(value = ["/chgCommCfg"])
    open fun chgCommCfg(@RequestBody param: UpdateCommCfgReq): MgrGateBaseResp {
        if (param.groupName.trim() == "") {
            return MgrGateBaseResp(Code.COMM_CFG_NAME_ERROR.code, Code.COMM_CFG_NAME_ERROR.errMsg, null)
        }

        if (param.seedNodes.trim() == "") {
            return MgrGateBaseResp(Code.COMM_CFG_OTHER_PARAM_ERROR.code, Code.COMM_CFG_OTHER_PARAM_ERROR.errMsg, null)
        }

        // 找到目标配置并更新
        val cfgs = mutableListOf<CommCfg>()
        mpm.dao.findNodeDataOfChildren(CLUSTER_NODE_NAME).forEach {
            cfgs.add(toObj(it))
        }
        val existCommCfg = cfgs.firstOrNull { it.id == param.id }
        if (existCommCfg == null) {
            return MgrGateBaseResp(Code.COMM_CFG_NOT_FOUND.code, Code.COMM_CFG_NOT_FOUND.errMsg, null)
        }

        existCommCfg.groupName = param.groupName
        existCommCfg.seedNodes = param.seedNodes
        existCommCfg.kafkaAddrs = param.kafkaAddrs
        existCommCfg.dcLogTopic = param.dcLogTopic
        mpm.dao.createOrUpdateData("$CLUSTER_NODE_NAME/${existCommCfg.id}", toJson(existCommCfg))

        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, null)
    }
}