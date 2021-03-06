// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 1546
 * 客户端 -&gt; 服务器
 * 抽卡
 * </pre>
 *
 * Protobuf type {@code client2server.PlayLottery}
 */
public  final class PlayLottery extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.PlayLottery)
    PlayLotteryOrBuilder {
  // Use PlayLottery.newBuilder() to construct.
  private PlayLottery(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private PlayLottery() {
    protoId_ = 0;
    drawTimes_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private PlayLottery(
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
            protoId_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            drawTimes_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_PlayLottery_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_PlayLottery_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.PlayLottery.class, pb4client.PlayLottery.Builder.class);
  }

  private int bitField0_;
  public static final int PROTOID_FIELD_NUMBER = 1;
  private int protoId_;
  /**
   * <pre>
   * 要抽哪一个的模板id，普通的/高级/活动的抽卡模板id
   * </pre>
   *
   * <code>required int32 protoId = 1;</code>
   */
  public boolean hasProtoId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 要抽哪一个的模板id，普通的/高级/活动的抽卡模板id
   * </pre>
   *
   * <code>required int32 protoId = 1;</code>
   */
  public int getProtoId() {
    return protoId_;
  }

  public static final int DRAWTIMES_FIELD_NUMBER = 2;
  private int drawTimes_;
  /**
   * <pre>
   * 抽多少次 ：1 - 单次抽  10- 十连抽
   * </pre>
   *
   * <code>required int32 drawTimes = 2;</code>
   */
  public boolean hasDrawTimes() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 抽多少次 ：1 - 单次抽  10- 十连抽
   * </pre>
   *
   * <code>required int32 drawTimes = 2;</code>
   */
  public int getDrawTimes() {
    return drawTimes_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasProtoId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasDrawTimes()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, protoId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, drawTimes_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, protoId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, drawTimes_);
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
    if (!(obj instanceof pb4client.PlayLottery)) {
      return super.equals(obj);
    }
    pb4client.PlayLottery other = (pb4client.PlayLottery) obj;

    boolean result = true;
    result = result && (hasProtoId() == other.hasProtoId());
    if (hasProtoId()) {
      result = result && (getProtoId()
          == other.getProtoId());
    }
    result = result && (hasDrawTimes() == other.hasDrawTimes());
    if (hasDrawTimes()) {
      result = result && (getDrawTimes()
          == other.getDrawTimes());
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
    if (hasProtoId()) {
      hash = (37 * hash) + PROTOID_FIELD_NUMBER;
      hash = (53 * hash) + getProtoId();
    }
    if (hasDrawTimes()) {
      hash = (37 * hash) + DRAWTIMES_FIELD_NUMBER;
      hash = (53 * hash) + getDrawTimes();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.PlayLottery parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.PlayLottery parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.PlayLottery parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.PlayLottery parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.PlayLottery parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.PlayLottery parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.PlayLottery parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.PlayLottery parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.PlayLottery parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.PlayLottery parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.PlayLottery parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.PlayLottery parseFrom(
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
  public static Builder newBuilder(pb4client.PlayLottery prototype) {
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
   * msgType = 1546
   * 客户端 -&gt; 服务器
   * 抽卡
   * </pre>
   *
   * Protobuf type {@code client2server.PlayLottery}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.PlayLottery)
      pb4client.PlayLotteryOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_PlayLottery_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_PlayLottery_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.PlayLottery.class, pb4client.PlayLottery.Builder.class);
    }

    // Construct using pb4client.PlayLottery.newBuilder()
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
      protoId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      drawTimes_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_PlayLottery_descriptor;
    }

    public pb4client.PlayLottery getDefaultInstanceForType() {
      return pb4client.PlayLottery.getDefaultInstance();
    }

    public pb4client.PlayLottery build() {
      pb4client.PlayLottery result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.PlayLottery buildPartial() {
      pb4client.PlayLottery result = new pb4client.PlayLottery(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.protoId_ = protoId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.drawTimes_ = drawTimes_;
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
      if (other instanceof pb4client.PlayLottery) {
        return mergeFrom((pb4client.PlayLottery)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.PlayLottery other) {
      if (other == pb4client.PlayLottery.getDefaultInstance()) return this;
      if (other.hasProtoId()) {
        setProtoId(other.getProtoId());
      }
      if (other.hasDrawTimes()) {
        setDrawTimes(other.getDrawTimes());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasProtoId()) {
        return false;
      }
      if (!hasDrawTimes()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.PlayLottery parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.PlayLottery) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int protoId_ ;
    /**
     * <pre>
     * 要抽哪一个的模板id，普通的/高级/活动的抽卡模板id
     * </pre>
     *
     * <code>required int32 protoId = 1;</code>
     */
    public boolean hasProtoId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 要抽哪一个的模板id，普通的/高级/活动的抽卡模板id
     * </pre>
     *
     * <code>required int32 protoId = 1;</code>
     */
    public int getProtoId() {
      return protoId_;
    }
    /**
     * <pre>
     * 要抽哪一个的模板id，普通的/高级/活动的抽卡模板id
     * </pre>
     *
     * <code>required int32 protoId = 1;</code>
     */
    public Builder setProtoId(int value) {
      bitField0_ |= 0x00000001;
      protoId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 要抽哪一个的模板id，普通的/高级/活动的抽卡模板id
     * </pre>
     *
     * <code>required int32 protoId = 1;</code>
     */
    public Builder clearProtoId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      protoId_ = 0;
      onChanged();
      return this;
    }

    private int drawTimes_ ;
    /**
     * <pre>
     * 抽多少次 ：1 - 单次抽  10- 十连抽
     * </pre>
     *
     * <code>required int32 drawTimes = 2;</code>
     */
    public boolean hasDrawTimes() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 抽多少次 ：1 - 单次抽  10- 十连抽
     * </pre>
     *
     * <code>required int32 drawTimes = 2;</code>
     */
    public int getDrawTimes() {
      return drawTimes_;
    }
    /**
     * <pre>
     * 抽多少次 ：1 - 单次抽  10- 十连抽
     * </pre>
     *
     * <code>required int32 drawTimes = 2;</code>
     */
    public Builder setDrawTimes(int value) {
      bitField0_ |= 0x00000002;
      drawTimes_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 抽多少次 ：1 - 单次抽  10- 十连抽
     * </pre>
     *
     * <code>required int32 drawTimes = 2;</code>
     */
    public Builder clearDrawTimes() {
      bitField0_ = (bitField0_ & ~0x00000002);
      drawTimes_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.PlayLottery)
  }

  // @@protoc_insertion_point(class_scope:client2server.PlayLottery)
  private static final pb4client.PlayLottery DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.PlayLottery();
  }

  public static pb4client.PlayLottery getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<PlayLottery>
      PARSER = new com.google.protobuf.AbstractParser<PlayLottery>() {
    public PlayLottery parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new PlayLottery(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<PlayLottery> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<PlayLottery> getParserForType() {
    return PARSER;
  }

  public pb4client.PlayLottery getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

