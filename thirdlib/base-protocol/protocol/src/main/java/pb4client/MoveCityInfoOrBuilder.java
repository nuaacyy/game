// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface MoveCityInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.MoveCityInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int64 cityId = 1;</code>
   */
  boolean hasCityId();
  /**
   * <code>required int64 cityId = 1;</code>
   */
  long getCityId();

  /**
   * <pre>
   *0:可迁城   1.建筑升级中 2.部队外出中 3：资源不足
   * </pre>
   *
   * <code>required int32 resultType = 2;</code>
   */
  boolean hasResultType();
  /**
   * <pre>
   *0:可迁城   1.建筑升级中 2.部队外出中 3：资源不足
   * </pre>
   *
   * <code>required int32 resultType = 2;</code>
   */
  int getResultType();
}
