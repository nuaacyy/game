// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

public interface OccupyWonder2WorldTellOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.OccupyWonder2WorldTell)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated int64 playerIds = 1;</code>
   */
  java.util.List<java.lang.Long> getPlayerIdsList();
  /**
   * <code>repeated int64 playerIds = 1;</code>
   */
  int getPlayerIdsCount();
  /**
   * <code>repeated int64 playerIds = 1;</code>
   */
  long getPlayerIds(int index);

  /**
   * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
   */
  java.util.List<pb4client.OccupyWonder> 
      getOccupyWonderInfoList();
  /**
   * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
   */
  pb4client.OccupyWonder getOccupyWonderInfo(int index);
  /**
   * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
   */
  int getOccupyWonderInfoCount();
  /**
   * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
   */
  java.util.List<? extends pb4client.OccupyWonderOrBuilder> 
      getOccupyWonderInfoOrBuilderList();
  /**
   * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
   */
  pb4client.OccupyWonderOrBuilder getOccupyWonderInfoOrBuilder(
      int index);

  /**
   * <code>int32 changeType = 3;</code>
   */
  int getChangeType();
}