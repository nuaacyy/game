// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface StrongholdCountChangeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.StrongholdCountChange)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *上次结算时间
   * </pre>
   *
   * <code>optional int32 time = 1;</code>
   */
  boolean hasTime();
  /**
   * <pre>
   *上次结算时间
   * </pre>
   *
   * <code>optional int32 time = 1;</code>
   */
  int getTime();

  /**
   * <pre>
   *上限
   * </pre>
   *
   * <code>optional int32 strongholdLimit = 2;</code>
   */
  boolean hasStrongholdLimit();
  /**
   * <pre>
   *上限
   * </pre>
   *
   * <code>optional int32 strongholdLimit = 2;</code>
   */
  int getStrongholdLimit();

  /**
   * <pre>
   *当前次数
   * </pre>
   *
   * <code>optional int32 strongholdCount = 3;</code>
   */
  boolean hasStrongholdCount();
  /**
   * <pre>
   *当前次数
   * </pre>
   *
   * <code>optional int32 strongholdCount = 3;</code>
   */
  int getStrongholdCount();
}
