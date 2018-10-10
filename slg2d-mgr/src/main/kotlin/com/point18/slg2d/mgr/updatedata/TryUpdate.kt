package com.point18.slg2d.mgr.updatedata

import com.point18.slg2d.common.baseg.ZK_DATA_STRUCT_VERSION
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.MGR_INFO_NODE_NAME
import com.point18.slg2d.common.zkdomain.MgrInfo
import com.point18.slg2d.mgr.jsonHelper.toJson
import com.point18.slg2d.mgr.jsonHelper.toObj
import com.point18.slg2d.mgr.mpm
import xyz.ariane.util.lzInfo

interface UpdateZkData {
    fun update()
}

class InvalidUpdate() : UpdateZkData {
    override fun update() {

    }
}

class TryUpdate {

    private var updateMethods = listOf<UpdateZkData>()

    init {
        val updates = mutableListOf<UpdateZkData>()
        updates.add(InvalidUpdate())
        updates.add(UpdateZkDataTo1())
        updates.add(UpdateZkDataTo2())
        updates.add(UpdateZkDataTo3())
        updates.add(UpdateZkDataTo4())
        updates.add(UpdateZkDataTo5())
        updates.add(UpdateZkDataToLatest())
        updateMethods = updates
    }

    /**
     * 尝试更新数据
     */
    fun tryUpdate() {
        normalLog.lzInfo { "尝试更新数据到目标版本" }

        var dataStr = mpm.dao.findNodeData(MGR_INFO_NODE_NAME)
        if (dataStr == null) {
            val mgrInfo = MgrInfo(1, ZK_DATA_STRUCT_VERSION)
            dataStr = toJson(mgrInfo)
            mpm.dao.createOrUpdateData(MGR_INFO_NODE_NAME, dataStr)
        }

        val mgrInfo = toObj<MgrInfo>(dataStr)
        var version = mgrInfo.version
        normalLog.lzInfo { "当前数据版本：$version，目标版本：$ZK_DATA_STRUCT_VERSION" }

        while (version < ZK_DATA_STRUCT_VERSION) {
            // 更新
            version += 1
            if (updateMethods.size <= version) {
                throw Exception("GM后台的更新方法不足！")
            }

            updateMethods[version].update()
        }
    }
}
