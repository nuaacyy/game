package com.point18.slg2d.common.zkdomain

import com.point18.slg2d.common.baseg.ZK_DATA_STRUCT_VERSION
import com.point18.slg2d.common.baseg.ZkDomain

data class Account(
    override var id: Long,
    var accName: String,
    var pwd: String,
    var salt: String,
    var regTime: Long,
    var lastLoginTime: Long,
    var state: Int,       // 状态
    var name: String,   // 真实名字
    var phone: String,  // 联系方式
    var groupId: Long // 所属群组
) : ZkDomain {
    override var dataVersion: Int = ZK_DATA_STRUCT_VERSION
}