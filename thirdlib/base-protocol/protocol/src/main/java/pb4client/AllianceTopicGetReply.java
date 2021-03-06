// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 893
 * 客户端 -&gt; 服务器
 * 玩家滚动回复列表时: 请求历史回复内容
 * </pre>
 *
 * Protobuf type {@code client2server.AllianceTopicGetReply}
 */
public  final class AllianceTopicGetReply extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.AllianceTopicGetReply)
    AllianceTopicGetReplyOrBuilder {
  // Use AllianceTopicGetReply.newBuilder() to construct.
  private AllianceTopicGetReply(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllianceTopicGetReply() {
    topicId_ = 0L;
    replyId_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AllianceTopicGetReply(
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
            topicId_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            replyId_ = input.readInt64();
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
    return pb4client.War2GamePkt.internal_static_client2server_AllianceTopicGetReply_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_AllianceTopicGetReply_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.AllianceTopicGetReply.class, pb4client.AllianceTopicGetReply.Builder.class);
  }

  private int bitField0_;
  public static final int TOPICID_FIELD_NUMBER = 1;
  private long topicId_;
  /**
   * <pre>
   *主题ID
   * </pre>
   *
   * <code>required int64 topicId = 1;</code>
   */
  public boolean hasTopicId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *主题ID
   * </pre>
   *
   * <code>required int64 topicId = 1;</code>
   */
  public long getTopicId() {
    return topicId_;
  }

  public static final int REPLYID_FIELD_NUMBER = 2;
  private long replyId_;
  /**
   * <pre>
   *客户端拥有的离现在最久的回复ID
   * </pre>
   *
   * <code>required int64 replyId = 2;</code>
   */
  public boolean hasReplyId() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *客户端拥有的离现在最久的回复ID
   * </pre>
   *
   * <code>required int64 replyId = 2;</code>
   */
  public long getReplyId() {
    return replyId_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasTopicId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasReplyId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, topicId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt64(2, replyId_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, topicId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, replyId_);
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
    if (!(obj instanceof pb4client.AllianceTopicGetReply)) {
      return super.equals(obj);
    }
    pb4client.AllianceTopicGetReply other = (pb4client.AllianceTopicGetReply) obj;

    boolean result = true;
    result = result && (hasTopicId() == other.hasTopicId());
    if (hasTopicId()) {
      result = result && (getTopicId()
          == other.getTopicId());
    }
    result = result && (hasReplyId() == other.hasReplyId());
    if (hasReplyId()) {
      result = result && (getReplyId()
          == other.getReplyId());
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
    if (hasTopicId()) {
      hash = (37 * hash) + TOPICID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getTopicId());
    }
    if (hasReplyId()) {
      hash = (37 * hash) + REPLYID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getReplyId());
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.AllianceTopicGetReply parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceTopicGetReply parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceTopicGetReply parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceTopicGetReply parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceTopicGetReply parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceTopicGetReply parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceTopicGetReply parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AllianceTopicGetReply parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AllianceTopicGetReply parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.AllianceTopicGetReply parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AllianceTopicGetReply parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AllianceTopicGetReply parseFrom(
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
  public static Builder newBuilder(pb4client.AllianceTopicGetReply prototype) {
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
   * msgType = 893
   * 客户端 -&gt; 服务器
   * 玩家滚动回复列表时: 请求历史回复内容
   * </pre>
   *
   * Protobuf type {@code client2server.AllianceTopicGetReply}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.AllianceTopicGetReply)
      pb4client.AllianceTopicGetReplyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceTopicGetReply_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceTopicGetReply_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.AllianceTopicGetReply.class, pb4client.AllianceTopicGetReply.Builder.class);
    }

    // Construct using pb4client.AllianceTopicGetReply.newBuilder()
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
      topicId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      replyId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceTopicGetReply_descriptor;
    }

    public pb4client.AllianceTopicGetReply getDefaultInstanceForType() {
      return pb4client.AllianceTopicGetReply.getDefaultInstance();
    }

    public pb4client.AllianceTopicGetReply build() {
      pb4client.AllianceTopicGetReply result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.AllianceTopicGetReply buildPartial() {
      pb4client.AllianceTopicGetReply result = new pb4client.AllianceTopicGetReply(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.topicId_ = topicId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.replyId_ = replyId_;
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
      if (other instanceof pb4client.AllianceTopicGetReply) {
        return mergeFrom((pb4client.AllianceTopicGetReply)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.AllianceTopicGetReply other) {
      if (other == pb4client.AllianceTopicGetReply.getDefaultInstance()) return this;
      if (other.hasTopicId()) {
        setTopicId(other.getTopicId());
      }
      if (other.hasReplyId()) {
        setReplyId(other.getReplyId());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasTopicId()) {
        return false;
      }
      if (!hasReplyId()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.AllianceTopicGetReply parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.AllianceTopicGetReply) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long topicId_ ;
    /**
     * <pre>
     *主题ID
     * </pre>
     *
     * <code>required int64 topicId = 1;</code>
     */
    public boolean hasTopicId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *主题ID
     * </pre>
     *
     * <code>required int64 topicId = 1;</code>
     */
    public long getTopicId() {
      return topicId_;
    }
    /**
     * <pre>
     *主题ID
     * </pre>
     *
     * <code>required int64 topicId = 1;</code>
     */
    public Builder setTopicId(long value) {
      bitField0_ |= 0x00000001;
      topicId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *主题ID
     * </pre>
     *
     * <code>required int64 topicId = 1;</code>
     */
    public Builder clearTopicId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      topicId_ = 0L;
      onChanged();
      return this;
    }

    private long replyId_ ;
    /**
     * <pre>
     *客户端拥有的离现在最久的回复ID
     * </pre>
     *
     * <code>required int64 replyId = 2;</code>
     */
    public boolean hasReplyId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *客户端拥有的离现在最久的回复ID
     * </pre>
     *
     * <code>required int64 replyId = 2;</code>
     */
    public long getReplyId() {
      return replyId_;
    }
    /**
     * <pre>
     *客户端拥有的离现在最久的回复ID
     * </pre>
     *
     * <code>required int64 replyId = 2;</code>
     */
    public Builder setReplyId(long value) {
      bitField0_ |= 0x00000002;
      replyId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *客户端拥有的离现在最久的回复ID
     * </pre>
     *
     * <code>required int64 replyId = 2;</code>
     */
    public Builder clearReplyId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      replyId_ = 0L;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.AllianceTopicGetReply)
  }

  // @@protoc_insertion_point(class_scope:client2server.AllianceTopicGetReply)
  private static final pb4client.AllianceTopicGetReply DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.AllianceTopicGetReply();
  }

  public static pb4client.AllianceTopicGetReply getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<AllianceTopicGetReply>
      PARSER = new com.google.protobuf.AbstractParser<AllianceTopicGetReply>() {
    public AllianceTopicGetReply parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AllianceTopicGetReply(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllianceTopicGetReply> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllianceTopicGetReply> getParserForType() {
    return PARSER;
  }

  public pb4client.AllianceTopicGetReply getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

