// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3169
 * 服务器 -&gt; 客户端
 * 奇观部队变化
 * </pre>
 *
 * Protobuf type {@code client2server.WonderForceChange}
 */
public  final class WonderForceChange extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.WonderForceChange)
    WonderForceChangeOrBuilder {
  // Use WonderForceChange.newBuilder() to construct.
  private WonderForceChange(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private WonderForceChange() {
    changeType_ = 0;
    forces_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private WonderForceChange(
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
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              forces_ = new java.util.ArrayList<pb4client.MassForce>();
              mutable_bitField0_ |= 0x00000002;
            }
            forces_.add(
                input.readMessage(pb4client.MassForce.PARSER, extensionRegistry));
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
        forces_ = java.util.Collections.unmodifiableList(forces_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_WonderForceChange_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_WonderForceChange_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.WonderForceChange.class, pb4client.WonderForceChange.Builder.class);
  }

  private int bitField0_;
  public static final int CHANGETYPE_FIELD_NUMBER = 1;
  private int changeType_;
  /**
   * <pre>
   * 1-新增 2-删除 3-更新
   * </pre>
   *
   * <code>required int32 changeType = 1;</code>
   */
  public boolean hasChangeType() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 1-新增 2-删除 3-更新
   * </pre>
   *
   * <code>required int32 changeType = 1;</code>
   */
  public int getChangeType() {
    return changeType_;
  }

  public static final int FORCES_FIELD_NUMBER = 2;
  private java.util.List<pb4client.MassForce> forces_;
  /**
   * <code>repeated .client2server.MassForce forces = 2;</code>
   */
  public java.util.List<pb4client.MassForce> getForcesList() {
    return forces_;
  }
  /**
   * <code>repeated .client2server.MassForce forces = 2;</code>
   */
  public java.util.List<? extends pb4client.MassForceOrBuilder> 
      getForcesOrBuilderList() {
    return forces_;
  }
  /**
   * <code>repeated .client2server.MassForce forces = 2;</code>
   */
  public int getForcesCount() {
    return forces_.size();
  }
  /**
   * <code>repeated .client2server.MassForce forces = 2;</code>
   */
  public pb4client.MassForce getForces(int index) {
    return forces_.get(index);
  }
  /**
   * <code>repeated .client2server.MassForce forces = 2;</code>
   */
  public pb4client.MassForceOrBuilder getForcesOrBuilder(
      int index) {
    return forces_.get(index);
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
    for (int i = 0; i < getForcesCount(); i++) {
      if (!getForces(i).isInitialized()) {
        memoizedIsInitialized = 0;
        return false;
      }
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, changeType_);
    }
    for (int i = 0; i < forces_.size(); i++) {
      output.writeMessage(2, forces_.get(i));
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
    for (int i = 0; i < forces_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, forces_.get(i));
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
    if (!(obj instanceof pb4client.WonderForceChange)) {
      return super.equals(obj);
    }
    pb4client.WonderForceChange other = (pb4client.WonderForceChange) obj;

    boolean result = true;
    result = result && (hasChangeType() == other.hasChangeType());
    if (hasChangeType()) {
      result = result && (getChangeType()
          == other.getChangeType());
    }
    result = result && getForcesList()
        .equals(other.getForcesList());
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
    if (getForcesCount() > 0) {
      hash = (37 * hash) + FORCES_FIELD_NUMBER;
      hash = (53 * hash) + getForcesList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.WonderForceChange parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.WonderForceChange parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.WonderForceChange parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.WonderForceChange parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.WonderForceChange parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.WonderForceChange parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.WonderForceChange parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.WonderForceChange parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.WonderForceChange parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.WonderForceChange parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.WonderForceChange parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.WonderForceChange parseFrom(
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
  public static Builder newBuilder(pb4client.WonderForceChange prototype) {
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
   * msgType = 3169
   * 服务器 -&gt; 客户端
   * 奇观部队变化
   * </pre>
   *
   * Protobuf type {@code client2server.WonderForceChange}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.WonderForceChange)
      pb4client.WonderForceChangeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_WonderForceChange_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_WonderForceChange_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.WonderForceChange.class, pb4client.WonderForceChange.Builder.class);
    }

    // Construct using pb4client.WonderForceChange.newBuilder()
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
        getForcesFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      changeType_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (forcesBuilder_ == null) {
        forces_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        forcesBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_WonderForceChange_descriptor;
    }

    public pb4client.WonderForceChange getDefaultInstanceForType() {
      return pb4client.WonderForceChange.getDefaultInstance();
    }

    public pb4client.WonderForceChange build() {
      pb4client.WonderForceChange result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.WonderForceChange buildPartial() {
      pb4client.WonderForceChange result = new pb4client.WonderForceChange(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.changeType_ = changeType_;
      if (forcesBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          forces_ = java.util.Collections.unmodifiableList(forces_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.forces_ = forces_;
      } else {
        result.forces_ = forcesBuilder_.build();
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
      if (other instanceof pb4client.WonderForceChange) {
        return mergeFrom((pb4client.WonderForceChange)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.WonderForceChange other) {
      if (other == pb4client.WonderForceChange.getDefaultInstance()) return this;
      if (other.hasChangeType()) {
        setChangeType(other.getChangeType());
      }
      if (forcesBuilder_ == null) {
        if (!other.forces_.isEmpty()) {
          if (forces_.isEmpty()) {
            forces_ = other.forces_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureForcesIsMutable();
            forces_.addAll(other.forces_);
          }
          onChanged();
        }
      } else {
        if (!other.forces_.isEmpty()) {
          if (forcesBuilder_.isEmpty()) {
            forcesBuilder_.dispose();
            forcesBuilder_ = null;
            forces_ = other.forces_;
            bitField0_ = (bitField0_ & ~0x00000002);
            forcesBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getForcesFieldBuilder() : null;
          } else {
            forcesBuilder_.addAllMessages(other.forces_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasChangeType()) {
        return false;
      }
      for (int i = 0; i < getForcesCount(); i++) {
        if (!getForces(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.WonderForceChange parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.WonderForceChange) e.getUnfinishedMessage();
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
     * 1-新增 2-删除 3-更新
     * </pre>
     *
     * <code>required int32 changeType = 1;</code>
     */
    public boolean hasChangeType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 1-新增 2-删除 3-更新
     * </pre>
     *
     * <code>required int32 changeType = 1;</code>
     */
    public int getChangeType() {
      return changeType_;
    }
    /**
     * <pre>
     * 1-新增 2-删除 3-更新
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
     * 1-新增 2-删除 3-更新
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

    private java.util.List<pb4client.MassForce> forces_ =
      java.util.Collections.emptyList();
    private void ensureForcesIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        forces_ = new java.util.ArrayList<pb4client.MassForce>(forces_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.MassForce, pb4client.MassForce.Builder, pb4client.MassForceOrBuilder> forcesBuilder_;

    /**
     * <code>repeated .client2server.MassForce forces = 2;</code>
     */
    public java.util.List<pb4client.MassForce> getForcesList() {
      if (forcesBuilder_ == null) {
        return java.util.Collections.unmodifiableList(forces_);
      } else {
        return forcesBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .client2server.MassForce forces = 2;</code>
     */
    public int getForcesCount() {
      if (forcesBuilder_ == null) {
        return forces_.size();
      } else {
        return forcesBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .client2server.MassForce forces = 2;</code>
     */
    public pb4client.MassForce getForces(int index) {
      if (forcesBuilder_ == null) {
        return forces_.get(index);
      } else {
        return forcesBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .client2server.MassForce forces = 2;</code>
     */
    public Builder setForces(
        int index, pb4client.MassForce value) {
      if (forcesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureForcesIsMutable();
        forces_.set(index, value);
        onChanged();
      } else {
        forcesBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.MassForce forces = 2;</code>
     */
    public Builder setForces(
        int index, pb4client.MassForce.Builder builderForValue) {
      if (forcesBuilder_ == null) {
        ensureForcesIsMutable();
        forces_.set(index, builderForValue.build());
        onChanged();
      } else {
        forcesBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.MassForce forces = 2;</code>
     */
    public Builder addForces(pb4client.MassForce value) {
      if (forcesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureForcesIsMutable();
        forces_.add(value);
        onChanged();
      } else {
        forcesBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.MassForce forces = 2;</code>
     */
    public Builder addForces(
        int index, pb4client.MassForce value) {
      if (forcesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureForcesIsMutable();
        forces_.add(index, value);
        onChanged();
      } else {
        forcesBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.MassForce forces = 2;</code>
     */
    public Builder addForces(
        pb4client.MassForce.Builder builderForValue) {
      if (forcesBuilder_ == null) {
        ensureForcesIsMutable();
        forces_.add(builderForValue.build());
        onChanged();
      } else {
        forcesBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.MassForce forces = 2;</code>
     */
    public Builder addForces(
        int index, pb4client.MassForce.Builder builderForValue) {
      if (forcesBuilder_ == null) {
        ensureForcesIsMutable();
        forces_.add(index, builderForValue.build());
        onChanged();
      } else {
        forcesBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.MassForce forces = 2;</code>
     */
    public Builder addAllForces(
        java.lang.Iterable<? extends pb4client.MassForce> values) {
      if (forcesBuilder_ == null) {
        ensureForcesIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, forces_);
        onChanged();
      } else {
        forcesBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.MassForce forces = 2;</code>
     */
    public Builder clearForces() {
      if (forcesBuilder_ == null) {
        forces_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        forcesBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .client2server.MassForce forces = 2;</code>
     */
    public Builder removeForces(int index) {
      if (forcesBuilder_ == null) {
        ensureForcesIsMutable();
        forces_.remove(index);
        onChanged();
      } else {
        forcesBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.MassForce forces = 2;</code>
     */
    public pb4client.MassForce.Builder getForcesBuilder(
        int index) {
      return getForcesFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .client2server.MassForce forces = 2;</code>
     */
    public pb4client.MassForceOrBuilder getForcesOrBuilder(
        int index) {
      if (forcesBuilder_ == null) {
        return forces_.get(index);  } else {
        return forcesBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .client2server.MassForce forces = 2;</code>
     */
    public java.util.List<? extends pb4client.MassForceOrBuilder> 
         getForcesOrBuilderList() {
      if (forcesBuilder_ != null) {
        return forcesBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(forces_);
      }
    }
    /**
     * <code>repeated .client2server.MassForce forces = 2;</code>
     */
    public pb4client.MassForce.Builder addForcesBuilder() {
      return getForcesFieldBuilder().addBuilder(
          pb4client.MassForce.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.MassForce forces = 2;</code>
     */
    public pb4client.MassForce.Builder addForcesBuilder(
        int index) {
      return getForcesFieldBuilder().addBuilder(
          index, pb4client.MassForce.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.MassForce forces = 2;</code>
     */
    public java.util.List<pb4client.MassForce.Builder> 
         getForcesBuilderList() {
      return getForcesFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.MassForce, pb4client.MassForce.Builder, pb4client.MassForceOrBuilder> 
        getForcesFieldBuilder() {
      if (forcesBuilder_ == null) {
        forcesBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.MassForce, pb4client.MassForce.Builder, pb4client.MassForceOrBuilder>(
                forces_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        forces_ = null;
      }
      return forcesBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.WonderForceChange)
  }

  // @@protoc_insertion_point(class_scope:client2server.WonderForceChange)
  private static final pb4client.WonderForceChange DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.WonderForceChange();
  }

  public static pb4client.WonderForceChange getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<WonderForceChange>
      PARSER = new com.google.protobuf.AbstractParser<WonderForceChange>() {
    public WonderForceChange parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new WonderForceChange(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<WonderForceChange> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<WonderForceChange> getParserForType() {
    return PARSER;
  }

  public pb4client.WonderForceChange getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

