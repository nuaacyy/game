package com.point18.slg2d.common.constg

//请求行为分类
//日志请求
const val LogMove: Int = 101 //移动
const val LogMoveEnd: Int = 102 //移动结束
const val LogStartAttack: Int = 103 //准备普通攻击
const val LogBeginAttack: Int = 104 //开始普通攻击
const val LogAttack: Int = 105 //普通攻击伤害
const val LogSingSkill: Int = 106 //开始吟唱技能
const val LogLaunchSkill: Int = 107 //释放技能
const val LogLaunchSkillEffect: Int = 108 //释放技能效果
const val LogSkillEffect: Int = 109 //技能效果生效
const val LogAttachBuff: Int = 110 //附加Buff
const val LogDetachBuff: Int = 111 //移除Buff
const val LogProperty: Int = 112 //属性变化
const val LogDie: Int = 113 //死亡
const val LogFightFinish: Int = 114 //战斗结束
const val LogRevive: Int = 115 //复活中
const val LogCreateSceneEntity: Int = 116 //创建场景对象

//日志使用枚举参数
const val CostHp: Int = 200 //扣除的血量
const val AddHp: Int = 201 //增加的血量
const val CostMorale: Int = 202 //扣除士气
const val AddMorale: Int = 203 //添加士气
const val IsDodge: Int = 204 //是否闪避
const val IsCrit: Int = 205 //是否暴击
const val SkillId: Int = 206 //技能Id
const val SkillEffId: Int = 207 //SkillEffId
const val ToPosX: Int = 208 //移动到的坐标
const val ToPosY: Int = 209 //移动到的坐标
const val MoveSpeed: Int = 210 //移动速度
const val FromPosX: Int = 211 //起始坐标
const val FromPosY: Int = 212 //起始坐标
const val AtkIndex: Int = 213 //攻击顺序标记
const val BuffId: Int = 214 //buff唯一Id
const val SkillMainEntity: Int = 215 //技能的主对象
const val SkillTargetEntity: Int = 216 //目标实体
const val EntityId: Int = 217 //对象Id
const val FightResult: Int = 300 //战斗结果

//实体属性分类
//1-15取hero.go中的英雄通用战斗属性

//实体其他属性从20起
const val ProtoId: Int = 20 //配置Id
const val UnitType: Int = 22 //单位类别 英雄/士兵
const val AtkType: Int = 23 //攻击类别 物理/魔法
const val FightSide: Int = 24 //是否是攻击方
const val MoraleLimit: Int = 25 //士气上限
const val Lv: Int = 26 //等级
const val KillAddMorale: Int = 27 //击杀增加的士气
const val ActionOrder: Int = 28 //攻击顺序 普攻-普攻-技能A     普攻-普攻-技能A-普攻-技能B
const val UniqueSkillList: Int = 29 //大招
const val ActiveSkillList: Int = 30 //主动技能
const val PassiveSkillList: Int = 31 //被动技能
const val AttachHeroId: Int = 32 //附属的英雄Id
const val TouchVolume: Int = 33 //触碰体积
const val AtkAnimation: Int = 34 //攻击前摇时间列表
const val Star: Int = 35 //星级
const val Awake: Int = 36 //阶级
const val BulletSpeed: Int = 37 //飞行速度
const val AtkVolume: Int = 38 //攻击体积

//临时属性从50起
const val Hp: Int = 50 //当前血量
const val Morale: Int = 51 //士气
const val PosX: Int = 52 //当前坐标X
const val PosY: Int = 53 //当前坐标Y
const val AliveTime: Int = 56 //存活结束时间
const val Exp: Int = 57 //战前经验
const val Intimacy: Int = 58 //亲密度

//攻击顺序类型
const val NormalAttack: Int = 1 //普攻
const val SkillA: Int = 2 //技能A
const val SkillB: Int = 3 //技能B
const val SkillC: Int = 4 //技能C

const val UpdateTime: Int = 100 //更新时间 ms/次

//状态机状态
const val NullState: Int = 1 //无状态
const val MoveState: Int = 2 //移动状态
const val AttackState: Int = 3 //普通攻击状态
const val SkillState: Int = 4 //释放技能状态
const val FlyState: Int = 5 //飞行状态
const val DieState: Int = 6 //死亡状态
const val ReviveState: Int = 7 //复活状态

fun boolToInt(v: Boolean): Int {
    if (v) {
        return TRUE
    }
    return FALSE
}

fun intToBool(v: Int): Boolean {
    return v == TRUE
}

const val Hero: Int = 1 //英雄
const val Skill: Int = 2 //技能
const val Summoned: Int = 3 //召唤物

//范围
const val Self: Int = 1 //自身
const val AtkTarget: Int = 2 //攻击目标
const val AoeBySelf: Int = 3 //以自身为中心的aoe
const val AoeByAtkTarget: Int = 4 //以攻击目标为中心的aoe
const val LineAoe: Int = 5 //线型aoe
const val FullScreenAoe: Int = 6 //全屏aoe
const val PosByAtkTarget: Int = 7 //以攻击目标为坐标的地
const val PosBySelf: Int = 8 //以自身为坐标的地
const val FanShapedAoe: Int = 9 //扇形AOE

//阵营
const val FriendlyForce: Int = 1 //己方（友军，包括自身）
const val EnemyForce: Int = 2 //敌方
const val NoDistinguish: Int = 3 //不分敌我
const val SelfForce: Int = 4 //本队（自身英雄+召唤对象）
const val FriendlyForceExceptSelf: Int = 5 //己方（友军，不包括自身）

