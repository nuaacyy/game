package tryakka.cluster.transformation

import java.io.Serializable

val BACKEND_REGISTRATION = "BackendRegistration"

class TransformationJob(val text: String) : Serializable

class TransformationResult(val text: String) : Serializable {

    override fun toString(): String {
        return "TransformationResult($text)"
    }
}

class JobFailed(val reason: String, val job: TransformationJob) : Serializable {

    override fun toString(): String {
        return "JobFailed($reason)"
    }
}

