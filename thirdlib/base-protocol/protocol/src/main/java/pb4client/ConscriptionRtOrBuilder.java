// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface ConscriptionRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.ConscriptionRt)
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
   * <pre>
   *征兵信息
   * </pre>
   *
   * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
   */
  java.util.List<pb4client.HeroEndTime> 
      getHeroEndTimeList();
  /**
   * <pre>
   *征兵信息
   * </pre>
   *
   * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
   */
  pb4client.HeroEndTime getHeroEndTime(int index);
  /**
   * <pre>
   *征兵信息
   * </pre>
   *
   * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
   */
  int getHeroEndTimeCount();
  /**
   * <pre>
   *征兵信息
   * </pre>
   *
   * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
   */
  java.util.List<? extends pb4client.HeroEndTimeOrBuilder> 
      getHeroEndTimeOrBuilderList();
  /**
   * <pre>
   *征兵信息
   * </pre>
   *
   * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
   */
  pb4client.HeroEndTimeOrBuilder getHeroEndTimeOrBuilder(
      int index);
}