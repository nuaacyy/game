// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3157
 * 服务器 -&gt; 客户端
 * 据点攻打次数变化主推
 * </pre>
 *
 * Protobuf type {@code client2server.StrongholdCountChange}
 */
public  final class StrongholdCountChange extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.StrongholdCountChange)
    StrongholdCountChangeOrBuilder {
  // Use StrongholdCountChange.newBuilder() to construct.
  private StrongholdCountChange(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private StrongholdCountChange() {
    time_ = 0;
    strongholdLimit_ = 0;
    strongholdCount_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private StrongholdCountChange(
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
            time_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            strongholdLimit_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            strongholdCount_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_StrongholdCountChange_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_StrongholdCountChange_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.StrongholdCountChange.class, pb4client.StrongholdCountChange.Builder.class);
  }

  private int bitField0_;
  public static final int TIME_FIELD_NUMBER = 1;
  private int time_;
  /**
   * <pre>
   *上次结算时间
   * </pre>
   *
   * <code>optional int32 time = 1;</code>
   */
  public boolean hasTime() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *上次结算时间
   * </pre>
   *
   * <code>optional int32 time = 1;</code>
   */
  public int getTime() {
    return time_;
  }

  public static final int STRONGHOLDLIMIT_FIELD_NUMBER = 2;
  private int strongholdLimit_;
  /**
   * <pre>
   *上限
   * </pre>
   *
   * <code>optional int32 strongholdLimit = 2;</code>
   */
  public boolean hasStrongholdLimit() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *上限
   * </pre>
   *
   * <code>optional int32 strongholdLimit = 2;</code>
   */
  public int getStrongholdLimit() {
    return strongholdLimit_;
  }

  public static final int STRONGHOLDCOUNT_FIELD_NUMBER = 3;
  private int strongholdCount_;
  /**
   * <pre>
   *当前次数
   * </pre>
   *
   * <code>optional int32 strongholdCount = 3;</code>
   */
  public boolean hasStrongholdCount() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   *当前次数
   * </pre>
   *
   * <code>optional int32 strongholdCount = 3;</code>
   */
  public int getStrongholdCount() {
    return strongholdCount_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, time_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, strongholdLimit_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, strongholdCount_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, time_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, strongholdLimit_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, strongholdCount_);
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
    if (!(obj instanceof pb4client.StrongholdCountChange)) {
      return super.equals(obj);
    }
    pb4client.StrongholdCountChange other = (pb4client.StrongholdCountChange) obj;

    boolean result = true;
    result = result && (hasTime() == other.hasTime());
    if (hasTime()) {
      result = result && (getTime()
          == other.getTime());
    }
    result = result && (hasStrongholdLimit() == other.hasStrongholdLimit());
    if (hasStrongholdLimit()) {
      result = result && (getStrongholdLimit()
          == other.getStrongholdLimit());
    }
    result = result && (hasStrongholdCount() == other.hasStrongholdCount());
    if (hasStrongholdCount()) {
      result = result && (getStrongholdCount()
          == other.getStrongholdCount());
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
    if (hasTime()) {
      hash = (37 * hash) + TIME_FIELD_NUMBER;
      hash = (53 * hash) + getTime();
    }
    if (hasStrongholdLimit()) {
      hash = (37 * hash) + STRONGHOLDLIMIT_FIELD_NUMBER;
      hash = (53 * hash) + getStrongholdLimit();
    }
    if (hasStrongholdCount()) {
      hash = (37 * hash) + STRONGHOLDCOUNT_FIELD_NUMBER;
      hash = (53 * hash) + getStrongholdCount();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.StrongholdCountChange parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.StrongholdCountChange parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.StrongholdCountChange parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.StrongholdCountChange parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.StrongholdCountChange parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.StrongholdCountChange parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.StrongholdCountChange parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.StrongholdCountChange parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.StrongholdCountChange parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.StrongholdCountChange parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.StrongholdCountChange parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.StrongholdCountChange parseFrom(
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
  public static Builder newBuilder(pb4client.StrongholdCountChange prototype) {
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
   * msgType = 3157
   * 服务器 -&gt; 客户端
   * 据点攻打次数变化主推
   * </pre>
   *
   * Protobuf type {@code client2server.StrongholdCountChange}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.StrongholdCountChange)
      pb4client.StrongholdCountChangeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_StrongholdCountChange_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_StrongholdCountChange_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.StrongholdCountChange.class, pb4client.StrongholdCountChange.Builder.class);
    }

    // Construct using pb4client.StrongholdCountChange.newBuilder()
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
      time_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      strongholdLimit_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      strongholdCount_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_StrongholdCountChange_descriptor;
    }

    public pb4client.StrongholdCountChange getDefaultInstanceForType() {
      return pb4client.StrongholdCountChange.getDefaultInstance();
    }

    public pb4client.StrongholdCountChange build() {
      pb4client.StrongholdCountChange result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.StrongholdCountChange buildPartial() {
      pb4client.StrongholdCountChange result = new pb4client.StrongholdCountChange(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.time_ = time_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.strongholdLimit_ = strongholdLimit_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.strongholdCount_ = strongholdCount_;
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
      if (other instanceof pb4client.StrongholdCountChange) {
        return mergeFrom((pb4client.StrongholdCountChange)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.StrongholdCountChange other) {
      if (other == pb4client.StrongholdCountChange.getDefaultInstance()) return this;
      if (other.hasTime()) {
        setTime(other.getTime());
      }
      if (other.hasStrongholdLimit()) {
        setStrongholdLimit(other.getStrongholdLimit());
      }
      if (other.hasStrongholdCount()) {
        setStrongholdCount(other.getStrongholdCount());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.StrongholdCountChange parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.StrongholdCountChange) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int time_ ;
    /**
     * <pre>
     *上次结算时间
     * </pre>
     *
     * <code>optional int32 time = 1;</code>
     */
    public boolean hasTime() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *上次结算时间
     * </pre>
     *
     * <code>optional int32 time = 1;</code>
     */
    public int getTime() {
      return time_;
    }
    /**
     * <pre>
     *上次结算时间
     * </pre>
     *
     * <code>optional int32 time = 1;</code>
     */
    public Builder setTime(int value) {
      bitField0_ |= 0x00000001;
      time_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *上次结算时间
     * </pre>
     *
     * <code>optional int32 time = 1;</code>
     */
    public Builder clearTime() {
      bitField0_ = (bitField0_ & ~0x00000001);
      time_ = 0;
      onChanged();
      return this;
    }

    private int strongholdLimit_ ;
    /**
     * <pre>
     *上限
     * </pre>
     *
     * <code>optional int32 strongholdLimit = 2;</code>
     */
    public boolean hasStrongholdLimit() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *上限
     * </pre>
     *
     * <code>optional int32 strongholdLimit = 2;</code>
     */
    public int getStrongholdLimit() {
      return strongholdLimit_;
    }
    /**
     * <pre>
     *上限
     * </pre>
     *
     * <code>optional int32 strongholdLimit = 2;</code>
     */
    public Builder setStrongholdLimit(int value) {
      bitField0_ |= 0x00000002;
      strongholdLimit_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *上限
     * </pre>
     *
     * <code>optional int32 strongholdLimit = 2;</code>
     */
    public Builder clearStrongholdLimit() {
      bitField0_ = (bitField0_ & ~0x00000002);
      strongholdLimit_ = 0;
      onChanged();
      return this;
    }

    private int strongholdCount_ ;
    /**
     * <pre>
     *当前次数
     * </pre>
     *
     * <code>optional int32 strongholdCount = 3;</code>
     */
    public boolean hasStrongholdCount() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     *当前次数
     * </pre>
     *
     * <code>optional int32 strongholdCount = 3;</code>
     */
    public int getStrongholdCount() {
      return strongholdCount_;
    }
    /**
     * <pre>
     *当前次数
     * </pre>
     *
     * <code>optional int32 strongholdCount = 3;</code>
     */
    public Builder setStrongholdCount(int value) {
      bitField0_ |= 0x00000004;
      strongholdCount_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *当前次数
     * </pre>
     *
     * <code>optional int32 strongholdCount = 3;</code>
     */
    public Builder clearStrongholdCount() {
      bitField0_ = (bitField0_ & ~0x00000004);
      strongholdCount_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.StrongholdCountChange)
  }

  // @@protoc_insertion_point(class_scope:client2server.StrongholdCountChange)
  private static final pb4client.StrongholdCountChange DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.StrongholdCountChange();
  }

  public static pb4client.StrongholdCountChange getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<StrongholdCountChange>
      PARSER = new com.google.protobuf.AbstractParser<StrongholdCountChange>() {
    public StrongholdCountChange parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new StrongholdCountChange(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<StrongholdCountChange> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<StrongholdCountChange> getParserForType() {
    return PARSER;
  }

  public pb4client.StrongholdCountChange getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
