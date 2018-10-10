// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface UnlockBuildingRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.UnlockBuildingRt)
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
   * 城池ID
   * </pre>
   *
   * <code>optional int64 cityId = 2;</code>
   */
  boolean hasCityId();
  /**
   * <pre>
   * 城池ID
   * </pre>
   *
   * <code>optional int64 cityId = 2;</code>
   */
  long getCityId();

  /**
   * <pre>
   * 建筑实例唯一ID
   * </pre>
   *
   * <code>optional int64 buildingId = 3;</code>
   */
  boolean hasBuildingId();
  /**
   * <pre>
   * 建筑实例唯一ID
   * </pre>
   *
   * <code>optional int64 buildingId = 3;</code>
   */
  long getBuildingId();
}
