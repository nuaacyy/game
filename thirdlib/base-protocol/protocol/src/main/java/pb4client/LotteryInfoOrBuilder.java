// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface LotteryInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.LotteryInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int32 protoId = 1;</code>
   */
  boolean hasProtoId();
  /**
   * <code>required int32 protoId = 1;</code>
   */
  int getProtoId();

  /**
   * <pre>
   * 下一次刷新免费抽的时间
   * </pre>
   *
   * <code>required int64 nextRefreshTime = 2;</code>
   */
  boolean hasNextRefreshTime();
  /**
   * <pre>
   * 下一次刷新免费抽的时间
   * </pre>
   *
   * <code>required int64 nextRefreshTime = 2;</code>
   */
  long getNextRefreshTime();

  /**
   * <pre>
   * 剩余的免费次数
   * </pre>
   *
   * <code>required int32 freeTimes = 3;</code>
   */
  boolean hasFreeTimes();
  /**
   * <pre>
   * 剩余的免费次数
   * </pre>
   *
   * <code>required int32 freeTimes = 3;</code>
   */
  int getFreeTimes();

  /**
   * <pre>
   * 今天的折扣次数 0 没有折扣 1有折扣 
   * </pre>
   *
   * <code>required int32 todayDiscount = 4;</code>
   */
  boolean hasTodayDiscount();
  /**
   * <pre>
   * 今天的折扣次数 0 没有折扣 1有折扣 
   * </pre>
   *
   * <code>required int32 todayDiscount = 4;</code>
   */
  int getTodayDiscount();

  /**
   * <pre>
   * 有多少次必得奖励
   * </pre>
   *
   * <code>required int32 lackTimes = 5;</code>
   */
  boolean hasLackTimes();
  /**
   * <pre>
   * 有多少次必得奖励
   * </pre>
   *
   * <code>required int32 lackTimes = 5;</code>
   */
  int getLackTimes();

  /**
   * <pre>
   * 活动开始时间
   * </pre>
   *
   * <code>required int64 startTime = 6;</code>
   */
  boolean hasStartTime();
  /**
   * <pre>
   * 活动开始时间
   * </pre>
   *
   * <code>required int64 startTime = 6;</code>
   */
  long getStartTime();

  /**
   * <pre>
   * 活动开始时间
   * </pre>
   *
   * <code>required int64 endTime = 7;</code>
   */
  boolean hasEndTime();
  /**
   * <pre>
   * 活动开始时间
   * </pre>
   *
   * <code>required int64 endTime = 7;</code>
   */
  long getEndTime();
}