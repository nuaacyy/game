// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface CureSoliderInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.CureSoliderInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 要治疗的ID
   * </pre>
   *
   * <code>required int32 soliderId = 1;</code>
   */
  boolean hasSoliderId();
  /**
   * <pre>
   * 要治疗的ID
   * </pre>
   *
   * <code>required int32 soliderId = 1;</code>
   */
  int getSoliderId();

  /**
   * <pre>
   * 治疗数量
   * </pre>
   *
   * <code>required int32 cureNum = 2;</code>
   */
  boolean hasCureNum();
  /**
   * <pre>
   * 治疗数量
   * </pre>
   *
   * <code>required int32 cureNum = 2;</code>
   */
  int getCureNum();
}