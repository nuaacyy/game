package com.point18.slg2d.common.baseg

import xyz.ariane.util.concurrent.NamedThreadFactory
import xyz.ariane.util.lzInfo
import com.point18.slg2d.common.commonfunc.normalLog
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import java.util.*

class ServerNet(
    val name: String, // 名字
    val addr: String, // 监听地址
    val port: Int, // 监听端口
    epsSize: Int, // 最大客户端数量
    private val initializer: ChannelInitializer<SocketChannel> // 连接初始化器
) {
    val epNoList: LinkedList<Int> = LinkedList()

    init {
        for (i in 1 until epsSize) {
            this.epNoList.add(i)
        }
    }

    fun start() {
        // 开始监听
        startListen()
    }

    private fun startListen() {
        // 创建网络服务器
        val bossGroup = NioEventLoopGroup()
        val workerGroup = NioEventLoopGroup()
        val b = ServerBootstrap()
        b.group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel::class.java)
            .option(ChannelOption.SO_BACKLOG, 128) // 服务器可连接队列大小
            .option(ChannelOption.SO_REUSEADDR, true)
            .childOption(ChannelOption.SO_KEEPALIVE, true)
            .childOption(ChannelOption.TCP_NODELAY, true)
            .childHandler(this.initializer) // 连接如何处理的初始化器

        // 绑定端口并开始接受连接
        val bindFuture = b.bind(port)
        normalLog.lzInfo {"${this.name} started on port: $port"}

        // 开启一个线程，监控网络服务器的关闭
        NamedThreadFactory(this.name + "-sync-close").newThread {
            try {
                // 等待关闭
                bindFuture.channel().closeFuture().sync()
                normalLog.lzInfo {"channel ${bindFuture.channel()} is closed"}

            } catch (e: Exception) {
                normalLog.error("", e)
            } finally {
                bossGroup.shutdownGracefully()
                workerGroup.shutdownGracefully()
                normalLog.lzInfo {"All loop groups {} are closed"}
            }
        }.start()

        // 等待端口启动完毕
        bindFuture.sync()
    }
}

fun startNet(
    name: String,
    addr: String,
    port: Int,
    clientNum: Int,
    initializer: ChannelInitializer<SocketChannel>
): ServerNet {
    val serverNet = ServerNet(name, addr, port, clientNum, initializer)
    serverNet.start()
    return serverNet
}

