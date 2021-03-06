// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface AllianceExchangeDemandInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.AllianceExchangeDemandInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *需求ID（每个换卡需求的唯一ID）
   * </pre>
   *
   * <code>required int64 demandId = 1;</code>
   */
  boolean hasDemandId();
  /**
   * <pre>
   *需求ID（每个换卡需求的唯一ID）
   * </pre>
   *
   * <code>required int64 demandId = 1;</code>
   */
  long getDemandId();

  /**
   * <pre>
   *玩家ID
   * </pre>
   *
   * <code>required int64 playerId = 2;</code>
   */
  boolean hasPlayerId();
  /**
   * <pre>
   *玩家ID
   * </pre>
   *
   * <code>required int64 playerId = 2;</code>
   */
  long getPlayerId();

  /**
   * <pre>
   *玩家名称
   * </pre>
   *
   * <code>required string playerName = 3;</code>
   */
  boolean hasPlayerName();
  /**
   * <pre>
   *玩家名称
   * </pre>
   *
   * <code>required string playerName = 3;</code>
   */
  java.lang.String getPlayerName();
  /**
   * <pre>
   *玩家名称
   * </pre>
   *
   * <code>required string playerName = 3;</code>
   */
  com.google.protobuf.ByteString
      getPlayerNameBytes();

  /**
   * <pre>
   *职位
   * </pre>
   *
   * <code>repeated int32 positions = 4;</code>
   */
  java.util.List<java.lang.Integer> getPositionsList();
  /**
   * <pre>
   *职位
   * </pre>
   *
   * <code>repeated int32 positions = 4;</code>
   */
  int getPositionsCount();
  /**
   * <pre>
   *职位
   * </pre>
   *
   * <code>repeated int32 positions = 4;</code>
   */
  int getPositions(int index);

  /**
   * <pre>
   *结束时间
   * </pre>
   *
   * <code>required int32 endTime = 5;</code>
   */
  boolean hasEndTime();
  /**
   * <pre>
   *结束时间
   * </pre>
   *
   * <code>required int32 endTime = 5;</code>
   */
  int getEndTime();

  /**
   * <pre>
   *模板ID
   * </pre>
   *
   * <code>required int32 protoId = 6;</code>
   */
  boolean hasProtoId();
  /**
   * <pre>
   *模板ID
   * </pre>
   *
   * <code>required int32 protoId = 6;</code>
   */
  int getProtoId();

  /**
   * <pre>
   *背包中拥有的卡牌数量
   * </pre>
   *
   * <code>required int32 ownQty = 7;</code>
   */
  boolean hasOwnQty();
  /**
   * <pre>
   *背包中拥有的卡牌数量
   * </pre>
   *
   * <code>required int32 ownQty = 7;</code>
   */
  int getOwnQty();

  /**
   * <pre>
   *捐献卡牌：玩家捐献数
   * </pre>
   *
   * <code>required int32 donateQty = 8;</code>
   */
  boolean hasDonateQty();
  /**
   * <pre>
   *捐献卡牌：玩家捐献数
   * </pre>
   *
   * <code>required int32 donateQty = 8;</code>
   */
  int getDonateQty();

  /**
   * <pre>
   *收集进度：当前对方收集数
   * </pre>
   *
   * <code>required int32 nowQty = 9;</code>
   */
  boolean hasNowQty();
  /**
   * <pre>
   *收集进度：当前对方收集数
   * </pre>
   *
   * <code>required int32 nowQty = 9;</code>
   */
  int getNowQty();

  /**
   * <pre>
   *收集目标数量
   * </pre>
   *
   * <code>required int32 targetNum = 10;</code>
   */
  boolean hasTargetNum();
  /**
   * <pre>
   *收集目标数量
   * </pre>
   *
   * <code>required int32 targetNum = 10;</code>
   */
  int getTargetNum();
}
