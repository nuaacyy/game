package com.szyz.sexy.common.msg

import com.google.common.collect.Maps
import com.point18.slg2d.common.msgtrans.InternalMessage
import com.typesafe.config.ConfigFactory
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import xyz.ariane.util.DependencyTreeTraverse
import xyz.ariane.util.TRANSIENT_FILED_FILTER
import xyz.ariane.util.checkKryoSupport
import xyz.ariane.util.io.ClassResolver
import java.lang.reflect.Modifier

/**
 * 检查内部消息注册合法性
 *
 * Created by liul on 2016/2/26.
 */
class InternalMsgValidateTest {

    lateinit var messageMappings: Map<String, Any>

    @Before
    fun setUp() {
        val config = ConfigFactory.parseResources("kryo-serialization.conf")
        val mappingsCfg = config.getConfig("akka.actor.kryo.mappings")
        messageMappings = mappingsCfg.root().unwrapped()
    }

    @Test
    fun checkNumberConflict() {
        val idClazzMap = Maps.newHashMap<Any, String>()
        for ((clazzName, id) in messageMappings) {
            val exist = idClazzMap[id]
            if (exist != null) {
                Assert.fail("消息号[$id]已经分配给了[$exist]")
            }
            idClazzMap[id] = clazzName
        }
    }

    @Test
    fun checkMissingClasses() {
        loop@ for (clazzName in messageMappings.keys) {
            val clazz = Class.forName(clazzName)
            when (clazz) {
//                PlayerEnvelope::class.java -> continue@loop
//                WorldEnvelope::class.java -> continue@loop
                else -> {
                    DependencyTreeTraverse(
                        clazz,
                        consumer = { type ->
                            Assert.assertTrue(
                                "未注册依赖类 $type $clazz",
                                !checkKryoSupport(type) || messageMappings.containsKey(type.name)
                            )
                        },
                        fieldFilter = TRANSIENT_FILED_FILTER
                    ).apply {
                        try {
                            execute()
                        } catch (e: Exception) {
                            printStackTrace()
                            throw e
                        }
                    }
                }
            }
        }
    }

    @Test
    fun checkAllInternalMsgResisted() {
        val allInternalMsg = ClassResolver<InternalMessage>().findImplementations(
            InternalMessage::class.java,
            "com.szyz.sexy.common"
        ).classes

        // 1.消息需要继承自InternalMessage用于内部通信; 2.类型对象不会跨进程传输;
        val excludeMsg = setOf(
            "SystemMessage",
            "Unfreeze",
            "Handoff",
            "GmMessage",
            "CompileGroovyScript",
            "CompileGroovyResult",
            "BatchExecuteUniverseKingRoomScript",
            "ExecuteUniverseKingRoomScript",
            // TODO 以下类型来自弃用模块或不应继承InternalMessage
            "WHTurnCardReward",
            "HLSuperSdkChargeAns",
            "LHSuperSdkChargeAsk",
            "TencentTaskMarketJsonResponse",
            "TencentServletJsonResponse"
        )

        for (clazz in allInternalMsg) {
            Assert.assertTrue(
                "${clazz.name} 是 InternalMessage 的非 Abstract 实现,但是没有在 kryo-serialization.conf 中注册",
                messageMappings.containsKey(clazz.name) || excludeMsg.contains(clazz.simpleName) || clazz.isInterface || Modifier.isAbstract(
                    clazz.modifiers
                )
            )
        }
    }

}