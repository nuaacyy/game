// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

public interface MatePlayerOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.MatePlayer)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 myPlayerId = 1;</code>
   */
  long getMyPlayerId();

  /**
   * <code>int32 photoProtoId = 2;</code>
   */
  int getPhotoProtoId();

  /**
   * <code>string name = 3;</code>
   */
  java.lang.String getName();
  /**
   * <code>string name = 3;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>int32 areaNo = 4;</code>
   */
  int getAreaNo();

  /**
   * <code>int32 vipLv = 5;</code>
   */
  int getVipLv();

  /**
   * <code>string allianceShortName = 6;</code>
   */
  java.lang.String getAllianceShortName();
  /**
   * <code>string allianceShortName = 6;</code>
   */
  com.google.protobuf.ByteString
      getAllianceShortNameBytes();

  /**
   * <code>int32 castleLv = 7;</code>
   */
  int getCastleLv();

  /**
   * <pre>
   * 玩家昵称
   * </pre>
   *
   * <code>string shortName = 8;</code>
   */
  java.lang.String getShortName();
  /**
   * <pre>
   * 玩家昵称
   * </pre>
   *
   * <code>string shortName = 8;</code>
   */
  com.google.protobuf.ByteString
      getShortNameBytes();
}
