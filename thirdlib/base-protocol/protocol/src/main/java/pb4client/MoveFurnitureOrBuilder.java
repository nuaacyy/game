// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface MoveFurnitureOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.MoveFurniture)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *家具唯一Id
   * </pre>
   *
   * <code>required int64 furnitureId = 1;</code>
   */
  boolean hasFurnitureId();
  /**
   * <pre>
   *家具唯一Id
   * </pre>
   *
   * <code>required int64 furnitureId = 1;</code>
   */
  long getFurnitureId();

  /**
   * <pre>
   * 家具x坐标
   * </pre>
   *
   * <code>required int32 x = 2;</code>
   */
  boolean hasX();
  /**
   * <pre>
   * 家具x坐标
   * </pre>
   *
   * <code>required int32 x = 2;</code>
   */
  int getX();

  /**
   * <pre>
   * 家具y坐标
   * </pre>
   *
   * <code>required int32 y = 3;</code>
   */
  boolean hasY();
  /**
   * <pre>
   * 家具y坐标
   * </pre>
   *
   * <code>required int32 y = 3;</code>
   */
  int getY();

  /**
   * <pre>
   * 家具朝向
   * </pre>
   *
   * <code>required int32 direction = 4;</code>
   */
  boolean hasDirection();
  /**
   * <pre>
   * 家具朝向
   * </pre>
   *
   * <code>required int32 direction = 4;</code>
   */
  int getDirection();
}