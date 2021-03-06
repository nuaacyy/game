// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.RestoreCityEndTimeRt}
 */
public  final class RestoreCityEndTimeRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.RestoreCityEndTimeRt)
    RestoreCityEndTimeRtOrBuilder {
  // Use RestoreCityEndTimeRt.newBuilder() to construct.
  private RestoreCityEndTimeRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RestoreCityEndTimeRt() {
    rt_ = 0;
    endTime_ = 0;
    yubeibing_ = 0;
    yubeibingTime_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private RestoreCityEndTimeRt(
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
            endTime_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            yubeibing_ = input.readInt32();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            yubeibingTime_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_RestoreCityEndTimeRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_RestoreCityEndTimeRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.RestoreCityEndTimeRt.class, pb4client.RestoreCityEndTimeRt.Builder.class);
  }

  private int bitField0_;
  public static final int RT_FIELD_NUMBER = 1;
  private int rt_;
  /**
   * <code>required int32 rt = 1;</code>
   */
  public boolean hasRt() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required int32 rt = 1;</code>
   */
  public int getRt() {
    return rt_;
  }

  public static final int ENDTIME_FIELD_NUMBER = 2;
  private int endTime_;
  /**
   * <pre>
   *恢复到点时间,如果不在恢复期间就返回zeroTime
   * </pre>
   *
   * <code>optional int32 endTime = 2;</code>
   */
  public boolean hasEndTime() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *恢复到点时间,如果不在恢复期间就返回zeroTime
   * </pre>
   *
   * <code>optional int32 endTime = 2;</code>
   */
  public int getEndTime() {
    return endTime_;
  }

  public static final int YUBEIBING_FIELD_NUMBER = 3;
  private int yubeibing_;
  /**
   * <pre>
   *上次变化后的预备兵数量
   * </pre>
   *
   * <code>optional int32 yubeibing = 3;</code>
   */
  public boolean hasYubeibing() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   *上次变化后的预备兵数量
   * </pre>
   *
   * <code>optional int32 yubeibing = 3;</code>
   */
  public int getYubeibing() {
    return yubeibing_;
  }

  public static final int YUBEIBINGTIME_FIELD_NUMBER = 4;
  private int yubeibingTime_;
  /**
   * <pre>
   *上次变化预备兵的时间
   * </pre>
   *
   * <code>optional int32 yubeibingTime = 4;</code>
   */
  public boolean hasYubeibingTime() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   *上次变化预备兵的时间
   * </pre>
   *
   * <code>optional int32 yubeibingTime = 4;</code>
   */
  public int getYubeibingTime() {
    return yubeibingTime_;
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
      output.writeInt32(2, endTime_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, yubeibing_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, yubeibingTime_);
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
        .computeInt32Size(2, endTime_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, yubeibing_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, yubeibingTime_);
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
    if (!(obj instanceof pb4client.RestoreCityEndTimeRt)) {
      return super.equals(obj);
    }
    pb4client.RestoreCityEndTimeRt other = (pb4client.RestoreCityEndTimeRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && (hasEndTime() == other.hasEndTime());
    if (hasEndTime()) {
      result = result && (getEndTime()
          == other.getEndTime());
    }
    result = result && (hasYubeibing() == other.hasYubeibing());
    if (hasYubeibing()) {
      result = result && (getYubeibing()
          == other.getYubeibing());
    }
    result = result && (hasYubeibingTime() == other.hasYubeibingTime());
    if (hasYubeibingTime()) {
      result = result && (getYubeibingTime()
          == other.getYubeibingTime());
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
    if (hasEndTime()) {
      hash = (37 * hash) + ENDTIME_FIELD_NUMBER;
      hash = (53 * hash) + getEndTime();
    }
    if (hasYubeibing()) {
      hash = (37 * hash) + YUBEIBING_FIELD_NUMBER;
      hash = (53 * hash) + getYubeibing();
    }
    if (hasYubeibingTime()) {
      hash = (37 * hash) + YUBEIBINGTIME_FIELD_NUMBER;
      hash = (53 * hash) + getYubeibingTime();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.RestoreCityEndTimeRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.RestoreCityEndTimeRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.RestoreCityEndTimeRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.RestoreCityEndTimeRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.RestoreCityEndTimeRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.RestoreCityEndTimeRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.RestoreCityEndTimeRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.RestoreCityEndTimeRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.RestoreCityEndTimeRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.RestoreCityEndTimeRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.RestoreCityEndTimeRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.RestoreCityEndTimeRt parseFrom(
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
  public static Builder newBuilder(pb4client.RestoreCityEndTimeRt prototype) {
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
   * Protobuf type {@code client2server.RestoreCityEndTimeRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.RestoreCityEndTimeRt)
      pb4client.RestoreCityEndTimeRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_RestoreCityEndTimeRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_RestoreCityEndTimeRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.RestoreCityEndTimeRt.class, pb4client.RestoreCityEndTimeRt.Builder.class);
    }

    // Construct using pb4client.RestoreCityEndTimeRt.newBuilder()
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
      endTime_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      yubeibing_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      yubeibingTime_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_RestoreCityEndTimeRt_descriptor;
    }

    public pb4client.RestoreCityEndTimeRt getDefaultInstanceForType() {
      return pb4client.RestoreCityEndTimeRt.getDefaultInstance();
    }

    public pb4client.RestoreCityEndTimeRt build() {
      pb4client.RestoreCityEndTimeRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.RestoreCityEndTimeRt buildPartial() {
      pb4client.RestoreCityEndTimeRt result = new pb4client.RestoreCityEndTimeRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.endTime_ = endTime_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.yubeibing_ = yubeibing_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.yubeibingTime_ = yubeibingTime_;
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
      if (other instanceof pb4client.RestoreCityEndTimeRt) {
        return mergeFrom((pb4client.RestoreCityEndTimeRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.RestoreCityEndTimeRt other) {
      if (other == pb4client.RestoreCityEndTimeRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (other.hasEndTime()) {
        setEndTime(other.getEndTime());
      }
      if (other.hasYubeibing()) {
        setYubeibing(other.getYubeibing());
      }
      if (other.hasYubeibingTime()) {
        setYubeibingTime(other.getYubeibingTime());
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
      pb4client.RestoreCityEndTimeRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.RestoreCityEndTimeRt) e.getUnfinishedMessage();
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
     * <code>required int32 rt = 1;</code>
     */
    public boolean hasRt() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 rt = 1;</code>
     */
    public int getRt() {
      return rt_;
    }
    /**
     * <code>required int32 rt = 1;</code>
     */
    public Builder setRt(int value) {
      bitField0_ |= 0x00000001;
      rt_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 rt = 1;</code>
     */
    public Builder clearRt() {
      bitField0_ = (bitField0_ & ~0x00000001);
      rt_ = 0;
      onChanged();
      return this;
    }

    private int endTime_ ;
    /**
     * <pre>
     *恢复到点时间,如果不在恢复期间就返回zeroTime
     * </pre>
     *
     * <code>optional int32 endTime = 2;</code>
     */
    public boolean hasEndTime() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *恢复到点时间,如果不在恢复期间就返回zeroTime
     * </pre>
     *
     * <code>optional int32 endTime = 2;</code>
     */
    public int getEndTime() {
      return endTime_;
    }
    /**
     * <pre>
     *恢复到点时间,如果不在恢复期间就返回zeroTime
     * </pre>
     *
     * <code>optional int32 endTime = 2;</code>
     */
    public Builder setEndTime(int value) {
      bitField0_ |= 0x00000002;
      endTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *恢复到点时间,如果不在恢复期间就返回zeroTime
     * </pre>
     *
     * <code>optional int32 endTime = 2;</code>
     */
    public Builder clearEndTime() {
      bitField0_ = (bitField0_ & ~0x00000002);
      endTime_ = 0;
      onChanged();
      return this;
    }

    private int yubeibing_ ;
    /**
     * <pre>
     *上次变化后的预备兵数量
     * </pre>
     *
     * <code>optional int32 yubeibing = 3;</code>
     */
    public boolean hasYubeibing() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     *上次变化后的预备兵数量
     * </pre>
     *
     * <code>optional int32 yubeibing = 3;</code>
     */
    public int getYubeibing() {
      return yubeibing_;
    }
    /**
     * <pre>
     *上次变化后的预备兵数量
     * </pre>
     *
     * <code>optional int32 yubeibing = 3;</code>
     */
    public Builder setYubeibing(int value) {
      bitField0_ |= 0x00000004;
      yubeibing_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *上次变化后的预备兵数量
     * </pre>
     *
     * <code>optional int32 yubeibing = 3;</code>
     */
    public Builder clearYubeibing() {
      bitField0_ = (bitField0_ & ~0x00000004);
      yubeibing_ = 0;
      onChanged();
      return this;
    }

    private int yubeibingTime_ ;
    /**
     * <pre>
     *上次变化预备兵的时间
     * </pre>
     *
     * <code>optional int32 yubeibingTime = 4;</code>
     */
    public boolean hasYubeibingTime() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     *上次变化预备兵的时间
     * </pre>
     *
     * <code>optional int32 yubeibingTime = 4;</code>
     */
    public int getYubeibingTime() {
      return yubeibingTime_;
    }
    /**
     * <pre>
     *上次变化预备兵的时间
     * </pre>
     *
     * <code>optional int32 yubeibingTime = 4;</code>
     */
    public Builder setYubeibingTime(int value) {
      bitField0_ |= 0x00000008;
      yubeibingTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *上次变化预备兵的时间
     * </pre>
     *
     * <code>optional int32 yubeibingTime = 4;</code>
     */
    public Builder clearYubeibingTime() {
      bitField0_ = (bitField0_ & ~0x00000008);
      yubeibingTime_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.RestoreCityEndTimeRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.RestoreCityEndTimeRt)
  private static final pb4client.RestoreCityEndTimeRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.RestoreCityEndTimeRt();
  }

  public static pb4client.RestoreCityEndTimeRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<RestoreCityEndTimeRt>
      PARSER = new com.google.protobuf.AbstractParser<RestoreCityEndTimeRt>() {
    public RestoreCityEndTimeRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new RestoreCityEndTimeRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<RestoreCityEndTimeRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RestoreCityEndTimeRt> getParserForType() {
    return PARSER;
  }

  public pb4client.RestoreCityEndTimeRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

