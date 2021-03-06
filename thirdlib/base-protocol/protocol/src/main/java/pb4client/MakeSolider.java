// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 1081
 * 客户端 -&gt; 服务器
 * 造兵
 * </pre>
 *
 * Protobuf type {@code client2server.MakeSolider}
 */
public  final class MakeSolider extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.MakeSolider)
    MakeSoliderOrBuilder {
  // Use MakeSolider.newBuilder() to construct.
  private MakeSolider(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MakeSolider() {
    soliderId_ = 0;
    makeType_ = 0;
    makeNum_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MakeSolider(
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
            soliderId_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            makeType_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            makeNum_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_MakeSolider_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_MakeSolider_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.MakeSolider.class, pb4client.MakeSolider.Builder.class);
  }

  private int bitField0_;
  public static final int SOLIDERID_FIELD_NUMBER = 1;
  private int soliderId_;
  /**
   * <pre>
   * 要造的ID
   * </pre>
   *
   * <code>required int32 soliderId = 1;</code>
   */
  public boolean hasSoliderId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 要造的ID
   * </pre>
   *
   * <code>required int32 soliderId = 1;</code>
   */
  public int getSoliderId() {
    return soliderId_;
  }

  public static final int MAKETYPE_FIELD_NUMBER = 2;
  private int makeType_;
  /**
   * <pre>
   * 造兵类型  1-普通研发  2-元宝补齐资源研发
   * </pre>
   *
   * <code>required int32 makeType = 2;</code>
   */
  public boolean hasMakeType() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 造兵类型  1-普通研发  2-元宝补齐资源研发
   * </pre>
   *
   * <code>required int32 makeType = 2;</code>
   */
  public int getMakeType() {
    return makeType_;
  }

  public static final int MAKENUM_FIELD_NUMBER = 3;
  private int makeNum_;
  /**
   * <pre>
   * 造兵数量
   * </pre>
   *
   * <code>required int32 makeNum = 3;</code>
   */
  public boolean hasMakeNum() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 造兵数量
   * </pre>
   *
   * <code>required int32 makeNum = 3;</code>
   */
  public int getMakeNum() {
    return makeNum_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasSoliderId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasMakeType()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasMakeNum()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, soliderId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, makeType_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, makeNum_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, soliderId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, makeType_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, makeNum_);
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
    if (!(obj instanceof pb4client.MakeSolider)) {
      return super.equals(obj);
    }
    pb4client.MakeSolider other = (pb4client.MakeSolider) obj;

    boolean result = true;
    result = result && (hasSoliderId() == other.hasSoliderId());
    if (hasSoliderId()) {
      result = result && (getSoliderId()
          == other.getSoliderId());
    }
    result = result && (hasMakeType() == other.hasMakeType());
    if (hasMakeType()) {
      result = result && (getMakeType()
          == other.getMakeType());
    }
    result = result && (hasMakeNum() == other.hasMakeNum());
    if (hasMakeNum()) {
      result = result && (getMakeNum()
          == other.getMakeNum());
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
    if (hasSoliderId()) {
      hash = (37 * hash) + SOLIDERID_FIELD_NUMBER;
      hash = (53 * hash) + getSoliderId();
    }
    if (hasMakeType()) {
      hash = (37 * hash) + MAKETYPE_FIELD_NUMBER;
      hash = (53 * hash) + getMakeType();
    }
    if (hasMakeNum()) {
      hash = (37 * hash) + MAKENUM_FIELD_NUMBER;
      hash = (53 * hash) + getMakeNum();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.MakeSolider parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.MakeSolider parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.MakeSolider parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.MakeSolider parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.MakeSolider parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.MakeSolider parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.MakeSolider parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.MakeSolider parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.MakeSolider parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.MakeSolider parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.MakeSolider parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.MakeSolider parseFrom(
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
  public static Builder newBuilder(pb4client.MakeSolider prototype) {
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
   * msgType = 1081
   * 客户端 -&gt; 服务器
   * 造兵
   * </pre>
   *
   * Protobuf type {@code client2server.MakeSolider}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.MakeSolider)
      pb4client.MakeSoliderOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_MakeSolider_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_MakeSolider_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.MakeSolider.class, pb4client.MakeSolider.Builder.class);
    }

    // Construct using pb4client.MakeSolider.newBuilder()
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
      soliderId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      makeType_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      makeNum_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_MakeSolider_descriptor;
    }

    public pb4client.MakeSolider getDefaultInstanceForType() {
      return pb4client.MakeSolider.getDefaultInstance();
    }

    public pb4client.MakeSolider build() {
      pb4client.MakeSolider result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.MakeSolider buildPartial() {
      pb4client.MakeSolider result = new pb4client.MakeSolider(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.soliderId_ = soliderId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.makeType_ = makeType_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.makeNum_ = makeNum_;
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
      if (other instanceof pb4client.MakeSolider) {
        return mergeFrom((pb4client.MakeSolider)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.MakeSolider other) {
      if (other == pb4client.MakeSolider.getDefaultInstance()) return this;
      if (other.hasSoliderId()) {
        setSoliderId(other.getSoliderId());
      }
      if (other.hasMakeType()) {
        setMakeType(other.getMakeType());
      }
      if (other.hasMakeNum()) {
        setMakeNum(other.getMakeNum());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasSoliderId()) {
        return false;
      }
      if (!hasMakeType()) {
        return false;
      }
      if (!hasMakeNum()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.MakeSolider parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.MakeSolider) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int soliderId_ ;
    /**
     * <pre>
     * 要造的ID
     * </pre>
     *
     * <code>required int32 soliderId = 1;</code>
     */
    public boolean hasSoliderId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 要造的ID
     * </pre>
     *
     * <code>required int32 soliderId = 1;</code>
     */
    public int getSoliderId() {
      return soliderId_;
    }
    /**
     * <pre>
     * 要造的ID
     * </pre>
     *
     * <code>required int32 soliderId = 1;</code>
     */
    public Builder setSoliderId(int value) {
      bitField0_ |= 0x00000001;
      soliderId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 要造的ID
     * </pre>
     *
     * <code>required int32 soliderId = 1;</code>
     */
    public Builder clearSoliderId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      soliderId_ = 0;
      onChanged();
      return this;
    }

    private int makeType_ ;
    /**
     * <pre>
     * 造兵类型  1-普通研发  2-元宝补齐资源研发
     * </pre>
     *
     * <code>required int32 makeType = 2;</code>
     */
    public boolean hasMakeType() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 造兵类型  1-普通研发  2-元宝补齐资源研发
     * </pre>
     *
     * <code>required int32 makeType = 2;</code>
     */
    public int getMakeType() {
      return makeType_;
    }
    /**
     * <pre>
     * 造兵类型  1-普通研发  2-元宝补齐资源研发
     * </pre>
     *
     * <code>required int32 makeType = 2;</code>
     */
    public Builder setMakeType(int value) {
      bitField0_ |= 0x00000002;
      makeType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 造兵类型  1-普通研发  2-元宝补齐资源研发
     * </pre>
     *
     * <code>required int32 makeType = 2;</code>
     */
    public Builder clearMakeType() {
      bitField0_ = (bitField0_ & ~0x00000002);
      makeType_ = 0;
      onChanged();
      return this;
    }

    private int makeNum_ ;
    /**
     * <pre>
     * 造兵数量
     * </pre>
     *
     * <code>required int32 makeNum = 3;</code>
     */
    public boolean hasMakeNum() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 造兵数量
     * </pre>
     *
     * <code>required int32 makeNum = 3;</code>
     */
    public int getMakeNum() {
      return makeNum_;
    }
    /**
     * <pre>
     * 造兵数量
     * </pre>
     *
     * <code>required int32 makeNum = 3;</code>
     */
    public Builder setMakeNum(int value) {
      bitField0_ |= 0x00000004;
      makeNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 造兵数量
     * </pre>
     *
     * <code>required int32 makeNum = 3;</code>
     */
    public Builder clearMakeNum() {
      bitField0_ = (bitField0_ & ~0x00000004);
      makeNum_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.MakeSolider)
  }

  // @@protoc_insertion_point(class_scope:client2server.MakeSolider)
  private static final pb4client.MakeSolider DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.MakeSolider();
  }

  public static pb4client.MakeSolider getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<MakeSolider>
      PARSER = new com.google.protobuf.AbstractParser<MakeSolider>() {
    public MakeSolider parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new MakeSolider(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MakeSolider> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MakeSolider> getParserForType() {
    return PARSER;
  }

  public pb4client.MakeSolider getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

