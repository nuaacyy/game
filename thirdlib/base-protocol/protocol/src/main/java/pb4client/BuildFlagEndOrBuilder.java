// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface BuildFlagEndOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.BuildFlagEnd)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *战旗坐标
   * </pre>
   *
   * <code>required int32 x = 1;</code>
   */
  boolean hasX();
  /**
   * <pre>
   *战旗坐标
   * </pre>
   *
   * <code>required int32 x = 1;</code>
   */
  int getX();

  /**
   * <pre>
   *战旗坐标
   * </pre>
   *
   * <code>required int32 y = 2;</code>
   */
  boolean hasY();
  /**
   * <pre>
   *战旗坐标
   * </pre>
   *
   * <code>required int32 y = 2;</code>
   */
  int getY();

  /**
   * <pre>
   *新的结束时间,有可能会超过当前时间,心跳会马上在刷完成的
   * </pre>
   *
   * <code>required int32 endTime = 3;</code>
   */
  boolean hasEndTime();
  /**
   * <pre>
   *新的结束时间,有可能会超过当前时间,心跳会马上在刷完成的
   * </pre>
   *
   * <code>required int32 endTime = 3;</code>
   */
  int getEndTime();
}