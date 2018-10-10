// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

/**
 * <pre>
 * 更新infoByHome中的数据
 * </pre>
 *
 * Protobuf type {@code pb4server.UpdateInfoByHomeAskReq}
 */
public  final class UpdateInfoByHomeAskReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.UpdateInfoByHomeAskReq)
    UpdateInfoByHomeAskReqOrBuilder {
  // Use UpdateInfoByHomeAskReq.newBuilder() to construct.
  private UpdateInfoByHomeAskReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private UpdateInfoByHomeAskReq() {
    updates_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private UpdateInfoByHomeAskReq(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              updates_ = new java.util.ArrayList<pb4server.UpdateInfoByHomeVo>();
              mutable_bitField0_ |= 0x00000001;
            }
            updates_.add(
                input.readMessage(pb4server.UpdateInfoByHomeVo.parser(), extensionRegistry));
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
        updates_ = java.util.Collections.unmodifiableList(updates_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalWkt.internal_static_pb4server_UpdateInfoByHomeAskReq_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalWkt.internal_static_pb4server_UpdateInfoByHomeAskReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.UpdateInfoByHomeAskReq.class, pb4server.UpdateInfoByHomeAskReq.Builder.class);
  }

  public static final int UPDATES_FIELD_NUMBER = 1;
  private java.util.List<pb4server.UpdateInfoByHomeVo> updates_;
  /**
   * <code>repeated .pb4server.UpdateInfoByHomeVo updates = 1;</code>
   */
  public java.util.List<pb4server.UpdateInfoByHomeVo> getUpdatesList() {
    return updates_;
  }
  /**
   * <code>repeated .pb4server.UpdateInfoByHomeVo updates = 1;</code>
   */
  public java.util.List<? extends pb4server.UpdateInfoByHomeVoOrBuilder> 
      getUpdatesOrBuilderList() {
    return updates_;
  }
  /**
   * <code>repeated .pb4server.UpdateInfoByHomeVo updates = 1;</code>
   */
  public int getUpdatesCount() {
    return updates_.size();
  }
  /**
   * <code>repeated .pb4server.UpdateInfoByHomeVo updates = 1;</code>
   */
  public pb4server.UpdateInfoByHomeVo getUpdates(int index) {
    return updates_.get(index);
  }
  /**
   * <code>repeated .pb4server.UpdateInfoByHomeVo updates = 1;</code>
   */
  public pb4server.UpdateInfoByHomeVoOrBuilder getUpdatesOrBuilder(
      int index) {
    return updates_.get(index);
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
    for (int i = 0; i < updates_.size(); i++) {
      output.writeMessage(1, updates_.get(i));
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < updates_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, updates_.get(i));
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof pb4server.UpdateInfoByHomeAskReq)) {
      return super.equals(obj);
    }
    pb4server.UpdateInfoByHomeAskReq other = (pb4server.UpdateInfoByHomeAskReq) obj;

    boolean result = true;
    result = result && getUpdatesList()
        .equals(other.getUpdatesList());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getUpdatesCount() > 0) {
      hash = (37 * hash) + UPDATES_FIELD_NUMBER;
      hash = (53 * hash) + getUpdatesList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.UpdateInfoByHomeAskReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.UpdateInfoByHomeAskReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.UpdateInfoByHomeAskReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.UpdateInfoByHomeAskReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.UpdateInfoByHomeAskReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.UpdateInfoByHomeAskReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.UpdateInfoByHomeAskReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.UpdateInfoByHomeAskReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.UpdateInfoByHomeAskReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.UpdateInfoByHomeAskReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.UpdateInfoByHomeAskReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.UpdateInfoByHomeAskReq parseFrom(
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
  public static Builder newBuilder(pb4server.UpdateInfoByHomeAskReq prototype) {
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
   * 更新infoByHome中的数据
   * </pre>
   *
   * Protobuf type {@code pb4server.UpdateInfoByHomeAskReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.UpdateInfoByHomeAskReq)
      pb4server.UpdateInfoByHomeAskReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalWkt.internal_static_pb4server_UpdateInfoByHomeAskReq_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalWkt.internal_static_pb4server_UpdateInfoByHomeAskReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.UpdateInfoByHomeAskReq.class, pb4server.UpdateInfoByHomeAskReq.Builder.class);
    }

    // Construct using pb4server.UpdateInfoByHomeAskReq.newBuilder()
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
        getUpdatesFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (updatesBuilder_ == null) {
        updates_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        updatesBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalWkt.internal_static_pb4server_UpdateInfoByHomeAskReq_descriptor;
    }

    public pb4server.UpdateInfoByHomeAskReq getDefaultInstanceForType() {
      return pb4server.UpdateInfoByHomeAskReq.getDefaultInstance();
    }

    public pb4server.UpdateInfoByHomeAskReq build() {
      pb4server.UpdateInfoByHomeAskReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.UpdateInfoByHomeAskReq buildPartial() {
      pb4server.UpdateInfoByHomeAskReq result = new pb4server.UpdateInfoByHomeAskReq(this);
      int from_bitField0_ = bitField0_;
      if (updatesBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          updates_ = java.util.Collections.unmodifiableList(updates_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.updates_ = updates_;
      } else {
        result.updates_ = updatesBuilder_.build();
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
      if (other instanceof pb4server.UpdateInfoByHomeAskReq) {
        return mergeFrom((pb4server.UpdateInfoByHomeAskReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.UpdateInfoByHomeAskReq other) {
      if (other == pb4server.UpdateInfoByHomeAskReq.getDefaultInstance()) return this;
      if (updatesBuilder_ == null) {
        if (!other.updates_.isEmpty()) {
          if (updates_.isEmpty()) {
            updates_ = other.updates_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureUpdatesIsMutable();
            updates_.addAll(other.updates_);
          }
          onChanged();
        }
      } else {
        if (!other.updates_.isEmpty()) {
          if (updatesBuilder_.isEmpty()) {
            updatesBuilder_.dispose();
            updatesBuilder_ = null;
            updates_ = other.updates_;
            bitField0_ = (bitField0_ & ~0x00000001);
            updatesBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getUpdatesFieldBuilder() : null;
          } else {
            updatesBuilder_.addAllMessages(other.updates_);
          }
        }
      }
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
      pb4server.UpdateInfoByHomeAskReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.UpdateInfoByHomeAskReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<pb4server.UpdateInfoByHomeVo> updates_ =
      java.util.Collections.emptyList();
    private void ensureUpdatesIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        updates_ = new java.util.ArrayList<pb4server.UpdateInfoByHomeVo>(updates_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.UpdateInfoByHomeVo, pb4server.UpdateInfoByHomeVo.Builder, pb4server.UpdateInfoByHomeVoOrBuilder> updatesBuilder_;

    /**
     * <code>repeated .pb4server.UpdateInfoByHomeVo updates = 1;</code>
     */
    public java.util.List<pb4server.UpdateInfoByHomeVo> getUpdatesList() {
      if (updatesBuilder_ == null) {
        return java.util.Collections.unmodifiableList(updates_);
      } else {
        return updatesBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .pb4server.UpdateInfoByHomeVo updates = 1;</code>
     */
    public int getUpdatesCount() {
      if (updatesBuilder_ == null) {
        return updates_.size();
      } else {
        return updatesBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .pb4server.UpdateInfoByHomeVo updates = 1;</code>
     */
    public pb4server.UpdateInfoByHomeVo getUpdates(int index) {
      if (updatesBuilder_ == null) {
        return updates_.get(index);
      } else {
        return updatesBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .pb4server.UpdateInfoByHomeVo updates = 1;</code>
     */
    public Builder setUpdates(
        int index, pb4server.UpdateInfoByHomeVo value) {
      if (updatesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureUpdatesIsMutable();
        updates_.set(index, value);
        onChanged();
      } else {
        updatesBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.UpdateInfoByHomeVo updates = 1;</code>
     */
    public Builder setUpdates(
        int index, pb4server.UpdateInfoByHomeVo.Builder builderForValue) {
      if (updatesBuilder_ == null) {
        ensureUpdatesIsMutable();
        updates_.set(index, builderForValue.build());
        onChanged();
      } else {
        updatesBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.UpdateInfoByHomeVo updates = 1;</code>
     */
    public Builder addUpdates(pb4server.UpdateInfoByHomeVo value) {
      if (updatesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureUpdatesIsMutable();
        updates_.add(value);
        onChanged();
      } else {
        updatesBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.UpdateInfoByHomeVo updates = 1;</code>
     */
    public Builder addUpdates(
        int index, pb4server.UpdateInfoByHomeVo value) {
      if (updatesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureUpdatesIsMutable();
        updates_.add(index, value);
        onChanged();
      } else {
        updatesBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.UpdateInfoByHomeVo updates = 1;</code>
     */
    public Builder addUpdates(
        pb4server.UpdateInfoByHomeVo.Builder builderForValue) {
      if (updatesBuilder_ == null) {
        ensureUpdatesIsMutable();
        updates_.add(builderForValue.build());
        onChanged();
      } else {
        updatesBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.UpdateInfoByHomeVo updates = 1;</code>
     */
    public Builder addUpdates(
        int index, pb4server.UpdateInfoByHomeVo.Builder builderForValue) {
      if (updatesBuilder_ == null) {
        ensureUpdatesIsMutable();
        updates_.add(index, builderForValue.build());
        onChanged();
      } else {
        updatesBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.UpdateInfoByHomeVo updates = 1;</code>
     */
    public Builder addAllUpdates(
        java.lang.Iterable<? extends pb4server.UpdateInfoByHomeVo> values) {
      if (updatesBuilder_ == null) {
        ensureUpdatesIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, updates_);
        onChanged();
      } else {
        updatesBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.UpdateInfoByHomeVo updates = 1;</code>
     */
    public Builder clearUpdates() {
      if (updatesBuilder_ == null) {
        updates_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        updatesBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.UpdateInfoByHomeVo updates = 1;</code>
     */
    public Builder removeUpdates(int index) {
      if (updatesBuilder_ == null) {
        ensureUpdatesIsMutable();
        updates_.remove(index);
        onChanged();
      } else {
        updatesBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .pb4server.UpdateInfoByHomeVo updates = 1;</code>
     */
    public pb4server.UpdateInfoByHomeVo.Builder getUpdatesBuilder(
        int index) {
      return getUpdatesFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .pb4server.UpdateInfoByHomeVo updates = 1;</code>
     */
    public pb4server.UpdateInfoByHomeVoOrBuilder getUpdatesOrBuilder(
        int index) {
      if (updatesBuilder_ == null) {
        return updates_.get(index);  } else {
        return updatesBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .pb4server.UpdateInfoByHomeVo updates = 1;</code>
     */
    public java.util.List<? extends pb4server.UpdateInfoByHomeVoOrBuilder> 
         getUpdatesOrBuilderList() {
      if (updatesBuilder_ != null) {
        return updatesBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(updates_);
      }
    }
    /**
     * <code>repeated .pb4server.UpdateInfoByHomeVo updates = 1;</code>
     */
    public pb4server.UpdateInfoByHomeVo.Builder addUpdatesBuilder() {
      return getUpdatesFieldBuilder().addBuilder(
          pb4server.UpdateInfoByHomeVo.getDefaultInstance());
    }
    /**
     * <code>repeated .pb4server.UpdateInfoByHomeVo updates = 1;</code>
     */
    public pb4server.UpdateInfoByHomeVo.Builder addUpdatesBuilder(
        int index) {
      return getUpdatesFieldBuilder().addBuilder(
          index, pb4server.UpdateInfoByHomeVo.getDefaultInstance());
    }
    /**
     * <code>repeated .pb4server.UpdateInfoByHomeVo updates = 1;</code>
     */
    public java.util.List<pb4server.UpdateInfoByHomeVo.Builder> 
         getUpdatesBuilderList() {
      return getUpdatesFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4server.UpdateInfoByHomeVo, pb4server.UpdateInfoByHomeVo.Builder, pb4server.UpdateInfoByHomeVoOrBuilder> 
        getUpdatesFieldBuilder() {
      if (updatesBuilder_ == null) {
        updatesBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4server.UpdateInfoByHomeVo, pb4server.UpdateInfoByHomeVo.Builder, pb4server.UpdateInfoByHomeVoOrBuilder>(
                updates_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        updates_ = null;
      }
      return updatesBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.UpdateInfoByHomeAskReq)
  }

  // @@protoc_insertion_point(class_scope:pb4server.UpdateInfoByHomeAskReq)
  private static final pb4server.UpdateInfoByHomeAskReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.UpdateInfoByHomeAskReq();
  }

  public static pb4server.UpdateInfoByHomeAskReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<UpdateInfoByHomeAskReq>
      PARSER = new com.google.protobuf.AbstractParser<UpdateInfoByHomeAskReq>() {
    public UpdateInfoByHomeAskReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new UpdateInfoByHomeAskReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<UpdateInfoByHomeAskReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<UpdateInfoByHomeAskReq> getParserForType() {
    return PARSER;
  }

  public pb4server.UpdateInfoByHomeAskReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
