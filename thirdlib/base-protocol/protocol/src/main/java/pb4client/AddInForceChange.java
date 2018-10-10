// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.AddInForceChange}
 */
public  final class AddInForceChange extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.AddInForceChange)
    AddInForceChangeOrBuilder {
  // Use AddInForceChange.newBuilder() to construct.
  private AddInForceChange(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AddInForceChange() {
    changeType_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AddInForceChange(
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
            changeType_ = input.readInt32();
            break;
          }
          case 18: {
            pb4client.AddInForce.Builder subBuilder = null;
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
              subBuilder = addInForces_.toBuilder();
            }
            addInForces_ = input.readMessage(pb4client.AddInForce.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(addInForces_);
              addInForces_ = subBuilder.buildPartial();
            }
            bitField0_ |= 0x00000002;
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
    return pb4client.War2GamePkt.internal_static_client2server_AddInForceChange_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_AddInForceChange_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.AddInForceChange.class, pb4client.AddInForceChange.Builder.class);
  }

  private int bitField0_;
  public static final int CHANGETYPE_FIELD_NUMBER = 1;
  private int changeType_;
  /**
   * <pre>
   *变化类型 1-新增 2-删除 3-修改
   * </pre>
   *
   * <code>required int32 changeType = 1;</code>
   */
  public boolean hasChangeType() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *变化类型 1-新增 2-删除 3-修改
   * </pre>
   *
   * <code>required int32 changeType = 1;</code>
   */
  public int getChangeType() {
    return changeType_;
  }

  public static final int ADDINFORCES_FIELD_NUMBER = 2;
  private pb4client.AddInForce addInForces_;
  /**
   * <pre>
   *武将信息
   * </pre>
   *
   * <code>required .client2server.AddInForce addInForces = 2;</code>
   */
  public boolean hasAddInForces() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *武将信息
   * </pre>
   *
   * <code>required .client2server.AddInForce addInForces = 2;</code>
   */
  public pb4client.AddInForce getAddInForces() {
    return addInForces_ == null ? pb4client.AddInForce.getDefaultInstance() : addInForces_;
  }
  /**
   * <pre>
   *武将信息
   * </pre>
   *
   * <code>required .client2server.AddInForce addInForces = 2;</code>
   */
  public pb4client.AddInForceOrBuilder getAddInForcesOrBuilder() {
    return addInForces_ == null ? pb4client.AddInForce.getDefaultInstance() : addInForces_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasChangeType()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasAddInForces()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!getAddInForces().isInitialized()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, changeType_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeMessage(2, getAddInForces());
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, changeType_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getAddInForces());
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
    if (!(obj instanceof pb4client.AddInForceChange)) {
      return super.equals(obj);
    }
    pb4client.AddInForceChange other = (pb4client.AddInForceChange) obj;

    boolean result = true;
    result = result && (hasChangeType() == other.hasChangeType());
    if (hasChangeType()) {
      result = result && (getChangeType()
          == other.getChangeType());
    }
    result = result && (hasAddInForces() == other.hasAddInForces());
    if (hasAddInForces()) {
      result = result && getAddInForces()
          .equals(other.getAddInForces());
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
    if (hasChangeType()) {
      hash = (37 * hash) + CHANGETYPE_FIELD_NUMBER;
      hash = (53 * hash) + getChangeType();
    }
    if (hasAddInForces()) {
      hash = (37 * hash) + ADDINFORCES_FIELD_NUMBER;
      hash = (53 * hash) + getAddInForces().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.AddInForceChange parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AddInForceChange parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AddInForceChange parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AddInForceChange parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AddInForceChange parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AddInForceChange parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AddInForceChange parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AddInForceChange parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AddInForceChange parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.AddInForceChange parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AddInForceChange parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AddInForceChange parseFrom(
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
  public static Builder newBuilder(pb4client.AddInForceChange prototype) {
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
   * Protobuf type {@code client2server.AddInForceChange}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.AddInForceChange)
      pb4client.AddInForceChangeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_AddInForceChange_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_AddInForceChange_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.AddInForceChange.class, pb4client.AddInForceChange.Builder.class);
    }

    // Construct using pb4client.AddInForceChange.newBuilder()
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
        getAddInForcesFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      changeType_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (addInForcesBuilder_ == null) {
        addInForces_ = null;
      } else {
        addInForcesBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_AddInForceChange_descriptor;
    }

    public pb4client.AddInForceChange getDefaultInstanceForType() {
      return pb4client.AddInForceChange.getDefaultInstance();
    }

    public pb4client.AddInForceChange build() {
      pb4client.AddInForceChange result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.AddInForceChange buildPartial() {
      pb4client.AddInForceChange result = new pb4client.AddInForceChange(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.changeType_ = changeType_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      if (addInForcesBuilder_ == null) {
        result.addInForces_ = addInForces_;
      } else {
        result.addInForces_ = addInForcesBuilder_.build();
      }
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
      if (other instanceof pb4client.AddInForceChange) {
        return mergeFrom((pb4client.AddInForceChange)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.AddInForceChange other) {
      if (other == pb4client.AddInForceChange.getDefaultInstance()) return this;
      if (other.hasChangeType()) {
        setChangeType(other.getChangeType());
      }
      if (other.hasAddInForces()) {
        mergeAddInForces(other.getAddInForces());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasChangeType()) {
        return false;
      }
      if (!hasAddInForces()) {
        return false;
      }
      if (!getAddInForces().isInitialized()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.AddInForceChange parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.AddInForceChange) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int changeType_ ;
    /**
     * <pre>
     *变化类型 1-新增 2-删除 3-修改
     * </pre>
     *
     * <code>required int32 changeType = 1;</code>
     */
    public boolean hasChangeType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *变化类型 1-新增 2-删除 3-修改
     * </pre>
     *
     * <code>required int32 changeType = 1;</code>
     */
    public int getChangeType() {
      return changeType_;
    }
    /**
     * <pre>
     *变化类型 1-新增 2-删除 3-修改
     * </pre>
     *
     * <code>required int32 changeType = 1;</code>
     */
    public Builder setChangeType(int value) {
      bitField0_ |= 0x00000001;
      changeType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *变化类型 1-新增 2-删除 3-修改
     * </pre>
     *
     * <code>required int32 changeType = 1;</code>
     */
    public Builder clearChangeType() {
      bitField0_ = (bitField0_ & ~0x00000001);
      changeType_ = 0;
      onChanged();
      return this;
    }

    private pb4client.AddInForce addInForces_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.AddInForce, pb4client.AddInForce.Builder, pb4client.AddInForceOrBuilder> addInForcesBuilder_;
    /**
     * <pre>
     *武将信息
     * </pre>
     *
     * <code>required .client2server.AddInForce addInForces = 2;</code>
     */
    public boolean hasAddInForces() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *武将信息
     * </pre>
     *
     * <code>required .client2server.AddInForce addInForces = 2;</code>
     */
    public pb4client.AddInForce getAddInForces() {
      if (addInForcesBuilder_ == null) {
        return addInForces_ == null ? pb4client.AddInForce.getDefaultInstance() : addInForces_;
      } else {
        return addInForcesBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     *武将信息
     * </pre>
     *
     * <code>required .client2server.AddInForce addInForces = 2;</code>
     */
    public Builder setAddInForces(pb4client.AddInForce value) {
      if (addInForcesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        addInForces_ = value;
        onChanged();
      } else {
        addInForcesBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <pre>
     *武将信息
     * </pre>
     *
     * <code>required .client2server.AddInForce addInForces = 2;</code>
     */
    public Builder setAddInForces(
        pb4client.AddInForce.Builder builderForValue) {
      if (addInForcesBuilder_ == null) {
        addInForces_ = builderForValue.build();
        onChanged();
      } else {
        addInForcesBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <pre>
     *武将信息
     * </pre>
     *
     * <code>required .client2server.AddInForce addInForces = 2;</code>
     */
    public Builder mergeAddInForces(pb4client.AddInForce value) {
      if (addInForcesBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002) &&
            addInForces_ != null &&
            addInForces_ != pb4client.AddInForce.getDefaultInstance()) {
          addInForces_ =
            pb4client.AddInForce.newBuilder(addInForces_).mergeFrom(value).buildPartial();
        } else {
          addInForces_ = value;
        }
        onChanged();
      } else {
        addInForcesBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <pre>
     *武将信息
     * </pre>
     *
     * <code>required .client2server.AddInForce addInForces = 2;</code>
     */
    public Builder clearAddInForces() {
      if (addInForcesBuilder_ == null) {
        addInForces_ = null;
        onChanged();
      } else {
        addInForcesBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }
    /**
     * <pre>
     *武将信息
     * </pre>
     *
     * <code>required .client2server.AddInForce addInForces = 2;</code>
     */
    public pb4client.AddInForce.Builder getAddInForcesBuilder() {
      bitField0_ |= 0x00000002;
      onChanged();
      return getAddInForcesFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     *武将信息
     * </pre>
     *
     * <code>required .client2server.AddInForce addInForces = 2;</code>
     */
    public pb4client.AddInForceOrBuilder getAddInForcesOrBuilder() {
      if (addInForcesBuilder_ != null) {
        return addInForcesBuilder_.getMessageOrBuilder();
      } else {
        return addInForces_ == null ?
            pb4client.AddInForce.getDefaultInstance() : addInForces_;
      }
    }
    /**
     * <pre>
     *武将信息
     * </pre>
     *
     * <code>required .client2server.AddInForce addInForces = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.AddInForce, pb4client.AddInForce.Builder, pb4client.AddInForceOrBuilder> 
        getAddInForcesFieldBuilder() {
      if (addInForcesBuilder_ == null) {
        addInForcesBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4client.AddInForce, pb4client.AddInForce.Builder, pb4client.AddInForceOrBuilder>(
                getAddInForces(),
                getParentForChildren(),
                isClean());
        addInForces_ = null;
      }
      return addInForcesBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.AddInForceChange)
  }

  // @@protoc_insertion_point(class_scope:client2server.AddInForceChange)
  private static final pb4client.AddInForceChange DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.AddInForceChange();
  }

  public static pb4client.AddInForceChange getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<AddInForceChange>
      PARSER = new com.google.protobuf.AbstractParser<AddInForceChange>() {
    public AddInForceChange parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AddInForceChange(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AddInForceChange> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AddInForceChange> getParserForType() {
    return PARSER;
  }

  public pb4client.AddInForceChange getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
