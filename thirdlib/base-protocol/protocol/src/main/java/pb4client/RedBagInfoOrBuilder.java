// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface RedBagInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.RedBagInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *红包标题 
   * </pre>
   *
   * <code>required string redBagTitle = 1;</code>
   */
  boolean hasRedBagTitle();
  /**
   * <pre>
   *红包标题 
   * </pre>
   *
   * <code>required string redBagTitle = 1;</code>
   */
  java.lang.String getRedBagTitle();
  /**
   * <pre>
   *红包标题 
   * </pre>
   *
   * <code>required string redBagTitle = 1;</code>
   */
  com.google.protobuf.ByteString
      getRedBagTitleBytes();

  /**
   * <pre>
   *红包个数 
   * </pre>
   *
   * <code>required int32 redBagNum = 2;</code>
   */
  boolean hasRedBagNum();
  /**
   * <pre>
   *红包个数 
   * </pre>
   *
   * <code>required int32 redBagNum = 2;</code>
   */
  int getRedBagNum();

  /**
   * <pre>
   *红包总金额 
   * </pre>
   *
   * <code>required int32 redBagMoney = 3;</code>
   */
  boolean hasRedBagMoney();
  /**
   * <pre>
   *红包总金额 
   * </pre>
   *
   * <code>required int32 redBagMoney = 3;</code>
   */
  int getRedBagMoney();
}
