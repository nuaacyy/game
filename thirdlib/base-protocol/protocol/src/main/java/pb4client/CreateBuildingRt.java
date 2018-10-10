// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.CreateBuildingRt}
 */
public  final class CreateBuildingRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.CreateBuildingRt)
    CreateBuildingRtOrBuilder {
  // Use CreateBuildingRt.newBuilder() to construct.
  private CreateBuildingRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CreateBuildingRt() {
    rt_ = 0;
    cityId_ = 0L;
    buildingId_ = 0L;
    buildingType_ = 0;
    completeTime_ = 0;
    direction_ = 0;
    x_ = 0;
    y_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CreateBuildingRt(
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
            cityId_ = input.readInt64();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            buildingId_ = input.readInt64();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            buildingType_ = input.readInt32();
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            completeTime_ = input.readInt32();
            break;
          }
          case 48: {
            bitField0_ |= 0x00000020;
            direction_ = input.readInt32();
            break;
          }
          case 56: {
            bitField0_ |= 0x00000040;
            x_ = input.readInt32();
            break;
          }
          case 64: {
            bitField0_ |= 0x00000080;
            y_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_CreateBuildingRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_CreateBuildingRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.CreateBuildingRt.class, pb4client.CreateBuildingRt.Builder.class);
  }

  private int bitField0_;
  public static final int RT_FIELD_NUMBER = 1;
  private int rt_;
  /**
   * <code>required int32 rt = 1;</code>
   */
  public boolean hasRt() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required int32 rt = 1;</code>
   */
  public int getRt() {
    return rt_;
  }

  public static final int CITYID_FIELD_NUMBER = 2;
  private long cityId_;
  /**
   * <pre>
   * 城池ID
   * </pre>
   *
   * <code>optional int64 cityId = 2;</code>
   */
  public boolean hasCityId() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 城池ID
   * </pre>
   *
   * <code>optional int64 cityId = 2;</code>
   */
  public long getCityId() {
    return cityId_;
  }

  public static final int BUILDINGID_FIELD_NUMBER = 3;
  private long buildingId_;
  /**
   * <pre>
   * 建筑实例唯一ID
   * </pre>
   *
   * <code>optional int64 buildingId = 3;</code>
   */
  public boolean hasBuildingId() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 建筑实例唯一ID
   * </pre>
   *
   * <code>optional int64 buildingId = 3;</code>
   */
  public long getBuildingId() {
    return buildingId_;
  }

  public static final int BUILDINGTYPE_FIELD_NUMBER = 4;
  private int buildingType_;
  /**
   * <pre>
   * 建筑类型
   * </pre>
   *
   * <code>optional int32 buildingType = 4;</code>
   */
  public boolean hasBuildingType() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   * 建筑类型
   * </pre>
   *
   * <code>optional int32 buildingType = 4;</code>
   */
  public int getBuildingType() {
    return buildingType_;
  }

  public static final int COMPLETETIME_FIELD_NUMBER = 5;
  private int completeTime_;
  /**
   * <pre>
   * 完成时间
   * </pre>
   *
   * <code>optional int32 completeTime = 5;</code>
   */
  public boolean hasCompleteTime() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <pre>
   * 完成时间
   * </pre>
   *
   * <code>optional int32 completeTime = 5;</code>
   */
  public int getCompleteTime() {
    return completeTime_;
  }

  public static final int DIRECTION_FIELD_NUMBER = 6;
  private int direction_;
  /**
   * <pre>
   * 建筑方向
   * </pre>
   *
   * <code>optional int32 direction = 6;</code>
   */
  public boolean hasDirection() {
    return ((bitField0_ & 0x00000020) == 0x00000020);
  }
  /**
   * <pre>
   * 建筑方向
   * </pre>
   *
   * <code>optional int32 direction = 6;</code>
   */
  public int getDirection() {
    return direction_;
  }

  public static final int X_FIELD_NUMBER = 7;
  private int x_;
  /**
   * <pre>
   * 建筑X坐标
   * </pre>
   *
   * <code>optional int32 x = 7;</code>
   */
  public boolean hasX() {
    return ((bitField0_ & 0x00000040) == 0x00000040);
  }
  /**
   * <pre>
   * 建筑X坐标
   * </pre>
   *
   * <code>optional int32 x = 7;</code>
   */
  public int getX() {
    return x_;
  }

  public static final int Y_FIELD_NUMBER = 8;
  private int y_;
  /**
   * <pre>
   * 建筑坐标
   * </pre>
   *
   * <code>optional int32 y = 8;</code>
   */
  public boolean hasY() {
    return ((bitField0_ & 0x00000080) == 0x00000080);
  }
  /**
   * <pre>
   * 建筑坐标
   * </pre>
   *
   * <code>optional int32 y = 8;</code>
   */
  public int getY() {
    return y_;
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
      output.writeInt64(2, cityId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt64(3, buildingId_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, buildingType_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt32(5, completeTime_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      output.writeInt32(6, direction_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      output.writeInt32(7, x_);
    }
    if (((bitField0_ & 0x00000080) == 0x00000080)) {
      output.writeInt32(8, y_);
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
        .computeInt64Size(2, cityId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, buildingId_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, buildingType_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, completeTime_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, direction_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, x_);
    }
    if (((bitField0_ & 0x00000080) == 0x00000080)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(8, y_);
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
    if (!(obj instanceof pb4client.CreateBuildingRt)) {
      return super.equals(obj);
    }
    pb4client.CreateBuildingRt other = (pb4client.CreateBuildingRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && (hasCityId() == other.hasCityId());
    if (hasCityId()) {
      result = result && (getCityId()
          == other.getCityId());
    }
    result = result && (hasBuildingId() == other.hasBuildingId());
    if (hasBuildingId()) {
      result = result && (getBuildingId()
          == other.getBuildingId());
    }
    result = result && (hasBuildingType() == other.hasBuildingType());
    if (hasBuildingType()) {
      result = result && (getBuildingType()
          == other.getBuildingType());
    }
    result = result && (hasCompleteTime() == other.hasCompleteTime());
    if (hasCompleteTime()) {
      result = result && (getCompleteTime()
          == other.getCompleteTime());
    }
    result = result && (hasDirection() == other.hasDirection());
    if (hasDirection()) {
      result = result && (getDirection()
          == other.getDirection());
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
    if (hasCityId()) {
      hash = (37 * hash) + CITYID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getCityId());
    }
    if (hasBuildingId()) {
      hash = (37 * hash) + BUILDINGID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getBuildingId());
    }
    if (hasBuildingType()) {
      hash = (37 * hash) + BUILDINGTYPE_FIELD_NUMBER;
      hash = (53 * hash) + getBuildingType();
    }
    if (hasCompleteTime()) {
      hash = (37 * hash) + COMPLETETIME_FIELD_NUMBER;
      hash = (53 * hash) + getCompleteTime();
    }
    if (hasDirection()) {
      hash = (37 * hash) + DIRECTION_FIELD_NUMBER;
      hash = (53 * hash) + getDirection();
    }
    if (hasX()) {
      hash = (37 * hash) + X_FIELD_NUMBER;
      hash = (53 * hash) + getX();
    }
    if (hasY()) {
      hash = (37 * hash) + Y_FIELD_NUMBER;
      hash = (53 * hash) + getY();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.CreateBuildingRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.CreateBuildingRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.CreateBuildingRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.CreateBuildingRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.CreateBuildingRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.CreateBuildingRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.CreateBuildingRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.CreateBuildingRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.CreateBuildingRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.CreateBuildingRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.CreateBuildingRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.CreateBuildingRt parseFrom(
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
  public static Builder newBuilder(pb4client.CreateBuildingRt prototype) {
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
   * Protobuf type {@code client2server.CreateBuildingRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.CreateBuildingRt)
      pb4client.CreateBuildingRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_CreateBuildingRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_CreateBuildingRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.CreateBuildingRt.class, pb4client.CreateBuildingRt.Builder.class);
    }

    // Construct using pb4client.CreateBuildingRt.newBuilder()
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
      cityId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      buildingId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000004);
      buildingType_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      completeTime_ = 0;
      bitField0_ = (bitField0_ & ~0x00000010);
      direction_ = 0;
      bitField0_ = (bitField0_ & ~0x00000020);
      x_ = 0;
      bitField0_ = (bitField0_ & ~0x00000040);
      y_ = 0;
      bitField0_ = (bitField0_ & ~0x00000080);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_CreateBuildingRt_descriptor;
    }

    public pb4client.CreateBuildingRt getDefaultInstanceForType() {
      return pb4client.CreateBuildingRt.getDefaultInstance();
    }

    public pb4client.CreateBuildingRt build() {
      pb4client.CreateBuildingRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.CreateBuildingRt buildPartial() {
      pb4client.CreateBuildingRt result = new pb4client.CreateBuildingRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.cityId_ = cityId_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.buildingId_ = buildingId_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.buildingType_ = buildingType_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.completeTime_ = completeTime_;
      if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
        to_bitField0_ |= 0x00000020;
      }
      result.direction_ = direction_;
      if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
        to_bitField0_ |= 0x00000040;
      }
      result.x_ = x_;
      if (((from_bitField0_ & 0x00000080) == 0x00000080)) {
        to_bitField0_ |= 0x00000080;
      }
      result.y_ = y_;
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
      if (other instanceof pb4client.CreateBuildingRt) {
        return mergeFrom((pb4client.CreateBuildingRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.CreateBuildingRt other) {
      if (other == pb4client.CreateBuildingRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (other.hasCityId()) {
        setCityId(other.getCityId());
      }
      if (other.hasBuildingId()) {
        setBuildingId(other.getBuildingId());
      }
      if (other.hasBuildingType()) {
        setBuildingType(other.getBuildingType());
      }
      if (other.hasCompleteTime()) {
        setCompleteTime(other.getCompleteTime());
      }
      if (other.hasDirection()) {
        setDirection(other.getDirection());
      }
      if (other.hasX()) {
        setX(other.getX());
      }
      if (other.hasY()) {
        setY(other.getY());
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
      pb4client.CreateBuildingRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.CreateBuildingRt) e.getUnfinishedMessage();
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
     * <code>required int32 rt = 1;</code>
     */
    public boolean hasRt() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 rt = 1;</code>
     */
    public int getRt() {
      return rt_;
    }
    /**
     * <code>required int32 rt = 1;</code>
     */
    public Builder setRt(int value) {
      bitField0_ |= 0x00000001;
      rt_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 rt = 1;</code>
     */
    public Builder clearRt() {
      bitField0_ = (bitField0_ & ~0x00000001);
      rt_ = 0;
      onChanged();
      return this;
    }

    private long cityId_ ;
    /**
     * <pre>
     * 城池ID
     * </pre>
     *
     * <code>optional int64 cityId = 2;</code>
     */
    public boolean hasCityId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 城池ID
     * </pre>
     *
     * <code>optional int64 cityId = 2;</code>
     */
    public long getCityId() {
      return cityId_;
    }
    /**
     * <pre>
     * 城池ID
     * </pre>
     *
     * <code>optional int64 cityId = 2;</code>
     */
    public Builder setCityId(long value) {
      bitField0_ |= 0x00000002;
      cityId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 城池ID
     * </pre>
     *
     * <code>optional int64 cityId = 2;</code>
     */
    public Builder clearCityId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      cityId_ = 0L;
      onChanged();
      return this;
    }

    private long buildingId_ ;
    /**
     * <pre>
     * 建筑实例唯一ID
     * </pre>
     *
     * <code>optional int64 buildingId = 3;</code>
     */
    public boolean hasBuildingId() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 建筑实例唯一ID
     * </pre>
     *
     * <code>optional int64 buildingId = 3;</code>
     */
    public long getBuildingId() {
      return buildingId_;
    }
    /**
     * <pre>
     * 建筑实例唯一ID
     * </pre>
     *
     * <code>optional int64 buildingId = 3;</code>
     */
    public Builder setBuildingId(long value) {
      bitField0_ |= 0x00000004;
      buildingId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 建筑实例唯一ID
     * </pre>
     *
     * <code>optional int64 buildingId = 3;</code>
     */
    public Builder clearBuildingId() {
      bitField0_ = (bitField0_ & ~0x00000004);
      buildingId_ = 0L;
      onChanged();
      return this;
    }

    private int buildingType_ ;
    /**
     * <pre>
     * 建筑类型
     * </pre>
     *
     * <code>optional int32 buildingType = 4;</code>
     */
    public boolean hasBuildingType() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     * 建筑类型
     * </pre>
     *
     * <code>optional int32 buildingType = 4;</code>
     */
    public int getBuildingType() {
      return buildingType_;
    }
    /**
     * <pre>
     * 建筑类型
     * </pre>
     *
     * <code>optional int32 buildingType = 4;</code>
     */
    public Builder setBuildingType(int value) {
      bitField0_ |= 0x00000008;
      buildingType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 建筑类型
     * </pre>
     *
     * <code>optional int32 buildingType = 4;</code>
     */
    public Builder clearBuildingType() {
      bitField0_ = (bitField0_ & ~0x00000008);
      buildingType_ = 0;
      onChanged();
      return this;
    }

    private int completeTime_ ;
    /**
     * <pre>
     * 完成时间
     * </pre>
     *
     * <code>optional int32 completeTime = 5;</code>
     */
    public boolean hasCompleteTime() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     * 完成时间
     * </pre>
     *
     * <code>optional int32 completeTime = 5;</code>
     */
    public int getCompleteTime() {
      return completeTime_;
    }
    /**
     * <pre>
     * 完成时间
     * </pre>
     *
     * <code>optional int32 completeTime = 5;</code>
     */
    public Builder setCompleteTime(int value) {
      bitField0_ |= 0x00000010;
      completeTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 完成时间
     * </pre>
     *
     * <code>optional int32 completeTime = 5;</code>
     */
    public Builder clearCompleteTime() {
      bitField0_ = (bitField0_ & ~0x00000010);
      completeTime_ = 0;
      onChanged();
      return this;
    }

    private int direction_ ;
    /**
     * <pre>
     * 建筑方向
     * </pre>
     *
     * <code>optional int32 direction = 6;</code>
     */
    public boolean hasDirection() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <pre>
     * 建筑方向
     * </pre>
     *
     * <code>optional int32 direction = 6;</code>
     */
    public int getDirection() {
      return direction_;
    }
    /**
     * <pre>
     * 建筑方向
     * </pre>
     *
     * <code>optional int32 direction = 6;</code>
     */
    public Builder setDirection(int value) {
      bitField0_ |= 0x00000020;
      direction_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 建筑方向
     * </pre>
     *
     * <code>optional int32 direction = 6;</code>
     */
    public Builder clearDirection() {
      bitField0_ = (bitField0_ & ~0x00000020);
      direction_ = 0;
      onChanged();
      return this;
    }

    private int x_ ;
    /**
     * <pre>
     * 建筑X坐标
     * </pre>
     *
     * <code>optional int32 x = 7;</code>
     */
    public boolean hasX() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    /**
     * <pre>
     * 建筑X坐标
     * </pre>
     *
     * <code>optional int32 x = 7;</code>
     */
    public int getX() {
      return x_;
    }
    /**
     * <pre>
     * 建筑X坐标
     * </pre>
     *
     * <code>optional int32 x = 7;</code>
     */
    public Builder setX(int value) {
      bitField0_ |= 0x00000040;
      x_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 建筑X坐标
     * </pre>
     *
     * <code>optional int32 x = 7;</code>
     */
    public Builder clearX() {
      bitField0_ = (bitField0_ & ~0x00000040);
      x_ = 0;
      onChanged();
      return this;
    }

    private int y_ ;
    /**
     * <pre>
     * 建筑坐标
     * </pre>
     *
     * <code>optional int32 y = 8;</code>
     */
    public boolean hasY() {
      return ((bitField0_ & 0x00000080) == 0x00000080);
    }
    /**
     * <pre>
     * 建筑坐标
     * </pre>
     *
     * <code>optional int32 y = 8;</code>
     */
    public int getY() {
      return y_;
    }
    /**
     * <pre>
     * 建筑坐标
     * </pre>
     *
     * <code>optional int32 y = 8;</code>
     */
    public Builder setY(int value) {
      bitField0_ |= 0x00000080;
      y_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 建筑坐标
     * </pre>
     *
     * <code>optional int32 y = 8;</code>
     */
    public Builder clearY() {
      bitField0_ = (bitField0_ & ~0x00000080);
      y_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.CreateBuildingRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.CreateBuildingRt)
  private static final pb4client.CreateBuildingRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.CreateBuildingRt();
  }

  public static pb4client.CreateBuildingRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<CreateBuildingRt>
      PARSER = new com.google.protobuf.AbstractParser<CreateBuildingRt>() {
    public CreateBuildingRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new CreateBuildingRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CreateBuildingRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CreateBuildingRt> getParserForType() {
    return PARSER;
  }

  public pb4client.CreateBuildingRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

