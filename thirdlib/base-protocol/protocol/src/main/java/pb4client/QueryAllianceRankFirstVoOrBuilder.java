// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface QueryAllianceRankFirstVoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.QueryAllianceRankFirstVo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int32 rankType = 1;</code>
   */
  boolean hasRankType();
  /**
   * <code>required int32 rankType = 1;</code>
   */
  int getRankType();

  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>required .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
   */
  boolean hasQueryAllianceRankVos();
  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>required .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
   */
  pb4client.QueryAllianceRankVo getQueryAllianceRankVos();
  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>required .client2server.QueryAllianceRankVo queryAllianceRankVos = 2;</code>
   */
  pb4client.QueryAllianceRankVoOrBuilder getQueryAllianceRankVosOrBuilder();
}
