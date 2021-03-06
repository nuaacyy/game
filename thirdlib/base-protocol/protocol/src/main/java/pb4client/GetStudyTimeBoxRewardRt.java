// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.GetStudyTimeBoxRewardRt}
 */
public  final class GetStudyTimeBoxRewardRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.GetStudyTimeBoxRewardRt)
    GetStudyTimeBoxRewardRtOrBuilder {
  // Use GetStudyTimeBoxRewardRt.newBuilder() to construct.
  private GetStudyTimeBoxRewardRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetStudyTimeBoxRewardRt() {
    rt_ = 0;
    timeBoxIndex_ = 0;
    timeBoxReward_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetStudyTimeBoxRewardRt(
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
            timeBoxIndex_ = input.readInt32();
            break;
          }
          case 26: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000004;
            timeBoxReward_ = bs;
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
    return pb4client.War2GamePkt.internal_static_client2server_GetStudyTimeBoxRewardRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_GetStudyTimeBoxRewardRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.GetStudyTimeBoxRewardRt.class, pb4client.GetStudyTimeBoxRewardRt.Builder.class);
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

  public static final int TIMEBOXINDEX_FIELD_NUMBER = 2;
  private int timeBoxIndex_;
  /**
   * <pre>
   * 槽位ID
   * </pre>
   *
   * <code>optional int32 timeBoxIndex = 2;</code>
   */
  public boolean hasTimeBoxIndex() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 槽位ID
   * </pre>
   *
   * <code>optional int32 timeBoxIndex = 2;</code>
   */
  public int getTimeBoxIndex() {
    return timeBoxIndex_;
  }

  public static final int TIMEBOXREWARD_FIELD_NUMBER = 3;
  private volatile java.lang.Object timeBoxReward_;
  /**
   * <pre>
   * 奖励
   * </pre>
   *
   * <code>optional string timeBoxReward = 3;</code>
   */
  public boolean hasTimeBoxReward() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 奖励
   * </pre>
   *
   * <code>optional string timeBoxReward = 3;</code>
   */
  public java.lang.String getTimeBoxReward() {
    java.lang.Object ref = timeBoxReward_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        timeBoxReward_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   * 奖励
   * </pre>
   *
   * <code>optional string timeBoxReward = 3;</code>
   */
  public com.google.protobuf.ByteString
      getTimeBoxRewardBytes() {
    java.lang.Object ref = timeBoxReward_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      timeBoxReward_ = b;
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
      output.writeInt32(2, timeBoxIndex_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, timeBoxReward_);
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
        .computeInt32Size(2, timeBoxIndex_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, timeBoxReward_);
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
    if (!(obj instanceof pb4client.GetStudyTimeBoxRewardRt)) {
      return super.equals(obj);
    }
    pb4client.GetStudyTimeBoxRewardRt other = (pb4client.GetStudyTimeBoxRewardRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && (hasTimeBoxIndex() == other.hasTimeBoxIndex());
    if (hasTimeBoxIndex()) {
      result = result && (getTimeBoxIndex()
          == other.getTimeBoxIndex());
    }
    result = result && (hasTimeBoxReward() == other.hasTimeBoxReward());
    if (hasTimeBoxReward()) {
      result = result && getTimeBoxReward()
          .equals(other.getTimeBoxReward());
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
    if (hasTimeBoxIndex()) {
      hash = (37 * hash) + TIMEBOXINDEX_FIELD_NUMBER;
      hash = (53 * hash) + getTimeBoxIndex();
    }
    if (hasTimeBoxReward()) {
      hash = (37 * hash) + TIMEBOXREWARD_FIELD_NUMBER;
      hash = (53 * hash) + getTimeBoxReward().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.GetStudyTimeBoxRewardRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GetStudyTimeBoxRewardRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GetStudyTimeBoxRewardRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GetStudyTimeBoxRewardRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GetStudyTimeBoxRewardRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GetStudyTimeBoxRewardRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GetStudyTimeBoxRewardRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.GetStudyTimeBoxRewardRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.GetStudyTimeBoxRewardRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.GetStudyTimeBoxRewardRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.GetStudyTimeBoxRewardRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.GetStudyTimeBoxRewardRt parseFrom(
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
  public static Builder newBuilder(pb4client.GetStudyTimeBoxRewardRt prototype) {
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
   * Protobuf type {@code client2server.GetStudyTimeBoxRewardRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.GetStudyTimeBoxRewardRt)
      pb4client.GetStudyTimeBoxRewardRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_GetStudyTimeBoxRewardRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_GetStudyTimeBoxRewardRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.GetStudyTimeBoxRewardRt.class, pb4client.GetStudyTimeBoxRewardRt.Builder.class);
    }

    // Construct using pb4client.GetStudyTimeBoxRewardRt.newBuilder()
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
      timeBoxIndex_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      timeBoxReward_ = "";
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_GetStudyTimeBoxRewardRt_descriptor;
    }

    public pb4client.GetStudyTimeBoxRewardRt getDefaultInstanceForType() {
      return pb4client.GetStudyTimeBoxRewardRt.getDefaultInstance();
    }

    public pb4client.GetStudyTimeBoxRewardRt build() {
      pb4client.GetStudyTimeBoxRewardRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.GetStudyTimeBoxRewardRt buildPartial() {
      pb4client.GetStudyTimeBoxRewardRt result = new pb4client.GetStudyTimeBoxRewardRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.timeBoxIndex_ = timeBoxIndex_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.timeBoxReward_ = timeBoxReward_;
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
      if (other instanceof pb4client.GetStudyTimeBoxRewardRt) {
        return mergeFrom((pb4client.GetStudyTimeBoxRewardRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.GetStudyTimeBoxRewardRt other) {
      if (other == pb4client.GetStudyTimeBoxRewardRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (other.hasTimeBoxIndex()) {
        setTimeBoxIndex(other.getTimeBoxIndex());
      }
      if (other.hasTimeBoxReward()) {
        bitField0_ |= 0x00000004;
        timeBoxReward_ = other.timeBoxReward_;
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
      pb4client.GetStudyTimeBoxRewardRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.GetStudyTimeBoxRewardRt) e.getUnfinishedMessage();
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

    private int timeBoxIndex_ ;
    /**
     * <pre>
     * 槽位ID
     * </pre>
     *
     * <code>optional int32 timeBoxIndex = 2;</code>
     */
    public boolean hasTimeBoxIndex() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 槽位ID
     * </pre>
     *
     * <code>optional int32 timeBoxIndex = 2;</code>
     */
    public int getTimeBoxIndex() {
      return timeBoxIndex_;
    }
    /**
     * <pre>
     * 槽位ID
     * </pre>
     *
     * <code>optional int32 timeBoxIndex = 2;</code>
     */
    public Builder setTimeBoxIndex(int value) {
      bitField0_ |= 0x00000002;
      timeBoxIndex_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 槽位ID
     * </pre>
     *
     * <code>optional int32 timeBoxIndex = 2;</code>
     */
    public Builder clearTimeBoxIndex() {
      bitField0_ = (bitField0_ & ~0x00000002);
      timeBoxIndex_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object timeBoxReward_ = "";
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>optional string timeBoxReward = 3;</code>
     */
    public boolean hasTimeBoxReward() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>optional string timeBoxReward = 3;</code>
     */
    public java.lang.String getTimeBoxReward() {
      java.lang.Object ref = timeBoxReward_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          timeBoxReward_ = s;
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
     * <code>optional string timeBoxReward = 3;</code>
     */
    public com.google.protobuf.ByteString
        getTimeBoxRewardBytes() {
      java.lang.Object ref = timeBoxReward_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        timeBoxReward_ = b;
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
     * <code>optional string timeBoxReward = 3;</code>
     */
    public Builder setTimeBoxReward(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      timeBoxReward_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>optional string timeBoxReward = 3;</code>
     */
    public Builder clearTimeBoxReward() {
      bitField0_ = (bitField0_ & ~0x00000004);
      timeBoxReward_ = getDefaultInstance().getTimeBoxReward();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>optional string timeBoxReward = 3;</code>
     */
    public Builder setTimeBoxRewardBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      timeBoxReward_ = value;
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


    // @@protoc_insertion_point(builder_scope:client2server.GetStudyTimeBoxRewardRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.GetStudyTimeBoxRewardRt)
  private static final pb4client.GetStudyTimeBoxRewardRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.GetStudyTimeBoxRewardRt();
  }

  public static pb4client.GetStudyTimeBoxRewardRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<GetStudyTimeBoxRewardRt>
      PARSER = new com.google.protobuf.AbstractParser<GetStudyTimeBoxRewardRt>() {
    public GetStudyTimeBoxRewardRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new GetStudyTimeBoxRewardRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetStudyTimeBoxRewardRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetStudyTimeBoxRewardRt> getParserForType() {
    return PARSER;
  }

  public pb4client.GetStudyTimeBoxRewardRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

