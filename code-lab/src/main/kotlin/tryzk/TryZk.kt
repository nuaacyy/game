package tryzk

import com.point18.slg2d.common.baseg.ZkDomain
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.PROCESS_NODE_NAME
import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.framework.recipes.cache.PathChildrenCache
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener
import org.apache.curator.retry.ExponentialBackoffRetry
import org.apache.zookeeper.CreateMode
import xyz.ariane.util.lzInfo

fun main(args: Array<String>) {
    normalLog.lzInfo { "测试ZK访问" }

    // 创建一个zkclient 访问zk 基本的sleep时间：1000ms ，最大尝试次数：3
    val retryPolicy = ExponentialBackoffRetry(1000, 3)

    // zk的访问地址
    val zkHost = "172.18.3.203"
    val zkPort = "12181"
    val zkClient = CuratorFrameworkFactory.newClient("$zkHost:$zkPort", retryPolicy)

    zkClient.start()  // zk客户端启动
    zkClient.blockUntilConnected()

    val nodes = zkClient.children.forPath(PROCESS_NODE_NAME)

    normalLog.lzInfo { "显示各子节点的名字" }
    nodes.forEach {
        println("子节点名字：$it")
        val subNodes = zkClient.children.forPath("$PROCESS_NODE_NAME/$it")
        subNodes.forEach { graSon ->
            println("孙节点名字：$graSon")
        }
    }

    normalLog.lzInfo { "显示各子节点的数据" }
    for (eachNode in nodes) {
        println(eachNode + "：" + zkClient.data.forPath("$PROCESS_NODE_NAME/${eachNode}").toString(charset = Charsets.UTF_8))
    }

    //删除节点
//    zkClient.delete().guaranteed().deletingChildrenIfNeeded().forPath("/super")
//    Thread.sleep(10)
//    pathChildCache.close() // 关闭这个cache

    zkClient.close() // 关闭client连接
}

class ListenerThread(pathChildCache0: PathChildrenCache) : Thread() {
    private val pathChildCache = pathChildCache0
    private var times = 1

    init {
        //  给节点缓存增加一个监听器，当缓存被操作时，监听器起作用
        pathChildCache.listenable.addListener(PathChildrenCacheListener { client0, event ->
            println("listening times = ${times}==========================================") // 打印这是第几次的监听结果
            times++ // 自加
            println("operate Type: ${event.type}")
            println("cache after operated:")
            pathChildCache.currentData.forEach {
                println("path:${it.path}，content ： ${it.data.toString(charset = Charsets.UTF_8)}")
            }
            println("end=================================================\n")
        })
    }

    override fun run() {
        pathChildCache.start()
    }
}

class ListenerRunnable(pathChildCache0: PathChildrenCache) : Runnable {

    private val pathChildCache = pathChildCache0
    private var times = 1

    init {
        //  给节点缓存增加一个监听器，当缓存被操作时，监听器起作用
        pathChildCache.listenable.addListener(PathChildrenCacheListener { client0, event ->
            println("listening times = ${times}==========================================") // 打印这是第几次的监听结果
            times++ // 自加
            println("operate Type: ${event.type}")
            println("cache after operated:")
            var nodeNO = 0
            pathChildCache.currentData.forEach {
                println("node:${nodeNO++} path:${it.path}，content ： ${it.data.toString(charset = Charsets.UTF_8)}")
            }
            nodeNO = 0
            println("end=================================================\n")
        })
    }

    override fun run() {
        pathChildCache.start()
    }
}

// 查找结点的全部数据
fun <V : ZkDomain> findAll(
        obj: CuratorFramework,
        str2ObjList: (str: String) -> MutableList<V>,
        clazz: Class<V>): MutableList<V> {
    val path = clazzToPath(clazz)  // 获得这个类的路径
//    println("path = ${path}")
    if (obj.checkExists().forPath(path) == null) { //  这个类在是否存在
        return mutableListOf()
    }
    val nodeData = obj.data.forPath(path)  //　获得这个节点的全部数据
    return str2ObjList(nodeData.toString(charset = Charsets.UTF_8))  //  转换数据格式
}

// 更新记录
fun <V : ZkDomain> createOrUpdate(obj: CuratorFramework, str2ObjList: (str: String) -> MutableList<V>, instance: V) {
    val allDomain = findAll(obj, str2ObjList, instance.javaClass)
    allDomain.removeIf { it.id == instance.id }
    allDomain.add(instance)
    createOrUpdateNode(obj, clazzToPath(instance.javaClass), toJson(allDomain))  //  tojson 转成 String
}

// 通过类的路径，查找，不存在则创建一个节点，无论是create或者是update，都是forpath（）
fun createOrUpdateNode(obj: CuratorFramework, path: String, domainData: String) {
    val byteData = domainData.toByteArray(charset = Charsets.UTF_8)
    if (obj.checkExists().forPath(path) == null) {
        //节点不存在直接创建
        obj.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                .forPath(path, byteData) // 创建一个永久节点，并且把bytedata数据放进去
        return
    }
    obj.setData().forPath(path, byteData)  //  存在这个节点则直接放进去，不用创建
}

// 转换：类-》类路径
fun <V : ZkDomain> clazzToPath(clazz: Class<V>): String {
    return "/" + clazz.typeName.replace('.', '/', true)  //  获得这个类的路径
}

// 删除记录
fun <V : ZkDomain> delete(obj: CuratorFramework, str2ObjList: (str: String) -> MutableList<V>, instance: V) {
    val allDomain = findAll(obj, str2ObjList, instance.javaClass)
    allDomain.removeIf { it.id == instance.id }
    createOrUpdateNode(obj, clazzToPath(instance.javaClass), toJson(allDomain))
}