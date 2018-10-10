package tryakka.sharding

import akka.cluster.sharding.ShardRegion

/**
 * me.chaopeng.akka.extractor.ClusterExtractor
 *
 * @author chao
 * @version 1.0 - 2015-09-23
 */
class ClusterExtractor : ShardRegion.MessageExtractor {

    override fun entityId(message: Any): String? {
        return (message as? ClusterMessage)?.id?.toString()
    }

    override fun entityMessage(message: Any): Any? {
        return (message as? ClusterMessage)?.payload
    }

    override fun shardId(message: Any): String? {
        if (message is ClusterMessage) {
            val id = message.id
            return (id % 10).toString()
        }
        return null
    }
}