// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface ArenaRewardGetOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.ArenaRewardGet)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *钻石数量 
   * </pre>
   *
   * <code>required int64 gold = 1;</code>
   */
  boolean hasGold();
  /**
   * <pre>
   *钻石数量 
   * </pre>
   *
   * <code>required int64 gold = 1;</code>
   */
  long getGold();

  /**
   * <pre>
   * 竞技b数量
   * </pre>
   *
   * <code>required int64 jjCoin = 2;</code>
   */
  boolean hasJjCoin();
  /**
   * <pre>
   * 竞技b数量
   * </pre>
   *
   * <code>required int64 jjCoin = 2;</code>
   */
  long getJjCoin();
}
