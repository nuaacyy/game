// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.OpenActivityRt}
 */
public  final class OpenActivityRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.OpenActivityRt)
    OpenActivityRtOrBuilder {
  // Use OpenActivityRt.newBuilder() to construct.
  private OpenActivityRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private OpenActivityRt() {
    rt_ = 0;
    openActivityVos_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private OpenActivityRt(
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
            rt_ = input.readInt32();
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              openActivityVos_ = new java.util.ArrayList<pb4client.OpenActivityVo>();
              mutable_bitField0_ |= 0x00000002;
            }
            openActivityVos_.add(
                input.readMessage(pb4client.OpenActivityVo.PARSER, extensionRegistry));
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
        openActivityVos_ = java.util.Collections.unmodifiableList(openActivityVos_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_OpenActivityRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_OpenActivityRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.OpenActivityRt.class, pb4client.OpenActivityRt.Builder.class);
  }

  private int bitField0_;
  public static final int RT_FIELD_NUMBER = 1;
  private int rt_;
  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  public boolean hasRt() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  public int getRt() {
    return rt_;
  }

  public static final int OPENACTIVITYVOS_FIELD_NUMBER = 2;
  private java.util.List<pb4client.OpenActivityVo> openActivityVos_;
  /**
   * <code>repeated .client2server.OpenActivityVo openActivityVos = 2;</code>
   */
  public java.util.List<pb4client.OpenActivityVo> getOpenActivityVosList() {
    return openActivityVos_;
  }
  /**
   * <code>repeated .client2server.OpenActivityVo openActivityVos = 2;</code>
   */
  public java.util.List<? extends pb4client.OpenActivityVoOrBuilder> 
      getOpenActivityVosOrBuilderList() {
    return openActivityVos_;
  }
  /**
   * <code>repeated .client2server.OpenActivityVo openActivityVos = 2;</code>
   */
  public int getOpenActivityVosCount() {
    return openActivityVos_.size();
  }
  /**
   * <code>repeated .client2server.OpenActivityVo openActivityVos = 2;</code>
   */
  public pb4client.OpenActivityVo getOpenActivityVos(int index) {
    return openActivityVos_.get(index);
  }
  /**
   * <code>repeated .client2server.OpenActivityVo openActivityVos = 2;</code>
   */
  public pb4client.OpenActivityVoOrBuilder getOpenActivityVosOrBuilder(
      int index) {
    return openActivityVos_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasRt()) {
      memoizedIsInitialized = 0;
      return false;
    }
    for (int i = 0; i < getOpenActivityVosCount(); i++) {
      if (!getOpenActivityVos(i).isInitialized()) {
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
      output.writeInt32(1, rt_);
    }
    for (int i = 0; i < openActivityVos_.size(); i++) {
      output.writeMessage(2, openActivityVos_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, rt_);
    }
    for (int i = 0; i < openActivityVos_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, openActivityVos_.get(i));
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
    if (!(obj instanceof pb4client.OpenActivityRt)) {
      return super.equals(obj);
    }
    pb4client.OpenActivityRt other = (pb4client.OpenActivityRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && getOpenActivityVosList()
        .equals(other.getOpenActivityVosList());
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
    if (hasRt()) {
      hash = (37 * hash) + RT_FIELD_NUMBER;
      hash = (53 * hash) + getRt();
    }
    if (getOpenActivityVosCount() > 0) {
      hash = (37 * hash) + OPENACTIVITYVOS_FIELD_NUMBER;
      hash = (53 * hash) + getOpenActivityVosList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.OpenActivityRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.OpenActivityRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.OpenActivityRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.OpenActivityRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.OpenActivityRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.OpenActivityRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.OpenActivityRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.OpenActivityRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.OpenActivityRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.OpenActivityRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.OpenActivityRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.OpenActivityRt parseFrom(
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
  public static Builder newBuilder(pb4client.OpenActivityRt prototype) {
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
   * Protobuf type {@code client2server.OpenActivityRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.OpenActivityRt)
      pb4client.OpenActivityRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_OpenActivityRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_OpenActivityRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.OpenActivityRt.class, pb4client.OpenActivityRt.Builder.class);
    }

    // Construct using pb4client.OpenActivityRt.newBuilder()
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
        getOpenActivityVosFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (openActivityVosBuilder_ == null) {
        openActivityVos_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        openActivityVosBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_OpenActivityRt_descriptor;
    }

    public pb4client.OpenActivityRt getDefaultInstanceForType() {
      return pb4client.OpenActivityRt.getDefaultInstance();
    }

    public pb4client.OpenActivityRt build() {
      pb4client.OpenActivityRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.OpenActivityRt buildPartial() {
      pb4client.OpenActivityRt result = new pb4client.OpenActivityRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (openActivityVosBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          openActivityVos_ = java.util.Collections.unmodifiableList(openActivityVos_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.openActivityVos_ = openActivityVos_;
      } else {
        result.openActivityVos_ = openActivityVosBuilder_.build();
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
      if (other instanceof pb4client.OpenActivityRt) {
        return mergeFrom((pb4client.OpenActivityRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.OpenActivityRt other) {
      if (other == pb4client.OpenActivityRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (openActivityVosBuilder_ == null) {
        if (!other.openActivityVos_.isEmpty()) {
          if (openActivityVos_.isEmpty()) {
            openActivityVos_ = other.openActivityVos_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureOpenActivityVosIsMutable();
            openActivityVos_.addAll(other.openActivityVos_);
          }
          onChanged();
        }
      } else {
        if (!other.openActivityVos_.isEmpty()) {
          if (openActivityVosBuilder_.isEmpty()) {
            openActivityVosBuilder_.dispose();
            openActivityVosBuilder_ = null;
            openActivityVos_ = other.openActivityVos_;
            bitField0_ = (bitField0_ & ~0x00000002);
            openActivityVosBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getOpenActivityVosFieldBuilder() : null;
          } else {
            openActivityVosBuilder_.addAllMessages(other.openActivityVos_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasRt()) {
        return false;
      }
      for (int i = 0; i < getOpenActivityVosCount(); i++) {
        if (!getOpenActivityVos(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.OpenActivityRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.OpenActivityRt) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int rt_ ;
    /**
     * <pre>
     * 返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public boolean hasRt() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public int getRt() {
      return rt_;
    }
    /**
     * <pre>
     * 返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public Builder setRt(int value) {
      bitField0_ |= 0x00000001;
      rt_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public Builder clearRt() {
      bitField0_ = (bitField0_ & ~0x00000001);
      rt_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<pb4client.OpenActivityVo> openActivityVos_ =
      java.util.Collections.emptyList();
    private void ensureOpenActivityVosIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        openActivityVos_ = new java.util.ArrayList<pb4client.OpenActivityVo>(openActivityVos_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.OpenActivityVo, pb4client.OpenActivityVo.Builder, pb4client.OpenActivityVoOrBuilder> openActivityVosBuilder_;

    /**
     * <code>repeated .client2server.OpenActivityVo openActivityVos = 2;</code>
     */
    public java.util.List<pb4client.OpenActivityVo> getOpenActivityVosList() {
      if (openActivityVosBuilder_ == null) {
        return java.util.Collections.unmodifiableList(openActivityVos_);
      } else {
        return openActivityVosBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .client2server.OpenActivityVo openActivityVos = 2;</code>
     */
    public int getOpenActivityVosCount() {
      if (openActivityVosBuilder_ == null) {
        return openActivityVos_.size();
      } else {
        return openActivityVosBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .client2server.OpenActivityVo openActivityVos = 2;</code>
     */
    public pb4client.OpenActivityVo getOpenActivityVos(int index) {
      if (openActivityVosBuilder_ == null) {
        return openActivityVos_.get(index);
      } else {
        return openActivityVosBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .client2server.OpenActivityVo openActivityVos = 2;</code>
     */
    public Builder setOpenActivityVos(
        int index, pb4client.OpenActivityVo value) {
      if (openActivityVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureOpenActivityVosIsMutable();
        openActivityVos_.set(index, value);
        onChanged();
      } else {
        openActivityVosBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.OpenActivityVo openActivityVos = 2;</code>
     */
    public Builder setOpenActivityVos(
        int index, pb4client.OpenActivityVo.Builder builderForValue) {
      if (openActivityVosBuilder_ == null) {
        ensureOpenActivityVosIsMutable();
        openActivityVos_.set(index, builderForValue.build());
        onChanged();
      } else {
        openActivityVosBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.OpenActivityVo openActivityVos = 2;</code>
     */
    public Builder addOpenActivityVos(pb4client.OpenActivityVo value) {
      if (openActivityVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureOpenActivityVosIsMutable();
        openActivityVos_.add(value);
        onChanged();
      } else {
        openActivityVosBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.OpenActivityVo openActivityVos = 2;</code>
     */
    public Builder addOpenActivityVos(
        int index, pb4client.OpenActivityVo value) {
      if (openActivityVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureOpenActivityVosIsMutable();
        openActivityVos_.add(index, value);
        onChanged();
      } else {
        openActivityVosBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.OpenActivityVo openActivityVos = 2;</code>
     */
    public Builder addOpenActivityVos(
        pb4client.OpenActivityVo.Builder builderForValue) {
      if (openActivityVosBuilder_ == null) {
        ensureOpenActivityVosIsMutable();
        openActivityVos_.add(builderForValue.build());
        onChanged();
      } else {
        openActivityVosBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.OpenActivityVo openActivityVos = 2;</code>
     */
    public Builder addOpenActivityVos(
        int index, pb4client.OpenActivityVo.Builder builderForValue) {
      if (openActivityVosBuilder_ == null) {
        ensureOpenActivityVosIsMutable();
        openActivityVos_.add(index, builderForValue.build());
        onChanged();
      } else {
        openActivityVosBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.OpenActivityVo openActivityVos = 2;</code>
     */
    public Builder addAllOpenActivityVos(
        java.lang.Iterable<? extends pb4client.OpenActivityVo> values) {
      if (openActivityVosBuilder_ == null) {
        ensureOpenActivityVosIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, openActivityVos_);
        onChanged();
      } else {
        openActivityVosBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.OpenActivityVo openActivityVos = 2;</code>
     */
    public Builder clearOpenActivityVos() {
      if (openActivityVosBuilder_ == null) {
        openActivityVos_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        openActivityVosBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .client2server.OpenActivityVo openActivityVos = 2;</code>
     */
    public Builder removeOpenActivityVos(int index) {
      if (openActivityVosBuilder_ == null) {
        ensureOpenActivityVosIsMutable();
        openActivityVos_.remove(index);
        onChanged();
      } else {
        openActivityVosBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.OpenActivityVo openActivityVos = 2;</code>
     */
    public pb4client.OpenActivityVo.Builder getOpenActivityVosBuilder(
        int index) {
      return getOpenActivityVosFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .client2server.OpenActivityVo openActivityVos = 2;</code>
     */
    public pb4client.OpenActivityVoOrBuilder getOpenActivityVosOrBuilder(
        int index) {
      if (openActivityVosBuilder_ == null) {
        return openActivityVos_.get(index);  } else {
        return openActivityVosBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .client2server.OpenActivityVo openActivityVos = 2;</code>
     */
    public java.util.List<? extends pb4client.OpenActivityVoOrBuilder> 
         getOpenActivityVosOrBuilderList() {
      if (openActivityVosBuilder_ != null) {
        return openActivityVosBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(openActivityVos_);
      }
    }
    /**
     * <code>repeated .client2server.OpenActivityVo openActivityVos = 2;</code>
     */
    public pb4client.OpenActivityVo.Builder addOpenActivityVosBuilder() {
      return getOpenActivityVosFieldBuilder().addBuilder(
          pb4client.OpenActivityVo.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.OpenActivityVo openActivityVos = 2;</code>
     */
    public pb4client.OpenActivityVo.Builder addOpenActivityVosBuilder(
        int index) {
      return getOpenActivityVosFieldBuilder().addBuilder(
          index, pb4client.OpenActivityVo.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.OpenActivityVo openActivityVos = 2;</code>
     */
    public java.util.List<pb4client.OpenActivityVo.Builder> 
         getOpenActivityVosBuilderList() {
      return getOpenActivityVosFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.OpenActivityVo, pb4client.OpenActivityVo.Builder, pb4client.OpenActivityVoOrBuilder> 
        getOpenActivityVosFieldBuilder() {
      if (openActivityVosBuilder_ == null) {
        openActivityVosBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.OpenActivityVo, pb4client.OpenActivityVo.Builder, pb4client.OpenActivityVoOrBuilder>(
                openActivityVos_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        openActivityVos_ = null;
      }
      return openActivityVosBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.OpenActivityRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.OpenActivityRt)
  private static final pb4client.OpenActivityRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.OpenActivityRt();
  }

  public static pb4client.OpenActivityRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<OpenActivityRt>
      PARSER = new com.google.protobuf.AbstractParser<OpenActivityRt>() {
    public OpenActivityRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new OpenActivityRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<OpenActivityRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<OpenActivityRt> getParserForType() {
    return PARSER;
  }

  public pb4client.OpenActivityRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

