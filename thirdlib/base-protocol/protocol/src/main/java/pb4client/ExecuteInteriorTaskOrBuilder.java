// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface ExecuteInteriorTaskOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.ExecuteInteriorTask)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 内政任务的模板id
   * </pre>
   *
   * <code>required int32 id = 1;</code>
   */
  boolean hasId();
  /**
   * <pre>
   * 内政任务的模板id
   * </pre>
   *
   * <code>required int32 id = 1;</code>
   */
  int getId();

  /**
   * <pre>
   * 进行的操作 1 领取任务 2 领取奖励
   * </pre>
   *
   * <code>required int32 op = 2;</code>
   */
  boolean hasOp();
  /**
   * <pre>
   * 进行的操作 1 领取任务 2 领取奖励
   * </pre>
   *
   * <code>required int32 op = 2;</code>
   */
  int getOp();

  /**
   * <pre>
   * 内政任务的位置
   * </pre>
   *
   * <code>required int32 pos = 3;</code>
   */
  boolean hasPos();
  /**
   * <pre>
   * 内政任务的位置
   * </pre>
   *
   * <code>required int32 pos = 3;</code>
   */
  int getPos();
}