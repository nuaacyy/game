package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.NewLibraryItem

data class NewLibraryItemNotifier(
    val msg: pb4client.NewLibraryItem.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.NewLibraryItem_3183, this.msg.build())
    }
}

fun createNewLibraryItemNotifier(
    typeList: MutableSet<Int>
): NewLibraryItemNotifier {
    val newLibraryItemBuilder = NewLibraryItem.newBuilder()
    newLibraryItemBuilder.addAllNewItem(typeList)
    return NewLibraryItemNotifier(newLibraryItemBuilder)
}

