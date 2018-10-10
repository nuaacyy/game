// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface StartMassOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.StartMass)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *加入集结时间
   * </pre>
   *
   * <code>required int32 joinMassTime = 1;</code>
   */
  boolean hasJoinMassTime();
  /**
   * <pre>
   *加入集结时间
   * </pre>
   *
   * <code>required int32 joinMassTime = 1;</code>
   */
  int getJoinMassTime();

  /**
   * <pre>
   *打人、占领城、打遗迹
   * </pre>
   *
   * <code>required int32 runType = 2;</code>
   */
  boolean hasRunType();
  /**
   * <pre>
   *打人、占领城、打遗迹
   * </pre>
   *
   * <code>required int32 runType = 2;</code>
   */
  int getRunType();

  /**
   * <pre>
   *目的地X
   * </pre>
   *
   * <code>required int32 aimsX = 3;</code>
   */
  boolean hasAimsX();
  /**
   * <pre>
   *目的地X
   * </pre>
   *
   * <code>required int32 aimsX = 3;</code>
   */
  int getAimsX();

  /**
   * <pre>
   *目的地Y
   * </pre>
   *
   * <code>required int32 aimsY = 4;</code>
   */
  boolean hasAimsY();
  /**
   * <pre>
   *目的地Y
   * </pre>
   *
   * <code>required int32 aimsY = 4;</code>
   */
  int getAimsY();

  /**
   * <pre>
   *集结军名称
   * </pre>
   *
   * <code>required string massName = 5;</code>
   */
  boolean hasMassName();
  /**
   * <pre>
   *集结军名称
   * </pre>
   *
   * <code>required string massName = 5;</code>
   */
  java.lang.String getMassName();
  /**
   * <pre>
   *集结军名称
   * </pre>
   *
   * <code>required string massName = 5;</code>
   */
  com.google.protobuf.ByteString
      getMassNameBytes();

  /**
   * <code>repeated int64 heroIds = 6;</code>
   */
  java.util.List<java.lang.Long> getHeroIdsList();
  /**
   * <code>repeated int64 heroIds = 6;</code>
   */
  int getHeroIdsCount();
  /**
   * <code>repeated int64 heroIds = 6;</code>
   */
  long getHeroIds(int index);

  /**
   * <code>repeated .client2server.BattleSolider soliders = 7;</code>
   */
  java.util.List<pb4client.BattleSolider> 
      getSolidersList();
  /**
   * <code>repeated .client2server.BattleSolider soliders = 7;</code>
   */
  pb4client.BattleSolider getSoliders(int index);
  /**
   * <code>repeated .client2server.BattleSolider soliders = 7;</code>
   */
  int getSolidersCount();
  /**
   * <code>repeated .client2server.BattleSolider soliders = 7;</code>
   */
  java.util.List<? extends pb4client.BattleSoliderOrBuilder> 
      getSolidersOrBuilderList();
  /**
   * <code>repeated .client2server.BattleSolider soliders = 7;</code>
   */
  pb4client.BattleSoliderOrBuilder getSolidersOrBuilder(
      int index);
}