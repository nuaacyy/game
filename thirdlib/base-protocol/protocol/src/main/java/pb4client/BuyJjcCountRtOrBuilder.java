// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface BuyJjcCountRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.BuyJjcCountRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  boolean hasRt();
  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <pre>
   * 挑战购买次数
   * </pre>
   *
   * <code>optional int32 buyTimes = 2;</code>
   */
  boolean hasBuyTimes();
  /**
   * <pre>
   * 挑战购买次数
   * </pre>
   *
   * <code>optional int32 buyTimes = 2;</code>
   */
  int getBuyTimes();

  /**
   * <pre>
   * 下一次挑战刷新时间
   * </pre>
   *
   * <code>optional int64 nextRefreshTime = 3;</code>
   */
  boolean hasNextRefreshTime();
  /**
   * <pre>
   * 下一次挑战刷新时间
   * </pre>
   *
   * <code>optional int64 nextRefreshTime = 3;</code>
   */
  long getNextRefreshTime();
}
