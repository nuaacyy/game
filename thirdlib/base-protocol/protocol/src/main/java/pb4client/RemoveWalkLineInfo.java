// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.RemoveWalkLineInfo}
 */
public  final class RemoveWalkLineInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.RemoveWalkLineInfo)
    RemoveWalkLineInfoOrBuilder {
  // Use RemoveWalkLineInfo.newBuilder() to construct.
  private RemoveWalkLineInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RemoveWalkLineInfo() {
    onlyId_ = 0L;
    removeSource_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private RemoveWalkLineInfo(
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
            bitField0_ |= 0x00000001;
            onlyId_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            removeSource_ = input.readInt32();
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
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_RemoveWalkLineInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_RemoveWalkLineInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.RemoveWalkLineInfo.class, pb4client.RemoveWalkLineInfo.Builder.class);
  }

  private int bitField0_;
  public static final int ONLYID_FIELD_NUMBER = 1;
  private long onlyId_;
  /**
   * <pre>
   * 需要删除的ID
   * </pre>
   *
   * <code>required int64 onlyId = 1;</code>
   */
  public boolean hasOnlyId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 需要删除的ID
   * </pre>
   *
   * <code>required int64 onlyId = 1;</code>
   */
  public long getOnlyId() {
    return onlyId_;
  }

  public static final int REMOVESOURCE_FIELD_NUMBER = 2;
  private int removeSource_;
  /**
   * <pre>
   *删除信息来源 1-来自自己  2-来自盟友 3-来自下属
   * </pre>
   *
   * <code>required int32 removeSource = 2;</code>
   */
  public boolean hasRemoveSource() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *删除信息来源 1-来自自己  2-来自盟友 3-来自下属
   * </pre>
   *
   * <code>required int32 removeSource = 2;</code>
   */
  public int getRemoveSource() {
    return removeSource_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasOnlyId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasRemoveSource()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, onlyId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, removeSource_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, onlyId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, removeSource_);
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
    if (!(obj instanceof pb4client.RemoveWalkLineInfo)) {
      return super.equals(obj);
    }
    pb4client.RemoveWalkLineInfo other = (pb4client.RemoveWalkLineInfo) obj;

    boolean result = true;
    result = result && (hasOnlyId() == other.hasOnlyId());
    if (hasOnlyId()) {
      result = result && (getOnlyId()
          == other.getOnlyId());
    }
    result = result && (hasRemoveSource() == other.hasRemoveSource());
    if (hasRemoveSource()) {
      result = result && (getRemoveSource()
          == other.getRemoveSource());
    }
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
    if (hasOnlyId()) {
      hash = (37 * hash) + ONLYID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getOnlyId());
    }
    if (hasRemoveSource()) {
      hash = (37 * hash) + REMOVESOURCE_FIELD_NUMBER;
      hash = (53 * hash) + getRemoveSource();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.RemoveWalkLineInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.RemoveWalkLineInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.RemoveWalkLineInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.RemoveWalkLineInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.RemoveWalkLineInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.RemoveWalkLineInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.RemoveWalkLineInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.RemoveWalkLineInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.RemoveWalkLineInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.RemoveWalkLineInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.RemoveWalkLineInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.RemoveWalkLineInfo parseFrom(
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
  public static Builder newBuilder(pb4client.RemoveWalkLineInfo prototype) {
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
   * Protobuf type {@code client2server.RemoveWalkLineInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.RemoveWalkLineInfo)
      pb4client.RemoveWalkLineInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_RemoveWalkLineInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_RemoveWalkLineInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.RemoveWalkLineInfo.class, pb4client.RemoveWalkLineInfo.Builder.class);
    }

    // Construct using pb4client.RemoveWalkLineInfo.newBuilder()
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
      onlyId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      removeSource_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_RemoveWalkLineInfo_descriptor;
    }

    public pb4client.RemoveWalkLineInfo getDefaultInstanceForType() {
      return pb4client.RemoveWalkLineInfo.getDefaultInstance();
    }

    public pb4client.RemoveWalkLineInfo build() {
      pb4client.RemoveWalkLineInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.RemoveWalkLineInfo buildPartial() {
      pb4client.RemoveWalkLineInfo result = new pb4client.RemoveWalkLineInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.onlyId_ = onlyId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.removeSource_ = removeSource_;
      result.bitField0_ = to_bitField0_;
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
      if (other instanceof pb4client.RemoveWalkLineInfo) {
        return mergeFrom((pb4client.RemoveWalkLineInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.RemoveWalkLineInfo other) {
      if (other == pb4client.RemoveWalkLineInfo.getDefaultInstance()) return this;
      if (other.hasOnlyId()) {
        setOnlyId(other.getOnlyId());
      }
      if (other.hasRemoveSource()) {
        setRemoveSource(other.getRemoveSource());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasOnlyId()) {
        return false;
      }
      if (!hasRemoveSource()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.RemoveWalkLineInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.RemoveWalkLineInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long onlyId_ ;
    /**
     * <pre>
     * 需要删除的ID
     * </pre>
     *
     * <code>required int64 onlyId = 1;</code>
     */
    public boolean hasOnlyId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 需要删除的ID
     * </pre>
     *
     * <code>required int64 onlyId = 1;</code>
     */
    public long getOnlyId() {
      return onlyId_;
    }
    /**
     * <pre>
     * 需要删除的ID
     * </pre>
     *
     * <code>required int64 onlyId = 1;</code>
     */
    public Builder setOnlyId(long value) {
      bitField0_ |= 0x00000001;
      onlyId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 需要删除的ID
     * </pre>
     *
     * <code>required int64 onlyId = 1;</code>
     */
    public Builder clearOnlyId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      onlyId_ = 0L;
      onChanged();
      return this;
    }

    private int removeSource_ ;
    /**
     * <pre>
     *删除信息来源 1-来自自己  2-来自盟友 3-来自下属
     * </pre>
     *
     * <code>required int32 removeSource = 2;</code>
     */
    public boolean hasRemoveSource() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *删除信息来源 1-来自自己  2-来自盟友 3-来自下属
     * </pre>
     *
     * <code>required int32 removeSource = 2;</code>
     */
    public int getRemoveSource() {
      return removeSource_;
    }
    /**
     * <pre>
     *删除信息来源 1-来自自己  2-来自盟友 3-来自下属
     * </pre>
     *
     * <code>required int32 removeSource = 2;</code>
     */
    public Builder setRemoveSource(int value) {
      bitField0_ |= 0x00000002;
      removeSource_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *删除信息来源 1-来自自己  2-来自盟友 3-来自下属
     * </pre>
     *
     * <code>required int32 removeSource = 2;</code>
     */
    public Builder clearRemoveSource() {
      bitField0_ = (bitField0_ & ~0x00000002);
      removeSource_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.RemoveWalkLineInfo)
  }

  // @@protoc_insertion_point(class_scope:client2server.RemoveWalkLineInfo)
  private static final pb4client.RemoveWalkLineInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.RemoveWalkLineInfo();
  }

  public static pb4client.RemoveWalkLineInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<RemoveWalkLineInfo>
      PARSER = new com.google.protobuf.AbstractParser<RemoveWalkLineInfo>() {
    public RemoveWalkLineInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new RemoveWalkLineInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<RemoveWalkLineInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RemoveWalkLineInfo> getParserForType() {
    return PARSER;
  }

  public pb4client.RemoveWalkLineInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
