// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3143
 * 服务器 -&gt; 客户端
 * 集结通知
 * </pre>
 *
 * Protobuf type {@code client2server.NoticeMassGroupChange}
 */
public  final class NoticeMassGroupChange extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.NoticeMassGroupChange)
    NoticeMassGroupChangeOrBuilder {
  // Use NoticeMassGroupChange.newBuilder() to construct.
  private NoticeMassGroupChange(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private NoticeMassGroupChange() {
    changeType_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private NoticeMassGroupChange(
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
          case 26: {
            pb4client.MassGroup.Builder subBuilder = null;
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
              subBuilder = massGroup_.toBuilder();
            }
            massGroup_ = input.readMessage(pb4client.MassGroup.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(massGroup_);
              massGroup_ = subBuilder.buildPartial();
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
    return pb4client.War2GamePkt.internal_static_client2server_NoticeMassGroupChange_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_NoticeMassGroupChange_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.NoticeMassGroupChange.class, pb4client.NoticeMassGroupChange.Builder.class);
  }

  private int bitField0_;
  public static final int CHANGETYPE_FIELD_NUMBER = 1;
  private int changeType_;
  /**
   * <pre>
   *1-新增  2-删除 3-更新
   * </pre>
   *
   * <code>required int32 changeType = 1;</code>
   */
  public boolean hasChangeType() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *1-新增  2-删除 3-更新
   * </pre>
   *
   * <code>required int32 changeType = 1;</code>
   */
  public int getChangeType() {
    return changeType_;
  }

  public static final int MASSGROUP_FIELD_NUMBER = 3;
  private pb4client.MassGroup massGroup_;
  /**
   * <pre>
   *集结组
   * </pre>
   *
   * <code>required .client2server.MassGroup massGroup = 3;</code>
   */
  public boolean hasMassGroup() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *集结组
   * </pre>
   *
   * <code>required .client2server.MassGroup massGroup = 3;</code>
   */
  public pb4client.MassGroup getMassGroup() {
    return massGroup_ == null ? pb4client.MassGroup.getDefaultInstance() : massGroup_;
  }
  /**
   * <pre>
   *集结组
   * </pre>
   *
   * <code>required .client2server.MassGroup massGroup = 3;</code>
   */
  public pb4client.MassGroupOrBuilder getMassGroupOrBuilder() {
    return massGroup_ == null ? pb4client.MassGroup.getDefaultInstance() : massGroup_;
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
    if (!hasMassGroup()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!getMassGroup().isInitialized()) {
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
      output.writeMessage(3, getMassGroup());
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
        .computeMessageSize(3, getMassGroup());
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
    if (!(obj instanceof pb4client.NoticeMassGroupChange)) {
      return super.equals(obj);
    }
    pb4client.NoticeMassGroupChange other = (pb4client.NoticeMassGroupChange) obj;

    boolean result = true;
    result = result && (hasChangeType() == other.hasChangeType());
    if (hasChangeType()) {
      result = result && (getChangeType()
          == other.getChangeType());
    }
    result = result && (hasMassGroup() == other.hasMassGroup());
    if (hasMassGroup()) {
      result = result && getMassGroup()
          .equals(other.getMassGroup());
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
    if (hasMassGroup()) {
      hash = (37 * hash) + MASSGROUP_FIELD_NUMBER;
      hash = (53 * hash) + getMassGroup().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.NoticeMassGroupChange parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.NoticeMassGroupChange parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.NoticeMassGroupChange parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.NoticeMassGroupChange parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.NoticeMassGroupChange parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.NoticeMassGroupChange parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.NoticeMassGroupChange parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.NoticeMassGroupChange parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.NoticeMassGroupChange parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.NoticeMassGroupChange parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.NoticeMassGroupChange parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.NoticeMassGroupChange parseFrom(
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
  public static Builder newBuilder(pb4client.NoticeMassGroupChange prototype) {
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
   * msgType = 3143
   * 服务器 -&gt; 客户端
   * 集结通知
   * </pre>
   *
   * Protobuf type {@code client2server.NoticeMassGroupChange}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.NoticeMassGroupChange)
      pb4client.NoticeMassGroupChangeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_NoticeMassGroupChange_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_NoticeMassGroupChange_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.NoticeMassGroupChange.class, pb4client.NoticeMassGroupChange.Builder.class);
    }

    // Construct using pb4client.NoticeMassGroupChange.newBuilder()
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
        getMassGroupFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      changeType_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (massGroupBuilder_ == null) {
        massGroup_ = null;
      } else {
        massGroupBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_NoticeMassGroupChange_descriptor;
    }

    public pb4client.NoticeMassGroupChange getDefaultInstanceForType() {
      return pb4client.NoticeMassGroupChange.getDefaultInstance();
    }

    public pb4client.NoticeMassGroupChange build() {
      pb4client.NoticeMassGroupChange result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.NoticeMassGroupChange buildPartial() {
      pb4client.NoticeMassGroupChange result = new pb4client.NoticeMassGroupChange(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.changeType_ = changeType_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      if (massGroupBuilder_ == null) {
        result.massGroup_ = massGroup_;
      } else {
        result.massGroup_ = massGroupBuilder_.build();
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
      if (other instanceof pb4client.NoticeMassGroupChange) {
        return mergeFrom((pb4client.NoticeMassGroupChange)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.NoticeMassGroupChange other) {
      if (other == pb4client.NoticeMassGroupChange.getDefaultInstance()) return this;
      if (other.hasChangeType()) {
        setChangeType(other.getChangeType());
      }
      if (other.hasMassGroup()) {
        mergeMassGroup(other.getMassGroup());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasChangeType()) {
        return false;
      }
      if (!hasMassGroup()) {
        return false;
      }
      if (!getMassGroup().isInitialized()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.NoticeMassGroupChange parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.NoticeMassGroupChange) e.getUnfinishedMessage();
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
     *1-新增  2-删除 3-更新
     * </pre>
     *
     * <code>required int32 changeType = 1;</code>
     */
    public boolean hasChangeType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *1-新增  2-删除 3-更新
     * </pre>
     *
     * <code>required int32 changeType = 1;</code>
     */
    public int getChangeType() {
      return changeType_;
    }
    /**
     * <pre>
     *1-新增  2-删除 3-更新
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
     *1-新增  2-删除 3-更新
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

    private pb4client.MassGroup massGroup_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.MassGroup, pb4client.MassGroup.Builder, pb4client.MassGroupOrBuilder> massGroupBuilder_;
    /**
     * <pre>
     *集结组
     * </pre>
     *
     * <code>required .client2server.MassGroup massGroup = 3;</code>
     */
    public boolean hasMassGroup() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *集结组
     * </pre>
     *
     * <code>required .client2server.MassGroup massGroup = 3;</code>
     */
    public pb4client.MassGroup getMassGroup() {
      if (massGroupBuilder_ == null) {
        return massGroup_ == null ? pb4client.MassGroup.getDefaultInstance() : massGroup_;
      } else {
        return massGroupBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     *集结组
     * </pre>
     *
     * <code>required .client2server.MassGroup massGroup = 3;</code>
     */
    public Builder setMassGroup(pb4client.MassGroup value) {
      if (massGroupBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        massGroup_ = value;
        onChanged();
      } else {
        massGroupBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <pre>
     *集结组
     * </pre>
     *
     * <code>required .client2server.MassGroup massGroup = 3;</code>
     */
    public Builder setMassGroup(
        pb4client.MassGroup.Builder builderForValue) {
      if (massGroupBuilder_ == null) {
        massGroup_ = builderForValue.build();
        onChanged();
      } else {
        massGroupBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <pre>
     *集结组
     * </pre>
     *
     * <code>required .client2server.MassGroup massGroup = 3;</code>
     */
    public Builder mergeMassGroup(pb4client.MassGroup value) {
      if (massGroupBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002) &&
            massGroup_ != null &&
            massGroup_ != pb4client.MassGroup.getDefaultInstance()) {
          massGroup_ =
            pb4client.MassGroup.newBuilder(massGroup_).mergeFrom(value).buildPartial();
        } else {
          massGroup_ = value;
        }
        onChanged();
      } else {
        massGroupBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <pre>
     *集结组
     * </pre>
     *
     * <code>required .client2server.MassGroup massGroup = 3;</code>
     */
    public Builder clearMassGroup() {
      if (massGroupBuilder_ == null) {
        massGroup_ = null;
        onChanged();
      } else {
        massGroupBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }
    /**
     * <pre>
     *集结组
     * </pre>
     *
     * <code>required .client2server.MassGroup massGroup = 3;</code>
     */
    public pb4client.MassGroup.Builder getMassGroupBuilder() {
      bitField0_ |= 0x00000002;
      onChanged();
      return getMassGroupFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     *集结组
     * </pre>
     *
     * <code>required .client2server.MassGroup massGroup = 3;</code>
     */
    public pb4client.MassGroupOrBuilder getMassGroupOrBuilder() {
      if (massGroupBuilder_ != null) {
        return massGroupBuilder_.getMessageOrBuilder();
      } else {
        return massGroup_ == null ?
            pb4client.MassGroup.getDefaultInstance() : massGroup_;
      }
    }
    /**
     * <pre>
     *集结组
     * </pre>
     *
     * <code>required .client2server.MassGroup massGroup = 3;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.MassGroup, pb4client.MassGroup.Builder, pb4client.MassGroupOrBuilder> 
        getMassGroupFieldBuilder() {
      if (massGroupBuilder_ == null) {
        massGroupBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4client.MassGroup, pb4client.MassGroup.Builder, pb4client.MassGroupOrBuilder>(
                getMassGroup(),
                getParentForChildren(),
                isClean());
        massGroup_ = null;
      }
      return massGroupBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.NoticeMassGroupChange)
  }

  // @@protoc_insertion_point(class_scope:client2server.NoticeMassGroupChange)
  private static final pb4client.NoticeMassGroupChange DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.NoticeMassGroupChange();
  }

  public static pb4client.NoticeMassGroupChange getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<NoticeMassGroupChange>
      PARSER = new com.google.protobuf.AbstractParser<NoticeMassGroupChange>() {
    public NoticeMassGroupChange parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new NoticeMassGroupChange(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<NoticeMassGroupChange> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<NoticeMassGroupChange> getParserForType() {
    return PARSER;
  }

  public pb4client.NoticeMassGroupChange getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

