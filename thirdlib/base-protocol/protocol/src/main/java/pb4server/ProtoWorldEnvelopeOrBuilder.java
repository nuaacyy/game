// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

public interface ProtoWorldEnvelopeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.ProtoWorldEnvelope)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 msgType = 1;</code>
   */
  int getMsgType();

  /**
   * <code>int32 clientMsgNo = 2;</code>
   */
  int getClientMsgNo();

  /**
   * <code>int64 playerId = 3;</code>
   */
  long getPlayerId();

  /**
   * <code>int64 worldId = 4;</code>
   */
  long getWorldId();

  /**
   * <code>bytes msgBin = 5;</code>
   */
  com.google.protobuf.ByteString getMsgBin();
}
