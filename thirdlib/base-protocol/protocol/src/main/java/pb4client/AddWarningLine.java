// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3109
 * 服务器 -&gt; 客户端
 * 新增预警线线主推
 * </pre>
 *
 * Protobuf type {@code client2server.AddWarningLine}
 */
public  final class AddWarningLine extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.AddWarningLine)
    AddWarningLineOrBuilder {
  // Use AddWarningLine.newBuilder() to construct.
  private AddWarningLine(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AddWarningLine() {
    warningInfo_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AddWarningLine(
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
              warningInfo_ = new java.util.ArrayList<pb4client.WarningInfo>();
              mutable_bitField0_ |= 0x00000001;
            }
            warningInfo_.add(
                input.readMessage(pb4client.WarningInfo.PARSER, extensionRegistry));
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
        warningInfo_ = java.util.Collections.unmodifiableList(warningInfo_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_AddWarningLine_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_AddWarningLine_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.AddWarningLine.class, pb4client.AddWarningLine.Builder.class);
  }

  public static final int WARNINGINFO_FIELD_NUMBER = 1;
  private java.util.List<pb4client.WarningInfo> warningInfo_;
  /**
   * <code>repeated .client2server.WarningInfo warningInfo = 1;</code>
   */
  public java.util.List<pb4client.WarningInfo> getWarningInfoList() {
    return warningInfo_;
  }
  /**
   * <code>repeated .client2server.WarningInfo warningInfo = 1;</code>
   */
  public java.util.List<? extends pb4client.WarningInfoOrBuilder> 
      getWarningInfoOrBuilderList() {
    return warningInfo_;
  }
  /**
   * <code>repeated .client2server.WarningInfo warningInfo = 1;</code>
   */
  public int getWarningInfoCount() {
    return warningInfo_.size();
  }
  /**
   * <code>repeated .client2server.WarningInfo warningInfo = 1;</code>
   */
  public pb4client.WarningInfo getWarningInfo(int index) {
    return warningInfo_.get(index);
  }
  /**
   * <code>repeated .client2server.WarningInfo warningInfo = 1;</code>
   */
  public pb4client.WarningInfoOrBuilder getWarningInfoOrBuilder(
      int index) {
    return warningInfo_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    for (int i = 0; i < getWarningInfoCount(); i++) {
      if (!getWarningInfo(i).isInitialized()) {
        memoizedIsInitialized = 0;
        return false;
      }
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < warningInfo_.size(); i++) {
      output.writeMessage(1, warningInfo_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < warningInfo_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, warningInfo_.get(i));
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
    if (!(obj instanceof pb4client.AddWarningLine)) {
      return super.equals(obj);
    }
    pb4client.AddWarningLine other = (pb4client.AddWarningLine) obj;

    boolean result = true;
    result = result && getWarningInfoList()
        .equals(other.getWarningInfoList());
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
    if (getWarningInfoCount() > 0) {
      hash = (37 * hash) + WARNINGINFO_FIELD_NUMBER;
      hash = (53 * hash) + getWarningInfoList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.AddWarningLine parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AddWarningLine parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AddWarningLine parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AddWarningLine parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AddWarningLine parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AddWarningLine parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AddWarningLine parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AddWarningLine parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AddWarningLine parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.AddWarningLine parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AddWarningLine parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AddWarningLine parseFrom(
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
  public static Builder newBuilder(pb4client.AddWarningLine prototype) {
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
   * msgType = 3109
   * 服务器 -&gt; 客户端
   * 新增预警线线主推
   * </pre>
   *
   * Protobuf type {@code client2server.AddWarningLine}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.AddWarningLine)
      pb4client.AddWarningLineOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_AddWarningLine_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_AddWarningLine_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.AddWarningLine.class, pb4client.AddWarningLine.Builder.class);
    }

    // Construct using pb4client.AddWarningLine.newBuilder()
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
        getWarningInfoFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (warningInfoBuilder_ == null) {
        warningInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        warningInfoBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_AddWarningLine_descriptor;
    }

    public pb4client.AddWarningLine getDefaultInstanceForType() {
      return pb4client.AddWarningLine.getDefaultInstance();
    }

    public pb4client.AddWarningLine build() {
      pb4client.AddWarningLine result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.AddWarningLine buildPartial() {
      pb4client.AddWarningLine result = new pb4client.AddWarningLine(this);
      int from_bitField0_ = bitField0_;
      if (warningInfoBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          warningInfo_ = java.util.Collections.unmodifiableList(warningInfo_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.warningInfo_ = warningInfo_;
      } else {
        result.warningInfo_ = warningInfoBuilder_.build();
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
      if (other instanceof pb4client.AddWarningLine) {
        return mergeFrom((pb4client.AddWarningLine)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.AddWarningLine other) {
      if (other == pb4client.AddWarningLine.getDefaultInstance()) return this;
      if (warningInfoBuilder_ == null) {
        if (!other.warningInfo_.isEmpty()) {
          if (warningInfo_.isEmpty()) {
            warningInfo_ = other.warningInfo_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureWarningInfoIsMutable();
            warningInfo_.addAll(other.warningInfo_);
          }
          onChanged();
        }
      } else {
        if (!other.warningInfo_.isEmpty()) {
          if (warningInfoBuilder_.isEmpty()) {
            warningInfoBuilder_.dispose();
            warningInfoBuilder_ = null;
            warningInfo_ = other.warningInfo_;
            bitField0_ = (bitField0_ & ~0x00000001);
            warningInfoBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getWarningInfoFieldBuilder() : null;
          } else {
            warningInfoBuilder_.addAllMessages(other.warningInfo_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      for (int i = 0; i < getWarningInfoCount(); i++) {
        if (!getWarningInfo(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.AddWarningLine parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.AddWarningLine) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<pb4client.WarningInfo> warningInfo_ =
      java.util.Collections.emptyList();
    private void ensureWarningInfoIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        warningInfo_ = new java.util.ArrayList<pb4client.WarningInfo>(warningInfo_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.WarningInfo, pb4client.WarningInfo.Builder, pb4client.WarningInfoOrBuilder> warningInfoBuilder_;

    /**
     * <code>repeated .client2server.WarningInfo warningInfo = 1;</code>
     */
    public java.util.List<pb4client.WarningInfo> getWarningInfoList() {
      if (warningInfoBuilder_ == null) {
        return java.util.Collections.unmodifiableList(warningInfo_);
      } else {
        return warningInfoBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .client2server.WarningInfo warningInfo = 1;</code>
     */
    public int getWarningInfoCount() {
      if (warningInfoBuilder_ == null) {
        return warningInfo_.size();
      } else {
        return warningInfoBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .client2server.WarningInfo warningInfo = 1;</code>
     */
    public pb4client.WarningInfo getWarningInfo(int index) {
      if (warningInfoBuilder_ == null) {
        return warningInfo_.get(index);
      } else {
        return warningInfoBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .client2server.WarningInfo warningInfo = 1;</code>
     */
    public Builder setWarningInfo(
        int index, pb4client.WarningInfo value) {
      if (warningInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureWarningInfoIsMutable();
        warningInfo_.set(index, value);
        onChanged();
      } else {
        warningInfoBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WarningInfo warningInfo = 1;</code>
     */
    public Builder setWarningInfo(
        int index, pb4client.WarningInfo.Builder builderForValue) {
      if (warningInfoBuilder_ == null) {
        ensureWarningInfoIsMutable();
        warningInfo_.set(index, builderForValue.build());
        onChanged();
      } else {
        warningInfoBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WarningInfo warningInfo = 1;</code>
     */
    public Builder addWarningInfo(pb4client.WarningInfo value) {
      if (warningInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureWarningInfoIsMutable();
        warningInfo_.add(value);
        onChanged();
      } else {
        warningInfoBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WarningInfo warningInfo = 1;</code>
     */
    public Builder addWarningInfo(
        int index, pb4client.WarningInfo value) {
      if (warningInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureWarningInfoIsMutable();
        warningInfo_.add(index, value);
        onChanged();
      } else {
        warningInfoBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WarningInfo warningInfo = 1;</code>
     */
    public Builder addWarningInfo(
        pb4client.WarningInfo.Builder builderForValue) {
      if (warningInfoBuilder_ == null) {
        ensureWarningInfoIsMutable();
        warningInfo_.add(builderForValue.build());
        onChanged();
      } else {
        warningInfoBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WarningInfo warningInfo = 1;</code>
     */
    public Builder addWarningInfo(
        int index, pb4client.WarningInfo.Builder builderForValue) {
      if (warningInfoBuilder_ == null) {
        ensureWarningInfoIsMutable();
        warningInfo_.add(index, builderForValue.build());
        onChanged();
      } else {
        warningInfoBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WarningInfo warningInfo = 1;</code>
     */
    public Builder addAllWarningInfo(
        java.lang.Iterable<? extends pb4client.WarningInfo> values) {
      if (warningInfoBuilder_ == null) {
        ensureWarningInfoIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, warningInfo_);
        onChanged();
      } else {
        warningInfoBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WarningInfo warningInfo = 1;</code>
     */
    public Builder clearWarningInfo() {
      if (warningInfoBuilder_ == null) {
        warningInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        warningInfoBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WarningInfo warningInfo = 1;</code>
     */
    public Builder removeWarningInfo(int index) {
      if (warningInfoBuilder_ == null) {
        ensureWarningInfoIsMutable();
        warningInfo_.remove(index);
        onChanged();
      } else {
        warningInfoBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.WarningInfo warningInfo = 1;</code>
     */
    public pb4client.WarningInfo.Builder getWarningInfoBuilder(
        int index) {
      return getWarningInfoFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .client2server.WarningInfo warningInfo = 1;</code>
     */
    public pb4client.WarningInfoOrBuilder getWarningInfoOrBuilder(
        int index) {
      if (warningInfoBuilder_ == null) {
        return warningInfo_.get(index);  } else {
        return warningInfoBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .client2server.WarningInfo warningInfo = 1;</code>
     */
    public java.util.List<? extends pb4client.WarningInfoOrBuilder> 
         getWarningInfoOrBuilderList() {
      if (warningInfoBuilder_ != null) {
        return warningInfoBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(warningInfo_);
      }
    }
    /**
     * <code>repeated .client2server.WarningInfo warningInfo = 1;</code>
     */
    public pb4client.WarningInfo.Builder addWarningInfoBuilder() {
      return getWarningInfoFieldBuilder().addBuilder(
          pb4client.WarningInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.WarningInfo warningInfo = 1;</code>
     */
    public pb4client.WarningInfo.Builder addWarningInfoBuilder(
        int index) {
      return getWarningInfoFieldBuilder().addBuilder(
          index, pb4client.WarningInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.WarningInfo warningInfo = 1;</code>
     */
    public java.util.List<pb4client.WarningInfo.Builder> 
         getWarningInfoBuilderList() {
      return getWarningInfoFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.WarningInfo, pb4client.WarningInfo.Builder, pb4client.WarningInfoOrBuilder> 
        getWarningInfoFieldBuilder() {
      if (warningInfoBuilder_ == null) {
        warningInfoBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.WarningInfo, pb4client.WarningInfo.Builder, pb4client.WarningInfoOrBuilder>(
                warningInfo_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        warningInfo_ = null;
      }
      return warningInfoBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.AddWarningLine)
  }

  // @@protoc_insertion_point(class_scope:client2server.AddWarningLine)
  private static final pb4client.AddWarningLine DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.AddWarningLine();
  }

  public static pb4client.AddWarningLine getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<AddWarningLine>
      PARSER = new com.google.protobuf.AbstractParser<AddWarningLine>() {
    public AddWarningLine parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AddWarningLine(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AddWarningLine> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AddWarningLine> getParserForType() {
    return PARSER;
  }

  public pb4client.AddWarningLine getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

