package trykotlin.refval

class MyClass(var level: Int)

fun main(args: Array<String>) {
    val classMap = hashMapOf<Int, MyClass>()
    val cc = MyClass(0)
    cc.level = 1
    classMap[1] = cc
    val mc = classMap[1]
    if (mc != null) {
        println("mc 存在")
        mc.level = 2
        //classMap[1] = mc
    }
    println(classMap[1]?.level)
}