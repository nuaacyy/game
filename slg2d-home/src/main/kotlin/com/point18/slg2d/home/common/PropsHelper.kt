package com.point18.slg2d.home.common

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.NORMAL_BAG
import com.point18.slg2d.common.constg.PROP_KING_EQUIP
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus4
import com.point18.slg2d.home.module.event.PropChangeEvent
import com.point18.slg2d.home.module.event.UsePropsAtOnceEvent
import com.point18.slg2d.home.module.fireEvent
import xyz.ariane.util.lzDebug
import java.util.*

class PropsHelper : HomeHelperPlus4<NewEquipDC, BagDC, KingEquipPlanDC, LibraryDC>(
    NewEquipDC::class.java,
    BagDC::class.java,
    KingEquipPlanDC::class.java,
    LibraryDC::class.java
) {

    /** 获得新物品，判断是新建格子还是叠加进去 **/
    fun getProps(
        session: PlayerActor,
        protoId: Int,
        num: Int
    ): LinkedList<NewEquip> {
        return prepare(session) { newEquipDC: NewEquipDC, bagDC: BagDC, kingEquipPlanDC: KingEquipPlanDC, libraryDC: LibraryDC ->
            val newProps = LinkedList<NewEquip>()
            val equipProto = pcs.equipCache.equipProtoMap[protoId]
            var equipVo = newEquipDC.findPropByProtoId(protoId)
            if (equipProto == null) {
                com.point18.slg2d.common.commonfunc.normalLog.lzDebug { "propHelper.kt : pcs.equipCache.equipProtoMap[protoId] == null" }
                return@prepare LinkedList()
            }

            if (equipProto.stackNum == 1) {
                //不能叠加
                for (i in 0 until num) {
                    val equipProps = hashMapOf<Int, HashMap<Int, Int>>()
                    val bagId = bagDC.findBagIdByPlayerIdAndBagType(NORMAL_BAG)
                    equipVo = newEquipDC.createNewEquip(bagId, protoId, 1, equipProps)
                    newProps += equipVo
                }
            } else {
                if (equipVo == null) {
                    val equipProps = hashMapOf<Int, HashMap<Int, Int>>()
                    val bagId = bagDC.findBagIdByPlayerIdAndBagType(NORMAL_BAG)
                    equipVo = newEquipDC.createNewEquip(bagId, protoId, num, equipProps)
                    newProps += equipVo
                } else {
                    equipVo.haveNum += num
                    newProps += equipVo
                }
            }
            if (equipVo == null) {
                normalLog.lzDebug { "propHelper.kt : equipVo == null" }
                return@prepare LinkedList()
            }
            val newProto = pcs.equipCache.equipProtoMap[equipVo.equipProtoId]
            if (newProto == null) {
                normalLog.lzDebug { "propHelper.kt : pcs.equipCache.equipProtoMap[equipVo.equipProtoId] == null" }
                return@prepare LinkedList()
            }
            if (newProto.useAtOnce == 0) {
//        val isPay = areaData.findFirstPay(areaCache, player.playerId)
//        var rolePaid = 0
//        if (isPay) {
//            rolePaid = 1
//        }
//        val account = areaData.findAccountById(areaCache, player.accountId)
//        if (account == null) {
//            commonfunc.normalLog.debug("propHelper.kt : findAccountById(areaCache, player.accountId) == null")
//            return LinkedList()
//        }
//		val channelId = Integer.parseInt(account.channelId)
//		val rsProduce = datacenter.RsProduce
//		datacenter.InitBase(rsProduce.Base(), areaCache, "", channelId)
//		rsProduce.Init(account.user, player.playerId, player.accType, player.name, 0, 0, GetVipLv(areaCache, player.playerId),
//			player.birthTime.format(commonfun.DefaultTimeFormat), rolePaid, 0, protoId, 1, (action), 0,
//			num, (equipVo.haveNum - num))
//		datacenter.Dc.WriteLog(rsProduce)
            }

            // 获取道具
            fireEvent(session, PropChangeEvent(protoId))

            return@prepare newProps
        }
    }

    fun getDiffProps(
        session: PlayerActor,
        action: Int,
        protoId: Int,
        num: Int
    ): HashMap<Long, Int> {
        return prepare(session) { newEquipDC: NewEquipDC, bagDC: BagDC, kingEquipPlanDC: KingEquipPlanDC, libraryDC: LibraryDC ->
            val diffProps = hashMapOf<Long, Int>()
            val equipProto = pcs.equipCache.equipProtoMap[protoId]

            if (equipProto == null) {
                normalLog.lzDebug { "propHelper.kt : pcs.equipCache.equipProtoMap[protoId] == null" }
                return@prepare hashMapOf()
            }
            if (equipProto.stackNum == 1) {
                //不能叠加
                for (i in 0 until num) {
                    val equipProps = hashMapOf<Int, HashMap<Int, Int>>()
                    val bagId = bagDC.findBagIdByPlayerIdAndBagType(NORMAL_BAG)
                    val equipVo = newEquipDC.createNewEquip(bagId, protoId, 1, equipProps)
                    diffProps[equipVo.id] = 1
                }
            } else {
                var equipVo = newEquipDC.findPropByProtoId(protoId)
                if (equipVo == null) {
                    val bagId = bagDC.findBagIdByPlayerIdAndBagType(NORMAL_BAG)
                    equipVo = newEquipDC.createNewEquip(bagId, protoId, num, hashMapOf())
                }
                diffProps[equipVo.id] = num
            }

            if (equipProto.useAtOnce == 1) {
                for ((propId, equipNum) in diffProps) {
                    val prop = newEquipDC.findPropById(propId)
                    if (prop == null) {
                        continue
                    }
                    fireEvent(session, UsePropsAtOnceEvent(prop.id, equipProto.id, prop.haveNum))
                }
                diffProps.clear()
            }

            // 获取道具
            fireEvent(session, PropChangeEvent(protoId))

            diffProps
        }
    }

    // 删除新物品,判断是否要删格子
    fun removeProps(
        session: PlayerActor,
        propId: Long,
        num: Int
    ) {
        prepare(session) { newEquipDC: NewEquipDC, bagDC: BagDC, kingEquipPlanDC: KingEquipPlanDC, libraryDC: LibraryDC ->
            val equipVo = newEquipDC.findPropById(propId)
            if (equipVo == null) {
                normalLog.lzDebug { "propHelper.kt : areaData.findPropById(areaCache, player.playerId, propId) == null" }
                return@prepare
            }
            if (equipVo.haveNum > num) {
                equipVo.haveNum -= num

            } else {
                newEquipDC.deleteNewEquip(equipVo)
                val ep = pcs.equipCache.equipProtoMap[equipVo.equipProtoId]
                if (ep == null) {
                    normalLog.lzDebug { "propHelper.kt : pcs.equipCache.equipProtoMap[equipVo.equipProtoId] == null" }
                    return@prepare
                }
                if (ep.mainType == PROP_KING_EQUIP) {
                    // 删除预设里的使用
                    val playerPlans = kingEquipPlanDC.findKingEquipPlansByPlayerId()
                    for (p in playerPlans) {
                        val plan = p.planMap[equipVo.id]
                        if (plan != null) {
                            p.planMap.remove(equipVo.id)
                            p.planMap = p.planMap
                        }
                    }
                }
            }
        }
    }
}