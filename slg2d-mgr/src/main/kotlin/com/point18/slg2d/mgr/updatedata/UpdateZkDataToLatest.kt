package com.point18.slg2d.mgr.updatedata

import com.point18.slg2d.common.baseg.ZK_DATA_STRUCT_VERSION
import com.point18.slg2d.common.commonfunc.ZkTransaction
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.DATASOURCE_NODE_NAME
import com.point18.slg2d.common.constg.MGR_INFO_NODE_NAME
import com.point18.slg2d.common.zkdomain.DataSource
import com.point18.slg2d.common.zkdomain.MgrInfo
import com.point18.slg2d.common.zkdomain.ZkDatabaseConfig
import com.point18.slg2d.mgr.jsonHelper.toJson
import com.point18.slg2d.mgr.jsonHelper.toObj
import com.point18.slg2d.mgr.mpm
import xyz.ariane.util.lzInfo
import java.util.*

class UpdateZkDataToLatest : UpdateZkData {

    override fun update() {

        val targetVersion = ZK_DATA_STRUCT_VERSION

        normalLog.lzInfo { "更新数据到版本 $targetVersion" }

        val transaction = ZkTransaction(mpm.dao.zkClient())

        // 更新数据源配置
        normalLog.lzInfo { "开始更新数据到新版本 $DATASOURCE_NODE_NAME" }
        val dataSourceStr = mpm.dao.findNodeData(DATASOURCE_NODE_NAME)
        if (dataSourceStr != null) {
            val dataSource = toObj<DataSource>(dataSourceStr)
            dataSource.dataVersion = targetVersion
            for ((_, eachDataSource) in dataSource.dsMap) {
                eachDataSource.clusterId = 0L
                val dss = LinkedList<ZkDatabaseConfig>()
                dss += eachDataSource.datasourceList
                eachDataSource.dataSources = dss
            }
            transaction.setData(
                DATASOURCE_NODE_NAME,
                toJson(dataSource)
            )
        }

        // 更新MgrInfo
        normalLog.lzInfo { "开始更新数据到新版本 $MGR_INFO_NODE_NAME" }
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
