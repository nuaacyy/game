// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface FightTowerRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.FightTowerRt)
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
   *本次挑战的所有简单战报ID集合
   * </pre>
   *
   * <code>repeated int64 fightInfoIds = 2;</code>
   */
  java.util.List<java.lang.Long> getFightInfoIdsList();
  /**
   * <pre>
   *本次挑战的所有简单战报ID集合
   * </pre>
   *
   * <code>repeated int64 fightInfoIds = 2;</code>
   */
  int getFightInfoIdsCount();
  /**
   * <pre>
   *本次挑战的所有简单战报ID集合
   * </pre>
   *
   * <code>repeated int64 fightInfoIds = 2;</code>
   */
  long getFightInfoIds(int index);
}
