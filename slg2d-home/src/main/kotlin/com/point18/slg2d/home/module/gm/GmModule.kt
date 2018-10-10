package com.point18.slg2d.home.module.gm

import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.HomeHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import java.util.Arrays.asList

class GmModule : IModule {

    var cmds = hashMapOf<String, GmCommandWrap>()

    override fun moduleInit() {
        this.cmds["add"] = GmCommandWrap(GmAddRes())
        this.cmds["addProps"] = GmCommandWrap(GmAddProps())
        this.cmds["allBuildingToTopLv"] = GmCommandWrap(GmAllBuildingToTopLv())
        this.cmds["chageVipRewardTime"] = GmCommandWrap(GmVip())
        this.cmds["addTestMail"] = GmCommandWrap(GmAddTestMail())
        this.cmds["flushHomeDb"] = GmCommandWrap(GmFlushHomeDb())
        this.cmds["stopHome"] = GmCommandWrap(GmStopHome())
        this.cmds["homeTaskOver"] = GmCommandWrap(GmHomeTaskOver())
        this.cmds["getTask"] = GmCommandWrap(GmGetTask())
        this.cmds["unitTaskTo"] = GmCommandWrap(GmUnitTaskTo())

        for ((_, cmd) in cmds) {
            cmd.initHelper()
        }
    }
}

var gmM = GmModule()

interface GmCommand {
    fun exec(session: PlayerActor, message: String)
}

class GmCommandWrap(private val gmCommand: GmCommand) :
    HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java, asList(gmCommand as HomeHelper)) {

    fun execGm(session: PlayerActor, message: String) {
        // GM命令的种类太多了，所以让GM命令实例自己去初始化。
        requireDc(session) {
            gmCommand.exec(session, message)
        }
    }
}
