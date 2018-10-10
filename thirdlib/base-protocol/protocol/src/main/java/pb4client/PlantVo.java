// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.PlantVo}
 */
public  final class PlantVo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.PlantVo)
    PlantVoOrBuilder {
  // Use PlantVo.newBuilder() to construct.
  private PlantVo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private PlantVo() {
    id_ = 0L;
    seedId_ = 0;
    buildId_ = 0L;
    overTime_ = 0;
    isBig_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private PlantVo(
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
            id_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            seedId_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            buildId_ = input.readInt64();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            overTime_ = input.readInt32();
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            isBig_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_PlantVo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_PlantVo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.PlantVo.class, pb4client.PlantVo.Builder.class);
  }

  private int bitField0_;
  public static final int ID_FIELD_NUMBER = 1;
  private long id_;
  /**
   * <pre>
   * 本条种植信息的唯一ID
   * </pre>
   *
   * <code>required int64 id = 1;</code>
   */
  public boolean hasId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 本条种植信息的唯一ID
   * </pre>
   *
   * <code>required int64 id = 1;</code>
   */
  public long getId() {
    return id_;
  }

  public static final int SEEDID_FIELD_NUMBER = 2;
  private int seedId_;
  /**
   * <pre>
   * 种植的植物ID
   * </pre>
   *
   * <code>required int32 seedId = 2;</code>
   */
  public boolean hasSeedId() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 种植的植物ID
   * </pre>
   *
   * <code>required int32 seedId = 2;</code>
   */
  public int getSeedId() {
    return seedId_;
  }

  public static final int BUILDID_FIELD_NUMBER = 3;
  private long buildId_;
  /**
   * <pre>
   * 所在建筑的唯一ID
   * </pre>
   *
   * <code>required int64 buildId = 3;</code>
   */
  public boolean hasBuildId() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 所在建筑的唯一ID
   * </pre>
   *
   * <code>required int64 buildId = 3;</code>
   */
  public long getBuildId() {
    return buildId_;
  }

  public static final int OVERTIME_FIELD_NUMBER = 4;
  private int overTime_;
  /**
   * <pre>
   * 成熟秒数
   * </pre>
   *
   * <code>required int32 overTime = 4;</code>
   */
  public boolean hasOverTime() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   * 成熟秒数
   * </pre>
   *
   * <code>required int32 overTime = 4;</code>
   */
  public int getOverTime() {
    return overTime_;
  }

  public static final int ISBIG_FIELD_NUMBER = 5;
  private int isBig_;
  /**
   * <pre>
   * 是否是大丰收  0-否 1-是
   * </pre>
   *
   * <code>required int32 isBig = 5;</code>
   */
  public boolean hasIsBig() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <pre>
   * 是否是大丰收  0-否 1-是
   * </pre>
   *
   * <code>required int32 isBig = 5;</code>
   */
  public int getIsBig() {
    return isBig_;
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
    if (!hasSeedId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasBuildId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasOverTime()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasIsBig()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, id_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, seedId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt64(3, buildId_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, overTime_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt32(5, isBig_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, id_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, seedId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, buildId_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, overTime_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, isBig_);
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
    if (!(obj instanceof pb4client.PlantVo)) {
      return super.equals(obj);
    }
    pb4client.PlantVo other = (pb4client.PlantVo) obj;

    boolean result = true;
    result = result && (hasId() == other.hasId());
    if (hasId()) {
      result = result && (getId()
          == other.getId());
    }
    result = result && (hasSeedId() == other.hasSeedId());
    if (hasSeedId()) {
      result = result && (getSeedId()
          == other.getSeedId());
    }
    result = result && (hasBuildId() == other.hasBuildId());
    if (hasBuildId()) {
      result = result && (getBuildId()
          == other.getBuildId());
    }
    result = result && (hasOverTime() == other.hasOverTime());
    if (hasOverTime()) {
      result = result && (getOverTime()
          == other.getOverTime());
    }
    result = result && (hasIsBig() == other.hasIsBig());
    if (hasIsBig()) {
      result = result && (getIsBig()
          == other.getIsBig());
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
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getId());
    }
    if (hasSeedId()) {
      hash = (37 * hash) + SEEDID_FIELD_NUMBER;
      hash = (53 * hash) + getSeedId();
    }
    if (hasBuildId()) {
      hash = (37 * hash) + BUILDID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getBuildId());
    }
    if (hasOverTime()) {
      hash = (37 * hash) + OVERTIME_FIELD_NUMBER;
      hash = (53 * hash) + getOverTime();
    }
    if (hasIsBig()) {
      hash = (37 * hash) + ISBIG_FIELD_NUMBER;
      hash = (53 * hash) + getIsBig();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.PlantVo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.PlantVo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.PlantVo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.PlantVo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.PlantVo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.PlantVo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.PlantVo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.PlantVo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.PlantVo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.PlantVo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.PlantVo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.PlantVo parseFrom(
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
  public static Builder newBuilder(pb4client.PlantVo prototype) {
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
   * Protobuf type {@code client2server.PlantVo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.PlantVo)
      pb4client.PlantVoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_PlantVo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_PlantVo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.PlantVo.class, pb4client.PlantVo.Builder.class);
    }

    // Construct using pb4client.PlantVo.newBuilder()
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
      id_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      seedId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      buildId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000004);
      overTime_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      isBig_ = 0;
      bitField0_ = (bitField0_ & ~0x00000010);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_PlantVo_descriptor;
    }

    public pb4client.PlantVo getDefaultInstanceForType() {
      return pb4client.PlantVo.getDefaultInstance();
    }

    public pb4client.PlantVo build() {
      pb4client.PlantVo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.PlantVo buildPartial() {
      pb4client.PlantVo result = new pb4client.PlantVo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.id_ = id_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.seedId_ = seedId_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.buildId_ = buildId_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.overTime_ = overTime_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.isBig_ = isBig_;
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
      if (other instanceof pb4client.PlantVo) {
        return mergeFrom((pb4client.PlantVo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.PlantVo other) {
      if (other == pb4client.PlantVo.getDefaultInstance()) return this;
      if (other.hasId()) {
        setId(other.getId());
      }
      if (other.hasSeedId()) {
        setSeedId(other.getSeedId());
      }
      if (other.hasBuildId()) {
        setBuildId(other.getBuildId());
      }
      if (other.hasOverTime()) {
        setOverTime(other.getOverTime());
      }
      if (other.hasIsBig()) {
        setIsBig(other.getIsBig());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasId()) {
        return false;
      }
      if (!hasSeedId()) {
        return false;
      }
      if (!hasBuildId()) {
        return false;
      }
      if (!hasOverTime()) {
        return false;
      }
      if (!hasIsBig()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.PlantVo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.PlantVo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long id_ ;
    /**
     * <pre>
     * 本条种植信息的唯一ID
     * </pre>
     *
     * <code>required int64 id = 1;</code>
     */
    public boolean hasId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 本条种植信息的唯一ID
     * </pre>
     *
     * <code>required int64 id = 1;</code>
     */
    public long getId() {
      return id_;
    }
    /**
     * <pre>
     * 本条种植信息的唯一ID
     * </pre>
     *
     * <code>required int64 id = 1;</code>
     */
    public Builder setId(long value) {
      bitField0_ |= 0x00000001;
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 本条种植信息的唯一ID
     * </pre>
     *
     * <code>required int64 id = 1;</code>
     */
    public Builder clearId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      id_ = 0L;
      onChanged();
      return this;
    }

    private int seedId_ ;
    /**
     * <pre>
     * 种植的植物ID
     * </pre>
     *
     * <code>required int32 seedId = 2;</code>
     */
    public boolean hasSeedId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 种植的植物ID
     * </pre>
     *
     * <code>required int32 seedId = 2;</code>
     */
    public int getSeedId() {
      return seedId_;
    }
    /**
     * <pre>
     * 种植的植物ID
     * </pre>
     *
     * <code>required int32 seedId = 2;</code>
     */
    public Builder setSeedId(int value) {
      bitField0_ |= 0x00000002;
      seedId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 种植的植物ID
     * </pre>
     *
     * <code>required int32 seedId = 2;</code>
     */
    public Builder clearSeedId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      seedId_ = 0;
      onChanged();
      return this;
    }

    private long buildId_ ;
    /**
     * <pre>
     * 所在建筑的唯一ID
     * </pre>
     *
     * <code>required int64 buildId = 3;</code>
     */
    public boolean hasBuildId() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 所在建筑的唯一ID
     * </pre>
     *
     * <code>required int64 buildId = 3;</code>
     */
    public long getBuildId() {
      return buildId_;
    }
    /**
     * <pre>
     * 所在建筑的唯一ID
     * </pre>
     *
     * <code>required int64 buildId = 3;</code>
     */
    public Builder setBuildId(long value) {
      bitField0_ |= 0x00000004;
      buildId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 所在建筑的唯一ID
     * </pre>
     *
     * <code>required int64 buildId = 3;</code>
     */
    public Builder clearBuildId() {
      bitField0_ = (bitField0_ & ~0x00000004);
      buildId_ = 0L;
      onChanged();
      return this;
    }

    private int overTime_ ;
    /**
     * <pre>
     * 成熟秒数
     * </pre>
     *
     * <code>required int32 overTime = 4;</code>
     */
    public boolean hasOverTime() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     * 成熟秒数
     * </pre>
     *
     * <code>required int32 overTime = 4;</code>
     */
    public int getOverTime() {
      return overTime_;
    }
    /**
     * <pre>
     * 成熟秒数
     * </pre>
     *
     * <code>required int32 overTime = 4;</code>
     */
    public Builder setOverTime(int value) {
      bitField0_ |= 0x00000008;
      overTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 成熟秒数
     * </pre>
     *
     * <code>required int32 overTime = 4;</code>
     */
    public Builder clearOverTime() {
      bitField0_ = (bitField0_ & ~0x00000008);
      overTime_ = 0;
      onChanged();
      return this;
    }

    private int isBig_ ;
    /**
     * <pre>
     * 是否是大丰收  0-否 1-是
     * </pre>
     *
     * <code>required int32 isBig = 5;</code>
     */
    public boolean hasIsBig() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     * 是否是大丰收  0-否 1-是
     * </pre>
     *
     * <code>required int32 isBig = 5;</code>
     */
    public int getIsBig() {
      return isBig_;
    }
    /**
     * <pre>
     * 是否是大丰收  0-否 1-是
     * </pre>
     *
     * <code>required int32 isBig = 5;</code>
     */
    public Builder setIsBig(int value) {
      bitField0_ |= 0x00000010;
      isBig_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 是否是大丰收  0-否 1-是
     * </pre>
     *
     * <code>required int32 isBig = 5;</code>
     */
    public Builder clearIsBig() {
      bitField0_ = (bitField0_ & ~0x00000010);
      isBig_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.PlantVo)
  }

  // @@protoc_insertion_point(class_scope:client2server.PlantVo)
  private static final pb4client.PlantVo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.PlantVo();
  }

  public static pb4client.PlantVo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<PlantVo>
      PARSER = new com.google.protobuf.AbstractParser<PlantVo>() {
    public PlantVo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new PlantVo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<PlantVo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<PlantVo> getParserForType() {
    return PARSER;
  }

  public pb4client.PlantVo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
