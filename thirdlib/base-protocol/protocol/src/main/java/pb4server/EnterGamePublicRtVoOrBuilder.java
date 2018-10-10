// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

public interface EnterGamePublicRtVoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.EnterGamePublicRtVo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *联盟成员
   * </pre>
   *
   * <code>repeated .pb4server.MemberPlayerInfoVo members = 1;</code>
   */
  java.util.List<pb4server.MemberPlayerInfoVo> 
      getMembersList();
  /**
   * <pre>
   *联盟成员
   * </pre>
   *
   * <code>repeated .pb4server.MemberPlayerInfoVo members = 1;</code>
   */
  pb4server.MemberPlayerInfoVo getMembers(int index);
  /**
   * <pre>
   *联盟成员
   * </pre>
   *
   * <code>repeated .pb4server.MemberPlayerInfoVo members = 1;</code>
   */
  int getMembersCount();
  /**
   * <pre>
   *联盟成员
   * </pre>
   *
   * <code>repeated .pb4server.MemberPlayerInfoVo members = 1;</code>
   */
  java.util.List<? extends pb4server.MemberPlayerInfoVoOrBuilder> 
      getMembersOrBuilderList();
  /**
   * <pre>
   *联盟成员
   * </pre>
   *
   * <code>repeated .pb4server.MemberPlayerInfoVo members = 1;</code>
   */
  pb4server.MemberPlayerInfoVoOrBuilder getMembersOrBuilder(
      int index);

  /**
   * <pre>
   *联盟信息
   * </pre>
   *
   * <code>.pb4server.AllianceInfoVo allianceInfo = 2;</code>
   */
  boolean hasAllianceInfo();
  /**
   * <pre>
   *联盟信息
   * </pre>
   *
   * <code>.pb4server.AllianceInfoVo allianceInfo = 2;</code>
   */
  pb4server.AllianceInfoVo getAllianceInfo();
  /**
   * <pre>
   *联盟信息
   * </pre>
   *
   * <code>.pb4server.AllianceInfoVo allianceInfo = 2;</code>
   */
  pb4server.AllianceInfoVoOrBuilder getAllianceInfoOrBuilder();

  /**
   * <pre>
   *是否是首次沦陷提醒（本次）
   * </pre>
   *
   * <code>int32 occupiedFlag = 3;</code>
   */
  int getOccupiedFlag();

  /**
   * <pre>
   *上级同盟ID
   * </pre>
   *
   * <code>int64 occupiedById = 4;</code>
   */
  long getOccupiedById();

  /**
   * <pre>
   *上级同盟名称
   * </pre>
   *
   * <code>string occupiedByName = 5;</code>
   */
  java.lang.String getOccupiedByName();
  /**
   * <pre>
   *上级同盟名称
   * </pre>
   *
   * <code>string occupiedByName = 5;</code>
   */
  com.google.protobuf.ByteString
      getOccupiedByNameBytes();

  /**
   * <pre>
   *上级同盟简称
   * </pre>
   *
   * <code>string occupiedByShortName = 6;</code>
   */
  java.lang.String getOccupiedByShortName();
  /**
   * <pre>
   *上级同盟简称
   * </pre>
   *
   * <code>string occupiedByShortName = 6;</code>
   */
  com.google.protobuf.ByteString
      getOccupiedByShortNameBytes();

  /**
   * <pre>
   *被沦陷的时间
   * </pre>
   *
   * <code>int32 occupiedByAt = 7;</code>
   */
  int getOccupiedByAt();

  /**
   * <pre>
   *攻击的玩家ID
   * </pre>
   *
   * <code>int64 occupiedByPlayerId = 8;</code>
   */
  long getOccupiedByPlayerId();

  /**
   * <pre>
   *攻击的玩家名字
   * </pre>
   *
   * <code>string occupiedByPlayerName = 9;</code>
   */
  java.lang.String getOccupiedByPlayerName();
  /**
   * <pre>
   *攻击的玩家名字
   * </pre>
   *
   * <code>string occupiedByPlayerName = 9;</code>
   */
  com.google.protobuf.ByteString
      getOccupiedByPlayerNameBytes();

  /**
   * <pre>
   *请求捐卡的模版ID
   * </pre>
   *
   * <code>int32 demandProtoId = 10;</code>
   */
  int getDemandProtoId();

  /**
   * <pre>
   *请求捐卡未读消息数量
   * </pre>
   *
   * <code>int32 demandUnRead = 11;</code>
   */
  int getDemandUnRead();

  /**
   * <pre>
   *联盟标记列表
   * </pre>
   *
   * <code>repeated .pb4server.AllianceMarkInfoVo allianceMarks = 12;</code>
   */
  java.util.List<pb4server.AllianceMarkInfoVo> 
      getAllianceMarksList();
  /**
   * <pre>
   *联盟标记列表
   * </pre>
   *
   * <code>repeated .pb4server.AllianceMarkInfoVo allianceMarks = 12;</code>
   */
  pb4server.AllianceMarkInfoVo getAllianceMarks(int index);
  /**
   * <pre>
   *联盟标记列表
   * </pre>
   *
   * <code>repeated .pb4server.AllianceMarkInfoVo allianceMarks = 12;</code>
   */
  int getAllianceMarksCount();
  /**
   * <pre>
   *联盟标记列表
   * </pre>
   *
   * <code>repeated .pb4server.AllianceMarkInfoVo allianceMarks = 12;</code>
   */
  java.util.List<? extends pb4server.AllianceMarkInfoVoOrBuilder> 
      getAllianceMarksOrBuilderList();
  /**
   * <pre>
   *联盟标记列表
   * </pre>
   *
   * <code>repeated .pb4server.AllianceMarkInfoVo allianceMarks = 12;</code>
   */
  pb4server.AllianceMarkInfoVoOrBuilder getAllianceMarksOrBuilder(
      int index);

  /**
   * <pre>
   *所有的小红点信息
   * </pre>
   *
   * <code>repeated .pb4server.RedPointVo redPoints = 13;</code>
   */
  java.util.List<pb4server.RedPointVo> 
      getRedPointsList();
  /**
   * <pre>
   *所有的小红点信息
   * </pre>
   *
   * <code>repeated .pb4server.RedPointVo redPoints = 13;</code>
   */
  pb4server.RedPointVo getRedPoints(int index);
  /**
   * <pre>
   *所有的小红点信息
   * </pre>
   *
   * <code>repeated .pb4server.RedPointVo redPoints = 13;</code>
   */
  int getRedPointsCount();
  /**
   * <pre>
   *所有的小红点信息
   * </pre>
   *
   * <code>repeated .pb4server.RedPointVo redPoints = 13;</code>
   */
  java.util.List<? extends pb4server.RedPointVoOrBuilder> 
      getRedPointsOrBuilderList();
  /**
   * <pre>
   *所有的小红点信息
   * </pre>
   *
   * <code>repeated .pb4server.RedPointVo redPoints = 13;</code>
   */
  pb4server.RedPointVoOrBuilder getRedPointsOrBuilder(
      int index);

  /**
   * <pre>
   *是否有未读的联盟邮件主题
   * </pre>
   *
   * <code>int32 noReadTopic = 14;</code>
   */
  int getNoReadTopic();

  /**
   * <pre>
   *联盟外交前20条的发布时间
   * </pre>
   *
   * <code>repeated int32 allianceWaijiaoTime = 15;</code>
   */
  java.util.List<java.lang.Integer> getAllianceWaijiaoTimeList();
  /**
   * <pre>
   *联盟外交前20条的发布时间
   * </pre>
   *
   * <code>repeated int32 allianceWaijiaoTime = 15;</code>
   */
  int getAllianceWaijiaoTimeCount();
  /**
   * <pre>
   *联盟外交前20条的发布时间
   * </pre>
   *
   * <code>repeated int32 allianceWaijiaoTime = 15;</code>
   */
  int getAllianceWaijiaoTime(int index);

  /**
   * <pre>
   *联盟活跃度等级
   * </pre>
   *
   * <code>.pb4server.AllianceLivenessVo allianceLivenessVo = 16;</code>
   */
  boolean hasAllianceLivenessVo();
  /**
   * <pre>
   *联盟活跃度等级
   * </pre>
   *
   * <code>.pb4server.AllianceLivenessVo allianceLivenessVo = 16;</code>
   */
  pb4server.AllianceLivenessVo getAllianceLivenessVo();
  /**
   * <pre>
   *联盟活跃度等级
   * </pre>
   *
   * <code>.pb4server.AllianceLivenessVo allianceLivenessVo = 16;</code>
   */
  pb4server.AllianceLivenessVoOrBuilder getAllianceLivenessVoOrBuilder();

  /**
   * <pre>
   *联盟段位等级
   * </pre>
   *
   * <code>int32 rankLv = 17;</code>
   */
  int getRankLv();

  /**
   * <pre>
   *猎杀邀请信息
   * </pre>
   *
   * <code>repeated .pb4server.HunterInviteInfoVo hunterInvites = 18;</code>
   */
  java.util.List<pb4server.HunterInviteInfoVo> 
      getHunterInvitesList();
  /**
   * <pre>
   *猎杀邀请信息
   * </pre>
   *
   * <code>repeated .pb4server.HunterInviteInfoVo hunterInvites = 18;</code>
   */
  pb4server.HunterInviteInfoVo getHunterInvites(int index);
  /**
   * <pre>
   *猎杀邀请信息
   * </pre>
   *
   * <code>repeated .pb4server.HunterInviteInfoVo hunterInvites = 18;</code>
   */
  int getHunterInvitesCount();
  /**
   * <pre>
   *猎杀邀请信息
   * </pre>
   *
   * <code>repeated .pb4server.HunterInviteInfoVo hunterInvites = 18;</code>
   */
  java.util.List<? extends pb4server.HunterInviteInfoVoOrBuilder> 
      getHunterInvitesOrBuilderList();
  /**
   * <pre>
   *猎杀邀请信息
   * </pre>
   *
   * <code>repeated .pb4server.HunterInviteInfoVo hunterInvites = 18;</code>
   */
  pb4server.HunterInviteInfoVoOrBuilder getHunterInvitesOrBuilder(
      int index);

  /**
   * <pre>
   * 申请加入联盟数量
   * </pre>
   *
   * <code>int32 reqListNum = 19;</code>
   */
  int getReqListNum();
}
