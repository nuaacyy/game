// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface WeekTargetRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.WeekTargetRt)
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
   * <code>repeated .client2server.Wtarget targets = 2;</code>
   */
  java.util.List<pb4client.Wtarget> 
      getTargetsList();
  /**
   * <code>repeated .client2server.Wtarget targets = 2;</code>
   */
  pb4client.Wtarget getTargets(int index);
  /**
   * <code>repeated .client2server.Wtarget targets = 2;</code>
   */
  int getTargetsCount();
  /**
   * <code>repeated .client2server.Wtarget targets = 2;</code>
   */
  java.util.List<? extends pb4client.WtargetOrBuilder> 
      getTargetsOrBuilderList();
  /**
   * <code>repeated .client2server.Wtarget targets = 2;</code>
   */
  pb4client.WtargetOrBuilder getTargetsOrBuilder(
      int index);
}
