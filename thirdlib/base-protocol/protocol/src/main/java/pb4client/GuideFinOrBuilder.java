// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface GuideFinOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.GuideFin)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 新手引导类型(0-强制引导  1-被动引导)
   * </pre>
   *
   * <code>optional int32 type = 1;</code>
   */
  boolean hasType();
  /**
   * <pre>
   * 新手引导类型(0-强制引导  1-被动引导)
   * </pre>
   *
   * <code>optional int32 type = 1;</code>
   */
  int getType();

  /**
   * <pre>
   * 被动触发的新手引导模板id
   * </pre>
   *
   * <code>optional int32 guideId = 2;</code>
   */
  boolean hasGuideId();
  /**
   * <pre>
   * 被动触发的新手引导模板id
   * </pre>
   *
   * <code>optional int32 guideId = 2;</code>
   */
  int getGuideId();
}
