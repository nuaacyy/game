// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface PlantRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.PlantRt)
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
   * <code>optional .client2server.PlantVo plantVo = 2;</code>
   */
  boolean hasPlantVo();
  /**
   * <code>optional .client2server.PlantVo plantVo = 2;</code>
   */
  pb4client.PlantVo getPlantVo();
  /**
   * <code>optional .client2server.PlantVo plantVo = 2;</code>
   */
  pb4client.PlantVoOrBuilder getPlantVoOrBuilder();
}
