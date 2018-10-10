package com.point18.slg2d.home.helper

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.HomeHelper
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.IClientDealWrap
import com.point18.slg2d.home.module.IEventHandler
import com.point18.slg2d.home.module.IHeartDeal
import com.point18.slg2d.home.module.askDeal.AskHomeDealBase
import com.point18.slg2d.home.module.askDeal.SyncMsgDealWrap
import com.point18.slg2d.home.module.askDeal.TellHomeDealBase
import com.point18.slg2d.home.module.gm.GmCommandWrap
import xyz.ariane.util.DataContainer

fun checkIsTopDeal(instance: Any): Boolean {
    if (instance is HomeClientMsgDeal ||
        instance is IHeartDeal ||
        instance is IClientDealWrap ||
        instance is AskHomeDealBase ||
        instance is TellHomeDealBase ||
        instance is IEventHandler<*> ||
        instance is SyncMsgDealWrap ||
        instance is GmCommandWrap
    ) {
        return true
    } else {
        return false
    }
}

abstract class HomeHelperPlus1<DC1 : DataContainer>(
    private val dc1: Class<DC1>,
    depHhs: List<HomeHelper> = listOf()
) : HomeHelper(depHhs, mutableSetOf(dc1)) {

    protected fun <T> prepare(session: PlayerActor, cb: (dc1: DC1) -> T): T {
        assert(initialized) { "${this::class.java} 模块尚未初始化" }

        val db = session.db
        val d1 = requireNotNull(db.getDCIfPresent(dc1))
        return cb(d1)
    }

    protected fun <T> requireDc(session: PlayerActor, cb: (dc1: DC1) -> T) {
        assert(checkIsTopDeal(this)) { "${this::class.java} require方法不可以在非HomeClientMsgDeal方法中被调用" }

        session.db.unsafeRequireKt(dataContainers) {
            prepare(session, cb)
        }
    }

}

abstract class HomeHelperPlus2<DC1 : DataContainer, DC2 : DataContainer>(
    private val dc1: Class<DC1>,
    private val dc2: Class<DC2>,
    depHhs: List<HomeHelper> = listOf()
) : HomeHelper(depHhs, mutableSetOf(dc1, dc2)) {

    protected fun <T> prepare(session: PlayerActor, cb: (dc1: DC1, dc2: DC2) -> T): T {
        assert(initialized) { "${this::class.java} 模块尚未初始化" }

        val db = session.db
        val d1 = requireNotNull(db.getDCIfPresent(dc1))
        val d2 = requireNotNull(db.getDCIfPresent(dc2))
        return cb(d1, d2)
    }

    protected fun <T> requireDc(session: PlayerActor, cb: (dc1: DC1, dc2: DC2) -> T) {
        assert(checkIsTopDeal(this)) { "${this::class.java} require方法不可以在非HomeClientMsgDeal方法中被调用" }

        session.db.unsafeRequireKt(dataContainers) {
            prepare(session, cb)
        }
    }

}

abstract class HomeHelperPlus3<DC1 : DataContainer, DC2 : DataContainer, DC3 : DataContainer>(
    private val dc1: Class<DC1>,
    private val dc2: Class<DC2>,
    private val dc3: Class<DC3>,
    depHhs: List<HomeHelper> = listOf()
) : HomeHelper(depHhs, mutableSetOf(dc1, dc2, dc3)) {

    protected fun <T> prepare(session: PlayerActor, cb: (dc1: DC1, dc2: DC2, dc3: DC3) -> T): T {
        assert(initialized) { "${this::class.java} 模块尚未初始化" }

        val db = session.db
        val d1 = requireNotNull(db.getDCIfPresent(dc1))
        val d2 = requireNotNull(db.getDCIfPresent(dc2))
        val d3 = requireNotNull(db.getDCIfPresent(dc3))
        return cb(d1, d2, d3)
    }

    protected fun <T> requireDc(session: PlayerActor, cb: (dc1: DC1, dc2: DC2, dc3: DC3) -> T) {
        assert(checkIsTopDeal(this)) { "${this::class.java} require方法不可以在非HomeClientMsgDeal方法中被调用" }

        session.db.unsafeRequireKt(dataContainers) {
            prepare(session, cb)
        }
    }

}

