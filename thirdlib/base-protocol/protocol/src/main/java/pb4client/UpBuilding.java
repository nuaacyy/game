// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 192
 * 客户端 -&gt; 服务器
 * 建筑升级
 * </pre>
 *
 * Protobuf type {@code client2server.UpBuilding}
 */
public  final class UpBuilding extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.UpBuilding)
    UpBuildingOrBuilder {
  // Use UpBuilding.newBuilder() to construct.
  private UpBuilding(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private UpBuilding() {
    cityId_ = 0L;
    buildingId_ = 0L;
    lvUpType_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private UpBuilding(
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
            cityId_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            buildingId_ = input.readInt64();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            lvUpType_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_UpBuilding_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_UpBuilding_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.UpBuilding.class, pb4client.UpBuilding.Builder.class);
  }

  private int bitField0_;
  public static final int CITYID_FIELD_NUMBER = 1;
  private long cityId_;
  /**
   * <pre>
   * 城池ID
   * </pre>
   *
   * <code>required int64 cityId = 1;</code>
   */
  public boolean hasCityId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 城池ID
   * </pre>
   *
   * <code>required int64 cityId = 1;</code>
   */
  public long getCityId() {
    return cityId_;
  }

  public static final int BUILDINGID_FIELD_NUMBER = 2;
  private long buildingId_;
  /**
   * <pre>
   * 建筑实例唯一ID
   * </pre>
   *
   * <code>required int64 buildingId = 2;</code>
   */
  public boolean hasBuildingId() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 建筑实例唯一ID
   * </pre>
   *
   * <code>required int64 buildingId = 2;</code>
   */
  public long getBuildingId() {
    return buildingId_;
  }

  public static final int LVUPTYPE_FIELD_NUMBER = 3;
  private int lvUpType_;
  /**
   * <pre>
   * 升级类型 1-普通  2-秒
   * </pre>
   *
   * <code>required int32 lvUpType = 3;</code>
   */
  public boolean hasLvUpType() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 升级类型 1-普通  2-秒
   * </pre>
   *
   * <code>required int32 lvUpType = 3;</code>
   */
  public int getLvUpType() {
    return lvUpType_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasCityId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasBuildingId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasLvUpType()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, cityId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt64(2, buildingId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, lvUpType_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, cityId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, buildingId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, lvUpType_);
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
    if (!(obj instanceof pb4client.UpBuilding)) {
      return super.equals(obj);
    }
    pb4client.UpBuilding other = (pb4client.UpBuilding) obj;

    boolean result = true;
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
    result = result && (hasLvUpType() == other.hasLvUpType());
    if (hasLvUpType()) {
      result = result && (getLvUpType()
          == other.getLvUpType());
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
    if (hasLvUpType()) {
      hash = (37 * hash) + LVUPTYPE_FIELD_NUMBER;
      hash = (53 * hash) + getLvUpType();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.UpBuilding parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.UpBuilding parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.UpBuilding parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.UpBuilding parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.UpBuilding parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.UpBuilding parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.UpBuilding parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.UpBuilding parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.UpBuilding parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.UpBuilding parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.UpBuilding parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.UpBuilding parseFrom(
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
  public static Builder newBuilder(pb4client.UpBuilding prototype) {
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
   * msgType = 192
   * 客户端 -&gt; 服务器
   * 建筑升级
   * </pre>
   *
   * Protobuf type {@code client2server.UpBuilding}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.UpBuilding)
      pb4client.UpBuildingOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_UpBuilding_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_UpBuilding_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.UpBuilding.class, pb4client.UpBuilding.Builder.class);
    }

    // Construct using pb4client.UpBuilding.newBuilder()
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
      cityId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      buildingId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      lvUpType_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_UpBuilding_descriptor;
    }

    public pb4client.UpBuilding getDefaultInstanceForType() {
      return pb4client.UpBuilding.getDefaultInstance();
    }

    public pb4client.UpBuilding build() {
      pb4client.UpBuilding result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.UpBuilding buildPartial() {
      pb4client.UpBuilding result = new pb4client.UpBuilding(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.cityId_ = cityId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.buildingId_ = buildingId_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.lvUpType_ = lvUpType_;
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
      if (other instanceof pb4client.UpBuilding) {
        return mergeFrom((pb4client.UpBuilding)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.UpBuilding other) {
      if (other == pb4client.UpBuilding.getDefaultInstance()) return this;
      if (other.hasCityId()) {
        setCityId(other.getCityId());
      }
      if (other.hasBuildingId()) {
        setBuildingId(other.getBuildingId());
      }
      if (other.hasLvUpType()) {
        setLvUpType(other.getLvUpType());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasCityId()) {
        return false;
      }
      if (!hasBuildingId()) {
        return false;
      }
      if (!hasLvUpType()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.UpBuilding parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.UpBuilding) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long cityId_ ;
    /**
     * <pre>
     * 城池ID
     * </pre>
     *
     * <code>required int64 cityId = 1;</code>
     */
    public boolean hasCityId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 城池ID
     * </pre>
     *
     * <code>required int64 cityId = 1;</code>
     */
    public long getCityId() {
      return cityId_;
    }
    /**
     * <pre>
     * 城池ID
     * </pre>
     *
     * <code>required int64 cityId = 1;</code>
     */
    public Builder setCityId(long value) {
      bitField0_ |= 0x00000001;
      cityId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 城池ID
     * </pre>
     *
     * <code>required int64 cityId = 1;</code>
     */
    public Builder clearCityId() {
      bitField0_ = (bitField0_ & ~0x00000001);
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
     * <code>required int64 buildingId = 2;</code>
     */
    public boolean hasBuildingId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 建筑实例唯一ID
     * </pre>
     *
     * <code>required int64 buildingId = 2;</code>
     */
    public long getBuildingId() {
      return buildingId_;
    }
    /**
     * <pre>
     * 建筑实例唯一ID
     * </pre>
     *
     * <code>required int64 buildingId = 2;</code>
     */
    public Builder setBuildingId(long value) {
      bitField0_ |= 0x00000002;
      buildingId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 建筑实例唯一ID
     * </pre>
     *
     * <code>required int64 buildingId = 2;</code>
     */
    public Builder clearBuildingId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      buildingId_ = 0L;
      onChanged();
      return this;
    }

    private int lvUpType_ ;
    /**
     * <pre>
     * 升级类型 1-普通  2-秒
     * </pre>
     *
     * <code>required int32 lvUpType = 3;</code>
     */
    public boolean hasLvUpType() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 升级类型 1-普通  2-秒
     * </pre>
     *
     * <code>required int32 lvUpType = 3;</code>
     */
    public int getLvUpType() {
      return lvUpType_;
    }
    /**
     * <pre>
     * 升级类型 1-普通  2-秒
     * </pre>
     *
     * <code>required int32 lvUpType = 3;</code>
     */
    public Builder setLvUpType(int value) {
      bitField0_ |= 0x00000004;
      lvUpType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 升级类型 1-普通  2-秒
     * </pre>
     *
     * <code>required int32 lvUpType = 3;</code>
     */
    public Builder clearLvUpType() {
      bitField0_ = (bitField0_ & ~0x00000004);
      lvUpType_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.UpBuilding)
  }

  // @@protoc_insertion_point(class_scope:client2server.UpBuilding)
  private static final pb4client.UpBuilding DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.UpBuilding();
  }

  public static pb4client.UpBuilding getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<UpBuilding>
      PARSER = new com.google.protobuf.AbstractParser<UpBuilding>() {
    public UpBuilding parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new UpBuilding(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<UpBuilding> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<UpBuilding> getParserForType() {
    return PARSER;
  }

  public pb4client.UpBuilding getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

