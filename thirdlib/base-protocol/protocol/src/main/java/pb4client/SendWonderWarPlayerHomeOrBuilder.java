// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface SendWonderWarPlayerHomeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.SendWonderWarPlayerHome)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int32 wonderId = 1;</code>
   */
  boolean hasWonderId();
  /**
   * <code>required int32 wonderId = 1;</code>
   */
  int getWonderId();

  /**
   * <code>repeated int64 playerIds = 2;</code>
   */
  java.util.List<java.lang.Long> getPlayerIdsList();
  /**
   * <code>repeated int64 playerIds = 2;</code>
   */
  int getPlayerIdsCount();
  /**
   * <code>repeated int64 playerIds = 2;</code>
   */
  long getPlayerIds(int index);
}
