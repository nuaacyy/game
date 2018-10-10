package com.point18.slg2d.home.module.skin

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ACTION_STRENGTH_SKIN
import com.point18.slg2d.common.constg.MainBuilding
import com.point18.slg2d.common.constg.RES_PROPS
import com.point18.slg2d.common.constg.SKIN_EFFECT
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.*
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.StrengthSkinEvent
import com.point18.slg2d.home.module.fireEvent
import pb4client.StrengSkinRt
import java.util.*

// 强化皮肤
class StrengthSkinDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus3<HomePlayerDC, SkinDC, InnerCityDC>(
        HomePlayerDC::class.java,
        SkinDC::class.java,
        InnerCityDC::class.java,
        Arrays.asList(
            resHelper,
            effectHelper
        )
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val skinType = (msg as pb4client.StrengSkin).skinType
        val useNum = msg.useNum
        prepare(session) { homePlayerDC, skinDC, innerCityDC ->
            val rt = strengthSkin(session, skinType, useNum, homePlayerDC, skinDC, innerCityDC)
            session.sendMsg(MsgType.StrengSkin_46, rt)
        }
    }

    fun strengthSkin(
        session: PlayerActor,
        skinType: Int,
        useNum: Int,
        homePlayerDC: HomePlayerDC,
        skinDC: SkinDC,
        innerCityDC: InnerCityDC
    ): StrengSkinRt {
        val rt = pb4client.StrengSkinRt.newBuilder()
        rt.skintype = skinType
        rt.rt = ResultCode.SUCCESS.code
        rt.isSuccess = 1

        val player = homePlayerDC.player

        val mainBuilding =
            innerCityDC.findMaxLvInnerBuildingFromCastleIdAndType(player.focusCastleId, MainBuilding)

        if (mainBuilding == null) {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }

        val limitLevel = pcs.basicProtoCache.openSkinStrengthen
        if (mainBuilding.lv < limitLevel) {
            // 限制强化等级
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }

        val skinInfoList = skinDC.findSkinsByPlayerId()
        var skin: Skin? = null

        for (skinInfo in skinInfoList) {

            if (skinInfo.skinType == skinType) {
                skin = skinInfo
            }
        }


        if (skin == null && skinType != 1) {
            // 没有该皮肤
            rt.rt = (ResultCode.SKIN_NO_HAVE_ERROR.code)
            return rt.build()
        }


        if (skin == null && skinType == 1) {
            // 没有默认皮肤,给一个
            skin = skinDC.createSkin(skinType, 0)
        }

        val data = pcs.skinAttributeCache.skinAttributeProtoMapBySkinType[skinType]
        if (data == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }
        if (skin == null) {
            throw RuntimeException(" strengthSkin.kt :: skin == null")
        }
        val curStar = skin.star
        var curSkinAttribute: com.point18.slg2d.common.pc.SkinAttributeProto? = null
        var maxStar: Int
        maxStar = -1


        for (skinAttr in data) {

            if (skinAttr.star == curStar) {
                curSkinAttribute = skinAttr
            }


            if (skinAttr.star > maxStar) {
                maxStar = skinAttr.star
            }
        }


        if (maxStar == curStar) {
            // 已经是最大星了
            rt.rt = (ResultCode.SKIN_MAX_STAR_ERROR.code)
            return rt.build()
        }


        if (curSkinAttribute == null) {
            // 找不到配置
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }


        if (useNum <= 0 || useNum > curSkinAttribute.propsNum) {
            // 数量不对
            rt.rt = (ResultCode.SKIN_USE_NUM_ERROR.code)
            return rt.build()
        }

        val resVos = ResVo(RES_PROPS, curSkinAttribute.props.toLong(), useNum.toLong())

        val checkCost = resHelper.checkRes(session, resVos)

        if (!checkCost) {
            rt.rt = (ResultCode.LESS_RESOUCE.code)
            return rt.build()
        }

        // 扣除消耗
        resHelper.costRes(session, ACTION_STRENGTH_SKIN, homePlayerDC.player, resVos)

        val rand = com.point18.slg2d.common.commonfunc.getRandInt(10000)

        if (rand > ((useNum.toFloat()) / (curSkinAttribute.propsNum.toFloat()) * 10000)) {
            //失败
            rt.isSuccess = (2)
            return rt.build()
        }

        skin.star = curStar + 1

        effectHelper.syncEffect2World(session, SKIN_EFFECT)
        fireEvent(session, StrengthSkinEvent(skin.skinType, skin.star))
        return rt.build()
    }
}
