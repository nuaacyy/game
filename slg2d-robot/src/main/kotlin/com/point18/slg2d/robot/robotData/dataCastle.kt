package com.point18.slg2d.robot.robotData


data class CastleData(
	var id: Long,
	var x: Int,
	var y: Int,
	var name: String,
	var castleType: Int,
	var lv: Int
)

class RDataCastle {
	var castles: CastleData

	init {
		castles = CastleData(0,0,0,"",0,0)
	}
}
