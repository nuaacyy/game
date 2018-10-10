// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface LoginOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.Login)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required string account = 1;</code>
   */
  boolean hasAccount();
  /**
   * <code>required string account = 1;</code>
   */
  java.lang.String getAccount();
  /**
   * <code>required string account = 1;</code>
   */
  com.google.protobuf.ByteString
      getAccountBytes();

  /**
   * <code>required string pwd = 2;</code>
   */
  boolean hasPwd();
  /**
   * <code>required string pwd = 2;</code>
   */
  java.lang.String getPwd();
  /**
   * <code>required string pwd = 2;</code>
   */
  com.google.protobuf.ByteString
      getPwdBytes();

  /**
   * <pre>
   * 服务器id
   * </pre>
   *
   * <code>required string sid = 3;</code>
   */
  boolean hasSid();
  /**
   * <pre>
   * 服务器id
   * </pre>
   *
   * <code>required string sid = 3;</code>
   */
  java.lang.String getSid();
  /**
   * <pre>
   * 服务器id
   * </pre>
   *
   * <code>required string sid = 3;</code>
   */
  com.google.protobuf.ByteString
      getSidBytes();

  /**
   * <pre>
   *登录类型 1-账号密码  2-令牌
   * </pre>
   *
   * <code>required int32 loginType = 4;</code>
   */
  boolean hasLoginType();
  /**
   * <pre>
   *登录类型 1-账号密码  2-令牌
   * </pre>
   *
   * <code>required int32 loginType = 4;</code>
   */
  int getLoginType();

  /**
   * <pre>
   *登录令牌
   * </pre>
   *
   * <code>required string token = 5;</code>
   */
  boolean hasToken();
  /**
   * <pre>
   *登录令牌
   * </pre>
   *
   * <code>required string token = 5;</code>
   */
  java.lang.String getToken();
  /**
   * <pre>
   *登录令牌
   * </pre>
   *
   * <code>required string token = 5;</code>
   */
  com.google.protobuf.ByteString
      getTokenBytes();

  /**
   * <pre>
   *设备号	设备号，PC、手游需要发送客户端设备ID，统一使用公司标准，页游可以根据需要发送相应值，比如浏览器信息
   * </pre>
   *
   * <code>required string device = 6;</code>
   */
  boolean hasDevice();
  /**
   * <pre>
   *设备号	设备号，PC、手游需要发送客户端设备ID，统一使用公司标准，页游可以根据需要发送相应值，比如浏览器信息
   * </pre>
   *
   * <code>required string device = 6;</code>
   */
  java.lang.String getDevice();
  /**
   * <pre>
   *设备号	设备号，PC、手游需要发送客户端设备ID，统一使用公司标准，页游可以根据需要发送相应值，比如浏览器信息
   * </pre>
   *
   * <code>required string device = 6;</code>
   */
  com.google.protobuf.ByteString
      getDeviceBytes();
}
