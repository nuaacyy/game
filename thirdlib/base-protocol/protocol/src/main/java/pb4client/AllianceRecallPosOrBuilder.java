// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface AllianceRecallPosOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.AllianceRecallPos)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *罢免玩家ID
   * </pre>
   *
   * <code>required int64 setPlayerId = 1;</code>
   */
  boolean hasSetPlayerId();
  /**
   * <pre>
   *罢免玩家ID
   * </pre>
   *
   * <code>required int64 setPlayerId = 1;</code>
   */
  long getSetPlayerId();

  /**
   * <pre>
   *罢免职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
   * </pre>
   *
   * <code>required int32 position = 2;</code>
   */
  boolean hasPosition();
  /**
   * <pre>
   *罢免职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
   * </pre>
   *
   * <code>required int32 position = 2;</code>
   */
  int getPosition();
}
