// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface BossLibInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.BossLibInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int32 libraryType = 1;</code>
   */
  boolean hasLibraryType();
  /**
   * <code>required int32 libraryType = 1;</code>
   */
  int getLibraryType();

  /**
   * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
   */
  java.util.List<pb4client.BossInfo> 
      getBossInfoList();
  /**
   * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
   */
  pb4client.BossInfo getBossInfo(int index);
  /**
   * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
   */
  int getBossInfoCount();
  /**
   * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
   */
  java.util.List<? extends pb4client.BossInfoOrBuilder> 
      getBossInfoOrBuilderList();
  /**
   * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
   */
  pb4client.BossInfoOrBuilder getBossInfoOrBuilder(
      int index);
}
