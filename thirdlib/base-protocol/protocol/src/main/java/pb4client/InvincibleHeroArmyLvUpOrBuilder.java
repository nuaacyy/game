// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface InvincibleHeroArmyLvUpOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.InvincibleHeroArmyLvUp)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 要升级兵团的武将ID
   * </pre>
   *
   * <code>required int64 heroId = 1;</code>
   */
  boolean hasHeroId();
  /**
   * <pre>
   * 要升级兵团的武将ID
   * </pre>
   *
   * <code>required int64 heroId = 1;</code>
   */
  long getHeroId();

  /**
   * <pre>
   * 要升级到的目标等级
   * </pre>
   *
   * <code>required int32 toLv = 2;</code>
   */
  boolean hasToLv();
  /**
   * <pre>
   * 要升级到的目标等级
   * </pre>
   *
   * <code>required int32 toLv = 2;</code>
   */
  int getToLv();
}
