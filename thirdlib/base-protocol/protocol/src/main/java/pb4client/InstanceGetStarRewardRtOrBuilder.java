// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface InstanceGetStarRewardRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.InstanceGetStarRewardRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int32 rt = 1;</code>
   */
  boolean hasRt();
  /**
   * <code>required int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <pre>
   * 要领取的章节ID
   * </pre>
   *
   * <code>optional int32 chapterId = 2;</code>
   */
  boolean hasChapterId();
  /**
   * <pre>
   * 要领取的章节ID
   * </pre>
   *
   * <code>optional int32 chapterId = 2;</code>
   */
  int getChapterId();

  /**
   * <pre>
   * 要领取的星数
   * </pre>
   *
   * <code>optional int32 starNum = 3;</code>
   */
  boolean hasStarNum();
  /**
   * <pre>
   * 要领取的星数
   * </pre>
   *
   * <code>optional int32 starNum = 3;</code>
   */
  int getStarNum();
}