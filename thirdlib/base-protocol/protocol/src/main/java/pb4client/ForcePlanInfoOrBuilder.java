// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface ForcePlanInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.ForcePlanInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 武将IDS
   * </pre>
   *
   * <code>repeated int64 heroId = 1;</code>
   */
  java.util.List<java.lang.Long> getHeroIdList();
  /**
   * <pre>
   * 武将IDS
   * </pre>
   *
   * <code>repeated int64 heroId = 1;</code>
   */
  int getHeroIdCount();
  /**
   * <pre>
   * 武将IDS
   * </pre>
   *
   * <code>repeated int64 heroId = 1;</code>
   */
  long getHeroId(int index);

  /**
   * <pre>
   * 士兵信息
   * </pre>
   *
   * <code>repeated .client2server.ForcePlanSolider forcePlanSoliders = 2;</code>
   */
  java.util.List<pb4client.ForcePlanSolider> 
      getForcePlanSolidersList();
  /**
   * <pre>
   * 士兵信息
   * </pre>
   *
   * <code>repeated .client2server.ForcePlanSolider forcePlanSoliders = 2;</code>
   */
  pb4client.ForcePlanSolider getForcePlanSoliders(int index);
  /**
   * <pre>
   * 士兵信息
   * </pre>
   *
   * <code>repeated .client2server.ForcePlanSolider forcePlanSoliders = 2;</code>
   */
  int getForcePlanSolidersCount();
  /**
   * <pre>
   * 士兵信息
   * </pre>
   *
   * <code>repeated .client2server.ForcePlanSolider forcePlanSoliders = 2;</code>
   */
  java.util.List<? extends pb4client.ForcePlanSoliderOrBuilder> 
      getForcePlanSolidersOrBuilderList();
  /**
   * <pre>
   * 士兵信息
   * </pre>
   *
   * <code>repeated .client2server.ForcePlanSolider forcePlanSoliders = 2;</code>
   */
  pb4client.ForcePlanSoliderOrBuilder getForcePlanSolidersOrBuilder(
      int index);
}
