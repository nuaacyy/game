// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface HeroLevelUpInFoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.HeroLevelUpInFo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int64 heroId = 1;</code>
   */
  boolean hasHeroId();
  /**
   * <code>required int64 heroId = 1;</code>
   */
  long getHeroId();

  /**
   * <code>required int32 heroProtoId = 2;</code>
   */
  boolean hasHeroProtoId();
  /**
   * <code>required int32 heroProtoId = 2;</code>
   */
  int getHeroProtoId();

  /**
   * <code>required int32 lv = 3;</code>
   */
  boolean hasLv();
  /**
   * <code>required int32 lv = 3;</code>
   */
  int getLv();

  /**
   * <pre>
   *原为float 现*100为int
   * </pre>
   *
   * <code>required int32 attack = 4;</code>
   */
  boolean hasAttack();
  /**
   * <pre>
   *原为float 现*100为int
   * </pre>
   *
   * <code>required int32 attack = 4;</code>
   */
  int getAttack();

  /**
   * <pre>
   *原为float 现*100为int
   * </pre>
   *
   * <code>required int32 magic = 5;</code>
   */
  boolean hasMagic();
  /**
   * <pre>
   *原为float 现*100为int
   * </pre>
   *
   * <code>required int32 magic = 5;</code>
   */
  int getMagic();

  /**
   * <pre>
   *原为float 现*100为int
   * </pre>
   *
   * <code>required int32 defence = 6;</code>
   */
  boolean hasDefence();
  /**
   * <pre>
   *原为float 现*100为int
   * </pre>
   *
   * <code>required int32 defence = 6;</code>
   */
  int getDefence();

  /**
   * <pre>
   *原为float 现*100为int
   * </pre>
   *
   * <code>required int32 speed = 7;</code>
   */
  boolean hasSpeed();
  /**
   * <pre>
   *原为float 现*100为int
   * </pre>
   *
   * <code>required int32 speed = 7;</code>
   */
  int getSpeed();

  /**
   * <pre>
   *原为float 现*100为int
   * </pre>
   *
   * <code>required int32 attCity = 8;</code>
   */
  boolean hasAttCity();
  /**
   * <pre>
   *原为float 现*100为int
   * </pre>
   *
   * <code>required int32 attCity = 8;</code>
   */
  int getAttCity();

  /**
   * <code>required int32 points = 9;</code>
   */
  boolean hasPoints();
  /**
   * <code>required int32 points = 9;</code>
   */
  int getPoints();

  /**
   * <code>required int32 experience = 10;</code>
   */
  boolean hasExperience();
  /**
   * <code>required int32 experience = 10;</code>
   */
  int getExperience();
}
