// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface AllianceMessageRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.AllianceMessageRt)
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
   * <code>repeated .client2server.AllianceChatMessage allianceChatMessage = 2;</code>
   */
  java.util.List<pb4client.AllianceChatMessage> 
      getAllianceChatMessageList();
  /**
   * <code>repeated .client2server.AllianceChatMessage allianceChatMessage = 2;</code>
   */
  pb4client.AllianceChatMessage getAllianceChatMessage(int index);
  /**
   * <code>repeated .client2server.AllianceChatMessage allianceChatMessage = 2;</code>
   */
  int getAllianceChatMessageCount();
  /**
   * <code>repeated .client2server.AllianceChatMessage allianceChatMessage = 2;</code>
   */
  java.util.List<? extends pb4client.AllianceChatMessageOrBuilder> 
      getAllianceChatMessageOrBuilderList();
  /**
   * <code>repeated .client2server.AllianceChatMessage allianceChatMessage = 2;</code>
   */
  pb4client.AllianceChatMessageOrBuilder getAllianceChatMessageOrBuilder(
      int index);
}
