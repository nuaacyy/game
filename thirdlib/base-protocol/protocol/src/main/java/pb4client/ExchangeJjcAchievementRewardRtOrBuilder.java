// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface ExchangeJjcAchievementRewardRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.ExchangeJjcAchievementRewardRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  boolean hasRt();
  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <pre>
   * 奖励模版ID
   * </pre>
   *
   * <code>repeated int32 ids = 2;</code>
   */
  java.util.List<java.lang.Integer> getIdsList();
  /**
   * <pre>
   * 奖励模版ID
   * </pre>
   *
   * <code>repeated int32 ids = 2;</code>
   */
  int getIdsCount();
  /**
   * <pre>
   * 奖励模版ID
   * </pre>
   *
   * <code>repeated int32 ids = 2;</code>
   */
  int getIds(int index);
}
