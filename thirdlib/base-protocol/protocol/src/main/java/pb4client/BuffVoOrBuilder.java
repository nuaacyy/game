// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface BuffVoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.BuffVo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 服务器buff唯一ID
   * </pre>
   *
   * <code>required int64 buffId = 1;</code>
   */
  boolean hasBuffId();
  /**
   * <pre>
   * 服务器buff唯一ID
   * </pre>
   *
   * <code>required int64 buffId = 1;</code>
   */
  long getBuffId();

  /**
   * <pre>
   * buff模版ID
   * </pre>
   *
   * <code>required int32 buffProtoId = 2;</code>
   */
  boolean hasBuffProtoId();
  /**
   * <pre>
   * buff模版ID
   * </pre>
   *
   * <code>required int32 buffProtoId = 2;</code>
   */
  int getBuffProtoId();

  /**
   * <pre>
   * buff过期时间
   * </pre>
   *
   * <code>required int32 buffOverTime = 3;</code>
   */
  boolean hasBuffOverTime();
  /**
   * <pre>
   * buff过期时间
   * </pre>
   *
   * <code>required int32 buffOverTime = 3;</code>
   */
  int getBuffOverTime();

  /**
   * <pre>
   * buff开始时间
   * </pre>
   *
   * <code>required int32 buffStartTime = 4;</code>
   */
  boolean hasBuffStartTime();
  /**
   * <pre>
   * buff开始时间
   * </pre>
   *
   * <code>required int32 buffStartTime = 4;</code>
   */
  int getBuffStartTime();
}