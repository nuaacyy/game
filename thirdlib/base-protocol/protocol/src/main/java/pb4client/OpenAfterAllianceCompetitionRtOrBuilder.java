// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface OpenAfterAllianceCompetitionRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.OpenAfterAllianceCompetitionRt)
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
   * 联盟参赛之前的段位
   * </pre>
   *
   * <code>optional int32 beforeRankLv = 2;</code>
   */
  boolean hasBeforeRankLv();
  /**
   * <pre>
   * 联盟参赛之前的段位
   * </pre>
   *
   * <code>optional int32 beforeRankLv = 2;</code>
   */
  int getBeforeRankLv();

  /**
   * <pre>
   * 联盟参赛之后的段位
   * </pre>
   *
   * <code>optional int32 afterRankLv = 3;</code>
   */
  boolean hasAfterRankLv();
  /**
   * <pre>
   * 联盟参赛之后的段位
   * </pre>
   *
   * <code>optional int32 afterRankLv = 3;</code>
   */
  int getAfterRankLv();

  /**
   * <pre>
   * 本次参赛获取到的积分
   * </pre>
   *
   * <code>optional int32 score = 4;</code>
   */
  boolean hasScore();
  /**
   * <pre>
   * 本次参赛获取到的积分
   * </pre>
   *
   * <code>optional int32 score = 4;</code>
   */
  int getScore();
}
