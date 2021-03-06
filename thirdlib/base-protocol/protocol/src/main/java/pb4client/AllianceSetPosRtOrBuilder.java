// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface AllianceSetPosRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.AllianceSetPosRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *返回值：832-没有任命权限；833-该职位玩家已达上限；834-盟主转让失败：盟主只能转让给副盟主；899-没有操作权限
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  boolean hasRt();
  /**
   * <pre>
   *返回值：832-没有任命权限；833-该职位玩家已达上限；834-盟主转让失败：盟主只能转让给副盟主；899-没有操作权限
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <pre>
   *任命玩家ID
   * </pre>
   *
   * <code>optional int64 setPlayerId = 2;</code>
   */
  boolean hasSetPlayerId();
  /**
   * <pre>
   *任命玩家ID
   * </pre>
   *
   * <code>optional int64 setPlayerId = 2;</code>
   */
  long getSetPlayerId();

  /**
   * <pre>
   *任命后的职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
   * </pre>
   *
   * <code>repeated int32 positions = 3;</code>
   */
  java.util.List<java.lang.Integer> getPositionsList();
  /**
   * <pre>
   *任命后的职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
   * </pre>
   *
   * <code>repeated int32 positions = 3;</code>
   */
  int getPositionsCount();
  /**
   * <pre>
   *任命后的职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
   * </pre>
   *
   * <code>repeated int32 positions = 3;</code>
   */
  int getPositions(int index);
}
