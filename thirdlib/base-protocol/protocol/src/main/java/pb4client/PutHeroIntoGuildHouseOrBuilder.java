// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface PutHeroIntoGuildHouseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.PutHeroIntoGuildHouse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 英雄Id
   * </pre>
   *
   * <code>required int64 heroId = 1;</code>
   */
  boolean hasHeroId();
  /**
   * <pre>
   * 英雄Id
   * </pre>
   *
   * <code>required int64 heroId = 1;</code>
   */
  long getHeroId();

  /**
   * <pre>
   * 所在楼层 0-回收
   * </pre>
   *
   * <code>required int32 floorIdx = 2;</code>
   */
  boolean hasFloorIdx();
  /**
   * <pre>
   * 所在楼层 0-回收
   * </pre>
   *
   * <code>required int32 floorIdx = 2;</code>
   */
  int getFloorIdx();
}