// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * 遗迹地块信息
 * </pre>
 *
 * Protobuf type {@code client2server.RelicLandInfo}
 */
public  final class RelicLandInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.RelicLandInfo)
    RelicLandInfoOrBuilder {
  // Use RelicLandInfo.newBuilder() to construct.
  private RelicLandInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RelicLandInfo() {
    relicId_ = 0;
    tileId_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private RelicLandInfo(
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
          case 104: {
            bitField0_ |= 0x00000001;
            relicId_ = input.readInt32();
            break;
          }
          case 112: {
            bitField0_ |= 0x00000002;
            tileId_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_RelicLandInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_RelicLandInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.RelicLandInfo.class, pb4client.RelicLandInfo.Builder.class);
  }

  private int bitField0_;
  public static final int RELICID_FIELD_NUMBER = 13;
  private int relicId_;
  /**
   * <pre>
   * 如果类型是遗迹的话,这个是relic表的id字段,模版与等级都可以取到
   * </pre>
   *
   * <code>optional int32 relicId = 13;</code>
   */
  public boolean hasRelicId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 如果类型是遗迹的话,这个是relic表的id字段,模版与等级都可以取到
   * </pre>
   *
   * <code>optional int32 relicId = 13;</code>
   */
  public int getRelicId() {
    return relicId_;
  }

  public static final int TILEID_FIELD_NUMBER = 14;
  private int tileId_;
  /**
   * <pre>
   * (客户端使用)
   * </pre>
   *
   * <code>optional int32 tileId = 14;</code>
   */
  public boolean hasTileId() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * (客户端使用)
   * </pre>
   *
   * <code>optional int32 tileId = 14;</code>
   */
  public int getTileId() {
    return tileId_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(13, relicId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(14, tileId_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(13, relicId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(14, tileId_);
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
    if (!(obj instanceof pb4client.RelicLandInfo)) {
      return super.equals(obj);
    }
    pb4client.RelicLandInfo other = (pb4client.RelicLandInfo) obj;

    boolean result = true;
    result = result && (hasRelicId() == other.hasRelicId());
    if (hasRelicId()) {
      result = result && (getRelicId()
          == other.getRelicId());
    }
    result = result && (hasTileId() == other.hasTileId());
    if (hasTileId()) {
      result = result && (getTileId()
          == other.getTileId());
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
    if (hasRelicId()) {
      hash = (37 * hash) + RELICID_FIELD_NUMBER;
      hash = (53 * hash) + getRelicId();
    }
    if (hasTileId()) {
      hash = (37 * hash) + TILEID_FIELD_NUMBER;
      hash = (53 * hash) + getTileId();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.RelicLandInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.RelicLandInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.RelicLandInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.RelicLandInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.RelicLandInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.RelicLandInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.RelicLandInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.RelicLandInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.RelicLandInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.RelicLandInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.RelicLandInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.RelicLandInfo parseFrom(
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
  public static Builder newBuilder(pb4client.RelicLandInfo prototype) {
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
   * 遗迹地块信息
   * </pre>
   *
   * Protobuf type {@code client2server.RelicLandInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.RelicLandInfo)
      pb4client.RelicLandInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_RelicLandInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_RelicLandInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.RelicLandInfo.class, pb4client.RelicLandInfo.Builder.class);
    }

    // Construct using pb4client.RelicLandInfo.newBuilder()
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
      relicId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      tileId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_RelicLandInfo_descriptor;
    }

    public pb4client.RelicLandInfo getDefaultInstanceForType() {
      return pb4client.RelicLandInfo.getDefaultInstance();
    }

    public pb4client.RelicLandInfo build() {
      pb4client.RelicLandInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.RelicLandInfo buildPartial() {
      pb4client.RelicLandInfo result = new pb4client.RelicLandInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.relicId_ = relicId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.tileId_ = tileId_;
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
      if (other instanceof pb4client.RelicLandInfo) {
        return mergeFrom((pb4client.RelicLandInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.RelicLandInfo other) {
      if (other == pb4client.RelicLandInfo.getDefaultInstance()) return this;
      if (other.hasRelicId()) {
        setRelicId(other.getRelicId());
      }
      if (other.hasTileId()) {
        setTileId(other.getTileId());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.RelicLandInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.RelicLandInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int relicId_ ;
    /**
     * <pre>
     * 如果类型是遗迹的话,这个是relic表的id字段,模版与等级都可以取到
     * </pre>
     *
     * <code>optional int32 relicId = 13;</code>
     */
    public boolean hasRelicId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 如果类型是遗迹的话,这个是relic表的id字段,模版与等级都可以取到
     * </pre>
     *
     * <code>optional int32 relicId = 13;</code>
     */
    public int getRelicId() {
      return relicId_;
    }
    /**
     * <pre>
     * 如果类型是遗迹的话,这个是relic表的id字段,模版与等级都可以取到
     * </pre>
     *
     * <code>optional int32 relicId = 13;</code>
     */
    public Builder setRelicId(int value) {
      bitField0_ |= 0x00000001;
      relicId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 如果类型是遗迹的话,这个是relic表的id字段,模版与等级都可以取到
     * </pre>
     *
     * <code>optional int32 relicId = 13;</code>
     */
    public Builder clearRelicId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      relicId_ = 0;
      onChanged();
      return this;
    }

    private int tileId_ ;
    /**
     * <pre>
     * (客户端使用)
     * </pre>
     *
     * <code>optional int32 tileId = 14;</code>
     */
    public boolean hasTileId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * (客户端使用)
     * </pre>
     *
     * <code>optional int32 tileId = 14;</code>
     */
    public int getTileId() {
      return tileId_;
    }
    /**
     * <pre>
     * (客户端使用)
     * </pre>
     *
     * <code>optional int32 tileId = 14;</code>
     */
    public Builder setTileId(int value) {
      bitField0_ |= 0x00000002;
      tileId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * (客户端使用)
     * </pre>
     *
     * <code>optional int32 tileId = 14;</code>
     */
    public Builder clearTileId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      tileId_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.RelicLandInfo)
  }

  // @@protoc_insertion_point(class_scope:client2server.RelicLandInfo)
  private static final pb4client.RelicLandInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.RelicLandInfo();
  }

  public static pb4client.RelicLandInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<RelicLandInfo>
      PARSER = new com.google.protobuf.AbstractParser<RelicLandInfo>() {
    public RelicLandInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new RelicLandInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<RelicLandInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RelicLandInfo> getParserForType() {
    return PARSER;
  }

  public pb4client.RelicLandInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
