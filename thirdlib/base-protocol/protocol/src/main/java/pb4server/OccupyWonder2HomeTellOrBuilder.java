// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

public interface OccupyWonder2HomeTellOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.OccupyWonder2HomeTell)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 playerId = 1;</code>
   */
  long getPlayerId();

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
   * <code>int32 changeType = 4;</code>
   */
  int getChangeType();
}
