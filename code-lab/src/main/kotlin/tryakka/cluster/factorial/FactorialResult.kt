package tryakka.cluster.factorial

import java.math.BigInteger
import java.io.Serializable

class FactorialResult internal constructor(val n: Int, val factorial: BigInteger) : Serializable {
    companion object {
        private val serialVersionUID = 1L
    }
}

class FactorialParam(val n: Int) : Serializable