// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3002
 * 服务器 -&gt; 客户端
 * 城池建造完毕主推
 * </pre>
 *
 * Protobuf type {@code client2server.BuildCityOver}
 */
public  final class BuildCityOver extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.BuildCityOver)
    BuildCityOverOrBuilder {
  // Use BuildCityOver.newBuilder() to construct.
  private BuildCityOver(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private BuildCityOver() {
    playerId_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private BuildCityOver(
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
            playerId_ = input.readInt64();
            break;
          }
          case 18: {
            pb4client.CityInfo.Builder subBuilder = null;
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
              subBuilder = cityInfo_.toBuilder();
            }
            cityInfo_ = input.readMessage(pb4client.CityInfo.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(cityInfo_);
              cityInfo_ = subBuilder.buildPartial();
            }
            bitField0_ |= 0x00000002;
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
    return pb4client.War2GamePkt.internal_static_client2server_BuildCityOver_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_BuildCityOver_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.BuildCityOver.class, pb4client.BuildCityOver.Builder.class);
  }

  private int bitField0_;
  public static final int PLAYERID_FIELD_NUMBER = 1;
  private long playerId_;
  /**
   * <pre>
   *主人ID
   * </pre>
   *
   * <code>required int64 playerId = 1;</code>
   */
  public boolean hasPlayerId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *主人ID
   * </pre>
   *
   * <code>required int64 playerId = 1;</code>
   */
  public long getPlayerId() {
    return playerId_;
  }

  public static final int CITYINFO_FIELD_NUMBER = 2;
  private pb4client.CityInfo cityInfo_;
  /**
   * <pre>
   *城池信息
   * </pre>
   *
   * <code>required .client2server.CityInfo cityInfo = 2;</code>
   */
  public boolean hasCityInfo() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *城池信息
   * </pre>
   *
   * <code>required .client2server.CityInfo cityInfo = 2;</code>
   */
  public pb4client.CityInfo getCityInfo() {
    return cityInfo_ == null ? pb4client.CityInfo.getDefaultInstance() : cityInfo_;
  }
  /**
   * <pre>
   *城池信息
   * </pre>
   *
   * <code>required .client2server.CityInfo cityInfo = 2;</code>
   */
  public pb4client.CityInfoOrBuilder getCityInfoOrBuilder() {
    return cityInfo_ == null ? pb4client.CityInfo.getDefaultInstance() : cityInfo_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasPlayerId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasCityInfo()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!getCityInfo().isInitialized()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, playerId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeMessage(2, getCityInfo());
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, playerId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getCityInfo());
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
    if (!(obj instanceof pb4client.BuildCityOver)) {
      return super.equals(obj);
    }
    pb4client.BuildCityOver other = (pb4client.BuildCityOver) obj;

    boolean result = true;
    result = result && (hasPlayerId() == other.hasPlayerId());
    if (hasPlayerId()) {
      result = result && (getPlayerId()
          == other.getPlayerId());
    }
    result = result && (hasCityInfo() == other.hasCityInfo());
    if (hasCityInfo()) {
      result = result && getCityInfo()
          .equals(other.getCityInfo());
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
    if (hasPlayerId()) {
      hash = (37 * hash) + PLAYERID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getPlayerId());
    }
    if (hasCityInfo()) {
      hash = (37 * hash) + CITYINFO_FIELD_NUMBER;
      hash = (53 * hash) + getCityInfo().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.BuildCityOver parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.BuildCityOver parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.BuildCityOver parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.BuildCityOver parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.BuildCityOver parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.BuildCityOver parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.BuildCityOver parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.BuildCityOver parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.BuildCityOver parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.BuildCityOver parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.BuildCityOver parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.BuildCityOver parseFrom(
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
  public static Builder newBuilder(pb4client.BuildCityOver prototype) {
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
   * msgType = 3002
   * 服务器 -&gt; 客户端
   * 城池建造完毕主推
   * </pre>
   *
   * Protobuf type {@code client2server.BuildCityOver}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.BuildCityOver)
      pb4client.BuildCityOverOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_BuildCityOver_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_BuildCityOver_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.BuildCityOver.class, pb4client.BuildCityOver.Builder.class);
    }

    // Construct using pb4client.BuildCityOver.newBuilder()
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
        getCityInfoFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      playerId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (cityInfoBuilder_ == null) {
        cityInfo_ = null;
      } else {
        cityInfoBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_BuildCityOver_descriptor;
    }

    public pb4client.BuildCityOver getDefaultInstanceForType() {
      return pb4client.BuildCityOver.getDefaultInstance();
    }

    public pb4client.BuildCityOver build() {
      pb4client.BuildCityOver result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.BuildCityOver buildPartial() {
      pb4client.BuildCityOver result = new pb4client.BuildCityOver(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.playerId_ = playerId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      if (cityInfoBuilder_ == null) {
        result.cityInfo_ = cityInfo_;
      } else {
        result.cityInfo_ = cityInfoBuilder_.build();
      }
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
      if (other instanceof pb4client.BuildCityOver) {
        return mergeFrom((pb4client.BuildCityOver)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.BuildCityOver other) {
      if (other == pb4client.BuildCityOver.getDefaultInstance()) return this;
      if (other.hasPlayerId()) {
        setPlayerId(other.getPlayerId());
      }
      if (other.hasCityInfo()) {
        mergeCityInfo(other.getCityInfo());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasPlayerId()) {
        return false;
      }
      if (!hasCityInfo()) {
        return false;
      }
      if (!getCityInfo().isInitialized()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.BuildCityOver parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.BuildCityOver) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long playerId_ ;
    /**
     * <pre>
     *主人ID
     * </pre>
     *
     * <code>required int64 playerId = 1;</code>
     */
    public boolean hasPlayerId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *主人ID
     * </pre>
     *
     * <code>required int64 playerId = 1;</code>
     */
    public long getPlayerId() {
      return playerId_;
    }
    /**
     * <pre>
     *主人ID
     * </pre>
     *
     * <code>required int64 playerId = 1;</code>
     */
    public Builder setPlayerId(long value) {
      bitField0_ |= 0x00000001;
      playerId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *主人ID
     * </pre>
     *
     * <code>required int64 playerId = 1;</code>
     */
    public Builder clearPlayerId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      playerId_ = 0L;
      onChanged();
      return this;
    }

    private pb4client.CityInfo cityInfo_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.CityInfo, pb4client.CityInfo.Builder, pb4client.CityInfoOrBuilder> cityInfoBuilder_;
    /**
     * <pre>
     *城池信息
     * </pre>
     *
     * <code>required .client2server.CityInfo cityInfo = 2;</code>
     */
    public boolean hasCityInfo() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *城池信息
     * </pre>
     *
     * <code>required .client2server.CityInfo cityInfo = 2;</code>
     */
    public pb4client.CityInfo getCityInfo() {
      if (cityInfoBuilder_ == null) {
        return cityInfo_ == null ? pb4client.CityInfo.getDefaultInstance() : cityInfo_;
      } else {
        return cityInfoBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     *城池信息
     * </pre>
     *
     * <code>required .client2server.CityInfo cityInfo = 2;</code>
     */
    public Builder setCityInfo(pb4client.CityInfo value) {
      if (cityInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        cityInfo_ = value;
        onChanged();
      } else {
        cityInfoBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <pre>
     *城池信息
     * </pre>
     *
     * <code>required .client2server.CityInfo cityInfo = 2;</code>
     */
    public Builder setCityInfo(
        pb4client.CityInfo.Builder builderForValue) {
      if (cityInfoBuilder_ == null) {
        cityInfo_ = builderForValue.build();
        onChanged();
      } else {
        cityInfoBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <pre>
     *城池信息
     * </pre>
     *
     * <code>required .client2server.CityInfo cityInfo = 2;</code>
     */
    public Builder mergeCityInfo(pb4client.CityInfo value) {
      if (cityInfoBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002) &&
            cityInfo_ != null &&
            cityInfo_ != pb4client.CityInfo.getDefaultInstance()) {
          cityInfo_ =
            pb4client.CityInfo.newBuilder(cityInfo_).mergeFrom(value).buildPartial();
        } else {
          cityInfo_ = value;
        }
        onChanged();
      } else {
        cityInfoBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <pre>
     *城池信息
     * </pre>
     *
     * <code>required .client2server.CityInfo cityInfo = 2;</code>
     */
    public Builder clearCityInfo() {
      if (cityInfoBuilder_ == null) {
        cityInfo_ = null;
        onChanged();
      } else {
        cityInfoBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }
    /**
     * <pre>
     *城池信息
     * </pre>
     *
     * <code>required .client2server.CityInfo cityInfo = 2;</code>
     */
    public pb4client.CityInfo.Builder getCityInfoBuilder() {
      bitField0_ |= 0x00000002;
      onChanged();
      return getCityInfoFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     *城池信息
     * </pre>
     *
     * <code>required .client2server.CityInfo cityInfo = 2;</code>
     */
    public pb4client.CityInfoOrBuilder getCityInfoOrBuilder() {
      if (cityInfoBuilder_ != null) {
        return cityInfoBuilder_.getMessageOrBuilder();
      } else {
        return cityInfo_ == null ?
            pb4client.CityInfo.getDefaultInstance() : cityInfo_;
      }
    }
    /**
     * <pre>
     *城池信息
     * </pre>
     *
     * <code>required .client2server.CityInfo cityInfo = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.CityInfo, pb4client.CityInfo.Builder, pb4client.CityInfoOrBuilder> 
        getCityInfoFieldBuilder() {
      if (cityInfoBuilder_ == null) {
        cityInfoBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4client.CityInfo, pb4client.CityInfo.Builder, pb4client.CityInfoOrBuilder>(
                getCityInfo(),
                getParentForChildren(),
                isClean());
        cityInfo_ = null;
      }
      return cityInfoBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.BuildCityOver)
  }

  // @@protoc_insertion_point(class_scope:client2server.BuildCityOver)
  private static final pb4client.BuildCityOver DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.BuildCityOver();
  }

  public static pb4client.BuildCityOver getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<BuildCityOver>
      PARSER = new com.google.protobuf.AbstractParser<BuildCityOver>() {
    public BuildCityOver parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new BuildCityOver(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<BuildCityOver> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<BuildCityOver> getParserForType() {
    return PARSER;
  }

  public pb4client.BuildCityOver getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

