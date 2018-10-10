// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface AllianceCompetitionQuestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.AllianceCompetitionQuest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 任务排序
   * </pre>
   *
   * <code>required int32 index = 1;</code>
   */
  boolean hasIndex();
  /**
   * <pre>
   * 任务排序
   * </pre>
   *
   * <code>required int32 index = 1;</code>
   */
  int getIndex();

  /**
   * <pre>
   * 被领取之后就是0
   * </pre>
   *
   * <code>required int32 questId = 2;</code>
   */
  boolean hasQuestId();
  /**
   * <pre>
   * 被领取之后就是0
   * </pre>
   *
   * <code>required int32 questId = 2;</code>
   */
  int getQuestId();

  /**
   * <pre>
   * 任务刷新倒计时,只有任务ID是0的时候这个才不为zeroTime
   * </pre>
   *
   * <code>required int32 refTime = 3;</code>
   */
  boolean hasRefTime();
  /**
   * <pre>
   * 任务刷新倒计时,只有任务ID是0的时候这个才不为zeroTime
   * </pre>
   *
   * <code>required int32 refTime = 3;</code>
   */
  int getRefTime();
}