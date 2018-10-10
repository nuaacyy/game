// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 *战斗记录
 * </pre>
 *
 * Protobuf type {@code client2server.FightRecord}
 */
public  final class FightRecord extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.FightRecord)
    FightRecordOrBuilder {
  // Use FightRecord.newBuilder() to construct.
  private FightRecord(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private FightRecord() {
    frame_ = 0;
    senderId_ = 0L;
    receiverId_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private FightRecord(
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
            frame_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            senderId_ = input.readInt64();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            receiverId_ = input.readInt64();
            break;
          }
          case 34: {
            pb4client.FightRequest.Builder subBuilder = null;
            if (((bitField0_ & 0x00000008) == 0x00000008)) {
              subBuilder = request_.toBuilder();
            }
            request_ = input.readMessage(pb4client.FightRequest.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(request_);
              request_ = subBuilder.buildPartial();
            }
            bitField0_ |= 0x00000008;
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
    return pb4client.War2GamePkt.internal_static_client2server_FightRecord_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_FightRecord_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.FightRecord.class, pb4client.FightRecord.Builder.class);
  }

  private int bitField0_;
  public static final int FRAME_FIELD_NUMBER = 1;
  private int frame_;
  /**
   * <pre>
   *帧
   * </pre>
   *
   * <code>required int32 frame = 1;</code>
   */
  public boolean hasFrame() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *帧
   * </pre>
   *
   * <code>required int32 frame = 1;</code>
   */
  public int getFrame() {
    return frame_;
  }

  public static final int SENDERID_FIELD_NUMBER = 2;
  private long senderId_;
  /**
   * <pre>
   *发送者Id
   * </pre>
   *
   * <code>required int64 senderId = 2;</code>
   */
  public boolean hasSenderId() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *发送者Id
   * </pre>
   *
   * <code>required int64 senderId = 2;</code>
   */
  public long getSenderId() {
    return senderId_;
  }

  public static final int RECEIVERID_FIELD_NUMBER = 3;
  private long receiverId_;
  /**
   * <pre>
   *接受者Id
   * </pre>
   *
   * <code>required int64 receiverId = 3;</code>
   */
  public boolean hasReceiverId() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   *接受者Id
   * </pre>
   *
   * <code>required int64 receiverId = 3;</code>
   */
  public long getReceiverId() {
    return receiverId_;
  }

  public static final int REQUEST_FIELD_NUMBER = 4;
  private pb4client.FightRequest request_;
  /**
   * <pre>
   *请求内容
   * </pre>
   *
   * <code>required .client2server.FightRequest request = 4;</code>
   */
  public boolean hasRequest() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   *请求内容
   * </pre>
   *
   * <code>required .client2server.FightRequest request = 4;</code>
   */
  public pb4client.FightRequest getRequest() {
    return request_ == null ? pb4client.FightRequest.getDefaultInstance() : request_;
  }
  /**
   * <pre>
   *请求内容
   * </pre>
   *
   * <code>required .client2server.FightRequest request = 4;</code>
   */
  public pb4client.FightRequestOrBuilder getRequestOrBuilder() {
    return request_ == null ? pb4client.FightRequest.getDefaultInstance() : request_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasFrame()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasSenderId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasReceiverId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasRequest()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!getRequest().isInitialized()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, frame_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt64(2, senderId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt64(3, receiverId_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeMessage(4, getRequest());
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, frame_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, senderId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, receiverId_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, getRequest());
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
    if (!(obj instanceof pb4client.FightRecord)) {
      return super.equals(obj);
    }
    pb4client.FightRecord other = (pb4client.FightRecord) obj;

    boolean result = true;
    result = result && (hasFrame() == other.hasFrame());
    if (hasFrame()) {
      result = result && (getFrame()
          == other.getFrame());
    }
    result = result && (hasSenderId() == other.hasSenderId());
    if (hasSenderId()) {
      result = result && (getSenderId()
          == other.getSenderId());
    }
    result = result && (hasReceiverId() == other.hasReceiverId());
    if (hasReceiverId()) {
      result = result && (getReceiverId()
          == other.getReceiverId());
    }
    result = result && (hasRequest() == other.hasRequest());
    if (hasRequest()) {
      result = result && getRequest()
          .equals(other.getRequest());
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
    if (hasFrame()) {
      hash = (37 * hash) + FRAME_FIELD_NUMBER;
      hash = (53 * hash) + getFrame();
    }
    if (hasSenderId()) {
      hash = (37 * hash) + SENDERID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getSenderId());
    }
    if (hasReceiverId()) {
      hash = (37 * hash) + RECEIVERID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getReceiverId());
    }
    if (hasRequest()) {
      hash = (37 * hash) + REQUEST_FIELD_NUMBER;
      hash = (53 * hash) + getRequest().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.FightRecord parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.FightRecord parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.FightRecord parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.FightRecord parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.FightRecord parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.FightRecord parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.FightRecord parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.FightRecord parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.FightRecord parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.FightRecord parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.FightRecord parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.FightRecord parseFrom(
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
  public static Builder newBuilder(pb4client.FightRecord prototype) {
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
   *战斗记录
   * </pre>
   *
   * Protobuf type {@code client2server.FightRecord}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.FightRecord)
      pb4client.FightRecordOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_FightRecord_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_FightRecord_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.FightRecord.class, pb4client.FightRecord.Builder.class);
    }

    // Construct using pb4client.FightRecord.newBuilder()
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
        getRequestFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      frame_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      senderId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      receiverId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000004);
      if (requestBuilder_ == null) {
        request_ = null;
      } else {
        requestBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000008);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_FightRecord_descriptor;
    }

    public pb4client.FightRecord getDefaultInstanceForType() {
      return pb4client.FightRecord.getDefaultInstance();
    }

    public pb4client.FightRecord build() {
      pb4client.FightRecord result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.FightRecord buildPartial() {
      pb4client.FightRecord result = new pb4client.FightRecord(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.frame_ = frame_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.senderId_ = senderId_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.receiverId_ = receiverId_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      if (requestBuilder_ == null) {
        result.request_ = request_;
      } else {
        result.request_ = requestBuilder_.build();
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
      if (other instanceof pb4client.FightRecord) {
        return mergeFrom((pb4client.FightRecord)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.FightRecord other) {
      if (other == pb4client.FightRecord.getDefaultInstance()) return this;
      if (other.hasFrame()) {
        setFrame(other.getFrame());
      }
      if (other.hasSenderId()) {
        setSenderId(other.getSenderId());
      }
      if (other.hasReceiverId()) {
        setReceiverId(other.getReceiverId());
      }
      if (other.hasRequest()) {
        mergeRequest(other.getRequest());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasFrame()) {
        return false;
      }
      if (!hasSenderId()) {
        return false;
      }
      if (!hasReceiverId()) {
        return false;
      }
      if (!hasRequest()) {
        return false;
      }
      if (!getRequest().isInitialized()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.FightRecord parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.FightRecord) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int frame_ ;
    /**
     * <pre>
     *帧
     * </pre>
     *
     * <code>required int32 frame = 1;</code>
     */
    public boolean hasFrame() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *帧
     * </pre>
     *
     * <code>required int32 frame = 1;</code>
     */
    public int getFrame() {
      return frame_;
    }
    /**
     * <pre>
     *帧
     * </pre>
     *
     * <code>required int32 frame = 1;</code>
     */
    public Builder setFrame(int value) {
      bitField0_ |= 0x00000001;
      frame_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *帧
     * </pre>
     *
     * <code>required int32 frame = 1;</code>
     */
    public Builder clearFrame() {
      bitField0_ = (bitField0_ & ~0x00000001);
      frame_ = 0;
      onChanged();
      return this;
    }

    private long senderId_ ;
    /**
     * <pre>
     *发送者Id
     * </pre>
     *
     * <code>required int64 senderId = 2;</code>
     */
    public boolean hasSenderId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *发送者Id
     * </pre>
     *
     * <code>required int64 senderId = 2;</code>
     */
    public long getSenderId() {
      return senderId_;
    }
    /**
     * <pre>
     *发送者Id
     * </pre>
     *
     * <code>required int64 senderId = 2;</code>
     */
    public Builder setSenderId(long value) {
      bitField0_ |= 0x00000002;
      senderId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *发送者Id
     * </pre>
     *
     * <code>required int64 senderId = 2;</code>
     */
    public Builder clearSenderId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      senderId_ = 0L;
      onChanged();
      return this;
    }

    private long receiverId_ ;
    /**
     * <pre>
     *接受者Id
     * </pre>
     *
     * <code>required int64 receiverId = 3;</code>
     */
    public boolean hasReceiverId() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     *接受者Id
     * </pre>
     *
     * <code>required int64 receiverId = 3;</code>
     */
    public long getReceiverId() {
      return receiverId_;
    }
    /**
     * <pre>
     *接受者Id
     * </pre>
     *
     * <code>required int64 receiverId = 3;</code>
     */
    public Builder setReceiverId(long value) {
      bitField0_ |= 0x00000004;
      receiverId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *接受者Id
     * </pre>
     *
     * <code>required int64 receiverId = 3;</code>
     */
    public Builder clearReceiverId() {
      bitField0_ = (bitField0_ & ~0x00000004);
      receiverId_ = 0L;
      onChanged();
      return this;
    }

    private pb4client.FightRequest request_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.FightRequest, pb4client.FightRequest.Builder, pb4client.FightRequestOrBuilder> requestBuilder_;
    /**
     * <pre>
     *请求内容
     * </pre>
     *
     * <code>required .client2server.FightRequest request = 4;</code>
     */
    public boolean hasRequest() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     *请求内容
     * </pre>
     *
     * <code>required .client2server.FightRequest request = 4;</code>
     */
    public pb4client.FightRequest getRequest() {
      if (requestBuilder_ == null) {
        return request_ == null ? pb4client.FightRequest.getDefaultInstance() : request_;
      } else {
        return requestBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     *请求内容
     * </pre>
     *
     * <code>required .client2server.FightRequest request = 4;</code>
     */
    public Builder setRequest(pb4client.FightRequest value) {
      if (requestBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        request_ = value;
        onChanged();
      } else {
        requestBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000008;
      return this;
    }
    /**
     * <pre>
     *请求内容
     * </pre>
     *
     * <code>required .client2server.FightRequest request = 4;</code>
     */
    public Builder setRequest(
        pb4client.FightRequest.Builder builderForValue) {
      if (requestBuilder_ == null) {
        request_ = builderForValue.build();
        onChanged();
      } else {
        requestBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000008;
      return this;
    }
    /**
     * <pre>
     *请求内容
     * </pre>
     *
     * <code>required .client2server.FightRequest request = 4;</code>
     */
    public Builder mergeRequest(pb4client.FightRequest value) {
      if (requestBuilder_ == null) {
        if (((bitField0_ & 0x00000008) == 0x00000008) &&
            request_ != null &&
            request_ != pb4client.FightRequest.getDefaultInstance()) {
          request_ =
            pb4client.FightRequest.newBuilder(request_).mergeFrom(value).buildPartial();
        } else {
          request_ = value;
        }
        onChanged();
      } else {
        requestBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000008;
      return this;
    }
    /**
     * <pre>
     *请求内容
     * </pre>
     *
     * <code>required .client2server.FightRequest request = 4;</code>
     */
    public Builder clearRequest() {
      if (requestBuilder_ == null) {
        request_ = null;
        onChanged();
      } else {
        requestBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000008);
      return this;
    }
    /**
     * <pre>
     *请求内容
     * </pre>
     *
     * <code>required .client2server.FightRequest request = 4;</code>
     */
    public pb4client.FightRequest.Builder getRequestBuilder() {
      bitField0_ |= 0x00000008;
      onChanged();
      return getRequestFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     *请求内容
     * </pre>
     *
     * <code>required .client2server.FightRequest request = 4;</code>
     */
    public pb4client.FightRequestOrBuilder getRequestOrBuilder() {
      if (requestBuilder_ != null) {
        return requestBuilder_.getMessageOrBuilder();
      } else {
        return request_ == null ?
            pb4client.FightRequest.getDefaultInstance() : request_;
      }
    }
    /**
     * <pre>
     *请求内容
     * </pre>
     *
     * <code>required .client2server.FightRequest request = 4;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.FightRequest, pb4client.FightRequest.Builder, pb4client.FightRequestOrBuilder> 
        getRequestFieldBuilder() {
      if (requestBuilder_ == null) {
        requestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4client.FightRequest, pb4client.FightRequest.Builder, pb4client.FightRequestOrBuilder>(
                getRequest(),
                getParentForChildren(),
                isClean());
        request_ = null;
      }
      return requestBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.FightRecord)
  }

  // @@protoc_insertion_point(class_scope:client2server.FightRecord)
  private static final pb4client.FightRecord DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.FightRecord();
  }

  public static pb4client.FightRecord getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<FightRecord>
      PARSER = new com.google.protobuf.AbstractParser<FightRecord>() {
    public FightRecord parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new FightRecord(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<FightRecord> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<FightRecord> getParserForType() {
    return PARSER;
  }

  public pb4client.FightRecord getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
