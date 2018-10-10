package com.point18.slg2d.common.areaInitFromZK

import com.point18.slg2d.common.baseg.ZkDomain
import com.point18.slg2d.common.commonfunc.toJson
import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.retry.ExponentialBackoffRetry
import org.apache.zookeeper.CreateMode
import java.util.*

class BackStageZooKeeperDao(zkAddr: String) {

    // 创建一个zkclient 访问zk 基本的sleep时间：1000ms ，最大尝试次数：3
    private val retryPolicy = ExponentialBackoffRetry(1000, 3)

    // zk的访问地址是192.168.189.32:12181
    val zkClient: CuratorFramework

    init {
        val zkClient = CuratorFrameworkFactory.newClient(zkAddr, retryPolicy)
        if (zkClient == null) {
            throw RuntimeException("无法初始化zk客户端")
        }

        zkClient.start()
        zkClient.blockUntilConnected()

        this.zkClient = zkClient
    }

    fun close() {
        val zkClient = zkClient

        zkClient.close()
    }

    fun findAllByPath(path: String): LinkedList<String> {
        val all = LinkedList<String>()
        if (zkClient.checkExists().forPath(path) == null) {
            // println("findAllByPath 查找${path}下面的全部数据，结果这个${path}不存在")
            return all
        }

        val childNodes = this.showAllChildrenName(path)
        childNodes.forEach { it ->
            val tmpPath = "$path/$it"
            val tmp = this.findNodeData(tmpPath)
            if (tmp != null) {
                all.add(tmp)
                // println("findAllByPath 查找${path}下面的全部数据，结果${tmpPath}存在$tmp")
            }
        }
        return all
    }

    fun findNodeData(path: String): String? {
        if (zkClient.checkExists().forPath(path) == null) {
            // println("在${path}节点找数据，但是这个节点没有数据")
            return null
        } else {
            val nodeData = zkClient.data.forPath(path)
            if (nodeData.size <= 2) {
                // println("这个节点${path}两个Bytes都没有，有问题")
            }
            val nodeDataString = nodeData.toString(charset = Charsets.UTF_8)
            // println("在${path}节点找数据，这个节点数据:$nodeDataString")
            return nodeDataString
        }
    }

    private fun showAllChildrenName(path: String): LinkedList<String> {
        if (zkClient.checkExists().forPath(path) == null) {
            //节点不存在
            // println("showAllChildrenName:${path}节点不存在")
            return LinkedList()
        }
        val childNodes = LinkedList(zkClient.children.forPath(path))
        if (childNodes.size == 0) {
            // println("${path}节点存在，但是没有子节点")
        }
        childNodes.forEach {
            // println("${path} 存在节点 ${it}")
        }
        return childNodes
    }

    fun clear(path: String) {
        // println(" 正在清理节点：${path}")
        if (zkClient.checkExists().forPath(path) == null) {
            //节点不存在
            // println("正在清理节点:${path}节点不存在")
            return
        }
        zkClient.delete().guaranteed().deletingChildrenIfNeeded().forPath(path)
    }

    //新建节点
    fun createOneNode(path: String, domainData: String) {
        val byteData = domainData.toByteArray(charset = Charsets.UTF_8)
        if (zkClient.checkExists().forPath(path) == null) {
            //不存在这个节点，创建一个
            // println("$path 不存在，createOneNode创建它")
            zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                .forPath(path, byteData)
            return
        } else {
            // println("$path 存在，createOneNode不创建它")
        }
    }

    fun createOrUpdateNode(path: String, domainData: String) {
        val byteData = domainData.toByteArray(charset = Charsets.UTF_8)
        if (zkClient.checkExists().forPath(path) == null) {
            //节点不存在直接创建
            zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                .forPath(path, byteData)
            return
        }
        zkClient.setData().forPath(path, byteData)
    }

    fun <V : ZkDomain> delete(str2ObjList: (str: String) -> LinkedList<V>, instance: V, path: String) {
        val allDomain = findAll(str2ObjList, path)
        allDomain.removeIf { it.id == instance.id }
        createOrUpdateNode(path, toJson(allDomain))
    }

    fun <V : ZkDomain> deleteById(
        str2ObjList: (str: String) -> LinkedList<V>,
        clazz: Class<V>,
        id: Long,
        path: String
    ) {
        val allDomain = findAll(str2ObjList, path)
        allDomain.removeIf { it.id == id }
        createOrUpdateNode(path, toJson(allDomain))
    }

    fun <V : ZkDomain> findById(str2ObjList: (str: String) -> LinkedList<V>, id: Long, path: String): V? {
        val allDomain = findAll(str2ObjList, path)
        return allDomain.firstOrNull { it.id == id }
    }

    fun <V : ZkDomain> findAll(
        str2ObjList: (str: String) -> LinkedList<V>, path: String
    ): LinkedList<V> {
        if (zkClient.checkExists().forPath(path) == null) {
            return LinkedList()
        }
        val nodeData = zkClient.data.forPath(path)
        return str2ObjList(nodeData.toString(charset = Charsets.UTF_8))
    }

    fun <V : ZkDomain> createOrUpdate(str2ObjList: (str: String) -> LinkedList<V>, instance: V, path: String) {
        val allDomain = findAll(str2ObjList, path)
        allDomain.removeIf { it.id == instance.id }
        allDomain.add(instance)
        createOrUpdateNode(path, toJson(allDomain))
    }
}