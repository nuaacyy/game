// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

public interface OpenAfterAllianceCompetitionAskRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.OpenAfterAllianceCompetitionAskRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <pre>
   * 联盟参赛之前的段位
   * </pre>
   *
   * <code>int32 beforeRankLv = 2;</code>
   */
  int getBeforeRankLv();

  /**
   * <pre>
   * 联盟参赛之后的段位
   * </pre>
   *
   * <code>int32 afterRankLv = 3;</code>
   */
  int getAfterRankLv();

  /**
   * <pre>
   * 本次参赛获取到的积分
   * </pre>
   *
   * <code>int32 score = 4;</code>
   */
  int getScore();
}
