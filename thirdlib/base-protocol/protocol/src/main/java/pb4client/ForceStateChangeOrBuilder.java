// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface ForceStateChangeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.ForceStateChange)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *部队ID
   * </pre>
   *
   * <code>required int64 forceId = 1;</code>
   */
  boolean hasForceId();
  /**
   * <pre>
   *部队ID
   * </pre>
   *
   * <code>required int64 forceId = 1;</code>
   */
  long getForceId();

  /**
   * <pre>
   *部队状态  	//部队状态  1-待命 2-行军 3-驻守 4-屯田 5-征兵 6-回城 7-练兵 8-对峙 9-集结 10-等待 11-调动 12-帮忙建造帮派战旗中 13-等待出发遗迹中
   * </pre>
   *
   * <code>required int32 forceState = 2;</code>
   */
  boolean hasForceState();
  /**
   * <pre>
   *部队状态  	//部队状态  1-待命 2-行军 3-驻守 4-屯田 5-征兵 6-回城 7-练兵 8-对峙 9-集结 10-等待 11-调动 12-帮忙建造帮派战旗中 13-等待出发遗迹中
   * </pre>
   *
   * <code>required int32 forceState = 2;</code>
   */
  int getForceState();
}
