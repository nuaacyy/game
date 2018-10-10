// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface SendRedBagInfoRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.SendRedBagInfoRt)
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
   * 一共发出去的钱
   * </pre>
   *
   * <code>optional int64 allSendMoney = 2;</code>
   */
  boolean hasAllSendMoney();
  /**
   * <pre>
   * 一共发出去的钱
   * </pre>
   *
   * <code>optional int64 allSendMoney = 2;</code>
   */
  long getAllSendMoney();

  /**
   * <pre>
   * 一共发出去的红包数量
   * </pre>
   *
   * <code>optional int32 allSendNum = 3;</code>
   */
  boolean hasAllSendNum();
  /**
   * <pre>
   * 一共发出去的红包数量
   * </pre>
   *
   * <code>optional int32 allSendNum = 3;</code>
   */
  int getAllSendNum();

  /**
   * <pre>
   * 发出的红包的数据
   * </pre>
   *
   * <code>repeated .client2server.SendRedBagInfoVo sendRedBagInfoVos = 4;</code>
   */
  java.util.List<pb4client.SendRedBagInfoVo> 
      getSendRedBagInfoVosList();
  /**
   * <pre>
   * 发出的红包的数据
   * </pre>
   *
   * <code>repeated .client2server.SendRedBagInfoVo sendRedBagInfoVos = 4;</code>
   */
  pb4client.SendRedBagInfoVo getSendRedBagInfoVos(int index);
  /**
   * <pre>
   * 发出的红包的数据
   * </pre>
   *
   * <code>repeated .client2server.SendRedBagInfoVo sendRedBagInfoVos = 4;</code>
   */
  int getSendRedBagInfoVosCount();
  /**
   * <pre>
   * 发出的红包的数据
   * </pre>
   *
   * <code>repeated .client2server.SendRedBagInfoVo sendRedBagInfoVos = 4;</code>
   */
  java.util.List<? extends pb4client.SendRedBagInfoVoOrBuilder> 
      getSendRedBagInfoVosOrBuilderList();
  /**
   * <pre>
   * 发出的红包的数据
   * </pre>
   *
   * <code>repeated .client2server.SendRedBagInfoVo sendRedBagInfoVos = 4;</code>
   */
  pb4client.SendRedBagInfoVoOrBuilder getSendRedBagInfoVosOrBuilder(
      int index);
}