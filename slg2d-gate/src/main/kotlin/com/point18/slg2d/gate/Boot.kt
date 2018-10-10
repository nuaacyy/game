package com.point18.slg2d.gate

import com.point18.slg2d.common.baseg.ClusterRole
import com.point18.slg2d.common.baseg.GameWorldShard
import com.point18.slg2d.common.baseg.ProcessMgr
import com.point18.slg2d.common.baseg.startNet
import com.point18.slg2d.common.commonfunc.processConfig
import com.point18.slg2d.common.constg.PROCESS_GATE
import com.point18.slg2d.common.constg.SERVER_NAME_GAME
import com.point18.slg2d.gate.net4g.*
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.LengthFieldBasedFrameDecoder
import io.netty.handler.codec.LengthFieldPrepender

const val MAX_FRAME_BYTES_LENGTH = 1024 * 1024 * 32

open class GateProcess : ProcessMgr(PROCESS_GATE, ClusterRole.gate) {

    override val includingShards: List<GameWorldShard> get() = emptyList()

    override fun beforeCreatingActorSystem() {
        dealFetchedConfig()
    }

    override fun afterCreatingActorSystem() {
        // 启动世界服的 Shard
        startWorldShardProxy()

        // 启动玩家服的 Shard 代理
        startHomeShardProxy()

        // 启动多播服务
        startMulticastService()

        // 启动网络
        startNet()
    }

    // 加载配置
    private fun dealFetchedConfig() {
        val processConfig = processConfig
        processConfig.gameId = platform.gameId.toInt()
        processConfig.id = ipProcess.id.toInt()

        processConfig.tcpAddr = ipProcess.tcpAddr
        processConfig.tcpPort = ipProcess.tcpPort

        processConfig.processInnerAddr = ipProcess.processIp
        processConfig.clusterId = commCfg.id
    }

    private fun startMulticastService() {
        actorSystem.actorOf(MulticastService.props(), "multicastService")
        println("startMulticastService game服启动多播服务")
    }

    private fun startNet() {
        // 启动网络
        startNet(
                SERVER_NAME_GAME,
                processConfig.tcpAddr,
                processConfig.tcpPort,
                100000,
                object : ChannelInitializer<SocketChannel>() {
                    override fun initChannel(ch: SocketChannel) {
                        // 解码处理
                        // * lengthFieldOffset：从头部偏移多少字节才算是长度部分。
                        // * lengthFieldLength：长度字段有多长
                        // * lengthAdjustment：计算长度时，从payload开始多增加多少字节，-4表示增加4字节
                        // * initialBytesToStrip：解码后，抛弃多少字节头部数据
                        ch.pipeline()
                                .addLast(
                                        "frameDecoder",
//                                        LengthFieldBasedFrameDecoder(MAX_FRAME_BYTES_LENGTH, 0, 4, -4, 4, true)
                                                CustomLengthFrameDecoder(MAX_FRAME_BYTES_LENGTH, 0, 4, -4, 4, true)
                                )
                        ch.pipeline().addLast("protobufDecoder", C2SMsgProtobufDecoder())

                        // 编码处理
                        ch.pipeline().addLast("frameEncoder", LengthFieldPrepender(4, true))
                        ch.pipeline().addLast("protobufEncoder", C2SMsgProtobufEncoder())

                        ch.pipeline().addLast(GameEpHandler())
                    }
                })
    }
}

// 网关进程单例
var gpm = object : GateProcess() {

}