// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface WalkGroupOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.WalkGroup)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *行军组Id
   * </pre>
   *
   * <code>required int64 groupId = 1;</code>
   */
  boolean hasGroupId();
  /**
   * <pre>
   *行军组Id
   * </pre>
   *
   * <code>required int64 groupId = 1;</code>
   */
  long getGroupId();

  /**
   * <code>optional int32 startX = 2;</code>
   */
  boolean hasStartX();
  /**
   * <code>optional int32 startX = 2;</code>
   */
  int getStartX();

  /**
   * <code>optional int32 startY = 3;</code>
   */
  boolean hasStartY();
  /**
   * <code>optional int32 startY = 3;</code>
   */
  int getStartY();

  /**
   * <code>optional int32 gotoX = 4;</code>
   */
  boolean hasGotoX();
  /**
   * <code>optional int32 gotoX = 4;</code>
   */
  int getGotoX();

  /**
   * <code>optional int32 gotoY = 5;</code>
   */
  boolean hasGotoY();
  /**
   * <code>optional int32 gotoY = 5;</code>
   */
  int getGotoY();

  /**
   * <pre>
   *目标玩家名称
   * </pre>
   *
   * <code>optional string taregetPlayerName = 6;</code>
   */
  boolean hasTaregetPlayerName();
  /**
   * <pre>
   *目标玩家名称
   * </pre>
   *
   * <code>optional string taregetPlayerName = 6;</code>
   */
  java.lang.String getTaregetPlayerName();
  /**
   * <pre>
   *目标玩家名称
   * </pre>
   *
   * <code>optional string taregetPlayerName = 6;</code>
   */
  com.google.protobuf.ByteString
      getTaregetPlayerNameBytes();

  /**
   * <code>optional int32 bossId = 7;</code>
   */
  boolean hasBossId();
  /**
   * <code>optional int32 bossId = 7;</code>
   */
  int getBossId();

  /**
   * <code>optional int32 relicId = 8;</code>
   */
  boolean hasRelicId();
  /**
   * <code>optional int32 relicId = 8;</code>
   */
  int getRelicId();

  /**
   * <code>optional int32 resType = 9;</code>
   */
  boolean hasResType();
  /**
   * <code>optional int32 resType = 9;</code>
   */
  int getResType();

  /**
   * <code>optional int32 resLv = 10;</code>
   */
  boolean hasResLv();
  /**
   * <code>optional int32 resLv = 10;</code>
   */
  int getResLv();

  /**
   * <pre>
   *出发时的行军类型
   * </pre>
   *
   * <code>optional int32 startRunType = 11;</code>
   */
  boolean hasStartRunType();
  /**
   * <pre>
   *出发时的行军类型
   * </pre>
   *
   * <code>optional int32 startRunType = 11;</code>
   */
  int getStartRunType();

  /**
   * <pre>
   *行军类型
   * </pre>
   *
   * <code>optional int32 runType = 12;</code>
   */
  boolean hasRunType();
  /**
   * <pre>
   *行军类型
   * </pre>
   *
   * <code>optional int32 runType = 12;</code>
   */
  int getRunType();

  /**
   * <code>optional int32 forceState = 13;</code>
   */
  boolean hasForceState();
  /**
   * <code>optional int32 forceState = 13;</code>
   */
  int getForceState();

  /**
   * <pre>
   *运输的资源
   * </pre>
   *
   * <code>optional string haveRes = 16;</code>
   */
  boolean hasHaveRes();
  /**
   * <pre>
   *运输的资源
   * </pre>
   *
   * <code>optional string haveRes = 16;</code>
   */
  java.lang.String getHaveRes();
  /**
   * <pre>
   *运输的资源
   * </pre>
   *
   * <code>optional string haveRes = 16;</code>
   */
  com.google.protobuf.ByteString
      getHaveResBytes();

  /**
   * <pre>
   *采集负重
   * </pre>
   *
   * <code>optional int32 farmWeight = 17;</code>
   */
  boolean hasFarmWeight();
  /**
   * <pre>
   *采集负重
   * </pre>
   *
   * <code>optional int32 farmWeight = 17;</code>
   */
  int getFarmWeight();

  /**
   * <pre>
   *开始时间
   * </pre>
   *
   * <code>optional int32 startTime = 18;</code>
   */
  boolean hasStartTime();
  /**
   * <pre>
   *开始时间
   * </pre>
   *
   * <code>optional int32 startTime = 18;</code>
   */
  int getStartTime();

  /**
   * <pre>
   *结束时间
   * </pre>
   *
   * <code>optional int32 overTime = 19;</code>
   */
  boolean hasOverTime();
  /**
   * <pre>
   *结束时间
   * </pre>
   *
   * <code>optional int32 overTime = 19;</code>
   */
  int getOverTime();

  /**
   * <pre>
   *这条线的唯一ID
   * </pre>
   *
   * <code>optional int64 walkOnlyId = 20;</code>
   */
  boolean hasWalkOnlyId();
  /**
   * <pre>
   *这条线的唯一ID
   * </pre>
   *
   * <code>optional int64 walkOnlyId = 20;</code>
   */
  long getWalkOnlyId();

  /**
   * <pre>
   *非预警部队信息
   * </pre>
   *
   * <code>repeated .client2server.ForceInfoInWalk forces = 21;</code>
   */
  java.util.List<pb4client.ForceInfoInWalk> 
      getForcesList();
  /**
   * <pre>
   *非预警部队信息
   * </pre>
   *
   * <code>repeated .client2server.ForceInfoInWalk forces = 21;</code>
   */
  pb4client.ForceInfoInWalk getForces(int index);
  /**
   * <pre>
   *非预警部队信息
   * </pre>
   *
   * <code>repeated .client2server.ForceInfoInWalk forces = 21;</code>
   */
  int getForcesCount();
  /**
   * <pre>
   *非预警部队信息
   * </pre>
   *
   * <code>repeated .client2server.ForceInfoInWalk forces = 21;</code>
   */
  java.util.List<? extends pb4client.ForceInfoInWalkOrBuilder> 
      getForcesOrBuilderList();
  /**
   * <pre>
   *非预警部队信息
   * </pre>
   *
   * <code>repeated .client2server.ForceInfoInWalk forces = 21;</code>
   */
  pb4client.ForceInfoInWalkOrBuilder getForcesOrBuilder(
      int index);

  /**
   * <pre>
   *预警部队信息
   * </pre>
   *
   * <code>optional .client2server.WarningInfoInWalk warnInfo = 22;</code>
   */
  boolean hasWarnInfo();
  /**
   * <pre>
   *预警部队信息
   * </pre>
   *
   * <code>optional .client2server.WarningInfoInWalk warnInfo = 22;</code>
   */
  pb4client.WarningInfoInWalk getWarnInfo();
  /**
   * <pre>
   *预警部队信息
   * </pre>
   *
   * <code>optional .client2server.WarningInfoInWalk warnInfo = 22;</code>
   */
  pb4client.WarningInfoInWalkOrBuilder getWarnInfoOrBuilder();

  /**
   * <pre>
   *是否行军冲突 0：否 1：是
   * </pre>
   *
   * <code>optional int32 isConflict = 23;</code>
   */
  boolean hasIsConflict();
  /**
   * <pre>
   *是否行军冲突 0：否 1：是
   * </pre>
   *
   * <code>optional int32 isConflict = 23;</code>
   */
  int getIsConflict();

  /**
   * <pre>
   *集结Id
   * </pre>
   *
   * <code>optional int64 massId = 24;</code>
   */
  boolean hasMassId();
  /**
   * <pre>
   *集结Id
   * </pre>
   *
   * <code>optional int64 massId = 24;</code>
   */
  long getMassId();

  /**
   * <pre>
   *初始行军时间
   * </pre>
   *
   * <code>optional int32 initialWalkTime = 25;</code>
   */
  boolean hasInitialWalkTime();
  /**
   * <pre>
   *初始行军时间
   * </pre>
   *
   * <code>optional int32 initialWalkTime = 25;</code>
   */
  int getInitialWalkTime();
}