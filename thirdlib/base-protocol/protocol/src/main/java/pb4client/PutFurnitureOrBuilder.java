// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface PutFurnitureOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.PutFurniture)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *家具模板Id
   * </pre>
   *
   * <code>required int32 protoId = 1;</code>
   */
  boolean hasProtoId();
  /**
   * <pre>
   *家具模板Id
   * </pre>
   *
   * <code>required int32 protoId = 1;</code>
   */
  int getProtoId();

  /**
   * <pre>
   *家具所在层 1-1F 2-2F
   * </pre>
   *
   * <code>required int32 floorIdx = 2;</code>
   */
  boolean hasFloorIdx();
  /**
   * <pre>
   *家具所在层 1-1F 2-2F
   * </pre>
   *
   * <code>required int32 floorIdx = 2;</code>
   */
  int getFloorIdx();

  /**
   * <pre>
   * 家具x坐标
   * </pre>
   *
   * <code>required int32 x = 3;</code>
   */
  boolean hasX();
  /**
   * <pre>
   * 家具x坐标
   * </pre>
   *
   * <code>required int32 x = 3;</code>
   */
  int getX();

  /**
   * <pre>
   * 家具y坐标
   * </pre>
   *
   * <code>required int32 y = 4;</code>
   */
  boolean hasY();
  /**
   * <pre>
   * 家具y坐标
   * </pre>
   *
   * <code>required int32 y = 4;</code>
   */
  int getY();

  /**
   * <pre>
   * 家具朝向
   * </pre>
   *
   * <code>required int32 direction = 5;</code>
   */
  boolean hasDirection();
  /**
   * <pre>
   * 家具朝向
   * </pre>
   *
   * <code>required int32 direction = 5;</code>
   */
  int getDirection();

  /**
   * <pre>
   * 购买数量
   * </pre>
   *
   * <code>optional int32 buyNum = 6;</code>
   */
  boolean hasBuyNum();
  /**
   * <pre>
   * 购买数量
   * </pre>
   *
   * <code>optional int32 buyNum = 6;</code>
   */
  int getBuyNum();
}
