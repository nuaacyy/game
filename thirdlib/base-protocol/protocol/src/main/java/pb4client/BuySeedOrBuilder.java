// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface BuySeedOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.BuySeed)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 购买种子的props表的ID
   * </pre>
   *
   * <code>required int32 seedId = 1;</code>
   */
  boolean hasSeedId();
  /**
   * <pre>
   * 购买种子的props表的ID
   * </pre>
   *
   * <code>required int32 seedId = 1;</code>
   */
  int getSeedId();

  /**
   * <pre>
   * 购买数量
   * </pre>
   *
   * <code>required int32 num = 2;</code>
   */
  boolean hasNum();
  /**
   * <pre>
   * 购买数量
   * </pre>
   *
   * <code>required int32 num = 2;</code>
   */
  int getNum();
}
