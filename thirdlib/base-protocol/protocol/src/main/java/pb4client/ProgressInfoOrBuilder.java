// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface ProgressInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.ProgressInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *条件类型
   * </pre>
   *
   * <code>required int32 checkType = 1;</code>
   */
  boolean hasCheckType();
  /**
   * <pre>
   *条件类型
   * </pre>
   *
   * <code>required int32 checkType = 1;</code>
   */
  int getCheckType();

  /**
   * <pre>
   *进度
   * </pre>
   *
   * <code>required int32 progress = 2;</code>
   */
  boolean hasProgress();
  /**
   * <pre>
   *进度
   * </pre>
   *
   * <code>required int32 progress = 2;</code>
   */
  int getProgress();
}