// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface MoveServerOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.MoveServer)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 目标地图服的唯一ID
   * </pre>
   *
   * <code>required int64 worldId = 1;</code>
   */
  boolean hasWorldId();
  /**
   * <pre>
   * 目标地图服的唯一ID
   * </pre>
   *
   * <code>required int64 worldId = 1;</code>
   */
  long getWorldId();

  /**
   * <pre>
   *目标地图服的目标坐标X
   * </pre>
   *
   * <code>required int32 x = 2;</code>
   */
  boolean hasX();
  /**
   * <pre>
   *目标地图服的目标坐标X
   * </pre>
   *
   * <code>required int32 x = 2;</code>
   */
  int getX();

  /**
   * <pre>
   *目标地图服的目标坐标Y
   * </pre>
   *
   * <code>required int32 y = 3;</code>
   */
  boolean hasY();
  /**
   * <pre>
   *目标地图服的目标坐标Y
   * </pre>
   *
   * <code>required int32 y = 3;</code>
   */
  int getY();
}
