// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.AllianceShopInfo}
 */
public  final class AllianceShopInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.AllianceShopInfo)
    AllianceShopInfoOrBuilder {
  // Use AllianceShopInfo.newBuilder() to construct.
  private AllianceShopInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllianceShopInfo() {
    shopAddress_ = 0;
    propsId_ = 0;
    shopNum_ = 0;
    buyState_ = 0;
    costValue_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AllianceShopInfo(
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
            shopAddress_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            propsId_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            shopNum_ = input.readInt32();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            buyState_ = input.readInt32();
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            costValue_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_AllianceShopInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_AllianceShopInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.AllianceShopInfo.class, pb4client.AllianceShopInfo.Builder.class);
  }

  private int bitField0_;
  public static final int SHOPADDRESS_FIELD_NUMBER = 1;
  private int shopAddress_;
  /**
   * <pre>
   *在市场中的位置
   * </pre>
   *
   * <code>required int32 shopAddress = 1;</code>
   */
  public boolean hasShopAddress() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *在市场中的位置
   * </pre>
   *
   * <code>required int32 shopAddress = 1;</code>
   */
  public int getShopAddress() {
    return shopAddress_;
  }

  public static final int PROPSID_FIELD_NUMBER = 2;
  private int propsId_;
  /**
   * <pre>
   *模板唯一ID
   * </pre>
   *
   * <code>required int32 propsId = 2;</code>
   */
  public boolean hasPropsId() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *模板唯一ID
   * </pre>
   *
   * <code>required int32 propsId = 2;</code>
   */
  public int getPropsId() {
    return propsId_;
  }

  public static final int SHOPNUM_FIELD_NUMBER = 3;
  private int shopNum_;
  /**
   * <pre>
   *数量
   * </pre>
   *
   * <code>required int32 shopNum = 3;</code>
   */
  public boolean hasShopNum() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   *数量
   * </pre>
   *
   * <code>required int32 shopNum = 3;</code>
   */
  public int getShopNum() {
    return shopNum_;
  }

  public static final int BUYSTATE_FIELD_NUMBER = 4;
  private int buyState_;
  /**
   * <pre>
   *购买状态  0-未购买  1-已买
   * </pre>
   *
   * <code>required int32 buyState = 4;</code>
   */
  public boolean hasBuyState() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   *购买状态  0-未购买  1-已买
   * </pre>
   *
   * <code>required int32 buyState = 4;</code>
   */
  public int getBuyState() {
    return buyState_;
  }

  public static final int COSTVALUE_FIELD_NUMBER = 5;
  private int costValue_;
  /**
   * <pre>
   *价格值
   * </pre>
   *
   * <code>required int32 costValue = 5;</code>
   */
  public boolean hasCostValue() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <pre>
   *价格值
   * </pre>
   *
   * <code>required int32 costValue = 5;</code>
   */
  public int getCostValue() {
    return costValue_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasShopAddress()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasPropsId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasShopNum()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasBuyState()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasCostValue()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, shopAddress_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, propsId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, shopNum_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, buyState_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt32(5, costValue_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, shopAddress_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, propsId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, shopNum_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, buyState_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, costValue_);
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
    if (!(obj instanceof pb4client.AllianceShopInfo)) {
      return super.equals(obj);
    }
    pb4client.AllianceShopInfo other = (pb4client.AllianceShopInfo) obj;

    boolean result = true;
    result = result && (hasShopAddress() == other.hasShopAddress());
    if (hasShopAddress()) {
      result = result && (getShopAddress()
          == other.getShopAddress());
    }
    result = result && (hasPropsId() == other.hasPropsId());
    if (hasPropsId()) {
      result = result && (getPropsId()
          == other.getPropsId());
    }
    result = result && (hasShopNum() == other.hasShopNum());
    if (hasShopNum()) {
      result = result && (getShopNum()
          == other.getShopNum());
    }
    result = result && (hasBuyState() == other.hasBuyState());
    if (hasBuyState()) {
      result = result && (getBuyState()
          == other.getBuyState());
    }
    result = result && (hasCostValue() == other.hasCostValue());
    if (hasCostValue()) {
      result = result && (getCostValue()
          == other.getCostValue());
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
    if (hasShopAddress()) {
      hash = (37 * hash) + SHOPADDRESS_FIELD_NUMBER;
      hash = (53 * hash) + getShopAddress();
    }
    if (hasPropsId()) {
      hash = (37 * hash) + PROPSID_FIELD_NUMBER;
      hash = (53 * hash) + getPropsId();
    }
    if (hasShopNum()) {
      hash = (37 * hash) + SHOPNUM_FIELD_NUMBER;
      hash = (53 * hash) + getShopNum();
    }
    if (hasBuyState()) {
      hash = (37 * hash) + BUYSTATE_FIELD_NUMBER;
      hash = (53 * hash) + getBuyState();
    }
    if (hasCostValue()) {
      hash = (37 * hash) + COSTVALUE_FIELD_NUMBER;
      hash = (53 * hash) + getCostValue();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.AllianceShopInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceShopInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceShopInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceShopInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceShopInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceShopInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceShopInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AllianceShopInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AllianceShopInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.AllianceShopInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AllianceShopInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AllianceShopInfo parseFrom(
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
  public static Builder newBuilder(pb4client.AllianceShopInfo prototype) {
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
   * Protobuf type {@code client2server.AllianceShopInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.AllianceShopInfo)
      pb4client.AllianceShopInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceShopInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceShopInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.AllianceShopInfo.class, pb4client.AllianceShopInfo.Builder.class);
    }

    // Construct using pb4client.AllianceShopInfo.newBuilder()
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
      shopAddress_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      propsId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      shopNum_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      buyState_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      costValue_ = 0;
      bitField0_ = (bitField0_ & ~0x00000010);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceShopInfo_descriptor;
    }

    public pb4client.AllianceShopInfo getDefaultInstanceForType() {
      return pb4client.AllianceShopInfo.getDefaultInstance();
    }

    public pb4client.AllianceShopInfo build() {
      pb4client.AllianceShopInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.AllianceShopInfo buildPartial() {
      pb4client.AllianceShopInfo result = new pb4client.AllianceShopInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.shopAddress_ = shopAddress_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.propsId_ = propsId_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.shopNum_ = shopNum_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.buyState_ = buyState_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.costValue_ = costValue_;
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
      if (other instanceof pb4client.AllianceShopInfo) {
        return mergeFrom((pb4client.AllianceShopInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.AllianceShopInfo other) {
      if (other == pb4client.AllianceShopInfo.getDefaultInstance()) return this;
      if (other.hasShopAddress()) {
        setShopAddress(other.getShopAddress());
      }
      if (other.hasPropsId()) {
        setPropsId(other.getPropsId());
      }
      if (other.hasShopNum()) {
        setShopNum(other.getShopNum());
      }
      if (other.hasBuyState()) {
        setBuyState(other.getBuyState());
      }
      if (other.hasCostValue()) {
        setCostValue(other.getCostValue());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasShopAddress()) {
        return false;
      }
      if (!hasPropsId()) {
        return false;
      }
      if (!hasShopNum()) {
        return false;
      }
      if (!hasBuyState()) {
        return false;
      }
      if (!hasCostValue()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.AllianceShopInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.AllianceShopInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int shopAddress_ ;
    /**
     * <pre>
     *在市场中的位置
     * </pre>
     *
     * <code>required int32 shopAddress = 1;</code>
     */
    public boolean hasShopAddress() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *在市场中的位置
     * </pre>
     *
     * <code>required int32 shopAddress = 1;</code>
     */
    public int getShopAddress() {
      return shopAddress_;
    }
    /**
     * <pre>
     *在市场中的位置
     * </pre>
     *
     * <code>required int32 shopAddress = 1;</code>
     */
    public Builder setShopAddress(int value) {
      bitField0_ |= 0x00000001;
      shopAddress_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *在市场中的位置
     * </pre>
     *
     * <code>required int32 shopAddress = 1;</code>
     */
    public Builder clearShopAddress() {
      bitField0_ = (bitField0_ & ~0x00000001);
      shopAddress_ = 0;
      onChanged();
      return this;
    }

    private int propsId_ ;
    /**
     * <pre>
     *模板唯一ID
     * </pre>
     *
     * <code>required int32 propsId = 2;</code>
     */
    public boolean hasPropsId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *模板唯一ID
     * </pre>
     *
     * <code>required int32 propsId = 2;</code>
     */
    public int getPropsId() {
      return propsId_;
    }
    /**
     * <pre>
     *模板唯一ID
     * </pre>
     *
     * <code>required int32 propsId = 2;</code>
     */
    public Builder setPropsId(int value) {
      bitField0_ |= 0x00000002;
      propsId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *模板唯一ID
     * </pre>
     *
     * <code>required int32 propsId = 2;</code>
     */
    public Builder clearPropsId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      propsId_ = 0;
      onChanged();
      return this;
    }

    private int shopNum_ ;
    /**
     * <pre>
     *数量
     * </pre>
     *
     * <code>required int32 shopNum = 3;</code>
     */
    public boolean hasShopNum() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     *数量
     * </pre>
     *
     * <code>required int32 shopNum = 3;</code>
     */
    public int getShopNum() {
      return shopNum_;
    }
    /**
     * <pre>
     *数量
     * </pre>
     *
     * <code>required int32 shopNum = 3;</code>
     */
    public Builder setShopNum(int value) {
      bitField0_ |= 0x00000004;
      shopNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *数量
     * </pre>
     *
     * <code>required int32 shopNum = 3;</code>
     */
    public Builder clearShopNum() {
      bitField0_ = (bitField0_ & ~0x00000004);
      shopNum_ = 0;
      onChanged();
      return this;
    }

    private int buyState_ ;
    /**
     * <pre>
     *购买状态  0-未购买  1-已买
     * </pre>
     *
     * <code>required int32 buyState = 4;</code>
     */
    public boolean hasBuyState() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     *购买状态  0-未购买  1-已买
     * </pre>
     *
     * <code>required int32 buyState = 4;</code>
     */
    public int getBuyState() {
      return buyState_;
    }
    /**
     * <pre>
     *购买状态  0-未购买  1-已买
     * </pre>
     *
     * <code>required int32 buyState = 4;</code>
     */
    public Builder setBuyState(int value) {
      bitField0_ |= 0x00000008;
      buyState_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *购买状态  0-未购买  1-已买
     * </pre>
     *
     * <code>required int32 buyState = 4;</code>
     */
    public Builder clearBuyState() {
      bitField0_ = (bitField0_ & ~0x00000008);
      buyState_ = 0;
      onChanged();
      return this;
    }

    private int costValue_ ;
    /**
     * <pre>
     *价格值
     * </pre>
     *
     * <code>required int32 costValue = 5;</code>
     */
    public boolean hasCostValue() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     *价格值
     * </pre>
     *
     * <code>required int32 costValue = 5;</code>
     */
    public int getCostValue() {
      return costValue_;
    }
    /**
     * <pre>
     *价格值
     * </pre>
     *
     * <code>required int32 costValue = 5;</code>
     */
    public Builder setCostValue(int value) {
      bitField0_ |= 0x00000010;
      costValue_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *价格值
     * </pre>
     *
     * <code>required int32 costValue = 5;</code>
     */
    public Builder clearCostValue() {
      bitField0_ = (bitField0_ & ~0x00000010);
      costValue_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.AllianceShopInfo)
  }

  // @@protoc_insertion_point(class_scope:client2server.AllianceShopInfo)
  private static final pb4client.AllianceShopInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.AllianceShopInfo();
  }

  public static pb4client.AllianceShopInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<AllianceShopInfo>
      PARSER = new com.google.protobuf.AbstractParser<AllianceShopInfo>() {
    public AllianceShopInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AllianceShopInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllianceShopInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllianceShopInfo> getParserForType() {
    return PARSER;
  }

  public pb4client.AllianceShopInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

