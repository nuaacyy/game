// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface ScoutTrapsInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.ScoutTrapsInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *1-总数显示模糊值，陷阱列表不显示 2 总数和陷阱列表显示模糊 3 都是精确值
   * </pre>
   *
   * <code>required int32 type = 1;</code>
   */
  boolean hasType();
  /**
   * <pre>
   *1-总数显示模糊值，陷阱列表不显示 2 总数和陷阱列表显示模糊 3 都是精确值
   * </pre>
   *
   * <code>required int32 type = 1;</code>
   */
  int getType();

  /**
   * <pre>
   *陷阱总数
   * </pre>
   *
   * <code>required int32 trapCount = 2;</code>
   */
  boolean hasTrapCount();
  /**
   * <pre>
   *陷阱总数
   * </pre>
   *
   * <code>required int32 trapCount = 2;</code>
   */
  int getTrapCount();

  /**
   * <pre>
   *侦察等级没到不显示
   * </pre>
   *
   * <code>repeated .client2server.ScoutTrap scoutTrap = 3;</code>
   */
  java.util.List<pb4client.ScoutTrap> 
      getScoutTrapList();
  /**
   * <pre>
   *侦察等级没到不显示
   * </pre>
   *
   * <code>repeated .client2server.ScoutTrap scoutTrap = 3;</code>
   */
  pb4client.ScoutTrap getScoutTrap(int index);
  /**
   * <pre>
   *侦察等级没到不显示
   * </pre>
   *
   * <code>repeated .client2server.ScoutTrap scoutTrap = 3;</code>
   */
  int getScoutTrapCount();
  /**
   * <pre>
   *侦察等级没到不显示
   * </pre>
   *
   * <code>repeated .client2server.ScoutTrap scoutTrap = 3;</code>
   */
  java.util.List<? extends pb4client.ScoutTrapOrBuilder> 
      getScoutTrapOrBuilderList();
  /**
   * <pre>
   *侦察等级没到不显示
   * </pre>
   *
   * <code>repeated .client2server.ScoutTrap scoutTrap = 3;</code>
   */
  pb4client.ScoutTrapOrBuilder getScoutTrapOrBuilder(
      int index);
}