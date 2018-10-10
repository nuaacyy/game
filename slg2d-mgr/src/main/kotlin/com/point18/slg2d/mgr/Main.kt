package com.point18.slg2d.mgr

import com.point18.slg2d.mgr.config.AppConfig
import com.point18.slg2d.mgr.dao.ZkDao
import com.point18.slg2d.mgr.updatedata.TryUpdate
import com.point18.slg2d.mgr.util.backup
import com.point18.slg2d.mgr.util.restore
import org.springframework.boot.SpringApplication
import org.springframework.context.ConfigurableApplicationContext

open class MgrProcess {
    lateinit var appCtx: ConfigurableApplicationContext

    lateinit var dao: ZkDao
}

var mpm = object : MgrProcess() {

}

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        // 启动服务器
        startWebServer(args)

    } else if ("backup".equals(args[0])) {
        // 备份
        backup()

    } else if ("restore".equals(args[0])) {
        // 恢复
        restore()
    }
}

fun startWebServer(args: Array<String>) {
    mpm.appCtx = SpringApplication.run(AppConfig::class.java, *args)
    mpm.dao = mpm.appCtx.getBean("zkDao") as ZkDao

    // 更新
    val tryUpdate = TryUpdate()
    tryUpdate.tryUpdate()
}

