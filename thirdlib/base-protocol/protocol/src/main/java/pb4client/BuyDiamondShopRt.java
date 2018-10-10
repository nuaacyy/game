// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.BuyDiamondShopRt}
 */
public  final class BuyDiamondShopRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.BuyDiamondShopRt)
    BuyDiamondShopRtOrBuilder {
  // Use BuyDiamondShopRt.newBuilder() to construct.
  private BuyDiamondShopRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private BuyDiamondShopRt() {
    rt_ = 0;
    bigType_ = 0;
    smallType_ = 0;
    buyNum_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private BuyDiamondShopRt(
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
            rt_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            bigType_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            smallType_ = input.readInt32();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            buyNum_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_BuyDiamondShopRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_BuyDiamondShopRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.BuyDiamondShopRt.class, pb4client.BuyDiamondShopRt.Builder.class);
  }

  private int bitField0_;
  public static final int RT_FIELD_NUMBER = 1;
  private int rt_;
  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  public boolean hasRt() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  public int getRt() {
    return rt_;
  }

  public static final int BIGTYPE_FIELD_NUMBER = 2;
  private int bigType_;
  /**
   * <pre>
   * 购买的物品所在页签
   * </pre>
   *
   * <code>optional int32 bigType = 2;</code>
   */
  public boolean hasBigType() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 购买的物品所在页签
   * </pre>
   *
   * <code>optional int32 bigType = 2;</code>
   */
  public int getBigType() {
    return bigType_;
  }

  public static final int SMALLTYPE_FIELD_NUMBER = 3;
  private int smallType_;
  /**
   * <pre>
   * 购买的物品所在位置
   * </pre>
   *
   * <code>optional int32 smallType = 3;</code>
   */
  public boolean hasSmallType() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 购买的物品所在位置
   * </pre>
   *
   * <code>optional int32 smallType = 3;</code>
   */
  public int getSmallType() {
    return smallType_;
  }

  public static final int BUYNUM_FIELD_NUMBER = 4;
  private int buyNum_;
  /**
   * <pre>
   * 购买的物品数量
   * </pre>
   *
   * <code>optional int32 buyNum = 4;</code>
   */
  public boolean hasBuyNum() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   * 购买的物品数量
   * </pre>
   *
   * <code>optional int32 buyNum = 4;</code>
   */
  public int getBuyNum() {
    return buyNum_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasRt()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, rt_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, bigType_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, smallType_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, buyNum_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, rt_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, bigType_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, smallType_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, buyNum_);
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
    if (!(obj instanceof pb4client.BuyDiamondShopRt)) {
      return super.equals(obj);
    }
    pb4client.BuyDiamondShopRt other = (pb4client.BuyDiamondShopRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && (hasBigType() == other.hasBigType());
    if (hasBigType()) {
      result = result && (getBigType()
          == other.getBigType());
    }
    result = result && (hasSmallType() == other.hasSmallType());
    if (hasSmallType()) {
      result = result && (getSmallType()
          == other.getSmallType());
    }
    result = result && (hasBuyNum() == other.hasBuyNum());
    if (hasBuyNum()) {
      result = result && (getBuyNum()
          == other.getBuyNum());
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
    if (hasRt()) {
      hash = (37 * hash) + RT_FIELD_NUMBER;
      hash = (53 * hash) + getRt();
    }
    if (hasBigType()) {
      hash = (37 * hash) + BIGTYPE_FIELD_NUMBER;
      hash = (53 * hash) + getBigType();
    }
    if (hasSmallType()) {
      hash = (37 * hash) + SMALLTYPE_FIELD_NUMBER;
      hash = (53 * hash) + getSmallType();
    }
    if (hasBuyNum()) {
      hash = (37 * hash) + BUYNUM_FIELD_NUMBER;
      hash = (53 * hash) + getBuyNum();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.BuyDiamondShopRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.BuyDiamondShopRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.BuyDiamondShopRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.BuyDiamondShopRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.BuyDiamondShopRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.BuyDiamondShopRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.BuyDiamondShopRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.BuyDiamondShopRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.BuyDiamondShopRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.BuyDiamondShopRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.BuyDiamondShopRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.BuyDiamondShopRt parseFrom(
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
  public static Builder newBuilder(pb4client.BuyDiamondShopRt prototype) {
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
   * Protobuf type {@code client2server.BuyDiamondShopRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.BuyDiamondShopRt)
      pb4client.BuyDiamondShopRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_BuyDiamondShopRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_BuyDiamondShopRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.BuyDiamondShopRt.class, pb4client.BuyDiamondShopRt.Builder.class);
    }

    // Construct using pb4client.BuyDiamondShopRt.newBuilder()
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
      bitField0_ = (bitField0_ & ~0x00000001);
      bigType_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      smallType_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      buyNum_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_BuyDiamondShopRt_descriptor;
    }

    public pb4client.BuyDiamondShopRt getDefaultInstanceForType() {
      return pb4client.BuyDiamondShopRt.getDefaultInstance();
    }

    public pb4client.BuyDiamondShopRt build() {
      pb4client.BuyDiamondShopRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.BuyDiamondShopRt buildPartial() {
      pb4client.BuyDiamondShopRt result = new pb4client.BuyDiamondShopRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.bigType_ = bigType_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.smallType_ = smallType_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.buyNum_ = buyNum_;
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
      if (other instanceof pb4client.BuyDiamondShopRt) {
        return mergeFrom((pb4client.BuyDiamondShopRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.BuyDiamondShopRt other) {
      if (other == pb4client.BuyDiamondShopRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (other.hasBigType()) {
        setBigType(other.getBigType());
      }
      if (other.hasSmallType()) {
        setSmallType(other.getSmallType());
      }
      if (other.hasBuyNum()) {
        setBuyNum(other.getBuyNum());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasRt()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.BuyDiamondShopRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.BuyDiamondShopRt) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int rt_ ;
    /**
     * <pre>
     * 返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public boolean hasRt() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public int getRt() {
      return rt_;
    }
    /**
     * <pre>
     * 返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public Builder setRt(int value) {
      bitField0_ |= 0x00000001;
      rt_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public Builder clearRt() {
      bitField0_ = (bitField0_ & ~0x00000001);
      rt_ = 0;
      onChanged();
      return this;
    }

    private int bigType_ ;
    /**
     * <pre>
     * 购买的物品所在页签
     * </pre>
     *
     * <code>optional int32 bigType = 2;</code>
     */
    public boolean hasBigType() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 购买的物品所在页签
     * </pre>
     *
     * <code>optional int32 bigType = 2;</code>
     */
    public int getBigType() {
      return bigType_;
    }
    /**
     * <pre>
     * 购买的物品所在页签
     * </pre>
     *
     * <code>optional int32 bigType = 2;</code>
     */
    public Builder setBigType(int value) {
      bitField0_ |= 0x00000002;
      bigType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 购买的物品所在页签
     * </pre>
     *
     * <code>optional int32 bigType = 2;</code>
     */
    public Builder clearBigType() {
      bitField0_ = (bitField0_ & ~0x00000002);
      bigType_ = 0;
      onChanged();
      return this;
    }

    private int smallType_ ;
    /**
     * <pre>
     * 购买的物品所在位置
     * </pre>
     *
     * <code>optional int32 smallType = 3;</code>
     */
    public boolean hasSmallType() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 购买的物品所在位置
     * </pre>
     *
     * <code>optional int32 smallType = 3;</code>
     */
    public int getSmallType() {
      return smallType_;
    }
    /**
     * <pre>
     * 购买的物品所在位置
     * </pre>
     *
     * <code>optional int32 smallType = 3;</code>
     */
    public Builder setSmallType(int value) {
      bitField0_ |= 0x00000004;
      smallType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 购买的物品所在位置
     * </pre>
     *
     * <code>optional int32 smallType = 3;</code>
     */
    public Builder clearSmallType() {
      bitField0_ = (bitField0_ & ~0x00000004);
      smallType_ = 0;
      onChanged();
      return this;
    }

    private int buyNum_ ;
    /**
     * <pre>
     * 购买的物品数量
     * </pre>
     *
     * <code>optional int32 buyNum = 4;</code>
     */
    public boolean hasBuyNum() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     * 购买的物品数量
     * </pre>
     *
     * <code>optional int32 buyNum = 4;</code>
     */
    public int getBuyNum() {
      return buyNum_;
    }
    /**
     * <pre>
     * 购买的物品数量
     * </pre>
     *
     * <code>optional int32 buyNum = 4;</code>
     */
    public Builder setBuyNum(int value) {
      bitField0_ |= 0x00000008;
      buyNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 购买的物品数量
     * </pre>
     *
     * <code>optional int32 buyNum = 4;</code>
     */
    public Builder clearBuyNum() {
      bitField0_ = (bitField0_ & ~0x00000008);
      buyNum_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.BuyDiamondShopRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.BuyDiamondShopRt)
  private static final pb4client.BuyDiamondShopRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.BuyDiamondShopRt();
  }

  public static pb4client.BuyDiamondShopRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<BuyDiamondShopRt>
      PARSER = new com.google.protobuf.AbstractParser<BuyDiamondShopRt>() {
    public BuyDiamondShopRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new BuyDiamondShopRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<BuyDiamondShopRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<BuyDiamondShopRt> getParserForType() {
    return PARSER;
  }

  public pb4client.BuyDiamondShopRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

