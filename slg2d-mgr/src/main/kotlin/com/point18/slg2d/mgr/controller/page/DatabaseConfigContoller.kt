package com.point18.slg2d.mgr.controller.page

import com.point18.slg2d.common.constg.DATASOURCE_NODE_NAME
import com.point18.slg2d.common.resultcode.Code
import com.point18.slg2d.common.zkdomain.DataSource
import com.point18.slg2d.common.zkdomain.DataSourceList
import com.point18.slg2d.common.zkdomain.ZkDatabaseConfig
import com.point18.slg2d.mgr.jsonHelper.toJson
import com.point18.slg2d.mgr.jsonHelper.toObj
import com.point18.slg2d.mgr.mpm
import com.point18.slg2d.mgr.resp.MgrGateBaseResp
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/v1", produces = ["application/json"])
open class DatabaseConfigContoller {

    open class DatabaseConfig(
        val showKey: String,
        val configName: String,
        val shardId: Long,
        val socket: String,
        val database: String,
        val user: String,
        val password: String,
        val clusterId: Long
    )

    @PostMapping(value = ["/addDatabaseConfig"])
    open fun addDatabaseConfig(@RequestBody param: DatabaseConfig): MgrGateBaseResp {
        // 长度检查
        if (param.user.length > 50 || param.password.length > 50 || param.database.length > 50 || param.socket.length > 50) {
            return MgrGateBaseResp(Code.PARAM_ERROR.code, Code.PARAM_ERROR.errMsg, null)
        }

        val dataSourceString = mpm.dao.findNodeData(DATASOURCE_NODE_NAME)
        if (dataSourceString == null) {
            return MgrGateBaseResp(Code.ZK_DATA_DONT_EXIST.code, Code.ZK_DATA_DONT_EXIST.errMsg, null)
        }

        val zkDs = ZkDatabaseConfig(
            param.shardId,
            param.socket,
            param.database,
            param.user,
            param.password
        )

        val dataSource = toObj<DataSource>(dataSourceString)
        val oldDataSourceList = dataSource.dsMap[param.configName]
        if (oldDataSourceList != null) {
            // 修改
            val dss = oldDataSourceList.dataSources
            if (dss == null) {
                val newList = LinkedList<ZkDatabaseConfig>()
                newList += zkDs
                oldDataSourceList.dataSources = newList
            } else {
                dss += zkDs
            }

        } else {
            // 新增
            val dss = LinkedList<ZkDatabaseConfig>()
            dss += zkDs
            dataSource.dsMap["${param.clusterId}"] = DataSourceList(
                zkDs,
                dss,
                param.clusterId
            )
        }

        // 更新数据
        mpm.dao.createOrUpdateData(DATASOURCE_NODE_NAME, toJson(dataSource))

        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, null)
    }

    @GetMapping(value = ["/delDatabaseConfig"])
    open fun delDatabaseConfig(clusterId: Long, shardId: Long): MgrGateBaseResp {

        val dataSourceString = mpm.dao.findNodeData(DATASOURCE_NODE_NAME)
        if (dataSourceString == null) {
            return MgrGateBaseResp(Code.ZK_DATA_DONT_EXIST.code, Code.ZK_DATA_DONT_EXIST.errMsg, null)
        }

        val dataSource = toObj<DataSource>(dataSourceString)

        // TODO 由于遗留数据的关系，所以这里的删除暂时使用遍历查找的方式，遗留数据处理完毕后，再考虑改回map查找的方式。
        for ((oldClusterId, dataSourceList) in dataSource.dsMap) {
            if (dataSourceList.clusterId != clusterId) {
                continue
            }

            val dss = dataSourceList.dataSources
            if (dss != null) {
                val it = dss.iterator()
                while (it.hasNext()) {
                    val ds = it.next()
                    if (ds.shardId == shardId) {
                        it.remove()
                    }
                }
            }

            if (dss == null || dss.size == 0) {
                // 数据源为空，删除整个配置
                dataSource.dsMap.remove(oldClusterId)
                break
            }
        }

        // 更新数据
        mpm.dao.createOrUpdateData(DATASOURCE_NODE_NAME, toJson(dataSource))

        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, null)
    }

    @GetMapping(value = ["/queryDatabaseConfig"])
    open fun queryDatabaseConfig(): MgrGateBaseResp {

        val dataSourceString = mpm.dao.findNodeData(DATASOURCE_NODE_NAME)
        if (dataSourceString == null) {
            return MgrGateBaseResp(Code.ZK_DATA_DONT_EXIST.code, Code.ZK_DATA_DONT_EXIST.errMsg, null)
        }
        val dataSource = toObj<DataSource>(dataSourceString)

        val resp = LinkedList<DatabaseConfig>()
        for ((ip, ipDbCfg) in dataSource.dsMap) {
            val dss = ipDbCfg.dataSources
            if (dss != null) {
                for (eachDs in dss) {
                    val queryDBCfgResp = DatabaseConfig(
                        "${ipDbCfg.clusterId}-${eachDs.shardId}",
                        ip,
                        eachDs.shardId,
                        eachDs.socket,
                        eachDs.database,
                        eachDs.user,
                        eachDs.password,
                        ipDbCfg.clusterId
                    )
                    resp.add(queryDBCfgResp)
                }
            }
        }

        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, resp)
    }

    @PostMapping(value = ["/updateDatabaseConfig"])
    open fun updateWorldArea(@RequestBody param: DatabaseConfig): MgrGateBaseResp {
        // 长度检查
        if (param.user.length > 50 || param.password.length > 50 || param.database.length > 50 || param.socket.length > 50) {
            return MgrGateBaseResp(Code.PARAM_ERROR.code, Code.PARAM_ERROR.errMsg, null)
        }

        val dataSourceString = mpm.dao.findNodeData(DATASOURCE_NODE_NAME)
        if (dataSourceString == null) {
            return MgrGateBaseResp(Code.ZK_DATA_DONT_EXIST.code, Code.ZK_DATA_DONT_EXIST.errMsg, null)
        }

        // 反序列化
        val dataSource = toObj<DataSource>(dataSourceString)
        val existDs = dataSource.dsMap[param.configName]
            ?: return MgrGateBaseResp(Code.DATABASE_DONT_EXIST.code, Code.DATABASE_DONT_EXIST.errMsg, null)

        // 找到并变更数据
        var found = false
        val dss = existDs.dataSources
        if (dss == null) {
            return MgrGateBaseResp(Code.DATABASE_DONT_EXIST.code, Code.DATABASE_DONT_EXIST.errMsg, null)
        }

        val it = dss.iterator()
        while (it.hasNext()) {
            val eachDs = it.next()
            if (eachDs.shardId == param.shardId) {
                found = true // 找到对应要修改的了。
                it.remove()
                break
            }
        }
        if (!found) {
            return MgrGateBaseResp(Code.DATABASE_DONT_EXIST.code, Code.DATABASE_DONT_EXIST.errMsg, null)
        }

        val zkDs = ZkDatabaseConfig(
            param.shardId,
            param.socket,
            param.database,
            param.user,
            param.password
        )
        dss += zkDs
        existDs.clusterId = param.clusterId

        // 写入数据
        mpm.dao.createOrUpdateData(DATASOURCE_NODE_NAME, toJson(dataSource))

        return MgrGateBaseResp(Code.SUC_CODE.code, Code.SUC_CODE.errMsg, null)
    }

}