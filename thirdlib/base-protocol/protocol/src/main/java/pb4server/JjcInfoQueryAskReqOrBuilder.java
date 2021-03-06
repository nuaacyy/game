// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

public interface JjcInfoQueryAskReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.JjcInfoQueryAskReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 本日已领取的积分奖励模版ID
   * </pre>
   *
   * <code>repeated int32 scoreRewards = 1;</code>
   */
  java.util.List<java.lang.Integer> getScoreRewardsList();
  /**
   * <pre>
   * 本日已领取的积分奖励模版ID
   * </pre>
   *
   * <code>repeated int32 scoreRewards = 1;</code>
   */
  int getScoreRewardsCount();
  /**
   * <pre>
   * 本日已领取的积分奖励模版ID
   * </pre>
   *
   * <code>repeated int32 scoreRewards = 1;</code>
   */
  int getScoreRewards(int index);

  /**
   * <pre>
   * 已领取的竞技场奖励模版ID（历史最高排名奖励）
   * </pre>
   *
   * <code>repeated int32 drawRewards = 2;</code>
   */
  java.util.List<java.lang.Integer> getDrawRewardsList();
  /**
   * <pre>
   * 已领取的竞技场奖励模版ID（历史最高排名奖励）
   * </pre>
   *
   * <code>repeated int32 drawRewards = 2;</code>
   */
  int getDrawRewardsCount();
  /**
   * <pre>
   * 已领取的竞技场奖励模版ID（历史最高排名奖励）
   * </pre>
   *
   * <code>repeated int32 drawRewards = 2;</code>
   */
  int getDrawRewards(int index);

  /**
   * <pre>
   * 成就兑换
   * </pre>
   *
   * <code>repeated int32 achievementRewards = 3;</code>
   */
  java.util.List<java.lang.Integer> getAchievementRewardsList();
  /**
   * <pre>
   * 成就兑换
   * </pre>
   *
   * <code>repeated int32 achievementRewards = 3;</code>
   */
  int getAchievementRewardsCount();
  /**
   * <pre>
   * 成就兑换
   * </pre>
   *
   * <code>repeated int32 achievementRewards = 3;</code>
   */
  int getAchievementRewards(int index);

  /**
   * <pre>
   * 今天剩余的竞技场
   * </pre>
   *
   * <code>int32 todayNum = 4;</code>
   */
  int getTodayNum();

  /**
   * <pre>
   * 今天买了多少轮挑战机会
   * </pre>
   *
   * <code>int32 todayBuyCountNum = 5;</code>
   */
  int getTodayBuyCountNum();
}
