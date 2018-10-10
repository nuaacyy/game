// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface AllianceReplyInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.AllianceReplyInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *回复的回复ID
   * </pre>
   *
   * <code>required int64 replyId = 1;</code>
   */
  boolean hasReplyId();
  /**
   * <pre>
   *回复的回复ID
   * </pre>
   *
   * <code>required int64 replyId = 1;</code>
   */
  long getReplyId();

  /**
   * <pre>
   *回复的玩家ID
   * </pre>
   *
   * <code>required int64 playerId = 2;</code>
   */
  boolean hasPlayerId();
  /**
   * <pre>
   *回复的玩家ID
   * </pre>
   *
   * <code>required int64 playerId = 2;</code>
   */
  long getPlayerId();

  /**
   * <pre>
   *回复的玩家名称
   * </pre>
   *
   * <code>required string playerName = 3;</code>
   */
  boolean hasPlayerName();
  /**
   * <pre>
   *回复的玩家名称
   * </pre>
   *
   * <code>required string playerName = 3;</code>
   */
  java.lang.String getPlayerName();
  /**
   * <pre>
   *回复的玩家名称
   * </pre>
   *
   * <code>required string playerName = 3;</code>
   */
  com.google.protobuf.ByteString
      getPlayerNameBytes();

  /**
   * <pre>
   *回复的玩家昵称
   * </pre>
   *
   * <code>required string playerShortName = 4;</code>
   */
  boolean hasPlayerShortName();
  /**
   * <pre>
   *回复的玩家昵称
   * </pre>
   *
   * <code>required string playerShortName = 4;</code>
   */
  java.lang.String getPlayerShortName();
  /**
   * <pre>
   *回复的玩家昵称
   * </pre>
   *
   * <code>required string playerShortName = 4;</code>
   */
  com.google.protobuf.ByteString
      getPlayerShortNameBytes();

  /**
   * <pre>
   *回复者的职位
   * </pre>
   *
   * <code>repeated int32 positions = 5;</code>
   */
  java.util.List<java.lang.Integer> getPositionsList();
  /**
   * <pre>
   *回复者的职位
   * </pre>
   *
   * <code>repeated int32 positions = 5;</code>
   */
  int getPositionsCount();
  /**
   * <pre>
   *回复者的职位
   * </pre>
   *
   * <code>repeated int32 positions = 5;</code>
   */
  int getPositions(int index);

  /**
   * <pre>
   *回复者的头像模版
   * </pre>
   *
   * <code>required int32 photoProtoId = 6;</code>
   */
  boolean hasPhotoProtoId();
  /**
   * <pre>
   *回复者的头像模版
   * </pre>
   *
   * <code>required int32 photoProtoId = 6;</code>
   */
  int getPhotoProtoId();

  /**
   * <pre>
   *回复内容
   * </pre>
   *
   * <code>required string message = 7;</code>
   */
  boolean hasMessage();
  /**
   * <pre>
   *回复内容
   * </pre>
   *
   * <code>required string message = 7;</code>
   */
  java.lang.String getMessage();
  /**
   * <pre>
   *回复内容
   * </pre>
   *
   * <code>required string message = 7;</code>
   */
  com.google.protobuf.ByteString
      getMessageBytes();

  /**
   * <pre>
   *回复时间
   * </pre>
   *
   * <code>required int32 replyAt = 8;</code>
   */
  boolean hasReplyAt();
  /**
   * <pre>
   *回复时间
   * </pre>
   *
   * <code>required int32 replyAt = 8;</code>
   */
  int getReplyAt();

  /**
   * <pre>
   *vip等级
   * </pre>
   *
   * <code>required int32 vipLv = 9;</code>
   */
  boolean hasVipLv();
  /**
   * <pre>
   *vip等级
   * </pre>
   *
   * <code>required int32 vipLv = 9;</code>
   */
  int getVipLv();

  /**
   * <pre>
   * 官职	
   * </pre>
   *
   * <code>required int32 curentPos = 10;</code>
   */
  boolean hasCurentPos();
  /**
   * <pre>
   * 官职	
   * </pre>
   *
   * <code>required int32 curentPos = 10;</code>
   */
  int getCurentPos();
}
