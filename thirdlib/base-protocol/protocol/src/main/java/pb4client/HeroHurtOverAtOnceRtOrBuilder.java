// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface HeroHurtOverAtOnceRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.HeroHurtOverAtOnceRt)
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
   * 要秒的武将ID
   * </pre>
   *
   * <code>repeated int64 heroId = 2;</code>
   */
  java.util.List<java.lang.Long> getHeroIdList();
  /**
   * <pre>
   * 要秒的武将ID
   * </pre>
   *
   * <code>repeated int64 heroId = 2;</code>
   */
  int getHeroIdCount();
  /**
   * <pre>
   * 要秒的武将ID
   * </pre>
   *
   * <code>repeated int64 heroId = 2;</code>
   */
  long getHeroId(int index);
}