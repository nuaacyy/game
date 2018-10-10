package tryzk

import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.pc.ProtoCacheStore
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.retry.ExponentialBackoffRetry
import java.io.File

data class ZkConfig(
        var zkHost: String,
        var zkPort: Int,
        val rootNode: String,
        var clear: Boolean
)

fun resolveProto2Zk() {
    val zkConfigPath = "cfg/zkconfig.json"
    val zkFile = File(zkConfigPath)
    val configTxt = zkFile.readText()
    val zkConfig = toObj<ZkConfig>(configTxt)

    val retryPolicy = ExponentialBackoffRetry(1000, 3)
    val zkClient = CuratorFrameworkFactory.newClient(zkConfig.zkHost + ":" + zkConfig.zkPort.toString(), retryPolicy)
    zkClient.start()  // zk客户端启动
    zkClient.blockUntilConnected()

    val pcs = ProtoCacheStore()
    pcs.resolveProto2Zk(zkClient)

    zkClient.close() // 关闭client连接
}