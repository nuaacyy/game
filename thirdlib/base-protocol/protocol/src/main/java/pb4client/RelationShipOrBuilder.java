// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface RelationShipOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.RelationShip)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *对方联盟ID
   * </pre>
   *
   * <code>required int64 setAllianceId = 1;</code>
   */
  boolean hasSetAllianceId();
  /**
   * <pre>
   *对方联盟ID
   * </pre>
   *
   * <code>required int64 setAllianceId = 1;</code>
   */
  long getSetAllianceId();

  /**
   * <pre>
   *联盟关系ID:1-友好;2-敌对
   * </pre>
   *
   * <code>required int32 relationShipId = 2;</code>
   */
  boolean hasRelationShipId();
  /**
   * <pre>
   *联盟关系ID:1-友好;2-敌对
   * </pre>
   *
   * <code>required int32 relationShipId = 2;</code>
   */
  int getRelationShipId();
}
