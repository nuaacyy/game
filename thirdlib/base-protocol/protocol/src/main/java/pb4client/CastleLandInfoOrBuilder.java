// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface CastleLandInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.CastleLandInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 归属的玩家ID
   * </pre>
   *
   * <code>optional int64 playerId = 1;</code>
   */
  boolean hasPlayerId();
  /**
   * <pre>
   * 归属的玩家ID
   * </pre>
   *
   * <code>optional int64 playerId = 1;</code>
   */
  long getPlayerId();

  /**
   * <pre>
   * 城池归属 玩家名
   * </pre>
   *
   * <code>optional string playerName = 2;</code>
   */
  boolean hasPlayerName();
  /**
   * <pre>
   * 城池归属 玩家名
   * </pre>
   *
   * <code>optional string playerName = 2;</code>
   */
  java.lang.String getPlayerName();
  /**
   * <pre>
   * 城池归属 玩家名
   * </pre>
   *
   * <code>optional string playerName = 2;</code>
   */
  com.google.protobuf.ByteString
      getPlayerNameBytes();

  /**
   * <pre>
   * 联盟ID
   * </pre>
   *
   * <code>optional int64 allianceId = 3;</code>
   */
  boolean hasAllianceId();
  /**
   * <pre>
   * 联盟ID
   * </pre>
   *
   * <code>optional int64 allianceId = 3;</code>
   */
  long getAllianceId();

  /**
   * <pre>
   * 联盟名称
   * </pre>
   *
   * <code>optional string allianceName = 4;</code>
   */
  boolean hasAllianceName();
  /**
   * <pre>
   * 联盟名称
   * </pre>
   *
   * <code>optional string allianceName = 4;</code>
   */
  java.lang.String getAllianceName();
  /**
   * <pre>
   * 联盟名称
   * </pre>
   *
   * <code>optional string allianceName = 4;</code>
   */
  com.google.protobuf.ByteString
      getAllianceNameBytes();

  /**
   * <pre>
   * 联盟简称
   * </pre>
   *
   * <code>optional string allianceShortName = 5;</code>
   */
  boolean hasAllianceShortName();
  /**
   * <pre>
   * 联盟简称
   * </pre>
   *
   * <code>optional string allianceShortName = 5;</code>
   */
  java.lang.String getAllianceShortName();
  /**
   * <pre>
   * 联盟简称
   * </pre>
   *
   * <code>optional string allianceShortName = 5;</code>
   */
  com.google.protobuf.ByteString
      getAllianceShortNameBytes();

  /**
   * <pre>
   * 等级
   * </pre>
   *
   * <code>optional int32 lv = 6;</code>
   */
  boolean hasLv();
  /**
   * <pre>
   * 等级
   * </pre>
   *
   * <code>optional int32 lv = 6;</code>
   */
  int getLv();

  /**
   * <pre>
   *当前官职
   * </pre>
   *
   * <code>optional int32 currentPos = 7;</code>
   */
  boolean hasCurrentPos();
  /**
   * <pre>
   *当前官职
   * </pre>
   *
   * <code>optional int32 currentPos = 7;</code>
   */
  int getCurrentPos();

  /**
   * <pre>
   *城堡状态 1、和平  3、冒烟 4、冒火
   * </pre>
   *
   * <code>optional int32 castleStatus = 8;</code>
   */
  boolean hasCastleStatus();
  /**
   * <pre>
   *城堡状态 1、和平  3、冒烟 4、冒火
   * </pre>
   *
   * <code>optional int32 castleStatus = 8;</code>
   */
  int getCastleStatus();

  /**
   * <pre>
   *城堡保护罩结束时间
   * </pre>
   *
   * <code>optional int32 coverOverTime = 9;</code>
   */
  boolean hasCoverOverTime();
  /**
   * <pre>
   *城堡保护罩结束时间
   * </pre>
   *
   * <code>optional int32 coverOverTime = 9;</code>
   */
  int getCoverOverTime();

  /**
   * <pre>
   *监狱信息
   * </pre>
   *
   * <code>repeated .client2server.CellPrisonInfo prisonInfos = 10;</code>
   */
  java.util.List<pb4client.CellPrisonInfo> 
      getPrisonInfosList();
  /**
   * <pre>
   *监狱信息
   * </pre>
   *
   * <code>repeated .client2server.CellPrisonInfo prisonInfos = 10;</code>
   */
  pb4client.CellPrisonInfo getPrisonInfos(int index);
  /**
   * <pre>
   *监狱信息
   * </pre>
   *
   * <code>repeated .client2server.CellPrisonInfo prisonInfos = 10;</code>
   */
  int getPrisonInfosCount();
  /**
   * <pre>
   *监狱信息
   * </pre>
   *
   * <code>repeated .client2server.CellPrisonInfo prisonInfos = 10;</code>
   */
  java.util.List<? extends pb4client.CellPrisonInfoOrBuilder> 
      getPrisonInfosOrBuilderList();
  /**
   * <pre>
   *监狱信息
   * </pre>
   *
   * <code>repeated .client2server.CellPrisonInfo prisonInfos = 10;</code>
   */
  pb4client.CellPrisonInfoOrBuilder getPrisonInfosOrBuilder(
      int index);

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