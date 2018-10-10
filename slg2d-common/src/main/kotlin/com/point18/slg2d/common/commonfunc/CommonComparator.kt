package com.point18.slg2d.common.commonfunc

import java.util.Comparator

class AscComparator<T : Comparable<T>> : Comparator<T> {
    override fun compare(o1: T, o2: T): Int {
        if (o1 < o2) {
            return -1
        } else if (o1 > o2) {
            return 1
        }
        return 0
    }
}

class DescComparator<T : Comparable<T>> : Comparator<T> {
    override fun compare(o1: T, o2: T): Int {
        if (o1 > o2) {
            return -1
        } else if (o1 < o2) {
            return 1
        }
        return 0
    }
}