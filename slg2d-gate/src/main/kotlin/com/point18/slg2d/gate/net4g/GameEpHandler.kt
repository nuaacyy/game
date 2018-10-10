package com.point18.slg2d.gate.net4g

import akka.actor.ActorRef
import akka.actor.PoisonPill
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.netmsg.C2SMsg
import com.point18.slg2d.gate.gpm
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.util.AttributeKey
import xyz.ariane.util.lzDebug
import xyz.ariane.util.lzInfo

@ChannelHandler.Sharable
class GameEpHandler : SimpleChannelInboundHandler<C2SMsg>() {

    private val actorKey: AttributeKey<ActorRef> = AttributeKey.valueOf("actor")

    override fun channelActive(ctx: ChannelHandlerContext) {

        normalLog.lzInfo { "连接建立：${ctx.channel()} ${ctx.channel().remoteAddress()}" }

        // 启动一个actor
        val ref = gpm.actorSystem.actorOf(
            ChannelActor.props(
                ctx
            )
        )
        ctx.channel().attr(actorKey).set(ref)
    }

    private fun tellChannelActor(ctx: ChannelHandlerContext, msg: Any) {
        val channelActor = ctx.channel().attr(actorKey).get()
        channelActor?.tell(msg, ActorRef.noSender())
    }

    override fun channelRead0(ctx: ChannelHandlerContext, msg: C2SMsg) {

        normalLog.lzDebug { "接收到：$msg" }

        // 提交消息给Actor
        tellChannelActor(ctx, msg)
    }

    override fun channelInactive(ctx: ChannelHandlerContext) {

        normalLog.lzInfo { "连接断开：${ctx.channel()}" }

        // 让Actor停止
        tellChannelActor(ctx, PoisonPill.getInstance())
    }

	@Suppress("OverridingDeprecatedMember")
	override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
		try {
			val message = cause.message
			if (message != null) {
				with(message) {
					if (!contains("reset by peer")
						&& !contains("Connection timed out")
						&& !contains("No route to host")
						&& !contains("Broken pipe")
						&& !contains("远程主机强迫关闭了")
						&& !contains("连接超时")
						&& !contains("放弃了一个已建立的连接")) {
						normalLog.error("", cause)
					}
				}
			}
		} finally {
            // 连接关闭
			ctx.close()
		}
	}
}