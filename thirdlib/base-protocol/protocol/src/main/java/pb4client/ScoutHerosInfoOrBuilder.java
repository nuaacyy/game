// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface ScoutHerosInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.ScoutHerosInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 英雄数量
   * </pre>
   *
   * <code>required int32 herosNum = 1;</code>
   */
  boolean hasHerosNum();
  /**
   * <pre>
   * 英雄数量
   * </pre>
   *
   * <code>required int32 herosNum = 1;</code>
   */
  int getHerosNum();

  /**
   * <pre>
   * 英雄详情 侦察等级没到的话没有
   * </pre>
   *
   * <code>repeated .client2server.HeroInfoForReport heros = 2;</code>
   */
  java.util.List<pb4client.HeroInfoForReport> 
      getHerosList();
  /**
   * <pre>
   * 英雄详情 侦察等级没到的话没有
   * </pre>
   *
   * <code>repeated .client2server.HeroInfoForReport heros = 2;</code>
   */
  pb4client.HeroInfoForReport getHeros(int index);
  /**
   * <pre>
   * 英雄详情 侦察等级没到的话没有
   * </pre>
   *
   * <code>repeated .client2server.HeroInfoForReport heros = 2;</code>
   */
  int getHerosCount();
  /**
   * <pre>
   * 英雄详情 侦察等级没到的话没有
   * </pre>
   *
   * <code>repeated .client2server.HeroInfoForReport heros = 2;</code>
   */
  java.util.List<? extends pb4client.HeroInfoForReportOrBuilder> 
      getHerosOrBuilderList();
  /**
   * <pre>
   * 英雄详情 侦察等级没到的话没有
   * </pre>
   *
   * <code>repeated .client2server.HeroInfoForReport heros = 2;</code>
   */
  pb4client.HeroInfoForReportOrBuilder getHerosOrBuilder(
      int index);
}
