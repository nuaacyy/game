package com.point18.slg2d.home.module.library

import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.CommonLibVo
import com.point18.slg2d.home.dc.LibraryDC
import com.point18.slg2d.home.module.event.GetNewLibraryEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.module.registerEvent
import com.point18.slg2d.home.msgnotice.createNewLibraryItemNotifier

var libraryM = LibraryModule()

class LibraryModule : IModule {
    override fun moduleInit() {
        registerEvent(PROP_CHANGE, PropChangeEventHandler())

        registerEvent(GET_KING_EQUIP, GetKingEquipEventHandler())
    }

    fun handleOpenLibItem(libraryDC: LibraryDC, session: PlayerActor, protoId: Int) {
        val proto = pcs.equipCache.equipProtoMap[protoId] ?: return
        when {
            proto.mainType == PROP_TYPE_EQUIP -> openEquipItem(session, protoId, libraryDC)
            proto.mainType == PROP_TYPE_CARD -> openCardItem(session, protoId, libraryDC)
            else -> openPropItem(session, protoId, libraryDC)
        }

        // 点亮图鉴的事件
        fireEvent(session, GetNewLibraryEvent())
    }

    // 点亮卡片图鉴
    private fun openCardItem(session: PlayerActor, protoId: Int, libraryDC: LibraryDC) {
        val library = libraryDC.library
        val cardItem = library.card

        val cardProto = pcs.equipCache.equipProtoMap[protoId] ?: return
        val libraryType = cardProto.libraryType
        if (libraryType == 0) {
            return // 道具不在图鉴中
        }
        if (cardProto.mainType != PROP_TYPE_CARD) {
            return // 卡片配置的mainType==11，不是11代表不是装备
        }

        val libInfo = cardItem[libraryType]
        if (libInfo == null) {
            cardItem[libraryType] = CommonLibVo(protoId, hashSetOf(protoId), 0)
            library.newItem.add(LIB_CARD)
            createNewLibraryItemNotifier(library.newItem).notice(session)
        } else {
            val oldCardProto = pcs.equipCache.equipProtoMap[libInfo.protoId] ?: return
            if (cardProto.quality > oldCardProto.quality) {
                // 检测卡片的品质，如果品质更高，替换显示道具Id
                libInfo.protoId = protoId
            }
            libInfo.protoIds.add(protoId)
        }
    }

    // 点亮武器图鉴
    private fun openEquipItem(session: PlayerActor, protoId: Int, libraryDC: LibraryDC) {
        val library = libraryDC.library
        val equipItem = library.equip

        val equipProto = pcs.equipCache.equipProtoMap[protoId] ?: return
        val libraryType = equipProto.libraryType
        if (libraryType == 0) {
            return // 道具不在图鉴中
        }
        if (equipProto.mainType != PROP_TYPE_EQUIP) {
            return // 装备配置的mainType==3，不是3代表不是装备
        }

        val libInfo = equipItem[libraryType]
        if (libInfo == null) {
            equipItem[libraryType] = CommonLibVo(protoId, hashSetOf(protoId), 1)
            library.newItem.add(LIB_EQUIP)
            createNewLibraryItemNotifier(library.newItem).notice(session)
        } else {
            val oldEquipProto = pcs.equipCache.equipProtoMap[libInfo.protoId] ?: return
            if (equipProto.quality > oldEquipProto.quality) {
                // 检测卡片的品质，如果品质更高，替换显示道具Id
                libInfo.protoId = protoId
            }
            libInfo.protoIds.add(protoId)
            libInfo.num = libInfo.num + 1
        }
    }

    // 点亮道具图鉴
    private fun openPropItem(session: PlayerActor, protoId: Int, libraryDC: LibraryDC) {
        val library = libraryDC.library
        val propItem = library.prop

        val propProto = pcs.equipCache.equipProtoMap[protoId] ?: return
        val libraryType = propProto.libraryType
        if (libraryType == 0) {
            return // 道具不在图鉴中
        }
        if (propProto.mainType == PROP_TYPE_CARD) {
            return // 卡片道具不放在道具图鉴中
        }
        if (propProto.mainType == PROP_TYPE_EQUIP) {
            return // 装备道具不放在道具图鉴中
        }

        val libInfo = propItem[libraryType]
        if (libInfo == null) {
            propItem[libraryType] = CommonLibVo(protoId, hashSetOf(), 0)
            // library.newItem.add(LIB_PROP)
            //createNewLibraryItemNotifier(library.newItem).notice(session)
        }
    }
}

