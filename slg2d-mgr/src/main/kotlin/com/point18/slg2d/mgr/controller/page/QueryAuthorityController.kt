package com.point18.slg2d.mgr.controller.page

import com.point18.slg2d.common.resultcode.Code
import com.point18.slg2d.mgr.resp.MgrGateBaseResp
import com.point18.slg2d.mgr.rt4m.getGameAllRightsMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1", produces = ["application/json"])
open class QueryAuthorityController {

    data class RightResp(
        val id: Int,  // `json:"id"`
        val info: String     // `json:"info"`
    )

    @GetMapping(value = ["/queryAllAuthority"])
    open fun queryAllAuthority(): MgrGateBaseResp {
        val allRightsList = mutableListOf<RightResp>()
        val allRightMap = getGameAllRightsMap()
        for ((key, value) in allRightMap) {
            val rightResp = RightResp(key, value)
            allRightsList.add(rightResp)
        }

        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, allRightsList)
    }
}