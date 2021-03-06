// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.KickOutChatRoomAskReq}
 */
public  final class KickOutChatRoomAskReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.KickOutChatRoomAskReq)
    KickOutChatRoomAskReqOrBuilder {
  // Use KickOutChatRoomAskReq.newBuilder() to construct.
  private KickOutChatRoomAskReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private KickOutChatRoomAskReq() {
    roomId_ = 0L;
    playerIdRemove_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private KickOutChatRoomAskReq(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 8: {

            roomId_ = input.readInt64();
            break;
          }
          case 16: {

            playerIdRemove_ = input.readInt64();
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalPkt.internal_static_pb4server_KickOutChatRoomAskReq_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_KickOutChatRoomAskReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.KickOutChatRoomAskReq.class, pb4server.KickOutChatRoomAskReq.Builder.class);
  }

  public static final int ROOMID_FIELD_NUMBER = 1;
  private long roomId_;
  /**
   * <code>int64 roomId = 1;</code>
   */
  public long getRoomId() {
    return roomId_;
  }

  public static final int PLAYERIDREMOVE_FIELD_NUMBER = 2;
  private long playerIdRemove_;
  /**
   * <code>int64 playerIdRemove = 2;</code>
   */
  public long getPlayerIdRemove() {
    return playerIdRemove_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (roomId_ != 0L) {
      output.writeInt64(1, roomId_);
    }
    if (playerIdRemove_ != 0L) {
      output.writeInt64(2, playerIdRemove_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (roomId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, roomId_);
    }
    if (playerIdRemove_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, playerIdRemove_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof pb4server.KickOutChatRoomAskReq)) {
      return super.equals(obj);
    }
    pb4server.KickOutChatRoomAskReq other = (pb4server.KickOutChatRoomAskReq) obj;

    boolean result = true;
    result = result && (getRoomId()
        == other.getRoomId());
    result = result && (getPlayerIdRemove()
        == other.getPlayerIdRemove());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ROOMID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getRoomId());
    hash = (37 * hash) + PLAYERIDREMOVE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getPlayerIdRemove());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.KickOutChatRoomAskReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.KickOutChatRoomAskReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.KickOutChatRoomAskReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.KickOutChatRoomAskReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.KickOutChatRoomAskReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.KickOutChatRoomAskReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.KickOutChatRoomAskReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.KickOutChatRoomAskReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.KickOutChatRoomAskReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.KickOutChatRoomAskReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.KickOutChatRoomAskReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.KickOutChatRoomAskReq parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(pb4server.KickOutChatRoomAskReq prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code pb4server.KickOutChatRoomAskReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.KickOutChatRoomAskReq)
      pb4server.KickOutChatRoomAskReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_KickOutChatRoomAskReq_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_KickOutChatRoomAskReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.KickOutChatRoomAskReq.class, pb4server.KickOutChatRoomAskReq.Builder.class);
    }

    // Construct using pb4server.KickOutChatRoomAskReq.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      roomId_ = 0L;

      playerIdRemove_ = 0L;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_KickOutChatRoomAskReq_descriptor;
    }

    public pb4server.KickOutChatRoomAskReq getDefaultInstanceForType() {
      return pb4server.KickOutChatRoomAskReq.getDefaultInstance();
    }

    public pb4server.KickOutChatRoomAskReq build() {
      pb4server.KickOutChatRoomAskReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.KickOutChatRoomAskReq buildPartial() {
      pb4server.KickOutChatRoomAskReq result = new pb4server.KickOutChatRoomAskReq(this);
      result.roomId_ = roomId_;
      result.playerIdRemove_ = playerIdRemove_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof pb4server.KickOutChatRoomAskReq) {
        return mergeFrom((pb4server.KickOutChatRoomAskReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.KickOutChatRoomAskReq other) {
      if (other == pb4server.KickOutChatRoomAskReq.getDefaultInstance()) return this;
      if (other.getRoomId() != 0L) {
        setRoomId(other.getRoomId());
      }
      if (other.getPlayerIdRemove() != 0L) {
        setPlayerIdRemove(other.getPlayerIdRemove());
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4server.KickOutChatRoomAskReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.KickOutChatRoomAskReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long roomId_ ;
    /**
     * <code>int64 roomId = 1;</code>
     */
    public long getRoomId() {
      return roomId_;
    }
    /**
     * <code>int64 roomId = 1;</code>
     */
    public Builder setRoomId(long value) {
      
      roomId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 roomId = 1;</code>
     */
    public Builder clearRoomId() {
      
      roomId_ = 0L;
      onChanged();
      return this;
    }

    private long playerIdRemove_ ;
    /**
     * <code>int64 playerIdRemove = 2;</code>
     */
    public long getPlayerIdRemove() {
      return playerIdRemove_;
    }
    /**
     * <code>int64 playerIdRemove = 2;</code>
     */
    public Builder setPlayerIdRemove(long value) {
      
      playerIdRemove_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 playerIdRemove = 2;</code>
     */
    public Builder clearPlayerIdRemove() {
      
      playerIdRemove_ = 0L;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.KickOutChatRoomAskReq)
  }

  // @@protoc_insertion_point(class_scope:pb4server.KickOutChatRoomAskReq)
  private static final pb4server.KickOutChatRoomAskReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.KickOutChatRoomAskReq();
  }

  public static pb4server.KickOutChatRoomAskReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<KickOutChatRoomAskReq>
      PARSER = new com.google.protobuf.AbstractParser<KickOutChatRoomAskReq>() {
    public KickOutChatRoomAskReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new KickOutChatRoomAskReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<KickOutChatRoomAskReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<KickOutChatRoomAskReq> getParserForType() {
    return PARSER;
  }

  public pb4server.KickOutChatRoomAskReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

