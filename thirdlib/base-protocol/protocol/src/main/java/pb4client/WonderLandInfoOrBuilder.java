// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface WonderLandInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.WonderLandInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *奇观状态 1、和平 2、争夺 3、冒烟 4、冒火
   * </pre>
   *
   * <code>optional int32 wonderStatus = 1;</code>
   */
  boolean hasWonderStatus();
  /**
   * <pre>
   *奇观状态 1、和平 2、争夺 3、冒烟 4、冒火
   * </pre>
   *
   * <code>optional int32 wonderStatus = 1;</code>
   */
  int getWonderStatus();

  /**
   * <pre>
   *奇观冒烟冒火状态结束时间
   * </pre>
   *
   * <code>optional int32 statusOverTime = 2;</code>
   */
  boolean hasStatusOverTime();
  /**
   * <pre>
   *奇观冒烟冒火状态结束时间
   * </pre>
   *
   * <code>optional int32 statusOverTime = 2;</code>
   */
  int getStatusOverTime();

  /**
   * <pre>
   * 归属的玩家ID
   * </pre>
   *
   * <code>optional int64 playerId = 3;</code>
   */
  boolean hasPlayerId();
  /**
   * <pre>
   * 归属的玩家ID
   * </pre>
   *
   * <code>optional int64 playerId = 3;</code>
   */
  long getPlayerId();

  /**
   * <pre>
   * 城池归属 玩家名
   * </pre>
   *
   * <code>optional string playerName = 4;</code>
   */
  boolean hasPlayerName();
  /**
   * <pre>
   * 城池归属 玩家名
   * </pre>
   *
   * <code>optional string playerName = 4;</code>
   */
  java.lang.String getPlayerName();
  /**
   * <pre>
   * 城池归属 玩家名
   * </pre>
   *
   * <code>optional string playerName = 4;</code>
   */
  com.google.protobuf.ByteString
      getPlayerNameBytes();

  /**
   * <pre>
   * 联盟ID
   * </pre>
   *
   * <code>optional int64 allianceId = 5;</code>
   */
  boolean hasAllianceId();
  /**
   * <pre>
   * 联盟ID
   * </pre>
   *
   * <code>optional int64 allianceId = 5;</code>
   */
  long getAllianceId();

  /**
   * <pre>
   * 联盟名称
   * </pre>
   *
   * <code>optional string allianceName = 6;</code>
   */
  boolean hasAllianceName();
  /**
   * <pre>
   * 联盟名称
   * </pre>
   *
   * <code>optional string allianceName = 6;</code>
   */
  java.lang.String getAllianceName();
  /**
   * <pre>
   * 联盟名称
   * </pre>
   *
   * <code>optional string allianceName = 6;</code>
   */
  com.google.protobuf.ByteString
      getAllianceNameBytes();

  /**
   * <pre>
   * 联盟简称
   * </pre>
   *
   * <code>optional string allianceShortName = 7;</code>
   */
  boolean hasAllianceShortName();
  /**
   * <pre>
   * 联盟简称
   * </pre>
   *
   * <code>optional string allianceShortName = 7;</code>
   */
  java.lang.String getAllianceShortName();
  /**
   * <pre>
   * 联盟简称
   * </pre>
   *
   * <code>optional string allianceShortName = 7;</code>
   */
  com.google.protobuf.ByteString
      getAllianceShortNameBytes();

  /**
   * <pre>
   * (客户端使用)
   * </pre>
   *
   * <code>optional int32 tileId = 100;</code>
   */
  boolean hasTileId();
  /**
   * <pre>
   * (客户端使用)
   * </pre>
   *
   * <code>optional int32 tileId = 100;</code>
   */
  int getTileId();
}
