package com.point18.slg2d.home.module.equip

import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.CostResWithoutNoticeResult
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.dc.HeroDC
import com.point18.slg2d.home.dc.HomeMyTargetDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.NewEquipDC
import com.point18.slg2d.home.module.equip.usePropEff.*
import com.point18.slg2d.home.module.registerEvent
import java.util.*

class EquipModule : IModule {

    var usePropEff = hashMapOf<Int, HashMap<Int, UseProp>>()
    var usePropCond = hashMapOf<Int, HashMap<Int, UsePropCondition>>()

    override fun moduleInit() {
        usePropEff = hashMapOf()
        val usePropEffResMap = usePropEff.getOrPut(PROP_RES) { hashMapOf() }

        val usePropEffQuickMap = usePropEff.getOrPut(PROP_QUICK) { hashMapOf() }
        val usePropEffKingEquipMap =
            usePropEff.getOrPut(PROP_KING_EQUIP) { hashMapOf() }
        val usePropEffBoxMap = usePropEff.getOrPut(PROP_BOX) { hashMapOf() }
        val usePropEffGiftMap = usePropEff.getOrPut(PROP_GIFT) { hashMapOf() }
        val usePropEffUseMap = usePropEff.getOrPut(PROP_USE) { hashMapOf() }
        val usePropEffEquipMap = usePropEff.getOrPut(PROP_EQUIP) { hashMapOf() }
        val usePropEffBuffMap = usePropEff.getOrPut(PROP_BUFF) { hashMapOf() }
        val usePropEffSeedMap = usePropEff.getOrPut(PROP_SEED) { hashMapOf() }
        val usePropKingEquipCardMap =
            usePropEff.getOrPut(PROP_KING_EQUIP_CARD) { hashMapOf() }

        usePropEffResMap[CasinoCoinBag] = UseResBag()
        usePropEffResMap[CoinBag] = UseResBag()
        usePropEffResMap[FoodBag] = UseResBag()
        usePropEffResMap[WoodBag] = UseResBag()
        usePropEffResMap[StoneBag] = UseResBag()
        usePropEffResMap[IronBag] = UseResBag()
        usePropEffResMap[ExpCardBag] = UseResBag()
        usePropEffResMap[GoldBag] = UseResBag()
        usePropEffResMap[InstanceStrengthBag] = UseInstanceStrengthBag()
        usePropEffResMap[ZhenglingBag] = UseZhenglingBag()
        usePropEffResMap[VipTimeCard] = UseResBag()
        usePropEffResMap[VipExpCard] = UseResBag()
        usePropEffResMap[KingExpCard] = UseResBag()
        usePropEffResMap[SoliderBag] = UseSoliderAdd()
        usePropEffQuickMap[WALK_QUICK_TIME_PROP] = UseWalkSpeed()
        usePropEffQuickMap[MASS_QUICK_TIME_PROP] = UseMassSpeed()
        usePropEffUseMap[HalfWayHome] = UseHalfWayHome()
        usePropEffUseMap[RandomPointMoveCity] = UseRandomPointMoveCity()
        usePropEffUseMap[ADD_MARK_NUM] = UseAddMark()
        usePropEffUseMap[CALL_BOSS] = UseCallBoss()
        usePropEffUseMap[HeroGift] = UseHeroGift()

        usePropEffBoxMap[BOX_NORMAL] = UseResBag()
        usePropEffBoxMap[BOX_RAND1] = UseDropBag()
        usePropEffBoxMap[BOX_RAND2] = UseDropBag()
        usePropEffBoxMap[BOX_RAND3] = UseDropBag()

        usePropEffBuffMap[BUFF_FOOD_ADD] = UseBuffBag()
        usePropEffBuffMap[BUFF_STONE_ADD] = UseBuffBag()
        usePropEffBuffMap[BUFF_WOOD_ADD] = UseBuffBag()
        usePropEffBuffMap[BUFF_IRON_ADD] = UseBuffBag()
        usePropEffBuffMap[BUFF_GOLD_ADD] = UseBuffBag()
        usePropEffBuffMap[BUFF_ADD_ATK] = UseBuffBag()
        usePropEffBuffMap[BUFF_ADD_DEF] = UseBuffBag()
        usePropEffBuffMap[BUFF_ADD_HP] = UseBuffBag()
        usePropEffBuffMap[BUFF_SOLIDER_MAX] = UseBuffBag()
        usePropEffBuffMap[BUFF_KING_EXP] = UseBuffBag()
        usePropEffBuffMap[BUFF_MONSTER_HURT_ADD] = UseBuffBag()
        usePropEffBuffMap[BUFF_PLAYER_DEF] = UseBuffBag()
        usePropEffBuffMap[BUFF_DE_LOOK] = UseBuffBag()
        usePropEffBuffMap[BUFF_ZHABING] = UseBuffBag()
        usePropEffBuffMap[BUFF_JIANGDIJUNXIANG] = UseBuffBag()
        usePropEffBuffMap[BUFF_ADD_SPEEK_WALK] = UseBuffBag()
        usePropEffBuffMap[BUFF_ADD_SPEEK_FARM] = UseBuffBag()
        usePropEffBuffMap[BUFF_ADD_SPEEK_BUILDING] = UseBuffBag()
        usePropEffBuffMap[BUFF_ADD_SPEEK_RESEARCH] = UseBuffBag()
        usePropEffBuffMap[BUFF_ADD_SPEEK_MAKE_SOLIDER] = UseBuffBag()

        usePropCond = hashMapOf()

        registerEvent(USE_PROPS_AT_ONCE, UsePropsEventHandler())
    }

}

var equipM = EquipModule()

data class UsePropReturn(val rt: ResultCode, val s: String)

class Helpers(val resHelper: ResHelper, val effectHelper: ResearchEffectHelper)

data class UsePropsDepDcs(
    val homePlayerDC: HomePlayerDC,
    val heroDC: HeroDC,
    val homeMyTargetDC: HomeMyTargetDC
)

interface UseProp {
    fun useProp(
        depDcs: UsePropsDepDcs,
        propProtoId: Int,
        session: PlayerActor,
        useNum: Int,
        extendVal: String,
        helpers: Helpers,
        costs: LinkedList<ResVo>?,
        costRes: CostResWithoutNoticeResult?,
        sendToClient: (rt: Int, s: String) -> Unit
    ): UsePropReturn?
}

interface UsePropCondition {
    fun useCondition(
        propVoId: Int,
        session: PlayerActor,
        useNum: Int,
        extendVal: String
    ): ResultCode
}