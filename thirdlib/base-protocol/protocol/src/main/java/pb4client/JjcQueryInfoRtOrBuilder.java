// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface JjcQueryInfoRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.JjcQueryInfoRt)
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
   * 自己的排名
   * </pre>
   *
   * <code>optional int32 rank = 2;</code>
   */
  boolean hasRank();
  /**
   * <pre>
   * 自己的排名
   * </pre>
   *
   * <code>optional int32 rank = 2;</code>
   */
  int getRank();

  /**
   * <pre>
   * 自己的实力值
   * </pre>
   *
   * <code>optional int64 power = 3;</code>
   */
  boolean hasPower();
  /**
   * <pre>
   * 自己的实力值
   * </pre>
   *
   * <code>optional int64 power = 3;</code>
   */
  long getPower();

  /**
   * <pre>
   * 挑战剩余次数
   * </pre>
   *
   * <code>optional int32 times = 4;</code>
   */
  boolean hasTimes();
  /**
   * <pre>
   * 挑战剩余次数
   * </pre>
   *
   * <code>optional int32 times = 4;</code>
   */
  int getTimes();

  /**
   * <pre>
   * 挑战冷却结束时间
   * </pre>
   *
   * <code>optional int32 coldEndTime = 5;</code>
   */
  boolean hasColdEndTime();
  /**
   * <pre>
   * 挑战冷却结束时间
   * </pre>
   *
   * <code>optional int32 coldEndTime = 5;</code>
   */
  int getColdEndTime();

  /**
   * <pre>
   * 历史最高排名
   * </pre>
   *
   * <code>optional int32 maxRank = 6;</code>
   */
  boolean hasMaxRank();
  /**
   * <pre>
   * 历史最高排名
   * </pre>
   *
   * <code>optional int32 maxRank = 6;</code>
   */
  int getMaxRank();

  /**
   * <pre>
   * 本日积分
   * </pre>
   *
   * <code>optional int32 score = 7;</code>
   */
  boolean hasScore();
  /**
   * <pre>
   * 本日积分
   * </pre>
   *
   * <code>optional int32 score = 7;</code>
   */
  int getScore();

  /**
   * <pre>
   * 已领取奖励的模版ID（最高排名）
   * </pre>
   *
   * <code>repeated int32 drawRewards = 8;</code>
   */
  java.util.List<java.lang.Integer> getDrawRewardsList();
  /**
   * <pre>
   * 已领取奖励的模版ID（最高排名）
   * </pre>
   *
   * <code>repeated int32 drawRewards = 8;</code>
   */
  int getDrawRewardsCount();
  /**
   * <pre>
   * 已领取奖励的模版ID（最高排名）
   * </pre>
   *
   * <code>repeated int32 drawRewards = 8;</code>
   */
  int getDrawRewards(int index);

  /**
   * <pre>
   * 本日已领取奖励的模版ID（积分）
   * </pre>
   *
   * <code>repeated int32 scoreRewards = 9;</code>
   */
  java.util.List<java.lang.Integer> getScoreRewardsList();
  /**
   * <pre>
   * 本日已领取奖励的模版ID（积分）
   * </pre>
   *
   * <code>repeated int32 scoreRewards = 9;</code>
   */
  int getScoreRewardsCount();
  /**
   * <pre>
   * 本日已领取奖励的模版ID（积分）
   * </pre>
   *
   * <code>repeated int32 scoreRewards = 9;</code>
   */
  int getScoreRewards(int index);

  /**
   * <pre>
   * 挑战对手排名1（低，数值大）
   * </pre>
   *
   * <code>optional .client2server.JjcChallengeInfo challenge1 = 10;</code>
   */
  boolean hasChallenge1();
  /**
   * <pre>
   * 挑战对手排名1（低，数值大）
   * </pre>
   *
   * <code>optional .client2server.JjcChallengeInfo challenge1 = 10;</code>
   */
  pb4client.JjcChallengeInfo getChallenge1();
  /**
   * <pre>
   * 挑战对手排名1（低，数值大）
   * </pre>
   *
   * <code>optional .client2server.JjcChallengeInfo challenge1 = 10;</code>
   */
  pb4client.JjcChallengeInfoOrBuilder getChallenge1OrBuilder();

  /**
   * <pre>
   * 挑战对手排名2（中，数值中）
   * </pre>
   *
   * <code>optional .client2server.JjcChallengeInfo challenge2 = 11;</code>
   */
  boolean hasChallenge2();
  /**
   * <pre>
   * 挑战对手排名2（中，数值中）
   * </pre>
   *
   * <code>optional .client2server.JjcChallengeInfo challenge2 = 11;</code>
   */
  pb4client.JjcChallengeInfo getChallenge2();
  /**
   * <pre>
   * 挑战对手排名2（中，数值中）
   * </pre>
   *
   * <code>optional .client2server.JjcChallengeInfo challenge2 = 11;</code>
   */
  pb4client.JjcChallengeInfoOrBuilder getChallenge2OrBuilder();

  /**
   * <pre>
   * 挑战对手排名3（高，数值小）
   * </pre>
   *
   * <code>optional .client2server.JjcChallengeInfo challenge3 = 12;</code>
   */
  boolean hasChallenge3();
  /**
   * <pre>
   * 挑战对手排名3（高，数值小）
   * </pre>
   *
   * <code>optional .client2server.JjcChallengeInfo challenge3 = 12;</code>
   */
  pb4client.JjcChallengeInfo getChallenge3();
  /**
   * <pre>
   * 挑战对手排名3（高，数值小）
   * </pre>
   *
   * <code>optional .client2server.JjcChallengeInfo challenge3 = 12;</code>
   */
  pb4client.JjcChallengeInfoOrBuilder getChallenge3OrBuilder();

  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroPos heroInfo = 13;</code>
   */
  java.util.List<pb4client.HeroPos> 
      getHeroInfoList();
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroPos heroInfo = 13;</code>
   */
  pb4client.HeroPos getHeroInfo(int index);
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroPos heroInfo = 13;</code>
   */
  int getHeroInfoCount();
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroPos heroInfo = 13;</code>
   */
  java.util.List<? extends pb4client.HeroPosOrBuilder> 
      getHeroInfoOrBuilderList();
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroPos heroInfo = 13;</code>
   */
  pb4client.HeroPosOrBuilder getHeroInfoOrBuilder(
      int index);

  /**
   * <pre>
   * 已领取成就奖励的模版ID
   * </pre>
   *
   * <code>repeated int32 achievementExchangeRewards = 14;</code>
   */
  java.util.List<java.lang.Integer> getAchievementExchangeRewardsList();
  /**
   * <pre>
   * 已领取成就奖励的模版ID
   * </pre>
   *
   * <code>repeated int32 achievementExchangeRewards = 14;</code>
   */
  int getAchievementExchangeRewardsCount();
  /**
   * <pre>
   * 已领取成就奖励的模版ID
   * </pre>
   *
   * <code>repeated int32 achievementExchangeRewards = 14;</code>
   */
  int getAchievementExchangeRewards(int index);

  /**
   * <pre>
   * 前十名
   * </pre>
   *
   * <code>repeated .client2server.JjcChallengeInfo tenRank = 15;</code>
   */
  java.util.List<pb4client.JjcChallengeInfo> 
      getTenRankList();
  /**
   * <pre>
   * 前十名
   * </pre>
   *
   * <code>repeated .client2server.JjcChallengeInfo tenRank = 15;</code>
   */
  pb4client.JjcChallengeInfo getTenRank(int index);
  /**
   * <pre>
   * 前十名
   * </pre>
   *
   * <code>repeated .client2server.JjcChallengeInfo tenRank = 15;</code>
   */
  int getTenRankCount();
  /**
   * <pre>
   * 前十名
   * </pre>
   *
   * <code>repeated .client2server.JjcChallengeInfo tenRank = 15;</code>
   */
  java.util.List<? extends pb4client.JjcChallengeInfoOrBuilder> 
      getTenRankOrBuilderList();
  /**
   * <pre>
   * 前十名
   * </pre>
   *
   * <code>repeated .client2server.JjcChallengeInfo tenRank = 15;</code>
   */
  pb4client.JjcChallengeInfoOrBuilder getTenRankOrBuilder(
      int index);

  /**
   * <pre>
   * 挑战购买次数
   * </pre>
   *
   * <code>optional int32 buyTimes = 16;</code>
   */
  boolean hasBuyTimes();
  /**
   * <pre>
   * 挑战购买次数
   * </pre>
   *
   * <code>optional int32 buyTimes = 16;</code>
   */
  int getBuyTimes();

  /**
   * <pre>
   * 下一次挑战刷新时间
   * </pre>
   *
   * <code>optional int64 nextRefreshTime = 17;</code>
   */
  boolean hasNextRefreshTime();
  /**
   * <pre>
   * 下一次挑战刷新时间
   * </pre>
   *
   * <code>optional int64 nextRefreshTime = 17;</code>
   */
  long getNextRefreshTime();
}
