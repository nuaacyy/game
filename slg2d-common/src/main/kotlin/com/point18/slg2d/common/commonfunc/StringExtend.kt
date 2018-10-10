package com.point18.slg2d.common.commonfunc

import xyz.ariane.util.lzDebug

fun String?.print() {
    if (this == null) {
        return
    }
    normalLog.lzDebug { this }
}