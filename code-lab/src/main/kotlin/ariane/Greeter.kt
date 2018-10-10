package ariane

import javaclass.JavaList

fun main(args: Array<String>) {
    val name = 0
    println("Hello, $name")

    if (mayReturnNull(2) == "1") {
        println("check null")
    }

    var thisList = mutableListOf<Long>()
    val jl = JavaList()
    thisList = jl.value
}

fun mayReturnNull(v: Int): String? {
    if (v == 1) {
        return "1"
    } else {
        return null
    }
}