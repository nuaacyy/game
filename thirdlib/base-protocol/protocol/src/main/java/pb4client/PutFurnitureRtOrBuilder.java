// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface PutFurnitureRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.PutFurnitureRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int32 rt = 1;</code>
   */
  boolean hasRt();
  /**
   * <code>required int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <pre>
   * 摆放的家具
   * </pre>
   *
   * <code>optional .client2server.FurnitureInfo furniture = 2;</code>
   */
  boolean hasFurniture();
  /**
   * <pre>
   * 摆放的家具
   * </pre>
   *
   * <code>optional .client2server.FurnitureInfo furniture = 2;</code>
   */
  pb4client.FurnitureInfo getFurniture();
  /**
   * <pre>
   * 摆放的家具
   * </pre>
   *
   * <code>optional .client2server.FurnitureInfo furniture = 2;</code>
   */
  pb4client.FurnitureInfoOrBuilder getFurnitureOrBuilder();
}