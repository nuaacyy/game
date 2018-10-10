// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.SellVo}
 */
public  final class SellVo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.SellVo)
    SellVoOrBuilder {
  // Use SellVo.newBuilder() to construct.
  private SellVo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SellVo() {
    equipId_ = 0L;
    equipNum_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SellVo(
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
            equipId_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            equipNum_ = input.readInt64();
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
    return pb4client.War2GamePkt.internal_static_client2server_SellVo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_SellVo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.SellVo.class, pb4client.SellVo.Builder.class);
  }

  private int bitField0_;
  public static final int EQUIPID_FIELD_NUMBER = 1;
  private long equipId_;
  /**
   * <pre>
   * 要卖掉的ID
   * </pre>
   *
   * <code>required int64 equipId = 1;</code>
   */
  public boolean hasEquipId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 要卖掉的ID
   * </pre>
   *
   * <code>required int64 equipId = 1;</code>
   */
  public long getEquipId() {
    return equipId_;
  }

  public static final int EQUIPNUM_FIELD_NUMBER = 2;
  private long equipNum_;
  /**
   * <pre>
   * 要卖掉的数量
   * </pre>
   *
   * <code>required int64 equipNum = 2;</code>
   */
  public boolean hasEquipNum() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 要卖掉的数量
   * </pre>
   *
   * <code>required int64 equipNum = 2;</code>
   */
  public long getEquipNum() {
    return equipNum_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasEquipId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasEquipNum()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, equipId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt64(2, equipNum_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, equipId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, equipNum_);
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
    if (!(obj instanceof pb4client.SellVo)) {
      return super.equals(obj);
    }
    pb4client.SellVo other = (pb4client.SellVo) obj;

    boolean result = true;
    result = result && (hasEquipId() == other.hasEquipId());
    if (hasEquipId()) {
      result = result && (getEquipId()
          == other.getEquipId());
    }
    result = result && (hasEquipNum() == other.hasEquipNum());
    if (hasEquipNum()) {
      result = result && (getEquipNum()
          == other.getEquipNum());
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
    if (hasEquipId()) {
      hash = (37 * hash) + EQUIPID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getEquipId());
    }
    if (hasEquipNum()) {
      hash = (37 * hash) + EQUIPNUM_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getEquipNum());
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.SellVo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SellVo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SellVo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SellVo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SellVo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SellVo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SellVo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.SellVo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.SellVo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.SellVo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.SellVo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.SellVo parseFrom(
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
  public static Builder newBuilder(pb4client.SellVo prototype) {
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
   * Protobuf type {@code client2server.SellVo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.SellVo)
      pb4client.SellVoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_SellVo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_SellVo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.SellVo.class, pb4client.SellVo.Builder.class);
    }

    // Construct using pb4client.SellVo.newBuilder()
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
      equipId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      equipNum_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_SellVo_descriptor;
    }

    public pb4client.SellVo getDefaultInstanceForType() {
      return pb4client.SellVo.getDefaultInstance();
    }

    public pb4client.SellVo build() {
      pb4client.SellVo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.SellVo buildPartial() {
      pb4client.SellVo result = new pb4client.SellVo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.equipId_ = equipId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.equipNum_ = equipNum_;
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
      if (other instanceof pb4client.SellVo) {
        return mergeFrom((pb4client.SellVo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.SellVo other) {
      if (other == pb4client.SellVo.getDefaultInstance()) return this;
      if (other.hasEquipId()) {
        setEquipId(other.getEquipId());
      }
      if (other.hasEquipNum()) {
        setEquipNum(other.getEquipNum());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasEquipId()) {
        return false;
      }
      if (!hasEquipNum()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.SellVo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.SellVo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long equipId_ ;
    /**
     * <pre>
     * 要卖掉的ID
     * </pre>
     *
     * <code>required int64 equipId = 1;</code>
     */
    public boolean hasEquipId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 要卖掉的ID
     * </pre>
     *
     * <code>required int64 equipId = 1;</code>
     */
    public long getEquipId() {
      return equipId_;
    }
    /**
     * <pre>
     * 要卖掉的ID
     * </pre>
     *
     * <code>required int64 equipId = 1;</code>
     */
    public Builder setEquipId(long value) {
      bitField0_ |= 0x00000001;
      equipId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 要卖掉的ID
     * </pre>
     *
     * <code>required int64 equipId = 1;</code>
     */
    public Builder clearEquipId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      equipId_ = 0L;
      onChanged();
      return this;
    }

    private long equipNum_ ;
    /**
     * <pre>
     * 要卖掉的数量
     * </pre>
     *
     * <code>required int64 equipNum = 2;</code>
     */
    public boolean hasEquipNum() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 要卖掉的数量
     * </pre>
     *
     * <code>required int64 equipNum = 2;</code>
     */
    public long getEquipNum() {
      return equipNum_;
    }
    /**
     * <pre>
     * 要卖掉的数量
     * </pre>
     *
     * <code>required int64 equipNum = 2;</code>
     */
    public Builder setEquipNum(long value) {
      bitField0_ |= 0x00000002;
      equipNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 要卖掉的数量
     * </pre>
     *
     * <code>required int64 equipNum = 2;</code>
     */
    public Builder clearEquipNum() {
      bitField0_ = (bitField0_ & ~0x00000002);
      equipNum_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:client2server.SellVo)
  }

  // @@protoc_insertion_point(class_scope:client2server.SellVo)
  private static final pb4client.SellVo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.SellVo();
  }

  public static pb4client.SellVo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<SellVo>
      PARSER = new com.google.protobuf.AbstractParser<SellVo>() {
    public SellVo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new SellVo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SellVo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SellVo> getParserForType() {
    return PARSER;
  }

  public pb4client.SellVo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
