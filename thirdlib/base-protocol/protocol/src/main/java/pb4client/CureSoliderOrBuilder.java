// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface CureSoliderOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.CureSolider)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 治疗类型  1-普通治疗  2-元宝补齐资源治疗
   * </pre>
   *
   * <code>required int32 cureType = 1;</code>
   */
  boolean hasCureType();
  /**
   * <pre>
   * 治疗类型  1-普通治疗  2-元宝补齐资源治疗
   * </pre>
   *
   * <code>required int32 cureType = 1;</code>
   */
  int getCureType();

  /**
   * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
   */
  java.util.List<pb4client.CureSoliderInfo> 
      getCureSoliderInfoList();
  /**
   * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
   */
  pb4client.CureSoliderInfo getCureSoliderInfo(int index);
  /**
   * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
   */
  int getCureSoliderInfoCount();
  /**
   * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
   */
  java.util.List<? extends pb4client.CureSoliderInfoOrBuilder> 
      getCureSoliderInfoOrBuilderList();
  /**
   * <code>repeated .client2server.CureSoliderInfo cureSoliderInfo = 2;</code>
   */
  pb4client.CureSoliderInfoOrBuilder getCureSoliderInfoOrBuilder(
      int index);

  /**
   * <pre>
   * 类型  1-士兵  2-陷阱
   * </pre>
   *
   * <code>required int32 trapOrSolider = 3;</code>
   */
  boolean hasTrapOrSolider();
  /**
   * <pre>
   * 类型  1-士兵  2-陷阱
   * </pre>
   *
   * <code>required int32 trapOrSolider = 3;</code>
   */
  int getTrapOrSolider();

  /**
   * <pre>
   * 活动治疗 1-活动治疗 其他-默认普通治疗
   * </pre>
   *
   * <code>optional int32 eventCure = 4;</code>
   */
  boolean hasEventCure();
  /**
   * <pre>
   * 活动治疗 1-活动治疗 其他-默认普通治疗
   * </pre>
   *
   * <code>optional int32 eventCure = 4;</code>
   */
  int getEventCure();
}
