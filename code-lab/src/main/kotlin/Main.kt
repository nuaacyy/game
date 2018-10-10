import com.point18.slg2d.common.pc.xmlDir
import tryzk.resolveProto2Zk

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("无效的启动参数")
    } else {
        when (args[0]) {
            "proto2zk" -> {
                xmlDir = "../2csv_server/"
                resolveProto2Zk()
            }
            "ConfigHotUpload" -> {
                xmlDir = "../cehuaproto/2csv_server/"
                resolveProto2Zk()
            }
            else -> {
                println("无意义的启动参数")
            }
        }
    }
}