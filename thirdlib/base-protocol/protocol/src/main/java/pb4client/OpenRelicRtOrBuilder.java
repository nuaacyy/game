// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface OpenRelicRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.OpenRelicRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  boolean hasRt();
  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <code>repeated .client2server.RelicBus relicBuss = 2;</code>
   */
  java.util.List<pb4client.RelicBus> 
      getRelicBussList();
  /**
   * <code>repeated .client2server.RelicBus relicBuss = 2;</code>
   */
  pb4client.RelicBus getRelicBuss(int index);
  /**
   * <code>repeated .client2server.RelicBus relicBuss = 2;</code>
   */
  int getRelicBussCount();
  /**
   * <code>repeated .client2server.RelicBus relicBuss = 2;</code>
   */
  java.util.List<? extends pb4client.RelicBusOrBuilder> 
      getRelicBussOrBuilderList();
  /**
   * <code>repeated .client2server.RelicBus relicBuss = 2;</code>
   */
  pb4client.RelicBusOrBuilder getRelicBussOrBuilder(
      int index);
}
