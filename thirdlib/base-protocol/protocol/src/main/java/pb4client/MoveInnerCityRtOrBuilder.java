// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface MoveInnerCityRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.MoveInnerCityRt)
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
   * <code>optional int32 oldPositionIndex = 2;</code>
   */
  boolean hasOldPositionIndex();
  /**
   * <code>optional int32 oldPositionIndex = 2;</code>
   */
  int getOldPositionIndex();

  /**
   * <code>optional int32 newPositionIndex = 3;</code>
   */
  boolean hasNewPositionIndex();
  /**
   * <code>optional int32 newPositionIndex = 3;</code>
   */
  int getNewPositionIndex();
}