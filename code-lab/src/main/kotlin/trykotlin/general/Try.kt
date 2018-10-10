package trykotlin.general

abstract class UseOtherClass3<A, B, C> {

    private val a: A? = null
    private val b: B? = null
    private val c: C? = null

    abstract fun require()

}

class Ca

class Cb

class Cc

class UseAbc : UseOtherClass3<Ca, Cb, Cc>() {

    override fun require() {
    }
}

fun main(args: Array<String>) {

}