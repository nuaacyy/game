// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 118
 * 客户端 -&gt; 服务器
 * 查询某野外要塞/军营的守军恢复时间
 *废弃
 * </pre>
 *
 * Protobuf type {@code client2server.RestoreCityEndTime}
 */
public  final class RestoreCityEndTime extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.RestoreCityEndTime)
    RestoreCityEndTimeOrBuilder {
  // Use RestoreCityEndTime.newBuilder() to construct.
  private RestoreCityEndTime(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RestoreCityEndTime() {
    cityX_ = 0;
    cityY_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private RestoreCityEndTime(
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
            cityX_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            cityY_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_RestoreCityEndTime_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_RestoreCityEndTime_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.RestoreCityEndTime.class, pb4client.RestoreCityEndTime.Builder.class);
  }

  private int bitField0_;
  public static final int CITYX_FIELD_NUMBER = 1;
  private int cityX_;
  /**
   * <code>required int32 cityX = 1;</code>
   */
  public boolean hasCityX() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required int32 cityX = 1;</code>
   */
  public int getCityX() {
    return cityX_;
  }

  public static final int CITYY_FIELD_NUMBER = 2;
  private int cityY_;
  /**
   * <code>required int32 cityY = 2;</code>
   */
  public boolean hasCityY() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>required int32 cityY = 2;</code>
   */
  public int getCityY() {
    return cityY_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasCityX()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasCityY()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, cityX_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, cityY_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, cityX_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, cityY_);
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
    if (!(obj instanceof pb4client.RestoreCityEndTime)) {
      return super.equals(obj);
    }
    pb4client.RestoreCityEndTime other = (pb4client.RestoreCityEndTime) obj;

    boolean result = true;
    result = result && (hasCityX() == other.hasCityX());
    if (hasCityX()) {
      result = result && (getCityX()
          == other.getCityX());
    }
    result = result && (hasCityY() == other.hasCityY());
    if (hasCityY()) {
      result = result && (getCityY()
          == other.getCityY());
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
    if (hasCityX()) {
      hash = (37 * hash) + CITYX_FIELD_NUMBER;
      hash = (53 * hash) + getCityX();
    }
    if (hasCityY()) {
      hash = (37 * hash) + CITYY_FIELD_NUMBER;
      hash = (53 * hash) + getCityY();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.RestoreCityEndTime parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.RestoreCityEndTime parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.RestoreCityEndTime parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.RestoreCityEndTime parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.RestoreCityEndTime parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.RestoreCityEndTime parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.RestoreCityEndTime parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.RestoreCityEndTime parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.RestoreCityEndTime parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.RestoreCityEndTime parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.RestoreCityEndTime parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.RestoreCityEndTime parseFrom(
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
  public static Builder newBuilder(pb4client.RestoreCityEndTime prototype) {
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
   * msgType = 118
   * 客户端 -&gt; 服务器
   * 查询某野外要塞/军营的守军恢复时间
   *废弃
   * </pre>
   *
   * Protobuf type {@code client2server.RestoreCityEndTime}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.RestoreCityEndTime)
      pb4client.RestoreCityEndTimeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_RestoreCityEndTime_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_RestoreCityEndTime_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.RestoreCityEndTime.class, pb4client.RestoreCityEndTime.Builder.class);
    }

    // Construct using pb4client.RestoreCityEndTime.newBuilder()
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
      cityX_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      cityY_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_RestoreCityEndTime_descriptor;
    }

    public pb4client.RestoreCityEndTime getDefaultInstanceForType() {
      return pb4client.RestoreCityEndTime.getDefaultInstance();
    }

    public pb4client.RestoreCityEndTime build() {
      pb4client.RestoreCityEndTime result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.RestoreCityEndTime buildPartial() {
      pb4client.RestoreCityEndTime result = new pb4client.RestoreCityEndTime(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.cityX_ = cityX_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.cityY_ = cityY_;
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
      if (other instanceof pb4client.RestoreCityEndTime) {
        return mergeFrom((pb4client.RestoreCityEndTime)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.RestoreCityEndTime other) {
      if (other == pb4client.RestoreCityEndTime.getDefaultInstance()) return this;
      if (other.hasCityX()) {
        setCityX(other.getCityX());
      }
      if (other.hasCityY()) {
        setCityY(other.getCityY());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasCityX()) {
        return false;
      }
      if (!hasCityY()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.RestoreCityEndTime parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.RestoreCityEndTime) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int cityX_ ;
    /**
     * <code>required int32 cityX = 1;</code>
     */
    public boolean hasCityX() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 cityX = 1;</code>
     */
    public int getCityX() {
      return cityX_;
    }
    /**
     * <code>required int32 cityX = 1;</code>
     */
    public Builder setCityX(int value) {
      bitField0_ |= 0x00000001;
      cityX_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 cityX = 1;</code>
     */
    public Builder clearCityX() {
      bitField0_ = (bitField0_ & ~0x00000001);
      cityX_ = 0;
      onChanged();
      return this;
    }

    private int cityY_ ;
    /**
     * <code>required int32 cityY = 2;</code>
     */
    public boolean hasCityY() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 cityY = 2;</code>
     */
    public int getCityY() {
      return cityY_;
    }
    /**
     * <code>required int32 cityY = 2;</code>
     */
    public Builder setCityY(int value) {
      bitField0_ |= 0x00000002;
      cityY_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 cityY = 2;</code>
     */
    public Builder clearCityY() {
      bitField0_ = (bitField0_ & ~0x00000002);
      cityY_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.RestoreCityEndTime)
  }

  // @@protoc_insertion_point(class_scope:client2server.RestoreCityEndTime)
  private static final pb4client.RestoreCityEndTime DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.RestoreCityEndTime();
  }

  public static pb4client.RestoreCityEndTime getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<RestoreCityEndTime>
      PARSER = new com.google.protobuf.AbstractParser<RestoreCityEndTime>() {
    public RestoreCityEndTime parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new RestoreCityEndTime(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<RestoreCityEndTime> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RestoreCityEndTime> getParserForType() {
    return PARSER;
  }

  public pb4client.RestoreCityEndTime getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
