package tryakka.cluster.simple

import akka.cluster.Cluster
import akka.cluster.ClusterEvent.MemberEvent
import akka.cluster.ClusterEvent.MemberRemoved
import akka.cluster.ClusterEvent.UnreachableMember
import akka.cluster.ClusterEvent.MemberUp
import akka.cluster.ClusterEvent.MemberJoined
import akka.cluster.ClusterEvent.CurrentClusterState
import akka.actor.AbstractActor
import akka.cluster.ClusterEvent
import akka.event.Logging

class SimpleClusterListener2 : AbstractActor() {
    // 日志
    internal var log = Logging.getLogger(context.system(), this)

    // 获取集群
    internal var cluster = Cluster.get(context.system())

    override fun preStart() {
        // 订阅集群事件
        cluster.subscribe(
            self(),
            MemberEvent::class.java,
            UnreachableMember::class.java
        )
    }

    override fun postStop() {
        // 取消订阅
        cluster.unsubscribe(self)
    }

    override fun createReceive(): Receive {
        return receiveBuilder()
            .match(CurrentClusterState::class.java) { state ->
                log.info("收到 CurrentClusterState，state：${state.allRoles}，当前所有成员: ${state.members.count()}")
            }
            .match(MemberJoined::class.java) { mJoined ->
                log.info("成员 is Joined: ${mJoined.member()}")
            }
            .match(MemberUp::class.java) { mUp ->
                log.info("成员 is Up: ${mUp.member()}")
            }
            .match(UnreachableMember::class.java) { mUnreachable ->
                log.info("成员 detected as 无法到达: ${mUnreachable.member()}")
            }
            .match(ClusterEvent.ReachableMember::class.java) { mReachable ->
                log.info("成员 detected as 可到达: ${mReachable.member()}")
            }
            .match(ClusterEvent.MemberExited::class.java) { mExisted ->
                log.info("成员 is 离开: ${mExisted.member()}")
            }
            .match(MemberRemoved::class.java) { mRemoved ->
                log.info("成员 is 移除: ${mRemoved.member()}")
            }
            .match(MemberEvent::class.java) { message ->
                // ignore
                log.info("其他成员事件：$message")
            }
            .build()
    }
}
