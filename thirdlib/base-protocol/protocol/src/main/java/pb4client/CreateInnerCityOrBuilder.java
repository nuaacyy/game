// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface CreateInnerCityOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.CreateInnerCity)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 城池ID
   * </pre>
   *
   * <code>required int64 cityId = 1;</code>
   */
  boolean hasCityId();
  /**
   * <pre>
   * 城池ID
   * </pre>
   *
   * <code>required int64 cityId = 1;</code>
   */
  long getCityId();

  /**
   * <pre>
   * 建筑的坑位索引
   * </pre>
   *
   * <code>required int32 positionIndex = 2;</code>
   */
  boolean hasPositionIndex();
  /**
   * <pre>
   * 建筑的坑位索引
   * </pre>
   *
   * <code>required int32 positionIndex = 2;</code>
   */
  int getPositionIndex();

  /**
   * <pre>
   * 建筑类型
   * </pre>
   *
   * <code>required int32 innerCityType = 3;</code>
   */
  boolean hasInnerCityType();
  /**
   * <pre>
   * 建筑类型
   * </pre>
   *
   * <code>required int32 innerCityType = 3;</code>
   */
  int getInnerCityType();

  /**
   * <pre>
   * 1正常 2秒建筑
   * </pre>
   *
   * <code>required int32 createType = 4;</code>
   */
  boolean hasCreateType();
  /**
   * <pre>
   * 1正常 2秒建筑
   * </pre>
   *
   * <code>required int32 createType = 4;</code>
   */
  int getCreateType();
}
