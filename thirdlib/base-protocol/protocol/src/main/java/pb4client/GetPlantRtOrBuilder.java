// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface GetPlantRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.GetPlantRt)
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
   * 被收菜的建筑ID
   * </pre>
   *
   * <code>repeated int64 buildingId = 2;</code>
   */
  java.util.List<java.lang.Long> getBuildingIdList();
  /**
   * <pre>
   * 被收菜的建筑ID
   * </pre>
   *
   * <code>repeated int64 buildingId = 2;</code>
   */
  int getBuildingIdCount();
  /**
   * <pre>
   * 被收菜的建筑ID
   * </pre>
   *
   * <code>repeated int64 buildingId = 2;</code>
   */
  long getBuildingId(int index);
}
