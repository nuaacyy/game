// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.AskStrangerInfoAskRt}
 */
public  final class AskStrangerInfoAskRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.AskStrangerInfoAskRt)
    AskStrangerInfoAskRtOrBuilder {
  // Use AskStrangerInfoAskRt.newBuilder() to construct.
  private AskStrangerInfoAskRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AskStrangerInfoAskRt() {
    rt_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private AskStrangerInfoAskRt(
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
          case 8: {

            rt_ = input.readInt32();
            break;
          }
          case 18: {
            pb4server.FriendInfoVo.Builder subBuilder = null;
            if (targetPlayer_ != null) {
              subBuilder = targetPlayer_.toBuilder();
            }
            targetPlayer_ = input.readMessage(pb4server.FriendInfoVo.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(targetPlayer_);
              targetPlayer_ = subBuilder.buildPartial();
            }

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
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalHkt.internal_static_pb4server_AskStrangerInfoAskRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalHkt.internal_static_pb4server_AskStrangerInfoAskRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.AskStrangerInfoAskRt.class, pb4server.AskStrangerInfoAskRt.Builder.class);
  }

  public static final int RT_FIELD_NUMBER = 1;
  private int rt_;
  /**
   * <code>int32 rt = 1;</code>
   */
  public int getRt() {
    return rt_;
  }

  public static final int TARGETPLAYER_FIELD_NUMBER = 2;
  private pb4server.FriendInfoVo targetPlayer_;
  /**
   * <code>.pb4server.FriendInfoVo targetPlayer = 2;</code>
   */
  public boolean hasTargetPlayer() {
    return targetPlayer_ != null;
  }
  /**
   * <code>.pb4server.FriendInfoVo targetPlayer = 2;</code>
   */
  public pb4server.FriendInfoVo getTargetPlayer() {
    return targetPlayer_ == null ? pb4server.FriendInfoVo.getDefaultInstance() : targetPlayer_;
  }
  /**
   * <code>.pb4server.FriendInfoVo targetPlayer = 2;</code>
   */
  public pb4server.FriendInfoVoOrBuilder getTargetPlayerOrBuilder() {
    return getTargetPlayer();
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
    if (rt_ != 0) {
      output.writeInt32(1, rt_);
    }
    if (targetPlayer_ != null) {
      output.writeMessage(2, getTargetPlayer());
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (rt_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, rt_);
    }
    if (targetPlayer_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getTargetPlayer());
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
    if (!(obj instanceof pb4server.AskStrangerInfoAskRt)) {
      return super.equals(obj);
    }
    pb4server.AskStrangerInfoAskRt other = (pb4server.AskStrangerInfoAskRt) obj;

    boolean result = true;
    result = result && (getRt()
        == other.getRt());
    result = result && (hasTargetPlayer() == other.hasTargetPlayer());
    if (hasTargetPlayer()) {
      result = result && getTargetPlayer()
          .equals(other.getTargetPlayer());
    }
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + RT_FIELD_NUMBER;
    hash = (53 * hash) + getRt();
    if (hasTargetPlayer()) {
      hash = (37 * hash) + TARGETPLAYER_FIELD_NUMBER;
      hash = (53 * hash) + getTargetPlayer().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.AskStrangerInfoAskRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AskStrangerInfoAskRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AskStrangerInfoAskRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AskStrangerInfoAskRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AskStrangerInfoAskRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AskStrangerInfoAskRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AskStrangerInfoAskRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AskStrangerInfoAskRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AskStrangerInfoAskRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.AskStrangerInfoAskRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AskStrangerInfoAskRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AskStrangerInfoAskRt parseFrom(
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
  public static Builder newBuilder(pb4server.AskStrangerInfoAskRt prototype) {
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
   * Protobuf type {@code pb4server.AskStrangerInfoAskRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.AskStrangerInfoAskRt)
      pb4server.AskStrangerInfoAskRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalHkt.internal_static_pb4server_AskStrangerInfoAskRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalHkt.internal_static_pb4server_AskStrangerInfoAskRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.AskStrangerInfoAskRt.class, pb4server.AskStrangerInfoAskRt.Builder.class);
    }

    // Construct using pb4server.AskStrangerInfoAskRt.newBuilder()
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
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;

      if (targetPlayerBuilder_ == null) {
        targetPlayer_ = null;
      } else {
        targetPlayer_ = null;
        targetPlayerBuilder_ = null;
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalHkt.internal_static_pb4server_AskStrangerInfoAskRt_descriptor;
    }

    public pb4server.AskStrangerInfoAskRt getDefaultInstanceForType() {
      return pb4server.AskStrangerInfoAskRt.getDefaultInstance();
    }

    public pb4server.AskStrangerInfoAskRt build() {
      pb4server.AskStrangerInfoAskRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.AskStrangerInfoAskRt buildPartial() {
      pb4server.AskStrangerInfoAskRt result = new pb4server.AskStrangerInfoAskRt(this);
      result.rt_ = rt_;
      if (targetPlayerBuilder_ == null) {
        result.targetPlayer_ = targetPlayer_;
      } else {
        result.targetPlayer_ = targetPlayerBuilder_.build();
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
      if (other instanceof pb4server.AskStrangerInfoAskRt) {
        return mergeFrom((pb4server.AskStrangerInfoAskRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.AskStrangerInfoAskRt other) {
      if (other == pb4server.AskStrangerInfoAskRt.getDefaultInstance()) return this;
      if (other.getRt() != 0) {
        setRt(other.getRt());
      }
      if (other.hasTargetPlayer()) {
        mergeTargetPlayer(other.getTargetPlayer());
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
      pb4server.AskStrangerInfoAskRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.AskStrangerInfoAskRt) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int rt_ ;
    /**
     * <code>int32 rt = 1;</code>
     */
    public int getRt() {
      return rt_;
    }
    /**
     * <code>int32 rt = 1;</code>
     */
    public Builder setRt(int value) {
      
      rt_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 rt = 1;</code>
     */
    public Builder clearRt() {
      
      rt_ = 0;
      onChanged();
      return this;
    }

    private pb4server.FriendInfoVo targetPlayer_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4server.FriendInfoVo, pb4server.FriendInfoVo.Builder, pb4server.FriendInfoVoOrBuilder> targetPlayerBuilder_;
    /**
     * <code>.pb4server.FriendInfoVo targetPlayer = 2;</code>
     */
    public boolean hasTargetPlayer() {
      return targetPlayerBuilder_ != null || targetPlayer_ != null;
    }
    /**
     * <code>.pb4server.FriendInfoVo targetPlayer = 2;</code>
     */
    public pb4server.FriendInfoVo getTargetPlayer() {
      if (targetPlayerBuilder_ == null) {
        return targetPlayer_ == null ? pb4server.FriendInfoVo.getDefaultInstance() : targetPlayer_;
      } else {
        return targetPlayerBuilder_.getMessage();
      }
    }
    /**
     * <code>.pb4server.FriendInfoVo targetPlayer = 2;</code>
     */
    public Builder setTargetPlayer(pb4server.FriendInfoVo value) {
      if (targetPlayerBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        targetPlayer_ = value;
        onChanged();
      } else {
        targetPlayerBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.pb4server.FriendInfoVo targetPlayer = 2;</code>
     */
    public Builder setTargetPlayer(
        pb4server.FriendInfoVo.Builder builderForValue) {
      if (targetPlayerBuilder_ == null) {
        targetPlayer_ = builderForValue.build();
        onChanged();
      } else {
        targetPlayerBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.pb4server.FriendInfoVo targetPlayer = 2;</code>
     */
    public Builder mergeTargetPlayer(pb4server.FriendInfoVo value) {
      if (targetPlayerBuilder_ == null) {
        if (targetPlayer_ != null) {
          targetPlayer_ =
            pb4server.FriendInfoVo.newBuilder(targetPlayer_).mergeFrom(value).buildPartial();
        } else {
          targetPlayer_ = value;
        }
        onChanged();
      } else {
        targetPlayerBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.pb4server.FriendInfoVo targetPlayer = 2;</code>
     */
    public Builder clearTargetPlayer() {
      if (targetPlayerBuilder_ == null) {
        targetPlayer_ = null;
        onChanged();
      } else {
        targetPlayer_ = null;
        targetPlayerBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.pb4server.FriendInfoVo targetPlayer = 2;</code>
     */
    public pb4server.FriendInfoVo.Builder getTargetPlayerBuilder() {
      
      onChanged();
      return getTargetPlayerFieldBuilder().getBuilder();
    }
    /**
     * <code>.pb4server.FriendInfoVo targetPlayer = 2;</code>
     */
    public pb4server.FriendInfoVoOrBuilder getTargetPlayerOrBuilder() {
      if (targetPlayerBuilder_ != null) {
        return targetPlayerBuilder_.getMessageOrBuilder();
      } else {
        return targetPlayer_ == null ?
            pb4server.FriendInfoVo.getDefaultInstance() : targetPlayer_;
      }
    }
    /**
     * <code>.pb4server.FriendInfoVo targetPlayer = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4server.FriendInfoVo, pb4server.FriendInfoVo.Builder, pb4server.FriendInfoVoOrBuilder> 
        getTargetPlayerFieldBuilder() {
      if (targetPlayerBuilder_ == null) {
        targetPlayerBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4server.FriendInfoVo, pb4server.FriendInfoVo.Builder, pb4server.FriendInfoVoOrBuilder>(
                getTargetPlayer(),
                getParentForChildren(),
                isClean());
        targetPlayer_ = null;
      }
      return targetPlayerBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.AskStrangerInfoAskRt)
  }

  // @@protoc_insertion_point(class_scope:pb4server.AskStrangerInfoAskRt)
  private static final pb4server.AskStrangerInfoAskRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.AskStrangerInfoAskRt();
  }

  public static pb4server.AskStrangerInfoAskRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AskStrangerInfoAskRt>
      PARSER = new com.google.protobuf.AbstractParser<AskStrangerInfoAskRt>() {
    public AskStrangerInfoAskRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AskStrangerInfoAskRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AskStrangerInfoAskRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AskStrangerInfoAskRt> getParserForType() {
    return PARSER;
  }

  public pb4server.AskStrangerInfoAskRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
