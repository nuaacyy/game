// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

public interface QueryAllianceRankAskVosOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.QueryAllianceRankAskVos)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 rankType = 1;</code>
   */
  int getRankType();

  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>.pb4server.QueryAllianceRankAskVo queryAllianceRankVos = 2;</code>
   */
  boolean hasQueryAllianceRankVos();
  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>.pb4server.QueryAllianceRankAskVo queryAllianceRankVos = 2;</code>
   */
  pb4server.QueryAllianceRankAskVo getQueryAllianceRankVos();
  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>.pb4server.QueryAllianceRankAskVo queryAllianceRankVos = 2;</code>
   */
  pb4server.QueryAllianceRankAskVoOrBuilder getQueryAllianceRankVosOrBuilder();
}