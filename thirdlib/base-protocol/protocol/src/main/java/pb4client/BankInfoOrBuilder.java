// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface BankInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.BankInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 方案类型
   * </pre>
   *
   * <code>required int32 type = 1;</code>
   */
  boolean hasType();
  /**
   * <pre>
   * 方案类型
   * </pre>
   *
   * <code>required int32 type = 1;</code>
   */
  int getType();

  /**
   * <pre>
   * 选择的方案
   * </pre>
   *
   * <code>required int32 userPlan = 2;</code>
   */
  boolean hasUserPlan();
  /**
   * <pre>
   * 选择的方案
   * </pre>
   *
   * <code>required int32 userPlan = 2;</code>
   */
  int getUserPlan();

  /**
   * <pre>
   * 投资的钱
   * </pre>
   *
   * <code>optional int64 useMoney = 3;</code>
   */
  boolean hasUseMoney();
  /**
   * <pre>
   * 投资的钱
   * </pre>
   *
   * <code>optional int64 useMoney = 3;</code>
   */
  long getUseMoney();

  /**
   * <pre>
   * 利率
   * </pre>
   *
   * <code>optional int32 rate = 4;</code>
   */
  boolean hasRate();
  /**
   * <pre>
   * 利率
   * </pre>
   *
   * <code>optional int32 rate = 4;</code>
   */
  int getRate();

  /**
   * <pre>
   * 到期时间
   * </pre>
   *
   * <code>required int32 overTime = 5;</code>
   */
  boolean hasOverTime();
  /**
   * <pre>
   * 到期时间
   * </pre>
   *
   * <code>required int32 overTime = 5;</code>
   */
  int getOverTime();
}
