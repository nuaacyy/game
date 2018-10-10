// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3139
 * 服务器 -&gt; 客户端
 * 行军小人的位置更新
 * </pre>
 *
 * Protobuf type {@code client2server.WalkRobotUpdate}
 */
public  final class WalkRobotUpdate extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.WalkRobotUpdate)
    WalkRobotUpdateOrBuilder {
  // Use WalkRobotUpdate.newBuilder() to construct.
  private WalkRobotUpdate(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private WalkRobotUpdate() {
    walkRobots_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private WalkRobotUpdate(
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
              walkRobots_ = new java.util.ArrayList<pb4client.WalkRobot>();
              mutable_bitField0_ |= 0x00000001;
            }
            walkRobots_.add(
                input.readMessage(pb4client.WalkRobot.PARSER, extensionRegistry));
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
        walkRobots_ = java.util.Collections.unmodifiableList(walkRobots_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_WalkRobotUpdate_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_WalkRobotUpdate_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.WalkRobotUpdate.class, pb4client.WalkRobotUpdate.Builder.class);
  }

  public static final int WALKROBOTS_FIELD_NUMBER = 1;
  private java.util.List<pb4client.WalkRobot> walkRobots_;
  /**
   * <code>repeated .client2server.WalkRobot walkRobots = 1;</code>
   */
  public java.util.List<pb4client.WalkRobot> getWalkRobotsList() {
    return walkRobots_;
  }
  /**
   * <code>repeated .client2server.WalkRobot walkRobots = 1;</code>
   */
  public java.util.List<? extends pb4client.WalkRobotOrBuilder> 
      getWalkRobotsOrBuilderList() {
    return walkRobots_;
  }
  /**
   * <code>repeated .client2server.WalkRobot walkRobots = 1;</code>
   */
  public int getWalkRobotsCount() {
    return walkRobots_.size();
  }
  /**
   * <code>repeated .client2server.WalkRobot walkRobots = 1;</code>
   */
  public pb4client.WalkRobot getWalkRobots(int index) {
    return walkRobots_.get(index);
  }
  /**
   * <code>repeated .client2server.WalkRobot walkRobots = 1;</code>
   */
  public pb4client.WalkRobotOrBuilder getWalkRobotsOrBuilder(
      int index) {
    return walkRobots_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    for (int i = 0; i < getWalkRobotsCount(); i++) {
      if (!getWalkRobots(i).isInitialized()) {
        memoizedIsInitialized = 0;
        return false;
      }
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < walkRobots_.size(); i++) {
      output.writeMessage(1, walkRobots_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < walkRobots_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, walkRobots_.get(i));
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
    if (!(obj instanceof pb4client.WalkRobotUpdate)) {
      return super.equals(obj);
    }
    pb4client.WalkRobotUpdate other = (pb4client.WalkRobotUpdate) obj;

    boolean result = true;
    result = result && getWalkRobotsList()
        .equals(other.getWalkRobotsList());
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
    if (getWalkRobotsCount() > 0) {
      hash = (37 * hash) + WALKROBOTS_FIELD_NUMBER;
      hash = (53 * hash) + getWalkRobotsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.WalkRobotUpdate parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.WalkRobotUpdate parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.WalkRobotUpdate parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.WalkRobotUpdate parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.WalkRobotUpdate parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.WalkRobotUpdate parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.WalkRobotUpdate parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.WalkRobotUpdate parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.WalkRobotUpdate parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.WalkRobotUpdate parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.WalkRobotUpdate parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.WalkRobotUpdate parseFrom(
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
  public static Builder newBuilder(pb4client.WalkRobotUpdate prototype) {
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
   * msgType = 3139
   * 服务器 -&gt; 客户端
   * 行军小人的位置更新
   * </pre>
   *
   * Protobuf type {@code client2server.WalkRobotUpdate}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.WalkRobotUpdate)
      pb4client.WalkRobotUpdateOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_WalkRobotUpdate_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_WalkRobotUpdate_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.WalkRobotUpdate.class, pb4client.WalkRobotUpdate.Builder.class);
    }

    // Construct using pb4client.WalkRobotUpdate.newBuilder()
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
        getWalkRobotsFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (walkRobotsBuilder_ == null) {
        walkRobots_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        walkRobotsBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_WalkRobotUpdate_descriptor;
    }

    public pb4client.WalkRobotUpdate getDefaultInstanceForType() {
      return pb4client.WalkRobotUpdate.getDefaultInstance();
    }

    public pb4client.WalkRobotUpdate build() {
      pb4client.WalkRobotUpdate result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.WalkRobotUpdate buildPartial() {
      pb4client.WalkRobotUpdate result = new pb4client.WalkRobotUpdate(this);
      int from_bitField0_ = bitField0_;
      if (walkRobotsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          walkRobots_ = java.util.Collections.unmodifiableList(walkRobots_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.walkRobots_ = walkRobots_;
      } else {
        result.walkRobots_ = walkRobotsBuilder_.build();
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
      if (other instanceof pb4client.WalkRobotUpdate) {
        return mergeFrom((pb4client.WalkRobotUpdate)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.WalkRobotUpdate other) {
      if (other == pb4client.WalkRobotUpdate.getDefaultInstance()) return this;
      if (walkRobotsBuilder_ == null) {
        if (!other.walkRobots_.isEmpty()) {
          if (walkRobots_.isEmpty()) {
            walkRobots_ = other.walkRobots_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureWalkRobotsIsMutable();
            walkRobots_.addAll(other.walkRobots_);
          }
          onChanged();
        }
      } else {
        if (!other.walkRobots_.isEmpty()) {
          if (walkRobotsBuilder_.isEmpty()) {
            walkRobotsBuilder_.dispose();
            walkRobotsBuilder_ = null;
            walkRobots_ = other.walkRobots_;
            bitField0_ = (bitField0_ & ~0x00000001);
            walkRobotsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getWalkRobotsFieldBuilder() : null;
          } else {
            walkRobotsBuilder_.addAllMessages(other.walkRobots_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      for (int i = 0; i < getWalkRobotsCount(); i++) {
        if (!getWalkRobots(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.WalkRobotUpdate parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.WalkRobotUpdate) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<pb4client.WalkRobot> walkRobots_ =
      java.util.Collections.emptyList();
    private void ensureWalkRobotsIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        walkRobots_ = new java.util.ArrayList<pb4client.WalkRobot>(walkRobots_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.WalkRobot, pb4client.WalkRobot.Builder, pb4client.WalkRobotOrBuilder> walkRobotsBuilder_;

    /**
     * <code>repeated .client2server.WalkRobot walkRobots = 1;</code>
     */
    public java.util.List<pb4client.WalkRobot> getWalkRobotsList() {
      if (walkRobotsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(walkRobots_);
      } else {
        return walkRobotsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .client2server.WalkRobot walkRobots = 1;</code>
     */
    public int getWalkRobotsCount() {
      if (walkRobotsBuilder_ == null) {
        return walkRobots_.size();
      } else {
        return walkRobotsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .client2server.WalkRobot walkRobots = 1;</code>
     */
    public pb4client.WalkRobot getWalkRobots(int index) {
      if (walkRobotsBuilder_ == null) {
        return walkRobots_.get(index);
      } else {
        return walkRobotsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .client2server.WalkRobot walkRobots = 1;</code>
     */
    public Builder setWalkRobots(
        int index, pb4client.WalkRobot value) {
      if (walkRobotsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureWalkRobotsIsMutable();
        walkRobots_.set(index, value);
        onChanged();
      } else {
        walkRobotsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WalkRobot walkRobots = 1;</code>
     */
    public Builder setWalkRobots(
        int index, pb4client.WalkRobot.Builder builderForValue) {
      if (walkRobotsBuilder_ == null) {
        ensureWalkRobotsIsMutable();
        walkRobots_.set(index, builderForValue.build());
        onChanged();
      } else {
        walkRobotsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WalkRobot walkRobots = 1;</code>
     */
    public Builder addWalkRobots(pb4client.WalkRobot value) {
      if (walkRobotsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureWalkRobotsIsMutable();
        walkRobots_.add(value);
        onChanged();
      } else {
        walkRobotsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WalkRobot walkRobots = 1;</code>
     */
    public Builder addWalkRobots(
        int index, pb4client.WalkRobot value) {
      if (walkRobotsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureWalkRobotsIsMutable();
        walkRobots_.add(index, value);
        onChanged();
      } else {
        walkRobotsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WalkRobot walkRobots = 1;</code>
     */
    public Builder addWalkRobots(
        pb4client.WalkRobot.Builder builderForValue) {
      if (walkRobotsBuilder_ == null) {
        ensureWalkRobotsIsMutable();
        walkRobots_.add(builderForValue.build());
        onChanged();
      } else {
        walkRobotsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WalkRobot walkRobots = 1;</code>
     */
    public Builder addWalkRobots(
        int index, pb4client.WalkRobot.Builder builderForValue) {
      if (walkRobotsBuilder_ == null) {
        ensureWalkRobotsIsMutable();
        walkRobots_.add(index, builderForValue.build());
        onChanged();
      } else {
        walkRobotsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WalkRobot walkRobots = 1;</code>
     */
    public Builder addAllWalkRobots(
        java.lang.Iterable<? extends pb4client.WalkRobot> values) {
      if (walkRobotsBuilder_ == null) {
        ensureWalkRobotsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, walkRobots_);
        onChanged();
      } else {
        walkRobotsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WalkRobot walkRobots = 1;</code>
     */
    public Builder clearWalkRobots() {
      if (walkRobotsBuilder_ == null) {
        walkRobots_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        walkRobotsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WalkRobot walkRobots = 1;</code>
     */
    public Builder removeWalkRobots(int index) {
      if (walkRobotsBuilder_ == null) {
        ensureWalkRobotsIsMutable();
        walkRobots_.remove(index);
        onChanged();
      } else {
        walkRobotsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WalkRobot walkRobots = 1;</code>
     */
    public pb4client.WalkRobot.Builder getWalkRobotsBuilder(
        int index) {
      return getWalkRobotsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .client2server.WalkRobot walkRobots = 1;</code>
     */
    public pb4client.WalkRobotOrBuilder getWalkRobotsOrBuilder(
        int index) {
      if (walkRobotsBuilder_ == null) {
        return walkRobots_.get(index);  } else {
        return walkRobotsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .client2server.WalkRobot walkRobots = 1;</code>
     */
    public java.util.List<? extends pb4client.WalkRobotOrBuilder> 
         getWalkRobotsOrBuilderList() {
      if (walkRobotsBuilder_ != null) {
        return walkRobotsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(walkRobots_);
      }
    }
    /**
     * <code>repeated .client2server.WalkRobot walkRobots = 1;</code>
     */
    public pb4client.WalkRobot.Builder addWalkRobotsBuilder() {
      return getWalkRobotsFieldBuilder().addBuilder(
          pb4client.WalkRobot.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.WalkRobot walkRobots = 1;</code>
     */
    public pb4client.WalkRobot.Builder addWalkRobotsBuilder(
        int index) {
      return getWalkRobotsFieldBuilder().addBuilder(
          index, pb4client.WalkRobot.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.WalkRobot walkRobots = 1;</code>
     */
    public java.util.List<pb4client.WalkRobot.Builder> 
         getWalkRobotsBuilderList() {
      return getWalkRobotsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.WalkRobot, pb4client.WalkRobot.Builder, pb4client.WalkRobotOrBuilder> 
        getWalkRobotsFieldBuilder() {
      if (walkRobotsBuilder_ == null) {
        walkRobotsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.WalkRobot, pb4client.WalkRobot.Builder, pb4client.WalkRobotOrBuilder>(
                walkRobots_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        walkRobots_ = null;
      }
      return walkRobotsBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.WalkRobotUpdate)
  }

  // @@protoc_insertion_point(class_scope:client2server.WalkRobotUpdate)
  private static final pb4client.WalkRobotUpdate DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.WalkRobotUpdate();
  }

  public static pb4client.WalkRobotUpdate getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<WalkRobotUpdate>
      PARSER = new com.google.protobuf.AbstractParser<WalkRobotUpdate>() {
    public WalkRobotUpdate parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new WalkRobotUpdate(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<WalkRobotUpdate> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<WalkRobotUpdate> getParserForType() {
    return PARSER;
  }

  public pb4client.WalkRobotUpdate getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
