// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.HeroEquipVo}
 */
public  final class HeroEquipVo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.HeroEquipVo)
    HeroEquipVoOrBuilder {
  // Use HeroEquipVo.newBuilder() to construct.
  private HeroEquipVo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private HeroEquipVo() {
    heroTrophiesId_ = 0;
    advLv_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private HeroEquipVo(
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
            heroTrophiesId_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            advLv_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_HeroEquipVo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_HeroEquipVo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.HeroEquipVo.class, pb4client.HeroEquipVo.Builder.class);
  }

  private int bitField0_;
  public static final int HEROTROPHIESID_FIELD_NUMBER = 1;
  private int heroTrophiesId_;
  /**
   * <pre>
   * 战利品包ID
   * </pre>
   *
   * <code>required int32 heroTrophiesId = 1;</code>
   */
  public boolean hasHeroTrophiesId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 战利品包ID
   * </pre>
   *
   * <code>required int32 heroTrophiesId = 1;</code>
   */
  public int getHeroTrophiesId() {
    return heroTrophiesId_;
  }

  public static final int ADVLV_FIELD_NUMBER = 2;
  private int advLv_;
  /**
   * <pre>
   * 装备阶级
   * </pre>
   *
   * <code>required int32 advLv = 2;</code>
   */
  public boolean hasAdvLv() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 装备阶级
   * </pre>
   *
   * <code>required int32 advLv = 2;</code>
   */
  public int getAdvLv() {
    return advLv_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasHeroTrophiesId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasAdvLv()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, heroTrophiesId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, advLv_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, heroTrophiesId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, advLv_);
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
    if (!(obj instanceof pb4client.HeroEquipVo)) {
      return super.equals(obj);
    }
    pb4client.HeroEquipVo other = (pb4client.HeroEquipVo) obj;

    boolean result = true;
    result = result && (hasHeroTrophiesId() == other.hasHeroTrophiesId());
    if (hasHeroTrophiesId()) {
      result = result && (getHeroTrophiesId()
          == other.getHeroTrophiesId());
    }
    result = result && (hasAdvLv() == other.hasAdvLv());
    if (hasAdvLv()) {
      result = result && (getAdvLv()
          == other.getAdvLv());
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
    if (hasHeroTrophiesId()) {
      hash = (37 * hash) + HEROTROPHIESID_FIELD_NUMBER;
      hash = (53 * hash) + getHeroTrophiesId();
    }
    if (hasAdvLv()) {
      hash = (37 * hash) + ADVLV_FIELD_NUMBER;
      hash = (53 * hash) + getAdvLv();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.HeroEquipVo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.HeroEquipVo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.HeroEquipVo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.HeroEquipVo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.HeroEquipVo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.HeroEquipVo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.HeroEquipVo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.HeroEquipVo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.HeroEquipVo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.HeroEquipVo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.HeroEquipVo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.HeroEquipVo parseFrom(
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
  public static Builder newBuilder(pb4client.HeroEquipVo prototype) {
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
   * Protobuf type {@code client2server.HeroEquipVo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.HeroEquipVo)
      pb4client.HeroEquipVoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_HeroEquipVo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_HeroEquipVo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.HeroEquipVo.class, pb4client.HeroEquipVo.Builder.class);
    }

    // Construct using pb4client.HeroEquipVo.newBuilder()
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
      heroTrophiesId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      advLv_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_HeroEquipVo_descriptor;
    }

    public pb4client.HeroEquipVo getDefaultInstanceForType() {
      return pb4client.HeroEquipVo.getDefaultInstance();
    }

    public pb4client.HeroEquipVo build() {
      pb4client.HeroEquipVo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.HeroEquipVo buildPartial() {
      pb4client.HeroEquipVo result = new pb4client.HeroEquipVo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.heroTrophiesId_ = heroTrophiesId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.advLv_ = advLv_;
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
      if (other instanceof pb4client.HeroEquipVo) {
        return mergeFrom((pb4client.HeroEquipVo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.HeroEquipVo other) {
      if (other == pb4client.HeroEquipVo.getDefaultInstance()) return this;
      if (other.hasHeroTrophiesId()) {
        setHeroTrophiesId(other.getHeroTrophiesId());
      }
      if (other.hasAdvLv()) {
        setAdvLv(other.getAdvLv());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasHeroTrophiesId()) {
        return false;
      }
      if (!hasAdvLv()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.HeroEquipVo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.HeroEquipVo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int heroTrophiesId_ ;
    /**
     * <pre>
     * 战利品包ID
     * </pre>
     *
     * <code>required int32 heroTrophiesId = 1;</code>
     */
    public boolean hasHeroTrophiesId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 战利品包ID
     * </pre>
     *
     * <code>required int32 heroTrophiesId = 1;</code>
     */
    public int getHeroTrophiesId() {
      return heroTrophiesId_;
    }
    /**
     * <pre>
     * 战利品包ID
     * </pre>
     *
     * <code>required int32 heroTrophiesId = 1;</code>
     */
    public Builder setHeroTrophiesId(int value) {
      bitField0_ |= 0x00000001;
      heroTrophiesId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 战利品包ID
     * </pre>
     *
     * <code>required int32 heroTrophiesId = 1;</code>
     */
    public Builder clearHeroTrophiesId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      heroTrophiesId_ = 0;
      onChanged();
      return this;
    }

    private int advLv_ ;
    /**
     * <pre>
     * 装备阶级
     * </pre>
     *
     * <code>required int32 advLv = 2;</code>
     */
    public boolean hasAdvLv() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 装备阶级
     * </pre>
     *
     * <code>required int32 advLv = 2;</code>
     */
    public int getAdvLv() {
      return advLv_;
    }
    /**
     * <pre>
     * 装备阶级
     * </pre>
     *
     * <code>required int32 advLv = 2;</code>
     */
    public Builder setAdvLv(int value) {
      bitField0_ |= 0x00000002;
      advLv_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 装备阶级
     * </pre>
     *
     * <code>required int32 advLv = 2;</code>
     */
    public Builder clearAdvLv() {
      bitField0_ = (bitField0_ & ~0x00000002);
      advLv_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.HeroEquipVo)
  }

  // @@protoc_insertion_point(class_scope:client2server.HeroEquipVo)
  private static final pb4client.HeroEquipVo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.HeroEquipVo();
  }

  public static pb4client.HeroEquipVo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<HeroEquipVo>
      PARSER = new com.google.protobuf.AbstractParser<HeroEquipVo>() {
    public HeroEquipVo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new HeroEquipVo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<HeroEquipVo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<HeroEquipVo> getParserForType() {
    return PARSER;
  }

  public pb4client.HeroEquipVo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