abstract class HomeHelperPlus4<DC1 : DataContainer, DC2 : DataContainer, DC3 : DataContainer, DC4 : DataContainer>(
    private val dc1: Class<DC1>,
    private val dc2: Class<DC2>,
    private val dc3: Class<DC3>,
    private val dc4: Class<DC4>,
    depHhs: List<HomeHelper> = listOf()
) : HomeHelper(depHhs, mutableSetOf(dc1, dc2, dc3, dc4)) {

    protected fun <T> prepare(session: PlayerActor, cb: (dc1: DC1, dc2: DC2, dc3: DC3, dc4: DC4) -> T): T {
        assert(initialized) { "${this::class.java} 模块尚未初始化" }

        val db = session.db
        val d1 = requireNotNull(db.getDCIfPresent(dc1))
        val d2 = requireNotNull(db.getDCIfPresent(dc2))
        val d3 = requireNotNull(db.getDCIfPresent(dc3))
        val d4 = requireNotNull(db.getDCIfPresent(dc4))
        return cb(d1, d2, d3, d4)
    }

    protected fun <T> requireDc(session: PlayerActor, cb: (dc1: DC1, dc2: DC2, dc3: DC3, dc4: DC4) -> T) {
        assert(checkIsTopDeal(this)) { "${this::class.java} require方法不可以在非HomeClientMsgDeal方法中被调用" }

        session.db.unsafeRequireKt(dataContainers) {
            prepare(session, cb)
        }
    }

}

abstract class HomeHelperPlus5<
        DC1 : DataContainer,
        DC2 : DataContainer,
        DC3 : DataContainer,
        DC4 : DataContainer,
        DC5 : DataContainer
        >(
    private val dc1: Class<DC1>,
    private val dc2: Class<DC2>,
    private val dc3: Class<DC3>,
    private val dc4: Class<DC4>,
    private val dc5: Class<DC5>,
    depHhs: List<HomeHelper> = listOf()
) : HomeHelper(depHhs, mutableSetOf(dc1, dc2, dc3, dc4, dc5)) {

    protected fun <T> prepare(
        session: PlayerActor,
        cb: (dc1: DC1, dc2: DC2, dc3: DC3, dc4: DC4, dc5: DC5) -> T
    ): T {
        assert(initialized) { "${this::class.java} 模块尚未初始化" }

        val db = session.db
        val d1 = requireNotNull(db.getDCIfPresent(dc1))
        val d2 = requireNotNull(db.getDCIfPresent(dc2))
        val d3 = requireNotNull(db.getDCIfPresent(dc3))
        val d4 = requireNotNull(db.getDCIfPresent(dc4))
        val d5 = requireNotNull(db.getDCIfPresent(dc5))
        return cb(d1, d2, d3, d4, d5)
    }

    protected fun <T> requireDc(session: PlayerActor, cb: (dc1: DC1, dc2: DC2, dc3: DC3, dc4: DC4, dc5: DC5) -> T) {
        assert(checkIsTopDeal(this)) { "${this::class.java} require方法不可以在非HomeClientMsgDeal方法中被调用" }

        session.db.unsafeRequireKt(dataContainers) {
            prepare(session, cb)
        }
    }

}

