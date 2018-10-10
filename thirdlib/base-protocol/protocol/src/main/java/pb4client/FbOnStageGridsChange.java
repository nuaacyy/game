// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3094
 * 推送正在进行中的副本的格子信息
 * </pre>
 *
 * Protobuf type {@code client2server.FbOnStageGridsChange}
 */
public  final class FbOnStageGridsChange extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.FbOnStageGridsChange)
    FbOnStageGridsChangeOrBuilder {
  // Use FbOnStageGridsChange.newBuilder() to construct.
  private FbOnStageGridsChange(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private FbOnStageGridsChange() {
    grids_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private FbOnStageGridsChange(
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
              grids_ = new java.util.ArrayList<pb4client.FbForceGridInfo>();
              mutable_bitField0_ |= 0x00000001;
            }
            grids_.add(
                input.readMessage(pb4client.FbForceGridInfo.PARSER, extensionRegistry));
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
        grids_ = java.util.Collections.unmodifiableList(grids_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_FbOnStageGridsChange_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_FbOnStageGridsChange_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.FbOnStageGridsChange.class, pb4client.FbOnStageGridsChange.Builder.class);
  }

  public static final int GRIDS_FIELD_NUMBER = 1;
  private java.util.List<pb4client.FbForceGridInfo> grids_;
  /**
   * <code>repeated .client2server.FbForceGridInfo grids = 1;</code>
   */
  public java.util.List<pb4client.FbForceGridInfo> getGridsList() {
    return grids_;
  }
  /**
   * <code>repeated .client2server.FbForceGridInfo grids = 1;</code>
   */
  public java.util.List<? extends pb4client.FbForceGridInfoOrBuilder> 
      getGridsOrBuilderList() {
    return grids_;
  }
  /**
   * <code>repeated .client2server.FbForceGridInfo grids = 1;</code>
   */
  public int getGridsCount() {
    return grids_.size();
  }
  /**
   * <code>repeated .client2server.FbForceGridInfo grids = 1;</code>
   */
  public pb4client.FbForceGridInfo getGrids(int index) {
    return grids_.get(index);
  }
  /**
   * <code>repeated .client2server.FbForceGridInfo grids = 1;</code>
   */
  public pb4client.FbForceGridInfoOrBuilder getGridsOrBuilder(
      int index) {
    return grids_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    for (int i = 0; i < getGridsCount(); i++) {
      if (!getGrids(i).isInitialized()) {
        memoizedIsInitialized = 0;
        return false;
      }
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < grids_.size(); i++) {
      output.writeMessage(1, grids_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < grids_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, grids_.get(i));
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
    if (!(obj instanceof pb4client.FbOnStageGridsChange)) {
      return super.equals(obj);
    }
    pb4client.FbOnStageGridsChange other = (pb4client.FbOnStageGridsChange) obj;

    boolean result = true;
    result = result && getGridsList()
        .equals(other.getGridsList());
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
    if (getGridsCount() > 0) {
      hash = (37 * hash) + GRIDS_FIELD_NUMBER;
      hash = (53 * hash) + getGridsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.FbOnStageGridsChange parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.FbOnStageGridsChange parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.FbOnStageGridsChange parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.FbOnStageGridsChange parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.FbOnStageGridsChange parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.FbOnStageGridsChange parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.FbOnStageGridsChange parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.FbOnStageGridsChange parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.FbOnStageGridsChange parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.FbOnStageGridsChange parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.FbOnStageGridsChange parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.FbOnStageGridsChange parseFrom(
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
  public static Builder newBuilder(pb4client.FbOnStageGridsChange prototype) {
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
   * msgType = 3094
   * 推送正在进行中的副本的格子信息
   * </pre>
   *
   * Protobuf type {@code client2server.FbOnStageGridsChange}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.FbOnStageGridsChange)
      pb4client.FbOnStageGridsChangeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_FbOnStageGridsChange_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_FbOnStageGridsChange_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.FbOnStageGridsChange.class, pb4client.FbOnStageGridsChange.Builder.class);
    }

    // Construct using pb4client.FbOnStageGridsChange.newBuilder()
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
        getGridsFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (gridsBuilder_ == null) {
        grids_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        gridsBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_FbOnStageGridsChange_descriptor;
    }

    public pb4client.FbOnStageGridsChange getDefaultInstanceForType() {
      return pb4client.FbOnStageGridsChange.getDefaultInstance();
    }

    public pb4client.FbOnStageGridsChange build() {
      pb4client.FbOnStageGridsChange result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.FbOnStageGridsChange buildPartial() {
      pb4client.FbOnStageGridsChange result = new pb4client.FbOnStageGridsChange(this);
      int from_bitField0_ = bitField0_;
      if (gridsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          grids_ = java.util.Collections.unmodifiableList(grids_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.grids_ = grids_;
      } else {
        result.grids_ = gridsBuilder_.build();
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
      if (other instanceof pb4client.FbOnStageGridsChange) {
        return mergeFrom((pb4client.FbOnStageGridsChange)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.FbOnStageGridsChange other) {
      if (other == pb4client.FbOnStageGridsChange.getDefaultInstance()) return this;
      if (gridsBuilder_ == null) {
        if (!other.grids_.isEmpty()) {
          if (grids_.isEmpty()) {
            grids_ = other.grids_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureGridsIsMutable();
            grids_.addAll(other.grids_);
          }
          onChanged();
        }
      } else {
        if (!other.grids_.isEmpty()) {
          if (gridsBuilder_.isEmpty()) {
            gridsBuilder_.dispose();
            gridsBuilder_ = null;
            grids_ = other.grids_;
            bitField0_ = (bitField0_ & ~0x00000001);
            gridsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getGridsFieldBuilder() : null;
          } else {
            gridsBuilder_.addAllMessages(other.grids_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      for (int i = 0; i < getGridsCount(); i++) {
        if (!getGrids(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.FbOnStageGridsChange parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.FbOnStageGridsChange) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<pb4client.FbForceGridInfo> grids_ =
      java.util.Collections.emptyList();
    private void ensureGridsIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        grids_ = new java.util.ArrayList<pb4client.FbForceGridInfo>(grids_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.FbForceGridInfo, pb4client.FbForceGridInfo.Builder, pb4client.FbForceGridInfoOrBuilder> gridsBuilder_;

    /**
     * <code>repeated .client2server.FbForceGridInfo grids = 1;</code>
     */
    public java.util.List<pb4client.FbForceGridInfo> getGridsList() {
      if (gridsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(grids_);
      } else {
        return gridsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .client2server.FbForceGridInfo grids = 1;</code>
     */
    public int getGridsCount() {
      if (gridsBuilder_ == null) {
        return grids_.size();
      } else {
        return gridsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .client2server.FbForceGridInfo grids = 1;</code>
     */
    public pb4client.FbForceGridInfo getGrids(int index) {
      if (gridsBuilder_ == null) {
        return grids_.get(index);
      } else {
        return gridsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .client2server.FbForceGridInfo grids = 1;</code>
     */
    public Builder setGrids(
        int index, pb4client.FbForceGridInfo value) {
      if (gridsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureGridsIsMutable();
        grids_.set(index, value);
        onChanged();
      } else {
        gridsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.FbForceGridInfo grids = 1;</code>
     */
    public Builder setGrids(
        int index, pb4client.FbForceGridInfo.Builder builderForValue) {
      if (gridsBuilder_ == null) {
        ensureGridsIsMutable();
        grids_.set(index, builderForValue.build());
        onChanged();
      } else {
        gridsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.FbForceGridInfo grids = 1;</code>
     */
    public Builder addGrids(pb4client.FbForceGridInfo value) {
      if (gridsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureGridsIsMutable();
        grids_.add(value);
        onChanged();
      } else {
        gridsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.FbForceGridInfo grids = 1;</code>
     */
    public Builder addGrids(
        int index, pb4client.FbForceGridInfo value) {
      if (gridsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureGridsIsMutable();
        grids_.add(index, value);
        onChanged();
      } else {
        gridsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.FbForceGridInfo grids = 1;</code>
     */
    public Builder addGrids(
        pb4client.FbForceGridInfo.Builder builderForValue) {
      if (gridsBuilder_ == null) {
        ensureGridsIsMutable();
        grids_.add(builderForValue.build());
        onChanged();
      } else {
        gridsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.FbForceGridInfo grids = 1;</code>
     */
    public Builder addGrids(
        int index, pb4client.FbForceGridInfo.Builder builderForValue) {
      if (gridsBuilder_ == null) {
        ensureGridsIsMutable();
        grids_.add(index, builderForValue.build());
        onChanged();
      } else {
        gridsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.FbForceGridInfo grids = 1;</code>
     */
    public Builder addAllGrids(
        java.lang.Iterable<? extends pb4client.FbForceGridInfo> values) {
      if (gridsBuilder_ == null) {
        ensureGridsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, grids_);
        onChanged();
      } else {
        gridsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.FbForceGridInfo grids = 1;</code>
     */
    public Builder clearGrids() {
      if (gridsBuilder_ == null) {
        grids_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        gridsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .client2server.FbForceGridInfo grids = 1;</code>
     */
    public Builder removeGrids(int index) {
      if (gridsBuilder_ == null) {
        ensureGridsIsMutable();
        grids_.remove(index);
        onChanged();
      } else {
        gridsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.FbForceGridInfo grids = 1;</code>
     */
    public pb4client.FbForceGridInfo.Builder getGridsBuilder(
        int index) {
      return getGridsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .client2server.FbForceGridInfo grids = 1;</code>
     */
    public pb4client.FbForceGridInfoOrBuilder getGridsOrBuilder(
        int index) {
      if (gridsBuilder_ == null) {
        return grids_.get(index);  } else {
        return gridsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .client2server.FbForceGridInfo grids = 1;</code>
     */
    public java.util.List<? extends pb4client.FbForceGridInfoOrBuilder> 
         getGridsOrBuilderList() {
      if (gridsBuilder_ != null) {
        return gridsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(grids_);
      }
    }
    /**
     * <code>repeated .client2server.FbForceGridInfo grids = 1;</code>
     */
    public pb4client.FbForceGridInfo.Builder addGridsBuilder() {
      return getGridsFieldBuilder().addBuilder(
          pb4client.FbForceGridInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.FbForceGridInfo grids = 1;</code>
     */
    public pb4client.FbForceGridInfo.Builder addGridsBuilder(
        int index) {
      return getGridsFieldBuilder().addBuilder(
          index, pb4client.FbForceGridInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.FbForceGridInfo grids = 1;</code>
     */
    public java.util.List<pb4client.FbForceGridInfo.Builder> 
         getGridsBuilderList() {
      return getGridsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.FbForceGridInfo, pb4client.FbForceGridInfo.Builder, pb4client.FbForceGridInfoOrBuilder> 
        getGridsFieldBuilder() {
      if (gridsBuilder_ == null) {
        gridsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.FbForceGridInfo, pb4client.FbForceGridInfo.Builder, pb4client.FbForceGridInfoOrBuilder>(
                grids_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        grids_ = null;
      }
      return gridsBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.FbOnStageGridsChange)
  }

  // @@protoc_insertion_point(class_scope:client2server.FbOnStageGridsChange)
  private static final pb4client.FbOnStageGridsChange DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.FbOnStageGridsChange();
  }

  public static pb4client.FbOnStageGridsChange getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<FbOnStageGridsChange>
      PARSER = new com.google.protobuf.AbstractParser<FbOnStageGridsChange>() {
    public FbOnStageGridsChange parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new FbOnStageGridsChange(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<FbOnStageGridsChange> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<FbOnStageGridsChange> getParserForType() {
    return PARSER;
  }

  public pb4client.FbOnStageGridsChange getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

