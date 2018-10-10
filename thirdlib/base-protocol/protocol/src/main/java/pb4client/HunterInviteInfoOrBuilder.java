// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface HunterInviteInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.HunterInviteInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *邀请Id
   * </pre>
   *
   * <code>optional int64 inviteId = 1;</code>
   */
  boolean hasInviteId();
  /**
   * <pre>
   *邀请Id
   * </pre>
   *
   * <code>optional int64 inviteId = 1;</code>
   */
  long getInviteId();

  /**
   * <pre>
   *最新邀请时间
   * </pre>
   *
   * <code>optional int64 inviteTime = 2;</code>
   */
  boolean hasInviteTime();
  /**
   * <pre>
   *最新邀请时间
   * </pre>
   *
   * <code>optional int64 inviteTime = 2;</code>
   */
  long getInviteTime();

  /**
   * <pre>
   *魔物配置Id
   * </pre>
   *
   * <code>optional int32 bossId = 3;</code>
   */
  boolean hasBossId();
  /**
   * <pre>
   *魔物配置Id
   * </pre>
   *
   * <code>optional int32 bossId = 3;</code>
   */
  int getBossId();

  /**
   * <pre>
   *当前血量
   * </pre>
   *
   * <code>optional int32 nowHp = 4;</code>
   */
  boolean hasNowHp();
  /**
   * <pre>
   *当前血量
   * </pre>
   *
   * <code>optional int32 nowHp = 4;</code>
   */
  int getNowHp();

  /**
   * <pre>
   *已猎杀的同盟人数
   * </pre>
   *
   * <code>optional int32 haveHunterNum = 5;</code>
   */
  boolean hasHaveHunterNum();
  /**
   * <pre>
   *已猎杀的同盟人数
   * </pre>
   *
   * <code>optional int32 haveHunterNum = 5;</code>
   */
  int getHaveHunterNum();

  /**
   * <pre>
   *区服Id
   * </pre>
   *
   * <code>required int64 pltAreaNo = 6;</code>
   */
  boolean hasPltAreaNo();
  /**
   * <pre>
   *区服Id
   * </pre>
   *
   * <code>required int64 pltAreaNo = 6;</code>
   */
  long getPltAreaNo();

  /**
   * <pre>
   *坐标x
   * </pre>
   *
   * <code>required int32 posX = 7;</code>
   */
  boolean hasPosX();
  /**
   * <pre>
   *坐标x
   * </pre>
   *
   * <code>required int32 posX = 7;</code>
   */
  int getPosX();

  /**
   * <pre>
   *坐标Y
   * </pre>
   *
   * <code>required int32 posY = 8;</code>
   */
  boolean hasPosY();
  /**
   * <pre>
   *坐标Y
   * </pre>
   *
   * <code>required int32 posY = 8;</code>
   */
  int getPosY();
}