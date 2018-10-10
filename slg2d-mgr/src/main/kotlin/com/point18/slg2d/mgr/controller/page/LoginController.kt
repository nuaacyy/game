package com.point18.slg2d.mgr.controller.page

import com.point18.slg2d.common.commonfunc.getRandInt
import com.point18.slg2d.common.resultcode.Code
import com.point18.slg2d.common.zkdomain.Account
import com.point18.slg2d.common.zkdomain.Group
import com.point18.slg2d.mgr.jsonHelper.toObj
import com.point18.slg2d.mgr.mconstg.ACCOUNTS_SET_PATH
import com.point18.slg2d.mgr.mconstg.GROUPS_SET_PATH
import com.point18.slg2d.mgr.mconstg.SESSIONKEY
import com.point18.slg2d.mgr.mconstg.STATE_ACTIVE
import com.point18.slg2d.mgr.mpm
import com.point18.slg2d.mgr.resp.MgrGateBaseResp
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

@RestController
@RequestMapping("/v1", produces = ["application/json"])
open class LoginController {
    class LoginResp(
        rt: Int,
        errorMsg: String,
        val accId: Long,
        val accName: String
    ) : MgrGateBaseResp(rt, errorMsg)

    @GetMapping(value = ["/login"])
    open fun login(session: HttpSession, req: HttpServletRequest, name: String, pwd: String): LoginResp {
        // 验证密码
        val allAccount = mpm.dao.findAll<Account>({ toObj(it) }, ACCOUNTS_SET_PATH)
        val account = allAccount.firstOrNull { it.accName == name && it.id != 0L }
        if (account == null) {
            return LoginResp(Code.MGR_ACCOUNT_NOT_FOUND.code, Code.MGR_ACCOUNT_NOT_FOUND.errMsg, 0, name)
        }
        if (account.state != STATE_ACTIVE) {
            return LoginResp(Code.MGR_ACCOUNT_NOT_ACTIVE.code, Code.MGR_ACCOUNT_NOT_ACTIVE.errMsg, 0, name)
        }

        val md5NameAndPwd = com.point18.slg2d.common.commonfunc.md5("$name:$pwd:${account.salt}")
        if (account.pwd != md5NameAndPwd) {
            return LoginResp(Code.MGR_ACCOUNT_PWD_INVALID.code, Code.MGR_ACCOUNT_PWD_INVALID.errMsg, 0, name)
        }

        // 查看权限组 获取权限
        val allGroups = mpm.dao.findAll<Group>({ toObj(it) }, GROUPS_SET_PATH)
        val group = allGroups.firstOrNull { it.id == account.groupId }
        if (group == null) {
            return LoginResp(Code.GROUP_NOT_EXIST.code, Code.GROUP_NOT_EXIST.errMsg, 0, name)
        }

        val rights = group.rights
        val rightsMap = hashMapOf<Int, Int>()
        for (v in rights) {
            rightsMap[v] = 1
        }

        val ip = req.remoteAddr

        val id = getRandInt(100000)

        val gmData = com.point18.slg2d.common.zkdomain.GmData(
            id.toLong(),
            rightsMap,
            account.id,
            ip,
            account.accName
        )
        session.setAttribute(SESSIONKEY, gmData)

        return LoginResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, account.id, account.accName)
    }

}