// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface BuildInfoForCellEventOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.BuildInfoForCellEvent)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int32 x = 1;</code>
   */
  boolean hasX();
  /**
   * <code>required int32 x = 1;</code>
   */
  int getX();

  /**
   * <code>required int32 y = 2;</code>
   */
  boolean hasY();
  /**
   * <code>required int32 y = 2;</code>
   */
  int getY();

  /**
   * <pre>
   *是否正在被建造  0-没有 非0-正在建造的建筑类型
   * </pre>
   *
   * <code>required int32 isBuilding = 3;</code>
   */
  boolean hasIsBuilding();
  /**
   * <pre>
   *是否正在被建造  0-没有 非0-正在建造的建筑类型
   * </pre>
   *
   * <code>required int32 isBuilding = 3;</code>
   */
  int getIsBuilding();

  /**
   * <pre>
   *玩家取得正在造的建筑名字
   * </pre>
   *
   * <code>required string buildName = 4;</code>
   */
  boolean hasBuildName();
  /**
   * <pre>
   *玩家取得正在造的建筑名字
   * </pre>
   *
   * <code>required string buildName = 4;</code>
   */
  java.lang.String getBuildName();
  /**
   * <pre>
   *玩家取得正在造的建筑名字
   * </pre>
   *
   * <code>required string buildName = 4;</code>
   */
  com.google.protobuf.ByteString
      getBuildNameBytes();
}
