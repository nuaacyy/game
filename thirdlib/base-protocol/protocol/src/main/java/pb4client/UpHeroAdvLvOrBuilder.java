// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface UpHeroAdvLvOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.UpHeroAdvLv)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 武将编号
   * </pre>
   *
   * <code>required int64 heroId = 1;</code>
   */
  boolean hasHeroId();
  /**
   * <pre>
   * 武将编号
   * </pre>
   *
   * <code>required int64 heroId = 1;</code>
   */
  long getHeroId();

  /**
   * <pre>
   * 升到的进阶等级
   * </pre>
   *
   * <code>required int32 toLv = 2;</code>
   */
  boolean hasToLv();
  /**
   * <pre>
   * 升到的进阶等级
   * </pre>
   *
   * <code>required int32 toLv = 2;</code>
   */
  int getToLv();
}
