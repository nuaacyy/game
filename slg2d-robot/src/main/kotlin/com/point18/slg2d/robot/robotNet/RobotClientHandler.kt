package com.point18.slg2d.robot.robotNet

import akka.actor.ActorRef
import com.point18.slg2d.common.netmsg.C2SMsg
import com.point18.slg2d.common.netmsg.MsgType
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.util.AttributeKey
import com.point18.slg2d.robot.robotData.findAwaitConnectRobot
import com.point18.slg2d.robot.robotData.removeAwaitConnectRobot
import com.point18.slg2d.robot.robotData.robotManage
import com.point18.slg2d.robot.robotruntime.RobotPropChg
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

@ChannelHandler.Sharable
class RobotClientHandler : SimpleChannelInboundHandler<C2SMsg>() {

	private val sessionKey = AttributeKey.valueOf<ActorRef>("key")   //定义一个属性，相当于map键值对：key是name，value是ActorRef

	private val rc = AtomicInteger()

	override fun channelActive(ctx: ChannelHandlerContext) {  //当连接建立时，channelActive（）回调方法被调用

//		println("channelActive连接建立")
        val channel = ctx.channel()    //  连接建立，获取channel
        val chg = RobotPropChg(MsgType.Connected, null, Date())   //  建立连接后，需要发一个MsgType.Connected消息告知

		val robot: ActorRef? = synchronized(robotManage) {
            val r = findAwaitConnectRobot(channel)   // 从等待连接队列的map中找到这个机器人
			if (r != null) {
                removeAwaitConnectRobot(channel)  // 找到机器人后，因为已经建立好连接，所以从等待连接的map中删除
			}
			r
		}

        if (robot == null) {   //  有channel但是找不到机器人，不正常，抛异常
			throw RuntimeException("找不到目标连接")
		}

		// 保存机器人引用，这么做是因为：需要建立channel->ActorRef的对应关系
		val attr = channel.attr(sessionKey)  // 在channel中加入这么一个属性键值对：“key”：ActorRef，channel的attr是其他handler可见的
		attr.set(robot)   // 把“key”设置成找到的robot

		// 通知消息
        robot.tell(chg, robot)   // 发送消息告知
	}

	public override fun channelRead0(ctx: ChannelHandlerContext, inMsg: C2SMsg) {
        val message = inMsg   // 接受的消息类型是C2SMsg
		// 分派并执行消息。
		val channel = ctx.channel()  // 从上下文获取channel
        val msgNo = MsgType.fromValue(message.msgType.msgType, MsgType.Error)  // 从枚举类中找到msgType
		val chg = RobotPropChg(msgNo, message, Date())  //  组成通知信息

		val attr = channel.attr(sessionKey)  // 从channel获取想要的attr - sessionKey
		val robot = attr.get()        // attr 是一个ActorRef

		// 通知消息
		robot.tell(chg, robot)
	}

	override fun channelInactive(ctx: ChannelHandlerContext) {  //当连接断开时，channelInactive（）回调方法是会被调用
//		println("channelInactive连接断开")

		val channel = ctx.channel()
		val chg = RobotPropChg(MsgType.Disconnected, null, Date())

		val attr = channel.attr(sessionKey)
		val robot = attr.get()

		// 通知消息
		robot.tell(chg, robot)
	}

	@Throws(Exception::class)
	override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
		println("触发断线异常 $cause")
		super.exceptionCaught(ctx, cause)
	}
}