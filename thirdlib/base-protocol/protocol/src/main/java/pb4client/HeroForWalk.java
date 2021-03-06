// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.HeroForWalk}
 */
public  final class HeroForWalk extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.HeroForWalk)
    HeroForWalkOrBuilder {
  // Use HeroForWalk.newBuilder() to construct.
  private HeroForWalk(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private HeroForWalk() {
    protoId_ = 0;
    lv_ = 0;
    starLv_ = 0;
    awake_ = 0;
    isLord_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private HeroForWalk(
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
            lv_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            starLv_ = input.readInt32();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            awake_ = input.readInt32();
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            isLord_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_HeroForWalk_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_HeroForWalk_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.HeroForWalk.class, pb4client.HeroForWalk.Builder.class);
  }

  private int bitField0_;
  public static final int PROTOID_FIELD_NUMBER = 1;
  private int protoId_;
  /**
   * <pre>
   *配置ID
   * </pre>
   *
   * <code>required int32 protoId = 1;</code>
   */
  public boolean hasProtoId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *配置ID
   * </pre>
   *
   * <code>required int32 protoId = 1;</code>
   */
  public int getProtoId() {
    return protoId_;
  }

  public static final int LV_FIELD_NUMBER = 2;
  private int lv_;
  /**
   * <pre>
   *等级
   * </pre>
   *
   * <code>required int32 lv = 2;</code>
   */
  public boolean hasLv() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *等级
   * </pre>
   *
   * <code>required int32 lv = 2;</code>
   */
  public int getLv() {
    return lv_;
  }

  public static final int STARLV_FIELD_NUMBER = 3;
  private int starLv_;
  /**
   * <pre>
   *星级
   * </pre>
   *
   * <code>required int32 starLv = 3;</code>
   */
  public boolean hasStarLv() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   *星级
   * </pre>
   *
   * <code>required int32 starLv = 3;</code>
   */
  public int getStarLv() {
    return starLv_;
  }

  public static final int AWAKE_FIELD_NUMBER = 4;
  private int awake_;
  /**
   * <pre>
   *军阶
   * </pre>
   *
   * <code>required int32 awake = 4;</code>
   */
  public boolean hasAwake() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   *军阶
   * </pre>
   *
   * <code>required int32 awake = 4;</code>
   */
  public int getAwake() {
    return awake_;
  }

  public static final int ISLORD_FIELD_NUMBER = 5;
  private int isLord_;
  /**
   * <pre>
   *是否是领主
   * </pre>
   *
   * <code>optional int32 isLord = 5;</code>
   */
  public boolean hasIsLord() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <pre>
   *是否是领主
   * </pre>
   *
   * <code>optional int32 isLord = 5;</code>
   */
  public int getIsLord() {
    return isLord_;
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
    if (!hasLv()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasStarLv()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasAwake()) {
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
      output.writeInt32(2, lv_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, starLv_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, awake_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt32(5, isLord_);
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
        .computeInt32Size(2, lv_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, starLv_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, awake_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, isLord_);
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
    if (!(obj instanceof pb4client.HeroForWalk)) {
      return super.equals(obj);
    }
    pb4client.HeroForWalk other = (pb4client.HeroForWalk) obj;

    boolean result = true;
    result = result && (hasProtoId() == other.hasProtoId());
    if (hasProtoId()) {
      result = result && (getProtoId()
          == other.getProtoId());
    }
    result = result && (hasLv() == other.hasLv());
    if (hasLv()) {
      result = result && (getLv()
          == other.getLv());
    }
    result = result && (hasStarLv() == other.hasStarLv());
    if (hasStarLv()) {
      result = result && (getStarLv()
          == other.getStarLv());
    }
    result = result && (hasAwake() == other.hasAwake());
    if (hasAwake()) {
      result = result && (getAwake()
          == other.getAwake());
    }
    result = result && (hasIsLord() == other.hasIsLord());
    if (hasIsLord()) {
      result = result && (getIsLord()
          == other.getIsLord());
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
    if (hasLv()) {
      hash = (37 * hash) + LV_FIELD_NUMBER;
      hash = (53 * hash) + getLv();
    }
    if (hasStarLv()) {
      hash = (37 * hash) + STARLV_FIELD_NUMBER;
      hash = (53 * hash) + getStarLv();
    }
    if (hasAwake()) {
      hash = (37 * hash) + AWAKE_FIELD_NUMBER;
      hash = (53 * hash) + getAwake();
    }
    if (hasIsLord()) {
      hash = (37 * hash) + ISLORD_FIELD_NUMBER;
      hash = (53 * hash) + getIsLord();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.HeroForWalk parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.HeroForWalk parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.HeroForWalk parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.HeroForWalk parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.HeroForWalk parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.HeroForWalk parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.HeroForWalk parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.HeroForWalk parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.HeroForWalk parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.HeroForWalk parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.HeroForWalk parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.HeroForWalk parseFrom(
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
  public static Builder newBuilder(pb4client.HeroForWalk prototype) {
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
   * Protobuf type {@code client2server.HeroForWalk}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.HeroForWalk)
      pb4client.HeroForWalkOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_HeroForWalk_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_HeroForWalk_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.HeroForWalk.class, pb4client.HeroForWalk.Builder.class);
    }

    // Construct using pb4client.HeroForWalk.newBuilder()
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
      lv_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      starLv_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      awake_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      isLord_ = 0;
      bitField0_ = (bitField0_ & ~0x00000010);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_HeroForWalk_descriptor;
    }

    public pb4client.HeroForWalk getDefaultInstanceForType() {
      return pb4client.HeroForWalk.getDefaultInstance();
    }

    public pb4client.HeroForWalk build() {
      pb4client.HeroForWalk result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.HeroForWalk buildPartial() {
      pb4client.HeroForWalk result = new pb4client.HeroForWalk(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.protoId_ = protoId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.lv_ = lv_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.starLv_ = starLv_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.awake_ = awake_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.isLord_ = isLord_;
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
      if (other instanceof pb4client.HeroForWalk) {
        return mergeFrom((pb4client.HeroForWalk)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.HeroForWalk other) {
      if (other == pb4client.HeroForWalk.getDefaultInstance()) return this;
      if (other.hasProtoId()) {
        setProtoId(other.getProtoId());
      }
      if (other.hasLv()) {
        setLv(other.getLv());
      }
      if (other.hasStarLv()) {
        setStarLv(other.getStarLv());
      }
      if (other.hasAwake()) {
        setAwake(other.getAwake());
      }
      if (other.hasIsLord()) {
        setIsLord(other.getIsLord());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasProtoId()) {
        return false;
      }
      if (!hasLv()) {
        return false;
      }
      if (!hasStarLv()) {
        return false;
      }
      if (!hasAwake()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.HeroForWalk parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.HeroForWalk) e.getUnfinishedMessage();
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
     *配置ID
     * </pre>
     *
     * <code>required int32 protoId = 1;</code>
     */
    public boolean hasProtoId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *配置ID
     * </pre>
     *
     * <code>required int32 protoId = 1;</code>
     */
    public int getProtoId() {
      return protoId_;
    }
    /**
     * <pre>
     *配置ID
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
     *配置ID
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

    private int lv_ ;
    /**
     * <pre>
     *等级
     * </pre>
     *
     * <code>required int32 lv = 2;</code>
     */
    public boolean hasLv() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *等级
     * </pre>
     *
     * <code>required int32 lv = 2;</code>
     */
    public int getLv() {
      return lv_;
    }
    /**
     * <pre>
     *等级
     * </pre>
     *
     * <code>required int32 lv = 2;</code>
     */
    public Builder setLv(int value) {
      bitField0_ |= 0x00000002;
      lv_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *等级
     * </pre>
     *
     * <code>required int32 lv = 2;</code>
     */
    public Builder clearLv() {
      bitField0_ = (bitField0_ & ~0x00000002);
      lv_ = 0;
      onChanged();
      return this;
    }

    private int starLv_ ;
    /**
     * <pre>
     *星级
     * </pre>
     *
     * <code>required int32 starLv = 3;</code>
     */
    public boolean hasStarLv() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     *星级
     * </pre>
     *
     * <code>required int32 starLv = 3;</code>
     */
    public int getStarLv() {
      return starLv_;
    }
    /**
     * <pre>
     *星级
     * </pre>
     *
     * <code>required int32 starLv = 3;</code>
     */
    public Builder setStarLv(int value) {
      bitField0_ |= 0x00000004;
      starLv_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *星级
     * </pre>
     *
     * <code>required int32 starLv = 3;</code>
     */
    public Builder clearStarLv() {
      bitField0_ = (bitField0_ & ~0x00000004);
      starLv_ = 0;
      onChanged();
      return this;
    }

    private int awake_ ;
    /**
     * <pre>
     *军阶
     * </pre>
     *
     * <code>required int32 awake = 4;</code>
     */
    public boolean hasAwake() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     *军阶
     * </pre>
     *
     * <code>required int32 awake = 4;</code>
     */
    public int getAwake() {
      return awake_;
    }
    /**
     * <pre>
     *军阶
     * </pre>
     *
     * <code>required int32 awake = 4;</code>
     */
    public Builder setAwake(int value) {
      bitField0_ |= 0x00000008;
      awake_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *军阶
     * </pre>
     *
     * <code>required int32 awake = 4;</code>
     */
    public Builder clearAwake() {
      bitField0_ = (bitField0_ & ~0x00000008);
      awake_ = 0;
      onChanged();
      return this;
    }

    private int isLord_ ;
    /**
     * <pre>
     *是否是领主
     * </pre>
     *
     * <code>optional int32 isLord = 5;</code>
     */
    public boolean hasIsLord() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     *是否是领主
     * </pre>
     *
     * <code>optional int32 isLord = 5;</code>
     */
    public int getIsLord() {
      return isLord_;
    }
    /**
     * <pre>
     *是否是领主
     * </pre>
     *
     * <code>optional int32 isLord = 5;</code>
     */
    public Builder setIsLord(int value) {
      bitField0_ |= 0x00000010;
      isLord_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *是否是领主
     * </pre>
     *
     * <code>optional int32 isLord = 5;</code>
     */
    public Builder clearIsLord() {
      bitField0_ = (bitField0_ & ~0x00000010);
      isLord_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.HeroForWalk)
  }

  // @@protoc_insertion_point(class_scope:client2server.HeroForWalk)
  private static final pb4client.HeroForWalk DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.HeroForWalk();
  }

  public static pb4client.HeroForWalk getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<HeroForWalk>
      PARSER = new com.google.protobuf.AbstractParser<HeroForWalk>() {
    public HeroForWalk parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new HeroForWalk(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<HeroForWalk> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<HeroForWalk> getParserForType() {
    return PARSER;
  }

  public pb4client.HeroForWalk getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

