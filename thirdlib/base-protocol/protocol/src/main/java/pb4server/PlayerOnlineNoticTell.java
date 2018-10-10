// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

/**
 * <pre>
 * 玩家上线所需的公共服数据
 * </pre>
 *
 * Protobuf type {@code pb4server.PlayerOnlineNoticTell}
 */
public  final class PlayerOnlineNoticTell extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.PlayerOnlineNoticTell)
    PlayerOnlineNoticTellOrBuilder {
  // Use PlayerOnlineNoticTell.newBuilder() to construct.
  private PlayerOnlineNoticTell(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private PlayerOnlineNoticTell() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private PlayerOnlineNoticTell(
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
            pb4server.EnterGamePublicRtVo.Builder subBuilder = null;
            if (enterGamePublicRt_ != null) {
              subBuilder = enterGamePublicRt_.toBuilder();
            }
            enterGamePublicRt_ = input.readMessage(pb4server.EnterGamePublicRtVo.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(enterGamePublicRt_);
              enterGamePublicRt_ = subBuilder.buildPartial();
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
    return pb4server.InternalWkt.internal_static_pb4server_PlayerOnlineNoticTell_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalWkt.internal_static_pb4server_PlayerOnlineNoticTell_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.PlayerOnlineNoticTell.class, pb4server.PlayerOnlineNoticTell.Builder.class);
  }

  public static final int ENTERGAMEPUBLICRT_FIELD_NUMBER = 1;
  private pb4server.EnterGamePublicRtVo enterGamePublicRt_;
  /**
   * <code>.pb4server.EnterGamePublicRtVo enterGamePublicRt = 1;</code>
   */
  public boolean hasEnterGamePublicRt() {
    return enterGamePublicRt_ != null;
  }
  /**
   * <code>.pb4server.EnterGamePublicRtVo enterGamePublicRt = 1;</code>
   */
  public pb4server.EnterGamePublicRtVo getEnterGamePublicRt() {
    return enterGamePublicRt_ == null ? pb4server.EnterGamePublicRtVo.getDefaultInstance() : enterGamePublicRt_;
  }
  /**
   * <code>.pb4server.EnterGamePublicRtVo enterGamePublicRt = 1;</code>
   */
  public pb4server.EnterGamePublicRtVoOrBuilder getEnterGamePublicRtOrBuilder() {
    return getEnterGamePublicRt();
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
    if (enterGamePublicRt_ != null) {
      output.writeMessage(1, getEnterGamePublicRt());
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (enterGamePublicRt_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getEnterGamePublicRt());
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
    if (!(obj instanceof pb4server.PlayerOnlineNoticTell)) {
      return super.equals(obj);
    }
    pb4server.PlayerOnlineNoticTell other = (pb4server.PlayerOnlineNoticTell) obj;

    boolean result = true;
    result = result && (hasEnterGamePublicRt() == other.hasEnterGamePublicRt());
    if (hasEnterGamePublicRt()) {
      result = result && getEnterGamePublicRt()
          .equals(other.getEnterGamePublicRt());
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
    if (hasEnterGamePublicRt()) {
      hash = (37 * hash) + ENTERGAMEPUBLICRT_FIELD_NUMBER;
      hash = (53 * hash) + getEnterGamePublicRt().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.PlayerOnlineNoticTell parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.PlayerOnlineNoticTell parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.PlayerOnlineNoticTell parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.PlayerOnlineNoticTell parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.PlayerOnlineNoticTell parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.PlayerOnlineNoticTell parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.PlayerOnlineNoticTell parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.PlayerOnlineNoticTell parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.PlayerOnlineNoticTell parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.PlayerOnlineNoticTell parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.PlayerOnlineNoticTell parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.PlayerOnlineNoticTell parseFrom(
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
  public static Builder newBuilder(pb4server.PlayerOnlineNoticTell prototype) {
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
   * 玩家上线所需的公共服数据
   * </pre>
   *
   * Protobuf type {@code pb4server.PlayerOnlineNoticTell}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.PlayerOnlineNoticTell)
      pb4server.PlayerOnlineNoticTellOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalWkt.internal_static_pb4server_PlayerOnlineNoticTell_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalWkt.internal_static_pb4server_PlayerOnlineNoticTell_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.PlayerOnlineNoticTell.class, pb4server.PlayerOnlineNoticTell.Builder.class);
    }

    // Construct using pb4server.PlayerOnlineNoticTell.newBuilder()
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
      if (enterGamePublicRtBuilder_ == null) {
        enterGamePublicRt_ = null;
      } else {
        enterGamePublicRt_ = null;
        enterGamePublicRtBuilder_ = null;
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalWkt.internal_static_pb4server_PlayerOnlineNoticTell_descriptor;
    }

    public pb4server.PlayerOnlineNoticTell getDefaultInstanceForType() {
      return pb4server.PlayerOnlineNoticTell.getDefaultInstance();
    }

    public pb4server.PlayerOnlineNoticTell build() {
      pb4server.PlayerOnlineNoticTell result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.PlayerOnlineNoticTell buildPartial() {
      pb4server.PlayerOnlineNoticTell result = new pb4server.PlayerOnlineNoticTell(this);
      if (enterGamePublicRtBuilder_ == null) {
        result.enterGamePublicRt_ = enterGamePublicRt_;
      } else {
        result.enterGamePublicRt_ = enterGamePublicRtBuilder_.build();
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
      if (other instanceof pb4server.PlayerOnlineNoticTell) {
        return mergeFrom((pb4server.PlayerOnlineNoticTell)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.PlayerOnlineNoticTell other) {
      if (other == pb4server.PlayerOnlineNoticTell.getDefaultInstance()) return this;
      if (other.hasEnterGamePublicRt()) {
        mergeEnterGamePublicRt(other.getEnterGamePublicRt());
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
      pb4server.PlayerOnlineNoticTell parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.PlayerOnlineNoticTell) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private pb4server.EnterGamePublicRtVo enterGamePublicRt_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4server.EnterGamePublicRtVo, pb4server.EnterGamePublicRtVo.Builder, pb4server.EnterGamePublicRtVoOrBuilder> enterGamePublicRtBuilder_;
    /**
     * <code>.pb4server.EnterGamePublicRtVo enterGamePublicRt = 1;</code>
     */
    public boolean hasEnterGamePublicRt() {
      return enterGamePublicRtBuilder_ != null || enterGamePublicRt_ != null;
    }
    /**
     * <code>.pb4server.EnterGamePublicRtVo enterGamePublicRt = 1;</code>
     */
    public pb4server.EnterGamePublicRtVo getEnterGamePublicRt() {
      if (enterGamePublicRtBuilder_ == null) {
        return enterGamePublicRt_ == null ? pb4server.EnterGamePublicRtVo.getDefaultInstance() : enterGamePublicRt_;
      } else {
        return enterGamePublicRtBuilder_.getMessage();
      }
    }
    /**
     * <code>.pb4server.EnterGamePublicRtVo enterGamePublicRt = 1;</code>
     */
    public Builder setEnterGamePublicRt(pb4server.EnterGamePublicRtVo value) {
      if (enterGamePublicRtBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        enterGamePublicRt_ = value;
        onChanged();
      } else {
        enterGamePublicRtBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.pb4server.EnterGamePublicRtVo enterGamePublicRt = 1;</code>
     */
    public Builder setEnterGamePublicRt(
        pb4server.EnterGamePublicRtVo.Builder builderForValue) {
      if (enterGamePublicRtBuilder_ == null) {
        enterGamePublicRt_ = builderForValue.build();
        onChanged();
      } else {
        enterGamePublicRtBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.pb4server.EnterGamePublicRtVo enterGamePublicRt = 1;</code>
     */
    public Builder mergeEnterGamePublicRt(pb4server.EnterGamePublicRtVo value) {
      if (enterGamePublicRtBuilder_ == null) {
        if (enterGamePublicRt_ != null) {
          enterGamePublicRt_ =
            pb4server.EnterGamePublicRtVo.newBuilder(enterGamePublicRt_).mergeFrom(value).buildPartial();
        } else {
          enterGamePublicRt_ = value;
        }
        onChanged();
      } else {
        enterGamePublicRtBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.pb4server.EnterGamePublicRtVo enterGamePublicRt = 1;</code>
     */
    public Builder clearEnterGamePublicRt() {
      if (enterGamePublicRtBuilder_ == null) {
        enterGamePublicRt_ = null;
        onChanged();
      } else {
        enterGamePublicRt_ = null;
        enterGamePublicRtBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.pb4server.EnterGamePublicRtVo enterGamePublicRt = 1;</code>
     */
    public pb4server.EnterGamePublicRtVo.Builder getEnterGamePublicRtBuilder() {
      
      onChanged();
      return getEnterGamePublicRtFieldBuilder().getBuilder();
    }
    /**
     * <code>.pb4server.EnterGamePublicRtVo enterGamePublicRt = 1;</code>
     */
    public pb4server.EnterGamePublicRtVoOrBuilder getEnterGamePublicRtOrBuilder() {
      if (enterGamePublicRtBuilder_ != null) {
        return enterGamePublicRtBuilder_.getMessageOrBuilder();
      } else {
        return enterGamePublicRt_ == null ?
            pb4server.EnterGamePublicRtVo.getDefaultInstance() : enterGamePublicRt_;
      }
    }
    /**
     * <code>.pb4server.EnterGamePublicRtVo enterGamePublicRt = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4server.EnterGamePublicRtVo, pb4server.EnterGamePublicRtVo.Builder, pb4server.EnterGamePublicRtVoOrBuilder> 
        getEnterGamePublicRtFieldBuilder() {
      if (enterGamePublicRtBuilder_ == null) {
        enterGamePublicRtBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4server.EnterGamePublicRtVo, pb4server.EnterGamePublicRtVo.Builder, pb4server.EnterGamePublicRtVoOrBuilder>(
                getEnterGamePublicRt(),
                getParentForChildren(),
                isClean());
        enterGamePublicRt_ = null;
      }
      return enterGamePublicRtBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.PlayerOnlineNoticTell)
  }

  // @@protoc_insertion_point(class_scope:pb4server.PlayerOnlineNoticTell)
  private static final pb4server.PlayerOnlineNoticTell DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.PlayerOnlineNoticTell();
  }

  public static pb4server.PlayerOnlineNoticTell getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<PlayerOnlineNoticTell>
      PARSER = new com.google.protobuf.AbstractParser<PlayerOnlineNoticTell>() {
    public PlayerOnlineNoticTell parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new PlayerOnlineNoticTell(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<PlayerOnlineNoticTell> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<PlayerOnlineNoticTell> getParserForType() {
    return PARSER;
  }

  public pb4server.PlayerOnlineNoticTell getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
