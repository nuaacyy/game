// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface HeroStarLvUpRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.HeroStarLvUpRt)
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
   * 升星武将ID
   * </pre>
   *
   * <code>optional int64 heroId = 2;</code>
   */
  boolean hasHeroId();
  /**
   * <pre>
   * 升星武将ID
   * </pre>
   *
   * <code>optional int64 heroId = 2;</code>
   */
  long getHeroId();
}
