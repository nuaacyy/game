// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface MonthCardInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.MonthCardInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 月卡ID 对应礼包表唯一ID
   * </pre>
   *
   * <code>required int32 monthCardId = 1;</code>
   */
  boolean hasMonthCardId();
  /**
   * <pre>
   * 月卡ID 对应礼包表唯一ID
   * </pre>
   *
   * <code>required int32 monthCardId = 1;</code>
   */
  int getMonthCardId();

  /**
   * <pre>
   * 今天月卡是否领取过
   * </pre>
   *
   * <code>required int32 inDayRecv = 2;</code>
   */
  boolean hasInDayRecv();
  /**
   * <pre>
   * 今天月卡是否领取过
   * </pre>
   *
   * <code>required int32 inDayRecv = 2;</code>
   */
  int getInDayRecv();

  /**
   * <pre>
   * 月卡过期时间
   * </pre>
   *
   * <code>required int64 overdueTime = 3;</code>
   */
  boolean hasOverdueTime();
  /**
   * <pre>
   * 月卡过期时间
   * </pre>
   *
   * <code>required int64 overdueTime = 3;</code>
   */
  long getOverdueTime();
}
