// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface IntPropertyOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.IntProperty)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *属性类别
   * </pre>
   *
   * <code>required int32 propertyType = 1;</code>
   */
  boolean hasPropertyType();
  /**
   * <pre>
   *属性类别
   * </pre>
   *
   * <code>required int32 propertyType = 1;</code>
   */
  int getPropertyType();

  /**
   * <pre>
   *属性值
   * </pre>
   *
   * <code>required int64 propertyValue = 2;</code>
   */
  boolean hasPropertyValue();
  /**
   * <pre>
   *属性值
   * </pre>
   *
   * <code>required int64 propertyValue = 2;</code>
   */
  long getPropertyValue();
}
