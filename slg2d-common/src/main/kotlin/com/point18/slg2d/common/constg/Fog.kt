package com.point18.slg2d.common.constg

typealias FogState = Int

const val NotWin: FogState = 1          //1、未战胜
const val NotGetReward: FogState = 2    //2、已战胜未领取奖励
const val UnLocked: FogState = 3        //3、已战胜且领取奖励