abstract class HomeHelperPlus6<
        DC1 : DataContainer,
        DC2 : DataContainer,
        DC3 : DataContainer,
        DC4 : DataContainer,
        DC5 : DataContainer,
        DC6 : DataContainer
        >(
    private val dc1: Class<DC1>,
    private val dc2: Class<DC2>,
    private val dc3: Class<DC3>,
    private val dc4: Class<DC4>,
    private val dc5: Class<DC5>,
    private val dc6: Class<DC6>,
    depHhs: List<HomeHelper> = listOf()
) : HomeHelper(depHhs, mutableSetOf(dc1, dc2, dc3, dc4, dc5, dc6)) {

    protected fun <T> prepare(
        session: PlayerActor,
        cb: (dc1: DC1, dc2: DC2, dc3: DC3, dc4: DC4, dc5: DC5, dc6: DC6) -> T
    ): T {
        assert(initialized) { "${this::class.java} 模块尚未初始化" }

        val db = session.db
        val d1 = requireNotNull(db.getDCIfPresent(dc1))
        val d2 = requireNotNull(db.getDCIfPresent(dc2))
        val d3 = requireNotNull(db.getDCIfPresent(dc3))
        val d4 = requireNotNull(db.getDCIfPresent(dc4))
        val d5 = requireNotNull(db.getDCIfPresent(dc5))
        val d6 = requireNotNull(db.getDCIfPresent(dc6))
        return cb(d1, d2, d3, d4, d5, d6)
    }

    protected fun <T> requireDc(
        session: PlayerActor,
        cb: (dc1: DC1, dc2: DC2, dc3: DC3, dc4: DC4, dc5: DC5, dc6: DC6) -> T
    ) {
        assert(checkIsTopDeal(this)) { "${this::class.java} require方法不可以在非HomeClientMsgDeal方法中被调用" }

        session.db.unsafeRequireKt(dataContainers) {
            prepare(session, cb)
        }
    }

}

abstract class HomeHelperPlus7<
        DC1 : DataContainer,
        DC2 : DataContainer,
        DC3 : DataContainer,
        DC4 : DataContainer,
        DC5 : DataContainer,
        DC6 : DataContainer,
        DC7 : DataContainer
        >(
    private val dc1: Class<DC1>,
    private val dc2: Class<DC2>,
    private val dc3: Class<DC3>,
    private val dc4: Class<DC4>,
    private val dc5: Class<DC5>,
    private val dc6: Class<DC6>,
    private val dc7: Class<DC7>,
    depHhs: List<HomeHelper> = listOf()
) : HomeHelper(depHhs, mutableSetOf(dc1, dc2, dc3, dc4, dc5, dc6, dc7)) {

    protected fun <T> prepare(
        session: PlayerActor,
        cb: (dc1: DC1, dc2: DC2, dc3: DC3, dc4: DC4, dc5: DC5, dc6: DC6, dc7: DC7) -> T
    ): T {
        assert(initialized) { "${this::class.java} 模块尚未初始化" }

        val db = session.db
        val d1 = requireNotNull(db.getDCIfPresent(dc1))
        val d2 = requireNotNull(db.getDCIfPresent(dc2))
        val d3 = requireNotNull(db.getDCIfPresent(dc3))
        val d4 = requireNotNull(db.getDCIfPresent(dc4))
        val d5 = requireNotNull(db.getDCIfPresent(dc5))
        val d6 = requireNotNull(db.getDCIfPresent(dc6))
        val d7 = requireNotNull(db.getDCIfPresent(dc7))
        return cb(d1, d2, d3, d4, d5, d6, d7)
    }

    protected fun <T> requireDc(
        session: PlayerActor,
        cb: (dc1: DC1, dc2: DC2, dc3: DC3, dc4: DC4, dc5: DC5, dc6: DC6, dc7: DC7) -> T
    ) {
        assert(checkIsTopDeal(this)) { "${this::class.java} require方法不可以在非HomeClientMsgDeal方法中被调用" }

        session.db.unsafeRequireKt(dataContainers) {
            prepare(session, cb)
        }
    }

}

