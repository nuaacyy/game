// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.AllianceBigGiftVo}
 */
public  final class AllianceBigGiftVo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.AllianceBigGiftVo)
    AllianceBigGiftVoOrBuilder {
  // Use AllianceBigGiftVo.newBuilder() to construct.
  private AllianceBigGiftVo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllianceBigGiftVo() {
    bigGiftId_ = 0;
    bigGiftExp_ = 0;
    giftLv_ = 0;
    giftExp_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private AllianceBigGiftVo(
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

            bigGiftId_ = input.readInt32();
            break;
          }
          case 16: {

            bigGiftExp_ = input.readInt32();
            break;
          }
          case 24: {

            giftLv_ = input.readInt32();
            break;
          }
          case 32: {

            giftExp_ = input.readInt32();
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
    return pb4server.InternalPkt.internal_static_pb4server_AllianceBigGiftVo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_AllianceBigGiftVo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.AllianceBigGiftVo.class, pb4server.AllianceBigGiftVo.Builder.class);
  }

  public static final int BIGGIFTID_FIELD_NUMBER = 1;
  private int bigGiftId_;
  /**
   * <pre>
   * 左侧大礼物ID
   * </pre>
   *
   * <code>int32 bigGiftId = 1;</code>
   */
  public int getBigGiftId() {
    return bigGiftId_;
  }

  public static final int BIGGIFTEXP_FIELD_NUMBER = 2;
  private int bigGiftExp_;
  /**
   * <pre>
   * 左侧大礼物钥匙值
   * </pre>
   *
   * <code>int32 bigGiftExp = 2;</code>
   */
  public int getBigGiftExp() {
    return bigGiftExp_;
  }

  public static final int GIFTLV_FIELD_NUMBER = 3;
  private int giftLv_;
  /**
   * <pre>
   * 上侧的礼物等级
   * </pre>
   *
   * <code>int32 giftLv = 3;</code>
   */
  public int getGiftLv() {
    return giftLv_;
  }

  public static final int GIFTEXP_FIELD_NUMBER = 4;
  private int giftExp_;
  /**
   * <pre>
   * 上侧的礼物经验
   * </pre>
   *
   * <code>int32 giftExp = 4;</code>
   */
  public int getGiftExp() {
    return giftExp_;
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
    if (bigGiftId_ != 0) {
      output.writeInt32(1, bigGiftId_);
    }
    if (bigGiftExp_ != 0) {
      output.writeInt32(2, bigGiftExp_);
    }
    if (giftLv_ != 0) {
      output.writeInt32(3, giftLv_);
    }
    if (giftExp_ != 0) {
      output.writeInt32(4, giftExp_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (bigGiftId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, bigGiftId_);
    }
    if (bigGiftExp_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, bigGiftExp_);
    }
    if (giftLv_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, giftLv_);
    }
    if (giftExp_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, giftExp_);
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
    if (!(obj instanceof pb4server.AllianceBigGiftVo)) {
      return super.equals(obj);
    }
    pb4server.AllianceBigGiftVo other = (pb4server.AllianceBigGiftVo) obj;

    boolean result = true;
    result = result && (getBigGiftId()
        == other.getBigGiftId());
    result = result && (getBigGiftExp()
        == other.getBigGiftExp());
    result = result && (getGiftLv()
        == other.getGiftLv());
    result = result && (getGiftExp()
        == other.getGiftExp());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + BIGGIFTID_FIELD_NUMBER;
    hash = (53 * hash) + getBigGiftId();
    hash = (37 * hash) + BIGGIFTEXP_FIELD_NUMBER;
    hash = (53 * hash) + getBigGiftExp();
    hash = (37 * hash) + GIFTLV_FIELD_NUMBER;
    hash = (53 * hash) + getGiftLv();
    hash = (37 * hash) + GIFTEXP_FIELD_NUMBER;
    hash = (53 * hash) + getGiftExp();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.AllianceBigGiftVo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceBigGiftVo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceBigGiftVo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceBigGiftVo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceBigGiftVo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceBigGiftVo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceBigGiftVo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AllianceBigGiftVo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AllianceBigGiftVo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.AllianceBigGiftVo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AllianceBigGiftVo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AllianceBigGiftVo parseFrom(
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
  public static Builder newBuilder(pb4server.AllianceBigGiftVo prototype) {
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
   * Protobuf type {@code pb4server.AllianceBigGiftVo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.AllianceBigGiftVo)
      pb4server.AllianceBigGiftVoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_AllianceBigGiftVo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_AllianceBigGiftVo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.AllianceBigGiftVo.class, pb4server.AllianceBigGiftVo.Builder.class);
    }

    // Construct using pb4server.AllianceBigGiftVo.newBuilder()
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
      bigGiftId_ = 0;

      bigGiftExp_ = 0;

      giftLv_ = 0;

      giftExp_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_AllianceBigGiftVo_descriptor;
    }

    public pb4server.AllianceBigGiftVo getDefaultInstanceForType() {
      return pb4server.AllianceBigGiftVo.getDefaultInstance();
    }

    public pb4server.AllianceBigGiftVo build() {
      pb4server.AllianceBigGiftVo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.AllianceBigGiftVo buildPartial() {
      pb4server.AllianceBigGiftVo result = new pb4server.AllianceBigGiftVo(this);
      result.bigGiftId_ = bigGiftId_;
      result.bigGiftExp_ = bigGiftExp_;
      result.giftLv_ = giftLv_;
      result.giftExp_ = giftExp_;
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
      if (other instanceof pb4server.AllianceBigGiftVo) {
        return mergeFrom((pb4server.AllianceBigGiftVo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.AllianceBigGiftVo other) {
      if (other == pb4server.AllianceBigGiftVo.getDefaultInstance()) return this;
      if (other.getBigGiftId() != 0) {
        setBigGiftId(other.getBigGiftId());
      }
      if (other.getBigGiftExp() != 0) {
        setBigGiftExp(other.getBigGiftExp());
      }
      if (other.getGiftLv() != 0) {
        setGiftLv(other.getGiftLv());
      }
      if (other.getGiftExp() != 0) {
        setGiftExp(other.getGiftExp());
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
      pb4server.AllianceBigGiftVo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.AllianceBigGiftVo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int bigGiftId_ ;
    /**
     * <pre>
     * 左侧大礼物ID
     * </pre>
     *
     * <code>int32 bigGiftId = 1;</code>
     */
    public int getBigGiftId() {
      return bigGiftId_;
    }
    /**
     * <pre>
     * 左侧大礼物ID
     * </pre>
     *
     * <code>int32 bigGiftId = 1;</code>
     */
    public Builder setBigGiftId(int value) {
      
      bigGiftId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 左侧大礼物ID
     * </pre>
     *
     * <code>int32 bigGiftId = 1;</code>
     */
    public Builder clearBigGiftId() {
      
      bigGiftId_ = 0;
      onChanged();
      return this;
    }

    private int bigGiftExp_ ;
    /**
     * <pre>
     * 左侧大礼物钥匙值
     * </pre>
     *
     * <code>int32 bigGiftExp = 2;</code>
     */
    public int getBigGiftExp() {
      return bigGiftExp_;
    }
    /**
     * <pre>
     * 左侧大礼物钥匙值
     * </pre>
     *
     * <code>int32 bigGiftExp = 2;</code>
     */
    public Builder setBigGiftExp(int value) {
      
      bigGiftExp_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 左侧大礼物钥匙值
     * </pre>
     *
     * <code>int32 bigGiftExp = 2;</code>
     */
    public Builder clearBigGiftExp() {
      
      bigGiftExp_ = 0;
      onChanged();
      return this;
    }

    private int giftLv_ ;
    /**
     * <pre>
     * 上侧的礼物等级
     * </pre>
     *
     * <code>int32 giftLv = 3;</code>
     */
    public int getGiftLv() {
      return giftLv_;
    }
    /**
     * <pre>
     * 上侧的礼物等级
     * </pre>
     *
     * <code>int32 giftLv = 3;</code>
     */
    public Builder setGiftLv(int value) {
      
      giftLv_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 上侧的礼物等级
     * </pre>
     *
     * <code>int32 giftLv = 3;</code>
     */
    public Builder clearGiftLv() {
      
      giftLv_ = 0;
      onChanged();
      return this;
    }

    private int giftExp_ ;
    /**
     * <pre>
     * 上侧的礼物经验
     * </pre>
     *
     * <code>int32 giftExp = 4;</code>
     */
    public int getGiftExp() {
      return giftExp_;
    }
    /**
     * <pre>
     * 上侧的礼物经验
     * </pre>
     *
     * <code>int32 giftExp = 4;</code>
     */
    public Builder setGiftExp(int value) {
      
      giftExp_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 上侧的礼物经验
     * </pre>
     *
     * <code>int32 giftExp = 4;</code>
     */
    public Builder clearGiftExp() {
      
      giftExp_ = 0;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.AllianceBigGiftVo)
  }

  // @@protoc_insertion_point(class_scope:pb4server.AllianceBigGiftVo)
  private static final pb4server.AllianceBigGiftVo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.AllianceBigGiftVo();
  }

  public static pb4server.AllianceBigGiftVo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AllianceBigGiftVo>
      PARSER = new com.google.protobuf.AbstractParser<AllianceBigGiftVo>() {
    public AllianceBigGiftVo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AllianceBigGiftVo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllianceBigGiftVo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllianceBigGiftVo> getParserForType() {
    return PARSER;
  }

  public pb4server.AllianceBigGiftVo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

