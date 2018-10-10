package com.point18.slg2d.world.module.heroBattle1.directEff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.heroBattle1.fightLogic.directEffStrategyMap

fun directEffInit() {
    directEffStrategyMap[SKILL_SHUNJIANSHANGHAI] = ShunjianshanghaiStrategy()
    directEffStrategyMap[SKILL_SHUNJIANZHILIAO] = ShunjianzhiliaoStrategy()
    directEffStrategyMap[SKILL_TIAOFEI] = TiaofeiStrategy()
    directEffStrategyMap[SKILL_DAPA] = JidaoStrategy()
    directEffStrategyMap[SKILL_JITUI] = JituiStrategy()
    directEffStrategyMap[SKILL_YICHUYOUYI] = YichuyouyiStrategy()
    directEffStrategyMap[SKILL_YICHUYOUHAI] = YichuyouhaiStrategy()
    directEffStrategyMap[SKILL_XUELIANGSHANGXIANJIAJIANXUE] = XueliangshangxianjiajianxueStrategy()
    directEffStrategyMap[SKILL_XUELIANGJIAJIANXUE] = XueliangjiajianxueStrategy()
    directEffStrategyMap[SKILL_TIAOYUE] = TiaoyueStrategy()
    directEffStrategyMap[SKILL_SHANXIAN] = ShanxianStrategy()
    directEffStrategyMap[SKILL_GOUREN] = GourenStrategy()
    directEffStrategyMap[SKILL_ZHAOHUAN] = ZhaohuanStrategy()
    directEffStrategyMap[SKILL_SHUNJIANHUIQI] = ShunjianhuiqiStrategy()
    directEffStrategyMap[SKILL_SHUNJIANJIANQI] = ShunjianjianqiStrategy()
}
