// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

public interface DrawCasinoAskReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.DrawCasinoAskReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 rate = 1;</code>
   */
  int getRate();

  /**
   * <pre>
   * 服务器编号
   * </pre>
   *
   * <code>int32 areaNo = 2;</code>
   */
  int getAreaNo();

  /**
   * <pre>
   * 玩家名
   * </pre>
   *
   * <code>string playerName = 3;</code>
   */
  java.lang.String getPlayerName();
  /**
   * <pre>
   * 玩家名
   * </pre>
   *
   * <code>string playerName = 3;</code>
   */
  com.google.protobuf.ByteString
      getPlayerNameBytes();

  /**
   * <pre>
   * 玩家昵称
   * </pre>
   *
   * <code>string allianceShortName = 4;</code>
   */
  java.lang.String getAllianceShortName();
  /**
   * <pre>
   * 玩家昵称
   * </pre>
   *
   * <code>string allianceShortName = 4;</code>
   */
  com.google.protobuf.ByteString
      getAllianceShortNameBytes();

  /**
   * <pre>
   * 玩家id
   * </pre>
   *
   * <code>int64 myPlayerId = 5;</code>
   */
  long getMyPlayerId();
}
