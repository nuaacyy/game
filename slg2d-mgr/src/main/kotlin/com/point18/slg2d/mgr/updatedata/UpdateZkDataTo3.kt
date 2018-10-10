package com.point18.slg2d.mgr.updatedata

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.zkdomain.v3.*
import com.point18.slg2d.mgr.jsonHelper.toJson
import com.point18.slg2d.mgr.jsonHelper.toObj
import com.point18.slg2d.mgr.mpm
import xyz.ariane.util.lzInfo

class UpdateZkDataTo3 : UpdateZkData {

    override fun update() {

        val targetVersion = 3

        normalLog.lzInfo { "更新数据到版本 $targetVersion" }

        // 更新集群配置
        normalLog.lzInfo { "开始更新数据到版本 $CLUSTER_NODE_NAME" }
        val allCommCfg = mutableListOf<com.point18.slg2d.common.zkdomain.v2.CommCfg>()
        mpm.dao.findNodeDataOfChildren(CLUSTER_NODE_NAME).forEach {
            allCommCfg.add(toObj(it))
        }
        allCommCfg.forEach { eachCommCfg ->
            val newCommCfg = CommCfg(
                eachCommCfg.id,
                eachCommCfg.groupName,
                eachCommCfg.seedNodes
            )
            newCommCfg.dataVersion = targetVersion
            mpm.dao.createOrUpdateData("$CLUSTER_NODE_NAME/${eachCommCfg.id}", toJson(newCommCfg))
        }

        // 更新MgrInfo
        normalLog.lzInfo { "开始更新数据到版本 $MGR_INFO_NODE_NAME" }
        val mgrInfoStr = mpm.dao.findNodeData(MGR_INFO_NODE_NAME)
        if (mgrInfoStr != null) {
            val mgrInfo = toObj<MgrInfo>(mgrInfoStr)
            mgrInfo.dataVersion = targetVersion
            mgrInfo.version = targetVersion
            mpm.dao.createOrUpdateData(
                MGR_INFO_NODE_NAME,
                toJson(mgrInfo)
            )
        }
    }

}
