// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

public interface HunterInviteInfoVoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.HunterInviteInfoVo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *邀请Id
   * </pre>
   *
   * <code>int64 inviteId = 1;</code>
   */
  long getInviteId();

  /**
   * <pre>
   *邀请者Id
   * </pre>
   *
   * <code>int64 invitePlayerId = 2;</code>
   */
  long getInvitePlayerId();

  /**
   * <pre>
   *魔物配置Id
   * </pre>
   *
   * <code>int32 bossId = 3;</code>
   */
  int getBossId();

  /**
   * <pre>
   *当前血量
   * </pre>
   *
   * <code>int32 nowHp = 4;</code>
   */
  int getNowHp();

  /**
   * <pre>
   *已猎杀的同盟人数
   * </pre>
   *
   * <code>int32 haveHunterNum = 5;</code>
   */
  int getHaveHunterNum();

  /**
   * <pre>
   *区服Id
   * </pre>
   *
   * <code>int64 pltAreaNo = 6;</code>
   */
  long getPltAreaNo();

  /**
   * <pre>
   *坐标x
   * </pre>
   *
   * <code>int32 posX = 7;</code>
   */
  int getPosX();

  /**
   * <pre>
   *坐标Y
   * </pre>
   *
   * <code>int32 posY = 8;</code>
   */
  int getPosY();
}
