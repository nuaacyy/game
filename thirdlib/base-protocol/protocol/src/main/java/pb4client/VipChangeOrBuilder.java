// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface VipChangeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.VipChange)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *vip等级
   * </pre>
   *
   * <code>optional int32 vipLv = 1;</code>
   */
  boolean hasVipLv();
  /**
   * <pre>
   *vip等级
   * </pre>
   *
   * <code>optional int32 vipLv = 1;</code>
   */
  int getVipLv();

  /**
   * <pre>
   *vip经验
   * </pre>
   *
   * <code>optional int32 vipExp = 2;</code>
   */
  boolean hasVipExp();
  /**
   * <pre>
   *vip经验
   * </pre>
   *
   * <code>optional int32 vipExp = 2;</code>
   */
  int getVipExp();

  /**
   * <pre>
   * 明天登陆获得的经验
   * </pre>
   *
   * <code>optional int32 nextVipExp = 3;</code>
   */
  boolean hasNextVipExp();
  /**
   * <pre>
   * 明天登陆获得的经验
   * </pre>
   *
   * <code>optional int32 nextVipExp = 3;</code>
   */
  int getNextVipExp();
}
