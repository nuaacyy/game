// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: systemMsg.proto

package pb4server;

public final class Systemkt {
  private Systemkt() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_pb4server_HandoffTell_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_pb4server_HandoffTell_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_pb4server_ChannelExpiredTell_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_pb4server_ChannelExpiredTell_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_pb4server_PingAskReq_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_pb4server_PingAskReq_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_pb4server_PingAskRt_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_pb4server_PingAskRt_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_pb4server_WakeUpWorld_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_pb4server_WakeUpWorld_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_pb4server_InitPublic_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_pb4server_InitPublic_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017systemMsg.proto\022\tpb4server\"\r\n\013HandoffT" +
      "ell\"\024\n\022ChannelExpiredTell\"\036\n\nPingAskReq\022" +
      "\020\n\010playerId\030\001 \001(\003\"\035\n\tPingAskRt\022\020\n\010player" +
      "Id\030\001 \001(\003\"\036\n\013WakeUpWorld\022\017\n\007worldId\030\001 \001(\003" +
      "\"\036\n\nInitPublic\022\020\n\010publicId\030\001 \001(\003B\027\n\tpb4s" +
      "erverB\010SystemktP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_pb4server_HandoffTell_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_pb4server_HandoffTell_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_pb4server_HandoffTell_descriptor,
        new java.lang.String[] { });
    internal_static_pb4server_ChannelExpiredTell_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_pb4server_ChannelExpiredTell_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_pb4server_ChannelExpiredTell_descriptor,
        new java.lang.String[] { });
    internal_static_pb4server_PingAskReq_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_pb4server_PingAskReq_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_pb4server_PingAskReq_descriptor,
        new java.lang.String[] { "PlayerId", });
    internal_static_pb4server_PingAskRt_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_pb4server_PingAskRt_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_pb4server_PingAskRt_descriptor,
        new java.lang.String[] { "PlayerId", });
    internal_static_pb4server_WakeUpWorld_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_pb4server_WakeUpWorld_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_pb4server_WakeUpWorld_descriptor,
        new java.lang.String[] { "WorldId", });
    internal_static_pb4server_InitPublic_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_pb4server_InitPublic_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_pb4server_InitPublic_descriptor,
        new java.lang.String[] { "PublicId", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}