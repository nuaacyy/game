package tryakka.sharding

import java.io.Serializable

/**
 * me.chaopeng.akka.message.ClusterMessage
 *
 * @author chao
 * @version 1.0 - 2015-09-23
 */
class ClusterMessage(val id: Int, val payload: Any) : Serializable