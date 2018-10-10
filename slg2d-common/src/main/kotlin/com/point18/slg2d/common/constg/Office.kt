package com.point18.slg2d.common.constg

//官职分类
const val KingOffice: Int = 1 //王国官职
const val OverlordOffice: Int = 2 //霸主官职
const val GreatEmperorOffice: Int = 3 //大帝官职

const val OneLvOffice: Int = 1 //一级官职
const val TwoLvOffice: Int = 2 //二级官职
const val ThreeLvOffice: Int = 3 //三级官职

const val KING_PROTO_ID = 1 //国王官职Id

//官职功能
enum class OfficeFunction(val v : Int) {
    SetOffice(2), //设置官职
    EditorCountryNotice(3), //发布王国公告
    SendCountryBuff(4), //发布王国强化
    AwardAlliance(5), //赏赐
    AmnestyWholeCountry(6) //天下大赦
}
