package com.point18.slg2d.world.module.heroBattle.heroBuff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.heroBattle.fightLogic.*

fun buffInit() {
    registerBuff(SKILL_CHIXUSHANGHAI, ChixushanghaiBuffStrategy, DefaultBuffFactory)
    registerBuff(SKILL_CHIXUZHILIAO, ChixuzhiliaoBuffStrategy, DefaultBuffFactory)
    registerBuff(SKILL_SHUXINGBIANHUAN, ShuxingbianhuaBuffStrategy, DefaultBuffFactory)
    registerBuff(SKILL_XUANYUN, XuanyunBuffStrategy, DefaultBuffFactory)
    registerBuff(SKILL_CHENMO, ChenmoBuffStrategy, DefaultBuffFactory)
    registerBuff(SKILL_MEIHUO, MeihuoBuffStrategy, DefaultBuffFactory)
    registerBuff(SKILL_ZUZHIHUIFU, ZuzhizhiliaoBuffStrategy, DefaultBuffFactory)
    registerBuff(SKILL_BATI, BatiBuffStrategy, DefaultBuffFactory)
    registerBuff(SKILL_WUDI, WudiBuffStrategy, DefaultBuffFactory)
    registerBuff(SKILL_WUGONGJIASHANG, WulijiashangBuffStrategy, DefaultBuffFactory)
    registerBuff(SKILL_FAGONGJIASHANG, FashujiashangBuffStrategy, DefaultBuffFactory)
    registerBuff(SKILL_WUFANGJIANSHANG, WulijianshangBuffStrategy, DefaultBuffFactory)
    registerBuff(SKILL_FAFANGJIANSHANG, FashujianshangBuffStrategy, DefaultBuffFactory)
    registerBuff(SKILL_GONGKU, GongkuBuffStrategy, DefaultBuffFactory)
    registerBuff(SKILL_TUJIN, TujinBuffStrategy, DefaultBuffFactory)
    registerBuff(SKILL_DINGREN, DingrenbiaojiBuffStrategy, DefaultBuffFactory)
    registerBuff(SKILL_DINGWEI, DingweibiaojiBuffStrategy, DefaultBuffFactory)
    registerBuff(SKILL_FANTAN, FantanBuffStrategy, DefaultBuffFactory)
    registerBuff(SKILL_GUANLIANJINNENG, ShifangjinnengBuffStrategy, DefaultBuffFactory)
    registerBuff(SKILL_FUHUO, FuhuoBuffStrategy, FuhuoBuffFactory)
    registerBuff(SKILL_XISHOUSHANGHAI, XishoushanghaiBuffStrategy, HudunBuffFactory)
    registerBuff(SKILL_ZHIKONG, ZhikongBuffStrategy, DefaultBuffFactory)
    registerBuff(SKILL_BEIHOUSHOUSHANGJIASHEN, BeihoushanghaijiashenBuffStrategy, DefaultBuffFactory)
    registerBuff(SKILL_BEIHOUZAOCHENGSHANGHAIJIASHEN, BeihouzaochengshanghaijiashenBuffStrategy, DefaultBuffFactory)
    registerBuff(SKILL_DOTSHANGHAIJIASHEN, DotshanghaijiashenBuffStrategy, DefaultBuffFactory)
    registerBuff(SKILL_DINGSHEN, DingshenBuffStrategy, DefaultBuffFactory)
    registerBuff(SKILL_YIDONGSHIFANGJINENG, MoveShifangjinnengBuffStrategy, MoveLaunchSkillBuffFactory)
    registerBuff(SKILL_CHUFAJINENG, ChufajinnengBuffStrategy, ChufaBuffFactory)
    registerBuff(SKILL_FIX_CHIXUHUIXUE, FixChixuhuixueBuffStrategy, DefaultBuffFactory)
    registerBuff(SKILL_FIX_CHIXUHUIQI, FixChixuhuiqiBuffStrategy, DefaultBuffFactory)
}
