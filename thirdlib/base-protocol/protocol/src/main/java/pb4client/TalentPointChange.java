// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3132
 * 服务器 -&gt; 客户端
 * 天赋点变化
 * </pre>
 *
 * Protobuf type {@code client2server.TalentPointChange}
 */
public  final class TalentPointChange extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.TalentPointChange)
    TalentPointChangeOrBuilder {
  // Use TalentPointChange.newBuilder() to construct.
  private TalentPointChange(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TalentPointChange() {
    leftTalentPoint_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TalentPointChange(
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
              leftTalentPoint_ = new java.util.ArrayList<pb4client.TalentPoint>();
              mutable_bitField0_ |= 0x00000001;
            }
            leftTalentPoint_.add(
                input.readMessage(pb4client.TalentPoint.PARSER, extensionRegistry));
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
        leftTalentPoint_ = java.util.Collections.unmodifiableList(leftTalentPoint_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_TalentPointChange_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_TalentPointChange_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.TalentPointChange.class, pb4client.TalentPointChange.Builder.class);
  }

  public static final int LEFTTALENTPOINT_FIELD_NUMBER = 1;
  private java.util.List<pb4client.TalentPoint> leftTalentPoint_;
  /**
   * <pre>
   *剩余天赋点数
   * </pre>
   *
   * <code>repeated .client2server.TalentPoint leftTalentPoint = 1;</code>
   */
  public java.util.List<pb4client.TalentPoint> getLeftTalentPointList() {
    return leftTalentPoint_;
  }
  /**
   * <pre>
   *剩余天赋点数
   * </pre>
   *
   * <code>repeated .client2server.TalentPoint leftTalentPoint = 1;</code>
   */
  public java.util.List<? extends pb4client.TalentPointOrBuilder> 
      getLeftTalentPointOrBuilderList() {
    return leftTalentPoint_;
  }
  /**
   * <pre>
   *剩余天赋点数
   * </pre>
   *
   * <code>repeated .client2server.TalentPoint leftTalentPoint = 1;</code>
   */
  public int getLeftTalentPointCount() {
    return leftTalentPoint_.size();
  }
  /**
   * <pre>
   *剩余天赋点数
   * </pre>
   *
   * <code>repeated .client2server.TalentPoint leftTalentPoint = 1;</code>
   */
  public pb4client.TalentPoint getLeftTalentPoint(int index) {
    return leftTalentPoint_.get(index);
  }
  /**
   * <pre>
   *剩余天赋点数
   * </pre>
   *
   * <code>repeated .client2server.TalentPoint leftTalentPoint = 1;</code>
   */
  public pb4client.TalentPointOrBuilder getLeftTalentPointOrBuilder(
      int index) {
    return leftTalentPoint_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    for (int i = 0; i < getLeftTalentPointCount(); i++) {
      if (!getLeftTalentPoint(i).isInitialized()) {
        memoizedIsInitialized = 0;
        return false;
      }
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < leftTalentPoint_.size(); i++) {
      output.writeMessage(1, leftTalentPoint_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < leftTalentPoint_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, leftTalentPoint_.get(i));
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
    if (!(obj instanceof pb4client.TalentPointChange)) {
      return super.equals(obj);
    }
    pb4client.TalentPointChange other = (pb4client.TalentPointChange) obj;

    boolean result = true;
    result = result && getLeftTalentPointList()
        .equals(other.getLeftTalentPointList());
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
    if (getLeftTalentPointCount() > 0) {
      hash = (37 * hash) + LEFTTALENTPOINT_FIELD_NUMBER;
      hash = (53 * hash) + getLeftTalentPointList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.TalentPointChange parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.TalentPointChange parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.TalentPointChange parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.TalentPointChange parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.TalentPointChange parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.TalentPointChange parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.TalentPointChange parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.TalentPointChange parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.TalentPointChange parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.TalentPointChange parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.TalentPointChange parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.TalentPointChange parseFrom(
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
  public static Builder newBuilder(pb4client.TalentPointChange prototype) {
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
   * msgType = 3132
   * 服务器 -&gt; 客户端
   * 天赋点变化
   * </pre>
   *
   * Protobuf type {@code client2server.TalentPointChange}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.TalentPointChange)
      pb4client.TalentPointChangeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_TalentPointChange_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_TalentPointChange_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.TalentPointChange.class, pb4client.TalentPointChange.Builder.class);
    }

    // Construct using pb4client.TalentPointChange.newBuilder()
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
        getLeftTalentPointFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (leftTalentPointBuilder_ == null) {
        leftTalentPoint_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        leftTalentPointBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_TalentPointChange_descriptor;
    }

    public pb4client.TalentPointChange getDefaultInstanceForType() {
      return pb4client.TalentPointChange.getDefaultInstance();
    }

    public pb4client.TalentPointChange build() {
      pb4client.TalentPointChange result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.TalentPointChange buildPartial() {
      pb4client.TalentPointChange result = new pb4client.TalentPointChange(this);
      int from_bitField0_ = bitField0_;
      if (leftTalentPointBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          leftTalentPoint_ = java.util.Collections.unmodifiableList(leftTalentPoint_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.leftTalentPoint_ = leftTalentPoint_;
      } else {
        result.leftTalentPoint_ = leftTalentPointBuilder_.build();
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
      if (other instanceof pb4client.TalentPointChange) {
        return mergeFrom((pb4client.TalentPointChange)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.TalentPointChange other) {
      if (other == pb4client.TalentPointChange.getDefaultInstance()) return this;
      if (leftTalentPointBuilder_ == null) {
        if (!other.leftTalentPoint_.isEmpty()) {
          if (leftTalentPoint_.isEmpty()) {
            leftTalentPoint_ = other.leftTalentPoint_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureLeftTalentPointIsMutable();
            leftTalentPoint_.addAll(other.leftTalentPoint_);
          }
          onChanged();
        }
      } else {
        if (!other.leftTalentPoint_.isEmpty()) {
          if (leftTalentPointBuilder_.isEmpty()) {
            leftTalentPointBuilder_.dispose();
            leftTalentPointBuilder_ = null;
            leftTalentPoint_ = other.leftTalentPoint_;
            bitField0_ = (bitField0_ & ~0x00000001);
            leftTalentPointBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getLeftTalentPointFieldBuilder() : null;
          } else {
            leftTalentPointBuilder_.addAllMessages(other.leftTalentPoint_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      for (int i = 0; i < getLeftTalentPointCount(); i++) {
        if (!getLeftTalentPoint(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.TalentPointChange parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.TalentPointChange) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<pb4client.TalentPoint> leftTalentPoint_ =
      java.util.Collections.emptyList();
    private void ensureLeftTalentPointIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        leftTalentPoint_ = new java.util.ArrayList<pb4client.TalentPoint>(leftTalentPoint_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.TalentPoint, pb4client.TalentPoint.Builder, pb4client.TalentPointOrBuilder> leftTalentPointBuilder_;

    /**
     * <pre>
     *剩余天赋点数
     * </pre>
     *
     * <code>repeated .client2server.TalentPoint leftTalentPoint = 1;</code>
     */
    public java.util.List<pb4client.TalentPoint> getLeftTalentPointList() {
      if (leftTalentPointBuilder_ == null) {
        return java.util.Collections.unmodifiableList(leftTalentPoint_);
      } else {
        return leftTalentPointBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     *剩余天赋点数
     * </pre>
     *
     * <code>repeated .client2server.TalentPoint leftTalentPoint = 1;</code>
     */
    public int getLeftTalentPointCount() {
      if (leftTalentPointBuilder_ == null) {
        return leftTalentPoint_.size();
      } else {
        return leftTalentPointBuilder_.getCount();
      }
    }
    /**
     * <pre>
     *剩余天赋点数
     * </pre>
     *
     * <code>repeated .client2server.TalentPoint leftTalentPoint = 1;</code>
     */
    public pb4client.TalentPoint getLeftTalentPoint(int index) {
      if (leftTalentPointBuilder_ == null) {
        return leftTalentPoint_.get(index);
      } else {
        return leftTalentPointBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     *剩余天赋点数
     * </pre>
     *
     * <code>repeated .client2server.TalentPoint leftTalentPoint = 1;</code>
     */
    public Builder setLeftTalentPoint(
        int index, pb4client.TalentPoint value) {
      if (leftTalentPointBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureLeftTalentPointIsMutable();
        leftTalentPoint_.set(index, value);
        onChanged();
      } else {
        leftTalentPointBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *剩余天赋点数
     * </pre>
     *
     * <code>repeated .client2server.TalentPoint leftTalentPoint = 1;</code>
     */
    public Builder setLeftTalentPoint(
        int index, pb4client.TalentPoint.Builder builderForValue) {
      if (leftTalentPointBuilder_ == null) {
        ensureLeftTalentPointIsMutable();
        leftTalentPoint_.set(index, builderForValue.build());
        onChanged();
      } else {
        leftTalentPointBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *剩余天赋点数
     * </pre>
     *
     * <code>repeated .client2server.TalentPoint leftTalentPoint = 1;</code>
     */
    public Builder addLeftTalentPoint(pb4client.TalentPoint value) {
      if (leftTalentPointBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureLeftTalentPointIsMutable();
        leftTalentPoint_.add(value);
        onChanged();
      } else {
        leftTalentPointBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     *剩余天赋点数
     * </pre>
     *
     * <code>repeated .client2server.TalentPoint leftTalentPoint = 1;</code>
     */
    public Builder addLeftTalentPoint(
        int index, pb4client.TalentPoint value) {
      if (leftTalentPointBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureLeftTalentPointIsMutable();
        leftTalentPoint_.add(index, value);
        onChanged();
      } else {
        leftTalentPointBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *剩余天赋点数
     * </pre>
     *
     * <code>repeated .client2server.TalentPoint leftTalentPoint = 1;</code>
     */
    public Builder addLeftTalentPoint(
        pb4client.TalentPoint.Builder builderForValue) {
      if (leftTalentPointBuilder_ == null) {
        ensureLeftTalentPointIsMutable();
        leftTalentPoint_.add(builderForValue.build());
        onChanged();
      } else {
        leftTalentPointBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *剩余天赋点数
     * </pre>
     *
     * <code>repeated .client2server.TalentPoint leftTalentPoint = 1;</code>
     */
    public Builder addLeftTalentPoint(
        int index, pb4client.TalentPoint.Builder builderForValue) {
      if (leftTalentPointBuilder_ == null) {
        ensureLeftTalentPointIsMutable();
        leftTalentPoint_.add(index, builderForValue.build());
        onChanged();
      } else {
        leftTalentPointBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *剩余天赋点数
     * </pre>
     *
     * <code>repeated .client2server.TalentPoint leftTalentPoint = 1;</code>
     */
    public Builder addAllLeftTalentPoint(
        java.lang.Iterable<? extends pb4client.TalentPoint> values) {
      if (leftTalentPointBuilder_ == null) {
        ensureLeftTalentPointIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, leftTalentPoint_);
        onChanged();
      } else {
        leftTalentPointBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     *剩余天赋点数
     * </pre>
     *
     * <code>repeated .client2server.TalentPoint leftTalentPoint = 1;</code>
     */
    public Builder clearLeftTalentPoint() {
      if (leftTalentPointBuilder_ == null) {
        leftTalentPoint_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        leftTalentPointBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     *剩余天赋点数
     * </pre>
     *
     * <code>repeated .client2server.TalentPoint leftTalentPoint = 1;</code>
     */
    public Builder removeLeftTalentPoint(int index) {
      if (leftTalentPointBuilder_ == null) {
        ensureLeftTalentPointIsMutable();
        leftTalentPoint_.remove(index);
        onChanged();
      } else {
        leftTalentPointBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     *剩余天赋点数
     * </pre>
     *
     * <code>repeated .client2server.TalentPoint leftTalentPoint = 1;</code>
     */
    public pb4client.TalentPoint.Builder getLeftTalentPointBuilder(
        int index) {
      return getLeftTalentPointFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     *剩余天赋点数
     * </pre>
     *
     * <code>repeated .client2server.TalentPoint leftTalentPoint = 1;</code>
     */
    public pb4client.TalentPointOrBuilder getLeftTalentPointOrBuilder(
        int index) {
      if (leftTalentPointBuilder_ == null) {
        return leftTalentPoint_.get(index);  } else {
        return leftTalentPointBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     *剩余天赋点数
     * </pre>
     *
     * <code>repeated .client2server.TalentPoint leftTalentPoint = 1;</code>
     */
    public java.util.List<? extends pb4client.TalentPointOrBuilder> 
         getLeftTalentPointOrBuilderList() {
      if (leftTalentPointBuilder_ != null) {
        return leftTalentPointBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(leftTalentPoint_);
      }
    }
    /**
     * <pre>
     *剩余天赋点数
     * </pre>
     *
     * <code>repeated .client2server.TalentPoint leftTalentPoint = 1;</code>
     */
    public pb4client.TalentPoint.Builder addLeftTalentPointBuilder() {
      return getLeftTalentPointFieldBuilder().addBuilder(
          pb4client.TalentPoint.getDefaultInstance());
    }
    /**
     * <pre>
     *剩余天赋点数
     * </pre>
     *
     * <code>repeated .client2server.TalentPoint leftTalentPoint = 1;</code>
     */
    public pb4client.TalentPoint.Builder addLeftTalentPointBuilder(
        int index) {
      return getLeftTalentPointFieldBuilder().addBuilder(
          index, pb4client.TalentPoint.getDefaultInstance());
    }
    /**
     * <pre>
     *剩余天赋点数
     * </pre>
     *
     * <code>repeated .client2server.TalentPoint leftTalentPoint = 1;</code>
     */
    public java.util.List<pb4client.TalentPoint.Builder> 
         getLeftTalentPointBuilderList() {
      return getLeftTalentPointFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.TalentPoint, pb4client.TalentPoint.Builder, pb4client.TalentPointOrBuilder> 
        getLeftTalentPointFieldBuilder() {
      if (leftTalentPointBuilder_ == null) {
        leftTalentPointBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.TalentPoint, pb4client.TalentPoint.Builder, pb4client.TalentPointOrBuilder>(
                leftTalentPoint_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        leftTalentPoint_ = null;
      }
      return leftTalentPointBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.TalentPointChange)
  }

  // @@protoc_insertion_point(class_scope:client2server.TalentPointChange)
  private static final pb4client.TalentPointChange DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.TalentPointChange();
  }

  public static pb4client.TalentPointChange getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<TalentPointChange>
      PARSER = new com.google.protobuf.AbstractParser<TalentPointChange>() {
    public TalentPointChange parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new TalentPointChange(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TalentPointChange> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TalentPointChange> getParserForType() {
    return PARSER;
  }

  public pb4client.TalentPointChange getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

