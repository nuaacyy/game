package trycurator

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.framework.recipes.cache.PathChildrenCache
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener
import org.apache.curator.retry.ExponentialBackoffRetry
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder
import org.apache.curator.x.discovery.ServiceInstance
import org.apache.curator.x.discovery.details.InstanceSerializer
import org.apache.zookeeper.CreateMode
import org.apache.curator.x.discovery.UriSpec
import org.apache.curator.x.discovery.strategies.RandomStrategy

fun tryCurator1() {
    val policy = ExponentialBackoffRetry(1000, 3)
    val client = CuratorFrameworkFactory.newClient("192.168.189.32:12181", policy)
    client.start()

    //添加节点
    val str = "This is Client1"
    client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/super/client", str.toByteArray())

    //读取节点
    val rst = client.data.forPath("/super/client")
    println("读取节点内容:" + rst.toString(charset = Charsets.UTF_8))

    //删除节点
    client.delete().guaranteed().deletingChildrenIfNeeded().forPath("/super")
}

fun tryCurator2() {
    val td = WatchThread()
    td.start()

    val policy = ExponentialBackoffRetry(1000, 3)
    val client = CuratorFrameworkFactory.newClient("192.168.189.32:12181", policy)
    client.start()

    //添加节点
    val str1 = "This is Client1"
    client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/super/client1", str1.toByteArray())
    Thread.sleep(500)
    val str2 = "This is Client2"
    client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/super/client2", str2.toByteArray())
    Thread.sleep(500)

    //更改节点
    val str3 = "This is Client3"
    client.setData().forPath("/super/client2", str3.toByteArray())

    val tmp = client.children.forPath("/com/point18/slg2d/common/zkdomain/IpHostCfg/192.168.189.75/1")
    val rst = client.data.forPath("/com/point18/slg2d/common/zkdomain/IpHostCfg/192.168.189.75/1")
    println(rst.toString(charset = Charsets.UTF_8))
    tmp.forEach { println("super have child:" + it) }

    Thread.sleep(5000)

    //删除节点
    client.delete().guaranteed().deletingChildrenIfNeeded().forPath("/super")
}

fun tryCurator4() {
    val td = WatchThread()
    td.start()

    val policy = ExponentialBackoffRetry(1000, 3)
    val client = CuratorFrameworkFactory.newClient("192.168.189.32:12181", policy)
    client.start()

    //添加节点
//    val str1 = "{\"datasourceList\":[{\"shardId\":0,\"socket\":\"192.168.11.160:3306\",\"database\":\"slg2d\",\"user\":\"root\",\"password\":\"123456\"}]}"
//    client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/Mgr/172.30.18.188/datasource", str1.toByteArray())
//    Thread.sleep(500)

    //更改节点
//    val str3 = "This is Client3"
//    client.setData().forPath("/super/client2", str3.toByteArray())
//    val rst = client.data.forPath("/Mgr/172.30.18.188/datasource")
//    println(rst.toString(charset = Charsets.UTF_8))

    val tmp = client.children.forPath("/Mgr")
    tmp.forEach { println("have child:" + it) }

    Thread.sleep(5000)
    //删除节点
}

class WatchThread : Thread() {
    override fun run() {
        val policy = ExponentialBackoffRetry(1000, 3)
        val client = CuratorFrameworkFactory.newClient("192.168.189.32:12181", policy)
        client.start()

        val pathChildCache = PathChildrenCache(client, "/super", true)
        pathChildCache.listenable.addListener(PathChildrenCacheListener { c, event ->
            println("====================================================")
            println("event:${event.type}")
            println("当前子节点:")
            pathChildCache.currentData.forEach {
                println("子节点:${it.path}，改子节点内容 ： ${it.data.toString(charset = Charsets.UTF_8)}")
            }
            println("====================================================")
        })
        pathChildCache.start()
        Thread.sleep(10000)
        pathChildCache.close()
        client.close()
    }
}

fun tryCurator3() {
    val serverThread = ServerThread()
    serverThread.start()
    Thread.sleep(3000)

    val clientThread = ClientThread()
    clientThread.start()
    Thread.sleep(10000000)
}

data class ServerPayLoad(
    var payload: Int
)

class ServerThread : Thread() {
    override fun run() {
        val policy = ExponentialBackoffRetry(1000, 3)
        val client = CuratorFrameworkFactory.newClient("192.168.189.32:12181", policy)
        client.start()

        val registry = ServiceDiscoveryBuilder.builder(ServerPayLoad::class.java).client(client)
            .serializer(object : InstanceSerializer<ServerPayLoad> {
                override fun serialize(instance: ServiceInstance<ServerPayLoad>?): ByteArray {
                    return jacksonObjectMapper().writeValueAsBytes(instance)
                }

                override fun deserialize(bytes: ByteArray?): ServiceInstance<ServerPayLoad> {
                    return jacksonObjectMapper().readValue(bytes!!)
                }
            })
            .basePath("services")
            .build()
        registry.start()

        val host1 = ServiceInstance.builder<ServerPayLoad>()
            .id("host1")
            .name("Server")
            .port(21888)
            .address("192.168.189.1")
            .payload(ServerPayLoad(5))
            .uriSpec(UriSpec("{scheme}://{address}:{port}"))
            .build()
        registry.registerService(host1)

        val host2 = ServiceInstance.builder<ServerPayLoad>()
            .id("host2")
            .name("Server")
            .port(21888)
            .address("192.168.189.2")
            .payload(ServerPayLoad(3))
            .uriSpec(UriSpec("{scheme}://{address}:{port}"))
            .build()
        registry.registerService(host2)

        println("成功注册服务器...")
        Thread.sleep(100000)
        registry.unregisterService(host1)
        Thread.sleep(5000)
        registry.unregisterService(host2)
    }
}

class ClientThread : Thread() {
    override fun run() {
        val policy = ExponentialBackoffRetry(1000, 3)
        val client = CuratorFrameworkFactory.newClient("192.168.189.32:12181", policy)
        client.start()

        val discovery = ServiceDiscoveryBuilder.builder(ServerPayLoad::class.java).client(client)
            .serializer(object : InstanceSerializer<ServerPayLoad> {
                override fun serialize(instance: ServiceInstance<ServerPayLoad>?): ByteArray {
                    return jacksonObjectMapper().writeValueAsBytes(instance)
                }

                override fun deserialize(bytes: ByteArray?): ServiceInstance<ServerPayLoad> {
                    return jacksonObjectMapper().readValue(bytes!!)
                }
            }).basePath("services").build()
        discovery.start()

        for (i in 0..10) {
            val provider = discovery.serviceProviderBuilder().serviceName("Server").providerStrategy(RandomStrategy<ServerPayLoad>()).build()
            provider.start()
            println("发现服务,name:${provider.instance.name}ip:${provider.instance.address},port:${provider.instance.port}")
            Thread.sleep(1000)
        }
        Thread.sleep(10000)
    }
}