// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface AllianceCountInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.AllianceCountInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *查询期间：0-今天；1-昨天
   * </pre>
   *
   * <code>required int32 day = 1;</code>
   */
  boolean hasDay();
  /**
   * <pre>
   *查询期间：0-今天；1-昨天
   * </pre>
   *
   * <code>required int32 day = 1;</code>
   */
  int getDay();

  /**
   * <pre>
   *玩家ID
   * </pre>
   *
   * <code>required int64 playerId = 2;</code>
   */
  boolean hasPlayerId();
  /**
   * <pre>
   *玩家ID
   * </pre>
   *
   * <code>required int64 playerId = 2;</code>
   */
  long getPlayerId();

  /**
   * <pre>
   *玩家名称
   * </pre>
   *
   * <code>required string playerName = 3;</code>
   */
  boolean hasPlayerName();
  /**
   * <pre>
   *玩家名称
   * </pre>
   *
   * <code>required string playerName = 3;</code>
   */
  java.lang.String getPlayerName();
  /**
   * <pre>
   *玩家名称
   * </pre>
   *
   * <code>required string playerName = 3;</code>
   */
  com.google.protobuf.ByteString
      getPlayerNameBytes();

  /**
   * <pre>
   *贡献
   * </pre>
   *
   * <code>required int32 contributions = 4;</code>
   */
  boolean hasContributions();
  /**
   * <pre>
   *贡献
   * </pre>
   *
   * <code>required int32 contributions = 4;</code>
   */
  int getContributions();

  /**
   * <pre>
   *功勋
   * </pre>
   *
   * <code>required int32 meritorious = 5;</code>
   */
  boolean hasMeritorious();
  /**
   * <pre>
   *功勋
   * </pre>
   *
   * <code>required int32 meritorious = 5;</code>
   */
  int getMeritorious();

  /**
   * <pre>
   *拆地值
   * </pre>
   *
   * <code>required int32 landDurables = 6;</code>
   */
  boolean hasLandDurables();
  /**
   * <pre>
   *拆地值
   * </pre>
   *
   * <code>required int32 landDurables = 6;</code>
   */
  int getLandDurables();

  /**
   * <pre>
   *攻城值
   * </pre>
   *
   * <code>required int32 cityDurables = 7;</code>
   */
  boolean hasCityDurables();
  /**
   * <pre>
   *攻城值
   * </pre>
   *
   * <code>required int32 cityDurables = 7;</code>
   */
  int getCityDurables();
}