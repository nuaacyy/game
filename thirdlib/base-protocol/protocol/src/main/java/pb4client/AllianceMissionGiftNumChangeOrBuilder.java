// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface AllianceMissionGiftNumChangeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.AllianceMissionGiftNumChange)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 变化类型 1-新增  2-减少 3- 重置
   * </pre>
   *
   * <code>required int32 changeType = 1;</code>
   */
  boolean hasChangeType();
  /**
   * <pre>
   * 变化类型 1-新增  2-减少 3- 重置
   * </pre>
   *
   * <code>required int32 changeType = 1;</code>
   */
  int getChangeType();

  /**
   * <pre>
   * 变化数量
   * </pre>
   *
   * <code>required int32 changeValue = 2;</code>
   */
  boolean hasChangeValue();
  /**
   * <pre>
   * 变化数量
   * </pre>
   *
   * <code>required int32 changeValue = 2;</code>
   */
  int getChangeValue();
}
