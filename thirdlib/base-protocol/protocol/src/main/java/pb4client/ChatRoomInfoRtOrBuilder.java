// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface ChatRoomInfoRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.ChatRoomInfoRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int32 rt = 1;</code>
   */
  boolean hasRt();
  /**
   * <code>required int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <code>repeated .client2server.ChatRoomGeneral chatRooms = 2;</code>
   */
  java.util.List<pb4client.ChatRoomGeneral> 
      getChatRoomsList();
  /**
   * <code>repeated .client2server.ChatRoomGeneral chatRooms = 2;</code>
   */
  pb4client.ChatRoomGeneral getChatRooms(int index);
  /**
   * <code>repeated .client2server.ChatRoomGeneral chatRooms = 2;</code>
   */
  int getChatRoomsCount();
  /**
   * <code>repeated .client2server.ChatRoomGeneral chatRooms = 2;</code>
   */
  java.util.List<? extends pb4client.ChatRoomGeneralOrBuilder> 
      getChatRoomsOrBuilderList();
  /**
   * <code>repeated .client2server.ChatRoomGeneral chatRooms = 2;</code>
   */
  pb4client.ChatRoomGeneralOrBuilder getChatRoomsOrBuilder(
      int index);
}
