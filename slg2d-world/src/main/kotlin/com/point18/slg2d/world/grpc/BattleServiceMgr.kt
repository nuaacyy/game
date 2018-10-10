package com.point18.slg2d.world.grpc

import com.point18.slg2d.common.commonfunc.LockObj
import com.point18.slg2d.common.commonfunc.normalLog
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import xyz.ariane.util.lzWarn
import java.util.*

data class BattleService(
    val ip: String,
    val port: Int,
    val channel: ManagedChannel
)

class BattleServiceMgr {
    private val serviceList = LinkedList<BattleService>()

    private val lockObj = LockObj()

    private var selectIndex = 0

    //添加战斗服务
    fun addBattleService(addrress: String) {
        val addressStrs = addrress.split(":")
        if (addressStrs.size != 2) {
            normalLog.lzWarn { "新增战斗服务地址错误:$addrress" }
            return
        }
        val ip = addressStrs[0]
        val port = addressStrs[1].toIntOrNull()
        if (port == null) {
            normalLog.lzWarn { "新增战斗服务地址错误:$addrress" }
            return
        }
        synchronized(lockObj) {
            for (i in serviceList.indices) {
                val service = serviceList[i]
                if (service.ip != ip || service.port != port) {
                    continue
                }
                if(!service.channel.isShutdown) {
                    service.channel.shutdownNow()
                }
                serviceList.removeAt(i)
                break
            }
            val channel = ManagedChannelBuilder.forAddress(ip, port).usePlaintext().build()
            serviceList.add(BattleService(ip, port, channel))
        }
    }

    //移除战斗服务
    fun removeBattleService(addrress: String) {
        val addressStrs = addrress.split(":")
        if (addressStrs.size != 2) {
            normalLog.lzWarn { "移除战斗服务地址错误:$addrress" }
            return
        }
        val ip = addressStrs[0]
        val port = addressStrs[1].toIntOrNull()
        if (port == null) {
            normalLog.lzWarn { "移除战斗服务地址错误:$addrress" }
            return
        }
        synchronized(lockObj) {
            for (i in serviceList.indices) {
                val service = serviceList[i]
                if (service.ip != ip || service.port != port) {
                    continue
                }
                service.channel.shutdownNow()
                serviceList.removeAt(i)
                break
            }
        }
    }

    //选择战斗服务，暂时使用简单的轮询算法
    fun selectBattleService(): ManagedChannel? {
        synchronized(lockObj) {
            if (serviceList.isEmpty()) {
                return null
            }
            if (selectIndex >= serviceList.size) {
                selectIndex = 0
            }
            val channel = serviceList[selectIndex].channel
            selectIndex++
            return channel
        }
    }
}