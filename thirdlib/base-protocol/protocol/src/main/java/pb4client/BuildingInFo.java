// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.BuildingInFo}
 */
public  final class BuildingInFo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.BuildingInFo)
    BuildingInFoOrBuilder {
  // Use BuildingInFo.newBuilder() to construct.
  private BuildingInFo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private BuildingInFo() {
    type_ = 0;
    lv_ = 0;
    completeTime_ = 0;
    state_ = 0;
    x_ = 0;
    y_ = 0;
    destroyTime_ = 0;
    helperId_ = 0L;
    buildingId_ = 0L;
    direction_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private BuildingInFo(
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
            type_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            lv_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            completeTime_ = input.readInt32();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            state_ = input.readInt32();
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            x_ = input.readInt32();
            break;
          }
          case 48: {
            bitField0_ |= 0x00000020;
            y_ = input.readInt32();
            break;
          }
          case 56: {
            bitField0_ |= 0x00000040;
            destroyTime_ = input.readInt32();
            break;
          }
          case 64: {
            bitField0_ |= 0x00000080;
            helperId_ = input.readInt64();
            break;
          }
          case 72: {
            bitField0_ |= 0x00000100;
            buildingId_ = input.readInt64();
            break;
          }
          case 80: {
            bitField0_ |= 0x00000200;
            direction_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_BuildingInFo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_BuildingInFo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.BuildingInFo.class, pb4client.BuildingInFo.Builder.class);
  }

  private int bitField0_;
  public static final int TYPE_FIELD_NUMBER = 1;
  private int type_;
  /**
   * <code>required int32 type = 1;</code>
   */
  public boolean hasType() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required int32 type = 1;</code>
   */
  public int getType() {
    return type_;
  }

  public static final int LV_FIELD_NUMBER = 2;
  private int lv_;
  /**
   * <code>required int32 lv = 2;</code>
   */
  public boolean hasLv() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>required int32 lv = 2;</code>
   */
  public int getLv() {
    return lv_;
  }

  public static final int COMPLETETIME_FIELD_NUMBER = 3;
  private int completeTime_;
  /**
   * <code>required int32 completeTime = 3;</code>
   */
  public boolean hasCompleteTime() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <code>required int32 completeTime = 3;</code>
   */
  public int getCompleteTime() {
    return completeTime_;
  }

  public static final int STATE_FIELD_NUMBER = 4;
  private int state_;
  /**
   * <code>required int32 state = 4;</code>
   */
  public boolean hasState() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <code>required int32 state = 4;</code>
   */
  public int getState() {
    return state_;
  }

  public static final int X_FIELD_NUMBER = 5;
  private int x_;
  /**
   * <pre>
   * 建筑所在位置的x坐标
   * </pre>
   *
   * <code>required int32 x = 5;</code>
   */
  public boolean hasX() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <pre>
   * 建筑所在位置的x坐标
   * </pre>
   *
   * <code>required int32 x = 5;</code>
   */
  public int getX() {
    return x_;
  }

  public static final int Y_FIELD_NUMBER = 6;
  private int y_;
  /**
   * <pre>
   * 建筑所在位置的y坐标
   * </pre>
   *
   * <code>required int32 y = 6;</code>
   */
  public boolean hasY() {
    return ((bitField0_ & 0x00000020) == 0x00000020);
  }
  /**
   * <pre>
   * 建筑所在位置的y坐标
   * </pre>
   *
   * <code>required int32 y = 6;</code>
   */
  public int getY() {
    return y_;
  }

  public static final int DESTROYTIME_FIELD_NUMBER = 7;
  private int destroyTime_;
  /**
   * <code>required int32 destroyTime = 7;</code>
   */
  public boolean hasDestroyTime() {
    return ((bitField0_ & 0x00000040) == 0x00000040);
  }
  /**
   * <code>required int32 destroyTime = 7;</code>
   */
  public int getDestroyTime() {
    return destroyTime_;
  }

  public static final int HELPERID_FIELD_NUMBER = 8;
  private long helperId_;
  /**
   * <pre>
   * 帮助里面的唯一ID
   * </pre>
   *
   * <code>required int64 helperId = 8;</code>
   */
  public boolean hasHelperId() {
    return ((bitField0_ & 0x00000080) == 0x00000080);
  }
  /**
   * <pre>
   * 帮助里面的唯一ID
   * </pre>
   *
   * <code>required int64 helperId = 8;</code>
   */
  public long getHelperId() {
    return helperId_;
  }

  public static final int BUILDINGID_FIELD_NUMBER = 9;
  private long buildingId_;
  /**
   * <pre>
   * 建筑唯一ID
   * </pre>
   *
   * <code>required int64 buildingId = 9;</code>
   */
  public boolean hasBuildingId() {
    return ((bitField0_ & 0x00000100) == 0x00000100);
  }
  /**
   * <pre>
   * 建筑唯一ID
   * </pre>
   *
   * <code>required int64 buildingId = 9;</code>
   */
  public long getBuildingId() {
    return buildingId_;
  }

  public static final int DIRECTION_FIELD_NUMBER = 10;
  private int direction_;
  /**
   * <pre>
   * 建筑的方向
   * </pre>
   *
   * <code>required int32 direction = 10;</code>
   */
  public boolean hasDirection() {
    return ((bitField0_ & 0x00000200) == 0x00000200);
  }
  /**
   * <pre>
   * 建筑的方向
   * </pre>
   *
   * <code>required int32 direction = 10;</code>
   */
  public int getDirection() {
    return direction_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasType()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasLv()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasCompleteTime()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasState()) {
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
    if (!hasDestroyTime()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasHelperId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasBuildingId()) {
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
      output.writeInt32(1, type_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, lv_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, completeTime_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, state_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt32(5, x_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      output.writeInt32(6, y_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      output.writeInt32(7, destroyTime_);
    }
    if (((bitField0_ & 0x00000080) == 0x00000080)) {
      output.writeInt64(8, helperId_);
    }
    if (((bitField0_ & 0x00000100) == 0x00000100)) {
      output.writeInt64(9, buildingId_);
    }
    if (((bitField0_ & 0x00000200) == 0x00000200)) {
      output.writeInt32(10, direction_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, type_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, lv_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, completeTime_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, state_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, x_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, y_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, destroyTime_);
    }
    if (((bitField0_ & 0x00000080) == 0x00000080)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(8, helperId_);
    }
    if (((bitField0_ & 0x00000100) == 0x00000100)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(9, buildingId_);
    }
    if (((bitField0_ & 0x00000200) == 0x00000200)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(10, direction_);
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
    if (!(obj instanceof pb4client.BuildingInFo)) {
      return super.equals(obj);
    }
    pb4client.BuildingInFo other = (pb4client.BuildingInFo) obj;

    boolean result = true;
    result = result && (hasType() == other.hasType());
    if (hasType()) {
      result = result && (getType()
          == other.getType());
    }
    result = result && (hasLv() == other.hasLv());
    if (hasLv()) {
      result = result && (getLv()
          == other.getLv());
    }
    result = result && (hasCompleteTime() == other.hasCompleteTime());
    if (hasCompleteTime()) {
      result = result && (getCompleteTime()
          == other.getCompleteTime());
    }
    result = result && (hasState() == other.hasState());
    if (hasState()) {
      result = result && (getState()
          == other.getState());
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
    result = result && (hasDestroyTime() == other.hasDestroyTime());
    if (hasDestroyTime()) {
      result = result && (getDestroyTime()
          == other.getDestroyTime());
    }
    result = result && (hasHelperId() == other.hasHelperId());
    if (hasHelperId()) {
      result = result && (getHelperId()
          == other.getHelperId());
    }
    result = result && (hasBuildingId() == other.hasBuildingId());
    if (hasBuildingId()) {
      result = result && (getBuildingId()
          == other.getBuildingId());
    }
    result = result && (hasDirection() == other.hasDirection());
    if (hasDirection()) {
      result = result && (getDirection()
          == other.getDirection());
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
    if (hasType()) {
      hash = (37 * hash) + TYPE_FIELD_NUMBER;
      hash = (53 * hash) + getType();
    }
    if (hasLv()) {
      hash = (37 * hash) + LV_FIELD_NUMBER;
      hash = (53 * hash) + getLv();
    }
    if (hasCompleteTime()) {
      hash = (37 * hash) + COMPLETETIME_FIELD_NUMBER;
      hash = (53 * hash) + getCompleteTime();
    }
    if (hasState()) {
      hash = (37 * hash) + STATE_FIELD_NUMBER;
      hash = (53 * hash) + getState();
    }
    if (hasX()) {
      hash = (37 * hash) + X_FIELD_NUMBER;
      hash = (53 * hash) + getX();
    }
    if (hasY()) {
      hash = (37 * hash) + Y_FIELD_NUMBER;
      hash = (53 * hash) + getY();
    }
    if (hasDestroyTime()) {
      hash = (37 * hash) + DESTROYTIME_FIELD_NUMBER;
      hash = (53 * hash) + getDestroyTime();
    }
    if (hasHelperId()) {
      hash = (37 * hash) + HELPERID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getHelperId());
    }
    if (hasBuildingId()) {
      hash = (37 * hash) + BUILDINGID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getBuildingId());
    }
    if (hasDirection()) {
      hash = (37 * hash) + DIRECTION_FIELD_NUMBER;
      hash = (53 * hash) + getDirection();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.BuildingInFo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.BuildingInFo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.BuildingInFo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.BuildingInFo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.BuildingInFo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.BuildingInFo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.BuildingInFo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.BuildingInFo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.BuildingInFo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.BuildingInFo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.BuildingInFo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.BuildingInFo parseFrom(
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
  public static Builder newBuilder(pb4client.BuildingInFo prototype) {
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
   * Protobuf type {@code client2server.BuildingInFo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.BuildingInFo)
      pb4client.BuildingInFoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_BuildingInFo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_BuildingInFo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.BuildingInFo.class, pb4client.BuildingInFo.Builder.class);
    }

    // Construct using pb4client.BuildingInFo.newBuilder()
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
      type_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      lv_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      completeTime_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      state_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      x_ = 0;
      bitField0_ = (bitField0_ & ~0x00000010);
      y_ = 0;
      bitField0_ = (bitField0_ & ~0x00000020);
      destroyTime_ = 0;
      bitField0_ = (bitField0_ & ~0x00000040);
      helperId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000080);
      buildingId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000100);
      direction_ = 0;
      bitField0_ = (bitField0_ & ~0x00000200);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_BuildingInFo_descriptor;
    }

    public pb4client.BuildingInFo getDefaultInstanceForType() {
      return pb4client.BuildingInFo.getDefaultInstance();
    }

    public pb4client.BuildingInFo build() {
      pb4client.BuildingInFo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.BuildingInFo buildPartial() {
      pb4client.BuildingInFo result = new pb4client.BuildingInFo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.type_ = type_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.lv_ = lv_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.completeTime_ = completeTime_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.state_ = state_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.x_ = x_;
      if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
        to_bitField0_ |= 0x00000020;
      }
      result.y_ = y_;
      if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
        to_bitField0_ |= 0x00000040;
      }
      result.destroyTime_ = destroyTime_;
      if (((from_bitField0_ & 0x00000080) == 0x00000080)) {
        to_bitField0_ |= 0x00000080;
      }
      result.helperId_ = helperId_;
      if (((from_bitField0_ & 0x00000100) == 0x00000100)) {
        to_bitField0_ |= 0x00000100;
      }
      result.buildingId_ = buildingId_;
      if (((from_bitField0_ & 0x00000200) == 0x00000200)) {
        to_bitField0_ |= 0x00000200;
      }
      result.direction_ = direction_;
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
      if (other instanceof pb4client.BuildingInFo) {
        return mergeFrom((pb4client.BuildingInFo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.BuildingInFo other) {
      if (other == pb4client.BuildingInFo.getDefaultInstance()) return this;
      if (other.hasType()) {
        setType(other.getType());
      }
      if (other.hasLv()) {
        setLv(other.getLv());
      }
      if (other.hasCompleteTime()) {
        setCompleteTime(other.getCompleteTime());
      }
      if (other.hasState()) {
        setState(other.getState());
      }
      if (other.hasX()) {
        setX(other.getX());
      }
      if (other.hasY()) {
        setY(other.getY());
      }
      if (other.hasDestroyTime()) {
        setDestroyTime(other.getDestroyTime());
      }
      if (other.hasHelperId()) {
        setHelperId(other.getHelperId());
      }
      if (other.hasBuildingId()) {
        setBuildingId(other.getBuildingId());
      }
      if (other.hasDirection()) {
        setDirection(other.getDirection());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasType()) {
        return false;
      }
      if (!hasLv()) {
        return false;
      }
      if (!hasCompleteTime()) {
        return false;
      }
      if (!hasState()) {
        return false;
      }
      if (!hasX()) {
        return false;
      }
      if (!hasY()) {
        return false;
      }
      if (!hasDestroyTime()) {
        return false;
      }
      if (!hasHelperId()) {
        return false;
      }
      if (!hasBuildingId()) {
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
      pb4client.BuildingInFo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.BuildingInFo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int type_ ;
    /**
     * <code>required int32 type = 1;</code>
     */
    public boolean hasType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 type = 1;</code>
     */
    public int getType() {
      return type_;
    }
    /**
     * <code>required int32 type = 1;</code>
     */
    public Builder setType(int value) {
      bitField0_ |= 0x00000001;
      type_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 type = 1;</code>
     */
    public Builder clearType() {
      bitField0_ = (bitField0_ & ~0x00000001);
      type_ = 0;
      onChanged();
      return this;
    }

    private int lv_ ;
    /**
     * <code>required int32 lv = 2;</code>
     */
    public boolean hasLv() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 lv = 2;</code>
     */
    public int getLv() {
      return lv_;
    }
    /**
     * <code>required int32 lv = 2;</code>
     */
    public Builder setLv(int value) {
      bitField0_ |= 0x00000002;
      lv_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 lv = 2;</code>
     */
    public Builder clearLv() {
      bitField0_ = (bitField0_ & ~0x00000002);
      lv_ = 0;
      onChanged();
      return this;
    }

    private int completeTime_ ;
    /**
     * <code>required int32 completeTime = 3;</code>
     */
    public boolean hasCompleteTime() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required int32 completeTime = 3;</code>
     */
    public int getCompleteTime() {
      return completeTime_;
    }
    /**
     * <code>required int32 completeTime = 3;</code>
     */
    public Builder setCompleteTime(int value) {
      bitField0_ |= 0x00000004;
      completeTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 completeTime = 3;</code>
     */
    public Builder clearCompleteTime() {
      bitField0_ = (bitField0_ & ~0x00000004);
      completeTime_ = 0;
      onChanged();
      return this;
    }

    private int state_ ;
    /**
     * <code>required int32 state = 4;</code>
     */
    public boolean hasState() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>required int32 state = 4;</code>
     */
    public int getState() {
      return state_;
    }
    /**
     * <code>required int32 state = 4;</code>
     */
    public Builder setState(int value) {
      bitField0_ |= 0x00000008;
      state_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 state = 4;</code>
     */
    public Builder clearState() {
      bitField0_ = (bitField0_ & ~0x00000008);
      state_ = 0;
      onChanged();
      return this;
    }

    private int x_ ;
    /**
     * <pre>
     * 建筑所在位置的x坐标
     * </pre>
     *
     * <code>required int32 x = 5;</code>
     */
    public boolean hasX() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     * 建筑所在位置的x坐标
     * </pre>
     *
     * <code>required int32 x = 5;</code>
     */
    public int getX() {
      return x_;
    }
    /**
     * <pre>
     * 建筑所在位置的x坐标
     * </pre>
     *
     * <code>required int32 x = 5;</code>
     */
    public Builder setX(int value) {
      bitField0_ |= 0x00000010;
      x_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 建筑所在位置的x坐标
     * </pre>
     *
     * <code>required int32 x = 5;</code>
     */
    public Builder clearX() {
      bitField0_ = (bitField0_ & ~0x00000010);
      x_ = 0;
      onChanged();
      return this;
    }

    private int y_ ;
    /**
     * <pre>
     * 建筑所在位置的y坐标
     * </pre>
     *
     * <code>required int32 y = 6;</code>
     */
    public boolean hasY() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <pre>
     * 建筑所在位置的y坐标
     * </pre>
     *
     * <code>required int32 y = 6;</code>
     */
    public int getY() {
      return y_;
    }
    /**
     * <pre>
     * 建筑所在位置的y坐标
     * </pre>
     *
     * <code>required int32 y = 6;</code>
     */
    public Builder setY(int value) {
      bitField0_ |= 0x00000020;
      y_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 建筑所在位置的y坐标
     * </pre>
     *
     * <code>required int32 y = 6;</code>
     */
    public Builder clearY() {
      bitField0_ = (bitField0_ & ~0x00000020);
      y_ = 0;
      onChanged();
      return this;
    }

    private int destroyTime_ ;
    /**
     * <code>required int32 destroyTime = 7;</code>
     */
    public boolean hasDestroyTime() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    /**
     * <code>required int32 destroyTime = 7;</code>
     */
    public int getDestroyTime() {
      return destroyTime_;
    }
    /**
     * <code>required int32 destroyTime = 7;</code>
     */
    public Builder setDestroyTime(int value) {
      bitField0_ |= 0x00000040;
      destroyTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 destroyTime = 7;</code>
     */
    public Builder clearDestroyTime() {
      bitField0_ = (bitField0_ & ~0x00000040);
      destroyTime_ = 0;
      onChanged();
      return this;
    }

    private long helperId_ ;
    /**
     * <pre>
     * 帮助里面的唯一ID
     * </pre>
     *
     * <code>required int64 helperId = 8;</code>
     */
    public boolean hasHelperId() {
      return ((bitField0_ & 0x00000080) == 0x00000080);
    }
    /**
     * <pre>
     * 帮助里面的唯一ID
     * </pre>
     *
     * <code>required int64 helperId = 8;</code>
     */
    public long getHelperId() {
      return helperId_;
    }
    /**
     * <pre>
     * 帮助里面的唯一ID
     * </pre>
     *
     * <code>required int64 helperId = 8;</code>
     */
    public Builder setHelperId(long value) {
      bitField0_ |= 0x00000080;
      helperId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 帮助里面的唯一ID
     * </pre>
     *
     * <code>required int64 helperId = 8;</code>
     */
    public Builder clearHelperId() {
      bitField0_ = (bitField0_ & ~0x00000080);
      helperId_ = 0L;
      onChanged();
      return this;
    }

    private long buildingId_ ;
    /**
     * <pre>
     * 建筑唯一ID
     * </pre>
     *
     * <code>required int64 buildingId = 9;</code>
     */
    public boolean hasBuildingId() {
      return ((bitField0_ & 0x00000100) == 0x00000100);
    }
    /**
     * <pre>
     * 建筑唯一ID
     * </pre>
     *
     * <code>required int64 buildingId = 9;</code>
     */
    public long getBuildingId() {
      return buildingId_;
    }
    /**
     * <pre>
     * 建筑唯一ID
     * </pre>
     *
     * <code>required int64 buildingId = 9;</code>
     */
    public Builder setBuildingId(long value) {
      bitField0_ |= 0x00000100;
      buildingId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 建筑唯一ID
     * </pre>
     *
     * <code>required int64 buildingId = 9;</code>
     */
    public Builder clearBuildingId() {
      bitField0_ = (bitField0_ & ~0x00000100);
      buildingId_ = 0L;
      onChanged();
      return this;
    }

    private int direction_ ;
    /**
     * <pre>
     * 建筑的方向
     * </pre>
     *
     * <code>required int32 direction = 10;</code>
     */
    public boolean hasDirection() {
      return ((bitField0_ & 0x00000200) == 0x00000200);
    }
    /**
     * <pre>
     * 建筑的方向
     * </pre>
     *
     * <code>required int32 direction = 10;</code>
     */
    public int getDirection() {
      return direction_;
    }
    /**
     * <pre>
     * 建筑的方向
     * </pre>
     *
     * <code>required int32 direction = 10;</code>
     */
    public Builder setDirection(int value) {
      bitField0_ |= 0x00000200;
      direction_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 建筑的方向
     * </pre>
     *
     * <code>required int32 direction = 10;</code>
     */
    public Builder clearDirection() {
      bitField0_ = (bitField0_ & ~0x00000200);
      direction_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.BuildingInFo)
  }

  // @@protoc_insertion_point(class_scope:client2server.BuildingInFo)
  private static final pb4client.BuildingInFo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.BuildingInFo();
  }

  public static pb4client.BuildingInFo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<BuildingInFo>
      PARSER = new com.google.protobuf.AbstractParser<BuildingInFo>() {
    public BuildingInFo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new BuildingInFo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<BuildingInFo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<BuildingInFo> getParserForType() {
    return PARSER;
  }

  public pb4client.BuildingInFo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
