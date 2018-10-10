package com.point18.slg2d.mgr.util

import com.point18.slg2d.common.commonfunc.toObj
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.retry.ExponentialBackoffRetry
import org.apache.zookeeper.CreateMode
import java.io.File

fun restore() {
    // 加载zk相关的配置
    val zkConfigPath = "./cfg/zkconfig.json"
    val zkFile = File(zkConfigPath)
    val configTxt = zkFile.readText()
    val zkConfig = toObj<ZkConfig>(configTxt)
    println("${zkConfig.zkHost}  ${zkConfig.zkPort} ")

    val retryPolicy = ExponentialBackoffRetry(1000, 3)
    val zkClient = CuratorFrameworkFactory.newClient(zkConfig.zkHost + ":" + zkConfig.zkPort.toString(), retryPolicy)
    zkClient.start()  // zk客户端启动
    zkClient.blockUntilConnected()

    val rootNode = zkConfig.rootNode

    val rootExist = zkClient.checkExists().forPath(rootNode)
    if (rootExist != null) {
        if (zkConfig.clear) {
            // 删除root节点
            println("root节点 $rootNode 存在，需要先删除并创建")
            zkClient.delete().guaranteed().deletingChildrenIfNeeded().forPath(rootNode)

            zkClient
                .create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath(rootNode, "".toByteArray())
        }

    } else {
        println("root节点 $rootNode 不存在，创建")
        zkClient
            .create()
            .creatingParentsIfNeeded()
            .withMode(CreateMode.PERSISTENT)
            .forPath(rootNode, "".toByteArray())
    }

    val dataPath = "./zkDataBackup.json"
    val dataFile = File(dataPath)
    val dataJson = dataFile.readText()
    val dataMap = toObj<HashMap<String, String>>(dataJson)

    for (eachEntry in dataMap) {
        if (zkClient.checkExists().forPath(eachEntry.key) == null) {
            // 节点不存在直接创建
            println("节点 ${eachEntry.key} 不存在！尝试创建。")
            zkClient
                .create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath(eachEntry.key, eachEntry.value.toByteArray())
        }

        zkClient.setData().forPath(eachEntry.key, eachEntry.value.toByteArray())
    }


    zkClient.close() // 关闭client连接
}