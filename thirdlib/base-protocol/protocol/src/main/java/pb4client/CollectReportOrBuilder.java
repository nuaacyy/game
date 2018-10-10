// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface CollectReportOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.CollectReport)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *获得的资源
   * </pre>
   *
   * <code>required string resVo = 1;</code>
   */
  boolean hasResVo();
  /**
   * <pre>
   *获得的资源
   * </pre>
   *
   * <code>required string resVo = 1;</code>
   */
  java.lang.String getResVo();
  /**
   * <pre>
   *获得的资源
   * </pre>
   *
   * <code>required string resVo = 1;</code>
   */
  com.google.protobuf.ByteString
      getResVoBytes();

  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroInfoForReport heros = 2;</code>
   */
  java.util.List<pb4client.HeroInfoForReport> 
      getHerosList();
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroInfoForReport heros = 2;</code>
   */
  pb4client.HeroInfoForReport getHeros(int index);
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroInfoForReport heros = 2;</code>
   */
  int getHerosCount();
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroInfoForReport heros = 2;</code>
   */
  java.util.List<? extends pb4client.HeroInfoForReportOrBuilder> 
      getHerosOrBuilderList();
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroInfoForReport heros = 2;</code>
   */
  pb4client.HeroInfoForReportOrBuilder getHerosOrBuilder(
      int index);

  /**
   * <pre>
   *资源类别
   * </pre>
   *
   * <code>required int32 resType = 3;</code>
   */
  boolean hasResType();
  /**
   * <pre>
   *资源类别
   * </pre>
   *
   * <code>required int32 resType = 3;</code>
   */
  int getResType();

  /**
   * <pre>
   *资源点等级
   * </pre>
   *
   * <code>required int32 resLv = 4;</code>
   */
  boolean hasResLv();
  /**
   * <pre>
   *资源点等级
   * </pre>
   *
   * <code>required int32 resLv = 4;</code>
   */
  int getResLv();
}
