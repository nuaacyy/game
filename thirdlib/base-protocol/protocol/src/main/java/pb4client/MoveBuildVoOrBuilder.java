// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface MoveBuildVoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.MoveBuildVo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 唯一ID
   * </pre>
   *
   * <code>required int64 buildingId = 1;</code>
   */
  boolean hasBuildingId();
  /**
   * <pre>
   * 唯一ID
   * </pre>
   *
   * <code>required int64 buildingId = 1;</code>
   */
  long getBuildingId();

  /**
   * <pre>
   * x
   * </pre>
   *
   * <code>required int32 newX = 2;</code>
   */
  boolean hasNewX();
  /**
   * <pre>
   * x
   * </pre>
   *
   * <code>required int32 newX = 2;</code>
   */
  int getNewX();

  /**
   * <pre>
   * y
   * </pre>
   *
   * <code>required int32 newY = 3;</code>
   */
  boolean hasNewY();
  /**
   * <pre>
   * y
   * </pre>
   *
   * <code>required int32 newY = 3;</code>
   */
  int getNewY();

  /**
   * <pre>
   * 建筑朝向
   * </pre>
   *
   * <code>required int32 direction = 4;</code>
   */
  boolean hasDirection();
  /**
   * <pre>
   * 建筑朝向
   * </pre>
   *
   * <code>required int32 direction = 4;</code>
   */
  int getDirection();
}
