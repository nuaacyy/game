// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.JjcShopInfo}
 */
public  final class JjcShopInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.JjcShopInfo)
    JjcShopInfoOrBuilder {
  // Use JjcShopInfo.newBuilder() to construct.
  private JjcShopInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private JjcShopInfo() {
    id_ = 0;
    type_ = 0;
    protoId_ = 0;
    num_ = 0;
    resType_ = 0;
    resCost_ = 0;
    isBuy_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private JjcShopInfo(
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
            id_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            type_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            protoId_ = input.readInt32();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            num_ = input.readInt32();
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            resType_ = input.readInt32();
            break;
          }
          case 48: {
            bitField0_ |= 0x00000020;
            resCost_ = input.readInt32();
            break;
          }
          case 56: {
            bitField0_ |= 0x00000040;
            isBuy_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_JjcShopInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_JjcShopInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.JjcShopInfo.class, pb4client.JjcShopInfo.Builder.class);
  }

  private int bitField0_;
  public static final int ID_FIELD_NUMBER = 1;
  private int id_;
  /**
   * <pre>
   * 商品ID (arenaShop.xml.id)
   * </pre>
   *
   * <code>required int32 id = 1;</code>
   */
  public boolean hasId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 商品ID (arenaShop.xml.id)
   * </pre>
   *
   * <code>required int32 id = 1;</code>
   */
  public int getId() {
    return id_;
  }

  public static final int TYPE_FIELD_NUMBER = 2;
  private int type_;
  /**
   * <pre>
   * 类型：1-道具 2-武将卡
   * </pre>
   *
   * <code>required int32 type = 2;</code>
   */
  public boolean hasType() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 类型：1-道具 2-武将卡
   * </pre>
   *
   * <code>required int32 type = 2;</code>
   */
  public int getType() {
    return type_;
  }

  public static final int PROTOID_FIELD_NUMBER = 3;
  private int protoId_;
  /**
   * <pre>
   * 道具ID或模版ID
   * </pre>
   *
   * <code>required int32 protoId = 3;</code>
   */
  public boolean hasProtoId() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 道具ID或模版ID
   * </pre>
   *
   * <code>required int32 protoId = 3;</code>
   */
  public int getProtoId() {
    return protoId_;
  }

  public static final int NUM_FIELD_NUMBER = 4;
  private int num_;
  /**
   * <pre>
   * 数量
   * </pre>
   *
   * <code>required int32 num = 4;</code>
   */
  public boolean hasNum() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   * 数量
   * </pre>
   *
   * <code>required int32 num = 4;</code>
   */
  public int getNum() {
    return num_;
  }

  public static final int RESTYPE_FIELD_NUMBER = 5;
  private int resType_;
  /**
   * <pre>
   * 购买货币类型
   * </pre>
   *
   * <code>required int32 resType = 5;</code>
   */
  public boolean hasResType() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <pre>
   * 购买货币类型
   * </pre>
   *
   * <code>required int32 resType = 5;</code>
   */
  public int getResType() {
    return resType_;
  }

  public static final int RESCOST_FIELD_NUMBER = 6;
  private int resCost_;
  /**
   * <pre>
   * 购买货币价格
   * </pre>
   *
   * <code>required int32 resCost = 6;</code>
   */
  public boolean hasResCost() {
    return ((bitField0_ & 0x00000020) == 0x00000020);
  }
  /**
   * <pre>
   * 购买货币价格
   * </pre>
   *
   * <code>required int32 resCost = 6;</code>
   */
  public int getResCost() {
    return resCost_;
  }

  public static final int ISBUY_FIELD_NUMBER = 7;
  private int isBuy_;
  /**
   * <pre>
   * 是否已经购买 1-已购买 0-未购买
   * </pre>
   *
   * <code>required int32 isBuy = 7;</code>
   */
  public boolean hasIsBuy() {
    return ((bitField0_ & 0x00000040) == 0x00000040);
  }
  /**
   * <pre>
   * 是否已经购买 1-已购买 0-未购买
   * </pre>
   *
   * <code>required int32 isBuy = 7;</code>
   */
  public int getIsBuy() {
    return isBuy_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasType()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasProtoId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasNum()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasResType()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasResCost()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasIsBuy()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, id_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, type_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, protoId_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, num_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt32(5, resType_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      output.writeInt32(6, resCost_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      output.writeInt32(7, isBuy_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, id_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, type_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, protoId_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, num_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, resType_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, resCost_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, isBuy_);
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
    if (!(obj instanceof pb4client.JjcShopInfo)) {
      return super.equals(obj);
    }
    pb4client.JjcShopInfo other = (pb4client.JjcShopInfo) obj;

    boolean result = true;
    result = result && (hasId() == other.hasId());
    if (hasId()) {
      result = result && (getId()
          == other.getId());
    }
    result = result && (hasType() == other.hasType());
    if (hasType()) {
      result = result && (getType()
          == other.getType());
    }
    result = result && (hasProtoId() == other.hasProtoId());
    if (hasProtoId()) {
      result = result && (getProtoId()
          == other.getProtoId());
    }
    result = result && (hasNum() == other.hasNum());
    if (hasNum()) {
      result = result && (getNum()
          == other.getNum());
    }
    result = result && (hasResType() == other.hasResType());
    if (hasResType()) {
      result = result && (getResType()
          == other.getResType());
    }
    result = result && (hasResCost() == other.hasResCost());
    if (hasResCost()) {
      result = result && (getResCost()
          == other.getResCost());
    }
    result = result && (hasIsBuy() == other.hasIsBuy());
    if (hasIsBuy()) {
      result = result && (getIsBuy()
          == other.getIsBuy());
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
    if (hasId()) {
      hash = (37 * hash) + ID_FIELD_NUMBER;
      hash = (53 * hash) + getId();
    }
    if (hasType()) {
      hash = (37 * hash) + TYPE_FIELD_NUMBER;
      hash = (53 * hash) + getType();
    }
    if (hasProtoId()) {
      hash = (37 * hash) + PROTOID_FIELD_NUMBER;
      hash = (53 * hash) + getProtoId();
    }
    if (hasNum()) {
      hash = (37 * hash) + NUM_FIELD_NUMBER;
      hash = (53 * hash) + getNum();
    }
    if (hasResType()) {
      hash = (37 * hash) + RESTYPE_FIELD_NUMBER;
      hash = (53 * hash) + getResType();
    }
    if (hasResCost()) {
      hash = (37 * hash) + RESCOST_FIELD_NUMBER;
      hash = (53 * hash) + getResCost();
    }
    if (hasIsBuy()) {
      hash = (37 * hash) + ISBUY_FIELD_NUMBER;
      hash = (53 * hash) + getIsBuy();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.JjcShopInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.JjcShopInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.JjcShopInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.JjcShopInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.JjcShopInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.JjcShopInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.JjcShopInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.JjcShopInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.JjcShopInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.JjcShopInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.JjcShopInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.JjcShopInfo parseFrom(
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
  public static Builder newBuilder(pb4client.JjcShopInfo prototype) {
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
   * Protobuf type {@code client2server.JjcShopInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.JjcShopInfo)
      pb4client.JjcShopInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_JjcShopInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_JjcShopInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.JjcShopInfo.class, pb4client.JjcShopInfo.Builder.class);
    }

    // Construct using pb4client.JjcShopInfo.newBuilder()
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
      id_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      type_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      protoId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      num_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      resType_ = 0;
      bitField0_ = (bitField0_ & ~0x00000010);
      resCost_ = 0;
      bitField0_ = (bitField0_ & ~0x00000020);
      isBuy_ = 0;
      bitField0_ = (bitField0_ & ~0x00000040);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_JjcShopInfo_descriptor;
    }

    public pb4client.JjcShopInfo getDefaultInstanceForType() {
      return pb4client.JjcShopInfo.getDefaultInstance();
    }

    public pb4client.JjcShopInfo build() {
      pb4client.JjcShopInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.JjcShopInfo buildPartial() {
      pb4client.JjcShopInfo result = new pb4client.JjcShopInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.id_ = id_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.type_ = type_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.protoId_ = protoId_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.num_ = num_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.resType_ = resType_;
      if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
        to_bitField0_ |= 0x00000020;
      }
      result.resCost_ = resCost_;
      if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
        to_bitField0_ |= 0x00000040;
      }
      result.isBuy_ = isBuy_;
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
      if (other instanceof pb4client.JjcShopInfo) {
        return mergeFrom((pb4client.JjcShopInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.JjcShopInfo other) {
      if (other == pb4client.JjcShopInfo.getDefaultInstance()) return this;
      if (other.hasId()) {
        setId(other.getId());
      }
      if (other.hasType()) {
        setType(other.getType());
      }
      if (other.hasProtoId()) {
        setProtoId(other.getProtoId());
      }
      if (other.hasNum()) {
        setNum(other.getNum());
      }
      if (other.hasResType()) {
        setResType(other.getResType());
      }
      if (other.hasResCost()) {
        setResCost(other.getResCost());
      }
      if (other.hasIsBuy()) {
        setIsBuy(other.getIsBuy());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasId()) {
        return false;
      }
      if (!hasType()) {
        return false;
      }
      if (!hasProtoId()) {
        return false;
      }
      if (!hasNum()) {
        return false;
      }
      if (!hasResType()) {
        return false;
      }
      if (!hasResCost()) {
        return false;
      }
      if (!hasIsBuy()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.JjcShopInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.JjcShopInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int id_ ;
    /**
     * <pre>
     * 商品ID (arenaShop.xml.id)
     * </pre>
     *
     * <code>required int32 id = 1;</code>
     */
    public boolean hasId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 商品ID (arenaShop.xml.id)
     * </pre>
     *
     * <code>required int32 id = 1;</code>
     */
    public int getId() {
      return id_;
    }
    /**
     * <pre>
     * 商品ID (arenaShop.xml.id)
     * </pre>
     *
     * <code>required int32 id = 1;</code>
     */
    public Builder setId(int value) {
      bitField0_ |= 0x00000001;
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 商品ID (arenaShop.xml.id)
     * </pre>
     *
     * <code>required int32 id = 1;</code>
     */
    public Builder clearId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      id_ = 0;
      onChanged();
      return this;
    }

    private int type_ ;
    /**
     * <pre>
     * 类型：1-道具 2-武将卡
     * </pre>
     *
     * <code>required int32 type = 2;</code>
     */
    public boolean hasType() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 类型：1-道具 2-武将卡
     * </pre>
     *
     * <code>required int32 type = 2;</code>
     */
    public int getType() {
      return type_;
    }
    /**
     * <pre>
     * 类型：1-道具 2-武将卡
     * </pre>
     *
     * <code>required int32 type = 2;</code>
     */
    public Builder setType(int value) {
      bitField0_ |= 0x00000002;
      type_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 类型：1-道具 2-武将卡
     * </pre>
     *
     * <code>required int32 type = 2;</code>
     */
    public Builder clearType() {
      bitField0_ = (bitField0_ & ~0x00000002);
      type_ = 0;
      onChanged();
      return this;
    }

    private int protoId_ ;
    /**
     * <pre>
     * 道具ID或模版ID
     * </pre>
     *
     * <code>required int32 protoId = 3;</code>
     */
    public boolean hasProtoId() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 道具ID或模版ID
     * </pre>
     *
     * <code>required int32 protoId = 3;</code>
     */
    public int getProtoId() {
      return protoId_;
    }
    /**
     * <pre>
     * 道具ID或模版ID
     * </pre>
     *
     * <code>required int32 protoId = 3;</code>
     */
    public Builder setProtoId(int value) {
      bitField0_ |= 0x00000004;
      protoId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 道具ID或模版ID
     * </pre>
     *
     * <code>required int32 protoId = 3;</code>
     */
    public Builder clearProtoId() {
      bitField0_ = (bitField0_ & ~0x00000004);
      protoId_ = 0;
      onChanged();
      return this;
    }

    private int num_ ;
    /**
     * <pre>
     * 数量
     * </pre>
     *
     * <code>required int32 num = 4;</code>
     */
    public boolean hasNum() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     * 数量
     * </pre>
     *
     * <code>required int32 num = 4;</code>
     */
    public int getNum() {
      return num_;
    }
    /**
     * <pre>
     * 数量
     * </pre>
     *
     * <code>required int32 num = 4;</code>
     */
    public Builder setNum(int value) {
      bitField0_ |= 0x00000008;
      num_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 数量
     * </pre>
     *
     * <code>required int32 num = 4;</code>
     */
    public Builder clearNum() {
      bitField0_ = (bitField0_ & ~0x00000008);
      num_ = 0;
      onChanged();
      return this;
    }

    private int resType_ ;
    /**
     * <pre>
     * 购买货币类型
     * </pre>
     *
     * <code>required int32 resType = 5;</code>
     */
    public boolean hasResType() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     * 购买货币类型
     * </pre>
     *
     * <code>required int32 resType = 5;</code>
     */
    public int getResType() {
      return resType_;
    }
    /**
     * <pre>
     * 购买货币类型
     * </pre>
     *
     * <code>required int32 resType = 5;</code>
     */
    public Builder setResType(int value) {
      bitField0_ |= 0x00000010;
      resType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 购买货币类型
     * </pre>
     *
     * <code>required int32 resType = 5;</code>
     */
    public Builder clearResType() {
      bitField0_ = (bitField0_ & ~0x00000010);
      resType_ = 0;
      onChanged();
      return this;
    }

    private int resCost_ ;
    /**
     * <pre>
     * 购买货币价格
     * </pre>
     *
     * <code>required int32 resCost = 6;</code>
     */
    public boolean hasResCost() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <pre>
     * 购买货币价格
     * </pre>
     *
     * <code>required int32 resCost = 6;</code>
     */
    public int getResCost() {
      return resCost_;
    }
    /**
     * <pre>
     * 购买货币价格
     * </pre>
     *
     * <code>required int32 resCost = 6;</code>
     */
    public Builder setResCost(int value) {
      bitField0_ |= 0x00000020;
      resCost_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 购买货币价格
     * </pre>
     *
     * <code>required int32 resCost = 6;</code>
     */
    public Builder clearResCost() {
      bitField0_ = (bitField0_ & ~0x00000020);
      resCost_ = 0;
      onChanged();
      return this;
    }

    private int isBuy_ ;
    /**
     * <pre>
     * 是否已经购买 1-已购买 0-未购买
     * </pre>
     *
     * <code>required int32 isBuy = 7;</code>
     */
    public boolean hasIsBuy() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    /**
     * <pre>
     * 是否已经购买 1-已购买 0-未购买
     * </pre>
     *
     * <code>required int32 isBuy = 7;</code>
     */
    public int getIsBuy() {
      return isBuy_;
    }
    /**
     * <pre>
     * 是否已经购买 1-已购买 0-未购买
     * </pre>
     *
     * <code>required int32 isBuy = 7;</code>
     */
    public Builder setIsBuy(int value) {
      bitField0_ |= 0x00000040;
      isBuy_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 是否已经购买 1-已购买 0-未购买
     * </pre>
     *
     * <code>required int32 isBuy = 7;</code>
     */
    public Builder clearIsBuy() {
      bitField0_ = (bitField0_ & ~0x00000040);
      isBuy_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.JjcShopInfo)
  }

  // @@protoc_insertion_point(class_scope:client2server.JjcShopInfo)
  private static final pb4client.JjcShopInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.JjcShopInfo();
  }

  public static pb4client.JjcShopInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<JjcShopInfo>
      PARSER = new com.google.protobuf.AbstractParser<JjcShopInfo>() {
    public JjcShopInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new JjcShopInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<JjcShopInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<JjcShopInfo> getParserForType() {
    return PARSER;
  }

  public pb4client.JjcShopInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

