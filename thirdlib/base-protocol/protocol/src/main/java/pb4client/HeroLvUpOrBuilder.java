// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface HeroLvUpOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.HeroLvUp)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 升级武将ID
   * </pre>
   *
   * <code>required int64 heroId = 1;</code>
   */
  boolean hasHeroId();
  /**
   * <pre>
   * 升级武将ID
   * </pre>
   *
   * <code>required int64 heroId = 1;</code>
   */
  long getHeroId();

  /**
   * <pre>
   * 被吃掉的卡ID
   * </pre>
   *
   * <code>repeated int64 materialIds = 2;</code>
   */
  java.util.List<java.lang.Long> getMaterialIdsList();
  /**
   * <pre>
   * 被吃掉的卡ID
   * </pre>
   *
   * <code>repeated int64 materialIds = 2;</code>
   */
  int getMaterialIdsCount();
  /**
   * <pre>
   * 被吃掉的卡ID
   * </pre>
   *
   * <code>repeated int64 materialIds = 2;</code>
   */
  long getMaterialIds(int index);
}
