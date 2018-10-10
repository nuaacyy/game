package com.point18.slg2d.mgr.dao

import com.point18.slg2d.common.baseg.ZkDomain
import com.point18.slg2d.common.commonfunc.zkIdGen
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.zkdomain.ServerProcess
import com.point18.slg2d.mgr.jsonHelper.toJson
import com.point18.slg2d.mgr.jsonHelper.toObj
import com.point18.slg2d.mgr.zk.ZookeeperFactoryBean
import org.apache.curator.framework.CuratorFramework
import org.apache.zookeeper.CreateMode
import org.springframework.beans.factory.annotation.Value
import javax.annotation.Resource

class ZkDao {

    @Resource
    lateinit var zookeeperFactoryBean: ZookeeperFactoryBean

    @Value("\${countNode}")
    lateinit var countNode: String

    private var zkClient: CuratorFramework? = null

    /**
     * 获取客户端
     */
    fun zkClient(): CuratorFramework {
        return zookeeperFactoryBean.`object`
    }

    /**
     * 生成Id
     */
    fun genId(): Int? {
        return zkIdGen(zkClient(), countNode, 200)
    }

    /**
     * 找到特定路径子节点的数据
     */
    fun findNodeDataOfChildren(path: String): MutableList<String> {

        val all = mutableListOf<String>()
        val zkClient = zkClient()
        if (zkClient.checkExists().forPath(path) == null) {
            return all
        }

        val childNodes = this.findNodeNameOfChildren(path)
        childNodes.forEach { it ->
            val tmpPath = "$path/$it"
            val tmp = this.findNodeData(tmpPath)
            if (tmp != null) {
                all.add(tmp)
            }
        }

        return all
    }

    /**
     * 找到特定路径节点的数据
     */
    fun findNodeData(path: String): String? {
        val zkClient = zkClient()
        if (zkClient.checkExists().forPath(path) == null) {
            return null

        } else {
            val nodeData = zkClient.data.forPath(path)
            if (nodeData.size <= 2) {
                println("这个节点${path}的数据<=2 Bytes，有问题")
                return null
            }

            return nodeData.toString(charset = Charsets.UTF_8)
        }
    }

    /**
     * 找到所有进程的配置
     */
    fun findAllProcesses(type: Int? = null): MutableList<ServerProcess> {
        val allProcess = mutableListOf<ServerProcess>()
        if (type != null && type != PROCESS_WORLD &&
            type != PROCESS_HOME &&
            type != PROCESS_CHAT &&
            type != PROCESS_PUBLIC &&
            type != PROCESS_GATE
        ) {
            return allProcess
        }

        val childNodes = this.findNodeNameOfChildren(PROCESS_NODE_NAME) // find all ip nodes
        if (type == null) {
            // 不指定设备类型，就找出全部类型的process
            childNodes.forEach { processIp ->
                this.findNodeDataOfChildren("$PROCESS_NODE_NAME/$processIp").forEach { processDataStr ->
                    allProcess.add(toObj(processDataStr))
                }
            }

        } else {
            // 指定设备类型，就找出对应类型的process
            childNodes.forEach { it ->
                val tmpPath = "$PROCESS_NODE_NAME/$it/$type"
                val tmpProcess = this.findNodeData(tmpPath)
                if (tmpProcess != null) {
                    allProcess.add(toObj(tmpProcess))
                }
            }
        }

        return allProcess
    }

    fun findNodeNameOfChildren(path: String): MutableList<String> {
        val zkClient = zkClient()
        if (zkClient.checkExists().forPath(path) == null) {
            //节点不存在
            return mutableListOf()
        }

        return zkClient.children.forPath(path)
    }

    /**
     * 清理特定路径的节点
     */
    fun clear(path: String): Boolean {
        val zkClient = zkClient()
        if (zkClient.checkExists().forPath(path) == null) {
            //节点不存在
            return false
        }

        zkClient.delete().guaranteed().deletingChildrenIfNeeded().forPath(path)
        return true
    }

    /**
     * 创建或更新指定节点的数据
     */
    fun createOrUpdateData(path: String, domainData: String) {
        val zkClient = zkClient()
        val byteData = domainData.toByteArray(charset = Charsets.UTF_8)
        if (zkClient.checkExists().forPath(path) == null) {
            //节点不存在直接创建
            zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                .forPath(path, byteData)
        } else {
            zkClient.setData().forPath(path, byteData)
        }
    }

    /**
     * 删除指定节点的数据
     */
    fun <V : ZkDomain> deleteSpecData(str2ObjList: (str: String) -> MutableList<V>, instance: V, path: String) {
        val allDomain = findAll(str2ObjList, path)
        allDomain.removeIf { it.id == instance.id }
        createOrUpdateData(path, toJson(allDomain))
    }

    fun <V : ZkDomain> deleteById(
        str2ObjList: (str: String) -> MutableList<V>,
        clazz: Class<V>,
        id: Long,
        path: String
    ) {
        val allDomain = findAll(str2ObjList, path)
        allDomain.removeIf { it.id == id }
        createOrUpdateData(path, toJson(allDomain))
    }

    /**
     * 找到指定节点中特定ID的数据
     */
    fun <V : ZkDomain> findById(str2ObjList: (str: String) -> MutableList<V>, id: Long, path: String): V? {
        val allDomain = findAll(str2ObjList, path)
        return allDomain.firstOrNull { it.id == id }
    }

    /**
     * 找到指定节点的数据
     */
    fun <V : ZkDomain> findAll(
        str2ObjList: (str: String) -> MutableList<V>, path: String
    ): MutableList<V> {
        val zkClient = zkClient()
        if (zkClient.checkExists().forPath(path) == null) {
            return mutableListOf()
        }

        val nodeData = zkClient.data.forPath(path)
        val nodeDataStr = nodeData.toString(charset = Charsets.UTF_8)
        return str2ObjList(nodeDataStr)
    }

    /**
     * 更新指定节点的数据
     */
    fun <V : ZkDomain> createOrUpdateListData(str2ObjList: (str: String) -> MutableList<V>, instance: V, path: String) {
        val allDomain = findAll(str2ObjList, path)
        allDomain.removeIf { it.id == instance.id }
        allDomain.add(instance)
        createOrUpdateData(path, toJson(allDomain))
    }

}