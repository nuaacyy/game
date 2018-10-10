// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * 魔物图鉴
 * </pre>
 *
 * Protobuf type {@code client2server.BossLibInfo}
 */
public  final class BossLibInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.BossLibInfo)
    BossLibInfoOrBuilder {
  // Use BossLibInfo.newBuilder() to construct.
  private BossLibInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private BossLibInfo() {
    libraryType_ = 0;
    bossInfo_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private BossLibInfo(
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
            libraryType_ = input.readInt32();
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              bossInfo_ = new java.util.ArrayList<pb4client.BossInfo>();
              mutable_bitField0_ |= 0x00000002;
            }
            bossInfo_.add(
                input.readMessage(pb4client.BossInfo.PARSER, extensionRegistry));
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
        bossInfo_ = java.util.Collections.unmodifiableList(bossInfo_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_BossLibInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_BossLibInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.BossLibInfo.class, pb4client.BossLibInfo.Builder.class);
  }

  private int bitField0_;
  public static final int LIBRARYTYPE_FIELD_NUMBER = 1;
  private int libraryType_;
  /**
   * <code>required int32 libraryType = 1;</code>
   */
  public boolean hasLibraryType() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required int32 libraryType = 1;</code>
   */
  public int getLibraryType() {
    return libraryType_;
  }

  public static final int BOSSINFO_FIELD_NUMBER = 2;
  private java.util.List<pb4client.BossInfo> bossInfo_;
  /**
   * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
   */
  public java.util.List<pb4client.BossInfo> getBossInfoList() {
    return bossInfo_;
  }
  /**
   * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
   */
  public java.util.List<? extends pb4client.BossInfoOrBuilder> 
      getBossInfoOrBuilderList() {
    return bossInfo_;
  }
  /**
   * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
   */
  public int getBossInfoCount() {
    return bossInfo_.size();
  }
  /**
   * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
   */
  public pb4client.BossInfo getBossInfo(int index) {
    return bossInfo_.get(index);
  }
  /**
   * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
   */
  public pb4client.BossInfoOrBuilder getBossInfoOrBuilder(
      int index) {
    return bossInfo_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasLibraryType()) {
      memoizedIsInitialized = 0;
      return false;
    }
    for (int i = 0; i < getBossInfoCount(); i++) {
      if (!getBossInfo(i).isInitialized()) {
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
      output.writeInt32(1, libraryType_);
    }
    for (int i = 0; i < bossInfo_.size(); i++) {
      output.writeMessage(2, bossInfo_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, libraryType_);
    }
    for (int i = 0; i < bossInfo_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, bossInfo_.get(i));
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
    if (!(obj instanceof pb4client.BossLibInfo)) {
      return super.equals(obj);
    }
    pb4client.BossLibInfo other = (pb4client.BossLibInfo) obj;

    boolean result = true;
    result = result && (hasLibraryType() == other.hasLibraryType());
    if (hasLibraryType()) {
      result = result && (getLibraryType()
          == other.getLibraryType());
    }
    result = result && getBossInfoList()
        .equals(other.getBossInfoList());
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
    if (hasLibraryType()) {
      hash = (37 * hash) + LIBRARYTYPE_FIELD_NUMBER;
      hash = (53 * hash) + getLibraryType();
    }
    if (getBossInfoCount() > 0) {
      hash = (37 * hash) + BOSSINFO_FIELD_NUMBER;
      hash = (53 * hash) + getBossInfoList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.BossLibInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.BossLibInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.BossLibInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.BossLibInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.BossLibInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.BossLibInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.BossLibInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.BossLibInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.BossLibInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.BossLibInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.BossLibInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.BossLibInfo parseFrom(
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
  public static Builder newBuilder(pb4client.BossLibInfo prototype) {
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
   * 魔物图鉴
   * </pre>
   *
   * Protobuf type {@code client2server.BossLibInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.BossLibInfo)
      pb4client.BossLibInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_BossLibInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_BossLibInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.BossLibInfo.class, pb4client.BossLibInfo.Builder.class);
    }

    // Construct using pb4client.BossLibInfo.newBuilder()
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
        getBossInfoFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      libraryType_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (bossInfoBuilder_ == null) {
        bossInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        bossInfoBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_BossLibInfo_descriptor;
    }

    public pb4client.BossLibInfo getDefaultInstanceForType() {
      return pb4client.BossLibInfo.getDefaultInstance();
    }

    public pb4client.BossLibInfo build() {
      pb4client.BossLibInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.BossLibInfo buildPartial() {
      pb4client.BossLibInfo result = new pb4client.BossLibInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.libraryType_ = libraryType_;
      if (bossInfoBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          bossInfo_ = java.util.Collections.unmodifiableList(bossInfo_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.bossInfo_ = bossInfo_;
      } else {
        result.bossInfo_ = bossInfoBuilder_.build();
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
      if (other instanceof pb4client.BossLibInfo) {
        return mergeFrom((pb4client.BossLibInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.BossLibInfo other) {
      if (other == pb4client.BossLibInfo.getDefaultInstance()) return this;
      if (other.hasLibraryType()) {
        setLibraryType(other.getLibraryType());
      }
      if (bossInfoBuilder_ == null) {
        if (!other.bossInfo_.isEmpty()) {
          if (bossInfo_.isEmpty()) {
            bossInfo_ = other.bossInfo_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureBossInfoIsMutable();
            bossInfo_.addAll(other.bossInfo_);
          }
          onChanged();
        }
      } else {
        if (!other.bossInfo_.isEmpty()) {
          if (bossInfoBuilder_.isEmpty()) {
            bossInfoBuilder_.dispose();
            bossInfoBuilder_ = null;
            bossInfo_ = other.bossInfo_;
            bitField0_ = (bitField0_ & ~0x00000002);
            bossInfoBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getBossInfoFieldBuilder() : null;
          } else {
            bossInfoBuilder_.addAllMessages(other.bossInfo_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasLibraryType()) {
        return false;
      }
      for (int i = 0; i < getBossInfoCount(); i++) {
        if (!getBossInfo(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.BossLibInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.BossLibInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int libraryType_ ;
    /**
     * <code>required int32 libraryType = 1;</code>
     */
    public boolean hasLibraryType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 libraryType = 1;</code>
     */
    public int getLibraryType() {
      return libraryType_;
    }
    /**
     * <code>required int32 libraryType = 1;</code>
     */
    public Builder setLibraryType(int value) {
      bitField0_ |= 0x00000001;
      libraryType_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 libraryType = 1;</code>
     */
    public Builder clearLibraryType() {
      bitField0_ = (bitField0_ & ~0x00000001);
      libraryType_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<pb4client.BossInfo> bossInfo_ =
      java.util.Collections.emptyList();
    private void ensureBossInfoIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        bossInfo_ = new java.util.ArrayList<pb4client.BossInfo>(bossInfo_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.BossInfo, pb4client.BossInfo.Builder, pb4client.BossInfoOrBuilder> bossInfoBuilder_;

    /**
     * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
     */
    public java.util.List<pb4client.BossInfo> getBossInfoList() {
      if (bossInfoBuilder_ == null) {
        return java.util.Collections.unmodifiableList(bossInfo_);
      } else {
        return bossInfoBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
     */
    public int getBossInfoCount() {
      if (bossInfoBuilder_ == null) {
        return bossInfo_.size();
      } else {
        return bossInfoBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
     */
    public pb4client.BossInfo getBossInfo(int index) {
      if (bossInfoBuilder_ == null) {
        return bossInfo_.get(index);
      } else {
        return bossInfoBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
     */
    public Builder setBossInfo(
        int index, pb4client.BossInfo value) {
      if (bossInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureBossInfoIsMutable();
        bossInfo_.set(index, value);
        onChanged();
      } else {
        bossInfoBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
     */
    public Builder setBossInfo(
        int index, pb4client.BossInfo.Builder builderForValue) {
      if (bossInfoBuilder_ == null) {
        ensureBossInfoIsMutable();
        bossInfo_.set(index, builderForValue.build());
        onChanged();
      } else {
        bossInfoBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
     */
    public Builder addBossInfo(pb4client.BossInfo value) {
      if (bossInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureBossInfoIsMutable();
        bossInfo_.add(value);
        onChanged();
      } else {
        bossInfoBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
     */
    public Builder addBossInfo(
        int index, pb4client.BossInfo value) {
      if (bossInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureBossInfoIsMutable();
        bossInfo_.add(index, value);
        onChanged();
      } else {
        bossInfoBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
     */
    public Builder addBossInfo(
        pb4client.BossInfo.Builder builderForValue) {
      if (bossInfoBuilder_ == null) {
        ensureBossInfoIsMutable();
        bossInfo_.add(builderForValue.build());
        onChanged();
      } else {
        bossInfoBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
     */
    public Builder addBossInfo(
        int index, pb4client.BossInfo.Builder builderForValue) {
      if (bossInfoBuilder_ == null) {
        ensureBossInfoIsMutable();
        bossInfo_.add(index, builderForValue.build());
        onChanged();
      } else {
        bossInfoBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
     */
    public Builder addAllBossInfo(
        java.lang.Iterable<? extends pb4client.BossInfo> values) {
      if (bossInfoBuilder_ == null) {
        ensureBossInfoIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, bossInfo_);
        onChanged();
      } else {
        bossInfoBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
     */
    public Builder clearBossInfo() {
      if (bossInfoBuilder_ == null) {
        bossInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        bossInfoBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
     */
    public Builder removeBossInfo(int index) {
      if (bossInfoBuilder_ == null) {
        ensureBossInfoIsMutable();
        bossInfo_.remove(index);
        onChanged();
      } else {
        bossInfoBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
     */
    public pb4client.BossInfo.Builder getBossInfoBuilder(
        int index) {
      return getBossInfoFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
     */
    public pb4client.BossInfoOrBuilder getBossInfoOrBuilder(
        int index) {
      if (bossInfoBuilder_ == null) {
        return bossInfo_.get(index);  } else {
        return bossInfoBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
     */
    public java.util.List<? extends pb4client.BossInfoOrBuilder> 
         getBossInfoOrBuilderList() {
      if (bossInfoBuilder_ != null) {
        return bossInfoBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(bossInfo_);
      }
    }
    /**
     * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
     */
    public pb4client.BossInfo.Builder addBossInfoBuilder() {
      return getBossInfoFieldBuilder().addBuilder(
          pb4client.BossInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
     */
    public pb4client.BossInfo.Builder addBossInfoBuilder(
        int index) {
      return getBossInfoFieldBuilder().addBuilder(
          index, pb4client.BossInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.BossInfo bossInfo = 2;</code>
     */
    public java.util.List<pb4client.BossInfo.Builder> 
         getBossInfoBuilderList() {
      return getBossInfoFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.BossInfo, pb4client.BossInfo.Builder, pb4client.BossInfoOrBuilder> 
        getBossInfoFieldBuilder() {
      if (bossInfoBuilder_ == null) {
        bossInfoBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.BossInfo, pb4client.BossInfo.Builder, pb4client.BossInfoOrBuilder>(
                bossInfo_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        bossInfo_ = null;
      }
      return bossInfoBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.BossLibInfo)
  }

  // @@protoc_insertion_point(class_scope:client2server.BossLibInfo)
  private static final pb4client.BossLibInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.BossLibInfo();
  }

  public static pb4client.BossLibInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<BossLibInfo>
      PARSER = new com.google.protobuf.AbstractParser<BossLibInfo>() {
    public BossLibInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new BossLibInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<BossLibInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<BossLibInfo> getParserForType() {
    return PARSER;
  }

  public pb4client.BossLibInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

