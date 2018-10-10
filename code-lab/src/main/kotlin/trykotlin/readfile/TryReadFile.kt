package trykotlin.readfile

import java.io.File

fun main(args: Array<String>) {
    readFile()
}

fun readFile() {
    val filename = "cfg/testconfig.json"
    val file = File(filename)
    val contents = file.readText()
    println(contents)

//    //大写前三行
//    file.readLines().take(3).forEach {
//        println(it.toUpperCase())
//    }
//
//    //直接处理行
//    file.forEachLine(action = ::println)
//
//    //读取为bytes
//    val bytes: ByteArray = file.readBytes()
//    println(bytes.joinToString(separator = ""))
//
//    //直接处理Reader或InputStream
//    val reader: Reader = file.reader()
//    val inputStream: InputStream = file.inputStream()
//    val bufferedReader: BufferedReader = file.bufferedReader()
}