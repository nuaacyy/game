// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

public interface QueryInAllianceRankRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.QueryInAllianceRankRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
   */
  java.util.List<pb4server.QueryInAllianceRankVo> 
      getQueryInAllianceRankVosList();
  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
   */
  pb4server.QueryInAllianceRankVo getQueryInAllianceRankVos(int index);
  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
   */
  int getQueryInAllianceRankVosCount();
  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
   */
  java.util.List<? extends pb4server.QueryInAllianceRankVoOrBuilder> 
      getQueryInAllianceRankVosOrBuilderList();
  /**
   * <pre>
   * 排行内容
   * </pre>
   *
   * <code>repeated .pb4server.QueryInAllianceRankVo queryInAllianceRankVos = 2;</code>
   */
  pb4server.QueryInAllianceRankVoOrBuilder getQueryInAllianceRankVosOrBuilder(
      int index);
}
