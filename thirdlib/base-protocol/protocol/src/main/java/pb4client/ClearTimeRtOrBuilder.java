// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface ClearTimeRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.ClearTimeRt)
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
   * <pre>
   * 要加速的功能  1-科技 2-造兵 3-秒治疗兵 4-秒建筑 5- 秒锻造  6- 秒武将升星  7-秒武将升阶  8 -秒晋升  9-秒联盟宝藏
   * </pre>
   *
   * <code>optional int32 clearType = 2;</code>
   */
  boolean hasClearType();
  /**
   * <pre>
   * 要加速的功能  1-科技 2-造兵 3-秒治疗兵 4-秒建筑 5- 秒锻造  6- 秒武将升星  7-秒武将升阶  8 -秒晋升  9-秒联盟宝藏
   * </pre>
   *
   * <code>optional int32 clearType = 2;</code>
   */
  int getClearType();
}
