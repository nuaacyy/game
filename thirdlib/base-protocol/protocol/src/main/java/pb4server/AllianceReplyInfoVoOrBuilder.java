// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

public interface AllianceReplyInfoVoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.AllianceReplyInfoVo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *回复的回复ID
   * </pre>
   *
   * <code>int64 replyId = 1;</code>
   */
  long getReplyId();

  /**
   * <pre>
   *回复的玩家ID
   * </pre>
   *
   * <code>int64 playerId = 2;</code>
   */
  long getPlayerId();

  /**
   * <pre>
   *回复的玩家名称
   * </pre>
   *
   * <code>string playerName = 3;</code>
   */
  java.lang.String getPlayerName();
  /**
   * <pre>
   *回复的玩家名称
   * </pre>
   *
   * <code>string playerName = 3;</code>
   */
  com.google.protobuf.ByteString
      getPlayerNameBytes();

  /**
   * <pre>
   *回复的玩家昵称
   * </pre>
   *
   * <code>string playerShortName = 4;</code>
   */
  java.lang.String getPlayerShortName();
  /**
   * <pre>
   *回复的玩家昵称
   * </pre>
   *
   * <code>string playerShortName = 4;</code>
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
   * <code>int32 photoProtoId = 6;</code>
   */
  int getPhotoProtoId();

  /**
   * <pre>
   *回复内容
   * </pre>
   *
   * <code>string message = 7;</code>
   */
  java.lang.String getMessage();
  /**
   * <pre>
   *回复内容
   * </pre>
   *
   * <code>string message = 7;</code>
   */
  com.google.protobuf.ByteString
      getMessageBytes();

  /**
   * <pre>
   *回复时间
   * </pre>
   *
   * <code>int32 replyAt = 8;</code>
   */
  int getReplyAt();

  /**
   * <pre>
   *vip等级
   * </pre>
   *
   * <code>int32 vipLv = 9;</code>
   */
  int getVipLv();

  /**
   * <pre>
   * 官职
   * </pre>
   *
   * <code>int32 curentPos = 10;</code>
   */
  int getCurentPos();
}
