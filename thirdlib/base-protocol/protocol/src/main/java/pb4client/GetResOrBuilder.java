// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface GetResOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.GetRes)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 奖励行为
   * </pre>
   *
   * <code>required int32 actionType = 1;</code>
   */
  boolean hasActionType();
  /**
   * <pre>
   * 奖励行为
   * </pre>
   *
   * <code>required int32 actionType = 1;</code>
   */
  int getActionType();

  /**
   * <pre>
   * 获得的奖励
   * </pre>
   *
   * <code>required string resString = 2;</code>
   */
  boolean hasResString();
  /**
   * <pre>
   * 获得的奖励
   * </pre>
   *
   * <code>required string resString = 2;</code>
   */
  java.lang.String getResString();
  /**
   * <pre>
   * 获得的奖励
   * </pre>
   *
   * <code>required string resString = 2;</code>
   */
  com.google.protobuf.ByteString
      getResStringBytes();
}
