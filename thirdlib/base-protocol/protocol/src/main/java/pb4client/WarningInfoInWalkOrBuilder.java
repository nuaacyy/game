// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface WarningInfoInWalkOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.WarningInfoInWalk)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional int64 playerId = 2;</code>
   */
  boolean hasPlayerId();
  /**
   * <code>optional int64 playerId = 2;</code>
   */
  long getPlayerId();

  /**
   * <code>optional string playerName = 3;</code>
   */
  boolean hasPlayerName();
  /**
   * <code>optional string playerName = 3;</code>
   */
  java.lang.String getPlayerName();
  /**
   * <code>optional string playerName = 3;</code>
   */
  com.google.protobuf.ByteString
      getPlayerNameBytes();

  /**
   * <pre>
   *头像
   * </pre>
   *
   * <code>optional int32 playerPhoto = 4;</code>
   */
  boolean hasPlayerPhoto();
  /**
   * <pre>
   *头像
   * </pre>
   *
   * <code>optional int32 playerPhoto = 4;</code>
   */
  int getPlayerPhoto();

  /**
   * <code>optional string allianceName = 5;</code>
   */
  boolean hasAllianceName();
  /**
   * <code>optional string allianceName = 5;</code>
   */
  java.lang.String getAllianceName();
  /**
   * <code>optional string allianceName = 5;</code>
   */
  com.google.protobuf.ByteString
      getAllianceNameBytes();

  /**
   * <code>optional string allianceShortName = 6;</code>
   */
  boolean hasAllianceShortName();
  /**
   * <code>optional string allianceShortName = 6;</code>
   */
  java.lang.String getAllianceShortName();
  /**
   * <code>optional string allianceShortName = 6;</code>
   */
  com.google.protobuf.ByteString
      getAllianceShortNameBytes();

  /**
   * <pre>
   *英雄数量
   * </pre>
   *
   * <code>optional int32 heroNum = 7;</code>
   */
  boolean hasHeroNum();
  /**
   * <pre>
   *英雄数量
   * </pre>
   *
   * <code>optional int32 heroNum = 7;</code>
   */
  int getHeroNum();

  /**
   * <pre>
   *士兵估算总量
   * </pre>
   *
   * <code>optional int32 soliderNum = 8;</code>
   */
  boolean hasSoliderNum();
  /**
   * <pre>
   *士兵估算总量
   * </pre>
   *
   * <code>optional int32 soliderNum = 8;</code>
   */
  int getSoliderNum();

  /**
   * <pre>
   *是否君主在部队内
   * </pre>
   *
   * <code>optional int32 isLordInForce = 9;</code>
   */
  boolean hasIsLordInForce();
  /**
   * <pre>
   *是否君主在部队内
   * </pre>
   *
   * <code>optional int32 isLordInForce = 9;</code>
   */
  int getIsLordInForce();

  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroForWalk heros = 10;</code>
   */
  java.util.List<pb4client.HeroForWalk> 
      getHerosList();
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroForWalk heros = 10;</code>
   */
  pb4client.HeroForWalk getHeros(int index);
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroForWalk heros = 10;</code>
   */
  int getHerosCount();
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroForWalk heros = 10;</code>
   */
  java.util.List<? extends pb4client.HeroForWalkOrBuilder> 
      getHerosOrBuilderList();
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroForWalk heros = 10;</code>
   */
  pb4client.HeroForWalkOrBuilder getHerosOrBuilder(
      int index);

  /**
   * <pre>
   *士兵类别分类的估算值
   * </pre>
   *
   * <code>repeated .client2server.SoliderByArmyType soldiersByType = 11;</code>
   */
  java.util.List<pb4client.SoliderByArmyType> 
      getSoldiersByTypeList();
  /**
   * <pre>
   *士兵类别分类的估算值
   * </pre>
   *
   * <code>repeated .client2server.SoliderByArmyType soldiersByType = 11;</code>
   */
  pb4client.SoliderByArmyType getSoldiersByType(int index);
  /**
   * <pre>
   *士兵类别分类的估算值
   * </pre>
   *
   * <code>repeated .client2server.SoliderByArmyType soldiersByType = 11;</code>
   */
  int getSoldiersByTypeCount();
  /**
   * <pre>
   *士兵类别分类的估算值
   * </pre>
   *
   * <code>repeated .client2server.SoliderByArmyType soldiersByType = 11;</code>
   */
  java.util.List<? extends pb4client.SoliderByArmyTypeOrBuilder> 
      getSoldiersByTypeOrBuilderList();
  /**
   * <pre>
   *士兵类别分类的估算值
   * </pre>
   *
   * <code>repeated .client2server.SoliderByArmyType soldiersByType = 11;</code>
   */
  pb4client.SoliderByArmyTypeOrBuilder getSoldiersByTypeOrBuilder(
      int index);

  /**
   * <pre>
   *士兵估算量
   * </pre>
   *
   * <code>repeated .client2server.SoliderForWalk vagueSoliders = 12;</code>
   */
  java.util.List<pb4client.SoliderForWalk> 
      getVagueSolidersList();
  /**
   * <pre>
   *士兵估算量
   * </pre>
   *
   * <code>repeated .client2server.SoliderForWalk vagueSoliders = 12;</code>
   */
  pb4client.SoliderForWalk getVagueSoliders(int index);
  /**
   * <pre>
   *士兵估算量
   * </pre>
   *
   * <code>repeated .client2server.SoliderForWalk vagueSoliders = 12;</code>
   */
  int getVagueSolidersCount();
  /**
   * <pre>
   *士兵估算量
   * </pre>
   *
   * <code>repeated .client2server.SoliderForWalk vagueSoliders = 12;</code>
   */
  java.util.List<? extends pb4client.SoliderForWalkOrBuilder> 
      getVagueSolidersOrBuilderList();
  /**
   * <pre>
   *士兵估算量
   * </pre>
   *
   * <code>repeated .client2server.SoliderForWalk vagueSoliders = 12;</code>
   */
  pb4client.SoliderForWalkOrBuilder getVagueSolidersOrBuilder(
      int index);

  /**
   * <pre>
   *士兵精确量
   * </pre>
   *
   * <code>repeated .client2server.SoliderForWalk exactSoliders = 13;</code>
   */
  java.util.List<pb4client.SoliderForWalk> 
      getExactSolidersList();
  /**
   * <pre>
   *士兵精确量
   * </pre>
   *
   * <code>repeated .client2server.SoliderForWalk exactSoliders = 13;</code>
   */
  pb4client.SoliderForWalk getExactSoliders(int index);
  /**
   * <pre>
   *士兵精确量
   * </pre>
   *
   * <code>repeated .client2server.SoliderForWalk exactSoliders = 13;</code>
   */
  int getExactSolidersCount();
  /**
   * <pre>
   *士兵精确量
   * </pre>
   *
   * <code>repeated .client2server.SoliderForWalk exactSoliders = 13;</code>
   */
  java.util.List<? extends pb4client.SoliderForWalkOrBuilder> 
      getExactSolidersOrBuilderList();
  /**
   * <pre>
   *士兵精确量
   * </pre>
   *
   * <code>repeated .client2server.SoliderForWalk exactSoliders = 13;</code>
   */
  pb4client.SoliderForWalkOrBuilder getExactSolidersOrBuilder(
      int index);
}
