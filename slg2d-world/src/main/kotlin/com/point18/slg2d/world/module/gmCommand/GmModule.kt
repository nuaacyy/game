package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.world.area4data.PlayerSession
import pb4server.UseGmReqTell

class GmModule : com.point18.slg2d.common.baseg.IModule {

    var cmds = hashMapOf<String, GmCommand>()

    override fun moduleInit() {
        //this.cmds = hashMapOf()
//        this.cmds["add"] = GmAddResToHome()
//        this.cmds["addProps"] = GmAddProps()
//        this.cmds["addTili"] = GmAddTili()
//        this.cmds["clearNewPlayerTime"] = GmClearNewPlayerTime()
//        this.cmds["clearQuest"] = GmClearQuest()
//        this.cmds["questTo"] = GmQuestTo()
//        this.cmds["yali"] = GmYali()
//        this.cmds["addTalent"] = GmAddTalentPoint()
//        this.cmds["timeTaskForWeekRank"] = GmTimeTaskForWeekRank()
//        this.cmds["addKingExp"] = GmAddKingExp()
//        this.cmds["addFriend"] = GmAddFriend()
//        this.cmds["lookxy"] = GmLookxy()
//        this.cmds["ziyuandaiLv"] = GmZiyuandaiLv()
        this.cmds["addTimeBoxDrop"] = GmAddTimeBoxDrop()
//        this.cmds["reduceTimeBoxTime"] = GmReduceTimeBoxTime()
//        this.cmds["refWorldBoss"] = GmRefWorldBoss()
//        this.cmds["openSendAllianceGift"] = GmOpenSendAllianceGift()
        this.cmds["startWonderWar"] = GmStartWonderWar()
        this.cmds["stopWonderWar"] = GmStopWonderWar()
        this.cmds["activityBossAppear"] = GmActivityBossAppear()
        this.cmds["activityBossDisappear"] = GmActivityBossDisappear()
//        this.cmds["setAllianceCompetitionQuestFinish"] = GmSetAllianceCompetitionQuestFinish()
//        this.cmds["moveCity"] = GmMoveCity()
//        this.cmds["clearKingBuffCd"] = GmClearKingBuffCd()
//        this.cmds["refMap"] = GmRefMap()
//        this.cmds["stopRefMap"] = GmStopRefMap()
//        this.cmds["lookXyha"] = GmLookXyha()
//        this.cmds["allBuildingToTopLv"] = GmAllBuildingToTopLv()
        this.cmds["addHurtTroops"] = GmAddHurtTroops()
//        this.cmds["chageVipRewardTime"] = GmChageVipRewardTime()
//        this.cmds["addTestMail"] = GmAddTestMail()
        this.cmds["activityOver"] = GMActivityOver()
        this.cmds["flushWorldDb"] = GmFlushWorldDb()
        this.cmds["stopWorld"] = GmStopWorld()
        this.cmds["worldTaskOver"] = GmWorldTaskOver()
        this.cmds["reloadConfig"] = GmReloadConfig()
        this.cmds["achievementFinish"] = GmAchievementOver()
        this.cmds["createOnePlayer"] = GmCreatePlayer()
        this.cmds["allGoto"] = GmAllGotoXy()
        this.cmds["allGotossssss"] = GmAllGotoXysss()
        this.cmds["createPlayerGo"] = GmCreatePlayerGo()
        this.cmds["ms"] = GmMoveServer()
    }

    fun disposeGm(session: PlayerSession, message: String): Int {
        // 格式1： -gm add 类型 数量
        // 格式2： -gm changeCD building
        val messages = message.split(" ")
        if (messages.size == 1) {
            return 2
        }
        val gmBigType = messages[1]

        // todo 这边需要做分支处理 到底这个GM是放世界的还是home的 ,现在全部往home扔
        val gmActor = GmM.cmds[gmBigType]
        if (gmActor != null) {
            // 世界服的GM
            gmActor.exec(session, message)
        } else {
            // 往Home扔
            val tell = UseGmReqTell.newBuilder()
            tell.gmType = gmBigType
            tell.message = message
            session.areaCache.tellHome(session.areaCache.fillW2HTellMsgHeader(session.playerId) {
                it.setUseGmReqTell(tell)
            })
        }

        return 1
    }
}

var GmM = GmModule()

interface GmCommand {
    fun exec(session: PlayerSession, message: String): (Int)
}

// 检测是否是GM命令
fun detectionGm(message: String): Boolean {
    // 检测分割符号
    val messages = message.split(" ")
    val pdGM = messages[0]
    var detection = false
    if (pdGM == "-GM" || pdGM == "-gm") {
        detection = true
    }

    return detection
}