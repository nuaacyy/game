// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface EquipPropOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.EquipProp)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *属性代号
   * </pre>
   *
   * <code>required int32 propType = 1;</code>
   */
  boolean hasPropType();
  /**
   * <pre>
   *属性代号
   * </pre>
   *
   * <code>required int32 propType = 1;</code>
   */
  int getPropType();

  /**
   * <pre>
   *属性值
   * </pre>
   *
   * <code>required int32 propValue = 2;</code>
   */
  boolean hasPropValue();
  /**
   * <pre>
   *属性值
   * </pre>
   *
   * <code>required int32 propValue = 2;</code>
   */
  int getPropValue();
}