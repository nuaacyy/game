// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface EnterGuildHouseRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.EnterGuildHouseRt)
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
   * 后宅名称
   * </pre>
   *
   * <code>optional string name = 2;</code>
   */
  boolean hasName();
  /**
   * <pre>
   * 后宅名称
   * </pre>
   *
   * <code>optional string name = 2;</code>
   */
  java.lang.String getName();
  /**
   * <pre>
   * 后宅名称
   * </pre>
   *
   * <code>optional string name = 2;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <pre>
   * 舒适度
   * </pre>
   *
   * <code>optional int32 comfort = 3;</code>
   */
  boolean hasComfort();
  /**
   * <pre>
   * 舒适度
   * </pre>
   *
   * <code>optional int32 comfort = 3;</code>
   */
  int getComfort();

  /**
   * <pre>
   * 新增被赞数
   * </pre>
   *
   * <code>optional int32 thumbedUpNew = 4;</code>
   */
  boolean hasThumbedUpNew();
  /**
   * <pre>
   * 新增被赞数
   * </pre>
   *
   * <code>optional int32 thumbedUpNew = 4;</code>
   */
  int getThumbedUpNew();

  /**
   * <pre>
   * 当前后宅家具
   * </pre>
   *
   * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
   */
  java.util.List<pb4client.FurnitureInfo> 
      getFurnituresList();
  /**
   * <pre>
   * 当前后宅家具
   * </pre>
   *
   * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
   */
  pb4client.FurnitureInfo getFurnitures(int index);
  /**
   * <pre>
   * 当前后宅家具
   * </pre>
   *
   * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
   */
  int getFurnituresCount();
  /**
   * <pre>
   * 当前后宅家具
   * </pre>
   *
   * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
   */
  java.util.List<? extends pb4client.FurnitureInfoOrBuilder> 
      getFurnituresOrBuilderList();
  /**
   * <pre>
   * 当前后宅家具
   * </pre>
   *
   * <code>repeated .client2server.FurnitureInfo furnitures = 5;</code>
   */
  pb4client.FurnitureInfoOrBuilder getFurnituresOrBuilder(
      int index);
}
