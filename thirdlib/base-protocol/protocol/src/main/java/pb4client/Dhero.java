// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.Dhero}
 */
public  final class Dhero extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.Dhero)
    DheroOrBuilder {
  // Use Dhero.newBuilder() to construct.
  private Dhero(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Dhero() {
    heroProtoId_ = 0;
    heroStarLv_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Dhero(
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
            heroProtoId_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            heroStarLv_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_Dhero_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_Dhero_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.Dhero.class, pb4client.Dhero.Builder.class);
  }

  private int bitField0_;
  public static final int HEROPROTOID_FIELD_NUMBER = 1;
  private int heroProtoId_;
  /**
   * <pre>
   * 抽到的武将卡模板
   * </pre>
   *
   * <code>required int32 heroProtoId = 1;</code>
   */
  public boolean hasHeroProtoId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 抽到的武将卡模板
   * </pre>
   *
   * <code>required int32 heroProtoId = 1;</code>
   */
  public int getHeroProtoId() {
    return heroProtoId_;
  }

  public static final int HEROSTARLV_FIELD_NUMBER = 2;
  private int heroStarLv_;
  /**
   * <pre>
   * 抽到的武将卡星级
   * </pre>
   *
   * <code>required int32 heroStarLv = 2;</code>
   */
  public boolean hasHeroStarLv() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 抽到的武将卡星级
   * </pre>
   *
   * <code>required int32 heroStarLv = 2;</code>
   */
  public int getHeroStarLv() {
    return heroStarLv_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasHeroProtoId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasHeroStarLv()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, heroProtoId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, heroStarLv_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, heroProtoId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, heroStarLv_);
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
    if (!(obj instanceof pb4client.Dhero)) {
      return super.equals(obj);
    }
    pb4client.Dhero other = (pb4client.Dhero) obj;

    boolean result = true;
    result = result && (hasHeroProtoId() == other.hasHeroProtoId());
    if (hasHeroProtoId()) {
      result = result && (getHeroProtoId()
          == other.getHeroProtoId());
    }
    result = result && (hasHeroStarLv() == other.hasHeroStarLv());
    if (hasHeroStarLv()) {
      result = result && (getHeroStarLv()
          == other.getHeroStarLv());
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
    if (hasHeroProtoId()) {
      hash = (37 * hash) + HEROPROTOID_FIELD_NUMBER;
      hash = (53 * hash) + getHeroProtoId();
    }
    if (hasHeroStarLv()) {
      hash = (37 * hash) + HEROSTARLV_FIELD_NUMBER;
      hash = (53 * hash) + getHeroStarLv();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.Dhero parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.Dhero parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.Dhero parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.Dhero parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.Dhero parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.Dhero parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.Dhero parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.Dhero parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.Dhero parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.Dhero parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.Dhero parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.Dhero parseFrom(
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
  public static Builder newBuilder(pb4client.Dhero prototype) {
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
   * Protobuf type {@code client2server.Dhero}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.Dhero)
      pb4client.DheroOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_Dhero_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_Dhero_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.Dhero.class, pb4client.Dhero.Builder.class);
    }

    // Construct using pb4client.Dhero.newBuilder()
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
      heroProtoId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      heroStarLv_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_Dhero_descriptor;
    }

    public pb4client.Dhero getDefaultInstanceForType() {
      return pb4client.Dhero.getDefaultInstance();
    }

    public pb4client.Dhero build() {
      pb4client.Dhero result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.Dhero buildPartial() {
      pb4client.Dhero result = new pb4client.Dhero(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.heroProtoId_ = heroProtoId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.heroStarLv_ = heroStarLv_;
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
      if (other instanceof pb4client.Dhero) {
        return mergeFrom((pb4client.Dhero)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.Dhero other) {
      if (other == pb4client.Dhero.getDefaultInstance()) return this;
      if (other.hasHeroProtoId()) {
        setHeroProtoId(other.getHeroProtoId());
      }
      if (other.hasHeroStarLv()) {
        setHeroStarLv(other.getHeroStarLv());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasHeroProtoId()) {
        return false;
      }
      if (!hasHeroStarLv()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.Dhero parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.Dhero) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int heroProtoId_ ;
    /**
     * <pre>
     * 抽到的武将卡模板
     * </pre>
     *
     * <code>required int32 heroProtoId = 1;</code>
     */
    public boolean hasHeroProtoId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 抽到的武将卡模板
     * </pre>
     *
     * <code>required int32 heroProtoId = 1;</code>
     */
    public int getHeroProtoId() {
      return heroProtoId_;
    }
    /**
     * <pre>
     * 抽到的武将卡模板
     * </pre>
     *
     * <code>required int32 heroProtoId = 1;</code>
     */
    public Builder setHeroProtoId(int value) {
      bitField0_ |= 0x00000001;
      heroProtoId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 抽到的武将卡模板
     * </pre>
     *
     * <code>required int32 heroProtoId = 1;</code>
     */
    public Builder clearHeroProtoId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      heroProtoId_ = 0;
      onChanged();
      return this;
    }

    private int heroStarLv_ ;
    /**
     * <pre>
     * 抽到的武将卡星级
     * </pre>
     *
     * <code>required int32 heroStarLv = 2;</code>
     */
    public boolean hasHeroStarLv() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 抽到的武将卡星级
     * </pre>
     *
     * <code>required int32 heroStarLv = 2;</code>
     */
    public int getHeroStarLv() {
      return heroStarLv_;
    }
    /**
     * <pre>
     * 抽到的武将卡星级
     * </pre>
     *
     * <code>required int32 heroStarLv = 2;</code>
     */
    public Builder setHeroStarLv(int value) {
      bitField0_ |= 0x00000002;
      heroStarLv_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 抽到的武将卡星级
     * </pre>
     *
     * <code>required int32 heroStarLv = 2;</code>
     */
    public Builder clearHeroStarLv() {
      bitField0_ = (bitField0_ & ~0x00000002);
      heroStarLv_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.Dhero)
  }

  // @@protoc_insertion_point(class_scope:client2server.Dhero)
  private static final pb4client.Dhero DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.Dhero();
  }

  public static pb4client.Dhero getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<Dhero>
      PARSER = new com.google.protobuf.AbstractParser<Dhero>() {
    public Dhero parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new Dhero(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Dhero> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Dhero> getParserForType() {
    return PARSER;
  }

  public pb4client.Dhero getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

