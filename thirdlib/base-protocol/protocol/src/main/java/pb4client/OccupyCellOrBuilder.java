// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface OccupyCellOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.OccupyCell)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *玩家城信息
   * </pre>
   *
   * <code>required .client2server.CastleCell castle = 1;</code>
   */
  boolean hasCastle();
  /**
   * <pre>
   *玩家城信息
   * </pre>
   *
   * <code>required .client2server.CastleCell castle = 1;</code>
   */
  pb4client.CastleCell getCastle();
  /**
   * <pre>
   *玩家城信息
   * </pre>
   *
   * <code>required .client2server.CastleCell castle = 1;</code>
   */
  pb4client.CastleCellOrBuilder getCastleOrBuilder();

  /**
   * <pre>
   *行军组Id
   * </pre>
   *
   * <code>required int64 groupId = 2;</code>
   */
  boolean hasGroupId();
  /**
   * <pre>
   *行军组Id
   * </pre>
   *
   * <code>required int64 groupId = 2;</code>
   */
  long getGroupId();

  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroForWalk heros = 3;</code>
   */
  java.util.List<pb4client.HeroForWalk> 
      getHerosList();
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroForWalk heros = 3;</code>
   */
  pb4client.HeroForWalk getHeros(int index);
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroForWalk heros = 3;</code>
   */
  int getHerosCount();
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroForWalk heros = 3;</code>
   */
  java.util.List<? extends pb4client.HeroForWalkOrBuilder> 
      getHerosOrBuilderList();
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroForWalk heros = 3;</code>
   */
  pb4client.HeroForWalkOrBuilder getHerosOrBuilder(
      int index);

  /**
   * <pre>
   *士兵信息
   * </pre>
   *
   * <code>repeated .client2server.SoliderForWalk soliders = 4;</code>
   */
  java.util.List<pb4client.SoliderForWalk> 
      getSolidersList();
  /**
   * <pre>
   *士兵信息
   * </pre>
   *
   * <code>repeated .client2server.SoliderForWalk soliders = 4;</code>
   */
  pb4client.SoliderForWalk getSoliders(int index);
  /**
   * <pre>
   *士兵信息
   * </pre>
   *
   * <code>repeated .client2server.SoliderForWalk soliders = 4;</code>
   */
  int getSolidersCount();
  /**
   * <pre>
   *士兵信息
   * </pre>
   *
   * <code>repeated .client2server.SoliderForWalk soliders = 4;</code>
   */
  java.util.List<? extends pb4client.SoliderForWalkOrBuilder> 
      getSolidersOrBuilderList();
  /**
   * <pre>
   *士兵信息
   * </pre>
   *
   * <code>repeated .client2server.SoliderForWalk soliders = 4;</code>
   */
  pb4client.SoliderForWalkOrBuilder getSolidersOrBuilder(
      int index);
}