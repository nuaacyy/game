package tryakka.cluster.statsrouter

import akka.actor.AbstractActor
import akka.actor.Address
import akka.actor.Cancellable
import akka.cluster.Cluster
import akka.cluster.ClusterEvent.*
import akka.cluster.MemberStatus
import scala.concurrent.duration.Duration
import java.util.ArrayList
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit
import kotlin.collections.HashSet

// servicePath 是服务的目标路径
class StatsSampleClient(internal val servicePath: String) : AbstractActor() {

    internal val tickTask: Cancellable
    internal val nodes: MutableSet<Address> = HashSet<Address>()

    // 获取到集群
    internal var cluster = Cluster.get(context.system())

    init {
        // 2s的间隔
        val interval = Duration.create(2, TimeUnit.SECONDS)

        // 发起间隔任务，每隔一定时间发送tick消息
        tickTask = context
            .system()
            .scheduler()
            .schedule(
                interval, interval, self(), "tick",
                context.dispatcher(), null
            )
    }

    // subscribe to cluster changes, MemberEvent
    override fun preStart() {
        // 订阅成员事件消息
        cluster.subscribe(self(), MemberEvent::class.java, ReachabilityEvent::class.java)
    }

    // re-subscribe when restart
    override fun postStop() {
        cluster.unsubscribe(self())

        // 取消定时任务
        tickTask.cancel()
    }

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
            .matchEquals("tick", { t -> !nodes.isEmpty() }) { t ->
                // 随机选择一个
                val nodesList = ArrayList(nodes)
                val address = nodesList.get(
                    ThreadLocalRandom.current().nextInt(
                        nodesList.size
                    )
                )

                // 获取服务，并发送消息
                val serviceAddr = address.toString() + servicePath
                println("随机选择一个发送StatsJob $serviceAddr")
                val service = context.actorSelection(serviceAddr)
                service.tell(
                    StatsJob("this is the text that will be analyzed"),
                    self()
                )
            }
            .match(StatsResult::class.java, {
                // 打印结果
                println(it)
            })
            .match(JobFailed::class.java, {
                // 打印失败
                println(it)
            })
            .match(CurrentClusterState::class.java) { state ->
                println("收到 CurrentClusterState")

                // 清理掉nodes列表
                nodes.clear()

                // 重新将所有compute成员添加到nodes列表中
                for (member in state.members) {
                    if (member.hasRole("compute") && member.status().equals(MemberStatus.up())) {
                        println("成功添加 member ${member.address()}")
                        nodes.add(member.address())
                    }
                }
            }
            .match(MemberUp::class.java) { mUp ->
                println("收到 MemberUp")

                // 将compute添加到列表中
                if (mUp.member().hasRole("compute")) {
                    println("成功添加 member ${mUp.member().address()}")
                    nodes.add(mUp.member().address())
                }

            }
            .match(MemberEvent::class.java) { other ->
                println("收到 MemberEvent 准备移除 member 原因 ${other.toString()}")

                // 移除成员
                nodes.remove(other.member().address())
            }
            .match(UnreachableMember::class.java) { unreachable ->
                println("收到 UnreachableMember 准备移除 member")

                // 移除成员
                nodes.remove(unreachable.member().address())
            }
            .match(ReachableMember::class.java) { reachable ->
                println("收到 ReachableMember 重新添加 member")

                // 重新添加成员
                if (reachable.member().hasRole("compute"))
                    nodes.add(reachable.member().address())
            }
            .build()
    }

}
