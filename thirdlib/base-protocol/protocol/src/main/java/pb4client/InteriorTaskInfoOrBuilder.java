// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface InteriorTaskInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.InteriorTaskInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 任务的模板id
   * </pre>
   *
   * <code>required int32 id = 1;</code>
   */
  boolean hasId();
  /**
   * <pre>
   * 任务的模板id
   * </pre>
   *
   * <code>required int32 id = 1;</code>
   */
  int getId();

  /**
   * <pre>
   * 任务位置
   * </pre>
   *
   * <code>required int32 pos = 2;</code>
   */
  boolean hasPos();
  /**
   * <pre>
   * 任务位置
   * </pre>
   *
   * <code>required int32 pos = 2;</code>
   */
  int getPos();

  /**
   * <pre>
   * 奖励
   * </pre>
   *
   * <code>required string rewards = 3;</code>
   */
  boolean hasRewards();
  /**
   * <pre>
   * 奖励
   * </pre>
   *
   * <code>required string rewards = 3;</code>
   */
  java.lang.String getRewards();
  /**
   * <pre>
   * 奖励
   * </pre>
   *
   * <code>required string rewards = 3;</code>
   */
  com.google.protobuf.ByteString
      getRewardsBytes();

  /**
   * <pre>
   * 任务状态 0 未完成 1 完成
   * </pre>
   *
   * <code>required int32 state = 4;</code>
   */
  boolean hasState();
  /**
   * <pre>
   * 任务状态 0 未完成 1 完成
   * </pre>
   *
   * <code>required int32 state = 4;</code>
   */
  int getState();

  /**
   * <pre>
   * 任务完成时间
   * </pre>
   *
   * <code>required int32 finishTime = 5;</code>
   */
  boolean hasFinishTime();
  /**
   * <pre>
   * 任务完成时间
   * </pre>
   *
   * <code>required int32 finishTime = 5;</code>
   */
  int getFinishTime();
}
