// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

public interface RenameChatRoomAskRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.RenameChatRoomAskRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <code>repeated int64 members = 2;</code>
   */
  java.util.List<java.lang.Long> getMembersList();
  /**
   * <code>repeated int64 members = 2;</code>
   */
  int getMembersCount();
  /**
   * <code>repeated int64 members = 2;</code>
   */
  long getMembers(int index);

  /**
   * <code>int64 chatRoomId = 3;</code>
   */
  long getChatRoomId();

  /**
   * <code>string roomName = 4;</code>
   */
  java.lang.String getRoomName();
  /**
   * <code>string roomName = 4;</code>
   */
  com.google.protobuf.ByteString
      getRoomNameBytes();

  /**
   * <code>int32 unreadNum = 5;</code>
   */
  int getUnreadNum();

  /**
   * <code>repeated int32 iconProtoIds = 6;</code>
   */
  java.util.List<java.lang.Integer> getIconProtoIdsList();
  /**
   * <code>repeated int32 iconProtoIds = 6;</code>
   */
  int getIconProtoIdsCount();
  /**
   * <code>repeated int32 iconProtoIds = 6;</code>
   */
  int getIconProtoIds(int index);

  /**
   * <code>int32 memberNum = 7;</code>
   */
  int getMemberNum();

  /**
   * <code>int64 roomPlayerId = 8;</code>
   */
  long getRoomPlayerId();
}