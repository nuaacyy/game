// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 843
 * 客户端 -&gt; 服务器
 * 联盟捐献/换卡的玩家捐献
 * </pre>
 *
 * Protobuf type {@code client2server.AllianceExchangeDonate}
 */
public  final class AllianceExchangeDonate extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.AllianceExchangeDonate)
    AllianceExchangeDonateOrBuilder {
  // Use AllianceExchangeDonate.newBuilder() to construct.
  private AllianceExchangeDonate(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllianceExchangeDonate() {
    demandId_ = 0L;
    protoId_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AllianceExchangeDonate(
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
            demandId_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            protoId_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_AllianceExchangeDonate_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_AllianceExchangeDonate_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.AllianceExchangeDonate.class, pb4client.AllianceExchangeDonate.Builder.class);
  }

  private int bitField0_;
  public static final int DEMANDID_FIELD_NUMBER = 1;
  private long demandId_;
  /**
   * <pre>
   *需求ID
   * </pre>
   *
   * <code>required int64 demandId = 1;</code>
   */
  public boolean hasDemandId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *需求ID
   * </pre>
   *
   * <code>required int64 demandId = 1;</code>
   */
  public long getDemandId() {
    return demandId_;
  }

  public static final int PROTOID_FIELD_NUMBER = 2;
  private int protoId_;
  /**
   * <pre>
   *模板ID
   * </pre>
   *
   * <code>required int32 protoId = 2;</code>
   */
  public boolean hasProtoId() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *模板ID
   * </pre>
   *
   * <code>required int32 protoId = 2;</code>
   */
  public int getProtoId() {
    return protoId_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasDemandId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasProtoId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, demandId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, protoId_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, demandId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, protoId_);
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
    if (!(obj instanceof pb4client.AllianceExchangeDonate)) {
      return super.equals(obj);
    }
    pb4client.AllianceExchangeDonate other = (pb4client.AllianceExchangeDonate) obj;

    boolean result = true;
    result = result && (hasDemandId() == other.hasDemandId());
    if (hasDemandId()) {
      result = result && (getDemandId()
          == other.getDemandId());
    }
    result = result && (hasProtoId() == other.hasProtoId());
    if (hasProtoId()) {
      result = result && (getProtoId()
          == other.getProtoId());
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
    if (hasDemandId()) {
      hash = (37 * hash) + DEMANDID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getDemandId());
    }
    if (hasProtoId()) {
      hash = (37 * hash) + PROTOID_FIELD_NUMBER;
      hash = (53 * hash) + getProtoId();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.AllianceExchangeDonate parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceExchangeDonate parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceExchangeDonate parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceExchangeDonate parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceExchangeDonate parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceExchangeDonate parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceExchangeDonate parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AllianceExchangeDonate parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AllianceExchangeDonate parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.AllianceExchangeDonate parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AllianceExchangeDonate parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AllianceExchangeDonate parseFrom(
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
  public static Builder newBuilder(pb4client.AllianceExchangeDonate prototype) {
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
   * msgType = 843
   * 客户端 -&gt; 服务器
   * 联盟捐献/换卡的玩家捐献
   * </pre>
   *
   * Protobuf type {@code client2server.AllianceExchangeDonate}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.AllianceExchangeDonate)
      pb4client.AllianceExchangeDonateOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceExchangeDonate_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceExchangeDonate_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.AllianceExchangeDonate.class, pb4client.AllianceExchangeDonate.Builder.class);
    }

    // Construct using pb4client.AllianceExchangeDonate.newBuilder()
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
      demandId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      protoId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceExchangeDonate_descriptor;
    }

    public pb4client.AllianceExchangeDonate getDefaultInstanceForType() {
      return pb4client.AllianceExchangeDonate.getDefaultInstance();
    }

    public pb4client.AllianceExchangeDonate build() {
      pb4client.AllianceExchangeDonate result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.AllianceExchangeDonate buildPartial() {
      pb4client.AllianceExchangeDonate result = new pb4client.AllianceExchangeDonate(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.demandId_ = demandId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.protoId_ = protoId_;
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
      if (other instanceof pb4client.AllianceExchangeDonate) {
        return mergeFrom((pb4client.AllianceExchangeDonate)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.AllianceExchangeDonate other) {
      if (other == pb4client.AllianceExchangeDonate.getDefaultInstance()) return this;
      if (other.hasDemandId()) {
        setDemandId(other.getDemandId());
      }
      if (other.hasProtoId()) {
        setProtoId(other.getProtoId());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasDemandId()) {
        return false;
      }
      if (!hasProtoId()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.AllianceExchangeDonate parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.AllianceExchangeDonate) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long demandId_ ;
    /**
     * <pre>
     *需求ID
     * </pre>
     *
     * <code>required int64 demandId = 1;</code>
     */
    public boolean hasDemandId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *需求ID
     * </pre>
     *
     * <code>required int64 demandId = 1;</code>
     */
    public long getDemandId() {
      return demandId_;
    }
    /**
     * <pre>
     *需求ID
     * </pre>
     *
     * <code>required int64 demandId = 1;</code>
     */
    public Builder setDemandId(long value) {
      bitField0_ |= 0x00000001;
      demandId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *需求ID
     * </pre>
     *
     * <code>required int64 demandId = 1;</code>
     */
    public Builder clearDemandId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      demandId_ = 0L;
      onChanged();
      return this;
    }

    private int protoId_ ;
    /**
     * <pre>
     *模板ID
     * </pre>
     *
     * <code>required int32 protoId = 2;</code>
     */
    public boolean hasProtoId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *模板ID
     * </pre>
     *
     * <code>required int32 protoId = 2;</code>
     */
    public int getProtoId() {
      return protoId_;
    }
    /**
     * <pre>
     *模板ID
     * </pre>
     *
     * <code>required int32 protoId = 2;</code>
     */
    public Builder setProtoId(int value) {
      bitField0_ |= 0x00000002;
      protoId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *模板ID
     * </pre>
     *
     * <code>required int32 protoId = 2;</code>
     */
    public Builder clearProtoId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      protoId_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.AllianceExchangeDonate)
  }

  // @@protoc_insertion_point(class_scope:client2server.AllianceExchangeDonate)
  private static final pb4client.AllianceExchangeDonate DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.AllianceExchangeDonate();
  }

  public static pb4client.AllianceExchangeDonate getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<AllianceExchangeDonate>
      PARSER = new com.google.protobuf.AbstractParser<AllianceExchangeDonate>() {
    public AllianceExchangeDonate parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AllianceExchangeDonate(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllianceExchangeDonate> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllianceExchangeDonate> getParserForType() {
    return PARSER;
  }

  public pb4client.AllianceExchangeDonate getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

