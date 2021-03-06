// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

public interface AllianceMissionChangeNotic2GTellOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.AllianceMissionChangeNotic2GTell)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated int64 playerIds = 1;</code>
   */
  java.util.List<java.lang.Long> getPlayerIdsList();
  /**
   * <code>repeated int64 playerIds = 1;</code>
   */
  int getPlayerIdsCount();
  /**
   * <code>repeated int64 playerIds = 1;</code>
   */
  long getPlayerIds(int index);

  /**
   * <pre>
   * 活跃度等级
   * </pre>
   *
   * <code>int32 lv = 2;</code>
   */
  int getLv();

  /**
   * <pre>
   *活跃度经验
   * </pre>
   *
   * <code>int32 exp = 3;</code>
   */
  int getExp();

  /**
   * <pre>
   *活跃度积分
   * </pre>
   *
   * <code>int32 score = 4;</code>
   */
  int getScore();

  /**
   * <pre>
   *本日奖品ID
   * </pre>
   *
   * <code>int32 allianceMissionGiftId = 5;</code>
   */
  int getAllianceMissionGiftId();

  /**
   * <pre>
   *本日活跃度等级
   * </pre>
   *
   * <code>int32 todayLv = 6;</code>
   */
  int getTodayLv();
}
