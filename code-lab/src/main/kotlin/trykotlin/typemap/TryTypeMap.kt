package trykotlin.typemap

open class BaseClass {

}

class ClassA: BaseClass() {

}

class ClassB: BaseClass() {

}

val typeMap : HashMap<Class<out BaseClass>, Int> = hashMapOf()

fun main(args: Array<String>) {
    typeMap[ClassA::class.java] = 1
}