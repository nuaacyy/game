// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface MassPlayerOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.MassPlayer)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *目标玩家Id
   * </pre>
   *
   * <code>optional int64 playerId = 9;</code>
   */
  boolean hasPlayerId();
  /**
   * <pre>
   *目标玩家Id
   * </pre>
   *
   * <code>optional int64 playerId = 9;</code>
   */
  long getPlayerId();

  /**
   * <pre>
   *目标玩家名称
   * </pre>
   *
   * <code>optional string name = 10;</code>
   */
  boolean hasName();
  /**
   * <pre>
   *目标玩家名称
   * </pre>
   *
   * <code>optional string name = 10;</code>
   */
  java.lang.String getName();
  /**
   * <pre>
   *目标玩家名称
   * </pre>
   *
   * <code>optional string name = 10;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <pre>
   *目标玩家头像
   * </pre>
   *
   * <code>optional int32 photo = 11;</code>
   */
  boolean hasPhoto();
  /**
   * <pre>
   *目标玩家头像
   * </pre>
   *
   * <code>optional int32 photo = 11;</code>
   */
  int getPhoto();

  /**
   * <pre>
   *目标玩家联盟Id
   * </pre>
   *
   * <code>optional int64 allianceId = 12;</code>
   */
  boolean hasAllianceId();
  /**
   * <pre>
   *目标玩家联盟Id
   * </pre>
   *
   * <code>optional int64 allianceId = 12;</code>
   */
  long getAllianceId();

  /**
   * <pre>
   *目标联盟名称
   * </pre>
   *
   * <code>optional string allianceName = 13;</code>
   */
  boolean hasAllianceName();
  /**
   * <pre>
   *目标联盟名称
   * </pre>
   *
   * <code>optional string allianceName = 13;</code>
   */
  java.lang.String getAllianceName();
  /**
   * <pre>
   *目标联盟名称
   * </pre>
   *
   * <code>optional string allianceName = 13;</code>
   */
  com.google.protobuf.ByteString
      getAllianceNameBytes();

  /**
   * <pre>
   *目标联盟名称
   * </pre>
   *
   * <code>optional string allianceShortName = 14;</code>
   */
  boolean hasAllianceShortName();
  /**
   * <pre>
   *目标联盟名称
   * </pre>
   *
   * <code>optional string allianceShortName = 14;</code>
   */
  java.lang.String getAllianceShortName();
  /**
   * <pre>
   *目标联盟名称
   * </pre>
   *
   * <code>optional string allianceShortName = 14;</code>
   */
  com.google.protobuf.ByteString
      getAllianceShortNameBytes();
}
