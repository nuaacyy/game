package com.point18.slg2d.common.commonfunc

import java.security.MessageDigest

fun md5(s: String): String {
    val instance = MessageDigest.getInstance("MD5") //获取md5加密对象
    val digest = instance.digest(s.toByteArray())   //对字符串见米，返回字节数组
    val sb = StringBuffer()
    for (b in digest) {
        val i = b.toInt() and 0xff  //获取低8位有效值
        var hexString = Integer.toHexString(i) //整数转16进制
        if (hexString.length < 2) {
            hexString = "0$hexString"
        }
        sb.append(hexString)
    }
    return sb.toString()
}