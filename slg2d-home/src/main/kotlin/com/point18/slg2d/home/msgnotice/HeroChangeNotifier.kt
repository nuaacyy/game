package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.commonfunc.getTimeSec
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HeroProperty
import pb4client.DetailedQueryHerosInFo
import pb4client.HeroChange
import pb4client.HeroEquipVo

// 武将升级推送
data class HeroChangeNotifier(
    val msg: HeroChange.Builder
) {
    fun appendHero(heroProperty: HeroProperty) {
        val msgPb = createHeroPbByProps(heroProperty)
        this.msg.addHeroInfo(msgPb)
    }

    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.HeroChange_3010, this.msg.build())
    }
}

fun createHeroChangeNotifier(): HeroChangeNotifier {
    return HeroChangeNotifier(HeroChange.newBuilder())
}

// 刷新武将属性并生成protobuf结构
fun createHeroPbByProps(heroProperty: HeroProperty): pb4client.DetailedQueryHerosInFo.Builder {
    return createHeroPropsPbFromData(heroProperty)
}

fun createHeroPropsPbFromData(heroProps: HeroProperty): pb4client.DetailedQueryHerosInFo.Builder {
    // 生成protobuf消息结构
    val detailedQueryHeroListInfoBuilder = DetailedQueryHerosInFo.newBuilder()
    detailedQueryHeroListInfoBuilder.heroId = heroProps.heroId
    detailedQueryHeroListInfoBuilder.heroProtoId = heroProps.heroProtoId
    detailedQueryHeroListInfoBuilder.lv = heroProps.lv
    detailedQueryHeroListInfoBuilder.advLv = heroProps.advLv
    detailedQueryHeroListInfoBuilder.exp = heroProps.exp
    detailedQueryHeroListInfoBuilder.skill1 = heroProps.skill1
    detailedQueryHeroListInfoBuilder.skill2 = heroProps.skill2
    detailedQueryHeroListInfoBuilder.skill3 = heroProps.skill3
    detailedQueryHeroListInfoBuilder.skill4 = heroProps.skill4
    detailedQueryHeroListInfoBuilder.awake = heroProps.awake
    detailedQueryHeroListInfoBuilder.intSkillId1 = heroProps.IntSkillId1
    detailedQueryHeroListInfoBuilder.intSkillId2 = heroProps.IntSkillId2
    detailedQueryHeroListInfoBuilder.intSkillId3 = heroProps.IntSkillId3
    for ((eId, eInfo) in heroProps.heroEquips) {
        val heroEquipVoBuilder = HeroEquipVo.newBuilder()
        heroEquipVoBuilder.heroTrophiesId = eId
        heroEquipVoBuilder.advLv = eInfo.advLv
        detailedQueryHeroListInfoBuilder.addHeroEquipVos(heroEquipVoBuilder)
    }
    detailedQueryHeroListInfoBuilder.starLvUpEndTime = getTimeSec(heroProps.starLvUpEndTime)
    detailedQueryHeroListInfoBuilder.superLvUpEndTime = getTimeSec(heroProps.superLvUpEndTime)
    detailedQueryHeroListInfoBuilder.mainHeroStartTime = getTimeSec(heroProps.mainHeroStartTime)
    detailedQueryHeroListInfoBuilder.mainHeroOverTime = getTimeSec(heroProps.mainHeroOverTime)
    detailedQueryHeroListInfoBuilder.heroPower = heroProps.heroPower
    detailedQueryHeroListInfoBuilder.onFloorIdx = heroProps.onFloorIdx
    return detailedQueryHeroListInfoBuilder
}