//特殊
const val HpLowest: Int = 1 //生命最低
const val HpHighest: Int = 2//生命最高
const val Nearest: Int = 3 //最近的
const val Farest: Int = 4 //最远的
const val MoraleLowest: Int = 5 //士气最低
const val MoraleHighest: Int = 6//士气最高

//技能类别
const val UniqueSkill: Int = 1 //大招
const val ActiveSkill: Int = 2 //主动技能
const val PassiveSkill: Int = 3 //被动技能

//技能类别
const val SKILL_SHUNJIANSHANGHAI: Int = 1   //瞬间伤害
const val SKILL_CHIXUSHANGHAI: Int = 2   //持续伤害
const val SKILL_SHUNJIANZHILIAO: Int = 3   //瞬间治疗
const val SKILL_CHIXUZHILIAO: Int = 4   //持续治疗
const val SKILL_SHUXINGBIANHUAN: Int = 5   //属性变化
const val SKILL_XUANYUN: Int = 6   //眩晕
const val SKILL_CHENMO: Int = 7   //沉默
const val SKILL_TIAOFEI: Int = 8   //挑飞
const val SKILL_DAPA: Int = 9   //打趴
const val SKILL_JITUI: Int = 10  //击退
const val SKILL_MEIHUO: Int = 11  //魅惑
const val SKILL_ZUZHIHUIFU: Int = 12  //阻止回复
const val SKILL_BATI: Int = 13  //霸体
const val SKILL_WUDI: Int = 14  //无敌
const val SKILL_WUGONGJIASHANG: Int = 15  //物攻加伤
const val SKILL_FAGONGJIASHANG: Int = 16  //法攻加伤
const val SKILL_WUFANGJIANSHANG: Int = 17  //减伤
const val SKILL_FAFANGJIANSHANG: Int = 18  //减伤
const val SKILL_YICHUYOUYI: Int = 19  //移除有益效果
const val SKILL_YICHUYOUHAI: Int = 20  //移除有害效果
const val SKILL_GONGKU: Int = 21  //共苦
const val SKILL_XUELIANGSHANGXIANJIAJIANXUE: Int = 22  //按照当前血量上限加减血
const val SKILL_XUELIANGJIAJIANXUE: Int = 23  //按照当前血量加减血
const val SKILL_TIAOYUE: Int = 24  //跳跃
const val SKILL_SHANXIAN: Int = 25  //闪现
const val SKILL_TUJIN: Int = 26  //突进
const val SKILL_GOUREN: Int = 27  //钩人
const val SKILL_DINGREN: Int = 28  //定人标记
const val SKILL_DINGWEI: Int = 29  //定位标记
const val SKILL_ZHAOHUAN: Int = 30  //召唤
const val SKILL_SHOUWANG: Int = 31  //兽王大招
const val SKILL_FANTAN: Int = 32  //反弹
const val SKILL_SHUNJIANHUIQI: Int = 33   //瞬间回气
const val SKILL_SHUNJIANJIANQI: Int = 34   //瞬间减气
const val SKILL_FUHUO: Int = 35     //复活
const val SKILL_XISHOUSHANGHAI: Int = 36     //吸收伤害
const val SKILL_ZHIKONG: Int = 37       //滞空
const val SKILL_BEIHOUSHOUSHANGJIASHEN: Int = 38   //背后受到的伤害加深
const val SKILL_BEIHOUZAOCHENGSHANGHAIJIASHEN: Int = 39   //背后造成的伤害加深
const val SKILL_DOTSHANGHAIJIASHEN: Int = 40    //dot伤害加深
const val SKILL_FIX_CHIXUHUIXUE: Int = 41 //固定持续回血
const val SKILL_FIX_CHIXUHUIQI: Int = 42 //固定持续回气
const val SKILL_DINGSHEN: Int = 43 //定身
const val SKILL_GUANLIANJINNENG: Int = 100 //关联技能
const val SKILL_YIDONGSHIFANGJINENG: Int = 101 //移动释放技能
const val SKILL_CHUFAJINENG: Int = 102 //触发技能

//buff生效时机
const val OnDead: Int = 1   //死亡时
const val OnProtectCoverRemove: Int = 2 //保护罩移除时
const val OnHpChange: Int = 3   //血量变化时候
const val OnBuffAttach: Int = 4  //buff附加时
const val OnBuffHeart: Int = 5  //buff心跳
const val OnBuffDetach: Int = 6  //buff移除时
const val OnStartMove: Int = 7  //开始移动时
const val OnStartAttack: Int = 8  //开始普攻时
const val OnStartLaunchSkill: Int = 9  //开始发动技能时
const val OnSelectTarget: Int = 10  //选择目标时
const val OnHpRecovery: Int = 11  //回血时
const val OnControl: Int = 12  //被控时
const val OnBeforeHurt: Int = 13  //受到伤害前
const val OnAfterHurt: Int = 14 //受到伤害后
const val OnHurt: Int = 15 //造成伤害时候
const val OnBeHurt1: Int = 16 //受到伤害时候
const val OnBeHurt2: Int = 17 //受到伤害时候
const val OnBeHurt3: Int = 18 //受到伤害时候
const val OnCheckDistance: Int = 19 //检查距离时候
const val OnDotHurt: Int = 20 //dot伤害时

const val NoConflict: Int = 0 //不冲突
const val ConflictAndReplace: Int = 1 //冲突且替换
const val ConflictAndNoReplace: Int = 2 //冲突且不替换

//Buff Filter
const val BuffCover: Int = 1 //覆盖
const val BuffIgnore: Int = 2 //忽略
const val BuffCompareByTime: Int = 3 //失效时间比较

//站位类型
typealias PosType = Int

const val FrontRow: PosType = 1
const val MiddleRow: PosType = 2
const val BackRow: PosType = 3