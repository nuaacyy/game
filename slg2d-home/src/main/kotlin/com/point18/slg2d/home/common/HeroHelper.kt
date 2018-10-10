package com.point18.slg2d.home.common

import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.common.syncdomain.HeroData
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.Hero
import com.point18.slg2d.home.dc.HeroProperty
import com.point18.slg2d.home.dc.HomePlayer
import com.point18.slg2d.home.msgnotice.createHeroChangeNotifier
import pb4server.Home2WorldAskResp
import pb4server.UpdateInfoByHomeAskReq
import java.util.*

// (英雄无敌版的武将养成用的方法)  检测前置条件
fun checkHeroInvincibleCondition(
    player: HomePlayer,
    hero: Hero,
    condition: Map<Int, Int>
): (Boolean) {
    for ((checkType, checkValue) in condition) {
        if (checkType == KING_LV_CHECK) {
            if (player.kingLv < checkValue) {
                return false
            }
        } else if (checkType == HERO_SUPER_LV_CHECK) {
            if (hero.awake < checkValue) {
                return false
            }
        } else if (checkType == HERO_STAR_LV_CHECK) {
            if (hero.advLv < checkValue) {
                return false
            }
        } else if (checkType == HERO_LV_CHECK) {
            if (hero.level < checkValue) {
                return false
            }
        } else {
            return false
        }
    }
    return true
}

// 发送指定武将信息至客户端
fun sendHeroListInfo(session: PlayerActor, heroList: LinkedList<Hero>) {
    val heroChangeNotifier = createHeroChangeNotifier()
    for (hero in heroList) {
        val heroProperty = generateHeroRefPropsByDbData(hero)
        heroChangeNotifier.appendHero(heroProperty)
    }
    heroChangeNotifier.notice(session)
}

// 刷新武将属性
fun generateHeroRefPropsByDbData(hero: Hero): (HeroProperty) {
    // 从模板中找到该武将的模板 用来计算增加的属性
    // 计算战斗数值
    val (atkW, defW, _, _, hpW, speedW, baojiW, baojilvW, _, _, _, _, _, _) =
        getHeroBasicInfo(hero.protoId, hero.level, hero.advLv, hero.awake, hero.heroEquipInfoMap)

    val heroProp = HeroProperty(
        hero.id,
        hero.protoId,
        hero.level,
        hero.advLv,
        hero.exp,
        atkW,
        hpW,
        defW,
        speedW,
        baojiW,
        baojilvW,
        hero.skillId1,
        hero.skillId2,
        hero.skillId3,
        hero.skillId4,
        hero.awake,
        hero.intSkillId1,
        hero.intSkillId2,
        hero.intSkillId3,
        hero.intHeroAddress,
        hero.mainHeroState,
        hero.posState,
        hero.heroEquipInfoMap,
        hero.starUpEndTime,
        hero.superUpEndTime,
        hero.mainHeroStateStartTime,
        hero.mainHeroStateEndTime,
        hero.heroStrength,
        hero.onFloorIdx
    )

    return heroProp
}

//同步英雄到世界
fun syncHero2World(session: PlayerActor, hero: Hero) {
    val heroDataList = LinkedList<HeroData>()
    heroDataList += hero.toSyncDomain()

    val askMsg = UpdateInfoByHomeAskReq.newBuilder()
    val updateInfoByHomeVo = pb4server.UpdateInfoByHomeVo.newBuilder()
    updateInfoByHomeVo.updateType = UPDATE_INFO_BY_HOME_HERO
    updateInfoByHomeVo.updateValue = toJson(heroDataList)
    askMsg.addUpdates(updateInfoByHomeVo)

    session.createACS<Home2WorldAskResp>()
        .ask(session.worldShardProxy,
            session.fillHome2WorldAskMsgHeader { it.setUpdateInfoByHomeAskReq(askMsg) },
            Home2WorldAskResp::class.java)
        .whenCompleteKt { rt, askErr ->

            try {
                when {
                    askErr != null -> {
                    }
                    rt == null -> {

                    }
                    else -> {
                        val rt = rt.updateInfoByHomeAskRt
                        if (rt.rt != ResultCode.SUCCESS.code) {

                        } else {

                        }
                    }
                }

            } catch (e: Exception) {
            }
        }
}