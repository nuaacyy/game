// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface BuildInfoByAllianceHelpOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.BuildInfoByAllianceHelp)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 建筑所在城池ID
   * </pre>
   *
   * <code>required int64 cityId = 1;</code>
   */
  boolean hasCityId();
  /**
   * <pre>
   * 建筑所在城池ID
   * </pre>
   *
   * <code>required int64 cityId = 1;</code>
   */
  long getCityId();

  /**
   * <pre>
   * 建筑类型
   * </pre>
   *
   * <code>required int32 buildType = 2;</code>
   */
  boolean hasBuildType();
  /**
   * <pre>
   * 建筑类型
   * </pre>
   *
   * <code>required int32 buildType = 2;</code>
   */
  int getBuildType();

  /**
   * <pre>
   * 建筑位置坐标x
   * </pre>
   *
   * <code>required int32 x = 3;</code>
   */
  boolean hasX();
  /**
   * <pre>
   * 建筑位置坐标x
   * </pre>
   *
   * <code>required int32 x = 3;</code>
   */
  int getX();

  /**
   * <pre>
   * 建筑位置坐标y
   * </pre>
   *
   * <code>required int32 y = 4;</code>
   */
  boolean hasY();
  /**
   * <pre>
   * 建筑位置坐标y
   * </pre>
   *
   * <code>required int32 y = 4;</code>
   */
  int getY();

  /**
   * <pre>
   * 帮助表中的ID
   * </pre>
   *
   * <code>required int64 helpId = 5;</code>
   */
  boolean hasHelpId();
  /**
   * <pre>
   * 帮助表中的ID
   * </pre>
   *
   * <code>required int64 helpId = 5;</code>
   */
  long getHelpId();

  /**
   * <pre>
   * 建造结束时间
   * </pre>
   *
   * <code>required int32 overTime = 6;</code>
   */
  boolean hasOverTime();
  /**
   * <pre>
   * 建造结束时间
   * </pre>
   *
   * <code>required int32 overTime = 6;</code>
   */
  int getOverTime();

  /**
   * <pre>
   * 建筑唯一ID
   * </pre>
   *
   * <code>required int64 buildingId = 7;</code>
   */
  boolean hasBuildingId();
  /**
   * <pre>
   * 建筑唯一ID
   * </pre>
   *
   * <code>required int64 buildingId = 7;</code>
   */
  long getBuildingId();
}
