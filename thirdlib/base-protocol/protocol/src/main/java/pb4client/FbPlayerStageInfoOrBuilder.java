// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface FbPlayerStageInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.FbPlayerStageInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *关卡编号
   * </pre>
   *
   * <code>required int32 stageNo = 1;</code>
   */
  boolean hasStageNo();
  /**
   * <pre>
   *关卡编号
   * </pre>
   *
   * <code>required int32 stageNo = 1;</code>
   */
  int getStageNo();

  /**
   * <pre>
   *难度等级:1~简单;2~普通;3~困难
   * </pre>
   *
   * <code>required int32 level = 2;</code>
   */
  boolean hasLevel();
  /**
   * <pre>
   *难度等级:1~简单;2~普通;3~困难
   * </pre>
   *
   * <code>required int32 level = 2;</code>
   */
  int getLevel();

  /**
   * <pre>
   *星级判定:0~尚未通关;1~一星通关;2~二星通关;3~三星通关
   * </pre>
   *
   * <code>required int32 star = 3;</code>
   */
  boolean hasStar();
  /**
   * <pre>
   *星级判定:0~尚未通关;1~一星通关;2~二星通关;3~三星通关
   * </pre>
   *
   * <code>required int32 star = 3;</code>
   */
  int getStar();

  /**
   * <pre>
   *最佳通关玩家ID
   * </pre>
   *
   * <code>required int64 bestPlayerId = 4;</code>
   */
  boolean hasBestPlayerId();
  /**
   * <pre>
   *最佳通关玩家ID
   * </pre>
   *
   * <code>required int64 bestPlayerId = 4;</code>
   */
  long getBestPlayerId();

  /**
   * <pre>
   *最佳通关玩家名称
   * </pre>
   *
   * <code>required string bestPlayerName = 5;</code>
   */
  boolean hasBestPlayerName();
  /**
   * <pre>
   *最佳通关玩家名称
   * </pre>
   *
   * <code>required string bestPlayerName = 5;</code>
   */
  java.lang.String getBestPlayerName();
  /**
   * <pre>
   *最佳通关玩家名称
   * </pre>
   *
   * <code>required string bestPlayerName = 5;</code>
   */
  com.google.protobuf.ByteString
      getBestPlayerNameBytes();

  /**
   * <pre>
   *最佳通关战损：累计死兵
   * </pre>
   *
   * <code>required int32 bestDeadSoldier = 6;</code>
   */
  boolean hasBestDeadSoldier();
  /**
   * <pre>
   *最佳通关战损：累计死兵
   * </pre>
   *
   * <code>required int32 bestDeadSoldier = 6;</code>
   */
  int getBestDeadSoldier();
}