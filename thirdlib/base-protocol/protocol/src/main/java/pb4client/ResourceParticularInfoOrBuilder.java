// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface ResourceParticularInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.ResourceParticularInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 1.领地产量  2.设施产量  3.同盟加成  5.维持消耗
   * </pre>
   *
   * <code>required int32 types = 1;</code>
   */
  boolean hasTypes();
  /**
   * <pre>
   * 1.领地产量  2.设施产量  3.同盟加成  5.维持消耗
   * </pre>
   *
   * <code>required int32 types = 1;</code>
   */
  int getTypes();

  /**
   * <code>required .client2server.YieldChange yieldChange = 2;</code>
   */
  boolean hasYieldChange();
  /**
   * <code>required .client2server.YieldChange yieldChange = 2;</code>
   */
  pb4client.YieldChange getYieldChange();
  /**
   * <code>required .client2server.YieldChange yieldChange = 2;</code>
   */
  pb4client.YieldChangeOrBuilder getYieldChangeOrBuilder();
}
