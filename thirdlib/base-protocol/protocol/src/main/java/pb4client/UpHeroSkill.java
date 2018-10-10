// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 31
 * 客户端 -&gt; 服务器
 * 请求武将升级技能
 * </pre>
 *
 * Protobuf type {@code client2server.UpHeroSkill}
 */
public  final class UpHeroSkill extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.UpHeroSkill)
    UpHeroSkillOrBuilder {
  // Use UpHeroSkill.newBuilder() to construct.
  private UpHeroSkill(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private UpHeroSkill() {
    heroId_ = 0L;
    pos_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private UpHeroSkill(
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
            heroId_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            pos_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_UpHeroSkill_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_UpHeroSkill_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.UpHeroSkill.class, pb4client.UpHeroSkill.Builder.class);
  }

  private int bitField0_;
  public static final int HEROID_FIELD_NUMBER = 1;
  private long heroId_;
  /**
   * <pre>
   * 武将编号
   * </pre>
   *
   * <code>required int64 heroId = 1;</code>
   */
  public boolean hasHeroId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 武将编号
   * </pre>
   *
   * <code>required int64 heroId = 1;</code>
   */
  public long getHeroId() {
    return heroId_;
  }

  public static final int POS_FIELD_NUMBER = 2;
  private int pos_;
  /**
   * <pre>
   * 第几个技能(1-4)
   * </pre>
   *
   * <code>required int32 pos = 2;</code>
   */
  public boolean hasPos() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 第几个技能(1-4)
   * </pre>
   *
   * <code>required int32 pos = 2;</code>
   */
  public int getPos() {
    return pos_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasHeroId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasPos()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, heroId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, pos_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, heroId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, pos_);
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
    if (!(obj instanceof pb4client.UpHeroSkill)) {
      return super.equals(obj);
    }
    pb4client.UpHeroSkill other = (pb4client.UpHeroSkill) obj;

    boolean result = true;
    result = result && (hasHeroId() == other.hasHeroId());
    if (hasHeroId()) {
      result = result && (getHeroId()
          == other.getHeroId());
    }
    result = result && (hasPos() == other.hasPos());
    if (hasPos()) {
      result = result && (getPos()
          == other.getPos());
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
    if (hasHeroId()) {
      hash = (37 * hash) + HEROID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getHeroId());
    }
    if (hasPos()) {
      hash = (37 * hash) + POS_FIELD_NUMBER;
      hash = (53 * hash) + getPos();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.UpHeroSkill parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.UpHeroSkill parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.UpHeroSkill parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.UpHeroSkill parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.UpHeroSkill parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.UpHeroSkill parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.UpHeroSkill parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.UpHeroSkill parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.UpHeroSkill parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.UpHeroSkill parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.UpHeroSkill parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.UpHeroSkill parseFrom(
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
  public static Builder newBuilder(pb4client.UpHeroSkill prototype) {
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
   * msgType = 31
   * 客户端 -&gt; 服务器
   * 请求武将升级技能
   * </pre>
   *
   * Protobuf type {@code client2server.UpHeroSkill}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.UpHeroSkill)
      pb4client.UpHeroSkillOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_UpHeroSkill_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_UpHeroSkill_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.UpHeroSkill.class, pb4client.UpHeroSkill.Builder.class);
    }

    // Construct using pb4client.UpHeroSkill.newBuilder()
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
      heroId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      pos_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_UpHeroSkill_descriptor;
    }

    public pb4client.UpHeroSkill getDefaultInstanceForType() {
      return pb4client.UpHeroSkill.getDefaultInstance();
    }

    public pb4client.UpHeroSkill build() {
      pb4client.UpHeroSkill result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.UpHeroSkill buildPartial() {
      pb4client.UpHeroSkill result = new pb4client.UpHeroSkill(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.heroId_ = heroId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.pos_ = pos_;
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
      if (other instanceof pb4client.UpHeroSkill) {
        return mergeFrom((pb4client.UpHeroSkill)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.UpHeroSkill other) {
      if (other == pb4client.UpHeroSkill.getDefaultInstance()) return this;
      if (other.hasHeroId()) {
        setHeroId(other.getHeroId());
      }
      if (other.hasPos()) {
        setPos(other.getPos());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasHeroId()) {
        return false;
      }
      if (!hasPos()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.UpHeroSkill parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.UpHeroSkill) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long heroId_ ;
    /**
     * <pre>
     * 武将编号
     * </pre>
     *
     * <code>required int64 heroId = 1;</code>
     */
    public boolean hasHeroId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 武将编号
     * </pre>
     *
     * <code>required int64 heroId = 1;</code>
     */
    public long getHeroId() {
      return heroId_;
    }
    /**
     * <pre>
     * 武将编号
     * </pre>
     *
     * <code>required int64 heroId = 1;</code>
     */
    public Builder setHeroId(long value) {
      bitField0_ |= 0x00000001;
      heroId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 武将编号
     * </pre>
     *
     * <code>required int64 heroId = 1;</code>
     */
    public Builder clearHeroId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      heroId_ = 0L;
      onChanged();
      return this;
    }

    private int pos_ ;
    /**
     * <pre>
     * 第几个技能(1-4)
     * </pre>
     *
     * <code>required int32 pos = 2;</code>
     */
    public boolean hasPos() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 第几个技能(1-4)
     * </pre>
     *
     * <code>required int32 pos = 2;</code>
     */
    public int getPos() {
      return pos_;
    }
    /**
     * <pre>
     * 第几个技能(1-4)
     * </pre>
     *
     * <code>required int32 pos = 2;</code>
     */
    public Builder setPos(int value) {
      bitField0_ |= 0x00000002;
      pos_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 第几个技能(1-4)
     * </pre>
     *
     * <code>required int32 pos = 2;</code>
     */
    public Builder clearPos() {
      bitField0_ = (bitField0_ & ~0x00000002);
      pos_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.UpHeroSkill)
  }

  // @@protoc_insertion_point(class_scope:client2server.UpHeroSkill)
  private static final pb4client.UpHeroSkill DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.UpHeroSkill();
  }

  public static pb4client.UpHeroSkill getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<UpHeroSkill>
      PARSER = new com.google.protobuf.AbstractParser<UpHeroSkill>() {
    public UpHeroSkill parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new UpHeroSkill(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<UpHeroSkill> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<UpHeroSkill> getParserForType() {
    return PARSER;
  }

  public pb4client.UpHeroSkill getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

