// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface SelectJjcDefForceRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.SelectJjcDefForceRt)
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
   *机器人的配置,unitTeam表ID
   * </pre>
   *
   * <code>optional int32 jjcDefForceNpcTeamId = 3;</code>
   */
  boolean hasJjcDefForceNpcTeamId();
  /**
   * <pre>
   *机器人的配置,unitTeam表ID
   * </pre>
   *
   * <code>optional int32 jjcDefForceNpcTeamId = 3;</code>
   */
  int getJjcDefForceNpcTeamId();

  /**
   * <pre>
   * 对方武将数据
   * </pre>
   *
   * <code>repeated .client2server.JjcHeroGenral jjcHeros = 4;</code>
   */
  java.util.List<pb4client.JjcHeroGenral> 
      getJjcHerosList();
  /**
   * <pre>
   * 对方武将数据
   * </pre>
   *
   * <code>repeated .client2server.JjcHeroGenral jjcHeros = 4;</code>
   */
  pb4client.JjcHeroGenral getJjcHeros(int index);
  /**
   * <pre>
   * 对方武将数据
   * </pre>
   *
   * <code>repeated .client2server.JjcHeroGenral jjcHeros = 4;</code>
   */
  int getJjcHerosCount();
  /**
   * <pre>
   * 对方武将数据
   * </pre>
   *
   * <code>repeated .client2server.JjcHeroGenral jjcHeros = 4;</code>
   */
  java.util.List<? extends pb4client.JjcHeroGenralOrBuilder> 
      getJjcHerosOrBuilderList();
  /**
   * <pre>
   * 对方武将数据
   * </pre>
   *
   * <code>repeated .client2server.JjcHeroGenral jjcHeros = 4;</code>
   */
  pb4client.JjcHeroGenralOrBuilder getJjcHerosOrBuilder(
      int index);

  /**
   * <pre>
   *战斗力
   * </pre>
   *
   * <code>optional int64 fightValue = 5;</code>
   */
  boolean hasFightValue();
  /**
   * <pre>
   *战斗力
   * </pre>
   *
   * <code>optional int64 fightValue = 5;</code>
   */
  long getFightValue();

  /**
   * <pre>
   *玩家Id
   * </pre>
   *
   * <code>optional int64 defPlayerId = 6;</code>
   */
  boolean hasDefPlayerId();
  /**
   * <pre>
   *玩家Id
   * </pre>
   *
   * <code>optional int64 defPlayerId = 6;</code>
   */
  long getDefPlayerId();
}
