// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface InstanceUnitInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.InstanceUnitInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 关卡信息
   * </pre>
   *
   * <code>required int32 unitId = 1;</code>
   */
  boolean hasUnitId();
  /**
   * <pre>
   * 关卡信息
   * </pre>
   *
   * <code>required int32 unitId = 1;</code>
   */
  int getUnitId();

  /**
   * <pre>
   * 已领取宝箱星数
   * </pre>
   *
   * <code>repeated int32 starNumBox = 2;</code>
   */
  java.util.List<java.lang.Integer> getStarNumBoxList();
  /**
   * <pre>
   * 已领取宝箱星数
   * </pre>
   *
   * <code>repeated int32 starNumBox = 2;</code>
   */
  int getStarNumBoxCount();
  /**
   * <pre>
   * 已领取宝箱星数
   * </pre>
   *
   * <code>repeated int32 starNumBox = 2;</code>
   */
  int getStarNumBox(int index);
}
