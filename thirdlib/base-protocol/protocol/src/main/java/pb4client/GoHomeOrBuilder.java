// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface GoHomeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.GoHome)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *回城时间
   * </pre>
   *
   * <code>required int32 walkTime = 1;</code>
   */
  boolean hasWalkTime();
  /**
   * <pre>
   *回城时间
   * </pre>
   *
   * <code>required int32 walkTime = 1;</code>
   */
  int getWalkTime();

  /**
   * <pre>
   *回城部队
   * </pre>
   *
   * <code>required int64 forceId = 2;</code>
   */
  boolean hasForceId();
  /**
   * <pre>
   *回城部队
   * </pre>
   *
   * <code>required int64 forceId = 2;</code>
   */
  long getForceId();

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
   *出发地X
   * </pre>
   *
   * <code>required int32 startX = 5;</code>
   */
  boolean hasStartX();
  /**
   * <pre>
   *出发地X
   * </pre>
   *
   * <code>required int32 startX = 5;</code>
   */
  int getStartX();

  /**
   * <pre>
   *出发地Y
   * </pre>
   *
   * <code>required int32 startY = 6;</code>
   */
  boolean hasStartY();
  /**
   * <pre>
   *出发地Y
   * </pre>
   *
   * <code>required int32 startY = 6;</code>
   */
  int getStartY();

  /**
   * <pre>
   *半路折回取消行军的回城时间
   * </pre>
   *
   * <code>required int32 cancelWalkTime = 7;</code>
   */
  boolean hasCancelWalkTime();
  /**
   * <pre>
   *半路折回取消行军的回城时间
   * </pre>
   *
   * <code>required int32 cancelWalkTime = 7;</code>
   */
  int getCancelWalkTime();

  /**
   * <pre>
   *需要的总时间
   * </pre>
   *
   * <code>required int32 userWalkTime = 8;</code>
   */
  boolean hasUserWalkTime();
  /**
   * <pre>
   *需要的总时间
   * </pre>
   *
   * <code>required int32 userWalkTime = 8;</code>
   */
  int getUserWalkTime();

  /**
   * <pre>
   *部队隶属军团ID
   * </pre>
   *
   * <code>required int64 armyGroupId = 9;</code>
   */
  boolean hasArmyGroupId();
  /**
   * <pre>
   *部队隶属军团ID
   * </pre>
   *
   * <code>required int64 armyGroupId = 9;</code>
   */
  long getArmyGroupId();

  /**
   * <pre>
   * 部队兵种
   * </pre>
   *
   * <code>required int32 forceArmy = 10;</code>
   */
  boolean hasForceArmy();
  /**
   * <pre>
   * 部队兵种
   * </pre>
   *
   * <code>required int32 forceArmy = 10;</code>
   */
  int getForceArmy();
}