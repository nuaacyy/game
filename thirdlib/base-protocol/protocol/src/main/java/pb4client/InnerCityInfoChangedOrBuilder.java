// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface InnerCityInfoChangedOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.InnerCityInfoChanged)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 1新增 2删除 3 变化
   * </pre>
   *
   * <code>required int32 op = 1;</code>
   */
  boolean hasOp();
  /**
   * <pre>
   * 1新增 2删除 3 变化
   * </pre>
   *
   * <code>required int32 op = 1;</code>
   */
  int getOp();

  /**
   * <pre>
   * 建筑信息
   * </pre>
   *
   * <code>required .client2server.InnerCityInfo innerCityInfo = 2;</code>
   */
  boolean hasInnerCityInfo();
  /**
   * <pre>
   * 建筑信息
   * </pre>
   *
   * <code>required .client2server.InnerCityInfo innerCityInfo = 2;</code>
   */
  pb4client.InnerCityInfo getInnerCityInfo();
  /**
   * <pre>
   * 建筑信息
   * </pre>
   *
   * <code>required .client2server.InnerCityInfo innerCityInfo = 2;</code>
   */
  pb4client.InnerCityInfoOrBuilder getInnerCityInfoOrBuilder();
}
