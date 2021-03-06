// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.SplitKingEquipRt}
 */
public  final class SplitKingEquipRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.SplitKingEquipRt)
    SplitKingEquipRtOrBuilder {
  // Use SplitKingEquipRt.newBuilder() to construct.
  private SplitKingEquipRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SplitKingEquipRt() {
    rt_ = 0;
    equipId_ = 0L;
    res_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SplitKingEquipRt(
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
            rt_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            equipId_ = input.readInt64();
            break;
          }
          case 26: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000004;
            res_ = bs;
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
    return pb4client.War2GamePkt.internal_static_client2server_SplitKingEquipRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_SplitKingEquipRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.SplitKingEquipRt.class, pb4client.SplitKingEquipRt.Builder.class);
  }

  private int bitField0_;
  public static final int RT_FIELD_NUMBER = 1;
  private int rt_;
  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  public boolean hasRt() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  public int getRt() {
    return rt_;
  }

  public static final int EQUIPID_FIELD_NUMBER = 2;
  private long equipId_;
  /**
   * <pre>
   * 要分解的装备ID
   * </pre>
   *
   * <code>optional int64 equipId = 2;</code>
   */
  public boolean hasEquipId() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 要分解的装备ID
   * </pre>
   *
   * <code>optional int64 equipId = 2;</code>
   */
  public long getEquipId() {
    return equipId_;
  }

  public static final int RES_FIELD_NUMBER = 3;
  private volatile java.lang.Object res_;
  /**
   * <pre>
   * 奖励
   * </pre>
   *
   * <code>optional string res = 3;</code>
   */
  public boolean hasRes() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 奖励
   * </pre>
   *
   * <code>optional string res = 3;</code>
   */
  public java.lang.String getRes() {
    java.lang.Object ref = res_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        res_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   * 奖励
   * </pre>
   *
   * <code>optional string res = 3;</code>
   */
  public com.google.protobuf.ByteString
      getResBytes() {
    java.lang.Object ref = res_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      res_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasRt()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, rt_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt64(2, equipId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, res_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, rt_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, equipId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, res_);
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
    if (!(obj instanceof pb4client.SplitKingEquipRt)) {
      return super.equals(obj);
    }
    pb4client.SplitKingEquipRt other = (pb4client.SplitKingEquipRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && (hasEquipId() == other.hasEquipId());
    if (hasEquipId()) {
      result = result && (getEquipId()
          == other.getEquipId());
    }
    result = result && (hasRes() == other.hasRes());
    if (hasRes()) {
      result = result && getRes()
          .equals(other.getRes());
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
    if (hasRt()) {
      hash = (37 * hash) + RT_FIELD_NUMBER;
      hash = (53 * hash) + getRt();
    }
    if (hasEquipId()) {
      hash = (37 * hash) + EQUIPID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getEquipId());
    }
    if (hasRes()) {
      hash = (37 * hash) + RES_FIELD_NUMBER;
      hash = (53 * hash) + getRes().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.SplitKingEquipRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SplitKingEquipRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SplitKingEquipRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SplitKingEquipRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SplitKingEquipRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SplitKingEquipRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SplitKingEquipRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.SplitKingEquipRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.SplitKingEquipRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.SplitKingEquipRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.SplitKingEquipRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.SplitKingEquipRt parseFrom(
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
  public static Builder newBuilder(pb4client.SplitKingEquipRt prototype) {
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
   * Protobuf type {@code client2server.SplitKingEquipRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.SplitKingEquipRt)
      pb4client.SplitKingEquipRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_SplitKingEquipRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_SplitKingEquipRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.SplitKingEquipRt.class, pb4client.SplitKingEquipRt.Builder.class);
    }

    // Construct using pb4client.SplitKingEquipRt.newBuilder()
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
      rt_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      equipId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      res_ = "";
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_SplitKingEquipRt_descriptor;
    }

    public pb4client.SplitKingEquipRt getDefaultInstanceForType() {
      return pb4client.SplitKingEquipRt.getDefaultInstance();
    }

    public pb4client.SplitKingEquipRt build() {
      pb4client.SplitKingEquipRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.SplitKingEquipRt buildPartial() {
      pb4client.SplitKingEquipRt result = new pb4client.SplitKingEquipRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.equipId_ = equipId_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.res_ = res_;
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
      if (other instanceof pb4client.SplitKingEquipRt) {
        return mergeFrom((pb4client.SplitKingEquipRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.SplitKingEquipRt other) {
      if (other == pb4client.SplitKingEquipRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (other.hasEquipId()) {
        setEquipId(other.getEquipId());
      }
      if (other.hasRes()) {
        bitField0_ |= 0x00000004;
        res_ = other.res_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasRt()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.SplitKingEquipRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.SplitKingEquipRt) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int rt_ ;
    /**
     * <pre>
     * 返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public boolean hasRt() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public int getRt() {
      return rt_;
    }
    /**
     * <pre>
     * 返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public Builder setRt(int value) {
      bitField0_ |= 0x00000001;
      rt_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public Builder clearRt() {
      bitField0_ = (bitField0_ & ~0x00000001);
      rt_ = 0;
      onChanged();
      return this;
    }

    private long equipId_ ;
    /**
     * <pre>
     * 要分解的装备ID
     * </pre>
     *
     * <code>optional int64 equipId = 2;</code>
     */
    public boolean hasEquipId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 要分解的装备ID
     * </pre>
     *
     * <code>optional int64 equipId = 2;</code>
     */
    public long getEquipId() {
      return equipId_;
    }
    /**
     * <pre>
     * 要分解的装备ID
     * </pre>
     *
     * <code>optional int64 equipId = 2;</code>
     */
    public Builder setEquipId(long value) {
      bitField0_ |= 0x00000002;
      equipId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 要分解的装备ID
     * </pre>
     *
     * <code>optional int64 equipId = 2;</code>
     */
    public Builder clearEquipId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      equipId_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object res_ = "";
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>optional string res = 3;</code>
     */
    public boolean hasRes() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>optional string res = 3;</code>
     */
    public java.lang.String getRes() {
      java.lang.Object ref = res_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          res_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>optional string res = 3;</code>
     */
    public com.google.protobuf.ByteString
        getResBytes() {
      java.lang.Object ref = res_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        res_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>optional string res = 3;</code>
     */
    public Builder setRes(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      res_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>optional string res = 3;</code>
     */
    public Builder clearRes() {
      bitField0_ = (bitField0_ & ~0x00000004);
      res_ = getDefaultInstance().getRes();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>optional string res = 3;</code>
     */
    public Builder setResBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      res_ = value;
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


    // @@protoc_insertion_point(builder_scope:client2server.SplitKingEquipRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.SplitKingEquipRt)
  private static final pb4client.SplitKingEquipRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.SplitKingEquipRt();
  }

  public static pb4client.SplitKingEquipRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<SplitKingEquipRt>
      PARSER = new com.google.protobuf.AbstractParser<SplitKingEquipRt>() {
    public SplitKingEquipRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new SplitKingEquipRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SplitKingEquipRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SplitKingEquipRt> getParserForType() {
    return PARSER;
  }

  public pb4client.SplitKingEquipRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

