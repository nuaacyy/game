// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.CityChangeInfo}
 */
public  final class CityChangeInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.CityChangeInfo)
    CityChangeInfoOrBuilder {
  // Use CityChangeInfo.newBuilder() to construct.
  private CityChangeInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CityChangeInfo() {
    cityId_ = 0L;
    changeType_ = 0;
    charngeValue_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CityChangeInfo(
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
            changeType_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            charngeValue_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_CityChangeInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_CityChangeInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.CityChangeInfo.class, pb4client.CityChangeInfo.Builder.class);
  }

  private int bitField0_;
  public static final int CITYID_FIELD_NUMBER = 1;
  private long cityId_;
  /**
   * <pre>
   *城池ID
   * </pre>
   *
   * <code>required int64 cityId = 1;</code>
   */
  public boolean hasCityId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *城池ID
   * </pre>
   *
   * <code>required int64 cityId = 1;</code>
   */
  public long getCityId() {
    return cityId_;
  }

  public static final int CHANGETYPE_FIELD_NUMBER = 2;
  private int changeType_;
  /**
   * <pre>
   *变化类型 （1.城池耐久度上限  2.Cost值  3.出征队列  4.扩建次数上限 5.预备兵上限 6.开启队列前锋数量 7-部队容纳数变化 8.增加城池等级 9.要塞耐久上限）
   * </pre>
   *
   * <code>required int32 changeType = 2;</code>
   */
  public boolean hasChangeType() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *变化类型 （1.城池耐久度上限  2.Cost值  3.出征队列  4.扩建次数上限 5.预备兵上限 6.开启队列前锋数量 7-部队容纳数变化 8.增加城池等级 9.要塞耐久上限）
   * </pre>
   *
   * <code>required int32 changeType = 2;</code>
   */
  public int getChangeType() {
    return changeType_;
  }

  public static final int CHARNGEVALUE_FIELD_NUMBER = 3;
  private int charngeValue_;
  /**
   * <pre>
   *变化值
   * </pre>
   *
   * <code>required int32 charngeValue = 3;</code>
   */
  public boolean hasCharngeValue() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   *变化值
   * </pre>
   *
   * <code>required int32 charngeValue = 3;</code>
   */
  public int getCharngeValue() {
    return charngeValue_;
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
    if (!hasChangeType()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasCharngeValue()) {
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
      output.writeInt32(2, changeType_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, charngeValue_);
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
        .computeInt32Size(2, changeType_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, charngeValue_);
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
    if (!(obj instanceof pb4client.CityChangeInfo)) {
      return super.equals(obj);
    }
    pb4client.CityChangeInfo other = (pb4client.CityChangeInfo) obj;

    boolean result = true;
    result = result && (hasCityId() == other.hasCityId());
    if (hasCityId()) {
      result = result && (getCityId()
          == other.getCityId());
    }
    result = result && (hasChangeType() == other.hasChangeType());
    if (hasChangeType()) {
      result = result && (getChangeType()
          == other.getChangeType());
    }
    result = result && (hasCharngeValue() == other.hasCharngeValue());
    if (hasCharngeValue()) {
      result = result && (getCharngeValue()
          == other.getCharngeValue());
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
    if (hasChangeType()) {
      hash = (37 * hash) + CHANGETYPE_FIELD_NUMBER;
      hash = (53 * hash) + getChangeType();
    }
    if (hasCharngeValue()) {
      hash = (37 * hash) + CHARNGEVALUE_FIELD_NUMBER;
      hash = (53 * hash) + getCharngeValue();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.CityChangeInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.CityChangeInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.CityChangeInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.CityChangeInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.CityChangeInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.CityChangeInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.CityChangeInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.CityChangeInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.CityChangeInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.CityChangeInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.CityChangeInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.CityChangeInfo parseFrom(
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
  public static Builder newBuilder(pb4client.CityChangeInfo prototype) {
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
   * Protobuf type {@code client2server.CityChangeInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.CityChangeInfo)
      pb4client.CityChangeInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_CityChangeInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_CityChangeInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.CityChangeInfo.class, pb4client.CityChangeInfo.Builder.class);
    }

    // Construct using pb4client.CityChangeInfo.newBuilder()
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
      changeType_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      charngeValue_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_CityChangeInfo_descriptor;
    }

    public pb4client.CityChangeInfo getDefaultInstanceForType() {
      return pb4client.CityChangeInfo.getDefaultInstance();
    }

    public pb4client.CityChangeInfo build() {
      pb4client.CityChangeInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.CityChangeInfo buildPartial() {
      pb4client.CityChangeInfo result = new pb4client.CityChangeInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.cityId_ = cityId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.changeType_ = changeType_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.charngeValue_ = charngeValue_;
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
      if (other instanceof pb4client.CityChangeInfo) {
        return mergeFrom((pb4client.CityChangeInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.CityChangeInfo other) {
      if (other == pb4client.CityChangeInfo.getDefaultInstance()) return this;
      if (other.hasCityId()) {
        setCityId(other.getCityId());
      }
      if (other.hasChangeType()) {
        setChangeType(other.getChangeType());
      }
      if (other.hasCharngeValue()) {
        setCharngeValue(other.getCharngeValue());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasCityId()) {
        return false;
      }
      if (!hasChangeType()) {
        return false;
      }
      if (!hasCharngeValue()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.CityChangeInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.CityChangeInfo) e.getUnfinishedMessage();
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
     *城池ID
     * </pre>
     *
     * <code>required int64 cityId = 1;</code>
     */
    public boolean hasCityId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *城池ID
     * </pre>
     *
     * <code>required int64 cityId = 1;</code>
     */
    public long getCityId() {
      return cityId_;
    }
    /**
     * <pre>
     *城池ID
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
     *城池ID
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

    private int changeType_ ;
    /**
     * <pre>
     *变化类型 （1.城池耐久度上限  2.Cost值  3.出征队列  4.扩建次数上限 5.预备兵上限 6.开启队列前锋数量 7-部队容纳数变化 8.增加城池等级 9.要塞耐久上限）
     * </pre>
     *
     * <code>required int32 changeType = 2;</code>
     */
    public boolean hasChangeType() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *变化类型 （1.城池耐久度上限  2.Cost值  3.出征队列  4.扩建次数上限 5.预备兵上限 6.开启队列前锋数量 7-部队容纳数变化 8.增加城池等级 9.要塞耐久上限）
     * </pre>
     *
     * <code>required int32 changeType = 2;</code>
     */
    public int getChangeType() {
      return changeType_;
    }
    /**
     * <pre>
     *变化类型 （1.城池耐久度上限  2.Cost值  3.出征队列  4.扩建次数上限 5.预备兵上限 6.开启队列前锋数量 7-部队容纳数变化 8.增加城池等级 9.要塞耐久上限）
     * </pre>
     *
     * <code>required int32 changeType = 2;</code>
     */
    public Builder setChangeType(int value) {
      bitField0_ |= 0x00000002;
      changeType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *变化类型 （1.城池耐久度上限  2.Cost值  3.出征队列  4.扩建次数上限 5.预备兵上限 6.开启队列前锋数量 7-部队容纳数变化 8.增加城池等级 9.要塞耐久上限）
     * </pre>
     *
     * <code>required int32 changeType = 2;</code>
     */
    public Builder clearChangeType() {
      bitField0_ = (bitField0_ & ~0x00000002);
      changeType_ = 0;
      onChanged();
      return this;
    }

    private int charngeValue_ ;
    /**
     * <pre>
     *变化值
     * </pre>
     *
     * <code>required int32 charngeValue = 3;</code>
     */
    public boolean hasCharngeValue() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     *变化值
     * </pre>
     *
     * <code>required int32 charngeValue = 3;</code>
     */
    public int getCharngeValue() {
      return charngeValue_;
    }
    /**
     * <pre>
     *变化值
     * </pre>
     *
     * <code>required int32 charngeValue = 3;</code>
     */
    public Builder setCharngeValue(int value) {
      bitField0_ |= 0x00000004;
      charngeValue_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *变化值
     * </pre>
     *
     * <code>required int32 charngeValue = 3;</code>
     */
    public Builder clearCharngeValue() {
      bitField0_ = (bitField0_ & ~0x00000004);
      charngeValue_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.CityChangeInfo)
  }

  // @@protoc_insertion_point(class_scope:client2server.CityChangeInfo)
  private static final pb4client.CityChangeInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.CityChangeInfo();
  }

  public static pb4client.CityChangeInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<CityChangeInfo>
      PARSER = new com.google.protobuf.AbstractParser<CityChangeInfo>() {
    public CityChangeInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new CityChangeInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CityChangeInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CityChangeInfo> getParserForType() {
    return PARSER;
  }

  public pb4client.CityChangeInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

