// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface AllianceExchangeDetailInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.AllianceExchangeDetailInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *玩家ID
   * </pre>
   *
   * <code>required int64 playerId = 1;</code>
   */
  boolean hasPlayerId();
  /**
   * <pre>
   *玩家ID
   * </pre>
   *
   * <code>required int64 playerId = 1;</code>
   */
  long getPlayerId();

  /**
   * <pre>
   *玩家名字
   * </pre>
   *
   * <code>required string playerName = 2;</code>
   */
  boolean hasPlayerName();
  /**
   * <pre>
   *玩家名字
   * </pre>
   *
   * <code>required string playerName = 2;</code>
   */
  java.lang.String getPlayerName();
  /**
   * <pre>
   *玩家名字
   * </pre>
   *
   * <code>required string playerName = 2;</code>
   */
  com.google.protobuf.ByteString
      getPlayerNameBytes();

  /**
   * <pre>
   *捐卡数量
   * </pre>
   *
   * <code>required int32 qty = 3;</code>
   */
  boolean hasQty();
  /**
   * <pre>
   *捐卡数量
   * </pre>
   *
   * <code>required int32 qty = 3;</code>
   */
  int getQty();
}