package com.point18.slg2d.mgr.config

import com.point18.slg2d.mgr.dao.ZkDao
import com.point18.slg2d.mgr.zk.ZookeeperFactoryBean
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.PropertySource

@SpringBootApplication(scanBasePackages = [
    "com.point18.slg2d.mgr.config"])
@PropertySource(value = ["file:zookeeper.properties"])
open class AppConfig {
    @Bean
    open fun zookeeperFactory(): ZookeeperFactoryBean {
        return ZookeeperFactoryBean()
    }

    @Bean
    open fun zkDao(): ZkDao {
        return ZkDao()
    }
}