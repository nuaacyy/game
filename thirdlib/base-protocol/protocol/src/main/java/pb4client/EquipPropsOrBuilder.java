// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface EquipPropsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.EquipProps)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *属性位置
   * </pre>
   *
   * <code>required int32 propAddress = 1;</code>
   */
  boolean hasPropAddress();
  /**
   * <pre>
   *属性位置
   * </pre>
   *
   * <code>required int32 propAddress = 1;</code>
   */
  int getPropAddress();

  /**
   * <pre>
   *属性
   * </pre>
   *
   * <code>repeated .client2server.EquipProp propValues = 2;</code>
   */
  java.util.List<pb4client.EquipProp> 
      getPropValuesList();
  /**
   * <pre>
   *属性
   * </pre>
   *
   * <code>repeated .client2server.EquipProp propValues = 2;</code>
   */
  pb4client.EquipProp getPropValues(int index);
  /**
   * <pre>
   *属性
   * </pre>
   *
   * <code>repeated .client2server.EquipProp propValues = 2;</code>
   */
  int getPropValuesCount();
  /**
   * <pre>
   *属性
   * </pre>
   *
   * <code>repeated .client2server.EquipProp propValues = 2;</code>
   */
  java.util.List<? extends pb4client.EquipPropOrBuilder> 
      getPropValuesOrBuilderList();
  /**
   * <pre>
   *属性
   * </pre>
   *
   * <code>repeated .client2server.EquipProp propValues = 2;</code>
   */
  pb4client.EquipPropOrBuilder getPropValuesOrBuilder(
      int index);
}
