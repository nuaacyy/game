// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface CancelLandOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.CancelLand)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 删除铺路的信息
   * </pre>
   *
   * <code>repeated .client2server.CancelLandVo landGridInfo = 1;</code>
   */
  java.util.List<pb4client.CancelLandVo> 
      getLandGridInfoList();
  /**
   * <pre>
   * 删除铺路的信息
   * </pre>
   *
   * <code>repeated .client2server.CancelLandVo landGridInfo = 1;</code>
   */
  pb4client.CancelLandVo getLandGridInfo(int index);
  /**
   * <pre>
   * 删除铺路的信息
   * </pre>
   *
   * <code>repeated .client2server.CancelLandVo landGridInfo = 1;</code>
   */
  int getLandGridInfoCount();
  /**
   * <pre>
   * 删除铺路的信息
   * </pre>
   *
   * <code>repeated .client2server.CancelLandVo landGridInfo = 1;</code>
   */
  java.util.List<? extends pb4client.CancelLandVoOrBuilder> 
      getLandGridInfoOrBuilderList();
  /**
   * <pre>
   * 删除铺路的信息
   * </pre>
   *
   * <code>repeated .client2server.CancelLandVo landGridInfo = 1;</code>
   */
  pb4client.CancelLandVoOrBuilder getLandGridInfoOrBuilder(
      int index);
}