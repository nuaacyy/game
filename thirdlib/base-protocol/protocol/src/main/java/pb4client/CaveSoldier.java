// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.CaveSoldier}
 */
public  final class CaveSoldier extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.CaveSoldier)
    CaveSoldierOrBuilder {
  // Use CaveSoldier.newBuilder() to construct.
  private CaveSoldier(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CaveSoldier() {
    soldierType_ = 0;
    soldierNum_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CaveSoldier(
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
            soldierType_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            soldierNum_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_CaveSoldier_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_CaveSoldier_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.CaveSoldier.class, pb4client.CaveSoldier.Builder.class);
  }

  private int bitField0_;
  public static final int SOLDIERTYPE_FIELD_NUMBER = 1;
  private int soldierType_;
  /**
   * <pre>
   * 士兵类型
   * </pre>
   *
   * <code>required int32 soldierType = 1;</code>
   */
  public boolean hasSoldierType() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 士兵类型
   * </pre>
   *
   * <code>required int32 soldierType = 1;</code>
   */
  public int getSoldierType() {
    return soldierType_;
  }

  public static final int SOLDIERNUM_FIELD_NUMBER = 2;
  private int soldierNum_;
  /**
   * <pre>
   * 士兵数
   * </pre>
   *
   * <code>required int32 soldierNum = 2;</code>
   */
  public boolean hasSoldierNum() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 士兵数
   * </pre>
   *
   * <code>required int32 soldierNum = 2;</code>
   */
  public int getSoldierNum() {
    return soldierNum_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasSoldierType()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasSoldierNum()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, soldierType_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, soldierNum_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, soldierType_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, soldierNum_);
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
    if (!(obj instanceof pb4client.CaveSoldier)) {
      return super.equals(obj);
    }
    pb4client.CaveSoldier other = (pb4client.CaveSoldier) obj;

    boolean result = true;
    result = result && (hasSoldierType() == other.hasSoldierType());
    if (hasSoldierType()) {
      result = result && (getSoldierType()
          == other.getSoldierType());
    }
    result = result && (hasSoldierNum() == other.hasSoldierNum());
    if (hasSoldierNum()) {
      result = result && (getSoldierNum()
          == other.getSoldierNum());
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
    if (hasSoldierType()) {
      hash = (37 * hash) + SOLDIERTYPE_FIELD_NUMBER;
      hash = (53 * hash) + getSoldierType();
    }
    if (hasSoldierNum()) {
      hash = (37 * hash) + SOLDIERNUM_FIELD_NUMBER;
      hash = (53 * hash) + getSoldierNum();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.CaveSoldier parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.CaveSoldier parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.CaveSoldier parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.CaveSoldier parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.CaveSoldier parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.CaveSoldier parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.CaveSoldier parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.CaveSoldier parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.CaveSoldier parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.CaveSoldier parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.CaveSoldier parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.CaveSoldier parseFrom(
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
  public static Builder newBuilder(pb4client.CaveSoldier prototype) {
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
   * Protobuf type {@code client2server.CaveSoldier}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.CaveSoldier)
      pb4client.CaveSoldierOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_CaveSoldier_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_CaveSoldier_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.CaveSoldier.class, pb4client.CaveSoldier.Builder.class);
    }

    // Construct using pb4client.CaveSoldier.newBuilder()
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
      soldierType_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      soldierNum_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_CaveSoldier_descriptor;
    }

    public pb4client.CaveSoldier getDefaultInstanceForType() {
      return pb4client.CaveSoldier.getDefaultInstance();
    }

    public pb4client.CaveSoldier build() {
      pb4client.CaveSoldier result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.CaveSoldier buildPartial() {
      pb4client.CaveSoldier result = new pb4client.CaveSoldier(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.soldierType_ = soldierType_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.soldierNum_ = soldierNum_;
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
      if (other instanceof pb4client.CaveSoldier) {
        return mergeFrom((pb4client.CaveSoldier)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.CaveSoldier other) {
      if (other == pb4client.CaveSoldier.getDefaultInstance()) return this;
      if (other.hasSoldierType()) {
        setSoldierType(other.getSoldierType());
      }
      if (other.hasSoldierNum()) {
        setSoldierNum(other.getSoldierNum());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasSoldierType()) {
        return false;
      }
      if (!hasSoldierNum()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.CaveSoldier parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.CaveSoldier) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int soldierType_ ;
    /**
     * <pre>
     * 士兵类型
     * </pre>
     *
     * <code>required int32 soldierType = 1;</code>
     */
    public boolean hasSoldierType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 士兵类型
     * </pre>
     *
     * <code>required int32 soldierType = 1;</code>
     */
    public int getSoldierType() {
      return soldierType_;
    }
    /**
     * <pre>
     * 士兵类型
     * </pre>
     *
     * <code>required int32 soldierType = 1;</code>
     */
    public Builder setSoldierType(int value) {
      bitField0_ |= 0x00000001;
      soldierType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 士兵类型
     * </pre>
     *
     * <code>required int32 soldierType = 1;</code>
     */
    public Builder clearSoldierType() {
      bitField0_ = (bitField0_ & ~0x00000001);
      soldierType_ = 0;
      onChanged();
      return this;
    }

    private int soldierNum_ ;
    /**
     * <pre>
     * 士兵数
     * </pre>
     *
     * <code>required int32 soldierNum = 2;</code>
     */
    public boolean hasSoldierNum() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 士兵数
     * </pre>
     *
     * <code>required int32 soldierNum = 2;</code>
     */
    public int getSoldierNum() {
      return soldierNum_;
    }
    /**
     * <pre>
     * 士兵数
     * </pre>
     *
     * <code>required int32 soldierNum = 2;</code>
     */
    public Builder setSoldierNum(int value) {
      bitField0_ |= 0x00000002;
      soldierNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 士兵数
     * </pre>
     *
     * <code>required int32 soldierNum = 2;</code>
     */
    public Builder clearSoldierNum() {
      bitField0_ = (bitField0_ & ~0x00000002);
      soldierNum_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.CaveSoldier)
  }

  // @@protoc_insertion_point(class_scope:client2server.CaveSoldier)
  private static final pb4client.CaveSoldier DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.CaveSoldier();
  }

  public static pb4client.CaveSoldier getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<CaveSoldier>
      PARSER = new com.google.protobuf.AbstractParser<CaveSoldier>() {
    public CaveSoldier parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new CaveSoldier(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CaveSoldier> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CaveSoldier> getParserForType() {
    return PARSER;
  }

  public pb4client.CaveSoldier getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

