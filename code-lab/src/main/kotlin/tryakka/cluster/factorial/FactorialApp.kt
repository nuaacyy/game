package tryakka.cluster.factorial

// 在一个Cluster里实现前端分派任务，后端计算，再由前端返还结果的机制。
object FactorialApp {

    @JvmStatic
    fun main(args: Array<String>) {
        // starting 3 backend nodes and 1 frontend node
        // 启动3个后端和1个前端
        factorialBackendStart(arrayOf("2551"))
        factorialBackendStart(arrayOf("2552"))
        factorialBackendStart(arrayOf())

        factorialFrontendStart(arrayOf())
    }
}