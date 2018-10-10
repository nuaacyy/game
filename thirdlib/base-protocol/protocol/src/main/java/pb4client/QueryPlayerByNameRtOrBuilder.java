// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface QueryPlayerByNameRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.QueryPlayerByNameRt)
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
   * 查询的个人信息
   * </pre>
   *
   * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
   */
  java.util.List<pb4client.QueryPlayerByNameVo> 
      getQueryPlayerByNameVosList();
  /**
   * <pre>
   * 查询的个人信息
   * </pre>
   *
   * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
   */
  pb4client.QueryPlayerByNameVo getQueryPlayerByNameVos(int index);
  /**
   * <pre>
   * 查询的个人信息
   * </pre>
   *
   * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
   */
  int getQueryPlayerByNameVosCount();
  /**
   * <pre>
   * 查询的个人信息
   * </pre>
   *
   * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
   */
  java.util.List<? extends pb4client.QueryPlayerByNameVoOrBuilder> 
      getQueryPlayerByNameVosOrBuilderList();
  /**
   * <pre>
   * 查询的个人信息
   * </pre>
   *
   * <code>repeated .client2server.QueryPlayerByNameVo queryPlayerByNameVos = 2;</code>
   */
  pb4client.QueryPlayerByNameVoOrBuilder getQueryPlayerByNameVosOrBuilder(
      int index);
}