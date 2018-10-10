package com.point18.slg2d.mgr.controller.page

import com.point18.slg2d.common.resultcode.Code
import com.point18.slg2d.common.zkdomain.Group
import com.point18.slg2d.mgr.jsonHelper.toObj
import com.point18.slg2d.mgr.mconstg.GROUPS_SET_PATH
import com.point18.slg2d.mgr.mconstg.STATE_ACTIVE
import com.point18.slg2d.mgr.mconstg.STATE_STOP
import com.point18.slg2d.mgr.mpm
import com.point18.slg2d.mgr.resp.MgrGateBaseResp
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/v1", produces = ["application/json"])
open class GroupController {
    data class CreateGroupReq(
        val groupName: String,
        val groupState: Int,
        val isQa: Int,
        val rights: MutableList<GroupRightReqData>,
        val platforms: LinkedList<Int>
    )

    data class GroupRightReqData(
        val id: Int,
        val checked: Boolean
    )

    @PostMapping(value = ["/createGroup"])
    open fun createGroup(@RequestBody param: CreateGroupReq): MgrGateBaseResp {
        if (param.groupName.trim() == "") {
            return MgrGateBaseResp(Code.GROUP_NAME_NULL.code, Code.GROUP_NAME_NULL.errMsg, null)
        }

        if (param.groupState != STATE_ACTIVE && param.groupState != STATE_STOP) {
            return MgrGateBaseResp(Code.GROUP_STATE_ERR.code, Code.GROUP_STATE_ERR.errMsg, null)
        }

        val allGroup = mpm.dao.findAll<Group>({ toObj(it) }, GROUPS_SET_PATH)
        var group = allGroup.firstOrNull { it.groupName == param.groupName }
        if (group != null) {
            return MgrGateBaseResp(Code.GROUP_NAME_EXIST.code, Code.GROUP_NAME_EXIST.errMsg, null)
        }

        val groupRights = LinkedList<Int>()
        for (v in param.rights) {
            if (v.checked) {
                groupRights.add(v.id)
            }
        }

        val id = mpm.dao.genId() ?: return MgrGateBaseResp(Code.ID_GEN_ERR.code, Code.ID_GEN_ERR.errMsg, null)

        group = Group(
            id.toLong(),
            param.groupName,
            param.groupState,
            param.platforms,
            groupRights,
            param.isQa
        )
        mpm.dao.createOrUpdateListData({ toObj(it) }, group, GROUPS_SET_PATH)

        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, null)
    }

    @GetMapping(value = ["/delGroup"])
    open fun delGroup(id: String): MgrGateBaseResp {
        val groupId = id.toLongOrNull()
        if (groupId == null) {
            return MgrGateBaseResp(Code.PARAM_ERROR.code, Code.PARAM_ERROR.errMsg, null)
        }

        val allgroup = mpm.dao.findAll<Group>({ toObj(it) }, GROUPS_SET_PATH)
        val group = allgroup.filter { it.id == groupId }
        if (group.isEmpty()) {
            return MgrGateBaseResp(Code.GROUP_NOT_EXIST.code, Code.GROUP_NOT_EXIST.errMsg, null)
        }

        mpm.dao.deleteSpecData({ toObj(it) }, group[0], GROUPS_SET_PATH)

        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, null)
    }

    data class AllGroupResp(
        val id: Long,
        val groupName: String,
        val groupState: Int,
        val isQa: Int,
        val rights: MutableList<GroupRightResp>,
        val platforms: MutableList<Int>
    )

    data class GroupRightResp(
        val id: Int  //`json:"id"`
    )

    @GetMapping(value = ["/queryAllGroups"])
    open fun queryAllGroups(): MgrGateBaseResp {
        val groupsResps = mutableListOf<AllGroupResp>()
        val groups = mpm.dao.findAll<Group>({ toObj(it) }, GROUPS_SET_PATH)
//        groups.forEach { println(it.toString()) }
        for (eachGroup in groups) {
            val groupRightsResp = mutableListOf<GroupRightResp>()
            for (right in eachGroup.rights) {
                groupRightsResp.add(GroupRightResp(right))
            }
//            println(eachGroup.id.toString())
            val groupsResp = AllGroupResp(
                eachGroup.id,
                eachGroup.groupName,
                eachGroup.state,
                eachGroup.qa,
                groupRightsResp,
                eachGroup.platforms
            )
            groupsResps.add(groupsResp)
        }

        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, groupsResps)
    }

    data class UpdateGroupReq(
        val groupId: Long,
        val groupName: String,
        val groupState: Int,
        val isQa: Int,
        val rights: LinkedList<GroupRightReqData>,
        val platforms: LinkedList<Int>
    )

    data class SaveGroupRights(
        val Id: MutableList<Int>
    )

    @PostMapping(value = ["/modifyGroup"])
    open fun modifyGroup(@RequestBody param: UpdateGroupReq): MgrGateBaseResp {
        val allGroups = mpm.dao.findAll<Group>({ toObj(it) }, GROUPS_SET_PATH)
        val group = allGroups.firstOrNull { it.id == param.groupId }
        if (group == null) {
            allGroups.forEach { println(it.toString()) }
            println("id : ${param.groupId} ")
            return MgrGateBaseResp(Code.GROUP_NOT_EXIST.code, Code.GROUP_NOT_EXIST.errMsg, null)
        }

        if (param.groupName.trim() == "") {
            return MgrGateBaseResp(Code.GROUP_NAME_NULL.code, Code.GROUP_NAME_NULL.errMsg, null)
        }

        if (param.groupState != STATE_ACTIVE && param.groupState != STATE_STOP) {
            return MgrGateBaseResp(Code.GROUP_STATE_ERR.code, Code.GROUP_STATE_ERR.errMsg, null)
        }

        val groupRights = LinkedList<Int>()
        for (v in param.rights) {
            if (v.checked) {
                groupRights.add(v.id)
            }
        }

        group.groupName = param.groupName
        group.state = param.groupState
        group.platforms = param.platforms
        group.rights = groupRights
        group.qa = param.isQa

        mpm.dao.createOrUpdateListData({ toObj(it) }, group, GROUPS_SET_PATH)

        //TODO 记录日志
//        operateData, _ := json.Marshal(req.Rights)
//        mdao.SaveMgrLog(session, rt4m.GROUP_MODIFY, string(operateData), 1)
        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, null)
    }
}