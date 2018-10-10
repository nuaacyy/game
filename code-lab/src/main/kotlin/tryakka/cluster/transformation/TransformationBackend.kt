package tryakka.cluster.transformation

import akka.actor.AbstractActor
import akka.cluster.Cluster
import akka.cluster.ClusterEvent.CurrentClusterState
import akka.cluster.ClusterEvent.MemberUp
import akka.cluster.Member
import akka.cluster.MemberStatus

class TransformationBackend : AbstractActor() {

    internal var cluster = Cluster.get(context.system())

    //subscribe to cluster changes, MemberUp
    override fun preStart() {
        cluster.subscribe(self(), MemberUp::class.java)
    }

    //re-subscribe when restart
    override fun postStop() {
        cluster.unsubscribe(self())
    }

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
            .match(TransformationJob::class.java) { job ->
                // 由于front是转发的，所以这里的消息会直接交给发起ask的sender来处理。
                sender().tell(
                    TransformationResult(job.text.toUpperCase()),
                    self()
                )
            }
            .match(CurrentClusterState::class.java) { state ->
                println("收到 CurrentClusterState ${state.members.count()}")
                // 这个消息会在第一次加入集群后收到
                // 这里要做的就是给每个up成员发送注册消息
                for (member in state.members) {
                    if (member.status() == MemberStatus.up()) {
                        println("当前状态，有UP成员 ${member.roles}")
                        register(member)
                    }
                }
            }
            .match(MemberUp::class.java) { mUp ->
                // 向新up的成员发送注册消息
                println("有新成员 UP ${mUp.member()} ${mUp.member().roles} ${mUp.member().address()}")
                register(mUp.member()) }
            .build()
    }

    /**
     * 向前端注册
     * 这里用了selection
     */
    internal fun register(member: Member) {
        val hasRole = member.hasRole("frontend")
        if (hasRole) {
            context
                .actorSelection(member.address().toString() + "/user/frontend")
                .tell(BACKEND_REGISTRATION, self())
        }
    }
}
