// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

/**
 * <pre>
 * 加入聊天室前查询home
 * </pre>
 *
 * Protobuf type {@code pb4server.BeforeJoinRoomAskReq}
 */
public  final class BeforeJoinRoomAskReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.BeforeJoinRoomAskReq)
    BeforeJoinRoomAskReqOrBuilder {
  // Use BeforeJoinRoomAskReq.newBuilder() to construct.
  private BeforeJoinRoomAskReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private BeforeJoinRoomAskReq() {
    groupId_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private BeforeJoinRoomAskReq(
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

            groupId_ = input.readInt64();
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
    return pb4server.InternalHkt.internal_static_pb4server_BeforeJoinRoomAskReq_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalHkt.internal_static_pb4server_BeforeJoinRoomAskReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.BeforeJoinRoomAskReq.class, pb4server.BeforeJoinRoomAskReq.Builder.class);
  }

  public static final int GROUPID_FIELD_NUMBER = 1;
  private long groupId_;
  /**
   * <code>int64 groupId = 1;</code>
   */
  public long getGroupId() {
    return groupId_;
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
    if (groupId_ != 0L) {
      output.writeInt64(1, groupId_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (groupId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, groupId_);
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
    if (!(obj instanceof pb4server.BeforeJoinRoomAskReq)) {
      return super.equals(obj);
    }
    pb4server.BeforeJoinRoomAskReq other = (pb4server.BeforeJoinRoomAskReq) obj;

    boolean result = true;
    result = result && (getGroupId()
        == other.getGroupId());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + GROUPID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getGroupId());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.BeforeJoinRoomAskReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.BeforeJoinRoomAskReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.BeforeJoinRoomAskReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.BeforeJoinRoomAskReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.BeforeJoinRoomAskReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.BeforeJoinRoomAskReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.BeforeJoinRoomAskReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.BeforeJoinRoomAskReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.BeforeJoinRoomAskReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.BeforeJoinRoomAskReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.BeforeJoinRoomAskReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.BeforeJoinRoomAskReq parseFrom(
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
  public static Builder newBuilder(pb4server.BeforeJoinRoomAskReq prototype) {
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
   * <pre>
   * 加入聊天室前查询home
   * </pre>
   *
   * Protobuf type {@code pb4server.BeforeJoinRoomAskReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.BeforeJoinRoomAskReq)
      pb4server.BeforeJoinRoomAskReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalHkt.internal_static_pb4server_BeforeJoinRoomAskReq_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalHkt.internal_static_pb4server_BeforeJoinRoomAskReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.BeforeJoinRoomAskReq.class, pb4server.BeforeJoinRoomAskReq.Builder.class);
    }

    // Construct using pb4server.BeforeJoinRoomAskReq.newBuilder()
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
      groupId_ = 0L;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalHkt.internal_static_pb4server_BeforeJoinRoomAskReq_descriptor;
    }

    public pb4server.BeforeJoinRoomAskReq getDefaultInstanceForType() {
      return pb4server.BeforeJoinRoomAskReq.getDefaultInstance();
    }

    public pb4server.BeforeJoinRoomAskReq build() {
      pb4server.BeforeJoinRoomAskReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.BeforeJoinRoomAskReq buildPartial() {
      pb4server.BeforeJoinRoomAskReq result = new pb4server.BeforeJoinRoomAskReq(this);
      result.groupId_ = groupId_;
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
      if (other instanceof pb4server.BeforeJoinRoomAskReq) {
        return mergeFrom((pb4server.BeforeJoinRoomAskReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.BeforeJoinRoomAskReq other) {
      if (other == pb4server.BeforeJoinRoomAskReq.getDefaultInstance()) return this;
      if (other.getGroupId() != 0L) {
        setGroupId(other.getGroupId());
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
      pb4server.BeforeJoinRoomAskReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.BeforeJoinRoomAskReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long groupId_ ;
    /**
     * <code>int64 groupId = 1;</code>
     */
    public long getGroupId() {
      return groupId_;
    }
    /**
     * <code>int64 groupId = 1;</code>
     */
    public Builder setGroupId(long value) {
      
      groupId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 groupId = 1;</code>
     */
    public Builder clearGroupId() {
      
      groupId_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:pb4server.BeforeJoinRoomAskReq)
  }

  // @@protoc_insertion_point(class_scope:pb4server.BeforeJoinRoomAskReq)
  private static final pb4server.BeforeJoinRoomAskReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.BeforeJoinRoomAskReq();
  }

  public static pb4server.BeforeJoinRoomAskReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<BeforeJoinRoomAskReq>
      PARSER = new com.google.protobuf.AbstractParser<BeforeJoinRoomAskReq>() {
    public BeforeJoinRoomAskReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new BeforeJoinRoomAskReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<BeforeJoinRoomAskReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<BeforeJoinRoomAskReq> getParserForType() {
    return PARSER;
  }

  public pb4server.BeforeJoinRoomAskReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

