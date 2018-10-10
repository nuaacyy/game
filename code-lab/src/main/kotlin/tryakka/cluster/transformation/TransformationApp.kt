package tryakka.cluster.transformation

fun main(args: Array<String>) {
    println("starting")

    // starting 2 frontend nodes and 3 backend nodes
    backendStart(arrayOf("2551"))
    backendStart(arrayOf("2552"))
    backendStart(arrayOf<String>())

    frontendStart(arrayOf<String>())
}
