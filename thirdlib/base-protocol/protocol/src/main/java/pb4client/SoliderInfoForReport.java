// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 *士兵数据
 * </pre>
 *
 * Protobuf type {@code client2server.SoliderInfoForReport}
 */
public  final class SoliderInfoForReport extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.SoliderInfoForReport)
    SoliderInfoForReportOrBuilder {
  // Use SoliderInfoForReport.newBuilder() to construct.
  private SoliderInfoForReport(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SoliderInfoForReport() {
    protoId_ = 0;
    totalNum_ = 0;
    woundedNum_ = 0;
    diedNum_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SoliderInfoForReport(
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
            totalNum_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            woundedNum_ = input.readInt32();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            diedNum_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_SoliderInfoForReport_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_SoliderInfoForReport_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.SoliderInfoForReport.class, pb4client.SoliderInfoForReport.Builder.class);
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

  public static final int TOTALNUM_FIELD_NUMBER = 2;
  private int totalNum_;
  /**
   * <pre>
   *总兵力
   * </pre>
   *
   * <code>required int32 totalNum = 2;</code>
   */
  public boolean hasTotalNum() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *总兵力
   * </pre>
   *
   * <code>required int32 totalNum = 2;</code>
   */
  public int getTotalNum() {
    return totalNum_;
  }

  public static final int WOUNDEDNUM_FIELD_NUMBER = 3;
  private int woundedNum_;
  /**
   * <pre>
   *伤兵量
   * </pre>
   *
   * <code>required int32 woundedNum = 3;</code>
   */
  public boolean hasWoundedNum() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   *伤兵量
   * </pre>
   *
   * <code>required int32 woundedNum = 3;</code>
   */
  public int getWoundedNum() {
    return woundedNum_;
  }

  public static final int DIEDNUM_FIELD_NUMBER = 4;
  private int diedNum_;
  /**
   * <pre>
   *死兵量
   * </pre>
   *
   * <code>required int32 diedNum = 4;</code>
   */
  public boolean hasDiedNum() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   *死兵量
   * </pre>
   *
   * <code>required int32 diedNum = 4;</code>
   */
  public int getDiedNum() {
    return diedNum_;
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
    if (!hasTotalNum()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasWoundedNum()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasDiedNum()) {
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
      output.writeInt32(2, totalNum_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, woundedNum_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, diedNum_);
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
        .computeInt32Size(2, totalNum_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, woundedNum_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, diedNum_);
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
    if (!(obj instanceof pb4client.SoliderInfoForReport)) {
      return super.equals(obj);
    }
    pb4client.SoliderInfoForReport other = (pb4client.SoliderInfoForReport) obj;

    boolean result = true;
    result = result && (hasProtoId() == other.hasProtoId());
    if (hasProtoId()) {
      result = result && (getProtoId()
          == other.getProtoId());
    }
    result = result && (hasTotalNum() == other.hasTotalNum());
    if (hasTotalNum()) {
      result = result && (getTotalNum()
          == other.getTotalNum());
    }
    result = result && (hasWoundedNum() == other.hasWoundedNum());
    if (hasWoundedNum()) {
      result = result && (getWoundedNum()
          == other.getWoundedNum());
    }
    result = result && (hasDiedNum() == other.hasDiedNum());
    if (hasDiedNum()) {
      result = result && (getDiedNum()
          == other.getDiedNum());
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
    if (hasTotalNum()) {
      hash = (37 * hash) + TOTALNUM_FIELD_NUMBER;
      hash = (53 * hash) + getTotalNum();
    }
    if (hasWoundedNum()) {
      hash = (37 * hash) + WOUNDEDNUM_FIELD_NUMBER;
      hash = (53 * hash) + getWoundedNum();
    }
    if (hasDiedNum()) {
      hash = (37 * hash) + DIEDNUM_FIELD_NUMBER;
      hash = (53 * hash) + getDiedNum();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.SoliderInfoForReport parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SoliderInfoForReport parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SoliderInfoForReport parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SoliderInfoForReport parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SoliderInfoForReport parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SoliderInfoForReport parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SoliderInfoForReport parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.SoliderInfoForReport parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.SoliderInfoForReport parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.SoliderInfoForReport parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.SoliderInfoForReport parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.SoliderInfoForReport parseFrom(
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
  public static Builder newBuilder(pb4client.SoliderInfoForReport prototype) {
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
   *士兵数据
   * </pre>
   *
   * Protobuf type {@code client2server.SoliderInfoForReport}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.SoliderInfoForReport)
      pb4client.SoliderInfoForReportOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_SoliderInfoForReport_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_SoliderInfoForReport_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.SoliderInfoForReport.class, pb4client.SoliderInfoForReport.Builder.class);
    }

    // Construct using pb4client.SoliderInfoForReport.newBuilder()
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
      totalNum_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      woundedNum_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      diedNum_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_SoliderInfoForReport_descriptor;
    }

    public pb4client.SoliderInfoForReport getDefaultInstanceForType() {
      return pb4client.SoliderInfoForReport.getDefaultInstance();
    }

    public pb4client.SoliderInfoForReport build() {
      pb4client.SoliderInfoForReport result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.SoliderInfoForReport buildPartial() {
      pb4client.SoliderInfoForReport result = new pb4client.SoliderInfoForReport(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.protoId_ = protoId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.totalNum_ = totalNum_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.woundedNum_ = woundedNum_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.diedNum_ = diedNum_;
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
      if (other instanceof pb4client.SoliderInfoForReport) {
        return mergeFrom((pb4client.SoliderInfoForReport)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.SoliderInfoForReport other) {
      if (other == pb4client.SoliderInfoForReport.getDefaultInstance()) return this;
      if (other.hasProtoId()) {
        setProtoId(other.getProtoId());
      }
      if (other.hasTotalNum()) {
        setTotalNum(other.getTotalNum());
      }
      if (other.hasWoundedNum()) {
        setWoundedNum(other.getWoundedNum());
      }
      if (other.hasDiedNum()) {
        setDiedNum(other.getDiedNum());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasProtoId()) {
        return false;
      }
      if (!hasTotalNum()) {
        return false;
      }
      if (!hasWoundedNum()) {
        return false;
      }
      if (!hasDiedNum()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.SoliderInfoForReport parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.SoliderInfoForReport) e.getUnfinishedMessage();
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

    private int totalNum_ ;
    /**
     * <pre>
     *总兵力
     * </pre>
     *
     * <code>required int32 totalNum = 2;</code>
     */
    public boolean hasTotalNum() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *总兵力
     * </pre>
     *
     * <code>required int32 totalNum = 2;</code>
     */
    public int getTotalNum() {
      return totalNum_;
    }
    /**
     * <pre>
     *总兵力
     * </pre>
     *
     * <code>required int32 totalNum = 2;</code>
     */
    public Builder setTotalNum(int value) {
      bitField0_ |= 0x00000002;
      totalNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *总兵力
     * </pre>
     *
     * <code>required int32 totalNum = 2;</code>
     */
    public Builder clearTotalNum() {
      bitField0_ = (bitField0_ & ~0x00000002);
      totalNum_ = 0;
      onChanged();
      return this;
    }

    private int woundedNum_ ;
    /**
     * <pre>
     *伤兵量
     * </pre>
     *
     * <code>required int32 woundedNum = 3;</code>
     */
    public boolean hasWoundedNum() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     *伤兵量
     * </pre>
     *
     * <code>required int32 woundedNum = 3;</code>
     */
    public int getWoundedNum() {
      return woundedNum_;
    }
    /**
     * <pre>
     *伤兵量
     * </pre>
     *
     * <code>required int32 woundedNum = 3;</code>
     */
    public Builder setWoundedNum(int value) {
      bitField0_ |= 0x00000004;
      woundedNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *伤兵量
     * </pre>
     *
     * <code>required int32 woundedNum = 3;</code>
     */
    public Builder clearWoundedNum() {
      bitField0_ = (bitField0_ & ~0x00000004);
      woundedNum_ = 0;
      onChanged();
      return this;
    }

    private int diedNum_ ;
    /**
     * <pre>
     *死兵量
     * </pre>
     *
     * <code>required int32 diedNum = 4;</code>
     */
    public boolean hasDiedNum() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     *死兵量
     * </pre>
     *
     * <code>required int32 diedNum = 4;</code>
     */
    public int getDiedNum() {
      return diedNum_;
    }
    /**
     * <pre>
     *死兵量
     * </pre>
     *
     * <code>required int32 diedNum = 4;</code>
     */
    public Builder setDiedNum(int value) {
      bitField0_ |= 0x00000008;
      diedNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *死兵量
     * </pre>
     *
     * <code>required int32 diedNum = 4;</code>
     */
    public Builder clearDiedNum() {
      bitField0_ = (bitField0_ & ~0x00000008);
      diedNum_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.SoliderInfoForReport)
  }

  // @@protoc_insertion_point(class_scope:client2server.SoliderInfoForReport)
  private static final pb4client.SoliderInfoForReport DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.SoliderInfoForReport();
  }

  public static pb4client.SoliderInfoForReport getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<SoliderInfoForReport>
      PARSER = new com.google.protobuf.AbstractParser<SoliderInfoForReport>() {
    public SoliderInfoForReport parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new SoliderInfoForReport(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SoliderInfoForReport> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SoliderInfoForReport> getParserForType() {
    return PARSER;
  }

  public pb4client.SoliderInfoForReport getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

