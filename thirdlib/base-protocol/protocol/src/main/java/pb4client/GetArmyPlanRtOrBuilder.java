// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface GetArmyPlanRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.GetArmyPlanRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int32 rt = 1;</code>
   */
  boolean hasRt();
  /**
   * <code>required int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
   */
  java.util.List<pb4client.HeroPos> 
      getHeroInfoList();
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
   */
  pb4client.HeroPos getHeroInfo(int index);
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
   */
  int getHeroInfoCount();
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
   */
  java.util.List<? extends pb4client.HeroPosOrBuilder> 
      getHeroInfoOrBuilderList();
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
   */
  pb4client.HeroPosOrBuilder getHeroInfoOrBuilder(
      int index);
}
