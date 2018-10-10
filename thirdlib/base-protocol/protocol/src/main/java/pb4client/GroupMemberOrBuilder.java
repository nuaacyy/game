// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface GroupMemberOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.GroupMember)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 玩家id
   * </pre>
   *
   * <code>required int64 playerId = 1;</code>
   */
  boolean hasPlayerId();
  /**
   * <pre>
   * 玩家id
   * </pre>
   *
   * <code>required int64 playerId = 1;</code>
   */
  long getPlayerId();

  /**
   * <pre>
   * 玩家名字
   * </pre>
   *
   * <code>required string playerName = 2;</code>
   */
  boolean hasPlayerName();
  /**
   * <pre>
   * 玩家名字
   * </pre>
   *
   * <code>required string playerName = 2;</code>
   */
  java.lang.String getPlayerName();
  /**
   * <pre>
   * 玩家名字
   * </pre>
   *
   * <code>required string playerName = 2;</code>
   */
  com.google.protobuf.ByteString
      getPlayerNameBytes();

  /**
   * <pre>
   * 头像ID
   * </pre>
   *
   * <code>required int32 protoId = 3;</code>
   */
  boolean hasProtoId();
  /**
   * <pre>
   * 头像ID
   * </pre>
   *
   * <code>required int32 protoId = 3;</code>
   */
  int getProtoId();

  /**
   * <pre>
   * 
   * </pre>
   *
   * <code>required int32 vipLv = 4;</code>
   */
  boolean hasVipLv();
  /**
   * <pre>
   * 
   * </pre>
   *
   * <code>required int32 vipLv = 4;</code>
   */
  int getVipLv();

  /**
   * <pre>
   * 
   * </pre>
   *
   * <code>required int32 areaNo = 5;</code>
   */
  boolean hasAreaNo();
  /**
   * <pre>
   * 
   * </pre>
   *
   * <code>required int32 areaNo = 5;</code>
   */
  int getAreaNo();

  /**
   * <pre>
   * 
   * </pre>
   *
   * <code>required string allianceShortName = 6;</code>
   */
  boolean hasAllianceShortName();
  /**
   * <pre>
   * 
   * </pre>
   *
   * <code>required string allianceShortName = 6;</code>
   */
  java.lang.String getAllianceShortName();
  /**
   * <pre>
   * 
   * </pre>
   *
   * <code>required string allianceShortName = 6;</code>
   */
  com.google.protobuf.ByteString
      getAllianceShortNameBytes();

  /**
   * <pre>
   * 
   * </pre>
   *
   * <code>optional int64 fightValue = 7;</code>
   */
  boolean hasFightValue();
  /**
   * <pre>
   * 
   * </pre>
   *
   * <code>optional int64 fightValue = 7;</code>
   */
  long getFightValue();

  /**
   * <pre>
   * </pre>
   *
   * <code>required int32 castleLv = 8;</code>
   */
  boolean hasCastleLv();
  /**
   * <pre>
   * </pre>
   *
   * <code>required int32 castleLv = 8;</code>
   */
  int getCastleLv();

  /**
   * <pre>
   * 玩家昵称
   * </pre>
   *
   * <code>optional string playerShortName = 9;</code>
   */
  boolean hasPlayerShortName();
  /**
   * <pre>
   * 玩家昵称
   * </pre>
   *
   * <code>optional string playerShortName = 9;</code>
   */
  java.lang.String getPlayerShortName();
  /**
   * <pre>
   * 玩家昵称
   * </pre>
   *
   * <code>optional string playerShortName = 9;</code>
   */
  com.google.protobuf.ByteString
      getPlayerShortNameBytes();
}
