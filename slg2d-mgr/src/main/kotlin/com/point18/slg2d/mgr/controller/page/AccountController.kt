package com.point18.slg2d.mgr.controller.page

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.getRandInt
import com.point18.slg2d.common.commonfunc.md5
import com.point18.slg2d.common.resultcode.Code
import com.point18.slg2d.common.zkdomain.Account
import com.point18.slg2d.common.zkdomain.GmData
import com.point18.slg2d.common.zkdomain.Group
import com.point18.slg2d.mgr.jsonHelper.toObj
import com.point18.slg2d.mgr.mconstg.*
import com.point18.slg2d.mgr.mpm
import com.point18.slg2d.mgr.resp.MgrGateBaseResp
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.HttpSession

@RestController
@RequestMapping("/v1", produces = ["application/json"])
open class AccountController {
    data class CreateAccountReq(

        val pwd: String,
        val name: String,
        val phone: String,
        val groupId: Long,
        val account: String,
        val accstate: Int

    ) //

    @PostMapping(value = ["/createAccount"])
    open fun createAccount(@RequestBody req: CreateAccountReq): MgrGateBaseResp {
//        println(req.toString())
        if (req.account.trim() == "" || req.account.length < 6 || req.account.length > 20) {
            return MgrGateBaseResp(Code.GM_ACCOUNT_FORMAT_ERR.code, Code.GM_ACCOUNT_FORMAT_ERR.errMsg, null)
        }

        if (req.pwd.trim() == "" || req.pwd.length < 6 || req.pwd.length > 20) {
            return MgrGateBaseResp(Code.GM_PWD_FORMAT_ERR.code, Code.GM_PWD_FORMAT_ERR.errMsg, null)
        }

        if (req.accstate != STATE_ACTIVE && req.accstate != STATE_STOP) {
            return MgrGateBaseResp(Code.GROUP_STATE_ERR.code, Code.GROUP_STATE_ERR.errMsg, null)
        }

        val group = mpm.dao.findAll({ toObj<MutableList<Group>>(it) }, GROUPS_SET_PATH)
        if (group.filter { it.id == req.groupId }.isEmpty()) {
            println(group.toString())
            return MgrGateBaseResp(Code.GROUP_NOT_EXIST.code, Code.GROUP_NOT_EXIST.errMsg, null)
        }

        val allAccount = mpm.dao.findAll({ toObj<MutableList<Account>>(it) }, ACCOUNTS_SET_PATH)
        var account = allAccount.firstOrNull { it.accName == req.account && it.id != 0L }
        if (account != null) {
            return MgrGateBaseResp(Code.GM_ACCOUNT_EXIST.code, Code.GM_ACCOUNT_EXIST.errMsg, null)
        }
        val id = mpm.dao.genId() ?: return MgrGateBaseResp(Code.ID_GEN_ERR.code, Code.ID_GEN_ERR.errMsg, null)

        val salt = getRandInt(1000000).toString()
        val pwdMd5 = md5("${req.account}:${req.pwd}:$salt")
        account = Account(
            id.toLong(),
            req.account,
            pwdMd5,
            salt,
            getNowTime(),
            getNowTime(),
            STATE_ACTIVE,
            req.name,
            req.phone,
            req.groupId
        )
        mpm.dao.createOrUpdateListData({ toObj(it) }, account, ACCOUNTS_SET_PATH)

        //todo 记录日志
        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, null)
    }

    @GetMapping(value = ["/delAccount"])
    open fun delAccount(id: String): MgrGateBaseResp {
        val accountId = id.toLongOrNull()
        if (accountId == null) {
            return MgrGateBaseResp(Code.PARAM_ERROR.code, Code.PARAM_ERROR.errMsg, null)
        }

        val account = mpm.dao.findById({ toObj<MutableList<Account>>(it) }, accountId, ACCOUNTS_SET_PATH)
        if (account == null) {
            return MgrGateBaseResp(Code.GM_ACCOUNT_NOT_EXIST.code, Code.GM_ACCOUNT_NOT_EXIST.errMsg, null)
        }
        mpm.dao.deleteSpecData({ toObj(it) }, account, ACCOUNTS_SET_PATH)

        //todo 记录日志
        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, null)
    }

    data class AllAccountResp(
        val id: Long,
        val accName: String,
        val name: String,
        val createTime: String,
        val lastLoginTime: String,
        val phone: String,
        val state: Int,
        val groupId: Long
    )

    @GetMapping(value = ["/queryAllAccount"])
    open fun queryAllAccount(): MgrGateBaseResp {
        val accountResps = mutableListOf<AllAccountResp>()
        val accounts = mpm.dao.findAll<Account>({ toObj(it) }, ACCOUNTS_SET_PATH)
        for (eachAccount in accounts) {
            val accountResp = AllAccountResp(
                eachAccount.id,
                eachAccount.accName,
                eachAccount.name,
                Date(eachAccount.regTime).toString(),
                Date(eachAccount.lastLoginTime).toString(),
                eachAccount.phone,
                eachAccount.state,
                eachAccount.groupId
            )
            accountResps.add(accountResp)
        }
        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, accountResps)
    }

    data class UpdateAccountReq(
        val accountId: Long,
        val account: String,
        val accstate: Int,
        val groupId: Long,
        val name: String,
        val phone: String
    )

    @PostMapping(value = ["/modifyAccount"])
    open fun modifyAccount(@RequestBody req: UpdateAccountReq): MgrGateBaseResp {
        val account = mpm.dao.findById<Account>({ toObj(it) }, req.accountId, ACCOUNTS_SET_PATH)
        if (account == null) {
            return MgrGateBaseResp(Code.GM_ACCOUNT_NOT_EXIST.code, Code.GM_ACCOUNT_NOT_EXIST.errMsg, null)
        }

        if (req.accstate != STATE_ACTIVE && req.accstate != STATE_STOP) {
            return MgrGateBaseResp(Code.GROUP_STATE_ERR.code, Code.GROUP_STATE_ERR.errMsg, null)
        }

        val group = mpm.dao.findById<Group>({ toObj(it) }, req.groupId, ACCOUNTS_SET_PATH)
        if (group == null) {
            return MgrGateBaseResp(Code.GROUP_NOT_EXIST.code, Code.GROUP_NOT_EXIST.errMsg, null)
        }

        account.state = req.accstate
        account.name = req.name
        account.phone = req.phone
        account.groupId = req.groupId
        mpm.dao.createOrUpdateListData({ toObj(it) }, account, ACCOUNTS_SET_PATH)

        //todo  记录日志
        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, null)
    }

    data class UpdateAccountPwdReq(
        val oldPwd: String,
        val newPwd: String
    )

    @PostMapping(value = ["/modifyAccountPwd"])
    open fun modifyAccountPwd(session: HttpSession, @RequestBody req: UpdateAccountPwdReq): MgrGateBaseResp {
        // 验证新密码格式
        if (req.newPwd.trim() == "" || req.newPwd.length < 6 || req.newPwd.length > 20) {
            return MgrGateBaseResp(Code.GM_PWD_FORMAT_ERR.code, Code.GM_PWD_FORMAT_ERR.errMsg, null)
        }

        val gmdata = session.getAttribute(SESSIONKEY)
        if (gmdata == null || gmdata !is GmData) {
            return MgrGateBaseResp(Code.NOT_LOGIN.code, Code.NOT_LOGIN.errMsg, null)
        }

        val accName = gmdata.getAccName
        val allAccount = mpm.dao.findAll<Account>({ toObj(it) }, ACCOUNTS_SET_PATH)
        val account = allAccount.firstOrNull { it.accName == accName && it.id != 0L }
        if (account == null) {
            return MgrGateBaseResp(Code.GM_ACCOUNT_NOT_EXIST.code, Code.GM_ACCOUNT_NOT_EXIST.errMsg, null)
        }

        // 验证老密码是否相符
        val oldPwdMd5 = md5("${account.accName}:${req.oldPwd}:${account.salt}")
        if (oldPwdMd5 != account.pwd) {
            return MgrGateBaseResp(Code.MGR_PWD_INVALID.code, Code.MGR_PWD_INVALID.errMsg, null)
        }

        // 存入新密码
        val pwdMd5 = md5("${account.accName}:${req.newPwd}:${account.salt}")
        account.pwd = pwdMd5
        mpm.dao.createOrUpdateListData({ toObj(it) }, account, ACCOUNTS_SET_PATH)

        // TODO 记录日志
        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, null)
    }
}