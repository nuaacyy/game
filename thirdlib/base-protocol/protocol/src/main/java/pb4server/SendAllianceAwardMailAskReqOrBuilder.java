// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

public interface SendAllianceAwardMailAskReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.SendAllianceAwardMailAskReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 readType = 1;</code>
   */
  int getReadType();

  /**
   * <code>string title = 2;</code>
   */
  java.lang.String getTitle();
  /**
   * <code>string title = 2;</code>
   */
  com.google.protobuf.ByteString
      getTitleBytes();

  /**
   * <code>repeated string titleParas = 3;</code>
   */
  java.util.List<java.lang.String>
      getTitleParasList();
  /**
   * <code>repeated string titleParas = 3;</code>
   */
  int getTitleParasCount();
  /**
   * <code>repeated string titleParas = 3;</code>
   */
  java.lang.String getTitleParas(int index);
  /**
   * <code>repeated string titleParas = 3;</code>
   */
  com.google.protobuf.ByteString
      getTitleParasBytes(int index);

  /**
   * <code>string content = 4;</code>
   */
  java.lang.String getContent();
  /**
   * <code>string content = 4;</code>
   */
  com.google.protobuf.ByteString
      getContentBytes();

  /**
   * <code>repeated string contentParas = 5;</code>
   */
  java.util.List<java.lang.String>
      getContentParasList();
  /**
   * <code>repeated string contentParas = 5;</code>
   */
  int getContentParasCount();
  /**
   * <code>repeated string contentParas = 5;</code>
   */
  java.lang.String getContentParas(int index);
  /**
   * <code>repeated string contentParas = 5;</code>
   */
  com.google.protobuf.ByteString
      getContentParasBytes(int index);

  /**
   * <code>string attach = 6;</code>
   */
  java.lang.String getAttach();
  /**
   * <code>string attach = 6;</code>
   */
  com.google.protobuf.ByteString
      getAttachBytes();
}
