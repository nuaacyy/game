package jh

fun main(args: Array<String>) {
    val a  = listOf(3450..3500)

    val i = 3600

    a.forEach { it ->
        if (i in it) {
            println("1")
        } else {
            println("2")
        }
    }
    print(1)
}