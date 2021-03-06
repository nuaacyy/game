// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface NewForceInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.NewForceInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int64 forceId = 1;</code>
   */
  boolean hasForceId();
  /**
   * <code>required int64 forceId = 1;</code>
   */
  long getForceId();

  /**
   * <pre>
   *部队中格子信息
   * </pre>
   *
   * <code>repeated .client2server.AddInForce addInForce = 2;</code>
   */
  java.util.List<pb4client.AddInForce> 
      getAddInForceList();
  /**
   * <pre>
   *部队中格子信息
   * </pre>
   *
   * <code>repeated .client2server.AddInForce addInForce = 2;</code>
   */
  pb4client.AddInForce getAddInForce(int index);
  /**
   * <pre>
   *部队中格子信息
   * </pre>
   *
   * <code>repeated .client2server.AddInForce addInForce = 2;</code>
   */
  int getAddInForceCount();
  /**
   * <pre>
   *部队中格子信息
   * </pre>
   *
   * <code>repeated .client2server.AddInForce addInForce = 2;</code>
   */
  java.util.List<? extends pb4client.AddInForceOrBuilder> 
      getAddInForceOrBuilderList();
  /**
   * <pre>
   *部队中格子信息
   * </pre>
   *
   * <code>repeated .client2server.AddInForce addInForce = 2;</code>
   */
  pb4client.AddInForceOrBuilder getAddInForceOrBuilder(
      int index);

  /**
   * <code>required int32 forceState = 3;</code>
   */
  boolean hasForceState();
  /**
   * <code>required int32 forceState = 3;</code>
   */
  int getForceState();

  /**
   * <pre>
   *练兵,对峙,屯田等结束时间
   * </pre>
   *
   * <code>required int32 leaveTime = 4;</code>
   */
  boolean hasLeaveTime();
  /**
   * <pre>
   *练兵,对峙,屯田等结束时间
   * </pre>
   *
   * <code>required int32 leaveTime = 4;</code>
   */
  int getLeaveTime();

  /**
   * <pre>
   *当前X
   * </pre>
   *
   * <code>required int32 nowX = 5;</code>
   */
  boolean hasNowX();
  /**
   * <pre>
   *当前X
   * </pre>
   *
   * <code>required int32 nowX = 5;</code>
   */
  int getNowX();

  /**
   * <pre>
   *当前Y
   * </pre>
   *
   * <code>required int32 nowY = 6;</code>
   */
  boolean hasNowY();
  /**
   * <pre>
   *当前Y
   * </pre>
   *
   * <code>required int32 nowY = 6;</code>
   */
  int getNowY();

  /**
   * <pre>
   *一级归属-主城分城
   * </pre>
   *
   * <code>required int64 bigCity = 7;</code>
   */
  boolean hasBigCity();
  /**
   * <pre>
   *一级归属-主城分城
   * </pre>
   *
   * <code>required int64 bigCity = 7;</code>
   */
  long getBigCity();

  /**
   * <pre>
   *二级归属 -要塞军营
   * </pre>
   *
   * <code>required int64 smallCity = 8;</code>
   */
  boolean hasSmallCity();
  /**
   * <pre>
   *二级归属 -要塞军营
   * </pre>
   *
   * <code>required int64 smallCity = 8;</code>
   */
  long getSmallCity();

  /**
   * <pre>
   *部队在城中的位置编号  1-5
   * </pre>
   *
   * <code>required int32 forceAddId = 9;</code>
   */
  boolean hasForceAddId();
  /**
   * <pre>
   *部队在城中的位置编号  1-5
   * </pre>
   *
   * <code>required int32 forceAddId = 9;</code>
   */
  int getForceAddId();

  /**
   * <pre>
   *隶属军团ID
   * </pre>
   *
   * <code>required int64 inArmyGroup = 10;</code>
   */
  boolean hasInArmyGroup();
  /**
   * <pre>
   *隶属军团ID
   * </pre>
   *
   * <code>required int64 inArmyGroup = 10;</code>
   */
  long getInArmyGroup();

  /**
   * <pre>
   *在军团中的位置编号
   * </pre>
   *
   * <code>required int32 inArmyGroupAddress = 11;</code>
   */
  boolean hasInArmyGroupAddress();
  /**
   * <pre>
   *在军团中的位置编号
   * </pre>
   *
   * <code>required int32 inArmyGroupAddress = 11;</code>
   */
  int getInArmyGroupAddress();

  /**
   * <pre>
   * 当前第几次操作
   * </pre>
   *
   * <code>required int32 opNum = 12;</code>
   */
  boolean hasOpNum();
  /**
   * <pre>
   * 当前第几次操作
   * </pre>
   *
   * <code>required int32 opNum = 12;</code>
   */
  int getOpNum();

  /**
   * <pre>
   * 操作的最大次数
   * </pre>
   *
   * <code>required int32 opMaxNum = 13;</code>
   */
  boolean hasOpMaxNum();
  /**
   * <pre>
   * 操作的最大次数
   * </pre>
   *
   * <code>required int32 opMaxNum = 13;</code>
   */
  int getOpMaxNum();
}
