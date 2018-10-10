package com.point18.slg2d.common.constg

// 部队状态
const val RecordingStandby: Int = 1  // 待命
const val RecordingMarch: Int = 2  // 行军
const val RecordingGarrison: Int = 3  // 驻守
const val RecordingMita: Int = 4  // 屯田
const val RecordingConscription: Int = 5  // 征兵
const val RecordingBackCity: Int = 6  // 回城
const val RecordingTraining: Int = 7  // 练兵
const val RecordingInFight: Int = 8  // 对峙
const val RecordingWait: Int = 10 // 等待状态,在土地上等待指令,用于军团出征中,等待状态会驻守本块地
const val RecordingTransfer: Int = 11 // 调动
const val RecordingBuildFlag: Int = 12 // 帮忙建造帮派战旗中

// 表示会发生cellEvent的部队状态
var CELL_EVENT_FORCE_HAPPEN_MARK: HashMap<Int, Int> = hashMapOf()