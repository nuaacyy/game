package com.point18.slg2d.mgr.zk

import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.retry.ExponentialBackoffRetry
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.FactoryBean
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ZookeeperFactoryBean : FactoryBean<CuratorFramework>, InitializingBean, DisposableBean {

    private lateinit var zkClient: CuratorFramework

    @Value("\${zk.url}")
    private lateinit var connectString: String

    override fun isSingleton(): Boolean {
        return true
    }

    override fun getObject(): CuratorFramework {
        return zkClient
    }

    override fun getObjectType(): Class<*> {
        return CuratorFramework::class.java
    }

    override fun afterPropertiesSet() {
        //重试策略
        val retryPolicy = ExponentialBackoffRetry(1000, 3)

        //创建连接
        zkClient = CuratorFrameworkFactory.newClient(connectString, 2000, 10000, retryPolicy)
        zkClient.start()
    }

    override fun destroy() {
        zkClient.close()
    }
}