abstract class HomeHelperPlus8<
        DC1 : DataContainer,
        DC2 : DataContainer,
        DC3 : DataContainer,
        DC4 : DataContainer,
        DC5 : DataContainer,
        DC6 : DataContainer,
        DC7 : DataContainer,
        DC8 : DataContainer
        >(
    private val dc1: Class<DC1>,
    private val dc2: Class<DC2>,
    private val dc3: Class<DC3>,
    private val dc4: Class<DC4>,
    private val dc5: Class<DC5>,
    private val dc6: Class<DC6>,
    private val dc7: Class<DC7>,
    private val dc8: Class<DC8>,
    depHhs: List<HomeHelper> = listOf()
) : HomeHelper(depHhs, mutableSetOf(dc1, dc2, dc3, dc4, dc5, dc6, dc7, dc8)) {

    protected fun <T> prepare(
        session: PlayerActor,
        cb: (dc1: DC1, dc2: DC2, dc3: DC3, dc4: DC4, dc5: DC5, dc6: DC6, dc7: DC7, dc8: DC8) -> T
    ): T {
        assert(initialized) { "${this::class.java} 模块尚未初始化" }

        val db = session.db
        val d1 = requireNotNull(db.getDCIfPresent(dc1))
        val d2 = requireNotNull(db.getDCIfPresent(dc2))
        val d3 = requireNotNull(db.getDCIfPresent(dc3))
        val d4 = requireNotNull(db.getDCIfPresent(dc4))
        val d5 = requireNotNull(db.getDCIfPresent(dc5))
        val d6 = requireNotNull(db.getDCIfPresent(dc6))
        val d7 = requireNotNull(db.getDCIfPresent(dc7))
        val d8 = requireNotNull(db.getDCIfPresent(dc8))
        return cb(d1, d2, d3, d4, d5, d6, d7, d8)
    }

    protected fun <T> requireDc(
        session: PlayerActor,
        cb: (dc1: DC1, dc2: DC2, dc3: DC3, dc4: DC4, dc5: DC5, dc6: DC6, dc7: DC7, dc8: DC8) -> T
    ) {
        assert(checkIsTopDeal(this)) { "${this::class.java} require方法不可以在非HomeClientMsgDeal方法中被调用" }

        session.db.unsafeRequireKt(dataContainers) {
            prepare(session, cb)
        }
    }

}

abstract class HomeHelperPlus9<
        DC1 : DataContainer,
        DC2 : DataContainer,
        DC3 : DataContainer,
        DC4 : DataContainer,
        DC5 : DataContainer,
        DC6 : DataContainer,
        DC7 : DataContainer,
        DC8 : DataContainer,
        DC9 : DataContainer
        >(
    private val dc1: Class<DC1>,
    private val dc2: Class<DC2>,
    private val dc3: Class<DC3>,
    private val dc4: Class<DC4>,
    private val dc5: Class<DC5>,
    private val dc6: Class<DC6>,
    private val dc7: Class<DC7>,
    private val dc8: Class<DC8>,
    private val dc9: Class<DC9>,
    depHhs: List<HomeHelper> = listOf()
) : HomeHelper(depHhs, mutableSetOf(dc1, dc2, dc3, dc4, dc5, dc6, dc7, dc8, dc9)) {

    protected fun <T> prepare(
        session: PlayerActor,
        cb: (dc1: DC1, dc2: DC2, dc3: DC3, dc4: DC4, dc5: DC5, dc6: DC6, dc7: DC7, dc8: DC8, dc9: DC9) -> T
    ): T {
        assert(initialized) { "${this::class.java} 模块尚未初始化" }

        val db = session.db
        val d1 = requireNotNull(db.getDCIfPresent(dc1))
        val d2 = requireNotNull(db.getDCIfPresent(dc2))
        val d3 = requireNotNull(db.getDCIfPresent(dc3))
        val d4 = requireNotNull(db.getDCIfPresent(dc4))
        val d5 = requireNotNull(db.getDCIfPresent(dc5))
        val d6 = requireNotNull(db.getDCIfPresent(dc6))
        val d7 = requireNotNull(db.getDCIfPresent(dc7))
        val d8 = requireNotNull(db.getDCIfPresent(dc8))
        val d9 = requireNotNull(db.getDCIfPresent(dc9))
        return cb(d1, d2, d3, d4, d5, d6, d7, d8, d9)
    }

    protected fun <T> requireDc(
        session: PlayerActor,
        cb: (dc1: DC1, dc2: DC2, dc3: DC3, dc4: DC4, dc5: DC5, dc6: DC6, dc7: DC7, dc8: DC8, dc9: DC9) -> T
    ) {
        assert(checkIsTopDeal(this)) { "${this::class.java} require方法不可以在非HomeClientMsgDeal方法中被调用" }

        session.db.unsafeRequireKt(dataContainers) {
            prepare(session, cb)
        }
    }

}

