// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.FetchServerTimeRt}
 */
public  final class FetchServerTimeRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.FetchServerTimeRt)
    FetchServerTimeRtOrBuilder {
  // Use FetchServerTimeRt.newBuilder() to construct.
  private FetchServerTimeRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private FetchServerTimeRt() {
    rt_ = 0;
    reqTime_ = 0L;
    time_ = 0L;
    timeZoneName_ = "";
    timeZoneValue_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private FetchServerTimeRt(
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
            reqTime_ = input.readInt64();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            time_ = input.readInt64();
            break;
          }
          case 34: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000008;
            timeZoneName_ = bs;
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            timeZoneValue_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_FetchServerTimeRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_FetchServerTimeRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.FetchServerTimeRt.class, pb4client.FetchServerTimeRt.Builder.class);
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

  public static final int REQTIME_FIELD_NUMBER = 2;
  private long reqTime_;
  /**
   * <code>optional int64 reqTime = 2;</code>
   */
  public boolean hasReqTime() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>optional int64 reqTime = 2;</code>
   */
  public long getReqTime() {
    return reqTime_;
  }

  public static final int TIME_FIELD_NUMBER = 3;
  private long time_;
  /**
   * <code>optional int64 time = 3;</code>
   */
  public boolean hasTime() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <code>optional int64 time = 3;</code>
   */
  public long getTime() {
    return time_;
  }

  public static final int TIMEZONENAME_FIELD_NUMBER = 4;
  private volatile java.lang.Object timeZoneName_;
  /**
   * <pre>
   *时区名字
   * </pre>
   *
   * <code>optional string timeZoneName = 4;</code>
   */
  public boolean hasTimeZoneName() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   *时区名字
   * </pre>
   *
   * <code>optional string timeZoneName = 4;</code>
   */
  public java.lang.String getTimeZoneName() {
    java.lang.Object ref = timeZoneName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        timeZoneName_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   *时区名字
   * </pre>
   *
   * <code>optional string timeZoneName = 4;</code>
   */
  public com.google.protobuf.ByteString
      getTimeZoneNameBytes() {
    java.lang.Object ref = timeZoneName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      timeZoneName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int TIMEZONEVALUE_FIELD_NUMBER = 5;
  private int timeZoneValue_;
  /**
   * <pre>
   *时区偏移值
   * </pre>
   *
   * <code>optional int32 timeZoneValue = 5;</code>
   */
  public boolean hasTimeZoneValue() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <pre>
   *时区偏移值
   * </pre>
   *
   * <code>optional int32 timeZoneValue = 5;</code>
   */
  public int getTimeZoneValue() {
    return timeZoneValue_;
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
      output.writeInt64(2, reqTime_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt64(3, time_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, timeZoneName_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt32(5, timeZoneValue_);
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
        .computeInt64Size(2, reqTime_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, time_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, timeZoneName_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, timeZoneValue_);
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
    if (!(obj instanceof pb4client.FetchServerTimeRt)) {
      return super.equals(obj);
    }
    pb4client.FetchServerTimeRt other = (pb4client.FetchServerTimeRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && (hasReqTime() == other.hasReqTime());
    if (hasReqTime()) {
      result = result && (getReqTime()
          == other.getReqTime());
    }
    result = result && (hasTime() == other.hasTime());
    if (hasTime()) {
      result = result && (getTime()
          == other.getTime());
    }
    result = result && (hasTimeZoneName() == other.hasTimeZoneName());
    if (hasTimeZoneName()) {
      result = result && getTimeZoneName()
          .equals(other.getTimeZoneName());
    }
    result = result && (hasTimeZoneValue() == other.hasTimeZoneValue());
    if (hasTimeZoneValue()) {
      result = result && (getTimeZoneValue()
          == other.getTimeZoneValue());
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
    if (hasReqTime()) {
      hash = (37 * hash) + REQTIME_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getReqTime());
    }
    if (hasTime()) {
      hash = (37 * hash) + TIME_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getTime());
    }
    if (hasTimeZoneName()) {
      hash = (37 * hash) + TIMEZONENAME_FIELD_NUMBER;
      hash = (53 * hash) + getTimeZoneName().hashCode();
    }
    if (hasTimeZoneValue()) {
      hash = (37 * hash) + TIMEZONEVALUE_FIELD_NUMBER;
      hash = (53 * hash) + getTimeZoneValue();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.FetchServerTimeRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.FetchServerTimeRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.FetchServerTimeRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.FetchServerTimeRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.FetchServerTimeRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.FetchServerTimeRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.FetchServerTimeRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.FetchServerTimeRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.FetchServerTimeRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.FetchServerTimeRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.FetchServerTimeRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.FetchServerTimeRt parseFrom(
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
  public static Builder newBuilder(pb4client.FetchServerTimeRt prototype) {
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
   * Protobuf type {@code client2server.FetchServerTimeRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.FetchServerTimeRt)
      pb4client.FetchServerTimeRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_FetchServerTimeRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_FetchServerTimeRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.FetchServerTimeRt.class, pb4client.FetchServerTimeRt.Builder.class);
    }

    // Construct using pb4client.FetchServerTimeRt.newBuilder()
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
      reqTime_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      time_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000004);
      timeZoneName_ = "";
      bitField0_ = (bitField0_ & ~0x00000008);
      timeZoneValue_ = 0;
      bitField0_ = (bitField0_ & ~0x00000010);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_FetchServerTimeRt_descriptor;
    }

    public pb4client.FetchServerTimeRt getDefaultInstanceForType() {
      return pb4client.FetchServerTimeRt.getDefaultInstance();
    }

    public pb4client.FetchServerTimeRt build() {
      pb4client.FetchServerTimeRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.FetchServerTimeRt buildPartial() {
      pb4client.FetchServerTimeRt result = new pb4client.FetchServerTimeRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.reqTime_ = reqTime_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.time_ = time_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.timeZoneName_ = timeZoneName_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.timeZoneValue_ = timeZoneValue_;
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
      if (other instanceof pb4client.FetchServerTimeRt) {
        return mergeFrom((pb4client.FetchServerTimeRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.FetchServerTimeRt other) {
      if (other == pb4client.FetchServerTimeRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (other.hasReqTime()) {
        setReqTime(other.getReqTime());
      }
      if (other.hasTime()) {
        setTime(other.getTime());
      }
      if (other.hasTimeZoneName()) {
        bitField0_ |= 0x00000008;
        timeZoneName_ = other.timeZoneName_;
        onChanged();
      }
      if (other.hasTimeZoneValue()) {
        setTimeZoneValue(other.getTimeZoneValue());
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
      pb4client.FetchServerTimeRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.FetchServerTimeRt) e.getUnfinishedMessage();
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

    private long reqTime_ ;
    /**
     * <code>optional int64 reqTime = 2;</code>
     */
    public boolean hasReqTime() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int64 reqTime = 2;</code>
     */
    public long getReqTime() {
      return reqTime_;
    }
    /**
     * <code>optional int64 reqTime = 2;</code>
     */
    public Builder setReqTime(long value) {
      bitField0_ |= 0x00000002;
      reqTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int64 reqTime = 2;</code>
     */
    public Builder clearReqTime() {
      bitField0_ = (bitField0_ & ~0x00000002);
      reqTime_ = 0L;
      onChanged();
      return this;
    }

    private long time_ ;
    /**
     * <code>optional int64 time = 3;</code>
     */
    public boolean hasTime() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional int64 time = 3;</code>
     */
    public long getTime() {
      return time_;
    }
    /**
     * <code>optional int64 time = 3;</code>
     */
    public Builder setTime(long value) {
      bitField0_ |= 0x00000004;
      time_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int64 time = 3;</code>
     */
    public Builder clearTime() {
      bitField0_ = (bitField0_ & ~0x00000004);
      time_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object timeZoneName_ = "";
    /**
     * <pre>
     *时区名字
     * </pre>
     *
     * <code>optional string timeZoneName = 4;</code>
     */
    public boolean hasTimeZoneName() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     *时区名字
     * </pre>
     *
     * <code>optional string timeZoneName = 4;</code>
     */
    public java.lang.String getTimeZoneName() {
      java.lang.Object ref = timeZoneName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          timeZoneName_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *时区名字
     * </pre>
     *
     * <code>optional string timeZoneName = 4;</code>
     */
    public com.google.protobuf.ByteString
        getTimeZoneNameBytes() {
      java.lang.Object ref = timeZoneName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        timeZoneName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *时区名字
     * </pre>
     *
     * <code>optional string timeZoneName = 4;</code>
     */
    public Builder setTimeZoneName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
      timeZoneName_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *时区名字
     * </pre>
     *
     * <code>optional string timeZoneName = 4;</code>
     */
    public Builder clearTimeZoneName() {
      bitField0_ = (bitField0_ & ~0x00000008);
      timeZoneName_ = getDefaultInstance().getTimeZoneName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *时区名字
     * </pre>
     *
     * <code>optional string timeZoneName = 4;</code>
     */
    public Builder setTimeZoneNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
      timeZoneName_ = value;
      onChanged();
      return this;
    }

    private int timeZoneValue_ ;
    /**
     * <pre>
     *时区偏移值
     * </pre>
     *
     * <code>optional int32 timeZoneValue = 5;</code>
     */
    public boolean hasTimeZoneValue() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     *时区偏移值
     * </pre>
     *
     * <code>optional int32 timeZoneValue = 5;</code>
     */
    public int getTimeZoneValue() {
      return timeZoneValue_;
    }
    /**
     * <pre>
     *时区偏移值
     * </pre>
     *
     * <code>optional int32 timeZoneValue = 5;</code>
     */
    public Builder setTimeZoneValue(int value) {
      bitField0_ |= 0x00000010;
      timeZoneValue_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *时区偏移值
     * </pre>
     *
     * <code>optional int32 timeZoneValue = 5;</code>
     */
    public Builder clearTimeZoneValue() {
      bitField0_ = (bitField0_ & ~0x00000010);
      timeZoneValue_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.FetchServerTimeRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.FetchServerTimeRt)
  private static final pb4client.FetchServerTimeRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.FetchServerTimeRt();
  }

  public static pb4client.FetchServerTimeRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<FetchServerTimeRt>
      PARSER = new com.google.protobuf.AbstractParser<FetchServerTimeRt>() {
    public FetchServerTimeRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new FetchServerTimeRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<FetchServerTimeRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<FetchServerTimeRt> getParserForType() {
    return PARSER;
  }

  public pb4client.FetchServerTimeRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
