// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface MassRuinsFightReportOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.MassRuinsFightReport)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 战斗结果  1-成功  2-失败
   * </pre>
   *
   * <code>required int32 fightResult = 2;</code>
   */
  boolean hasFightResult();
  /**
   * <pre>
   * 战斗结果  1-成功  2-失败
   * </pre>
   *
   * <code>required int32 fightResult = 2;</code>
   */
  int getFightResult();

  /**
   * <pre>
   * 遗迹Id
   * </pre>
   *
   * <code>required int32 relicId = 3;</code>
   */
  boolean hasRelicId();
  /**
   * <pre>
   * 遗迹Id
   * </pre>
   *
   * <code>required int32 relicId = 3;</code>
   */
  int getRelicId();

  /**
   * <pre>
   * 集结攻击方战斗数据
   * </pre>
   *
   * <code>repeated .client2server.FightInfoForReport atkFightInfo = 4;</code>
   */
  java.util.List<pb4client.FightInfoForReport> 
      getAtkFightInfoList();
  /**
   * <pre>
   * 集结攻击方战斗数据
   * </pre>
   *
   * <code>repeated .client2server.FightInfoForReport atkFightInfo = 4;</code>
   */
  pb4client.FightInfoForReport getAtkFightInfo(int index);
  /**
   * <pre>
   * 集结攻击方战斗数据
   * </pre>
   *
   * <code>repeated .client2server.FightInfoForReport atkFightInfo = 4;</code>
   */
  int getAtkFightInfoCount();
  /**
   * <pre>
   * 集结攻击方战斗数据
   * </pre>
   *
   * <code>repeated .client2server.FightInfoForReport atkFightInfo = 4;</code>
   */
  java.util.List<? extends pb4client.FightInfoForReportOrBuilder> 
      getAtkFightInfoOrBuilderList();
  /**
   * <pre>
   * 集结攻击方战斗数据
   * </pre>
   *
   * <code>repeated .client2server.FightInfoForReport atkFightInfo = 4;</code>
   */
  pb4client.FightInfoForReportOrBuilder getAtkFightInfoOrBuilder(
      int index);

  /**
   * <pre>
   * 集结防守方战斗数据
   * </pre>
   *
   * <code>repeated .client2server.FightInfoForReport defFightInfo = 5;</code>
   */
  java.util.List<pb4client.FightInfoForReport> 
      getDefFightInfoList();
  /**
   * <pre>
   * 集结防守方战斗数据
   * </pre>
   *
   * <code>repeated .client2server.FightInfoForReport defFightInfo = 5;</code>
   */
  pb4client.FightInfoForReport getDefFightInfo(int index);
  /**
   * <pre>
   * 集结防守方战斗数据
   * </pre>
   *
   * <code>repeated .client2server.FightInfoForReport defFightInfo = 5;</code>
   */
  int getDefFightInfoCount();
  /**
   * <pre>
   * 集结防守方战斗数据
   * </pre>
   *
   * <code>repeated .client2server.FightInfoForReport defFightInfo = 5;</code>
   */
  java.util.List<? extends pb4client.FightInfoForReportOrBuilder> 
      getDefFightInfoOrBuilderList();
  /**
   * <pre>
   * 集结防守方战斗数据
   * </pre>
   *
   * <code>repeated .client2server.FightInfoForReport defFightInfo = 5;</code>
   */
  pb4client.FightInfoForReportOrBuilder getDefFightInfoOrBuilder(
      int index);

  /**
   * <pre>
   * 奖励信息
   * </pre>
   *
   * <code>optional .client2server.RewardInfoForReport reward = 6;</code>
   */
  boolean hasReward();
  /**
   * <pre>
   * 奖励信息
   * </pre>
   *
   * <code>optional .client2server.RewardInfoForReport reward = 6;</code>
   */
  pb4client.RewardInfoForReport getReward();
  /**
   * <pre>
   * 奖励信息
   * </pre>
   *
   * <code>optional .client2server.RewardInfoForReport reward = 6;</code>
   */
  pb4client.RewardInfoForReportOrBuilder getRewardOrBuilder();

  /**
   * <pre>
   * 攻击方英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroInfoForReport atkHeros = 7;</code>
   */
  java.util.List<pb4client.HeroInfoForReport> 
      getAtkHerosList();
  /**
   * <pre>
   * 攻击方英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroInfoForReport atkHeros = 7;</code>
   */
  pb4client.HeroInfoForReport getAtkHeros(int index);
  /**
   * <pre>
   * 攻击方英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroInfoForReport atkHeros = 7;</code>
   */
  int getAtkHerosCount();
  /**
   * <pre>
   * 攻击方英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroInfoForReport atkHeros = 7;</code>
   */
  java.util.List<? extends pb4client.HeroInfoForReportOrBuilder> 
      getAtkHerosOrBuilderList();
  /**
   * <pre>
   * 攻击方英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroInfoForReport atkHeros = 7;</code>
   */
  pb4client.HeroInfoForReportOrBuilder getAtkHerosOrBuilder(
      int index);

  /**
   * <pre>
   * 防守方英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroInfoForReport defHeros = 8;</code>
   */
  java.util.List<pb4client.HeroInfoForReport> 
      getDefHerosList();
  /**
   * <pre>
   * 防守方英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroInfoForReport defHeros = 8;</code>
   */
  pb4client.HeroInfoForReport getDefHeros(int index);
  /**
   * <pre>
   * 防守方英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroInfoForReport defHeros = 8;</code>
   */
  int getDefHerosCount();
  /**
   * <pre>
   * 防守方英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroInfoForReport defHeros = 8;</code>
   */
  java.util.List<? extends pb4client.HeroInfoForReportOrBuilder> 
      getDefHerosOrBuilderList();
  /**
   * <pre>
   * 防守方英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroInfoForReport defHeros = 8;</code>
   */
  pb4client.HeroInfoForReportOrBuilder getDefHerosOrBuilder(
      int index);

  /**
   * <pre>
   * 回复量
   * </pre>
   *
   * <code>required int32 recoveryNum = 9;</code>
   */
  boolean hasRecoveryNum();
  /**
   * <pre>
   * 回复量
   * </pre>
   *
   * <code>required int32 recoveryNum = 9;</code>
   */
  int getRecoveryNum();

  /**
   * <pre>
   *添加时光之盒奖励结果
   * </pre>
   *
   * <code>required int32 addRelicBoxResult = 10;</code>
   */
  boolean hasAddRelicBoxResult();
  /**
   * <pre>
   *添加时光之盒奖励结果
   * </pre>
   *
   * <code>required int32 addRelicBoxResult = 10;</code>
   */
  int getAddRelicBoxResult();
}
