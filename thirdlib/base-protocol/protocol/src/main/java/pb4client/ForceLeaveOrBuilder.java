// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface ForceLeaveOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.ForceLeave)
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
   *走掉的部队ID集合
   * </pre>
   *
   * <code>repeated int64 leaveForces = 3;</code>
   */
  java.util.List<java.lang.Long> getLeaveForcesList();
  /**
   * <pre>
   *走掉的部队ID集合
   * </pre>
   *
   * <code>repeated int64 leaveForces = 3;</code>
   */
  int getLeaveForcesCount();
  /**
   * <pre>
   *走掉的部队ID集合
   * </pre>
   *
   * <code>repeated int64 leaveForces = 3;</code>
   */
  long getLeaveForces(int index);
}