abstract class HomeHelperPlus10<
        DC1 : DataContainer,
        DC2 : DataContainer,
        DC3 : DataContainer,
        DC4 : DataContainer,
        DC5 : DataContainer,
        DC6 : DataContainer,
        DC7 : DataContainer,
        DC8 : DataContainer,
        DC9 : DataContainer,
        DC10 : DataContainer
        >(
    private val dc1: Class<DC1>,
    private val dc2: Class<DC2>,
    private val dc3: Class<DC3>,
    private val dc4: Class<DC4>,
    private val dc5: Class<DC5>,
    private val dc6: Class<DC6>,
    private val dc7: Class<DC7>,
    private val dc8: Class<DC8>,
    private val dc9: Class<DC9>,
    private val dc10: Class<DC10>,
    depHhs: List<HomeHelper> = listOf()
) : HomeHelper(depHhs, mutableSetOf(dc1, dc2, dc3, dc4, dc5, dc6, dc7, dc8, dc9, dc10)) {

    protected fun <T> prepare(
        session: PlayerActor,
        cb: (dc1: DC1, dc2: DC2, dc3: DC3, dc4: DC4, dc5: DC5, dc6: DC6, dc7: DC7, dc8: DC8, dc9: DC9, dc10: DC10) -> T
    ): T {
        assert(initialized) { "${this::class.java} 模块尚未初始化" }

        val db = session.db
        val d1 = requireNotNull(db.getDCIfPresent(dc1))
        val d2 = requireNotNull(db.getDCIfPresent(dc2))
        val d3 = requireNotNull(db.getDCIfPresent(dc3))
        val d4 = requireNotNull(db.getDCIfPresent(dc4))
        val d5 = requireNotNull(db.getDCIfPresent(dc5))
        val d6 = requireNotNull(db.getDCIfPresent(dc6))
        val d7 = requireNotNull(db.getDCIfPresent(dc7))
        val d8 = requireNotNull(db.getDCIfPresent(dc8))
        val d9 = requireNotNull(db.getDCIfPresent(dc9))
        val d10 = requireNotNull(db.getDCIfPresent(dc10))
        return cb(d1, d2, d3, d4, d5, d6, d7, d8, d9, d10)
    }

    protected fun <T> requireDc(
        session: PlayerActor,
        cb: (dc1: DC1, dc2: DC2, dc3: DC3, dc4: DC4, dc5: DC5, dc6: DC6, dc7: DC7, dc8: DC8, dc9: DC9, dc10: DC10) -> T
    ) {
        assert(checkIsTopDeal(this)) { "${this::class.java} require方法不可以在非HomeClientMsgDeal方法中被调用" }

        session.db.unsafeRequireKt(dataContainers) {
            prepare(session, cb)
        }
    }

}

abstract class HomeHelperPlus11<
        DC1 : DataContainer,
        DC2 : DataContainer,
        DC3 : DataContainer,
        DC4 : DataContainer,
        DC5 : DataContainer,
        DC6 : DataContainer,
        DC7 : DataContainer,
        DC8 : DataContainer,
        DC9 : DataContainer,
        DC10 : DataContainer,
        DC11 : DataContainer
        >(
    private val dc1: Class<DC1>,
    private val dc2: Class<DC2>,
    private val dc3: Class<DC3>,
    private val dc4: Class<DC4>,
    private val dc5: Class<DC5>,
    private val dc6: Class<DC6>,
    private val dc7: Class<DC7>,
    private val dc8: Class<DC8>,
    private val dc9: Class<DC9>,
    private val dc10: Class<DC10>,
    private val dc11: Class<DC11>,
    depHhs: List<HomeHelper> = listOf()
) : HomeHelper(depHhs, mutableSetOf(dc1, dc2, dc3, dc4, dc5, dc6, dc7, dc8, dc9, dc10, dc11)) {

    protected fun <T> prepare(
        session: PlayerActor,
        cb: (dc1: DC1, dc2: DC2, dc3: DC3, dc4: DC4, dc5: DC5, dc6: DC6, dc7: DC7, dc8: DC8, dc9: DC9, dc10: DC10, dc11: DC11) -> T
    ): T {
        assert(initialized) { "${this::class.java} 模块尚未初始化" }

        val db = session.db
        val d1 = requireNotNull(db.getDCIfPresent(dc1))
        val d2 = requireNotNull(db.getDCIfPresent(dc2))
        val d3 = requireNotNull(db.getDCIfPresent(dc3))
        val d4 = requireNotNull(db.getDCIfPresent(dc4))
        val d5 = requireNotNull(db.getDCIfPresent(dc5))
        val d6 = requireNotNull(db.getDCIfPresent(dc6))
        val d7 = requireNotNull(db.getDCIfPresent(dc7))
        val d8 = requireNotNull(db.getDCIfPresent(dc8))
        val d9 = requireNotNull(db.getDCIfPresent(dc9))
        val d10 = requireNotNull(db.getDCIfPresent(dc10))
        val d11 = requireNotNull(db.getDCIfPresent(dc11))
        return cb(d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11)
    }

    protected fun <T> requireDc(
        session: PlayerActor,
        cb: (dc1: DC1, dc2: DC2, dc3: DC3, dc4: DC4, dc5: DC5, dc6: DC6, dc7: DC7, dc8: DC8, dc9: DC9, dc10: DC10, dc11: DC11) -> T
    ) {
        assert(checkIsTopDeal(this)) { "${this::class.java} require方法不可以在非HomeClientMsgDeal方法中被调用" }

        session.db.unsafeRequireKt(dataContainers) {
            prepare(session, cb)
        }
    }

}

