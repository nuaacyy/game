// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

public interface InviteJoinChatAskReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.InviteJoinChatAskReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 roomId = 1;</code>
   */
  long getRoomId();

  /**
   * <code>int64 playerIdAdd = 2;</code>
   */
  long getPlayerIdAdd();

  /**
   * <pre>
   * 玩家名字
   * </pre>
   *
   * <code>string playerName = 3;</code>
   */
  java.lang.String getPlayerName();
  /**
   * <pre>
   * 玩家名字
   * </pre>
   *
   * <code>string playerName = 3;</code>
   */
  com.google.protobuf.ByteString
      getPlayerNameBytes();

  /**
   * <pre>
   * 头像ID
   * </pre>
   *
   * <code>int32 protoId = 4;</code>
   */
  int getProtoId();

  /**
   * <code>int32 vipLv = 5;</code>
   */
  int getVipLv();

  /**
   * <code>int32 areaNo = 6;</code>
   */
  int getAreaNo();

  /**
   * <code>string allianceShortName = 7;</code>
   */
  java.lang.String getAllianceShortName();
  /**
   * <code>string allianceShortName = 7;</code>
   */
  com.google.protobuf.ByteString
      getAllianceShortNameBytes();

  /**
   * <code>int64 fightValue = 8;</code>
   */
  long getFightValue();

  /**
   * <code>int32 castleLv = 9;</code>
   */
  int getCastleLv();

  /**
   * <code>string playerShortName = 10;</code>
   */
  java.lang.String getPlayerShortName();
  /**
   * <code>string playerShortName = 10;</code>
   */
  com.google.protobuf.ByteString
      getPlayerShortNameBytes();
}
