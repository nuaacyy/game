package com.point18.slg2d.common.commonfunc

import java.io.*

fun obj2Bytes(obj: Serializable): ByteArray {
    val bo = ByteArrayOutputStream()
    val oo = ObjectOutputStream(bo)
    oo.writeObject(obj)
    val bytes = bo.toByteArray()
    bo.close()
    oo.close()
    return bytes
}

fun bytes2Obj(bytes: ByteArray): Any {
    val bi = ByteArrayInputStream(bytes)
    val oi = ObjectInputStream(bi)
    val obj = oi.readObject()
    bi.close()
    oi.close()
    return obj
}