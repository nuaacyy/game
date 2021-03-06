// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.InstanceStar}
 */
public  final class InstanceStar extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.InstanceStar)
    InstanceStarOrBuilder {
  // Use InstanceStar.newBuilder() to construct.
  private InstanceStar(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private InstanceStar() {
    floorId_ = 0;
    starCondition_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private InstanceStar(
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
            floorId_ = input.readInt32();
            break;
          }
          case 16: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              starCondition_ = new java.util.ArrayList<java.lang.Integer>();
              mutable_bitField0_ |= 0x00000002;
            }
            starCondition_.add(input.readInt32());
            break;
          }
          case 18: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002) && input.getBytesUntilLimit() > 0) {
              starCondition_ = new java.util.ArrayList<java.lang.Integer>();
              mutable_bitField0_ |= 0x00000002;
            }
            while (input.getBytesUntilLimit() > 0) {
              starCondition_.add(input.readInt32());
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
      if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
        starCondition_ = java.util.Collections.unmodifiableList(starCondition_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_InstanceStar_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_InstanceStar_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.InstanceStar.class, pb4client.InstanceStar.Builder.class);
  }

  private int bitField0_;
  public static final int FLOORID_FIELD_NUMBER = 1;
  private int floorId_;
  /**
   * <pre>
   * 层数
   * </pre>
   *
   * <code>required int32 floorId = 1;</code>
   */
  public boolean hasFloorId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 层数
   * </pre>
   *
   * <code>required int32 floorId = 1;</code>
   */
  public int getFloorId() {
    return floorId_;
  }

  public static final int STARCONDITION_FIELD_NUMBER = 2;
  private java.util.List<java.lang.Integer> starCondition_;
  /**
   * <pre>
   * 星数条件
   * </pre>
   *
   * <code>repeated int32 starCondition = 2;</code>
   */
  public java.util.List<java.lang.Integer>
      getStarConditionList() {
    return starCondition_;
  }
  /**
   * <pre>
   * 星数条件
   * </pre>
   *
   * <code>repeated int32 starCondition = 2;</code>
   */
  public int getStarConditionCount() {
    return starCondition_.size();
  }
  /**
   * <pre>
   * 星数条件
   * </pre>
   *
   * <code>repeated int32 starCondition = 2;</code>
   */
  public int getStarCondition(int index) {
    return starCondition_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasFloorId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, floorId_);
    }
    for (int i = 0; i < starCondition_.size(); i++) {
      output.writeInt32(2, starCondition_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, floorId_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < starCondition_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt32SizeNoTag(starCondition_.get(i));
      }
      size += dataSize;
      size += 1 * getStarConditionList().size();
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
    if (!(obj instanceof pb4client.InstanceStar)) {
      return super.equals(obj);
    }
    pb4client.InstanceStar other = (pb4client.InstanceStar) obj;

    boolean result = true;
    result = result && (hasFloorId() == other.hasFloorId());
    if (hasFloorId()) {
      result = result && (getFloorId()
          == other.getFloorId());
    }
    result = result && getStarConditionList()
        .equals(other.getStarConditionList());
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
    if (hasFloorId()) {
      hash = (37 * hash) + FLOORID_FIELD_NUMBER;
      hash = (53 * hash) + getFloorId();
    }
    if (getStarConditionCount() > 0) {
      hash = (37 * hash) + STARCONDITION_FIELD_NUMBER;
      hash = (53 * hash) + getStarConditionList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.InstanceStar parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.InstanceStar parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.InstanceStar parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.InstanceStar parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.InstanceStar parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.InstanceStar parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.InstanceStar parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.InstanceStar parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.InstanceStar parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.InstanceStar parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.InstanceStar parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.InstanceStar parseFrom(
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
  public static Builder newBuilder(pb4client.InstanceStar prototype) {
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
   * Protobuf type {@code client2server.InstanceStar}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.InstanceStar)
      pb4client.InstanceStarOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_InstanceStar_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_InstanceStar_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.InstanceStar.class, pb4client.InstanceStar.Builder.class);
    }

    // Construct using pb4client.InstanceStar.newBuilder()
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
      floorId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      starCondition_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_InstanceStar_descriptor;
    }

    public pb4client.InstanceStar getDefaultInstanceForType() {
      return pb4client.InstanceStar.getDefaultInstance();
    }

    public pb4client.InstanceStar build() {
      pb4client.InstanceStar result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.InstanceStar buildPartial() {
      pb4client.InstanceStar result = new pb4client.InstanceStar(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.floorId_ = floorId_;
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        starCondition_ = java.util.Collections.unmodifiableList(starCondition_);
        bitField0_ = (bitField0_ & ~0x00000002);
      }
      result.starCondition_ = starCondition_;
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
      if (other instanceof pb4client.InstanceStar) {
        return mergeFrom((pb4client.InstanceStar)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.InstanceStar other) {
      if (other == pb4client.InstanceStar.getDefaultInstance()) return this;
      if (other.hasFloorId()) {
        setFloorId(other.getFloorId());
      }
      if (!other.starCondition_.isEmpty()) {
        if (starCondition_.isEmpty()) {
          starCondition_ = other.starCondition_;
          bitField0_ = (bitField0_ & ~0x00000002);
        } else {
          ensureStarConditionIsMutable();
          starCondition_.addAll(other.starCondition_);
        }
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasFloorId()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.InstanceStar parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.InstanceStar) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int floorId_ ;
    /**
     * <pre>
     * 层数
     * </pre>
     *
     * <code>required int32 floorId = 1;</code>
     */
    public boolean hasFloorId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 层数
     * </pre>
     *
     * <code>required int32 floorId = 1;</code>
     */
    public int getFloorId() {
      return floorId_;
    }
    /**
     * <pre>
     * 层数
     * </pre>
     *
     * <code>required int32 floorId = 1;</code>
     */
    public Builder setFloorId(int value) {
      bitField0_ |= 0x00000001;
      floorId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 层数
     * </pre>
     *
     * <code>required int32 floorId = 1;</code>
     */
    public Builder clearFloorId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      floorId_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<java.lang.Integer> starCondition_ = java.util.Collections.emptyList();
    private void ensureStarConditionIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        starCondition_ = new java.util.ArrayList<java.lang.Integer>(starCondition_);
        bitField0_ |= 0x00000002;
       }
    }
    /**
     * <pre>
     * 星数条件
     * </pre>
     *
     * <code>repeated int32 starCondition = 2;</code>
     */
    public java.util.List<java.lang.Integer>
        getStarConditionList() {
      return java.util.Collections.unmodifiableList(starCondition_);
    }
    /**
     * <pre>
     * 星数条件
     * </pre>
     *
     * <code>repeated int32 starCondition = 2;</code>
     */
    public int getStarConditionCount() {
      return starCondition_.size();
    }
    /**
     * <pre>
     * 星数条件
     * </pre>
     *
     * <code>repeated int32 starCondition = 2;</code>
     */
    public int getStarCondition(int index) {
      return starCondition_.get(index);
    }
    /**
     * <pre>
     * 星数条件
     * </pre>
     *
     * <code>repeated int32 starCondition = 2;</code>
     */
    public Builder setStarCondition(
        int index, int value) {
      ensureStarConditionIsMutable();
      starCondition_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 星数条件
     * </pre>
     *
     * <code>repeated int32 starCondition = 2;</code>
     */
    public Builder addStarCondition(int value) {
      ensureStarConditionIsMutable();
      starCondition_.add(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 星数条件
     * </pre>
     *
     * <code>repeated int32 starCondition = 2;</code>
     */
    public Builder addAllStarCondition(
        java.lang.Iterable<? extends java.lang.Integer> values) {
      ensureStarConditionIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, starCondition_);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 星数条件
     * </pre>
     *
     * <code>repeated int32 starCondition = 2;</code>
     */
    public Builder clearStarCondition() {
      starCondition_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000002);
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


    // @@protoc_insertion_point(builder_scope:client2server.InstanceStar)
  }

  // @@protoc_insertion_point(class_scope:client2server.InstanceStar)
  private static final pb4client.InstanceStar DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.InstanceStar();
  }

  public static pb4client.InstanceStar getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<InstanceStar>
      PARSER = new com.google.protobuf.AbstractParser<InstanceStar>() {
    public InstanceStar parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new InstanceStar(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<InstanceStar> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<InstanceStar> getParserForType() {
    return PARSER;
  }

  public pb4client.InstanceStar getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

