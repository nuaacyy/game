// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface SellEquipOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.SellEquip)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 要卖掉的
   * </pre>
   *
   * <code>repeated .client2server.SellVo sellVos = 1;</code>
   */
  java.util.List<pb4client.SellVo> 
      getSellVosList();
  /**
   * <pre>
   * 要卖掉的
   * </pre>
   *
   * <code>repeated .client2server.SellVo sellVos = 1;</code>
   */
  pb4client.SellVo getSellVos(int index);
  /**
   * <pre>
   * 要卖掉的
   * </pre>
   *
   * <code>repeated .client2server.SellVo sellVos = 1;</code>
   */
  int getSellVosCount();
  /**
   * <pre>
   * 要卖掉的
   * </pre>
   *
   * <code>repeated .client2server.SellVo sellVos = 1;</code>
   */
  java.util.List<? extends pb4client.SellVoOrBuilder> 
      getSellVosOrBuilderList();
  /**
   * <pre>
   * 要卖掉的
   * </pre>
   *
   * <code>repeated .client2server.SellVo sellVos = 1;</code>
   */
  pb4client.SellVoOrBuilder getSellVosOrBuilder(
      int index);
}
