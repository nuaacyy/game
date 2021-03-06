// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface ForceValueChangeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.ForceValueChange)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *部队ID
   * </pre>
   *
   * <code>required int64 forceId = 1;</code>
   */
  boolean hasForceId();
  /**
   * <pre>
   *部队ID
   * </pre>
   *
   * <code>required int64 forceId = 1;</code>
   */
  long getForceId();

  /**
   * <pre>
   *变化的类型(1-某操作结束时间,如练兵屯田对峙等.2-当前X  3-当前Y 4-部队当前一级归属  5-部队当前二级归属 6- 部队隶属的军团ID  7-部队在军团中的位置 8-操作次数（练兵） 9.-总次数)
   * </pre>
   *
   * <code>required int32 changeType = 2;</code>
   */
  boolean hasChangeType();
  /**
   * <pre>
   *变化的类型(1-某操作结束时间,如练兵屯田对峙等.2-当前X  3-当前Y 4-部队当前一级归属  5-部队当前二级归属 6- 部队隶属的军团ID  7-部队在军团中的位置 8-操作次数（练兵） 9.-总次数)
   * </pre>
   *
   * <code>required int32 changeType = 2;</code>
   */
  int getChangeType();

  /**
   * <pre>
   *变化后的值
   * </pre>
   *
   * <code>required int64 nowValue = 3;</code>
   */
  boolean hasNowValue();
  /**
   * <pre>
   *变化后的值
   * </pre>
   *
   * <code>required int64 nowValue = 3;</code>
   */
  long getNowValue();
}
