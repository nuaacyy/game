// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface AllianceQueryReqListRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.AllianceQueryReqListRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  boolean hasRt();
  /**
   * <pre>
   *返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <pre>
   * 可以直接加入的战斗力
   * </pre>
   *
   * <code>optional int64 canAddPower = 2;</code>
   */
  boolean hasCanAddPower();
  /**
   * <pre>
   * 可以直接加入的战斗力
   * </pre>
   *
   * <code>optional int64 canAddPower = 2;</code>
   */
  long getCanAddPower();

  /**
   * <pre>
   * 可申请的最低战斗力
   * </pre>
   *
   * <code>optional int64 powerLimit = 3;</code>
   */
  boolean hasPowerLimit();
  /**
   * <pre>
   * 可申请的最低战斗力
   * </pre>
   *
   * <code>optional int64 powerLimit = 3;</code>
   */
  long getPowerLimit();

  /**
   * <pre>
   *申请加入联盟的玩家列表
   * </pre>
   *
   * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
   */
  java.util.List<pb4client.AllianceQueryReqListInfo> 
      getPlayersList();
  /**
   * <pre>
   *申请加入联盟的玩家列表
   * </pre>
   *
   * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
   */
  pb4client.AllianceQueryReqListInfo getPlayers(int index);
  /**
   * <pre>
   *申请加入联盟的玩家列表
   * </pre>
   *
   * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
   */
  int getPlayersCount();
  /**
   * <pre>
   *申请加入联盟的玩家列表
   * </pre>
   *
   * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
   */
  java.util.List<? extends pb4client.AllianceQueryReqListInfoOrBuilder> 
      getPlayersOrBuilderList();
  /**
   * <pre>
   *申请加入联盟的玩家列表
   * </pre>
   *
   * <code>repeated .client2server.AllianceQueryReqListInfo players = 4;</code>
   */
  pb4client.AllianceQueryReqListInfoOrBuilder getPlayersOrBuilder(
      int index);
}
