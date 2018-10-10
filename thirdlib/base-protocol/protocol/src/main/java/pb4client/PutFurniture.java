// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 1523
 * 客户端 -&gt; 服务器
 * 摆放家具
 * </pre>
 *
 * Protobuf type {@code client2server.PutFurniture}
 */
public  final class PutFurniture extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.PutFurniture)
    PutFurnitureOrBuilder {
  // Use PutFurniture.newBuilder() to construct.
  private PutFurniture(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private PutFurniture() {
    protoId_ = 0;
    floorIdx_ = 0;
    x_ = 0;
    y_ = 0;
    direction_ = 0;
    buyNum_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private PutFurniture(
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
            floorIdx_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            x_ = input.readInt32();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            y_ = input.readInt32();
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            direction_ = input.readInt32();
            break;
          }
          case 48: {
            bitField0_ |= 0x00000020;
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
    return pb4client.War2GamePkt.internal_static_client2server_PutFurniture_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_PutFurniture_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.PutFurniture.class, pb4client.PutFurniture.Builder.class);
  }

  private int bitField0_;
  public static final int PROTOID_FIELD_NUMBER = 1;
  private int protoId_;
  /**
   * <pre>
   *家具模板Id
   * </pre>
   *
   * <code>required int32 protoId = 1;</code>
   */
  public boolean hasProtoId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *家具模板Id
   * </pre>
   *
   * <code>required int32 protoId = 1;</code>
   */
  public int getProtoId() {
    return protoId_;
  }

  public static final int FLOORIDX_FIELD_NUMBER = 2;
  private int floorIdx_;
  /**
   * <pre>
   *家具所在层 1-1F 2-2F
   * </pre>
   *
   * <code>required int32 floorIdx = 2;</code>
   */
  public boolean hasFloorIdx() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *家具所在层 1-1F 2-2F
   * </pre>
   *
   * <code>required int32 floorIdx = 2;</code>
   */
  public int getFloorIdx() {
    return floorIdx_;
  }

  public static final int X_FIELD_NUMBER = 3;
  private int x_;
  /**
   * <pre>
   * 家具x坐标
   * </pre>
   *
   * <code>required int32 x = 3;</code>
   */
  public boolean hasX() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 家具x坐标
   * </pre>
   *
   * <code>required int32 x = 3;</code>
   */
  public int getX() {
    return x_;
  }

  public static final int Y_FIELD_NUMBER = 4;
  private int y_;
  /**
   * <pre>
   * 家具y坐标
   * </pre>
   *
   * <code>required int32 y = 4;</code>
   */
  public boolean hasY() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   * 家具y坐标
   * </pre>
   *
   * <code>required int32 y = 4;</code>
   */
  public int getY() {
    return y_;
  }

  public static final int DIRECTION_FIELD_NUMBER = 5;
  private int direction_;
  /**
   * <pre>
   * 家具朝向
   * </pre>
   *
   * <code>required int32 direction = 5;</code>
   */
  public boolean hasDirection() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <pre>
   * 家具朝向
   * </pre>
   *
   * <code>required int32 direction = 5;</code>
   */
  public int getDirection() {
    return direction_;
  }

  public static final int BUYNUM_FIELD_NUMBER = 6;
  private int buyNum_;
  /**
   * <pre>
   * 购买数量
   * </pre>
   *
   * <code>optional int32 buyNum = 6;</code>
   */
  public boolean hasBuyNum() {
    return ((bitField0_ & 0x00000020) == 0x00000020);
  }
  /**
   * <pre>
   * 购买数量
   * </pre>
   *
   * <code>optional int32 buyNum = 6;</code>
   */
  public int getBuyNum() {
    return buyNum_;
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
    if (!hasFloorIdx()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasX()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasY()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasDirection()) {
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
      output.writeInt32(2, floorIdx_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, x_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, y_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt32(5, direction_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      output.writeInt32(6, buyNum_);
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
        .computeInt32Size(2, floorIdx_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, x_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, y_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, direction_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, buyNum_);
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
    if (!(obj instanceof pb4client.PutFurniture)) {
      return super.equals(obj);
    }
    pb4client.PutFurniture other = (pb4client.PutFurniture) obj;

    boolean result = true;
    result = result && (hasProtoId() == other.hasProtoId());
    if (hasProtoId()) {
      result = result && (getProtoId()
          == other.getProtoId());
    }
    result = result && (hasFloorIdx() == other.hasFloorIdx());
    if (hasFloorIdx()) {
      result = result && (getFloorIdx()
          == other.getFloorIdx());
    }
    result = result && (hasX() == other.hasX());
    if (hasX()) {
      result = result && (getX()
          == other.getX());
    }
    result = result && (hasY() == other.hasY());
    if (hasY()) {
      result = result && (getY()
          == other.getY());
    }
    result = result && (hasDirection() == other.hasDirection());
    if (hasDirection()) {
      result = result && (getDirection()
          == other.getDirection());
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
    if (hasProtoId()) {
      hash = (37 * hash) + PROTOID_FIELD_NUMBER;
      hash = (53 * hash) + getProtoId();
    }
    if (hasFloorIdx()) {
      hash = (37 * hash) + FLOORIDX_FIELD_NUMBER;
      hash = (53 * hash) + getFloorIdx();
    }
    if (hasX()) {
      hash = (37 * hash) + X_FIELD_NUMBER;
      hash = (53 * hash) + getX();
    }
    if (hasY()) {
      hash = (37 * hash) + Y_FIELD_NUMBER;
      hash = (53 * hash) + getY();
    }
    if (hasDirection()) {
      hash = (37 * hash) + DIRECTION_FIELD_NUMBER;
      hash = (53 * hash) + getDirection();
    }
    if (hasBuyNum()) {
      hash = (37 * hash) + BUYNUM_FIELD_NUMBER;
      hash = (53 * hash) + getBuyNum();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.PutFurniture parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.PutFurniture parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.PutFurniture parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.PutFurniture parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.PutFurniture parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.PutFurniture parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.PutFurniture parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.PutFurniture parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.PutFurniture parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.PutFurniture parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.PutFurniture parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.PutFurniture parseFrom(
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
  public static Builder newBuilder(pb4client.PutFurniture prototype) {
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
   * msgType = 1523
   * 客户端 -&gt; 服务器
   * 摆放家具
   * </pre>
   *
   * Protobuf type {@code client2server.PutFurniture}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.PutFurniture)
      pb4client.PutFurnitureOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_PutFurniture_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_PutFurniture_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.PutFurniture.class, pb4client.PutFurniture.Builder.class);
    }

    // Construct using pb4client.PutFurniture.newBuilder()
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
      floorIdx_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      x_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      y_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      direction_ = 0;
      bitField0_ = (bitField0_ & ~0x00000010);
      buyNum_ = 0;
      bitField0_ = (bitField0_ & ~0x00000020);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_PutFurniture_descriptor;
    }

    public pb4client.PutFurniture getDefaultInstanceForType() {
      return pb4client.PutFurniture.getDefaultInstance();
    }

    public pb4client.PutFurniture build() {
      pb4client.PutFurniture result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.PutFurniture buildPartial() {
      pb4client.PutFurniture result = new pb4client.PutFurniture(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.protoId_ = protoId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.floorIdx_ = floorIdx_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.x_ = x_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.y_ = y_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.direction_ = direction_;
      if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
        to_bitField0_ |= 0x00000020;
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
      if (other instanceof pb4client.PutFurniture) {
        return mergeFrom((pb4client.PutFurniture)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.PutFurniture other) {
      if (other == pb4client.PutFurniture.getDefaultInstance()) return this;
      if (other.hasProtoId()) {
        setProtoId(other.getProtoId());
      }
      if (other.hasFloorIdx()) {
        setFloorIdx(other.getFloorIdx());
      }
      if (other.hasX()) {
        setX(other.getX());
      }
      if (other.hasY()) {
        setY(other.getY());
      }
      if (other.hasDirection()) {
        setDirection(other.getDirection());
      }
      if (other.hasBuyNum()) {
        setBuyNum(other.getBuyNum());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasProtoId()) {
        return false;
      }
      if (!hasFloorIdx()) {
        return false;
      }
      if (!hasX()) {
        return false;
      }
      if (!hasY()) {
        return false;
      }
      if (!hasDirection()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.PutFurniture parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.PutFurniture) e.getUnfinishedMessage();
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
     *家具模板Id
     * </pre>
     *
     * <code>required int32 protoId = 1;</code>
     */
    public boolean hasProtoId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *家具模板Id
     * </pre>
     *
     * <code>required int32 protoId = 1;</code>
     */
    public int getProtoId() {
      return protoId_;
    }
    /**
     * <pre>
     *家具模板Id
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
     *家具模板Id
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

    private int floorIdx_ ;
    /**
     * <pre>
     *家具所在层 1-1F 2-2F
     * </pre>
     *
     * <code>required int32 floorIdx = 2;</code>
     */
    public boolean hasFloorIdx() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *家具所在层 1-1F 2-2F
     * </pre>
     *
     * <code>required int32 floorIdx = 2;</code>
     */
    public int getFloorIdx() {
      return floorIdx_;
    }
    /**
     * <pre>
     *家具所在层 1-1F 2-2F
     * </pre>
     *
     * <code>required int32 floorIdx = 2;</code>
     */
    public Builder setFloorIdx(int value) {
      bitField0_ |= 0x00000002;
      floorIdx_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *家具所在层 1-1F 2-2F
     * </pre>
     *
     * <code>required int32 floorIdx = 2;</code>
     */
    public Builder clearFloorIdx() {
      bitField0_ = (bitField0_ & ~0x00000002);
      floorIdx_ = 0;
      onChanged();
      return this;
    }

    private int x_ ;
    /**
     * <pre>
     * 家具x坐标
     * </pre>
     *
     * <code>required int32 x = 3;</code>
     */
    public boolean hasX() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 家具x坐标
     * </pre>
     *
     * <code>required int32 x = 3;</code>
     */
    public int getX() {
      return x_;
    }
    /**
     * <pre>
     * 家具x坐标
     * </pre>
     *
     * <code>required int32 x = 3;</code>
     */
    public Builder setX(int value) {
      bitField0_ |= 0x00000004;
      x_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 家具x坐标
     * </pre>
     *
     * <code>required int32 x = 3;</code>
     */
    public Builder clearX() {
      bitField0_ = (bitField0_ & ~0x00000004);
      x_ = 0;
      onChanged();
      return this;
    }

    private int y_ ;
    /**
     * <pre>
     * 家具y坐标
     * </pre>
     *
     * <code>required int32 y = 4;</code>
     */
    public boolean hasY() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     * 家具y坐标
     * </pre>
     *
     * <code>required int32 y = 4;</code>
     */
    public int getY() {
      return y_;
    }
    /**
     * <pre>
     * 家具y坐标
     * </pre>
     *
     * <code>required int32 y = 4;</code>
     */
    public Builder setY(int value) {
      bitField0_ |= 0x00000008;
      y_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 家具y坐标
     * </pre>
     *
     * <code>required int32 y = 4;</code>
     */
    public Builder clearY() {
      bitField0_ = (bitField0_ & ~0x00000008);
      y_ = 0;
      onChanged();
      return this;
    }

    private int direction_ ;
    /**
     * <pre>
     * 家具朝向
     * </pre>
     *
     * <code>required int32 direction = 5;</code>
     */
    public boolean hasDirection() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     * 家具朝向
     * </pre>
     *
     * <code>required int32 direction = 5;</code>
     */
    public int getDirection() {
      return direction_;
    }
    /**
     * <pre>
     * 家具朝向
     * </pre>
     *
     * <code>required int32 direction = 5;</code>
     */
    public Builder setDirection(int value) {
      bitField0_ |= 0x00000010;
      direction_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 家具朝向
     * </pre>
     *
     * <code>required int32 direction = 5;</code>
     */
    public Builder clearDirection() {
      bitField0_ = (bitField0_ & ~0x00000010);
      direction_ = 0;
      onChanged();
      return this;
    }

    private int buyNum_ ;
    /**
     * <pre>
     * 购买数量
     * </pre>
     *
     * <code>optional int32 buyNum = 6;</code>
     */
    public boolean hasBuyNum() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <pre>
     * 购买数量
     * </pre>
     *
     * <code>optional int32 buyNum = 6;</code>
     */
    public int getBuyNum() {
      return buyNum_;
    }
    /**
     * <pre>
     * 购买数量
     * </pre>
     *
     * <code>optional int32 buyNum = 6;</code>
     */
    public Builder setBuyNum(int value) {
      bitField0_ |= 0x00000020;
      buyNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 购买数量
     * </pre>
     *
     * <code>optional int32 buyNum = 6;</code>
     */
    public Builder clearBuyNum() {
      bitField0_ = (bitField0_ & ~0x00000020);
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


    // @@protoc_insertion_point(builder_scope:client2server.PutFurniture)
  }

  // @@protoc_insertion_point(class_scope:client2server.PutFurniture)
  private static final pb4client.PutFurniture DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.PutFurniture();
  }

  public static pb4client.PutFurniture getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<PutFurniture>
      PARSER = new com.google.protobuf.AbstractParser<PutFurniture>() {
    public PutFurniture parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new PutFurniture(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<PutFurniture> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<PutFurniture> getParserForType() {
    return PARSER;
  }

  public pb4client.PutFurniture getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

