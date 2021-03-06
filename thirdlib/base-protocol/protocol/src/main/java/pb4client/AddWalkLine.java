// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3031
 * 服务器 -&gt; 客户端
 * 新增行军线主推
 * </pre>
 *
 * Protobuf type {@code client2server.AddWalkLine}
 */
public  final class AddWalkLine extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.AddWalkLine)
    AddWalkLineOrBuilder {
  // Use AddWalkLine.newBuilder() to construct.
  private AddWalkLine(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AddWalkLine() {
    walkInfo_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AddWalkLine(
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
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              walkInfo_ = new java.util.ArrayList<pb4client.WalkInfo>();
              mutable_bitField0_ |= 0x00000001;
            }
            walkInfo_.add(
                input.readMessage(pb4client.WalkInfo.PARSER, extensionRegistry));
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
        walkInfo_ = java.util.Collections.unmodifiableList(walkInfo_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_AddWalkLine_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_AddWalkLine_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.AddWalkLine.class, pb4client.AddWalkLine.Builder.class);
  }

  public static final int WALKINFO_FIELD_NUMBER = 1;
  private java.util.List<pb4client.WalkInfo> walkInfo_;
  /**
   * <code>repeated .client2server.WalkInfo walkInfo = 1;</code>
   */
  public java.util.List<pb4client.WalkInfo> getWalkInfoList() {
    return walkInfo_;
  }
  /**
   * <code>repeated .client2server.WalkInfo walkInfo = 1;</code>
   */
  public java.util.List<? extends pb4client.WalkInfoOrBuilder> 
      getWalkInfoOrBuilderList() {
    return walkInfo_;
  }
  /**
   * <code>repeated .client2server.WalkInfo walkInfo = 1;</code>
   */
  public int getWalkInfoCount() {
    return walkInfo_.size();
  }
  /**
   * <code>repeated .client2server.WalkInfo walkInfo = 1;</code>
   */
  public pb4client.WalkInfo getWalkInfo(int index) {
    return walkInfo_.get(index);
  }
  /**
   * <code>repeated .client2server.WalkInfo walkInfo = 1;</code>
   */
  public pb4client.WalkInfoOrBuilder getWalkInfoOrBuilder(
      int index) {
    return walkInfo_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    for (int i = 0; i < getWalkInfoCount(); i++) {
      if (!getWalkInfo(i).isInitialized()) {
        memoizedIsInitialized = 0;
        return false;
      }
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < walkInfo_.size(); i++) {
      output.writeMessage(1, walkInfo_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < walkInfo_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, walkInfo_.get(i));
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
    if (!(obj instanceof pb4client.AddWalkLine)) {
      return super.equals(obj);
    }
    pb4client.AddWalkLine other = (pb4client.AddWalkLine) obj;

    boolean result = true;
    result = result && getWalkInfoList()
        .equals(other.getWalkInfoList());
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
    if (getWalkInfoCount() > 0) {
      hash = (37 * hash) + WALKINFO_FIELD_NUMBER;
      hash = (53 * hash) + getWalkInfoList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.AddWalkLine parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AddWalkLine parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AddWalkLine parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AddWalkLine parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AddWalkLine parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AddWalkLine parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AddWalkLine parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AddWalkLine parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AddWalkLine parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.AddWalkLine parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AddWalkLine parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AddWalkLine parseFrom(
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
  public static Builder newBuilder(pb4client.AddWalkLine prototype) {
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
   * msgType = 3031
   * 服务器 -&gt; 客户端
   * 新增行军线主推
   * </pre>
   *
   * Protobuf type {@code client2server.AddWalkLine}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.AddWalkLine)
      pb4client.AddWalkLineOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_AddWalkLine_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_AddWalkLine_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.AddWalkLine.class, pb4client.AddWalkLine.Builder.class);
    }

    // Construct using pb4client.AddWalkLine.newBuilder()
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
        getWalkInfoFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (walkInfoBuilder_ == null) {
        walkInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        walkInfoBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_AddWalkLine_descriptor;
    }

    public pb4client.AddWalkLine getDefaultInstanceForType() {
      return pb4client.AddWalkLine.getDefaultInstance();
    }

    public pb4client.AddWalkLine build() {
      pb4client.AddWalkLine result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.AddWalkLine buildPartial() {
      pb4client.AddWalkLine result = new pb4client.AddWalkLine(this);
      int from_bitField0_ = bitField0_;
      if (walkInfoBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          walkInfo_ = java.util.Collections.unmodifiableList(walkInfo_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.walkInfo_ = walkInfo_;
      } else {
        result.walkInfo_ = walkInfoBuilder_.build();
      }
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
      if (other instanceof pb4client.AddWalkLine) {
        return mergeFrom((pb4client.AddWalkLine)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.AddWalkLine other) {
      if (other == pb4client.AddWalkLine.getDefaultInstance()) return this;
      if (walkInfoBuilder_ == null) {
        if (!other.walkInfo_.isEmpty()) {
          if (walkInfo_.isEmpty()) {
            walkInfo_ = other.walkInfo_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureWalkInfoIsMutable();
            walkInfo_.addAll(other.walkInfo_);
          }
          onChanged();
        }
      } else {
        if (!other.walkInfo_.isEmpty()) {
          if (walkInfoBuilder_.isEmpty()) {
            walkInfoBuilder_.dispose();
            walkInfoBuilder_ = null;
            walkInfo_ = other.walkInfo_;
            bitField0_ = (bitField0_ & ~0x00000001);
            walkInfoBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getWalkInfoFieldBuilder() : null;
          } else {
            walkInfoBuilder_.addAllMessages(other.walkInfo_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      for (int i = 0; i < getWalkInfoCount(); i++) {
        if (!getWalkInfo(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.AddWalkLine parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.AddWalkLine) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<pb4client.WalkInfo> walkInfo_ =
      java.util.Collections.emptyList();
    private void ensureWalkInfoIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        walkInfo_ = new java.util.ArrayList<pb4client.WalkInfo>(walkInfo_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.WalkInfo, pb4client.WalkInfo.Builder, pb4client.WalkInfoOrBuilder> walkInfoBuilder_;

    /**
     * <code>repeated .client2server.WalkInfo walkInfo = 1;</code>
     */
    public java.util.List<pb4client.WalkInfo> getWalkInfoList() {
      if (walkInfoBuilder_ == null) {
        return java.util.Collections.unmodifiableList(walkInfo_);
      } else {
        return walkInfoBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .client2server.WalkInfo walkInfo = 1;</code>
     */
    public int getWalkInfoCount() {
      if (walkInfoBuilder_ == null) {
        return walkInfo_.size();
      } else {
        return walkInfoBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .client2server.WalkInfo walkInfo = 1;</code>
     */
    public pb4client.WalkInfo getWalkInfo(int index) {
      if (walkInfoBuilder_ == null) {
        return walkInfo_.get(index);
      } else {
        return walkInfoBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .client2server.WalkInfo walkInfo = 1;</code>
     */
    public Builder setWalkInfo(
        int index, pb4client.WalkInfo value) {
      if (walkInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureWalkInfoIsMutable();
        walkInfo_.set(index, value);
        onChanged();
      } else {
        walkInfoBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WalkInfo walkInfo = 1;</code>
     */
    public Builder setWalkInfo(
        int index, pb4client.WalkInfo.Builder builderForValue) {
      if (walkInfoBuilder_ == null) {
        ensureWalkInfoIsMutable();
        walkInfo_.set(index, builderForValue.build());
        onChanged();
      } else {
        walkInfoBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WalkInfo walkInfo = 1;</code>
     */
    public Builder addWalkInfo(pb4client.WalkInfo value) {
      if (walkInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureWalkInfoIsMutable();
        walkInfo_.add(value);
        onChanged();
      } else {
        walkInfoBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WalkInfo walkInfo = 1;</code>
     */
    public Builder addWalkInfo(
        int index, pb4client.WalkInfo value) {
      if (walkInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureWalkInfoIsMutable();
        walkInfo_.add(index, value);
        onChanged();
      } else {
        walkInfoBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WalkInfo walkInfo = 1;</code>
     */
    public Builder addWalkInfo(
        pb4client.WalkInfo.Builder builderForValue) {
      if (walkInfoBuilder_ == null) {
        ensureWalkInfoIsMutable();
        walkInfo_.add(builderForValue.build());
        onChanged();
      } else {
        walkInfoBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WalkInfo walkInfo = 1;</code>
     */
    public Builder addWalkInfo(
        int index, pb4client.WalkInfo.Builder builderForValue) {
      if (walkInfoBuilder_ == null) {
        ensureWalkInfoIsMutable();
        walkInfo_.add(index, builderForValue.build());
        onChanged();
      } else {
        walkInfoBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WalkInfo walkInfo = 1;</code>
     */
    public Builder addAllWalkInfo(
        java.lang.Iterable<? extends pb4client.WalkInfo> values) {
      if (walkInfoBuilder_ == null) {
        ensureWalkInfoIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, walkInfo_);
        onChanged();
      } else {
        walkInfoBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WalkInfo walkInfo = 1;</code>
     */
    public Builder clearWalkInfo() {
      if (walkInfoBuilder_ == null) {
        walkInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        walkInfoBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WalkInfo walkInfo = 1;</code>
     */
    public Builder removeWalkInfo(int index) {
      if (walkInfoBuilder_ == null) {
        ensureWalkInfoIsMutable();
        walkInfo_.remove(index);
        onChanged();
      } else {
        walkInfoBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WalkInfo walkInfo = 1;</code>
     */
    public pb4client.WalkInfo.Builder getWalkInfoBuilder(
        int index) {
      return getWalkInfoFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .client2server.WalkInfo walkInfo = 1;</code>
     */
    public pb4client.WalkInfoOrBuilder getWalkInfoOrBuilder(
        int index) {
      if (walkInfoBuilder_ == null) {
        return walkInfo_.get(index);  } else {
        return walkInfoBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .client2server.WalkInfo walkInfo = 1;</code>
     */
    public java.util.List<? extends pb4client.WalkInfoOrBuilder> 
         getWalkInfoOrBuilderList() {
      if (walkInfoBuilder_ != null) {
        return walkInfoBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(walkInfo_);
      }
    }
    /**
     * <code>repeated .client2server.WalkInfo walkInfo = 1;</code>
     */
    public pb4client.WalkInfo.Builder addWalkInfoBuilder() {
      return getWalkInfoFieldBuilder().addBuilder(
          pb4client.WalkInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.WalkInfo walkInfo = 1;</code>
     */
    public pb4client.WalkInfo.Builder addWalkInfoBuilder(
        int index) {
      return getWalkInfoFieldBuilder().addBuilder(
          index, pb4client.WalkInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.WalkInfo walkInfo = 1;</code>
     */
    public java.util.List<pb4client.WalkInfo.Builder> 
         getWalkInfoBuilderList() {
      return getWalkInfoFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.WalkInfo, pb4client.WalkInfo.Builder, pb4client.WalkInfoOrBuilder> 
        getWalkInfoFieldBuilder() {
      if (walkInfoBuilder_ == null) {
        walkInfoBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.WalkInfo, pb4client.WalkInfo.Builder, pb4client.WalkInfoOrBuilder>(
                walkInfo_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        walkInfo_ = null;
      }
      return walkInfoBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.AddWalkLine)
  }

  // @@protoc_insertion_point(class_scope:client2server.AddWalkLine)
  private static final pb4client.AddWalkLine DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.AddWalkLine();
  }

  public static pb4client.AddWalkLine getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<AddWalkLine>
      PARSER = new com.google.protobuf.AbstractParser<AddWalkLine>() {
    public AddWalkLine parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AddWalkLine(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AddWalkLine> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AddWalkLine> getParserForType() {
    return PARSER;
  }

  public pb4client.AddWalkLine getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

