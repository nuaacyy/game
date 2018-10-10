package com.point18.slg2d.mgr.util

import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.retry.ExponentialBackoffRetry
import java.io.File

fun backup() {
    val zkConfigPath = "./cfg/zkconfig.json"
    val zkFile = File(zkConfigPath)
    val configTxt = zkFile.readText()
    val zkConfig = toObj<ZkConfig>(configTxt)
    println("${zkConfig.zkHost}  ${zkConfig.zkPort} ")

    val retryPolicy = ExponentialBackoffRetry(1000, 3)
    val zkClient = CuratorFrameworkFactory.newClient("${zkConfig.zkHost}:${zkConfig.zkPort}", retryPolicy)
    zkClient.start()  // zk客户端启动
    zkClient.blockUntilConnected()
    val rootDir = "/Mgr"

    val zkData = hashMapOf<String, String>()

    val blackList = listOf(
        "GameConfig", "GateArea", "PublicArea", "HomeArea", "172.30.18.188"
    )

    traverseZkNodes(zkClient, mutableListOf(rootDir), zkData, blackList)

    zkData.forEach {
        println(it.key)
        println(it.value)
    }

    val filePath = "./zkDataBackup.json"
    val file = File(filePath)
    println(file.absolutePath)
    file.writeText(toJson(zkData))
    zkClient.close() // 关闭client连接
}

fun traverseZkNodes(zkCli: CuratorFramework, dirs: MutableList<String>, zkDataMap: HashMap<String, String>, blackList: List<String>?) {
    for (eachDir in dirs) {

        // 访问每个节点都必须先查看节点数据,把节点数据存进map,再去看有没有子节点
        val data = zkCli.data.forPath(eachDir)
        val dataString = data.toString(charset = Charsets.UTF_8)

        // 黑名单节点名移除
        var childNodes = zkCli.children.forPath(eachDir)
        if (blackList != null) {
            childNodes = childNodes - blackList
        }

        // 如果节点没有内容就跳过不写进map
        if (dataString.trim().isEmpty() && childNodes.isEmpty()) {
            continue
        } else {
            zkDataMap[eachDir] = dataString
        }

        // 没有子节点就跳过,继续下一个节点
        if (childNodes.isEmpty()) {
            continue
        }

        // 拼接子节点全路径
        val newDirs = mutableListOf<String>()
        childNodes.forEach { newDirs.add("$eachDir/$it") }

        // 递归子节点
        traverseZkNodes(zkCli, newDirs, zkDataMap, null)
    }
}