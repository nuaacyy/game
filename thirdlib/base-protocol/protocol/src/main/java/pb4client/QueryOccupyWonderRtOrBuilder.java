// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface QueryOccupyWonderRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.QueryOccupyWonderRt)
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
   * <pre>
   *奇观信息
   * </pre>
   *
   * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
   */
  java.util.List<pb4client.OccupyWonder> 
      getOccupyWonderInfoList();
  /**
   * <pre>
   *奇观信息
   * </pre>
   *
   * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
   */
  pb4client.OccupyWonder getOccupyWonderInfo(int index);
  /**
   * <pre>
   *奇观信息
   * </pre>
   *
   * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
   */
  int getOccupyWonderInfoCount();
  /**
   * <pre>
   *奇观信息
   * </pre>
   *
   * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
   */
  java.util.List<? extends pb4client.OccupyWonderOrBuilder> 
      getOccupyWonderInfoOrBuilderList();
  /**
   * <pre>
   *奇观信息
   * </pre>
   *
   * <code>repeated .client2server.OccupyWonder occupyWonderInfo = 2;</code>
   */
  pb4client.OccupyWonderOrBuilder getOccupyWonderInfoOrBuilder(
      int index);
}