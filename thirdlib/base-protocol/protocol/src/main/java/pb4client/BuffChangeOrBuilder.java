// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface BuffChangeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.BuffChange)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 变化类型  1-新增  2-过期
   * </pre>
   *
   * <code>required int32 changeType = 1;</code>
   */
  boolean hasChangeType();
  /**
   * <pre>
   * 变化类型  1-新增  2-过期
   * </pre>
   *
   * <code>required int32 changeType = 1;</code>
   */
  int getChangeType();

  /**
   * <code>required .client2server.BuffVo buff = 2;</code>
   */
  boolean hasBuff();
  /**
   * <code>required .client2server.BuffVo buff = 2;</code>
   */
  pb4client.BuffVo getBuff();
  /**
   * <code>required .client2server.BuffVo buff = 2;</code>
   */
  pb4client.BuffVoOrBuilder getBuffOrBuilder();
}
