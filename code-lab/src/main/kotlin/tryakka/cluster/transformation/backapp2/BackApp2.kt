package tryakka.cluster.transformation.backapp2

import tryakka.cluster.transformation.backendStart

fun main(args: Array<String>) {
    backendStart(arrayOf("2552"))
    backendStart(arrayOf<String>())
}