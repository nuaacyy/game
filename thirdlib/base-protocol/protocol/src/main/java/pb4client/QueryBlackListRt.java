// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.QueryBlackListRt}
 */
public  final class QueryBlackListRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.QueryBlackListRt)
    QueryBlackListRtOrBuilder {
  // Use QueryBlackListRt.newBuilder() to construct.
  private QueryBlackListRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QueryBlackListRt() {
    rt_ = 0;
    blackPlayerInfo_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private QueryBlackListRt(
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
              blackPlayerInfo_ = new java.util.ArrayList<pb4client.FriendInfo>();
              mutable_bitField0_ |= 0x00000002;
            }
            blackPlayerInfo_.add(
                input.readMessage(pb4client.FriendInfo.PARSER, extensionRegistry));
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
        blackPlayerInfo_ = java.util.Collections.unmodifiableList(blackPlayerInfo_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_QueryBlackListRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_QueryBlackListRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.QueryBlackListRt.class, pb4client.QueryBlackListRt.Builder.class);
  }

  private int bitField0_;
  public static final int RT_FIELD_NUMBER = 1;
  private int rt_;
  /**
   * <code>required int32 rt = 1;</code>
   */
  public boolean hasRt() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required int32 rt = 1;</code>
   */
  public int getRt() {
    return rt_;
  }

  public static final int BLACKPLAYERINFO_FIELD_NUMBER = 2;
  private java.util.List<pb4client.FriendInfo> blackPlayerInfo_;
  /**
   * <code>repeated .client2server.FriendInfo blackPlayerInfo = 2;</code>
   */
  public java.util.List<pb4client.FriendInfo> getBlackPlayerInfoList() {
    return blackPlayerInfo_;
  }
  /**
   * <code>repeated .client2server.FriendInfo blackPlayerInfo = 2;</code>
   */
  public java.util.List<? extends pb4client.FriendInfoOrBuilder> 
      getBlackPlayerInfoOrBuilderList() {
    return blackPlayerInfo_;
  }
  /**
   * <code>repeated .client2server.FriendInfo blackPlayerInfo = 2;</code>
   */
  public int getBlackPlayerInfoCount() {
    return blackPlayerInfo_.size();
  }
  /**
   * <code>repeated .client2server.FriendInfo blackPlayerInfo = 2;</code>
   */
  public pb4client.FriendInfo getBlackPlayerInfo(int index) {
    return blackPlayerInfo_.get(index);
  }
  /**
   * <code>repeated .client2server.FriendInfo blackPlayerInfo = 2;</code>
   */
  public pb4client.FriendInfoOrBuilder getBlackPlayerInfoOrBuilder(
      int index) {
    return blackPlayerInfo_.get(index);
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
    for (int i = 0; i < getBlackPlayerInfoCount(); i++) {
      if (!getBlackPlayerInfo(i).isInitialized()) {
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
    for (int i = 0; i < blackPlayerInfo_.size(); i++) {
      output.writeMessage(2, blackPlayerInfo_.get(i));
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
    for (int i = 0; i < blackPlayerInfo_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, blackPlayerInfo_.get(i));
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
    if (!(obj instanceof pb4client.QueryBlackListRt)) {
      return super.equals(obj);
    }
    pb4client.QueryBlackListRt other = (pb4client.QueryBlackListRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && getBlackPlayerInfoList()
        .equals(other.getBlackPlayerInfoList());
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
    if (getBlackPlayerInfoCount() > 0) {
      hash = (37 * hash) + BLACKPLAYERINFO_FIELD_NUMBER;
      hash = (53 * hash) + getBlackPlayerInfoList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.QueryBlackListRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.QueryBlackListRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.QueryBlackListRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.QueryBlackListRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.QueryBlackListRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.QueryBlackListRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.QueryBlackListRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.QueryBlackListRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.QueryBlackListRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.QueryBlackListRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.QueryBlackListRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.QueryBlackListRt parseFrom(
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
  public static Builder newBuilder(pb4client.QueryBlackListRt prototype) {
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
   * Protobuf type {@code client2server.QueryBlackListRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.QueryBlackListRt)
      pb4client.QueryBlackListRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_QueryBlackListRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_QueryBlackListRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.QueryBlackListRt.class, pb4client.QueryBlackListRt.Builder.class);
    }

    // Construct using pb4client.QueryBlackListRt.newBuilder()
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
        getBlackPlayerInfoFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (blackPlayerInfoBuilder_ == null) {
        blackPlayerInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        blackPlayerInfoBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_QueryBlackListRt_descriptor;
    }

    public pb4client.QueryBlackListRt getDefaultInstanceForType() {
      return pb4client.QueryBlackListRt.getDefaultInstance();
    }

    public pb4client.QueryBlackListRt build() {
      pb4client.QueryBlackListRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.QueryBlackListRt buildPartial() {
      pb4client.QueryBlackListRt result = new pb4client.QueryBlackListRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (blackPlayerInfoBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          blackPlayerInfo_ = java.util.Collections.unmodifiableList(blackPlayerInfo_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.blackPlayerInfo_ = blackPlayerInfo_;
      } else {
        result.blackPlayerInfo_ = blackPlayerInfoBuilder_.build();
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
      if (other instanceof pb4client.QueryBlackListRt) {
        return mergeFrom((pb4client.QueryBlackListRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.QueryBlackListRt other) {
      if (other == pb4client.QueryBlackListRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (blackPlayerInfoBuilder_ == null) {
        if (!other.blackPlayerInfo_.isEmpty()) {
          if (blackPlayerInfo_.isEmpty()) {
            blackPlayerInfo_ = other.blackPlayerInfo_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureBlackPlayerInfoIsMutable();
            blackPlayerInfo_.addAll(other.blackPlayerInfo_);
          }
          onChanged();
        }
      } else {
        if (!other.blackPlayerInfo_.isEmpty()) {
          if (blackPlayerInfoBuilder_.isEmpty()) {
            blackPlayerInfoBuilder_.dispose();
            blackPlayerInfoBuilder_ = null;
            blackPlayerInfo_ = other.blackPlayerInfo_;
            bitField0_ = (bitField0_ & ~0x00000002);
            blackPlayerInfoBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getBlackPlayerInfoFieldBuilder() : null;
          } else {
            blackPlayerInfoBuilder_.addAllMessages(other.blackPlayerInfo_);
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
      for (int i = 0; i < getBlackPlayerInfoCount(); i++) {
        if (!getBlackPlayerInfo(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.QueryBlackListRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.QueryBlackListRt) e.getUnfinishedMessage();
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
     * <code>required int32 rt = 1;</code>
     */
    public boolean hasRt() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 rt = 1;</code>
     */
    public int getRt() {
      return rt_;
    }
    /**
     * <code>required int32 rt = 1;</code>
     */
    public Builder setRt(int value) {
      bitField0_ |= 0x00000001;
      rt_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 rt = 1;</code>
     */
    public Builder clearRt() {
      bitField0_ = (bitField0_ & ~0x00000001);
      rt_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<pb4client.FriendInfo> blackPlayerInfo_ =
      java.util.Collections.emptyList();
    private void ensureBlackPlayerInfoIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        blackPlayerInfo_ = new java.util.ArrayList<pb4client.FriendInfo>(blackPlayerInfo_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.FriendInfo, pb4client.FriendInfo.Builder, pb4client.FriendInfoOrBuilder> blackPlayerInfoBuilder_;

    /**
     * <code>repeated .client2server.FriendInfo blackPlayerInfo = 2;</code>
     */
    public java.util.List<pb4client.FriendInfo> getBlackPlayerInfoList() {
      if (blackPlayerInfoBuilder_ == null) {
        return java.util.Collections.unmodifiableList(blackPlayerInfo_);
      } else {
        return blackPlayerInfoBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .client2server.FriendInfo blackPlayerInfo = 2;</code>
     */
    public int getBlackPlayerInfoCount() {
      if (blackPlayerInfoBuilder_ == null) {
        return blackPlayerInfo_.size();
      } else {
        return blackPlayerInfoBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .client2server.FriendInfo blackPlayerInfo = 2;</code>
     */
    public pb4client.FriendInfo getBlackPlayerInfo(int index) {
      if (blackPlayerInfoBuilder_ == null) {
        return blackPlayerInfo_.get(index);
      } else {
        return blackPlayerInfoBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .client2server.FriendInfo blackPlayerInfo = 2;</code>
     */
    public Builder setBlackPlayerInfo(
        int index, pb4client.FriendInfo value) {
      if (blackPlayerInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureBlackPlayerInfoIsMutable();
        blackPlayerInfo_.set(index, value);
        onChanged();
      } else {
        blackPlayerInfoBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.FriendInfo blackPlayerInfo = 2;</code>
     */
    public Builder setBlackPlayerInfo(
        int index, pb4client.FriendInfo.Builder builderForValue) {
      if (blackPlayerInfoBuilder_ == null) {
        ensureBlackPlayerInfoIsMutable();
        blackPlayerInfo_.set(index, builderForValue.build());
        onChanged();
      } else {
        blackPlayerInfoBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.FriendInfo blackPlayerInfo = 2;</code>
     */
    public Builder addBlackPlayerInfo(pb4client.FriendInfo value) {
      if (blackPlayerInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureBlackPlayerInfoIsMutable();
        blackPlayerInfo_.add(value);
        onChanged();
      } else {
        blackPlayerInfoBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.FriendInfo blackPlayerInfo = 2;</code>
     */
    public Builder addBlackPlayerInfo(
        int index, pb4client.FriendInfo value) {
      if (blackPlayerInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureBlackPlayerInfoIsMutable();
        blackPlayerInfo_.add(index, value);
        onChanged();
      } else {
        blackPlayerInfoBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.FriendInfo blackPlayerInfo = 2;</code>
     */
    public Builder addBlackPlayerInfo(
        pb4client.FriendInfo.Builder builderForValue) {
      if (blackPlayerInfoBuilder_ == null) {
        ensureBlackPlayerInfoIsMutable();
        blackPlayerInfo_.add(builderForValue.build());
        onChanged();
      } else {
        blackPlayerInfoBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.FriendInfo blackPlayerInfo = 2;</code>
     */
    public Builder addBlackPlayerInfo(
        int index, pb4client.FriendInfo.Builder builderForValue) {
      if (blackPlayerInfoBuilder_ == null) {
        ensureBlackPlayerInfoIsMutable();
        blackPlayerInfo_.add(index, builderForValue.build());
        onChanged();
      } else {
        blackPlayerInfoBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.FriendInfo blackPlayerInfo = 2;</code>
     */
    public Builder addAllBlackPlayerInfo(
        java.lang.Iterable<? extends pb4client.FriendInfo> values) {
      if (blackPlayerInfoBuilder_ == null) {
        ensureBlackPlayerInfoIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, blackPlayerInfo_);
        onChanged();
      } else {
        blackPlayerInfoBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.FriendInfo blackPlayerInfo = 2;</code>
     */
    public Builder clearBlackPlayerInfo() {
      if (blackPlayerInfoBuilder_ == null) {
        blackPlayerInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        blackPlayerInfoBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .client2server.FriendInfo blackPlayerInfo = 2;</code>
     */
    public Builder removeBlackPlayerInfo(int index) {
      if (blackPlayerInfoBuilder_ == null) {
        ensureBlackPlayerInfoIsMutable();
        blackPlayerInfo_.remove(index);
        onChanged();
      } else {
        blackPlayerInfoBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.FriendInfo blackPlayerInfo = 2;</code>
     */
    public pb4client.FriendInfo.Builder getBlackPlayerInfoBuilder(
        int index) {
      return getBlackPlayerInfoFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .client2server.FriendInfo blackPlayerInfo = 2;</code>
     */
    public pb4client.FriendInfoOrBuilder getBlackPlayerInfoOrBuilder(
        int index) {
      if (blackPlayerInfoBuilder_ == null) {
        return blackPlayerInfo_.get(index);  } else {
        return blackPlayerInfoBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .client2server.FriendInfo blackPlayerInfo = 2;</code>
     */
    public java.util.List<? extends pb4client.FriendInfoOrBuilder> 
         getBlackPlayerInfoOrBuilderList() {
      if (blackPlayerInfoBuilder_ != null) {
        return blackPlayerInfoBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(blackPlayerInfo_);
      }
    }
    /**
     * <code>repeated .client2server.FriendInfo blackPlayerInfo = 2;</code>
     */
    public pb4client.FriendInfo.Builder addBlackPlayerInfoBuilder() {
      return getBlackPlayerInfoFieldBuilder().addBuilder(
          pb4client.FriendInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.FriendInfo blackPlayerInfo = 2;</code>
     */
    public pb4client.FriendInfo.Builder addBlackPlayerInfoBuilder(
        int index) {
      return getBlackPlayerInfoFieldBuilder().addBuilder(
          index, pb4client.FriendInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.FriendInfo blackPlayerInfo = 2;</code>
     */
    public java.util.List<pb4client.FriendInfo.Builder> 
         getBlackPlayerInfoBuilderList() {
      return getBlackPlayerInfoFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.FriendInfo, pb4client.FriendInfo.Builder, pb4client.FriendInfoOrBuilder> 
        getBlackPlayerInfoFieldBuilder() {
      if (blackPlayerInfoBuilder_ == null) {
        blackPlayerInfoBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.FriendInfo, pb4client.FriendInfo.Builder, pb4client.FriendInfoOrBuilder>(
                blackPlayerInfo_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        blackPlayerInfo_ = null;
      }
      return blackPlayerInfoBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.QueryBlackListRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.QueryBlackListRt)
  private static final pb4client.QueryBlackListRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.QueryBlackListRt();
  }

  public static pb4client.QueryBlackListRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<QueryBlackListRt>
      PARSER = new com.google.protobuf.AbstractParser<QueryBlackListRt>() {
    public QueryBlackListRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new QueryBlackListRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<QueryBlackListRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QueryBlackListRt> getParserForType() {
    return PARSER;
  }

  public pb4client.QueryBlackListRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

