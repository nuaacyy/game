// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface SwitchLibTagRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.SwitchLibTagRt)
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
   * <code>repeated .client2server.LibInfo libItems = 2;</code>
   */
  java.util.List<pb4client.LibInfo> 
      getLibItemsList();
  /**
   * <code>repeated .client2server.LibInfo libItems = 2;</code>
   */
  pb4client.LibInfo getLibItems(int index);
  /**
   * <code>repeated .client2server.LibInfo libItems = 2;</code>
   */
  int getLibItemsCount();
  /**
   * <code>repeated .client2server.LibInfo libItems = 2;</code>
   */
  java.util.List<? extends pb4client.LibInfoOrBuilder> 
      getLibItemsOrBuilderList();
  /**
   * <code>repeated .client2server.LibInfo libItems = 2;</code>
   */
  pb4client.LibInfoOrBuilder getLibItemsOrBuilder(
      int index);

  /**
   * <code>repeated .client2server.MonsterLibInfo monsterLibItems = 3;</code>
   */
  java.util.List<pb4client.MonsterLibInfo> 
      getMonsterLibItemsList();
  /**
   * <code>repeated .client2server.MonsterLibInfo monsterLibItems = 3;</code>
   */
  pb4client.MonsterLibInfo getMonsterLibItems(int index);
  /**
   * <code>repeated .client2server.MonsterLibInfo monsterLibItems = 3;</code>
   */
  int getMonsterLibItemsCount();
  /**
   * <code>repeated .client2server.MonsterLibInfo monsterLibItems = 3;</code>
   */
  java.util.List<? extends pb4client.MonsterLibInfoOrBuilder> 
      getMonsterLibItemsOrBuilderList();
  /**
   * <code>repeated .client2server.MonsterLibInfo monsterLibItems = 3;</code>
   */
  pb4client.MonsterLibInfoOrBuilder getMonsterLibItemsOrBuilder(
      int index);

  /**
   * <code>repeated .client2server.BossLibInfo bossLibItems = 4;</code>
   */
  java.util.List<pb4client.BossLibInfo> 
      getBossLibItemsList();
  /**
   * <code>repeated .client2server.BossLibInfo bossLibItems = 4;</code>
   */
  pb4client.BossLibInfo getBossLibItems(int index);
  /**
   * <code>repeated .client2server.BossLibInfo bossLibItems = 4;</code>
   */
  int getBossLibItemsCount();
  /**
   * <code>repeated .client2server.BossLibInfo bossLibItems = 4;</code>
   */
  java.util.List<? extends pb4client.BossLibInfoOrBuilder> 
      getBossLibItemsOrBuilderList();
  /**
   * <code>repeated .client2server.BossLibInfo bossLibItems = 4;</code>
   */
  pb4client.BossLibInfoOrBuilder getBossLibItemsOrBuilder(
      int index);
}
