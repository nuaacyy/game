// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface UpHeroSkillRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.UpHeroSkillRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int32 rt = 1;</code>
   */
  boolean hasRt();
  /**
   * <code>required int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <pre>
   * 武将编号
   * </pre>
   *
   * <code>optional int64 heroId = 2;</code>
   */
  boolean hasHeroId();
  /**
   * <pre>
   * 武将编号
   * </pre>
   *
   * <code>optional int64 heroId = 2;</code>
   */
  long getHeroId();

  /**
   * <pre>
   * 第几个技能(1-4)
   * </pre>
   *
   * <code>optional int32 pos = 3;</code>
   */
  boolean hasPos();
  /**
   * <pre>
   * 第几个技能(1-4)
   * </pre>
   *
   * <code>optional int32 pos = 3;</code>
   */
  int getPos();
}