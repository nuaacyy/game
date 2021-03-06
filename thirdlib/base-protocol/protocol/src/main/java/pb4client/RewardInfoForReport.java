// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 *战报中奖励信息
 * </pre>
 *
 * Protobuf type {@code client2server.RewardInfoForReport}
 */
public  final class RewardInfoForReport extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.RewardInfoForReport)
    RewardInfoForReportOrBuilder {
  // Use RewardInfoForReport.newBuilder() to construct.
  private RewardInfoForReport(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RewardInfoForReport() {
    kingExp_ = 0;
    resVo_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private RewardInfoForReport(
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
            kingExp_ = input.readInt32();
            break;
          }
          case 18: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000002;
            resVo_ = bs;
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
    return pb4client.War2GamePkt.internal_static_client2server_RewardInfoForReport_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_RewardInfoForReport_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.RewardInfoForReport.class, pb4client.RewardInfoForReport.Builder.class);
  }

  private int bitField0_;
  public static final int KINGEXP_FIELD_NUMBER = 1;
  private int kingExp_;
  /**
   * <pre>
   *君主经验
   * </pre>
   *
   * <code>required int32 kingExp = 1;</code>
   */
  public boolean hasKingExp() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *君主经验
   * </pre>
   *
   * <code>required int32 kingExp = 1;</code>
   */
  public int getKingExp() {
    return kingExp_;
  }

  public static final int RESVO_FIELD_NUMBER = 2;
  private volatile java.lang.Object resVo_;
  /**
   * <pre>
   *资源奖励信息
   * </pre>
   *
   * <code>required string resVo = 2;</code>
   */
  public boolean hasResVo() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *资源奖励信息
   * </pre>
   *
   * <code>required string resVo = 2;</code>
   */
  public java.lang.String getResVo() {
    java.lang.Object ref = resVo_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        resVo_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   *资源奖励信息
   * </pre>
   *
   * <code>required string resVo = 2;</code>
   */
  public com.google.protobuf.ByteString
      getResVoBytes() {
    java.lang.Object ref = resVo_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      resVo_ = b;
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

    if (!hasKingExp()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasResVo()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, kingExp_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, resVo_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, kingExp_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, resVo_);
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
    if (!(obj instanceof pb4client.RewardInfoForReport)) {
      return super.equals(obj);
    }
    pb4client.RewardInfoForReport other = (pb4client.RewardInfoForReport) obj;

    boolean result = true;
    result = result && (hasKingExp() == other.hasKingExp());
    if (hasKingExp()) {
      result = result && (getKingExp()
          == other.getKingExp());
    }
    result = result && (hasResVo() == other.hasResVo());
    if (hasResVo()) {
      result = result && getResVo()
          .equals(other.getResVo());
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
    if (hasKingExp()) {
      hash = (37 * hash) + KINGEXP_FIELD_NUMBER;
      hash = (53 * hash) + getKingExp();
    }
    if (hasResVo()) {
      hash = (37 * hash) + RESVO_FIELD_NUMBER;
      hash = (53 * hash) + getResVo().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.RewardInfoForReport parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.RewardInfoForReport parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.RewardInfoForReport parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.RewardInfoForReport parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.RewardInfoForReport parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.RewardInfoForReport parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.RewardInfoForReport parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.RewardInfoForReport parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.RewardInfoForReport parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.RewardInfoForReport parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.RewardInfoForReport parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.RewardInfoForReport parseFrom(
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
  public static Builder newBuilder(pb4client.RewardInfoForReport prototype) {
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
   *战报中奖励信息
   * </pre>
   *
   * Protobuf type {@code client2server.RewardInfoForReport}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.RewardInfoForReport)
      pb4client.RewardInfoForReportOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_RewardInfoForReport_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_RewardInfoForReport_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.RewardInfoForReport.class, pb4client.RewardInfoForReport.Builder.class);
    }

    // Construct using pb4client.RewardInfoForReport.newBuilder()
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
      kingExp_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      resVo_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_RewardInfoForReport_descriptor;
    }

    public pb4client.RewardInfoForReport getDefaultInstanceForType() {
      return pb4client.RewardInfoForReport.getDefaultInstance();
    }

    public pb4client.RewardInfoForReport build() {
      pb4client.RewardInfoForReport result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.RewardInfoForReport buildPartial() {
      pb4client.RewardInfoForReport result = new pb4client.RewardInfoForReport(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.kingExp_ = kingExp_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.resVo_ = resVo_;
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
      if (other instanceof pb4client.RewardInfoForReport) {
        return mergeFrom((pb4client.RewardInfoForReport)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.RewardInfoForReport other) {
      if (other == pb4client.RewardInfoForReport.getDefaultInstance()) return this;
      if (other.hasKingExp()) {
        setKingExp(other.getKingExp());
      }
      if (other.hasResVo()) {
        bitField0_ |= 0x00000002;
        resVo_ = other.resVo_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasKingExp()) {
        return false;
      }
      if (!hasResVo()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.RewardInfoForReport parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.RewardInfoForReport) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int kingExp_ ;
    /**
     * <pre>
     *君主经验
     * </pre>
     *
     * <code>required int32 kingExp = 1;</code>
     */
    public boolean hasKingExp() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *君主经验
     * </pre>
     *
     * <code>required int32 kingExp = 1;</code>
     */
    public int getKingExp() {
      return kingExp_;
    }
    /**
     * <pre>
     *君主经验
     * </pre>
     *
     * <code>required int32 kingExp = 1;</code>
     */
    public Builder setKingExp(int value) {
      bitField0_ |= 0x00000001;
      kingExp_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *君主经验
     * </pre>
     *
     * <code>required int32 kingExp = 1;</code>
     */
    public Builder clearKingExp() {
      bitField0_ = (bitField0_ & ~0x00000001);
      kingExp_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object resVo_ = "";
    /**
     * <pre>
     *资源奖励信息
     * </pre>
     *
     * <code>required string resVo = 2;</code>
     */
    public boolean hasResVo() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *资源奖励信息
     * </pre>
     *
     * <code>required string resVo = 2;</code>
     */
    public java.lang.String getResVo() {
      java.lang.Object ref = resVo_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          resVo_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *资源奖励信息
     * </pre>
     *
     * <code>required string resVo = 2;</code>
     */
    public com.google.protobuf.ByteString
        getResVoBytes() {
      java.lang.Object ref = resVo_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        resVo_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *资源奖励信息
     * </pre>
     *
     * <code>required string resVo = 2;</code>
     */
    public Builder setResVo(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      resVo_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *资源奖励信息
     * </pre>
     *
     * <code>required string resVo = 2;</code>
     */
    public Builder clearResVo() {
      bitField0_ = (bitField0_ & ~0x00000002);
      resVo_ = getDefaultInstance().getResVo();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *资源奖励信息
     * </pre>
     *
     * <code>required string resVo = 2;</code>
     */
    public Builder setResVoBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      resVo_ = value;
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


    // @@protoc_insertion_point(builder_scope:client2server.RewardInfoForReport)
  }

  // @@protoc_insertion_point(class_scope:client2server.RewardInfoForReport)
  private static final pb4client.RewardInfoForReport DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.RewardInfoForReport();
  }

  public static pb4client.RewardInfoForReport getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<RewardInfoForReport>
      PARSER = new com.google.protobuf.AbstractParser<RewardInfoForReport>() {
    public RewardInfoForReport parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new RewardInfoForReport(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<RewardInfoForReport> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RewardInfoForReport> getParserForType() {
    return PARSER;
  }

  public pb4client.RewardInfoForReport getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

