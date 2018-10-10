// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 114
 * 客户端 -&gt; 服务器
 * 战斗模拟器
 * </pre>
 *
 * Protobuf type {@code client2server.TestFight}
 */
public  final class TestFight extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.TestFight)
    TestFightOrBuilder {
  // Use TestFight.newBuilder() to construct.
  private TestFight(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TestFight() {
    fightType_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TestFight(
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
            fightType_ = input.readInt32();
            break;
          }
          case 18: {
            pb4client.TestFightData.Builder subBuilder = null;
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
              subBuilder = atkTestFightData_.toBuilder();
            }
            atkTestFightData_ = input.readMessage(pb4client.TestFightData.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(atkTestFightData_);
              atkTestFightData_ = subBuilder.buildPartial();
            }
            bitField0_ |= 0x00000002;
            break;
          }
          case 26: {
            pb4client.TestFightData.Builder subBuilder = null;
            if (((bitField0_ & 0x00000004) == 0x00000004)) {
              subBuilder = defTestFightData_.toBuilder();
            }
            defTestFightData_ = input.readMessage(pb4client.TestFightData.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(defTestFightData_);
              defTestFightData_ = subBuilder.buildPartial();
            }
            bitField0_ |= 0x00000004;
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
    return pb4client.War2GamePkt.internal_static_client2server_TestFight_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_TestFight_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.TestFight.class, pb4client.TestFight.Builder.class);
  }

  private int bitField0_;
  public static final int FIGHTTYPE_FIELD_NUMBER = 1;
  private int fightType_;
  /**
   * <code>required int32 fightType = 1;</code>
   */
  public boolean hasFightType() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required int32 fightType = 1;</code>
   */
  public int getFightType() {
    return fightType_;
  }

  public static final int ATKTESTFIGHTDATA_FIELD_NUMBER = 2;
  private pb4client.TestFightData atkTestFightData_;
  /**
   * <code>required .client2server.TestFightData atkTestFightData = 2;</code>
   */
  public boolean hasAtkTestFightData() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>required .client2server.TestFightData atkTestFightData = 2;</code>
   */
  public pb4client.TestFightData getAtkTestFightData() {
    return atkTestFightData_ == null ? pb4client.TestFightData.getDefaultInstance() : atkTestFightData_;
  }
  /**
   * <code>required .client2server.TestFightData atkTestFightData = 2;</code>
   */
  public pb4client.TestFightDataOrBuilder getAtkTestFightDataOrBuilder() {
    return atkTestFightData_ == null ? pb4client.TestFightData.getDefaultInstance() : atkTestFightData_;
  }

  public static final int DEFTESTFIGHTDATA_FIELD_NUMBER = 3;
  private pb4client.TestFightData defTestFightData_;
  /**
   * <code>required .client2server.TestFightData defTestFightData = 3;</code>
   */
  public boolean hasDefTestFightData() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <code>required .client2server.TestFightData defTestFightData = 3;</code>
   */
  public pb4client.TestFightData getDefTestFightData() {
    return defTestFightData_ == null ? pb4client.TestFightData.getDefaultInstance() : defTestFightData_;
  }
  /**
   * <code>required .client2server.TestFightData defTestFightData = 3;</code>
   */
  public pb4client.TestFightDataOrBuilder getDefTestFightDataOrBuilder() {
    return defTestFightData_ == null ? pb4client.TestFightData.getDefaultInstance() : defTestFightData_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasFightType()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasAtkTestFightData()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasDefTestFightData()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!getAtkTestFightData().isInitialized()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!getDefTestFightData().isInitialized()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, fightType_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeMessage(2, getAtkTestFightData());
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeMessage(3, getDefTestFightData());
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, fightType_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getAtkTestFightData());
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getDefTestFightData());
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
    if (!(obj instanceof pb4client.TestFight)) {
      return super.equals(obj);
    }
    pb4client.TestFight other = (pb4client.TestFight) obj;

    boolean result = true;
    result = result && (hasFightType() == other.hasFightType());
    if (hasFightType()) {
      result = result && (getFightType()
          == other.getFightType());
    }
    result = result && (hasAtkTestFightData() == other.hasAtkTestFightData());
    if (hasAtkTestFightData()) {
      result = result && getAtkTestFightData()
          .equals(other.getAtkTestFightData());
    }
    result = result && (hasDefTestFightData() == other.hasDefTestFightData());
    if (hasDefTestFightData()) {
      result = result && getDefTestFightData()
          .equals(other.getDefTestFightData());
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
    if (hasFightType()) {
      hash = (37 * hash) + FIGHTTYPE_FIELD_NUMBER;
      hash = (53 * hash) + getFightType();
    }
    if (hasAtkTestFightData()) {
      hash = (37 * hash) + ATKTESTFIGHTDATA_FIELD_NUMBER;
      hash = (53 * hash) + getAtkTestFightData().hashCode();
    }
    if (hasDefTestFightData()) {
      hash = (37 * hash) + DEFTESTFIGHTDATA_FIELD_NUMBER;
      hash = (53 * hash) + getDefTestFightData().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.TestFight parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.TestFight parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.TestFight parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.TestFight parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.TestFight parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.TestFight parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.TestFight parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.TestFight parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.TestFight parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.TestFight parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.TestFight parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.TestFight parseFrom(
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
  public static Builder newBuilder(pb4client.TestFight prototype) {
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
   * msgType = 114
   * 客户端 -&gt; 服务器
   * 战斗模拟器
   * </pre>
   *
   * Protobuf type {@code client2server.TestFight}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.TestFight)
      pb4client.TestFightOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_TestFight_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_TestFight_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.TestFight.class, pb4client.TestFight.Builder.class);
    }

    // Construct using pb4client.TestFight.newBuilder()
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
        getAtkTestFightDataFieldBuilder();
        getDefTestFightDataFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      fightType_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (atkTestFightDataBuilder_ == null) {
        atkTestFightData_ = null;
      } else {
        atkTestFightDataBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      if (defTestFightDataBuilder_ == null) {
        defTestFightData_ = null;
      } else {
        defTestFightDataBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_TestFight_descriptor;
    }

    public pb4client.TestFight getDefaultInstanceForType() {
      return pb4client.TestFight.getDefaultInstance();
    }

    public pb4client.TestFight build() {
      pb4client.TestFight result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.TestFight buildPartial() {
      pb4client.TestFight result = new pb4client.TestFight(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.fightType_ = fightType_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      if (atkTestFightDataBuilder_ == null) {
        result.atkTestFightData_ = atkTestFightData_;
      } else {
        result.atkTestFightData_ = atkTestFightDataBuilder_.build();
      }
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      if (defTestFightDataBuilder_ == null) {
        result.defTestFightData_ = defTestFightData_;
      } else {
        result.defTestFightData_ = defTestFightDataBuilder_.build();
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
      if (other instanceof pb4client.TestFight) {
        return mergeFrom((pb4client.TestFight)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.TestFight other) {
      if (other == pb4client.TestFight.getDefaultInstance()) return this;
      if (other.hasFightType()) {
        setFightType(other.getFightType());
      }
      if (other.hasAtkTestFightData()) {
        mergeAtkTestFightData(other.getAtkTestFightData());
      }
      if (other.hasDefTestFightData()) {
        mergeDefTestFightData(other.getDefTestFightData());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasFightType()) {
        return false;
      }
      if (!hasAtkTestFightData()) {
        return false;
      }
      if (!hasDefTestFightData()) {
        return false;
      }
      if (!getAtkTestFightData().isInitialized()) {
        return false;
      }
      if (!getDefTestFightData().isInitialized()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.TestFight parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.TestFight) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int fightType_ ;
    /**
     * <code>required int32 fightType = 1;</code>
     */
    public boolean hasFightType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 fightType = 1;</code>
     */
    public int getFightType() {
      return fightType_;
    }
    /**
     * <code>required int32 fightType = 1;</code>
     */
    public Builder setFightType(int value) {
      bitField0_ |= 0x00000001;
      fightType_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 fightType = 1;</code>
     */
    public Builder clearFightType() {
      bitField0_ = (bitField0_ & ~0x00000001);
      fightType_ = 0;
      onChanged();
      return this;
    }

    private pb4client.TestFightData atkTestFightData_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.TestFightData, pb4client.TestFightData.Builder, pb4client.TestFightDataOrBuilder> atkTestFightDataBuilder_;
    /**
     * <code>required .client2server.TestFightData atkTestFightData = 2;</code>
     */
    public boolean hasAtkTestFightData() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required .client2server.TestFightData atkTestFightData = 2;</code>
     */
    public pb4client.TestFightData getAtkTestFightData() {
      if (atkTestFightDataBuilder_ == null) {
        return atkTestFightData_ == null ? pb4client.TestFightData.getDefaultInstance() : atkTestFightData_;
      } else {
        return atkTestFightDataBuilder_.getMessage();
      }
    }
    /**
     * <code>required .client2server.TestFightData atkTestFightData = 2;</code>
     */
    public Builder setAtkTestFightData(pb4client.TestFightData value) {
      if (atkTestFightDataBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        atkTestFightData_ = value;
        onChanged();
      } else {
        atkTestFightDataBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <code>required .client2server.TestFightData atkTestFightData = 2;</code>
     */
    public Builder setAtkTestFightData(
        pb4client.TestFightData.Builder builderForValue) {
      if (atkTestFightDataBuilder_ == null) {
        atkTestFightData_ = builderForValue.build();
        onChanged();
      } else {
        atkTestFightDataBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <code>required .client2server.TestFightData atkTestFightData = 2;</code>
     */
    public Builder mergeAtkTestFightData(pb4client.TestFightData value) {
      if (atkTestFightDataBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002) &&
            atkTestFightData_ != null &&
            atkTestFightData_ != pb4client.TestFightData.getDefaultInstance()) {
          atkTestFightData_ =
            pb4client.TestFightData.newBuilder(atkTestFightData_).mergeFrom(value).buildPartial();
        } else {
          atkTestFightData_ = value;
        }
        onChanged();
      } else {
        atkTestFightDataBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <code>required .client2server.TestFightData atkTestFightData = 2;</code>
     */
    public Builder clearAtkTestFightData() {
      if (atkTestFightDataBuilder_ == null) {
        atkTestFightData_ = null;
        onChanged();
      } else {
        atkTestFightDataBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }
    /**
     * <code>required .client2server.TestFightData atkTestFightData = 2;</code>
     */
    public pb4client.TestFightData.Builder getAtkTestFightDataBuilder() {
      bitField0_ |= 0x00000002;
      onChanged();
      return getAtkTestFightDataFieldBuilder().getBuilder();
    }
    /**
     * <code>required .client2server.TestFightData atkTestFightData = 2;</code>
     */
    public pb4client.TestFightDataOrBuilder getAtkTestFightDataOrBuilder() {
      if (atkTestFightDataBuilder_ != null) {
        return atkTestFightDataBuilder_.getMessageOrBuilder();
      } else {
        return atkTestFightData_ == null ?
            pb4client.TestFightData.getDefaultInstance() : atkTestFightData_;
      }
    }
    /**
     * <code>required .client2server.TestFightData atkTestFightData = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.TestFightData, pb4client.TestFightData.Builder, pb4client.TestFightDataOrBuilder> 
        getAtkTestFightDataFieldBuilder() {
      if (atkTestFightDataBuilder_ == null) {
        atkTestFightDataBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4client.TestFightData, pb4client.TestFightData.Builder, pb4client.TestFightDataOrBuilder>(
                getAtkTestFightData(),
                getParentForChildren(),
                isClean());
        atkTestFightData_ = null;
      }
      return atkTestFightDataBuilder_;
    }

    private pb4client.TestFightData defTestFightData_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.TestFightData, pb4client.TestFightData.Builder, pb4client.TestFightDataOrBuilder> defTestFightDataBuilder_;
    /**
     * <code>required .client2server.TestFightData defTestFightData = 3;</code>
     */
    public boolean hasDefTestFightData() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required .client2server.TestFightData defTestFightData = 3;</code>
     */
    public pb4client.TestFightData getDefTestFightData() {
      if (defTestFightDataBuilder_ == null) {
        return defTestFightData_ == null ? pb4client.TestFightData.getDefaultInstance() : defTestFightData_;
      } else {
        return defTestFightDataBuilder_.getMessage();
      }
    }
    /**
     * <code>required .client2server.TestFightData defTestFightData = 3;</code>
     */
    public Builder setDefTestFightData(pb4client.TestFightData value) {
      if (defTestFightDataBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        defTestFightData_ = value;
        onChanged();
      } else {
        defTestFightDataBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000004;
      return this;
    }
    /**
     * <code>required .client2server.TestFightData defTestFightData = 3;</code>
     */
    public Builder setDefTestFightData(
        pb4client.TestFightData.Builder builderForValue) {
      if (defTestFightDataBuilder_ == null) {
        defTestFightData_ = builderForValue.build();
        onChanged();
      } else {
        defTestFightDataBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000004;
      return this;
    }
    /**
     * <code>required .client2server.TestFightData defTestFightData = 3;</code>
     */
    public Builder mergeDefTestFightData(pb4client.TestFightData value) {
      if (defTestFightDataBuilder_ == null) {
        if (((bitField0_ & 0x00000004) == 0x00000004) &&
            defTestFightData_ != null &&
            defTestFightData_ != pb4client.TestFightData.getDefaultInstance()) {
          defTestFightData_ =
            pb4client.TestFightData.newBuilder(defTestFightData_).mergeFrom(value).buildPartial();
        } else {
          defTestFightData_ = value;
        }
        onChanged();
      } else {
        defTestFightDataBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000004;
      return this;
    }
    /**
     * <code>required .client2server.TestFightData defTestFightData = 3;</code>
     */
    public Builder clearDefTestFightData() {
      if (defTestFightDataBuilder_ == null) {
        defTestFightData_ = null;
        onChanged();
      } else {
        defTestFightDataBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }
    /**
     * <code>required .client2server.TestFightData defTestFightData = 3;</code>
     */
    public pb4client.TestFightData.Builder getDefTestFightDataBuilder() {
      bitField0_ |= 0x00000004;
      onChanged();
      return getDefTestFightDataFieldBuilder().getBuilder();
    }
    /**
     * <code>required .client2server.TestFightData defTestFightData = 3;</code>
     */
    public pb4client.TestFightDataOrBuilder getDefTestFightDataOrBuilder() {
      if (defTestFightDataBuilder_ != null) {
        return defTestFightDataBuilder_.getMessageOrBuilder();
      } else {
        return defTestFightData_ == null ?
            pb4client.TestFightData.getDefaultInstance() : defTestFightData_;
      }
    }
    /**
     * <code>required .client2server.TestFightData defTestFightData = 3;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.TestFightData, pb4client.TestFightData.Builder, pb4client.TestFightDataOrBuilder> 
        getDefTestFightDataFieldBuilder() {
      if (defTestFightDataBuilder_ == null) {
        defTestFightDataBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4client.TestFightData, pb4client.TestFightData.Builder, pb4client.TestFightDataOrBuilder>(
                getDefTestFightData(),
                getParentForChildren(),
                isClean());
        defTestFightData_ = null;
      }
      return defTestFightDataBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.TestFight)
  }

  // @@protoc_insertion_point(class_scope:client2server.TestFight)
  private static final pb4client.TestFight DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.TestFight();
  }

  public static pb4client.TestFight getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<TestFight>
      PARSER = new com.google.protobuf.AbstractParser<TestFight>() {
    public TestFight parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new TestFight(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TestFight> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TestFight> getParserForType() {
    return PARSER;
  }

  public pb4client.TestFight getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

