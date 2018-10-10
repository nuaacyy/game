// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

/**
 * <pre>
 * 竞技场战斗
 * </pre>
 *
 * Protobuf type {@code pb4server.ArenaFightAskReq}
 */
public  final class ArenaFightAskReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.ArenaFightAskReq)
    ArenaFightAskReqOrBuilder {
  // Use ArenaFightAskReq.newBuilder() to construct.
  private ArenaFightAskReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ArenaFightAskReq() {
    defRank_ = 0;
    defPlayerId_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private ArenaFightAskReq(
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

            defRank_ = input.readInt32();
            break;
          }
          case 16: {

            defPlayerId_ = input.readInt64();
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
    return pb4server.InternalWkt.internal_static_pb4server_ArenaFightAskReq_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalWkt.internal_static_pb4server_ArenaFightAskReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.ArenaFightAskReq.class, pb4server.ArenaFightAskReq.Builder.class);
  }

  public static final int DEFRANK_FIELD_NUMBER = 1;
  private int defRank_;
  /**
   * <code>int32 defRank = 1;</code>
   */
  public int getDefRank() {
    return defRank_;
  }

  public static final int DEFPLAYERID_FIELD_NUMBER = 2;
  private long defPlayerId_;
  /**
   * <code>int64 defPlayerId = 2;</code>
   */
  public long getDefPlayerId() {
    return defPlayerId_;
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
    if (defRank_ != 0) {
      output.writeInt32(1, defRank_);
    }
    if (defPlayerId_ != 0L) {
      output.writeInt64(2, defPlayerId_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (defRank_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, defRank_);
    }
    if (defPlayerId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, defPlayerId_);
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
    if (!(obj instanceof pb4server.ArenaFightAskReq)) {
      return super.equals(obj);
    }
    pb4server.ArenaFightAskReq other = (pb4server.ArenaFightAskReq) obj;

    boolean result = true;
    result = result && (getDefRank()
        == other.getDefRank());
    result = result && (getDefPlayerId()
        == other.getDefPlayerId());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + DEFRANK_FIELD_NUMBER;
    hash = (53 * hash) + getDefRank();
    hash = (37 * hash) + DEFPLAYERID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getDefPlayerId());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.ArenaFightAskReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.ArenaFightAskReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.ArenaFightAskReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.ArenaFightAskReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.ArenaFightAskReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.ArenaFightAskReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.ArenaFightAskReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.ArenaFightAskReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.ArenaFightAskReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.ArenaFightAskReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.ArenaFightAskReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.ArenaFightAskReq parseFrom(
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
  public static Builder newBuilder(pb4server.ArenaFightAskReq prototype) {
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
   * 竞技场战斗
   * </pre>
   *
   * Protobuf type {@code pb4server.ArenaFightAskReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.ArenaFightAskReq)
      pb4server.ArenaFightAskReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalWkt.internal_static_pb4server_ArenaFightAskReq_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalWkt.internal_static_pb4server_ArenaFightAskReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.ArenaFightAskReq.class, pb4server.ArenaFightAskReq.Builder.class);
    }

    // Construct using pb4server.ArenaFightAskReq.newBuilder()
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
      defRank_ = 0;

      defPlayerId_ = 0L;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalWkt.internal_static_pb4server_ArenaFightAskReq_descriptor;
    }

    public pb4server.ArenaFightAskReq getDefaultInstanceForType() {
      return pb4server.ArenaFightAskReq.getDefaultInstance();
    }

    public pb4server.ArenaFightAskReq build() {
      pb4server.ArenaFightAskReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.ArenaFightAskReq buildPartial() {
      pb4server.ArenaFightAskReq result = new pb4server.ArenaFightAskReq(this);
      result.defRank_ = defRank_;
      result.defPlayerId_ = defPlayerId_;
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
      if (other instanceof pb4server.ArenaFightAskReq) {
        return mergeFrom((pb4server.ArenaFightAskReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.ArenaFightAskReq other) {
      if (other == pb4server.ArenaFightAskReq.getDefaultInstance()) return this;
      if (other.getDefRank() != 0) {
        setDefRank(other.getDefRank());
      }
      if (other.getDefPlayerId() != 0L) {
        setDefPlayerId(other.getDefPlayerId());
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
      pb4server.ArenaFightAskReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.ArenaFightAskReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int defRank_ ;
    /**
     * <code>int32 defRank = 1;</code>
     */
    public int getDefRank() {
      return defRank_;
    }
    /**
     * <code>int32 defRank = 1;</code>
     */
    public Builder setDefRank(int value) {
      
      defRank_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 defRank = 1;</code>
     */
    public Builder clearDefRank() {
      
      defRank_ = 0;
      onChanged();
      return this;
    }

    private long defPlayerId_ ;
    /**
     * <code>int64 defPlayerId = 2;</code>
     */
    public long getDefPlayerId() {
      return defPlayerId_;
    }
    /**
     * <code>int64 defPlayerId = 2;</code>
     */
    public Builder setDefPlayerId(long value) {
      
      defPlayerId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 defPlayerId = 2;</code>
     */
    public Builder clearDefPlayerId() {
      
      defPlayerId_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:pb4server.ArenaFightAskReq)
  }

  // @@protoc_insertion_point(class_scope:pb4server.ArenaFightAskReq)
  private static final pb4server.ArenaFightAskReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.ArenaFightAskReq();
  }

  public static pb4server.ArenaFightAskReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ArenaFightAskReq>
      PARSER = new com.google.protobuf.AbstractParser<ArenaFightAskReq>() {
    public ArenaFightAskReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new ArenaFightAskReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ArenaFightAskReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ArenaFightAskReq> getParserForType() {
    return PARSER;
  }

  public pb4server.ArenaFightAskReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
