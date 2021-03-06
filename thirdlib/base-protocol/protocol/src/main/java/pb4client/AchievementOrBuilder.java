// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface AchievementOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.Achievement)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 成就唯一ID
   * </pre>
   *
   * <code>required int64 id = 1;</code>
   */
  boolean hasId();
  /**
   * <pre>
   * 成就唯一ID
   * </pre>
   *
   * <code>required int64 id = 1;</code>
   */
  long getId();

  /**
   * <pre>
   * 成就模板ID
   * </pre>
   *
   * <code>required int32 protoId = 2;</code>
   */
  boolean hasProtoId();
  /**
   * <pre>
   * 成就模板ID
   * </pre>
   *
   * <code>required int32 protoId = 2;</code>
   */
  int getProtoId();

  /**
   * <pre>
   * 成就状态 0-进行中 1-已完成 2-已领取奖励
   * </pre>
   *
   * <code>required int32 state = 3;</code>
   */
  boolean hasState();
  /**
   * <pre>
   * 成就状态 0-进行中 1-已完成 2-已领取奖励
   * </pre>
   *
   * <code>required int32 state = 3;</code>
   */
  int getState();

  /**
   * <pre>
   * 成就当前进度
   * </pre>
   *
   * <code>repeated .client2server.ProgressInfo allProgress = 4;</code>
   */
  java.util.List<pb4client.ProgressInfo> 
      getAllProgressList();
  /**
   * <pre>
   * 成就当前进度
   * </pre>
   *
   * <code>repeated .client2server.ProgressInfo allProgress = 4;</code>
   */
  pb4client.ProgressInfo getAllProgress(int index);
  /**
   * <pre>
   * 成就当前进度
   * </pre>
   *
   * <code>repeated .client2server.ProgressInfo allProgress = 4;</code>
   */
  int getAllProgressCount();
  /**
   * <pre>
   * 成就当前进度
   * </pre>
   *
   * <code>repeated .client2server.ProgressInfo allProgress = 4;</code>
   */
  java.util.List<? extends pb4client.ProgressInfoOrBuilder> 
      getAllProgressOrBuilderList();
  /**
   * <pre>
   * 成就当前进度
   * </pre>
   *
   * <code>repeated .client2server.ProgressInfo allProgress = 4;</code>
   */
  pb4client.ProgressInfoOrBuilder getAllProgressOrBuilder(
      int index);
}
