// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

public interface GetChatInfoRtVoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.GetChatInfoRtVo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <code>repeated .pb4server.ChatInfoVo chatInfos = 2;</code>
   */
  java.util.List<pb4server.ChatInfoVo> 
      getChatInfosList();
  /**
   * <code>repeated .pb4server.ChatInfoVo chatInfos = 2;</code>
   */
  pb4server.ChatInfoVo getChatInfos(int index);
  /**
   * <code>repeated .pb4server.ChatInfoVo chatInfos = 2;</code>
   */
  int getChatInfosCount();
  /**
   * <code>repeated .pb4server.ChatInfoVo chatInfos = 2;</code>
   */
  java.util.List<? extends pb4server.ChatInfoVoOrBuilder> 
      getChatInfosOrBuilderList();
  /**
   * <code>repeated .pb4server.ChatInfoVo chatInfos = 2;</code>
   */
  pb4server.ChatInfoVoOrBuilder getChatInfosOrBuilder(
      int index);

  /**
   * <code>int32 chatType = 3;</code>
   */
  int getChatType();
}
