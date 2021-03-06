// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface RemoveBuildingOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.RemoveBuilding)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *坐标X
   * </pre>
   *
   * <code>required int32 x = 1;</code>
   */
  boolean hasX();
  /**
   * <pre>
   *坐标X
   * </pre>
   *
   * <code>required int32 x = 1;</code>
   */
  int getX();

  /**
   * <pre>
   *坐标Y
   * </pre>
   *
   * <code>required int32 y = 2;</code>
   */
  boolean hasY();
  /**
   * <pre>
   *坐标Y
   * </pre>
   *
   * <code>required int32 y = 2;</code>
   */
  int getY();

  /**
   * <pre>
   *地的类型
   * </pre>
   *
   * <code>required int32 type = 3;</code>
   */
  boolean hasType();
  /**
   * <pre>
   *地的类型
   * </pre>
   *
   * <code>required int32 type = 3;</code>
   */
  int getType();

  /**
   * <pre>
   *城池id
   * </pre>
   *
   * <code>required int64 castleId = 4;</code>
   */
  boolean hasCastleId();
  /**
   * <pre>
   *城池id
   * </pre>
   *
   * <code>required int64 castleId = 4;</code>
   */
  long getCastleId();

  /**
   * <pre>
   *地与我的关系  (1-自己 2-其他)
   * </pre>
   *
   * <code>required int32 state = 5;</code>
   */
  boolean hasState();
  /**
   * <pre>
   *地与我的关系  (1-自己 2-其他)
   * </pre>
   *
   * <code>required int32 state = 5;</code>
   */
  int getState();
}
