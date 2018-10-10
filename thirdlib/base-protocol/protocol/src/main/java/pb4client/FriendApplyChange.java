// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3172
 * 服务器 -&gt; 客户端
 * 玩家的好友添加信息发生变化
 * </pre>
 *
 * Protobuf type {@code client2server.FriendApplyChange}
 */
public  final class FriendApplyChange extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.FriendApplyChange)
    FriendApplyChangeOrBuilder {
  // Use FriendApplyChange.newBuilder() to construct.
  private FriendApplyChange(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private FriendApplyChange() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private FriendApplyChange(
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
            pb4client.FriendApply.Builder subBuilder = null;
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
              subBuilder = friendApply_.toBuilder();
            }
            friendApply_ = input.readMessage(pb4client.FriendApply.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(friendApply_);
              friendApply_ = subBuilder.buildPartial();
            }
            bitField0_ |= 0x00000001;
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
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_FriendApplyChange_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_FriendApplyChange_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.FriendApplyChange.class, pb4client.FriendApplyChange.Builder.class);
  }

  private int bitField0_;
  public static final int FRIENDAPPLY_FIELD_NUMBER = 1;
  private pb4client.FriendApply friendApply_;
  /**
   * <pre>
   * 加我为好友的人信息
   * </pre>
   *
   * <code>required .client2server.FriendApply friendApply = 1;</code>
   */
  public boolean hasFriendApply() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 加我为好友的人信息
   * </pre>
   *
   * <code>required .client2server.FriendApply friendApply = 1;</code>
   */
  public pb4client.FriendApply getFriendApply() {
    return friendApply_ == null ? pb4client.FriendApply.getDefaultInstance() : friendApply_;
  }
  /**
   * <pre>
   * 加我为好友的人信息
   * </pre>
   *
   * <code>required .client2server.FriendApply friendApply = 1;</code>
   */
  public pb4client.FriendApplyOrBuilder getFriendApplyOrBuilder() {
    return friendApply_ == null ? pb4client.FriendApply.getDefaultInstance() : friendApply_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasFriendApply()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!getFriendApply().isInitialized()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeMessage(1, getFriendApply());
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getFriendApply());
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
    if (!(obj instanceof pb4client.FriendApplyChange)) {
      return super.equals(obj);
    }
    pb4client.FriendApplyChange other = (pb4client.FriendApplyChange) obj;

    boolean result = true;
    result = result && (hasFriendApply() == other.hasFriendApply());
    if (hasFriendApply()) {
      result = result && getFriendApply()
          .equals(other.getFriendApply());
    }
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
    if (hasFriendApply()) {
      hash = (37 * hash) + FRIENDAPPLY_FIELD_NUMBER;
      hash = (53 * hash) + getFriendApply().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.FriendApplyChange parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.FriendApplyChange parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.FriendApplyChange parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.FriendApplyChange parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.FriendApplyChange parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.FriendApplyChange parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.FriendApplyChange parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.FriendApplyChange parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.FriendApplyChange parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.FriendApplyChange parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.FriendApplyChange parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.FriendApplyChange parseFrom(
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
  public static Builder newBuilder(pb4client.FriendApplyChange prototype) {
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
   * msgType = 3172
   * 服务器 -&gt; 客户端
   * 玩家的好友添加信息发生变化
   * </pre>
   *
   * Protobuf type {@code client2server.FriendApplyChange}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.FriendApplyChange)
      pb4client.FriendApplyChangeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_FriendApplyChange_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_FriendApplyChange_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.FriendApplyChange.class, pb4client.FriendApplyChange.Builder.class);
    }

    // Construct using pb4client.FriendApplyChange.newBuilder()
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
        getFriendApplyFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (friendApplyBuilder_ == null) {
        friendApply_ = null;
      } else {
        friendApplyBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_FriendApplyChange_descriptor;
    }

    public pb4client.FriendApplyChange getDefaultInstanceForType() {
      return pb4client.FriendApplyChange.getDefaultInstance();
    }

    public pb4client.FriendApplyChange build() {
      pb4client.FriendApplyChange result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.FriendApplyChange buildPartial() {
      pb4client.FriendApplyChange result = new pb4client.FriendApplyChange(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      if (friendApplyBuilder_ == null) {
        result.friendApply_ = friendApply_;
      } else {
        result.friendApply_ = friendApplyBuilder_.build();
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
      if (other instanceof pb4client.FriendApplyChange) {
        return mergeFrom((pb4client.FriendApplyChange)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.FriendApplyChange other) {
      if (other == pb4client.FriendApplyChange.getDefaultInstance()) return this;
      if (other.hasFriendApply()) {
        mergeFriendApply(other.getFriendApply());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasFriendApply()) {
        return false;
      }
      if (!getFriendApply().isInitialized()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.FriendApplyChange parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.FriendApplyChange) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private pb4client.FriendApply friendApply_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.FriendApply, pb4client.FriendApply.Builder, pb4client.FriendApplyOrBuilder> friendApplyBuilder_;
    /**
     * <pre>
     * 加我为好友的人信息
     * </pre>
     *
     * <code>required .client2server.FriendApply friendApply = 1;</code>
     */
    public boolean hasFriendApply() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 加我为好友的人信息
     * </pre>
     *
     * <code>required .client2server.FriendApply friendApply = 1;</code>
     */
    public pb4client.FriendApply getFriendApply() {
      if (friendApplyBuilder_ == null) {
        return friendApply_ == null ? pb4client.FriendApply.getDefaultInstance() : friendApply_;
      } else {
        return friendApplyBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     * 加我为好友的人信息
     * </pre>
     *
     * <code>required .client2server.FriendApply friendApply = 1;</code>
     */
    public Builder setFriendApply(pb4client.FriendApply value) {
      if (friendApplyBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        friendApply_ = value;
        onChanged();
      } else {
        friendApplyBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <pre>
     * 加我为好友的人信息
     * </pre>
     *
     * <code>required .client2server.FriendApply friendApply = 1;</code>
     */
    public Builder setFriendApply(
        pb4client.FriendApply.Builder builderForValue) {
      if (friendApplyBuilder_ == null) {
        friendApply_ = builderForValue.build();
        onChanged();
      } else {
        friendApplyBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <pre>
     * 加我为好友的人信息
     * </pre>
     *
     * <code>required .client2server.FriendApply friendApply = 1;</code>
     */
    public Builder mergeFriendApply(pb4client.FriendApply value) {
      if (friendApplyBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001) &&
            friendApply_ != null &&
            friendApply_ != pb4client.FriendApply.getDefaultInstance()) {
          friendApply_ =
            pb4client.FriendApply.newBuilder(friendApply_).mergeFrom(value).buildPartial();
        } else {
          friendApply_ = value;
        }
        onChanged();
      } else {
        friendApplyBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <pre>
     * 加我为好友的人信息
     * </pre>
     *
     * <code>required .client2server.FriendApply friendApply = 1;</code>
     */
    public Builder clearFriendApply() {
      if (friendApplyBuilder_ == null) {
        friendApply_ = null;
        onChanged();
      } else {
        friendApplyBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }
    /**
     * <pre>
     * 加我为好友的人信息
     * </pre>
     *
     * <code>required .client2server.FriendApply friendApply = 1;</code>
     */
    public pb4client.FriendApply.Builder getFriendApplyBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getFriendApplyFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     * 加我为好友的人信息
     * </pre>
     *
     * <code>required .client2server.FriendApply friendApply = 1;</code>
     */
    public pb4client.FriendApplyOrBuilder getFriendApplyOrBuilder() {
      if (friendApplyBuilder_ != null) {
        return friendApplyBuilder_.getMessageOrBuilder();
      } else {
        return friendApply_ == null ?
            pb4client.FriendApply.getDefaultInstance() : friendApply_;
      }
    }
    /**
     * <pre>
     * 加我为好友的人信息
     * </pre>
     *
     * <code>required .client2server.FriendApply friendApply = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.FriendApply, pb4client.FriendApply.Builder, pb4client.FriendApplyOrBuilder> 
        getFriendApplyFieldBuilder() {
      if (friendApplyBuilder_ == null) {
        friendApplyBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4client.FriendApply, pb4client.FriendApply.Builder, pb4client.FriendApplyOrBuilder>(
                getFriendApply(),
                getParentForChildren(),
                isClean());
        friendApply_ = null;
      }
      return friendApplyBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.FriendApplyChange)
  }

  // @@protoc_insertion_point(class_scope:client2server.FriendApplyChange)
  private static final pb4client.FriendApplyChange DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.FriendApplyChange();
  }

  public static pb4client.FriendApplyChange getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<FriendApplyChange>
      PARSER = new com.google.protobuf.AbstractParser<FriendApplyChange>() {
    public FriendApplyChange parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new FriendApplyChange(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<FriendApplyChange> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<FriendApplyChange> getParserForType() {
    return PARSER;
  }

  public pb4client.FriendApplyChange getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
