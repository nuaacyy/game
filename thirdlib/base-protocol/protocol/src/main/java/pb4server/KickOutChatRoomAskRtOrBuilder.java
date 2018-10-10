// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

public interface KickOutChatRoomAskRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.KickOutChatRoomAskRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <code>int64 chatRoomId = 2;</code>
   */
  long getChatRoomId();

  /**
   * <code>string roomName = 3;</code>
   */
  java.lang.String getRoomName();
  /**
   * <code>string roomName = 3;</code>
   */
  com.google.protobuf.ByteString
      getRoomNameBytes();

  /**
   * <code>int32 unreadNum = 4;</code>
   */
  int getUnreadNum();

  /**
   * <code>repeated int32 iconProtoIds = 5;</code>
   */
  java.util.List<java.lang.Integer> getIconProtoIdsList();
  /**
   * <code>repeated int32 iconProtoIds = 5;</code>
   */
  int getIconProtoIdsCount();
  /**
   * <code>repeated int32 iconProtoIds = 5;</code>
   */
  int getIconProtoIds(int index);

  /**
   * <code>int32 memberNum = 6;</code>
   */
  int getMemberNum();

  /**
   * <code>int64 roomPlayerId = 7;</code>
   */
  long getRoomPlayerId();

  /**
   * <code>repeated int64 memberIds = 8;</code>
   */
  java.util.List<java.lang.Long> getMemberIdsList();
  /**
   * <code>repeated int64 memberIds = 8;</code>
   */
  int getMemberIdsCount();
  /**
   * <code>repeated int64 memberIds = 8;</code>
   */
  long getMemberIds(int index);

  /**
   * <code>int64 lastSendTime = 9;</code>
   */
  long getLastSendTime();
}
