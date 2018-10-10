// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3118
 * 删除N个武将
 * </pre>
 *
 * Protobuf type {@code client2server.DeleteHero}
 */
public  final class DeleteHero extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.DeleteHero)
    DeleteHeroOrBuilder {
  // Use DeleteHero.newBuilder() to construct.
  private DeleteHero(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private DeleteHero() {
    deleteHeroIds_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private DeleteHero(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownField(input, unknownFields,
                                   extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 8: {
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              deleteHeroIds_ = new java.util.ArrayList<java.lang.Long>();
              mutable_bitField0_ |= 0x00000001;
            }
            deleteHeroIds_.add(input.readInt64());
            break;
          }
          case 10: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001) && input.getBytesUntilLimit() > 0) {
              deleteHeroIds_ = new java.util.ArrayList<java.lang.Long>();
              mutable_bitField0_ |= 0x00000001;
            }
            while (input.getBytesUntilLimit() > 0) {
              deleteHeroIds_.add(input.readInt64());
            }
            input.popLimit(limit);
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
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        deleteHeroIds_ = java.util.Collections.unmodifiableList(deleteHeroIds_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_DeleteHero_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_DeleteHero_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.DeleteHero.class, pb4client.DeleteHero.Builder.class);
  }

  public static final int DELETEHEROIDS_FIELD_NUMBER = 1;
  private java.util.List<java.lang.Long> deleteHeroIds_;
  /**
   * <pre>
   *被删除的武将的ids
   * </pre>
   *
   * <code>repeated int64 deleteHeroIds = 1;</code>
   */
  public java.util.List<java.lang.Long>
      getDeleteHeroIdsList() {
    return deleteHeroIds_;
  }
  /**
   * <pre>
   *被删除的武将的ids
   * </pre>
   *
   * <code>repeated int64 deleteHeroIds = 1;</code>
   */
  public int getDeleteHeroIdsCount() {
    return deleteHeroIds_.size();
  }
  /**
   * <pre>
   *被删除的武将的ids
   * </pre>
   *
   * <code>repeated int64 deleteHeroIds = 1;</code>
   */
  public long getDeleteHeroIds(int index) {
    return deleteHeroIds_.get(index);
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
    for (int i = 0; i < deleteHeroIds_.size(); i++) {
      output.writeInt64(1, deleteHeroIds_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      for (int i = 0; i < deleteHeroIds_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt64SizeNoTag(deleteHeroIds_.get(i));
      }
      size += dataSize;
      size += 1 * getDeleteHeroIdsList().size();
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof pb4client.DeleteHero)) {
      return super.equals(obj);
    }
    pb4client.DeleteHero other = (pb4client.DeleteHero) obj;

    boolean result = true;
    result = result && getDeleteHeroIdsList()
        .equals(other.getDeleteHeroIdsList());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getDeleteHeroIdsCount() > 0) {
      hash = (37 * hash) + DELETEHEROIDS_FIELD_NUMBER;
      hash = (53 * hash) + getDeleteHeroIdsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.DeleteHero parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.DeleteHero parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.DeleteHero parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.DeleteHero parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.DeleteHero parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.DeleteHero parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.DeleteHero parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.DeleteHero parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.DeleteHero parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.DeleteHero parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.DeleteHero parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.DeleteHero parseFrom(
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
  public static Builder newBuilder(pb4client.DeleteHero prototype) {
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
   * msgType = 3118
   * 删除N个武将
   * </pre>
   *
   * Protobuf type {@code client2server.DeleteHero}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.DeleteHero)
      pb4client.DeleteHeroOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_DeleteHero_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_DeleteHero_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.DeleteHero.class, pb4client.DeleteHero.Builder.class);
    }

    // Construct using pb4client.DeleteHero.newBuilder()
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
      deleteHeroIds_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_DeleteHero_descriptor;
    }

    public pb4client.DeleteHero getDefaultInstanceForType() {
      return pb4client.DeleteHero.getDefaultInstance();
    }

    public pb4client.DeleteHero build() {
      pb4client.DeleteHero result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.DeleteHero buildPartial() {
      pb4client.DeleteHero result = new pb4client.DeleteHero(this);
      int from_bitField0_ = bitField0_;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        deleteHeroIds_ = java.util.Collections.unmodifiableList(deleteHeroIds_);
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.deleteHeroIds_ = deleteHeroIds_;
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
      if (other instanceof pb4client.DeleteHero) {
        return mergeFrom((pb4client.DeleteHero)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.DeleteHero other) {
      if (other == pb4client.DeleteHero.getDefaultInstance()) return this;
      if (!other.deleteHeroIds_.isEmpty()) {
        if (deleteHeroIds_.isEmpty()) {
          deleteHeroIds_ = other.deleteHeroIds_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureDeleteHeroIdsIsMutable();
          deleteHeroIds_.addAll(other.deleteHeroIds_);
        }
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
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
      pb4client.DeleteHero parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.DeleteHero) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<java.lang.Long> deleteHeroIds_ = java.util.Collections.emptyList();
    private void ensureDeleteHeroIdsIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        deleteHeroIds_ = new java.util.ArrayList<java.lang.Long>(deleteHeroIds_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <pre>
     *被删除的武将的ids
     * </pre>
     *
     * <code>repeated int64 deleteHeroIds = 1;</code>
     */
    public java.util.List<java.lang.Long>
        getDeleteHeroIdsList() {
      return java.util.Collections.unmodifiableList(deleteHeroIds_);
    }
    /**
     * <pre>
     *被删除的武将的ids
     * </pre>
     *
     * <code>repeated int64 deleteHeroIds = 1;</code>
     */
    public int getDeleteHeroIdsCount() {
      return deleteHeroIds_.size();
    }
    /**
     * <pre>
     *被删除的武将的ids
     * </pre>
     *
     * <code>repeated int64 deleteHeroIds = 1;</code>
     */
    public long getDeleteHeroIds(int index) {
      return deleteHeroIds_.get(index);
    }
    /**
     * <pre>
     *被删除的武将的ids
     * </pre>
     *
     * <code>repeated int64 deleteHeroIds = 1;</code>
     */
    public Builder setDeleteHeroIds(
        int index, long value) {
      ensureDeleteHeroIdsIsMutable();
      deleteHeroIds_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *被删除的武将的ids
     * </pre>
     *
     * <code>repeated int64 deleteHeroIds = 1;</code>
     */
    public Builder addDeleteHeroIds(long value) {
      ensureDeleteHeroIdsIsMutable();
      deleteHeroIds_.add(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *被删除的武将的ids
     * </pre>
     *
     * <code>repeated int64 deleteHeroIds = 1;</code>
     */
    public Builder addAllDeleteHeroIds(
        java.lang.Iterable<? extends java.lang.Long> values) {
      ensureDeleteHeroIdsIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, deleteHeroIds_);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *被删除的武将的ids
     * </pre>
     *
     * <code>repeated int64 deleteHeroIds = 1;</code>
     */
    public Builder clearDeleteHeroIds() {
      deleteHeroIds_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.DeleteHero)
  }

  // @@protoc_insertion_point(class_scope:client2server.DeleteHero)
  private static final pb4client.DeleteHero DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.DeleteHero();
  }

  public static pb4client.DeleteHero getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<DeleteHero>
      PARSER = new com.google.protobuf.AbstractParser<DeleteHero>() {
    public DeleteHero parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new DeleteHero(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<DeleteHero> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<DeleteHero> getParserForType() {
    return PARSER;
  }

  public pb4client.DeleteHero getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

