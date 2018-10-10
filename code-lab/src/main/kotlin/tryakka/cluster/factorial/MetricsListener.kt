package tryakka.cluster.factorial

import akka.cluster.metrics.StandardMetrics
import akka.cluster.metrics.StandardMetrics.Cpu
import akka.cluster.metrics.StandardMetrics.HeapMemory
import akka.cluster.ClusterEvent.CurrentClusterState
import akka.cluster.metrics.ClusterMetricsChanged
import java.time.Clock.system
import akka.cluster.metrics.ClusterMetricsExtension
import akka.event.LoggingAdapter
import akka.actor.AbstractActor
import akka.cluster.metrics.NodeMetrics
import akka.event.Logging
import akka.cluster.Cluster

class MetricsListener : AbstractActor() {

    internal var log = Logging.getLogger(context.system(), this)

    // 集群
    internal var cluster = Cluster.get(context.system())

    internal var extension = ClusterMetricsExtension.get(context.system())

    // Subscribe unto ClusterMetricsEvent events.
    override fun preStart() {
        extension.subscribe(self)
    }

    // Unsubscribe from ClusterMetricsEvent events.
    override fun postStop() {
        extension.unsubscribe(self)
    }

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
            .match(ClusterMetricsChanged::class.java) { clusterMetrics ->
                for (nodeMetrics in clusterMetrics.nodeMetrics) {
                    if (nodeMetrics.address().equals(cluster.selfAddress())) {
                        logHeap(nodeMetrics)
                        logCpu(nodeMetrics)
                    }
                }
            }
            .match(CurrentClusterState::class.java) { message ->
                // Ignore.
            }
            .build()
    }

    internal fun logHeap(nodeMetrics: NodeMetrics) {
        val heap = StandardMetrics.extractHeapMemory(nodeMetrics)
        if (heap != null) {
            log.info("Used heap: {} MB", heap.used().toDouble() / 1024.0 / 1024.0)
        }
    }

    internal fun logCpu(nodeMetrics: NodeMetrics) {
        val cpu = StandardMetrics.extractCpu(nodeMetrics)
        if (cpu != null && cpu.systemLoadAverage().isDefined) {
            log.info(
                "Load: {} ({} processors)", cpu.systemLoadAverage().get(),
                cpu.processors()
            )
        }
    }

}
