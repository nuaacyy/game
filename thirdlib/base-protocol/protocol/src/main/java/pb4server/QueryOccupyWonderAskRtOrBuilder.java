// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

public interface QueryOccupyWonderAskRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.QueryOccupyWonderAskRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 rt = 1;</code>
   */
  int getRt();

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
}
