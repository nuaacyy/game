// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface BuildCityOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.BuildCity)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * X
   * </pre>
   *
   * <code>required int32 x = 1;</code>
   */
  boolean hasX();
  /**
   * <pre>
   * X
   * </pre>
   *
   * <code>required int32 x = 1;</code>
   */
  int getX();

  /**
   * <pre>
   * Y
   * </pre>
   *
   * <code>required int32 y = 2;</code>
   */
  boolean hasY();
  /**
   * <pre>
   * Y
   * </pre>
   *
   * <code>required int32 y = 2;</code>
   */
  int getY();

  /**
   * <pre>
   *	要建造的建筑类型
   * </pre>
   *
   * <code>required int32 cityType = 3;</code>
   */
  boolean hasCityType();
  /**
   * <pre>
   *	要建造的建筑类型
   * </pre>
   *
   * <code>required int32 cityType = 3;</code>
   */
  int getCityType();

  /**
   * <pre>
   * 玩家取得建筑名字
   * </pre>
   *
   * <code>required string name = 4;</code>
   */
  boolean hasName();
  /**
   * <pre>
   * 玩家取得建筑名字
   * </pre>
   *
   * <code>required string name = 4;</code>
   */
  java.lang.String getName();
  /**
   * <pre>
   * 玩家取得建筑名字
   * </pre>
   *
   * <code>required string name = 4;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();
}