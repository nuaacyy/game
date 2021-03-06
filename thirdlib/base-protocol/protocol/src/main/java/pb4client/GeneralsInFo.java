// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.GeneralsInFo}
 */
public  final class GeneralsInFo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.GeneralsInFo)
    GeneralsInFoOrBuilder {
  // Use GeneralsInFo.newBuilder() to construct.
  private GeneralsInFo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GeneralsInFo() {
    heroId_ = 0L;
    heroProtoId_ = 0;
    lv_ = 0;
    city_ = 0;
    advancedNum_ = 0;
    wakes_ = 0;
    troops_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GeneralsInFo(
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
            heroId_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            heroProtoId_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            lv_ = input.readInt32();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            city_ = input.readInt32();
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            advancedNum_ = input.readInt32();
            break;
          }
          case 48: {
            bitField0_ |= 0x00000020;
            wakes_ = input.readInt32();
            break;
          }
          case 56: {
            bitField0_ |= 0x00000040;
            troops_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_GeneralsInFo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_GeneralsInFo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.GeneralsInFo.class, pb4client.GeneralsInFo.Builder.class);
  }

  private int bitField0_;
  public static final int HEROID_FIELD_NUMBER = 1;
  private long heroId_;
  /**
   * <code>required int64 heroId = 1;</code>
   */
  public boolean hasHeroId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required int64 heroId = 1;</code>
   */
  public long getHeroId() {
    return heroId_;
  }

  public static final int HEROPROTOID_FIELD_NUMBER = 2;
  private int heroProtoId_;
  /**
   * <code>required int32 heroProtoId = 2;</code>
   */
  public boolean hasHeroProtoId() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>required int32 heroProtoId = 2;</code>
   */
  public int getHeroProtoId() {
    return heroProtoId_;
  }

  public static final int LV_FIELD_NUMBER = 3;
  private int lv_;
  /**
   * <code>required int32 lv = 3;</code>
   */
  public boolean hasLv() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <code>required int32 lv = 3;</code>
   */
  public int getLv() {
    return lv_;
  }

  public static final int CITY_FIELD_NUMBER = 4;
  private int city_;
  /**
   * <code>required int32 city = 4;</code>
   */
  public boolean hasCity() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <code>required int32 city = 4;</code>
   */
  public int getCity() {
    return city_;
  }

  public static final int ADVANCEDNUM_FIELD_NUMBER = 5;
  private int advancedNum_;
  /**
   * <code>required int32 advancedNum = 5;</code>
   */
  public boolean hasAdvancedNum() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <code>required int32 advancedNum = 5;</code>
   */
  public int getAdvancedNum() {
    return advancedNum_;
  }

  public static final int WAKES_FIELD_NUMBER = 6;
  private int wakes_;
  /**
   * <code>required int32 wakes = 6;</code>
   */
  public boolean hasWakes() {
    return ((bitField0_ & 0x00000020) == 0x00000020);
  }
  /**
   * <code>required int32 wakes = 6;</code>
   */
  public int getWakes() {
    return wakes_;
  }

  public static final int TROOPS_FIELD_NUMBER = 7;
  private int troops_;
  /**
   * <code>required int32 troops = 7;</code>
   */
  public boolean hasTroops() {
    return ((bitField0_ & 0x00000040) == 0x00000040);
  }
  /**
   * <code>required int32 troops = 7;</code>
   */
  public int getTroops() {
    return troops_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasHeroId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasHeroProtoId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasLv()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasCity()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasAdvancedNum()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasWakes()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasTroops()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, heroId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, heroProtoId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, lv_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, city_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt32(5, advancedNum_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      output.writeInt32(6, wakes_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      output.writeInt32(7, troops_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, heroId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, heroProtoId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, lv_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, city_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, advancedNum_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, wakes_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, troops_);
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
    if (!(obj instanceof pb4client.GeneralsInFo)) {
      return super.equals(obj);
    }
    pb4client.GeneralsInFo other = (pb4client.GeneralsInFo) obj;

    boolean result = true;
    result = result && (hasHeroId() == other.hasHeroId());
    if (hasHeroId()) {
      result = result && (getHeroId()
          == other.getHeroId());
    }
    result = result && (hasHeroProtoId() == other.hasHeroProtoId());
    if (hasHeroProtoId()) {
      result = result && (getHeroProtoId()
          == other.getHeroProtoId());
    }
    result = result && (hasLv() == other.hasLv());
    if (hasLv()) {
      result = result && (getLv()
          == other.getLv());
    }
    result = result && (hasCity() == other.hasCity());
    if (hasCity()) {
      result = result && (getCity()
          == other.getCity());
    }
    result = result && (hasAdvancedNum() == other.hasAdvancedNum());
    if (hasAdvancedNum()) {
      result = result && (getAdvancedNum()
          == other.getAdvancedNum());
    }
    result = result && (hasWakes() == other.hasWakes());
    if (hasWakes()) {
      result = result && (getWakes()
          == other.getWakes());
    }
    result = result && (hasTroops() == other.hasTroops());
    if (hasTroops()) {
      result = result && (getTroops()
          == other.getTroops());
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
    if (hasHeroId()) {
      hash = (37 * hash) + HEROID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getHeroId());
    }
    if (hasHeroProtoId()) {
      hash = (37 * hash) + HEROPROTOID_FIELD_NUMBER;
      hash = (53 * hash) + getHeroProtoId();
    }
    if (hasLv()) {
      hash = (37 * hash) + LV_FIELD_NUMBER;
      hash = (53 * hash) + getLv();
    }
    if (hasCity()) {
      hash = (37 * hash) + CITY_FIELD_NUMBER;
      hash = (53 * hash) + getCity();
    }
    if (hasAdvancedNum()) {
      hash = (37 * hash) + ADVANCEDNUM_FIELD_NUMBER;
      hash = (53 * hash) + getAdvancedNum();
    }
    if (hasWakes()) {
      hash = (37 * hash) + WAKES_FIELD_NUMBER;
      hash = (53 * hash) + getWakes();
    }
    if (hasTroops()) {
      hash = (37 * hash) + TROOPS_FIELD_NUMBER;
      hash = (53 * hash) + getTroops();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.GeneralsInFo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GeneralsInFo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GeneralsInFo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GeneralsInFo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GeneralsInFo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GeneralsInFo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GeneralsInFo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.GeneralsInFo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.GeneralsInFo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.GeneralsInFo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.GeneralsInFo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.GeneralsInFo parseFrom(
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
  public static Builder newBuilder(pb4client.GeneralsInFo prototype) {
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
   * Protobuf type {@code client2server.GeneralsInFo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.GeneralsInFo)
      pb4client.GeneralsInFoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_GeneralsInFo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_GeneralsInFo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.GeneralsInFo.class, pb4client.GeneralsInFo.Builder.class);
    }

    // Construct using pb4client.GeneralsInFo.newBuilder()
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
      heroId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      heroProtoId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      lv_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      city_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      advancedNum_ = 0;
      bitField0_ = (bitField0_ & ~0x00000010);
      wakes_ = 0;
      bitField0_ = (bitField0_ & ~0x00000020);
      troops_ = 0;
      bitField0_ = (bitField0_ & ~0x00000040);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_GeneralsInFo_descriptor;
    }

    public pb4client.GeneralsInFo getDefaultInstanceForType() {
      return pb4client.GeneralsInFo.getDefaultInstance();
    }

    public pb4client.GeneralsInFo build() {
      pb4client.GeneralsInFo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.GeneralsInFo buildPartial() {
      pb4client.GeneralsInFo result = new pb4client.GeneralsInFo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.heroId_ = heroId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.heroProtoId_ = heroProtoId_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.lv_ = lv_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.city_ = city_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.advancedNum_ = advancedNum_;
      if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
        to_bitField0_ |= 0x00000020;
      }
      result.wakes_ = wakes_;
      if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
        to_bitField0_ |= 0x00000040;
      }
      result.troops_ = troops_;
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
      if (other instanceof pb4client.GeneralsInFo) {
        return mergeFrom((pb4client.GeneralsInFo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.GeneralsInFo other) {
      if (other == pb4client.GeneralsInFo.getDefaultInstance()) return this;
      if (other.hasHeroId()) {
        setHeroId(other.getHeroId());
      }
      if (other.hasHeroProtoId()) {
        setHeroProtoId(other.getHeroProtoId());
      }
      if (other.hasLv()) {
        setLv(other.getLv());
      }
      if (other.hasCity()) {
        setCity(other.getCity());
      }
      if (other.hasAdvancedNum()) {
        setAdvancedNum(other.getAdvancedNum());
      }
      if (other.hasWakes()) {
        setWakes(other.getWakes());
      }
      if (other.hasTroops()) {
        setTroops(other.getTroops());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasHeroId()) {
        return false;
      }
      if (!hasHeroProtoId()) {
        return false;
      }
      if (!hasLv()) {
        return false;
      }
      if (!hasCity()) {
        return false;
      }
      if (!hasAdvancedNum()) {
        return false;
      }
      if (!hasWakes()) {
        return false;
      }
      if (!hasTroops()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.GeneralsInFo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.GeneralsInFo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long heroId_ ;
    /**
     * <code>required int64 heroId = 1;</code>
     */
    public boolean hasHeroId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int64 heroId = 1;</code>
     */
    public long getHeroId() {
      return heroId_;
    }
    /**
     * <code>required int64 heroId = 1;</code>
     */
    public Builder setHeroId(long value) {
      bitField0_ |= 0x00000001;
      heroId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int64 heroId = 1;</code>
     */
    public Builder clearHeroId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      heroId_ = 0L;
      onChanged();
      return this;
    }

    private int heroProtoId_ ;
    /**
     * <code>required int32 heroProtoId = 2;</code>
     */
    public boolean hasHeroProtoId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 heroProtoId = 2;</code>
     */
    public int getHeroProtoId() {
      return heroProtoId_;
    }
    /**
     * <code>required int32 heroProtoId = 2;</code>
     */
    public Builder setHeroProtoId(int value) {
      bitField0_ |= 0x00000002;
      heroProtoId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 heroProtoId = 2;</code>
     */
    public Builder clearHeroProtoId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      heroProtoId_ = 0;
      onChanged();
      return this;
    }

    private int lv_ ;
    /**
     * <code>required int32 lv = 3;</code>
     */
    public boolean hasLv() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required int32 lv = 3;</code>
     */
    public int getLv() {
      return lv_;
    }
    /**
     * <code>required int32 lv = 3;</code>
     */
    public Builder setLv(int value) {
      bitField0_ |= 0x00000004;
      lv_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 lv = 3;</code>
     */
    public Builder clearLv() {
      bitField0_ = (bitField0_ & ~0x00000004);
      lv_ = 0;
      onChanged();
      return this;
    }

    private int city_ ;
    /**
     * <code>required int32 city = 4;</code>
     */
    public boolean hasCity() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>required int32 city = 4;</code>
     */
    public int getCity() {
      return city_;
    }
    /**
     * <code>required int32 city = 4;</code>
     */
    public Builder setCity(int value) {
      bitField0_ |= 0x00000008;
      city_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 city = 4;</code>
     */
    public Builder clearCity() {
      bitField0_ = (bitField0_ & ~0x00000008);
      city_ = 0;
      onChanged();
      return this;
    }

    private int advancedNum_ ;
    /**
     * <code>required int32 advancedNum = 5;</code>
     */
    public boolean hasAdvancedNum() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>required int32 advancedNum = 5;</code>
     */
    public int getAdvancedNum() {
      return advancedNum_;
    }
    /**
     * <code>required int32 advancedNum = 5;</code>
     */
    public Builder setAdvancedNum(int value) {
      bitField0_ |= 0x00000010;
      advancedNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 advancedNum = 5;</code>
     */
    public Builder clearAdvancedNum() {
      bitField0_ = (bitField0_ & ~0x00000010);
      advancedNum_ = 0;
      onChanged();
      return this;
    }

    private int wakes_ ;
    /**
     * <code>required int32 wakes = 6;</code>
     */
    public boolean hasWakes() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <code>required int32 wakes = 6;</code>
     */
    public int getWakes() {
      return wakes_;
    }
    /**
     * <code>required int32 wakes = 6;</code>
     */
    public Builder setWakes(int value) {
      bitField0_ |= 0x00000020;
      wakes_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 wakes = 6;</code>
     */
    public Builder clearWakes() {
      bitField0_ = (bitField0_ & ~0x00000020);
      wakes_ = 0;
      onChanged();
      return this;
    }

    private int troops_ ;
    /**
     * <code>required int32 troops = 7;</code>
     */
    public boolean hasTroops() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    /**
     * <code>required int32 troops = 7;</code>
     */
    public int getTroops() {
      return troops_;
    }
    /**
     * <code>required int32 troops = 7;</code>
     */
    public Builder setTroops(int value) {
      bitField0_ |= 0x00000040;
      troops_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 troops = 7;</code>
     */
    public Builder clearTroops() {
      bitField0_ = (bitField0_ & ~0x00000040);
      troops_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.GeneralsInFo)
  }

  // @@protoc_insertion_point(class_scope:client2server.GeneralsInFo)
  private static final pb4client.GeneralsInFo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.GeneralsInFo();
  }

  public static pb4client.GeneralsInFo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<GeneralsInFo>
      PARSER = new com.google.protobuf.AbstractParser<GeneralsInFo>() {
    public GeneralsInFo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new GeneralsInFo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GeneralsInFo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GeneralsInFo> getParserForType() {
    return PARSER;
  }

  public pb4client.GeneralsInFo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

