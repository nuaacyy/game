package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.*
import pb4server.*
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*
import java.util.Arrays.asList

class QueryInAllianceRankOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(publicCache: PublicCache, req: World2PublicAskReq, resp: World2PublicAskResp.Builder) {
        val internalMessage = req.queryInAllianceRankReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val worldId = req.worldId
        val rt = dealMsg(publicCache, allianceId, playerId, worldId, internalMessage)

        resp.setQueryInAllianceRankRt(rt)
    }

    fun dealMsg(publicCache: PublicCache,
                allianceId: Long,
                playerId: Long,
                worldId: Long,
                req: QueryInAllianceRankReq): QueryInAllianceRankRt.Builder {
        val rt = QueryInAllianceRankRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val allianceMembers =
            publicCache.allianceMemberCache.findAllianceMembersMapByAllianceId(allianceId)
        val allianceMemberTraces =
            publicCache.allianceMemberTraceCache.findAllianceMemberTracesByAllianceId(allianceId)

        when (req.rankType) {
            com.point18.slg2d.common.constg.IN_ALLIANCE_HONOR_RANK -> {
                if (allianceMemberTraces.size > 1) {
                    allianceMemberTraces.sortWith(Comparator { a1, a2 ->
                        if (a1.honor > a2.honor) {
                            return@Comparator 1
                        } else if (a1.honor == a2.honor) {
                            if (a1.id < a2.id) {
                                return@Comparator 1
                            } else if (a1.id < a2.id) {
                                return@Comparator 0
                            } else {
                                return@Comparator -1
                            }
                        } else {
                            return@Comparator -1
                        }
                    })
                }

                for (allianceMemberTrace in allianceMemberTraces) {
                    val allianceMember = allianceMembers[allianceMemberTrace.playerId]
                    if (allianceMember == null) {
                        // 不存在的话说明玩家已经不在这个联盟了,无视这一条
                        continue
                    }
                    val nowPos = LinkedList<Int>()
                    for ((p, _) in allianceMember.alliancePosMap) {
                        nowPos.add(p)
                    }
                    val queryInAllianceRankVo = makeQueryInAllianceRankVo(allianceMember, allianceMemberTrace)
                    queryInAllianceRankVo.addValue(allianceMemberTrace.honor)
                    rt.addQueryInAllianceRankVos(queryInAllianceRankVo)
                }
            }
            com.point18.slg2d.common.constg.IN_ALLIANCE_KILLSOLIDER_RANK -> {
                if (allianceMemberTraces.size > 1) {
                    allianceMemberTraces.sortWith(Comparator { a1, a2 ->
                        if (a1.killSolider > a2.killSolider) {
                            return@Comparator 1
                        } else if (a1.killSolider == a2.killSolider) {
                            if (a1.id < a2.id) {
                                return@Comparator 1
                            } else if (a1.id < a2.id) {
                                return@Comparator 0
                            } else {
                                return@Comparator -1
                            }
                        } else {
                            return@Comparator -1
                        }
                    })
                }

                for (allianceMemberTrace in allianceMemberTraces) {
                    val allianceMember = allianceMembers[allianceMemberTrace.playerId]
                    if (allianceMember == null) {
                        // 不存在的话说明玩家已经不在这个联盟了,无视这一条
                        continue
                    }
                    val queryInAllianceRankVo = makeQueryInAllianceRankVo(allianceMember, allianceMemberTrace)
                    queryInAllianceRankVo.addValue(allianceMemberTrace.killSolider)
                    rt.addQueryInAllianceRankVos(queryInAllianceRankVo)
                }
            }

            com.point18.slg2d.common.constg.IN_ALLIANCE_CureSolider_RANK -> {
                if (allianceMemberTraces.size > 1) {
                    allianceMemberTraces.sortWith(Comparator { a1, a2 ->
                        if (a1.cureSolider > a2.cureSolider) {
                            return@Comparator 1
                        } else if (a1.cureSolider == a2.cureSolider) {
                            if (a1.id < a2.id) {
                                return@Comparator 1
                            } else if (a1.id < a2.id) {
                                return@Comparator 0
                            } else {
                                return@Comparator -1
                            }
                        } else {
                            return@Comparator -1
                        }
                    })
                }

                for (allianceMemberTrace in allianceMemberTraces) {
                    val allianceMember = allianceMembers[allianceMemberTrace.playerId]
                    if (allianceMember == null) {
                        // 不存在的话说明玩家已经不在这个联盟了,无视这一条
                        continue
                    }

                    val queryInAllianceRankVo = makeQueryInAllianceRankVo(allianceMember, allianceMemberTrace)
                    queryInAllianceRankVo.addValue(allianceMemberTrace.cureSolider)
                    rt.addQueryInAllianceRankVos(queryInAllianceRankVo)
                }
            }

            com.point18.slg2d.common.constg.IN_ALLIANCE_KillMonster_RANK -> {
                if (allianceMemberTraces.size > 1) {
                    allianceMemberTraces.sortWith(Comparator { a1, a2 ->
                        if (a1.killMonster > a2.killMonster) {
                            return@Comparator 1
                        } else if (a1.killMonster == a2.killMonster) {
                            if (a1.id < a2.id) {
                                return@Comparator 1
                            } else if (a1.id < a2.id) {
                                return@Comparator 0
                            } else {
                                return@Comparator -1
                            }
                        } else {
                            return@Comparator -1
                        }
                    })
                }

                for (allianceMemberTrace in allianceMemberTraces) {
                    val allianceMember = allianceMembers[allianceMemberTrace.playerId]
                    if (allianceMember == null) {
                        // 不存在的话说明玩家已经不在这个联盟了,无视这一条
                        continue
                    }

                    val queryInAllianceRankVo = makeQueryInAllianceRankVo(allianceMember, allianceMemberTrace)
                    queryInAllianceRankVo.addValue(allianceMemberTrace.killMonster)
                    rt.addQueryInAllianceRankVos(queryInAllianceRankVo)
                }
            }

            com.point18.slg2d.common.constg.IN_ALLIANCE_WEEKHONOR_RANK -> {
                if (allianceMemberTraces.size > 1) {
                    allianceMemberTraces.sortWith(Comparator { a1, a2 ->
                        if (a1.weekHonor > a2.weekHonor) {
                            return@Comparator 1
                        } else if (a1.weekHonor == a2.weekHonor) {
                            if (a1.id < a2.id) {
                                return@Comparator 1
                            } else if (a1.id < a2.id) {
                                return@Comparator 0
                            } else {
                                return@Comparator -1
                            }
                        } else {
                            return@Comparator -1
                        }
                    })
                }

                for (allianceMemberTrace in allianceMemberTraces) {
                    val allianceMember = allianceMembers[allianceMemberTrace.playerId]
                    if (allianceMember == null) {
                        // 不存在的话说明玩家已经不在这个联盟了,无视这一条
                        continue
                    }

                    val queryInAllianceRankVo = makeQueryInAllianceRankVo(allianceMember, allianceMemberTrace)
                    queryInAllianceRankVo.addValue(allianceMemberTrace.weekHonor)
                    rt.addQueryInAllianceRankVos(queryInAllianceRankVo)
                }
            }

            com.point18.slg2d.common.constg.IN_ALLIANCE_WEEKKILLSOLIDER_RANK -> {
                if (allianceMemberTraces.size > 1) {
                    allianceMemberTraces.sortWith(Comparator { a1, a2 ->
                        if (a1.weekKillSolider > a2.weekKillSolider) {
                            return@Comparator 1
                        } else if (a1.weekKillSolider == a2.weekKillSolider) {
                            if (a1.id < a2.id) {
                                return@Comparator 1
                            } else if (a1.id < a2.id) {
                                return@Comparator 0
                            } else {
                                return@Comparator -1
                            }
                        } else {
                            return@Comparator -1
                        }
                    })
                }

                for (allianceMemberTrace in allianceMemberTraces) {
                    val allianceMember = allianceMembers[allianceMemberTrace.playerId]
                    if (allianceMember == null) {
                        // 不存在的话说明玩家已经不在这个联盟了,无视这一条
                        continue
                    }

                    val queryInAllianceRankVo = makeQueryInAllianceRankVo(allianceMember, allianceMemberTrace)
                    queryInAllianceRankVo.addValue(allianceMemberTrace.weekKillSolider)
                    rt.addQueryInAllianceRankVos(queryInAllianceRankVo)
                }

            }

            com.point18.slg2d.common.constg.IN_ALLIANCE_WEEKCURESOLIDER_RANK -> {
                if (allianceMemberTraces.size > 1) {
                    allianceMemberTraces.sortWith(Comparator { a1, a2 ->
                        if (a1.weekCureSolider > a2.weekCureSolider) {
                            return@Comparator 1
                        } else if (a1.weekCureSolider == a2.weekCureSolider) {
                            if (a1.id < a2.id) {
                                return@Comparator 1
                            } else if (a1.id < a2.id) {
                                return@Comparator 0
                            } else {
                                return@Comparator -1
                            }
                        } else {
                            return@Comparator -1
                        }
                    })
                }

                for (allianceMemberTrace in allianceMemberTraces) {
                    val allianceMember = allianceMembers[allianceMemberTrace.playerId]
                    if (allianceMember == null) {
                        // 不存在的话说明玩家已经不在这个联盟了,无视这一条
                        continue
                    }

                    val queryInAllianceRankVo = makeQueryInAllianceRankVo(allianceMember, allianceMemberTrace)
                    queryInAllianceRankVo.addValue(allianceMemberTrace.weekCureSolider)
                    rt.addQueryInAllianceRankVos(queryInAllianceRankVo)
                }
            }

            com.point18.slg2d.common.constg.IN_ALLIANCE_WEEKTRANSPORTATIONVALUE_RANK -> {
                if (allianceMemberTraces.size > 1) {
                    allianceMemberTraces.sortWith(Comparator { a1, a2 ->
                        if (a1.weekTransportationValue > a2.weekTransportationValue) {
                            return@Comparator 1
                        } else if (a1.weekTransportationValue == a2.weekTransportationValue) {
                            if (a1.id < a2.id) {
                                return@Comparator 1
                            } else if (a1.id < a2.id) {
                                return@Comparator 0
                            } else {
                                return@Comparator -1
                            }
                        } else {
                            return@Comparator -1
                        }
                    })
                }

                for (allianceMemberTrace in allianceMemberTraces) {
                    val allianceMember = allianceMembers[allianceMemberTrace.playerId]
                    if (allianceMember == null) {
                        // 不存在的话说明玩家已经不在这个联盟了,无视这一条
                        continue
                    }

                    val queryInAllianceRankVo = makeQueryInAllianceRankVo(allianceMember, allianceMemberTrace)
                    queryInAllianceRankVo.addValue(allianceMemberTrace.weekTransportationValue)
                    rt.addQueryInAllianceRankVos(queryInAllianceRankVo)
                }
            }

            com.point18.slg2d.common.constg.IN_ALLIANCE_WEEKKILLMONSTER_RANK -> {
                if (allianceMemberTraces.size > 1) {
                    allianceMemberTraces.sortWith(Comparator { a1, a2 ->
                        if (a1.weekKillMonster > a2.weekKillMonster) {
                            return@Comparator 1
                        } else if (a1.weekKillMonster == a2.weekKillMonster) {
                            if (a1.id < a2.id) {
                                return@Comparator 1
                            } else if (a1.id < a2.id) {
                                return@Comparator 0
                            } else {
                                return@Comparator -1
                            }
                        } else {
                            return@Comparator -1
                        }
                    })
                }

                for (allianceMemberTrace in allianceMemberTraces) {
                    val allianceMember = allianceMembers[allianceMemberTrace.playerId]
                    if (allianceMember == null) {
                        // 不存在的话说明玩家已经不在这个联盟了,无视这一条
                        continue
                    }

                    val queryInAllianceRankVo = makeQueryInAllianceRankVo(allianceMember, allianceMemberTrace)
                    queryInAllianceRankVo.addValue(allianceMemberTrace.weekKillMonster)
                    rt.addQueryInAllianceRankVos(queryInAllianceRankVo)
                }
            }

            com.point18.slg2d.common.constg.IN_ALLIANCE_HELP_RANK -> {
                if (allianceMemberTraces.size > 1) {
                    allianceMemberTraces.sortWith(Comparator { a1, a2 ->
                        if (a1.weekHelp > a2.weekHelp) {
                            return@Comparator 1
                        } else if (a1.weekHelp == a2.weekHelp) {
                            if (a1.id < a2.id) {
                                return@Comparator 1
                            } else if (a1.id < a2.id) {
                                return@Comparator 0
                            } else {
                                return@Comparator -1
                            }
                        } else {
                            return@Comparator -1
                        }
                    })
                }

                for (allianceMemberTrace in allianceMemberTraces) {
                    val allianceMember = allianceMembers[allianceMemberTrace.playerId]
                    if (allianceMember == null) {
                        // 不存在的话说明玩家已经不在这个联盟了,无视这一条
                        continue
                    }

                    val queryInAllianceRankVo = makeQueryInAllianceRankVo(allianceMember, allianceMemberTrace)
                    queryInAllianceRankVo.addValue(allianceMemberTrace.weekHelp)
                    rt.addQueryInAllianceRankVos(queryInAllianceRankVo)
                }

            }

            com.point18.slg2d.common.constg.IN_ALLIANCE_COMPETITION_RANK -> {
                if (allianceMemberTraces.size > 1) {
                    allianceMemberTraces.sortWith(Comparator { a1, a2 ->
                        if (a1.allianceCompetitionScore > a2.allianceCompetitionScore) {
                            return@Comparator 1
                        } else if (a1.allianceCompetitionScore == a2.allianceCompetitionScore) {
                            if (a1.allianceCompetitionScoreChangeTime < a2.allianceCompetitionScoreChangeTime) {
                                return@Comparator 1
                            } else if (a1.allianceCompetitionScoreChangeTime < a2.allianceCompetitionScoreChangeTime) {
                                return@Comparator 0
                            } else {
                                return@Comparator -1
                            }
                        } else {
                            return@Comparator -1
                        }
                    })
                }

                for (allianceMemberTrace in allianceMemberTraces) {
                    val allianceMember = allianceMembers[allianceMemberTrace.playerId]
                    if (allianceMember == null) {
                        // 不存在的话说明玩家已经不在这个联盟了,无视这一条
                        continue
                    }

                    val queryInAllianceRankVo = makeQueryInAllianceRankVo(allianceMember, allianceMemberTrace)

                    val addValue = LinkedList(asList(
                        allianceMemberTrace.allianceCompetitionScore,
                        allianceMemberTrace.allianceCompetitionQuestSuccessNum.toLong(),
                        allianceMemberTrace.allianceCompetitionQuestGetNum.toLong()
                    ))

                    queryInAllianceRankVo.addAllValue(addValue)
                    rt.addQueryInAllianceRankVos(queryInAllianceRankVo)
                }
            }

            com.point18.slg2d.common.constg.IN_ALLIANCE_WEEKMONSTERSCORE_RANK -> {
                if (allianceMemberTraces.size > 1) {
                    allianceMemberTraces.sortWith(Comparator { a1, a2 ->
                        if (a1.weekMonsterScore > a2.weekMonsterScore) {
                            return@Comparator 1
                        } else if (a1.weekMonsterScore == a2.weekMonsterScore) {
                            if (a1.id < a2.id) {
                                return@Comparator 1
                            } else if (a1.id < a2.id) {
                                return@Comparator 0
                            } else {
                                return@Comparator -1
                            }
                        } else {
                            return@Comparator -1
                        }
                    })
                }

                for (allianceMemberTrace in allianceMemberTraces) {
                    val allianceMember = allianceMembers[allianceMemberTrace.playerId]
                    if (allianceMember == null) {
                        // 不存在的话说明玩家已经不在这个联盟了,无视这一条
                        continue
                    }

                    val queryInAllianceRankVo = makeQueryInAllianceRankVo(allianceMember, allianceMemberTrace)

                    queryInAllianceRankVo.addValue(allianceMemberTrace.weekMonsterScore)
                    rt.addQueryInAllianceRankVos(queryInAllianceRankVo)
                }
            }
        }

        return rt
    }
}

fun makeQueryInAllianceRankVo(
    allianceMember: AllianceMember,
    allianceMemberTrace: AllianceMemberTrace
): (QueryInAllianceRankVo.Builder) {
    val nowPos = LinkedList<Int>()
    for ((p, _) in allianceMember.alliancePosMap) {
        nowPos.add(p)
    }

    val qar = QueryInAllianceRankVo.newBuilder()
    qar.playerId = allianceMemberTrace.playerId
    qar.playerName = allianceMember.name
    qar.playerNickName = allianceMember.allianceNickName
    qar.areaNo = allianceMember.mapAreaNo.toLong()
    qar.photo = allianceMember.photoProtoId
    qar.power = allianceMember.memPower
    qar.addAllPos(nowPos)
    qar.curentPos = 0

    return qar
}