abstract class HomeHelperPlus12<
        DC1 : DataContainer,
        DC2 : DataContainer,
        DC3 : DataContainer,
        DC4 : DataContainer,
        DC5 : DataContainer,
        DC6 : DataContainer,
        DC7 : DataContainer,
        DC8 : DataContainer,
        DC9 : DataContainer,
        DC10 : DataContainer,
        DC11 : DataContainer,
        DC12 : DataContainer
        >(
    private val dc1: Class<DC1>,
    private val dc2: Class<DC2>,
    private val dc3: Class<DC3>,
    private val dc4: Class<DC4>,
    private val dc5: Class<DC5>,
    private val dc6: Class<DC6>,
    private val dc7: Class<DC7>,
    private val dc8: Class<DC8>,
    private val dc9: Class<DC9>,
    private val dc10: Class<DC10>,
    private val dc11: Class<DC11>,
    private val dc12: Class<DC12>,
    depHhs: List<HomeHelper> = listOf()
) : HomeHelper(depHhs, mutableSetOf(dc1, dc2, dc3, dc4, dc5, dc6, dc7, dc8, dc9, dc10, dc11, dc12)) {

    protected fun <T> prepare(
        session: PlayerActor,
        cb: (
            dc1: DC1, dc2: DC2, dc3: DC3, dc4: DC4, dc5: DC5, dc6: DC6,
            dc7: DC7, dc8: DC8, dc9: DC9, dc10: DC10, dc11: DC11, dc12: DC12
        ) -> T
    ): T {
        assert(initialized) { "${this::class.java} 模块尚未初始化" }

        val db = session.db
        val d1 = requireNotNull(db.getDCIfPresent(dc1))
        val d2 = requireNotNull(db.getDCIfPresent(dc2))
        val d3 = requireNotNull(db.getDCIfPresent(dc3))
        val d4 = requireNotNull(db.getDCIfPresent(dc4))
        val d5 = requireNotNull(db.getDCIfPresent(dc5))
        val d6 = requireNotNull(db.getDCIfPresent(dc6))
        val d7 = requireNotNull(db.getDCIfPresent(dc7))
        val d8 = requireNotNull(db.getDCIfPresent(dc8))
        val d9 = requireNotNull(db.getDCIfPresent(dc9))
        val d10 = requireNotNull(db.getDCIfPresent(dc10))
        val d11 = requireNotNull(db.getDCIfPresent(dc11))
        val d12 = requireNotNull(db.getDCIfPresent(dc12))
        return cb(d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12)
    }

    protected fun <T> requireDc(
        session: PlayerActor,
        cb: (
            dc1: DC1, dc2: DC2, dc3: DC3, dc4: DC4, dc5: DC5, dc6: DC6,
            dc7: DC7, dc8: DC8, dc9: DC9, dc10: DC10, dc11: DC11, dc12: DC12
        ) -> T
    ) {
        assert(checkIsTopDeal(this)) { "${this::class.java} require方法不可以在非HomeClientMsgDeal方法中被调用" }

        session.db.unsafeRequireKt(dataContainers) {
            prepare(session, cb)
        }
    }

}

