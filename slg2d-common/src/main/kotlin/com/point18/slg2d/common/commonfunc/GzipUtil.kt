package com.point18.slg2d.common.commonfunc

import java.util.zip.GZIPInputStream
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.zip.GZIPOutputStream

/***
 * 压缩GZip
 */
fun gzip(data: ByteArray): ByteArray? {
    var b: ByteArray? = null
    try {
        val bos = ByteArrayOutputStream()
        val gzip = GZIPOutputStream(bos)
        gzip.write(data)
        gzip.finish()
        gzip.close()
        b = bos.toByteArray()
        bos.close()
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    return b
}

/***
 * 解压GZip
 */
fun unGzip(data: ByteArray): ByteArray? {
    var b: ByteArray? = null
    try {
        val bis = ByteArrayInputStream(data)
        val gzip = GZIPInputStream(bis)
        val buf = ByteArray(1024)
        val baos = ByteArrayOutputStream()
        while (true) {
            val num = gzip.read(buf, 0, buf.size)
            if (num == -1) {
                break
            }
            baos.write(buf, 0, num)
        }
        b = baos.toByteArray()
        baos.flush()
        baos.close()
        gzip.close()
        bis.close()
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    return b
}
