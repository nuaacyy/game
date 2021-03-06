// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface MassGroupOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.MassGroup)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int64 massId = 1;</code>
   */
  boolean hasMassId();
  /**
   * <code>required int64 massId = 1;</code>
   */
  long getMassId();

  /**
   * <code>optional string massName = 2;</code>
   */
  boolean hasMassName();
  /**
   * <code>optional string massName = 2;</code>
   */
  java.lang.String getMassName();
  /**
   * <code>optional string massName = 2;</code>
   */
  com.google.protobuf.ByteString
      getMassNameBytes();

  /**
   * <code>optional int32 runType = 3;</code>
   */
  boolean hasRunType();
  /**
   * <code>optional int32 runType = 3;</code>
   */
  int getRunType();

  /**
   * <code>optional int64 mainPlayerId = 4;</code>
   */
  boolean hasMainPlayerId();
  /**
   * <code>optional int64 mainPlayerId = 4;</code>
   */
  long getMainPlayerId();

  /**
   * <code>optional int32 startX = 5;</code>
   */
  boolean hasStartX();
  /**
   * <code>optional int32 startX = 5;</code>
   */
  int getStartX();

  /**
   * <code>optional int32 startY = 6;</code>
   */
  boolean hasStartY();
  /**
   * <code>optional int32 startY = 6;</code>
   */
  int getStartY();

  /**
   * <code>optional int32 gotoX = 7;</code>
   */
  boolean hasGotoX();
  /**
   * <code>optional int32 gotoX = 7;</code>
   */
  int getGotoX();

  /**
   * <code>optional int32 gotoY = 8;</code>
   */
  boolean hasGotoY();
  /**
   * <code>optional int32 gotoY = 8;</code>
   */
  int getGotoY();

  /**
   * <pre>
   *目标Id（玩家、遗迹、奇观）
   * </pre>
   *
   * <code>optional int64 targetId = 9;</code>
   */
  boolean hasTargetId();
  /**
   * <pre>
   *目标Id（玩家、遗迹、奇观）
   * </pre>
   *
   * <code>optional int64 targetId = 9;</code>
   */
  long getTargetId();

  /**
   * <code>optional .client2server.MassPlayer selfPlayer = 10;</code>
   */
  boolean hasSelfPlayer();
  /**
   * <code>optional .client2server.MassPlayer selfPlayer = 10;</code>
   */
  pb4client.MassPlayer getSelfPlayer();
  /**
   * <code>optional .client2server.MassPlayer selfPlayer = 10;</code>
   */
  pb4client.MassPlayerOrBuilder getSelfPlayerOrBuilder();

  /**
   * <code>optional .client2server.MassPlayer targetPlayer = 11;</code>
   */
  boolean hasTargetPlayer();
  /**
   * <code>optional .client2server.MassPlayer targetPlayer = 11;</code>
   */
  pb4client.MassPlayer getTargetPlayer();
  /**
   * <code>optional .client2server.MassPlayer targetPlayer = 11;</code>
   */
  pb4client.MassPlayerOrBuilder getTargetPlayerOrBuilder();

  /**
   * <pre>
   *集结状态
   * </pre>
   *
   * <code>optional int32 massState = 15;</code>
   */
  boolean hasMassState();
  /**
   * <pre>
   *集结状态
   * </pre>
   *
   * <code>optional int32 massState = 15;</code>
   */
  int getMassState();

  /**
   * <pre>
   *集结时间
   * </pre>
   *
   * <code>optional int32 massTime = 16;</code>
   */
  boolean hasMassTime();
  /**
   * <pre>
   *集结时间
   * </pre>
   *
   * <code>optional int32 massTime = 16;</code>
   */
  int getMassTime();

  /**
   * <pre>
   *集结开始时间
   * </pre>
   *
   * <code>optional int32 massStartTime = 17;</code>
   */
  boolean hasMassStartTime();
  /**
   * <pre>
   *集结开始时间
   * </pre>
   *
   * <code>optional int32 massStartTime = 17;</code>
   */
  int getMassStartTime();

  /**
   * <pre>
   *抵达时间
   * </pre>
   *
   * <code>optional int32 arriveTime = 18;</code>
   */
  boolean hasArriveTime();
  /**
   * <pre>
   *抵达时间
   * </pre>
   *
   * <code>optional int32 arriveTime = 18;</code>
   */
  int getArriveTime();

  /**
   * <pre>
   *初始行军时间
   * </pre>
   *
   * <code>optional int32 initialWalkTime = 12;</code>
   */
  boolean hasInitialWalkTime();
  /**
   * <pre>
   *初始行军时间
   * </pre>
   *
   * <code>optional int32 initialWalkTime = 12;</code>
   */
  int getInitialWalkTime();

  /**
   * <code>optional int64 groupId = 19;</code>
   */
  boolean hasGroupId();
  /**
   * <code>optional int64 groupId = 19;</code>
   */
  long getGroupId();

  /**
   * <pre>
   *最大可集结数量/最大可增援数量
   * </pre>
   *
   * <code>optional int32 maxSolider = 20;</code>
   */
  boolean hasMaxSolider();
  /**
   * <pre>
   *最大可集结数量/最大可增援数量
   * </pre>
   *
   * <code>optional int32 maxSolider = 20;</code>
   */
  int getMaxSolider();

  /**
   * <code>repeated .client2server.MassForce forces = 21;</code>
   */
  java.util.List<pb4client.MassForce> 
      getForcesList();
  /**
   * <code>repeated .client2server.MassForce forces = 21;</code>
   */
  pb4client.MassForce getForces(int index);
  /**
   * <code>repeated .client2server.MassForce forces = 21;</code>
   */
  int getForcesCount();
  /**
   * <code>repeated .client2server.MassForce forces = 21;</code>
   */
  java.util.List<? extends pb4client.MassForceOrBuilder> 
      getForcesOrBuilderList();
  /**
   * <code>repeated .client2server.MassForce forces = 21;</code>
   */
  pb4client.MassForceOrBuilder getForcesOrBuilder(
      int index);
}
