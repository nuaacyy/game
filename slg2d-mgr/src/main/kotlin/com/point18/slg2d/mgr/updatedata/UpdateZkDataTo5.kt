package com.point18.slg2d.mgr.updatedata

import com.point18.slg2d.common.commonfunc.ZkTransaction
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.CLUSTER_NODE_NAME
import com.point18.slg2d.common.constg.MGR_INFO_NODE_NAME
import com.point18.slg2d.common.zkdomain.v5.CommCfg
import com.point18.slg2d.common.zkdomain.v5.MgrInfo
import com.point18.slg2d.mgr.jsonHelper.toJson
import com.point18.slg2d.mgr.jsonHelper.toObj
import com.point18.slg2d.mgr.mpm
import xyz.ariane.util.lzInfo

class UpdateZkDataTo5 : UpdateZkData {

    override fun update() {

        val targetVersion = 5

        normalLog.lzInfo { "更新数据到版本 $targetVersion" }

        val transaction = ZkTransaction(mpm.dao.zkClient())

        // 更新集群配置
        normalLog.lzInfo { "开始更新数据到版本 $CLUSTER_NODE_NAME" }
        val allCommCfg = mutableListOf<CommCfg>()
        mpm.dao.findNodeDataOfChildren(CLUSTER_NODE_NAME).forEach {
            allCommCfg.add(toObj(it))
        }
        allCommCfg.forEach { eachCluster ->
            eachCluster.dataVersion = targetVersion
            eachCluster.kafkaAddrs = "172.18.3.203:19092,172.18.3.203:19093,172.18.3.203:19094"
            eachCluster.dcLogTopic = "dclog1"
            mpm.dao.createOrUpdateData("$CLUSTER_NODE_NAME/${eachCluster.id}", toJson(eachCluster))
        }

        // 更新MgrInfo
        normalLog.lzInfo { "开始更新数据到版本 $MGR_INFO_NODE_NAME" }
        val mgrInfoStr = mpm.dao.findNodeData(MGR_INFO_NODE_NAME)
        if (mgrInfoStr != null) {
            val mgrInfo = toObj<MgrInfo>(mgrInfoStr)
            mgrInfo.dataVersion = targetVersion
            mgrInfo.version = targetVersion
            transaction.setData(
                MGR_INFO_NODE_NAME,
                toJson(mgrInfo)
            )
        }

        transaction.commit()?.forEach {
            normalLog.lzInfo { "${it.forPath}-${it.type}" }
        }
    }

}
