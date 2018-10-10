// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.MoveBuildVo}
 */
public  final class MoveBuildVo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.MoveBuildVo)
    MoveBuildVoOrBuilder {
  // Use MoveBuildVo.newBuilder() to construct.
  private MoveBuildVo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MoveBuildVo() {
    buildingId_ = 0L;
    newX_ = 0;
    newY_ = 0;
    direction_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MoveBuildVo(
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
            buildingId_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            newX_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            newY_ = input.readInt32();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
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
    return pb4client.War2GamePkt.internal_static_client2server_MoveBuildVo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_MoveBuildVo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.MoveBuildVo.class, pb4client.MoveBuildVo.Builder.class);
  }

  private int bitField0_;
  public static final int BUILDINGID_FIELD_NUMBER = 1;
  private long buildingId_;
  /**
   * <pre>
   * 唯一ID
   * </pre>
   *
   * <code>required int64 buildingId = 1;</code>
   */
  public boolean hasBuildingId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 唯一ID
   * </pre>
   *
   * <code>required int64 buildingId = 1;</code>
   */
  public long getBuildingId() {
    return buildingId_;
  }

  public static final int NEWX_FIELD_NUMBER = 2;
  private int newX_;
  /**
   * <pre>
   * x
   * </pre>
   *
   * <code>required int32 newX = 2;</code>
   */
  public boolean hasNewX() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * x
   * </pre>
   *
   * <code>required int32 newX = 2;</code>
   */
  public int getNewX() {
    return newX_;
  }

  public static final int NEWY_FIELD_NUMBER = 3;
  private int newY_;
  /**
   * <pre>
   * y
   * </pre>
   *
   * <code>required int32 newY = 3;</code>
   */
  public boolean hasNewY() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * y
   * </pre>
   *
   * <code>required int32 newY = 3;</code>
   */
  public int getNewY() {
    return newY_;
  }

  public static final int DIRECTION_FIELD_NUMBER = 4;
  private int direction_;
  /**
   * <pre>
   * 建筑朝向
   * </pre>
   *
   * <code>required int32 direction = 4;</code>
   */
  public boolean hasDirection() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   * 建筑朝向
   * </pre>
   *
   * <code>required int32 direction = 4;</code>
   */
  public int getDirection() {
    return direction_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasBuildingId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasNewX()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasNewY()) {
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
      output.writeInt64(1, buildingId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, newX_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, newY_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, direction_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, buildingId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, newX_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, newY_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, direction_);
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
    if (!(obj instanceof pb4client.MoveBuildVo)) {
      return super.equals(obj);
    }
    pb4client.MoveBuildVo other = (pb4client.MoveBuildVo) obj;

    boolean result = true;
    result = result && (hasBuildingId() == other.hasBuildingId());
    if (hasBuildingId()) {
      result = result && (getBuildingId()
          == other.getBuildingId());
    }
    result = result && (hasNewX() == other.hasNewX());
    if (hasNewX()) {
      result = result && (getNewX()
          == other.getNewX());
    }
    result = result && (hasNewY() == other.hasNewY());
    if (hasNewY()) {
      result = result && (getNewY()
          == other.getNewY());
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
    if (hasBuildingId()) {
      hash = (37 * hash) + BUILDINGID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getBuildingId());
    }
    if (hasNewX()) {
      hash = (37 * hash) + NEWX_FIELD_NUMBER;
      hash = (53 * hash) + getNewX();
    }
    if (hasNewY()) {
      hash = (37 * hash) + NEWY_FIELD_NUMBER;
      hash = (53 * hash) + getNewY();
    }
    if (hasDirection()) {
      hash = (37 * hash) + DIRECTION_FIELD_NUMBER;
      hash = (53 * hash) + getDirection();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.MoveBuildVo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.MoveBuildVo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.MoveBuildVo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.MoveBuildVo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.MoveBuildVo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.MoveBuildVo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.MoveBuildVo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.MoveBuildVo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.MoveBuildVo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.MoveBuildVo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.MoveBuildVo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.MoveBuildVo parseFrom(
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
  public static Builder newBuilder(pb4client.MoveBuildVo prototype) {
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
   * Protobuf type {@code client2server.MoveBuildVo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.MoveBuildVo)
      pb4client.MoveBuildVoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_MoveBuildVo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_MoveBuildVo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.MoveBuildVo.class, pb4client.MoveBuildVo.Builder.class);
    }

    // Construct using pb4client.MoveBuildVo.newBuilder()
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
      buildingId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      newX_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      newY_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      direction_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_MoveBuildVo_descriptor;
    }

    public pb4client.MoveBuildVo getDefaultInstanceForType() {
      return pb4client.MoveBuildVo.getDefaultInstance();
    }

    public pb4client.MoveBuildVo build() {
      pb4client.MoveBuildVo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.MoveBuildVo buildPartial() {
      pb4client.MoveBuildVo result = new pb4client.MoveBuildVo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.buildingId_ = buildingId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.newX_ = newX_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.newY_ = newY_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
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
      if (other instanceof pb4client.MoveBuildVo) {
        return mergeFrom((pb4client.MoveBuildVo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.MoveBuildVo other) {
      if (other == pb4client.MoveBuildVo.getDefaultInstance()) return this;
      if (other.hasBuildingId()) {
        setBuildingId(other.getBuildingId());
      }
      if (other.hasNewX()) {
        setNewX(other.getNewX());
      }
      if (other.hasNewY()) {
        setNewY(other.getNewY());
      }
      if (other.hasDirection()) {
        setDirection(other.getDirection());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasBuildingId()) {
        return false;
      }
      if (!hasNewX()) {
        return false;
      }
      if (!hasNewY()) {
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
      pb4client.MoveBuildVo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.MoveBuildVo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long buildingId_ ;
    /**
     * <pre>
     * 唯一ID
     * </pre>
     *
     * <code>required int64 buildingId = 1;</code>
     */
    public boolean hasBuildingId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 唯一ID
     * </pre>
     *
     * <code>required int64 buildingId = 1;</code>
     */
    public long getBuildingId() {
      return buildingId_;
    }
    /**
     * <pre>
     * 唯一ID
     * </pre>
     *
     * <code>required int64 buildingId = 1;</code>
     */
    public Builder setBuildingId(long value) {
      bitField0_ |= 0x00000001;
      buildingId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 唯一ID
     * </pre>
     *
     * <code>required int64 buildingId = 1;</code>
     */
    public Builder clearBuildingId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      buildingId_ = 0L;
      onChanged();
      return this;
    }

    private int newX_ ;
    /**
     * <pre>
     * x
     * </pre>
     *
     * <code>required int32 newX = 2;</code>
     */
    public boolean hasNewX() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * x
     * </pre>
     *
     * <code>required int32 newX = 2;</code>
     */
    public int getNewX() {
      return newX_;
    }
    /**
     * <pre>
     * x
     * </pre>
     *
     * <code>required int32 newX = 2;</code>
     */
    public Builder setNewX(int value) {
      bitField0_ |= 0x00000002;
      newX_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * x
     * </pre>
     *
     * <code>required int32 newX = 2;</code>
     */
    public Builder clearNewX() {
      bitField0_ = (bitField0_ & ~0x00000002);
      newX_ = 0;
      onChanged();
      return this;
    }

    private int newY_ ;
    /**
     * <pre>
     * y
     * </pre>
     *
     * <code>required int32 newY = 3;</code>
     */
    public boolean hasNewY() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * y
     * </pre>
     *
     * <code>required int32 newY = 3;</code>
     */
    public int getNewY() {
      return newY_;
    }
    /**
     * <pre>
     * y
     * </pre>
     *
     * <code>required int32 newY = 3;</code>
     */
    public Builder setNewY(int value) {
      bitField0_ |= 0x00000004;
      newY_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * y
     * </pre>
     *
     * <code>required int32 newY = 3;</code>
     */
    public Builder clearNewY() {
      bitField0_ = (bitField0_ & ~0x00000004);
      newY_ = 0;
      onChanged();
      return this;
    }

    private int direction_ ;
    /**
     * <pre>
     * 建筑朝向
     * </pre>
     *
     * <code>required int32 direction = 4;</code>
     */
    public boolean hasDirection() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     * 建筑朝向
     * </pre>
     *
     * <code>required int32 direction = 4;</code>
     */
    public int getDirection() {
      return direction_;
    }
    /**
     * <pre>
     * 建筑朝向
     * </pre>
     *
     * <code>required int32 direction = 4;</code>
     */
    public Builder setDirection(int value) {
      bitField0_ |= 0x00000008;
      direction_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 建筑朝向
     * </pre>
     *
     * <code>required int32 direction = 4;</code>
     */
    public Builder clearDirection() {
      bitField0_ = (bitField0_ & ~0x00000008);
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


    // @@protoc_insertion_point(builder_scope:client2server.MoveBuildVo)
  }

  // @@protoc_insertion_point(class_scope:client2server.MoveBuildVo)
  private static final pb4client.MoveBuildVo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.MoveBuildVo();
  }

  public static pb4client.MoveBuildVo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<MoveBuildVo>
      PARSER = new com.google.protobuf.AbstractParser<MoveBuildVo>() {
    public MoveBuildVo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new MoveBuildVo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MoveBuildVo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MoveBuildVo> getParserForType() {
    return PARSER;
  }

  public pb4client.MoveBuildVo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

