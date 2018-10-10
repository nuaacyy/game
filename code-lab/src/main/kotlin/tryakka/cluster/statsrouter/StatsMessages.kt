package tryakka.cluster.statsrouter

import java.io.Serializable

// JOB消息
class StatsJob(val text: String) : Serializable

// 结果消息
class StatsResult(val meanWordLength: Double) : Serializable {

    override fun toString(): String {
        return "meanWordLength: " + meanWordLength
    }
}

// JOB失败消息
class JobFailed(val reason: String) : Serializable {

    override fun toString(): String {
        return "JobFailed($reason)"
    }
}

class StatsLength(val wordCount: Int) : Serializable