interface IDcContainer27<
        DC1 : DataContainer,
        DC2 : DataContainer,
        DC3 : DataContainer,
        DC4 : DataContainer,
        DC5 : DataContainer,
        DC6 : DataContainer,
        DC7 : DataContainer,
        DC8 : DataContainer,
        DC9 : DataContainer,
        DC10 : DataContainer,
        DC11 : DataContainer,
        DC12 : DataContainer,
        DC13 : DataContainer,
        DC14 : DataContainer,
        DC15 : DataContainer,
        DC16 : DataContainer,
        DC17 : DataContainer,
        DC18 : DataContainer,
        DC19 : DataContainer,
        DC20 : DataContainer,
        DC21 : DataContainer,
        DC22 : DataContainer,
        DC23 : DataContainer,
        DC24 : DataContainer,
        DC25 : DataContainer,
        DC26 : DataContainer,
        DC27 : DataContainer
        > {

    fun configDcs(
        dc1: DC1,
        dc2: DC2,
        dc3: DC3,
        dc4: DC4,
        dc5: DC5,
        dc6: DC6,
        dc7: DC7,
        dc8: DC8,
        dc9: DC9,
        dc10: DC10,
        dc11: DC11,
        dc12: DC12,
        dc13: DC13,
        dc14: DC14,
        dc15: DC15,
        dc16: DC16,
        dc17: DC17,
        dc18: DC18,
        dc19: DC19,
        dc20: DC20,
        dc21: DC21,
        dc22: DC22,
        dc23: DC23,
        dc24: DC24,
        dc25: DC25,
        dc26: DC26,
        dc27: DC27
    )

    fun <T> invoke(c: () -> Unit)
}


