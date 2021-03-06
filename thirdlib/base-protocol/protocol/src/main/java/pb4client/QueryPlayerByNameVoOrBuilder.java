// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface QueryPlayerByNameVoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.QueryPlayerByNameVo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *好友信息
   * </pre>
   *
   * <code>required int64 playerId = 1;</code>
   */
  boolean hasPlayerId();
  /**
   * <pre>
   *好友信息
   * </pre>
   *
   * <code>required int64 playerId = 1;</code>
   */
  long getPlayerId();

  /**
   * <pre>
   *群组信息
   * </pre>
   *
   * <code>required string playerName = 2;</code>
   */
  boolean hasPlayerName();
  /**
   * <pre>
   *群组信息
   * </pre>
   *
   * <code>required string playerName = 2;</code>
   */
  java.lang.String getPlayerName();
  /**
   * <pre>
   *群组信息
   * </pre>
   *
   * <code>required string playerName = 2;</code>
   */
  com.google.protobuf.ByteString
      getPlayerNameBytes();

  /**
   * <pre>
   * 头像图标id
   * </pre>
   *
   * <code>required int32 photoId = 3;</code>
   */
  boolean hasPhotoId();
  /**
   * <pre>
   * 头像图标id
   * </pre>
   *
   * <code>required int32 photoId = 3;</code>
   */
  int getPhotoId();

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
   *联盟简称
   * </pre>
   *
   * <code>required string allianceShortName = 6;</code>
   */
  boolean hasAllianceShortName();
  /**
   * <pre>
   *联盟简称
   * </pre>
   *
   * <code>required string allianceShortName = 6;</code>
   */
  java.lang.String getAllianceShortName();
  /**
   * <pre>
   *联盟简称
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
   * <code>required int32 castleLv = 7;</code>
   */
  boolean hasCastleLv();
  /**
   * <pre>
   * 
   * </pre>
   *
   * <code>required int32 castleLv = 7;</code>
   */
  int getCastleLv();

  /**
   * <pre>
   * 
   * </pre>
   *
   * <code>required string shortName = 8;</code>
   */
  boolean hasShortName();
  /**
   * <pre>
   * 
   * </pre>
   *
   * <code>required string shortName = 8;</code>
   */
  java.lang.String getShortName();
  /**
   * <pre>
   * 
   * </pre>
   *
   * <code>required string shortName = 8;</code>
   */
  com.google.protobuf.ByteString
      getShortNameBytes();
}