abstract class HomeHelperPlus27<
        DC1 : DataContainer,
        DC2 : DataContainer,
        DC3 : DataContainer,
        DC4 : DataContainer,
        DC5 : DataContainer,
        DC6 : DataContainer,
        DC7 : DataContainer,
        DC8 : DataContainer,
        DC9 : DataContainer,
        DC10 : DataContainer,
        DC11 : DataContainer,
        DC12 : DataContainer,
        DC13 : DataContainer,
        DC14 : DataContainer,
        DC15 : DataContainer,
        DC16 : DataContainer,
        DC17 : DataContainer,
        DC18 : DataContainer,
        DC19 : DataContainer,
        DC20 : DataContainer,
        DC21 : DataContainer,
        DC22 : DataContainer,
        DC23 : DataContainer,
        DC24 : DataContainer,
        DC25 : DataContainer,
        DC26 : DataContainer,
        DC27 : DataContainer
        >(
    private val dc1: Class<DC1>,
    private val dc2: Class<DC2>,
    private val dc3: Class<DC3>,
    private val dc4: Class<DC4>,
    private val dc5: Class<DC5>,
    private val dc6: Class<DC6>,
    private val dc7: Class<DC7>,
    private val dc8: Class<DC8>,
    private val dc9: Class<DC9>,
    private val dc10: Class<DC10>,
    private val dc11: Class<DC11>,
    private val dc12: Class<DC12>,
    private val dc13: Class<DC13>,
    private val dc14: Class<DC14>,
    private val dc15: Class<DC15>,
    private val dc16: Class<DC16>,
    private val dc17: Class<DC17>,
    private val dc18: Class<DC18>,
    private val dc19: Class<DC19>,
    private val dc20: Class<DC20>,
    private val dc21: Class<DC21>,
    private val dc22: Class<DC22>,
    private val dc23: Class<DC23>,
    private val dc24: Class<DC24>,
    private val dc25: Class<DC25>,
    private val dc26: Class<DC26>,
    private val dc27: Class<DC27>,
    depHhs: List<HomeHelper> = listOf()
) : HomeHelper(
    depHhs, mutableSetOf(
        dc1, dc2, dc3, dc4, dc5, dc6, dc7, dc8, dc9, dc10,
        dc11, dc12, dc13, dc14, dc15, dc16, dc17, dc18, dc19, dc20,
        dc21, dc22, dc23, dc24, dc25, dc26, dc27
    )
) {

    protected fun <T> prepare(
        session: PlayerActor,
        dcContainer: IDcContainer27<DC1,
                DC2,
                DC3,
                DC4,
                DC5,
                DC6,
                DC7,
                DC8,
                DC9,
                DC10,
                DC11,
                DC12,
                DC13,
                DC14,
                DC15,
                DC16,
                DC17,
                DC18,
                DC19,
                DC20,
                DC21,
                DC22,
                DC23,
                DC24,
                DC25,
                DC26,
                DC27>,
        cb: () -> T
    ): T {
        assert(initialized) { "${this::class.java} 模块尚未初始化" }

        val db = session.db

        val d1 = requireNotNull(db.getDCIfPresent(dc1))
        val d2 = requireNotNull(db.getDCIfPresent(dc2))
        val d3 = requireNotNull(db.getDCIfPresent(dc3))
        val d4 = requireNotNull(db.getDCIfPresent(dc4))
        val d5 = requireNotNull(db.getDCIfPresent(dc5))
        val d6 = requireNotNull(db.getDCIfPresent(dc6))
        val d7 = requireNotNull(db.getDCIfPresent(dc7))
        val d8 = requireNotNull(db.getDCIfPresent(dc8))
        val d9 = requireNotNull(db.getDCIfPresent(dc9))
        val d10 = requireNotNull(db.getDCIfPresent(dc10))
        val d11 = requireNotNull(db.getDCIfPresent(dc11))
        val d12 = requireNotNull(db.getDCIfPresent(dc12))
        val d13 = requireNotNull(db.getDCIfPresent(dc13))
        val d14 = requireNotNull(db.getDCIfPresent(dc14))
        val d15 = requireNotNull(db.getDCIfPresent(dc15))
        val d16 = requireNotNull(db.getDCIfPresent(dc16))
        val d17 = requireNotNull(db.getDCIfPresent(dc17))
        val d18 = requireNotNull(db.getDCIfPresent(dc18))
        val d19 = requireNotNull(db.getDCIfPresent(dc19))
        val d20 = requireNotNull(db.getDCIfPresent(dc20))
        val d21 = requireNotNull(db.getDCIfPresent(dc21))
        val d22 = requireNotNull(db.getDCIfPresent(dc22))
        val d23 = requireNotNull(db.getDCIfPresent(dc23))
        val d24 = requireNotNull(db.getDCIfPresent(dc24))
        val d25 = requireNotNull(db.getDCIfPresent(dc25))
        val d26 = requireNotNull(db.getDCIfPresent(dc26))
        val d27 = requireNotNull(db.getDCIfPresent(dc27))
        dcContainer.configDcs(
            d1, d2, d3, d4, d5, d6, d7, d8, d9, d10,
            d11, d12, d13, d14, d15, d16, d17, d18, d19, d20,
            d21, d22, d23, d24, d25, d26, d27
        )

        return cb.invoke()
    }

    protected fun <T> requireDc(
        session: PlayerActor,
        dcContainer: IDcContainer27<DC1,
                DC2,
                DC3,
                DC4,
                DC5,
                DC6,
                DC7,
                DC8,
                DC9,
                DC10,
                DC11,
                DC12,
                DC13,
                DC14,
                DC15,
                DC16,
                DC17,
                DC18,
                DC19,
                DC20,
                DC21,
                DC22,
                DC23,
                DC24,
                DC25,
                DC26,
                DC27>,
        cb: () -> T
    ) {
        assert(checkIsTopDeal(this)) { "${this::class.java} require方法不可以在非HomeClientMsgDeal方法中被调用" }

        session.db.unsafeRequireKt(dataContainers) {
            prepare(session, dcContainer, cb)
        }
    }

}